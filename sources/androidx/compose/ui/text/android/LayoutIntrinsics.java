package androidx.compose.ui.text.android;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutIntrinsics.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001d\u0010\t\u001a\u0004\u0018\u00010\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0015\u0010\u0012¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/text/android/LayoutIntrinsics;", "", "charSequence", "", "textPaint", "Landroid/text/TextPaint;", "textDirectionHeuristic", "", "(Ljava/lang/CharSequence;Landroid/text/TextPaint;I)V", "boringMetrics", "Landroid/text/BoringLayout$Metrics;", "getBoringMetrics", "()Landroid/text/BoringLayout$Metrics;", "boringMetrics$delegate", "Lkotlin/Lazy;", "maxIntrinsicWidth", "", "getMaxIntrinsicWidth", "()F", "maxIntrinsicWidth$delegate", "minIntrinsicWidth", "getMinIntrinsicWidth", "minIntrinsicWidth$delegate", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutIntrinsics {
    public static final int $stable = 8;

    /* renamed from: boringMetrics$delegate, reason: from kotlin metadata */
    private final Lazy boringMetrics;

    /* renamed from: maxIntrinsicWidth$delegate, reason: from kotlin metadata */
    private final Lazy maxIntrinsicWidth;

    /* renamed from: minIntrinsicWidth$delegate, reason: from kotlin metadata */
    private final Lazy minIntrinsicWidth;

    public LayoutIntrinsics(final CharSequence charSequence, final TextPaint textPaint, final int i) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        this.boringMetrics = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<BoringLayout.Metrics>() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$boringMetrics$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BoringLayout.Metrics invoke() {
                return BoringLayoutFactory.INSTANCE.measure(charSequence, textPaint, TextLayoutKt.getTextDirectionHeuristic(i));
            }
        });
        this.minIntrinsicWidth = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Float>() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$minIntrinsicWidth$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(LayoutIntrinsicsKt.minIntrinsicWidth(charSequence, textPaint));
            }
        });
        this.maxIntrinsicWidth = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Float>() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics$maxIntrinsicWidth$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                Float fValueOf = this.this$0.getBoringMetrics() != null ? Float.valueOf(r0.width) : null;
                if (fValueOf == null) {
                    CharSequence charSequence2 = charSequence;
                    fValueOf = Float.valueOf((float) Math.ceil(Layout.getDesiredWidth(charSequence2, 0, charSequence2.length(), textPaint)));
                }
                return LayoutIntrinsicsKt.shouldIncreaseMaxIntrinsic(fValueOf.floatValue(), charSequence, textPaint) ? Float.valueOf(fValueOf.floatValue() + 0.5f) : fValueOf;
            }
        });
    }

    public final BoringLayout.Metrics getBoringMetrics() {
        return (BoringLayout.Metrics) this.boringMetrics.getValue();
    }

    public final float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth.getValue()).floatValue();
    }

    public final float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth.getValue()).floatValue();
    }
}
