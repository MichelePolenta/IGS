package it.unicam.cs.ids2324.project.Backend.Util.JWT;

import it.unicam.cs.ids2324.project.Backend.Model.Ruolo;

/**
 * Data Transfer Object (DTO) Manager per gestire informazioni legate agli utenti.
 * Questa classe viene utilizzata per trasferire informazioni relative agli utenti,
 * come nome utente, email, password e ruolo.
 */

public class DTOManager {
    private String nomeUtente;
    private String email;
    private String password;
    private Ruolo ruolo;

    public DTOManager(String nomeUtente, String email, String password, Ruolo ruolo){
        this.nomeUtente = nomeUtente;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getEmail() {
        return email;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }
}
