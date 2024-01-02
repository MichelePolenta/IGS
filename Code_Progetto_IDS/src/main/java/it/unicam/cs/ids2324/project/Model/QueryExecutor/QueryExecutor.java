package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import java.sql.*;

public class QueryExecutor {


    private String path = "jdbc:postgresql://dumbo.db.elephantsql.com/nhaxkrfg";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public  QueryExecutor() throws SQLException {
        connection = DriverManager.getConnection(path, "nhaxkrfg", "lzDxSDrrGglA-KFt9iYgRTXbmYbwEX2K");
        statement = connection.createStatement();
    }

    public String getPath(){
        return this.path;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
