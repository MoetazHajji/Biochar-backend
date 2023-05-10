package tn.esprit.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,  FilterAuthentificate filterAuthentificate) {
        return builder.routes()




                .route("analysis-service", r -> r.path("/biochar/analysis/**").uri("lb://analysis-service"))

                .route("internship-service", r -> r.path("/biochar/internship/**")
                        //.filters(f -> f.filter(filterAuthentificate.apply( new FilterAuthentificate.Config())))
                        .uri("lb://internship-service"))


                .route("stock-service", r -> r.path("/biochar/stock-service/**")
                        //.filters(f -> f.filter(filterAuthentificate.apply( new FilterAuthentificate.Config())))
                        .uri("lb://stock-service"))



                .route("training-service", r -> r.path("/biochar/training/**").uri("lb://training-service"))
                .route("hr-service", r -> r.path("/biochar/hr/**").uri("lb://hr-service"))


                .route("user-service", r -> r.path("/biochar/user-service/**").uri("lb://user-service"))
                .route("mail-service", r -> r.path("/biochar/mail-service/**")
                       // .filters(f -> f.filter(filterAuthentificate.apply( new FilterAuthentificate.Config())))
                        .uri("lb://mail-service"))


                .route("discovery-server", r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**") .uri("http://localhost:8761"))


                .build();
    }

}


