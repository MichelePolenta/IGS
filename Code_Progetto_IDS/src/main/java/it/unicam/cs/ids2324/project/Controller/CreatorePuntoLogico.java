package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.PuntoFisico;
import it.unicam.cs.ids2324.project.Model.PuntoLogico;

public class    CreatorePuntoLogico {

    
    public POI creaPOI(String titolo, String descrizione, PuntoFisico puntoDiRifierimento){
        return new PuntoLogico(puntoDiRifierimento.getComune(), titolo, descrizione, puntoDiRifierimento);
    }

}
