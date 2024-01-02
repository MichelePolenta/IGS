package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutorSelect extends  QueryExecutor{


    public QueryExecutorSelect() throws SQLException {
    }

    public boolean esistePersona(String cmd) throws SQLException {
         ResultSet resultSet = getStatement().executeQuery(cmd);
         if (resultSet.next())
             return resultSet.getInt(1) == 1;
         else
             return false;
    }

    public Persona eseguiQuerySelectPersona(String cmd) throws SQLException {
        ResultSet resultSet = this.getResultSet();
        resultSet = this.getStatement().executeQuery(cmd);
        Persona persona = null;
        while (resultSet.next()){

        }
        return null;
    }

    public POI eseguiQuerySelectPoi(String cmd) throws SQLException {
        ResultSet resultSet = this.getResultSet();
        resultSet = this.getStatement().executeQuery(cmd);
        Persona persona = null;
        while (resultSet.next()){

        }
        return null;
    }

    public List<RichestaAccreditamento> estraiRichiesteAccreditamento(String cmd) throws Exception {
        ResultSet resultSet = this.getResultSet();
        Persona persona = null;
        resultSet = this.getStatement().executeQuery(cmd);
        List<RichestaAccreditamento> richieste = new ArrayList<>();
        while (resultSet.next()){
            persona = new ContributorAut(resultSet.getString("nome"), resultSet.getString("cognome"),
                    resultSet.getString("mail"), resultSet.getString("password"), new Comune("Acri"), resultSet.getString("data_di_nascita"));
            richieste.add(new RichestaAccreditamento(persona, resultSet.getString("messaggio")));
        }
        return richieste;
    }


}
