package it.unicam.cs.ids2324.project.Model;

public abstract class POI {

    protected Comune comune;

    protected String titolo;

    protected String descrizione;

    protected double lat,lon;

    public POI(Comune comune,String titolo,String descrizione, double lat, double lon) {
        this.comune=comune;
        this.titolo=titolo;
        this.descrizione=descrizione;
        this.lat=lat;
        this.lon=lon;
    }

    public Comune getComune() {
        return this.comune;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public String getTitolo() {
        return this.titolo;
    }

    @Override
    public String toString() {
        return "POI" +
                "comune=" + comune +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", latitudine=" + lat +
                ", longitudine=" + lon +
                '}';
    }
}
