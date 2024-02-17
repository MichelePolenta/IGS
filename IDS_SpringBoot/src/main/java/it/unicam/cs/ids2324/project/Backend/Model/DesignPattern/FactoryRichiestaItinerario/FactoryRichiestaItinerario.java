package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;

/**
 * Interfaccia per il factory  che definisce il contratto per la creazione
 * di richieste relative all' {@code FactoryRichiestaItinerario}.
 */

public interface FactoryRichiestaItinerario {

    RichiestaItinerario create(Itinerario itinerario) throws Exception;


}
