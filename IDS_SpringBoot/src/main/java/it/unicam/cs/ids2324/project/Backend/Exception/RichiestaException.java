package it.unicam.cs.ids2324.project.Backend.Exception;

/**
 * Eccezione personalizzata per gestire errori legati alle richieste nell'applicazione.
 * Viene sollevata quando si verificano problemi relativi alle richieste.
 */

public class RichiestaException extends Exception{

    public RichiestaException(String text){
        super(text);
    }

}
