package org.example.Controller;

import java.time.LocalDate;

import org.example.Model.Comune;
import org.example.Model.ContributorAut;
import org.example.Model.Persona;

public class CreatoreContributorAut extends CreatorePersona {
    
    public Persona creaAttore(String nome, String mail, String password, String citta, LocalDate dataDiNascita)throws Exception{
        return new ContributorAut(nome, mail, password, new Comune(citta), dataDiNascita);
    }

}
