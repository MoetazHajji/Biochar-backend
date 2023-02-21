package tn.esprit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableAspectJAutoProxy
public class TrainingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }
}