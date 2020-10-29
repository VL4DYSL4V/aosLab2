package application;

import dao.FileCommandDAO;
import entity.Processor;
import ui.LabUI;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;

public class Application {

    public void start() throws IOException {
        Processor processor = new Processor(24);
        final LabUI labUI = new LabUI(processor, 
                new FileCommandDAO(
                    Collections.unmodifiableCollection(processor.getRegisters()))
                );
        SwingUtilities.invokeLater(() -> {
            labUI.setVisible(true);
        });
    }
}
