package androidx.compose.material;

import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: Menu.kt */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001cH\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001aa\u0010\u001e\u001a\u00020\u000f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001b¢\u0006\u0002\b\u001cH\u0001¢\u0006\u0002\u0010'\u001a \u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*H\u0000ø\u0001\u0000¢\u0006\u0002\u0010,\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0019\u0010\u0006\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0007\u0010\b\"\u000e\u0010\t\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\"\u0013\u0010\u000b\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\f\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\r\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"DropdownMenuItemDefaultMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "DropdownMenuItemDefaultMinHeight", "DropdownMenuItemDefaultMinWidth", "DropdownMenuItemHorizontalPadding", "DropdownMenuVerticalPadding", "getDropdownMenuVerticalPadding", "()F", "InTransitionDuration", "", "MenuElevation", "MenuVerticalMargin", "OutTransitionDuration", "DropdownMenuContent", "", "expandedStates", "Landroidx/compose/animation/core/MutableTransitionState;", "", "transformOriginState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/TransformOrigin;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItemContent", "onClick", "Lkotlin/Function0;", ViewProps.ENABLED, "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "calculateTransformOrigin", "parentBounds", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)J", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MenuKt {
    private static final float DropdownMenuItemDefaultMinHeight;
    private static final float DropdownMenuVerticalPadding;
    public static final int InTransitionDuration = 120;
    private static final float MenuElevation;
    private static final float MenuVerticalMargin;
    public static final int OutTransitionDuration = 75;
    private static final float DropdownMenuItemHorizontalPadding = Dp.m4390constructorimpl(16);
    private static final float DropdownMenuItemDefaultMinWidth = Dp.m4390constructorimpl(112);
    private static final float DropdownMenuItemDefaultMaxWidth = Dp.m4390constructorimpl(280);

    public static final float getDropdownMenuVerticalPadding() {
        return DropdownMenuVerticalPadding;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DropdownMenuContent(final androidx.compose.animation.core.MutableTransitionState<java.lang.Boolean> r21, final androidx.compose.runtime.MutableState<androidx.compose.ui.graphics.TransformOrigin> r22, androidx.compose.ui.Modifier r23, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.MenuKt.DropdownMenuContent(androidx.compose.animation.core.MutableTransitionState, androidx.compose.runtime.MutableState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DropdownMenuItemContent(final kotlin.jvm.functions.Function0<kotlin.Unit> r23, androidx.compose.ui.Modifier r24, boolean r25, androidx.compose.foundation.layout.PaddingValues r26, androidx.compose.foundation.interaction.MutableInteractionSource r27, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instructions count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.MenuKt.DropdownMenuItemContent(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long calculateTransformOrigin(androidx.compose.ui.unit.IntRect r5, androidx.compose.ui.unit.IntRect r6) {
        /*
            java.lang.String r0 = "parentBounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "menuBounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            int r0 = r6.getLeft()
            int r1 = r5.getRight()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            if (r0 < r1) goto L19
        L17:
            r0 = r3
            goto L53
        L19:
            int r0 = r6.getRight()
            int r1 = r5.getLeft()
            if (r0 > r1) goto L25
            r0 = r2
            goto L53
        L25:
            int r0 = r6.getWidth()
            if (r0 != 0) goto L2c
            goto L17
        L2c:
            int r0 = r5.getLeft()
            int r1 = r6.getLeft()
            int r0 = java.lang.Math.max(r0, r1)
            int r1 = r5.getRight()
            int r4 = r6.getRight()
            int r1 = java.lang.Math.min(r1, r4)
            int r0 = r0 + r1
            int r0 = r0 / 2
            int r1 = r6.getLeft()
            int r0 = r0 - r1
            float r0 = (float) r0
            int r1 = r6.getWidth()
            float r1 = (float) r1
            float r0 = r0 / r1
        L53:
            int r1 = r6.getTop()
            int r4 = r5.getBottom()
            if (r1 < r4) goto L5f
        L5d:
            r2 = r3
            goto L99
        L5f:
            int r1 = r6.getBottom()
            int r4 = r5.getTop()
            if (r1 > r4) goto L6a
            goto L99
        L6a:
            int r1 = r6.getHeight()
            if (r1 != 0) goto L71
            goto L5d
        L71:
            int r1 = r5.getTop()
            int r2 = r6.getTop()
            int r1 = java.lang.Math.max(r1, r2)
            int r5 = r5.getBottom()
            int r2 = r6.getBottom()
            int r5 = java.lang.Math.min(r5, r2)
            int r1 = r1 + r5
            int r1 = r1 / 2
            int r5 = r6.getTop()
            int r1 = r1 - r5
            float r5 = (float) r1
            int r6 = r6.getHeight()
            float r6 = (float) r6
            float r2 = r5 / r6
        L99:
            long r5 = androidx.compose.ui.graphics.TransformOriginKt.TransformOrigin(r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.MenuKt.calculateTransformOrigin(androidx.compose.ui.unit.IntRect, androidx.compose.ui.unit.IntRect):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: DropdownMenuContent$lambda-1, reason: not valid java name */
    public static final float m1331DropdownMenuContent$lambda1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: DropdownMenuContent$lambda-3, reason: not valid java name */
    public static final float m1332DropdownMenuContent$lambda3(State<Float> state) {
        return state.getValue().floatValue();
    }

    static {
        float f = 8;
        MenuElevation = Dp.m4390constructorimpl(f);
        float f2 = 48;
        MenuVerticalMargin = Dp.m4390constructorimpl(f2);
        DropdownMenuVerticalPadding = Dp.m4390constructorimpl(f);
        DropdownMenuItemDefaultMinHeight = Dp.m4390constructorimpl(f2);
    }
}
