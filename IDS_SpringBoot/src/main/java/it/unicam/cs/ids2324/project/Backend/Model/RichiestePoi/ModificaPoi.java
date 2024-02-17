package it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi;

import it.unicam.cs.ids2324.project.Backend.Model.POI;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Classe che rappresenta una richiesta di modifica di un punto di interesse.
 * Estende la classe astratta {@code RichiestaPoi}.
 */

@Entity
@DiscriminatorValue("Modifica")
public class ModificaPoi extends RichiestaPoi{

    @OneToOne
    @JoinColumn(name = "vecchiopoi")
    private POI vecchioPoi;

    public ModificaPoi(){}

    public ModificaPoi(POI poi, POI vecchioPoi){
        super(poi);
        this.vecchioPoi = vecchioPoi;
    }

    public POI getVecchioPoi(){
        return this.vecchioPoi;
    }

}
