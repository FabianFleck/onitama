package br.com.onitama.resource;

import br.com.onitama.model.response.PositionResponse;
import br.com.onitama.model.entity.PositionPart;
import br.com.onitama.service.CardService;
import br.com.onitama.service.MovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Movement")
@RequestMapping("/api/movement")
public class MovementResource {

    private final CardService cardService;
    private final MovementService service;

    public MovementResource(CardService cardService, MovementService service) {
        this.cardService = cardService;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PositionPart> move(Authentication authentication,
                                             @RequestParam int line,
                                             @RequestParam int column,
                                             @RequestParam int lineNew,
                                             @RequestParam int columnNew,
                                             @RequestParam Long playerId,
                                             @RequestParam Long cardId) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.move(username,
                new PositionPart(line, column),
                new PositionPart(lineNew, columnNew),
                playerId,
                cardId));
    }

    @GetMapping("/possible")
    public ResponseEntity<List<PositionResponse>> getPossibleMoves(Authentication authentication,
                                                                   @RequestParam int line,
                                                                   @RequestParam int column,
                                                                   @RequestParam Long playerId,
                                                                   @RequestParam Long cardId) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.getPossibleMoves(username, new PositionPart(line, column), playerId, cardId));
    }
}
