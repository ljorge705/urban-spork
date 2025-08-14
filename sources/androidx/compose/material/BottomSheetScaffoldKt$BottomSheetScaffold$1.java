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
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
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
import androidx.compose.runtime.internal.ComposableLambda;
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
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.common.C;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class BottomSheetScaffoldKt$BottomSheetScaffold$1 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
    final /* synthetic */ int $$dirty;
    final /* synthetic */ int $$dirty1;
    final /* synthetic */ int $$dirty2;
    final /* synthetic */ long $backgroundColor;
    final /* synthetic */ Function3<PaddingValues, Composer, Integer, Unit> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ long $drawerBackgroundColor;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $drawerContent;
    final /* synthetic */ long $drawerContentColor;
    final /* synthetic */ float $drawerElevation;
    final /* synthetic */ boolean $drawerGesturesEnabled;
    final /* synthetic */ long $drawerScrimColor;
    final /* synthetic */ Shape $drawerShape;
    final /* synthetic */ Function2<Composer, Integer, Unit> $floatingActionButton;
    final /* synthetic */ int $floatingActionButtonPosition;
    final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ long $sheetBackgroundColor;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $sheetContent;
    final /* synthetic */ long $sheetContentColor;
    final /* synthetic */ float $sheetElevation;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ float $sheetPeekHeight;
    final /* synthetic */ Shape $sheetShape;
    final /* synthetic */ Function3<SnackbarHostState, Composer, Integer, Unit> $snackbarHost;
    final /* synthetic */ Function2<Composer, Integer, Unit> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    BottomSheetScaffoldKt$BottomSheetScaffold$1(BottomSheetScaffoldState bottomSheetScaffoldState, boolean z, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, boolean z2, Shape shape, float f, long j, long j2, long j3, int i, float f2, CoroutineScope coroutineScope, int i2, int i3, long j4, long j5, int i4, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function32, Shape shape2, long j6, long j7, float f3, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function34) {
        super(3);
        this.$scaffoldState = bottomSheetScaffoldState;
        this.$sheetGesturesEnabled = z;
        this.$drawerContent = function3;
        this.$drawerGesturesEnabled = z2;
        this.$drawerShape = shape;
        this.$drawerElevation = f;
        this.$drawerBackgroundColor = j;
        this.$drawerContentColor = j2;
        this.$drawerScrimColor = j3;
        this.$$dirty1 = i;
        this.$sheetPeekHeight = f2;
        this.$scope = coroutineScope;
        this.$floatingActionButtonPosition = i2;
        this.$$dirty = i3;
        this.$backgroundColor = j4;
        this.$contentColor = j5;
        this.$$dirty2 = i4;
        this.$topBar = function2;
        this.$content = function32;
        this.$sheetShape = shape2;
        this.$sheetBackgroundColor = j6;
        this.$sheetContentColor = j7;
        this.$sheetElevation = f3;
        this.$sheetContent = function33;
        this.$floatingActionButton = function22;
        this.$snackbarHost = function34;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
        invoke(boxWithConstraintsScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation(composer, "C*286@12153L7,287@12222L39:BottomSheetScaffold.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = i | (composer.changed(BoxWithConstraints) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (((i2 & 91) ^ 18) != 0 || !composer.getSkipping()) {
            float fM4345getMaxHeightimpl = Constraints.m4345getMaxHeightimpl(BoxWithConstraints.getConstraints());
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer);
            final float fMo577toPx0680j_4 = ((Density) objConsume).mo577toPx0680j_4(this.$sheetPeekHeight);
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(fM4345getMaxHeightimpl), null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue;
            Modifier modifierNestedScroll$default = NestedScrollModifierKt.nestedScroll$default(Modifier.INSTANCE, this.$scaffoldState.getBottomSheetState().getNestedScrollConnection(), null, 2, null);
            BottomSheetState bottomSheetState = this.$scaffoldState.getBottomSheetState();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to(Float.valueOf(fM4345getMaxHeightimpl - fMo577toPx0680j_4), BottomSheetValue.Collapsed), TuplesKt.to(Float.valueOf(fM4345getMaxHeightimpl - m1203invoke$lambda2(mutableState)), BottomSheetValue.Expanded));
            Modifier modifierM1423swipeablepPrIpRY = SwipeableKt.m1423swipeablepPrIpRY(modifierNestedScroll$default, bottomSheetState, mapMapOf, Orientation.Vertical, (32 & 8) != 0 ? true : this.$sheetGesturesEnabled, (32 & 16) != 0 ? false : false, (32 & 32) != 0 ? null : null, (32 & 64) != 0 ? new Function2<T, T, FixedThreshold>() { // from class: androidx.compose.material.SwipeableKt$swipeable$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                public final FixedThreshold invoke(T t, T t2) {
                    return new FixedThreshold(Dp.m4390constructorimpl(56), null);
                }
            } : null, (32 & 128) != 0 ? SwipeableDefaults.resistanceConfig$default(SwipeableDefaults.INSTANCE, mapMapOf.keySet(), 0.0f, 0.0f, 6, null) : null, (32 & 256) != 0 ? SwipeableDefaults.INSTANCE.m1422getVelocityThresholdD9Ej5fM() : 0.0f);
            final BottomSheetScaffoldState bottomSheetScaffoldState = this.$scaffoldState;
            final CoroutineScope coroutineScope = this.$scope;
            final Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM1423swipeablepPrIpRY, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1
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
                    if (fMo577toPx0680j_4 == BottomSheetScaffoldKt$BottomSheetScaffold$1.m1203invoke$lambda2(mutableState)) {
                        return;
                    }
                    if (bottomSheetScaffoldState.getBottomSheetState().isCollapsed()) {
                        final BottomSheetScaffoldState bottomSheetScaffoldState2 = bottomSheetScaffoldState;
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        SemanticsPropertiesKt.expand$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1.1
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
                                if (!bottomSheetScaffoldState2.getBottomSheetState().getConfirmStateChange$material_release().invoke(BottomSheetValue.Expanded).booleanValue()) {
                                    return true;
                                }
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new C00591(bottomSheetScaffoldState2, null), 3, null);
                                return true;
                            }

                            /* compiled from: BottomSheetScaffold.kt */
                            @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1$1$1", f = "BottomSheetScaffold.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1$1$1, reason: invalid class name and collision with other inner class name */
                            static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C00591(BottomSheetScaffoldState bottomSheetScaffoldState, Continuation<? super C00591> continuation) {
                                    super(2, continuation);
                                    this.$scaffoldState = bottomSheetScaffoldState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C00591(this.$scaffoldState, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C00591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    int i = this.label;
                                    if (i == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        this.label = 1;
                                        if (this.$scaffoldState.getBottomSheetState().expand(this) == coroutine_suspended) {
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
                    } else {
                        final BottomSheetScaffoldState bottomSheetScaffoldState3 = bottomSheetScaffoldState;
                        final CoroutineScope coroutineScope3 = coroutineScope;
                        SemanticsPropertiesKt.collapse$default(semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1.2
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
                                if (!bottomSheetScaffoldState3.getBottomSheetState().getConfirmStateChange$material_release().invoke(BottomSheetValue.Collapsed).booleanValue()) {
                                    return true;
                                }
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(bottomSheetScaffoldState3, null), 3, null);
                                return true;
                            }

                            /* compiled from: BottomSheetScaffold.kt */
                            @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                            @DebugMetadata(c = "androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1$2$1", f = "BottomSheetScaffold.kt", i = {}, l = {314}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$swipeable$1$2$1, reason: invalid class name */
                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
                                int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                AnonymousClass1(BottomSheetScaffoldState bottomSheetScaffoldState, Continuation<? super AnonymousClass1> continuation) {
                                    super(2, continuation);
                                    this.$scaffoldState = bottomSheetScaffoldState;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new AnonymousClass1(this.$scaffoldState, continuation);
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
                                        if (this.$scaffoldState.getBottomSheetState().collapse(this) == coroutine_suspended) {
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
            }, 1, null);
            final BottomSheetScaffoldState bottomSheetScaffoldState2 = this.$scaffoldState;
            final int i3 = this.$floatingActionButtonPosition;
            final int i4 = this.$$dirty;
            final long j = this.$backgroundColor;
            final long j2 = this.$contentColor;
            final int i5 = this.$$dirty2;
            final Function2<Composer, Integer, Unit> function2 = this.$topBar;
            final Function3<PaddingValues, Composer, Integer, Unit> function3 = this.$content;
            final float f = this.$sheetPeekHeight;
            final Shape shape = this.$sheetShape;
            final long j3 = this.$sheetBackgroundColor;
            final long j4 = this.$sheetContentColor;
            final float f2 = this.$sheetElevation;
            final int i6 = this.$$dirty1;
            final Function3<ColumnScope, Composer, Integer, Unit> function32 = this.$sheetContent;
            final Function2<Composer, Integer, Unit> function22 = this.$floatingActionButton;
            final Function3<SnackbarHostState, Composer, Integer, Unit> function33 = this.$snackbarHost;
            ComposableLambda composableLambda = ComposableLambdaKt.composableLambda(composer, -819899396, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1
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

                public final void invoke(Composer composer2, int i7) {
                    ComposerKt.sourceInformation(composer2, "C322@13696L1634:BottomSheetScaffold.kt#jmzs0o");
                    if (((i7 & 11) ^ 2) != 0 || !composer2.getSkipping()) {
                        final long j5 = j;
                        final long j6 = j2;
                        final int i8 = i5;
                        final Function2<Composer, Integer, Unit> function23 = function2;
                        final int i9 = i4;
                        final Function3<PaddingValues, Composer, Integer, Unit> function34 = function3;
                        final float f3 = f;
                        ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda(composer2, -819899585, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1.1
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

                            public final void invoke(Composer composer3, int i10) {
                                ComposerKt.sourceInformation(composer3, "C324@13767L360:BottomSheetScaffold.kt#jmzs0o");
                                if (((i10 & 11) ^ 2) != 0 || !composer3.getSkipping()) {
                                    long j7 = j5;
                                    long j8 = j6;
                                    final Function2<Composer, Integer, Unit> function24 = function23;
                                    final int i11 = i9;
                                    final Function3<PaddingValues, Composer, Integer, Unit> function35 = function34;
                                    final float f4 = f3;
                                    final int i12 = i8;
                                    ComposableLambda composableLambda3 = ComposableLambdaKt.composableLambda(composer3, -819900219, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.BottomSheetScaffold.1.child.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                            invoke(composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer4, int i13) {
                                            ComposerKt.sourceInformation(composer4, "C328@13925L180:BottomSheetScaffold.kt#jmzs0o");
                                            if (((i13 & 11) ^ 2) != 0 || !composer4.getSkipping()) {
                                                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                Function2<Composer, Integer, Unit> function25 = function24;
                                                int i14 = i11;
                                                Function3<PaddingValues, Composer, Integer, Unit> function36 = function35;
                                                float f5 = f4;
                                                int i15 = i12;
                                                composer4.startReplaceableGroup(-1113031299);
                                                ComposerKt.sourceInformation(composer4, "C(Column)P(2,3,1)71@3450L61,72@3516L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                                composer4.startReplaceableGroup(1376089335);
                                                ComposerKt.sourceInformation(composer4, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                                                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                                ComposerKt.sourceInformationMarkerStart(composer4, 103361330, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume2 = composer4.consume(localDensity2);
                                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                                Density density = (Density) objConsume2;
                                                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                                ComposerKt.sourceInformationMarkerStart(composer4, 103361330, "C:CompositionLocal.kt#9igjgp");
                                                Object objConsume3 = composer4.consume(localLayoutDirection);
                                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                                LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
                                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(modifierFillMaxSize$default);
                                                if (!(composer4.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer4.startReusableNode();
                                                if (composer4.getInserting()) {
                                                    composer4.createNode(constructor);
                                                } else {
                                                    composer4.useNode();
                                                }
                                                composer4.disableReusing();
                                                Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composer4);
                                                Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                                                Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                                composer4.enableReusing();
                                                function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer4)), composer4, 0);
                                                composer4.startReplaceableGroup(2058660585);
                                                composer4.startReplaceableGroup(276693241);
                                                ComposerKt.sourceInformation(composer4, "C73@3564L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                                composer4.startReplaceableGroup(-1579943837);
                                                ComposerKt.sourceInformation(composer4, "C330@14031L48:BottomSheetScaffold.kt#jmzs0o");
                                                if (function25 == null) {
                                                    composer4.startReplaceableGroup(-1733618442);
                                                    composer4.endReplaceableGroup();
                                                } else {
                                                    composer4.startReplaceableGroup(-1579943829);
                                                    ComposerKt.sourceInformation(composer4, "329@13994L8");
                                                    function25.invoke(composer4, Integer.valueOf((i14 >> 9) & 14));
                                                    composer4.endReplaceableGroup();
                                                }
                                                function36.invoke(PaddingKt.m687PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, f5, 7, null), composer4, Integer.valueOf((i15 >> 3) & 112));
                                                composer4.endReplaceableGroup();
                                                composer4.endReplaceableGroup();
                                                composer4.endReplaceableGroup();
                                                composer4.endNode();
                                                composer4.endReplaceableGroup();
                                                composer4.endReplaceableGroup();
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    });
                                    int i13 = i8;
                                    SurfaceKt.m1419SurfaceFjzlyU((Modifier) null, (Shape) null, j7, j8, (BorderStroke) null, 0.0f, composableLambda3, composer3, ((i13 << 6) & 7168) | ((i13 << 6) & 896) | 1572864, 51);
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        });
                        final Modifier modifier = modifierSemantics$default;
                        final float f4 = f;
                        final MutableState<Float> mutableState2 = mutableState;
                        final Shape shape2 = shape;
                        final long j7 = j3;
                        final long j8 = j4;
                        final float f5 = f2;
                        final int i10 = i4;
                        final int i11 = i6;
                        final Function3<ColumnScope, Composer, Integer, Unit> function35 = function32;
                        ComposableLambda composableLambda3 = ComposableLambdaKt.composableLambda(composer2, -819899921, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1.2
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

                            public final void invoke(Composer composer3, int i12) {
                                ComposerKt.sourceInformation(composer3, "C339@14405L108,335@14199L614:BottomSheetScaffold.kt#jmzs0o");
                                if (((i12 & 11) ^ 2) != 0 || !composer3.getSkipping()) {
                                    Modifier modifierM724requiredHeightInVpY3zN4$default = SizeKt.m724requiredHeightInVpY3zN4$default(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), f4, 0.0f, 2, null);
                                    final MutableState<Float> mutableState3 = mutableState2;
                                    composer3.startReplaceableGroup(-3686930);
                                    ComposerKt.sourceInformation(composer3, "C(remember)P(1):Composables.kt#9igjgp");
                                    boolean zChanged = composer3.changed(mutableState3);
                                    Object objRememberedValue2 = composer3.rememberedValue();
                                    if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue2 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1$2$1$1
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
                                            public final void invoke2(LayoutCoordinates it) {
                                                Intrinsics.checkNotNullParameter(it, "it");
                                                BottomSheetScaffoldKt$BottomSheetScaffold$1.m1204invoke$lambda3(mutableState3, IntSize.m4549getHeightimpl(it.mo3401getSizeYbymL2g()));
                                            }
                                        };
                                        composer3.updateRememberedValue(objRememberedValue2);
                                    }
                                    composer3.endReplaceableGroup();
                                    Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifierM724requiredHeightInVpY3zN4$default, (Function1) objRememberedValue2);
                                    Shape shape3 = shape2;
                                    long j9 = j7;
                                    long j10 = j8;
                                    float f6 = f5;
                                    final Function3<ColumnScope, Composer, Integer, Unit> function36 = function35;
                                    final int i13 = i10;
                                    ComposableLambda composableLambda4 = ComposableLambdaKt.composableLambda(composer3, -819896533, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt.BottomSheetScaffold.1.child.1.2.2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                            invoke(composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer4, int i14) {
                                            ComposerKt.sourceInformation(composer4, "C346@14759L30:BottomSheetScaffold.kt#jmzs0o");
                                            if (((i14 & 11) ^ 2) == 0 && composer4.getSkipping()) {
                                                composer4.skipToGroupEnd();
                                                return;
                                            }
                                            Function3<ColumnScope, Composer, Integer, Unit> function37 = function36;
                                            int i15 = (i13 << 9) & 7168;
                                            composer4.startReplaceableGroup(-1113031299);
                                            ComposerKt.sourceInformation(composer4, "C(Column)P(2,3,1)71@3450L61,72@3516L133:Column.kt#2w3rfo");
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            int i16 = i15 >> 3;
                                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, (i16 & 112) | (i16 & 14));
                                            composer4.startReplaceableGroup(1376089335);
                                            ComposerKt.sourceInformation(composer4, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                                            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                            ComposerKt.sourceInformationMarkerStart(composer4, 103361330, "C:CompositionLocal.kt#9igjgp");
                                            Object objConsume2 = composer4.consume(localDensity2);
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            Density density = (Density) objConsume2;
                                            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                            ComposerKt.sourceInformationMarkerStart(composer4, 103361330, "C:CompositionLocal.kt#9igjgp");
                                            Object objConsume3 = composer4.consume(localLayoutDirection);
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
                                            int i17 = (((i15 << 3) & 112) << 9) & 7168;
                                            if (!(composer4.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                composer4.createNode(constructor);
                                            } else {
                                                composer4.useNode();
                                            }
                                            composer4.disableReusing();
                                            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composer4);
                                            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
                                            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                                            composer4.enableReusing();
                                            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer4)), composer4, Integer.valueOf((i17 >> 3) & 112));
                                            composer4.startReplaceableGroup(2058660585);
                                            composer4.startReplaceableGroup(276693241);
                                            ComposerKt.sourceInformation(composer4, "C73@3564L9:Column.kt#2w3rfo");
                                            if ((((i17 >> 9) & 10) ^ 2) == 0 && composer4.getSkipping()) {
                                                composer4.skipToGroupEnd();
                                            } else {
                                                function37.invoke(ColumnScopeInstance.INSTANCE, composer4, Integer.valueOf(((i15 >> 6) & 112) | 6));
                                            }
                                            composer4.endReplaceableGroup();
                                            composer4.endReplaceableGroup();
                                            composer4.endNode();
                                            composer4.endReplaceableGroup();
                                            composer4.endReplaceableGroup();
                                        }
                                    });
                                    int i14 = i10;
                                    int i15 = i11;
                                    SurfaceKt.m1419SurfaceFjzlyU(modifierOnGloballyPositioned, shape3, j9, j10, (BorderStroke) null, f6, composableLambda4, composer3, ((i14 >> 21) & 112) | 1572864 | ((i15 << 6) & 896) | ((i15 << 6) & 7168) | ((i14 >> 12) & 458752), 16);
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        });
                        final Function2<Composer, Integer, Unit> function24 = function22;
                        final int i12 = i4;
                        ComposableLambda composableLambda4 = ComposableLambdaKt.composableLambda(composer2, -819897194, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1.3
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

                            public final void invoke(Composer composer3, int i13) {
                                ComposerKt.sourceInformation(composer3, "C350@14894L82:BottomSheetScaffold.kt#jmzs0o");
                                if (((i13 & 11) ^ 2) != 0 || !composer3.getSkipping()) {
                                    Function2<Composer, Integer, Unit> function25 = function24;
                                    int i14 = i12;
                                    composer3.startReplaceableGroup(-1990474327);
                                    ComposerKt.sourceInformation(composer3, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                    composer3.startReplaceableGroup(1376089335);
                                    ComposerKt.sourceInformation(composer3, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume2 = composer3.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    Density density = (Density) objConsume2;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = composer3.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
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
                                    composer3.startReplaceableGroup(2068277740);
                                    ComposerKt.sourceInformation(composer3, "C:BottomSheetScaffold.kt#jmzs0o");
                                    if (function25 == null) {
                                        composer3.startReplaceableGroup(-307898817);
                                        composer3.endReplaceableGroup();
                                    } else {
                                        composer3.startReplaceableGroup(2068277762);
                                        ComposerKt.sourceInformation(composer3, "351@14946L8");
                                        function25.invoke(composer3, Integer.valueOf((i14 >> 15) & 14));
                                        composer3.endReplaceableGroup();
                                    }
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
                        });
                        final Function3<SnackbarHostState, Composer, Integer, Unit> function36 = function33;
                        final BottomSheetScaffoldState bottomSheetScaffoldState3 = bottomSheetScaffoldState2;
                        final int i13 = i4;
                        BottomSheetScaffoldKt.m1199BottomSheetScaffoldStackSlNgfk0(composableLambda2, composableLambda3, composableLambda4, ComposableLambdaKt.composableLambda(composer2, -819897283, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.BottomSheetScaffoldKt$BottomSheetScaffold$1$child$1.4
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

                            public final void invoke(Composer composer3, int i14) {
                                ComposerKt.sourceInformation(composer3, "C355@15049L97:BottomSheetScaffold.kt#jmzs0o");
                                if (((i14 & 11) ^ 2) != 0 || !composer3.getSkipping()) {
                                    Function3<SnackbarHostState, Composer, Integer, Unit> function37 = function36;
                                    BottomSheetScaffoldState bottomSheetScaffoldState4 = bottomSheetScaffoldState3;
                                    int i15 = i13;
                                    composer3.startReplaceableGroup(-1990474327);
                                    ComposerKt.sourceInformation(composer3, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                                    Modifier.Companion companion = Modifier.INSTANCE;
                                    MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                    composer3.startReplaceableGroup(1376089335);
                                    ComposerKt.sourceInformation(composer3, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                                    ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume2 = composer3.consume(localDensity2);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    Density density = (Density) objConsume2;
                                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 103361330, "C:CompositionLocal.kt#9igjgp");
                                    Object objConsume3 = composer3.consume(localLayoutDirection);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    LayoutDirection layoutDirection = (LayoutDirection) objConsume3;
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
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
                                    composer3.startReplaceableGroup(2068277895);
                                    ComposerKt.sourceInformation(composer3, "C356@15079L45:BottomSheetScaffold.kt#jmzs0o");
                                    function37.invoke(bottomSheetScaffoldState4.getSnackbarHostState(), composer3, Integer.valueOf((i15 >> 9) & 112));
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
                        }), bottomSheetScaffoldState2.getBottomSheetState().getOffset(), i3, composer2, ((i4 >> 3) & 458752) | 3510);
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            });
            if (this.$drawerContent == null) {
                composer.startReplaceableGroup(-249545651);
                ComposerKt.sourceInformation(composer, "364@15390L7");
                composableLambda.invoke(composer, 6);
                composer.endReplaceableGroup();
                return;
            }
            composer.startReplaceableGroup(-249545614);
            ComposerKt.sourceInformation(composer, "366@15427L480");
            Function3<ColumnScope, Composer, Integer, Unit> function34 = this.$drawerContent;
            DrawerState drawerState = this.$scaffoldState.getDrawerState();
            boolean z = this.$drawerGesturesEnabled;
            Shape shape2 = this.$drawerShape;
            float f3 = this.$drawerElevation;
            long j5 = this.$drawerBackgroundColor;
            long j6 = this.$drawerContentColor;
            long j7 = this.$drawerScrimColor;
            int i7 = this.$$dirty1;
            DrawerKt.m1284ModalDrawerGs3lGvM(function34, null, drawerState, z, shape2, f3, j5, j6, j7, composableLambda, composer, ((i7 >> 9) & 14) | C.ENCODING_PCM_32BIT | ((i7 >> 3) & 7168) | ((i7 >> 3) & 57344) | ((i7 >> 3) & 458752) | ((i7 >> 3) & 3670016) | ((i7 >> 3) & 29360128) | ((i7 >> 3) & 234881024), 2);
            composer.endReplaceableGroup();
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-2, reason: not valid java name */
    public static final float m1203invoke$lambda2(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-3, reason: not valid java name */
    public static final void m1204invoke$lambda3(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }
}
