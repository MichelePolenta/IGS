package it.unicam.cs.ids2324.project.Backend.Exception;

/**
 * Eccezione personalizzata per gestire errori legati alle credenziali nell'applicazione.
 * Viene sollevata quando si verificano problemi relativi alle credenziali utente.
 */

public class CredenzialiException extends Exception{

    public CredenzialiException(String text){
        super(text);
    }

}
