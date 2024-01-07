package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfJoins;
import it.unicam.cs.ids2324.project.Model.Comune;
import it.unicam.cs.ids2324.project.Model.POI;
import it.unicam.cs.ids2324.project.Model.Persona;
import it.unicam.cs.ids2324.project.Model.PuntoFisico;
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

    public Polygon getOsmElementPolygon(String query) throws IOException, ParserConfigurationException, SAXException {
        return  parsePolygonXML(executeResponse(query));
    }


    private PuntoFisico parsePoiXML(String xmlData, String comune) throws Exception {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
            Element nodeElement = (Element) xmlDoc.getElementsByTagName("node").item(0);
            String name = nodeElement.getElementsByTagName("tag").item(indexFinder(nodeElement,"name")).
                    getAttributes().getNamedItem("v").getTextContent();
            return new PuntoFisico(new Comune(comune), name, "", 0, 0);
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
                    results.add(com.mapbox.geojson.Point.fromLngLat(lon, lat));
                }
            }
            Polygon boundary = Polygon.fromLngLats(Collections.singletonList(results));
            return boundary;
    }

    public boolean internoAlComune(Point point, Comune comune){
        return TurfJoins.inside( point , comune.getConfiniComune());
    }

    public double calcolaLatLon(double min, double max) {
        return (min + max) / 2;
    }

    public boolean verificaAssociazionePOI(POI poi, double lon, double lat) {
        return poi.getLon() == lon && poi.getLat() == lat;
    }


}
