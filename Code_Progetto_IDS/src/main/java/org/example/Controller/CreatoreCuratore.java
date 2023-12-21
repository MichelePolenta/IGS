package org.example.Controller;

import java.time.LocalDate;

import org.example.Model.Comune;
import org.example.Model.Curatore;
import org.example.Model.Persona;


public class CreatoreCuratore extends CreatorePersona{
    
    public Persona creaAttore(String nome, String mail, String password, String citta, LocalDate dataDiNacita)throws Exception{
        return new Curatore(nome,mail, password, new Comune(citta), dataDiNacita);
    }
    

}
