package pl.smart4travel.model;

import java.util.List;

public class Stop {
    private String id;
    private List<Time> timeList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }
}
