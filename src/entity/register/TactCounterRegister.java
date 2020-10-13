package entity.register;

public class TactCounterRegister {

    private long tactAmount = 0;

    public long getTactAmount() {
        return tactAmount;
    }

    public void incrementTactAmount(){
        tactAmount++;
    }

    @Override
    public String toString() {
        return "Tact:\t"+tactAmount;
    }
}
