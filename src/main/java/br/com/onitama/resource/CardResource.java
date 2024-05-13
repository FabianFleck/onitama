package br.com.onitama.resource;

import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.model.response.BattleResponse;
import br.com.onitama.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Card")
@RequestMapping("/api/card")
public class CardResource {

    private final CardService service;

    public CardResource(CardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CardEntity>> getAllCards() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardEntity> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/distribute")
    public ResponseEntity<BattleResponse> distributeCards(@RequestParam String battleId) {
        return ResponseEntity.ok(service.distributeCards(battleId));
    }
}
