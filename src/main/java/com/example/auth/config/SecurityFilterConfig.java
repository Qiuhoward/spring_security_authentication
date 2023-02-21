package com.example.auth.config;

import com.example.auth.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <security_filter_chain_config>
 */
@Configuration
@EnableWebSecurity //enable security config
public class SecurityFilterConfig  {
    private final AuthenticationConfig authenticationConfig;
    private final JwtAuthenticationFilter filter;

    public SecurityFilterConfig(AuthenticationConfig authenticationConfig, JwtAuthenticationFilter filter) {
        this.authenticationConfig = authenticationConfig;
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((request)->
                        request.requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui/index.html#/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationConfig.authenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                ;

        return http.build();
    }
}
