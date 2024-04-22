package br.com.onitama.resource;

import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.Position;
import br.com.onitama.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Movement")
@RequestMapping("/api/movement")
public class MovementController {

    private final CardService cardService;

    public MovementController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/moves")
    public ResponseEntity<List<Position>> getPossibleMoves(@RequestParam int line, @RequestParam int column, @RequestParam ColorEnum colorEnum) {
        return ResponseEntity.ok(cardService.getPossibleMoves(line, column, colorEnum));
    }
}
