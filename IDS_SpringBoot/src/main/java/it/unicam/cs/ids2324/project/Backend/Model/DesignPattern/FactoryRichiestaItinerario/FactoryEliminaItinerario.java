package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.EliminaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;

/**
 * Implementazione del factory  per la creazione di richieste di eliminazione itinerario.
 * Implementa l'interfaccia  {@code FactoryRichiestaItinerario}.
 */

public class FactoryEliminaItinerario implements FactoryRichiestaItinerario {


    @Override
    public RichiestaItinerario create(Itinerario itinerario) {
        return new EliminaItinerario(itinerario);
    }
}
