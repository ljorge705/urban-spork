package net.time4j.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.time4j.base.ResourceLoader;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public final class PropertyBundle {
    private static final ConcurrentMap<CacheKey, BundleReference> CACHE = new ConcurrentHashMap(32);
    private static final ReferenceQueue<Object> REFERENCE_QUEUE = new ReferenceQueue<>();
    private final String baseName;
    private final Locale bundleLocale;
    private final Map<String, String> key2values;
    private final PropertyBundle parent;

    public Locale getLocale() {
        return this.bundleLocale;
    }

    private PropertyBundle(UTF8ResourceReader uTF8ResourceReader, String str, Locale locale) throws IOException {
        int i;
        this.parent = null;
        this.baseName = str;
        this.bundleLocale = locale;
        HashMap map = new HashMap();
        while (true) {
            String line = uTF8ResourceReader.readLine();
            if (line != null) {
                String strTrim = line.trim();
                if (!strTrim.isEmpty() && strTrim.charAt(0) != '#') {
                    int length = strTrim.length();
                    int i2 = 0;
                    while (true) {
                        if (i2 < length) {
                            if (strTrim.charAt(i2) == '=' && (i = i2 + 1) < length) {
                                map.put(strTrim.substring(0, i2), strTrim.substring(i));
                                break;
                            }
                            i2++;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                this.key2values = Collections.unmodifiableMap(map);
                return;
            }
        }
    }

    private PropertyBundle(PropertyBundle propertyBundle, PropertyBundle propertyBundle2) {
        this.parent = propertyBundle2;
        this.baseName = propertyBundle.baseName;
        this.bundleLocale = propertyBundle.bundleLocale;
        this.key2values = propertyBundle.key2values;
    }

    public static PropertyBundle load(String str, Locale locale) throws Throwable {
        PropertyBundle propertyBundle;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Base name must not be empty.");
        }
        if (locale == null) {
            throw new NullPointerException("Missing locale.");
        }
        CacheKey cacheKey = new CacheKey(str, locale);
        BundleReference bundleReference = CACHE.get(cacheKey);
        if (bundleReference != null && (propertyBundle = bundleReference.get()) != null) {
            return propertyBundle;
        }
        while (true) {
            Reference<? extends Object> referencePoll = REFERENCE_QUEUE.poll();
            if (referencePoll == null) {
                break;
            }
            CACHE.remove(((BundleReference) referencePoll).cacheKey);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Locale> it = getCandidateLocales(locale).iterator();
        while (it.hasNext()) {
            try {
                PropertyBundle propertyBundleNewBundle = newBundle(str, it.next());
                if (propertyBundleNewBundle != null) {
                    arrayList.add(propertyBundleNewBundle);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (arrayList.isEmpty()) {
            throw new MissingResourceException("Cannot find resource bundle for: " + toResourceName(str, locale), PropertyBundle.class.getName(), "");
        }
        for (int size = arrayList.size() - 1; size >= 1; size--) {
            int i = size - 1;
            arrayList.set(i, ((PropertyBundle) arrayList.get(i)).withParent((PropertyBundle) arrayList.get(size)));
        }
        PropertyBundle propertyBundle2 = (PropertyBundle) arrayList.get(0);
        CACHE.putIfAbsent(cacheKey, new BundleReference(propertyBundle2, cacheKey));
        return propertyBundle2;
    }

    public String getString(String str) {
        if (str == null) {
            throw new NullPointerException("Missing resource key.");
        }
        PropertyBundle propertyBundle = this;
        do {
            String str2 = propertyBundle.key2values.get(str);
            if (str2 != null) {
                return str2;
            }
            propertyBundle = propertyBundle.parent;
        } while (propertyBundle != null);
        throw new MissingResourceException("Cannot find property resource for: " + toResourceName(this.baseName, this.bundleLocale) + "=>" + str, PropertyBundle.class.getName(), str);
    }

    public boolean containsKey(String str) {
        if (str == null) {
            throw new NullPointerException("Missing resource key.");
        }
        PropertyBundle propertyBundle = this;
        while (propertyBundle.key2values.get(str) == null) {
            propertyBundle = propertyBundle.parent;
            if (propertyBundle == null) {
                return false;
            }
        }
        return true;
    }

    public Set<String> keySet() {
        HashSet hashSet = new HashSet(this.key2values.keySet());
        PropertyBundle propertyBundle = this;
        while (true) {
            propertyBundle = propertyBundle.parent;
            if (propertyBundle != null) {
                hashSet.addAll(propertyBundle.key2values.keySet());
            } else {
                return Collections.unmodifiableSet(hashSet);
            }
        }
    }

    public static void clearCache() {
        while (REFERENCE_QUEUE.poll() != null) {
        }
        CACHE.clear();
    }

    public Set<String> getInternalKeys() {
        return this.key2values.keySet();
    }

    public static List<Locale> getCandidateLocales(Locale locale) {
        String alias = LanguageMatch.getAlias(locale);
        String country = locale.getCountry();
        String variant = locale.getVariant();
        LinkedList linkedList = new LinkedList();
        if (!variant.isEmpty()) {
            linkedList.add(new Locale(alias, country, variant));
        }
        if (!country.isEmpty()) {
            linkedList.add(new Locale(alias, country, ""));
        }
        if (!alias.isEmpty()) {
            linkedList.add(new Locale(alias, "", ""));
            if (alias.equals("nn")) {
                linkedList.add(new Locale("nb", "", ""));
            }
        }
        linkedList.add(Locale.ROOT);
        return linkedList;
    }

    private PropertyBundle withParent(PropertyBundle propertyBundle) {
        return propertyBundle == null ? this : new PropertyBundle(this, propertyBundle);
    }

    private static PropertyBundle newBundle(String str, Locale locale) throws Throwable {
        int iIndexOf = str.indexOf(47);
        String strSubstring = str.substring(0, iIndexOf);
        String resourceName = toResourceName(str.substring(iIndexOf + 1), locale);
        InputStream inputStreamLoad = ResourceLoader.getInstance().load(ResourceLoader.getInstance().locate(strSubstring, PropertyBundle.class, resourceName), true);
        PropertyBundle propertyBundle = null;
        UTF8ResourceReader uTF8ResourceReader = null;
        if (inputStreamLoad == null) {
            try {
                inputStreamLoad = ResourceLoader.getInstance().load(PropertyBundle.class, resourceName, true);
            } catch (IOException unused) {
                return null;
            }
        }
        if (inputStreamLoad != null) {
            try {
                UTF8ResourceReader uTF8ResourceReader2 = new UTF8ResourceReader(inputStreamLoad);
                try {
                    propertyBundle = new PropertyBundle(uTF8ResourceReader2, str, locale);
                    uTF8ResourceReader2.close();
                } catch (Throwable th) {
                    th = th;
                    uTF8ResourceReader = uTF8ResourceReader2;
                    if (uTF8ResourceReader != null) {
                        uTF8ResourceReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return propertyBundle;
    }

    private static String toResourceName(String str, Locale locale) {
        String alias = LanguageMatch.getAlias(locale);
        String country = locale.getCountry();
        String variant = locale.getVariant();
        StringBuilder sb = new StringBuilder(str.length() + 20);
        sb.append(str.replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/'));
        if (!alias.isEmpty()) {
            sb.append('_').append(alias);
            if (!variant.isEmpty()) {
                sb.append('_').append(country).append('_').append(variant);
            } else if (!country.isEmpty()) {
                sb.append('_').append(country);
            }
        }
        return sb.append(".properties").toString();
    }

    private static class CacheKey {
        private final String baseName;
        private final Locale locale;

        CacheKey(String str, Locale locale) {
            this.baseName = str;
            this.locale = locale;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) obj;
            return this.baseName.equals(cacheKey.baseName) && this.locale.equals(cacheKey.locale);
        }

        public int hashCode() {
            return (this.baseName.hashCode() << 3) ^ this.locale.hashCode();
        }

        public String toString() {
            return this.baseName + "/" + this.locale;
        }
    }

    private static class BundleReference extends SoftReference<PropertyBundle> {
        private CacheKey cacheKey;

        BundleReference(PropertyBundle propertyBundle, CacheKey cacheKey) {
            super(propertyBundle, PropertyBundle.REFERENCE_QUEUE);
            this.cacheKey = cacheKey;
        }
    }
}
