package org.example.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Animatore extends Persona {

    
    public Animatore(String nome, String mail, String password,Comune citta, LocalDate dataDiNascita) throws Exception{
        this.controlloCredenziali(mail, password);
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = dataDiNascita;
        this.codice = generaCodice(Ruolo.ANIM, this.citta.getNome());
    }
    
    public boolean validazioneContenuto(String contenuto){
        return false;
    }

    public void creazioneContest(String nomeContest, ArrayList<ContributorAut> listaPartecipanti, LocalDate dataInizio, LocalDate dataFine){
    }

    @Override
    public Comune getCitta() {
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
