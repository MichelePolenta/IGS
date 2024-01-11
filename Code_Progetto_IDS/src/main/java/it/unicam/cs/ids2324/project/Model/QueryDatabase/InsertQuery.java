package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.Itinerario;
import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.RichestaAccreditamento;

public class InsertQuery {
    public String inserisciPersona() {
        return  "INSERT INTO persona (nome, cognome, mail, password, ruolo, datadinascita) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }


    public String inserisciComune(){
        return  "INSERT INTO comuni (nome) VALUES (?)";
    }

    public String insertContenutoItinerario(){
         return  "INSERT INTO contenuto_itinerario (itinerario, poi) VALUES (?, ?)";
    }

     public String inserisciPOI() {
        return "INSERT INTO poi (nome, descrizione, latitudine, longitudine, tipo, comune) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    public String inerisciRichiestaAccreditamento() {
            return "INSERT INTO poi (nome, descrizione, latitudine, longitudine, tipo, comune) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    }

    public String inserisciItinerario() {
        return "INSERT INTO itinerari (nome, descrizione) VALUES (?, ?)";
    }

}
