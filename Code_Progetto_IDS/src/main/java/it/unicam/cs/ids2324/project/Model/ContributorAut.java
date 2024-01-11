package it.unicam.cs.ids2324.project.Model;


import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutor;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;

import java.sql.SQLException;

public class ContributorAut extends Persona {

    public ContributorAut(String nome, String cognome,String mail, String password, Comune citta, String dataDiNascita) throws Exception{
       super(nome, cognome,mail, password, citta, dataDiNascita);
       this.ruolo = Ruolo.CONTRAUT+"";
    }

    public void inserimento(POI poi) throws SQLException {
        new QueryExecutorInsert().inserisciPo(poi);
    }

    public void inserimento(Itinerario itinerario)throws  SQLException{
        new QueryExecutorInsert().inserisciItinerario(itinerario);
    }

    public void modifica (POI poi){

    }

    public boolean modifica(Itinerario itinerario){
        return false;
    }

}