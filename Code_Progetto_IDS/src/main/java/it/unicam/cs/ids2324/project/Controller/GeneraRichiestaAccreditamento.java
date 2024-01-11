package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.*;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutor;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;

import java.sql.SQLException;

public class GeneraRichiestaAccreditamento {
    public RichestaAccreditamento genera(Persona persona, String messaggio, String ruolo) throws Exception {
        new QueryExecutorInsert().inerisciRichiestaAccreditamento(new GeneraRichiestaAccreditamento().genera(persona, messaggio, ruolo));
        return new RichestaAccreditamento(persona, messaggio,ruolo);
    }
}
