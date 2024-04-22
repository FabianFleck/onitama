package br.com.onitama.resource;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.service.BattleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Battle")
@RequestMapping("/api/babttle")
public class BattleResource {

    private final BattleService service;

    public BattleResource(BattleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BattleEntity> createBattle(@RequestParam String name) {
        return ResponseEntity.ok(service.createBattle(name));
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<BattleEntity> findById(@PathVariable("battleId") String battleId) {
        return ResponseEntity.ok(service.findById(battleId));
    }

    @PostMapping("/{battleId}")
    public ResponseEntity<BattleEntity> joinBattle(@PathVariable("battleId") String battleId, @RequestParam String name) {
        return ResponseEntity.ok(service.joinBattle(battleId, name));
    }
}
