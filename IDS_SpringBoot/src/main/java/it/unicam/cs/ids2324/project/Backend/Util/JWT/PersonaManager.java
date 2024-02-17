package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import it.unicam.cs.ids2324.project.Backend.Model.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe che implementa l'interfaccia `UserDetails` di Spring Security.
 * Gestisce le informazioni relative all'utente `Persona` e fornisce metodi per ottenere
 * dettagli quali autorità, nome utente, password e lo stato dell'account. Questa classe
 * consente all'applicazione Spring Security di interagire con l'oggetto `Persona`.
 *
 * Metodi:
 * - `getAuthorities()`: Restituisce la lista di autorità dell'utente, convertendo il ruolo
 *   dell'utente in un oggetto `SimpleGrantedAuthority`.
 * - `getUsername()`: Restituisce il nome dell'utente.
 * - `getPassword()`: Restituisce la password dell'utente.
 */
public class PersonaManager implements UserDetails {
    private final Persona persona;

    public PersonaManager(Persona persona) {
        this.persona = persona;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(persona.getRuolo().toString()).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() { return this.persona.getNome(); }

    @Override
    public String getPassword() {
        return this.persona.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;}
}
