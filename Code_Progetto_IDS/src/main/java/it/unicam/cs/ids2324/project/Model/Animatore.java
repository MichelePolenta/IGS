package it.unicam.cs.ids2324.project.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Animatore extends Persona {

    public Animatore(String nome, String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.ANIM+"";
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
    String getRuolo() {
        return this.ruolo;
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
