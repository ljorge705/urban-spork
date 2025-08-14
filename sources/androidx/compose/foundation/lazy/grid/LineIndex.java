package androidx.compose.foundation.lazy.grid;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* compiled from: ItemIndex.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u0000H\u0086\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0005J\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0005J\u0019\u0010\u0014\u001a\u00020\u0000H\u0086\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0005J\u001e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0000H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u000bJ!\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0003H\u0086\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u000bJ!\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0003H\u0086\nø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u0010\u0010\u001c\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/foundation/lazy/grid/LineIndex;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "compareTo", "other", "compareTo-bKFJvoY", "(II)I", "dec", "dec-hA7yfN8", "equals", "", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "inc", "inc-hA7yfN8", "minus", "i", "minus-fVkYB0M", "minus--_Ze7BM", "plus", "plus--_Ze7BM", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class LineIndex {
    private final int value;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ LineIndex m872boximpl(int i) {
        return new LineIndex(i);
    }

    /* renamed from: compareTo-bKFJvoY, reason: not valid java name */
    public static final int m873compareTobKFJvoY(int i, int i2) {
        return i - i2;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m874constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m876equalsimpl(int i, Object obj) {
        return (obj instanceof LineIndex) && i == ((LineIndex) obj).m884unboximpl();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m877equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m878hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m883toStringimpl(int i) {
        return "LineIndex(value=" + i + ')';
    }

    public boolean equals(Object obj) {
        return m876equalsimpl(this.value, obj);
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return m878hashCodeimpl(this.value);
    }

    public String toString() {
        return m883toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m884unboximpl() {
        return this.value;
    }

    private /* synthetic */ LineIndex(int i) {
        this.value = i;
    }

    /* renamed from: inc-hA7yfN8, reason: not valid java name */
    public static final int m879inchA7yfN8(int i) {
        return m874constructorimpl(i + 1);
    }

    /* renamed from: dec-hA7yfN8, reason: not valid java name */
    public static final int m875dechA7yfN8(int i) {
        return m874constructorimpl(i - 1);
    }

    /* renamed from: plus--_Ze7BM, reason: not valid java name */
    public static final int m882plus_Ze7BM(int i, int i2) {
        return m874constructorimpl(i + i2);
    }

    /* renamed from: minus--_Ze7BM, reason: not valid java name */
    public static final int m880minus_Ze7BM(int i, int i2) {
        return m874constructorimpl(i - i2);
    }

    /* renamed from: minus-fVkYB0M, reason: not valid java name */
    public static final int m881minusfVkYB0M(int i, int i2) {
        return m874constructorimpl(i - i2);
    }
}
