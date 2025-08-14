package com.uxcam.internals;

import com.uxcam.internals.el;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.models.GestureData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes6.dex */
public final class gt implements el.aa {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gu f182a;

    public gt(gu guVar) {
        this.f182a = guVar;
    }

    @Override // com.uxcam.internals.el.aa
    public final void a(ArrayList arrayList) throws NumberFormatException {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            GestureData gestureData = (GestureData) it.next();
            gestureData.setRage(true);
            gk.aa aaVarA = gk.a("rageClickDetector");
            gestureData.getGesture();
            gestureData.getTime();
            this.f182a.f183a.b();
            gestureData.getX();
            gestureData.getY();
            gestureData.isRage();
            gestureData.getActivityName();
            aaVarA.getClass();
        }
        gk.a("rageClickDetector").getClass();
        HashMap map = new HashMap();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.ENGLISH, "%.3f", Arrays.copyOf(new Object[]{Float.valueOf(((GestureData) arrayList.get(0)).getTime() - this.f182a.f183a.b())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        float f = Float.parseFloat(str);
        String activityName = ((GestureData) arrayList.get(0)).getActivityName();
        Intrinsics.checkNotNullExpressionValue(activityName, "gestures[0].activityName");
        map.put("activity", activityName);
        this.f182a.j.a("rageTap", f, map);
    }
}
