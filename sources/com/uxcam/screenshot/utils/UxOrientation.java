package com.uxcam.screenshot.utils;

import android.util.DisplayMetrics;
import com.uxcam.screenaction.utils.Util;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/uxcam/screenshot/utils/UxOrientation;", "", "()V", "isLandscape", "", "()Z", "isPortrait", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class UxOrientation {
    static {
        new UxOrientation();
    }

    private UxOrientation() {
    }

    public final boolean isLandscape() {
        DisplayMetrics displayMetrics = Util.getCurrentContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels > displayMetrics.heightPixels;
    }

    public final boolean isPortrait() {
        return !isLandscape();
    }
}
