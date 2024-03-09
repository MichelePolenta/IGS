package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import it.unicam.cs.ids2324.project.Backend.Model.Ruolo;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) Manager per gestire informazioni legate agli utenti.
 * Questa classe viene utilizzata per trasferire informazioni relative agli utenti,
 * come nome utente, email, password e ruolo.
 */

public class DTOManager {
    private String nome;
    private String cognome;
    private String mail;
    private String password;
    private LocalDate dataDiNascita;
    private Ruolo ruolo;

    public DTOManager(String nome, String cognome, LocalDate dataDiNascita,  String mail, String password, Ruolo ruolo){
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        this.ruolo = ruolo;
        this.dataDiNascita = dataDiNascita;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
}
