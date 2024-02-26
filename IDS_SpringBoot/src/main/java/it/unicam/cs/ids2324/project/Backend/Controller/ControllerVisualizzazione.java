package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Service.ComuneService;
import it.unicam.cs.ids2324.project.Backend.Service.VisualizzazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controllore per la visualizzazione di informazioni relative a punti di interesse (POI)
 * e itinerari all'interno dell'applicazione.
 *
 * Offre i seguenti endpoint:
 * - Recupero di tutti i POI disponibili (GET /allPoi)
 * - Recupero di tutti gli itinerari disponibili (GET /allItinerari)
 *
 * Si avvale del servizio `VisualizzazioneService` per la gestione dei POI e itinerari,
 * e di altri servizi iniettati per operazioni aggiuntive.
 *
 * Le risposte sono gestite attraverso oggetti `ResponseEntity`,
 * restituendo dati e stati HTTP appropriati come HttpStatus.CREATED e HttpStatus.OK.
 * La dipendenza da servizi come `ComuneService` e `VisualizzazioneService` suggerisce
 * che la logica di business e l'accesso ai dati sono delegati a servizi separati.
 */

@RestController
@RequestMapping("/visualizza")
public class ControllerVisualizzazione {

    @Autowired ComuneService comuneService;

    @Autowired
    private VisualizzazioneService visualizzazioneService;


    @GetMapping("/allPoi")
    public ResponseEntity<List<POI>> getAllPoi(){
        return new ResponseEntity<>(visualizzazioneService.getAllPoi(), HttpStatus.OK);
    }


    @GetMapping("/allItinerari")
    public ResponseEntity<List<Itinerario>> getAllItinerari(){
        return new ResponseEntity<>(visualizzazioneService.getAllItinerari(), HttpStatus.OK);
    }

    @GetMapping("/singlePoi/{id}")
    public ResponseEntity<POI> getSinglePoi(@PathVariable("id") int id){
        return  ResponseEntity.ok(visualizzazioneService.getSinglePoi(id));
    }



    @GetMapping("/singleIti/{id}")
    public ResponseEntity<Itinerario> getSingleIti(@PathVariable("id")int id){
        return  ResponseEntity.ok(visualizzazioneService.getSingleItinerario(id));
    }
}
