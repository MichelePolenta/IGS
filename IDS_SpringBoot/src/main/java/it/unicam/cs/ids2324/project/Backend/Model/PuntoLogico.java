package it.unicam.cs.ids2324.project.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un Punto Logico di Interesse (POI) nell'applicazione.
 * Estende la classe astratta POI e aggiunge le informazioni specifiche per un Punto Logico.
 * La tabella di persistenza è mappata sulla tabella "poi" nel database con il valore discriminator "Logico".
 */

@Entity
@Table(name = "poi")
@DiscriminatorValue("Logico")

public class PuntoLogico extends POI {

    public PuntoLogico(){}

    public PuntoLogico(int id,String titolo, String descrizione, PuntoFisico puntoDiRifierimento) throws Exception {
        super(id,puntoDiRifierimento.getComune(),titolo,descrizione,puntoDiRifierimento.getLatitudine(),puntoDiRifierimento.getLongitudine());
    }

    public PuntoLogico(String titolo, String descrizione, PuntoFisico puntoDiRifierimento) throws Exception {
        super(puntoDiRifierimento.getComune(),titolo,descrizione,puntoDiRifierimento.getLatitudine(),puntoDiRifierimento.getLongitudine());
    }

    public PuntoLogico(@JsonProperty String tipo, int id_poi){
        super(tipo, id_poi);
    }

    public PuntoLogico(@JsonProperty String jsonType, String nome,String descrizione){
      super(jsonType, nome, descrizione);
    }


    @Override
    public String toString() {
        return "Punto logico:" +
                "comune=" + this.comune +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getPoint().latitude() +
                ", longitudine=" + this.getPoint().longitude()+
                '}';
    }
}