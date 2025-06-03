package dev.destiny.afrorusbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AfroRusBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfroRusBackendApplication.class, args);
    }

}
