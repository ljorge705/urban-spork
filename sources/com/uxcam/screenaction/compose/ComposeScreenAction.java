package com.uxcam.screenaction.compose;

import android.graphics.Rect;
import com.uxcam.screenaction.models.GestureData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenaction/compose/ComposeScreenAction;", "", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final /* data */ class ComposeScreenAction {

    /* renamed from: a, reason: collision with root package name */
    public final String f231a;
    public final Rect b;
    public final GestureData c;
    public final String d;

    public ComposeScreenAction(String str, Rect bounds, GestureData gestureData, String displayName) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(gestureData, "gestureData");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        this.f231a = str;
        this.b = bounds;
        this.c = gestureData;
        this.d = displayName;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComposeScreenAction)) {
            return false;
        }
        ComposeScreenAction composeScreenAction = (ComposeScreenAction) obj;
        return Intrinsics.areEqual(this.f231a, composeScreenAction.f231a) && Intrinsics.areEqual(this.b, composeScreenAction.b) && Intrinsics.areEqual(this.c, composeScreenAction.c) && Intrinsics.areEqual(this.d, composeScreenAction.d);
    }

    public final int hashCode() {
        String str = this.f231a;
        return this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + ((str == null ? 0 : str.hashCode()) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ComposeScreenAction(text=" + this.f231a + ", bounds=" + this.b + ", gestureData=" + this.c + ", displayName=" + this.d + ')';
    }
}
