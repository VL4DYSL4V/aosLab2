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
        if(Objects.equals(register.getSignBit(), "0")) {
            sb.insert(0, "0");
        }else if(Objects.equals(register.getSignBit(), "1")){
            sb.insert(0, "1");
        }
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
