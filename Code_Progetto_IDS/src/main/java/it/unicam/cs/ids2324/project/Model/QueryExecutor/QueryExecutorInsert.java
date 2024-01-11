package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import it.unicam.cs.ids2324.project.Model.Itinerario;
import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.RichestaAccreditamento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutorInsert extends QueryExecutor{

    public QueryExecutorInsert()throws SQLException{
        super();
    }

    public void inserisciPersona(Persona persona) throws SQLException {
        String cmd = new InsertQuery().inserisciPersona();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1, persona.getNome());
            preparedStatement.setString(2, persona.getCognome());
            preparedStatement.setString(3, persona.getMail());
            preparedStatement.setString(4, persona.getPassword());
            preparedStatement.setString(5, persona.getRuolo());
            preparedStatement.setDate(6, Date.valueOf(persona.getDataDiNascita()));
            preparedStatement.executeUpdate();
    }

    public void inserisciComune(String nome) throws SQLException {
            String cmd = new InsertQuery().inserisciComune();
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();
    }

    public void insertContenutoItinerario(int idPoi, int idItinerario)throws SQLException{
        String cmd = new InsertQuery().insertContenutoItinerario();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setInt(1, idItinerario);
        preparedStatement.setInt(2, idPoi);
        preparedStatement.executeUpdate();
    }

    public void inserisciPo(POI poi)throws  SQLException{
        String cmd = new InsertQuery().inserisciPOI();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1, poi.getTitolo());
            preparedStatement.setString(2, poi.getDescrizione());
            preparedStatement.setDouble(3, poi.getPoint().latitude());
            preparedStatement.setDouble(4, poi.getPoint().longitude());
            preparedStatement.setBoolean(5, poi.getType());
            preparedStatement.setString(6, poi.getComune().getNome());
    }

    public void inerisciRichiestaAccreditamento(RichestaAccreditamento richestaAccreditamento) throws Exception {
        String cmd = new InsertQuery().inerisciRichiestaAccreditamento();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, richestaAccreditamento.getRuoloDacc());
        preparedStatement.setInt(2, richestaAccreditamento.getPersona().detectId());
        preparedStatement.setString(3, richestaAccreditamento.getMessaggio());
        preparedStatement.executeUpdate();
    }

    public void inserisciItinerario(Itinerario itinerario)throws SQLException{
        String cmd = new InsertQuery().inserisciItinerario();
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(cmd);
        preparedStatement.setString(1, itinerario.getTitolo());
        preparedStatement.setString(2, itinerario.getDescrizione());
        preparedStatement.executeUpdate();
    }




}