package pl.smart4travel.model;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Model {
    private List<Line> lineList;

    public Model() {
        lineList = new ArrayList<Line>();
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public Set<String> getStopsList() {
        Set<String> stopsList = new TreeSet<>();
        for(Line line: lineList)
            for(Direction direction: line.getDirectionList())
                for (Stop stop : direction.getStops()) {
                   stopsList.add(stop.getId());
                }
        return stopsList;
    }



    public boolean read() {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("resources\\bus_stops.xml"));

            NodeList lines = doc.getElementsByTagName("line");

            if(lines != null && lines.getLength()>0)
            {
                for(int i=0; i<lines.getLength(); i++)
                {
                    Element line = (Element)lines.item(i);
                    NodeList directions = line.getElementsByTagName("direction");
                    List<Direction> directionList = new ArrayList<>();

                    for(int j=0; j<directions.getLength(); j++)
                    {
                        Element direction = (Element)directions.item(j);
                        NodeList stops = direction.getElementsByTagName("stop");
                        List<Stop> stopsList = new ArrayList<>();

                        for(int k=0; k<stops.getLength(); k++)
                        {
                            Element stop = (Element)stops.item(k);
                            NodeList times = stop.getElementsByTagName("time");

                            List<Time> timeList = new ArrayList<>();
                            for(int l=0; l<times.getLength(); l++){
                                Element time = (Element)times.item(l);
                                timeList.add(new Time(Integer.parseInt(time.getElementsByTagName("hour").item(0).getTextContent()),
                                        Integer.parseInt(time.getElementsByTagName("min").item(0).getTextContent())));
                            }
                            Stop concreteStop = new Stop();
                            concreteStop.setId(stop.getAttribute("id"));
                            concreteStop.setLineId(line.getAttribute("id"));
                            concreteStop.setTimeList(timeList);
                            stopsList.add(concreteStop);
                        }
                        Direction concreteDirection = new Direction();
                        concreteDirection.setStops(stopsList);
                        directionList.add(concreteDirection);
                    }
                    Line concreteLine = new Line();
                    concreteLine.setId(line.getAttribute("id"));
                    concreteLine.setDirections(directionList);
                    lineList.add(concreteLine);
                }

            }

        } catch (Exception e) {
           return false;
        }
        return true;
    }

    public Route findRoute(String from, String to, int date) {

        Stop currentStop= new Stop();
        Date currentDate = new Date();
        Route route = new Route();
        List<Time> timeList = new ArrayList<>();
        for(Line line:this.getLineList())
        {
            boolean ok=false;
            boolean isTime=false;
            for(Direction direction:line.getDirectionList())
                for (Stop stop : direction.getStops()) {
                    if (stop.getId().equals(from)) {
                        ok = true;
                        currentStop=stop;
                    } else
                    if (ok && stop.getId().equals(to)) {
                        int i=0;
                        route.setLineId(stop.getLineId());
                        for (Time time : currentStop.getTimeList()) {
                            currentDate.setTime((time.getHour()*60*60 + time.getMinute()*60)*1000);
                            if(currentDate.getTime()>= date-60*60*1000 && currentDate.getTime()<=date +60*60*1000){
                                timeList.add(time);
                                i++;
                            }
                        }
                        route.setTimeList(timeList);
                        return route;
                    }
                }
            ok=false;
        }
        route.setTimeList(timeList);
        return route;
    }
}

