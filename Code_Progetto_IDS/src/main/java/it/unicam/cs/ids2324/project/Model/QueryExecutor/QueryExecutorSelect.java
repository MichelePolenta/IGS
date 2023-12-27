package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutorSelect extends  QueryExecutor{


    public QueryExecutorSelect() throws SQLException {
    }

    public Persona eseguiQuerySelectPersona(String cmd) throws SQLException {
        ResultSet resultSet = this.getResultSet();
        resultSet = this.getStatement().executeQuery(cmd);
        Persona persona = null;
        while (resultSet.next()){

        }
        return null;
    }


}
