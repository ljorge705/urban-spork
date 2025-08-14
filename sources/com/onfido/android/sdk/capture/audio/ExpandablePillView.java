package com.onfido.android.sdk.capture.audio;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.listeners.AnimatorListener;
import io.sentry.Session;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 52\u00020\u0001:\u00015B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0!J\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020#J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H\u0014J\u0010\u0010(\u001a\u00020\u001d2\b\b\u0001\u0010)\u001a\u00020\u0007J&\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\u001e\u0010-\u001a\u00020\u001d2\b\b\u0002\u0010.\u001a\u00020#2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0!J\u0006\u0010/\u001a\u00020\u001dJ\f\u0010\"\u001a\u00020#*\u00020\u0018H\u0002J4\u00100\u001a\u00020\u001d*\u00020\u00182\b\b\u0002\u00101\u001a\u00020\u00152\b\b\u0002\u00102\u001a\u00020\u00152\b\b\u0002\u00103\u001a\u00020\u00152\b\b\u0002\u00104\u001a\u00020\u0015H\u0002R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001a\u0010\u0012¨\u00066"}, d2 = {"Lcom/onfido/android/sdk/capture/audio/ExpandablePillView;", "Landroid/view/View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "audioPillPaint", "Landroid/graphics/Paint;", "getAudioPillPaint", "()Landroid/graphics/Paint;", "audioPillPaint$delegate", "Lkotlin/Lazy;", "extraHeightAnimator", "Landroid/animation/ValueAnimator;", "getExtraHeightAnimator", "()Landroid/animation/ValueAnimator;", "extraHeightAnimator$delegate", "rx", "", "ry", "volumePillRect", "Landroid/graphics/RectF;", "volumePillWidthAnimator", "getVolumePillWidthAnimator", "volumePillWidthAnimator$delegate", "grow", "", "finalWidth", "finalHeight", "onAnimationFinished", "Lkotlin/Function0;", "hasWidth", "", "isAnimationInProgress", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setColor", "color", "setInitialShape", "width", "height", "shrink", "shouldSkipHeight", "stopAnimation", "setWithOptionals", "left", "top", ViewProps.RIGHT, ViewProps.BOTTOM, "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpandablePillView extends View {
    private static final Companion Companion = new Companion(null);
    private static final long WIDTH_ANIMATION_DURATION_MS = 300;

    /* renamed from: audioPillPaint$delegate, reason: from kotlin metadata */
    private final Lazy audioPillPaint;

    /* renamed from: extraHeightAnimator$delegate, reason: from kotlin metadata */
    private final Lazy extraHeightAnimator;
    private float rx;
    private float ry;
    private final RectF volumePillRect;

    /* renamed from: volumePillWidthAnimator$delegate, reason: from kotlin metadata */
    private final Lazy volumePillWidthAnimator;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/audio/ExpandablePillView$Companion;", "", "()V", "WIDTH_ANIMATION_DURATION_MS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExpandablePillView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final Paint getAudioPillPaint() {
        return (Paint) this.audioPillPaint.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ValueAnimator getExtraHeightAnimator() {
        return (ValueAnimator) this.extraHeightAnimator.getValue();
    }

    private final ValueAnimator getVolumePillWidthAnimator() {
        return (ValueAnimator) this.volumePillWidthAnimator.getValue();
    }

    private final void setWithOptionals(RectF rectF, float f, float f2, float f3, float f4) {
        rectF.set(f, f2, f3, f4);
    }

    static /* synthetic */ void setWithOptionals$default(ExpandablePillView expandablePillView, RectF rectF, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = rectF.left;
        }
        float f5 = f;
        if ((i & 2) != 0) {
            f2 = rectF.top;
        }
        float f6 = f2;
        if ((i & 4) != 0) {
            f3 = rectF.right;
        }
        float f7 = f3;
        if ((i & 8) != 0) {
            f4 = rectF.bottom;
        }
        expandablePillView.setWithOptionals(rectF, f5, f6, f7, f4);
    }

    public static /* synthetic */ void shrink$default(ExpandablePillView expandablePillView, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        expandablePillView.shrink(z, function0);
    }

    public final void grow(int finalWidth, final int finalHeight, final Function0<Unit> onAnimationFinished) {
        Intrinsics.checkNotNullParameter(onAnimationFinished, "onAnimationFinished");
        ValueAnimator volumePillWidthAnimator = getVolumePillWidthAnimator();
        volumePillWidthAnimator.removeAllListeners();
        volumePillWidthAnimator.setIntValues((int) this.volumePillRect.right, finalWidth);
        volumePillWidthAnimator.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$grow$1$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ValueAnimator extraHeightAnimator = this.this$0.getExtraHeightAnimator();
                ExpandablePillView expandablePillView = this.this$0;
                int i = finalHeight;
                final Function0<Unit> function0 = onAnimationFinished;
                extraHeightAnimator.removeAllListeners();
                extraHeightAnimator.setIntValues((int) expandablePillView.volumePillRect.bottom, i);
                extraHeightAnimator.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$grow$1$1$onAnimationEnd$1$1
                    @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
                    }

                    @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation2) {
                        Intrinsics.checkNotNullParameter(animation2, "animation");
                        function0.invoke();
                    }

                    @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                        AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
                    }

                    @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
                    }
                });
                extraHeightAnimator.start();
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
            }
        });
        volumePillWidthAnimator.start();
    }

    public final boolean hasWidth() {
        return hasWidth(this.volumePillRect);
    }

    public final boolean isAnimationInProgress() {
        return getVolumePillWidthAnimator().isRunning() || getExtraHeightAnimator().isRunning();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (hasWidth(this.volumePillRect)) {
            canvas.drawRoundRect(this.volumePillRect, this.rx, this.ry, getAudioPillPaint());
        }
    }

    public final void setColor(int color) {
        getAudioPillPaint().setColor(color);
        invalidate();
    }

    public final void setInitialShape(float width, float height, float rx, float ry) {
        RectF rectF = this.volumePillRect;
        rectF.right = width;
        rectF.bottom = height;
        this.rx = rx;
        this.ry = ry;
    }

    public final void shrink(boolean shouldSkipHeight, final Function0<Unit> onAnimationFinished) {
        Intrinsics.checkNotNullParameter(onAnimationFinished, "onAnimationFinished");
        final ValueAnimator volumePillWidthAnimator = getVolumePillWidthAnimator();
        volumePillWidthAnimator.removeAllListeners();
        volumePillWidthAnimator.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$shrink$widthAnimation$1$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ExpandablePillView expandablePillView = this.this$0;
                ExpandablePillView.setWithOptionals$default(expandablePillView, expandablePillView.volumePillRect, 0.0f, 0.0f, 0.0f, 0.0f, 11, null);
                onAnimationFinished.invoke();
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
            }
        });
        if (shouldSkipHeight) {
            volumePillWidthAnimator.reverse();
            return;
        }
        ValueAnimator extraHeightAnimator = getExtraHeightAnimator();
        extraHeightAnimator.removeAllListeners();
        extraHeightAnimator.addListener(new AnimatorListener() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$shrink$1$1
            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationCancel(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                volumePillWidthAnimator.reverse();
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationRepeat(this, animator);
            }

            @Override // com.onfido.android.sdk.capture.utils.listeners.AnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimatorListener.DefaultImpls.onAnimationStart(this, animator);
            }
        });
        extraHeightAnimator.reverse();
    }

    public final void stopAnimation() {
        if (getVolumePillWidthAnimator().isRunning()) {
            getVolumePillWidthAnimator().removeAllListeners();
            getVolumePillWidthAnimator().cancel();
        }
        if (getExtraHeightAnimator().isRunning()) {
            getExtraHeightAnimator().removeAllListeners();
            getExtraHeightAnimator().cancel();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExpandablePillView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final boolean hasWidth(RectF rectF) {
        return rectF.right > 0.0f;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpandablePillView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.volumePillRect = new RectF();
        this.audioPillPaint = LazyKt.lazy(new Function0<Paint>() { // from class: com.onfido.android.sdk.capture.audio.ExpandablePillView$audioPillPaint$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                Paint paint = new Paint();
                Context context2 = context;
                paint.setAntiAlias(true);
                paint.setColor(ContextUtilsKt.colorFromAttr(context2, R.attr.onfidoColorActionMain));
                return paint;
            }
        });
        this.volumePillWidthAnimator = LazyKt.lazy(new ExpandablePillView$volumePillWidthAnimator$2(this));
        this.extraHeightAnimator = LazyKt.lazy(new ExpandablePillView$extraHeightAnimator$2(this));
        setWillNotDraw(false);
    }

    public /* synthetic */ ExpandablePillView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
