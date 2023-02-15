package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InternshipServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InternshipServiceApplication.class, args);
    }
}