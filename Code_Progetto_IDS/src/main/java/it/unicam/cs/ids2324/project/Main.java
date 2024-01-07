package it.unicam.cs.ids2324.project;

import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.PuntoFisico;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.QueryOSM;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.ManagerOSM;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import java.util.Scanner;

import static org.eclipse.persistence.expressions.ExpressionOperator.Log;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SQLException, IOException, SAXException {
        new ManagerOSM().executeQuery(new QueryOSM().getComune("Ancona"));
    }

}


