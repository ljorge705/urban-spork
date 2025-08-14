package net.time4j;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import net.time4j.base.WallTime;
import net.time4j.engine.ChronoCondition;
import net.time4j.format.CalendarText;
import net.time4j.format.OutputContext;
import net.time4j.format.TextWidth;

/* loaded from: classes4.dex */
public enum Meridiem implements ChronoCondition<WallTime> {
    AM,
    PM;

    public static Meridiem ofHour(int i) {
        if (i < 0 || i > 24) {
            throw new IllegalArgumentException("Hour of day out of range: " + i);
        }
        return (i < 12 || i == 24) ? AM : PM;
    }

    public String getDisplayName(Locale locale) {
        return getDisplayName(locale, TextWidth.WIDE, OutputContext.FORMAT);
    }

    public String getDisplayName(Locale locale, TextWidth textWidth, OutputContext outputContext) {
        return CalendarText.getIsoInstance(locale).getMeridiems(textWidth, outputContext).print(this);
    }

    public static Meridiem parse(CharSequence charSequence, Locale locale, TextWidth textWidth, OutputContext outputContext) throws ParseException {
        char cCharAt;
        if (charSequence.length() == 2 && ((cCharAt = charSequence.charAt(1)) == 'M' || cCharAt == 'm')) {
            char cCharAt2 = charSequence.charAt(0);
            if (cCharAt2 == 'A' || cCharAt2 == 'a') {
                return AM;
            }
            if (cCharAt2 == 'P' || cCharAt2 == 'p') {
                return PM;
            }
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Meridiem meridiem = (Meridiem) CalendarText.getIsoInstance(locale).getMeridiems(textWidth, outputContext).parse(charSequence, parsePosition, Meridiem.class);
        if (meridiem != null) {
            return meridiem;
        }
        throw new ParseException("Cannot parse: " + ((Object) charSequence), parsePosition.getErrorIndex());
    }

    @Override // net.time4j.engine.ChronoCondition
    public boolean test(WallTime wallTime) {
        int hour = wallTime.getHour();
        if (this == AM) {
            if (hour < 12 || hour == 24) {
                return true;
            }
        } else if (hour >= 12 && hour < 24) {
            return true;
        }
        return false;
    }
}
