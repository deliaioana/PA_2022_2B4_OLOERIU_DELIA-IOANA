package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        String fileName = new String("savedGame.txt");
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        frame.canvas.getInfoFromFile(file, fileName, scanner);
        frame.canvas.getGraph().getFromFile(file, fileName, scanner);
        frame.repaint();
    }

    private void printUsefulInfo(String message){
        System.out.println(message);
        frame.canvas.getGraph().printGraphInConsole();
        System.out.println("\nLast node: ");
        System.out.println(frame.canvas.getGraph().getLastNode());
    }

    private void saveGame(ActionEvent actionEvent) throws IOException {
        String fileName = new String("savedGame.txt");
        FileWriter fileWriter = new FileWriter(fileName);

        frame.canvas.saveCanvasInfoToFile(fileName, fileWriter);
        frame.canvas.getGraph().saveToFile(fileName, fileWriter);
    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }
}
