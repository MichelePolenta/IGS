package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import it.unicam.cs.ids2324.project.Backend.Service.PersonaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

/**
 * Filtro che gestisce l'autenticazione JWT per ogni richiesta. Estende OncePerRequestFilter
 * di Spring Security.
 * Questo filtro viene eseguito una volta per ogni richiesta HTTP e si occupa di verificare la presenza
 * e la validità del token JWT nell'intestazione della richiesta. Se il token è valido, estrae i dettagli
 * dell'utente associato e crea un oggetto di autenticazione, che viene poi impostato nel contesto di sicurezza
 * di Spring Security. In questo modo, l'utente viene considerato autenticato e può accedere alle risorse
 * protette dall'autenticazione JWT.
 */
@Component
public class AutenticazioneJWT extends OncePerRequestFilter {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private GeneraJWT generaJwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.generaJwt.getTokenRichiesta(request);
        if (token != null && this.generaJwt.validazioneToken(token)) {
            UserDetails userDetails = this.personaService.loadUserByUsername(this.generaJwt.getMailToken(token));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            "", userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
