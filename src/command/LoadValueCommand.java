package command;

import entity.register.Register;

public class LoadValueCommand implements ProcessorCommand{

    private Integer value;
    private Register register;

    public LoadValueCommand(Integer value, Register register) {
        this.value = value;
        this.register = register;
    }

    @Override
    public void execute() {
        register.setBinaryValue(Integer.toBinaryString(value));
    }

    @Override
    public String toString() {
        return "Load value: " + register.getName() + ", value = " + value;
    }

    @Override
    public Register getResultHolder() {
        return register;
    }
}
