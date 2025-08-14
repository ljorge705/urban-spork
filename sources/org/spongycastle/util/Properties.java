package org.spongycastle.util;

import com.facebook.hermes.intl.Constants;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/* loaded from: classes7.dex */
public class Properties {
    private static final ThreadLocal threadProperties = new ThreadLocal();

    private Properties() {
    }

    public static boolean isOverrideSet(String str) {
        try {
            String strFetchProperty = fetchProperty(str);
            if (strFetchProperty != null) {
                return "true".equals(Strings.toLowerCase(strFetchProperty));
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }

    public static boolean setThreadOverride(String str, boolean z) {
        boolean zIsOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = threadProperties;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, z ? "true" : Constants.CASEFIRST_FALSE);
        threadLocal.set(map);
        return zIsOverrideSet;
    }

    public static boolean removeThreadOverride(String str) {
        boolean zIsOverrideSet = isOverrideSet(str);
        ThreadLocal threadLocal = threadProperties;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            return false;
        }
        map.remove(str);
        if (map.isEmpty()) {
            threadLocal.remove();
        } else {
            threadLocal.set(map);
        }
        return zIsOverrideSet;
    }

    public static BigInteger asBigInteger(String str) {
        String strFetchProperty = fetchProperty(str);
        if (strFetchProperty != null) {
            return new BigInteger(strFetchProperty);
        }
        return null;
    }

    public static Set<String> asKeySet(String str) {
        HashSet hashSet = new HashSet();
        String strFetchProperty = fetchProperty(str);
        if (strFetchProperty != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(strFetchProperty, com.clevertap.android.sdk.Constants.SEPARATOR_COMMA);
            while (stringTokenizer.hasMoreElements()) {
                hashSet.add(Strings.toLowerCase(stringTokenizer.nextToken()).trim());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static String fetchProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.spongycastle.util.Properties.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                Map map = (Map) Properties.threadProperties.get();
                if (map != null) {
                    return map.get(str);
                }
                return System.getProperty(str);
            }
        });
    }
}
