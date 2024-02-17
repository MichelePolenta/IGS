package it.unicam.cs.ids2324.project.Backend.Exception;

/**
 * Eccezione personalizzata per gestire errori legati agli itinerari nell'applicazione.
 * Viene sollevata quando si verificano problemi relativi agli itinerari.
 */

public class ItinerarioException extends Exception{

    public ItinerarioException(String text){
        super(text);
    }

}
