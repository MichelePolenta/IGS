package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import java.sql.*;

public class QueryExecutorInsert extends QueryExecutor {


    public QueryExecutorInsert() throws SQLException {}


    public void eseguiQueryInsert(String cmd) throws SQLException {
        Statement statement = this.getStatement();
        statement.executeUpdate(cmd);
    }


}
