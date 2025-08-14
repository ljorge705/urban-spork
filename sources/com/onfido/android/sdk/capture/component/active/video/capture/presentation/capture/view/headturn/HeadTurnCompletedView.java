package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.ViewExtensionsKt;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcViewHeadTurnCompletedBinding;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/HeadTurnCompletedView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcViewHeadTurnCompletedBinding;", "onSizeChanged", "", Constants.INAPP_WINDOW, "h", "oldw", "oldh", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnCompletedView extends FrameLayout {
    private final OnfidoAvcViewHeadTurnCompletedBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedView(Context context) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnCompletedBinding onfidoAvcViewHeadTurnCompletedBindingInflate = OnfidoAvcViewHeadTurnCompletedBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnCompletedBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnCompletedBindingInflate;
        onfidoAvcViewHeadTurnCompletedBindingInflate.watermark.setDarkBackground();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) throws Resources.NotFoundException {
        TextView instructionTextView = this.binding.instructionTextView;
        Intrinsics.checkNotNullExpressionValue(instructionTextView, "instructionTextView");
        ViewExtensionsKt.setOvalMargin$default(instructionTextView, getWidth(), getHeight(), 0, 4, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedView(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnCompletedBinding onfidoAvcViewHeadTurnCompletedBindingInflate = OnfidoAvcViewHeadTurnCompletedBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnCompletedBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnCompletedBindingInflate;
        onfidoAvcViewHeadTurnCompletedBindingInflate.watermark.setDarkBackground();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnCompletedView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnCompletedBinding onfidoAvcViewHeadTurnCompletedBindingInflate = OnfidoAvcViewHeadTurnCompletedBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnCompletedBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnCompletedBindingInflate;
        onfidoAvcViewHeadTurnCompletedBindingInflate.watermark.setDarkBackground();
    }
}
