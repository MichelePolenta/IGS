package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.QueryOSM;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.ManagerOSM;
import com.mapbox.geojson.Point;
import java.awt.*;

public class Comune {

    private String nome;

    private Polygon confiniComune;

    public Comune(String nome) throws Exception {
        if (nome == "" || nome.equals(null)) throw new Exception("Inserire un comune");
        this.nome = nome;
        if (new ManagerOSM().getOsmElementPolygon(new QueryOSM().getComune(this.nome)).coordinates().get(0).isEmpty()) throw  new Exception("Comune errato!");
        this.confiniComune = new ManagerOSM().getOsmElementPolygon(new QueryOSM().getComune(this.nome));
    }

    public String getNome() {
        return this.nome;
    }

    public Polygon getConfiniComune() {
        return confiniComune;
    }
}
