package it.unicam.cs.ids2324.project.Model;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Itinerario {

    private String titolo;
    private String descrizione;
    private ArrayList<POI> punti;
    private ArrayList<Media> contenuti;

    public Itinerario(String titolo, String  descrizione, ArrayList<POI> punti) throws  Exception{
        if (punti.size() <2) throw  new Exception("L'itinerario deve contenere almeno due punti");
        this.titolo  = titolo;
        this.descrizione = descrizione;
        this.punti = punti;
    }

    public void  aggiungiPOI(POI poi){
        this.punti.add(poi);
    }

    public void addMedia(Media media){
        this.contenuti.add(media);
    }

    public void addMedia(ArrayList<Media> medias){
        this.contenuti.addAll(medias);
    }

    public void  aggiungiPOI(List<POI> pois){
        this.punti.addAll(pois);
    }

    public boolean rimuoviPOI(POI poi) throws  Exception{
        if(punti.size()<3) throw new Exception("Per poter rimuovere un punto l'itinerario ne deve contenere almeno tre");
        for (POI punto : this.punti){
            Iterator iterator = punti.iterator();
            if (poi.equals(iterator.next()))
                this.punti.remove(poi);
            return true;
        }
        return false;
    }

    public ArrayList<POI> getPunti() {
        return punti;
    }

    public List<Media> getContenuti() {
        return contenuti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getTitolo() {
        return titolo;
    }


}
