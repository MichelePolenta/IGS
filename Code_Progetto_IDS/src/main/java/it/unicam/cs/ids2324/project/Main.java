package it.unicam.cs.ids2324.project;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.QueryOSM;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.ManagerOSM;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SQLException, IOException, SAXException {
        new ManagerOSM().executeQuery(new QueryOSM().getComune("Ancona"));
    }

}


