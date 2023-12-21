package it.unicam.cs.ids2324.project.Controller;

import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.PuntoFisico;

public class CreatorePuntoFisico {

    public POI creaPOI(Comune comune, String titolo, String descrizione, double lat, double lon) {
        return new PuntoFisico(comune,titolo,descrizione,lat,lon);
    }

}
