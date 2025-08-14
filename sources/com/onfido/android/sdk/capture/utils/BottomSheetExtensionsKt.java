package com.onfido.android.sdk.capture.utils;

import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000\u001a\u001c\u0010\u0007\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0000¨\u0006\b"}, d2 = {"collapse", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/ViewGroup;", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "expand", "hide", "toggle", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BottomSheetExtensionsKt {
    public static final <T extends ViewGroup> void collapse(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setState(4);
    }

    public static final <T extends ViewGroup> void expand(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setState(3);
    }

    public static final <T extends ViewGroup> void hide(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setState(5);
    }

    public static final <T extends ViewGroup> void toggle(BottomSheetBehavior<T> bottomSheetBehavior) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        int state = bottomSheetBehavior.getState();
        if (state == 3) {
            collapse(bottomSheetBehavior);
        } else {
            if (state != 4) {
                return;
            }
            expand(bottomSheetBehavior);
        }
    }
}
