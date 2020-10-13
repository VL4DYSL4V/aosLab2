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
        if(Objects.equals(register.getSignBit(), "0")) {
            sb.insert(sb.length() - 1, "0");
        }else if(Objects.equals(register.getSignBit(), "1")){
            sb.insert(sb.length() - 1, "1");
        }
        sb.deleteCharAt(0);
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
