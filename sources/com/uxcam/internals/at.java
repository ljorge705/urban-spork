package com.uxcam.internals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class at {

    /* renamed from: a, reason: collision with root package name */
    public static final aa f87a = new aa();

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final HashSet f88a = new HashSet();
        public int d = 0;
        public int e = 0;
        public long f = 0;
        public long g = 0;
        public long h = Long.MAX_VALUE;
        public final ak b = new ak(new int[]{10, 200, 500, 1000, 2000, 5000});
        public final ak c = new ak(new int[]{100, 500, 2000, 10000, 50000});

        public final JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("totalCallCount", this.d);
            jSONObject.put("failedCallCount", this.e);
            jSONObject.put("longestCallDurationMs", this.g);
            long j = this.h;
            if (j == Long.MAX_VALUE) {
                jSONObject.put("shortestCallDurationMs", 0L);
            } else {
                jSONObject.put("shortestCallDurationMs", j);
            }
            int i = this.d;
            if (i > 0) {
                jSONObject.put("averageCallDurationMs", this.f / i);
            } else {
                jSONObject.put("averageCallDurationMs", this.f);
            }
            jSONObject.put("durationData", this.b.a());
            jSONObject.put("responseSizeData", this.c.a());
            JSONArray jSONArray = new JSONArray();
            Iterator it = this.f88a.iterator();
            while (it.hasNext()) {
                jSONArray.put((String) it.next());
            }
            jSONObject.put("uniqueHosts", jSONArray);
            return jSONObject;
        }
    }

    public static class ab {
        public static JSONObject a() throws JSONException {
            JSONObject jSONObjectA = at.f87a.a();
            boolean z = hj.c;
            ef efVar = new ef(jSONObjectA, z);
            if (!z) {
                return null;
            }
            if (!efVar.f134a.has("totalCallCount")) {
                HashMap map = new HashMap();
                map.put("site_of_error", "PreUploadConditionChecker");
                ht.c("[#status#] #method#".replace("#method#", "Key { totalCallCount } was not found in the data to be sent."), map);
                return efVar.f134a;
            }
            int i = efVar.f134a.getInt("totalCallCount");
            if (i == 0) {
                JSONObject jSONObject = new JSONObject();
                efVar.f134a = jSONObject;
                jSONObject.put("totalCallCount", i);
            }
            return efVar.f134a;
        }
    }

    public static void a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("httpStatusCode");
        String string = jSONObject.getString("protocol");
        aa aaVar = f87a;
        aaVar.d++;
        aaVar.f88a.add(jSONObject.getString("requestUrl"));
        int i2 = 0;
        if (i == -1 && string.isEmpty()) {
            aaVar.e++;
            long j = jSONObject.getLong("callDurationMs");
            aaVar.f += j;
            long j2 = aaVar.g;
            long j3 = aaVar.h;
            ak akVar = aaVar.b;
            int i3 = 0;
            while (true) {
                if (i3 >= akVar.f80a.length || j < r6[i3]) {
                    break;
                } else {
                    i3++;
                }
            }
            int[] iArr = akVar.b;
            iArr[i3] = iArr[i3] + 1;
        } else {
            long j4 = jSONObject.getLong("callDurationMs");
            aaVar.f += j4;
            if (j4 > aaVar.g) {
                aaVar.g = j4;
            }
            if (j4 < aaVar.h) {
                aaVar.h = j4;
            }
            ak akVar2 = aaVar.b;
            int i4 = 0;
            while (true) {
                if (i4 >= akVar2.f80a.length || j4 < r6[i4]) {
                    break;
                } else {
                    i4++;
                }
            }
            int[] iArr2 = akVar2.b;
            iArr2[i4] = iArr2[i4] + 1;
        }
        long j5 = jSONObject.getLong("responseSizeBytes");
        ak akVar3 = aaVar.c;
        while (true) {
            if (i2 >= akVar3.f80a.length || j5 < r3[i2]) {
                break;
            } else {
                i2++;
            }
        }
        int[] iArr3 = akVar3.b;
        iArr3[i2] = iArr3[i2] + 1;
        try {
            aaVar.a().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
