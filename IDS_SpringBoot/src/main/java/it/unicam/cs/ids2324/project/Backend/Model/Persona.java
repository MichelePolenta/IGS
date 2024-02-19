package it.unicam.cs.ids2324.project.Backend.Model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che rappresenta una persona nell'applicazione.
 * La tabella di persistenza è mappata sulla tabella "Persona" nel database.
 * Contiene campi come:
 * - nome: Nome della persona.
 * - cognome: Cognome della persona.
 * - mail: Indirizzo email unico e identificativo della persona, utilizzato come ID.
 * - password: Password associata all'account della persona.
 * - comune: Comune di residenza della persona.
 * - dataDiNascita: Data di nascita della persona.
 * - ruolo: Ruolo della persona nell'applicazione (e.g., Contributor, Contributor Autorizzato).
 *
 */

@Entity
@Table(name = "Persona")
public  class Persona {

    private String nome;
    private String cognome;

    @Id
    @Column(name = "mail")
    private String mail;

    private String password;

    @ManyToOne
    @JoinColumn(name = "comune")
    private Comuni comune;

    @Column(name = "datadinascita")
    private LocalDate dataDiNascita;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruolo")
    private Ruolo ruolo;


    public Persona(String nome, String cognome, String mail, String password, Comuni comune, String dataDiNascita, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataDiNascita = LocalDate.parse(dataDiNascita, formatter);
        this.comune = comune;
        this.ruolo = ruolo;
    }



    public Persona() {}

    public Comuni getComune(){
        return this.comune;
    }


    public String getNome(){
        return this.nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public String getPassword(){
        return this.password;
    }

    public Ruolo getRuolo(){
        return this.ruolo;
    }

    public String getMail(){
        return this.mail;
    }

    public LocalDate getDataDiNascita(){
        return this.dataDiNascita;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome+"\n"+
                "Mail: "+this.mail+"\n"+
                "Password: "+this.password+"\n"+
                "Citta: "+this.comune.getNome()+"\n"+
                "Data: "+this.dataDiNascita+"\n"+
                "Ruolo: "+this.ruolo+"";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComune(Comuni comune) {
        this.comune = comune;
    }


}
