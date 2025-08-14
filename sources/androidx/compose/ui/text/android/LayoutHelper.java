package androidx.compose.ui.text.android;

import android.text.Layout;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LayoutHelper.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\rJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001aJ\u0010\u0010\u001e\u001a\u00020\r2\b\b\u0001\u0010\u0015\u001a\u00020\rJ\u001a\u0010\u001f\u001a\u00020\r2\b\b\u0001\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\u001aJ\u0010\u0010 \u001a\u00020\r2\b\b\u0001\u0010\u0015\u001a\u00020\rJ\u000e\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u00020\u001a2\b\b\u0001\u0010\u0015\u001a\u00020\rJ\u0010\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/compose/ui/text/android/LayoutHelper;", "", "layout", "Landroid/text/Layout;", "(Landroid/text/Layout;)V", "bidiProcessedParagraphs", "", "getLayout", "()Landroid/text/Layout;", "paragraphBidi", "", "Ljava/text/Bidi;", "paragraphCount", "", "getParagraphCount", "()I", "paragraphEnds", "", "tmpBuffer", "", "analyzeBidi", "paragraphIndex", "getDownstreamHorizontal", "", "offset", "primary", "", "getHorizontalPosition", "usePrimaryDirection", "upstream", "getParagraphEnd", "getParagraphForOffset", "getParagraphStart", "isLineEndSpace", "c", "", "isRtlParagraph", "lineEndToVisibleEnd", "lineEnd", "BidiRun", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutHelper {
    public static final int $stable = 8;
    private final boolean[] bidiProcessedParagraphs;
    private final Layout layout;
    private final List<Bidi> paragraphBidi;
    private final int paragraphCount;
    private final List<Integer> paragraphEnds;
    private char[] tmpBuffer;

    public final Layout getLayout() {
        return this.layout;
    }

    public final int getParagraphCount() {
        return this.paragraphCount;
    }

    public final boolean isLineEndSpace(char c) {
        return c == ' ' || c == '\n' || c == 5760 || (8192 <= c && c < 8203 && c != 8199) || c == 8287 || c == 12288;
    }

    public LayoutHelper(Layout layout) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        this.layout = layout;
        ArrayList arrayList = new ArrayList();
        int length = 0;
        do {
            CharSequence text = this.layout.getText();
            Intrinsics.checkNotNullExpressionValue(text, "layout.text");
            int iIndexOf$default = StringsKt.indexOf$default(text, '\n', length, false, 4, (Object) null);
            length = iIndexOf$default < 0 ? this.layout.getText().length() : iIndexOf$default + 1;
            arrayList.add(Integer.valueOf(length));
        } while (length < this.layout.getText().length());
        this.paragraphEnds = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList2.add(null);
        }
        this.paragraphBidi = arrayList2;
        this.bidiProcessedParagraphs = new boolean[this.paragraphEnds.size()];
        this.paragraphCount = this.paragraphEnds.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.text.Bidi analyzeBidi(int r12) {
        /*
            r11 = this;
            boolean[] r0 = r11.bidiProcessedParagraphs
            boolean r0 = r0[r12]
            if (r0 == 0) goto Lf
            java.util.List<java.text.Bidi> r0 = r11.paragraphBidi
            java.lang.Object r12 = r0.get(r12)
            java.text.Bidi r12 = (java.text.Bidi) r12
            return r12
        Lf:
            r0 = 0
            if (r12 != 0) goto L14
            r1 = r0
            goto L22
        L14:
            java.util.List<java.lang.Integer> r1 = r11.paragraphEnds
            int r2 = r12 + (-1)
            java.lang.Object r1 = r1.get(r2)
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
        L22:
            java.util.List<java.lang.Integer> r2 = r11.paragraphEnds
            java.lang.Object r2 = r2.get(r12)
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            int r8 = r2 - r1
            char[] r3 = r11.tmpBuffer
            if (r3 == 0) goto L37
            int r4 = r3.length
            if (r4 >= r8) goto L39
        L37:
            char[] r3 = new char[r8]
        L39:
            r10 = r3
            android.text.Layout r3 = r11.layout
            java.lang.CharSequence r3 = r3.getText()
            android.text.TextUtils.getChars(r3, r1, r2, r10, r0)
            boolean r0 = java.text.Bidi.requiresBidi(r10, r0, r8)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5f
            boolean r9 = r11.isRtlParagraph(r12)
            java.text.Bidi r0 = new java.text.Bidi
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r0
            r4 = r10
            r3.<init>(r4, r5, r6, r7, r8, r9)
            int r3 = r0.getRunCount()
            if (r3 != r2) goto L60
        L5f:
            r0 = r1
        L60:
            java.util.List<java.text.Bidi> r3 = r11.paragraphBidi
            r3.set(r12, r0)
            boolean[] r3 = r11.bidiProcessedParagraphs
            r3[r12] = r2
            if (r0 == 0) goto L72
            char[] r12 = r11.tmpBuffer
            if (r10 != r12) goto L71
            r10 = r1
            goto L72
        L71:
            r10 = r12
        L72:
            r11.tmpBuffer = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutHelper.analyzeBidi(int):java.text.Bidi");
    }

    public static /* synthetic */ int getParagraphForOffset$default(LayoutHelper layoutHelper, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return layoutHelper.getParagraphForOffset(i, z);
    }

    public final int getParagraphForOffset(int offset, boolean upstream) {
        int iBinarySearch$default = CollectionsKt.binarySearch$default(this.paragraphEnds, Integer.valueOf(offset), 0, 0, 6, (Object) null);
        int i = iBinarySearch$default < 0 ? -(iBinarySearch$default + 1) : iBinarySearch$default + 1;
        if (upstream && i > 0) {
            int i2 = i - 1;
            if (offset == this.paragraphEnds.get(i2).intValue()) {
                return i2;
            }
        }
        return i;
    }

    public final int getParagraphStart(int paragraphIndex) {
        if (paragraphIndex == 0) {
            return 0;
        }
        return this.paragraphEnds.get(paragraphIndex - 1).intValue();
    }

    public final int getParagraphEnd(int paragraphIndex) {
        return this.paragraphEnds.get(paragraphIndex).intValue();
    }

    public final boolean isRtlParagraph(int paragraphIndex) {
        return this.layout.getParagraphDirection(this.layout.getLineForOffset(getParagraphStart(paragraphIndex))) == -1;
    }

    public final float getHorizontalPosition(int offset, boolean usePrimaryDirection, boolean upstream) {
        int iLineEndToVisibleEnd = offset;
        if (!upstream) {
            return getDownstreamHorizontal(offset, usePrimaryDirection);
        }
        int lineForOffset = LayoutCompatKt.getLineForOffset(this.layout, iLineEndToVisibleEnd, upstream);
        int lineStart = this.layout.getLineStart(lineForOffset);
        int lineEnd = this.layout.getLineEnd(lineForOffset);
        if (iLineEndToVisibleEnd != lineStart && iLineEndToVisibleEnd != lineEnd) {
            return getDownstreamHorizontal(offset, usePrimaryDirection);
        }
        if (iLineEndToVisibleEnd == 0 || iLineEndToVisibleEnd == this.layout.getText().length()) {
            return getDownstreamHorizontal(offset, usePrimaryDirection);
        }
        int paragraphForOffset = getParagraphForOffset(iLineEndToVisibleEnd, upstream);
        boolean zIsRtlParagraph = isRtlParagraph(paragraphForOffset);
        int iLineEndToVisibleEnd2 = lineEndToVisibleEnd(lineEnd);
        int paragraphStart = getParagraphStart(paragraphForOffset);
        int i = lineStart - paragraphStart;
        int i2 = iLineEndToVisibleEnd2 - paragraphStart;
        Bidi bidiAnalyzeBidi = analyzeBidi(paragraphForOffset);
        Bidi bidiCreateLineBidi = bidiAnalyzeBidi != null ? bidiAnalyzeBidi.createLineBidi(i, i2) : null;
        if (bidiCreateLineBidi == null || bidiCreateLineBidi.getRunCount() == 1) {
            boolean zIsRtlCharAt = this.layout.isRtlCharAt(lineStart);
            if (usePrimaryDirection || zIsRtlParagraph == zIsRtlCharAt) {
                zIsRtlParagraph = !zIsRtlParagraph;
            }
            return (iLineEndToVisibleEnd != lineStart ? zIsRtlParagraph : !zIsRtlParagraph) ? this.layout.getLineRight(lineForOffset) : this.layout.getLineLeft(lineForOffset);
        }
        int runCount = bidiCreateLineBidi.getRunCount();
        BidiRun[] bidiRunArr = new BidiRun[runCount];
        for (int i3 = 0; i3 < runCount; i3++) {
            bidiRunArr[i3] = new BidiRun(bidiCreateLineBidi.getRunStart(i3) + lineStart, bidiCreateLineBidi.getRunLimit(i3) + lineStart, bidiCreateLineBidi.getRunLevel(i3) % 2 == 1);
        }
        int runCount2 = bidiCreateLineBidi.getRunCount();
        byte[] bArr = new byte[runCount2];
        for (int i4 = 0; i4 < runCount2; i4++) {
            bArr[i4] = (byte) bidiCreateLineBidi.getRunLevel(i4);
        }
        Bidi.reorderVisually(bArr, 0, bidiRunArr, 0, runCount);
        int i5 = -1;
        if (iLineEndToVisibleEnd == lineStart) {
            int i6 = 0;
            while (true) {
                if (i6 >= runCount) {
                    break;
                }
                if (bidiRunArr[i6].getStart() == iLineEndToVisibleEnd) {
                    i5 = i6;
                    break;
                }
                i6++;
            }
            BidiRun bidiRun = bidiRunArr[i5];
            if (usePrimaryDirection || zIsRtlParagraph == bidiRun.isRtl()) {
                zIsRtlParagraph = !zIsRtlParagraph;
            }
            if (i5 == 0 && zIsRtlParagraph) {
                return this.layout.getLineLeft(lineForOffset);
            }
            if (i5 == ArraysKt.getLastIndex(bidiRunArr) && !zIsRtlParagraph) {
                return this.layout.getLineRight(lineForOffset);
            }
            if (zIsRtlParagraph) {
                return this.layout.getPrimaryHorizontal(bidiRunArr[i5 - 1].getStart());
            }
            return this.layout.getPrimaryHorizontal(bidiRunArr[i5 + 1].getStart());
        }
        if (iLineEndToVisibleEnd > iLineEndToVisibleEnd2) {
            iLineEndToVisibleEnd = lineEndToVisibleEnd(offset);
        }
        int i7 = 0;
        while (true) {
            if (i7 >= runCount) {
                break;
            }
            if (bidiRunArr[i7].getEnd() == iLineEndToVisibleEnd) {
                i5 = i7;
                break;
            }
            i7++;
        }
        BidiRun bidiRun2 = bidiRunArr[i5];
        if (!usePrimaryDirection && zIsRtlParagraph != bidiRun2.isRtl()) {
            zIsRtlParagraph = !zIsRtlParagraph;
        }
        if (i5 == 0 && zIsRtlParagraph) {
            return this.layout.getLineLeft(lineForOffset);
        }
        if (i5 == ArraysKt.getLastIndex(bidiRunArr) && !zIsRtlParagraph) {
            return this.layout.getLineRight(lineForOffset);
        }
        if (zIsRtlParagraph) {
            return this.layout.getPrimaryHorizontal(bidiRunArr[i5 - 1].getEnd());
        }
        return this.layout.getPrimaryHorizontal(bidiRunArr[i5 + 1].getEnd());
    }

    private final float getDownstreamHorizontal(int offset, boolean primary) {
        if (primary) {
            return this.layout.getPrimaryHorizontal(offset);
        }
        return this.layout.getSecondaryHorizontal(offset);
    }

    /* compiled from: LayoutHelper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/text/android/LayoutHelper$BidiRun;", "", ViewProps.START, "", ViewProps.END, "isRtl", "", "(IIZ)V", "getEnd", "()I", "()Z", "getStart", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class BidiRun {
        private final int end;
        private final boolean isRtl;
        private final int start;

        public static /* synthetic */ BidiRun copy$default(BidiRun bidiRun, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = bidiRun.start;
            }
            if ((i3 & 2) != 0) {
                i2 = bidiRun.end;
            }
            if ((i3 & 4) != 0) {
                z = bidiRun.isRtl;
            }
            return bidiRun.copy(i, i2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getStart() {
            return this.start;
        }

        /* renamed from: component2, reason: from getter */
        public final int getEnd() {
            return this.end;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsRtl() {
            return this.isRtl;
        }

        public final BidiRun copy(int start, int end, boolean isRtl) {
            return new BidiRun(start, end, isRtl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BidiRun)) {
                return false;
            }
            BidiRun bidiRun = (BidiRun) other;
            return this.start == bidiRun.start && this.end == bidiRun.end && this.isRtl == bidiRun.isRtl;
        }

        public final int getEnd() {
            return this.end;
        }

        public final int getStart() {
            return this.start;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iHashCode = ((Integer.hashCode(this.start) * 31) + Integer.hashCode(this.end)) * 31;
            boolean z = this.isRtl;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return iHashCode + i;
        }

        public final boolean isRtl() {
            return this.isRtl;
        }

        public String toString() {
            return "BidiRun(start=" + this.start + ", end=" + this.end + ", isRtl=" + this.isRtl + ')';
        }

        public BidiRun(int i, int i2, boolean z) {
            this.start = i;
            this.end = i2;
            this.isRtl = z;
        }
    }

    private final int lineEndToVisibleEnd(int lineEnd) {
        while (lineEnd > 0 && isLineEndSpace(this.layout.getText().charAt(lineEnd - 1))) {
            lineEnd--;
        }
        return lineEnd;
    }
}
