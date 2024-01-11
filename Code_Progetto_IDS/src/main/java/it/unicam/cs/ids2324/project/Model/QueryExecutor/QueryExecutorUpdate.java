package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.QueryDatabase.UpdateQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutorUpdate extends QueryExecutor{

    public QueryExecutorUpdate()throws SQLException{
        super();
    }
    public void updateAccreditamento(int id, String ruolo)throws SQLException {
        String cmd = new UpdateQuery().updateAccreditamento();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, ruolo);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
    }

}
