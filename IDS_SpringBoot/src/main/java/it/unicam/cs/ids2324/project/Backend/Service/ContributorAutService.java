package it.unicam.cs.ids2324.project.Backend.Service;


import it.unicam.cs.ids2324.project.Backend.Exception.ItinerarioException;
import it.unicam.cs.ids2324.project.Backend.Exception.POIException;
import it.unicam.cs.ids2324.project.Backend.Model.Comuni;
import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.PuntoLogico;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryComune;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryItinerario;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryPOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe di servizio gestisce le operazioni legate agli utenti contributor autorizzati.
 * Implementa l'interfaccia ModificheManager e offre funzionalità di inserimento, modifica e cancellazione
 * di POI (Punti di Interesse) e Itinerari. Si interfaccia con il livello di persistenza attraverso
 * le risorse ResourcePOI, ResourceItinerario e ResourceComune per accedere al database.
 *
 * Le principali funzionalità offerte da questa classe includono la gestione della cancellazione, modifica e inserimento
 * di POI, l'associazione di Punti Logici a Punti Fisici esistenti, la modifica di POI e la gestione degli Itinerari,
 * che include l'inserimento, la modifica e la cancellazione di Itinerari.
 */
@Service
public class ContributorAutService implements ModificheManager{

    private final RepositoryPOI repositoryPOI;
    private final RepositoryItinerario repositoryItinerario;
    private final RepositoryComune repositoryComune;


    @Autowired
    public ContributorAutService(RepositoryPOI repositoryPOI, RepositoryItinerario repositoryItinerario, RepositoryComune repositoryComune){
            this.repositoryPOI = repositoryPOI;
            this.repositoryItinerario = repositoryItinerario;
            this.repositoryComune = repositoryComune;
    }

    public void deletePoi(POI poi) throws Exception{
        if (poi == null)
            throw new POIException("Bisogna selezionare un poi da cancellare");
        if (verificaPoiItinerario(poi))
            throw new POIException("Un'itinerario associato ha solo due punti, eliminare prima l'itinerario");
        repositoryPOI.delete(poi);

    }

    public void insertPoi(POI poi)throws Exception{
        if (poi.getComune() == null || poi.getLatitudine() == 0.0 || poi.getDescrizione() == null || poi.getLatitudine() == 0 || poi.getLongitudine() == 0.0 || poi.getTitolo() == null)
            throw new POIException("Tutti i dati del punto devono essere compilati correttamente");
        poi.setVisible(true);
        repositoryPOI.save(poi);
    }

    public void insertPuntoLogico(int idPoi , PuntoLogico puntoLogico)throws Exception{
        if (idPoi == 0) throw new POIException("Bisogna selezione il punto fisico di riferimento");
        if (puntoLogico.getDescrizione() == null ||  puntoLogico.getTitolo() == null)
            throw new POIException("Tutti i dati del punto devono essere compilati correttamente");
        POI poi = getSinglePoi(idPoi);
        puntoLogico.setComune(poi.getComune());
        puntoLogico.setLatitudine(poi.getLatitudine());
        puntoLogico.setLongitudine(poi.getLongitudine());
        puntoLogico.setVisible(true);
        repositoryPOI.save(puntoLogico);
    }
    
    public void modifyPuntoFisico (int idVecchioPoi, POI nuovoPoi)throws Exception{
        if (idVecchioPoi == 0) throw new POIException("Bisogna selezionare il punto da modificare");
        if (nuovoPoi.getComune() == null || nuovoPoi.getLatitudine() == 0.0 || nuovoPoi.getDescrizione() == null || nuovoPoi.getLatitudine() == 0 || nuovoPoi.getLongitudine() == 0.0 || nuovoPoi.getTitolo() == null)
            throw  new POIException("I campi da modificare devono essere compilati correttamente");
        POI poi = settingPoi(this.getSinglePoi(idVecchioPoi), nuovoPoi.getTitolo(), nuovoPoi.getDescrizione(), nuovoPoi.getLatitudine(), nuovoPoi.getLongitudine());
        repositoryPOI.save(poi);
    }


    private POI settingPoi (POI poi, String nome, String descrizione, double latittudine, double longitudine){
        poi.setNome(nome);
        poi.setDescrizione(descrizione);
        poi.setLatitudine(latittudine);
        poi.setLongitudine(longitudine);
        return poi;
    }


    public void modifyPuntoLogico(int idVecchioPoi, POI nuovoPoi, int idPuntoDiRiferimento)throws Exception{
        if (idVecchioPoi == 0) throw new POIException("Bisogna selezionare un punto da modificare");
        if (idPuntoDiRiferimento == 0) throw  new POIException("Bisogna selezionare il punto fisico di riferimento");
        if (nuovoPoi.getTitolo() == null || nuovoPoi.getDescrizione() == null)
            throw new POIException("Bisogna compilare correttamente i campi del nuovo poi");
        POI puntoDiRiferimento = this.getSinglePoi(idPuntoDiRiferimento);
        POI poi = settingPoi(this.getSinglePoi(idVecchioPoi),nuovoPoi.getTitolo(), nuovoPoi.getDescrizione(), puntoDiRiferimento.getLatitudine(), puntoDiRiferimento.getLongitudine());
        repositoryPOI.save(poi);
    }


    public void insertIti(Itinerario itinerario, List<Integer> idPois, String nomeComune)throws Exception{
        if (nomeComune == null || itinerario.getTitolo() == null || itinerario.getDescrizione() == null)
            throw new ItinerarioException("Bisogna compilare correttamente i campi dell'itinerario");
        itinerario.setPoi(getPoiFromItinerario(idPois));
        itinerario.setComune(this.getComuneByNome(nomeComune));
        itinerario.setVisible(true);
        if (verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Tutti i punti dell'ititinerario devono condividere il comune con l'itinerario");
        repositoryItinerario.save(itinerario);
    }



    private List<POI> getPoiFromItinerario(List<Integer> idPois){
        ArrayList<POI> pois = new ArrayList<>();
        for (int i = 0; i< idPois.size(); i++)
            pois.add(this.getSinglePoi(idPois.get(i)));
        return pois;
    }

    public void modificaIti(int idItinerario, Itinerario itinerario, List<Integer> pois) throws Exception{
        if (idItinerario == 0) throw new ItinerarioException("Bisogna selezionare un'itinerario da modificare");
        if (itinerario.getTitolo() == null || itinerario.getDescrizione() == null)
            throw new ItinerarioException("Bisogna compilare correttamente i campi del nuovo itinerario");
        Itinerario vecchioItinerario = this.getSingleItinerario(idItinerario);
        itinerario.setTitolo(itinerario.getTitolo());
        itinerario.setDescrizione(itinerario.getDescrizione());
        itinerario.setPoi(getPoiFromItinerario(pois));
        if (verifyPois(itinerario,itinerario.getPoi()))
            throw new ItinerarioException("Tutti i punti dell'ititinerario devono condividere il comune con l'itinerario");
        repositoryItinerario.save(itinerario);
    }


    public void deleteIti(Itinerario itinerario)throws Exception{
        if (itinerario == null) throw new ItinerarioException("Bisogna selezionare un itinerario da cancellare");
        repositoryItinerario.delete(itinerario);
    }

    public POI getSinglePoi(int id){
        return repositoryPOI.findByIdAndVisible(id,true);
    }

    public Itinerario getSingleItinerario(int id){
        return repositoryItinerario.findByIdAndVisible(id,true);
    }

    public Comuni getComuneByNome(String nome){
        return  repositoryComune.findComuneByNome(nome);
    }


}
