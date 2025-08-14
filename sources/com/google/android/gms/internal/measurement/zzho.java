package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzho {
    private static volatile zzii zza;

    private zzho() {
    }

    public static zzii zza(Context context) {
        zzii zziiVar;
        zzii zziiVarZzc;
        zzii zziiVarZzc2;
        synchronized (zzho.class) {
            zziiVar = zza;
            if (zziiVar == null) {
                String str = Build.TYPE;
                String str2 = Build.TAGS;
                if ((str.equals("eng") || str.equals("userdebug")) && (str2.contains("dev-keys") || str2.contains("test-keys"))) {
                    if (zzhb.zzb() && !context.isDeviceProtectedStorage()) {
                        context = context.createDeviceProtectedStorageContext();
                    }
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        StrictMode.allowThreadDiskWrites();
                        try {
                            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
                            zziiVarZzc = file.exists() ? zzii.zzd(file) : zzii.zzc();
                        } catch (RuntimeException e) {
                            Log.e("HermeticFileOverrides", "no data dir", e);
                            zziiVarZzc = zzii.zzc();
                        }
                        if (zziiVarZzc.zzb()) {
                            Object objZza = zziiVarZzc.zza();
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream((File) objZza)));
                                try {
                                    SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
                                    HashMap map = new HashMap();
                                    while (true) {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        String[] strArrSplit = line.split(StringUtils.SPACE, 3);
                                        if (strArrSplit.length != 3) {
                                            Log.e("HermeticFileOverrides", "Invalid: " + line);
                                        } else {
                                            String str3 = new String(strArrSplit[0]);
                                            String strDecode = Uri.decode(new String(strArrSplit[1]));
                                            String strDecode2 = (String) map.get(strArrSplit[2]);
                                            if (strDecode2 == null) {
                                                String str4 = new String(strArrSplit[2]);
                                                strDecode2 = Uri.decode(str4);
                                                if (strDecode2.length() < 1024 || strDecode2 == str4) {
                                                    map.put(str4, strDecode2);
                                                }
                                            }
                                            if (!simpleArrayMap.containsKey(str3)) {
                                                simpleArrayMap.put(str3, new SimpleArrayMap());
                                            }
                                            ((SimpleArrayMap) simpleArrayMap.get(str3)).put(strDecode, strDecode2);
                                        }
                                    }
                                    Log.w("HermeticFileOverrides", "Parsed " + objZza.toString() + " for Android package " + context.getPackageName());
                                    zzhh zzhhVar = new zzhh(simpleArrayMap);
                                    bufferedReader.close();
                                    zziiVarZzc2 = zzii.zzd(zzhhVar);
                                } catch (Throwable th) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th2) {
                                        try {
                                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                                        } catch (Exception unused) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        } else {
                            zziiVarZzc2 = zzii.zzc();
                        }
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    } catch (Throwable th3) {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        throw th3;
                    }
                } else {
                    zziiVarZzc2 = zzii.zzc();
                }
                zziiVar = zziiVarZzc2;
                zza = zziiVar;
            }
        }
        return zziiVar;
    }
}
