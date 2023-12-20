package org.example.Controller;
import org.example.Model.Persona;
import org.example.Model.TuristaAutenticato;

public abstract class CreatorePersona {
    
    public Persona creaAttore(){
        return new TuristaAutenticato();
    }
            
}
