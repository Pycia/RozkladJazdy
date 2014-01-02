package pl.smart4travel.model;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String id;
    private List<Direction> directionList;

    public Line() {
        id = new String();
        directionList = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }

    public void setDirections(List<Direction> directionList) {
        this.directionList=directionList;
    }
}
