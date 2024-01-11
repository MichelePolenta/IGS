package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import java.sql.*;


public class QueryExecutor {

    private String path = "jdbc:postgresql://dumbo.db.elephantsql.com/nhaxkrfg";
    private Connection connection = DriverManager.getConnection(path, "nhaxkrfg", "lzDxSDrrGglA-KFt9iYgRTXbmYbwEX2K");;
    private Statement statement = connection.createStatement();
    private ResultSet resultSet;


    public  QueryExecutor() throws SQLException {
    }

    public void eseguiQuery(String cmd) throws Exception{
        this.getStatement().executeQuery(cmd);
    }

    public void eseguiQueryUpdate(String cmd) throws Exception{
        this.getStatement().executeUpdate(cmd);
    }

    public void apriNuovaConnessione() throws SQLException {
        this.connection = DriverManager.getConnection(path, "nhaxkrfg", "lzDxSDrrGglA-KFt9iYgRTXbmYbwEX2K");
        this.statement = this.connection.createStatement();
    }

    public void chiudiConnessioni() throws SQLException {
        String cmd = "SELECT pg_terminate_backend(pg_stat_activity.pid) " +
                "FROM pg_stat_activity " +
                "WHERE pg_stat_activity.datname = current_database() " +
                "  AND pid <> pg_backend_pid();";
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.execute();
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
