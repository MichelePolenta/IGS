package it.unicam.cs.ids2324.project.Backend.Repository;

import it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi.RichiestaPoi;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import it.unicam.cs.ids2324.project.Backend.Model.TipiRichieste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaccia che rappresenta una risorsa per la gestione delle operazioni CRUD su entità RichiestaPOI.
 * Estende JpaRepository fornito da Spring Data JPA per le operazioni di persistenza.
 * Utilizzata per interagire con la tabella "Richiesta POI" nel database.
 */
@Repository
public interface RepositoryRichiestePoi extends JpaRepository<RichiestaPoi, Integer> {

    public List<RichiestaPoi> getRichiestaPoiByStato(Stati stato);
    List<RichiestaPoi> getRichiestaPoiByStato(TipiRichieste richieste);
}
