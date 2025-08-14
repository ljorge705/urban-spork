package com.uxcam.internals;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ch implements cg {
    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    @Override // com.uxcam.internals.cg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.lang.String r5) throws java.security.NoSuchAlgorithmException {
        /*
            r4 = this;
            if (r5 == 0) goto L52
            boolean r0 = kotlin.text.StringsKt.isBlank(r5)
            if (r0 == 0) goto L9
            goto L52
        L9:
            r0 = 0
            java.lang.String r1 = "SHA-256"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            if (r5 == 0) goto L29
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.security.NoSuchAlgorithmException -> L2f
            java.lang.String r3 = "UTF_8"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            byte[] r2 = r5.getBytes(r2)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            java.lang.String r3 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            if (r2 == 0) goto L29
            byte[] r1 = r1.digest(r2)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            goto L2a
        L29:
            r1 = 0
        L2a:
            java.lang.String r5 = a(r1)     // Catch: java.security.NoSuchAlgorithmException -> L2f
            goto L3f
        L2f:
            r1 = move-exception
            r1.printStackTrace()
            if (r5 == 0) goto L3a
            int r5 = r5.hashCode()
            goto L3b
        L3a:
            r5 = r0
        L3b:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L3f:
            int r1 = r5.length()
            r2 = 6
            int r1 = java.lang.Math.min(r1, r2)
            java.lang.String r5 = r5.substring(r0, r1)
            java.lang.String r0 = "this as java.lang.String…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            goto L54
        L52:
            java.lang.String r5 = ""
        L54:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.ch.a(java.lang.String):java.lang.String");
    }

    public static String a(byte[] bArr) {
        Integer numValueOf = bArr != null ? Integer.valueOf(bArr.length) : null;
        Intrinsics.checkNotNull(numValueOf);
        StringBuilder sb = new StringBuilder(numValueOf.intValue() * 2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "hexString.toString()");
        return string;
    }
}
