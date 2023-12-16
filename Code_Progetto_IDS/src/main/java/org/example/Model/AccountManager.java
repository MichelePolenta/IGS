package org.example.Model;

public interface AccountManager {

    public String generaCodice();

    public boolean controlloMail(String mail) throws Exception;

    public boolean controlloPassword(String password) throws Exception;
}
