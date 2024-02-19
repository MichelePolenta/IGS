package it.unicam.cs.ids2324.project.Backend.Service;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.PuntoFisico;
import it.unicam.cs.ids2324.project.Backend.Model.PuntoLogico;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia che permette di fornire dei metodi di supporto
 * per le operazioni di modifica.
 */

public interface ModificheManager {

    default POI setModifiche(POI vecchioPOI, POI nuovoPoi){
        vecchioPOI.setNome(nuovoPoi.getTitolo());
        vecchioPOI.setDescrizione(nuovoPoi.getDescrizione());
        vecchioPOI.getItinerari().clear();
        if (nuovoPoi.getItinerari()!=null)
            vecchioPOI.getItinerari().addAll(nuovoPoi.getItinerari());
        vecchioPOI.setLatitudine(nuovoPoi.getLatitudine());
        vecchioPOI.setLongitudine(nuovoPoi.getLongitudine());
        return vecchioPOI;
    }

    default Itinerario setModifiche(Itinerario vecchioItinerario, Itinerario nuovoItinerario){
        ArrayList<POI> pois = new ArrayList<>();
        pois.addAll(nuovoItinerario.getPoi());
        vecchioItinerario.setTitolo(nuovoItinerario.getTitolo());
        vecchioItinerario.setComune(vecchioItinerario.getComune());
        vecchioItinerario.setDescrizione(nuovoItinerario.getDescrizione());
        vecchioItinerario.setPoi(pois);
        return vecchioItinerario;
    }

    /**
     *
     * Metodo che permette di verificare il tipo di un POI
     * generico e creare una nuova istanza.
     */
    default POI verifyType(POI poi) throws Exception{
        if (poi.getClass().equals(PuntoFisico.class)) {
            return new PuntoFisico(poi.getComune(), poi.getTitolo(), poi.getDescrizione(),poi.getLatitudine(),poi.getLongitudine());
        } else if (poi.getClass().equals(PuntoLogico.class)) {
            return new PuntoLogico(poi.getTitolo(), poi.getDescrizione(), new PuntoFisico(poi.getComune(), "", "", poi.getLatitudine(),poi.getLongitudine()));
        }
        return null;
    }


    /**
     * Metodo che permette di adattare un POI generico
     * ad un Punto Logico, sfruttando il punto di riferimento.
     */
    default POI adaptPoi(POI poi, POI puntoRiferimento){
        poi.setLatitudine(puntoRiferimento.getLatitudine());
        poi.setLongitudine(puntoRiferimento.getLongitudine());
        return poi;
    }


    default boolean verifyPois (Itinerario itinerario, List<POI> pois){
        for (POI poi: pois) {
            if (!poi.getComune().getNome().equals(itinerario.getComune().getNome()))
                return false;
        }
        return true;
    }

    default boolean verificaPoiItinerario(POI poi) {
        if (poi.getItinerari() != null) {
            for (Itinerario itinerario : poi.getItinerari()) {
                if (itinerario.getPoi().size() == 2)
                    return false;
            }
        }
        return true;
    }
}
