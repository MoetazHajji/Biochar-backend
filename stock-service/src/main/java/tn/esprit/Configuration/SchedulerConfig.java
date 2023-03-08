package tn.esprit.Configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tn.esprit.Service.ScrapperService;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulerConfig {
    private TaskExecutor taskExecutor;

    /*@Scheduled(fixedDelay = 10000)
    public void scheduledTask(){
        taskExecutor.execute(new ScrapperService());
    }*/

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }
}
