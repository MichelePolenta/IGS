package org.example.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public abstract class Persona {

    protected String nome;
    protected String mail;
    protected String password;
    protected Comune citta;
    protected LocalDate dataDiNascita;
    protected String codice;
    
    
    boolean controlloCredenziali(String mail, String password) throws Exception {
        if (!controlloPassword(password))
            return false;
        else if (!controlloMail(mail))
            return false;
        return true;
    }

    boolean controlloPassword(String password) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new Exception("La passowrd non è sicura");
        return true;
    }

    boolean controlloMail(String mail) throws Exception {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.matches())
            throw new Exception("La mail non è stata scritta correttamente");
        return true;
    }

    void visualizza(){}

    void seleziona(){}

    void segnala(String text){}

    void salvaInformazioni(String text){}


    abstract Comune getCitta();


    abstract String getNome();

    abstract String password();

    abstract String getCodice();

    abstract String getMail();

    abstract LocalDate getDataDiNascita();

    String generaCodice(Ruolo ruolo, String citta){
        Random random = new Random();
        return ruolo +""+random.nextInt(9)+""+random.nextInt(9)+citta;
    }
}
