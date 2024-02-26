package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Exception.RichiestaException;
import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.ModificaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import it.unicam.cs.ids2324.project.Backend.Model.TipiRichieste;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryItinerario;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryPOI;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryRichiesteItinerario;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryRichiestePoi;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.ModificaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service che rappresenta le operazioni del curatore, includendo l'accettazione e il rifiuto di richieste di modifica,
 * inserimento ed eliminazione di POI e Itinerari. Interagisce con le risorse ResourcePOI, ResourceItinerario,
 * ResourceRichiestePoi e ResourceRichiesteItinerario per accedere al database. Utilizza gli stati definiti in Stati
 * e le classi di richiesta specifiche ModificaPoi, ModificaItinerario, RichiestaPoi e RichiestaItinerario.
 */
@Service
public class CuratoreService implements ModificheManager{

    private final RepositoryPOI repositoryPOI;
    private final RepositoryItinerario repositoryItinerario;
    private final RepositoryRichiestePoi repositoryRichiestePoi;
    private final RepositoryRichiesteItinerario resourceRichiestaItinerario;

    @Autowired
    public CuratoreService(RepositoryPOI repositoryPOI, RepositoryItinerario repositoryItinerario, RepositoryRichiestePoi resourceRichiestaPoi,
                           RepositoryRichiesteItinerario repositoryRichiesteItinerario)
    {
        this.repositoryPOI = repositoryPOI;
        this.repositoryItinerario = repositoryItinerario;
        this.repositoryRichiestePoi = resourceRichiestaPoi;
        this.resourceRichiestaItinerario = repositoryRichiesteItinerario;
    }

    public void accettaRichiestaDiModifica(ModificaItinerario modificaItinerario) throws RichiestaException{
            if (modificaItinerario == null)
                throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
            Itinerario itinerario = modificaItinerario.getItinerario();
            Itinerario vecchioItinerario = repositoryItinerario.findById(modificaItinerario.getVecchioItinerario()).get();
            setModifiche(vecchioItinerario, itinerario);
            modificaItinerario.changeState(Stati.ACCETTATA);
            repositoryItinerario.delete(itinerario);
            repositoryItinerario.save(vecchioItinerario);
            modificaItinerario.setItinerario(null);
            resourceRichiestaItinerario.save(modificaItinerario);
    }

    public void accettaRichistaDiInserimento(RichiestaItinerario richiestaItinerario)throws RichiestaException {
        if (richiestaItinerario == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
        richiestaItinerario.getItinerario().setVisible(true);
        repositoryItinerario.save(richiestaItinerario.getItinerario());
        richiestaItinerario.changeState(Stati.ACCETTATA);
        resourceRichiestaItinerario.save(richiestaItinerario);
    }


    public void accettaRichistaDiInserimento(RichiestaPoi richiestaPoi)throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
        richiestaPoi.getPoi().setVisible(true);
        repositoryPOI.save(richiestaPoi.getPoi());
        richiestaPoi.changeState(Stati.ACCETTATA);
        repositoryRichiestePoi.save(richiestaPoi);
    }

    public void accettaRichistaDiEliminazione(RichiestaPoi richiestaPoi)throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
        POI poi = richiestaPoi.getPoi();
        poi.setComune(null);
        repositoryPOI.delete(poi);
        richiestaPoi.changeState(Stati.ACCETTATA);
        richiestaPoi.setPoi(null);
        repositoryRichiestePoi.save(richiestaPoi);
    }

    public void accettaRichiestaEliminazione(RichiestaItinerario richiestaItinerario)throws RichiestaException{
        if (richiestaItinerario == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
        repositoryItinerario.delete(richiestaItinerario.getItinerario());
        richiestaItinerario.changeState(Stati.ACCETTATA);
        richiestaItinerario.setItinerario(null);
        resourceRichiestaItinerario.save(richiestaItinerario);
    }

    public void accettaRichiestaModifica(RichiestaPoi richiestaPoi)throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da accettare");
            ModificaPoi modificaPoi = (ModificaPoi) richiestaPoi;
            POI vecchioPoi = modificaPoi.getVecchioPoi();
            POI nuovoPoi = modificaPoi.getPoi();
            vecchioPoi = setModifiche(vecchioPoi, nuovoPoi);
            repositoryPOI.save(vecchioPoi);
            repositoryPOI.delete(nuovoPoi);
            modificaPoi.changeState(Stati.ACCETTATA);
            modificaPoi.setPoi(null);
            repositoryRichiestePoi.save(modificaPoi);
    }


    public void rifiutaRichistaDiEliminazione(RichiestaPoi richiestaPoi) throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
        richiestaPoi.changeState(Stati.RIFIUTATA);
        repositoryRichiestePoi.save(richiestaPoi);
    }

    public void rifiutaRichistaDiEliminazione(RichiestaItinerario richiestaItinerario) throws RichiestaException{
        if (richiestaItinerario == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
        richiestaItinerario.changeState(Stati.RIFIUTATA);
        resourceRichiestaItinerario.save(richiestaItinerario);
    }

    public void rifiutaRichistaDiModifica(RichiestaItinerario modificaItinerario)throws RichiestaException {
        if (modificaItinerario == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
            ModificaItinerario Itinerario = (ModificaItinerario) modificaItinerario;
            Itinerario.changeState(Stati.RIFIUTATA);
            resourceRichiestaItinerario.save(Itinerario);
            repositoryItinerario.deleteById(Itinerario.getItinerario().getId());
    }

    public void rifiutaRichistaDiInserimento(RichiestaPoi richiestaPoi)throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
        richiestaPoi.changeState(Stati.RIFIUTATA);
        POI poi = richiestaPoi.getPoi();
        poi.setComune(null);
        repositoryPOI.delete(poi);
        richiestaPoi.setPoi(null);
        repositoryRichiestePoi.save(richiestaPoi);
    }

    public void rifiutaRichiestaInserimento(RichiestaItinerario richiestaItinerario)throws RichiestaException{
        if (richiestaItinerario == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
        richiestaItinerario.changeState(Stati.RIFIUTATA);
        resourceRichiestaItinerario.save(richiestaItinerario);
        repositoryItinerario.delete(richiestaItinerario.getItinerario());
    }

    public void rifiutaRichiestaModifica(RichiestaPoi richiestaPoi)throws RichiestaException{
        if (richiestaPoi == null)
            throw new RichiestaException("Bisogna selezionare una richiesta da rifiutare");
            richiestaPoi.changeState(Stati.RIFIUTATA);
            repositoryPOI.deleteById(richiestaPoi.getPoi().getId());
            richiestaPoi.setPoi(null);
            repositoryRichiestePoi.save(richiestaPoi);
    }

    public POI getSinglePoi(int id){
        return repositoryPOI.findById(id).get();
    }

    public Itinerario getSingleItinerario(int id){
        return repositoryItinerario.findById(id).get();
    }

    public List<RichiestaPoi> getRichiestaPoiByStato(Stati stato){
        return repositoryRichiestePoi.getRichiestaPoiByStato(stato);
    }

    public List<RichiestaItinerario> getRichiestaItinerarioByStato(Stati stato){
        return resourceRichiestaItinerario.getRichiestaItinerarioByStato(stato);
    }

    public RichiestaPoi getRichiestaPoiById(int id){
        return repositoryRichiestePoi.findById(id).get();
    }

    public RichiestaItinerario getRichiestaItinerarioById(int id){
        return resourceRichiestaItinerario.findById(id).get();
    }




}
