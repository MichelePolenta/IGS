package org.example.Model;

import java.util.regex.Pattern;
import javax.xml.crypto.Data;

public class TuristaAutenticato extends Persona {

    private String mail;
    private String password;

    public TuristaAutenticato(Persona persona, String mail, String password) throws Exception {
        super(persona.getNome(), persona.getCognome(), persona.getDataDiNascita(), persona.getCodiceFiscale(),
                persona.getCitta(), persona.getResidenza());
        if (controlloMail(mail) && controlloPassword(password)) {
            this.mail = mail;
            this.password = password;
        } else
            throw new Exception("Mail e password non compilati correttamente");
    }

    private boolean controlloPassword(String password) throws Exception {
        if (password.length() <= 8)
            throw new Exception("La password deve contenere almeno 8 caratteri");
        return true;
    }

    private boolean controlloCaratteriSpeciali(String password) {
        return false;
    }

    private boolean controlloMail(String mail) {
        if (!mail.contains("@"))
            return false;

        else if (!(mail.contains(".com")) || !(mail.contains(".it")))
            return false;

        return true;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

}
