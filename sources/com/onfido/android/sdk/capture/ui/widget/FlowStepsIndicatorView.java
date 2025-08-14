package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoViewFlowStepIndicatorBinding;
import io.sentry.Session;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/FlowStepsIndicatorView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoViewFlowStepIndicatorBinding;", "setStep", "", "stepNumber", "totalSteps", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FlowStepsIndicatorView extends FrameLayout {
    private final OnfidoViewFlowStepIndicatorBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlowStepsIndicatorView(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void setStep(int stepNumber, int totalSteps) {
        TextView textView = this.binding.stepsIndicatorText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getContext().getString(R.string.onfido_doc_video_capture_stepper);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(stepNumber), Integer.valueOf(totalSteps)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlowStepsIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowStepsIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoViewFlowStepIndicatorBinding onfidoViewFlowStepIndicatorBindingInflate = OnfidoViewFlowStepIndicatorBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoViewFlowStepIndicatorBindingInflate, "inflate(...)");
        this.binding = onfidoViewFlowStepIndicatorBindingInflate;
    }
}
