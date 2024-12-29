package com.spring.henallux.firstSpringProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_REQUEST = "/connexion";
    private static final String[] AUTHORIZED_REQUESTS_ANYBODY = {
            "/",
            "/css/**",
            "/js/**",
            "/images/**",
            "/connexion/**",
            "/inscription/**",
            "/categories/**",
            "/product/**",
            "/a-propos",
            "/azur",
            "/panier/**",
    };

    private final UserDetailsService userDetailsServiceImpl;

    @Autowired
    public WebSecurityConfiguration(UserDetailsService userDetailsServiceImplementation) {
        this.userDetailsServiceImpl = userDetailsServiceImplementation;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Désactiver CSRF pour le développement uniquement
                .authorizeRequests()
                .antMatchers(AUTHORIZED_REQUESTS_ANYBODY).permitAll() // Accès public
                .antMatchers("/admin/**").hasRole("ADMIN") // Accès restreint pour les administrateurs
                .antMatchers("/commande/**").authenticated() // Nécessite une connexion pour passer commande
                .anyRequest().authenticated() // Authentification requise pour tout le reste
                .and()
                .formLogin()
                .loginPage("/connexion") // Page de connexion personnalisée
                .loginProcessingUrl("/login") // URL où Spring Security traite l'authentification
                .defaultSuccessUrl("/azur") // Redirection après succès
                .failureUrl("/connexion?error=true") // Redirection en cas d'échec
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/azur")
                .permitAll();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }
}