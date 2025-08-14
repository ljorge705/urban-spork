package androidx.window.area;

import android.app.Activity;
import android.util.Log;
import androidx.window.area.WindowAreaControllerImpl;
import androidx.window.core.BuildConfig;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import io.sentry.cache.EnvelopeCache;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: WindowAreaControllerImpl.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl;", "Landroidx/window/area/WindowAreaController;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Landroidx/window/extensions/area/WindowAreaComponent;)V", "currentStatus", "Landroidx/window/area/WindowAreaStatus;", "rearDisplayMode", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "rearDisplayStatus", "Lkotlinx/coroutines/flow/Flow;", "Companion", "RearDisplaySessionConsumer", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class WindowAreaControllerImpl implements WindowAreaController {
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();
    private WindowAreaStatus currentStatus;
    private final WindowAreaComponent windowAreaComponent;

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent) {
        Intrinsics.checkNotNullParameter(windowAreaComponent, "windowAreaComponent");
        this.windowAreaComponent = windowAreaComponent;
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/area/WindowAreaStatus;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1", f = "WindowAreaControllerImpl.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super WindowAreaStatus>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WindowAreaControllerImpl.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super WindowAreaStatus> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final WindowAreaControllerImpl windowAreaControllerImpl = WindowAreaControllerImpl.this;
                final Consumer consumer = new Consumer() { // from class: androidx.window.area.WindowAreaControllerImpl$rearDisplayStatus$1$$ExternalSyntheticLambda0
                    @Override // androidx.window.extensions.core.util.function.Consumer
                    public final void accept(Object obj2) {
                        WindowAreaControllerImpl.AnonymousClass1.invokeSuspend$lambda$0(windowAreaControllerImpl, producerScope, (Integer) obj2);
                    }
                };
                WindowAreaControllerImpl.this.windowAreaComponent.addRearDisplayStatusListener(consumer);
                final WindowAreaControllerImpl windowAreaControllerImpl2 = WindowAreaControllerImpl.this;
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: androidx.window.area.WindowAreaControllerImpl.rearDisplayStatus.1.1
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
                        windowAreaControllerImpl2.windowAreaComponent.removeRearDisplayStatusListener(consumer);
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, Integer status) {
            WindowAreaAdapter windowAreaAdapter = WindowAreaAdapter.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(status, "status");
            windowAreaControllerImpl.currentStatus = windowAreaAdapter.translate$window_release(status.intValue());
            SendChannel channel = producerScope.getChannel();
            WindowAreaStatus windowAreaStatus = windowAreaControllerImpl.currentStatus;
            if (windowAreaStatus == null) {
                windowAreaStatus = WindowAreaStatus.UNSUPPORTED;
            }
            channel.mo7599trySendJP2dKIU(windowAreaStatus);
        }
    }

    @Override // androidx.window.area.WindowAreaController
    public Flow<WindowAreaStatus> rearDisplayStatus() {
        return FlowKt.distinctUntilChanged(FlowKt.callbackFlow(new AnonymousClass1(null)));
    }

    @Override // androidx.window.area.WindowAreaController
    public void rearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(windowAreaSessionCallback, "windowAreaSessionCallback");
        WindowAreaStatus windowAreaStatus = this.currentStatus;
        if (windowAreaStatus != null && !Intrinsics.areEqual(windowAreaStatus, WindowAreaStatus.AVAILABLE)) {
            throw new UnsupportedOperationException("Rear Display mode cannot be enabled currently");
        }
        this.windowAreaComponent.startRearDisplaySession(activity, new RearDisplaySessionConsumer(executor, windowAreaSessionCallback, this.windowAreaComponent));
    }

    /* compiled from: WindowAreaControllerImpl.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer;", "Landroidx/window/extensions/core/util/function/Consumer;", "", "executor", "Ljava/util/concurrent/Executor;", "appCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "extensionsComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "(Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "Landroidx/window/area/WindowAreaSession;", "accept", "", "t", "onSessionFinished", "onSessionStarted", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RearDisplaySessionConsumer implements Consumer<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;

        public RearDisplaySessionConsumer(Executor executor, WindowAreaSessionCallback appCallback, WindowAreaComponent extensionsComponent) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Intrinsics.checkNotNullParameter(appCallback, "appCallback");
            Intrinsics.checkNotNullParameter(extensionsComponent, "extensionsComponent");
            this.executor = executor;
            this.appCallback = appCallback;
            this.extensionsComponent = extensionsComponent;
        }

        @Override // androidx.window.extensions.core.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }

        public void accept(int t) {
            if (t == 0) {
                onSessionFinished();
            } else {
                if (t == 1) {
                    onSessionStarted();
                    return;
                }
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                    Log.d(WindowAreaControllerImpl.TAG, "Received an unknown session status value: " + t);
                }
                onSessionFinished();
            }
        }

        private final void onSessionStarted() {
            final RearDisplaySessionImpl rearDisplaySessionImpl = new RearDisplaySessionImpl(this.extensionsComponent);
            this.session = rearDisplaySessionImpl;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionStarted$lambda$1$lambda$0(this.f$0, rearDisplaySessionImpl);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionStarted$lambda$1$lambda$0(RearDisplaySessionConsumer this$0, WindowAreaSession it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "$it");
            this$0.appCallback.onSessionStarted(it);
        }

        private final void onSessionFinished() {
            this.session = null;
            this.executor.execute(new Runnable() { // from class: androidx.window.area.WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionFinished$lambda$2(this.f$0);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSessionFinished$lambda$2(RearDisplaySessionConsumer this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.appCallback.onSessionEnded();
        }
    }
}
