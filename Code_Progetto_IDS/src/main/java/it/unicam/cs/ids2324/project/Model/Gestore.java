package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class Gestore extends Persona {
    public Gestore(String nome, String cognome,String mail, String password,Comune citta, String dataDiNascita) throws Exception{
        super(nome, mail, cognome,password, citta, dataDiNascita);
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



}