package org.example.Controller;

import org.example.Model.Persona;

import java.time.LocalDate;

import org.example.Model.Gestore;

public class CreatoreGestore extends CreatorePersona {

    public Persona creaAttore(String nome, String mail, String password, String citta, LocalDate dataDiNascita){
       return new Gestore(nome, mail, password, citta, dataDiNascita); 
    }
    
}
