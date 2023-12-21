package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Curatore extends Persona {


    public Curatore(String nome, String mail, String password,Comune citta, String  dataDiNascita)throws Exception{
        this.controlloCredenziali(mail, password);
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = LocalDate.parse(dataDiNascita);
        this.codice = generaCodice(Ruolo.CUR, this.citta.getNome());
    }
    
    public boolean accettazione(String richiestaInserimento){
        return false;
    }

    public boolean accettazione(double richiestaModifica){
        return false;
    }

    @Override
    public Comune getCitta() {
        return this.citta;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String getCodice() {
        return this.codice;
    }

    @Override
    public String getMail() {
        return this.mail;
    }

    @Override
    public LocalDate getDataDiNascita() {
        return this.dataDiNascita;
    }


}
