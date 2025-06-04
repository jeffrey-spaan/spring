package com.example.backend.common.audit;

import com.example.backend.security.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Component}: Marks this class as a Spring component.</li>
 * </ul>
 *
 * @since alpha 0.0.1
 */
@Component
public class AuditorAwareImpl implements AuditorAware<User> {

    /**
     * This method retrieves the username of the current auditor from the Spring Security context.
     *
     * @return an Optional containing the username of the current auditor, or an empty Optional if the authentication is not valid.
     */
    @Override
    public Optional<User> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    }
}