package androidx.compose.foundation.pager;

import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.LazyListSnapLayoutInfoProviderKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Pager.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001aÚ\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00042%\b\u0002\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001c2\b\b\u0002\u0010!\u001a\u00020\"2&\u0010#\u001a\"\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b%H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001aÖ\u0001\u0010(\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010+\u001a\u00020,2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042#\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001c2\u0006\u0010!\u001a\u00020\"2&\u0010#\u001a\"\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b%H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a&\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\r2\u0006\u00102\u001a\u0002032\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0002\u001aÚ\u0001\u00107\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00042%\b\u0002\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001c2\b\b\u0002\u0010!\u001a\u00020\"2&\u0010#\u001a\"\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\u0002\b%H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00109\u001a(\u0010:\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*2\u0006\u0010;\u001a\u00020<H\u0002ø\u0001\u0001¢\u0006\u0002\u0010=\u001a\u0017\u0010>\u001a\u00020\b2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0082\b\u001a!\u0010B\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010C\u001a\u00020\u0004H\u0003¢\u0006\u0002\u0010D\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006E"}, d2 = {"ConsumeHorizontalFlingNestedScrollConnection", "Landroidx/compose/foundation/pager/ConsumeAllFlingOnDirection;", "ConsumeVerticalFlingNestedScrollConnection", "DEBUG", "", "LowVelocityAnimationDefaultDuration", "", "HorizontalPager", "", "pageCount", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "beyondBoundsPageCount", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "userScrollEnabled", "reverseLayout", Constants.KEY_KEY, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "pageContent", "page", "Landroidx/compose/runtime/Composable;", "HorizontalPager-AlbwjTQ", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "Pager", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "Pager-wKDqQAw", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;ILandroidx/compose/foundation/pager/PageSize;FLandroidx/compose/foundation/gestures/Orientation;ILandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SnapLayoutInfoProvider", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "pagerState", "pagerSnapDistance", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "VerticalPager", "VerticalPager-AlbwjTQ", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "calculateContentPaddings", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "pagerSemantics", "isVertical", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerKt {
    private static final ConsumeAllFlingOnDirection ConsumeHorizontalFlingNestedScrollConnection = new ConsumeAllFlingOnDirection(Orientation.Horizontal);
    private static final ConsumeAllFlingOnDirection ConsumeVerticalFlingNestedScrollConnection = new ConsumeAllFlingOnDirection(Orientation.Vertical);
    private static final boolean DEBUG = false;
    private static final int LowVelocityAnimationDefaultDuration = 500;

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012f  */
    /* renamed from: HorizontalPager-AlbwjTQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m928HorizontalPagerAlbwjTQ(final int r35, androidx.compose.ui.Modifier r36, androidx.compose.foundation.pager.PagerState r37, androidx.compose.foundation.layout.PaddingValues r38, androidx.compose.foundation.pager.PageSize r39, int r40, float r41, androidx.compose.ui.Alignment.Vertical r42, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior r43, boolean r44, boolean r45, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r46, androidx.compose.ui.input.nestedscroll.NestedScrollConnection r47, final kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r48, androidx.compose.runtime.Composer r49, final int r50, final int r51, final int r52) {
        /*
            Method dump skipped, instructions count: 962
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt.m928HorizontalPagerAlbwjTQ(int, androidx.compose.ui.Modifier, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.pager.PageSize, int, float, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior, boolean, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012f  */
    /* renamed from: VerticalPager-AlbwjTQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m930VerticalPagerAlbwjTQ(final int r35, androidx.compose.ui.Modifier r36, androidx.compose.foundation.pager.PagerState r37, androidx.compose.foundation.layout.PaddingValues r38, androidx.compose.foundation.pager.PageSize r39, int r40, float r41, androidx.compose.ui.Alignment.Horizontal r42, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior r43, boolean r44, boolean r45, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r46, androidx.compose.ui.input.nestedscroll.NestedScrollConnection r47, final kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r48, androidx.compose.runtime.Composer r49, final int r50, final int r51, final int r52) {
        /*
            Method dump skipped, instructions count: 962
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt.m930VerticalPagerAlbwjTQ(int, androidx.compose.ui.Modifier, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.pager.PageSize, int, float, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior, boolean, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* renamed from: Pager-wKDqQAw, reason: not valid java name */
    public static final void m929PagerwKDqQAw(final Modifier modifier, final PagerState state, final int i, final PageSize pageSize, final float f, final Orientation orientation, final int i2, Alignment.Vertical vertical, Alignment.Horizontal horizontal, final PaddingValues contentPadding, final SnapFlingBehavior flingBehavior, final boolean z, final boolean z2, final Function1<? super Integer, ? extends Object> function1, final NestedScrollConnection pageNestedScrollConnection, final Function3<? super Integer, ? super Composer, ? super Integer, Unit> pageContent, Composer composer, final int i3, final int i4, final int i5) {
        Object obj;
        boolean z3;
        Modifier.Companion companionPagerSemantics;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(pageSize, "pageSize");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(contentPadding, "contentPadding");
        Intrinsics.checkNotNullParameter(flingBehavior, "flingBehavior");
        Intrinsics.checkNotNullParameter(pageNestedScrollConnection, "pageNestedScrollConnection");
        Intrinsics.checkNotNullParameter(pageContent, "pageContent");
        Composer composerStartRestartGroup = composer.startRestartGroup(-765777783);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Pager)P(5,13,8,10,11:c#ui.unit.Dp,6!1,15,3!2,14,12!1,9)264@12945L7,265@13000L7,266@13044L187,274@13262L94,278@13406L77,278@13362L121,282@13511L188,282@13489L210,295@13843L2772:Pager.kt#g6yjnt");
        Alignment.Vertical centerVertically = (i5 & 128) != 0 ? Alignment.INSTANCE.getCenterVertically() : vertical;
        Alignment.Horizontal centerHorizontally = (i5 & 256) != 0 ? Alignment.INSTANCE.getCenterHorizontally() : horizontal;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-765777783, i3, i4, "androidx.compose.foundation.pager.Pager (Pager.kt:240)");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(("beyondBoundsPageCount should be greater than or equal to 0, you selected " + i2).toString());
        }
        boolean z4 = orientation == Orientation.Vertical;
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        boolean z5 = z4;
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        final Density density = (Density) objConsume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
        composerStartRestartGroup.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composerStartRestartGroup.changed(contentPadding) | composerStartRestartGroup.changed(orientation) | composerStartRestartGroup.changed(layoutDirection);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = Dp.m4388boximpl(calculateContentPaddings(contentPadding, orientation, layoutDirection));
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        final float fM4404unboximpl = ((Dp) objRememberedValue).m4404unboximpl();
        int i6 = i3 & 112;
        composerStartRestartGroup.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged2 = composerStartRestartGroup.changed(flingBehavior) | composerStartRestartGroup.changed(state);
        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new PagerWrapperFlingBehavior(flingBehavior, state);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
        }
        composerStartRestartGroup.endReplaceableGroup();
        final PagerWrapperFlingBehavior pagerWrapperFlingBehavior = (PagerWrapperFlingBehavior) objRememberedValue2;
        Dp dpM4388boximpl = Dp.m4388boximpl(f);
        Object objM4388boximpl = Dp.m4388boximpl(f);
        int i7 = (i3 >> 6) & 896;
        composerStartRestartGroup.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged3 = composerStartRestartGroup.changed(objM4388boximpl) | composerStartRestartGroup.changed(density) | composerStartRestartGroup.changed(state);
        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            obj = (Function2) new PagerKt$Pager$2$1(density, state, f, null);
            composerStartRestartGroup.updateRememberedValue(obj);
        } else {
            obj = objRememberedValue3;
        }
        composerStartRestartGroup.endReplaceableGroup();
        EffectsKt.LaunchedEffect(density, state, dpM4388boximpl, (Function2) obj, composerStartRestartGroup, i7 | i6 | 4096);
        int i8 = (i3 >> 3) & 14;
        composerStartRestartGroup.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged4 = composerStartRestartGroup.changed(state);
        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = (Function2) new PagerKt$Pager$3$1(state, null);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
        }
        composerStartRestartGroup.endReplaceableGroup();
        EffectsKt.LaunchedEffect(state, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue4, composerStartRestartGroup, i8 | 64);
        composerStartRestartGroup.startReplaceableGroup(1445594592);
        ComposerKt.sourceInformation(composerStartRestartGroup, "290@13768L33");
        if (z) {
            z3 = z5;
            companionPagerSemantics = pagerSemantics(Modifier.INSTANCE, state, z3, composerStartRestartGroup, i6 | 6);
        } else {
            z3 = z5;
            companionPagerSemantics = Modifier.INSTANCE;
        }
        composerStartRestartGroup.endReplaceableGroup();
        final boolean z6 = z3;
        final Alignment.Horizontal horizontal2 = centerHorizontally;
        final Alignment.Vertical vertical2 = centerVertically;
        BoxWithConstraintsKt.BoxWithConstraints(modifier.then(companionPagerSemantics), null, false, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1677736225, true, new Function3<BoxWithConstraintsScope, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.PagerKt$Pager$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer2, Integer num) {
                invoke(boxWithConstraintsScope, composer2, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:43:0x010b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope r27, androidx.compose.runtime.Composer r28, int r29) {
                /*
                    Method dump skipped, instructions count: 447
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt$Pager$4.invoke(androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):void");
            }
        }), composerStartRestartGroup, CipherStorageKeystoreRsaEcb.ENCRYPTION_KEY_SIZE, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Alignment.Vertical vertical3 = centerVertically;
        final Alignment.Horizontal horizontal3 = centerHorizontally;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.PagerKt$Pager$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i9) {
                PagerKt.m929PagerwKDqQAw(modifier, state, i, pageSize, f, orientation, i2, vertical3, horizontal3, contentPadding, flingBehavior, z, z2, function1, pageNestedScrollConnection, pageContent, composer2, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateContentPaddings(PaddingValues paddingValues, Orientation orientation, LayoutDirection layoutDirection) {
        float fMo671calculateLeftPaddingu2uoSUM;
        float fMo672calculateRightPaddingu2uoSUM;
        if (orientation == Orientation.Vertical) {
            fMo671calculateLeftPaddingu2uoSUM = paddingValues.getTop();
        } else {
            fMo671calculateLeftPaddingu2uoSUM = paddingValues.mo671calculateLeftPaddingu2uoSUM(layoutDirection);
        }
        if (orientation == Orientation.Vertical) {
            fMo672calculateRightPaddingu2uoSUM = paddingValues.getBottom();
        } else {
            fMo672calculateRightPaddingu2uoSUM = paddingValues.mo672calculateRightPaddingu2uoSUM(layoutDirection);
        }
        return Dp.m4390constructorimpl(fMo671calculateLeftPaddingu2uoSUM + fMo672calculateRightPaddingu2uoSUM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final PagerState pagerState, final PagerSnapDistance pagerSnapDistance, final DecayAnimationSpec<Float> decayAnimationSpec) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.pager.PagerKt.SnapLayoutInfoProvider.1
            public final LazyListLayoutInfo getLayoutInfo() {
                return pagerState.getLayoutInfo$foundation_release();
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public ClosedFloatingPointRange<Float> calculateSnappingOffsetBounds(Density density) {
                Intrinsics.checkNotNullParameter(density, "<this>");
                List<LazyListItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
                int size = visibleItemsInfo.size();
                float f = Float.NEGATIVE_INFINITY;
                float f2 = Float.POSITIVE_INFINITY;
                for (int i = 0; i < size; i++) {
                    float fCalculateDistanceToDesiredSnapPosition = LazyListSnapLayoutInfoProviderKt.calculateDistanceToDesiredSnapPosition(density, getLayoutInfo(), visibleItemsInfo.get(i), PagerStateKt.getSnapAlignmentStartToStart());
                    if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f) {
                        f = fCalculateDistanceToDesiredSnapPosition;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f2) {
                        f2 = fCalculateDistanceToDesiredSnapPosition;
                    }
                }
                return RangesKt.rangeTo(f, f2);
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnapStepSize(Density density) {
                Intrinsics.checkNotNullParameter(density, "<this>");
                LazyListLayoutInfo layoutInfo = getLayoutInfo();
                if (!(!layoutInfo.getVisibleItemsInfo().isEmpty())) {
                    return 0.0f;
                }
                List<LazyListItemInfo> visibleItemsInfo = layoutInfo.getVisibleItemsInfo();
                int size = visibleItemsInfo.size();
                int size2 = 0;
                for (int i = 0; i < size; i++) {
                    size2 += visibleItemsInfo.get(i).getSize();
                }
                return size2 / layoutInfo.getVisibleItemsInfo().size();
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(Density density, float f) {
                int currentPage;
                LazyListItemInfo lazyListItemInfo;
                double dFloor;
                Intrinsics.checkNotNullParameter(density, "<this>");
                int pageSize$foundation_release = pagerState.getPageSize$foundation_release() + pagerState.getPageSpacing$foundation_release();
                float fCalculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(decayAnimationSpec, 0.0f, f);
                LazyListItemInfo firstVisiblePage$foundation_release = pagerState.getFirstVisiblePage$foundation_release();
                if (firstVisiblePage$foundation_release != null) {
                    currentPage = firstVisiblePage$foundation_release.getIndex();
                    if (f < 0.0f) {
                        currentPage++;
                    }
                } else {
                    currentPage = pagerState.getCurrentPage();
                }
                List<LazyListItemInfo> visibleItemsInfo = getLayoutInfo().getVisibleItemsInfo();
                int size = visibleItemsInfo.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        lazyListItemInfo = null;
                        break;
                    }
                    lazyListItemInfo = visibleItemsInfo.get(i);
                    if (lazyListItemInfo.getIndex() == currentPage) {
                        break;
                    }
                    i++;
                }
                LazyListItemInfo lazyListItemInfo2 = lazyListItemInfo;
                int offset = lazyListItemInfo2 != null ? lazyListItemInfo2.getOffset() : 0;
                float f2 = ((currentPage * pageSize$foundation_release) + fCalculateTargetValue) / pageSize$foundation_release;
                if (f > 0.0f) {
                    dFloor = Math.ceil(f2);
                } else {
                    dFloor = Math.floor(f2);
                }
                int iCoerceAtLeast = RangesKt.coerceAtLeast(Math.abs((RangesKt.coerceIn(pagerSnapDistance.calculateTargetPage(currentPage, RangesKt.coerceIn((int) dFloor, 0, pagerState.getPageCount$foundation_release()), f, pagerState.getPageSize$foundation_release(), pagerState.getPageSpacing$foundation_release()), 0, pagerState.getPageCount$foundation_release()) - currentPage) * pageSize$foundation_release) - Math.abs(offset), 0);
                return iCoerceAtLeast == 0 ? iCoerceAtLeast : iCoerceAtLeast * Math.signum(f);
            }
        };
    }

    private static final Modifier pagerSemantics(Modifier modifier, final PagerState pagerState, final boolean z, Composer composer, int i) {
        composer.startReplaceableGroup(1509835088);
        ComposerKt.sourceInformation(composer, "C(pagerSemantics)P(1)739@30533L24:Pager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1509835088, i, -1, "androidx.compose.foundation.pager.pagerSemantics (Pager.kt:738)");
        }
        composer.startReplaceableGroup(773894976);
        ComposerKt.sourceInformation(composer, "CC(rememberCoroutineScope)476@19869L144:Effects.kt#9igjgp");
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
            composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
            objRememberedValue = compositionScopedCoroutineScopeCanceller;
        }
        composer.endReplaceableGroup();
        final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then(SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.pager.PagerKt.pagerSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semantics) {
                Intrinsics.checkNotNullParameter(semantics, "$this$semantics");
                if (z) {
                    final PagerState pagerState2 = pagerState;
                    final CoroutineScope coroutineScope2 = coroutineScope;
                    SemanticsPropertiesKt.pageUp$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.pager.PagerKt.pagerSemantics.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(PagerKt.pagerSemantics$performBackwardPaging(pagerState2, coroutineScope2));
                        }
                    }, 1, null);
                    final PagerState pagerState3 = pagerState;
                    final CoroutineScope coroutineScope3 = coroutineScope;
                    SemanticsPropertiesKt.pageDown$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.pager.PagerKt.pagerSemantics.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(PagerKt.pagerSemantics$performForwardPaging(pagerState3, coroutineScope3));
                        }
                    }, 1, null);
                    return;
                }
                final PagerState pagerState4 = pagerState;
                final CoroutineScope coroutineScope4 = coroutineScope;
                SemanticsPropertiesKt.pageLeft$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.pager.PagerKt.pagerSemantics.1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(PagerKt.pagerSemantics$performBackwardPaging(pagerState4, coroutineScope4));
                    }
                }, 1, null);
                final PagerState pagerState5 = pagerState;
                final CoroutineScope coroutineScope5 = coroutineScope;
                SemanticsPropertiesKt.pageRight$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.foundation.pager.PagerKt.pagerSemantics.1.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(PagerKt.pagerSemantics$performForwardPaging(pagerState5, coroutineScope5));
                    }
                }, 1, null);
            }
        }, 1, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performForwardPaging(PagerState pagerState, CoroutineScope coroutineScope) {
        if (!pagerState.getCanScrollForward()) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PagerKt$pagerSemantics$performForwardPaging$1(pagerState, null), 3, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performBackwardPaging(PagerState pagerState, CoroutineScope coroutineScope) {
        if (!pagerState.getCanScrollBackward()) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PagerKt$pagerSemantics$performBackwardPaging$1(pagerState, null), 3, null);
        return true;
    }
}
