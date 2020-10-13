package command;

import entity.register.Register;

public interface ProcessorCommand {

    void execute();

    Register getResultHolder();
}
