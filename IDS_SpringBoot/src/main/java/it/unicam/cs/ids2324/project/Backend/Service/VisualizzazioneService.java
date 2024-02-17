package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceItinerario;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourcePOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service che rappresenta le operazioni di visualizzazione, restituendo la lista di tutti i punti di interesse (POI)
 * e itinerari disponibili nel sistema. Interagisce con le risorse ResourcePOI e ResourceItinerario per accedere
 * al database.
 */
@Service
public class VisualizzazioneService {

    @Autowired
    private final ResourcePOI resourcePOI;

    @Autowired
    private final ResourceItinerario resourceItinerario;


    public VisualizzazioneService(ResourceItinerario resourceItinerario, ResourcePOI resourcePOI){
        this.resourcePOI = resourcePOI;
        this.resourceItinerario = resourceItinerario;
    }

    public List<POI> getAllPoi(){
        return resourcePOI.findAllByVisible(true);
    }

    public List<Itinerario> getAllItinerari(){
        return resourceItinerario.findAllByVisible(true);
    }




}
