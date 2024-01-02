package it.unicam.cs.ids2324.project.Model;

import it.unicam.cs.ids2324.project.Model.Persona;

public class RichestaAccreditamento {

    private Persona persona;
    private String messaggio;

    public RichestaAccreditamento(Persona persona, String messaggio){
        this.persona = persona;
        this.messaggio = messaggio;
    }


    public Persona getPersona() {
        return this.persona;
    }

    public String getMessaggio() {
        return this.messaggio;
    }

    @Override
    public String toString() {
        return persona+" "+"\n"+"Messaggio: "+messaggio;
    }
}
