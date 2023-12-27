package it.unicam.cs.ids2324.project.Model.QueryDatabase;

public class SelectQuery {

    public String showPersona(){
        return "SELECT nome, cognome, mail, password, ruolo, datadinascita FROM persona;";
    }

}
