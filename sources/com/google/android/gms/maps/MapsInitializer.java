package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        return initialize(context, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053 A[Catch: RemoteException -> 0x005f, all -> 0x0091, TryCatch #1 {RemoteException -> 0x005f, blocks: (B:21:0x004d, B:23:0x0053, B:24:0x0057), top: B:44:0x004d, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e A[Catch: all -> 0x0091, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x0022, B:10:0x0029, B:11:0x002d, B:13:0x003c, B:15:0x0041, B:21:0x004d, B:23:0x0053, B:24:0x0057, B:28:0x0067, B:30:0x007e, B:27:0x0060, B:34:0x0086, B:35:0x008b, B:37:0x008d), top: B:43:0x0003, inners: #1, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L91
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = r3.concat(r2)     // Catch: java.lang.Throwable -> L91
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L91
            boolean r1 = com.google.android.gms.maps.MapsInitializer.zzb     // Catch: java.lang.Throwable -> L91
            r2 = 0
            if (r1 == 0) goto L29
            if (r7 == 0) goto L27
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L91
        L27:
            monitor-exit(r0)
            return r2
        L29:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcc.zza(r5, r6)     // Catch: com.google.android.gms.common.GooglePlayServicesNotAvailableException -> L8c java.lang.Throwable -> L91
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            r3 = 1
            com.google.android.gms.maps.MapsInitializer.zzb = r3     // Catch: java.lang.Throwable -> L91
            r4 = 2
            if (r6 == 0) goto L4c
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L91
            if (r6 == 0) goto L4d
            if (r6 == r3) goto L4a
            goto L4c
        L4a:
            r3 = r4
            goto L4d
        L4c:
            r3 = r2
        L4d:
            int r6 = r1.zzd()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            if (r6 != r4) goto L57
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            com.google.android.gms.maps.MapsInitializer.zzc = r6     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
        L57:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            r1.zzl(r5, r3)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            goto L67
        L5f:
            r5 = move-exception
            java.lang.String r6 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch: java.lang.Throwable -> L91
        L67:
            java.lang.String r5 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = r1.concat(r6)     // Catch: java.lang.Throwable -> L91
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L91
            if (r7 == 0) goto L83
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L91
        L83:
            monitor-exit(r0)
            return r2
        L85:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch: java.lang.Throwable -> L91
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L91
            throw r6     // Catch: java.lang.Throwable -> L91
        L8c:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch: java.lang.Throwable -> L91
            monitor-exit(r0)
            return r5
        L91:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
