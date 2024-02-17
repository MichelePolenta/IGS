package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi;

import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.EliminaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import it.unicam.cs.ids2324.project.Backend.Resources.ResourceRichiestePoi;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;

/**
 * Implementazione del factory per la creazione di richieste di eliminazione POI.
 * Implementa l'interfaccia {@code FactoryRichiestaPoi}.
 */

public class FactoryEliminaPoi implements FactoryRichiestaPoi {

    public RichiestaPoi create(POI poi) {
        return new EliminaPoi(poi);
    }
}
