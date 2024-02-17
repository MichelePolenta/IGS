package it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi;

import it.unicam.cs.ids2324.project.Backend.Model.POI;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Classe che rappresenta una richiesta di eliminazione di un punto di interesse.
 * Estende la classe astratta {@code RichiestaPoi}.
 */

@Entity
@DiscriminatorValue("Elimina")
public class EliminaPoi extends  RichiestaPoi{

    public EliminaPoi() {}

    public EliminaPoi(POI poi ){
        super(poi);
    }


}
