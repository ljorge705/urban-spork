package androidx.compose.material;

import androidx.compose.ui.unit.Dp;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J~\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00140\u0018¢\u0006\u0002\b\u00192\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0018¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010\u001eR\u0019\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0007\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\t\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\n\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u000b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\f\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\r\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u000e\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u000f\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0010\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0011\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0012\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/material/TwoLine;", "", "()V", "ContentLeftPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ContentRightPadding", "IconLeftPadding", "IconMinPaddedWidth", "IconVerticalPadding", "MinHeight", "MinHeightWithIcon", "OverlineBaselineOffset", "OverlineToPrimaryBaselineOffset", "PrimaryBaselineOffsetNoIcon", "PrimaryBaselineOffsetWithIcon", "PrimaryToSecondaryBaselineOffsetNoIcon", "PrimaryToSecondaryBaselineOffsetWithIcon", "TrailingRightPadding", "ListItem", "", "modifier", "Landroidx/compose/ui/Modifier;", Constants.KEY_ICON, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "text", "secondaryText", "overlineText", "trailing", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class TwoLine {
    private static final float ContentLeftPadding;
    private static final float ContentRightPadding;
    private static final float IconLeftPadding;
    private static final float IconVerticalPadding;
    private static final float OverlineToPrimaryBaselineOffset;
    private static final float PrimaryToSecondaryBaselineOffsetNoIcon;
    private static final float PrimaryToSecondaryBaselineOffsetWithIcon;
    private static final float TrailingRightPadding;
    public static final TwoLine INSTANCE = new TwoLine();
    private static final float MinHeight = Dp.m4390constructorimpl(64);
    private static final float MinHeightWithIcon = Dp.m4390constructorimpl(72);
    private static final float IconMinPaddedWidth = Dp.m4390constructorimpl(40);
    private static final float OverlineBaselineOffset = Dp.m4390constructorimpl(24);
    private static final float PrimaryBaselineOffsetNoIcon = Dp.m4390constructorimpl(28);
    private static final float PrimaryBaselineOffsetWithIcon = Dp.m4390constructorimpl(32);

    private TwoLine() {
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void ListItem(androidx.compose.ui.Modifier r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r37, androidx.compose.runtime.Composer r38, final int r39, final int r40) {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TwoLine.ListItem(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    static {
        float f = 16;
        IconLeftPadding = Dp.m4390constructorimpl(f);
        IconVerticalPadding = Dp.m4390constructorimpl(f);
        ContentLeftPadding = Dp.m4390constructorimpl(f);
        ContentRightPadding = Dp.m4390constructorimpl(f);
        float f2 = 20;
        OverlineToPrimaryBaselineOffset = Dp.m4390constructorimpl(f2);
        PrimaryToSecondaryBaselineOffsetNoIcon = Dp.m4390constructorimpl(f2);
        PrimaryToSecondaryBaselineOffsetWithIcon = Dp.m4390constructorimpl(f2);
        TrailingRightPadding = Dp.m4390constructorimpl(f);
    }
}
