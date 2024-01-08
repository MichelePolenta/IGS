package it.unicam.cs.ids2324.project.Model.QueryDatabase;

public class QueryOSM {

    public  String getComune(String nomeComune){
        return "[out:xml];area['name'='"+ nomeComune +"']->.a;relation(area.a)['name'='"+ nomeComune +"']['admin_level'='8'];out geom;";
    }



}
