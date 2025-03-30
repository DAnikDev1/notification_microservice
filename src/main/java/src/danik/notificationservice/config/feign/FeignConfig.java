package src.danik.notificationservice.config.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import src.danik.notificationservice.config.user.UserContext;

@Configuration
public class FeignConfig {
    @Bean
    public FeignUserInterceptor feignUserInterceptor(UserContext userContext) {
        return new FeignUserInterceptor(userContext);
    }
}
