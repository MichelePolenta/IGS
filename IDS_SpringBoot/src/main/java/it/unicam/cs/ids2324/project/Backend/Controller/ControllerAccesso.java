package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Exception.CredenzialiException;
import it.unicam.cs.ids2324.project.Backend.Util.JWT.GeneraJWT;
import it.unicam.cs.ids2324.project.Backend.Util.JWT.Credenziali;
import it.unicam.cs.ids2324.project.Backend.Util.JWT.DTOManager;
import it.unicam.cs.ids2324.project.Backend.Model.Persona;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceComune;
import it.unicam.cs.ids2324.project.Backend.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * Controller per la gestione dell'autenticazione e della registrazione degli utenti.
 *
 * Offre i seguenti endpoint:
 *
 *  - /login: Autentica un utente e restituisce un token JWT e le informazioni dell'utente.
 *  - /signup: Registra un nuovo utente nel sistema.
 *
 *  Si avvale di diverse dipendenze iniettate per:
 *
 *  - Autenticazione (AuthenticationManager)
 *  - Codifica password (PasswordEncoder)
 *  - Generazione token JWT (GeneraJWT)
 *  - Gestione utenti (PersonaService)
 *  - Recupero informazioni comuni (ResourceComune)
 */


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class ControllerAccesso {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final GeneraJWT generaJwt;
    private final PersonaService personaService;
    private final ResourceComune resourceComune;


    @Autowired
    public ControllerAccesso(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          GeneraJWT generaJwt,
                          PersonaService personaService, ResourceComune resourceComune) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.generaJwt = generaJwt;
        this.personaService = personaService;
        this.resourceComune = resourceComune;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Credenziali credentials) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getMail(), credentials.getPassword()));
            DTOManager DTOManager = this.personaService.convertToDTO(this.personaService.getPersona(credentials.getMail()));
            String token = this.generaJwt.genera(DTOManager.getEmail(), DTOManager.getRuolo().name());
            return ResponseEntity.ok().headers(authorizationHeaders(token)).body(DTOManager);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali errate : " + e.getLocalizedMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody Persona persona, @RequestHeader("nomeComune") String nomeComune) throws Exception{
        try {
            persona.setPassword(this.passwordEncoder.encode(persona.getPassword()));
            persona.setComune(resourceComune.findComuneByNome(nomeComune));
            this.personaService.addPersona(persona);
            return ResponseEntity.ok("Persona aggiunta");
        } catch (CredenzialiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    private HttpHeaders authorizationHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
    }


}
