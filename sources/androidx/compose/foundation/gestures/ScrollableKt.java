package androidx.compose.foundation.gestures;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Scrollable.kt */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u0014*\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\"\u0010\u0017\u001a\u00020\u0018*\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001aO\u0010\u001c\u001a\u00020\u0018*\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0012\u001a\u00020\bH\u0003¢\u0006\u0002\u0010(\u001aR\u0010)\u001a\u00020\u0018*\u00020\u00182\u0006\u0010*\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 2\b\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007\u001aF\u0010)\u001a\u00020\u0018*\u00020\u00182\u0006\u0010*\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"DefaultScrollMotionDurationScale", "Landroidx/compose/ui/MotionDurationScale;", "getDefaultScrollMotionDurationScale", "()Landroidx/compose/ui/MotionDurationScale;", "DefaultScrollMotionDurationScaleFactor", "", "ModifierLocalScrollableContainer", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "", "getModifierLocalScrollableContainer", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "NoOpScrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "scrollableNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "scrollLogic", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", ViewProps.ENABLED, "awaitScrollEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseWheelScroll", "Landroidx/compose/ui/Modifier;", "scrollingLogicState", "mouseWheelScrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "pointerScrollable", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseDirection", "controller", "Landroidx/compose/foundation/gestures/ScrollableState;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/OverscrollEffect;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "scrollable", "state", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScrollableKt {
    private static final float DefaultScrollMotionDurationScaleFactor = 1.0f;
    private static final ScrollScope NoOpScrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollableKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float pixels) {
            return pixels;
        }
    };
    private static final ProvidableModifierLocal<Boolean> ModifierLocalScrollableContainer = ModifierLocalKt.modifierLocalOf(new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.ScrollableKt$ModifierLocalScrollableContainer$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return false;
        }
    });
    private static final MotionDurationScale DefaultScrollMotionDurationScale = new MotionDurationScale() { // from class: androidx.compose.foundation.gestures.ScrollableKt$DefaultScrollMotionDurationScale$1
        @Override // androidx.compose.ui.MotionDurationScale
        public float getScaleFactor() {
            return 1.0f;
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) MotionDurationScale.DefaultImpls.fold(this, r, function2);
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
            return (E) MotionDurationScale.DefaultImpls.get(this, key);
        }

        @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
        public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
            return MotionDurationScale.DefaultImpls.minusKey(this, key);
        }

        @Override // kotlin.coroutines.CoroutineContext
        public CoroutineContext plus(CoroutineContext coroutineContext) {
            return MotionDurationScale.DefaultImpls.plus(this, coroutineContext);
        }
    };

    /* compiled from: Scrollable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableKt", f = "Scrollable.kt", i = {0}, l = {313}, m = "awaitScrollEvent", n = {"$this$awaitScrollEvent"}, s = {"L$0"})
    /* renamed from: androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollableKt.awaitScrollEvent(null, this);
        }
    }

    public static final MotionDurationScale getDefaultScrollMotionDurationScale() {
        return DefaultScrollMotionDurationScale;
    }

    public static final ProvidableModifierLocal<Boolean> getModifierLocalScrollableContainer() {
        return ModifierLocalScrollableContainer;
    }

    public static /* synthetic */ Modifier scrollable$default(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = false;
        }
        return scrollable(modifier, scrollableState, orientation, z3, z2, (i & 16) != 0 ? null : flingBehavior, (i & 32) != 0 ? null : mutableInteractionSource);
    }

    public static final Modifier scrollable(Modifier modifier, ScrollableState state, Orientation orientation, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        return scrollable(modifier, state, orientation, null, z, z2, flingBehavior, mutableInteractionSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier pointerScrollable(Modifier modifier, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z, ScrollableState scrollableState, FlingBehavior flingBehavior, OverscrollEffect overscrollEffect, boolean z2, Composer composer, int i) {
        composer.startReplaceableGroup(-2012025036);
        ComposerKt.sourceInformation(composer, "C(pointerScrollable)P(3,4,6!1,2,5)251@10630L53,252@10706L224,262@10964L88,265@11078L46,266@11148L22,274@11392L47,275@11465L160:Scrollable.kt#8bwon0");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2012025036, i, -1, "androidx.compose.foundation.gestures.pointerScrollable (Scrollable.kt:241)");
        }
        composer.startReplaceableGroup(-1730186281);
        ComposerKt.sourceInformation(composer, "250@10581L15");
        FlingBehavior flingBehavior2 = flingBehavior == null ? ScrollableDefaults.INSTANCE.flingBehavior(composer, 6) : flingBehavior;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new NestedScrollDispatcher(), null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new ScrollingLogic(orientation, z, mutableState, scrollableState, flingBehavior2, overscrollEffect), composer, 0);
        Object objValueOf = Boolean.valueOf(z2);
        composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(objValueOf);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = scrollableNestedScrollConnection(stateRememberUpdatedState, z2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        NestedScrollConnection nestedScrollConnection = (NestedScrollConnection) objRememberedValue2;
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new ScrollDraggableState(stateRememberUpdatedState);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        ScrollConfig scrollConfigPlatformScrollConfig = AndroidScrollable_androidKt.platformScrollConfig(composer, 0);
        ScrollDraggableState scrollDraggableState = (ScrollDraggableState) objRememberedValue3;
        C02861 c02861 = new Function1<PointerInputChange, Boolean>() { // from class: androidx.compose.foundation.gestures.ScrollableKt.pointerScrollable.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PointerInputChange down) {
                Intrinsics.checkNotNullParameter(down, "down");
                return Boolean.valueOf(!PointerType.m3349equalsimpl0(down.getType(), PointerType.INSTANCE.m3354getMouseT8wyACA()));
            }
        };
        composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged2 = composer.changed(stateRememberUpdatedState);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.ScrollableKt$pointerScrollable$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.valueOf(stateRememberUpdatedState.getValue().shouldScrollImmediately());
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        composer.endReplaceableGroup();
        Function0 function0 = (Function0) objRememberedValue4;
        composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged3 = composer.changed(mutableState) | composer.changed(stateRememberUpdatedState);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = (Function3) new ScrollableKt$pointerScrollable$3$1(mutableState, stateRememberUpdatedState, null);
            composer.updateRememberedValue(objRememberedValue5);
        }
        composer.endReplaceableGroup();
        Modifier modifierNestedScroll = NestedScrollModifierKt.nestedScroll(mouseWheelScroll(DraggableKt.draggable(modifier, scrollDraggableState, c02861, orientation, (64 & 8) != 0 ? true : z2, (64 & 16) != 0 ? null : mutableInteractionSource, function0, (64 & 64) != 0 ? new DraggableKt.AnonymousClass6(null) : null, (64 & 128) != 0 ? new DraggableKt.AnonymousClass7(null) : (Function3) objRememberedValue5, (64 & 256) != 0 ? false : false), stateRememberUpdatedState, scrollConfigPlatformScrollConfig), nestedScrollConnection, (NestedScrollDispatcher) mutableState.getValue());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierNestedScroll;
    }

    /* compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableKt$mouseWheelScroll$1", f = "Scrollable.kt", i = {}, l = {291}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.gestures.ScrollableKt$mouseWheelScroll$1, reason: invalid class name and case insensitive filesystem */
    static final class C02851 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ScrollConfig $mouseWheelScrollConfig;
        final /* synthetic */ State<ScrollingLogic> $scrollingLogicState;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02851(ScrollConfig scrollConfig, State<ScrollingLogic> state, Continuation<? super C02851> continuation) {
            super(2, continuation);
            this.$mouseWheelScrollConfig = scrollConfig;
            this.$scrollingLogicState = state;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02851 c02851 = new C02851(this.$mouseWheelScrollConfig, this.$scrollingLogicState, continuation);
            c02851.L$0 = obj;
            return c02851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C02851) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: Scrollable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableKt$mouseWheelScroll$1$1", f = "Scrollable.kt", i = {0}, l = {293}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        /* renamed from: androidx.compose.foundation.gestures.ScrollableKt$mouseWheelScroll$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00191 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ScrollConfig $mouseWheelScrollConfig;
            final /* synthetic */ State<ScrollingLogic> $scrollingLogicState;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00191(ScrollConfig scrollConfig, State<ScrollingLogic> state, Continuation<? super C00191> continuation) {
                super(2, continuation);
                this.$mouseWheelScrollConfig = scrollConfig;
                this.$scrollingLogicState = state;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00191 c00191 = new C00191(this.$mouseWheelScrollConfig, this.$scrollingLogicState, continuation);
                c00191.L$0 = obj;
                return c00191;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00191) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
            
                r4 = r0.$mouseWheelScrollConfig;
                r5 = r0.$scrollingLogicState;
                r7 = r4.mo493calculateMouseWheelScroll8xgXZGE(r3, r12, r3.mo3194getSizeYbymL2g());
                r4 = r5.getValue();
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
            
                if (r4.getScrollableState().dispatchRawDelta(r4.reverseIfNeeded(r4.m594toFloatk4lQ0M(r7))) != 0.0f) goto L22;
             */
            /* JADX WARN: Code restructure failed: missing block: B:22:0x0084, code lost:
            
                r12 = r12.getChanges();
                r4 = r12.size();
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x008c, code lost:
            
                if (r6 >= r4) goto L28;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x008e, code lost:
            
                r12.get(r6).consume();
                r6 = r6 + 1;
             */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0034 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0059 A[EDGE_INSN: B:27:0x0059->B:19:0x0059 BREAK  A[LOOP:0: B:14:0x0046->B:18:0x0056], SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0035 -> B:13:0x003a). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                /*
                    r11 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r11.label
                    r2 = 1
                    if (r1 == 0) goto L1e
                    if (r1 != r2) goto L16
                    java.lang.Object r1 = r11.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r12)
                    r3 = r1
                    r1 = r0
                    r0 = r11
                    goto L3a
                L16:
                    java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r12.<init>(r0)
                    throw r12
                L1e:
                    kotlin.ResultKt.throwOnFailure(r12)
                    java.lang.Object r12 = r11.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r12 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r12
                    r1 = r12
                    r12 = r11
                L27:
                    r3 = r12
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r12.L$0 = r1
                    r12.label = r2
                    java.lang.Object r3 = androidx.compose.foundation.gestures.ScrollableKt.access$awaitScrollEvent(r1, r3)
                    if (r3 != r0) goto L35
                    return r0
                L35:
                    r10 = r0
                    r0 = r12
                    r12 = r3
                    r3 = r1
                    r1 = r10
                L3a:
                    androidx.compose.ui.input.pointer.PointerEvent r12 = (androidx.compose.ui.input.pointer.PointerEvent) r12
                    java.util.List r4 = r12.getChanges()
                    int r5 = r4.size()
                    r6 = 0
                    r7 = r6
                L46:
                    if (r7 >= r5) goto L59
                    java.lang.Object r8 = r4.get(r7)
                    androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                    boolean r8 = r8.isConsumed()
                    r8 = r8 ^ r2
                    if (r8 != 0) goto L56
                    goto L9a
                L56:
                    int r7 = r7 + 1
                    goto L46
                L59:
                    androidx.compose.foundation.gestures.ScrollConfig r4 = r0.$mouseWheelScrollConfig
                    androidx.compose.runtime.State<androidx.compose.foundation.gestures.ScrollingLogic> r5 = r0.$scrollingLogicState
                    r7 = r3
                    androidx.compose.ui.unit.Density r7 = (androidx.compose.ui.unit.Density) r7
                    long r8 = r3.mo3194getSizeYbymL2g()
                    long r7 = r4.mo493calculateMouseWheelScroll8xgXZGE(r7, r12, r8)
                    java.lang.Object r4 = r5.getValue()
                    androidx.compose.foundation.gestures.ScrollingLogic r4 = (androidx.compose.foundation.gestures.ScrollingLogic) r4
                    float r5 = r4.m594toFloatk4lQ0M(r7)
                    float r5 = r4.reverseIfNeeded(r5)
                    androidx.compose.foundation.gestures.ScrollableState r4 = r4.getScrollableState()
                    float r4 = r4.dispatchRawDelta(r5)
                    r5 = 0
                    int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                    if (r4 != 0) goto L84
                    goto L9a
                L84:
                    java.util.List r12 = r12.getChanges()
                    int r4 = r12.size()
                L8c:
                    if (r6 >= r4) goto L9a
                    java.lang.Object r5 = r12.get(r6)
                    androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
                    r5.consume()
                    int r6 = r6 + 1
                    goto L8c
                L9a:
                    r12 = r0
                    r0 = r1
                    r1 = r3
                    goto L27
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableKt.C02851.C00191.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00191(this.$mouseWheelScrollConfig, this.$scrollingLogicState, null), this) == coroutine_suspended) {
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

    private static final Modifier mouseWheelScroll(Modifier modifier, State<ScrollingLogic> state, ScrollConfig scrollConfig) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, state, scrollConfig, new C02851(scrollConfig, state, null));
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0042 -> B:18:0x0045). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitScrollEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.ScrollableKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1 r0 = (androidx.compose.foundation.gestures.ScrollableKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1 r0 = new androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            kotlin.ResultKt.throwOnFailure(r6)
        L39:
            r0.L$0 = r5
            r0.label = r3
            r6 = 0
            java.lang.Object r6 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r5, r6, r0, r3, r6)
            if (r6 != r1) goto L45
            return r1
        L45:
            androidx.compose.ui.input.pointer.PointerEvent r6 = (androidx.compose.ui.input.pointer.PointerEvent) r6
            int r2 = r6.getType()
            androidx.compose.ui.input.pointer.PointerEventType$Companion r4 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
            int r4 = r4.m3239getScroll7fucELk()
            boolean r2 = androidx.compose.ui.input.pointer.PointerEventType.m3230equalsimpl0(r2, r4)
            if (r2 == 0) goto L39
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableKt.awaitScrollEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000ø\u0001\u0001ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ%\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"androidx/compose/foundation/gestures/ScrollableKt$scrollableNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02871 implements NestedScrollConnection {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ State<ScrollingLogic> $scrollLogic;

        C02871(State<ScrollingLogic> state, boolean z) {
            this.$scrollLogic = state;
            this.$enabled = z;
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
        public long mo585onPreScrollOzD1aCk(long available, int source) {
            if (NestedScrollSource.m3172equalsimpl0(source, NestedScrollSource.INSTANCE.m3178getFlingWNlRxjI())) {
                this.$scrollLogic.getValue().registerNestedFling(true);
            }
            return Offset.INSTANCE.m1655getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
        public long mo584onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (this.$enabled) {
                return this.$scrollLogic.getValue().m589performRawScrollMKHz9U(available);
            }
            return Offset.INSTANCE.m1655getZeroF1C5BW0();
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object mo583onPostFlingRZ2iAVY(long r3, long r5, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r7) {
            /*
                r2 = this;
                boolean r3 = r7 instanceof androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1
                if (r3 == 0) goto L14
                r3 = r7
                androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1 r3 = (androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1) r3
                int r4 = r3.label
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                r4 = r4 & r0
                if (r4 == 0) goto L14
                int r4 = r3.label
                int r4 = r4 - r0
                r3.label = r4
                goto L19
            L14:
                androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1 r3 = new androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1$onPostFling$1
                r3.<init>(r2, r7)
            L19:
                java.lang.Object r4 = r3.result
                java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r3.label
                r1 = 1
                if (r0 == 0) goto L38
                if (r0 != r1) goto L30
                long r5 = r3.J$0
                java.lang.Object r3 = r3.L$0
                androidx.compose.foundation.gestures.ScrollableKt$scrollableNestedScrollConnection$1 r3 = (androidx.compose.foundation.gestures.ScrollableKt.C02871) r3
                kotlin.ResultKt.throwOnFailure(r4)
                goto L55
            L30:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L38:
                kotlin.ResultKt.throwOnFailure(r4)
                boolean r4 = r2.$enabled
                if (r4 == 0) goto L60
                androidx.compose.runtime.State<androidx.compose.foundation.gestures.ScrollingLogic> r4 = r2.$scrollLogic
                java.lang.Object r4 = r4.getValue()
                androidx.compose.foundation.gestures.ScrollingLogic r4 = (androidx.compose.foundation.gestures.ScrollingLogic) r4
                r3.L$0 = r2
                r3.J$0 = r5
                r3.label = r1
                java.lang.Object r4 = r4.m587doFlingAnimationQWom1Mo(r5, r3)
                if (r4 != r7) goto L54
                return r7
            L54:
                r3 = r2
            L55:
                androidx.compose.ui.unit.Velocity r4 = (androidx.compose.ui.unit.Velocity) r4
                long r0 = r4.getPackedValue()
                long r4 = androidx.compose.ui.unit.Velocity.m4618minusAH228Gc(r5, r0)
                goto L67
            L60:
                androidx.compose.ui.unit.Velocity$Companion r3 = androidx.compose.ui.unit.Velocity.INSTANCE
                long r4 = r3.m4626getZero9UxMQ8M()
                r3 = r2
            L67:
                androidx.compose.ui.unit.Velocity r4 = androidx.compose.ui.unit.Velocity.m4606boximpl(r4)
                androidx.compose.runtime.State<androidx.compose.foundation.gestures.ScrollingLogic> r3 = r3.$scrollLogic
                r4.getPackedValue()
                java.lang.Object r3 = r3.getValue()
                androidx.compose.foundation.gestures.ScrollingLogic r3 = (androidx.compose.foundation.gestures.ScrollingLogic) r3
                r5 = 0
                r3.registerNestedFling(r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableKt.C02871.mo583onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NestedScrollConnection scrollableNestedScrollConnection(State<ScrollingLogic> state, boolean z) {
        return new C02871(state, z);
    }

    public static final Modifier scrollable(Modifier modifier, final ScrollableState state, final Orientation orientation, final OverscrollEffect overscrollEffect, final boolean z, final boolean z2, final FlingBehavior flingBehavior, final MutableInteractionSource mutableInteractionSource) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.gestures.ScrollableKt$scrollable$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("scrollable");
                inspectorInfo.getProperties().set("orientation", orientation);
                inspectorInfo.getProperties().set("state", state);
                inspectorInfo.getProperties().set("overscrollEffect", overscrollEffect);
                inspectorInfo.getProperties().set(ViewProps.ENABLED, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("reverseDirection", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("flingBehavior", flingBehavior);
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.gestures.ScrollableKt.scrollable.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-629830927);
                ComposerKt.sourceInformation(composer, "C156@7453L24,158@7535L170,165@7822L242:Scrollable.kt#8bwon0");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-629830927, i, -1, "androidx.compose.foundation.gestures.scrollable.<anonymous> (Scrollable.kt:155)");
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
                CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                composer.endReplaceableGroup();
                Object[] objArr = {coroutineScope, orientation, state, Boolean.valueOf(z2)};
                Orientation orientation2 = orientation;
                ScrollableState scrollableState = state;
                boolean z3 = z2;
                composer.startReplaceableGroup(-568225417);
                ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
                boolean zChanged = false;
                for (int i2 = 0; i2 < 4; i2++) {
                    zChanged |= composer.changed(objArr[i2]);
                }
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new ContentInViewModifier(coroutineScope, orientation2, scrollableState, z3);
                    composer.updateRememberedValue(objRememberedValue2);
                }
                composer.endReplaceableGroup();
                Modifier modifierThen = ScrollableKt.pointerScrollable(FocusableKt.focusGroup(Modifier.INSTANCE).then(((ContentInViewModifier) objRememberedValue2).getModifier()), mutableInteractionSource, orientation, z2, state, flingBehavior, overscrollEffect, z, composer, 0).then(z ? ModifierLocalScrollableContainerProvider.INSTANCE : Modifier.INSTANCE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierThen;
            }
        });
    }
}
