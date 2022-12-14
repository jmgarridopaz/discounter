package io.github.jmgarridopaz.discounter.application;


public class BreakPointWithRate {

    private final Amount breakPoint;
    private final Rate rate;

    public BreakPointWithRate(Amount breakPoint, Rate rate) {
        if (breakPoint == null) {
            throw new NullPointerException("BreakPoint cannot be null");
        }
        if (rate == null) {
            throw new NullPointerException("Rate cannot be null");
        }
        this.breakPoint = breakPoint;
        this.rate = rate;
    }

    public Amount getBreakPoint() {
        return this.breakPoint;
    }

    public Rate getRate() {
        return this.rate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof BreakPointWithRate)) {
            return false;
        }
        BreakPointWithRate otherBreakPointWithRate = (BreakPointWithRate) other;
        return this.breakPoint.equals(otherBreakPointWithRate.breakPoint);
    }

    @Override
    public int hashCode() {
        return this.breakPoint.hashCode();
    }

}