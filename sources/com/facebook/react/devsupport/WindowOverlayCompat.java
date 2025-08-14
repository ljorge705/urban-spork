package com.facebook.react.devsupport;

import android.os.Build;
import androidx.media3.common.PlaybackException;

/* loaded from: classes5.dex */
class WindowOverlayCompat {
    private static final int TYPE_APPLICATION_OVERLAY = 2038;
    static final int TYPE_SYSTEM_OVERLAY;

    static {
        TYPE_SYSTEM_OVERLAY = Build.VERSION.SDK_INT < 26 ? PlaybackException.ERROR_CODE_IO_NO_PERMISSION : TYPE_APPLICATION_OVERLAY;
    }

    WindowOverlayCompat() {
    }
}
