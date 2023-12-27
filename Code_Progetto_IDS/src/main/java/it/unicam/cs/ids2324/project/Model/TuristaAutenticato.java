package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class TuristaAutenticato extends Persona {


    public TuristaAutenticato(String nome, String cognome,String mail, String password, Comune citta, String dataDiNascita) throws Exception {
        super(nome,  cognome,mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.TURAUT+"";
    }




}
