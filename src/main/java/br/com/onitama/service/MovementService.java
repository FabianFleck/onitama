package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.enumeration.BattleResultEnum;
import br.com.onitama.model.response.BattleResponse;
import br.com.onitama.model.response.PositionResponse;
import br.com.onitama.model.entity.*;
import br.com.onitama.model.enumeration.ColorEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static br.com.onitama.model.enumeration.ColorEnum.BLUE;
import static br.com.onitama.model.enumeration.ColorEnum.RED;

@Service
public class MovementService {

    private final CardService cardService;
    private final PlayerService playerService;
    private final PartService partService;
    private final BattleService battleService;
    private final TokenService tokenService;

    public MovementService(CardService cardService, PlayerService playerService, PartService partService, BattleService battleService, TokenService tokenService) {
        this.cardService = cardService;
        this.playerService = playerService;
        this.partService = partService;
        this.battleService = battleService;
        this.tokenService = tokenService;
    }

    public List<PositionResponse> getPossibleMoves(String username, PositionPart position, Long playerId, Long cardId) {
        PlayerEntity player = playerService.findById(playerId);
        tokenService.isAuthorization(player.getUser().getUsername(), username);

        PartEntity partToMove = partService.findPartAtPosition(player.getParts(), position);

        CardEntity card = cardService.findById(player, cardId);

        return calculatePossibleMoves(partToMove, card, player)
                .stream().map(positionPart -> new PositionResponse(positionPart.getLine(), positionPart.getColumn()))
                .toList();
    }

    public List<PositionPart> calculatePossibleMoves(PartEntity part, CardEntity card, PlayerEntity player) {
        if (part == null) {
            throw new UnprocessableEntityException("Peça não encontrada na posição atual.");
        }

        final PositionPart currentPosition = part.getPosition();

        List<PositionPart> possibleMoves = new ArrayList<>();
        int lineDirection = player.getColor() == ColorEnum.BLUE ? 1 : -1;
        int columnDirection = player.getColor() == RED ? 1 : -1;

        for (PositionEntity move : card.getPositions()) {
            int newLine = currentPosition.getLine() + (move.getLine() * lineDirection);
            int newColumn = currentPosition.getColumn() + (move.getColumn() * columnDirection);

            if (isValidPosition(newLine, newColumn) && !isPositionOccupied(newLine, newColumn, player.getParts())) {
                possibleMoves.add(new PositionPart(newLine, newColumn));
            }
        }
        return possibleMoves;
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 1 && line <= 5 && column >= 1 && column <= 5;
    }

    private boolean isPositionOccupied(int newLine, int newColumn, List<PartEntity> parts) {
        for (PartEntity part : parts) {
            PositionPart position = part.getPosition();
            if (position.getLine() == newLine && position.getColumn() == newColumn) {
                return true;
            }
        }
        return false;
    }

    private boolean areCardsDrawn(PlayerEntity player) {
        BattleEntity battle = battleService.findByPlayer(player);
        return battle.getTableCard() != null
                && battle.getPlayer1() != null
                && battle.getPlayer2() != null
                && battle.getPlayer1().getCard1() != null
                && battle.getPlayer1().getCard2() != null
                && battle.getPlayer2().getCard1() != null
                && battle.getPlayer2().getCard2() != null;
    }

    @Transactional
    public PositionPart move(String username, PositionPart currentPositionPart, PositionPart newPositionPart, Long playerId, Long cardId) {
        PlayerEntity player = playerService.findById(playerId);
        tokenService.isAuthorization(player.getUser().getUsername(), username);

        BattleEntity battle = battleService.findByPlayerId(playerId);

        if (battle.getResult() == BattleResultEnum.OPEN) {
            if (!areCardsDrawn(player)) {
                throw new UnprocessableEntityException("As cartas precisam ser sorteadas antes de fazer um movimento.");
            }

            if (!isPlayerTurn(battle, playerId)) {
                throw new UnprocessableEntityException("Não é o seu turno.");
            }

            CardEntity usedCard = cardService.findById(player, cardId);

            PartEntity partToMove = partService.findPartAtPosition(player.getParts(), currentPositionPart);

            List<PositionPart> possibleMoves = calculatePossibleMoves(partToMove, usedCard, player);

            if (!possibleMoves.contains(newPositionPart)) {
                throw new UnprocessableEntityException("Movimento inválido.");
            }

            partToMove.setPosition(newPositionPart);
            PartEntity part = partService.save(partToMove);

            boolean masterCaptured = partService.captureOpponentPartAtPosition(player, part.getPosition());

            cardService.swapCardsWithTable(player, usedCard);

            BattleResponse battleResponse = battleService.nextPlayer(player.getBattle().getId());

            if (masterCaptured || partTemplePosition(part, player.getColor())) {
                battleResponse = battleService.updateResult(battleResponse.getId(), player.getColor() == RED ? BattleResultEnum.RED : BattleResultEnum.BLUE);
            }

            battleService.notifyBattleUpdates(battleResponse);

            return partToMove.getPosition();
        }
        throw new UnprocessableEntityException("Jogo finalizado. " + player.getUser().getUsername() + " venceu!");
    }

    private boolean partTemplePosition(PartEntity part, ColorEnum color) {
        int line = RED == color ? 5 : 1;
        int column = 3;
        return part.getPosition().getLine() == line && part.getPosition().getColumn() == column;
    }

    private boolean isPlayerTurn(BattleEntity battle, Long playerId) {
        PlayerEntity currentPlayer = battle.getCurrentPlayer() == RED ? battle.getPlayer1() : battle.getPlayer2();
        return currentPlayer != null && currentPlayer.getId().equals(playerId);
    }
}
