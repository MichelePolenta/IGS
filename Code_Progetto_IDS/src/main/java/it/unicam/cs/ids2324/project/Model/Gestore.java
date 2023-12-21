package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Gestore extends Persona {
    public Gestore(String nome, String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        this.controlloCredenziali(mail, password);
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.citta = citta;
        this.dataDiNascita = LocalDate.parse(dataDiNascita);
        this.codice = generaCodice(Ruolo.GEST, this.citta.getNome());
    }

    public boolean accreditamento(String accreditamento){
        return false;
    }

    public boolean autorizzazione(String autorizzazione){
        return false;
    }

    public boolean cancellazione(String puntoDiRilievo){
        return false;
    }

    public boolean cancellazione(double itinerario){
        return false;
    }

    public boolean cancellazione(int contenuto){
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