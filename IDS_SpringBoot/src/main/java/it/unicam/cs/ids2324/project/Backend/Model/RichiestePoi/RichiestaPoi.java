package it.unicam.cs.ids2324.project.Backend.Model.RichiestePoi;


import it.unicam.cs.ids2324.project.Backend.Model.POI;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;

/**
 * Classe astratta che rappresenta una richiesta  POI nell'applicazione.
 * Utilizza una strategia di mapping Single Table per la persistenza tramite JPA.
 * Ogni richiesta è associata a uno specifico POI e ha uno stato di avanzamento.
 * Le sottoclassi concrete implementano azioni specifiche sulla richiesta di POI.
 *
 * La tabella di persistenza è mappata sulla tabella "richieste_poi" nel database,
 * utilizzando la strategia di ereditarietà Single Table.
 *
 * Contiene campi come:
 * - id_richiesta: Identificatore univoco della richiesta generato automaticamente.
 * - poi: POI associato alla richiesta.
 * - stato: Stato attuale della richiesta (e.g., IN_ATTESA, APPROVATA, RIFIUTATA).
 *
 * Metodi principali includono:
 * - changeState(Stati state): Metodo per cambiare lo stato della richiesta.
 * - getPoi(): Metodo per ottenere il POI associato alla richiesta.
 * - setPoi(POI poi): Metodo per impostare un nuovo POI per la richiesta.
 *
 * Le sottoclassi concrete rappresentano diverse tipologie di richieste di POI
 * (e.g., EliminaPoi, ModificaPoi, InserisciPoi).
 */

@Entity
@Table(name = "richieste_poi")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class RichiestaPoi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "richieste_poi_seq")
    @SequenceGenerator(name = "richieste_poi_seq", sequenceName = "richieste_poi_seq", allocationSize = 1)
    @Column(name = "id_richiesta")
    protected int id_richiesta;

    @OneToOne
    @JoinColumn(name = "poi")
    protected POI poi;

    @Enumerated(EnumType.STRING)
    @JoinTable(name = "richieste_poi")
    @Column(name = "stato")
    protected Stati stato;



    public void changeState(Stati state) {
        this.stato = state;
    }

        public RichiestaPoi(){
        this.stato= Stati.ATTESA;
    }

    public RichiestaPoi(POI poi){
        this.poi = poi;
        this.stato= Stati.ATTESA;
    }

    public POI getPoi() {
        return this.poi;
    }
    public void  setPoi(POI poi){
        this.poi = poi;
    }
}
