package pl.smart4travel.view;


import org.jdesktop.swingx.JXDatePicker;
import pl.smart4travel.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.Date;
import java.util.Vector;

public class ResultsPanel extends JPanel {
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

    public void setResult(Route route) {
        for(int i=0; i<4; i++){
            table.setValueAt("",i,0);
            table.setValueAt("",i,1);
        }
        Date currentDate = new Date();
        boolean isTime = false;
        Vector<String> columnNames = new Vector();
        columnNames.add("Linia");
        columnNames.add("Godziny odjazdu");
        if(route.getLineId()!=null){
            table.setValueAt(route.getLineId(),0,0);
            int i=0;
            for (Time time : route.getTimeList()) {
                isTime = true;
                if(time.getMinute()<9)
                    table.setValueAt(time.getHour() + ":0" + time.getMinute(),i,1);
                else
                    table.setValueAt(time.getHour() + ":" + time.getMinute(),i,1);
                i++;
            }
            if(!isTime)
                table.setValueAt("Brak odjazdów w o tej godzinie",0,1);
            return;
        } else{
            table.setValueAt("Brak linii odpowiadającej zapytaniu",0,0);
            for(int i=0; i<4; i++){
                table.setValueAt("",i,1);
            }
        }
    }
}
