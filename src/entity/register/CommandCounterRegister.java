package entity.register;

public class CommandCounterRegister {

    private long registeredCommandAmount = 0;

    public long getRegisteredCommandAmount() {
        return registeredCommandAmount;
    }

    public void incrementAmount() {
        registeredCommandAmount++;
    }

    
    @Override
    public String toString() {
        return "Command number:\t" + registeredCommandAmount;
    }
}
