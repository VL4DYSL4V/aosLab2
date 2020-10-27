package command;

import entity.register.Register;

import java.util.Objects;

public class LeftShiftCommand implements ProcessorCommand {
    private Register register;

    public LeftShiftCommand(Register register){
        this.register = register;
    }
    @Override
    public void execute() {
        String binaryValue = register.getBinaryValue();
        StringBuilder sb = new StringBuilder(binaryValue);
        sb.deleteCharAt(1);
        sb.append("0");
        register.setBinaryValue(sb.toString());
    }

    @Override
    public String toString() {
        return "Left shift: " + register.getName();
    }

    @Override
    public Register getResultHolder() {
        return register;
    }
}
