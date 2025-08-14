package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\u0007H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J(\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0014J\u0014\u0010\u0014\u001a\u00020\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/ShadowedScrollView;", "Landroid/widget/ScrollView;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bottomScrollListener", "Lkotlin/Function0;", "", "getSolidColor", "isBottomVisible", "", "onScrollChanged", "l", "t", "oldl", "oldt", "setBottomScrollListener", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShadowedScrollView extends ScrollView {
    private static final int BOTTOM_VISIBILITY_OFFSET_PX = 150;
    private static final Companion Companion = new Companion(null);
    private Function0<Unit> bottomScrollListener;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/ShadowedScrollView$Companion;", "", "()V", "BOTTOM_VISIBILITY_OFFSET_PX", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShadowedScrollView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final boolean isBottomVisible() {
        return getChildAt(0).getBottom() - (getHeight() + getScrollY()) <= 150;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return ContextCompat.getColor(getContext(), R.color.onfido_scroll_view_fading_edge_color);
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Function0<Unit> function0;
        super.onScrollChanged(l, t, oldl, oldt);
        if (!isBottomVisible() || (function0 = this.bottomScrollListener) == null) {
            return;
        }
        function0.invoke();
    }

    public final void setBottomScrollListener(Function0<Unit> bottomScrollListener) {
        Intrinsics.checkNotNullParameter(bottomScrollListener, "bottomScrollListener");
        this.bottomScrollListener = bottomScrollListener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ShadowedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadowedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setFadingEdgeLength(ContextUtilsKt.dimen(context, R.dimen.onfido_scroll_view_fading_edge_length));
        setVerticalFadingEdgeEnabled(true);
    }

    public /* synthetic */ ShadowedScrollView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
