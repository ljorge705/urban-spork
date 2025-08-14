package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004hijkB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B!\b\u0001\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ'\u0010I\u001a\u00020\u00192\u0018\u0010J\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\bKJ\u0019\u0010L\u001a\u00020\u00192\n\u0010M\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\bNJ\u0017\u0010O\u001a\u00020P2\u0006\u0010;\u001a\u00028\u0000H\u0001¢\u0006\u0004\bQ\u0010RJ\b\u0010S\u001a\u00020PH\u0002J\u001d\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020&2\u0006\u0010V\u001a\u00020WH\u0000¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020PH\u0000¢\u0006\u0002\bZJ\u0015\u0010[\u001a\u00020P2\u0006\u0010U\u001a\u00020&H\u0000¢\u0006\u0002\b\\J'\u0010]\u001a\u00020P2\u0018\u0010^\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030_R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\b`J'\u0010]\u001a\u00020P2\u0018\u0010J\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\b`J\u0019\u0010a\u001a\u00020\u00192\n\u0010M\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\bbJ'\u0010c\u001a\u00020P2\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010;\u001a\u00028\u00002\u0006\u0010+\u001a\u00020&H\u0007¢\u0006\u0004\bd\u0010eJ\u0017\u0010f\u001a\u00020P2\u0006\u0010;\u001a\u00028\u0000H\u0001¢\u0006\u0004\bg\u0010RR&\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030\fR\b\u0012\u0004\u0012\u00028\u00000\u00000\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u00008F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR1\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00198F@@X\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b!\u0010\"\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001c\u0010\u001a\"\u0004\b\u001f\u0010 R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R1\u0010+\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020&8F@FX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b/\u0010\"\u0012\u0004\b,\u0010\u001e\u001a\u0004\b-\u0010(\"\u0004\b.\u0010*R7\u00101\u001a\b\u0012\u0004\u0012\u00028\u0000002\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u0000008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\"\u001a\u0004\b2\u00103\"\u0004\b4\u00105R+\u00107\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020&8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\"\u001a\u0004\b8\u0010(\"\u0004\b9\u0010*R+\u0010;\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u00008F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\"\u001a\u0004\b<\u0010\u0015\"\u0004\b=\u0010\u0017R\u001b\u0010?\u001a\u00020&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\b@\u0010(R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010C\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u000f8F¢\u0006\u0006\u001a\u0004\bD\u0010\u0011R+\u0010E\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00198@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010\"\u001a\u0004\bF\u0010\u001a\"\u0004\bG\u0010 ¨\u0006l"}, d2 = {"Landroidx/compose/animation/core/Transition;", ExifInterface.LATITUDE_SOUTH, "", "initialState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "transitionState", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;)V", "_animations", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "_transitions", "animations", "", "getAnimations", "()Ljava/util/List;", "value", "currentState", "getCurrentState", "()Ljava/lang/Object;", "setCurrentState$animation_core_release", "(Ljava/lang/Object;)V", "isRunning", "", "()Z", "<set-?>", "isSeeking", "isSeeking$annotations", "()V", "setSeeking$animation_core_release", "(Z)V", "isSeeking$delegate", "Landroidx/compose/runtime/MutableState;", "getLabel", "()Ljava/lang/String;", "lastSeekedTimeNanos", "", "getLastSeekedTimeNanos$animation_core_release", "()J", "setLastSeekedTimeNanos$animation_core_release", "(J)V", "playTimeNanos", "getPlayTimeNanos$annotations", "getPlayTimeNanos", "setPlayTimeNanos", "playTimeNanos$delegate", "Landroidx/compose/animation/core/Transition$Segment;", "segment", "getSegment", "()Landroidx/compose/animation/core/Transition$Segment;", "setSegment", "(Landroidx/compose/animation/core/Transition$Segment;)V", "segment$delegate", "startTimeNanos", "getStartTimeNanos", "setStartTimeNanos", "startTimeNanos$delegate", "targetState", "getTargetState", "setTargetState$animation_core_release", "targetState$delegate", "totalDurationNanos", "getTotalDurationNanos", "totalDurationNanos$delegate", "Landroidx/compose/runtime/State;", "transitions", "getTransitions", "updateChildrenNeeded", "getUpdateChildrenNeeded$animation_core_release", "setUpdateChildrenNeeded$animation_core_release", "updateChildrenNeeded$delegate", "addAnimation", "animation", "addAnimation$animation_core_release", "addTransition", "transition", "addTransition$animation_core_release", "animateTo", "", "animateTo$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "onChildAnimationUpdated", "onFrame", "frameTimeNanos", "durationScale", "", "onFrame$animation_core_release", "onTransitionEnd", "onTransitionEnd$animation_core_release", "onTransitionStart", "onTransitionStart$animation_core_release", "removeAnimation", "deferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "removeAnimation$animation_core_release", "removeTransition", "removeTransition$animation_core_release", "setPlaytimeAfterInitialAndTargetStateEstablished", "seek", "(Ljava/lang/Object;Ljava/lang/Object;J)V", "updateTarget", "updateTarget$animation_core_release", "DeferredAnimation", "Segment", "SegmentImpl", "TransitionAnimationState", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Transition<S> {
    public static final int $stable = 0;
    private final SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> _animations;
    private final SnapshotStateList<Transition<?>> _transitions;

    /* renamed from: isSeeking$delegate, reason: from kotlin metadata */
    private final MutableState isSeeking;
    private final String label;
    private long lastSeekedTimeNanos;

    /* renamed from: playTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableState playTimeNanos;

    /* renamed from: segment$delegate, reason: from kotlin metadata */
    private final MutableState segment;

    /* renamed from: startTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableState startTimeNanos;

    /* renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;

    /* renamed from: totalDurationNanos$delegate, reason: from kotlin metadata */
    private final State totalDurationNanos;
    private final MutableTransitionState<S> transitionState;

    /* renamed from: updateChildrenNeeded$delegate, reason: from kotlin metadata */
    private final MutableState updateChildrenNeeded;

    public static /* synthetic */ void getPlayTimeNanos$annotations() {
    }

    public static /* synthetic */ void isSeeking$annotations() {
    }

    public final String getLabel() {
        return this.label;
    }

    /* renamed from: getLastSeekedTimeNanos$animation_core_release, reason: from getter */
    public final long getLastSeekedTimeNanos() {
        return this.lastSeekedTimeNanos;
    }

    public final void setLastSeekedTimeNanos$animation_core_release(long j) {
        this.lastSeekedTimeNanos = j;
    }

    public Transition(MutableTransitionState<S> transitionState, String str) {
        Intrinsics.checkNotNullParameter(transitionState, "transitionState");
        this.transitionState = transitionState;
        this.label = str;
        this.targetState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(getCurrentState(), null, 2, null);
        this.segment = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()), null, 2, null);
        this.playTimeNanos = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
        this.startTimeNanos = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Long.MIN_VALUE, null, 2, null);
        this.updateChildrenNeeded = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this._animations = SnapshotStateKt.mutableStateListOf();
        this._transitions = SnapshotStateKt.mutableStateListOf();
        this.isSeeking = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.totalDurationNanos = SnapshotStateKt.derivedStateOf(new Function0<Long>(this) { // from class: androidx.compose.animation.core.Transition$totalDurationNanos$2
            final /* synthetic */ Transition<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                Iterator<T> it = ((Transition) this.this$0)._animations.iterator();
                long jMax = 0;
                while (it.hasNext()) {
                    jMax = Math.max(jMax, ((Transition.TransitionAnimationState) it.next()).getDurationNanos$animation_core_release());
                }
                Iterator<T> it2 = ((Transition) this.this$0)._transitions.iterator();
                while (it2.hasNext()) {
                    jMax = Math.max(jMax, ((Transition) it2.next()).getTotalDurationNanos());
                }
                return Long.valueOf(jMax);
            }
        });
    }

    public /* synthetic */ Transition(MutableTransitionState mutableTransitionState, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableTransitionState, (i & 2) != 0 ? null : str);
    }

    public Transition(S s, String str) {
        this(new MutableTransitionState(s), str);
    }

    public final S getCurrentState() {
        return this.transitionState.getCurrentState();
    }

    public final void setCurrentState$animation_core_release(S s) {
        this.transitionState.setCurrentState$animation_core_release(s);
    }

    public final S getTargetState() {
        return (S) this.targetState.getValue();
    }

    public final Segment<S> getSegment() {
        return (Segment) this.segment.getValue();
    }

    public final boolean isRunning() {
        return getStartTimeNanos() != Long.MIN_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long getPlayTimeNanos() {
        return ((Number) this.playTimeNanos.getValue()).longValue();
    }

    public final void setPlayTimeNanos(long j) {
        this.playTimeNanos.setValue(Long.valueOf(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long getStartTimeNanos() {
        return ((Number) this.startTimeNanos.getValue()).longValue();
    }

    private final void setStartTimeNanos(long j) {
        this.startTimeNanos.setValue(Long.valueOf(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getUpdateChildrenNeeded$animation_core_release() {
        return ((Boolean) this.updateChildrenNeeded.getValue()).booleanValue();
    }

    public final void setUpdateChildrenNeeded$animation_core_release(boolean z) {
        this.updateChildrenNeeded.setValue(Boolean.valueOf(z));
    }

    public final List<Transition<?>> getTransitions() {
        return this._transitions;
    }

    public final List<Transition<S>.TransitionAnimationState<?, ?>> getAnimations() {
        return this._animations;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSeeking() {
        return ((Boolean) this.isSeeking.getValue()).booleanValue();
    }

    public final void setSeeking$animation_core_release(boolean z) {
        this.isSeeking.setValue(Boolean.valueOf(z));
    }

    public final void onFrame$animation_core_release(long frameTimeNanos, float durationScale) {
        if (getStartTimeNanos() == Long.MIN_VALUE) {
            onTransitionStart$animation_core_release(frameTimeNanos);
        }
        setUpdateChildrenNeeded$animation_core_release(false);
        setPlayTimeNanos(frameTimeNanos - getStartTimeNanos());
        boolean z = true;
        for (Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState : this._animations) {
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                transitionAnimationState.onPlayTimeChanged$animation_core_release(getPlayTimeNanos(), durationScale);
            }
            if (!transitionAnimationState.isFinished$animation_core_release()) {
                z = false;
            }
        }
        for (Transition<?> transition : this._transitions) {
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                transition.onFrame$animation_core_release(getPlayTimeNanos(), durationScale);
            }
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                z = false;
            }
        }
        if (z) {
            onTransitionEnd$animation_core_release();
        }
    }

    public final void onTransitionStart$animation_core_release(long frameTimeNanos) {
        setStartTimeNanos(frameTimeNanos);
        this.transitionState.setRunning$animation_core_release(true);
    }

    public final void onTransitionEnd$animation_core_release() {
        setStartTimeNanos(Long.MIN_VALUE);
        setCurrentState$animation_core_release(getTargetState());
        setPlayTimeNanos(0L);
        this.transitionState.setRunning$animation_core_release(false);
    }

    public final void seek(S initialState, S targetState, long playTimeNanos) {
        setStartTimeNanos(Long.MIN_VALUE);
        this.transitionState.setRunning$animation_core_release(false);
        if (!isSeeking() || !Intrinsics.areEqual(getCurrentState(), initialState) || !Intrinsics.areEqual(getTargetState(), targetState)) {
            setCurrentState$animation_core_release(initialState);
            setTargetState$animation_core_release(targetState);
            setSeeking$animation_core_release(true);
            setSegment(new SegmentImpl(initialState, targetState));
        }
        for (Transition<?> transition : this._transitions) {
            Intrinsics.checkNotNull(transition, "null cannot be cast to non-null type androidx.compose.animation.core.Transition<kotlin.Any>");
            if (transition.isSeeking()) {
                transition.seek(transition.getCurrentState(), transition.getTargetState(), playTimeNanos);
            }
        }
        Iterator<Transition<S>.TransitionAnimationState<?, ?>> it = this._animations.iterator();
        while (it.hasNext()) {
            it.next().seekTo$animation_core_release(playTimeNanos);
        }
        this.lastSeekedTimeNanos = playTimeNanos;
    }

    public final boolean addTransition$animation_core_release(Transition<?> transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        return this._transitions.add(transition);
    }

    public final boolean removeTransition$animation_core_release(Transition<?> transition) {
        Intrinsics.checkNotNullParameter(transition, "transition");
        return this._transitions.remove(transition);
    }

    public final boolean addAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        return this._animations.add(animation);
    }

    public final void removeAnimation$animation_core_release(Transition<S>.TransitionAnimationState<?, ?> animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this._animations.remove(animation);
    }

    public final void updateTarget$animation_core_release(final S s, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-583974681);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(updateTarget):Transition.kt#pdpnli");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(s) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i2 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-583974681, i, -1, "androidx.compose.animation.core.Transition.updateTarget (Transition.kt:399)");
            }
            if (!isSeeking() && !Intrinsics.areEqual(getTargetState(), s)) {
                setSegment(new SegmentImpl(getTargetState(), s));
                setCurrentState$animation_core_release(getTargetState());
                setTargetState$animation_core_release(s);
                if (!isRunning()) {
                    setUpdateChildrenNeeded$animation_core_release(true);
                }
                Iterator<Transition<S>.TransitionAnimationState<?, ?>> it = this._animations.iterator();
                while (it.hasNext()) {
                    it.next().resetAnimation$animation_core_release();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>(this) { // from class: androidx.compose.animation.core.Transition$updateTarget$2
            final /* synthetic */ Transition<S> $tmp0_rcvr;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.$tmp0_rcvr = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                this.$tmp0_rcvr.updateTarget$animation_core_release(s, composer2, i | 1);
            }
        });
    }

    public final void animateTo$animation_core_release(final S s, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1493585151);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(animateTo)426@16622L25,430@16892L655,430@16871L676:Transition.kt#pdpnli");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(s) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if ((i2 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1493585151, i2, -1, "androidx.compose.animation.core.Transition.animateTo (Transition.kt:424)");
            }
            if (!isSeeking()) {
                updateTarget$animation_core_release(s, composerStartRestartGroup, (i2 & 14) | (i2 & 112));
                if (!Intrinsics.areEqual(s, getCurrentState()) || isRunning() || getUpdateChildrenNeeded$animation_core_release()) {
                    int i3 = (i2 >> 3) & 14;
                    composerStartRestartGroup.startReplaceableGroup(1157296644);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1):Composables.kt#9igjgp");
                    boolean zChanged = composerStartRestartGroup.changed(this);
                    Transition$animateTo$1$1 transition$animateTo$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged || transition$animateTo$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        transition$animateTo$1$1RememberedValue = new Transition$animateTo$1$1(this, null);
                        composerStartRestartGroup.updateRememberedValue(transition$animateTo$1$1RememberedValue);
                    }
                    composerStartRestartGroup.endReplaceableGroup();
                    EffectsKt.LaunchedEffect(this, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) transition$animateTo$1$1RememberedValue, composerStartRestartGroup, i3 | 64);
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>(this) { // from class: androidx.compose.animation.core.Transition$animateTo$2
            final /* synthetic */ Transition<S> $tmp0_rcvr;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.$tmp0_rcvr = this;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                this.$tmp0_rcvr.animateTo$animation_core_release(s, composer2, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChildAnimationUpdated() {
        setUpdateChildrenNeeded$animation_core_release(true);
        if (isSeeking()) {
            long jMax = 0;
            for (Transition<S>.TransitionAnimationState<?, ?> transitionAnimationState : this._animations) {
                jMax = Math.max(jMax, transitionAnimationState.getDurationNanos$animation_core_release());
                transitionAnimationState.seekTo$animation_core_release(this.lastSeekedTimeNanos);
            }
            setUpdateChildrenNeeded$animation_core_release(false);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B3\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00028\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001d\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020EH\u0000¢\u0006\u0002\bFJ\r\u0010G\u001a\u00020BH\u0000¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001dH\u0000¢\u0006\u0002\bJJ!\u0010K\u001a\u00020B2\b\b\u0002\u0010\u0005\u001a\u00028\u00012\b\b\u0002\u0010L\u001a\u00020!H\u0002¢\u0006\u0002\u0010MJ-\u0010N\u001a\u00020B2\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u00103\u001a\u00028\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0000¢\u0006\u0004\bO\u0010PJ%\u0010Q\u001a\u00020B2\u0006\u00103\u001a\u00028\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015H\u0000¢\u0006\u0004\bR\u0010SRC\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R7\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00158F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020!8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0014\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R+\u0010*\u001a\u00020!2\u0006\u0010\f\u001a\u00020!8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u0014\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R+\u0010.\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u0014\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u00101R+\u00103\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u0014\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R+\u0010;\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u00018V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\u0014\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\u0010\u0010?\u001a\u00028\u0002X\u0082\u000e¢\u0006\u0004\n\u0002\u0010@¨\u0006T"}, d2 = {"Landroidx/compose/animation/core/Transition$TransitionAnimationState;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "initialValue", "initialVelocityVector", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "<set-?>", "Landroidx/compose/animation/core/TargetBasedAnimation;", "animation", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "animation$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animationSpec$delegate", "durationNanos", "", "getDurationNanos$animation_core_release", "()J", "interruptionSpec", "", "isFinished", "isFinished$animation_core_release", "()Z", "setFinished$animation_core_release", "(Z)V", "isFinished$delegate", "getLabel", "()Ljava/lang/String;", "needsReset", "getNeedsReset", "setNeedsReset", "needsReset$delegate", "offsetTimeNanos", "getOffsetTimeNanos", "setOffsetTimeNanos", "(J)V", "offsetTimeNanos$delegate", "targetValue", "getTargetValue", "()Ljava/lang/Object;", "setTargetValue", "(Ljava/lang/Object;)V", "targetValue$delegate", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "value", "getValue", "setValue$animation_core_release", "value$delegate", "velocityVector", "Landroidx/compose/animation/core/AnimationVector;", "onPlayTimeChanged", "", "playTimeNanos", "durationScale", "", "onPlayTimeChanged$animation_core_release", "resetAnimation", "resetAnimation$animation_core_release", "seekTo", "seekTo$animation_core_release", "updateAnimation", "isInterrupted", "(Ljava/lang/Object;Z)V", "updateInitialAndTargetValue", "updateInitialAndTargetValue$animation_core_release", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "updateTargetValue", "updateTargetValue$animation_core_release", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {

        /* renamed from: animation$delegate, reason: from kotlin metadata */
        private final MutableState animation;

        /* renamed from: animationSpec$delegate, reason: from kotlin metadata */
        private final MutableState animationSpec;
        private final FiniteAnimationSpec<T> interruptionSpec;

        /* renamed from: isFinished$delegate, reason: from kotlin metadata */
        private final MutableState isFinished;
        private final String label;

        /* renamed from: needsReset$delegate, reason: from kotlin metadata */
        private final MutableState needsReset;

        /* renamed from: offsetTimeNanos$delegate, reason: from kotlin metadata */
        private final MutableState offsetTimeNanos;

        /* renamed from: targetValue$delegate, reason: from kotlin metadata */
        private final MutableState targetValue;
        final /* synthetic */ Transition<S> this$0;
        private final TwoWayConverter<T, V> typeConverter;

        /* renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;
        private V velocityVector;

        public final String getLabel() {
            return this.label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public TransitionAnimationState(Transition transition, T t, V initialVelocityVector, TwoWayConverter<T, V> typeConverter, String label) {
            T tInvoke;
            Intrinsics.checkNotNullParameter(initialVelocityVector, "initialVelocityVector");
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = transition;
            this.typeConverter = typeConverter;
            this.label = label;
            this.targetValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            this.animationSpec = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null), null, 2, null);
            this.animation = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation(getAnimationSpec(), typeConverter, t, getTargetValue(), initialVelocityVector), null, 2, null);
            this.isFinished = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
            this.offsetTimeNanos = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0L, null, 2, null);
            this.needsReset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            this.velocityVector = initialVelocityVector;
            Float f = VisibilityThresholdsKt.getVisibilityThresholdMap().get(typeConverter);
            if (f != null) {
                float fFloatValue = f.floatValue();
                V vInvoke = typeConverter.getConvertToVector().invoke(t);
                int size = vInvoke.getSize();
                for (int i = 0; i < size; i++) {
                    vInvoke.set$animation_core_release(i, fFloatValue);
                }
                tInvoke = this.typeConverter.getConvertFromVector().invoke(vInvoke);
            } else {
                tInvoke = null;
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, tInvoke, 3, null);
        }

        private final T getTargetValue() {
            return this.targetValue.getValue();
        }

        public final FiniteAnimationSpec<T> getAnimationSpec() {
            return (FiniteAnimationSpec) this.animationSpec.getValue();
        }

        public final TargetBasedAnimation<T, V> getAnimation() {
            return (TargetBasedAnimation) this.animation.getValue();
        }

        public final boolean isFinished$animation_core_release() {
            return ((Boolean) this.isFinished.getValue()).booleanValue();
        }

        public final void setFinished$animation_core_release(boolean z) {
            this.isFinished.setValue(Boolean.valueOf(z));
        }

        private final long getOffsetTimeNanos() {
            return ((Number) this.offsetTimeNanos.getValue()).longValue();
        }

        private final void setOffsetTimeNanos(long j) {
            this.offsetTimeNanos.setValue(Long.valueOf(j));
        }

        private final boolean getNeedsReset() {
            return ((Boolean) this.needsReset.getValue()).booleanValue();
        }

        private final void setNeedsReset(boolean z) {
            this.needsReset.setValue(Boolean.valueOf(z));
        }

        @Override // androidx.compose.runtime.State
        public T getValue() {
            return this.value.getValue();
        }

        public final long getDurationNanos$animation_core_release() {
            return getAnimation().getDurationNanos();
        }

        public final void onPlayTimeChanged$animation_core_release(long playTimeNanos, float durationScale) {
            long durationNanos;
            if (durationScale > 0.0f) {
                float offsetTimeNanos = (playTimeNanos - getOffsetTimeNanos()) / durationScale;
                if (!(!Float.isNaN(offsetTimeNanos))) {
                    throw new IllegalStateException(("Duration scale adjusted time is NaN. Duration scale: " + durationScale + ",playTimeNanos: " + playTimeNanos + ", offsetTimeNanos: " + getOffsetTimeNanos()).toString());
                }
                durationNanos = (long) offsetTimeNanos;
            } else {
                durationNanos = getAnimation().getDurationNanos();
            }
            setValue$animation_core_release(getAnimation().getValueFromNanos(durationNanos));
            this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(durationNanos);
            if (getAnimation().isFinishedFromNanos(durationNanos)) {
                setFinished$animation_core_release(true);
                setOffsetTimeNanos(0L);
            }
        }

        public final void seekTo$animation_core_release(long playTimeNanos) {
            setValue$animation_core_release(getAnimation().getValueFromNanos(playTimeNanos));
            this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(playTimeNanos);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* synthetic */ void updateAnimation$default(TransitionAnimationState transitionAnimationState, Object obj, boolean z, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = transitionAnimationState.getValue();
            }
            if ((i & 2) != 0) {
                z = false;
            }
            transitionAnimationState.updateAnimation(obj, z);
        }

        private final void updateAnimation(T initialValue, boolean isInterrupted) {
            FiniteAnimationSpec<T> animationSpec = (!isInterrupted || (getAnimationSpec() instanceof SpringSpec)) ? getAnimationSpec() : this.interruptionSpec;
            setAnimation(new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, getTargetValue(), this.velocityVector));
            this.this$0.onChildAnimationUpdated();
        }

        public final void resetAnimation$animation_core_release() {
            setNeedsReset(true);
        }

        public final void updateTargetValue$animation_core_release(T targetValue, FiniteAnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            if (!Intrinsics.areEqual(getTargetValue(), targetValue) || getNeedsReset()) {
                setTargetValue(targetValue);
                setAnimationSpec(animationSpec);
                updateAnimation$default(this, null, !isFinished$animation_core_release(), 1, null);
                setFinished$animation_core_release(false);
                setOffsetTimeNanos(this.this$0.getPlayTimeNanos());
                setNeedsReset(false);
            }
        }

        public final void updateInitialAndTargetValue$animation_core_release(T initialValue, T targetValue, FiniteAnimationSpec<T> animationSpec) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            setTargetValue(targetValue);
            setAnimationSpec(animationSpec);
            if (Intrinsics.areEqual(getAnimation().getInitialValue(), initialValue) && Intrinsics.areEqual(getAnimation().getTargetValue(), targetValue)) {
                return;
            }
            updateAnimation$default(this, initialValue, false, 2, null);
        }

        private final void setTargetValue(T t) {
            this.targetValue.setValue(t);
        }

        private final void setAnimationSpec(FiniteAnimationSpec<T> finiteAnimationSpec) {
            this.animationSpec.setValue(finiteAnimationSpec);
        }

        private final void setAnimation(TargetBasedAnimation<T, V> targetBasedAnimation) {
            this.animation.setValue(targetBasedAnimation);
        }

        public void setValue$animation_core_release(T t) {
            this.value.setValue(t);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/Transition$SegmentImpl;", ExifInterface.LATITUDE_SOUTH, "Landroidx/compose/animation/core/Transition$Segment;", "initialState", "targetState", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTargetState", "equals", "", "other", "", "hashCode", "", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class SegmentImpl<S> implements Segment<S> {
        private final S initialState;
        private final S targetState;

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getInitialState() {
            return this.initialState;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getTargetState() {
            return this.targetState;
        }

        public SegmentImpl(S s, S s2) {
            this.initialState = s;
            this.targetState = s2;
        }

        public boolean equals(Object other) {
            if (other instanceof Segment) {
                Segment segment = (Segment) other;
                if (Intrinsics.areEqual(getInitialState(), segment.getInitialState()) && Intrinsics.areEqual(getTargetState(), segment.getTargetState())) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            S initialState = getInitialState();
            int iHashCode = (initialState != null ? initialState.hashCode() : 0) * 31;
            S targetState = getTargetState();
            return iHashCode + (targetState != null ? targetState.hashCode() : 0);
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001a\u0010\b\u001a\u00020\t*\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0001H\u0096\u0004¢\u0006\u0002\u0010\nR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Landroidx/compose/animation/core/Transition$Segment;", ExifInterface.LATITUDE_SOUTH, "", "initialState", "getInitialState", "()Ljava/lang/Object;", "targetState", "getTargetState", "isTransitioningTo", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Segment<S> {
        S getInitialState();

        S getTargetState();

        /* compiled from: Transition.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static <S> boolean isTransitioningTo(Segment<S> segment, S s, S s2) {
                return Segment.super.isTransitioningTo(s, s2);
            }
        }

        default boolean isTransitioningTo(S s, S s2) {
            return Intrinsics.areEqual(s, getInitialState()) && Intrinsics.areEqual(s2, getTargetState());
        }
    }

    /* compiled from: Transition.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\u00020\u0004:\u0001&B#\b\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJT\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u00192#\u0010\u001a\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001d0\u001b¢\u0006\u0002\b\u001e2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00028\u00010\u001bJ\r\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%R{\u0010\r\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000bR\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\f2.\u0010\n\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000bR\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\f8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "<set-?>", "Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "Landroidx/compose/animation/core/Transition;", "data", "getData$animation_core_release", "()Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "setData$animation_core_release", "(Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;)V", "data$delegate", "Landroidx/compose/runtime/MutableState;", "getLabel", "()Ljava/lang/String;", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "animate", "Landroidx/compose/runtime/State;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", "state", "setupSeeking", "", "setupSeeking$animation_core_release", "DeferredAnimationData", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class DeferredAnimation<T, V extends AnimationVector> {

        /* renamed from: data$delegate, reason: from kotlin metadata */
        private final MutableState data;
        private final String label;
        final /* synthetic */ Transition<S> this$0;
        private final TwoWayConverter<T, V> typeConverter;

        public final String getLabel() {
            return this.label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public DeferredAnimation(Transition transition, TwoWayConverter<T, V> typeConverter, String label) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(label, "label");
            this.this$0 = transition;
            this.typeConverter = typeConverter;
            this.label = label;
            this.data = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        }

        public final Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> getData$animation_core_release() {
            return (DeferredAnimationData) this.data.getValue();
        }

        /* compiled from: Transition.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u0000*\u0004\b\u0003\u0010\u0001*\b\b\u0004\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004Bk\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\f\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\t¢\u0006\u0002\u0010\u0011J\u0014\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\nR'\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R5\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R7\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "animation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", "state", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getAnimation", "()Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "getTargetValueByState", "()Lkotlin/jvm/functions/Function1;", "setTargetValueByState", "(Lkotlin/jvm/functions/Function1;)V", "getTransitionSpec", "setTransitionSpec", "value", "getValue", "()Ljava/lang/Object;", "updateAnimationStates", "", "segment", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public final class DeferredAnimationData<T, V extends AnimationVector> implements State<T> {
            private final Transition<S>.TransitionAnimationState<T, V> animation;
            private Function1<? super S, ? extends T> targetValueByState;
            final /* synthetic */ Transition<S>.DeferredAnimation<T, V> this$0;
            private Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec;

            public final Transition<S>.TransitionAnimationState<T, V> getAnimation() {
                return this.animation;
            }

            public final Function1<S, T> getTargetValueByState() {
                return this.targetValueByState;
            }

            public final Function1<Segment<S>, FiniteAnimationSpec<T>> getTransitionSpec() {
                return this.transitionSpec;
            }

            public final void setTargetValueByState(Function1<? super S, ? extends T> function1) {
                Intrinsics.checkNotNullParameter(function1, "<set-?>");
                this.targetValueByState = function1;
            }

            public final void setTransitionSpec(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> function1) {
                Intrinsics.checkNotNullParameter(function1, "<set-?>");
                this.transitionSpec = function1;
            }

            public DeferredAnimationData(DeferredAnimation deferredAnimation, Transition<S>.TransitionAnimationState<T, V> animation, Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, Function1<? super S, ? extends T> targetValueByState) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
                Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
                this.this$0 = deferredAnimation;
                this.animation = animation;
                this.transitionSpec = transitionSpec;
                this.targetValueByState = targetValueByState;
            }

            public final void updateAnimationStates(Segment<S> segment) {
                Intrinsics.checkNotNullParameter(segment, "segment");
                T tInvoke = this.targetValueByState.invoke(segment.getTargetState());
                if (this.this$0.this$0.isSeeking()) {
                    this.animation.updateInitialAndTargetValue$animation_core_release(this.targetValueByState.invoke(segment.getInitialState()), tInvoke, this.transitionSpec.invoke(segment));
                } else {
                    this.animation.updateTargetValue$animation_core_release(tInvoke, this.transitionSpec.invoke(segment));
                }
            }

            @Override // androidx.compose.runtime.State
            public T getValue() {
                updateAnimationStates(this.this$0.this$0.getSegment());
                return this.animation.getValue();
            }
        }

        public final State<T> animate(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, Function1<? super S, ? extends T> targetValueByState) {
            Intrinsics.checkNotNullParameter(transitionSpec, "transitionSpec");
            Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core_release = getData$animation_core_release();
            if (data$animation_core_release == null) {
                Transition<S> transition = this.this$0;
                data$animation_core_release = new DeferredAnimationData<>(this, new TransitionAnimationState(transition, targetValueByState.invoke(transition.getCurrentState()), AnimationStateKt.createZeroVectorFrom(this.typeConverter, targetValueByState.invoke(this.this$0.getCurrentState())), this.typeConverter, this.label), transitionSpec, targetValueByState);
                Transition<S> transition2 = this.this$0;
                setData$animation_core_release(data$animation_core_release);
                transition2.addAnimation$animation_core_release(data$animation_core_release.getAnimation());
            }
            Transition<S> transition3 = this.this$0;
            data$animation_core_release.setTargetValueByState(targetValueByState);
            data$animation_core_release.setTransitionSpec(transitionSpec);
            data$animation_core_release.updateAnimationStates(transition3.getSegment());
            return data$animation_core_release;
        }

        public final void setupSeeking$animation_core_release() {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core_release = getData$animation_core_release();
            if (data$animation_core_release != null) {
                Transition<S> transition = this.this$0;
                data$animation_core_release.getAnimation().updateInitialAndTargetValue$animation_core_release(data$animation_core_release.getTargetValueByState().invoke(transition.getSegment().getInitialState()), data$animation_core_release.getTargetValueByState().invoke(transition.getSegment().getTargetState()), data$animation_core_release.getTransitionSpec().invoke(transition.getSegment()));
            }
        }

        public final void setData$animation_core_release(Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData) {
            this.data.setValue(deferredAnimationData);
        }
    }

    public final void removeAnimation$animation_core_release(Transition<S>.DeferredAnimation<?, ?> deferredAnimation) {
        Transition<S>.TransitionAnimationState<?, ?> animation;
        Intrinsics.checkNotNullParameter(deferredAnimation, "deferredAnimation");
        Transition<S>.DeferredAnimationData<?, ?>.DeferredAnimationData<?, V> data$animation_core_release = deferredAnimation.getData$animation_core_release();
        if (data$animation_core_release == 0 || (animation = data$animation_core_release.getAnimation()) == null) {
            return;
        }
        removeAnimation$animation_core_release(animation);
    }

    public final void setTargetState$animation_core_release(S s) {
        this.targetState.setValue(s);
    }

    private final void setSegment(Segment<S> segment) {
        this.segment.setValue(segment);
    }

    public final long getTotalDurationNanos() {
        return ((Number) this.totalDurationNanos.getValue()).longValue();
    }
}
