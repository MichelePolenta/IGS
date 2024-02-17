package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi;

import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;

/**
 * Interfaccia per il design pattern factory che definisce il contratto per la creazione
 * di richieste di punti di interessi.
 */

public interface FactoryRichiestaPoi {

     RichiestaPoi create(POI poi) throws Exception;

}
