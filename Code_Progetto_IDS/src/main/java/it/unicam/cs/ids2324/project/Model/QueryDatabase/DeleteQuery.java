package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.Persona;

public class DeleteQuery {

    public String cancellaPoi(Double lat,Double lon){
        return "DELETE FROM poi WHERE latitudine='" + lat + "' AND longitudine='" + lon + "';";
    }

    public String cancellaPersona(String mail, String password){
        return "DELETE FROM persona WHERE mail='" + mail + "' AND password='" + password + "';";
    }
     public String cancellaRichiesta(String mail, String password){
         return "DELETE FROM richieste_accreditamento WHERE mail='" + mail + "' AND password='" + password + "';";
     }

    public String cancellaItinerario(String titolo, String descrizione){
        return "DELETE FROM itinerario WHERE titolo='" + titolo + "' AND descrizione='" + descrizione + "';";
    }


}
