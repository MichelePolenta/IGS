package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.InserisciItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceItinerario;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceRichiesteItinerario;

/**
 * Implementazione del factory per la creazione di richieste di inserimento itinerario.
 * Implementa l'interfaccia {@code FactoryRichiestaItinerario}.
 */

public class FactoryInserimentoItinerario implements FactoryRichiestaItinerario {

    @Override
    public RichiestaItinerario create(Itinerario itinerario) {
        InserisciItinerario inserisciItinerario = new InserisciItinerario(itinerario);
        itinerario.setVisible(false);
        return inserisciItinerario;
    }
}
