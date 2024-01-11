package it.unicam.cs.ids2324.project.Model.QueryDatabase;

import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.RichestaAccreditamento;
import org.eclipse.persistence.internal.descriptors.PersistenceObjectAttributeAccessor;

public class SelectQuery {

    public String getIdPoi(){
        return "SELECT id_poi FROM poi WHERE nome = ? AND descrizione = ?";
    }

    public String getIdComune(){
        return "SELECT id_comune FROM comuni WHERE nome = ?";
    }

    public String getIdItinerario(){
       return "SELECT id_itinerario FROM itinerario WHERE titolo = ? AND descrizione = ?";
    }

    public String getIdRichiestaAccreditamento() throws Exception {
        return  "SELECT id_richiesta FROM richieste_accreditamento WHERE persona = ?";
    }




    public String getPoiOfItinerario(int idItinerario){
        return "SELECT id_itinerario FROM itinerario WHERE titolo = ? AND descrizione = ?";
    }

    public String getPoi(){
        return "SELECT * FROM poi WHERE id_poi= ?";
    }

    public String getIdPersona(String mail, String password){
        return "SELECT id_persona FROM persona WHERE mail="+"'"+mail+"'"+" AND password="+"'"+password+"'"+";";
    }

    public String showAllPoi(){
        return "SELECT * FROM poi;";
    }

    public String showPersona(int id){
        return "SELECT nome, cognome, mail, password, ruolo, datadinascita FROM persona WHERE id_persona="+"'"+id+"'"+";";
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
