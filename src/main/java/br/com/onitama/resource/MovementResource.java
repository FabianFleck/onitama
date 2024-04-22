package br.com.onitama.resource;

import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.Position;
import br.com.onitama.service.CardService;
import br.com.onitama.service.MovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/possible")
    public ResponseEntity<List<Position>> getPossibleMoves(@RequestParam int line, @RequestParam int column, @RequestParam Long playerId, @RequestParam Long cardId) {
        return ResponseEntity.ok(service.getPossibleMoves(line, column, playerId, cardId));
    }
}
