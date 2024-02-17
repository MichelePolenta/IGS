package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

/**
 * Classe che gestisce la generazione e la validazione dei token JWT (JSON Web Token).
 * Utilizza la libreria 'io.jsonwebtoken' per la manipolazione dei token JWT.
 * La chiave segreta e il tempo di scadenza del token sono configurati tramite le annotazioni @Value.
 * Fornisce metodi per generare un nuovo token con le informazioni specificate, validare la firma e
 * recuperare le informazioni dal token. Inoltre, fornisce un metodo per estrarre il token dall'intestazione
 * di autorizzazione di una richiesta HTTP.
 *
 * Metodi:
 * - `getKey()`: Restituisce la chiave segreta utilizzata per firmare e verificare i token.
 * - `genera(String mail, String ruolo)`: Genera un nuovo token con la mail e il ruolo specificati.
 * - `validazioneToken(String token)`: Verifica se la firma del token è valida e se il token è scaduto.
 * - `getTokenRichiesta(HttpServletRequest request)`: Estrae il token dall'intestazione di autorizzazione
 *   di una richiesta HTTP.
 * - `getIdToken(String token)`: Estrae l'ID utente dal token.
 * - `getMailToken(String token)`: Estrae la mail dall'intestazione del token.
 */
@Component
public class GeneraJWT {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    private final Logger logger = LoggerFactory.getLogger(GeneraJWT.class);

    public Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public String genera(String mail, String ruolo) {
        return Jwts.builder()
                .claim("mail", mail)
                .claim("ruolo", ruolo)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }

    public boolean validazioneToken(String token) {
        try {
            Jwts.parser().setSigningKey(getKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getTokenRichiesta(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getIdToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token)
                .getPayload().get("id").toString();
    }

    public String getMailToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token)
                .getPayload().get("mail").toString();
    }

}