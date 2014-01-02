package pl.smart4travel.view;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pl.smart4travel.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputPanel extends JPanel {
    private List<InputPanelListener> inputPanelListenerList;
    private final JComboBox from;
    private final JComboBox to;

    private final JButton okButton;
    private final JSpinner timeSpinner;
    private final JSpinner.DateEditor timeEditor;
    private final Model rxf;

    public InputPanel() {
        rxf = new Model();
        rxf.read();

        inputPanelListenerList = new ArrayList<>();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        add(new JLabel("Z:"), createConstraints(1, 1, false));
        from = new JComboBox(rxf.getStopsList().toArray());
        from.setMaximumRowCount(12);
        AutoCompleteDecorator.decorate(from);
        add(from, createConstraints(1, 2, true));

        add(new JLabel("Do:"), createConstraints(1, 3, false));
        to = new JComboBox(rxf.getStopsList().toArray());
        to.setMaximumRowCount(12);
        AutoCompleteDecorator.decorate(to);
        add(to, createConstraints(1, 4, true));

        add(new JLabel("Godzina:"), createConstraints(2, 1, false));
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        add(timeSpinner, createConstraints(2, 2, true));

        okButton = new JButton();
        okButton.setText("OK");
        add(okButton, createConstraints(2, 4, true));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireOnSubmit();
            }
        });
    }

    private GridBagConstraints createConstraints(int row, int column, boolean stretch) {
        return new GridBagConstraints(column, row, 1, 1, stretch ? 1 : 0, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);
    }

    public void addListener(InputPanelListener inputPanelListener){
        inputPanelListenerList.add(inputPanelListener);
    }

    public void fireOnSubmit() {
        String time = timeSpinner.getValue().toString();
        int hour =Integer.parseInt(time.substring(11,13));
        int minute =Integer.parseInt(time.substring(14,16));

        for (InputPanelListener listener :inputPanelListenerList) {
            listener.onSubmit(from.getSelectedItem().toString(), to.getSelectedItem().toString(), (hour*60*60 + minute*60)*1000, rxf );
        }
    }
}
