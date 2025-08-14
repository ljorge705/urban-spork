package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalAlignedView;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcViewFeedbackLabelBinding;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/facealignment/FeedbackLabelView;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/util/OvalAlignedView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcViewFeedbackLabelBinding;", "setText", "", "value", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FeedbackLabelView extends OvalAlignedView {
    private final OnfidoAvcViewFeedbackLabelBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackLabelView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFeedbackLabelBinding onfidoAvcViewFeedbackLabelBindingInflate = OnfidoAvcViewFeedbackLabelBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewFeedbackLabelBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewFeedbackLabelBindingInflate;
    }

    public final void setText(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.binding.feedbackTextView.setText(value);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackLabelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFeedbackLabelBinding onfidoAvcViewFeedbackLabelBindingInflate = OnfidoAvcViewFeedbackLabelBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewFeedbackLabelBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewFeedbackLabelBindingInflate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackLabelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFeedbackLabelBinding onfidoAvcViewFeedbackLabelBindingInflate = OnfidoAvcViewFeedbackLabelBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewFeedbackLabelBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewFeedbackLabelBindingInflate;
    }
}
