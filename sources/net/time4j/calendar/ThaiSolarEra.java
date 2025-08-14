package net.time4j.calendar;

import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import java.util.Locale;
import net.time4j.PlainDate;
import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.EpochDays;
import net.time4j.format.CalendarText;
import net.time4j.format.TextWidth;

/* loaded from: classes4.dex */
public enum ThaiSolarEra implements CalendarEra {
    RATTANAKOSIN,
    BUDDHIST;

    public String getDisplayName(Locale locale) {
        return getDisplayName(locale, TextWidth.WIDE);
    }

    public String getDisplayName(Locale locale, TextWidth textWidth) {
        return CalendarText.getInstance("buddhist", locale).getEras(textWidth).print(this);
    }

    public int getYear(CalendarDate calendarDate) {
        int i;
        PlainDate plainDateOf = PlainDate.of(calendarDate.getDaysSinceEpochUTC(), EpochDays.UTC);
        if (this == RATTANAKOSIN) {
            int year = plainDateOf.getYear();
            i = year - 1781;
            if (plainDateOf.getMonth() < 4) {
                i = year - 1782;
            }
        } else {
            int year2 = plainDateOf.getYear();
            i = year2 + SanyoMakernoteDirectory.TAG_SCENE_SELECT;
            if (i < 2484 && plainDateOf.getMonth() < 4) {
                i = year2 + 542;
            }
        }
        if (i >= 1) {
            return i;
        }
        throw new IllegalArgumentException("Out of range: " + calendarDate);
    }

    int toIsoYear(int i, int i2) {
        if (i < 1) {
            throw new IllegalArgumentException("Out of bounds: " + i);
        }
        if (this == RATTANAKOSIN) {
            int iSafeAdd = MathUtils.safeAdd(i, 1781);
            return i2 < 4 ? MathUtils.safeAdd(iSafeAdd, 1) : iSafeAdd;
        }
        int iSafeSubtract = MathUtils.safeSubtract(i, SanyoMakernoteDirectory.TAG_SCENE_SELECT);
        if (i2 >= 4) {
            return iSafeSubtract;
        }
        if (iSafeSubtract != 1940) {
            return iSafeSubtract < 1940 ? MathUtils.safeAdd(iSafeSubtract, 1) : iSafeSubtract;
        }
        throw new IllegalArgumentException("Buddhist year 2483 does not know month: " + i2);
    }
}
