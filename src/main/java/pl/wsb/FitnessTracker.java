package pl.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.wsb")
public class FitnessTracker {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
    }

}
