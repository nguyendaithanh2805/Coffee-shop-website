package org.example.coffeeshopwebsite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login", "user/css/**", "/user/js/**", "/user/images/**", "/home", "/menu", "/about", "/blog", "/contact").permitAll() // Cho phép truy cập không cần xác thực
                        .requestMatchers("/admin", "/admin/**"/*, "/admin/css/**", "/admin/js/**", "/admin/images/**"*/).hasAuthority("ADMIN") // Yêu cầu quyền ADMIN cho /admin/**
                        .anyRequest().authenticated() // Yêu cầu xác thực cho tất cả các yêu cầu khác
                )
                .formLogin(login -> login
                        .loginPage("/admin/login") // Trang đăng nhập
                        .loginProcessingUrl("/admin/login") // URL xử lý đăng nhập
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/admin/dashboard", true) // Chuyển hướng sau khi đăng nhập thành công
                        .failureUrl("/admin/login?error") // Chuyển hướng nếu đăng nhập thất bại
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/admin/login") // Chuyển hướng sau khi đăng xuất
                );
        return httpSecurity.build();
    }
}
