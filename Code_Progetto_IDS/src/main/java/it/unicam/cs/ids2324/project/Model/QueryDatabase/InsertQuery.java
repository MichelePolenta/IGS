package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.RichestaAccreditamento;

public class InsertQuery {
    public String inserisciPersona(Persona persona) {
        return "INSERT INTO persona (nome, cognome, mail, password, ruolo, datadinascita) " +
                "VALUES ('" + persona.getNome() + "', '" + persona.getCognome() + "', '" + persona.getMail() + "', " +
                "'" + persona.getPassword() + "', '" + persona.getRuolo() + "', '" + persona.getDataDiNascita() + "');";
    }

     public String inserisciPOI(POI poi) {
        return "INSERT INTO poi (nome, descrizione, latitudine, longitudine, tipo) " +
                "VALUES ('" + poi.getTitolo() + "', '" + poi.getDescrizione() + "', '" + poi.getLat() + "', " +
                "'" + poi.getLon() + "', '" + poi.getType() + "');";
    }

    public String inerisciRichiestaAccreditamento(RichestaAccreditamento richestaAccreditamento){
            return "INSERT INTO richieste_accreditamento (nome, cognome, mail, password, ruolo_richiesto, data_di_nascita, messaggio) " +
                    "VALUES ('" + richestaAccreditamento.getPersona().getNome() + "', '" +  richestaAccreditamento.getPersona().getCognome() + "', '" +
                    richestaAccreditamento.getPersona().getMail() + "', " + "'" + richestaAccreditamento.getPersona().getPassword() + "', '"
                    + richestaAccreditamento.getPersona().getRuolo() + "', '" + richestaAccreditamento.getPersona().getDataDiNascita() + "', '"
                    +richestaAccreditamento.getMessaggio()+ "');";

    }

}
