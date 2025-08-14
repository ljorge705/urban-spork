package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class dk {

    /* renamed from: a, reason: collision with root package name */
    public final eh f122a;

    public dk(eh ehVar) {
        this.f122a = ehVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x000b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONArray a(long r10) throws org.json.JSONException, java.io.IOException {
        /*
            r9 = this;
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            com.uxcam.internals.eh r1 = r9.f122a
            java.io.BufferedReader r1 = r1.b()
        Lb:
            java.lang.String r2 = r1.readLine()
            if (r2 == 0) goto L71
            java.lang.String r2 = r2.trim()
            java.lang.String r3 = "\\s+"
            r4 = 5
            java.lang.String[] r2 = r2.split(r3, r4)
            r3 = 0
            r4 = r2[r3]     // Catch: java.lang.Exception -> L6a
            double r4 = java.lang.Double.parseDouble(r4)     // Catch: java.lang.Exception -> L6a
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r4 = r4 * r6
            long r4 = java.lang.Math.round(r4)     // Catch: java.lang.Exception -> L6a
            long r4 = r4 - r10
            double r4 = (double) r4     // Catch: java.lang.Exception -> L6a
            double r4 = r4 / r6
            float r4 = (float) r4     // Catch: java.lang.Exception -> L6a
            r5 = 0
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 >= 0) goto L37
            goto L6a
        L37:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Exception -> L6a
            r5.<init>()     // Catch: java.lang.Exception -> L6a
            java.lang.String r6 = "timeLine"
            double r7 = (double) r4     // Catch: java.lang.Exception -> L6a
            r5.put(r6, r7)     // Catch: java.lang.Exception -> L6a
            java.lang.String r4 = "level"
            r6 = 3
            r6 = r2[r6]     // Catch: java.lang.Exception -> L6a
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch: java.lang.Exception -> L6a
            java.lang.String r6 = r6.toLowerCase(r7)     // Catch: java.lang.Exception -> L6a
            r5.put(r4, r6)     // Catch: java.lang.Exception -> L6a
            r4 = 4
            r2 = r2[r4]     // Catch: java.lang.Exception -> L6a
            java.lang.String r4 = ":"
            r6 = 2
            java.lang.String[] r2 = r2.split(r4, r6)     // Catch: java.lang.Exception -> L6a
            java.lang.String r4 = "tag"
            r3 = r2[r3]     // Catch: java.lang.Exception -> L6a
            r5.put(r4, r3)     // Catch: java.lang.Exception -> L6a
            java.lang.String r3 = "message"
            r4 = 1
            r2 = r2[r4]     // Catch: java.lang.Exception -> L6a
            r5.put(r3, r2)     // Catch: java.lang.Exception -> L6a
            goto L6b
        L6a:
            r5 = 0
        L6b:
            if (r5 == 0) goto Lb
            r0.put(r5)
            goto Lb
        L71:
            r1.close()
            com.uxcam.internals.eh r10 = r9.f122a
            r10.a()
            r0.length()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.dk.a(long):org.json.JSONArray");
    }
}
