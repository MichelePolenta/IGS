package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Exception.CredenzialiException;
import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Exception.RichiestaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe per la gestione delle eccezioni lanciate dai controller dell'applicazione backend.
 *
 * Mappa le eccezioni specifiche del dominio a messaggi di errore e codici di stato HTTP appropriati.
 *
 * Le eccezioni gestite:
 *
 * - POIException: indica un errore relativo ai POI (punti di interesse).
 * - ItinerarioException: indica un errore relativo agli itinerari.
 * - CredenzialiException: indica un errore relativo alle credenziali di autenticazione.
 * - RichiestaException: indica un errore relativo alle richieste relative ai Poi ed agli itinerari.
 *
 * Tutte le eccezioni vengono mappate con il codice di stato BAD_REQUEST (400) e ad un messaggio di errore leggibile dall'utente.
 *
 */

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(POIException.class)
    public ResponseEntity<String> poiException(POIException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItinerarioException.class)
    public ResponseEntity<String> itinerarioException(ItinerarioException itinerarioException){
        return new ResponseEntity<>(itinerarioException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredenzialiException.class)
    public ResponseEntity<String> credenzialiException(CredenzialiException credenzialiException){
        return new ResponseEntity<>(credenzialiException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RichiestaException.class)
    public ResponseEntity<String> richiestaException(RichiestaException richiestaException){
        return new ResponseEntity<>(richiestaException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
