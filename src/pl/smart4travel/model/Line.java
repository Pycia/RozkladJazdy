package pl.smart4travel.model;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String id;
    private List<Stop> stops;


    public Line() {
        id = new String();
        stops = new ArrayList<Stop>();
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
