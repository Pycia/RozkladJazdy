package pl.smart4travel.view;


import org.jdesktop.swingx.JXDatePicker;
import pl.smart4travel.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.Date;
import java.util.Vector;

public class ResultsPanel extends JPanel implements InputPanelListener {
    private Stop stop;
    private JTable table;

    public ResultsPanel() {
        String[] columnNames = {"Linia",
                "Godziny odjazdu"
                };
        Object[][] data = {
                {"", ""},
                {"", ""},
                {"", ""},
                {"", ""}
        };

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setPreferredSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    @Override
    public void onSubmit(String from, String to, int date, Model model) {
        for(int i=0; i<4; i++){
            table.setValueAt("",i,0);
            table.setValueAt("",i,1);
        }
        Stop currentStop= new Stop();
        Vector<String> columnNames = new Vector();
        columnNames.add("Linia");
        columnNames.add("Godziny odjazdu");
        Vector<String> data= new Vector<>();
        Date currentDate = new Date();
        for(Line line:model.getLineList())
        {
            boolean ok=false;
            for(Direction direction:line.getDirectionList())
                for (Stop stop : direction.getStops()) {
                    if (stop.getId().equals(from)) {
                        ok = true;
                        currentStop=stop;
                    } else
                    if (ok && stop.getId().equals(to)) {
                        int i=0;
                        table.setValueAt(stop.getLineId(),0,0);
                        for (Time time : currentStop.getTimeList()) {
                            currentDate.setTime((time.getHour()*60*60 + time.getMinute()*60)*1000);
                            if(currentDate.getTime()>= date-60*60*1000 && currentDate.getTime()<=date +60*60*1000){
                                if(time.getMinute()<9)
                                    table.setValueAt(time.getHour() + ":0" + time.getMinute(),i,1);
                                else
                                    table.setValueAt(time.getHour() + ":" + time.getMinute(),i,1);
                                i++;
                            }
                        }
                        return;
                    }
                }
            ok=false;
        }
        table.setValueAt("Brak linii odpowiadającej zapytaniu",0,0);
        for(int i=0; i<4; i++){
            table.setValueAt("",i,1);
        }
    }
}
