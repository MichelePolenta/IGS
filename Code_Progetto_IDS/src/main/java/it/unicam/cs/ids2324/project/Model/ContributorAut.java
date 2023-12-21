package it.unicam.cs.ids2324.project.Model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContributorAut extends Persona {

    public ContributorAut(String nome, String mail, String password, Comune citta, String dataDiNascita) throws Exception{
       super(nome, mail, password, citta, dataDiNascita);
       this.ruolo = Ruolo.CONTRAUT+"";
    }

    public boolean inserimento(PuntoLogico puntoDiRilievo){
        return false;
    }

    public boolean inserimento(double itinerario){
        return false;
    }

    public boolean modifica(PuntoLogico puntoDiRilievo){
        return false;
    }

    public boolean modifica(double itinerario){
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