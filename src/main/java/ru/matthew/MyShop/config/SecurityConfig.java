package ru.matthew.MyShop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.matthew.MyShop.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .requestMatchers("/advertisments/*").permitAll()
                        .requestMatchers("/static/*").permitAll()
                        .anyRequest().permitAll()
                )
                .userDetailsService(customUserDetailsService)
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/advertisments/index")
                )
                .formLogin(formLogin -> formLogin
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/auth/signin")
                        .loginProcessingUrl("/auth/signin")
                        .failureUrl("/auth/login-error")
                        .defaultSuccessUrl("/advertisments/index", true))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
