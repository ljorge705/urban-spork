package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Surface.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a¬\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001af\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001ab\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00052\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010 \u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Surface", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "color", "Landroidx/compose/ui/graphics/Color;", "contentColor", Constants.KEY_BORDER, "Landroidx/compose/foundation/BorderStroke;", ViewProps.ELEVATION, "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indication", "Landroidx/compose/foundation/Indication;", ViewProps.ENABLED, "", "onClickLabel", "", ViewProps.ROLE, "Landroidx/compose/ui/semantics/Role;", "content", "Landroidx/compose/runtime/Composable;", "Surface-9VG74zQ", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Surface-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "clickAndSemanticsModifier", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SurfaceKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0130  */
    /* renamed from: Surface-F-jzlyU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1419SurfaceFjzlyU(androidx.compose.ui.Modifier r25, androidx.compose.ui.graphics.Shape r26, long r27, long r29, androidx.compose.foundation.BorderStroke r31, float r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt.m1419SurfaceFjzlyU(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.foundation.BorderStroke, float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:206:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0132  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: Surface-9VG74zQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1417Surface9VG74zQ(final kotlin.jvm.functions.Function0<kotlin.Unit> r29, androidx.compose.ui.Modifier r30, androidx.compose.ui.graphics.Shape r31, long r32, long r34, androidx.compose.foundation.BorderStroke r36, float r37, androidx.compose.foundation.interaction.MutableInteractionSource r38, androidx.compose.foundation.Indication r39, boolean r40, java.lang.String r41, androidx.compose.ui.semantics.Role r42, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, androidx.compose.runtime.Composer r44, final int r45, final int r46, final int r47) {
        /*
            Method dump skipped, instructions count: 863
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SurfaceKt.m1417Surface9VG74zQ(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.foundation.BorderStroke, float, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.Indication, boolean, java.lang.String, androidx.compose.ui.semantics.Role, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Surface-F-jzlyU, reason: not valid java name */
    public static final void m1418SurfaceFjzlyU(final Modifier modifier, final Shape shape, final long j, final long j2, final BorderStroke borderStroke, final float f, final Modifier modifier2, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        char c;
        long jMo1276apply7g2Lkgo;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-750961937);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Surface)P(6,7,2:c#ui.graphics.Color,4:c#ui.graphics.Color!1,5:c#ui.unit.Dp)243@11560L7,*244@11619L7,245@11692L6,250@11832L616:Surface.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(borderStroke) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 131072 : 65536;
        }
        if ((3670016 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier2) ? 1048576 : 524288;
        }
        if ((29360128 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(function2) ? 8388608 : 4194304;
        }
        final int i3 = i2;
        if (((i3 & 23967451) ^ 4793490) != 0 || !composerStartRestartGroup.getSkipping()) {
            ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localElevationOverlay);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ElevationOverlay elevationOverlay = (ElevationOverlay) objConsume;
            ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localAbsoluteElevation);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            float fM4390constructorimpl = Dp.m4390constructorimpl(((Dp) objConsume2).m4404unboximpl() + f);
            if (Color.m1878equalsimpl0(j, MaterialTheme.INSTANCE.getColors(composerStartRestartGroup, 0).m1246getSurface0d7_KjU()) && elevationOverlay != null) {
                composerStartRestartGroup.startReplaceableGroup(-750961487);
                ComposerKt.sourceInformation(composerStartRestartGroup, "246@11763L31");
                c = 0;
                jMo1276apply7g2Lkgo = elevationOverlay.mo1276apply7g2Lkgo(j, fM4390constructorimpl, composerStartRestartGroup, (i3 >> 6) & 14);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                c = 0;
                composerStartRestartGroup.startReplaceableGroup(-750961417);
                composerStartRestartGroup.endReplaceableGroup();
                jMo1276apply7g2Lkgo = j;
            }
            ProvidedValue[] providedValueArr = new ProvidedValue[2];
            providedValueArr[c] = ContentColorKt.getLocalContentColor().provides(Color.m1867boximpl(j2));
            providedValueArr[1] = ElevationOverlayKt.getLocalAbsoluteElevation().provides(Dp.m4388boximpl(fM4390constructorimpl));
            composer2 = composerStartRestartGroup;
            final long j3 = jMo1276apply7g2Lkgo;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda(composer2, -819902387, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    ComposerKt.sourceInformation(composer3, "C254@11981L461:Surface.kt#jmzs0o");
                    if (((i4 & 11) ^ 2) != 0 || !composer3.getSkipping()) {
                        Modifier modifierThen = ClipKt.clip(BackgroundKt.m426backgroundbw27NRU(ShadowKt.m1559shadowziNgDLE(modifier, f, shape, false).then(borderStroke != null ? BorderKt.border(Modifier.INSTANCE, borderStroke, shape) : Modifier.INSTANCE), j3, shape), shape).then(modifier2);
                        Function2<Composer, Integer, Unit> function22 = function2;
                        int i5 = i3;
                        composer3.startReplaceableGroup(-1990474327);
                        ComposerKt.sourceInformation(composer3, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true, composer3, 48);
                        composer3.startReplaceableGroup(1376089335);
                        ComposerKt.sourceInformation(composer3, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                        ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume3 = composer3.consume(localDensity);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Density density = (Density) objConsume3;
                        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                        ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                        Object objConsume4 = composer3.consume(localLayoutDirection);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        LayoutDirection layoutDirection = (LayoutDirection) objConsume4;
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierThen);
                        if (!(composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        composer3.disableReusing();
                        Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composer3);
                        Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                        Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                        composer3.enableReusing();
                        function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer3)), composer3, 0);
                        composer3.startReplaceableGroup(2058660585);
                        composer3.startReplaceableGroup(-1253629305);
                        ComposerKt.sourceInformation(composer3, "C72@3384L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        composer3.startReplaceableGroup(1505976098);
                        ComposerKt.sourceInformation(composer3, "C266@12423L9:Surface.kt#jmzs0o");
                        function22.invoke(composer3, Integer.valueOf((i5 >> 21) & 14));
                        composer3.endReplaceableGroup();
                        composer3.endReplaceableGroup();
                        composer3.endReplaceableGroup();
                        composer3.endNode();
                        composer3.endReplaceableGroup();
                        composer3.endReplaceableGroup();
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }), composer2, 56);
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SurfaceKt$Surface$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i4) {
                SurfaceKt.m1418SurfaceFjzlyU(modifier, shape, j, j2, borderStroke, f, modifier2, (Function2<? super Composer, ? super Integer, Unit>) function2, composer3, i | 1);
            }
        });
    }
}
