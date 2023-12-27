package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.TuristaAutenticato;

public class CreatoreTuristaAut extends CreatorePersona{
    public Persona creaAttore(String nome, String cognome,String mail, String password, String citta, String dataDiNascita) throws Exception {
        return new TuristaAutenticato(nome,cognome,mail, password, new Comune("citta"), dataDiNascita );
    }
}
