package pl.smart4travel.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Natalia on 30/12/13.
 */
public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
        getRootPane().setLayout(new BoxLayout(getRootPane(),BoxLayout.Y_AXIS));
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
            getRootPane().add(jLabelImage);
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
