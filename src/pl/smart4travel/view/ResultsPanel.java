package pl.smart4travel.view;


import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {
    public ResultsPanel() {
        String[] columnNames = {"Linia",
                "Godziny odjazdu"
                };
        Object[][] data = {
                {"130", "10:45"}
        };

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
