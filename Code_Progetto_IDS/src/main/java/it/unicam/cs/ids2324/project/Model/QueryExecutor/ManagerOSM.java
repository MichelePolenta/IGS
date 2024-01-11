package it.unicam.cs.ids2324.project.Model.QueryExecutor;

import com.mapbox.geojson.PointAsCoordinatesTypeAdapter;
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

    public PuntoFisico getOsmElementPoi(String query,Comune comune) throws Exception {
        return parsePoiXML(executeResponse(query),comune);
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


    private PuntoFisico parsePoiXML(String xmlData, Comune comune) throws Exception {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
            Element nodeElement = (Element) xmlDoc.getElementsByTagName("node").item(0);
            String name = nodeElement.getElementsByTagName("tag").item(indexFinder(nodeElement,"name")).
                    getAttributes().getNamedItem("v").getTextContent();
            double lat = Double.parseDouble(nodeElement.getAttribute("lat"));
            double lon = Double.parseDouble(nodeElement.getAttribute("lon"));
            return new PuntoFisico(comune, name, "Punto ricavati da OpenStreetMap", Point.fromLngLat(lon,lat));
    }

    private int indexFinder(Element node, String name){
        for (int i = 0; i < node.getElementsByTagName("tag").getLength(); i++) {
            if (node.getElementsByTagName("tag").item(i).getAttributes().getNamedItem("k").getTextContent().equals(name)){
                return i;
            }
        }
        return -1;
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

    public boolean internoAlComune(Point point, Comune comune){
        return TurfJoins.inside( point , comune.getConfiniComune());
    }

    public double calcolaLatLon(double min, double max) {
        return (min + max) / 2;
    }

    public boolean verificaAssociazionePOI(POI poi, double lon, double lat) {
        return poi.getPoint().longitude() == lon && poi.getPoint().latitude() == lat;
    }


}
