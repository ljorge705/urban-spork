package androidx.compose.ui.text;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: TextRange.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a)\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"TextRange", "Landroidx/compose/ui/text/TextRange;", "index", "", "(I)J", ViewProps.START, ViewProps.END, "(II)J", "packWithCheck", "", "constrain", "minimumValue", "maximumValue", "constrain-8ffj60Q", "(JII)J", "substring", "", "", "range", "substring-FDrldGo", "(Ljava/lang/CharSequence;J)Ljava/lang/String;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextRangeKt {
    /* renamed from: substring-FDrldGo, reason: not valid java name */
    public static final String m3912substringFDrldGo(CharSequence substring, long j) {
        Intrinsics.checkNotNullParameter(substring, "$this$substring");
        return substring.subSequence(TextRange.m3903getMinimpl(j), TextRange.m3902getMaximpl(j)).toString();
    }

    public static final long TextRange(int i, int i2) {
        return TextRange.m3894constructorimpl(packWithCheck(i, i2));
    }

    public static final long TextRange(int i) {
        return TextRange(i, i);
    }

    /* renamed from: constrain-8ffj60Q, reason: not valid java name */
    public static final long m3911constrain8ffj60Q(long j, int i, int i2) {
        int iCoerceIn = RangesKt.coerceIn(TextRange.m3905getStartimpl(j), i, i2);
        int iCoerceIn2 = RangesKt.coerceIn(TextRange.m3900getEndimpl(j), i, i2);
        return (iCoerceIn == TextRange.m3905getStartimpl(j) && iCoerceIn2 == TextRange.m3900getEndimpl(j)) ? j : TextRange(iCoerceIn, iCoerceIn2);
    }

    private static final long packWithCheck(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException(("start cannot be negative. [start: " + i + ", end: " + i2 + AbstractJsonLexerKt.END_LIST).toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(("end cannot be negative. [start: " + i + ", end: " + i2 + AbstractJsonLexerKt.END_LIST).toString());
        }
        return (i2 & 4294967295L) | (i << 32);
    }
}
