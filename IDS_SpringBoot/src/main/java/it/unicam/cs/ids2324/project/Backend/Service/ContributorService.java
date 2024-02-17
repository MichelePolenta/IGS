


package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.ModificaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.ModificaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;
import it.unicam.cs.ids2324.project.Backend.Resources.*;
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

    private final ResourcePOI resourcePOI;

    private final ResourceItinerario resourceItinerario;

    private final ResourceRichiestePoi resourceRichiestePoi;

    private final ResourceRichiesteItinerario resourceRichiesteItinerario;

    private final ResourceComune resourceComune;


    @Autowired
    public ContributorService(ResourcePOI resourcePOI, ResourceItinerario resourceItinerario  , ResourceRichiestePoi resourceRichiestePoi,
                              ResourceRichiesteItinerario resourceRichiesteItinerario, ResourceComune resourceComune)
    {
        this.resourcePOI = resourcePOI;
        this.resourceItinerario=resourceItinerario;
        this.resourceRichiestePoi = resourceRichiestePoi;
        this.resourceRichiesteItinerario = resourceRichiesteItinerario;
        this.resourceComune = resourceComune;
    }

    public POI getSinglePoi(int id){
        return resourcePOI.findByIdAndVisible(id,true);
    }

    public Itinerario getSingleItinerario(int id){
        return resourceItinerario.findByIdAndVisible(id,true);
    }

    public void modifyItinerario(int idItinerario, Itinerario modified, List<Integer> idPois) throws Exception {
        ArrayList<POI> pois = new ArrayList<>();
        for (int i = 0; i< idPois.size() ; i++)
            pois.add(resourcePOI.findByIdAndVisible(idPois.get(i), true));
        Itinerario itinerario = resourceItinerario.findByIdAndVisible(idItinerario, true);
        Itinerario backup =  new Itinerario();
        setModifiche(backup, itinerario);
        modified.setPoi(pois);
        setModifiche(itinerario, modified);
        if (verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Non tutti i punti dell'itinerario sono all'interno del comune di riferimento");
        ModificaItinerario modificaItinerario = (ModificaItinerario) new FactoryModificaItinerario(resourceItinerario).create(itinerario);
        resourceItinerario.save(modificaItinerario.getItinerario());
        resourceRichiesteItinerario.save(modificaItinerario);
        resourceItinerario.save(resetItinerario(idItinerario, backup));
    }


    private Itinerario resetItinerario(int idItinerario, Itinerario backup){
        Itinerario ultimate = resourceItinerario.findByIdAndVisible(idItinerario, true);
        ultimate.getPoi().clear();
        setModifiche(ultimate, backup);
        return ultimate;
    }


    public void modifyPoi(int idPoi,  POI modified) throws Exception {
        POI poi = resourcePOI.findByIdAndVisible(idPoi, true);
        POI backup = verifyType(poi);
        setModifiche(poi, modified);
        ModificaPoi modificaPoi = (ModificaPoi) new FactoryModificaPoi(resourcePOI).create(poi);
        resourcePOI.save(modificaPoi.getPoi());
        resourceRichiestePoi.save(modificaPoi);
        setModifiche(poi, backup);
        resourcePOI.save(poi);
    }

    public void insertPoiFisico(POI poi, String nomeComune) throws POIException {
        if (nomeComune == null) throw new POIException("Bisogna scegliere un comune per il punto");
        if (poi.getTitolo() == null || poi.getDescrizione() == null || poi.getLatitudine() == 0.0 || poi.getLongitudine() == 0.0)
            throw new POIException("Bisogna compilare bene i parametri del punto");
        poi.setComune(resourceComune.findComuneByNome(nomeComune));
        resourcePOI.save(poi);
        resourceRichiestePoi.save(new FactoryInserimentoPoi().create(poi));

    }

    public void insertPoiLogico(POI poi, int puntoDiRiferimento)throws POIException {
        if (puntoDiRiferimento == 0) throw new POIException("Per il punto logico bisogna scegliere un punto di riferimento");
        if (poi.getTitolo() == null || poi.getDescrizione() == null)
            throw new POIException("Bisogna compilare bene i paramentri del punto");
        POI punto = this.getSinglePoi(puntoDiRiferimento);
        poi.setLongitudine(punto.getLongitudine());
        poi.setLatitudine(punto.getLatitudine());
        poi.setComune(punto.getComune());
        resourcePOI.save(poi);
        resourceRichiestePoi.save(new FactoryInserimentoPoi().create(poi));
    }

    public void insertItinerario(Itinerario itinerario, String nomeComune, List<Integer> idPois) throws ItinerarioException {
        if (itinerario==null) throw new ItinerarioException("Bisogna prima selezionare un itinerario");
        itinerario.setComune(resourceComune.findComuneByNome(nomeComune));
        ArrayList<POI> list = new ArrayList<>();
        for (int i=0; i<idPois.size(); i++) list.add(resourcePOI.findByIdAndVisible(idPois.get(i), true));
        itinerario.setPoi(list);
        if (verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Non tutti i punti dell'itinerario sono all'interno del comune di riferimento");
        resourceItinerario.save(itinerario);
        resourceRichiesteItinerario.save(new FactoryInserimentoItinerario().create(itinerario));
    }

    public void eliminaPoi(POI poi)throws POIException{
        if (poi == null) throw new POIException("Bisogna selezionare un poi da cancellare");
        resourceRichiestePoi.save(new FactoryEliminaPoi().create(poi));
    }

    public void eliminaItinerario(Itinerario itinerario)throws ItinerarioException{
        if (itinerario == null) throw new ItinerarioException("Bisogna prima selezionare un itinerario");
        resourceRichiesteItinerario.save(new FactoryEliminaItinerario().create(itinerario));
    }

}
