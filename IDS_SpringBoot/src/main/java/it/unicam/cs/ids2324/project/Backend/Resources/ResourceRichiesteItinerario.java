package it.unicam.cs.ids2324.project.Backend.Resources;

import it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario.RichiestaItinerario;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaccia che rappresenta una risorsa per la gestione delle operazioni CRUD su entità RichiestaItinerario.
 * Estende JpaRepository fornito da Spring Data JPA per le operazioni di persistenza.
 * Utilizzata per interagire con la tabella "Richiesta Itinerario" nel database.
 */
@Repository
public interface ResourceRichiesteItinerario extends JpaRepository<RichiestaItinerario, Integer> {

    public List<RichiestaItinerario> getRichiestaItinerarioByStato(Stati stato);

}
