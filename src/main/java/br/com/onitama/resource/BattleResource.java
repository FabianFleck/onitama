package br.com.onitama.resource;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.response.BattleSimpleResponse;
import br.com.onitama.service.BattleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<BattleEntity> createBattle(Authentication authentication, @RequestParam ColorEnum color) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.createBattle(username, color));
    }

    @GetMapping
    public ResponseEntity<List<BattleSimpleResponse>> findAllByUserUsername(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.findAllByUserUsername(username));
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<BattleEntity> findById(@PathVariable("battleId") String battleId) {
        return ResponseEntity.ok(service.findById(battleId));
    }

    @PostMapping("/{battleId}")
    public ResponseEntity<BattleEntity> joinBattle(Authentication authentication, @PathVariable("battleId") String battleId) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.joinBattle(battleId, username));
    }
}
