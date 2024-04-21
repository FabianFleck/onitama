package br.com.onitama.resource;

import br.com.onitama.model.Position;
import br.com.onitama.model.Part;
import br.com.onitama.model.PartType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestResource {

    @GetMapping
    public ResponseEntity<Part> test() {
        Position position = new Position(3, 'a');
        Part part = new Part(position, PartType.MASTER);
        return ResponseEntity.ok(part);
    }
}
