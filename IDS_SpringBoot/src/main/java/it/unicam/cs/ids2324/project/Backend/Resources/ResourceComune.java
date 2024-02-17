package it.unicam.cs.ids2324.project.Backend.Resources;

import it.unicam.cs.ids2324.project.Backend.Model.Comuni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaccia che rappresenta una risorsa per la gestione delle operazioni CRUD su entità Comuni.
 * Estende JpaRepository fornito da Spring Data JPA per le operazioni di persistenza.
 * Utilizzata per interagire con la tabella "comuni" nel database.
 */
@Repository
public interface ResourceComune extends JpaRepository<Comuni, Integer> {

    Comuni findComuneByNome(String nome);

}
