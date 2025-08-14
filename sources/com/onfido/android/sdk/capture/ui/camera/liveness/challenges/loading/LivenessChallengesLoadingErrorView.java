package com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.databinding.OnfidoLivenessChallengesLoadingErrorBinding;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingErrorView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoLivenessChallengesLoadingErrorBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingErrorView$Listener;", "setListener", "", "Listener", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessChallengesLoadingErrorView extends RelativeLayout {
    private final OnfidoLivenessChallengesLoadingErrorBinding binding;
    private Listener listener;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingErrorView$Listener;", "", "onChallengesErrorBackPressed", "", "onChallengesReloadButtonPressed", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Listener {
        void onChallengesErrorBackPressed();

        void onChallengesReloadButtonPressed();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivenessChallengesLoadingErrorView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    static final void lambda$2$lambda$0(LivenessChallengesLoadingErrorView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Listener listener = this$0.listener;
        if (listener != null) {
            listener.onChallengesReloadButtonPressed();
        }
    }

    static final void lambda$2$lambda$1(LivenessChallengesLoadingErrorView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Listener listener = this$0.listener;
        if (listener != null) {
            listener.onChallengesErrorBackPressed();
        }
    }

    public final void setListener(Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivenessChallengesLoadingErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivenessChallengesLoadingErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoLivenessChallengesLoadingErrorBinding onfidoLivenessChallengesLoadingErrorBindingInflate = OnfidoLivenessChallengesLoadingErrorBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoLivenessChallengesLoadingErrorBindingInflate, "inflate(...)");
        this.binding = onfidoLivenessChallengesLoadingErrorBindingInflate;
        onfidoLivenessChallengesLoadingErrorBindingInflate.reloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingErrorView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LivenessChallengesLoadingErrorView.lambda$2$lambda$0(this.f$0, view);
            }
        });
        onfidoLivenessChallengesLoadingErrorBindingInflate.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingErrorView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LivenessChallengesLoadingErrorView.lambda$2$lambda$1(this.f$0, view);
            }
        });
    }

    public /* synthetic */ LivenessChallengesLoadingErrorView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
