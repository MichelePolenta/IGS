


package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.ModificaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.ModificaPoi;
import it.unicam.cs.ids2324.project.Backend.Repository.*;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi.FactoryEliminaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi.FactoryInserimentoPoi;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi.FactoryModificaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario.FactoryEliminaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario.FactoryInserimentoItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario.FactoryModificaItinerario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service che rappresenta le funzionalità del contributor.
 * Gestisce operazioni di inserimento, modifica e cancellazione di POI (Punti di Interesse) e Itinerari,
 * interfacciandosi con le risorse ResourcePOI, ResourceItinerario, ResourceRichiestePoi e ResourceRichiesteItinerario
 * per accedere al database. Utilizza inoltre le classi del package DesignPattern.FactoryRichiestaPoi
 * e DesignPattern.FactoryRichiestaItinerario per creare richieste di inserimento, modifica o eliminazione.
 */
@Service
public class ContributorService implements ModificheManager {

    private final RepositoryPOI repositoryPOI;

    private final RepositoryItinerario repositoryItinerario;

    private final RepositoryRichiestePoi repositoryRichiestePoi;

    private final RepositoryRichiesteItinerario repositoryRichiesteItinerario;

    private final RepositoryComune repositoryComune;


    @Autowired
    public ContributorService(RepositoryPOI repositoryPOI, RepositoryItinerario repositoryItinerario, RepositoryRichiestePoi repositoryRichiestePoi,
                              RepositoryRichiesteItinerario repositoryRichiesteItinerario, RepositoryComune repositoryComune)
    {
        this.repositoryPOI = repositoryPOI;
        this.repositoryItinerario = repositoryItinerario;
        this.repositoryRichiestePoi = repositoryRichiestePoi;
        this.repositoryRichiesteItinerario = repositoryRichiesteItinerario;
        this.repositoryComune = repositoryComune;
    }

    public POI getSinglePoi(int id){
        return repositoryPOI.findByIdAndVisible(id,true);
    }

    public Itinerario getSingleItinerario(int id){
        return repositoryItinerario.findByIdAndVisible(id,true);
    }

    public void modifyItinerario(int idItinerario, Itinerario modified, List<Integer> idPois) throws Exception {
        ArrayList<POI> pois = new ArrayList<>();
        for (int i = 0; i< idPois.size() ; i++)
            pois.add(repositoryPOI.findByIdAndVisible(idPois.get(i), true));
        Itinerario itinerario = repositoryItinerario.findByIdAndVisible(idItinerario, true);
        Itinerario backup =  new Itinerario();
        setModifiche(backup, itinerario);
        modified.setPoi(pois);
        setModifiche(itinerario, modified);
        if (!verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Non tutti i punti dell'itinerario sono all'interno del comune di riferimento");
        ModificaItinerario modificaItinerario = (ModificaItinerario) new FactoryModificaItinerario().create(itinerario);
        repositoryItinerario.save(modificaItinerario.getItinerario());
        repositoryRichiesteItinerario.save(modificaItinerario);
        repositoryItinerario.save(resetItinerario(idItinerario, backup));
    }


    private Itinerario resetItinerario(int idItinerario, Itinerario backup){
        Itinerario ultimate = repositoryItinerario.findByIdAndVisible(idItinerario, true);
        ultimate.getPoi().clear();
        setModifiche(ultimate, backup);
        return ultimate;
    }


    public void modifyPoi(int idPoi,  POI modified) throws Exception {
        POI poi = repositoryPOI.findByIdAndVisible(idPoi, true);
        POI backup = verifyType(poi);
        setModifiche(poi, modified);
        ModificaPoi modificaPoi = (ModificaPoi) new FactoryModificaPoi(repositoryPOI).create(poi);
        repositoryPOI.save(modificaPoi.getPoi());
        repositoryRichiestePoi.save(modificaPoi);
        setModifiche(poi, backup);
        repositoryPOI.save(poi);
    }

    public void insertPoiFisico(POI poi, String nomeComune) throws POIException {
        if (nomeComune == null) throw new POIException("Bisogna scegliere un comune per il punto");
        if (poi.getTitolo() == null || poi.getDescrizione() == null || poi.getLatitudine() == 0.0 || poi.getLongitudine() == 0.0)
            throw new POIException("Bisogna compilare bene i parametri del punto");
        poi.setComune(repositoryComune.findComuneByNome(nomeComune));
        repositoryPOI.save(poi);
        repositoryRichiestePoi.save(new FactoryInserimentoPoi().create(poi));

    }

    public void insertPoiLogico(POI poi, int puntoDiRiferimento)throws POIException {
        if (puntoDiRiferimento == 0) throw new POIException("Per il punto logico bisogna scegliere un punto di riferimento");
        if (poi.getTitolo() == null || poi.getDescrizione() == null)
            throw new POIException("Bisogna compilare bene i paramentri del punto");
        POI punto = this.getSinglePoi(puntoDiRiferimento);
        poi.setLongitudine(punto.getLongitudine());
        poi.setLatitudine(punto.getLatitudine());
        poi.setComune(punto.getComune());
        repositoryPOI.save(poi);
        repositoryRichiestePoi.save(new FactoryInserimentoPoi().create(poi));
    }

    public void insertItinerario(Itinerario itinerario, String nomeComune, List<Integer> idPois) throws ItinerarioException {
        if (itinerario==null) throw new ItinerarioException("Bisogna prima selezionare un itinerario");
        itinerario.setComune(repositoryComune.findComuneByNome(nomeComune));
        ArrayList<POI> list = new ArrayList<>();
        for (int i=0; i<idPois.size(); i++) list.add(repositoryPOI.findByIdAndVisible(idPois.get(i), true));
        itinerario.setPoi(list);
        if (!verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Non tutti i punti dell'itinerario sono all'interno del comune di riferimento");
        repositoryItinerario.save(itinerario);
        repositoryRichiesteItinerario.save(new FactoryInserimentoItinerario().create(itinerario));
    }

    public void eliminaPoi(POI poi)throws POIException{
        if (poi == null) throw new POIException("Bisogna selezionare un poi da cancellare");
        if (!verificaPoiItinerario(poi))
            throw new POIException("Un'itinerario associato ha solo due punti, eliminare prima l'itinerario");
        repositoryRichiestePoi.save(new FactoryEliminaPoi().create(poi));
    }

    public void eliminaItinerario(Itinerario itinerario)throws ItinerarioException{
        if (itinerario == null) throw new ItinerarioException("Bisogna prima selezionare un itinerario");
        repositoryRichiesteItinerario.save(new FactoryEliminaItinerario().create(itinerario));
    }

}
