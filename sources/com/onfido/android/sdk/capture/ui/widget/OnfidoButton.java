package com.onfido.android.sdk.capture.ui.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoButtonBinding;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import io.sentry.Session;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0002J\u0010\u00101\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0002J\b\u00102\u001a\u00020\u0016H\u0016J0\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u0007H\u0014J\u0018\u0010:\u001a\u0002042\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u0007H\u0014J\u0010\u0010=\u001a\u0002042\u0006\u0010>\u001a\u00020\u0007H\u0016J\u0010\u0010?\u001a\u0002042\u0006\u0010@\u001a\u00020\u0016H\u0016J\u0015\u0010A\u001a\u0002042\u0006\u0010B\u001a\u00020\u0007H\u0000¢\u0006\u0002\bCJ\u0012\u0010D\u001a\u0002042\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0017\u0010G\u001a\u0002042\b\b\u0001\u0010H\u001a\u00020\u0007H\u0000¢\u0006\u0002\bIJ\u0012\u0010G\u001a\u0002042\b\u0010J\u001a\u0004\u0018\u00010KH\u0007R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0014R\u0014\u0010#\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001dR\u0014\u0010%\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0014R\u0014\u0010'\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0014R\u0014\u0010)\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u001dR\u0014\u0010+\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u001dR\u0014\u0010-\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0014¨\u0006M"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/OnfidoButton;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "appCompatButton", "Landroidx/appcompat/widget/AppCompatButton;", "getAppCompatButton", "()Landroidx/appcompat/widget/AppCompatButton;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoButtonBinding;", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoButtonBinding;", "currentSystemFontScale", "", "getCurrentSystemFontScale", "()F", "enableForceHeight", "", "getEnableForceHeight$onfido_capture_sdk_core_release", "()Z", "setEnableForceHeight$onfido_capture_sdk_core_release", "(Z)V", "forcedHeight", "getForcedHeight$onfido_capture_sdk_core_release", "()I", "setForcedHeight$onfido_capture_sdk_core_release", "(I)V", ViewProps.MAX_HEIGHT, "scaledDensity", "getScaledDensity", "textHorizontalPadding", "getTextHorizontalPadding", "textLineSpacingAdd", "getTextLineSpacingAdd", "textSize", "getTextSize", "textVerticalPadding", "getTextVerticalPadding", "textWidth", "getTextWidth", "titleLineHeight", "getTitleLineHeight", "atMostDimen", RRWebVideoEvent.JsonKeys.SIZE, "exactDimen", "isEnabled", ViewProps.ON_LAYOUT, "", "changed", "left", "top", ViewProps.RIGHT, ViewProps.BOTTOM, "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "sendAccessibilityEvent", "eventType", "setEnabled", ViewProps.ENABLED, "setMaxHeight", "height", "setMaxHeight$onfido_capture_sdk_core_release", "setOnClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setText", "stringResId", "setText$onfido_capture_sdk_core_release", "text", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoButton extends FrameLayout {
    private static final int BACKGROUND_ALPHA_NON_PRESSED_STATE_HEX = 255;
    private static final int BACKGROUND_ALPHA_PRESSED_STATE_HEX = 85;
    private static final Companion Companion = new Companion(null);
    private static final float DEFAULT_SYSTEM_FONT_SIZE_SCALE = 1.0f;
    private static final int PRIMARY_BUTTON_FLAG = 0;
    private static final int SECONDARY_BUTTON_FLAG = 1;
    private final OnfidoButtonBinding binding;
    private boolean enableForceHeight;
    private int forcedHeight;
    private int maxHeight;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/widget/OnfidoButton$Companion;", "", "()V", "BACKGROUND_ALPHA_NON_PRESSED_STATE_HEX", "", "BACKGROUND_ALPHA_PRESSED_STATE_HEX", "DEFAULT_SYSTEM_FONT_SIZE_SCALE", "", "PRIMARY_BUTTON_FLAG", "SECONDARY_BUTTON_FLAG", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OnfidoButton(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final int atMostDimen(int size) {
        return View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
    }

    private final int exactDimen(int size) {
        return View.MeasureSpec.makeMeasureSpec(size, 1073741824);
    }

    private final float getCurrentSystemFontScale() {
        return getResources().getConfiguration().fontScale;
    }

    private final float getScaledDensity() {
        return getResources().getDisplayMetrics().scaledDensity;
    }

    private final int getTextHorizontalPadding() {
        return getAppCompatButton().getPaddingLeft() + getAppCompatButton().getPaddingRight();
    }

    private final float getTextLineSpacingAdd() {
        return getAppCompatButton().getLineSpacingExtra();
    }

    private final float getTextSize() {
        return getAppCompatButton().getTextSize() / getScaledDensity();
    }

    private final int getTextVerticalPadding() {
        return getAppCompatButton().getPaddingTop() + getAppCompatButton().getPaddingBottom();
    }

    private final int getTextWidth() {
        return ViewExtensionsKt.getTextPixelsWidth(getAppCompatButton(), getTextSize());
    }

    private final float getTitleLineHeight() {
        return getAppCompatButton().getPaint().getFontMetrics().bottom - getAppCompatButton().getPaint().getFontMetrics().top;
    }

    static final boolean lambda$1$lambda$0(OnfidoButtonBinding this_apply, Context context, View view, MotionEvent motionEvent) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNull(motionEvent);
        Intrinsics.checkNotNull(view);
        if (ViewExtensionsKt.isPressedAndContainedInView(motionEvent, view)) {
            i = R.drawable.onfido_bg_button_secondary_pressed;
            i2 = 85;
        } else {
            i = R.drawable.onfido_bg_button_secondary_unpressed;
            i2 = 255;
        }
        this_apply.appCompatButton.setBackground(ContextCompat.getDrawable(context, i));
        this_apply.appCompatButton.getBackground().mutate().setAlpha(i2);
        return false;
    }

    public final AppCompatButton getAppCompatButton() {
        AppCompatButton appCompatButton = this.binding.appCompatButton;
        Intrinsics.checkNotNullExpressionValue(appCompatButton, "appCompatButton");
        return appCompatButton;
    }

    public final OnfidoButtonBinding getBinding() {
        return this.binding;
    }

    /* renamed from: getEnableForceHeight$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getEnableForceHeight() {
        return this.enableForceHeight;
    }

    /* renamed from: getForcedHeight$onfido_capture_sdk_core_release, reason: from getter */
    public final int getForcedHeight() {
        return this.forcedHeight;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return getAppCompatButton().isEnabled();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getAppCompatButton().layout(0, 0, right - left, bottom - top);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int textWidth = View.MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE ? getTextWidth() + getTextHorizontalPadding() : View.MeasureSpec.getSize(widthMeasureSpec);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iDimen = ContextUtilsKt.dimen(context, R.dimen.onfido_flat_button_height);
        if (getCurrentSystemFontScale() > 1.0f) {
            int iScreenHeight = this.maxHeight;
            if (iScreenHeight < 0) {
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                iScreenHeight = ContextUtilsKt.screenHeight(context2);
            }
            getAppCompatButton().setMaxLines((int) ((iScreenHeight - getTextVerticalPadding()) / (getTitleLineHeight() + getTextLineSpacingAdd())));
            Layout layout = getAppCompatButton().getLayout();
            iDimen = Math.max(iDimen, (layout != null ? layout.getHeight() : iDimen) + getTextVerticalPadding());
        }
        if (!this.enableForceHeight || (i = this.forcedHeight) == 0) {
            setMeasuredDimension(textWidth, iDimen);
            return;
        }
        setMeasuredDimension(textWidth, i);
        int iExactDimen = exactDimen(this.forcedHeight);
        FrameLayout frameLayout = this.binding.root;
        frameLayout.measure(atMostDimen(frameLayout.getWidth()), iExactDimen);
        getAppCompatButton().measure(atMostDimen(getAppCompatButton().getWidth()), iExactDimen);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int eventType) {
        getAppCompatButton().sendAccessibilityEvent(eventType);
    }

    public final void setEnableForceHeight$onfido_capture_sdk_core_release(boolean z) {
        this.enableForceHeight = z;
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        getAppCompatButton().setEnabled(enabled);
    }

    public final void setForcedHeight$onfido_capture_sdk_core_release(int i) {
        this.forcedHeight = i;
    }

    public final void setMaxHeight$onfido_capture_sdk_core_release(int height) {
        this.maxHeight = height;
        requestLayout();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener listener) {
        super.setOnClickListener(listener);
        getAppCompatButton().setOnClickListener(listener);
    }

    public final void setText(String text) {
        getAppCompatButton().setText(text);
    }

    public final void setText$onfido_capture_sdk_core_release(int stringResId) {
        getAppCompatButton().setText(getContext().getText(stringResId));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OnfidoButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OnfidoButton(final android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r7.<init>(r8, r9, r10)
            r10 = -1
            r7.maxHeight = r10
            int[] r10 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton
            android.content.res.TypedArray r9 = r8.obtainStyledAttributes(r9, r10)
            java.lang.String r10 = "obtainStyledAttributes(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            int r10 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton_onfidoButtonType
            r0 = 0
            int r10 = r9.getInt(r10, r0)
            int r0 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton_onfidoButtonText
            java.lang.String r0 = r9.getString(r0)
            int r1 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton_onfidoButtonTextColor
            android.content.res.ColorStateList r1 = r9.getColorStateList(r1)
            r2 = 1
            if (r10 == 0) goto L4a
            if (r10 == r2) goto L41
            int r3 = com.onfido.android.sdk.capture.R.attr.onfidoColorActionMain
            int r3 = com.onfido.android.sdk.capture.utils.ContextUtilsKt.colorFromAttr(r8, r3)
            int r4 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton_onfidoButtonTextColor
            int r3 = r9.getColor(r4, r3)
            int r4 = com.onfido.android.sdk.capture.R.styleable.OnfidoButton_onfidoBackgroundDrawable
            android.graphics.drawable.Drawable r4 = r9.getDrawable(r4)
            goto L56
        L41:
            int r3 = com.onfido.android.sdk.capture.R.attr.onfidoColorContentOnActionSecondary
            int r3 = com.onfido.android.sdk.capture.utils.ContextUtilsKt.colorFromAttr(r8, r3)
            int r4 = com.onfido.android.sdk.capture.R.drawable.onfido_bg_button_secondary
            goto L52
        L4a:
            int r3 = com.onfido.android.sdk.capture.R.attr.onfidoColorContentOnAction
            int r3 = com.onfido.android.sdk.capture.utils.ContextUtilsKt.colorFromAttr(r8, r3)
            int r4 = com.onfido.android.sdk.capture.R.drawable.onfido_bg_button_primary
        L52:
            android.graphics.drawable.Drawable r4 = androidx.core.content.ContextCompat.getDrawable(r8, r4)
        L56:
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r8)
            com.onfido.android.sdk.capture.databinding.OnfidoButtonBinding r5 = com.onfido.android.sdk.capture.databinding.OnfidoButtonBinding.inflate(r5, r7, r2)
            java.lang.String r6 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r7.binding = r5
            if (r0 == 0) goto L6c
            androidx.appcompat.widget.AppCompatButton r6 = r5.appCompatButton
            r6.setText(r0)
        L6c:
            androidx.appcompat.widget.AppCompatButton r0 = r5.appCompatButton
            if (r1 == 0) goto L74
            r0.setTextColor(r1)
            goto L77
        L74:
            r0.setTextColor(r3)
        L77:
            float r0 = r7.getCurrentSystemFontScale()
            r1 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L86
            androidx.appcompat.widget.AppCompatButton r0 = r5.appCompatButton
            r0.setSingleLine()
        L86:
            androidx.appcompat.widget.AppCompatButton r0 = r5.appCompatButton
            r0.setBackground(r4)
            if (r10 != r2) goto L97
            com.onfido.android.sdk.capture.ui.widget.OnfidoButton$$ExternalSyntheticLambda0 r10 = new com.onfido.android.sdk.capture.ui.widget.OnfidoButton$$ExternalSyntheticLambda0
            r10.<init>()
            androidx.appcompat.widget.AppCompatButton r8 = r5.appCompatButton
            r8.setOnTouchListener(r10)
        L97:
            r9.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.widget.OnfidoButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public /* synthetic */ OnfidoButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
