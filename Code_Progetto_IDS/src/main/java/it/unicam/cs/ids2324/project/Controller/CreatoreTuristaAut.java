package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.TuristaAutenticato;

public class CreatoreTuristaAut extends CreatorePersona{
    @Override
    public Persona creaAttore(String nome, String mail, String password, String citta, String dataDiNacita) throws Exception {
        return new TuristaAutenticato(nome,mail, password, new Comune("citta"), dataDiNacita );
    }
}
