package com.uxcam.internals;

import android.content.SharedPreferences;
import java.util.Arrays;
import org.json.JSONArray;

/* loaded from: classes6.dex */
public final class hz {
    public static hz c;

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f203a;
    public final String[] b;

    public hz(SharedPreferences sharedPreferences) {
        this.f203a = sharedPreferences;
        String[] strArr = new String[3];
        this.b = strArr;
        Arrays.fill(strArr, "");
        String string = sharedPreferences.getString("last_session_id", null);
        if (string == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.b[i] = jSONArray.getString(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.b.length; i++) {
            sb.append("index:").append(i).append(" => ").append(this.b[i]).append(" ; ");
        }
        return "SManager{ " + sb.toString() + " }";
    }
}
