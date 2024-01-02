package it.unicam.cs.ids2324.project.Model;


import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContributorAut extends Persona {

    public ContributorAut(String nome, String cognome,String mail, String password, Comune citta, String dataDiNascita) throws Exception{
       super(nome, cognome,mail, password, citta, dataDiNascita);
       this.ruolo = Ruolo.CONTRAUT+"";
    }

    public void inserimento(POI poi) throws SQLException {
        new QueryExecutorInsert().eseguiQueryInsert(new InsertQuery().inserisciPOI(poi));
    }

    public boolean inserimento(Itinerario itinerario){
        return false;
    }

    public void modifica (POI poi){

    }

    public boolean modifica(Itinerario itinerario){
        return false;
    }

}