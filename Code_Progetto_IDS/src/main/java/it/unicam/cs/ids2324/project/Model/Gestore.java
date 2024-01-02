package it.unicam.cs.ids2324.project.Model;

import it.unicam.cs.ids2324.project.Model.QueryDatabase.DeleteQuery;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorDelete;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorSelect;

import java.sql.SQLException;
import java.util.List;

public class Gestore extends Persona {
    public Gestore(String nome, String cognome,String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, mail, cognome,password, citta, dataDiNascita);
        this.ruolo = Ruolo.GEST+"";
    }

    public List<RichestaAccreditamento> visualizzaRichiesteAccreditamento(){
        return null;
    }


    public void accredita(RichestaAccreditamento richestaAccreditamento, boolean status) throws SQLException {
        DeleteQuery deleteQuery = new DeleteQuery();
        InsertQuery insertQuery = new InsertQuery();
        QueryExecutorDelete queryExecutorDelete = new QueryExecutorDelete();
        QueryExecutorInsert queryExecutorInsert = new QueryExecutorInsert();
        if (status) accreditaTrue(deleteQuery, insertQuery, queryExecutorInsert, queryExecutorDelete, richestaAccreditamento);
        else accreditaFalse(deleteQuery, queryExecutorDelete, richestaAccreditamento);
    }

    private void accreditaTrue(DeleteQuery deleteQuery, InsertQuery insertQuery, QueryExecutorInsert queryExecutorInsert, QueryExecutorDelete queryExecutorDelete, RichestaAccreditamento richestaAccreditamento) throws SQLException {
        Persona persona = richestaAccreditamento.getPersona();
        if (new QueryExecutorSelect().esistePersona(new SelectQuery().esistePersona(persona.getMail(), persona.getPassword())))
            queryExecutorDelete.deletePersona(deleteQuery.cancellaPersona(persona.getMail(),persona.getPassword()));
        queryExecutorDelete.deletePersona(deleteQuery.cancellaRichiesta(persona.getMail(), persona.getPassword()));
        queryExecutorInsert.eseguiQueryInsert(insertQuery.inserisciPersona(persona));
    }

    private void accreditaFalse(DeleteQuery deleteQuery, QueryExecutorDelete queryExecutorDelete, RichestaAccreditamento richestaAccreditamento) throws SQLException {
        queryExecutorDelete.deletePersona(deleteQuery.cancellaRichiesta(richestaAccreditamento.getPersona().getMail(), richestaAccreditamento.getPersona().getPassword()));
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