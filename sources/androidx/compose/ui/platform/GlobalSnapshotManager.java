package androidx.compose.ui.platform;

import androidx.compose.runtime.snapshots.Snapshot;
import io.sentry.Session;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: GlobalSnapshotManager.android.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/GlobalSnapshotManager;", "", "()V", Session.JsonKeys.STARTED, "Ljava/util/concurrent/atomic/AtomicBoolean;", "ensureStarted", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class GlobalSnapshotManager {
    public static final GlobalSnapshotManager INSTANCE = new GlobalSnapshotManager();
    private static final AtomicBoolean started = new AtomicBoolean(false);

    private GlobalSnapshotManager() {
    }

    public final void ensureStarted() {
        if (started.compareAndSet(false, true)) {
            final Channel channelChannel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(AndroidUiDispatcher.INSTANCE.getMain()), null, null, new AnonymousClass1(channelChannel$default, null), 3, null);
            Snapshot.INSTANCE.registerGlobalWriteObserver(new Function1<Object, Unit>() { // from class: androidx.compose.ui.platform.GlobalSnapshotManager.ensureStarted.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    channelChannel$default.mo7599trySendJP2dKIU(Unit.INSTANCE);
                }
            });
        }
    }

    /* compiled from: GlobalSnapshotManager.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1", f = "GlobalSnapshotManager.android.kt", i = {0}, l = {63}, m = "invokeSuspend", n = {"$this$consume$iv$iv"}, s = {"L$0"})
    /* renamed from: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Channel<Unit> $channel;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Channel<Unit> channel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$channel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$channel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x003d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x004c A[Catch: all -> 0x0063, TRY_LEAVE, TryCatch #0 {all -> 0x0063, blocks: (B:16:0x0044, B:18:0x004c), top: B:29:0x0044 }] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x003e -> B:29:0x0044). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r6.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r3 = r6.L$0
                kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L66
                r4 = r3
                r3 = r1
                r1 = r0
                r0 = r6
                goto L44
            L1b:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L23:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.channels.Channel<kotlin.Unit> r7 = r6.$channel
                r3 = r7
                kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
                kotlinx.coroutines.channels.ChannelIterator r7 = r3.iterator()     // Catch: java.lang.Throwable -> L66
                r1 = r7
                r7 = r6
            L31:
                r7.L$0 = r3     // Catch: java.lang.Throwable -> L66
                r7.L$1 = r1     // Catch: java.lang.Throwable -> L66
                r7.label = r2     // Catch: java.lang.Throwable -> L66
                java.lang.Object r4 = r1.hasNext(r7)     // Catch: java.lang.Throwable -> L66
                if (r4 != r0) goto L3e
                return r0
            L3e:
                r5 = r0
                r0 = r7
                r7 = r4
                r4 = r3
                r3 = r1
                r1 = r5
            L44:
                java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L63
                boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L63
                if (r7 == 0) goto L5c
                java.lang.Object r7 = r3.next()     // Catch: java.lang.Throwable -> L63
                kotlin.Unit r7 = (kotlin.Unit) r7     // Catch: java.lang.Throwable -> L63
                androidx.compose.runtime.snapshots.Snapshot$Companion r7 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L63
                r7.sendApplyNotifications()     // Catch: java.lang.Throwable -> L63
                r7 = r0
                r0 = r1
                r1 = r3
                r3 = r4
                goto L31
            L5c:
                r7 = 0
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r7)
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            L63:
                r7 = move-exception
                r3 = r4
                goto L67
            L66:
                r7 = move-exception
            L67:
                throw r7     // Catch: java.lang.Throwable -> L68
            L68:
                r0 = move-exception
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r3, r7)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
