package dao;

import command.ProcessorCommand;

import java.io.IOException;
import java.util.Collection;

public interface CommandDao {

    Collection<ProcessorCommand> getCommands() throws IOException;

}
