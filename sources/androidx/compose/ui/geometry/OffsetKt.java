package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Offset.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a-\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a+\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0086\bø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\"!\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"!\u0010\u0007\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"!\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"isFinite", "", "Landroidx/compose/ui/geometry/Offset;", "isFinite-k-4lQ0M$annotations", "(J)V", "isFinite-k-4lQ0M", "(J)Z", "isSpecified", "isSpecified-k-4lQ0M$annotations", "isSpecified-k-4lQ0M", "isUnspecified", "isUnspecified-k-4lQ0M$annotations", "isUnspecified-k-4lQ0M", "Offset", "x", "", "y", "(FF)J", "lerp", ViewProps.START, "stop", "fraction", "lerp-Wko1d7g", "(JJF)J", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-3MmeM6k", "(JLkotlin/jvm/functions/Function0;)J", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OffsetKt {
    /* renamed from: isFinite-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m1657isFinitek4lQ0M$annotations(long j) {
    }

    /* renamed from: isSpecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m1659isSpecifiedk4lQ0M$annotations(long j) {
    }

    /* renamed from: isUnspecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m1661isUnspecifiedk4lQ0M$annotations(long j) {
    }

    /* renamed from: lerp-Wko1d7g, reason: not valid java name */
    public static final long m1662lerpWko1d7g(long j, long j2, float f) {
        return Offset(MathHelpersKt.lerp(Offset.m1639getXimpl(j), Offset.m1639getXimpl(j2), f), MathHelpersKt.lerp(Offset.m1640getYimpl(j), Offset.m1640getYimpl(j2), f));
    }

    /* renamed from: isFinite-k-4lQ0M, reason: not valid java name */
    public static final boolean m1656isFinitek4lQ0M(long j) {
        float fM1639getXimpl = Offset.m1639getXimpl(j);
        if (!Float.isInfinite(fM1639getXimpl) && !Float.isNaN(fM1639getXimpl)) {
            float fM1640getYimpl = Offset.m1640getYimpl(j);
            if (!Float.isInfinite(fM1640getYimpl) && !Float.isNaN(fM1640getYimpl)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: isSpecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m1658isSpecifiedk4lQ0M(long j) {
        return j != Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
    }

    /* renamed from: isUnspecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m1660isUnspecifiedk4lQ0M(long j) {
        return j == Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
    }

    /* renamed from: takeOrElse-3MmeM6k, reason: not valid java name */
    public static final long m1663takeOrElse3MmeM6k(long j, Function0<Offset> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return m1658isSpecifiedk4lQ0M(j) ? j : block.invoke().getPackedValue();
    }

    public static final long Offset(float f, float f2) {
        return Offset.m1631constructorimpl((Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32));
    }
}
