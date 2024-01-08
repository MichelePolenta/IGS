package it.unicam.cs.ids2324.project.Model;
/**
 * PuntoLogico
 */
public class PuntoLogico extends POI {

    public PuntoLogico(String titolo, String descrizione,PuntoFisico puntoDiRifierimento) throws Exception {
        super(puntoDiRifierimento.getComune(),titolo,descrizione, puntoDiRifierimento.getLat(), puntoDiRifierimento.getLon());
        this.type=false;
    }



    @Override
    public String toString() {
        return "Punto logico:" +
                "comune=" + this.comune.getNome() +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getLat() +
                ", longitudine=" + this.getLon() +
                ", tipologia=" + this.getTypeString() +
                '}';
    }
}