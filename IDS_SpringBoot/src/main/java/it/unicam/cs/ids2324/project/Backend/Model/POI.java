package it.unicam.cs.ids2324.project.Backend.Model;

import com.fasterxml.jackson.annotation.*;
import com.mapbox.geojson.Point;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.List;

/**
 * Classe astratta che rappresenta un Punto di Interesse (POI) nell'applicazione.
 * La tabella di persistenza è mappata sulla tabella "poi" nel database.
 * Contiene campi comuni a tutti i tipi di POI, come:
 * - id_poi: Identificatore unico del POI.
 * - comune: Comune a cui il POI appartiene.
 * - nome: Nome del POI.
 * - descrizione: Descrizione del POI.
 * - latitudine: Coordinata latitudine del POI.
 * - longitudine: Coordinata longitudine del POI.
 * - visible: Indica se il POI è visibile o meno.
 * - tipo: Tipo di POI (PuntoFisico o PuntoLogico).
 * - itinerario: Lista di itinerari a cui il POI è associato.
 *
 */

@Entity
@Table(name = "poi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "jsonType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PuntoFisico.class, name = "PuntoFisico"),
        @JsonSubTypes.Type(value = PuntoLogico.class, name = "PuntoLogico"),
})
public abstract class  POI {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poi_seq")
    @SequenceGenerator(name = "poi_seq", sequenceName = "poi_seq", allocationSize = 1)
    @Column(name = "id_poi")
    protected int id_poi;

    @ManyToOne
    @JoinColumn(name = "comune")
    protected Comuni comune;

    @NotNull
    protected String titolo;

    protected String descrizione;

    @NotNull
    protected double latitudine;

    @NotNull
    protected  double longitudine;

    protected boolean visible;

    @JsonProperty("JsonType")
    @Column(insertable = false, updatable = false)
    protected String tipo;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "contenuto_itinerario",
            joinColumns = @JoinColumn(name = "poi"),
            inverseJoinColumns = @JoinColumn(name = "itinerario")
    )
    private List<Itinerario> itinerario ;


    public POI(@JsonProperty String tipo,  int id_poi){
        this.tipo = tipo;
        this.id_poi = id_poi;
    }


    public POI(@JsonProperty String jsonType, String titolo, String descrizione){
        this.tipo = jsonType;
        this.titolo = titolo;
        this.descrizione = descrizione;
    }

    public POI(int id_poi, Comuni comune, String titolo, String descrizione, double latitudine, double longitudine) throws Exception {
        this.comune=comune;
        this.titolo = titolo;
        this.descrizione=descrizione;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.id_poi = id_poi;
    }

    public POI(Comuni comune, String titolo, String descrizione, double latitudine, double longitudine) throws Exception {
        this.comune=comune;
        this.titolo = titolo;
        this.descrizione=descrizione;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public POI() {

    }


    public void setComune(Comuni comune) {
        this.comune = comune;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public Comuni getComune()  {
        return this.comune;
    }


    public String getDescrizione() {
        return this.descrizione;
    }

    public String getTitolo() {
        return this.titolo;
    }

    @Override
    public String toString() {
        return "POI:" +
                "comune=" + this.comune +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.latitudine +
                ", longitudine=" + this.longitudine +
                '}';
    }


    public double getLatitudine() {
        return this.latitudine;
    }

    public double getLongitudine() {
        return this.longitudine;
    }

    public Point getPoint(){
        return Point.fromLngLat(this.getLongitudine(), this.getLatitudine() );
    }

        @JsonIgnore
    public List<Itinerario> getItinerari() {
        return this.itinerario;
    }

    public int getId(){
        return this.id_poi;
    }

    public void setItinerari(List<Itinerario> itinerari) {
        this.itinerario = itinerari;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean getVisible(){
        return this.visible;
    }



}
