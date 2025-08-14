package io.sentry.react;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes6.dex */
public final class RNSentryBreadcrumb {
    private RNSentryBreadcrumb() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String getCurrentScreenFrom(ReadableMap readableMap) {
        String string = readableMap.hasKey("category") ? readableMap.getString("category") : null;
        if (string == null || !NotificationCompat.CATEGORY_NAVIGATION.equals(string)) {
            return null;
        }
        ReadableMap map = readableMap.hasKey("data") ? readableMap.getMap("data") : null;
        if (map == null) {
            return null;
        }
        try {
            if (map.hasKey("to")) {
                return map.getString("to");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static io.sentry.Breadcrumb fromMap(com.facebook.react.bridge.ReadableMap r6) {
        /*
            io.sentry.Breadcrumb r0 = new io.sentry.Breadcrumb
            r0.<init>()
            java.lang.String r1 = "message"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L14
            java.lang.String r1 = r6.getString(r1)
            r0.setMessage(r1)
        L14:
            java.lang.String r1 = "type"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L23
            java.lang.String r1 = r6.getString(r1)
            r0.setType(r1)
        L23:
            java.lang.String r1 = "category"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L32
            java.lang.String r1 = r6.getString(r1)
            r0.setCategory(r1)
        L32:
            java.lang.String r1 = "origin"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L42
            java.lang.String r1 = r6.getString(r1)
            r0.setOrigin(r1)
            goto L47
        L42:
            java.lang.String r1 = "react-native"
            r0.setOrigin(r1)
        L47:
            java.lang.String r1 = "level"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto Lb6
            java.lang.String r1 = r6.getString(r1)
            int r2 = r1.hashCode()
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r2) {
                case 3237038: goto L86;
                case 95458899: goto L7c;
                case 96784904: goto L72;
                case 97203460: goto L68;
                case 1124446108: goto L5e;
                default: goto L5d;
            }
        L5d:
            goto L90
        L5e:
            java.lang.String r2 = "warning"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L90
            r1 = r5
            goto L91
        L68:
            java.lang.String r2 = "fatal"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L90
            r1 = 0
            goto L91
        L72:
            java.lang.String r2 = "error"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L90
            r1 = r3
            goto L91
        L7c:
            java.lang.String r2 = "debug"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L90
            r1 = r4
            goto L91
        L86:
            java.lang.String r2 = "info"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L90
            r1 = 4
            goto L91
        L90:
            r1 = -1
        L91:
            if (r1 == 0) goto Lb1
            if (r1 == r5) goto Lab
            if (r1 == r4) goto La5
            if (r1 == r3) goto L9f
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.INFO
            r0.setLevel(r1)
            goto Lb6
        L9f:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.ERROR
            r0.setLevel(r1)
            goto Lb6
        La5:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.DEBUG
            r0.setLevel(r1)
            goto Lb6
        Lab:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.WARNING
            r0.setLevel(r1)
            goto Lb6
        Lb1:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.FATAL
            r0.setLevel(r1)
        Lb6:
            java.lang.String r1 = "data"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto Lee
            com.facebook.react.bridge.ReadableMap r6 = r6.getMap(r1)
            java.util.HashMap r6 = r6.toHashMap()
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
        Lce:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto Lee
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            if (r2 == 0) goto Lce
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            r0.setData(r2, r1)
            goto Lce
        Lee:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.react.RNSentryBreadcrumb.fromMap(com.facebook.react.bridge.ReadableMap):io.sentry.Breadcrumb");
    }
}
