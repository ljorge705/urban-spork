package androidx.compose.foundation;

import android.os.Build;
import android.view.View;
import androidx.compose.foundation.MagnifierKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: Magnifier.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0001\u001ao\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\rH\u0007ø\u0001\u0000\u001ao\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000\"#\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"MagnifierPositionInRoot", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Offset;", "getMagnifierPositionInRoot", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "isPlatformMagnifierSupported", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "", "magnifier", "Landroidx/compose/ui/Modifier;", "sourceCenter", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/ExtensionFunctionType;", "magnifierCenter", "zoom", "", "style", "Landroidx/compose/foundation/MagnifierStyle;", "onSizeChanged", "Landroidx/compose/ui/unit/DpSize;", "", "platformMagnifierFactory", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MagnifierKt {
    private static final SemanticsPropertyKey<Function0<Offset>> MagnifierPositionInRoot = new SemanticsPropertyKey<>("MagnifierPositionInRoot", null, 2, null);

    public static final SemanticsPropertyKey<Function0<Offset>> getMagnifierPositionInRoot() {
        return MagnifierPositionInRoot;
    }

    public static final boolean isPlatformMagnifierSupported(int i) {
        return i >= 28;
    }

    public static /* synthetic */ Modifier magnifier$default(Modifier modifier, Function1 function1, Function1 function12, float f, MagnifierStyle magnifierStyle, Function1 function13, int i, Object obj) {
        if ((i & 2) != 0) {
            function12 = new Function1<Density, Offset>() { // from class: androidx.compose.foundation.MagnifierKt.magnifier.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Offset invoke(Density density) {
                    return Offset.m1628boximpl(m471invoketuRUvjQ(density));
                }

                /* renamed from: invoke-tuRUvjQ, reason: not valid java name */
                public final long m471invoketuRUvjQ(Density density) {
                    Intrinsics.checkNotNullParameter(density, "$this$null");
                    return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
                }
            };
        }
        Function1 function14 = function12;
        if ((i & 4) != 0) {
            f = Float.NaN;
        }
        float f2 = f;
        if ((i & 8) != 0) {
            magnifierStyle = MagnifierStyle.INSTANCE.getDefault();
        }
        MagnifierStyle magnifierStyle2 = magnifierStyle;
        if ((i & 16) != 0) {
            function13 = null;
        }
        return magnifier(modifier, function1, function14, f2, magnifierStyle2, function13);
    }

    /* compiled from: Magnifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.foundation.MagnifierKt$magnifier$4, reason: invalid class name */
    static final class AnonymousClass4 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Function1<Density, Offset> $magnifierCenter;
        final /* synthetic */ Function1<DpSize, Unit> $onSizeChanged;
        final /* synthetic */ PlatformMagnifierFactory $platformMagnifierFactory;
        final /* synthetic */ Function1<Density, Offset> $sourceCenter;
        final /* synthetic */ MagnifierStyle $style;
        final /* synthetic */ float $zoom;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(Function1<? super Density, Offset> function1, Function1<? super Density, Offset> function12, float f, Function1<? super DpSize, Unit> function13, PlatformMagnifierFactory platformMagnifierFactory, MagnifierStyle magnifierStyle) {
            super(3);
            this.$sourceCenter = function1;
            this.$magnifierCenter = function12;
            this.$zoom = f;
            this.$onSizeChanged = function13;
            this.$platformMagnifierFactory = platformMagnifierFactory;
            this.$style = magnifierStyle;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier composed, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(composed, "$this$composed");
            composer.startReplaceableGroup(-454877003);
            ComposerKt.sourceInformation(composer, "C273@11641L7,274@11680L7,275@11728L47,276@11807L34,277@11876L37,278@11937L26,279@11996L35,280@12062L331,290@12422L62,295@12614L107,307@13298L2602,373@15957L344,385@16670L78:Magnifier.kt#71ulvw");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-454877003, i, -1, "androidx.compose.foundation.magnifier.<anonymous> (Magnifier.kt:272)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localView);
            ComposerKt.sourceInformationMarkerEnd(composer);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer);
            final Density density = (Density) objConsume2;
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m1628boximpl(Offset.INSTANCE.m1654getUnspecifiedF1C5BW0()), null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue;
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(this.$sourceCenter, composer, 0);
            State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(this.$magnifierCenter, composer, 0);
            State stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(Float.valueOf(this.$zoom), composer, 0);
            State stateRememberUpdatedState4 = SnapshotStateKt.rememberUpdatedState(this.$onSizeChanged, composer, 0);
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt.derivedStateOf(new Function0<Offset>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$4$sourceCenterInRoot$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Offset invoke() {
                        return Offset.m1628boximpl(m473invokeF1C5BW0());
                    }

                    /* renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m473invokeF1C5BW0() {
                        long packedValue = ((Offset) MagnifierKt.AnonymousClass4.invoke$lambda$3(stateRememberUpdatedState).invoke(density)).getPackedValue();
                        if (OffsetKt.m1658isSpecifiedk4lQ0M(MagnifierKt.AnonymousClass4.invoke$lambda$1(mutableState)) && OffsetKt.m1658isSpecifiedk4lQ0M(packedValue)) {
                            return Offset.m1644plusMKHz9U(MagnifierKt.AnonymousClass4.invoke$lambda$1(mutableState), packedValue);
                        }
                        return Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
                    }
                });
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            final State state = (State) objRememberedValue2;
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt.derivedStateOf(new Function0<Boolean>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$4$isMagnifierShown$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(OffsetKt.m1658isSpecifiedk4lQ0M(MagnifierKt.AnonymousClass4.invoke$lambda$8(state)));
                    }
                });
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            State state2 = (State) objRememberedValue3;
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceableGroup();
            final MutableSharedFlow mutableSharedFlow = (MutableSharedFlow) objRememberedValue4;
            Float fValueOf = Float.valueOf(this.$platformMagnifierFactory.getCanUpdateZoom() ? 0.0f : this.$zoom);
            MagnifierStyle magnifierStyle = this.$style;
            EffectsKt.LaunchedEffect(new Object[]{view, density, fValueOf, magnifierStyle, Boolean.valueOf(Intrinsics.areEqual(magnifierStyle, MagnifierStyle.INSTANCE.getTextDefault()))}, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1(this.$platformMagnifierFactory, this.$style, view, density, this.$zoom, mutableSharedFlow, stateRememberUpdatedState4, state2, state, stateRememberUpdatedState2, mutableState, stateRememberUpdatedState3, null), composer, 72);
            composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = (Function1) new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$4$2$1
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
                        MagnifierKt.AnonymousClass4.invoke$lambda$2(mutableState, LayoutCoordinatesKt.positionInRoot(it));
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            composer.endReplaceableGroup();
            Modifier modifierDrawBehind = DrawModifierKt.drawBehind(OnGloballyPositionedModifierKt.onGloballyPositioned(composed, (Function1) objRememberedValue5), new Function1<DrawScope, Unit>() { // from class: androidx.compose.foundation.MagnifierKt.magnifier.4.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope drawBehind) {
                    Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
                    mutableSharedFlow.tryEmit(Unit.INSTANCE);
                }
            });
            composer.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged2 = composer.changed(state);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$4$4$1
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
                        SemanticsPropertyKey<Function0<Offset>> magnifierPositionInRoot = MagnifierKt.getMagnifierPositionInRoot();
                        final State<Offset> state3 = state;
                        semantics.set(magnifierPositionInRoot, new Function0<Offset>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$4$4$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Offset invoke() {
                                return Offset.m1628boximpl(m472invokeF1C5BW0());
                            }

                            /* renamed from: invoke-F1C5BW0, reason: not valid java name */
                            public final long m472invokeF1C5BW0() {
                                return MagnifierKt.AnonymousClass4.invoke$lambda$8(state3);
                            }
                        });
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            composer.endReplaceableGroup();
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierDrawBehind, false, (Function1) objRememberedValue6, 1, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierSemantics$default;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$1(MutableState<Offset> mutableState) {
            return mutableState.getValue().getPackedValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(MutableState<Offset> mutableState, long j) {
            mutableState.setValue(Offset.m1628boximpl(j));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function1<Density, Offset> invoke$lambda$3(State<? extends Function1<? super Density, Offset>> state) {
            return (Function1) state.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function1<Density, Offset> invoke$lambda$4(State<? extends Function1<? super Density, Offset>> state) {
            return (Function1) state.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$lambda$5(State<Float> state) {
            return state.getValue().floatValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function1<DpSize, Unit> invoke$lambda$6(State<? extends Function1<? super DpSize, Unit>> state) {
            return (Function1) state.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$8(State<Offset> state) {
            return state.getValue().getPackedValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invoke$lambda$10(State<Boolean> state) {
            return state.getValue().booleanValue();
        }

        /* compiled from: Magnifier.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.MagnifierKt$magnifier$4$1", f = "Magnifier.kt", i = {0}, l = {365}, m = "invokeSuspend", n = {"magnifier"}, s = {"L$0"})
        /* renamed from: androidx.compose.foundation.MagnifierKt$magnifier$4$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ MutableState<Offset> $anchorPositionInRoot$delegate;
            final /* synthetic */ Density $density;
            final /* synthetic */ State<Boolean> $isMagnifierShown$delegate;
            final /* synthetic */ MutableSharedFlow<Unit> $onNeedsUpdate;
            final /* synthetic */ PlatformMagnifierFactory $platformMagnifierFactory;
            final /* synthetic */ State<Offset> $sourceCenterInRoot$delegate;
            final /* synthetic */ MagnifierStyle $style;
            final /* synthetic */ State<Function1<Density, Offset>> $updatedMagnifierCenter$delegate;
            final /* synthetic */ State<Function1<DpSize, Unit>> $updatedOnSizeChanged$delegate;
            final /* synthetic */ State<Float> $updatedZoom$delegate;
            final /* synthetic */ View $view;
            final /* synthetic */ float $zoom;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(PlatformMagnifierFactory platformMagnifierFactory, MagnifierStyle magnifierStyle, View view, Density density, float f, MutableSharedFlow<Unit> mutableSharedFlow, State<? extends Function1<? super DpSize, Unit>> state, State<Boolean> state2, State<Offset> state3, State<? extends Function1<? super Density, Offset>> state4, MutableState<Offset> mutableState, State<Float> state5, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$platformMagnifierFactory = platformMagnifierFactory;
                this.$style = magnifierStyle;
                this.$view = view;
                this.$density = density;
                this.$zoom = f;
                this.$onNeedsUpdate = mutableSharedFlow;
                this.$updatedOnSizeChanged$delegate = state;
                this.$isMagnifierShown$delegate = state2;
                this.$sourceCenterInRoot$delegate = state3;
                this.$updatedMagnifierCenter$delegate = state4;
                this.$anchorPositionInRoot$delegate = mutableState;
                this.$updatedZoom$delegate = state5;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$platformMagnifierFactory, this.$style, this.$view, this.$density, this.$zoom, this.$onNeedsUpdate, this.$updatedOnSizeChanged$delegate, this.$isMagnifierShown$delegate, this.$sourceCenterInRoot$delegate, this.$updatedMagnifierCenter$delegate, this.$anchorPositionInRoot$delegate, this.$updatedZoom$delegate, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: Magnifier.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.MagnifierKt$magnifier$4$1$1", f = "Magnifier.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: androidx.compose.foundation.MagnifierKt$magnifier$4$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00121 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                final /* synthetic */ PlatformMagnifier $magnifier;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00121(PlatformMagnifier platformMagnifier, Continuation<? super C00121> continuation) {
                    super(2, continuation);
                    this.$magnifier = platformMagnifier;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00121(this.$magnifier, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
                    return ((C00121) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.$magnifier.updateContent();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                PlatformMagnifier platformMagnifier;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    platformMagnifier = (PlatformMagnifier) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        platformMagnifier.dismiss();
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        platformMagnifier.dismiss();
                        throw th;
                    }
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final PlatformMagnifier platformMagnifierCreate = this.$platformMagnifierFactory.create(this.$style, this.$view, this.$density, this.$zoom);
                final Ref.LongRef longRef = new Ref.LongRef();
                long jMo491getSizeYbymL2g = platformMagnifierCreate.mo491getSizeYbymL2g();
                Density density = this.$density;
                Function1 function1Invoke$lambda$6 = AnonymousClass4.invoke$lambda$6(this.$updatedOnSizeChanged$delegate);
                if (function1Invoke$lambda$6 != null) {
                    function1Invoke$lambda$6.invoke(DpSize.m4476boximpl(density.mo575toDpSizekrfVVM(IntSizeKt.m4560toSizeozmzZPI(jMo491getSizeYbymL2g))));
                }
                longRef.element = jMo491getSizeYbymL2g;
                FlowKt.launchIn(FlowKt.onEach(this.$onNeedsUpdate, new C00121(platformMagnifierCreate, null)), coroutineScope);
                try {
                    final Density density2 = this.$density;
                    final State<Boolean> state = this.$isMagnifierShown$delegate;
                    final State<Offset> state2 = this.$sourceCenterInRoot$delegate;
                    final State<Function1<Density, Offset>> state3 = this.$updatedMagnifierCenter$delegate;
                    final MutableState<Offset> mutableState = this.$anchorPositionInRoot$delegate;
                    final State<Float> state4 = this.$updatedZoom$delegate;
                    final State<Function1<DpSize, Unit>> state5 = this.$updatedOnSizeChanged$delegate;
                    this.L$0 = platformMagnifierCreate;
                    this.label = 1;
                    if (FlowKt.collect(SnapshotStateKt.snapshotFlow(new Function0<Unit>() { // from class: androidx.compose.foundation.MagnifierKt.magnifier.4.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
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
                            long jM1654getUnspecifiedF1C5BW0;
                            if (AnonymousClass4.invoke$lambda$10(state)) {
                                PlatformMagnifier platformMagnifier2 = platformMagnifierCreate;
                                long jInvoke$lambda$8 = AnonymousClass4.invoke$lambda$8(state2);
                                Object objInvoke = AnonymousClass4.invoke$lambda$4(state3).invoke(density2);
                                MutableState<Offset> mutableState2 = mutableState;
                                long packedValue = ((Offset) objInvoke).getPackedValue();
                                if (OffsetKt.m1658isSpecifiedk4lQ0M(packedValue)) {
                                    jM1654getUnspecifiedF1C5BW0 = Offset.m1644plusMKHz9U(AnonymousClass4.invoke$lambda$1(mutableState2), packedValue);
                                } else {
                                    jM1654getUnspecifiedF1C5BW0 = Offset.INSTANCE.m1654getUnspecifiedF1C5BW0();
                                }
                                platformMagnifier2.mo492updateWko1d7g(jInvoke$lambda$8, jM1654getUnspecifiedF1C5BW0, AnonymousClass4.invoke$lambda$5(state4));
                                long jMo491getSizeYbymL2g2 = platformMagnifierCreate.mo491getSizeYbymL2g();
                                Ref.LongRef longRef2 = longRef;
                                Density density3 = density2;
                                State<Function1<DpSize, Unit>> state6 = state5;
                                if (IntSize.m4548equalsimpl0(jMo491getSizeYbymL2g2, longRef2.element)) {
                                    return;
                                }
                                longRef2.element = jMo491getSizeYbymL2g2;
                                Function1 function1Invoke$lambda$62 = AnonymousClass4.invoke$lambda$6(state6);
                                if (function1Invoke$lambda$62 != null) {
                                    function1Invoke$lambda$62.invoke(DpSize.m4476boximpl(density3.mo575toDpSizekrfVVM(IntSizeKt.m4560toSizeozmzZPI(jMo491getSizeYbymL2g2))));
                                    return;
                                }
                                return;
                            }
                            platformMagnifierCreate.dismiss();
                        }
                    }), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    platformMagnifier = platformMagnifierCreate;
                    platformMagnifier.dismiss();
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    platformMagnifier = platformMagnifierCreate;
                    platformMagnifier.dismiss();
                    throw th;
                }
            }
        }
    }

    public static final Modifier magnifier(Modifier modifier, Function1<? super Density, Offset> sourceCenter, Function1<? super Density, Offset> magnifierCenter, float f, MagnifierStyle style, Function1<? super DpSize, Unit> function1, PlatformMagnifierFactory platformMagnifierFactory) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(sourceCenter, "sourceCenter");
        Intrinsics.checkNotNullParameter(magnifierCenter, "magnifierCenter");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(platformMagnifierFactory, "platformMagnifierFactory");
        return ComposedModifierKt.composed$default(modifier, null, new AnonymousClass4(sourceCenter, magnifierCenter, f, function1, platformMagnifierFactory, style), 1, null);
    }

    public static /* synthetic */ boolean isPlatformMagnifierSupported$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Build.VERSION.SDK_INT;
        }
        return isPlatformMagnifierSupported(i);
    }

    public static final Modifier magnifier(Modifier modifier, final Function1<? super Density, Offset> sourceCenter, final Function1<? super Density, Offset> magnifierCenter, final float f, final MagnifierStyle style, Function1<? super DpSize, Unit> function1) {
        Modifier.Companion companionMagnifier;
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(sourceCenter, "sourceCenter");
        Intrinsics.checkNotNullParameter(magnifierCenter, "magnifierCenter");
        Intrinsics.checkNotNullParameter(style, "style");
        Function1<InspectorInfo, Unit> noInspectorInfo = InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.MagnifierKt$magnifier$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName(MagnifierKt.isPlatformMagnifierSupported$default(0, 1, null) ? "magnifier" : "magnifier (not supported)");
                inspectorInfo.getProperties().set("sourceCenter", sourceCenter);
                inspectorInfo.getProperties().set("magnifierCenter", magnifierCenter);
                inspectorInfo.getProperties().set("zoom", Float.valueOf(f));
                inspectorInfo.getProperties().set("style", style);
            }
        } : InspectableValueKt.getNoInspectorInfo();
        Modifier.Companion companion = Modifier.INSTANCE;
        if (isPlatformMagnifierSupported$default(0, 1, null)) {
            companionMagnifier = magnifier(companion, sourceCenter, magnifierCenter, f, style, function1, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform());
        } else {
            companionMagnifier = Modifier.INSTANCE;
        }
        return InspectableValueKt.inspectableWrapper(modifier, noInspectorInfo, companionMagnifier);
    }
}
