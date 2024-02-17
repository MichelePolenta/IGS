package it.unicam.cs.ids2324.project.Backend.Util;

import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Backend.Model.Comuni;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.mapbox.geojson.Point;

/**
 * Manager per l'interazione con OpenStreetMap (OSM).
 * Questa classe gestisce le richieste a OSM per ottenere informazioni geografiche, in particolare
 * poligoni relativi agli elementi di OSM. Utilizza l'API Overpass per ottenere dati geografici
 * sulla base di una specifica query.
 */

public class ManagerOSM {

    private String host = "https://overpass-api.de/api/interpreter";
    private URL osm;
    private HttpURLConnection connection;

    public ManagerOSM() throws IOException {
        osm = new URL(host);
        connection = (HttpURLConnection) osm.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }

    public Polygon getOsmElementPolygon(String query) throws IOException, ParserConfigurationException, SAXException {
        return  parsePolygonXML(executeResponse(query));
    }


    private String executeResponse(String query) throws IOException {
        try (DataOutputStream printout = new DataOutputStream(connection.getOutputStream())) {
            printout.writeBytes("data=" + URLEncoder.encode(query, StandardCharsets.UTF_8));
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            return response.toString();
        }
    }

    private Polygon parsePolygonXML(String xmlData) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
            return xmlDocManager(xmlDoc, "relation");
    }

    private Polygon xmlDocManager(Document xmlDoc, String type) {
            Element boundsElement;
            List<Point> results = new ArrayList<>();
            NodeList elements = xmlDoc.getElementsByTagName(type);
            if (elements.getLength() > 0) {
                boundsElement = (Element) elements.item(0);
                NodeList ndNodes = boundsElement.getElementsByTagName("nd");
                for (int j = 0; j < ndNodes.getLength(); j++) {
                    double lat = Double.parseDouble(ndNodes.item(j).getAttributes().getNamedItem("lat").getTextContent());
                    double lon = Double.parseDouble(ndNodes.item(j).getAttributes().getNamedItem("lon").getTextContent());
                    results.add(Point.fromLngLat(lon, lat));
                }
            }
            Polygon boundary = Polygon.fromLngLats(Collections.singletonList(results));
            return boundary;
    }

    public boolean internoAlComune(Point point, Comuni comune) throws IOException, ParserConfigurationException, SAXException {
       return TurfJoins.inside( point , this.getOsmElementPolygon("[out:xml];area['name'='"+ comune.getNome() +"']->.a;relation(area.a)['name'='"+ comune.getNome() +"']['admin_level'='8'];out geom;"));
    }


}
