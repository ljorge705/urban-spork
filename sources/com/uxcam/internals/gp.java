package com.uxcam.internals;

import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.ScreenAction;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes6.dex */
public final class gp {

    /* renamed from: a, reason: collision with root package name */
    public String f180a;
    public float b;
    public ArrayList<GestureData> c = new ArrayList<>();
    public ArrayList<ScreenAction> d = new ArrayList<>();
    public float e;
    public boolean f;
    public boolean g;

    public final String toString() {
        return "TimelineData{activityName='" + this.f180a + "', viewAppearedTime=" + this.b + ", gestureList=" + this.c + ", screenActionList=" + this.d + ", viewedTime=" + this.e + ", userTagged=" + this.f + ", ignoreGesture=" + this.g + AbstractJsonLexerKt.END_OBJ;
    }
}
