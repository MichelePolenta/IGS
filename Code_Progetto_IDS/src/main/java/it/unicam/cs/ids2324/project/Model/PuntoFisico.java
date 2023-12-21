package it.unicam.cs.ids2324.project.Model;
public class PuntoFisico {

    private final String comune;

    private final String titolo;

    private final String descrizione;

    private final double lat,lon;

    public PuntoFisico(String comune,String titolo,String descrizione, double lat, double lon) {
        this.comune=comune;
        this.titolo=titolo;
        this.descrizione=descrizione;
        this.lat=lat;
        this.lon=lon;        
    }

    public String getComune() {
        return comune;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTitolo() {
        return titolo;
    }

}
