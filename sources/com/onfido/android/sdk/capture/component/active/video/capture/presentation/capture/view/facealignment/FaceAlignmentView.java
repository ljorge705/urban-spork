package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcViewFaceAlignmentBinding;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/facealignment/FaceAlignmentView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceAlignmentView extends FrameLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceAlignmentView(Context context) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFaceAlignmentBinding.inflate(LayoutInflater.from(getContext()), this).watermark.setDarkBackground();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceAlignmentView(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFaceAlignmentBinding.inflate(LayoutInflater.from(getContext()), this).watermark.setDarkBackground();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceAlignmentView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewFaceAlignmentBinding.inflate(LayoutInflater.from(getContext()), this).watermark.setDarkBackground();
    }
}
