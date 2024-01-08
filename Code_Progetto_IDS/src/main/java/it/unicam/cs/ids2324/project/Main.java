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
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Che comune vuoi visitare?");
        String comune = scanner.next();
        System.out.println(comune);
        System.out.println(new ManagerOSM().getOsmElementPolygon(new QueryOSM().getComune(comune)).coordinates());

    }

}


