package it.unicam.cs.ids2324.project.Model;
public class PuntoFisico extends POI {

    public PuntoFisico(Comune comune,String titolo,String descrizione, double lat, double lon) {
        super(comune,titolo,descrizione,lat,lon);
    }



    @Override
    public String toString() {
        return "Punto Fisico" +
                "comune=" + comune +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", latitudine=" + lat +
                ", longitudine=" + lon +
                '}';
    }


}
