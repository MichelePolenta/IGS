package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.Persona;

public class DeleteQuery {


    public String cancellaPersona(String mail, String password){
        return "DELETE FROM persona WHERE mail='" + mail + "' AND password='" + password + "';";
    }
     public String cancellaRichiesta(String mail, String password){
         return "DELETE FROM richieste_accreditamento WHERE mail='" + mail + "' AND password='" + password + "';";
     }


}
