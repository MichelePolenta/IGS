package org.example.Model;

public interface Persona {

    String nome = "";
    String cognome = "";
    int eta = 0;
    String codiceFiscale = "";
    String citta = "";
    String residenza = "";

    default public String getCitta() {
        return citta;
    }

    default public String getNome() {
        return nome;
    }

    default public String getCognome() {
        return cognome;
    }

    default public String getCodiceFiscale() {
        return codiceFiscale;
    }

    default public int getEta() {
        return eta;
    }

    default public String getResidenza() {
        return residenza;
    }
}
