package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.example.Controller.CreatoreContributorAut;
import org.example.Model.Persona;
import org.example.Model.Comune;

public class Main {
    public static void main(String[] args) throws Exception {
        CreatoreContributorAut creatoreContributorAut = new CreatoreContributorAut();
        creatoreContributorAut.creaAttore("Michele", "michelepolenta02@gmail.com", "password@@@@", new Comune("Ancona"), 
        new LocalDate.of(2002, 09, 29));
    }
}