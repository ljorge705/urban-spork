package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/ShadowedNestedScrollView;", "Landroidx/core/widget/NestedScrollView;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "getSolidColor", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShadowedNestedScrollView extends NestedScrollView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShadowedNestedScrollView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.View
    public int getSolidColor() {
        return ContextCompat.getColor(getContext(), R.color.onfido_scroll_view_fading_edge_color);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShadowedNestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadowedNestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setFadingEdgeLength(ContextUtilsKt.dimen(context, R.dimen.onfido_scroll_view_fading_edge_length));
        setVerticalFadingEdgeEnabled(true);
    }

    public /* synthetic */ ShadowedNestedScrollView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
