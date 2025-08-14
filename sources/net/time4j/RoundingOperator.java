package net.time4j;

import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoOperator;

/* loaded from: classes4.dex */
final class RoundingOperator<T extends ChronoEntity<T>> implements ChronoOperator<T> {
    private final ProportionalElement<?, T> element;
    private final boolean longBased;
    private final double stepwidth;
    private final Boolean up;

    RoundingOperator(ProportionalElement<?, T> proportionalElement, Boolean bool, int i) {
        this.element = proportionalElement;
        this.up = bool;
        this.stepwidth = i;
        this.longBased = proportionalElement.getType().equals(Long.class);
    }

    @Override // net.time4j.engine.ChronoOperator
    public T apply(T t) {
        double dFloor;
        double d;
        double d2;
        Number numberValueOf;
        double dDoubleValue = ((Number) t.get(this.element)).doubleValue();
        Boolean bool = this.up;
        if (bool == null) {
            double dCeil = Math.ceil(dDoubleValue / this.stepwidth);
            double d3 = this.stepwidth;
            d2 = dCeil * d3;
            double dFloor2 = Math.floor(dDoubleValue / d3) * this.stepwidth;
            if (dDoubleValue - dFloor2 < d2 - dDoubleValue) {
                d2 = dFloor2;
            }
        } else {
            if (bool.booleanValue()) {
                dFloor = Math.ceil(dDoubleValue / this.stepwidth);
                d = this.stepwidth;
            } else {
                dFloor = Math.floor(dDoubleValue / this.stepwidth);
                d = this.stepwidth;
            }
            d2 = d * dFloor;
        }
        if (this.longBased) {
            numberValueOf = Long.valueOf((long) d2);
        } else {
            numberValueOf = Integer.valueOf((int) d2);
        }
        return (T) t.with(lenient(this.element, numberValueOf));
    }

    private static <V extends Number, T extends ChronoEntity<T>> ChronoOperator<T> lenient(ProportionalElement<V, T> proportionalElement, Number number) {
        return proportionalElement.setLenient(proportionalElement.getType().cast(number));
    }
}
