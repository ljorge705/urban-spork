package net.time4j.tz;

import android.util.TimeUtils;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.time4j.base.GregorianDate;
import net.time4j.base.ResourceLoader;
import net.time4j.base.UnixTime;
import net.time4j.base.WallTime;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
public abstract class Timezone implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean ALLOW_SYSTEM_TZ_OVERRIDE;
    private static final boolean ANDROID;
    private static final ConcurrentMap<String, NamedReference> CACHE;
    private static final ZoneModelProvider DEFAULT_PROVIDER;
    private static final Map<String, TZID> ETCETERA;
    private static final LinkedList<Timezone> LAST_USED;
    private static final String NAME_DEFAULT = "DEFAULT";
    private static final String NAME_JUT = "java.util.TimeZone";
    static final ZoneNameProvider NAME_PROVIDER;
    private static final String NAME_TZDB = "TZDB";
    private static final ZoneModelProvider PLATFORM_PROVIDER;
    private static final Map<String, TZID> PREDEFINED;
    private static final ConcurrentMap<String, ZoneModelProvider> PROVIDERS;
    private static final ReferenceQueue<Timezone> QUEUE;
    private static final Timezone SYSTEM_TZ_ORIGINAL;
    private static volatile boolean cacheActive;
    private static volatile Timezone currentSystemTZ;
    private static int softLimit;
    private static volatile ZonalKeys zonalKeys;
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String REPOSITORY_VERSION = System.getProperty("net.time4j.tz.repository.version");
    private static final Comparator<TZID> ID_COMPARATOR = new Comparator<TZID>() { // from class: net.time4j.tz.Timezone.1
        @Override // java.util.Comparator
        public int compare(TZID tzid, TZID tzid2) {
            return tzid.canonical().compareTo(tzid2.canonical());
        }
    };
    public static final TransitionStrategy DEFAULT_CONFLICT_STRATEGY = GapResolver.PUSH_FORWARD.and(OverlapResolver.LATER_OFFSET);
    public static final TransitionStrategy STRICT_MODE = GapResolver.ABORT.and(OverlapResolver.LATER_OFFSET);

    public static Timezone ofSystem() {
        return (!ALLOW_SYSTEM_TZ_OVERRIDE || currentSystemTZ == null) ? SYSTEM_TZ_ORIGINAL : currentSystemTZ;
    }

    public abstract ZonalOffset getDaylightSavingOffset(UnixTime unixTime);

    public abstract TransitionHistory getHistory();

    public abstract TZID getID();

    public abstract ZonalOffset getOffset(GregorianDate gregorianDate, WallTime wallTime);

    public abstract ZonalOffset getOffset(UnixTime unixTime);

    public abstract ZonalOffset getStandardOffset(UnixTime unixTime);

    public abstract TransitionStrategy getStrategy();

    public abstract boolean isDaylightSaving(UnixTime unixTime);

    public abstract boolean isFixed();

    public abstract boolean isInvalid(GregorianDate gregorianDate, WallTime wallTime);

    public abstract Timezone with(TransitionStrategy transitionStrategy);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [net.time4j.tz.Timezone$1] */
    /* JADX WARN: Type inference failed for: r0v15, types: [net.time4j.tz.Timezone] */
    /* JADX WARN: Type inference failed for: r0v20, types: [net.time4j.tz.SingleOffsetTimezone] */
    /* JADX WARN: Type inference failed for: r0v21, types: [net.time4j.tz.Timezone] */
    /* JADX WARN: Type inference failed for: r0v25 */
    static {
        List<Class<? extends TZID>> listEmptyList;
        boolean zEqualsIgnoreCase = "Dalvik".equalsIgnoreCase(System.getProperty("java.vm.name"));
        ANDROID = zEqualsIgnoreCase;
        ALLOW_SYSTEM_TZ_OVERRIDE = zEqualsIgnoreCase || Boolean.getBoolean("net.time4j.allow.system.tz.override");
        ?? model = 0;
        model = 0;
        zonalKeys = null;
        currentSystemTZ = null;
        cacheActive = true;
        softLimit = 11;
        CACHE = new ConcurrentHashMap();
        PROVIDERS = new ConcurrentHashMap();
        QUEUE = new ReferenceQueue<>();
        LAST_USED = new LinkedList<>();
        try {
            listEmptyList = loadPredefined(Timezone.class.getClassLoader(), "AFRICA", "AMERICA", "AMERICA$ARGENTINA", "AMERICA$INDIANA", "AMERICA$KENTUCKY", "AMERICA$NORTH_DAKOTA", "ANTARCTICA", "ASIA", "ATLANTIC", "AUSTRALIA", "EUROPE", "INDIAN", "PACIFIC");
        } catch (ClassNotFoundException unused) {
            listEmptyList = Collections.emptyList();
        }
        HashMap map = new HashMap();
        map.put("Z", ZonalOffset.UTC);
        map.put("UT", ZonalOffset.UTC);
        map.put("UTC", ZonalOffset.UTC);
        map.put(TimeZones.GMT_ID, ZonalOffset.UTC);
        map.put("UTC0", ZonalOffset.UTC);
        map.put("GMT0", ZonalOffset.UTC);
        Iterator<Class<? extends TZID>> it = listEmptyList.iterator();
        while (it.hasNext()) {
            for (TZID tzid : (TZID[]) it.next().getEnumConstants()) {
                map.put(tzid.canonical(), tzid);
            }
        }
        PREDEFINED = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        fillEtcetera(map2);
        ETCETERA = Collections.unmodifiableMap(map2);
        ZoneModelProvider zoneModelProviderCompareTZDB = null;
        for (ZoneModelProvider zoneModelProvider : ResourceLoader.getInstance().services(ZoneModelProvider.class)) {
            String name = zoneModelProvider.getName();
            if (name.equals(NAME_TZDB)) {
                zoneModelProviderCompareTZDB = compareTZDB(zoneModelProvider, zoneModelProviderCompareTZDB);
            } else if (!name.isEmpty() && !name.equals(NAME_DEFAULT)) {
                PROVIDERS.put(name, zoneModelProvider);
            }
        }
        specificZoneNameRepository = null;
        for (ZoneNameProvider specificZoneNameRepository : ResourceLoader.getInstance().services(ZoneNameProvider.class)) {
        }
        PlatformZoneProvider platformZoneProvider = new PlatformZoneProvider();
        PLATFORM_PROVIDER = platformZoneProvider;
        if (specificZoneNameRepository == null) {
            specificZoneNameRepository = platformZoneProvider.getSpecificZoneNameRepository();
        }
        NAME_PROVIDER = specificZoneNameRepository;
        ConcurrentMap<String, ZoneModelProvider> concurrentMap = PROVIDERS;
        concurrentMap.put(NAME_JUT, platformZoneProvider);
        if (zoneModelProviderCompareTZDB == null) {
            DEFAULT_PROVIDER = platformZoneProvider;
        } else {
            concurrentMap.put(NAME_TZDB, zoneModelProviderCompareTZDB);
            DEFAULT_PROVIDER = zoneModelProviderCompareTZDB;
        }
        try {
            String property = System.getProperty("user.timezone");
            if ("Z".equals(property) || "UTC".equals(property)) {
                model = ZonalOffset.UTC.getModel();
            } else if (property != null) {
                model = getTZ(resolve(property), property, false);
            }
        } catch (SecurityException unused2) {
        }
        if (model == 0) {
            SYSTEM_TZ_ORIGINAL = getDefaultTZ();
        } else {
            SYSTEM_TZ_ORIGINAL = model;
        }
        if (ALLOW_SYSTEM_TZ_OVERRIDE) {
            currentSystemTZ = SYSTEM_TZ_ORIGINAL;
        }
        zonalKeys = new ZonalKeys();
    }

    Timezone() {
    }

    public static List<TZID> getAvailableIDs() {
        return zonalKeys.availables;
    }

    public static List<TZID> getAvailableIDs(String str) {
        if (!str.equals("INCLUDE_ALIAS")) {
            ZoneModelProvider provider = getProvider(str);
            if (provider == null) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = provider.getAvailableIDs().iterator();
            while (it.hasNext()) {
                arrayList.add(resolve(it.next()));
            }
            Collections.sort(arrayList, ID_COMPARATOR);
            return Collections.unmodifiableList(arrayList);
        }
        return zonalKeys.availablesAndAliases;
    }

    public static Set<TZID> getPreferredIDs(Locale locale, boolean z, String str) {
        ZoneModelProvider provider = getProvider(str);
        if (provider == null) {
            return Collections.emptySet();
        }
        ZoneNameProvider specificZoneNameRepository = provider.getSpecificZoneNameRepository();
        if (specificZoneNameRepository == null) {
            specificZoneNameRepository = NAME_PROVIDER;
        }
        HashSet hashSet = new HashSet();
        Iterator<String> it = specificZoneNameRepository.getPreferredIDs(locale, z).iterator();
        while (it.hasNext()) {
            hashSet.add(resolve(it.next()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public static Timezone ofPlatform() {
        return new PlatformTimezone();
    }

    public static Timezone of(TZID tzid) {
        return getTZ(tzid, true);
    }

    public static Timezone of(String str) {
        return getTZ(null, str, true);
    }

    public static Timezone of(String str, TZID tzid) {
        Timezone tz = getTZ(null, str, false);
        if (tz != null) {
            return tz;
        }
        Timezone tz2 = getTZ(tzid, false);
        return tz2 == null ? ofSystem() : tz2;
    }

    public static Timezone of(String str, TransitionHistory transitionHistory) {
        return new HistorizedTimezone(resolve(str), transitionHistory);
    }

    public static TZID normalize(TZID tzid) {
        return normalize(tzid.canonical());
    }

    public static TZID normalize(String str) {
        String strSubstring;
        String strSubstring2;
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                strSubstring = "";
                strSubstring2 = str;
                break;
            }
            if (str.charAt(i) == '~') {
                strSubstring = str.substring(0, i);
                strSubstring2 = str.substring(i + 1);
                break;
            }
            i++;
        }
        if (strSubstring2.isEmpty()) {
            throw new IllegalArgumentException("Empty zone identifier: " + str);
        }
        ZoneModelProvider zoneModelProvider = DEFAULT_PROVIDER;
        if (!strSubstring.isEmpty() && !strSubstring.equals(NAME_DEFAULT) && !strSubstring.equals("WINDOWS") && !strSubstring.equals("MILITARY") && (zoneModelProvider = PROVIDERS.get(strSubstring)) == null) {
            throw new IllegalArgumentException((strSubstring.equals(NAME_TZDB) ? "TZDB provider not available: " : "Timezone model provider not registered: ") + str);
        }
        Map<String, String> aliases = zoneModelProvider.getAliases();
        while (true) {
            String str2 = aliases.get(strSubstring2);
            if (str2 == null) {
                break;
            }
            strSubstring2 = str2;
        }
        Map<String, TZID> map = ETCETERA;
        if (map.containsKey(strSubstring2)) {
            return map.get(strSubstring2);
        }
        return resolve(strSubstring2);
    }

    public static String getProviderInfo() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(Timezone.class.getName());
        sb.append(":[default-provider=");
        sb.append(DEFAULT_PROVIDER.getName());
        sb.append(", registered={");
        Iterator<String> it = PROVIDERS.keySet().iterator();
        while (it.hasNext()) {
            ZoneModelProvider zoneModelProvider = PROVIDERS.get(it.next());
            if (zoneModelProvider != null) {
                sb.append("(name=");
                sb.append(zoneModelProvider.getName());
                String location = zoneModelProvider.getLocation();
                if (!location.isEmpty()) {
                    sb.append(",location=");
                    sb.append(location);
                }
                String version = zoneModelProvider.getVersion();
                if (!version.isEmpty()) {
                    sb.append(",version=");
                    sb.append(version);
                }
                sb.append(')');
            }
        }
        sb.append("}]");
        return sb.toString();
    }

    public static String getVersion(String str) {
        ZoneModelProvider provider = getProvider(str);
        return provider == null ? "" : provider.getVersion();
    }

    public static Set<String> getRegisteredProviders() {
        return Collections.unmodifiableSet(PROVIDERS.keySet());
    }

    public String getDisplayName(NameStyle nameStyle, Locale locale) {
        return getDisplayName(getID(), nameStyle, locale);
    }

    public static String getDisplayName(TZID tzid, NameStyle nameStyle, Locale locale) {
        String strSubstring;
        String strCanonical = tzid.canonical();
        int iIndexOf = strCanonical.indexOf(WebSocketProtocol.PAYLOAD_SHORT);
        ZoneModelProvider zoneModelProvider = DEFAULT_PROVIDER;
        if (iIndexOf >= 0) {
            String strSubstring2 = strCanonical.substring(0, iIndexOf);
            if (!strSubstring2.equals(NAME_DEFAULT) && (zoneModelProvider = PROVIDERS.get(strSubstring2)) == null) {
                return strCanonical;
            }
            strSubstring = strCanonical.substring(iIndexOf + 1);
        } else {
            strSubstring = strCanonical;
        }
        ZoneNameProvider specificZoneNameRepository = zoneModelProvider.getSpecificZoneNameRepository();
        if (specificZoneNameRepository == null) {
            specificZoneNameRepository = NAME_PROVIDER;
        }
        String displayName = specificZoneNameRepository.getDisplayName(strSubstring, nameStyle, locale);
        if (!displayName.isEmpty()) {
            return displayName;
        }
        ZoneNameProvider zoneNameProvider = NAME_PROVIDER;
        if (specificZoneNameRepository != zoneNameProvider) {
            displayName = zoneNameProvider.getDisplayName(strSubstring, nameStyle, locale);
        }
        if (!displayName.isEmpty()) {
            strCanonical = displayName;
        }
        return strCanonical;
    }

    public static boolean registerProvider(ZoneModelProvider zoneModelProvider) {
        String name = zoneModelProvider.getName();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Missing name of zone model provider.");
        }
        if (name.equals(NAME_TZDB)) {
            throw new IllegalArgumentException("TZDB provider cannot be registered after startup.");
        }
        if (name.equals(NAME_JUT)) {
            throw new IllegalArgumentException("Platform provider cannot be replaced.");
        }
        if (name.equals(NAME_DEFAULT)) {
            throw new IllegalArgumentException("Default zone model provider cannot be overridden.");
        }
        boolean z = PROVIDERS.putIfAbsent(name, zoneModelProvider) == null;
        if (z) {
            zonalKeys = new ZonalKeys();
        }
        return z;
    }

    public void dump(Appendable appendable) throws IOException {
        StringBuilder sb = new StringBuilder(4096);
        StringBuilder sbAppend = sb.append("Start Of Dump =>");
        String str = NEW_LINE;
        sbAppend.append(str);
        sb.append("*** Timezone-ID:").append(str);
        sb.append(">>> ").append(getID().canonical()).append(str);
        if (isFixed()) {
            sb.append("*** Fixed offset:").append(str).append(">>> ");
            sb.append(getHistory().getInitialOffset()).append(str);
        } else {
            sb.append("*** Strategy:").append(str);
            sb.append(">>> ").append(getStrategy()).append(str);
            TransitionHistory history = getHistory();
            sb.append("*** History:").append(str);
            if (history == null) {
                sb.append(">>> Not public!").append(str);
            } else {
                history.dump(sb);
            }
        }
        sb.append("<= End Of Dump").append(str);
        appendable.append(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Timezone getDefaultTZ() {
        String id = TimeZone.getDefault().getID();
        Timezone tz = getTZ(null, id, false);
        return tz == null ? new PlatformTimezone(new NamedID(id)) : tz;
    }

    private static Timezone getTZ(TZID tzid, boolean z) {
        if (tzid instanceof ZonalOffset) {
            return ((ZonalOffset) tzid).getModel();
        }
        return getTZ(tzid, tzid.canonical(), z);
    }

    private static Timezone getTZ(TZID tzid, String str, boolean z) {
        Timezone historizedTimezone;
        String strSubstring;
        ConcurrentMap<String, NamedReference> concurrentMap = CACHE;
        NamedReference namedReference = concurrentMap.get(str);
        if (namedReference != null) {
            historizedTimezone = namedReference.get();
            if (historizedTimezone == null) {
                concurrentMap.remove(namedReference.tzid);
            }
        } else {
            historizedTimezone = null;
        }
        if (historizedTimezone != null) {
            return historizedTimezone;
        }
        String strSubstring2 = "";
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                strSubstring = str;
                break;
            }
            if (str.charAt(i) == '~') {
                strSubstring2 = str.substring(0, i);
                strSubstring = str.substring(i + 1);
                break;
            }
            i++;
        }
        if (strSubstring.isEmpty()) {
            if (z) {
                throw new IllegalArgumentException("Timezone key is empty.");
            }
            return null;
        }
        ZoneModelProvider zoneModelProvider = DEFAULT_PROVIDER;
        boolean z2 = strSubstring2.isEmpty() || strSubstring2.equals(NAME_DEFAULT);
        if (!z2 && (zoneModelProvider = PROVIDERS.get(strSubstring2)) == null) {
            if (z) {
                throw new IllegalArgumentException((strSubstring2.equals(NAME_TZDB) ? "TZDB provider not available: " : "Timezone model provider not registered: ") + str);
            }
            return null;
        }
        if (tzid == null) {
            if (z2) {
                tzid = resolve(strSubstring);
                if (tzid instanceof ZonalOffset) {
                    return ((ZonalOffset) tzid).getModel();
                }
            } else {
                tzid = new NamedID(str);
            }
        }
        if (zoneModelProvider == PLATFORM_PROVIDER) {
            PlatformTimezone platformTimezone = new PlatformTimezone(tzid, strSubstring);
            if (!platformTimezone.isGMT() || strSubstring.equals(TimeZones.GMT_ID) || strSubstring.startsWith("UT") || strSubstring.equals("Z")) {
                historizedTimezone = platformTimezone;
            }
        } else {
            TransitionHistory transitionHistoryLoad = zoneModelProvider.load(strSubstring);
            if (transitionHistoryLoad == null) {
                historizedTimezone = getZoneByAlias(zoneModelProvider, tzid, strSubstring);
            } else {
                historizedTimezone = new HistorizedTimezone(tzid, transitionHistoryLoad);
            }
        }
        if (historizedTimezone == null) {
            if (!z) {
                return null;
            }
            if (TimeZone.getDefault().getID().equals(str)) {
                return new PlatformTimezone(new NamedID(str));
            }
            throw new IllegalArgumentException("Unknown timezone: " + str);
        }
        if (!cacheActive) {
            return historizedTimezone;
        }
        NamedReference namedReferencePutIfAbsent = CACHE.putIfAbsent(str, new NamedReference(historizedTimezone, QUEUE));
        if (namedReferencePutIfAbsent == null) {
            synchronized (Timezone.class) {
                LAST_USED.addFirst(historizedTimezone);
                while (true) {
                    LinkedList<Timezone> linkedList = LAST_USED;
                    if (linkedList.size() >= softLimit) {
                        linkedList.removeLast();
                    }
                }
            }
            return historizedTimezone;
        }
        Timezone timezone = namedReferencePutIfAbsent.get();
        return timezone != null ? timezone : historizedTimezone;
    }

    private static Timezone getZoneByAlias(ZoneModelProvider zoneModelProvider, TZID tzid, String str) {
        Map<String, String> aliases = zoneModelProvider.getAliases();
        String str2 = str;
        TransitionHistory transitionHistoryLoad = null;
        while (transitionHistoryLoad == null) {
            str2 = aliases.get(str2);
            if (str2 == null) {
                break;
            }
            transitionHistoryLoad = zoneModelProvider.load(str2);
        }
        if (transitionHistoryLoad == null) {
            String fallback = zoneModelProvider.getFallback();
            if (fallback.isEmpty()) {
                return null;
            }
            if (fallback.equals(zoneModelProvider.getName())) {
                throw new IllegalArgumentException("Circular zone model provider fallback: " + zoneModelProvider.getName());
            }
            return new FallbackTimezone(tzid, of(fallback + "~" + str));
        }
        return new HistorizedTimezone(tzid, transitionHistoryLoad);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TZID resolve(String str) {
        TZID tzid = PREDEFINED.get(str);
        if (tzid != null) {
            return tzid;
        }
        if (str.startsWith(TimeZones.GMT_ID)) {
            str = "UTC" + str.substring(3);
        }
        ZonalOffset zonalOffset = ZonalOffset.parse(str, false);
        return zonalOffset == null ? new NamedID(str) : zonalOffset;
    }

    private static void fillEtcetera(Map<String, TZID> map) {
        map.put("Etc/GMT", ZonalOffset.UTC);
        map.put("Etc/Greenwich", ZonalOffset.UTC);
        map.put("Etc/Universal", ZonalOffset.UTC);
        map.put("Etc/Zulu", ZonalOffset.UTC);
        map.put("Etc/GMT+0", ZonalOffset.UTC);
        map.put("Etc/GMT-0", ZonalOffset.UTC);
        map.put("Etc/GMT0", ZonalOffset.UTC);
        map.put("Etc/UTC", ZonalOffset.UTC);
        map.put("Etc/UCT", ZonalOffset.UTC);
        map.put("Etc/GMT-14", ZonalOffset.ofTotalSeconds(50400));
        map.put("Etc/GMT-13", ZonalOffset.ofTotalSeconds(46800));
        map.put("Etc/GMT-12", ZonalOffset.ofTotalSeconds(43200));
        map.put("Etc/GMT-11", ZonalOffset.ofTotalSeconds(39600));
        map.put("Etc/GMT-10", ZonalOffset.ofTotalSeconds(36000));
        map.put("Etc/GMT-9", ZonalOffset.ofTotalSeconds(32400));
        map.put("Etc/GMT-8", ZonalOffset.ofTotalSeconds(28800));
        map.put("Etc/GMT-7", ZonalOffset.ofTotalSeconds(25200));
        map.put("Etc/GMT-6", ZonalOffset.ofTotalSeconds(21600));
        map.put("Etc/GMT-5", ZonalOffset.ofTotalSeconds(18000));
        map.put("Etc/GMT-4", ZonalOffset.ofTotalSeconds(14400));
        map.put("Etc/GMT-3", ZonalOffset.ofTotalSeconds(10800));
        map.put("Etc/GMT-2", ZonalOffset.ofTotalSeconds(7200));
        map.put("Etc/GMT-1", ZonalOffset.ofTotalSeconds(NikonType2MakernoteDirectory.TAG_NIKON_SCAN));
        map.put("Etc/GMT+1", ZonalOffset.ofTotalSeconds(-3600));
        map.put("Etc/GMT+2", ZonalOffset.ofTotalSeconds(-7200));
        map.put("Etc/GMT+3", ZonalOffset.ofTotalSeconds(-10800));
        map.put("Etc/GMT+4", ZonalOffset.ofTotalSeconds(-14400));
        map.put("Etc/GMT+5", ZonalOffset.ofTotalSeconds(-18000));
        map.put("Etc/GMT+6", ZonalOffset.ofTotalSeconds(-21600));
        map.put("Etc/GMT+7", ZonalOffset.ofTotalSeconds(-25200));
        map.put("Etc/GMT+8", ZonalOffset.ofTotalSeconds(-28800));
        map.put("Etc/GMT+9", ZonalOffset.ofTotalSeconds(-32400));
        map.put("Etc/GMT+10", ZonalOffset.ofTotalSeconds(-36000));
        map.put("Etc/GMT+11", ZonalOffset.ofTotalSeconds(-39600));
        map.put("Etc/GMT+12", ZonalOffset.ofTotalSeconds(-43200));
    }

    private static List<Class<? extends TZID>> loadPredefined(ClassLoader classLoader, String... strArr) throws ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Class<?> cls = Class.forName("net.time4j.tz.olson." + str, true, classLoader);
            if (TZID.class.isAssignableFrom(cls)) {
                arrayList.add(cls);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static ZoneModelProvider getProvider(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Missing zone model provider.");
        }
        return str.equals(NAME_DEFAULT) ? DEFAULT_PROVIDER : PROVIDERS.get(str);
    }

    private static ZoneModelProvider compareTZDB(ZoneModelProvider zoneModelProvider, ZoneModelProvider zoneModelProvider2) {
        String version = zoneModelProvider.getVersion();
        if (!version.isEmpty()) {
            String str = REPOSITORY_VERSION;
            if (version.equals(str)) {
                return zoneModelProvider;
            }
            if (str == null) {
                if (zoneModelProvider2 == null || version.compareTo(zoneModelProvider2.getVersion()) > 0) {
                    return zoneModelProvider;
                }
                if (version.compareTo(zoneModelProvider2.getVersion()) == 0 && !zoneModelProvider.getLocation().contains("{java.home}")) {
                    return zoneModelProvider;
                }
            }
        }
        return zoneModelProvider2;
    }

    public static class Cache {
        private Cache() {
        }

        public static void refresh() {
            synchronized (Timezone.class) {
                do {
                } while (Timezone.QUEUE.poll() != null);
                Timezone.LAST_USED.clear();
            }
            ZonalKeys unused = Timezone.zonalKeys = new ZonalKeys();
            Timezone.CACHE.clear();
            if (Timezone.ALLOW_SYSTEM_TZ_OVERRIDE) {
                Timezone unused2 = Timezone.currentSystemTZ = Timezone.getDefaultTZ();
            }
        }

        public static void setCacheActive(boolean z) {
            boolean unused = Timezone.cacheActive = z;
            if (z) {
                return;
            }
            Timezone.CACHE.clear();
        }

        public static void setMinimumCacheSize(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Negative timezone cache size: " + i);
            }
            while (true) {
                NamedReference namedReference = (NamedReference) Timezone.QUEUE.poll();
                if (namedReference == null) {
                    break;
                } else {
                    Timezone.CACHE.remove(namedReference.tzid);
                }
            }
            synchronized (Timezone.class) {
                int unused = Timezone.softLimit = i + 1;
                int size = Timezone.LAST_USED.size() - i;
                for (int i2 = 0; i2 < size; i2++) {
                    Timezone.LAST_USED.removeLast();
                }
            }
        }
    }

    private static class NamedReference extends SoftReference<Timezone> {
        private final String tzid;

        NamedReference(Timezone timezone, ReferenceQueue<Timezone> referenceQueue) {
            super(timezone, referenceQueue);
            this.tzid = timezone.getID().canonical();
        }
    }

    private static class ZonalKeys {
        private final List<TZID> availables;
        private final List<TZID> availablesAndAliases;

        ZonalKeys() {
            ArrayList arrayList = new ArrayList(1024);
            ArrayList arrayList2 = new ArrayList(1024);
            arrayList.add(ZonalOffset.UTC);
            Iterator it = Timezone.PROVIDERS.entrySet().iterator();
            while (it.hasNext()) {
                ZoneModelProvider zoneModelProvider = (ZoneModelProvider) ((Map.Entry) it.next()).getValue();
                if (zoneModelProvider != Timezone.PLATFORM_PROVIDER || Timezone.DEFAULT_PROVIDER == Timezone.PLATFORM_PROVIDER) {
                    Iterator<String> it2 = zoneModelProvider.getAvailableIDs().iterator();
                    while (it2.hasNext()) {
                        TZID tzidResolve = Timezone.resolve(it2.next());
                        if (!arrayList.contains(tzidResolve)) {
                            arrayList.add(tzidResolve);
                        }
                    }
                    arrayList2.addAll(arrayList);
                    Iterator<String> it3 = zoneModelProvider.getAliases().keySet().iterator();
                    while (it3.hasNext()) {
                        TZID tzidResolve2 = Timezone.resolve(it3.next());
                        if (!arrayList2.contains(tzidResolve2)) {
                            arrayList2.add(tzidResolve2);
                        }
                    }
                }
            }
            Collections.sort(arrayList, Timezone.ID_COMPARATOR);
            Collections.sort(arrayList2, Timezone.ID_COMPARATOR);
            this.availables = Collections.unmodifiableList(arrayList);
            this.availablesAndAliases = Collections.unmodifiableList(arrayList2);
        }
    }

    private static class PlatformZoneProvider implements ZoneModelProvider, ZoneNameProvider {
        @Override // net.time4j.tz.ZoneModelProvider
        public String getFallback() {
            return "";
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public String getLocation() {
            return "";
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public String getName() {
            return Timezone.NAME_JUT;
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public ZoneNameProvider getSpecificZoneNameRepository() {
            return this;
        }

        @Override // net.time4j.tz.ZoneNameProvider
        public String getStdFormatPattern(boolean z, Locale locale) {
            return z ? TimeZones.GMT_ID : "GMT±hh:mm";
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public TransitionHistory load(String str) {
            return null;
        }

        private PlatformZoneProvider() {
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public Set<String> getAvailableIDs() {
            HashSet hashSet = new HashSet();
            hashSet.addAll(Arrays.asList(TimeZone.getAvailableIDs()));
            return hashSet;
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public Map<String, String> getAliases() {
            return Collections.emptyMap();
        }

        @Override // net.time4j.tz.ZoneModelProvider
        public String getVersion() {
            return TimeUtils.getTimeZoneDatabaseVersion();
        }

        @Override // net.time4j.tz.ZoneNameProvider
        public Set<String> getPreferredIDs(Locale locale, boolean z) {
            return Collections.emptySet();
        }

        @Override // net.time4j.tz.ZoneNameProvider
        public String getDisplayName(String str, NameStyle nameStyle, Locale locale) {
            if (locale == null) {
                throw new NullPointerException("Missing locale.");
            }
            if (str.isEmpty()) {
                return "";
            }
            TimeZone timeZoneFindZone = PlatformTimezone.findZone(str);
            if (timeZoneFindZone.getID().equals(str)) {
                return timeZoneFindZone.getDisplayName(nameStyle.isDaylightSaving(), !nameStyle.isAbbreviation() ? 1 : 0, locale);
            }
            return "";
        }
    }
}
