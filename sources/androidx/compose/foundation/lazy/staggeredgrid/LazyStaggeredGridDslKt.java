package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* compiled from: LazyStaggeredGridDsl.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001at\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001at\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a?\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001a¢\u0006\u0002\b\u00152\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a?\u0010\u001f\u001a\u0019\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001a¢\u0006\u0002\b\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010 \u001aÓ\u0001\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\"*\u00020\u00142\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"0#2%\b\n\u0010$\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(\u0018\u00010\u00132%\b\u0006\u0010)\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010(0\u00132%\b\n\u0010*\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020+\u0018\u00010\u001323\b\u0004\u0010,\u001a-\u0012\u0004\u0012\u00020-\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b.¢\u0006\u0002\b\u0015H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010/\u001aÓ\u0001\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\"*\u00020\u00142\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"002%\b\n\u0010$\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(\u0018\u00010\u00132%\b\u0006\u0010)\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010(0\u00132%\b\n\u0010*\u001a\u001f\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020+\u0018\u00010\u001323\b\u0004\u0010,\u001a-\u0012\u0004\u0012\u00020-\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b.¢\u0006\u0002\b\u0015H\u0087\bø\u0001\u0001¢\u0006\u0002\u00101\u001a§\u0002\u00102\u001a\u00020\u0001\"\u0004\b\u0000\u0010\"*\u00020\u00142\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"0#2:\b\n\u0010$\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(\u0018\u00010\u001a2:\b\u0006\u0010)\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010(0\u001a2:\b\n\u0010*\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020+\u0018\u00010\u001a2H\b\u0004\u0010,\u001aB\u0012\u0004\u0012\u00020-\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u000105¢\u0006\u0002\b.¢\u0006\u0002\b\u0015H\u0087\bø\u0001\u0001¢\u0006\u0002\u00106\u001a§\u0002\u00102\u001a\u00020\u0001\"\u0004\b\u0000\u0010\"*\u00020\u00142\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"002:\b\n\u0010$\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020(\u0018\u00010\u001a2:\b\u0006\u0010)\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010(0\u001a2:\b\n\u0010*\u001a4\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020+\u0018\u00010\u001a2H\b\u0004\u0010,\u001aB\u0012\u0004\u0012\u00020-\u0012\u0013\u0012\u001103¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H\"¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u000105¢\u0006\u0002\b.¢\u0006\u0002\b\u0015H\u0087\bø\u0001\u0001¢\u0006\u0002\u00107\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00068"}, d2 = {"LazyHorizontalStaggeredGrid", "", "rows", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalStaggeredGrid", "columns", "rememberColumnWidthSums", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRowHeightSums", "(Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridCells;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", FirebaseAnalytics.Param.ITEMS, ExifInterface.GPS_DIRECTION_TRUE, "", Constants.KEY_KEY, "Lkotlin/ParameterName;", "name", com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM, "", CMSAttributeTableGenerator.CONTENT_TYPE, "span", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "itemContent", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridDslKt {
    /* JADX WARN: Removed duplicated region for block: B:109:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyVerticalStaggeredGrid(final androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells r26, androidx.compose.ui.Modifier r27, androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState r28, androidx.compose.foundation.layout.PaddingValues r29, androidx.compose.foundation.layout.Arrangement.Vertical r30, androidx.compose.foundation.layout.Arrangement.Horizontal r31, androidx.compose.foundation.gestures.FlingBehavior r32, boolean r33, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope, kotlin.Unit> r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.LazyVerticalStaggeredGrid(androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Function2<Density, Constraints, int[]> rememberColumnWidthSums(final StaggeredGridCells staggeredGridCells, final Arrangement.Horizontal horizontal, final PaddingValues paddingValues, Composer composer, int i) {
        composer.startReplaceableGroup(1426908594);
        ComposerKt.sourceInformation(composer, "C(rememberColumnWidthSums)P(!1,2)87@3996L920:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1426908594, i, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberColumnWidthSums (LazyStaggeredGridDsl.kt:83)");
        }
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(staggeredGridCells) | composer.changed(horizontal) | composer.changed(paddingValues);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function2) new Function2<Density, Constraints, int[]>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberColumnWidthSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ int[] invoke(Density density, Constraints constraints) {
                    return m898invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final int[] m898invoke0kLqBqw(Density density, long j) {
                    Intrinsics.checkNotNullParameter(density, "$this$null");
                    if (Constraints.m4346getMaxWidthimpl(j) == Integer.MAX_VALUE) {
                        throw new IllegalArgumentException("LazyVerticalStaggeredGrid's width should be bound by parent.".toString());
                    }
                    List<Integer> listCalculateCrossAxisCellSizes = staggeredGridCells.calculateCrossAxisCellSizes(density, Constraints.m4346getMaxWidthimpl(j) - density.mo571roundToPx0680j_4(Dp.m4390constructorimpl(PaddingKt.calculateStartPadding(paddingValues, LayoutDirection.Ltr) + PaddingKt.calculateEndPadding(paddingValues, LayoutDirection.Ltr))), density.mo571roundToPx0680j_4(horizontal.getSpacing()));
                    int size = listCalculateCrossAxisCellSizes.size();
                    int[] iArr = new int[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = listCalculateCrossAxisCellSizes.get(i2).intValue();
                    }
                    int size2 = listCalculateCrossAxisCellSizes.size();
                    for (int i3 = 1; i3 < size2; i3++) {
                        iArr[i3] = iArr[i3] + iArr[i3 - 1];
                    }
                    return iArr;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<Density, Constraints, int[]> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0130  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyHorizontalStaggeredGrid(final androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells r26, androidx.compose.ui.Modifier r27, androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState r28, androidx.compose.foundation.layout.PaddingValues r29, androidx.compose.foundation.layout.Arrangement.Vertical r30, androidx.compose.foundation.layout.Arrangement.Horizontal r31, androidx.compose.foundation.gestures.FlingBehavior r32, boolean r33, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope, kotlin.Unit> r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instructions count: 598
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.LazyHorizontalStaggeredGrid(androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Function2<Density, Constraints, int[]> rememberRowHeightSums(final StaggeredGridCells staggeredGridCells, final Arrangement.Vertical vertical, final PaddingValues paddingValues, Composer composer, int i) {
        composer.startReplaceableGroup(-1665208491);
        ComposerKt.sourceInformation(composer, "C(rememberRowHeightSums)P(1,2)169@7463L860:LazyStaggeredGridDsl.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1665208491, i, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberRowHeightSums (LazyStaggeredGridDsl.kt:165)");
        }
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(staggeredGridCells) | composer.changed(vertical) | composer.changed(paddingValues);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function2) new Function2<Density, Constraints, int[]>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$rememberRowHeightSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ int[] invoke(Density density, Constraints constraints) {
                    return m899invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final int[] m899invoke0kLqBqw(Density density, long j) {
                    Intrinsics.checkNotNullParameter(density, "$this$null");
                    if (Constraints.m4345getMaxHeightimpl(j) == Integer.MAX_VALUE) {
                        throw new IllegalArgumentException("LazyHorizontalStaggeredGrid's height should be bound by parent.".toString());
                    }
                    List<Integer> listCalculateCrossAxisCellSizes = staggeredGridCells.calculateCrossAxisCellSizes(density, Constraints.m4345getMaxHeightimpl(j) - density.mo571roundToPx0680j_4(Dp.m4390constructorimpl(paddingValues.getTop() + paddingValues.getBottom())), density.mo571roundToPx0680j_4(vertical.getSpacing()));
                    int size = listCalculateCrossAxisCellSizes.size();
                    int[] iArr = new int[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = listCalculateCrossAxisCellSizes.get(i2).intValue();
                    }
                    int size2 = listCalculateCrossAxisCellSizes.size();
                    for (int i3 = 1; i3 < size2; i3++) {
                        iArr[i3] = iArr[i3] + iArr[i3 - 1];
                    }
                    return iArr;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<Density, Constraints, int[]> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope lazyStaggeredGridScope, List items, Function1 function1, Function1 contentType, Function1 function12, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            contentType = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.items.1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke((C03461) obj2);
                }
            };
        }
        if ((i & 8) != 0) {
            function12 = null;
        }
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.size(), function1 != null ? new LazyStaggeredGridDslKt$items$2$1(function1, items) : null, new AnonymousClass3(contentType, items), function12 != null ? new LazyStaggeredGridDslKt$items$4$1(function12, items) : null, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new AnonymousClass5(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$3, reason: invalid class name */
    public static final class AnonymousClass3 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            super(1);
            this.$contentType = function1;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(this.$items.get(i));
        }
    }

    public static final <T> void items(LazyStaggeredGridScope lazyStaggeredGridScope, List<? extends T> items, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> contentType, Function1<? super T, StaggeredGridItemSpan> function12, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.size(), function1 != null ? new LazyStaggeredGridDslKt$items$2$1(function1, items) : null, new AnonymousClass3(contentType, items), function12 != null ? new LazyStaggeredGridDslKt$items$4$1(function12, items) : null, ComposableLambdaKt.composableLambdaInstance(-886456479, true, new AnonymousClass5(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$5, reason: invalid class name */
    public static final class AnonymousClass5 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyStaggeredGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, List<? extends T> list) {
            super(4);
            this.$itemContent = function4;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C303@13344L25:LazyStaggeredGridDsl.kt#fzvcnm");
            if ((i2 & 14) == 0) {
                i3 = (composer.changed(items) ? 4 : 2) | i2;
            } else {
                i3 = i2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-886456479, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.items.<anonymous> (LazyStaggeredGridDsl.kt:303)");
            }
            this.$itemContent.invoke(items, this.$items.get(i), composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope lazyStaggeredGridScope, List items, Function2 function2, Function2 contentType, Function2 function22, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            contentType = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.itemsIndexed.1
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        if ((i & 8) != 0) {
            function22 = null;
        }
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.size(), function2 != null ? new LazyStaggeredGridDslKt$itemsIndexed$2$1(function2, items) : null, new C03493(contentType, items), function22 != null ? new LazyStaggeredGridDslKt$itemsIndexed$4$1(function22, items) : null, ComposableLambdaKt.composableLambdaInstance(284833944, true, new C03505(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    public static final class C03493 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03493(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            super(1);
            this.$contentType = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(Integer.valueOf(i), this.$items.get(i));
        }
    }

    public static final <T> void itemsIndexed(LazyStaggeredGridScope lazyStaggeredGridScope, List<? extends T> items, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> contentType, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function22, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.size(), function2 != null ? new LazyStaggeredGridDslKt$itemsIndexed$2$1(function2, items) : null, new C03493(contentType, items), function22 != null ? new LazyStaggeredGridDslKt$itemsIndexed$4$1(function22, items) : null, ComposableLambdaKt.composableLambdaInstance(284833944, true, new C03505(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$5, reason: invalid class name and case insensitive filesystem */
    public static final class C03505 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyStaggeredGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03505(Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, List<? extends T> list) {
            super(4);
            this.$itemContent = function5;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C343@15246L32:LazyStaggeredGridDsl.kt#fzvcnm");
            if ((i2 & 14) == 0) {
                i3 = (composer.changed(items) ? 4 : 2) | i2;
            } else {
                i3 = i2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(284833944, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed.<anonymous> (LazyStaggeredGridDsl.kt:343)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), this.$items.get(i), composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void items$default(LazyStaggeredGridScope lazyStaggeredGridScope, Object[] items, Function1 function1, Function1 contentType, Function1 function12, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            contentType = new Function1() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.items.6
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke((AnonymousClass6) obj2);
                }
            };
        }
        if ((i & 8) != 0) {
            function12 = null;
        }
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.length, function1 != null ? new LazyStaggeredGridDslKt$items$7$1(function1, items) : null, new AnonymousClass8(contentType, items), function12 != null ? new LazyStaggeredGridDslKt$items$9$1(function12, items) : null, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new AnonymousClass10(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$8, reason: invalid class name */
    public static final class AnonymousClass8 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(Function1<? super T, ? extends Object> function1, T[] tArr) {
            super(1);
            this.$contentType = function1;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(this.$items[i]);
        }
    }

    public static final <T> void items(LazyStaggeredGridScope lazyStaggeredGridScope, T[] items, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> contentType, Function1<? super T, StaggeredGridItemSpan> function12, Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.length, function1 != null ? new LazyStaggeredGridDslKt$items$7$1(function1, items) : null, new AnonymousClass8(contentType, items), function12 != null ? new LazyStaggeredGridDslKt$items$9$1(function12, items) : null, ComposableLambdaKt.composableLambdaInstance(2101296000, true, new AnonymousClass10(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$items$10, reason: invalid class name */
    public static final class AnonymousClass10 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyStaggeredGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(Function4<? super LazyStaggeredGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, T[] tArr) {
            super(4);
            this.$itemContent = function4;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C383@17050L25:LazyStaggeredGridDsl.kt#fzvcnm");
            if ((i2 & 14) == 0) {
                i3 = (composer.changed(items) ? 4 : 2) | i2;
            } else {
                i3 = i2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2101296000, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.items.<anonymous> (LazyStaggeredGridDsl.kt:383)");
            }
            this.$itemContent.invoke(items, this.$items[i], composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyStaggeredGridScope lazyStaggeredGridScope, Object[] items, Function2 function2, Function2 contentType, Function2 function22, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            contentType = new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt.itemsIndexed.6
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        if ((i & 8) != 0) {
            function22 = null;
        }
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.length, function2 != null ? new LazyStaggeredGridDslKt$itemsIndexed$7$1(function2, items) : null, new C03528(contentType, items), function22 != null ? new LazyStaggeredGridDslKt$itemsIndexed$9$1(function22, items) : null, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new C034810(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    public static final class C03528 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03528(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            super(1);
            this.$contentType = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$contentType.invoke(Integer.valueOf(i), this.$items[i]);
        }
    }

    public static final <T> void itemsIndexed(LazyStaggeredGridScope lazyStaggeredGridScope, T[] items, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> contentType, Function2<? super Integer, ? super T, StaggeredGridItemSpan> function22, Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyStaggeredGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyStaggeredGridScope.items(items.length, function2 != null ? new LazyStaggeredGridDslKt$itemsIndexed$7$1(function2, items) : null, new C03528(contentType, items), function22 != null ? new LazyStaggeredGridDslKt$itemsIndexed$9$1(function22, items) : null, ComposableLambdaKt.composableLambdaInstance(-804487775, true, new C034810(itemContent, items)));
    }

    /* compiled from: LazyStaggeredGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "index", "", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridDslKt$itemsIndexed$10, reason: invalid class name and case insensitive filesystem */
    public static final class C034810 extends Lambda implements Function4<LazyStaggeredGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyStaggeredGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C034810(Function5<? super LazyStaggeredGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, T[] tArr) {
            super(4);
            this.$itemContent = function5;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyStaggeredGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyStaggeredGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C423@18956L32:LazyStaggeredGridDsl.kt#fzvcnm");
            if ((i2 & 14) == 0) {
                i3 = (composer.changed(items) ? 4 : 2) | i2;
            } else {
                i3 = i2;
            }
            if ((i2 & 112) == 0) {
                i3 |= composer.changed(i) ? 32 : 16;
            }
            if ((i3 & 731) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-804487775, i3, -1, "androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed.<anonymous> (LazyStaggeredGridDsl.kt:423)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), this.$items[i], composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
