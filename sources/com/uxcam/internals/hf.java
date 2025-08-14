package com.uxcam.internals;

import com.uxcam.screenaction.models.KeyConstant;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class hf {

    /* renamed from: a, reason: collision with root package name */
    public List<hf> f190a;
    public String b;
    public String c;

    public final JSONObject a(ex screenTagManager, String str) throws JSONException {
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        JSONObject jSONObject = new JSONObject();
        String str2 = this.b;
        if (str2 != null) {
            jSONObject.put(KeyConstant.KEY_FRAGMENT_NAME, screenTagManager.a(str, str2));
        } else {
            String str3 = this.c;
            if (str3 != null) {
                jSONObject.put(KeyConstant.KEY_FRAGMENT_ACTIVITY_NAME, str3);
            }
        }
        List<hf> list = this.f190a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                List<hf> list2 = this.f190a;
                Intrinsics.checkNotNull(list2);
                Iterator<hf> it = list2.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().a(screenTagManager, str));
                }
                jSONObject.put(KeyConstant.KEY_CHILD_FRAGMENTS, jSONArray);
            }
        }
        return jSONObject;
    }

    public final String toString() {
        return "UXCamFragmentData{childFragmentList=" + this.f190a + ", fragmentName='" + this.b + "', activityName='" + this.c + "'}";
    }
}
