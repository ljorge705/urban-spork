package net.time4j.format.expert;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
enum TimezoneIDProcessor implements FormatProcessor<TZID> {
    INSTANCE;

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return false;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> quickPath(ChronoFormatter<?> chronoFormatter, AttributeQuery attributeQuery, int i) {
        return INSTANCE;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> withElement(ChronoElement<TZID> chronoElement) {
        return INSTANCE;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public int print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, Set<ElementPosition> set, boolean z) throws IOException {
        if (!chronoDisplay.hasTimezone()) {
            throw new IllegalArgumentException("Cannot extract timezone id from: " + chronoDisplay);
        }
        int length = appendable instanceof CharSequence ? ((CharSequence) appendable).length() : -1;
        String strCanonical = chronoDisplay.getTimezone().canonical();
        appendable.append(strCanonical);
        int length2 = strCanonical.length();
        if (length != -1 && length2 > 0 && set != null) {
            set.add(new ElementPosition(TimezoneElement.TIMEZONE_ID, length, length + length2));
        }
        return length2;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public void parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, ParsedEntity<?> parsedEntity, boolean z) {
        char cCharAt;
        char cCharAt2;
        int length = charSequence.length();
        int position = parseLog.getPosition();
        if (position >= length) {
            parseLog.setError(position, "Missing timezone name.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = position;
        while (i < length && (((cCharAt2 = charSequence.charAt(i)) >= 'a' && cCharAt2 <= 'z') || ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || cCharAt2 == '-' || cCharAt2 == '_' || cCharAt2 == '/'))) {
            sb.append(cCharAt2);
            i++;
        }
        if (!Character.isLetter(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
            i--;
        }
        String string = sb.toString();
        if (string.isEmpty()) {
            parseLog.setError(position, "Missing valid timezone id.");
            return;
        }
        if (string.startsWith("Etc/GMT")) {
            parseLog.setError(position, "Inverse Etc/GMT-Offsets are not supported, use UTC-Offsets instead.");
            return;
        }
        if (string.equals("Z")) {
            parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.UTC);
            parseLog.setPosition(i);
            return;
        }
        if (string.equals("UTC") || string.equals(TimeZones.GMT_ID) || string.equals("UT")) {
            if (length > i && ((cCharAt = charSequence.charAt(i)) == '+' || cCharAt == '-')) {
                parseLog.setPosition(i);
                TimezoneOffsetProcessor.EXTENDED_LONG_PARSER.parse(charSequence, parseLog, attributeQuery, parsedEntity, z);
                return;
            } else {
                parsedEntity.put(TimezoneElement.TIMEZONE_OFFSET, ZonalOffset.UTC);
                parseLog.setPosition(i);
                return;
            }
        }
        List<TZID> availableIDs = Timezone.getAvailableIDs("INCLUDE_ALIAS");
        int size = availableIDs.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            TZID tzid = availableIDs.get(i3);
            int iCompareTo = tzid.canonical().compareTo(string);
            if (iCompareTo < 0) {
                i2 = i3 + 1;
            } else {
                if (iCompareTo <= 0) {
                    parsedEntity.put(TimezoneElement.TIMEZONE_ID, tzid);
                    parseLog.setPosition(i);
                    return;
                }
                size = i3 - 1;
            }
        }
        parseLog.setError(position, "Cannot parse to timezone id: " + string);
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<TZID> getElement() {
        return TimezoneElement.TIMEZONE_ID;
    }
}
