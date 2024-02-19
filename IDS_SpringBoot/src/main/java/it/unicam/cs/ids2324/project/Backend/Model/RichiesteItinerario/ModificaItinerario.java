package it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import jakarta.persistence.*;

/**
 * Classe che rappresenta una richiesta di modifica di un itinerario.
 * Estende la classe astratta {@code  RichiestaItinerario}.
 */

@Entity
@DiscriminatorValue("Modifica")
public class ModificaItinerario extends RichiestaItinerario{

    @Column(name = "vecchioitinerario")
    private int vecchioitinerario;


        public ModificaItinerario(int vecchioitinerario, Itinerario itinerario ) {
        super(itinerario);
        this.vecchioitinerario = vecchioitinerario;
    }

    public ModificaItinerario() {}

    public int getVecchioItinerario() {
        return this.vecchioitinerario;
    }

    public void setItinerario(Itinerario itinerario){
        this.itinerario = itinerario;
    }

}
