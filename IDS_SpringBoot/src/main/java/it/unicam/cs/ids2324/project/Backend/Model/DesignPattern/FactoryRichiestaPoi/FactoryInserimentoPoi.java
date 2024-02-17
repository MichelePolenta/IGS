package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi;

import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.InserisciPoi;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;

/**
 * Implementazione del factory per la creazione di richieste di inserimento POI.
 * Implementa l'interfaccia  {@code FactoryRichiestaPoi}.
 */

public class FactoryInserimentoPoi implements FactoryRichiestaPoi {

    @Override
    public RichiestaPoi create(POI poi) {
        InserisciPoi inserisciPoi = new InserisciPoi(poi);
        poi.setVisible(false);
        return inserisciPoi;
    }
}
