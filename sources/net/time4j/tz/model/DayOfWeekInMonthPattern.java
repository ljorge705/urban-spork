package net.time4j.tz.model;

import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.CalendarUnit;
import net.time4j.Month;
import net.time4j.PlainDate;
import net.time4j.Weekday;
import net.time4j.base.GregorianMath;

/* loaded from: classes4.dex */
final class DayOfWeekInMonthPattern extends GregorianTimezoneRule {
    private static final long serialVersionUID = -7354650946442523175L;
    private final transient boolean after;
    private final transient byte dayOfMonth;
    private final transient byte dayOfWeek;

    int getDayOfMonth() {
        return this.dayOfMonth;
    }

    byte getDayOfWeek() {
        return this.dayOfWeek;
    }

    @Override // net.time4j.tz.model.DaylightSavingRule
    int getType() {
        return PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE;
    }

    boolean isAfter() {
        return this.after;
    }

    DayOfWeekInMonthPattern(Month month, int i, Weekday weekday, int i2, OffsetIndicator offsetIndicator, int i3, boolean z) {
        super(month, i2, offsetIndicator, i3);
        GregorianMath.checkDate(2000, month.getValue(), i);
        this.dayOfMonth = (byte) i;
        this.dayOfWeek = (byte) weekday.getValue();
        this.after = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // net.time4j.tz.model.GregorianTimezoneRule
    protected PlainDate getDate0(int i) {
        int i2;
        byte monthValue = getMonthValue();
        int dayOfWeek = GregorianMath.getDayOfWeek(i, monthValue, this.dayOfMonth);
        PlainDate plainDateOf = PlainDate.of(i, monthValue, this.dayOfMonth);
        byte b = this.dayOfWeek;
        if (dayOfWeek == b) {
            return plainDateOf;
        }
        int i3 = dayOfWeek - b;
        if (this.after) {
            i3 = -i3;
            i2 = 1;
        } else {
            i2 = -1;
        }
        if (i3 < 0) {
            i3 += 7;
        }
        return (PlainDate) plainDateOf.plus(i3 * i2, CalendarUnit.DAYS);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DayOfWeekInMonthPattern)) {
            return false;
        }
        DayOfWeekInMonthPattern dayOfWeekInMonthPattern = (DayOfWeekInMonthPattern) obj;
        return this.dayOfMonth == dayOfWeekInMonthPattern.dayOfMonth && this.dayOfWeek == dayOfWeekInMonthPattern.dayOfWeek && this.after == dayOfWeekInMonthPattern.after && super.isEqual(dayOfWeekInMonthPattern);
    }

    public int hashCode() {
        return this.dayOfMonth + ((this.dayOfWeek + (getMonthValue() * 37)) * 17) + (this.after ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("DayOfWeekInMonthPattern:[month=");
        sb.append((int) getMonthValue());
        sb.append(",dayOfMonth=");
        sb.append((int) this.dayOfMonth);
        sb.append(",dayOfWeek=");
        sb.append(Weekday.valueOf(this.dayOfWeek));
        sb.append(",day-overflow=");
        sb.append(getDayOverflow());
        sb.append(",time-of-day=");
        sb.append(getTimeOfDay());
        sb.append(",offset-indicator=");
        sb.append(getIndicator());
        sb.append(",dst-offset=");
        sb.append(getSavings());
        sb.append(",after=");
        sb.append(this.after);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private Object writeReplace() {
        return new SPX(this, getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }
}
