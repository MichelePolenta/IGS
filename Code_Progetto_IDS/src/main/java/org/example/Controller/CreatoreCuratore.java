package org.example.Controller;
import java.time.LocalDate;
import java.time.Period;

import org.example.Model.Curatore;
import org.example.Model.Persona;

public class CreatoreCuratore extends CreatorePersona{
    
    public Persona creaAttore(String nome, String mail, String password, String citta, LocalDate dataDiNacita){
        return new Curatore(nome,mail, password, citta, dataDiNacita);
    }

}
