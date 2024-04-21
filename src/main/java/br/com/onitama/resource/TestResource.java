package br.com.onitama.resource;

import br.com.onitama.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestResource {

    @GetMapping
    public ResponseEntity<Part> test() {
        Position position = new Position(3, 'a');
        Part part = new Part(position, PartType.MASTER);
        return ResponseEntity.ok(part);
    }

    @GetMapping("/player")
    public ResponseEntity<Player> player() {
        //Cria player
        Player player = new Player();

        //Adiciona peça a lista
        ArrayList<Part> parts = new ArrayList<>();

        for (int line = 1; line <=5; line++) {
            Position position = new Position(line, 'a');
            Part part = new Part(position, line == 3 ? PartType.MASTER : PartType.DISCIPLE);
            parts.add(part);
        }

        player.setName("Gilberto");
        player.setColor(PlayerColor.BLUE);
        player.setPart(parts);

        return ResponseEntity.ok(player);
    }
}
