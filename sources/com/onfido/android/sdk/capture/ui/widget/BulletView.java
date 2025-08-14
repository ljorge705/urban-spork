package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoBulletViewBinding;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/BulletView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoBulletViewBinding;", "setMaxLines", "", "line", "", "setStepTitle", "title", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BulletView extends RelativeLayout {
    private final OnfidoBulletViewBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BulletView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoBulletViewBinding onfidoBulletViewBindingInflate = OnfidoBulletViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoBulletViewBindingInflate, "inflate(...)");
        this.binding = onfidoBulletViewBindingInflate;
    }

    public final void setMaxLines(int line) {
        this.binding.bulletTitle.setMaxLines(line);
    }

    public final void setStepTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.binding.bulletTitle.setText(title);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        final int textSize = (((int) (this.binding.bulletTitle.getTextSize() + this.binding.bulletTitle.getLineSpacingExtra())) / 2) - (ContextUtilsKt.dimenInt(context, R.dimen.onfido_liveness_intro_video_bullet_size) / 2);
        View bullet = this.binding.bullet;
        Intrinsics.checkNotNullExpressionValue(bullet, "bullet");
        ViewExtensionsKt.changeLayoutParams(bullet, new Function1<ViewGroup.LayoutParams, Unit>() { // from class: com.onfido.android.sdk.capture.ui.widget.BulletView.setStepTitle.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup.LayoutParams layoutParams) {
                invoke2(layoutParams);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup.LayoutParams it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) it;
                layoutParams.setMargins(layoutParams.leftMargin, textSize, layoutParams.rightMargin, layoutParams.bottomMargin);
            }
        });
    }
}
