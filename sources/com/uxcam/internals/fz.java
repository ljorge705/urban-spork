package com.uxcam.internals;

import android.app.Activity;
import android.content.Context;
import com.uxcam.screenaction.utils.Util;

/* loaded from: classes6.dex */
public final class fz implements fy {

    /* renamed from: a, reason: collision with root package name */
    public final gs f166a;

    public fz(gs gsVar) {
        this.f166a = gsVar;
    }

    @Override // com.uxcam.internals.fy
    public final void a(Context context, String str) {
        this.f166a.a(context, str, true, (Activity) Util.getCurrentContext(), fp.f157n);
    }

    @Override // com.uxcam.internals.fy
    public final void b(Context context, String str) {
        this.f166a.a(context, str, false, (Activity) Util.getCurrentContext(), fp.f157n);
    }
}
