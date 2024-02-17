package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Model.Persona;
import it.unicam.cs.ids2324.project.Backend.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controllore per la gestione delle informazioni relative agli utenti.
 *
 * Offre un unico endpoint per recuperare i dettagli di un utente in base alla sua mail.
 *
 * Si avvale del servizio `PersonaService` per recuperare i dati dell'utente e convertire la sua entità in un DTO (Data Transfer Object) adatto alla risposta.
 *
 * Può restituire un codice di stato 404 (NOT FOUND) se l'utente non viene trovato.
 */

@RestController
@RequestMapping("/users")
public class ControllerPersona {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/userDetails/{mail}")
    public ResponseEntity<Object> getPersonaDetails(@PathVariable("mail") String mail) {
        Optional<Persona> persona = this.personaService.getPersona(mail);
        return persona.<ResponseEntity<Object>>map(value -> ResponseEntity.ok(this.personaService.
                convertToDTO(Optional.of(value)))).orElseGet(() -> ResponseEntity.status(404).body("Persona not found"));}


}
