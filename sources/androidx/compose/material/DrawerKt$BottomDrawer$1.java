package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Drawer.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class DrawerKt$BottomDrawer$1 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
    final /* synthetic */ int $$dirty;
    final /* synthetic */ Function2<Composer, Integer, Unit> $content;
    final /* synthetic */ long $drawerBackgroundColor;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $drawerContent;
    final /* synthetic */ long $drawerContentColor;
    final /* synthetic */ float $drawerElevation;
    final /* synthetic */ Shape $drawerShape;
    final /* synthetic */ BottomDrawerState $drawerState;
    final /* synthetic */ boolean $gesturesEnabled;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ long $scrimColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DrawerKt$BottomDrawer$1(boolean z, BottomDrawerState bottomDrawerState, Function2<? super Composer, ? super Integer, Unit> function2, int i, long j, Shape shape, long j2, long j3, float f, CoroutineScope coroutineScope, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3) {
        super(3);
        this.$gesturesEnabled = z;
        this.$drawerState = bottomDrawerState;
        this.$content = function2;
        this.$$dirty = i;
        this.$scrimColor = j;
        this.$drawerShape = shape;
        this.$drawerBackgroundColor = j2;
        this.$drawerContentColor = j3;
        this.$drawerElevation = f;
        this.$scope = coroutineScope;
        this.$drawerContent = function3;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
        invoke(boxWithConstraintsScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
        int i2;
        Map mapMapOf;
        Modifier.Companion companionNestedScroll$default;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation(composer, "C510@18925L51,*529@19765L7,551@20452L1672:Drawer.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = i | (composer.changed(BoxWithConstraints) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (((i2 & 91) ^ 18) != 0 || !composer.getSkipping()) {
            float fM4345getMaxHeightimpl = Constraints.m4345getMaxHeightimpl(BoxWithConstraints.getConstraints());
            Object objValueOf = Float.valueOf(fM4345getMaxHeightimpl);
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = composer.changed(objValueOf);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(fM4345getMaxHeightimpl), null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue;
            boolean z = Constraints.m4346getMaxWidthimpl(BoxWithConstraints.getConstraints()) > Constraints.m4345getMaxHeightimpl(BoxWithConstraints.getConstraints());
            float f = 0.5f * fM4345getMaxHeightimpl;
            float fMax = Math.max(0.0f, fM4345getMaxHeightimpl - m1290invoke$lambda1(mutableState));
            if (m1290invoke$lambda1(mutableState) < f || z) {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(fM4345getMaxHeightimpl), BottomDrawerValue.Closed), TuplesKt.to(Float.valueOf(fMax), BottomDrawerValue.Expanded));
            } else {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(fM4345getMaxHeightimpl), BottomDrawerValue.Closed), TuplesKt.to(Float.valueOf(f), BottomDrawerValue.Open), TuplesKt.to(Float.valueOf(fMax), BottomDrawerValue.Expanded));
            }
            Map map = mapMapOf;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Density density = (Density) objConsume;
            Modifier modifierM737sizeInqDBjuR0$default = SizeKt.m737sizeInqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, density.mo574toDpu2uoSUM(Constraints.m4346getMaxWidthimpl(BoxWithConstraints.getConstraints())), density.mo574toDpu2uoSUM(Constraints.m4345getMaxHeightimpl(BoxWithConstraints.getConstraints())), 3, null);
            if (this.$gesturesEnabled) {
                companionNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, this.$drawerState.getNestedScrollConnection(), null, 2, null);
            } else {
                companionNestedScroll$default = Modifier.INSTANCE;
            }
            Modifier modifierM1423swipeablepPrIpRY = SwipeableKt.m1423swipeablepPrIpRY(Modifier.INSTANCE.then(companionNestedScroll$default), this.$drawerState, map, Orientation.Vertical, (32 & 8) != 0 ? true : this.$gesturesEnabled, (32 & 16) != 0 ? false : false, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? new Function2<T, T, FixedThreshold>() { // from class: androidx.compose.material.SwipeableKt$swipeable$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final FixedThreshold invoke(T t, T t2) {
                    return new FixedThreshold(Dp.m4390constructorimpl(56), null);
                }
            } : null, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$default(SwipeableDefaults.INSTANCE, map.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1422getVelocityThresholdD9Ej5fM() : 0.0f);
            Function2<Composer, Integer, Unit> function2 = this.$content;
            final int i3 = this.$$dirty;
            long j = this.$scrimColor;
            final BottomDrawerState bottomDrawerState = this.$drawerState;
            Shape shape = this.$drawerShape;
            long j2 = this.$drawerBackgroundColor;
            long j3 = this.$drawerContentColor;
            float f2 = this.$drawerElevation;
            final boolean z2 = this.$gesturesEnabled;
            final CoroutineScope coroutineScope = this.$scope;
            final Function3<ColumnScope, Composer, Integer, Unit> function3 = this.$drawerContent;
            composer.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composer, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer, 0);
            composer.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composer, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Density density2 = (Density) objConsume2;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composer);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierM1423swipeablepPrIpRY);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            composer.disableReusing();
            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composer);
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density2, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composer.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer)), composer, 0);
            composer.startReplaceableGroup(2058660585);
            composer.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composer, "C72@3384L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer.startReplaceableGroup(1720989473);
            ComposerKt.sourceInformation(composer, "C552@20481L9,553@20503L427,564@20964L33,567@21081L63,568@21187L105,565@21010L1104:Drawer.kt#jmzs0o");
            function2.invoke(composer, Integer.valueOf((i3 >> 27) & 14));
            DrawerKt.m1282BottomDrawerScrim3JVO9M(j, new Function0<Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    if (z2 && bottomDrawerState.getConfirmStateChange$material_release().invoke(BottomDrawerValue.Closed).booleanValue()) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(bottomDrawerState, null), 3, null);
                    }
                }

                /* compiled from: Drawer.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$1$1$1$1", f = "Drawer.kt", i = {}, l = {560}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material.DrawerKt$BottomDrawer$1$1$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ BottomDrawerState $drawerState;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(BottomDrawerState bottomDrawerState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$drawerState = bottomDrawerState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$drawerState, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$drawerState.close(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, bottomDrawerState.getTargetValue() != BottomDrawerValue.Closed, composer, (i3 >> 24) & 14);
            final String strM1416getString4foXLRw = Strings_androidKt.m1416getString4foXLRw(Strings.INSTANCE.m1415getNavigationMenuUdPEhr4(), composer, 0);
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged2 = composer.changed(bottomDrawerState);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<Density, IntOffset>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$2$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(Density density3) {
                        return IntOffset.m4499boximpl(m1292invokeBjo55l4Bjo55l4(density3));
                    }

                    /* renamed from: invoke-Bjo55l4-Bjo55l4, reason: not valid java name */
                    public final long m1292invokeBjo55l4Bjo55l4(Density offset) {
                        Intrinsics.checkNotNullParameter(offset, "$this$offset");
                        return IntOffsetKt.IntOffset(0, MathKt.roundToInt(bottomDrawerState.getOffset().getValue().floatValue()));
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            Modifier modifierOffset = OffsetKt.offset(modifierM737sizeInqDBjuR0$default, (Function1) objRememberedValue2);
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged3 = composer.changed(mutableState);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                        invoke2(layoutCoordinates);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LayoutCoordinates position) {
                        Intrinsics.checkNotNullParameter(position, "position");
                        DrawerKt$BottomDrawer$1.m1291invoke$lambda2(mutableState, IntSize.m4549getHeightimpl(position.mo3401getSizeYbymL2g()));
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            int i4 = i3 >> 12;
            SurfaceKt.m1419SurfaceFjzlyU(SemanticsModifierKt.semantics$default(OnGloballyPositionedModifierKt.onGloballyPositioned(modifierOffset, (Function1) objRememberedValue3), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$4
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
                    SemanticsPropertiesKt.setPaneTitle(semantics, strM1416getString4foXLRw);
                    if (bottomDrawerState.isOpen()) {
                        final BottomDrawerState bottomDrawerState2 = bottomDrawerState;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        SemanticsPropertiesKt.dismiss$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$4.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Boolean invoke() {
                                return Boolean.valueOf(invoke2());
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2() {
                                if (!bottomDrawerState2.getConfirmStateChange$material_release().invoke(BottomDrawerValue.Closed).booleanValue()) {
                                    return true;
                                }
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00611(bottomDrawerState2, null), 3, null);
                                return true;
                            }

                            /* compiled from: Drawer.kt */
                            @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material.DrawerKt$BottomDrawer$1$1$4$1$1", f = "Drawer.kt", i = {}, l = {578}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: androidx.compose.material.DrawerKt$BottomDrawer$1$1$4$1$1, reason: invalid class name and collision with other inner class name */
                            static final class C00611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ BottomDrawerState $drawerState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C00611(BottomDrawerState bottomDrawerState, Continuation<? super C00611> continuation) {
                                    super(2, continuation);
                                    this.$drawerState = bottomDrawerState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C00611(this.$drawerState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C00611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    int i = this.label;
                                    if (i == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        this.label = 1;
                                        if (this.$drawerState.close(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    } else {
                                        if (i != 1) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        ResultKt.throwOnFailure(obj);
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        }, 1, null);
                    }
                }
            }, 1, null), shape, j2, j3, (BorderStroke) null, f2, ComposableLambdaKt.composableLambda(composer, -819908435, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.DrawerKt$BottomDrawer$1$1$5
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

                public final void invoke(Composer composer2, int i5) {
                    ComposerKt.sourceInformation(composer2, "C587@22069L31:Drawer.kt#jmzs0o");
                    if (((i5 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    Function3<ColumnScope, Composer, Integer, Unit> function32 = function3;
                    int i6 = (i3 << 9) & 7168;
                    composer2.startReplaceableGroup(-1113031299);
                    ComposerKt.sourceInformation(composer2, "C(Column)P(2,3,1)71@3450L61,72@3516L133:Column.kt#2w3rfo");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    int i7 = i6 >> 3;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, (i7 & 112) | (i7 & 14));
                    composer2.startReplaceableGroup(1376089335);
                    ComposerKt.sourceInformation(composer2, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                    ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                    ComposerKt.sourceInformationMarkerStart(composer2, 103361330, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume4 = composer2.consume(localDensity3);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Density density3 = (Density) objConsume4;
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composer2, 103361330, "C:CompositionLocal.kt#9igjgp");
                    Object objConsume5 = composer2.consume(localLayoutDirection2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    LayoutDirection layoutDirection2 = (LayoutDirection) objConsume5;
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(companion);
                    int i8 = (((i6 << 3) & 112) << 9) & 7168;
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor2);
                    } else {
                        composer2.useNode();
                    }
                    composer2.disableReusing();
                    Composer composerM1518constructorimpl2 = Updater.m1518constructorimpl(composer2);
                    Updater.m1525setimpl(composerM1518constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m1525setimpl(composerM1518constructorimpl2, density3, ComposeUiNode.INSTANCE.getSetDensity());
                    Updater.m1525setimpl(composerM1518constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                    composer2.enableReusing();
                    function3MaterializerOf2.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer2)), composer2, Integer.valueOf((i8 >> 3) & 112));
                    composer2.startReplaceableGroup(2058660585);
                    composer2.startReplaceableGroup(276693241);
                    ComposerKt.sourceInformation(composer2, "C73@3564L9:Column.kt#2w3rfo");
                    if ((((i8 >> 9) & 10) ^ 2) == 0 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                    } else {
                        function32.invoke(ColumnScopeInstance.INSTANCE, composer2, Integer.valueOf(((i6 >> 6) & 112) | 6));
                    }
                    composer2.endReplaceableGroup();
                    composer2.endReplaceableGroup();
                    composer2.endNode();
                    composer2.endReplaceableGroup();
                    composer2.endReplaceableGroup();
                }
            }), composer, ((i3 >> 9) & 112) | 1572864 | (i4 & 896) | (i4 & 7168) | (458752 & i3), 16);
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            return;
        }
        composer.skipToGroupEnd();
    }

    /* renamed from: invoke$lambda-1, reason: not valid java name */
    private static final float m1290invoke$lambda1(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-2, reason: not valid java name */
    public static final void m1291invoke$lambda2(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }
}
