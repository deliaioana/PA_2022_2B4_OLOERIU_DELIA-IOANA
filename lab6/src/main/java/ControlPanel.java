import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitButton = new JButton("Exit");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");

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

        loadButton.addActionListener(this::loadGame);

        add(exitButton);
        add(saveButton);
        add(loadButton);
    }

    private void loadGame(ActionEvent actionEvent) {
        //de implementat load
    }

    private void saveGame(ActionEvent actionEvent) throws IOException {
        frame.canvas.saveAsPng();
    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }
}
