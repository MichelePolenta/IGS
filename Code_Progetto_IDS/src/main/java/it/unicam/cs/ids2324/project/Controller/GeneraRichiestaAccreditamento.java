package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.*;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.InsertQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorInsert;

import java.sql.SQLException;

public class GeneraRichiestaAccreditamento {
    public RichestaAccreditamento genera(Persona persona, String messaggio ) throws SQLException {
        new QueryExecutorInsert().eseguiQueryInsert(new InsertQuery().inerisciRichiestaAccreditamento(new RichestaAccreditamento(persona, messaggio)));
        return new RichestaAccreditamento(persona, messaggio);

    }
}
