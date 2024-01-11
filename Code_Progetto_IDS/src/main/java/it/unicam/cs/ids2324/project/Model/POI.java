package it.unicam.cs.ids2324.project.Model;

import com.mapbox.geojson.Point;
import it.unicam.cs.ids2324.project.Model.QueryDatabase.SelectQuery;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.ManagerOSM;
import it.unicam.cs.ids2324.project.Model.QueryExecutor.QueryExecutorSelect;

import java.sql.SQLException;

public abstract class POI {

    protected Comune comune;
    protected String titolo;
    protected String descrizione;
    protected Point point;
    protected boolean type;

    public POI(Comune comune,String titolo,String descrizione, Point point) throws Exception {
        //if (new ManagerOSM().internoAlComune(point, comune))
        this.comune= comune;
        //else throw new Exception("Comune inserito errato rispetto alle coordinate!");
        this.titolo=titolo;
        this.descrizione=descrizione;
        this.point = point;
    }

    public int detectId() throws SQLException {
        QueryExecutorSelect select = new QueryExecutorSelect();
        int id = select.getIdPoi(this.getTitolo(), this.getDescrizione());
        select.getConnection().close();
        return id;
    }

    public Comune getComune() {
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
                "comune=" + this.comune.getNome() +
                ", titolo='" + this.getTitolo() + '\'' +
                ", descrizione='" + this.getDescrizione() + '\'' +
                ", latitudine=" + this.getPoint().latitude() +
                ", longitudine=" + this.getPoint().longitude() +
                ", tipologia=" + this.getTypeString() +
                '}';
    }

    public boolean getType() { return this.type; }

    public String getTypeString() {
        if (this.type) return "Fisico";
        else return "Logico";
    }

    public Point getPoint() {
        return point;
    }
}
