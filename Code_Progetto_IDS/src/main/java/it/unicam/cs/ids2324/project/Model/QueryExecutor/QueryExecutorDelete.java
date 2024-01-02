package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutorDelete extends QueryExecutor{


    public QueryExecutorDelete() throws SQLException {
        super();
    }

    public void deletePersona(String cmd) throws SQLException{
        Statement statement = this.getStatement();
        statement.executeUpdate(cmd);
    }



}
