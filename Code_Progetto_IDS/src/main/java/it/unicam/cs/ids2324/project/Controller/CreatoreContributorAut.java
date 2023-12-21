package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.ContributorAut;
import it.unicam.cs.ids2324.project.Model.Persona;
public class CreatoreContributorAut extends CreatorePersona {

    @Override
    public Persona creaAttore(String nome, String mail, String password, String citta, String dataDiNascita)throws Exception{
        return new ContributorAut(nome, mail, password, new Comune(citta), dataDiNascita);
    }

}
