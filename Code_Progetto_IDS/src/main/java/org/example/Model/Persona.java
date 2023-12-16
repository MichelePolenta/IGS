package org.example.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {

    private String nome;
    private LocalDate dataDiNascita;
    private String citta;
    protected String codice;
    private String mail;
    private String password;

    public Persona(String nome, String dataDiNascita, String citta, String mail, String password)
            throws Exception {
        if (controlloCredenziali(mail, password)) {
            this.nome = nome;
            this.dataDiNascita = LocalDate.parse(dataDiNascita, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.citta = citta;
            this.mail = mail;
        }
    }

    public boolean controlloCredenziali(String mail, String password) throws Exception {
        if (!controlloPassword(password))
            return false;
        else if (!controlloMail(mail))
            return false;
        return true;
    }

    public boolean controlloPassword(String password) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new Exception("La passowrd non è sicura");
        this.password = password;
        return true;
    }

    public boolean controlloMail(String mail) throws Exception {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.matches())
            throw new Exception("La mail non è stata scritta correttamente");
        this.mail = mail;
        return true;

    }

    public String getCitta() {
        return citta;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\n" + "Codice: " + this.getCodice() + "\n" + "Mail: " + this.getMail();
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
}
