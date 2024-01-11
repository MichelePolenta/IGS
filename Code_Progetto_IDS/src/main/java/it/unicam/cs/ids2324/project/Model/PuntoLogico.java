package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Point;

/**
 * PuntoLogico
 */
public class PuntoLogico extends POI {

    public PuntoLogico(String titolo, String descrizione, PuntoFisico puntoDiRifierimento) throws Exception {
        super(puntoDiRifierimento.getComune(),titolo,descrizione, puntoDiRifierimento.getPoint());
        this.type=false;
    }



    @Override
    public String toString() {
        return "Punto logico:" +
                "comune=" + this.comune.getNome() +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getPoint().latitude() +
                ", longitudine=" + this.getPoint().longitude()+
                ", tipologia=" + this.getTypeString() +
                '}';
    }
}