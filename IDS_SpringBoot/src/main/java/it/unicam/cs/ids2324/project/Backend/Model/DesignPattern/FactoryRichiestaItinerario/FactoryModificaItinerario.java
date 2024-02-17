package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.ModificaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceItinerario;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceRichiesteItinerario;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell factory  per la creazione di richieste di modifica itinerario.
 * Implementa l'interfaccia {@code FactoryRichiestaItinerario}.
 */

public class FactoryModificaItinerario implements FactoryRichiestaItinerario {

    private final ResourceItinerario resourceItinerario;

    public FactoryModificaItinerario(ResourceItinerario resourceItinerario ){
        this.resourceItinerario = resourceItinerario;
    }

    @Override
    public RichiestaItinerario create(Itinerario itinerario) throws Exception {
        List<POI> pois = new ArrayList<>();
        pois.addAll(itinerario.getPoi());
        Itinerario nuovoItinerario = new Itinerario(itinerario.getTitolo(), itinerario.getDescrizione());
        nuovoItinerario.setPoi(pois);
        nuovoItinerario.setComune(itinerario.getComune());
        nuovoItinerario.setVisible(false);
        return  new ModificaItinerario(itinerario.getId(), nuovoItinerario );
    }


}
