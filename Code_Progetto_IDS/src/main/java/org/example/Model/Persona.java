package org.example.Model;

import java.util.Date;
import java.util.regex.Pattern;

public class Persona {

    private String nome;
    private Date dataDiNascita;
    private String citta;
    protected String codice;
    private String mail;
    private String password;

    public Persona(String nome, Date dataDiNascita, String citta, String mail, String password)
            throws Exception {
        if (controlloCredenziali(mail, password)) {
            this.nome = nome;
            this.dataDiNascita = dataDiNascita;
            this.citta = citta;
        }
    }

    public boolean controlloCredenziali(String mail, String password) throws Exception {
        if (controlloPassword(password))
            this.password = password;
        else
            return false;
        if (controlloMail(mail))
            this.mail = mail;
        else
            return false;
        return false;
    }

    public boolean controlloPassword(String password) throws Exception {
        if (password.length() < 8)
            throw new Exception("La password è troppo corta");
        Pattern pattern = Pattern.compile("[!@#$%^&*()_+{}|:;<>?,./]");
        if (!pattern.matcher(password).matches())
            throw new Exception("La passowrd non contiene caratteri speciali");
        return true;
    }

    public boolean controlloMail(String mail) throws Exception {
        if ((!mail.contains("@")) || (!mail.contains(".it")) || (!mail.contains(".com")))
            throw new Exception("La mail non è stata scritta correttamente");
        return true;
    }

    public String getCitta() {
        return citta;
    }

    public String getNome() {
        return nome;
    }

    public String password() {
        return password;
    }

    public String getCodice() {
        return codice;
    }

    public String getMail() {
        return mail;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }
}
