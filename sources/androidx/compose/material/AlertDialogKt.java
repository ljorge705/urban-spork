package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnitKt;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: AlertDialog.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a~\u0010\b\u001a\u00020\t2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a8\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0002\b\fH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a;\u0010\u001e\u001a\u00020\t*\u00020\u001f2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b¢\u0006\u0002\b\fH\u0001¢\u0006\u0002\u0010 \"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0006\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"TextBaselineDistanceFromTitle", "Landroidx/compose/ui/unit/TextUnit;", "J", "TextBaselineDistanceFromTop", "TextPadding", "Landroidx/compose/ui/Modifier;", "TitleBaselineDistanceFromTop", "TitlePadding", "AlertDialogContent", "", Constants.KEY_BUTTONS, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", ViewProps.BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "AlertDialogContent-WMdw5o4", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/runtime/Composer;II)V", "AlertDialogFlowRow", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "content", "AlertDialogFlowRow-ixp7dh8", "(FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AlertDialogBaselineLayout", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AlertDialogKt {
    private static final Modifier TextPadding;
    private static final Modifier TitlePadding;
    private static final long TitleBaselineDistanceFromTop = TextUnitKt.getSp(40);
    private static final long TextBaselineDistanceFromTitle = TextUnitKt.getSp(36);
    private static final long TextBaselineDistanceFromTop = TextUnitKt.getSp(38);

    /* JADX WARN: Removed duplicated region for block: B:100:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f4  */
    /* renamed from: AlertDialogContent-WMdw5o4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1157AlertDialogContentWMdw5o4(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.ui.Modifier r28, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.ui.graphics.Shape r31, long r32, long r34, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AlertDialogKt.m1157AlertDialogContentWMdw5o4(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void AlertDialogBaselineLayout(final ColumnScope columnScope, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(columnScope, "<this>");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1735756929);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AlertDialogBaselineLayout)P(1)96@3561L3479:AlertDialog.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(columnScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(function2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(function22) ? 256 : 128;
        }
        if (((i2 & 731) ^ 146) != 0 || !composerStartRestartGroup.getSkipping()) {
            Modifier modifierWeight = columnScope.weight(Modifier.INSTANCE, 1.0f, false);
            AnonymousClass2 anonymousClass2 = new MeasurePolicy() { // from class: androidx.compose.material.AlertDialogKt.AlertDialogBaselineLayout.2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                /* JADX WARN: Removed duplicated region for block: B:37:0x00a4  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x00cf  */
                /* JADX WARN: Removed duplicated region for block: B:62:0x0103  */
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final androidx.compose.ui.layout.MeasureResult mo287measure3p2s80s(androidx.compose.ui.layout.MeasureScope r12, java.util.List<? extends androidx.compose.ui.layout.Measurable> r13, long r14) {
                    /*
                        Method dump skipped, instructions count: 371
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AlertDialogKt.AnonymousClass2.mo287measure3p2s80s(androidx.compose.ui.layout.MeasureScope, java.util.List, long):androidx.compose.ui.layout.MeasureResult");
                }
            };
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierWeight);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl, anonymousClass2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-1160646213);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C:AlertDialog.kt#jmzs0o");
            if (function2 == null) {
                composerStartRestartGroup.startReplaceableGroup(-1620294017);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(-1160646206);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*99@3629L106");
                Modifier modifierAlign = columnScope.align(LayoutIdKt.layoutId(TitlePadding, "title"), Alignment.INSTANCE.getStart());
                composerStartRestartGroup.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Density density2 = (Density) objConsume3;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LayoutDirection layoutDirection2 = (LayoutDirection) objConsume4;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifierAlign);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerStartRestartGroup.disableReusing();
                Composer composerM1518constructorimpl2 = Updater.m1518constructorimpl(composerStartRestartGroup);
                Updater.m1525setimpl(composerM1518constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl2, density2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composerStartRestartGroup.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(2058660585);
                composerStartRestartGroup.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-630329100);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C*100@3710L7:AlertDialog.kt#jmzs0o");
                function2.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                Unit unit = Unit.INSTANCE;
                composerStartRestartGroup.endReplaceableGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            if (function22 == null) {
                composerStartRestartGroup.startReplaceableGroup(-1620288747);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(-1160646036);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*104@3798L103");
                Modifier modifierAlign2 = columnScope.align(LayoutIdKt.layoutId(TextPadding, "text"), Alignment.INSTANCE.getStart());
                composerStartRestartGroup.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume5 = composerStartRestartGroup.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Density density3 = (Density) objConsume5;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume6 = composerStartRestartGroup.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LayoutDirection layoutDirection3 = (LayoutDirection) objConsume6;
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifierAlign2);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerStartRestartGroup.disableReusing();
                Composer composerM1518constructorimpl3 = Updater.m1518constructorimpl(composerStartRestartGroup);
                Updater.m1525setimpl(composerM1518constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl3, density3, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl3, layoutDirection3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composerStartRestartGroup.enableReusing();
                function3MaterializerOf3.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(2058660585);
                composerStartRestartGroup.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-630328933);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C*105@3877L6:AlertDialog.kt#jmzs0o");
                function22.invoke(composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                Unit unit3 = Unit.INSTANCE;
                composerStartRestartGroup.endReplaceableGroup();
                Unit unit4 = Unit.INSTANCE;
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AlertDialogKt.AlertDialogBaselineLayout.3
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

            public final void invoke(Composer composer2, int i3) {
                AlertDialogKt.AlertDialogBaselineLayout(columnScope, function2, function22, composer2, i | 1);
            }
        });
    }

    /* renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    public static final void m1158AlertDialogFlowRowixp7dh8(final float f, final float f2, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-489408515);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AlertDialogFlowRow)P(2:c#ui.unit.Dp,1:c#ui.unit.Dp)192@7298L3452:AlertDialog.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(content) ? 256 : 128;
        }
        if (((i2 & 731) ^ 146) != 0 || !composerStartRestartGroup.getSkipping()) {
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogFlowRow$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i3) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo287measure3p2s80s(final MeasureScope Layout, List<? extends Measurable> measurables, long j) {
                    int iMax;
                    Ref.IntRef intRef;
                    Ref.IntRef intRef2;
                    ArrayList arrayList;
                    ArrayList arrayList2;
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    final ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    Ref.IntRef intRef3 = new Ref.IntRef();
                    Ref.IntRef intRef4 = new Ref.IntRef();
                    ArrayList arrayList6 = new ArrayList();
                    Ref.IntRef intRef5 = new Ref.IntRef();
                    Ref.IntRef intRef6 = new Ref.IntRef();
                    long jConstraints$default = ConstraintsKt.Constraints$default(0, Constraints.m4346getMaxWidthimpl(j), 0, 0, 13, null);
                    Iterator<? extends Measurable> it = measurables.iterator();
                    while (it.hasNext()) {
                        Placeable placeableMo3396measureBRTryo0 = it.next().mo3396measureBRTryo0(jConstraints$default);
                        long j2 = jConstraints$default;
                        Ref.IntRef intRef7 = intRef6;
                        if (measure_3p2s80s$canAddToCurrentSequence(arrayList6, intRef5, Layout, f, j, placeableMo3396measureBRTryo0)) {
                            intRef = intRef5;
                            intRef2 = intRef4;
                            arrayList = arrayList5;
                            arrayList2 = arrayList6;
                        } else {
                            intRef = intRef5;
                            ArrayList arrayList7 = arrayList5;
                            arrayList = arrayList5;
                            arrayList2 = arrayList6;
                            intRef2 = intRef4;
                            measure_3p2s80s$startNewSequence(arrayList3, intRef4, Layout, f2, arrayList6, arrayList4, intRef7, arrayList7, intRef3, intRef);
                        }
                        Ref.IntRef intRef8 = intRef;
                        if (!arrayList2.isEmpty()) {
                            intRef8.element += Layout.mo571roundToPx0680j_4(f);
                        }
                        arrayList2.add(placeableMo3396measureBRTryo0);
                        intRef8.element += placeableMo3396measureBRTryo0.getWidth();
                        intRef6 = intRef7;
                        intRef6.element = Math.max(intRef6.element, placeableMo3396measureBRTryo0.getHeight());
                        intRef5 = intRef8;
                        arrayList6 = arrayList2;
                        jConstraints$default = j2;
                        arrayList5 = arrayList;
                        intRef4 = intRef2;
                    }
                    Ref.IntRef intRef9 = intRef4;
                    final ArrayList arrayList8 = arrayList5;
                    Ref.IntRef intRef10 = intRef5;
                    ArrayList arrayList9 = arrayList6;
                    if (!arrayList9.isEmpty()) {
                        measure_3p2s80s$startNewSequence(arrayList3, intRef9, Layout, f2, arrayList9, arrayList4, intRef6, arrayList8, intRef3, intRef10);
                    }
                    if (Constraints.m4346getMaxWidthimpl(j) != Integer.MAX_VALUE) {
                        iMax = Constraints.m4346getMaxWidthimpl(j);
                    } else {
                        iMax = Math.max(intRef3.element, Constraints.m4348getMinWidthimpl(j));
                    }
                    final int i3 = iMax;
                    int iMax2 = Math.max(intRef9.element, Constraints.m4347getMinHeightimpl(j));
                    final float f3 = f;
                    return MeasureScope.layout$default(Layout, i3, iMax2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogFlowRow$1$measure$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout) {
                            int i4;
                            int i5;
                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                            List<List<Placeable>> list = arrayList3;
                            MeasureScope measureScope = Layout;
                            float f4 = f3;
                            int i6 = i3;
                            List<Integer> list2 = arrayList8;
                            int size = list.size() - 1;
                            if (size < 0) {
                                return;
                            }
                            int i7 = 0;
                            while (true) {
                                int i8 = i7 + 1;
                                List<Placeable> list3 = list.get(i7);
                                int size2 = list3.size();
                                int[] iArr = new int[size2];
                                int i9 = 0;
                                while (i9 < size2) {
                                    iArr[i9] = list3.get(i9).getWidth() + (i9 < CollectionsKt.getLastIndex(list3) ? measureScope.mo571roundToPx0680j_4(f4) : 0);
                                    i9++;
                                }
                                Arrangement.Vertical bottom = Arrangement.INSTANCE.getBottom();
                                int[] iArr2 = new int[size2];
                                for (int i10 = 0; i10 < size2; i10++) {
                                    iArr2[i10] = 0;
                                }
                                bottom.arrange(measureScope, i6, iArr, iArr2);
                                int size3 = list3.size() - 1;
                                if (size3 >= 0) {
                                    int i11 = 0;
                                    while (true) {
                                        int i12 = size3;
                                        List<Placeable> list4 = list3;
                                        i5 = i8;
                                        int i13 = i7;
                                        Placeable.PlacementScope.place$default(layout, list3.get(i11), iArr2[i11], list2.get(i7).intValue(), 0.0f, 4, null);
                                        i11++;
                                        if (i11 > i12) {
                                            break;
                                        }
                                        size3 = i12;
                                        list3 = list4;
                                        i7 = i13;
                                        i8 = i5;
                                    }
                                    i4 = i5;
                                } else {
                                    i4 = i8;
                                }
                                if (i4 > size) {
                                    return;
                                } else {
                                    i7 = i4;
                                }
                            }
                        }
                    }, 4, null);
                }

                private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, Ref.IntRef intRef, MeasureScope measureScope, float f3, long j, Placeable placeable) {
                    return list.isEmpty() || (intRef.element + measureScope.mo571roundToPx0680j_4(f3)) + placeable.getWidth() <= Constraints.m4346getMaxWidthimpl(j);
                }

                private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, Ref.IntRef intRef, MeasureScope measureScope, float f3, List<Placeable> list2, List<Integer> list3, Ref.IntRef intRef2, List<Integer> list4, Ref.IntRef intRef3, Ref.IntRef intRef4) {
                    List<List<Placeable>> list5 = list;
                    if (!list5.isEmpty()) {
                        intRef.element += measureScope.mo571roundToPx0680j_4(f3);
                    }
                    list5.add(CollectionsKt.toList(list2));
                    list3.add(Integer.valueOf(intRef2.element));
                    list4.add(Integer.valueOf(intRef.element));
                    intRef.element += intRef2.element;
                    intRef3.element = Math.max(intRef3.element, intRef4.element);
                    list2.clear();
                    intRef4.element = 0;
                    intRef2.element = 0;
                }
            };
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
            int i3 = (((i2 >> 6) & 14) << 9) & 7168;
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, Integer.valueOf((i3 >> 3) & 112));
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            content.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.AlertDialogKt$AlertDialogFlowRow$2
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

            public final void invoke(Composer composer2, int i4) {
                AlertDialogKt.m1158AlertDialogFlowRowixp7dh8(f, f2, content, composer2, i | 1);
            }
        });
    }

    static {
        float f = 24;
        TitlePadding = PaddingKt.m694paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m4390constructorimpl(f), 0.0f, Dp.m4390constructorimpl(f), 0.0f, 10, null);
        TextPadding = PaddingKt.m694paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m4390constructorimpl(f), 0.0f, Dp.m4390constructorimpl(f), Dp.m4390constructorimpl(28), 2, null);
    }
}
