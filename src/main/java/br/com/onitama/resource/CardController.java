package br.com.onitama.resource;

import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Tag(name = "Card")
@RequestMapping("/api/card")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardEntity>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
