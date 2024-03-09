package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import it.unicam.cs.ids2324.project.Backend.Model.Ruolo;
import it.unicam.cs.ids2324.project.Backend.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configurazione della sicurezza dell'applicazione Spring.
 * Definisce i ruoli necessari per accedere a determinate risorse e configura l'autenticazione e
 * l'autorizzazione per le richieste HTTP.
 *
 * Metodi:
 * - `managerFunzionalità(HttpSecurity http)`: Configura la sicurezza per gestire le richieste HTTP.
 *   Definisce i ruoli necessari per accedere a determinate risorse e specifica che le richieste
 *   verso "/auth/**" sono accessibili a tutti, mentre le altre richieste richiedono autenticazione.
 *   Disabilita la protezione CSRF e aggiunge il filtro per la gestione dell'autenticazione JWT.
 * - `autenticazioneJWT()`: Restituisce un bean dell'oggetto `AutenticazioneJWT`, responsabile
 *   della gestione dell'autenticazione JWT.
 * - `providerAutenticazione()`: Restituisce un bean di tipo `AuthenticationProvider` per la
 *   configurazione dell'autenticazione.
 * - `managerAutenticazione(AuthenticationConfiguration config)`: Restituisce un bean per la
 *   gestione dell'autenticazione, ottenuto dalla configurazione di autenticazione.
 * - `criptaPassword()`: Restituisce un bean per la codifica e decodifica delle password utilizzando
 *   l'algoritmo bcrypt.
 */

@Configuration
@EnableWebSecurity
public class Sicurezza {

    @Autowired
    private PersonaService personaService;

    @Bean
    public SecurityFilterChain managerFunzionalità(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/curatore/**").hasAuthority(Ruolo.CUR.name())
                .requestMatchers("/contributor/**").hasAuthority(Ruolo.CONTR.name())
                .requestMatchers("/contributoraut/**").hasAnyAuthority(Ruolo.CONTRAUT.name(), Ruolo.CUR.name())
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/view/**").permitAll()
                .anyRequest().authenticated()
        );
        http.authenticationProvider(providerAutenticazione());
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(this.autenticazioneJWT(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AutenticazioneJWT autenticazioneJWT() {
        return new AutenticazioneJWT();
    }

    @Bean
    public AuthenticationProvider providerAutenticazione() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(personaService);
        authenticationProvider.setPasswordEncoder(criptaPassword());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager managerAutenticazione(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder criptaPassword() {
        return new BCryptPasswordEncoder();
    }
}
