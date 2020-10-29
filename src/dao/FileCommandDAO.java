package dao;

import command.LeftShiftCommand;
import command.LoadValueCommand;
import command.ProcessorCommand;
import command.RightShiftCommand;
import entity.register.Register;
import java.io.EOFException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FileCommandDAO implements CommandDao {

    private static final Path SOURCE_PATH = Paths.get("commands.txt");
    private final Collection<Register> registers;
    private int line = 0;
    public FileCommandDAO(Collection<Register> registers) {
        this.registers = registers;
    }

    private boolean sourceExists() {
        return Files.exists(SOURCE_PATH);
    }
    
    @Override
    public ProcessorCommand getCommand() throws IOException {
        ProcessorCommand command = null;
        if (sourceExists()) {
            List<String> lines = Files.readAllLines(SOURCE_PATH);
            if(lines.size() == line){
                throw new EOFException();
            }
            command = parse(lines.get(line));
            line++;
        }
        return command;
    }

    private ProcessorCommand parse(String s) {
        String[] parts = s.split(",");
        String command = parts[0];
        if (Objects.equals("load_to_Acc", command)) {
            String whatToLoad = parts[1];
            if (whatToLoad.matches("-?\\d+")) {
                return new LoadValueCommand(Integer.parseInt(whatToLoad), getByName("Acc"));
            } else if (whatToLoad.matches("A_[\\d]+")) {
                return new LoadValueCommand(Integer.parseInt(getByName(whatToLoad).getBinaryValue(), 2), getByName("Acc"));
            }
        }
        Register register = getByName(parts[1]);
        if (Objects.equals("flush_Acc", command)) {
            return new LoadValueCommand(getByName("Acc").getValue(), register);
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
