package it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Classe che rappresenta una richiesta di inserimento di un itinerario.
 * Estende la classe astratta {@code  RichiestaItinerario}.
 */

@Entity
@DiscriminatorValue("Inserimento")
public class InserisciItinerario extends RichiestaItinerario{
    public InserisciItinerario(Itinerario itinerario) {
        super(itinerario);
    }

    public InserisciItinerario() {}
}
