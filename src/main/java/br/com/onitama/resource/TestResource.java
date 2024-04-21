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
        Position position = new Position(3, 1);
        Part part = new Part(position, PartType.MASTER);
        return ResponseEntity.ok(part);
    }

    @GetMapping("/player")
    public ResponseEntity<Player> player() {
        //Cria player
        Player player = new Player();

        //Adiciona peça a lista
        ArrayList<Part> parts = new ArrayList<>();

        for (int columnIndex = 1; columnIndex <= 5; columnIndex++) {
            Position position = new Position(5, columnIndex);  // Todas as peças começam na linha 5, colunas de 1 a 5
            PartType partType = (columnIndex == 3) ? PartType.MASTER : PartType.DISCIPLE;  // O mestre está na coluna 3
            Part part = new Part(position, partType);
            parts.add(part);
        }

        player.setName("Gilberto");
        player.setColor(PlayerColor.BLUE);
        player.setPart(parts);

        return ResponseEntity.ok(player);
    }
}
