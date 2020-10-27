package entity;

import command.ProcessorCommand;
import entity.register.*;

import java.util.*;

public class Processor {

    private CommandRegister commandRegister;
    private CommandCounterRegister commandCounterRegister;
    private TactCounterRegister tactCounterRegister;
    private StateRegister stateRegister;
    private List<Register> registers = new ArrayList<>();
    private Queue<ProcessorCommand> commands = new ArrayDeque<>();

    private final int bitsInRegister;

    public Processor(int bitsInRegister) {
        this.bitsInRegister = bitsInRegister;
        createRegisters();
    }

    private void createRegisters() {
        this.commandRegister = new CommandRegister();
        this.commandCounterRegister = new CommandCounterRegister();
        this.tactCounterRegister = new TactCounterRegister();
        this.stateRegister = new StateRegister();
        registers.add(new Register(bitsInRegister, "A_1"));
        registers.add(new Register(bitsInRegister, "A_2"));
        registers.add(new Register(bitsInRegister, "A_3"));
        registers.add(new Register(bitsInRegister, "A_4"));

        this.stateRegister.setState(registers.get(registers.size() - 1).getSignBit());
    }

    public void offerCommands(Collection<ProcessorCommand> commands) {
        this.commands.addAll(commands);
    }

    public void executeNext() {
        if (!commands.isEmpty()) {
            new Scanner(System.in).next();
            commandRegister.setCurrentCommand(commands.peek()); // first tact
            commandCounterRegister.incrementAmount();
            tactCounterRegister.incrementTactAmount();
            System.out.println("Registering " + this.toString());

            new Scanner(System.in).next();
            commands.peek().execute();                          //second tact
            tactCounterRegister.incrementTactAmount();
            stateRegister.setState(commands.peek().getResultHolder().getSignBit());
            System.out.println("Executing " + this.toString());

            commands.remove();
        }
    }

    public Collection<Register> getRegisters() {
        return registers;
    }

    public boolean allCommandsAreDone() {
        return commands.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(300);
        sb.append(commandRegister.toString()).append("\n");
        registers.forEach((r) -> sb.append(r.toString()).append("\n"));
        sb.append(stateRegister.toString()).append("\n");
        sb.append(commandCounterRegister.toString()).append("\n");
        sb.append(tactCounterRegister.toString()).append("\n");
        return sb.toString();
    }
}
