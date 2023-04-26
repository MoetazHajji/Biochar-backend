package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages = "tn.esprit")
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "tn.esprit")
public class AnalysisServiceApplication  extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
    }
    public static void main(String[] args) {
        SpringApplication.run(AnalysisServiceApplication.class, args);
    }
}