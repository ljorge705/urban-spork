package com.uxcam.internals;

import android.util.Log;
import com.uxcam.internals.gk;

/* loaded from: classes6.dex */
public final class ho extends gk.ab {
    @Override // com.uxcam.internals.gk.ab
    public final void a(int i, String str, String str2) {
        if (str == null) {
            str = "UXCam 3.6.13[580]";
        }
        if (i != 4) {
            return;
        }
        Log.i(str, str2);
    }
}
