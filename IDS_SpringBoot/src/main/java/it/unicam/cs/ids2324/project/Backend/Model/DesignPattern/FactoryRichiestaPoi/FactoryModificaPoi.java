package it.unicam.cs.ids2324.project.Backend.Model.DesignPattern.FactoryRichiestaPoi;

import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.ModificaPoi;
import it.unicam.cs.ids2324.project.Backend.Service.ModificheManager;
import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;
import it.unicam.cs.ids2324.project.Backend.Repository.RepositoryPOI;

/**
 * Implementazione del factory per la creazione di richieste di modifica itinerario.
 * Implementa l'interfaccia {@code FactoryRichiestaPoi}.
 */

public class FactoryModificaPoi implements FactoryRichiestaPoi , ModificheManager {

    private final RepositoryPOI repositoryPOI;

    public FactoryModificaPoi(RepositoryPOI repositoryPOI){
        this.repositoryPOI = repositoryPOI;
    }

    @Override
    public RichiestaPoi create(POI poi) throws Exception{
        POI nuovoPoi = verifyType(poi);
        nuovoPoi.setVisible(false);
        return new ModificaPoi(nuovoPoi, repositoryPOI.findByIdAndVisible(poi.getId(), true));
    }

}
