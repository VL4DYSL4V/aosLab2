package entity.register;

import java.util.Objects;

public class StateRegister {
    private String state = "0"; //0 - "+", 1 - "-"

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String sign = Objects.equals(state, "0") ? "+" : "-";
        return "prev result sign:\t" + sign;
    }
}
