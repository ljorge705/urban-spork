package net.time4j.calendar;

import androidx.webkit.ProxyConfig;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Locale;
import java.util.Map;
import net.time4j.engine.AttributeKey;
import net.time4j.engine.AttributeQuery;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.NumberSystem;
import net.time4j.format.internal.DualFormatHelper;

/* loaded from: classes4.dex */
public final class EastAsianMonth implements Comparable<EastAsianMonth>, Serializable {
    private static final EastAsianMonth[] CACHE;
    public static final AttributeKey<Character> LEAP_MONTH_INDICATOR = Attributes.createKey("LEAP_MONTH_INDICATOR", Character.class);
    public static final AttributeKey<Boolean> LEAP_MONTH_IS_TRAILING = Attributes.createKey("LEAP_MONTH_IS_TRAILING", Boolean.class);
    private static final long serialVersionUID = 7544059597266533279L;
    private final int index;
    private final boolean leap;

    public int getNumber() {
        return this.index + 1;
    }

    public int hashCode() {
        return this.index + (this.leap ? 12 : 0);
    }

    public boolean isLeap() {
        return this.leap;
    }

    static {
        EastAsianMonth[] eastAsianMonthArr = new EastAsianMonth[24];
        for (int i = 0; i < 12; i++) {
            eastAsianMonthArr[i] = new EastAsianMonth(i, false);
            eastAsianMonthArr[i + 12] = new EastAsianMonth(i, true);
        }
        CACHE = eastAsianMonthArr;
    }

    private EastAsianMonth(int i, boolean z) {
        this.index = i;
        this.leap = z;
    }

    public static EastAsianMonth valueOf(int i) {
        if (i < 1 || i > 12) {
            throw new IllegalArgumentException("Out of range: " + i);
        }
        return CACHE[i - 1];
    }

    public EastAsianMonth withLeap() {
        return CACHE[this.index + 12];
    }

    public String getOldJapaneseName(Locale locale) {
        return CalendarText.getInstance("japanese", locale).getTextForms().get("t" + getNumber());
    }

    public String getDisplayName(Locale locale, NumberSystem numberSystem) {
        String displayName = getDisplayName(locale, numberSystem, Attributes.empty());
        String language = locale.getLanguage();
        if (language.equals("zh")) {
            return displayName + "月";
        }
        if (language.equals("ko")) {
            return displayName + "월";
        }
        return language.equals("ja") ? displayName + "月" : displayName;
    }

    @Override // java.lang.Comparable
    public int compareTo(EastAsianMonth eastAsianMonth) {
        int i = this.index;
        int i2 = eastAsianMonth.index;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        return this.leap ? !eastAsianMonth.leap ? 1 : 0 : eastAsianMonth.leap ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EastAsianMonth)) {
            return false;
        }
        EastAsianMonth eastAsianMonth = (EastAsianMonth) obj;
        return this.index == eastAsianMonth.index && this.leap == eastAsianMonth.leap;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.index + 1);
        return this.leap ? ProxyConfig.MATCH_ALL_SCHEMES + strValueOf : strValueOf;
    }

    String getDisplayName(Locale locale, NumberSystem numberSystem, AttributeQuery attributeQuery) {
        StringBuilder sb;
        StringBuilder sbAppend;
        Map<String, String> textForms = CalendarText.getInstance("generic", locale).getTextForms();
        String numeral = DualFormatHelper.toNumeral(numberSystem, ((Character) attributeQuery.get(Attributes.ZERO_DIGIT, Character.valueOf(numberSystem.getDigits().charAt(0)))).charValue(), getNumber());
        if (!this.leap) {
            return numeral;
        }
        boolean zBooleanValue = ((Boolean) attributeQuery.get(LEAP_MONTH_IS_TRAILING, Boolean.valueOf("R".equals(textForms.get("leap-alignment"))))).booleanValue();
        char cCharValue = ((Character) attributeQuery.get(LEAP_MONTH_INDICATOR, Character.valueOf(textForms.get("leap-indicator").charAt(0)))).charValue();
        if (zBooleanValue) {
            sb = new StringBuilder();
            sbAppend = sb.append(numeral).append(cCharValue);
        } else {
            sb = new StringBuilder();
            sbAppend = sb.append(cCharValue).append(numeral);
        }
        return sbAppend.toString();
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            return CACHE[this.index + (this.leap ? 12 : 0)];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new StreamCorruptedException();
        }
    }
}
