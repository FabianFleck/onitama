package br.com.onitama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class OnitamaApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OnitamaApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
