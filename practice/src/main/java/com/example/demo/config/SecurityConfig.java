package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AdminRepository adminRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/contact/**", "/admin/signup", "/admin/signin", "/css/**", "/js/**").permitAll()
                .requestMatchers("/admin/**").authenticated()
            )
            .formLogin(form -> form
                .loginPage("/admin/signin")              
                .loginProcessingUrl("/admin/signin")
                .defaultSuccessUrl("/admin/contacts", true)
                .failureUrl("/admin/signin?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/signin?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminRepository.findAll().stream()
                .filter(a -> a.getEmail().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("メールアドレスが登録されていません。"));

            return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}