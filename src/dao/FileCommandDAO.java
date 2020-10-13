package dao;

import command.LeftShiftCommand;
import command.LoadValueCommand;
import command.ProcessorCommand;
import command.RightShiftCommand;
import entity.register.Register;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class FileCommandDAO implements CommandDao {

    private static final Path SOURCE_PATH = Paths.get("commands.txt");
    private final Collection<Register> registers;

    public FileCommandDAO(Collection<Register> registers) {
        this.registers = registers;
    }

    private boolean sourceExists() {
        return Files.exists(SOURCE_PATH);
    }

    @Override
    public Collection<ProcessorCommand> getCommands() throws IOException {
        Collection<ProcessorCommand> processorCommands = new ArrayList<>();
        if (sourceExists()) {
            Collection<String> lines = Files.readAllLines(SOURCE_PATH);
            lines.forEach((line) -> processorCommands.add(parse(line)));
        }
        return processorCommands;
    }

    private ProcessorCommand parse(String s) {
        String[] parts = s.split(",");
        String command = parts[0];
        Register register = getByName(parts[1]);
        if (Objects.equals("load", command)) {
            String whatToLoad = parts[2];
            if (whatToLoad.matches("-?\\d+")) {
                return new LoadValueCommand(Integer.parseInt(whatToLoad), register);
            } else if (whatToLoad.matches("A_[\\d]+")) {
                return new LoadValueCommand(Integer.parseInt(getByName(whatToLoad).getBinaryValue(), 2), register);
            }
        } else if (Objects.equals("left_shift", command)) {
            return new LeftShiftCommand(register);
        } else if (Objects.equals("right_shift", command)) {
            return new RightShiftCommand(register);
        }
        throw new IllegalStateException("File is invalid");
    }

    private Register getByName(String name) {
        Register register = null;
        for (Register r : registers) {
            if (Objects.equals(r.getName(), name)) {
                register = r;
            }
        }
        if (register == null) {
            throw new IllegalStateException("File is invalid");
        }
        return register;
    }
}
