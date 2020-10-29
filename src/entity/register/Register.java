package entity.register;

import java.util.Random;

public final class Register {

    private final int bitAmount;
    private String binaryValue;
    private String name;

    public Register(int bitAmount, String name) {
        this.bitAmount = bitAmount;
        this.name = name;
        String binaries = Integer.toBinaryString(new Random().nextInt((int) (Math.pow(2, bitAmount - 1) - 1)));
        setBinaryValue(binaries);
    }

    public String getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(String binaryValue) {
        if (!binaryValue.matches("[01]+")) {
            throw new IllegalArgumentException();
        }
        int length = binaryValue.length();
        if (length > bitAmount) {
            binaryValue = binaryValue.substring(length - bitAmount);
        } else if (length < bitAmount) {
            StringBuilder sb = new StringBuilder(binaryValue);
            while (sb.length() < bitAmount) {
                sb.insert(0, "0");
            }
            binaryValue = sb.toString();
        }
        this.binaryValue = binaryValue;
    }

    public int getBitAmount() {
        return bitAmount;
    }

    public String getSignBit() {
        return binaryValue.substring(0, 1);
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        if (binaryValue.startsWith("0")) {
            return Integer.parseInt(binaryValue, 2);
        } else {
            String inverted = binaryValue.replaceAll("0", "2");
            inverted = inverted.replaceAll("1", "0");
            inverted = inverted.replaceAll("2", "1");
            Integer out = Integer.parseInt(inverted, 2) + 1;
            return (-1) * out;
        }
    }

    @Override
    public String toString() {
        return name + "\t" + binaryValue;
    }
}
