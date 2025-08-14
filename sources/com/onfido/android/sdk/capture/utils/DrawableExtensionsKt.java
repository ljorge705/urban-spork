package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"getVectorDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "resId", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DrawableExtensionsKt {
    public static final Drawable getVectorDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppCompatResources.getDrawable(context, i);
    }
}
