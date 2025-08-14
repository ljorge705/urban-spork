package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import com.oblador.keychain.KeychainModule;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: SelectionAdjustment.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b`\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJ?\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "", "adjust", "Landroidx/compose/ui/text/TextRange;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "newRawSelectionRange", "previousHandleOffset", "", "isStartHandle", "", "previousSelectionRange", "adjust-ZXO7KMw", "(Landroidx/compose/ui/text/TextLayoutResult;JIZLandroidx/compose/ui/text/TextRange;)J", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SelectionAdjustment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* renamed from: adjust-ZXO7KMw, reason: not valid java name */
    long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange);

    /* compiled from: SelectionAdjustment.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J9\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00100\u0015H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment$Companion;", "", "()V", "Character", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getCharacter", "()Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "CharacterWithWordAccelerate", "getCharacterWithWordAccelerate", KeychainModule.AccessControl.NONE, "getNone", "Paragraph", "getParagraph", "Word", "getWord", "adjustByBoundary", "Landroidx/compose/ui/text/TextRange;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "newRawSelection", "boundaryFun", "Lkotlin/Function1;", "", "adjustByBoundary--Dv-ylE", "(Landroidx/compose/ui/text/TextLayoutResult;JLkotlin/jvm/functions/Function1;)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SelectionAdjustment None = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$None$1
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            /* renamed from: adjust-ZXO7KMw */
            public long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange) {
                Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
                return newRawSelectionRange;
            }
        };
        private static final SelectionAdjustment Character = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Character$1
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            /* renamed from: adjust-ZXO7KMw */
            public long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange) {
                Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
                if (TextRange.m3899getCollapsedimpl(newRawSelectionRange)) {
                    return SelectionAdjustmentKt.ensureAtLeastOneChar(textLayoutResult.getLayoutInput().getText().getText(), TextRange.m3905getStartimpl(newRawSelectionRange), StringsKt.getLastIndex(textLayoutResult.getLayoutInput().getText()), isStartHandle, previousSelectionRange != null ? TextRange.m3904getReversedimpl(previousSelectionRange.getPackedValue()) : false);
                }
                return newRawSelectionRange;
            }
        };
        private static final SelectionAdjustment Word = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Word$1
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            /* renamed from: adjust-ZXO7KMw */
            public long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange) {
                Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
                return SelectionAdjustment.Companion.$$INSTANCE.m1090adjustByBoundaryDvylE(textLayoutResult, newRawSelectionRange, new SelectionAdjustment$Companion$Word$1$adjust$1(textLayoutResult));
            }
        };
        private static final SelectionAdjustment Paragraph = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Paragraph$1
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            /* renamed from: adjust-ZXO7KMw */
            public long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange) {
                Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
                return SelectionAdjustment.Companion.$$INSTANCE.m1090adjustByBoundaryDvylE(textLayoutResult, newRawSelectionRange, new SelectionAdjustment$Companion$Paragraph$1$adjust$boundaryFun$1(textLayoutResult.getLayoutInput().getText()));
            }
        };
        private static final SelectionAdjustment CharacterWithWordAccelerate = new SelectionAdjustment() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$CharacterWithWordAccelerate$1
            private final boolean isExpanding(int newRawOffset, int previousRawOffset, boolean isStart, boolean previousReversed) {
                if (previousRawOffset == -1) {
                    return true;
                }
                if (newRawOffset == previousRawOffset) {
                    return false;
                }
                if (isStart ^ previousReversed) {
                    if (newRawOffset < previousRawOffset) {
                        return true;
                    }
                } else if (newRawOffset > previousRawOffset) {
                    return true;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            /* renamed from: adjust-ZXO7KMw */
            public long mo1088adjustZXO7KMw(TextLayoutResult textLayoutResult, long newRawSelectionRange, int previousHandleOffset, boolean isStartHandle, TextRange previousSelectionRange) {
                int iUpdateSelectionBoundary;
                int iUpdateSelectionBoundary2;
                Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
                if (previousSelectionRange == null) {
                    return SelectionAdjustment.Companion.$$INSTANCE.getWord().mo1088adjustZXO7KMw(textLayoutResult, newRawSelectionRange, previousHandleOffset, isStartHandle, previousSelectionRange);
                }
                if (TextRange.m3899getCollapsedimpl(newRawSelectionRange)) {
                    return SelectionAdjustmentKt.ensureAtLeastOneChar(textLayoutResult.getLayoutInput().getText().getText(), TextRange.m3905getStartimpl(newRawSelectionRange), StringsKt.getLastIndex(textLayoutResult.getLayoutInput().getText()), isStartHandle, TextRange.m3904getReversedimpl(previousSelectionRange.getPackedValue()));
                }
                if (isStartHandle) {
                    iUpdateSelectionBoundary2 = updateSelectionBoundary(textLayoutResult, TextRange.m3905getStartimpl(newRawSelectionRange), previousHandleOffset, TextRange.m3905getStartimpl(previousSelectionRange.getPackedValue()), TextRange.m3900getEndimpl(newRawSelectionRange), true, TextRange.m3904getReversedimpl(newRawSelectionRange));
                    iUpdateSelectionBoundary = TextRange.m3900getEndimpl(newRawSelectionRange);
                } else {
                    int iM3905getStartimpl = TextRange.m3905getStartimpl(newRawSelectionRange);
                    iUpdateSelectionBoundary = updateSelectionBoundary(textLayoutResult, TextRange.m3900getEndimpl(newRawSelectionRange), previousHandleOffset, TextRange.m3900getEndimpl(previousSelectionRange.getPackedValue()), TextRange.m3905getStartimpl(newRawSelectionRange), false, TextRange.m3904getReversedimpl(newRawSelectionRange));
                    iUpdateSelectionBoundary2 = iM3905getStartimpl;
                }
                return TextRangeKt.TextRange(iUpdateSelectionBoundary2, iUpdateSelectionBoundary);
            }

            private final int updateSelectionBoundary(TextLayoutResult textLayoutResult, int newRawOffset, int previousRawOffset, int previousAdjustedOffset, int otherBoundaryOffset, boolean isStart, boolean isReversed) {
                if (newRawOffset == previousRawOffset) {
                    return previousAdjustedOffset;
                }
                int lineForOffset = textLayoutResult.getLineForOffset(newRawOffset);
                if (lineForOffset != textLayoutResult.getLineForOffset(previousAdjustedOffset)) {
                    return snapToWordBoundary(textLayoutResult, newRawOffset, lineForOffset, otherBoundaryOffset, isStart, isReversed);
                }
                return (isExpanding(newRawOffset, previousRawOffset, isStart, isReversed) && isAtWordBoundary(textLayoutResult, previousAdjustedOffset)) ? snapToWordBoundary(textLayoutResult, newRawOffset, lineForOffset, otherBoundaryOffset, isStart, isReversed) : newRawOffset;
            }

            private final int snapToWordBoundary(TextLayoutResult textLayoutResult, int newRawOffset, int currentLine, int otherBoundaryOffset, boolean isStart, boolean isReversed) {
                int lineStart;
                int lineEnd$default;
                long jM3879getWordBoundaryjx7JFs = textLayoutResult.m3879getWordBoundaryjx7JFs(newRawOffset);
                if (textLayoutResult.getLineForOffset(TextRange.m3905getStartimpl(jM3879getWordBoundaryjx7JFs)) == currentLine) {
                    lineStart = TextRange.m3905getStartimpl(jM3879getWordBoundaryjx7JFs);
                } else {
                    lineStart = textLayoutResult.getLineStart(currentLine);
                }
                if (textLayoutResult.getLineForOffset(TextRange.m3900getEndimpl(jM3879getWordBoundaryjx7JFs)) == currentLine) {
                    lineEnd$default = TextRange.m3900getEndimpl(jM3879getWordBoundaryjx7JFs);
                } else {
                    lineEnd$default = TextLayoutResult.getLineEnd$default(textLayoutResult, currentLine, false, 2, null);
                }
                if (lineStart == otherBoundaryOffset) {
                    return lineEnd$default;
                }
                if (lineEnd$default == otherBoundaryOffset) {
                    return lineStart;
                }
                int i = (lineStart + lineEnd$default) / 2;
                if (isStart ^ isReversed) {
                    if (newRawOffset <= i) {
                        return lineStart;
                    }
                } else if (newRawOffset < i) {
                    return lineStart;
                }
                return lineEnd$default;
            }

            private final boolean isAtWordBoundary(TextLayoutResult textLayoutResult, int i) {
                long jM3879getWordBoundaryjx7JFs = textLayoutResult.m3879getWordBoundaryjx7JFs(i);
                return i == TextRange.m3905getStartimpl(jM3879getWordBoundaryjx7JFs) || i == TextRange.m3900getEndimpl(jM3879getWordBoundaryjx7JFs);
            }
        };

        public final SelectionAdjustment getCharacter() {
            return Character;
        }

        public final SelectionAdjustment getCharacterWithWordAccelerate() {
            return CharacterWithWordAccelerate;
        }

        public final SelectionAdjustment getNone() {
            return None;
        }

        public final SelectionAdjustment getParagraph() {
            return Paragraph;
        }

        public final SelectionAdjustment getWord() {
            return Word;
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: adjustByBoundary--Dv-ylE, reason: not valid java name */
        public final long m1090adjustByBoundaryDvylE(TextLayoutResult textLayoutResult, long newRawSelection, Function1<? super Integer, TextRange> boundaryFun) {
            if (textLayoutResult.getLayoutInput().getText().length() == 0) {
                return TextRange.INSTANCE.m3910getZerod9O1mEE();
            }
            int lastIndex = StringsKt.getLastIndex(textLayoutResult.getLayoutInput().getText());
            long packedValue = boundaryFun.invoke(Integer.valueOf(RangesKt.coerceIn(TextRange.m3905getStartimpl(newRawSelection), 0, lastIndex))).getPackedValue();
            long packedValue2 = boundaryFun.invoke(Integer.valueOf(RangesKt.coerceIn(TextRange.m3900getEndimpl(newRawSelection), 0, lastIndex))).getPackedValue();
            return TextRangeKt.TextRange(TextRange.m3904getReversedimpl(newRawSelection) ? TextRange.m3900getEndimpl(packedValue) : TextRange.m3905getStartimpl(packedValue), TextRange.m3904getReversedimpl(newRawSelection) ? TextRange.m3905getStartimpl(packedValue2) : TextRange.m3900getEndimpl(packedValue2));
        }
    }
}
