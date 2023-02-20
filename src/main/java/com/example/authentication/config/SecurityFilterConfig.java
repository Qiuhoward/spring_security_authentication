package com.example.authentication.config;

import com.example.authentication.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <過濾器管理鍊配置>
 */
@Configuration
@EnableWebSecurity //用來啟用Spring Security所需的各項配置。
public class SecurityFilterConfig  {
    private final AuthenticationConfig authenticationConfig;
    private final JwtAuthenticationFilter filter;

    public SecurityFilterConfig(AuthenticationConfig authenticationConfig, JwtAuthenticationFilter filter) {
        this.authenticationConfig = authenticationConfig;
        this.filter = filter;
    }

    /**
     *<過濾條件相關配置>
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationConfig.authenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
