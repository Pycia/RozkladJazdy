package pl.smart4travel.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainWindow extends JFrame {
    private GridBagConstraints createConstraints(int y, boolean stretch, int padding) {
        return new GridBagConstraints(1, y, 1, 1, 1, stretch ? 1 : 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(padding, padding, padding, padding), 0, 0);
    }
    public MainWindow() throws HeadlessException {
        setLayout(new GridBagLayout());
        try
        {
            // Read from a file
            File FileToRead;
            FileToRead = new File("resources\\images\\logo.png");
            //Recognize file as image
            Image Picture = ImageIO.read(FileToRead);
            //Show the image inside the label
            JLabel jLabelImage = new JLabel();
            jLabelImage.setIcon(new ImageIcon(Picture));
            add(jLabelImage, createConstraints(1, false, 0));

            InputPanel inputPanel = new InputPanel();
            add(inputPanel, createConstraints(2, false, 10));

            ResultsPanel resultsPanel = new ResultsPanel();
            add(resultsPanel, createConstraints(3, true, 0));
            inputPanel.addListener(resultsPanel);
        }
        catch (Exception e)
        {
            //Display a message if something goes wrong
            JOptionPane.showMessageDialog( null, e.toString() );
        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MainWindow frame = new MainWindow();
        frame.setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
