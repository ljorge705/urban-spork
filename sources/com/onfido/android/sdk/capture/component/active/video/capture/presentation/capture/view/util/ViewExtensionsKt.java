package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.WindowHelperKt;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"setOvalMargin", "", "Landroid/view/View;", "width", "", "height", ViewProps.MARGIN_TOP, "toSize", "Landroid/util/Size;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewExtensionsKt {
    public static final void setOvalMargin(View view, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            OvalRect ovalRect = OvalRect.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            OnfidoRectF onfidoRectF = ovalRect.get(i, i2, WindowHelperKt.getWindowSizeClass(context));
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, ((int) onfidoRectF.getTop()) + ((int) onfidoRectF.height()) + i3, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    public static /* synthetic */ void setOvalMargin$default(View view, int i, int i2, int i3, int i4, Object obj) throws Resources.NotFoundException {
        if ((i4 & 4) != 0) {
            i3 = view.getContext().getResources().getDimensionPixelSize(R.dimen.onfido_avc_padding_oval_and_text);
        }
        setOvalMargin(view, i, i2, i3);
    }

    public static final Size toSize(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return new Size(view.getWidth(), view.getHeight());
    }
}
