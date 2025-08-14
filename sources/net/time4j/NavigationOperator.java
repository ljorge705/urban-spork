package net.time4j;

import java.lang.Enum;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoOperator;

/* loaded from: classes4.dex */
final class NavigationOperator<V extends Enum<V>> extends ElementOperator<PlainDate> {
    private final int len;
    private final ChronoOperator<PlainTimestamp> navTS;
    private final V value;

    @Override // net.time4j.ElementOperator
    ChronoOperator<PlainTimestamp> onTimestamp() {
        return this.navTS;
    }

    NavigationOperator(ChronoElement<V> chronoElement, int i, V v) {
        super(chronoElement, i);
        if (v == null) {
            throw new NullPointerException("Missing value.");
        }
        this.value = v;
        this.len = chronoElement.getType().getEnumConstants().length;
        this.navTS = new ChronoOperator<PlainTimestamp>() { // from class: net.time4j.NavigationOperator.1
            @Override // net.time4j.engine.ChronoOperator
            public PlainTimestamp apply(PlainTimestamp plainTimestamp) {
                return (PlainTimestamp) NavigationOperator.this.doApply(plainTimestamp);
            }
        };
    }

    @Override // net.time4j.engine.ChronoOperator
    public PlainDate apply(PlainDate plainDate) {
        return (PlainDate) doApply(plainDate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends ChronoEntity<T>> T doApply(T t) {
        String str;
        if (t.contains(PlainDate.CALENDAR_DATE)) {
            PlainDate plainDate = (PlainDate) t.get(PlainDate.CALENDAR_DATE);
            int iOrdinal = ((Enum) Enum.class.cast(plainDate.get(getElement()))).ordinal();
            return delta(iOrdinal) == iOrdinal ? t : (T) t.with(PlainDate.CALENDAR_DATE, plainDate.plus(r2 - iOrdinal, plainDate.getChronology().getBaseUnit(getElement())));
        }
        switch (getType()) {
            case 9:
                str = "setToNext";
                break;
            case 10:
                str = "setToPrevious";
                break;
            case 11:
                str = "setToNextOrSame";
                break;
            case 12:
                str = "setToPreviousOrSame";
                break;
            default:
                throw new AssertionError("Unknown: " + getType());
        }
        throw new ChronoException(str + "()-operation not supported on: " + getElement().name());
    }

    private int delta(int i) {
        int i2;
        int i3;
        int iOrdinal = this.value.ordinal();
        switch (getType()) {
            case 9:
                if (iOrdinal > i) {
                    return iOrdinal;
                }
                i2 = this.len;
                return iOrdinal + i2;
            case 10:
                if (iOrdinal < i) {
                    return iOrdinal;
                }
                i3 = this.len;
                return iOrdinal - i3;
            case 11:
                if (iOrdinal >= i) {
                    return iOrdinal;
                }
                i2 = this.len;
                return iOrdinal + i2;
            case 12:
                if (iOrdinal <= i) {
                    return iOrdinal;
                }
                i3 = this.len;
                return iOrdinal - i3;
            default:
                throw new AssertionError("Unknown: " + getType());
        }
    }
}
