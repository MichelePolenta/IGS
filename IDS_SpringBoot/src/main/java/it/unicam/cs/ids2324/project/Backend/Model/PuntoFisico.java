package it.unicam.cs.ids2324.project.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un Punto Fisico di Interesse (POI) nell'applicazione.
 * Estende la classe astratta POI e aggiunge le informazioni specifiche per un Punto Fisico.
 * La tabella di persistenza è mappata sulla tabella "poi" nel database con il valore discriminator "Fisico".
 */

@Entity
@Table(name = "poi")
@DiscriminatorValue("Fisico")
public class PuntoFisico extends POI {
    public PuntoFisico(){}

    public PuntoFisico(Comuni comune,String titolo,String descrizione,double latitudine,double longitudine)  throws Exception{
        super(comune,titolo,descrizione,latitudine,longitudine);
    }

    public PuntoFisico(int id,Comuni comune,String titolo,String descrizione,double latitudine,double longitudine)  throws Exception{
        super(id,comune,titolo,descrizione,latitudine,longitudine);
    }

    public PuntoFisico(@JsonProperty String tipo,  int id_poi){
        super(tipo, id_poi);
    }


    @Override
    public String toString() {
        return "Punto fisico:" +
                "comune=" + this.comune +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.latitudine +
                ", longitudine=" + this.longitudine +
                '}';
    }


}
