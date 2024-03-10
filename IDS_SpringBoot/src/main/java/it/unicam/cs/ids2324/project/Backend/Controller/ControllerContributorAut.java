package it.unicam.cs.ids2324.project.Backend.Controller;

import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Model.*;
import it.unicam.cs.ids2324.project.Backend.Service.ContributorAutService;
import it.unicam.cs.ids2324.project.Backend.Util.ManagerOSM;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle operazioni di contribuzione  da parte dei contributor autorizzati.
 *
 * Offre gli stessi endpoint del `ControllerContributor`.
 *
 * Si avvale del servizio `ContributorAutService` per le operazioni di gestione,
 * del  `ManagerOSM` per verificare la posizione dei POI fisici,
 * e di altri servizi iniettati per la gestione dei comuni e la validazione dei dati.
 *
 * Può sollevare eccezioni di tipo `POIException` e `ItinerarioException` in caso di errori.
 */

@RestController
@RequestMapping("/contributoraut")
public class  ControllerContributorAut extends ControllerVisualizzazione{

    @Autowired
    private  ContributorAutService contributorAutService;

    @PostMapping("/inserisciPoiFisico/{nomeComune}")
    public ResponseEntity<Object> inserisciPoiFisico(@PathVariable("nomeComune") String comune,@RequestBody PuntoFisico puntoFisico) throws Exception {
        try{
            puntoFisico.setComune(contributorAutService.getComuneByNome(comune));
            if(new ManagerOSM().internoAlComune(puntoFisico.getPoint(), comuneService.findComuneByNome(comune))){
                contributorAutService.insertPoi(puntoFisico);
                return new ResponseEntity<>(puntoFisico, HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


   @PostMapping("/inserisciPoiLogico/{idPoi}")
    public ResponseEntity<Object> inserisciPoiLogico(@RequestBody PuntoLogico puntoLogico,
                                                     @PathVariable("idPoi")Integer idPoi) throws Exception {
        try {
            contributorAutService.insertPuntoLogico(idPoi, puntoLogico);
            return new ResponseEntity<>(puntoLogico, HttpStatus.CREATED);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminaPoi/{id}")
    public ResponseEntity<Object> eliminaPoi(@PathVariable("id") int idPoi) throws Exception {
        try{
            POI poi = contributorAutService.getSinglePoi(idPoi);
            contributorAutService.deletePoi(poi);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/modificaPuntoFisico/{id}")
    public ResponseEntity<Object> modificaPuntoFisico(@PathVariable("id") int idPoi, @RequestBody PuntoFisico poiModified) throws Exception {
        try{
            poiModified = supportModifica(poiModified, idPoi);
            if(new ManagerOSM().internoAlComune(poiModified.getPoint(), comuneService.findComuneByNome(contributorAutService.getSinglePoi(idPoi).getComune().getNome()))){
                contributorAutService.modifyPuntoFisico(idPoi, poiModified);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    private PuntoFisico supportModifica(PuntoFisico poi, int idPoi){
        PuntoFisico supportPoi = poi;
        POI punto = this.contributorAutService.getSinglePoi(idPoi);
        if (poi.getTitolo() == "") supportPoi.setTitolo(punto.getTitolo());
        if (poi.getDescrizione() == "") supportPoi.setDescrizione(punto.getDescrizione());
        if (poi.getLatitudine() == 0.0) supportPoi.setLatitudine(punto.getLatitudine());
        if (poi.getLongitudine() == 0.0) supportPoi.setLongitudine(punto.getLongitudine());
        return supportPoi;
    }

    @PutMapping("/modificaPuntoLogico/{id}")
    public ResponseEntity<Object> modificaPuntoLogico(@PathVariable("id") int idPoi, @RequestBody PuntoLogico poiModified ,
                                                      @RequestHeader(value = "idNuovoPuntoFisico") int nuovoPuntoRiferimento) throws Exception {
        try{
            contributorAutService.modifyPuntoLogico(idPoi, poiModified, nuovoPuntoRiferimento);
                return new ResponseEntity<>(HttpStatus.OK);
        } catch (POIException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping ("/inserisciItinerario/{nomeComune}")
        public ResponseEntity<Object> inserisciItinerario(@PathVariable("nomeComune")String nome, @RequestBody Itinerario itinerario,
                                                          @Size(min = 2, message = "La lista idPois deve avere almeno di 2 elementi!")
                                                          @RequestHeader("idPois")List<Integer>idPois) throws Exception{
            try{
            contributorAutService.insertIti(itinerario, idPois, nome);
                  return new ResponseEntity<>(itinerario, HttpStatus.CREATED);
            } catch (ItinerarioException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

    @DeleteMapping("/eliminaItinerario/{id}")
        public ResponseEntity<Object> eliminaItinerario(@PathVariable("id") int idItinerario) throws Exception {
        try{
        Itinerario itinerario = contributorAutService.getSingleItinerario(idItinerario);
        contributorAutService.deleteIti(itinerario);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItinerarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

   @PutMapping("/modificaItinerario/{id}")
    public ResponseEntity<Object> modificaItinerario(@PathVariable("id") int idItinerario, @RequestBody Itinerario itinerario,
                                                     @Size(min = 2, message = "La lista idPois deve avere almeno di 2 elementi!")
                                                     @RequestHeader ("idPois")List<Integer>idPois)throws Exception{
        try {
        contributorAutService.modificaIti(idItinerario,itinerario, idPois);
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItinerarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
