package org.oobootcamp.warmup.length;

public class Length {
    private int value;
    private Unit unit;
    public Length(int value, Unit unit) {
        this.value  = value;
        this.unit  = unit;
    }

    public int getValue() {
        return this.value;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public boolean isEqual(Length target) throws Exception {
        if (!this.getUnit().equals(target.getUnit())) {
            throw new Exception("Unit is not equal");
        }

        return this.getValue() == target.getValue();
    }

    public boolean isLarger(Length target) throws Exception {
        if (!this.getUnit().equals(target.getUnit())) {
            throw new Exception("Unit is not equal");
        }

        return this.getValue() > target.getValue();
    }
}
