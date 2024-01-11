package it.unicam.cs.ids2324.project.Model;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorSelect;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public abstract class Persona {

    protected String nome;
    protected String cognome;
    protected String mail;
    protected String password;
    protected Comune comune;
    protected LocalDate dataDiNascita;
    protected String ruolo;

    Persona(String nome, String cognome, String mail, String password, Comune comune, String dataDiNascita) throws Exception {
        this.controlloCredenziali(mail, password);
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataDiNascita = LocalDate.parse(dataDiNascita, formatter);
    }
    
    boolean controlloCredenziali(String mail, String password) throws Exception {
        /*  if (!controlloPassword(password))
            return false;
        else if (!controlloMail(mail))
            return false;*/
        return true;
    }

    boolean controlloPassword(String password) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new Exception("La passowrd non è sicura");
        return true;
    }

    boolean controlloDataDiNascita(String dataDiNascita){
        return false;
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

    public int detectId()throws Exception{
        return new QueryExecutorSelect().getId(new SelectQuery().getIdPersona(this.getMail(), this.getPassword()));
    }

    public Comune getComune(){
        return this.comune;
    }


    public String getNome(){
        return this.nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRuolo(){
        return this.ruolo;
    }

    public String getMail(){
        return this.mail;
    }

    public LocalDate getDataDiNascita(){
        return this.dataDiNascita;
    }



    @Override
    public String toString() {
        return "Nome: "+this.nome+"\n"+
                "Mail: "+this.mail+"\n"+
                "Password: "+this.password+"\n"+
                "Citta: "+this.comune.getNome()+"\n"+
                "Data: "+this.dataDiNascita+"\n"+
                "Ruolo: "+this.ruolo+"";
    }
}
