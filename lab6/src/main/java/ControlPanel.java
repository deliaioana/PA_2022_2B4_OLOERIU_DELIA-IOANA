import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitButton = new JButton("Exit");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JButton pngButton = new JButton("Save PNG");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        exitButton.addActionListener(this::exitGame);

        saveButton.addActionListener(actionEvent -> {
            try {
                saveGame(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadButton.addActionListener(actionEvent1 -> {
            try {
                loadGame(actionEvent1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        pngButton.addActionListener(actionEvent -> {
            try {
                saveAsPng(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        add(exitButton);
        add(saveButton);
        add(loadButton);
        add(pngButton);
    }

    private void saveAsPng(ActionEvent actionEvent) throws IOException {
        frame.canvas.saveAsPng();
    }

    private void loadGame(ActionEvent actionEvent) throws FileNotFoundException {
        frame.canvas.getGraph().getFromFile("savedGame.txt");
        frame.repaint();
    }

    private void saveGame(ActionEvent actionEvent) throws IOException {

        frame.canvas.getGraph().saveToFile("savedGame.txt");
    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }
}
