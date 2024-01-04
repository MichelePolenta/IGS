package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Point;

import java.util.List;

public class Comune {

    private String nome;
    private List<Point> area;



    public Comune(String nome) {
        this.nome = nome;
    }




    public String getNome() {
        return this.nome;
    }

    public List<Point> getArea() {
        return area;
    }
}
