package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Curatore extends ContributorAut {


    public Curatore(String nome, String cognome,String mail, String password,Comune citta, String  dataDiNascita)throws Exception{
        super(nome, cognome,mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.CUR+"";
    }
    
    public boolean accettazione(String richiestaInserimento){
        return false;
    }

    public boolean accettazione(double richiestaModifica){
        return false;
    }





}
