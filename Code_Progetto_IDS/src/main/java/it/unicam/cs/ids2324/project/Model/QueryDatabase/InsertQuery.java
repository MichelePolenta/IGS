package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.Persona;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class InsertQuery {
    public String inserisciPersona(Persona persona) {
        return "INSERT INTO persona (nome, cognome, mail, password, ruolo, datadinascita) " +
                "VALUES ('" + persona.getNome() + "', '" + persona.getCognome() + "', '" + persona.getMail() + "', " +
                "'" + persona.getPassword() + "', '" + persona.getRuolo() + "', '" + persona.getDataDiNascita() + "');";
    }

    /**
     public String inserisciPOI(POI poi) {
        return "INSERT INTO persona (comune, titolo, descrizione, password, ruolo, datadinascita) " +
                "VALUES ('" + poi.getNome() + "', '" + poi.getCognome() + "', '" + poi.getMail() + "', " +
                "'" + poi.getPassword() + "', '" + poi.getRuolo() + "', '" + poi.getDataDiNascita() + "');";
    }*/

}
