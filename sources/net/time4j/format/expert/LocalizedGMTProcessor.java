package net.time4j.format.expert;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.UnixTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.Leniency;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
final class LocalizedGMTProcessor implements FormatProcessor<TZID> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean abbreviated;
    private final boolean caseInsensitive;
    private final Leniency lenientMode;
    private final Locale locale;
    private final String minusSign;
    private final boolean noPrefix;
    private final String plusSign;
    private final char zeroDigit;
    private static final ZonalOffset PROTOTYPE = ZonalOffset.ofTotalSeconds(64800);
    private static final ConcurrentMap<Locale, String> UTC_LITERALS = new ConcurrentHashMap();
    private static final ConcurrentMap<Locale, Info> STD_PATTERN_INFOS = new ConcurrentHashMap();

    public int hashCode() {
        return this.abbreviated ? 1 : 0;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return false;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> withElement(ChronoElement<TZID> chronoElement) {
        return this;
    }

    LocalizedGMTProcessor(boolean z) {
        this(z, true, false, Locale.ROOT, "+", "-", '0', Leniency.SMART);
    }

    private LocalizedGMTProcessor(boolean z, boolean z2, boolean z3, Locale locale, String str, String str2, char c, Leniency leniency) {
        this.abbreviated = z;
        this.caseInsensitive = z2;
        this.noPrefix = z3;
        this.locale = locale;
        this.plusSign = str;
        this.minusSign = str2;
        this.zeroDigit = c;
        this.lenientMode = leniency;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public int print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, Set<ElementPosition> set, boolean z) throws IOException {
        ZonalOffset offset;
        int length;
        ZonalOffset zonalOffset;
        char c;
        int length2;
        int length3 = appendable instanceof CharSequence ? ((CharSequence) appendable).length() : -1;
        TZID timezone = chronoDisplay.hasTimezone() ? chronoDisplay.getTimezone() : null;
        if (timezone == null) {
            offset = getOffset(chronoDisplay, attributeQuery);
        } else if (timezone instanceof ZonalOffset) {
            offset = (ZonalOffset) timezone;
        } else if (chronoDisplay instanceof UnixTime) {
            offset = Timezone.of(timezone).getOffset((UnixTime) chronoDisplay);
        } else {
            throw new IllegalArgumentException("Cannot extract timezone offset from: " + chronoDisplay);
        }
        Locale locale = z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
        char c2 = '0';
        char cCharValue = z ? this.zeroDigit : ((Character) attributeQuery.get(Attributes.ZERO_DIGIT, '0')).charValue();
        String str = z ? this.plusSign : (String) attributeQuery.get(AttributeSet.PLUS_SIGN, "+");
        String str2 = z ? this.minusSign : (String) attributeQuery.get(AttributeSet.MINUS_SIGN, "-");
        boolean zBooleanValue = z ? this.noPrefix : ((Boolean) attributeQuery.get(Attributes.NO_GMT_PREFIX, Boolean.FALSE)).booleanValue();
        int integralAmount = offset.getIntegralAmount();
        int fractionalAmount = offset.getFractionalAmount();
        if (!zBooleanValue && integralAmount == 0 && fractionalAmount == 0) {
            String literalUTC = getLiteralUTC(locale);
            appendable.append(literalUTC);
            length = literalUTC.length();
        } else {
            Info patternInfo = getPatternInfo(locale);
            int length4 = patternInfo.pattern.length();
            int length5 = 0;
            int i = 0;
            while (i < length4) {
                char cCharAt = patternInfo.pattern.charAt(i);
                if (patternInfo.start > i || patternInfo.end <= i) {
                    zonalOffset = offset;
                    c = c2;
                    if (!zBooleanValue) {
                        appendable.append(cCharAt);
                        length5++;
                    }
                } else {
                    if (offset.getSign() == OffsetSign.BEHIND_UTC) {
                        appendable.append(str2);
                        length2 = str2.length();
                    } else {
                        appendable.append(str);
                        length2 = str.length();
                    }
                    length5 += length2;
                    int absoluteHours = offset.getAbsoluteHours();
                    int absoluteMinutes = offset.getAbsoluteMinutes();
                    int absoluteSeconds = offset.getAbsoluteSeconds();
                    if (absoluteHours < 10 && !this.abbreviated) {
                        appendable.append(cCharValue);
                        length5++;
                    }
                    String strValueOf = String.valueOf(absoluteHours);
                    zonalOffset = offset;
                    for (int i2 = 0; i2 < strValueOf.length(); i2++) {
                        appendable.append((char) ((strValueOf.charAt(i2) - '0') + cCharValue));
                        length5++;
                    }
                    if (absoluteMinutes != 0 || absoluteSeconds != 0 || !this.abbreviated) {
                        appendable.append(patternInfo.separator);
                        length5 += patternInfo.separator.length();
                        if (absoluteMinutes < 10) {
                            appendable.append(cCharValue);
                            length5++;
                        }
                        String strValueOf2 = String.valueOf(absoluteMinutes);
                        for (int i3 = 0; i3 < strValueOf2.length(); i3++) {
                            appendable.append((char) ((strValueOf2.charAt(i3) - '0') + cCharValue));
                            length5++;
                        }
                        if (absoluteSeconds != 0) {
                            appendable.append(patternInfo.separator);
                            length5 += patternInfo.separator.length();
                            if (absoluteSeconds < 10) {
                                appendable.append(cCharValue);
                                length5++;
                            }
                            String strValueOf3 = String.valueOf(absoluteSeconds);
                            for (int i4 = 0; i4 < strValueOf3.length(); i4++) {
                                appendable.append((char) ((strValueOf3.charAt(i4) - '0') + cCharValue));
                                length5++;
                            }
                        }
                    }
                    c = '0';
                    i = patternInfo.end - 1;
                }
                i++;
                c2 = c;
                offset = zonalOffset;
            }
            length = length5;
        }
        if (length3 != -1 && length > 0 && set != null) {
            set.add(new ElementPosition(TimezoneElement.TIMEZONE_ID, length3, length3 + length));
        }
        return length;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public void parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, ParsedEntity<?> parsedEntity, boolean z) {
        int i;
        Locale locale;
        boolean z2;
        String str;
        String str2;
        OffsetSign offsetSign;
        int i2;
        Leniency leniency;
        int i3;
        int twoDigits;
        ZonalOffset zonalOffsetOfHoursMinutes;
        int iSubSequenceEquals;
        int length = charSequence.length();
        int position = parseLog.getPosition();
        if (position >= length) {
            parseLog.setError(position, "Missing localized time zone offset.");
            return;
        }
        Locale locale2 = z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
        boolean zIsRTL = CalendarText.isRTL(locale2);
        boolean zBooleanValue = z ? this.noPrefix : ((Boolean) attributeQuery.get(Attributes.NO_GMT_PREFIX, Boolean.FALSE)).booleanValue();
        boolean zBooleanValue2 = z ? this.caseInsensitive : ((Boolean) attributeQuery.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE)).booleanValue();
        char cCharValue = z ? this.zeroDigit : ((Character) attributeQuery.get(Attributes.ZERO_DIGIT, '0')).charValue();
        String str3 = z ? this.plusSign : (String) attributeQuery.get(AttributeSet.PLUS_SIGN, "+");
        String str4 = z ? this.minusSign : (String) attributeQuery.get(AttributeSet.MINUS_SIGN, "-");
        Info patternInfo = getPatternInfo(locale2);
        int length2 = patternInfo.pattern.length();
        int i4 = position;
        int i5 = 0;
        ZonalOffset zonalOffset = null;
        while (i5 < length2) {
            int i6 = length2;
            char cCharAt = patternInfo.pattern.charAt(i5);
            if (patternInfo.start > i5 || patternInfo.end <= i5) {
                i = position;
                locale = locale2;
                z2 = zIsRTL;
                str = str3;
                str2 = str4;
                if (zBooleanValue) {
                    continue;
                } else {
                    char cCharAt2 = i4 < length ? charSequence.charAt(i4) : (char) 0;
                    if ((zBooleanValue2 || cCharAt != cCharAt2) && !(zBooleanValue2 && charEqualsIgnoreCase(cCharAt, cCharAt2))) {
                        int utc = parseUTC(charSequence, length, i, locale, zBooleanValue2);
                        if (utc > 0) {
                            parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.UTC);
                            parseLog.setPosition(i + utc);
                            return;
                        } else {
                            parseLog.setError(i, "Literal mismatched in localized time zone offset.");
                            return;
                        }
                    }
                    i4++;
                }
            } else {
                int iSubSequenceEquals2 = LiteralProcessor.subSequenceEquals(charSequence, i4, str3, zBooleanValue2, zIsRTL);
                if (iSubSequenceEquals2 == -1) {
                    iSubSequenceEquals2 = LiteralProcessor.subSequenceEquals(charSequence, i4, str4, zBooleanValue2, zIsRTL);
                    if (iSubSequenceEquals2 == -1) {
                        int utc2 = zBooleanValue ? 0 : parseUTC(charSequence, length, position, locale2, zBooleanValue2);
                        if (utc2 > 0) {
                            parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.UTC);
                            parseLog.setPosition(position + utc2);
                            return;
                        } else {
                            parseLog.setError(position, "Missing sign in localized time zone offset.");
                            return;
                        }
                    }
                    offsetSign = OffsetSign.BEHIND_UTC;
                } else {
                    offsetSign = OffsetSign.AHEAD_OF_UTC;
                }
                OffsetSign offsetSign2 = offsetSign;
                int i7 = i4 + iSubSequenceEquals2;
                int hours = parseHours(charSequence, i7, cCharValue);
                str = str3;
                if (hours == -1000) {
                    parseLog.setError(i7, "Missing hour part in localized time zone offset.");
                    return;
                }
                if (hours < 0) {
                    hours = ~hours;
                    i2 = i7 + 1;
                } else {
                    i2 = i7 + 2;
                }
                if (i2 >= length) {
                    if (this.abbreviated) {
                        parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.ofHours(offsetSign2, hours));
                        parseLog.setPosition(i2);
                        return;
                    } else {
                        parseLog.setError(i2, "Missing minute part in localized time zone offset.");
                        return;
                    }
                }
                str2 = str4;
                if (z) {
                    leniency = this.lenientMode;
                    i = position;
                    locale = locale2;
                } else {
                    i = position;
                    locale = locale2;
                    leniency = (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
                }
                int iSubSequenceEquals3 = LiteralProcessor.subSequenceEquals(charSequence, i2, patternInfo.separator, zBooleanValue2, zIsRTL);
                if (iSubSequenceEquals3 != -1) {
                    i2 += iSubSequenceEquals3;
                } else if (this.abbreviated) {
                    parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.ofHours(offsetSign2, hours));
                    parseLog.setPosition(i2);
                    return;
                } else if (leniency.isStrict()) {
                    parseLog.setError(i2, "Mismatch of localized time zone offset separator.");
                    return;
                }
                int twoDigits2 = parseTwoDigits(charSequence, i2, cCharValue);
                if (twoDigits2 == -1000) {
                    parseLog.setError(i2, "Minute part in localized time zone offset does not match expected pattern mm.");
                    return;
                }
                i4 = i2 + 2;
                if (i4 >= length || (iSubSequenceEquals = LiteralProcessor.subSequenceEquals(charSequence, i4, patternInfo.separator, zBooleanValue2, zIsRTL)) == -1) {
                    z2 = zIsRTL;
                    i3 = -1000;
                    twoDigits = 0;
                } else {
                    int i8 = i4 + iSubSequenceEquals;
                    twoDigits = parseTwoDigits(charSequence, i8, cCharValue);
                    z2 = zIsRTL;
                    i3 = -1000;
                    i4 = twoDigits == -1000 ? i8 - iSubSequenceEquals : i8 + 2;
                }
                if (twoDigits == 0 || twoDigits == i3) {
                    zonalOffsetOfHoursMinutes = ZonalOffset.ofHoursMinutes(offsetSign2, hours, twoDigits2);
                } else {
                    int i9 = (hours * NikonType2MakernoteDirectory.TAG_NIKON_SCAN) + (twoDigits2 * 60) + twoDigits;
                    if (offsetSign2 == OffsetSign.BEHIND_UTC) {
                        i9 = -i9;
                    }
                    zonalOffsetOfHoursMinutes = ZonalOffset.ofTotalSeconds(i9);
                }
                zonalOffset = zonalOffsetOfHoursMinutes;
                i5 = patternInfo.end - 1;
            }
            position = i;
            locale2 = locale;
            i5++;
            length2 = i6;
            str3 = str;
            str4 = str2;
            zIsRTL = z2;
        }
        ZonalOffset zonalOffset2 = zonalOffset;
        if (zonalOffset2 == null) {
            parseLog.setError(i4, "Unable to determine localized time zone offset.");
        } else {
            parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, zonalOffset2);
            parseLog.setPosition(i4);
        }
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<TZID> getElement() {
        return TimezoneElement.TIMEZONE_OFFSET;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> quickPath(ChronoFormatter<?> chronoFormatter, AttributeQuery attributeQuery, int i) {
        return new LocalizedGMTProcessor(this.abbreviated, ((Boolean) attributeQuery.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE)).booleanValue(), ((Boolean) attributeQuery.get(Attributes.NO_GMT_PREFIX, Boolean.FALSE)).booleanValue(), (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT), (String) attributeQuery.get(AttributeSet.PLUS_SIGN, "+"), (String) attributeQuery.get(AttributeSet.MINUS_SIGN, "-"), ((Character) attributeQuery.get(Attributes.ZERO_DIGIT, '0')).charValue(), (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocalizedGMTProcessor) && this.abbreviated == ((LocalizedGMTProcessor) obj).abbreviated;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append("[abbreviated=");
        sb.append(this.abbreviated);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private static ZonalOffset getOffset(ChronoDisplay chronoDisplay, AttributeQuery attributeQuery) {
        if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
            TZID tzid = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID);
            if (tzid instanceof ZonalOffset) {
                return (ZonalOffset) tzid;
            }
        }
        throw new IllegalArgumentException("Cannot extract timezone offset from format attributes for: " + chronoDisplay);
    }

    private static int parseUTC(CharSequence charSequence, int i, int i2, Locale locale, boolean z) {
        String[] strArr = {TimeZones.GMT_ID, getLiteralUTC(locale), "UTC", "UT"};
        for (int i3 = 0; i3 < 4; i3++) {
            String str = strArr[i3];
            int length = str.length();
            if (i - i2 >= length) {
                String string = charSequence.subSequence(i2, i2 + length).toString();
                if ((z && string.equalsIgnoreCase(str)) || (!z && string.equals(str))) {
                    return length;
                }
            }
        }
        return 0;
    }

    private static int parseTwoDigits(CharSequence charSequence, int i, char c) {
        int iCharAt;
        int i2 = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = i + i3;
            if (i4 >= charSequence.length() || (iCharAt = charSequence.charAt(i4) - c) < 0 || iCharAt > 9) {
                return -1000;
            }
            i2 = (i2 * 10) + iCharAt;
        }
        return i2;
    }

    private static int parseHours(CharSequence charSequence, int i, char c) {
        int i2 = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            int i4 = i + i3;
            if (i4 >= charSequence.length()) {
                if (i3 == 0) {
                    return -1000;
                }
                return ~i2;
            }
            int iCharAt = charSequence.charAt(i4) - c;
            if (iCharAt < 0 || iCharAt > 9) {
                if (i3 == 0) {
                    return -1000;
                }
                return ~i2;
            }
            i2 = (i2 * 10) + iCharAt;
        }
        return i2;
    }

    private static boolean charEqualsIgnoreCase(char c, char c2) {
        return c == c2 || Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
    }

    private static String getLiteralUTC(Locale locale) {
        ConcurrentMap<Locale, String> concurrentMap = UTC_LITERALS;
        String str = concurrentMap.get(locale);
        if (str != null) {
            return str;
        }
        String stdFormatPattern = ZonalOffset.UTC.getStdFormatPattern(locale);
        String strPutIfAbsent = concurrentMap.putIfAbsent(locale, stdFormatPattern);
        return strPutIfAbsent != null ? strPutIfAbsent : stdFormatPattern;
    }

    private static Info getPatternInfo(Locale locale) {
        Info info = STD_PATTERN_INFOS.get(locale);
        if (info != null) {
            return info;
        }
        String stdFormatPattern = PROTOTYPE.getStdFormatPattern(locale);
        int length = stdFormatPattern.length();
        for (int i = 0; i < length; i++) {
            if (stdFormatPattern.charAt(i) == 177) {
                int iIndexOf = stdFormatPattern.indexOf("hh", i) + 2;
                int iIndexOf2 = stdFormatPattern.indexOf("mm", iIndexOf);
                Info info2 = new Info(stdFormatPattern, stdFormatPattern.substring(iIndexOf, iIndexOf2), i, iIndexOf2 + 2);
                Info infoPutIfAbsent = STD_PATTERN_INFOS.putIfAbsent(locale, info2);
                return infoPutIfAbsent != null ? infoPutIfAbsent : info2;
            }
        }
        return info;
    }

    private static class Info {
        private final int end;
        private final String pattern;
        private final String separator;
        private final int start;

        Info(String str, String str2, int i, int i2) {
            this.pattern = str;
            this.separator = str2;
            this.start = i;
            this.end = i2;
        }
    }
}
