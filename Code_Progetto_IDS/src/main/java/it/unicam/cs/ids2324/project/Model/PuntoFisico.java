package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Point;


public class PuntoFisico extends POI {

    public PuntoFisico(Comune comune, String titolo, String descrizione, Point point) throws Exception {
        super(comune,titolo,descrizione,point);
        this.type=true;
    }

    @Override
    public String toString() {
        return "Punto fisico:" +
                "comune=" + this.comune.getNome() +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getPoint().latitude() +
                ", longitudine=" + this.getPoint().longitude() +
                ", tipologia=" + this.getTypeString() +
                '}';
    }


}
