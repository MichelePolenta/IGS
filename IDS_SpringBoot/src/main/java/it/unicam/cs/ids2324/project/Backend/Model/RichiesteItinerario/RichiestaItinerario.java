package it.unicam.cs.ids2324.project.Backend.Model.RichiesteItinerario;

import it.unicam.cs.ids2324.project.Backend.Model.Itinerario;
import it.unicam.cs.ids2324.project.Backend.Model.Stati;
import jakarta.persistence.*;

/**
 * Classe astratta che rappresenta una richiesta riguardante l'itinerario nell'applicazione.
 * Utilizza una strategia di mapping Single Table per la persistenza tramite JPA.
 * Ogni richiesta è associata a uno specifico itinerario e ha uno stato di avanzamento.
 * Le sottoclassi concrete implementano azioni specifiche sulla richiesta di itinerario.
 *
 * La tabella di persistenza è mappata sulla tabella "richieste_itinerario" nel database,
 * utilizzando la strategia di ereditarietà Single Table.
 *
 * Contiene campi come:
 * - id_richiesta: Identificatore univoco della richiesta generato automaticamente.
 * - itinerario: Itinerario associato alla richiesta.
 * - stato: Stato attuale della richiesta (e.g., IN_ATTESA, APPROVATA, RIFIUTATA).
 *
 * Metodi principali includono:
 * - changeState(Stati state): Metodo per cambiare lo stato della richiesta.
 *
 * Le sottoclassi concrete rappresentano diverse tipologie di richieste di itinerario
 * (e.g., EliminaItinerario, ModificaItinerario, InserisciItinerario).
 */

@Entity
@Table(name = "richieste_itinerario")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class RichiestaItinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "richieste_itinerario_seq")
    @SequenceGenerator(name = "richieste_itinerario_seq", sequenceName = "richieste_itinerario_seq", allocationSize = 1)
    @Column(name = "id_richiesta")
    protected int id_richiesta;

    @OneToOne
    @JoinColumn(name = "itinerario")
    protected Itinerario itinerario;

    @Enumerated(EnumType.STRING)
    @JoinTable(name = "richieste_itinerario")
    @JoinColumn(name = "stato")
    protected Stati stato;

    public void changeState(Stati state) {
        this.stato = state;
    }

    public RichiestaItinerario(Itinerario itinerario){
        this.itinerario = itinerario;
        this.stato = Stati.ATTESA;
    }

    public RichiestaItinerario() {

    }

    public void setItinerario(Itinerario itinerario){
        this.itinerario = itinerario;
    }

    public void setId(int id){
        this.id_richiesta = id;
    }

    public int getId(){
        return this.id_richiesta;
    }

    public Itinerario getItinerario(){
        return this.itinerario;
    }
}
