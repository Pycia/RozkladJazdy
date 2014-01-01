package pl.smart4travel.model;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXMLFile {
    private List<Line> lineList;

    public ReadXMLFile() {
        lineList = new ArrayList<Line>();
    }

    public void read() {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("resources\\bus_stops.xml"));

            NodeList lines = doc.getElementsByTagName("line");

            if(lines != null && lines.getLength()>0)
            {
                List<Line> lineList = new ArrayList<>();
                for(int i=0; i<lines.getLength(); i++)
                {
                    Element line = (Element)lines.item(i);
                    NodeList directions = line.getElementsByTagName("direction");
                    List<Stop> stopsList = new ArrayList<>();

                    for(int j=0; j<directions.getLength(); j++)
                    {
                        Element direction = (Element)directions.item(j);
                        NodeList stops = direction.getElementsByTagName("stop");


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
                            concreteStop.setTimeList(timeList);
                            stopsList.add(concreteStop);
                        }
                    }
                    Line concreteLine = new Line();
                    concreteLine.setId(line.getAttribute("id"));
                    concreteLine.setStops(stopsList);
                    lineList.add(concreteLine);
                    System.out.println(lineList.get(i).getId());
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
