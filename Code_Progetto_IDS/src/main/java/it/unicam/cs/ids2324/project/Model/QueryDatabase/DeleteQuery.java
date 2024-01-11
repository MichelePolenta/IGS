package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.Persona;

public class DeleteQuery {

    public String cancellaPoi(){
        return "DELETE FROM poi WHERE latitudine = ? AND longitudine = ?";
    }

    public String cancellaPersona(){
        return "DELETE FROM persona WHERE mail = ? AND password = ?";
    }
     public String cancellaRichiesta(){
         return "DELETE FROM richieste_accreditamento WHERE id_richiesta = ?";
     }

    public String cancellaItinerario(){
        return "DELETE FROM itinerario WHERE titolo = ? AND descrizione = ?";
    }


}
