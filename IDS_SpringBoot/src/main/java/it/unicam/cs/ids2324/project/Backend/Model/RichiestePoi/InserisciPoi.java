package it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi;

import it.unicam.cs.ids2324.project.Backend.Model.POI;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Classe che rappresenta una richiesta di inserimento di un punto di interesse.
 * Estende la classe astratta {@code RichiestaPoi}.
 */

@Entity
@DiscriminatorValue("Inserimento")
public class InserisciPoi extends RichiestaPoi{

    public InserisciPoi(){}

    public InserisciPoi(POI poi ){

        super(poi);
    }

}
