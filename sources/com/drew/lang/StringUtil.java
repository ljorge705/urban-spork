package com.drew.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public final class StringUtil {
    public static String join(Iterable<? extends CharSequence> iterable, String str) {
        int length = str.length();
        Iterator<? extends CharSequence> it = iterable.iterator();
        StringBuilder sb = new StringBuilder(it.hasNext() ? it.next().length() + length : 0);
        Iterator<? extends CharSequence> it2 = iterable.iterator();
        if (it2.hasNext()) {
            sb.append(it2.next());
            while (it2.hasNext()) {
                sb.append(str);
                sb.append(it2.next());
            }
        }
        return sb.toString();
    }

    public static <T extends CharSequence> String join(T[] tArr, String str) {
        int length = str.length();
        int length2 = 0;
        for (T t : tArr) {
            length2 += t.length() + length;
        }
        StringBuilder sb = new StringBuilder(length2);
        boolean z = true;
        for (T t2 : tArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append((CharSequence) t2);
        }
        return sb.toString();
    }

    public static String fromStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            } else {
                return sb.toString();
            }
        }
    }

    public static int compare(String str, String str2) {
        boolean z = str == null;
        boolean z2 = str2 == null;
        if (z && z2) {
            return 0;
        }
        if (z) {
            return -1;
        }
        if (z2) {
            return 1;
        }
        return str.compareTo(str2);
    }

    public static String urlEncode(String str) {
        return str.replace(StringUtils.SPACE, "%20");
    }
}
