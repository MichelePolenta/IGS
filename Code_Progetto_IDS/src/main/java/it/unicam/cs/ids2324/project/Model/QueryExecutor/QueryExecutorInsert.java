package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.Persona;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class QueryExecutorInsert extends QueryExecutor {


    public QueryExecutorInsert() throws SQLException {}


    public void eseguiQueryInsertPersona(String cmd) throws SQLException {
        Statement statement = this.getStatement();
        statement.executeUpdate(cmd);
    }


}
