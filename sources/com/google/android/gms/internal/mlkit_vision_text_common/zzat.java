package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzat {
    static int zza(int i, String str) {
        if (i >= 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(str.length() + 40);
        sb.append(str);
        sb.append(" cannot be negative but was: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    static void zzb(Object obj, Object obj2) {
        if (obj == null) {
            String strValueOf = String.valueOf(obj2);
            String.valueOf(strValueOf).length();
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(strValueOf)));
        }
        if (obj2 != null) {
            return;
        }
        String string = obj.toString();
        StringBuilder sb = new StringBuilder(string.length() + 26);
        sb.append("null value in entry: ");
        sb.append(string);
        sb.append("=null");
        throw new NullPointerException(sb.toString());
    }
}
