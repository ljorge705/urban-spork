package net.time4j.format.expert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
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
final class TimezoneGenericProcessor implements FormatProcessor<TZID> {
    private static final Map<NameStyle, ConcurrentMap<Locale, ZoneLabels>> CACHE_ZONENAMES = new EnumMap(NameStyle.class);
    private static final String DEFAULT_PROVIDER = "DEFAULT";
    private static final int MAX = 25;
    private final FormatProcessor<TZID> fallback;
    private final Leniency lenientMode;
    private final Locale locale;
    private final Set<TZID> preferredZones;
    private final int protectedLength;
    private final NameStyle style;

    @Override // net.time4j.format.expert.FormatProcessor
    public boolean isNumerical() {
        return false;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> withElement(ChronoElement<TZID> chronoElement) {
        return this;
    }

    static {
        for (NameStyle nameStyle : NameStyle.values()) {
            CACHE_ZONENAMES.put(nameStyle, new ConcurrentHashMap());
        }
    }

    TimezoneGenericProcessor(NameStyle nameStyle) {
        this.style = nameStyle;
        this.fallback = new LocalizedGMTProcessor(nameStyle.isAbbreviation());
        this.preferredZones = null;
        this.lenientMode = Leniency.SMART;
        this.locale = Locale.ROOT;
        this.protectedLength = 0;
    }

    TimezoneGenericProcessor(NameStyle nameStyle, Set<TZID> set) {
        this.style = nameStyle;
        this.fallback = new LocalizedGMTProcessor(nameStyle.isAbbreviation());
        this.preferredZones = Collections.unmodifiableSet(new LinkedHashSet(set));
        this.lenientMode = Leniency.SMART;
        this.locale = Locale.ROOT;
        this.protectedLength = 0;
    }

    private TimezoneGenericProcessor(NameStyle nameStyle, FormatProcessor<TZID> formatProcessor, Set<TZID> set, Leniency leniency, Locale locale, int i) {
        this.style = nameStyle;
        this.fallback = formatProcessor;
        this.preferredZones = set;
        this.lenientMode = leniency;
        this.locale = locale;
        this.protectedLength = i;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public int print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, Set<ElementPosition> set, boolean z) throws IOException {
        TZID timezone;
        if (chronoDisplay.hasTimezone()) {
            timezone = chronoDisplay.getTimezone();
        } else if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
            timezone = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID);
        } else {
            throw new IllegalArgumentException("Cannot extract timezone name in style " + this.style + " from: " + chronoDisplay);
        }
        if (timezone instanceof ZonalOffset) {
            return this.fallback.print(chronoDisplay, appendable, attributeQuery, set, z);
        }
        String displayName = Timezone.of(timezone).getDisplayName(this.style, z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT));
        if (displayName.equals(timezone.canonical())) {
            return this.fallback.print(chronoDisplay, appendable, attributeQuery, set, z);
        }
        int length = appendable instanceof CharSequence ? ((CharSequence) appendable).length() : -1;
        appendable.append(displayName);
        int length2 = displayName.length();
        if (length != -1 && length2 > 0 && set != null) {
            set.add(new ElementPosition(TimezoneElement.TIMEZONE_ID, length, length + length2));
        }
        return length2;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public void parse(CharSequence charSequence, ParseLog parseLog, AttributeQuery attributeQuery, ParsedEntity<?> parsedEntity, boolean z) {
        List<TZID> listResolveUsingPreferred;
        boolean z2;
        ZoneLabels zoneLabelsPutIfAbsent;
        int position = parseLog.getPosition();
        int length = charSequence.length();
        int iIntValue = z ? this.protectedLength : ((Integer) attributeQuery.get(Attributes.PROTECTED_CHARACTERS, 0)).intValue();
        if (iIntValue > 0) {
            length -= iIntValue;
        }
        if (position >= length) {
            parseLog.setError(position, "Missing timezone name in style " + this.style + ".");
            return;
        }
        Locale locale = z ? this.locale : (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
        Leniency leniency = z ? this.lenientMode : (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
        String string = charSequence.subSequence(position, Math.min(position + 3, length)).toString();
        if (string.startsWith(TimeZones.GMT_ID) || string.startsWith("UT")) {
            this.fallback.parse(charSequence, parseLog, attributeQuery, parsedEntity, z);
            return;
        }
        ConcurrentMap<Locale, ZoneLabels> concurrentMap = CACHE_ZONENAMES.get(this.style);
        ZoneLabels zoneLabelsCreateZoneNames = concurrentMap.get(locale);
        if (zoneLabelsCreateZoneNames == null) {
            zoneLabelsCreateZoneNames = createZoneNames(locale);
            if (concurrentMap.size() < 25 && (zoneLabelsPutIfAbsent = concurrentMap.putIfAbsent(locale, zoneLabelsCreateZoneNames)) != null) {
                zoneLabelsCreateZoneNames = zoneLabelsPutIfAbsent;
            }
        }
        int[] iArr = {position};
        List<TZID> zoneNames = readZoneNames(zoneLabelsCreateZoneNames, charSequence.subSequence(0, length), iArr);
        int size = zoneNames.size();
        if (size == 0) {
            parseLog.setError(position, "Unknown timezone name: " + string);
            return;
        }
        if (size > 1 && !leniency.isStrict()) {
            zoneNames = excludeWinZones(zoneNames);
            size = zoneNames.size();
        }
        if (size <= 1 || leniency.isLax()) {
            listResolveUsingPreferred = zoneNames;
        } else {
            TZID tzid = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID, ZonalOffset.UTC);
            if (tzid instanceof ZonalOffset) {
                listResolveUsingPreferred = resolveUsingPreferred(zoneNames, locale, leniency);
            } else {
                Iterator<TZID> it = zoneNames.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        listResolveUsingPreferred = zoneNames;
                        z2 = false;
                        break;
                    } else {
                        TZID next = it.next();
                        if (next.canonical().equals(tzid.canonical())) {
                            listResolveUsingPreferred = Collections.singletonList(next);
                            z2 = true;
                            break;
                        }
                    }
                }
                if (!z2) {
                    listResolveUsingPreferred = resolveUsingPreferred(listResolveUsingPreferred, locale, leniency);
                }
            }
        }
        int size2 = listResolveUsingPreferred.size();
        if (size2 == 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<TZID> it2 = zoneNames.iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().canonical());
            }
            parseLog.setError(position, "Time zone name \"" + string + "\" not found among preferred timezones in locale " + locale + ", style=" + this.style + ", candidates=" + arrayList);
            return;
        }
        if (size2 == 1 || leniency.isLax()) {
            parsedEntity.put(TimezoneElement.TIMEZONE_ID, listResolveUsingPreferred.get(0));
            parseLog.setPosition(iArr[0]);
        } else {
            parseLog.setError(position, "Time zone name of style " + this.style + " is not unique: \"" + string + "\" in " + toString(listResolveUsingPreferred));
        }
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public ChronoElement<TZID> getElement() {
        return TimezoneElement.TIMEZONE_ID;
    }

    @Override // net.time4j.format.expert.FormatProcessor
    public FormatProcessor<TZID> quickPath(ChronoFormatter<?> chronoFormatter, AttributeQuery attributeQuery, int i) {
        return new TimezoneGenericProcessor(this.style, this.fallback, this.preferredZones, (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART), (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT), ((Integer) attributeQuery.get(Attributes.PROTECTED_CHARACTERS, 0)).intValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimezoneGenericProcessor)) {
            return false;
        }
        TimezoneGenericProcessor timezoneGenericProcessor = (TimezoneGenericProcessor) obj;
        if (this.style == timezoneGenericProcessor.style) {
            Set<TZID> set = this.preferredZones;
            Set<TZID> set2 = timezoneGenericProcessor.preferredZones;
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
        return this.style.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append("[style=");
        sb.append(this.style);
        sb.append(", preferredZones=");
        sb.append(this.preferredZones);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    private ZoneLabels createZoneNames(Locale locale) {
        ZoneLabels.Node nodeInsert = null;
        for (TZID tzid : Timezone.getAvailableIDs()) {
            String displayName = Timezone.getDisplayName(tzid, this.style, locale);
            if (!displayName.equals(tzid.canonical())) {
                nodeInsert = ZoneLabels.insert(nodeInsert, displayName, tzid);
            }
        }
        return new ZoneLabels(nodeInsert);
    }

    private static List<TZID> readZoneNames(ZoneLabels zoneLabels, CharSequence charSequence, int[] iArr) {
        String strLongestPrefixOf = zoneLabels.longestPrefixOf(charSequence, iArr[0]);
        List<TZID> listFind = zoneLabels.find(strLongestPrefixOf);
        if (!listFind.isEmpty()) {
            iArr[0] = iArr[0] + strLongestPrefixOf.length();
        }
        return listFind;
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
}
