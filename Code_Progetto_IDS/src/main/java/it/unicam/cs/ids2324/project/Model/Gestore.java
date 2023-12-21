package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Gestore extends Persona {
    public Gestore(String nome, String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.GEST+"";
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
    String getRuolo() {
        return this.ruolo;
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