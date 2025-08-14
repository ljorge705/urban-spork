package androidx.compose.foundation.lazy.grid;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* compiled from: LazyGridDsl.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a~\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a~\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001a\u001a&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001dH\u0002\u001aE\u0010!\u001a\u001f\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\"¢\u0006\u0002\b\u00162\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010%\u001aE\u0010&\u001a\u001f\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\"¢\u0006\u0002\b\u00162\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0003ø\u0001\u0000¢\u0006\u0002\u0010'\u001aá\u0001\u0010(\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0*2%\b\n\u0010+\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\u001420\b\n\u00100\u001a*\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010\"¢\u0006\u0002\b\u00162%\b\n\u00103\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\u001423\b\u0004\u00104\u001a-\u0012\u0004\u0012\u000205\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b6¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u00107\u001aá\u0001\u0010(\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0\u001c2%\b\n\u0010+\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\u001420\b\n\u00100\u001a*\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010\"¢\u0006\u0002\b\u00162%\b\n\u00103\u001a\u001f\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\u001423\b\u0004\u00104\u001a-\u0012\u0004\u0012\u000205\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b6¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u00108\u001aµ\u0002\u00109\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0*2:\b\n\u0010+\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\"2E\b\n\u00100\u001a?\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010;¢\u0006\u0002\b\u00162:\b\u0006\u00103\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\"2H\b\u0004\u00104\u001aB\u0012\u0004\u0012\u000205\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\b6¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010<\u001aµ\u0002\u00109\u001a\u00020\u0001\"\u0004\b\u0000\u0010)*\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0\u001c2:\b\n\u0010+\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020/\u0018\u00010\"2E\b\n\u00100\u001a?\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u000202\u0018\u00010;¢\u0006\u0002\b\u00162:\b\u0006\u00103\u001a4\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0006\u0012\u0004\u0018\u00010/0\"2H\b\u0004\u00104\u001aB\u0012\u0004\u0012\u000205\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(:\u0012\u0013\u0012\u0011H)¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00010;¢\u0006\u0002\b6¢\u0006\u0002\b\u0016H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010=\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006>"}, d2 = {"LazyHorizontalGrid", "", "rows", "Landroidx/compose/foundation/lazy/grid/GridCells;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyVerticalGrid", "columns", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "calculateCellsCrossAxisSizeImpl", "", "", "gridSize", "slotCount", "spacing", "rememberColumnWidthSums", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "rememberRowHeightSums", "(Landroidx/compose/foundation/lazy/grid/GridCells;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", FirebaseAnalytics.Param.ITEMS, ExifInterface.GPS_DIRECTION_TRUE, "", Constants.KEY_KEY, "Lkotlin/ParameterName;", "name", com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM, "", "span", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", CMSAttributeTableGenerator.CONTENT_TYPE, "itemContent", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/grid/LazyGridScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridDslKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:174:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyVerticalGrid(final androidx.compose.foundation.lazy.grid.GridCells r27, androidx.compose.ui.Modifier r28, androidx.compose.foundation.lazy.grid.LazyGridState r29, androidx.compose.foundation.layout.PaddingValues r30, boolean r31, androidx.compose.foundation.layout.Arrangement.Vertical r32, androidx.compose.foundation.layout.Arrangement.Horizontal r33, androidx.compose.foundation.gestures.FlingBehavior r34, boolean r35, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.grid.LazyGridScope, kotlin.Unit> r36, androidx.compose.runtime.Composer r37, final int r38, final int r39) {
        /*
            Method dump skipped, instructions count: 669
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyVerticalGrid(androidx.compose.foundation.lazy.grid.GridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:174:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyHorizontalGrid(final androidx.compose.foundation.lazy.grid.GridCells r27, androidx.compose.ui.Modifier r28, androidx.compose.foundation.lazy.grid.LazyGridState r29, androidx.compose.foundation.layout.PaddingValues r30, boolean r31, androidx.compose.foundation.layout.Arrangement.Horizontal r32, androidx.compose.foundation.layout.Arrangement.Vertical r33, androidx.compose.foundation.gestures.FlingBehavior r34, boolean r35, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.grid.LazyGridScope, kotlin.Unit> r36, androidx.compose.runtime.Composer r37, final int r38, final int r39) {
        /*
            Method dump skipped, instructions count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridDslKt.LazyHorizontalGrid(androidx.compose.foundation.lazy.grid.GridCells, androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.gestures.FlingBehavior, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Function2<Density, Constraints, List<Integer>> rememberColumnWidthSums(final GridCells gridCells, final Arrangement.Horizontal horizontal, final PaddingValues paddingValues, Composer composer, int i) {
        composer.startReplaceableGroup(-1355301804);
        ComposerKt.sourceInformation(composer, "C(rememberColumnWidthSums)P(!1,2)152@6816L830:LazyGridDsl.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1355301804, i, -1, "androidx.compose.foundation.lazy.grid.rememberColumnWidthSums (LazyGridDsl.kt:148)");
        }
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(gridCells) | composer.changed(horizontal) | composer.changed(paddingValues);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function2) new Function2<Density, Constraints, List<Integer>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt$rememberColumnWidthSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ List<Integer> invoke(Density density, Constraints constraints) {
                    return m843invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final List<Integer> m843invoke0kLqBqw(Density density, long j) {
                    Intrinsics.checkNotNullParameter(density, "$this$null");
                    if (Constraints.m4346getMaxWidthimpl(j) == Integer.MAX_VALUE) {
                        throw new IllegalArgumentException("LazyVerticalGrid's width should be bound by parent.".toString());
                    }
                    List<Integer> mutableList = CollectionsKt.toMutableList((Collection) gridCells.calculateCrossAxisCellSizes(density, Constraints.m4346getMaxWidthimpl(j) - density.mo571roundToPx0680j_4(Dp.m4390constructorimpl(PaddingKt.calculateStartPadding(paddingValues, LayoutDirection.Ltr) + PaddingKt.calculateEndPadding(paddingValues, LayoutDirection.Ltr))), density.mo571roundToPx0680j_4(horizontal.getSpacing())));
                    int size = mutableList.size();
                    for (int i2 = 1; i2 < size; i2++) {
                        mutableList.set(i2, Integer.valueOf(mutableList.get(i2).intValue() + mutableList.get(i2 - 1).intValue()));
                    }
                    return mutableList;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<Density, Constraints, List<Integer>> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }

    private static final Function2<Density, Constraints, List<Integer>> rememberRowHeightSums(final GridCells gridCells, final Arrangement.Vertical vertical, final PaddingValues paddingValues, Composer composer, int i) {
        composer.startReplaceableGroup(239683573);
        ComposerKt.sourceInformation(composer, "C(rememberRowHeightSums)P(1,2)184@7885L786:LazyGridDsl.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(239683573, i, -1, "androidx.compose.foundation.lazy.grid.rememberRowHeightSums (LazyGridDsl.kt:180)");
        }
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(gridCells) | composer.changed(vertical) | composer.changed(paddingValues);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function2) new Function2<Density, Constraints, List<Integer>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt$rememberRowHeightSums$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ List<Integer> invoke(Density density, Constraints constraints) {
                    return m844invoke0kLqBqw(density, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final List<Integer> m844invoke0kLqBqw(Density density, long j) {
                    Intrinsics.checkNotNullParameter(density, "$this$null");
                    if (Constraints.m4345getMaxHeightimpl(j) == Integer.MAX_VALUE) {
                        throw new IllegalArgumentException("LazyHorizontalGrid's height should be bound by parent.".toString());
                    }
                    List<Integer> mutableList = CollectionsKt.toMutableList((Collection) gridCells.calculateCrossAxisCellSizes(density, Constraints.m4345getMaxHeightimpl(j) - density.mo571roundToPx0680j_4(Dp.m4390constructorimpl(paddingValues.getTop() + paddingValues.getBottom())), density.mo571roundToPx0680j_4(vertical.getSpacing())));
                    int size = mutableList.size();
                    for (int i2 = 1; i2 < size; i2++) {
                        mutableList.set(i2, Integer.valueOf(mutableList.get(i2).intValue() + mutableList.get(i2 - 1).intValue()));
                    }
                    return mutableList;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<Density, Constraints, List<Integer>> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> calculateCellsCrossAxisSizeImpl(int i, int i2, int i3) {
        int i4 = i - (i3 * (i2 - 1));
        int i5 = i4 / i2;
        int i6 = i4 % i2;
        ArrayList arrayList = new ArrayList(i2);
        int i7 = 0;
        while (i7 < i2) {
            arrayList.add(Integer.valueOf((i7 < i6 ? 1 : 0) + i5));
            i7++;
        }
        return arrayList;
    }

    public static /* synthetic */ void items$default(LazyGridScope lazyGridScope, List items, Function1 function1, Function2 function2, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function2 = null;
        }
        if ((i & 8) != 0) {
            contentType = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.items.1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke((C03311) obj2);
                }
            };
        }
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), function1 != null ? new AnonymousClass2(function1, items) : null, function2 != null ? new AnonymousClass3(function2, items) : null, new AnonymousClass4(contentType, items), ComposableLambdaKt.composableLambdaInstance(699646206, true, new AnonymousClass5(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$2, reason: invalid class name */
    public static final class AnonymousClass2 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
            super(1);
            this.$key = function1;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$key.invoke(this.$items.get(i));
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$3, reason: invalid class name */
    public static final class AnonymousClass3 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function2<LazyGridItemSpanScope, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, List<? extends T> list) {
            super(2);
            this.$span = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m816boximpl(m839invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m839invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, this.$items.get(i)).getPackedValue();
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$4, reason: invalid class name */
    public static final class AnonymousClass4 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Function1<? super T, ? extends Object> function1, List<? extends T> list) {
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

    public static final <T> void items(LazyGridScope lazyGridScope, List<? extends T> items, Function1<? super T, ? extends Object> function1, Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, Function1<? super T, ? extends Object> contentType, Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), function1 != null ? new AnonymousClass2(function1, items) : null, function2 != null ? new AnonymousClass3(function2, items) : null, new AnonymousClass4(contentType, items), ComposableLambdaKt.composableLambdaInstance(699646206, true, new AnonymousClass5(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$5, reason: invalid class name */
    public static final class AnonymousClass5 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, List<? extends T> list) {
            super(4);
            this.$itemContent = function4;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C391@16605L22:LazyGridDsl.kt#7791vq");
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
                ComposerKt.traceEventStart(699646206, i3, -1, "androidx.compose.foundation.lazy.grid.items.<anonymous> (LazyGridDsl.kt:390)");
            }
            this.$itemContent.invoke(items, this.$items.get(i), composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyGridScope lazyGridScope, List items, Function2 function2, Function3 function3, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = null;
        }
        if ((i & 8) != 0) {
            contentType = new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.itemsIndexed.1
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), function2 != null ? new C03342(function2, items) : null, function3 != null ? new C03353(function3, items) : null, new C03364(contentType, items), ComposableLambdaKt.composableLambdaInstance(1229287273, true, new C03375(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$2, reason: invalid class name and case insensitive filesystem */
    public static final class C03342 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03342(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
            super(1);
            this.$key = function2;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$key.invoke(Integer.valueOf(i), this.$items.get(i));
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$3, reason: invalid class name and case insensitive filesystem */
    public static final class C03353 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ List<T> $items;
        final /* synthetic */ Function3<LazyGridItemSpanScope, Integer, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03353(Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, List<? extends T> list) {
            super(2);
            this.$span = function3;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m816boximpl(m841invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m841invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, Integer.valueOf(i), this.$items.get(i)).getPackedValue();
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$4, reason: invalid class name and case insensitive filesystem */
    public static final class C03364 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03364(Function2<? super Integer, ? super T, ? extends Object> function2, List<? extends T> list) {
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

    public static final <T> void itemsIndexed(LazyGridScope lazyGridScope, List<? extends T> items, Function2<? super Integer, ? super T, ? extends Object> function2, Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, Function2<? super Integer, ? super T, ? extends Object> contentType, Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.size(), function2 != null ? new C03342(function2, items) : null, function3 != null ? new C03353(function3, items) : null, new C03364(contentType, items), ComposableLambdaKt.composableLambdaInstance(1229287273, true, new C03375(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$5, reason: invalid class name and case insensitive filesystem */
    public static final class C03375 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ List<T> $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03375(Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, List<? extends T> list) {
            super(4);
            this.$itemContent = function5;
            this.$items = list;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C424@18380L26:LazyGridDsl.kt#7791vq");
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
                ComposerKt.traceEventStart(1229287273, i3, -1, "androidx.compose.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:423)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), this.$items.get(i), composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void items$default(LazyGridScope lazyGridScope, Object[] items, Function1 function1, Function2 function2, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function2 = null;
        }
        if ((i & 8) != 0) {
            contentType = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.items.6
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
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, function1 != null ? new AnonymousClass7(function1, items) : null, function2 != null ? new AnonymousClass8(function2, items) : null, new AnonymousClass9(contentType, items), ComposableLambdaKt.composableLambdaInstance(407562193, true, new AnonymousClass10(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$7, reason: invalid class name */
    public static final class AnonymousClass7 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function1<T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(Function1<? super T, ? extends Object> function1, T[] tArr) {
            super(1);
            this.$key = function1;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$key.invoke(this.$items[i]);
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$8, reason: invalid class name */
    public static final class AnonymousClass8 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function2<LazyGridItemSpanScope, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, T[] tArr) {
            super(2);
            this.$span = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m816boximpl(m840invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m840invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, this.$items[i]).getPackedValue();
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$9, reason: invalid class name */
    public static final class AnonymousClass9 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function1<T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(Function1<? super T, ? extends Object> function1, T[] tArr) {
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

    public static final <T> void items(LazyGridScope lazyGridScope, T[] items, Function1<? super T, ? extends Object> function1, Function2<? super LazyGridItemSpanScope, ? super T, GridItemSpan> function2, Function1<? super T, ? extends Object> contentType, Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, function1 != null ? new AnonymousClass7(function1, items) : null, function2 != null ? new AnonymousClass8(function2, items) : null, new AnonymousClass9(contentType, items), ComposableLambdaKt.composableLambdaInstance(407562193, true, new AnonymousClass10(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$items$10, reason: invalid class name */
    public static final class AnonymousClass10 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function4<LazyGridItemScope, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(Function4<? super LazyGridItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4, T[] tArr) {
            super(4);
            this.$itemContent = function4;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C457@20033L22:LazyGridDsl.kt#7791vq");
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
                ComposerKt.traceEventStart(407562193, i3, -1, "androidx.compose.foundation.lazy.grid.items.<anonymous> (LazyGridDsl.kt:456)");
            }
            this.$itemContent.invoke(items, this.$items[i], composer, Integer.valueOf(i3 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static /* synthetic */ void itemsIndexed$default(LazyGridScope lazyGridScope, Object[] items, Function2 function2, Function3 function3, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = null;
        }
        if ((i & 8) != 0) {
            contentType = new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridDslKt.itemsIndexed.6
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, function2 != null ? new C03397(function2, items) : null, function3 != null ? new C03408(function3, items) : null, new C03419(contentType, items), ComposableLambdaKt.composableLambdaInstance(-911455938, true, new C033310(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$7, reason: invalid class name and case insensitive filesystem */
    public static final class C03397 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function2<Integer, T, Object> $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03397(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
            super(1);
            this.$key = function2;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Integer num) {
            return invoke(num.intValue());
        }

        public final Object invoke(int i) {
            return this.$key.invoke(Integer.valueOf(i), this.$items[i]);
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$8, reason: invalid class name and case insensitive filesystem */
    public static final class C03408 extends Lambda implements Function2<LazyGridItemSpanScope, Integer, GridItemSpan> {
        final /* synthetic */ T[] $items;
        final /* synthetic */ Function3<LazyGridItemSpanScope, Integer, T, GridItemSpan> $span;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03408(Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, T[] tArr) {
            super(2);
            this.$span = function3;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ GridItemSpan invoke(LazyGridItemSpanScope lazyGridItemSpanScope, Integer num) {
            return GridItemSpan.m816boximpl(m842invoke_orMbw(lazyGridItemSpanScope, num.intValue()));
        }

        /* renamed from: invoke-_-orMbw, reason: not valid java name */
        public final long m842invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
            Intrinsics.checkNotNullParameter(lazyGridItemSpanScope, "$this$null");
            return this.$span.invoke(lazyGridItemSpanScope, Integer.valueOf(i), this.$items[i]).getPackedValue();
        }
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$9, reason: invalid class name and case insensitive filesystem */
    public static final class C03419 extends Lambda implements Function1<Integer, Object> {
        final /* synthetic */ Function2<Integer, T, Object> $contentType;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03419(Function2<? super Integer, ? super T, ? extends Object> function2, T[] tArr) {
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

    public static final <T> void itemsIndexed(LazyGridScope lazyGridScope, T[] items, Function2<? super Integer, ? super T, ? extends Object> function2, Function3<? super LazyGridItemSpanScope, ? super Integer, ? super T, GridItemSpan> function3, Function2<? super Integer, ? super T, ? extends Object> contentType, Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(lazyGridScope, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        lazyGridScope.items(items.length, function2 != null ? new C03397(function2, items) : null, function3 != null ? new C03408(function3, items) : null, new C03419(contentType, items), ComposableLambdaKt.composableLambdaInstance(-911455938, true, new C033310(itemContent, items)));
    }

    /* compiled from: LazyGridDsl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "it", "", "invoke", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;ILandroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 176)
    /* renamed from: androidx.compose.foundation.lazy.grid.LazyGridDslKt$itemsIndexed$10, reason: invalid class name and case insensitive filesystem */
    public static final class C033310 extends Lambda implements Function4<LazyGridItemScope, Integer, Composer, Integer, Unit> {
        final /* synthetic */ Function5<LazyGridItemScope, Integer, T, Composer, Integer, Unit> $itemContent;
        final /* synthetic */ T[] $items;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C033310(Function5<? super LazyGridItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5, T[] tArr) {
            super(4);
            this.$itemContent = function5;
            this.$items = tArr;
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(LazyGridItemScope lazyGridItemScope, Integer num, Composer composer, Integer num2) {
            invoke(lazyGridItemScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyGridItemScope items, int i, Composer composer, int i2) {
            int i3;
            Intrinsics.checkNotNullParameter(items, "$this$items");
            ComposerKt.sourceInformation(composer, "C490@21812L26:LazyGridDsl.kt#7791vq");
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
                ComposerKt.traceEventStart(-911455938, i3, -1, "androidx.compose.foundation.lazy.grid.itemsIndexed.<anonymous> (LazyGridDsl.kt:489)");
            }
            this.$itemContent.invoke(items, Integer.valueOf(i), this.$items[i], composer, Integer.valueOf((i3 & 14) | (i3 & 112)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
