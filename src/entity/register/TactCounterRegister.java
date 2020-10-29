package entity.register;

public class TactCounterRegister {

    private int tactAmount = 0;

    public int getTactAmount() {
        return tactAmount;
    }

    public void setTactAmount(int tactAmount){
        this.tactAmount = tactAmount;
    }

    @Override
    public String toString() {
        return "Tact:\t"+tactAmount;
    }
}
