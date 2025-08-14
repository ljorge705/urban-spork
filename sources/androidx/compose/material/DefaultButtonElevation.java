package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J&\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0019\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0007R\u0019\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0007R\u0019\u0010\u0004\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0007\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/DefaultButtonElevation;", "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "disabledElevation", "(FFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", ViewProps.ELEVATION, "Landroidx/compose/runtime/State;", ViewProps.ENABLED, "", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "(ZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class DefaultButtonElevation implements ButtonElevation {
    private final float defaultElevation;
    private final float disabledElevation;
    private final float pressedElevation;

    public /* synthetic */ DefaultButtonElevation(float f, float f2, float f3, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3);
    }

    private DefaultButtonElevation(float f, float f2, float f3) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.disabledElevation = f3;
    }

    @Override // androidx.compose.material.ButtonElevation
    public State<Dp> elevation(boolean z, InteractionSource interactionSource, Composer composer, int i) {
        float f;
        Intrinsics.checkNotNullParameter(interactionSource, "interactionSource");
        composer.startReplaceableGroup(-1598810717);
        ComposerKt.sourceInformation(composer, "C(elevation)466@19024L46,467@19079L584,494@19988L51:Button.kt#jmzs0o");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateListOf();
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
        EffectsKt.LaunchedEffect(interactionSource, new AnonymousClass1(interactionSource, snapshotStateList, null), composer, (i >> 3) & 14);
        Interaction interaction = (Interaction) CollectionsKt.lastOrNull((List) snapshotStateList);
        if (z) {
            f = interaction instanceof PressInteraction.Press ? this.pressedElevation : this.defaultElevation;
        } else {
            f = this.disabledElevation;
        }
        float f2 = f;
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Animatable(Dp.m4388boximpl(f2), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, 4, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        Animatable animatable = (Animatable) objRememberedValue2;
        if (!z) {
            composer.startReplaceableGroup(-1598809568);
            ComposerKt.sourceInformation(composer, "498@20138L80");
            EffectsKt.LaunchedEffect(Dp.m4388boximpl(f2), new AnonymousClass2(animatable, f2, null), composer, 0);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-1598809397);
            ComposerKt.sourceInformation(composer, "502@20248L416");
            EffectsKt.LaunchedEffect(Dp.m4388boximpl(f2), new AnonymousClass3(animatable, this, f2, interaction, null), composer, 0);
            composer.endReplaceableGroup();
        }
        State<Dp> stateAsState = animatable.asState();
        composer.endReplaceableGroup();
        return stateAsState;
    }

    /* compiled from: Button.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.DefaultButtonElevation$elevation$1", f = "Button.kt", i = {}, l = {563}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.DefaultButtonElevation$elevation$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ SnapshotStateList<Interaction> $interactions;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(InteractionSource interactionSource, SnapshotStateList<Interaction> snapshotStateList, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$interactionSource = interactionSource;
            this.$interactions = snapshotStateList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$interactionSource, this.$interactions, continuation);
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
                Flow<Interaction> interactions = this.$interactionSource.getInteractions();
                final SnapshotStateList<Interaction> snapshotStateList = this.$interactions;
                this.label = 1;
                if (interactions.collect(new FlowCollector<Interaction>() { // from class: androidx.compose.material.DefaultButtonElevation$elevation$1$invokeSuspend$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Interaction interaction, Continuation<? super Unit> continuation) {
                        Interaction interaction2 = interaction;
                        if (interaction2 instanceof PressInteraction.Press) {
                            snapshotStateList.add(interaction2);
                        } else if (interaction2 instanceof PressInteraction.Release) {
                            snapshotStateList.remove(((PressInteraction.Release) interaction2).getPress());
                        } else if (interaction2 instanceof PressInteraction.Cancel) {
                            snapshotStateList.remove(((PressInteraction.Cancel) interaction2).getPress());
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
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

    /* compiled from: Button.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.DefaultButtonElevation$elevation$2", f = "Button.kt", i = {}, l = {500}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.DefaultButtonElevation$elevation$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
        final /* synthetic */ float $target;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Animatable<Dp, AnimationVector1D> animatable, float f, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$animatable = animatable;
            this.$target = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$animatable, this.$target, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$animatable.snapTo(Dp.m4388boximpl(this.$target), this) == coroutine_suspended) {
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

    /* compiled from: Button.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.DefaultButtonElevation$elevation$3", f = "Button.kt", i = {}, l = {508}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.material.DefaultButtonElevation$elevation$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
        final /* synthetic */ Interaction $interaction;
        final /* synthetic */ float $target;
        int label;
        final /* synthetic */ DefaultButtonElevation this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Animatable<Dp, AnimationVector1D> animatable, DefaultButtonElevation defaultButtonElevation, float f, Interaction interaction, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$animatable = animatable;
            this.this$0 = defaultButtonElevation;
            this.$target = f;
            this.$interaction = interaction;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$animatable, this.this$0, this.$target, this.$interaction, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressInteraction.Press press = Dp.m4395equalsimpl0(this.$animatable.getTargetValue().m4404unboximpl(), this.this$0.pressedElevation) ? new PressInteraction.Press(Offset.INSTANCE.m1655getZeroF1C5BW0(), null) : null;
                this.label = 1;
                if (ElevationKt.m1300animateElevationrAjV9yQ(this.$animatable, this.$target, press, this.$interaction, this) == coroutine_suspended) {
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
}
