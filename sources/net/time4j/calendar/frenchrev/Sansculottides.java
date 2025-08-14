package net.time4j.calendar.frenchrev;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.util.Locale;
import net.time4j.format.CalendarText;
import net.time4j.format.OutputContext;

/* loaded from: classes4.dex */
public enum Sansculottides {
    COMPLEMENTARY_DAY_1,
    COMPLEMENTARY_DAY_2,
    COMPLEMENTARY_DAY_3,
    COMPLEMENTARY_DAY_4,
    COMPLEMENTARY_DAY_5,
    LEAP_DAY;

    public static Sansculottides valueOf(int i) {
        if (i < 1 || i > 6) {
            throw new IllegalArgumentException("Out of range: " + i);
        }
        return values()[i - 1];
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(Locale locale, OutputContext outputContext) {
        CalendarText calendarText = CalendarText.getInstance("frenchrev", locale);
        String str = calendarText.getTextForms().get("S(" + (outputContext == OutputContext.STANDALONE ? ExifInterface.LONGITUDE_WEST : Constants.INAPP_WINDOW) + ")_" + getValue());
        if (str == null && outputContext == OutputContext.STANDALONE) {
            return calendarText.getTextForms().get("S(w)_" + getValue());
        }
        return str;
    }
}
