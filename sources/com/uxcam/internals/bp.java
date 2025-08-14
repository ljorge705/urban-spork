package com.uxcam.internals;

import android.content.Context;
import com.uxcam.env.Environment;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes6.dex */
public final class bp implements bo {
    @Override // com.uxcam.internals.bo
    public final Environment a(Context context) {
        boolean z = (context.getApplicationInfo().flags & 2) != 0;
        ArrayList arrayList = new ArrayList(Arrays.asList("com.android.vending", "com.google.android.feedback"));
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return z ? Environment.ALPHA : installerPackageName != null && arrayList.contains(installerPackageName) ? Environment.RELEASE : Environment.BETA;
    }
}
