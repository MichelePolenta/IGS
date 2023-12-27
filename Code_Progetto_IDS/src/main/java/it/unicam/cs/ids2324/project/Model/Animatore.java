package it.unicam.cs.ids2324.project.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Animatore extends Persona {

    public Animatore(String nome, String cognome,String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, cognome,mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.ANIM+"";
    }
    
    public boolean validazioneContenuto(String contenuto){
        return false;
    }

    public void creazioneContest(String nomeContest, ArrayList<ContributorAut> listaPartecipanti, LocalDate dataInizio, LocalDate dataFine){
    }



}
