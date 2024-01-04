package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.PuntoFisico;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
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

    public void close() {
        connection.disconnect();
    }

    public void executeQuery(String query) throws IOException {
        try (DataOutputStream printout = new DataOutputStream(connection.getOutputStream())) {
            printout.writeBytes("data=" + URLEncoder.encode(query, StandardCharsets.UTF_8));
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);

            parseBoundsXML(response.toString());
        }
    }

    private PuntoFisico parsePoiXML(String xmlData) {
        String cityName ="";
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xmlData)));

            Element nodeElement = (Element) xmlDoc.getElementsByTagName("node").item(0);

            NodeList tagNodes = nodeElement.getElementsByTagName("tag");
            if (tagNodes.getLength() > 2) {
                cityName = tagNodes.item(2).getAttributes().getNamedItem("v").getTextContent();
            }

            return new PuntoFisico(new Comune("bho"), cityName, "", 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Gestione dell'errore
        }
    }

    private void parseBoundsXML(String xmlData) {
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
            xmlDocManager(xmlDoc, "relation");
        } catch (Exception e) {
            e.printStackTrace(); // Gestione dell'errore
        }
    }

    private void xmlDocManager(Document xmlDoc, String type) {
        try {
            Element boundsElement;
            List<Point> results = new ArrayList<>();

            NodeList elements = xmlDoc.getElementsByTagName(type);
            if (elements.getLength() > 0) {
                boundsElement = (Element) elements.item(0);

                NodeList ndNodes = boundsElement.getElementsByTagName("nd");
                for (int j = 0; j < ndNodes.getLength(); j++) {
                    double lat = Double.parseDouble(ndNodes.item(j).getAttributes().getNamedItem("lat").getTextContent());
                    double lon = Double.parseDouble(ndNodes.item(j).getAttributes().getNamedItem("lon").getTextContent());
                    results.add(com.mapbox.geojson.Point.fromLngLat(lon, lat));
                }
            }

            Polygon boundary = Polygon.fromLngLats(Collections.singletonList(results));

            Point monumentPoint = Point.fromLngLat(13.506659, 43.6252093);
            boolean isMonumentInAncona = TurfJoins.inside(monumentPoint, boundary);
            System.out.println("Il monumento è all'interno di Ancona? " + isMonumentInAncona);
            // Resto del codice...
        } catch (Exception e) {
            e.printStackTrace(); // Gestione dell'errore
        }
    }

    public double calcolaLatLon(double min, double max) {
        return (min + max) / 2;
    }

    public boolean verificaAssociazionePOI(POI poi, double lon, double lat) {
        return poi.getLon() == lon && poi.getLat() == lat;
    }


}
