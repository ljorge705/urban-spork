package com.uxcam.internals;

import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.KeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class gr implements gq {

    /* renamed from: a, reason: collision with root package name */
    public final gv f181a;
    public final gc b;
    public final ex c;

    public gr(gv timelineRepository, gc settingsStateHolder, ex screenTagManager) {
        Intrinsics.checkNotNullParameter(timelineRepository, "timelineRepository");
        Intrinsics.checkNotNullParameter(settingsStateHolder, "settingsStateHolder");
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        this.f181a = timelineRepository;
        this.b = settingsStateHolder;
        this.c = screenTagManager;
    }

    @Override // com.uxcam.internals.gq
    public final JSONArray a() throws JSONException {
        ArrayList arrayListE = this.f181a.e();
        if (this.b.a()) {
            arrayListE = this.c.a(this.f181a.e());
        }
        Iterator it = arrayListE.iterator();
        JSONArray jSONArray = new JSONArray();
        boolean z = true;
        while (it.hasNext()) {
            try {
                gp gpVar = (gp) it.next();
                JSONArray jSONArray2 = new JSONArray();
                if (z) {
                    Intrinsics.checkNotNull(gpVar);
                    if (gpVar.e - this.f181a.b() < 0.0f) {
                        this.f181a.a(0.0f);
                    }
                }
                JSONObject jSONObject = new JSONObject();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                Intrinsics.checkNotNull(gpVar);
                String str = String.format(locale, "%.3f", Arrays.copyOf(new Object[]{Float.valueOf(gpVar.b)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                jSONObject.put(KeyConstant.KEY_VIEW_APPEARED, Float.valueOf(str));
                jSONArray2.put(jSONObject);
                JSONArray jSONArray3 = new JSONArray();
                a(gpVar, jSONArray3);
                JSONObject jSONObject2 = new JSONObject();
                hg hgVarB = this.c.b(gpVar.f180a);
                if (hgVarB != null) {
                    hf hfVar = hgVarB.b;
                    Intrinsics.checkNotNull(hfVar);
                    jSONObject2 = hfVar.a(this.c, gpVar.f180a);
                }
                JSONObject jSONObjectB = b(gpVar, jSONArray3);
                float fB = gpVar.e;
                if (z) {
                    fB -= this.f181a.b();
                }
                if (!it.hasNext()) {
                    fB += this.f181a.b();
                }
                if (z) {
                    z = false;
                }
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format(locale, "%.3f", Arrays.copyOf(new Object[]{Float.valueOf(fB)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
                jSONObjectB.put(KeyConstant.KEY_VIEW_TIME, Float.valueOf(str2));
                jSONObjectB.put(KeyConstant.KEY_ACTIVITY_NAME, gpVar.f180a);
                jSONObjectB.put(KeyConstant.KEY_FRAGMENT_DATA, jSONObject2);
                jSONArray.put(jSONObjectB);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.f181a.a(0.0f);
        return jSONArray;
    }

    public final JSONObject b(gp gpVar, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KeyConstant.KEY_COORDINATES, jSONArray);
        float fB = gpVar.b - this.f181a.b();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[1];
        if (fB < 0.0f) {
            fB = 0.0f;
        }
        objArr[0] = Float.valueOf(fB);
        String str = String.format(locale, "%.3f", Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        jSONObject.put("at", Float.valueOf(str));
        return jSONObject;
    }

    public final void a(gp gpVar, JSONArray jSONArray) {
        Iterator<GestureData> it = gpVar.c.iterator();
        while (it.hasNext()) {
            GestureData gestureData = it.next();
            int orientation = gestureData.getOrientation();
            gestureData.decreaseTimeOffset(this.f181a.b());
            Boolean boolIsResponsive = gestureData.isResponsive();
            Intrinsics.checkNotNullExpressionValue(boolIsResponsive, "gestureData.isResponsive");
            boolean zBooleanValue = boolIsResponsive.booleanValue();
            boolean zIsRage = gestureData.isRage();
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(gestureData.getX());
            jSONArray2.put(gestureData.getY());
            jSONArray2.put(orientation);
            jSONArray2.put(gestureData.getGesture());
            jSONArray2.put(zBooleanValue ? 1 : 0);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[1];
            objArr[0] = Float.valueOf(gestureData.getTime() >= 0.0f ? gestureData.getTime() : 0.0f);
            String str = String.format(locale, "%.3f", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
            jSONArray2.put(Float.valueOf(str));
            Intrinsics.checkNotNullExpressionValue(gestureData, "gestureData");
            JSONArray jSONArray3 = new JSONArray();
            Iterator<GestureData> it2 = gestureData.getTrail().iterator();
            while (it2.hasNext()) {
                GestureData next = it2.next();
                JSONArray jSONArray4 = new JSONArray();
                jSONArray4.put(next.getGesture());
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format(Locale.ENGLISH, "%.3f", Arrays.copyOf(new Object[]{Float.valueOf(next.getTime())}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
                jSONArray4.put(Float.valueOf(str2));
                jSONArray4.put(next.getX());
                jSONArray4.put(next.getY());
                jSONArray3.put(jSONArray4);
            }
            if (gestureData.getTime() > 0.0f || gestureData.getGesture() == 10) {
                jSONArray2.put(jSONArray3);
                jSONArray.put(jSONArray2);
            }
            jSONArray2.put(zIsRage ? 1 : 0);
            if (gestureData.getScreenAction() != null) {
                jSONArray2.put(gestureData.getScreenAction().getJsonObject());
            } else {
                jSONArray2.put(new JSONObject());
            }
        }
    }
}
