package net.time4j.tz.spi;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.time4j.base.ResourceLoader;
import net.time4j.tz.NameStyle;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZoneModelProvider;
import net.time4j.tz.ZoneNameProvider;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
public class WinZoneProviderSPI implements ZoneModelProvider, ZoneNameProvider {
    public static final Map<String, Map<String, Set<TZID>>> NAME_BASED_MAP;
    private static final Map<String, Set<String>> PREFERRED_KEYS;
    private static final Map<String, Map<String, String>> REPOSITORY;
    private static final String VKEY = "VERSION";
    public static final String WIN_NAME_VERSION;

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T cast(Object obj) {
        return obj;
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getFallback() {
        return "DEFAULT";
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getLocation() {
        return "";
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public String getName() {
        return "WINDOWS";
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
    public String getVersion() {
        return "";
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public TransitionHistory load(String str) {
        return null;
    }

    static {
        Map<String, Map<String, String>> mapLoadData = loadData();
        WIN_NAME_VERSION = mapLoadData.get(VKEY).keySet().iterator().next();
        mapLoadData.remove(VKEY);
        REPOSITORY = Collections.unmodifiableMap(mapLoadData);
        PREFERRED_KEYS = prepareSmartMode();
        NAME_BASED_MAP = prepareResolvers();
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public Set<String> getAvailableIDs() {
        HashSet hashSet = new HashSet();
        Iterator<TZID> it = Timezone.getAvailableIDs("DEFAULT").iterator();
        while (it.hasNext()) {
            hashSet.add("WINDOWS~" + it.next().canonical());
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // net.time4j.tz.ZoneModelProvider
    public Map<String, String> getAliases() {
        return Collections.emptyMap();
    }

    @Override // net.time4j.tz.ZoneNameProvider
    public Set<String> getPreferredIDs(Locale locale, boolean z) {
        return getPreferredIDs(locale.getCountry(), z);
    }

    @Override // net.time4j.tz.ZoneNameProvider
    public String getDisplayName(String str, NameStyle nameStyle, Locale locale) {
        String str2;
        return (str.isEmpty() || (str2 = idsToNames(locale.getCountry()).get(new StringBuilder("WINDOWS~").append(str).toString())) == null) ? "" : str2;
    }

    private static Map<String, String> idsToNames(String str) {
        Map<String, String> map = REPOSITORY.get(str);
        if (map == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(map);
    }

    private static Set<String> getPreferredIDs(String str, boolean z) {
        if (z) {
            return getPreferences(str);
        }
        return idsToNames(str).keySet();
    }

    private static Set<String> getPreferences(String str) {
        Set<String> set = PREFERRED_KEYS.get(str);
        if (set == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(set);
    }

    private static Map<String, Map<String, String>> loadData() throws Throwable {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                InputStream inputStreamLoad = ResourceLoader.getInstance().load(ResourceLoader.getInstance().locate("olson", WinZoneProviderSPI.class, "data/winzone.ser"), true);
                if (inputStreamLoad == null) {
                    inputStreamLoad = WinZoneProviderSPI.class.getClassLoader().getResourceAsStream("data/winzone.ser");
                }
                objectInputStream = new ObjectInputStream(inputStreamLoad);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (ClassNotFoundException e2) {
            e = e2;
        }
        try {
            String utf = objectInputStream.readUTF();
            HashMap map = new HashMap((Map) cast(objectInputStream.readObject()));
            map.put(VKEY, Collections.singletonMap(utf, utf));
            try {
                objectInputStream.close();
            } catch (IOException unused) {
            }
            return map;
        } catch (IOException e3) {
            e = e3;
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e4) {
            e = e4;
            throw new IllegalStateException(e);
        } catch (Throwable th2) {
            objectInputStream2 = objectInputStream;
            th = th2;
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    private static Map<String, Set<String>> prepareSmartMode() {
        HashMap map = new HashMap();
        for (String str : REPOSITORY.keySet()) {
            Map<String, String> mapIdsToNames = idsToNames(str);
            Set setKeySet = mapIdsToNames.keySet();
            if (setKeySet.size() >= 2) {
                setKeySet = new HashSet();
                for (String str2 : new HashSet(mapIdsToNames.values())) {
                    for (Map.Entry<String, String> entry : getFallbackSet()) {
                        if (entry.getValue().equals(str2)) {
                            setKeySet.add(entry.getKey());
                        }
                    }
                }
            }
            map.put(str, setKeySet);
        }
        return Collections.unmodifiableMap(map);
    }

    private static Set<Map.Entry<String, String>> getFallbackSet() {
        return idsToNames("001").entrySet();
    }

    private static Map<String, Map<String, Set<TZID>>> prepareResolvers() {
        HashMap map = new HashMap();
        for (String str : REPOSITORY.keySet()) {
            for (Map.Entry<String, String> entry : REPOSITORY.get(str).entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Map map2 = (Map) map.get(value);
                if (map2 == null) {
                    map2 = new HashMap();
                    map.put(value, map2);
                }
                Set hashSet = (Set) map2.get(str);
                if (hashSet == null) {
                    hashSet = new HashSet();
                    map2.put(str, hashSet);
                }
                hashSet.add(new WinZoneID(key));
            }
        }
        return Collections.unmodifiableMap(map);
    }
}
