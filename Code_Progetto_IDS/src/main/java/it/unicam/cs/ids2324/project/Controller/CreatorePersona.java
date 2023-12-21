package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.TuristaAutenticato;

public abstract class CreatorePersona {
    
    public Persona creaAttore(){
        return new TuristaAutenticato();
    }
            
}
