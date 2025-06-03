package montebello.buguei.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "montebello.buguei")
public class BugueiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugueiApplication.class, args);
    }
}
