package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.QueryDatabase.DeleteQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutorDelete extends QueryExecutor {

    public QueryExecutorDelete()throws SQLException {
        super();
    }

    public void cancellaPoi(double lat , double lon)throws  SQLException{
        String cmd = new DeleteQuery().cancellaPoi();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setDouble(1, lat);
        preparedStatement.setDouble(2, lon);
        preparedStatement.executeUpdate();
    }

    public void cancellaPersona(String mail, String password) throws SQLException{
        String cmd = new DeleteQuery().cancellaPersona();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, mail);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();

    }

    public void cancellaRichiesta(int id) throws SQLException{
        String cmd = new DeleteQuery().cancellaRichiesta();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void cancellaItinerario(String titolo, String descrizione) throws SQLException{
        String cmd = new DeleteQuery().cancellaItinerario();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, titolo);
        preparedStatement.setString(2, descrizione);
        preparedStatement.executeUpdate();
    }
}
