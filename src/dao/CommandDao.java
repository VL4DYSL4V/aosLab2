package dao;

import command.ProcessorCommand;

import java.io.IOException;

public interface CommandDao {

    ProcessorCommand getCommand() throws IOException;

}
