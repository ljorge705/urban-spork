package com.uxcam.internals;

import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ak {

    /* renamed from: a, reason: collision with root package name */
    public final int[] f80a;
    public final int[] b;

    public ak(int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("binBreakPoints[] cannot be empty.");
        }
        this.f80a = iArr;
        int[] iArr2 = new int[iArr.length + 1];
        this.b = iArr2;
        Arrays.fill(iArr2, 0);
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        for (int i2 : this.f80a) {
            jSONArray.put(i2);
        }
        JSONArray jSONArray2 = new JSONArray();
        while (true) {
            int[] iArr = this.b;
            if (i < iArr.length) {
                jSONArray2.put(iArr[i]);
                i++;
            } else {
                try {
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        jSONObject.put("bin", jSONArray);
        jSONObject.put("data", jSONArray2);
        return jSONObject;
    }
}
