package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.databinding.OnfidoBulletStepViewBinding;
import com.onfido.android.sdk.capture.ui.userconsent.htmlutil.TextViewExtensionKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J(\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0015J\u0006\u0010\u001d\u001a\u00020\u0013J\u0006\u0010\u001e\u001a\u00020\u0013J\u0010\u0010\u001f\u001a\u00020\u00132\b\b\u0001\u0010 \u001a\u00020\u0007J\u0016\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\b\b\u0001\u0010 \u001a\u00020\u0007J\u000e\u0010%\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/BulletStepView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoBulletStepViewBinding;", "stepNumberHorizontalPadding", "getStepNumberHorizontalPadding", "()I", "stepNumberLayoutParams", "Landroid/widget/RelativeLayout$LayoutParams;", "getStepNumberLayoutParams", "()Landroid/widget/RelativeLayout$LayoutParams;", "adjustInfoAlignment", "", "isIcon", "", "changeBottomSeparatorDimensions", "width", "height", ViewProps.MARGIN_TOP, ViewProps.MARGIN_LEFT, "hideSeparator", "isTop", "hideSeparators", "setHtmlTitle", "setIcon", "iconRes", "setStepInfo", CTVariableUtils.NUMBER, "title", "", "setStepNumber", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BulletStepView extends RelativeLayout {
    private final OnfidoBulletStepViewBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BulletStepView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void adjustInfoAlignment(boolean isIcon) {
        final int textPixelsWidth$default;
        final int iRound;
        View view;
        String str;
        if (isIcon) {
            ViewGroup.LayoutParams layoutParams = this.binding.stepIcon.getLayoutParams();
            int i = layoutParams.width;
            int i2 = layoutParams.height;
            double d = 2;
            textPixelsWidth$default = (int) Math.ceil((Math.sqrt((i * i) + (i2 * i2)) / d) * d);
        } else {
            TextView stepNumber = this.binding.stepNumber;
            Intrinsics.checkNotNullExpressionValue(stepNumber, "stepNumber");
            textPixelsWidth$default = ViewExtensionsKt.getTextPixelsWidth$default(stepNumber, 0.0f, 1, null) + getStepNumberHorizontalPadding();
        }
        TextView stepNumber2 = this.binding.stepNumber;
        Intrinsics.checkNotNullExpressionValue(stepNumber2, "stepNumber");
        ViewExtensionsKt.changeLayoutParams(stepNumber2, new Function1<ViewGroup.LayoutParams, Unit>() { // from class: com.onfido.android.sdk.capture.ui.BulletStepView.adjustInfoAlignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup.LayoutParams layoutParams2) {
                invoke2(layoutParams2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup.LayoutParams it) {
                Intrinsics.checkNotNullParameter(it, "it");
                int i3 = textPixelsWidth$default;
                new RelativeLayout.LayoutParams(i3, i3).addRule(3, this.binding.topSeparator.getId());
            }
        });
        float f = this.binding.stepTitle.getPaint().getFontMetrics().descent - this.binding.stepTitle.getPaint().getFontMetrics().ascent;
        float f2 = textPixelsWidth$default;
        if (f > f2) {
            iRound = Math.round((f - f2) / 2.0f);
            view = this.binding.stepNumberContainer;
            str = "stepNumberContainer";
        } else {
            iRound = Math.round((f2 - f) / 2.0f);
            view = this.binding.stepTitle;
            str = "stepTitle";
        }
        Intrinsics.checkNotNullExpressionValue(view, str);
        ViewExtensionsKt.changeLayoutParams(view, new Function1<ViewGroup.LayoutParams, Unit>() { // from class: com.onfido.android.sdk.capture.ui.BulletStepView.adjustInfoAlignment.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewGroup.LayoutParams layoutParams2) {
                invoke2(layoutParams2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewGroup.LayoutParams it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) it;
                layoutParams2.setMargins(layoutParams2.leftMargin, iRound, layoutParams2.rightMargin, layoutParams2.bottomMargin);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeBottomSeparatorDimensions(int width, int height, int marginTop, int marginLeft) {
        View view = this.binding.bottomSeparator;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(3, this.binding.stepNumberContainer.getId());
        layoutParams.setMargins(marginLeft, marginTop, 0, 0);
        view.setLayoutParams(layoutParams);
    }

    private final int getStepNumberHorizontalPadding() {
        return getStepNumberLayoutParams().leftMargin + getStepNumberLayoutParams().rightMargin;
    }

    private final RelativeLayout.LayoutParams getStepNumberLayoutParams() {
        ViewGroup.LayoutParams layoutParams = this.binding.stepNumber.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        return (RelativeLayout.LayoutParams) layoutParams;
    }

    public final void hideSeparator(boolean isTop) {
        View view;
        String str;
        if (isTop) {
            view = this.binding.topSeparator;
            str = "topSeparator";
        } else {
            view = this.binding.bottomSeparator;
            str = "bottomSeparator";
        }
        Intrinsics.checkNotNullExpressionValue(view, str);
        ViewExtensionsKt.toGone$default(view, false, 1, null);
    }

    public final void hideSeparators() {
        hideSeparator(true);
        hideSeparator(false);
    }

    public final void setHtmlTitle() {
        TextView stepTitle = this.binding.stepTitle;
        Intrinsics.checkNotNullExpressionValue(stepTitle, "stepTitle");
        CharSequence text = this.binding.stepTitle.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type kotlin.String");
        TextViewExtensionKt.setTextFromHtml(stepTitle, (String) text);
    }

    public final void setIcon(int iconRes) {
        TextView stepNumber = this.binding.stepNumber;
        Intrinsics.checkNotNullExpressionValue(stepNumber, "stepNumber");
        ViewExtensionsKt.toGone$default(stepNumber, false, 1, null);
        ImageView stepIcon = this.binding.stepIcon;
        Intrinsics.checkNotNullExpressionValue(stepIcon, "stepIcon");
        ViewExtensionsKt.toVisible$default(stepIcon, false, 1, null);
        this.binding.stepIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), iconRes));
    }

    public final void setStepInfo(int number, String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.binding.stepNumber.setText(String.valueOf(number));
        this.binding.stepTitle.setText(title);
        adjustInfoAlignment(false);
    }

    public final void setStepNumber(int number) {
        this.binding.stepNumber.setText(String.valueOf(number));
        TextView stepNumber = this.binding.stepNumber;
        Intrinsics.checkNotNullExpressionValue(stepNumber, "stepNumber");
        ViewExtensionsKt.toVisible$default(stepNumber, false, 1, null);
        ImageView stepIcon = this.binding.stepIcon;
        Intrinsics.checkNotNullExpressionValue(stepIcon, "stepIcon");
        ViewExtensionsKt.toGone$default(stepIcon, false, 1, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BulletStepView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void setStepInfo(String title, int iconRes) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.binding.stepTitle.setText(title);
        setIcon(iconRes);
        hideSeparators();
        adjustInfoAlignment(true);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BulletStepView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        final OnfidoBulletStepViewBinding onfidoBulletStepViewBindingInflate = OnfidoBulletStepViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoBulletStepViewBindingInflate, "inflate(...)");
        this.binding = onfidoBulletStepViewBindingInflate;
        View bottomSeparator = onfidoBulletStepViewBindingInflate.bottomSeparator;
        Intrinsics.checkNotNullExpressionValue(bottomSeparator, "bottomSeparator");
        ViewExtensionsKt.runOnMeasured(bottomSeparator, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.BulletStepView$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewGroup.LayoutParams layoutParams = onfidoBulletStepViewBindingInflate.bottomSeparator.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                int height = onfidoBulletStepViewBindingInflate.bottomSeparator.getHeight();
                int i2 = layoutParams2.width;
                int i3 = onfidoBulletStepViewBindingInflate.topSeparator.getLayoutParams().height;
                int i4 = layoutParams2.topMargin;
                int i5 = layoutParams2.leftMargin;
                if (height < i3) {
                    this.changeBottomSeparatorDimensions(i2, i3, i4, i5);
                }
            }
        });
    }

    public /* synthetic */ BulletStepView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
