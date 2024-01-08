package it.unicam.cs.ids2324.project.Model;
public class PuntoFisico extends POI {

    public PuntoFisico(Comune comune,String titolo,String descrizione, double lat, double lon) throws Exception {
        super(comune,titolo,descrizione,lat,lon);
        this.type=true;
    }

    @Override
    public String toString() {
        return "Punto fisico:" +
                "comune=" + this.comune.getNome() +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getLat() +
                ", longitudine=" + this.getLon() +
                ", tipologia=" + this.getTypeString() +
                '}';
    }


}
