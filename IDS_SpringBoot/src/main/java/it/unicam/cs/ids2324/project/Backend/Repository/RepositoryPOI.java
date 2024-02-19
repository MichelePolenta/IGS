package it.unicam.cs.ids2324.project.Backend.Repository;

import it.unicam.cs.ids2324.project.Backend.Model.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaccia che rappresenta una risorsa per la gestione delle operazioni CRUD su entità POI.
 * Estende JpaRepository fornito da Spring Data JPA per le operazioni di persistenza.
 * Utilizzata per interagire con la tabella "POI" nel database.
 */
@Repository
public interface RepositoryPOI extends JpaRepository<POI, Integer> {

    List<POI> findAllByVisible(boolean visible);

    POI findByIdAndVisible(int id,boolean visible);


}
