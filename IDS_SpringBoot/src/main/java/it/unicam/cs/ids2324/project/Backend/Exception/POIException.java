package it.unicam.cs.ids2324.project.Backend.Exception;

/**
 * Eccezione personalizzata per gestire errori legati ai Punti di Interesse (POI) nell'applicazione.
 * Viene sollevata quando si verificano problemi relativi ai POI.
 */

public class POIException extends Exception{

    public POIException(String text){
        super(text);
    }

}
