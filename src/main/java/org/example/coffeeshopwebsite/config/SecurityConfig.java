package org.example.coffeeshopwebsite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        };
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login", "/admin/save-register", "/admin/register", "/admin/css/**", "/admin/js/**", "/admin/images/**", "/user/css/**", "/user/js/**", "/user/images/**", "/home", "/menu", "/about", "/blog", "/contact").permitAll() // Cho phép truy cập không cần xác thực
                        .requestMatchers("/admin", "/admin/**"/*, "/admin/css/**", "/admin/js/**", "/admin/images/**"*/).hasAuthority("ADMIN") // Yêu cầu quyền ADMIN cho /admin/**
                        .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các yêu cầu khác
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login") // URL xử lý đăng nhập,  Khi người dùng điền thông tin đăng nhập vào form và nhấn nút "Login", dữ liệu sẽ được gửi đến URL /admin/login để Spring Security xử lý việc xác thực.
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/admin/login?error") // Chuyển hướng nếu đăng nhập thất bại
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/admin/login") // Chuyển hướng sau khi đăng xuất
                )
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
        return httpSecurity.build();
    }
}
