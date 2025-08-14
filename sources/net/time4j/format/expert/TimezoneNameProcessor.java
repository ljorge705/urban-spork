package net.time4j.format.expert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.UnixTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.FlagElement;
import net.time4j.format.Attributes;
import net.time4j.format.Leniency;
import net.time4j.format.expert.ZoneLabels;
import net.time4j.tz.NameStyle;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
final class TimezoneNameProcessor implements FormatProcessor<TZID> {
    private static final ConcurrentMap<Locale, TZNames> CACHE_ABBREVIATIONS = new ConcurrentHashMap();
    private static final ConcurrentMap<Locale, TZNames> CACHE_ZONENAMES = new ConcurrentHashMap();
    private static final String DEFAULT_PROVIDER = "DEFAULT";
    private static final int MAX = 25;
    private final boolean abbreviated;
    private final FormatProcessor<TZID> fallback;
    private final Leniency lenientMode;
    private final Locale locale;
    private final Set<TZID> preferredZones;
    private final int protectedLength;

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return false;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> withElement(ChronoElement<TZID> chronoElement) {
        return this;
    }

    TimezoneNameProcessor(boolean z) {
        this.abbreviated = z;
        this.fallback = new LocalizedGMTProcessor(z);
        this.preferredZones = null;
        this.lenientMode = Leniency.SMART;
        this.locale = Locale.ROOT;
        this.protectedLength = 0;
    }

    TimezoneNameProcessor(boolean z, Set<TZID> set) {
        this.abbreviated = z;
        this.fallback = new LocalizedGMTProcessor(z);
        this.preferredZones = Collections.unmodifiableSet(new LinkedHashSet(set));
        this.lenientMode = Leniency.SMART;
        this.locale = Locale.ROOT;
        this.protectedLength = 0;
    }

