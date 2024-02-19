package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryItinerario;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryPOI;
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


    private final RepositoryPOI repositoryPOI;


    private final RepositoryItinerario repositoryItinerario;


    public VisualizzazioneService(RepositoryItinerario repositoryItinerario, RepositoryPOI repositoryPOI){
        this.repositoryPOI = repositoryPOI;
        this.repositoryItinerario = repositoryItinerario;
    }

    public List<POI> getAllPoi(){
        return repositoryPOI.findAllByVisible(true);
    }

    public List<Itinerario> getAllItinerari(){
        return repositoryItinerario.findAllByVisible(true);
    }

    public POI getSinglePoi(int id){
        return repositoryPOI.findByIdAndVisible(id, true);
    }

    public Itinerario getSingleItinerario(int id){
        return repositoryItinerario.findByIdAndVisible(id, true);
    }




}
