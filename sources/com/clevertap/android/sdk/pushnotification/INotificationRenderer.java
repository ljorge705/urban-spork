package com.clevertap.android.sdk.pushnotification;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.CleverTapInstanceConfig;

/* loaded from: classes5.dex */
public interface INotificationRenderer {
    String getActionButtonIconKey();

    Object getCollapseKey(Bundle bundle);

    String getMessage(Bundle bundle);

    String getTitle(Bundle bundle, Context context);

    NotificationCompat.Builder renderNotification(Bundle bundle, Context context, NotificationCompat.Builder builder, CleverTapInstanceConfig cleverTapInstanceConfig, int i);

    void setSmallIcon(int i, Context context);

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f1 A[Catch: all -> 0x0196, TryCatch #2 {all -> 0x0196, blocks: (B:20:0x0042, B:22:0x006b, B:25:0x0073, B:32:0x00a5, B:38:0x00b2, B:41:0x00be, B:43:0x00c4, B:46:0x00cf, B:52:0x00dc, B:55:0x00e4, B:61:0x00f1, B:63:0x010c, B:69:0x0133, B:64:0x0110, B:66:0x0116, B:67:0x0125, B:30:0x0089, B:27:0x0079), top: B:93:0x0042, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0110 A[Catch: all -> 0x0196, TryCatch #2 {all -> 0x0196, blocks: (B:20:0x0042, B:22:0x006b, B:25:0x0073, B:32:0x00a5, B:38:0x00b2, B:41:0x00be, B:43:0x00c4, B:46:0x00cf, B:52:0x00dc, B:55:0x00e4, B:61:0x00f1, B:63:0x010c, B:69:0x0133, B:64:0x0110, B:66:0x0116, B:67:0x0125, B:30:0x0089, B:27:0x0079), top: B:93:0x0042, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0133 A[Catch: all -> 0x0196, TRY_LEAVE, TryCatch #2 {all -> 0x0196, blocks: (B:20:0x0042, B:22:0x006b, B:25:0x0073, B:32:0x00a5, B:38:0x00b2, B:41:0x00be, B:43:0x00c4, B:46:0x00cf, B:52:0x00dc, B:55:0x00e4, B:61:0x00f1, B:63:0x010c, B:69:0x0133, B:64:0x0110, B:66:0x0116, B:67:0x0125, B:30:0x0089, B:27:0x0079), top: B:93:0x0042, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0169 A[Catch: all -> 0x0194, TryCatch #1 {all -> 0x0194, blocks: (B:71:0x0151, B:73:0x015c, B:75:0x0169, B:81:0x0187, B:76:0x016e, B:78:0x0174, B:80:0x0183, B:82:0x018b), top: B:91:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x016e A[Catch: all -> 0x0194, TryCatch #1 {all -> 0x0194, blocks: (B:71:0x0151, B:73:0x015c, B:75:0x0169, B:81:0x0187, B:76:0x016e, B:78:0x0174, B:80:0x0183, B:82:0x018b), top: B:91:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    default androidx.core.app.NotificationCompat.Builder setActionButtons(android.content.Context r18, android.os.Bundle r19, int r20, androidx.core.app.NotificationCompat.Builder r21, org.json.JSONArray r22) throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.pushnotification.INotificationRenderer.setActionButtons(android.content.Context, android.os.Bundle, int, androidx.core.app.NotificationCompat$Builder, org.json.JSONArray):androidx.core.app.NotificationCompat$Builder");
    }
}
