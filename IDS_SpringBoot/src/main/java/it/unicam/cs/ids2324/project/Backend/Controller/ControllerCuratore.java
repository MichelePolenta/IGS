package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Exception.RichiestaException;
import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.ModificaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import it.unicam.cs.ids2324.project.Backend.Model.TipiRichieste;
import it.unicam.cs.ids2324.project.Backend.Service.CuratoreService;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle richieste da parte dei curatori
 *
 * Eredita dal `ControllerContributorAut`, quindi offre gli stessi endpoint per la gestione di POI e itinerari,
 * ma in più ha endpoint specifici per accettare o rifiutare le richieste di modifica.
 *
 * Si avvale del servizio `CuratoreService` per effettuare le operazioni sulle richieste.
 *
 * Può sollevare eccezioni di tipo `RichiestaException` in caso di errori durante la gestione delle richieste.
 */

@RestController
@RequestMapping("/curatore")
public class ControllerCuratore extends ControllerContributorAut{

    @Autowired
    private CuratoreService curatoreService;

    @PutMapping ("/accettaInserimentoPoi/{id}")
    public ResponseEntity<Object> accettaRichiestaInserimentoPoi(@PathVariable("id") Integer idRichiesta){
        try{
            RichiestaPoi inserisciPoi = curatoreService.getRichiestaPoiById(idRichiesta);
            curatoreService.accettaRichistaDiInserimento(inserisciPoi);
            return new ResponseEntity<>(inserisciPoi, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/accettaInserimentoItinerario/{id}")
    public ResponseEntity<Object> accettaRichiestaInserimentoItinerario(@PathVariable("id") Integer idRichiesta){
        try{
        RichiestaItinerario richiestaItinerario = curatoreService.getRichiestaItinerarioById(idRichiesta);
        curatoreService.accettaRichistaDiInserimento(richiestaItinerario);
        return new ResponseEntity<>(richiestaItinerario, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("accettaEliminazioneItinerario/{id}")
    public ResponseEntity<Object> accettaRichiestaEliminazioneItinerario(@PathVariable("id") Integer idRichiesta){
        try{
            RichiestaItinerario richiestaItinerario = curatoreService.getRichiestaItinerarioById(idRichiesta);
            curatoreService.accettaRichiestaEliminazione(richiestaItinerario);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("accettaEliminazionePoi/{id}")
    public ResponseEntity<Object> accettaRichiestaEliminazionePoi(@PathVariable("id") Integer idRichiesta){
        try{
            RichiestaPoi richiestaPoi = curatoreService.getRichiestaPoiById(idRichiesta);
            curatoreService.accettaRichistaDiEliminazione(richiestaPoi);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/accettaModificaItinerario/{id}")
    public ResponseEntity<Object> accettaRichiestaModificaItinerario(@PathVariable("id") Integer idRichiesta){
        try{
            ModificaItinerario richiestaItinerario =  (ModificaItinerario) curatoreService.getRichiestaItinerarioById(idRichiesta);
            curatoreService.accettaRichiestaDiModifica(richiestaItinerario);
            return new ResponseEntity<>(richiestaItinerario, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

            @PutMapping("/accettaModificaPoi/{id}")
    public ResponseEntity<Object> accettaRichiestaModificaPoi(@PathVariable("id") Integer idRichiesta){
        try {
        RichiestaPoi richiestaPoi = curatoreService.getRichiestaPoiById(idRichiesta);
        curatoreService.accettaRichiestaModifica(richiestaPoi); return new ResponseEntity<>(richiestaPoi, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/rifiutaInserimentoPoi/{id}")
    public ResponseEntity<Object> rifiutaInserimentoPoi(@PathVariable("id") Integer idRichiesta){
        try {
        RichiestaPoi richiestaPoi = curatoreService.getRichiestaPoiById(idRichiesta);
        curatoreService.rifiutaRichistaDiInserimento(richiestaPoi);
        return new ResponseEntity<>(richiestaPoi, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("rifiutaInserimentoItinerario/{id}")
    public ResponseEntity<Object> rifiutaInserimentoItinerario(@PathVariable("id") Integer idRichiesta){
        try{
        RichiestaItinerario richiestaItinerario = curatoreService.getRichiestaItinerarioById(idRichiesta);
        curatoreService.rifiutaRichiestaInserimento(richiestaItinerario);
        return new ResponseEntity<>(richiestaItinerario, HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/rifiutaEliminazionePoi/{id}")
    public ResponseEntity<Object> rifiutaEliminazionePoi(@PathVariable("id") Integer idRichiesta){
        try{
        RichiestaPoi richiestaPoi = curatoreService.getRichiestaPoiById(idRichiesta);
        curatoreService.rifiutaRichistaDiEliminazione(richiestaPoi);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/rifiutaEliminazioneItinerario/{id}")
    public ResponseEntity<Object> rifiutaEliminazioneItinerario(@PathVariable("id") Integer idRichiesta){
        try{
        RichiestaItinerario richiestaItinerario = curatoreService.getRichiestaItinerarioById(idRichiesta);
        curatoreService.rifiutaRichistaDiEliminazione(richiestaItinerario);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/rifiutaModificaPoi/{id}")
    public ResponseEntity<Object> rifiutaModificaPoi(@PathVariable("id") Integer idRichiesta){
        try {
            RichiestaPoi richiestaPoi = curatoreService.getRichiestaPoiById(idRichiesta);
            curatoreService.rifiutaRichiestaModifica(richiestaPoi);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/rifiutaModificaItinerario/{id}")
    public ResponseEntity<Object> rifiutaModificaItinerario(@PathVariable("id") Integer idRichiesta){
        try {
            RichiestaItinerario richiestaItinerario = curatoreService.getRichiestaItinerarioById(idRichiesta);
            curatoreService.rifiutaRichistaDiModifica(richiestaItinerario);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (RichiestaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    @GetMapping("/singlePoi/{id}")
    public ResponseEntity<POI> getSinglePoi(@PathVariable("id") int id){
        return  ResponseEntity.ok(curatoreService.getSinglePoi(id));
    }

    @Override
    @GetMapping("/singleIti/{id}")
    public ResponseEntity<Itinerario> getSingleIti(@PathVariable("id")int id){
        return  ResponseEntity.ok(curatoreService.getSingleItinerario(id));
    }

    @GetMapping("/showRichiestePoi/{stato}")
    public ResponseEntity<List<RichiestaPoi>> getRichiestaPoiByTipo(@PathVariable("stato") Stati stato){
        return new ResponseEntity<>(curatoreService.getRichiestaPoiByStato(stato), HttpStatus.OK);
    }

    @GetMapping("/showRichiesteItinerario/{stato}")
    public ResponseEntity<List<RichiestaItinerario>> getRichiestaItinerarioByStato(@PathVariable("stato") Stati stato){
        return new ResponseEntity<>(curatoreService.getRichiestaItinerarioByStato(stato), HttpStatus.OK);
    }


    

}
