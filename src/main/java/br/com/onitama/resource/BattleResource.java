package br.com.onitama.resource;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.service.BattleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Battle")
@RequestMapping("/api/battle")
public class BattleResource {

    private final BattleService service;

    public BattleResource(BattleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BattleEntity> createBattle(@RequestParam String username) {
        return ResponseEntity.ok(service.createBattle(username));
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<BattleEntity> findById(@PathVariable("battleId") String battleId) {
        return ResponseEntity.ok(service.findById(battleId));
    }

    @PostMapping("/{battleId}")
    public ResponseEntity<BattleEntity> joinBattle(@PathVariable("battleId") String battleId, @RequestParam String username) {
        return ResponseEntity.ok(service.joinBattle(battleId, username));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<BattleEntity>> findAllByUserUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.findAllByUserUsername(username));
    }
}
