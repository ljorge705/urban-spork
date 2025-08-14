package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzfd {
    final /* synthetic */ zzfi zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzfd(zzfi zzfiVar, String str, Bundle bundle) {
        this.zza = zzfiVar;
        Preconditions.checkNotEmpty("default_event_parameters");
        this.zzb = "default_event_parameters";
        this.zzc = new Bundle();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009b A[Catch: NumberFormatException | JSONException -> 0x00a3, NumberFormatException | JSONException -> 0x00a3, TRY_LEAVE, TryCatch #0 {NumberFormatException | JSONException -> 0x00a3, blocks: (B:10:0x0027, B:32:0x0071, B:32:0x0071, B:33:0x0083, B:33:0x0083, B:34:0x008f, B:34:0x008f, B:35:0x009b, B:35:0x009b), top: B:47:0x0027, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle zza() {
        /*
            r11 = this;
            android.os.Bundle r0 = r11.zzd
            if (r0 == 0) goto L6
            goto Ld4
        L6:
            com.google.android.gms.measurement.internal.zzfi r0 = r11.zza
            android.content.SharedPreferences r0 = r0.zza()
            java.lang.String r1 = r11.zzb
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            if (r0 == 0) goto Lcc
            android.os.Bundle r1 = new android.os.Bundle     // Catch: org.json.JSONException -> Lbb
            r1.<init>()     // Catch: org.json.JSONException -> Lbb
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: org.json.JSONException -> Lbb
            r2.<init>(r0)     // Catch: org.json.JSONException -> Lbb
            r0 = 0
            r3 = r0
        L21:
            int r4 = r2.length()     // Catch: org.json.JSONException -> Lbb
            if (r3 >= r4) goto Lb8
            org.json.JSONObject r4 = r2.getJSONObject(r3)     // Catch: java.lang.Throwable -> La3
            java.lang.String r5 = "n"
            java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = "t"
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Throwable -> La3
            int r7 = r6.hashCode()     // Catch: java.lang.Throwable -> La3
            r8 = 100
            r9 = 2
            r10 = 1
            if (r7 == r8) goto L5e
            r8 = 108(0x6c, float:1.51E-43)
            if (r7 == r8) goto L54
            r8 = 115(0x73, float:1.61E-43)
            if (r7 == r8) goto L4a
            goto L68
        L4a:
            java.lang.String r7 = "s"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L68
            r7 = r0
            goto L69
        L54:
            java.lang.String r7 = "l"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L68
            r7 = r9
            goto L69
        L5e:
            java.lang.String r7 = "d"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L68
            r7 = r10
            goto L69
        L68:
            r7 = -1
        L69:
            java.lang.String r8 = "v"
            if (r7 == 0) goto L9b
            if (r7 == r10) goto L8f
            if (r7 == r9) goto L83
            com.google.android.gms.measurement.internal.zzfi r4 = r11.zza     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            com.google.android.gms.measurement.internal.zzgd r4 = r4.zzt     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            com.google.android.gms.measurement.internal.zzet r4 = r4.zzaA()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzd()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            java.lang.String r5 = "Unrecognized persisted bundle type. Type"
            r4.zzb(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L83:
            java.lang.String r4 = r4.getString(r8)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            long r6 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putLong(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L8f:
            java.lang.String r4 = r4.getString(r8)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            double r6 = java.lang.Double.parseDouble(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putDouble(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L9b:
            java.lang.String r4 = r4.getString(r8)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putString(r5, r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        La3:
            com.google.android.gms.measurement.internal.zzfi r4 = r11.zza     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzgd r4 = r4.zzt     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzet r4 = r4.zzaA()     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzd()     // Catch: org.json.JSONException -> Lbb
            java.lang.String r5 = "Error reading value from SharedPreferences. Value dropped"
            r4.zza(r5)     // Catch: org.json.JSONException -> Lbb
        Lb4:
            int r3 = r3 + 1
            goto L21
        Lb8:
            r11.zzd = r1     // Catch: org.json.JSONException -> Lbb
            goto Lcc
        Lbb:
            com.google.android.gms.measurement.internal.zzfi r0 = r11.zza
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzaA()
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzd()
            java.lang.String r1 = "Error loading bundle from SharedPreferences. Values will be lost"
            r0.zza(r1)
        Lcc:
            android.os.Bundle r0 = r11.zzd
            if (r0 != 0) goto Ld4
            android.os.Bundle r0 = r11.zzc
            r11.zzd = r0
        Ld4:
            android.os.Bundle r0 = r11.zzd
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfd.zza():android.os.Bundle");
    }

    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor editorEdit = this.zza.zza().edit();
        if (bundle.size() == 0) {
            editorEdit.remove(this.zzb);
        } else {
            String str = this.zzb;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("n", str2);
                        jSONObject.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, obj.toString());
                        if (obj instanceof String) {
                            jSONObject.put("t", "s");
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", "l");
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", "d");
                        } else {
                            this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences", e);
                    }
                }
            }
            editorEdit.putString(str, jSONArray.toString());
        }
        editorEdit.apply();
        this.zzd = bundle;
    }
}
