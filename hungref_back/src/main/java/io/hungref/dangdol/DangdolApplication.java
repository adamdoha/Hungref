package io.hungref.dangdol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DangdolApplication {
    public static void main(String[] args) {
        SpringApplication.run(DangdolApplication.class, args);
    }

}
