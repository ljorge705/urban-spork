package net.time4j.format;

import java.util.Locale;

/* loaded from: classes4.dex */
public interface WeekdataProvider {
    int getEndOfWeekend(Locale locale);

    int getFirstDayOfWeek(Locale locale);

    int getMinimalDaysInFirstWeek(Locale locale);

    int getStartOfWeekend(Locale locale);
}
