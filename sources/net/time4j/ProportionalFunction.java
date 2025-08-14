package net.time4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoFunction;

/* loaded from: classes4.dex */
final class ProportionalFunction implements ChronoFunction<ChronoEntity<?>, BigDecimal> {
    private final ChronoElement<? extends Number> element;
    private final boolean extendedRange;

    ProportionalFunction(ChronoElement<? extends Number> chronoElement, boolean z) {
        this.element = chronoElement;
        this.extendedRange = z;
    }

    @Override // net.time4j.engine.ChronoFunction
    public BigDecimal apply(ChronoEntity<?> chronoEntity) {
        long jLongValue = ((Number) chronoEntity.get(this.element)).longValue();
        long jLongValue2 = ((Number) chronoEntity.getMinimum(this.element)).longValue();
        long jLongValue3 = ((Number) chronoEntity.getMaximum(this.element)).longValue();
        if (jLongValue > jLongValue3) {
            jLongValue = jLongValue3;
        }
        if (jLongValue == jLongValue2) {
            return BigDecimal.ZERO;
        }
        if (this.extendedRange && (chronoEntity instanceof PlainTime) && !((PlainTime) PlainTime.class.cast(chronoEntity)).hasReducedRange(this.element)) {
            if (jLongValue == jLongValue3) {
                return BigDecimal.ONE;
            }
            jLongValue3--;
        }
        return new BigDecimal(jLongValue - jLongValue2).setScale(15).divide(new BigDecimal((jLongValue3 - jLongValue2) + 1), RoundingMode.HALF_UP).stripTrailingZeros();
    }
}
