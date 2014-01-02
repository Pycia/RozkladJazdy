package pl.smart4travel.model;

import java.util.ArrayList;
import java.util.List;

public class Direction {
    private List<Stop> stops;


    public Direction() {
        stops = new ArrayList<Stop>();
    }



    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
