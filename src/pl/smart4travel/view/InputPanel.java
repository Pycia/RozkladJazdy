package pl.smart4travel.view;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public InputPanel() {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        add(new JLabel("Z:"), createConstraints(1, 1, false));
        JComboBox from = new JComboBox();
        add(from, createConstraints(1, 2, true));

        add(new JLabel("Do:"), createConstraints(1, 3, false));
        JComboBox to = new JComboBox();
        add(to, createConstraints(1, 4, true));

        add(new JLabel("Data:"), createConstraints(2, 1, false));
        JComboBox date = new JComboBox();
        add(date, createConstraints(2, 2, true));

        add(new JLabel("Godzina:"), createConstraints(2, 3, false));
        JComboBox hour = new JComboBox();
        add(hour, createConstraints(2, 4, true));

        JButton okButton = new JButton();
        okButton.setText("OK");
        add(okButton, createConstraints(3, 4, true));

    }

    GridBagConstraints createConstraints(int row, int column, boolean stretch) {
        return new GridBagConstraints(column, row, 1, 1, stretch ? 1 : 0, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0);
    }
}
