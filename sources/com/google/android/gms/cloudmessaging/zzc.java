package com.google.android.gms.cloudmessaging;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzc extends ClassLoader {
    @Override // java.lang.ClassLoader
    protected final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
            return super.loadClass(str, z);
        }
        if (!Log.isLoggable("CloudMessengerCompat", 3)) {
            return zzd.class;
        }
        Log.d("CloudMessengerCompat", "Using renamed FirebaseIidMessengerCompat class");
        return zzd.class;
    }
}
