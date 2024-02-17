package it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Classe che rappresenta una richiesta di eliminazione di un itinerario.
 * Estende la classe astratta {@code  RichiestaItinerario}.
 */

@Entity
@DiscriminatorValue("Elimina")
public class EliminaItinerario extends RichiestaItinerario{

    public EliminaItinerario(Itinerario itinerario) {
        super(itinerario);
    }

    public EliminaItinerario() {}
}
