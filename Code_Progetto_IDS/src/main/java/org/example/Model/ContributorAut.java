package org.example.Model;

import java.io.*;
import java.time.LocalDate;


public class ContributorAut implements Persona {

    String nome;
    String mail;
    String password;
    String citta;
    LocalDate dataDiNascita;
    String codice;

    public ContributorAut(String nome, String mail, String password,String citta, LocalDate dataDiNascita){
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = dataDiNascita;
    }

    public boolean inserimento(String puntoDiRilievo){
        return false;
    }

    public boolean inserimento(double itinerario){
        return false;
    }

    public boolean modifica(String puntoDiRilievo){
        return false;
    }

    public boolean modifica(double itinerario){
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