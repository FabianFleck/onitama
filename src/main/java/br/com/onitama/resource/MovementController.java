package br.com.onitama.resource;

import br.com.onitama.model.Position;
import br.com.onitama.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movement")
public class MovementController {

    private final CardService cardService;

    public MovementController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/moves/{line}/{column}")
    public ResponseEntity<List<Position>> getPossibleMoves(@PathVariable("line") int line, @PathVariable("column") int column) {
        return ResponseEntity.ok(cardService.getPossibleMoves(line, column));
    }
}
