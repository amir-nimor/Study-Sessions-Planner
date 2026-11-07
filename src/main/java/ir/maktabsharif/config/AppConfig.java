package ir.maktabsharif.config;

import com.auth0.jwt.JWT;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan("ir.maktabsharif")
public class AppConfig {

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(
                "UserCash",
                "SubjectCache",
                "StudySessionCoach"
        );
    }

    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/viwes/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWT jwtService() {
        return new JWT();
    }


}
