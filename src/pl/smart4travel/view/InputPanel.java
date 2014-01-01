package pl.smart4travel.view;

import org.jdesktop.swingx.JXDatePicker;
import pl.smart4travel.model.ReadXMLFile;

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
    private final JXDatePicker date;

    private final JButton okButton;
    private final JSpinner timeSpinner;
    private final JSpinner.DateEditor timeEditor;

    public InputPanel() {
        inputPanelListenerList = new ArrayList<>();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        add(new JLabel("Z:"), createConstraints(1, 1, false));
        from = new JComboBox();
        add(from, createConstraints(1, 2, true));

        add(new JLabel("Do:"), createConstraints(1, 3, false));
        to = new JComboBox();
        add(to, createConstraints(1, 4, true));

        add(new JLabel("Data:"), createConstraints(2, 1, false));
        date = new JXDatePicker();
        date.setDate(new Date());
        add(date, createConstraints(2, 2, true));

        add(new JLabel("Godzina:"), createConstraints(2, 3, false));
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        add(timeSpinner, createConstraints(2, 4, true));

        okButton = new JButton();
        okButton.setText("OK");
        add(okButton, createConstraints(3, 4, true));
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
        Date date = new Date();
        date.setTime(this.date.getDate().getTime());
        String time = timeSpinner.getValue().toString();
        int hour =Integer.parseInt(time.substring(11,13));
        int minute =Integer.parseInt(time.substring(14,16));
        date.setTime(this.date.getDate().getTime() + (hour*60*60 + minute*60)*1000);

        for (InputPanelListener listener :inputPanelListenerList) {
            listener.onSubmit(from.getSelectedItem().toString(), to.getSelectedItem().toString(), date );
        }

        ReadXMLFile rxf = new ReadXMLFile();
        rxf.read();
    }
}
