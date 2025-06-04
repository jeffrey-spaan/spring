package com.example.backend.common.audit;

import com.example.backend.security.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * AuditorBeanConfig class
 * This class is used to configure the AuditorAware bean
 *
 * @since alpha 0.0.1
 * @see <a href="https://docs.spring.io/spring-data/jpa/reference/auditing.html">Spring Data JPA Auditing</a>
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareProvider")
public class AuditorBeanConfig {

    @Bean
    public AuditorAware<User> auditorAwareProvider() {
        return new AuditorAwareImpl();
    }
}