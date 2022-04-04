import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner rowSpinner;
    JSpinner colSpinner;
    JButton createButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Grid size:");
        rowSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        colSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createButton = new JButton("Create");
        createButton.addActionListener(this::updateGrid);

        add(label);
        add(rowSpinner);
        add(colSpinner);
        add(createButton);
    }

    private void updateGrid(ActionEvent actionEvent) {
        frame.canvas.init(getRows(), getCols());
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public int getRows() {
        return ((int) rowSpinner.getValue());
    }

    public int getCols() {
        return ((int) colSpinner.getValue());
    }
}
