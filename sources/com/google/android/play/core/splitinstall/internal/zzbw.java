package com.google.android.play.core.splitinstall.internal;

import java.io.File;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzbw {
    public static String zza(File file) {
        if (!file.getName().endsWith(".apk")) {
            throw new IllegalArgumentException("Non-apk found in splits directory.");
        }
        String strReplaceFirst = file.getName().replaceFirst("(_\\d+)?\\.apk", "");
        return (strReplaceFirst.equals("base-master") || strReplaceFirst.equals("base-main")) ? "" : strReplaceFirst.startsWith("base-") ? strReplaceFirst.replace("base-", "config.") : strReplaceFirst.replace("-", ".config.").replace(".config.master", "").replace(".config.main", "");
    }
}
