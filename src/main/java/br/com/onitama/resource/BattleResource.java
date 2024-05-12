package br.com.onitama.resource;

import br.com.onitama.mapper.BattleMapper;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.response.BattleResponse;
import br.com.onitama.model.response.BattleSimpleResponse;
import br.com.onitama.service.BattleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@Tag(name = "Battle")
@RequestMapping("/api/battle")
public class BattleResource {

    private final BattleService service;
    private final BattleMapper mapper;

    public BattleResource(BattleService service, BattleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/stream-battles")
    public SseEmitter streamBattles(Authentication authentication) {
        String username = authentication.getName();
        return service.streamBattles(username);
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
    public ResponseEntity<BattleResponse> findById(@PathVariable("battleId") String battleId) {
        return ResponseEntity.ok(mapper.toBattleResponse(service.findById(battleId)));
    }

    @PostMapping("/{battleId}")
    public ResponseEntity<BattleEntity> joinBattle(Authentication authentication, @PathVariable("battleId") String battleId) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.joinBattle(battleId, username));
    }
}
