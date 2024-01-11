package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import com.mapbox.geojson.LineString;
import it.unicam.cs.ids2324.project.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mapbox.geojson.Point;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import jakarta.xml.bind.annotation.W3CDomHandler;

public class QueryExecutorSelect extends  QueryExecutor{

    public QueryExecutorSelect()throws SQLException{
        super();
    }

    public int getIdPoi(String nome, String descrizione)throws SQLException{
        String cmd = new SelectQuery().getIdPoi();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descrizione);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return resultSet.getInt("id_poi");
        throw new SQLException("Non esiste il poi ricercato");
    }

    public int getIdComune(String nome)throws  SQLException{
        String cmd = new SelectQuery().getIdComune();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, nome);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return resultSet.getInt("id_comune");
        throw new SQLException("Il comune cercato non è presente");
    }

    public int getIdITinerario(String nome, String descrizione) throws SQLException{
        String cmd = new SelectQuery().getIdItinerario();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descrizione);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return resultSet.getInt("id_itinerario");
        throw new SQLException("L'itinerario cercato non è presente");
    }

    public int getIdRichiestaAccreditamento(Persona persona)throws Exception {
        String cmd = new SelectQuery().getIdRichiestaAccreditamento();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setInt(1, persona.detectId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) return resultSet.getInt("id_richiesta");
        throw new SQLException("La richiesta di accreditamento ricercata non è presente");
    }

    /*public int getPoiOfItinerario(int idItinerario)throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descrizione);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) return resultSet.getInt("id_itinerario");

    }*/

    public POI getPoi(int idPoi){
        String cmd  = new SelectQuery().getPoi();
        return null;
    }





}
