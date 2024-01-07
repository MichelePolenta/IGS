package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Point;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.ManagerOSM;

public abstract class POI {

    protected Comune comune;

    protected String titolo;

    protected String descrizione;

    protected double lat,lon;

    protected boolean type;

    public POI(Comune comune,String titolo,String descrizione, double lat, double lon) throws Exception {
        if (new ManagerOSM().internoAlComune(com.mapbox.geojson.Point.fromLngLat(lon, lat), comune))
        this.comune= comune;
        else throw new Exception("Comune inserito errato rispetto alle coordinate!");
        this.titolo=titolo;
        this.descrizione=descrizione;
        this.lat=lat;
        this.lon=lon;
    }

    public boolean detectComune(Point point){
        return false;
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
