package org.example.Controller;

import org.example.Model.PuntoFisico;

public class PuntoLogicoBuilder implements BuilderPOI{
    
    private String comune;

    private String titolo;

    private String descrizione;

    private double lat,lon;

    

    public PuntoFisico getResult() {
        return new PuntoFisico(this.comune, this.titolo, this.descrizione, this.lat, this.lon);
    }

    @Override
    public void reset(){
        this.comune=null;
        this.descrizione=null;
        this.lat=0;
        this.lon=0;
        this.titolo=null;
    }

    @Override
   public void setComune(String comune){
        this.comune=comune;
   }

   @Override
   public void setTitle(String titolo){
        this.titolo=titolo;
   }

   @Override
   public void setPosition(Double lat, Double lon){
        this.lat=lat;
        this.lon=lon;
   }

   @Override
   public void setDescription(String descrizione){
        this.descrizione=descrizione;
   }

   @Override
   public void setContent(){

   }
    
}
