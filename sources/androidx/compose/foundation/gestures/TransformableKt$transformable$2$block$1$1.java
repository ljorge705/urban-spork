package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TransformEvent;
import androidx.compose.runtime.State;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.drew.metadata.exif.makernotes.KodakMakernoteDirectory;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;

/* compiled from: Transformable.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$2$block$1$1", f = "Transformable.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TransformableKt$transformable$2$block$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<TransformEvent> $channel;
    final /* synthetic */ State<Boolean> $updatePanZoomLock;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TransformableKt$transformable$2$block$1$1(State<Boolean> state, Channel<TransformEvent> channel, Continuation<? super TransformableKt$transformable$2$block$1$1> continuation) {
        super(2, continuation);
        this.$updatePanZoomLock = state;
        this.$channel = channel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableKt$transformable$2$block$1$1 transformableKt$transformable$2$block$1$1 = new TransformableKt$transformable$2$block$1$1(this.$updatePanZoomLock, this.$channel, continuation);
        transformableKt$transformable$2$block$1$1.L$0 = obj;
        return transformableKt$transformable$2$block$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((TransformableKt$transformable$2$block$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: Transformable.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$2$block$1$1$1", f = "Transformable.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$2$block$1$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$null;
        final /* synthetic */ Channel<TransformEvent> $channel;
        final /* synthetic */ State<Boolean> $updatePanZoomLock;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PointerInputScope pointerInputScope, State<Boolean> state, Channel<TransformEvent> channel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$null = pointerInputScope;
            this.$updatePanZoomLock = state;
            this.$channel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$null, this.$updatePanZoomLock, this.$channel, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: Transformable.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$2$block$1$1$1$1", f = "Transformable.kt", i = {}, l = {KodakMakernoteDirectory.TAG_ISO_SETTING}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$2$block$1$1$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00271 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Channel<TransformEvent> $channel;
            final /* synthetic */ State<Boolean> $updatePanZoomLock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00271(State<Boolean> state, Channel<TransformEvent> channel, CoroutineScope coroutineScope, Continuation<? super C00271> continuation) {
                super(2, continuation);
                this.$updatePanZoomLock = state;
                this.$channel = channel;
                this.$$this$coroutineScope = coroutineScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00271 c00271 = new C00271(this.$updatePanZoomLock, this.$channel, this.$$this$coroutineScope, continuation);
                c00271.L$0 = obj;
                return c00271;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00271) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    try {
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                            this.label = 1;
                            if (TransformableKt.detectZoom(awaitPointerEventScope, this.$updatePanZoomLock, this.$channel, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                    } catch (CancellationException e) {
                        if (!CoroutineScopeKt.isActive(this.$$this$coroutineScope)) {
                            throw e;
                        }
                    }
                    return Unit.INSTANCE;
                } finally {
                    this.$channel.mo7599trySendJP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture(this.$$this$null, new C00271(this.$updatePanZoomLock, this.$channel, coroutineScope, null), this) == coroutine_suspended) {
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new AnonymousClass1((PointerInputScope) this.L$0, this.$updatePanZoomLock, this.$channel, null), this) == coroutine_suspended) {
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
