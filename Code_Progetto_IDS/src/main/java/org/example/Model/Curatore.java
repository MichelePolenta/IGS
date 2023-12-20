package org.example.Model;

import java.time.LocalDate;

public class Curatore implements Persona {

    String nome;
    String mail;
    String password;
    String citta;
    LocalDate dataDiNascita;
    String codice;

    public Curatore(String nome, String mail, String password,String citta, LocalDate dataDiNascita){
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = dataDiNascita;
    }
    
    public boolean accettazione(String richiestaInserimento){
        return false;
    }

    public boolean accettazione(double richiestaModifica){
        return false;
    }

    @Override
    public String getCitta() {
        return this.citta;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String getCodice() {
        return this.codice;
    }

    @Override
    public String getMail() {
        return this.mail;
    }

    @Override
    public LocalDate getDataDiNascita() {
        return this.dataDiNascita;
    }

}
