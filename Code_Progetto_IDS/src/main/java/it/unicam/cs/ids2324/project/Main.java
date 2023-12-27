package it.unicam.cs.ids2324.project;

import it.unicam.cs.ids2324.project.Controller.CreatoreContributorAut;
import it.unicam.cs.ids2324.project.Controller.CreatorePersona;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;
import org.openstreetmap.josm.io.OsmApi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        Persona persona = new CreatoreContributorAut().creaAttore("Michele","Polenta","michelepolenta02@gmail.com","Password3424234234324@","Ancona","29/09/2002");
        QueryExecutorInsert insert = new QueryExecutorInsert();
        insert.eseguiQueryInsertPersona(new InsertQuery().inserisciPersona(persona));
    }
}