package it.unicam.cs.ids2324.project.Model.QueryDatabase;

public class SelectQuery {

    public String showPersona(){
        return "SELECT nome, cognome, mail, password, ruolo, datadinascita FROM persona;";
    }

    public String showPoi(){
        return "SELECT nome, descrizione, latitudine, longitudine, tipo FROM poi;";
    }

    public String showItinerario(){
        return "SELECT titolo, descrizione FROM itinerario;";
    }

    public String esistePersona(String mail, String password){
        return  " SELECT COUNT (*) FROM persona WHERE mail = '" + mail + "' AND password = '" + password + "'";
    }

    public String showRichiesteAccreditamento(){
        return "SELECT * FROM richieste_accreditamento;";
    }

}
