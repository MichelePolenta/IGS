package it.unicam.cs.ids2324.project.Model.QueryDatabase;

public class UpdateQuery {

    public UpdateQuery(){}


    public String updateAccreditamento(){
        return "UPDATE persona SET ruolo = ? WHERE id_persona = ?";
    }


}
