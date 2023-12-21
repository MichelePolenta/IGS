package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Curatore extends Persona {


    public Curatore(String nome, String mail, String password,Comune citta, String  dataDiNascita)throws Exception{
        super(nome, mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.CUR+"";
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
