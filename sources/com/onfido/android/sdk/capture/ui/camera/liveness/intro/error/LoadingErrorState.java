package com.onfido.android.sdk.capture.ui.camera.liveness.intro.error;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.databinding.OnfidoLoadingErrorBinding;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LoadingErrorState;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoLoadingErrorBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LoadingErrorState$LoadingErrorStateListener;", "setErrorMessage", "", "message", "setListener", "Companion", "LoadingErrorStateListener", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LoadingErrorState extends LinearLayout {
    private static final float RELOAD_BUTTON_BACKGROUND_ALPHA_PRESSED_STATE = 0.3f;
    private final OnfidoLoadingErrorBinding binding;
    private LoadingErrorStateListener listener;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LoadingErrorState$LoadingErrorStateListener;", "", "onReloadPressed", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface LoadingErrorStateListener {
        void onReloadPressed();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingErrorState(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    static final void lambda$2$lambda$0(LoadingErrorState this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LoadingErrorStateListener loadingErrorStateListener = this$0.listener;
        if (loadingErrorStateListener != null) {
            loadingErrorStateListener.onReloadPressed();
        }
    }

    static final boolean lambda$2$lambda$1(OnfidoLoadingErrorBinding this_apply, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        this_apply.reloadButtonBackground.setAlpha((numValueOf != null && numValueOf.intValue() == 0) ? RELOAD_BUTTON_BACKGROUND_ALPHA_PRESSED_STATE : 1.0f);
        return false;
    }

    public final void setErrorMessage(int message) {
        this.binding.errorMessage.setText(getResources().getString(message));
    }

    public final void setListener(LoadingErrorStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LoadingErrorState(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingErrorState(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        final OnfidoLoadingErrorBinding onfidoLoadingErrorBindingInflate = OnfidoLoadingErrorBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoLoadingErrorBindingInflate, "inflate(...)");
        this.binding = onfidoLoadingErrorBindingInflate;
        onfidoLoadingErrorBindingInflate.reloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LoadingErrorState$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoadingErrorState.lambda$2$lambda$0(this.f$0, view);
            }
        });
        onfidoLoadingErrorBindingInflate.reloadButtonBackground.setOnTouchListener(new View.OnTouchListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.error.LoadingErrorState$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LoadingErrorState.lambda$2$lambda$1(onfidoLoadingErrorBindingInflate, view, motionEvent);
            }
        });
    }

    public /* synthetic */ LoadingErrorState(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
