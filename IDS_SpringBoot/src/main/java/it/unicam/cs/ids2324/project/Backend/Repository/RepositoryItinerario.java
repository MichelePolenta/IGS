package it.unicam.cs.ids2324.project.Backend.Repository;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaccia che rappresenta una risorsa per la gestione delle operazioni CRUD su entità Itinerario.
 * Estende JpaRepository fornito da Spring Data JPA per le operazioni di persistenza.
 * Utilizzata per interagire con la tabella "itinerario" nel database.
 */
@Repository
public interface RepositoryItinerario extends JpaRepository<Itinerario,Integer> {

   List<Itinerario> findAllByVisible(boolean visible);


   Itinerario findByIdAndVisible( int id_itinerario, boolean visible);

}
