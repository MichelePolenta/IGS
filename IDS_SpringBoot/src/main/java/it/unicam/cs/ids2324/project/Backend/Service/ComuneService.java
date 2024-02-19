package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryComune;
import it.unicam.cs.ids2324.project.Backend.Model.Comuni;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryPOI;
import org.springframework.stereotype.Service;

/**
 * Classe di servizio che gestisce le operazioni legate all'entità Comuni.
 * Utilizza la risorsa ResourceComune per accedere al database e fornire funzionalità di
 * ricerca e manipolazione dei dati relativi ai comuni.
 *
 * L'operazione personalizzata offerta da questa classe includono la ricerca di un comune
 * dato il suo nome attraverso il metodo {@code findComuneByNome}.
 */
@Service
public class ComuneService {

    private final RepositoryComune repositoryComune;

    public  ComuneService(RepositoryComune repositoryComune){
        this.repositoryComune = repositoryComune;
    }

    public Comuni findComuneByNome(String nome){
        return  repositoryComune.findComuneByNome(nome);
    }




}
