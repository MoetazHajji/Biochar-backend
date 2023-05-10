package tn.esprit.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.Mappers.AttachmentMapper;

@Configuration
public class MyConfigInitParameters {
    @Value("${myApp.hostPathUserService}")
    private String hostPathUserService;
    @Bean
    public String configure() {
        AttachmentMapper.host_ContextPath = hostPathUserService;
        return "configured";
    }

}