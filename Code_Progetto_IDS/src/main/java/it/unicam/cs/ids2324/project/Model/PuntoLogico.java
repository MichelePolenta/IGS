package it.unicam.cs.ids2324.project.Model;
/**
 * PuntoLogico
 */
public class PuntoLogico extends POI {

    public PuntoLogico(Comune comune, String titolo, String descrizione,PuntoFisico puntoDiRifierimento) {
        super(puntoDiRifierimento.getComune(),titolo,descrizione, puntoDiRifierimento.getLat(), puntoDiRifierimento.getLon());
    }



    @Override
    public String toString() {
        return "Punto Logico" +
                "comune=" + comune +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", latitudine=" + lat +
                ", longitudine=" + lon +
                '}';
    }
}