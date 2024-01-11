package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Animatore;
import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.Persona;

public class  CreatoreAnimatore extends CreatorePersona {

    @Override
    public Persona creaAttore(String nome, String cognome, String mail, String password, String citta, String dataDiNacita) throws Exception {
        return new Animatore(nome, cognome, mail, password, new Comune(citta), dataDiNacita);
    }
}
