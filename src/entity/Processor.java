package entity;

import command.ProcessorCommand;
import entity.register.*;

import java.util.*;

public class Processor {

    private CommandRegister commandRegister;
    private CommandCounterRegister commandCounterRegister;
    private TactCounterRegister tactCounterRegister;
    private StateRegister stateRegister;
    private final List<Register> registers = new ArrayList<>();
    private final Queue<ProcessorCommand> commands = new ArrayDeque<>();

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
        registers.add(new Register(bitsInRegister, "Acc"));

        this.stateRegister.setState(registers.get(registers.size() - 1).getSignBit());
    }

    public void offerCommand(ProcessorCommand command) {
        this.commands.offer(command);
    }

    public String executeNext() {
        if (!allCommandsAreDone()) {
            StringBuilder answerBuilder = new StringBuilder();
            if(commandRegister.getCurrentCommand() == null) {
                tactCounterRegister.setTactAmount(1);
                commandRegister.setCurrentCommand(commands.poll()); // first tact
                commandCounterRegister.incrementAmount();
                return answerBuilder.append("Registering ").append(toString()).toString();
            }else {
                commandRegister.getCurrentCommand().execute();    
                tactCounterRegister.setTactAmount(2);               //second tact
                stateRegister.setState(commandRegister.getCurrentCommand().getResultHolder().getSignBit());
                answerBuilder.append("Executed ").append(this.toString());
                commandRegister.setCurrentCommand(null);
                return answerBuilder.toString();
            }
        }
        return "";
    }

    public Collection<Register> getRegisters() {
        return registers;
    }

    public boolean allCommandsAreDone() {
        return commands.isEmpty() && commandRegister.getCurrentCommand() == null;
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
