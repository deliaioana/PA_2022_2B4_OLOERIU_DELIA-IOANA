import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        saveButton.addActionListener(this::saveGame);
        loadButton.addActionListener(this::loadGame);

        add(exitButton);
        add(saveButton);
        add(loadButton);
    }

    private void loadGame(ActionEvent actionEvent) {
        //de implementat load
    }

    private void saveGame(ActionEvent actionEvent) {
        //de implementat save
    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }
}
