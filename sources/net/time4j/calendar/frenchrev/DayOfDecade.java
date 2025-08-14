package net.time4j.calendar.frenchrev;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.util.Locale;
import net.time4j.format.CalendarText;
import net.time4j.format.OutputContext;
import net.time4j.format.TextWidth;

/* loaded from: classes4.dex */
public enum DayOfDecade {
    PRIMIDI,
    DUODI,
    TRIDI,
    QUARTIDI,
    QUINTIDI,
    SEXTIDI,
    SEPTIDI,
    OCTIDI,
    NONIDI,
    DECADI;

    public static DayOfDecade valueOf(int i) {
        if (i < 1 || i > 10) {
            throw new IllegalArgumentException("Out of range: " + i);
        }
        return values()[i - 1];
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getDisplayName(Locale locale) {
        return getDisplayName(locale, TextWidth.WIDE, OutputContext.FORMAT);
    }

    public String getDisplayName(Locale locale, TextWidth textWidth, OutputContext outputContext) {
        return CalendarText.getInstance("frenchrev", locale).getTextForms().get("D(" + (textWidth == TextWidth.NARROW ? "N" : outputContext == OutputContext.FORMAT ? Constants.INAPP_WINDOW : ExifInterface.LONGITUDE_WEST) + ")_" + getValue());
    }
}
