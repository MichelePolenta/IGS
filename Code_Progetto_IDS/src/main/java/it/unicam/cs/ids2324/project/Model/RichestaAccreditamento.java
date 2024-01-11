package it.unicam.cs.ids2324.project.Model;

import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorSelect;

public class RichestaAccreditamento {

    private Persona persona;
    private String messaggio;
    private String ruoloDacc;

    public RichestaAccreditamento(Persona persona, String messaggio, String ruoloDacc){
        this.persona = persona;
        this.messaggio = messaggio;
        this.ruoloDacc = ruoloDacc;
    }

    public int detectId() throws Exception {
        return new QueryExecutorSelect().getIdRichiestaAccreditamento(this.getPersona());
    }

    public Persona getPersona() {
        return this.persona;
    }

    public String getMessaggio() {
        return this.messaggio;
    }

    public String getRuoloDacc() {
        return ruoloDacc;
    }

    @Override
    public String toString() {
        return persona+" "+"\n"+"Messaggio: "+messaggio;
    }
}
