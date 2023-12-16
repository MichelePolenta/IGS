package org.example.Model;

import javax.xml.crypto.Data;

class Gestore extends Persona {

    private String mail;
    private String password;

    public Gestore(String nome, String cognome, Data data, String codiceFiscale, String citta, String residenza) {
        super(nome, cognome, data, codiceFiscale, citta, residenza);
    }

}