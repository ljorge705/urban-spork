package com.uxcam.internals;

import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.KeyConstant;
import io.sentry.protocol.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class gw implements gv {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList<gp> f186a = new ArrayList<>();
    public final ArrayList<bz> b = new ArrayList<>();
    public final ArrayList<bz> c = new ArrayList<>();
    public JSONArray d = new JSONArray();
    public JSONArray e = new JSONArray();
    public float f;
    public String g;

    @Override // com.uxcam.internals.gv
    public final String a() {
        return this.g;
    }

    @Override // com.uxcam.internals.gv
    public final void a(float f) {
        this.f = f;
    }

    @Override // com.uxcam.internals.gv
    public final void a(gp gpVar) {
        this.f186a.add(gpVar);
    }

    @Override // com.uxcam.internals.gv
    public final void a(String str) {
        this.g = str;
    }

    @Override // com.uxcam.internals.gv
    public final float b() {
        return this.f;
    }

    @Override // com.uxcam.internals.gv
    public final void b(bz bzVar) {
        this.c.add(bzVar);
    }

    @Override // com.uxcam.internals.gv
    public final JSONArray c() {
        return this.e;
    }

    @Override // com.uxcam.internals.gv
    public final void d() {
        this.c.clear();
    }

    @Override // com.uxcam.internals.gv
    public final ArrayList e() {
        return this.f186a;
    }

    @Override // com.uxcam.internals.gv
    public final void f() {
        this.f186a.clear();
    }

    @Override // com.uxcam.internals.gv
    public final void h() {
        this.b.clear();
    }

    @Override // com.uxcam.internals.gv
    public final void i() {
        this.e = new JSONArray();
    }

    @Override // com.uxcam.internals.gv
    public final void j() {
        this.d = new JSONArray();
    }

    @Override // com.uxcam.internals.gv
    public final JSONArray k() {
        return this.d;
    }

    @Override // com.uxcam.internals.gv
    public final ArrayList l() {
        return this.b;
    }

    public final HashSet m() {
        HashSet hashSet = new HashSet();
        Iterator<gp> it = this.f186a.iterator();
        while (it.hasNext()) {
            gp next = it.next();
            Intrinsics.checkNotNull(next);
            hashSet.add(new ac(next.f180a));
        }
        return hashSet;
    }

    public final JSONArray o() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        a(this.b, jSONArray);
        a(this.c, jSONArray);
        return jSONArray;
    }

    @Override // com.uxcam.internals.gv
    public final void a(bz bzVar) {
        this.b.add(bzVar);
    }

    @Override // com.uxcam.internals.gv
    public final void b(JSONObject jSONObject) {
        this.e.put(jSONObject);
    }

    @Override // com.uxcam.internals.gv
    public final String g() {
        ArrayList<gp> arrayList = this.f186a;
        if (!(!arrayList.isEmpty())) {
            return "";
        }
        gp gpVar = arrayList.get(arrayList.size() - 1);
        Intrinsics.checkNotNull(gpVar);
        return gpVar.f180a;
    }

    public final int n() {
        Iterator<gp> it = this.f186a.iterator();
        int i = 0;
        while (it.hasNext()) {
            gp next = it.next();
            Intrinsics.checkNotNull(next);
            Iterator<GestureData> it2 = next.c.iterator();
            while (it2.hasNext()) {
                GestureData next2 = it2.next();
                if (next2.getGesture() != 10 && next2.getGesture() != 3 && next2.getGesture() != 4 && next2.getGesture() != 5 && next2.getGesture() != 2) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // com.uxcam.internals.gv
    public final void a(JSONObject jSONObject) {
        this.d.put(jSONObject);
    }

    public static void a(ArrayList arrayList, JSONArray jSONArray) throws JSONException {
        Intrinsics.checkNotNull(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = new JSONObject();
                bz bzVar = (bz) it.next();
                Intrinsics.checkNotNull(bzVar);
                jSONObject.put("name", bzVar.b);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(Locale.ENGLISH, "%.3f", Arrays.copyOf(new Object[]{Float.valueOf(bzVar.c)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                jSONObject.put("time", Float.valueOf(str));
                jSONObject.put(KeyConstant.KEY_SCREEN, bzVar.d);
                if (bzVar.e) {
                    jSONObject.put("internal", true);
                }
                if (bzVar.f105a != null) {
                    jSONObject.put(Message.JsonKeys.PARAMS, new JSONObject(bzVar.f105a));
                }
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
