package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Gestore;
import it.unicam.cs.ids2324.project.Model.RichestaAccreditamento;

import java.sql.SQLException;

public class Accreditamento {

    public void accredita(Gestore gestore, RichestaAccreditamento richestaAccreditamento, boolean status) throws Exception {
        gestore.accredita(richestaAccreditamento, status);
    }

}
