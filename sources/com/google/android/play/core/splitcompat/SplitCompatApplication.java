package com.google.android.play.core.splitcompat;

import android.app.Application;
import android.content.Context;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public class SplitCompatApplication extends Application {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        SplitCompat.install(this);
    }
}
