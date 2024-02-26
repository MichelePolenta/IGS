package it.unicam.cs.ids2324.project.Backend.Model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un itinerario nell'applicazione.
 * La tabella di persistenza è mappata sulla tabella "itinerario" nel database,
 * utilizzando la strategia di ereditarietà Single Table.
 * Contiene campi come:
 * - id_itinerario: Identificatore univoco dell'itinerario generato automaticamente.
 * - titolo: Titolo dell'itinerario.
 * - descrizione: Descrizione dell'itinerario.
 * - comune: Comune associato all'itinerario.
 * - poi: Lista di POI che compongono l'itinerario.
 * - visible: Flag che indica se l'itinerario è visibile o meno.
 *
 */

@Entity
@Table(name = "itinerario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itinerario_seq")
    @SequenceGenerator(name = "itinerario_seq", sequenceName = "itinerario_seq", allocationSize = 1)
    @Column(name = "id_itinerario")
    private int id_itinerario;


    private String titolo;


    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "comune")
    private Comuni comune;



    @ManyToMany
    @JoinTable(
            name = "contenuto_itinerario",
            joinColumns = @JoinColumn(name = "itinerario"),
            inverseJoinColumns = @JoinColumn(name = "poi")
    )
    protected List<POI> poi;

    protected boolean visible;


    public Itinerario(){}

    public Itinerario(String titolo, String descrizione) throws  Exception{
        //if (punti.size() <2) throw  new Exception("L'itinerario deve contenere almeno due punti");
        this.titolo  = titolo;
        this.descrizione = descrizione;
        this.comune = comune;
        this.poi = poi;
    }

    public Itinerario(String titolo, String descrizione, Comuni comune, ArrayList<POI> poi){
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.comune = comune;
        this.poi = poi;
    }


    public List<POI> getPoi() {
        return this.poi;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public Comuni getComune(){
        return this.comune;
    }

    public int getId(){
        return this.id_itinerario;
    }

    public void setId(int id){
        this.id_itinerario = id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setComune(Comuni comune) {
        this.comune = comune;
    }

    public void setPoi(List<POI> poi) {
        this.poi = poi;
    }

    public boolean getVisible() {return this.visible;}


}
