package net.time4j.calendar.hindu;

import androidx.webkit.ProxyConfig;
import java.io.Serializable;
import net.time4j.engine.ChronoCondition;

/* loaded from: classes4.dex */
public final class HinduDay extends HinduPrimitive implements Comparable<HinduDay>, ChronoCondition<HinduCalendar>, Serializable {
    private final boolean leap;
    private final int value;

    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value + (this.leap ? 12 : 0);
    }

    @Override // net.time4j.calendar.hindu.HinduPrimitive
    public boolean isLeap() {
        return this.leap;
    }

    private HinduDay(int i, boolean z) {
        if (i < 1 || i > 32) {
            throw new IllegalArgumentException("Day-of-month value out of range: " + i);
        }
        this.value = i;
        this.leap = z;
    }

    public static HinduDay valueOf(int i) {
        return new HinduDay(i, false);
    }

    public HinduDay withLeap() {
        return this.leap ? this : new HinduDay(this.value, true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HinduDay)) {
            return false;
        }
        HinduDay hinduDay = (HinduDay) obj;
        return this.value == hinduDay.value && this.leap == hinduDay.leap;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.value);
        return this.leap ? ProxyConfig.MATCH_ALL_SCHEMES + strValueOf : strValueOf;
    }

    @Override // java.lang.Comparable
    public int compareTo(HinduDay hinduDay) {
        int i = this.value - hinduDay.value;
        if (i != 0) {
            return i;
        }
        if (this.leap) {
            return !hinduDay.leap ? 1 : 0;
        }
        return hinduDay.leap ? -1 : 0;
    }

    @Override // net.time4j.engine.ChronoCondition
    public boolean test(HinduCalendar hinduCalendar) {
        return equals(hinduCalendar.getDayOfMonth());
    }
}
