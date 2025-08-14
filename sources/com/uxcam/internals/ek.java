package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ek {
    public static ArrayList a(Context context) throws JSONException {
        ej ejVar;
        ArrayList arrayList = new ArrayList();
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("UXCamPreferences", 0) : null;
        String string = sharedPreferences == null ? "" : sharedPreferences.getString("push_notification_data", null);
        if (string != null && !string.isEmpty()) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    try {
                        ejVar = new ej(jSONObject.getLong("timeStamp"), new JSONObject(jSONObject.getString("uxCamData")));
                    } catch (JSONException unused) {
                        ejVar = new ej(0L, new JSONObject());
                    }
                    arrayList.add(ejVar);
                }
            } catch (JSONException e) {
                e.getMessage();
            }
        }
        return arrayList;
    }

    public static JSONArray a(Context context, double d) throws JSONException {
        ArrayList arrayListA = a(context);
        ArrayList arrayList = new ArrayList();
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            ej ejVar = (ej) it.next();
            ejVar.c = (float) ((ejVar.f136a - (System.currentTimeMillis() - (d * 1000.0d))) / 1000.0d);
            arrayList.add(ejVar);
        }
        new eg(context).a("push_notification_data");
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ej ejVar2 = (ej) it2.next();
                ejVar2.getClass();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timeStamp", ejVar2.f136a);
                jSONObject.put("uxCamData", ejVar2.b);
                jSONObject.put("timeLine", ejVar2.c);
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e) {
            e.getMessage();
        }
        return jSONArray;
    }
}
