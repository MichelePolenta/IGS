package it.unicam.cs.ids2324.project.Controller;

public interface BuilderPOI {

   public void reset();

   public void setComune(String comune);

   public void setTitle(String titolo);

   public void setPosition(Double lat, Double lon);

   public void setDescription(String descrizione);

   public void setContent();


}