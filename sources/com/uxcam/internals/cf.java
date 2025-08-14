package com.uxcam.internals;

import com.clevertap.android.sdk.inapp.evaluation.TriggerAdapter;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.models.KeyConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class cf {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f108a = new ArrayList();
    public final ArrayList b = new ArrayList();
    public final ArrayList<JSONObject> c = new ArrayList<>();
    public final ArrayList<Integer> d = new ArrayList<>();
    public final boolean e;
    public final int f;
    public final int g;

    public cf(ArrayList arrayList, JSONArray jSONArray, boolean z, int i, int i2) {
        this.e = z;
        this.f = i;
        this.g = i2;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bf bfVar = (bf) it.next();
            gk.aa aaVarA = gk.a("filter11");
            bfVar.getValue();
            bfVar.a();
            aaVarA.getClass();
            if (bfVar.a() == 1) {
                this.f108a.add(bfVar);
            } else if (bfVar.a() == 2) {
                this.b.add(bfVar);
            }
        }
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                this.c.add(jSONArray.getJSONObject(i3));
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public final boolean a() throws JSONException {
        if (this.f108a.isEmpty()) {
            return true;
        }
        Iterator<JSONObject> it = this.c.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            JSONObject next = it.next();
            if (next.getString("attribute").equalsIgnoreCase(KeyConstant.KEY_SCREEN)) {
                String string = next.getString(TriggerAdapter.INAPP_OPERATOR);
                JSONArray jSONArray = next.getJSONArray("value");
                if (string.equalsIgnoreCase("is")) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String string2 = jSONArray.getString(i);
                        Iterator it2 = this.f108a.iterator();
                        while (it2.hasNext()) {
                            if (string2.equalsIgnoreCase(((bf) it2.next()).getValue())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                if (string.equalsIgnoreCase("isNot")) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            break;
                        }
                        if (!a(jSONArray.getString(i2), this.f108a)) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                    return !z;
                }
                z2 = true;
            }
        }
        return !z2;
    }

    public final boolean b() throws JSONException {
        Iterator<JSONObject> it = this.c.iterator();
        while (it.hasNext()) {
            JSONObject next = it.next();
            if (next.getString("attribute").equalsIgnoreCase("isCrashed")) {
                String string = next.getString(TriggerAdapter.INAPP_OPERATOR);
                JSONArray jSONArray = next.getJSONArray("value");
                if (!string.equalsIgnoreCase("is") || this.e != jSONArray.getBoolean(0)) {
                    if (!string.equalsIgnoreCase("isNot") || this.e != jSONArray.getBoolean(0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final boolean c() throws JSONException {
        Iterator<JSONObject> it = this.c.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            JSONObject next = it.next();
            if (next.getString("attribute").equalsIgnoreCase("event")) {
                String string = next.getString(TriggerAdapter.INAPP_OPERATOR);
                JSONArray jSONArray = next.getJSONArray("value");
                if (string.equalsIgnoreCase("is")) {
                    if (this.b.isEmpty()) {
                        return false;
                    }
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String string2 = jSONArray.getString(i);
                        Iterator it2 = this.b.iterator();
                        while (it2.hasNext()) {
                            if (string2.equalsIgnoreCase(((bf) it2.next()).getValue())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                if (string.equalsIgnoreCase("isNot")) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            break;
                        }
                        if (!a(jSONArray.getString(i2), this.b)) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                    return !z;
                }
                z2 = true;
            }
        }
        return !z2;
    }

    public final boolean d() {
        boolean z = true;
        try {
            if (!a("noOfInteraction", this.g)) {
                this.d.add(1);
                gk.a("filter11").getClass();
                z = false;
            }
            if (!a("duration", this.f)) {
                this.d.add(2);
                gk.a("filter11").getClass();
                z = false;
            }
            if (!b()) {
                this.d.add(3);
                gk.a("filter11").getClass();
                z = false;
            }
            if (!c()) {
                this.d.add(4);
                gk.a("filter11").getClass();
                z = false;
            }
            if (a()) {
                gk.a("filter11").getClass();
                return z;
            }
            this.d.add(5);
            gk.a("filter11").getClass();
            return false;
        } catch (JSONException unused) {
            return true;
        }
    }

    public static boolean a(String str, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(((bf) it.next()).getValue())) {
                z = false;
            }
        }
        return z;
    }

    public final boolean a(String str, int i) throws JSONException {
        Iterator<JSONObject> it = this.c.iterator();
        while (it.hasNext()) {
            JSONObject next = it.next();
            if (next.getString("attribute").equalsIgnoreCase(str)) {
                String string = next.getString(TriggerAdapter.INAPP_OPERATOR);
                JSONArray jSONArray = next.getJSONArray("value");
                if (!string.equalsIgnoreCase("is") || i != jSONArray.getInt(0)) {
                    if (!string.equalsIgnoreCase("isNot") || i == jSONArray.getInt(0)) {
                        if (!string.equalsIgnoreCase("gt") || i <= jSONArray.getInt(0)) {
                            if (!string.equalsIgnoreCase("gteq") || i < jSONArray.getInt(0)) {
                                if (!string.equalsIgnoreCase("lt") || i >= jSONArray.getInt(0)) {
                                    if (!string.equalsIgnoreCase("lteq") || i > jSONArray.getInt(0)) {
                                        if (!string.equalsIgnoreCase("gt&lt") || i <= jSONArray.getInt(0) || i >= jSONArray.getInt(1)) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
