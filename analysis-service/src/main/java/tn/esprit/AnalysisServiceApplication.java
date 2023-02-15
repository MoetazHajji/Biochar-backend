package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AnalysisServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalysisServiceApplication.class, args);
    }
}