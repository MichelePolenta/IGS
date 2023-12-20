package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Animatore implements Persona {

    String nome;
    String mail;
    String password;
    String citta;
    LocalDate dataDiNascita;
    String codice;

    public Animatore(String nome, String mail, String password,String citta, LocalDate dataDiNascita){
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = dataDiNascita;
    }
    
    public boolean validazioneContenuto(String contenuto){
        return false;
    }

    public void creazioneContest(String nomeContest, ArrayList<ContributorAut> listaPartecipanti, LocalDate dataInizio, LocalDate dataFine){

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
