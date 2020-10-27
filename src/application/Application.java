package application;

import command.ProcessorCommand;
import dao.CommandDao;
import dao.FileCommandDAO;
import entity.Processor;
import requestHandler.NextTactRequestHandler;
import requestHandler.ProcessorRequestHandler;
import ui.LabUI;

import javax.swing.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class Application {

    public void start() throws IOException {
        Processor processor = new Processor(24);
        CommandDao commandDao = new FileCommandDAO(
                Collections.unmodifiableCollection(processor.getRegisters()));
        Collection<ProcessorCommand> processorCommands =
                commandDao.getCommands();
        processor.offerCommands(processorCommands);
        NextTactRequestHandler nextTactRequestHandler = new ProcessorRequestHandler(processor);
        final LabUI labUI = new LabUI(nextTactRequestHandler);
        SwingUtilities.invokeLater(() -> {
            labUI.setVisible(true);
        });
    }
}
