package org.example.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Persona {

    String nome = "";
    LocalDate dataDiNascita = null;
    String citta = "";
    String codice = "";
    String mail = "";
    String password = "";


    default boolean controlloCredenziali(String mail, String password) throws Exception {
        if (!controlloPassword(password))
            return false;
        else if (!controlloMail(mail))
            return false;
        return true;
    }

    default boolean controlloPassword(String password) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new Exception("La passowrd non è sicura");
        //this.password = password;
        return true;
    }

    default boolean controlloMail(String mail) throws Exception {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.matches())
            throw new Exception("La mail non è stata scritta correttamente");
        //this.mail = mail;
        return true;
        //test

    }

    default void visualizza(){}

    default void seleziona(){}


    default String getCitta() {
        return citta;
    }


    default String getNome() {
        return nome;
    }

    default String password() {
        return password;
    }

    default String getCodice() {
        return codice;
    }

    default String getMail() {
        return mail;
    }

    default LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
}
