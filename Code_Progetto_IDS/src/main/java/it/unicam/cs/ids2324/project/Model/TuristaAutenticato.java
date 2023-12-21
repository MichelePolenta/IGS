package it.unicam.cs.ids2324.project.Model;
import java.time.LocalDate;

public class TuristaAutenticato extends Persona {


    public TuristaAutenticato(String nome, String mail, String password, Comune citta, String dataDiNascita) throws Exception {
        super(nome, mail, password, citta, dataDiNascita);
        this.ruolo = Ruolo.TURAUT+"";
    }

    @Override
    Comune getCitta() {
        return this.citta;
    }

    @Override
    String getNome() {
        return this.nome;
    }

    @Override
    String password() {
        return this.password;
    }

    @Override
    String getRuolo() {
        return this.ruolo;
    }

    @Override
    String getMail() {
        return this.mail;
    }

    @Override
    LocalDate getDataDiNascita() {
        return this.dataDiNascita;
    }


}
