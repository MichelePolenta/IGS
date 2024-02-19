package it.unicam.cs.ids2324.project.Backend.Controller;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Model.*;
import it.unicam.cs.ids2324.project.Backend.Service.ContributorService;
import it.unicam.cs.ids2324.project.Backend.Util.ManagerOSM;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mapbox.geojson.Point;
import java.util.List;

/**
 * Controller per la gestione delle operazioni di contribuzione da parte dei contributor non autorizzati.
 *
 * Offre i seguenti endpoint:
 * - Inserimento di POI fisici e logici (POST /inserisciPoiFisico, /inserisciPoiLogico)
 *  - Eliminazione di POI (DELETE /eliminaPoi/{id})
 *  - Modifica di POI fisici e logici (PUT /modificaPuntoFisico/{id}, /modificaPuntoLogico/{id})
 *  - Inserimento e modifica di itinerari (POST /inserisciItinerario/{nomeComune}, PUT /modificaItinerario/{id})
 *  - Eliminazione di itinerari (DELETE /eliminaItinerario/{id})
 *  - Recupero di singolo POI per ID (GET /singlePoi/{id})
 *  - Recupero di singolo itinerario per ID (GET /singleIti/{id})
 *
 *  Si avvale del servizio `ContributorService` per le operazioni di gestione,
 *  del servizio `ManagerOSM` per verificare la posizione dei POI fisici,
 *  e di altri servizi iniettati per la gestione dei comuni e la validazione dei dati.
 *
 *  Può sollevare eccezioni di tipo `POIException` e `ItinerarioException` in caso di errori.
 */


@RestController
@RequestMapping("/contributor")
public class ControllerContributor extends ControllerVisualizzazione{

    @Autowired
    private ContributorService contributorService;

    @PostMapping("/inserisciPoiFisico/{nomeComune}")
    public ResponseEntity<Object> inserisciPoiFisico(@PathVariable("nomeComune") String comune, @RequestBody PuntoFisico puntoFisico) throws Exception{
        try{
           if (new ManagerOSM().internoAlComune(puntoFisico.getPoint(), comuneService.findComuneByNome(comune))) {
               contributorService.insertPoiFisico(puntoFisico, comune);
               return new ResponseEntity<>(puntoFisico, HttpStatus.CREATED);
           }
           else
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @PostMapping("/inserisciPoiLogico/{idPoiRiferimento}")
    public ResponseEntity<Object> inserisciPoiLogico(@RequestBody PuntoLogico puntoLogico
                                                     , @PathVariable("idPoiRiferimento")Integer idPoiRiferimento)throws Exception{
        try {
            contributorService.insertPoiLogico(puntoLogico, idPoiRiferimento);
        return new ResponseEntity<>(puntoLogico, HttpStatus.CREATED);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminaPoi/{id}")
    public ResponseEntity<Object> eliminaPoi(@PathVariable("id") int idPoi)throws Exception{
        try{
        POI poi = contributorService.getSinglePoi(idPoi);
        contributorService.eliminaPoi(poi);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/modificaPuntoFisico/{id}")
    public ResponseEntity<Object> modificaPuntoFisico(@RequestBody POI modifiedPoi, @PathVariable("id")int idPoi) throws Exception{
        try{
            if(new ManagerOSM().internoAlComune(modifiedPoi.getPoint(),contributorService.getSinglePoi(idPoi).getComune())) {
                contributorService.modifyPoi(idPoi, modifiedPoi);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/modificaPuntoLogico/{id}")
    public ResponseEntity<Object> modificaPuntoLogico(@RequestBody POI modifiedPoi, @PathVariable("id") int idPoi,
                                                      @RequestHeader("idPoiRiferimento") int idPoiRiferimento )throws Exception{
        try {
            modifiedPoi = contributorService.adaptPoi(modifiedPoi, contributorService.getSinglePoi(idPoiRiferimento));
            contributorService.modifyPoi(idPoi, modifiedPoi);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/inserisciItinerario/{nomeComune}")
    public ResponseEntity<Object> inserisciItinerario(@PathVariable("nomeComune")String nome, @RequestBody Itinerario itinerario,
                                                      @Size(min = 2, message = "La lista dei poi deve avere almeno di 2 elementi!")
                                                      @RequestHeader(value = "idPois")  List<Integer>idPois)throws Exception{
        try {
            contributorService.insertItinerario(itinerario,  nome, idPois);
            return new ResponseEntity<>(itinerario, HttpStatus.CREATED);
        } catch (ItinerarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminaItinerario/{id}")
    public ResponseEntity<Object> eliminaItinerario(@PathVariable("id") int id)throws Exception{
        try{
        Itinerario itinerario = contributorService.getSingleItinerario(id);
        contributorService.eliminaItinerario(itinerario);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItinerarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/modificaItinerario/{id}")
    public ResponseEntity<Object> modificaItinerario(@PathVariable("id") int idSelectedItinerario,@RequestBody Itinerario backup,
                                                     @Size(min = 2, message = "La lista idPois deve avere almeno di 2 elementi!")
                                                     @RequestHeader("idPois") List<Integer> idPois)throws Exception{
        try{
        contributorService.modifyItinerario(idSelectedItinerario, backup, idPois);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItinerarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
