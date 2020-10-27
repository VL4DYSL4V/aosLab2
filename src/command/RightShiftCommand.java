package command;

import entity.register.Register;

import java.util.Objects;

public class RightShiftCommand implements ProcessorCommand {

    private Register register;

    public RightShiftCommand(Register register){
        this.register = register;
    }

    @Override
    public void execute() {
        String binaryValue = register.getBinaryValue();
        StringBuilder sb = new StringBuilder(binaryValue);
        sb.insert(1, "0");
        sb.deleteCharAt(sb.length() - 1);
        register.setBinaryValue(sb.toString());
    }

    @Override
    public String toString() {
        return "Right shift: " + register.getName();
    }

    @Override
    public Register getResultHolder() {
        return register;
    }
}
