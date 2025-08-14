package androidx.compose.ui.unit;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;

/* compiled from: Dp.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0010\u001a\u00020\u0007H\u0087\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000bJ\u0019\u0010\u0012\u001a\u00020\u0007H\u0087\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u000bJ'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u001cH\u0087\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b#\u0010$J\u001e\u0010%\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u001e\u0010(\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010'J!\u0010*\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001bJ!\u0010*\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u001cH\u0087\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001dJ\u000f\u0010,\u001a\u00020-H\u0017¢\u0006\u0004\b.\u0010/R#\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\tR#\u0010\r\u001a\u00020\u00078FX\u0087\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\u0012\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000b\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00061"}, d2 = {"Landroidx/compose/ui/unit/DpSize;", "", "packedValue", "", "constructor-impl", "(J)J", "height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM$annotations", "()V", "getHeight-D9Ej5fM", "(J)F", "getPackedValue$annotations", "width", "getWidth-D9Ej5fM$annotations", "getWidth-D9Ej5fM", "component1", "component1-D9Ej5fM", "component2", "component2-D9Ej5fM", Constants.COPY_TYPE, "copy-DwJknco", "(JFF)J", "div", "other", "", "div-Gh9hcWk", "(JF)J", "", "(JI)J", "equals", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "(J)I", "minus", "minus-e_xh8Ic", "(JJ)J", "plus", "plus-e_xh8Ic", "times", "times-Gh9hcWk", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class DpSize {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Unspecified = DpKt.m4412DpSizeYgX7TsA(Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM(), Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM());
    private static final long Zero;
    private final long packedValue;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DpSize m4476boximpl(long j) {
        return new DpSize(j);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m4479constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4484equalsimpl(long j, Object obj) {
        return (obj instanceof DpSize) && j == ((DpSize) obj).getPackedValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4485equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getHeight-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m4487getHeightD9Ej5fM$annotations() {
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    /* renamed from: getWidth-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m4489getWidthD9Ej5fM$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4490hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object obj) {
        return m4484equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m4490hashCodeimpl(this.packedValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }

    private /* synthetic */ DpSize(long j) {
        this.packedValue = j;
    }

    /* renamed from: copy-DwJknco, reason: not valid java name */
    public static final long m4480copyDwJknco(long j, float f, float f2) {
        return DpKt.m4412DpSizeYgX7TsA(f, f2);
    }

    /* renamed from: copy-DwJknco$default, reason: not valid java name */
    public static /* synthetic */ long m4481copyDwJknco$default(long j, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = m4488getWidthD9Ej5fM(j);
        }
        if ((i & 2) != 0) {
            f2 = m4486getHeightD9Ej5fM(j);
        }
        return m4480copyDwJknco(j, f, f2);
    }

    /* renamed from: minus-e_xh8Ic, reason: not valid java name */
    public static final long m4491minuse_xh8Ic(long j, long j2) {
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) - m4488getWidthD9Ej5fM(j2)), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) - m4486getHeightD9Ej5fM(j2)));
    }

    /* renamed from: plus-e_xh8Ic, reason: not valid java name */
    public static final long m4492pluse_xh8Ic(long j, long j2) {
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) + m4488getWidthD9Ej5fM(j2)), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) + m4486getHeightD9Ej5fM(j2)));
    }

    /* renamed from: component1-D9Ej5fM, reason: not valid java name */
    public static final float m4477component1D9Ej5fM(long j) {
        return m4488getWidthD9Ej5fM(j);
    }

    /* renamed from: component2-D9Ej5fM, reason: not valid java name */
    public static final float m4478component2D9Ej5fM(long j) {
        return m4486getHeightD9Ej5fM(j);
    }

    /* renamed from: times-Gh9hcWk, reason: not valid java name */
    public static final long m4494timesGh9hcWk(long j, int i) {
        float f = i;
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) * f), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) * f));
    }

    /* renamed from: times-Gh9hcWk, reason: not valid java name */
    public static final long m4493timesGh9hcWk(long j, float f) {
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) * f), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) * f));
    }

    /* renamed from: div-Gh9hcWk, reason: not valid java name */
    public static final long m4483divGh9hcWk(long j, int i) {
        float f = i;
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) / f), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) / f));
    }

    /* renamed from: div-Gh9hcWk, reason: not valid java name */
    public static final long m4482divGh9hcWk(long j, float f) {
        return DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(m4488getWidthD9Ej5fM(j) / f), Dp.m4390constructorimpl(m4486getHeightD9Ej5fM(j) / f));
    }

    public String toString() {
        return m4495toStringimpl(this.packedValue);
    }

    /* compiled from: Dp.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\n"}, d2 = {"Landroidx/compose/ui/unit/DpSize$Companion;", "", "()V", "Unspecified", "Landroidx/compose/ui/unit/DpSize;", "getUnspecified-MYxV2XQ", "()J", "J", "Zero", "getZero-MYxV2XQ", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getZero-MYxV2XQ, reason: not valid java name */
        public final long m4498getZeroMYxV2XQ() {
            return DpSize.Zero;
        }

        /* renamed from: getUnspecified-MYxV2XQ, reason: not valid java name */
        public final long m4497getUnspecifiedMYxV2XQ() {
            return DpSize.Unspecified;
        }
    }

    /* renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public static final float m4488getWidthD9Ej5fM(long j) {
        if (j == Unspecified) {
            throw new IllegalStateException("DpSize is unspecified".toString());
        }
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Dp.m4390constructorimpl(Float.intBitsToFloat((int) (j >> 32)));
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public static final float m4486getHeightD9Ej5fM(long j) {
        if (j == Unspecified) {
            throw new IllegalStateException("DpSize is unspecified".toString());
        }
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Dp.m4390constructorimpl(Float.intBitsToFloat((int) (j & 4294967295L)));
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4495toStringimpl(long j) {
        return j != INSTANCE.m4497getUnspecifiedMYxV2XQ() ? ((Object) Dp.m4401toStringimpl(m4488getWidthD9Ej5fM(j))) + " x " + ((Object) Dp.m4401toStringimpl(m4486getHeightD9Ej5fM(j))) : "DpSize.Unspecified";
    }

    static {
        float f = 0;
        Zero = DpKt.m4412DpSizeYgX7TsA(Dp.m4390constructorimpl(f), Dp.m4390constructorimpl(f));
    }
}
