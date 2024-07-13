package org.example.coffeeshopwebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return ((request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        });
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/register/**", "/admin/css/**",
                                "/admin/js/**", "/admin/images/**", "/user/css/**",
                                "/user/js/**", "/user/images/**", "/home",
                                "/menu", "/about", "/blog", "/contact", "/access-denied").permitAll()
                        .requestMatchers("/cart/add").hasAuthority("ROLE_USER")
                        .requestMatchers("/admin", "/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/login?error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                )
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
        return httpSecurity.build();
    }
}