    private TimezoneNameProcessor(boolean z, FormatProcessor<TZID> formatProcessor, Set<TZID> set, Leniency leniency, Locale locale, int i) {
        this.abbreviated = z;
        this.fallback = formatProcessor;
        this.preferredZones = set;
        this.lenientMode = leniency;
        this.locale = locale;
        this.protectedLength = i;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public int print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, Set<ElementPosition> set, boolean z) throws IOException {
        if (!chronoDisplay.hasTimezone()) {
            throw new IllegalArgumentException("Cannot extract timezone name from: " + chronoDisplay);
        }
        TZID timezone = chronoDisplay.getTimezone();
        if (timezone instanceof ZonalOffset) {
            return this.fallback.print(chronoDisplay, appendable, attributeQuery, set, z);
        }
        if (chronoDisplay instanceof UnixTime) {
            Timezone timezoneOf = Timezone.of(timezone);
            String displayName = timezoneOf.getDisplayName(getStyle(timezoneOf.isDaylightSaving((UnixTime) UnixTime.class.cast(chronoDisplay))), z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT));
            int length = appendable instanceof CharSequence ? ((CharSequence) appendable).length() : -1;
            appendable.append(displayName);
            int length2 = displayName.length();
            if (length != -1 && length2 > 0 && set != null) {
                set.add(new ElementPosition(TimezoneElement.TIMEZONE_ID, length, length + length2));
            }
            return length2;
        }
        throw new IllegalArgumentException("Cannot extract timezone name from: " + chronoDisplay);
    }

    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v4 */
    @Override // net.time4j.format.expert.FormatProcessor
    public void parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, ParsedEntity<?> parsedEntity, boolean z) {
        TZNames tZNames;
        List<TZID> listResolveUsingPreferred;
        List<TZID> listResolveUsingPreferred2;
        ?? r11;
        boolean z2;
        TZNames tZNamesPutIfAbsent;
        int position = parseLog.getPosition();
        int length = charSequence.length();
        int iIntValue = z ? this.protectedLength : ((Integer) attributeQuery.get(Attributes.PROTECTED_CHARACTERS, 0)).intValue();
        if (iIntValue > 0) {
            length -= iIntValue;
        }
        if (position >= length) {
            parseLog.setError(position, "Missing timezone name.");
            return;
        }
        Locale locale = z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
        Leniency leniency = z ? this.lenientMode : (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
        String strExtractRelevantKey = extractRelevantKey(charSequence, position, length);
        if (strExtractRelevantKey.startsWith(TimeZones.GMT_ID) || strExtractRelevantKey.startsWith("UT")) {
            this.fallback.parse(charSequence, parseLog, attributeQuery, parsedEntity, z);
            return;
        }
        ConcurrentMap<Locale, TZNames> concurrentMap = this.abbreviated ? CACHE_ABBREVIATIONS : CACHE_ZONENAMES;
        TZNames tZNames2 = concurrentMap.get(locale);
        if (tZNames2 == null) {
            tZNames = new TZNames(createZoneNames(locale, false), createZoneNames(locale, true));
            if (concurrentMap.size() < 25 && (tZNamesPutIfAbsent = concurrentMap.putIfAbsent(locale, tZNames)) != null) {
                tZNames = tZNamesPutIfAbsent;
            }
        } else {
            tZNames = tZNames2;
        }
        List<TZID> arrayList = new ArrayList<>();
        List<TZID> arrayList2 = new ArrayList<>();
        int[] iArr = new int[2];
        tZNames.search(charSequence.subSequence(0, length), position, arrayList, arrayList2, iArr);
        int size = arrayList.size() + arrayList2.size();
        if (size == 0) {
            parseLog.setError(position, "\"" + strExtractRelevantKey + "\" does not match any known timezone name.");
            return;
        }
        if (size > 1 && !leniency.isStrict()) {
            arrayList = excludeWinZones(arrayList);
            arrayList2 = excludeWinZones(arrayList2);
            size = arrayList.size() + arrayList2.size();
        }
        if (size <= 1 || leniency.isLax()) {
            listResolveUsingPreferred = arrayList;
            listResolveUsingPreferred2 = arrayList2;
        } else {
            TZID tzid = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID, ZonalOffset.UTC);
            if (tzid instanceof ZonalOffset) {
                listResolveUsingPreferred = arrayList;
                listResolveUsingPreferred2 = arrayList2;
                z2 = false;
            } else {
                Iterator<TZID> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        listResolveUsingPreferred = arrayList;
                        listResolveUsingPreferred2 = arrayList2;
                        z2 = false;
                        break;
                    } else {
                        TZID next = it.next();
                        if (next.canonical().equals(tzid.canonical())) {
                            listResolveUsingPreferred = Collections.singletonList(next);
                            listResolveUsingPreferred2 = Collections.emptyList();
                            z2 = true;
                            break;
                        }
                    }
                }
                if (!z2) {
                    Iterator<TZID> it2 = listResolveUsingPreferred2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        TZID next2 = it2.next();
                        if (next2.canonical().equals(tzid.canonical())) {
                            listResolveUsingPreferred = Collections.emptyList();
                            listResolveUsingPreferred2 = Collections.singletonList(next2);
                            z2 = true;
                            break;
                        }
                    }
                }
            }
            if (!z2) {
                if (listResolveUsingPreferred.size() > 0) {
                    listResolveUsingPreferred = resolveUsingPreferred(listResolveUsingPreferred, locale, leniency);
                }
                if (listResolveUsingPreferred2.size() > 0) {
                    listResolveUsingPreferred2 = resolveUsingPreferred(listResolveUsingPreferred2, locale, leniency);
                }
            }
        }
        int size2 = listResolveUsingPreferred.size() + listResolveUsingPreferred2.size();
        if (size2 == 0) {
            ArrayList arrayList3 = new ArrayList();
            Iterator<TZID> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                arrayList3.add(it3.next().canonical());
            }
            Iterator<TZID> it4 = arrayList2.iterator();
            while (it4.hasNext()) {
                arrayList3.add(it4.next().canonical());
            }
            parseLog.setError(position, "Time zone name \"" + strExtractRelevantKey + "\" not found among preferred timezones in locale " + locale + ", candidates=" + arrayList3);
            return;
        }
        if (listResolveUsingPreferred.size() > 0) {
            if ((size2 != 2 || listResolveUsingPreferred2.size() != 1 || !listResolveUsingPreferred.get(0).canonical().equals(listResolveUsingPreferred2.get(0).canonical())) && !listResolveUsingPreferred2.isEmpty()) {
                ArrayList arrayList4 = new ArrayList(listResolveUsingPreferred);
                arrayList4.addAll(listResolveUsingPreferred2);
                listResolveUsingPreferred = arrayList4;
            }
            r11 = 0;
        } else {
            listResolveUsingPreferred = listResolveUsingPreferred2;
            r11 = 1;
        }
        if (listResolveUsingPreferred.size() == 1 || leniency.isLax()) {
            parsedEntity.put(TimezoneElement.TIMEZONE_ID, listResolveUsingPreferred.get(0));
            parsedEntity.put(FlagElement.DAYLIGHT_SAVING, Boolean.valueOf((boolean) r11));
            parseLog.setPosition(iArr[r11]);
            return;
        }
        parseLog.setError(position, "Time zone name is not unique: \"" + strExtractRelevantKey + "\" in " + toString(listResolveUsingPreferred));
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<TZID> getElement() {
        return TimezoneElement.TIMEZONE_ID;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> quickPath(ChronoFormatter<?> chronoFormatter, AttributeQuery attributeQuery, int i) {
        return new TimezoneNameProcessor(this.abbreviated, this.fallback, this.preferredZones, (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART), (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT), ((Integer) attributeQuery.get(Attributes.PROTECTED_CHARACTERS, 0)).intValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimezoneNameProcessor)) {
            return false;
        }
        TimezoneNameProcessor timezoneNameProcessor = (TimezoneNameProcessor) obj;
        if (this.abbreviated == timezoneNameProcessor.abbreviated) {
            Set<TZID> set = this.preferredZones;
            Set<TZID> set2 = timezoneNameProcessor.preferredZones;
            if (set == null) {
                if (set2 == null) {
                    return true;
                }
            } else if (set.equals(set2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Set<TZID> set = this.preferredZones;
        return (set == null ? 0 : set.hashCode()) + (this.abbreviated ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append("[abbreviated=");
        sb.append(this.abbreviated);
        sb.append(", preferredZones=");
        sb.append(this.preferredZones);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private String extractRelevantKey(CharSequence charSequence, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i2; i3++) {
            char cCharAt = charSequence.charAt(i3);
            if (!Character.isLetter(cCharAt) && (this.abbreviated || i3 <= i || Character.isDigit(cCharAt))) {
                break;
            }
            sb.append(cCharAt);
        }
        return sb.toString().trim();
    }

    private ZoneLabels createZoneNames(Locale locale, boolean z) {
        NameStyle style = getStyle(z);
        ZoneLabels.Node nodeInsert = null;
        for (TZID tzid : Timezone.getAvailableIDs()) {
            String displayName = Timezone.getDisplayName(tzid, style, locale);
            if (!displayName.equals(tzid.canonical())) {
                nodeInsert = ZoneLabels.insert(nodeInsert, displayName, tzid);
            }
        }
        return new ZoneLabels(nodeInsert);
    }

    private static List<TZID> excludeWinZones(List<TZID> list) {
        if (list.size() > 1) {
            ArrayList arrayList = new ArrayList(list);
            int size = list.size();
            for (int i = 1; i < size; i++) {
                TZID tzid = list.get(i);
                if (tzid.canonical().startsWith("WINDOWS~")) {
                    arrayList.remove(tzid);
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return list;
    }

    private List<TZID> resolveUsingPreferred(List<TZID> list, Locale locale, Leniency leniency) {
        boolean z;
        HashMap map = new HashMap();
        map.put(DEFAULT_PROVIDER, new ArrayList());
        Iterator<TZID> it = list.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            String strCanonical = it.next().canonical();
            Set<TZID> preferredIDs = this.preferredZones;
            int iIndexOf = strCanonical.indexOf(WebSocketProtocol.PAYLOAD_SHORT);
            String strSubstring = iIndexOf >= 0 ? strCanonical.substring(0, iIndexOf) : DEFAULT_PROVIDER;
            if (preferredIDs == null) {
                preferredIDs = Timezone.getPreferredIDs(locale, leniency.isSmart(), strSubstring);
            }
            Iterator<TZID> it2 = preferredIDs.iterator();
            while (true) {
                if (it2.hasNext()) {
                    TZID next = it2.next();
                    if (next.canonical().equals(strCanonical)) {
                        List arrayList = (List) map.get(strSubstring);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            map.put(strSubstring, arrayList);
                        }
                        arrayList.add(next);
                    }
                }
            }
        }
        List<TZID> list2 = (List) map.get(DEFAULT_PROVIDER);
        if (!list2.isEmpty()) {
            return list2;
        }
        map.remove(DEFAULT_PROVIDER);
        Iterator it3 = map.keySet().iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            List<TZID> list3 = (List) map.get((String) it3.next());
            if (!list3.isEmpty()) {
                z = true;
                list = list3;
                break;
            }
        }
        if (!z) {
            list = Collections.emptyList();
        }
        return list;
    }

    private NameStyle getStyle(boolean z) {
        return z ? this.abbreviated ? NameStyle.SHORT_DAYLIGHT_TIME : NameStyle.LONG_DAYLIGHT_TIME : this.abbreviated ? NameStyle.SHORT_STANDARD_TIME : NameStyle.LONG_STANDARD_TIME;
    }

    private static String toString(List<TZID> list) {
        StringBuilder sb = new StringBuilder(list.size() * 16);
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z = true;
        for (TZID tzid : list) {
            if (z) {
                z = false;
            } else {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(tzid.canonical());
        }
        return sb.append(AbstractJsonLexerKt.END_OBJ).toString();
    }

    private static class TZNames {
        private final ZoneLabels dstNames;
        private final ZoneLabels stdNames;

        TZNames(ZoneLabels zoneLabels, ZoneLabels zoneLabels2) {
            this.stdNames = zoneLabels;
            this.dstNames = zoneLabels2;
        }

        void search(CharSequence charSequence, int i, List<TZID> list, List<TZID> list2, int[] iArr) {
            String strLongestPrefixOf = this.stdNames.longestPrefixOf(charSequence, i);
            int length = strLongestPrefixOf.length();
            iArr[0] = i + length;
            String strLongestPrefixOf2 = this.dstNames.longestPrefixOf(charSequence, i);
            int length2 = strLongestPrefixOf2.length();
            iArr[1] = i + length2;
            if (length2 > length) {
                list2.addAll(this.dstNames.find(strLongestPrefixOf2));
                return;
            }
            if (length2 < length) {
                list.addAll(this.stdNames.find(strLongestPrefixOf));
            } else if (length > 0) {
                list.addAll(this.stdNames.find(strLongestPrefixOf));
                list2.addAll(this.dstNames.find(strLongestPrefixOf2));
            }
        }
    }
}
