package it.unicam.cs.ids2324.project.Controller;


import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.Curatore;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.TuristaAutenticato;

public abstract class CreatorePersona {

        public abstract Persona creaAttore(String nome, String cognome,String mail, String password, String citta, String dataDiNacita)throws Exception;

}
