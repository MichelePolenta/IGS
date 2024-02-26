package it.unicam.cs.ids2324.project.Backend.Model;

import jakarta.persistence.*;

/**
 * Classe che rappresenta un comune nell'applicazione.
 * La tabella di persistenza è mappata sulla tabella "comuni" nel database.
 * Contiene campi come:
 * - idComune: Identificatore univoco del comune generato automaticamente.
 * - nome: Nome del comune.
 *
 *
 * I costruttori consentono di creare istanze della classe con diverse combinazioni
 * di parametri, inclusi casi in cui si specifica solo il nome o solo l'ID del comune.
 */

@Entity
@Table(name = "comuni")
public class Comuni {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comuni_seq")
    @SequenceGenerator(name = "comuni_seq", sequenceName = "comuni_seq", allocationSize = 1)
    @Column(name = "id_comune")
    private int idComune;

    private String nome;

    public Comuni(){}

    public Comuni(int idComune){
        this.idComune = idComune;
    }

    public Comuni(int idComune, String nome){
        this.idComune = idComune;
        this.nome = nome;
    }

    public Comuni(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return this.idComune;
    }

    public String getNome() {
        return this.nome;
    }

}
