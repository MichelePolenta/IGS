package org.example.Model.Database_Interaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class QueryInsertExecutor {

    private Connection connection;
    //private Statement statement;

    public QueryInsertExecutor(String path) throws SQLException {
        connection = DriverManager.getConnection(path);
    }

    public void inserimento(String cmd) {
        String cmdValues;
    }

}
