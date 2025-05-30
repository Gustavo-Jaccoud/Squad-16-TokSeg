package com.tokseg.storage.infra.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Permitir acesso aberto a Swagger e documentação
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Permitir acesso aberto aos endpoints de autenticação
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/recoverpassword").permitAll()

                        // Condominium-controller - só admin
                        .requestMatchers(HttpMethod.GET, "/api/v1/condominium/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/condominium").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/condominium/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/condominium/**").hasRole("ADMIN")

                        // Compartment-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/compartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/compartment").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/compartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/compartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/compartment/firstAvailable").hasRole("RESIDENT")
                        .requestMatchers(HttpMethod.GET, "/api/v1/compartment/getByIdCabinet/**").hasRole("ADMIN")

                        // Cabinet-controller - admin
                        .requestMatchers(HttpMethod.GET, "/api/v1/cabinet/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cabinet").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cabinet/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cabinet/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cabinet/getByIdCondominium/**").hasRole("ADMIN")

                        // Block-controller - admin
                        .requestMatchers(HttpMethod.GET, "/api/v1/block/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/block").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/block/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/block/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/block/getByIdCondominium/**").hasRole("ADMIN")

                        // Apartment-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/apartment/getByIdUser/**").hasAnyRole("ADMIN", "RESIDENT")
                        .requestMatchers(HttpMethod.GET, "/api/v1/apartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/apartment").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/apartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/apartment/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/apartment/getByIdBlock/**").hasRole("ADMIN")

                        // Delivery-person-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliveryperson").hasAnyRole("ADMIN", "DELIVERYPERSON")
                        .requestMatchers(HttpMethod.POST, "/api/v1/deliveryperson").hasAnyRole("ADMIN", "DELIVERYPERSON")
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliveryperson/**").authenticated()

                        // Delivery-package-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/deliverypackage").hasAnyRole("ADMIN", "RESIDENT")
                        .requestMatchers(HttpMethod.POST, "/api/v1/deliverypackage").hasRole("DELIVERYPERSON")
                        .requestMatchers(HttpMethod.POST, "/api/v1/deliverypackage/pickUpDeliveryPackage").hasRole("RESIDENT")

                        // Notification-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/notification").hasRole("ADMIN")

                        // User-controller
                        .requestMatchers(HttpMethod.GET, "/api/v1/user").hasRole("ADMIN")

                        // Qualquer outra requisição deve estar autenticada
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

        @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
