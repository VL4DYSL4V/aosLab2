package application;

import command.ProcessorCommand;
import dao.CommandDao;
import dao.FileCommandDAO;
import entity.Processor;

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
        while (!processor.allCommandsAreDone()){
            processor.executeNext();
        }
    }
}
