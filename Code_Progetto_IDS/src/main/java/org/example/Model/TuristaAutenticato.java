package org.example.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.crypto.Data;

public class TuristaAutenticato extends Persona implements AccountManager {

    private List<String> storicoInformazioniSalvate = new ArrayList<String>();
    private List<String> storicoSegnalazioni = new ArrayList<String>();

    public TuristaAutenticato(String nome, Date dataDiNascita, String citta, String mail, String password)
            throws Exception {
        super(nome, dataDiNascita, citta, mail, password);
        this.codice = generaCodice();
    }

    public String generaCodice() {
        return Ruolo.TURAUT + "" + this.getNome().charAt(0) + "" + this.getNome().charAt(this.getNome().length() - 1);
    }

}
