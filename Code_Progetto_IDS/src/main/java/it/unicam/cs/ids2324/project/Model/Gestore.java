package it.unicam.cs.ids2324.project.Model;

import it.unicam.cs.ids2324.project.Model.QueryDatabase.DeleteQuery;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.UpdateQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutor;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorDelete;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorUpdate;

import java.util.List;

public class Gestore extends Persona {
    public Gestore(String nome, String cognome,String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, mail, cognome,password, citta, dataDiNascita);
        this.ruolo = Ruolo.GEST+"";
    }

    public List<RichestaAccreditamento> visualizzaRichiesteAccreditamento(){
        return null;
    }


    public void accredita(RichestaAccreditamento richestaAccreditamento, boolean status) throws Exception {
        DeleteQuery deleteQuery = new DeleteQuery();
        InsertQuery insertQuery = new InsertQuery();
        QueryExecutor queryExecutor = new QueryExecutor();
        if (status) accreditaTrue(deleteQuery, insertQuery, queryExecutor, richestaAccreditamento);
        else accreditaFalse(deleteQuery,queryExecutor, richestaAccreditamento);
    }

    private void accreditaTrue(DeleteQuery deleteQuery, InsertQuery insertQuery,QueryExecutor queryExecutor, RichestaAccreditamento richestaAccreditamento) throws Exception {
        Persona persona = richestaAccreditamento.getPersona();
        QueryExecutorUpdate queryExecutorUpdate = new QueryExecutorUpdate();
        queryExecutorUpdate.updateAccreditamento(persona.detectId(), richestaAccreditamento.getRuoloDacc());
        new QueryExecutorDelete().cancellaRichiesta(richestaAccreditamento.detectId());
    }

    private void accreditaFalse(DeleteQuery deleteQuery, QueryExecutor queryExecutor, RichestaAccreditamento richestaAccreditamento) throws Exception {
        new QueryExecutorDelete().cancellaRichiesta(richestaAccreditamento.detectId());
    }

    public boolean autorizzazione(String autorizzazione){
        return false;
    }

    public boolean cancellazione(String puntoDiRilievo){
        return false;
    }

    public boolean cancellazione(double itinerario){
        return false;
    }

    public boolean cancellazione(int contenuto){
        return false;
    }



}