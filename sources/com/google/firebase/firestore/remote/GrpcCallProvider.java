package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firestore.v1.FirestoreGrpc;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.MethodDescriptor;
import io.grpc.android.AndroidChannelBuilder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class GrpcCallProvider {
    private static final int CONNECTIVITY_ATTEMPT_TIMEOUT_MS = 15000;
    private static final String LOG_TAG = "GrpcCallProvider";
    private static Supplier<ManagedChannelBuilder<?>> overrideChannelBuilderSupplier;
    private final AsyncQueue asyncQueue;
    private CallOptions callOptions;
    private Task<ManagedChannel> channelTask;
    private AsyncQueue.DelayedTask connectivityAttemptTimer;
    private final Context context;
    private final DatabaseInfo databaseInfo;
    private final CallCredentials firestoreHeaders;

    GrpcCallProvider(AsyncQueue asyncQueue, Context context, DatabaseInfo databaseInfo, CallCredentials callCredentials) {
        this.asyncQueue = asyncQueue;
        this.context = context;
        this.databaseInfo = databaseInfo;
        this.firestoreHeaders = callCredentials;
        initChannelTask();
    }

    private ManagedChannel initChannel(Context context, DatabaseInfo databaseInfo) {
        ManagedChannelBuilder<?> managedChannelBuilder;
        try {
            ProviderInstaller.installIfNeeded(context);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IllegalStateException e) {
            Logger.warn(LOG_TAG, "Failed to update ssl context: %s", e);
        }
        Supplier<ManagedChannelBuilder<?>> supplier = overrideChannelBuilderSupplier;
        if (supplier != null) {
            managedChannelBuilder = supplier.get();
        } else {
            ManagedChannelBuilder<?> managedChannelBuilderForTarget = ManagedChannelBuilder.forTarget(databaseInfo.getHost());
            if (!databaseInfo.isSslEnabled()) {
                managedChannelBuilderForTarget.usePlaintext();
            }
            managedChannelBuilder = managedChannelBuilderForTarget;
        }
        managedChannelBuilder.keepAliveTime(30L, TimeUnit.SECONDS);
        return AndroidChannelBuilder.usingBuilder(managedChannelBuilder).context(context).build();
    }

    <ReqT, RespT> Task<ClientCall<ReqT, RespT>> createClientCall(final MethodDescriptor<ReqT, RespT> methodDescriptor) {
        return (Task<ClientCall<ReqT, RespT>>) this.channelTask.continueWithTask(this.asyncQueue.getExecutor(), new Continuation() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m5344x1673c39e(methodDescriptor, task);
            }
        });
    }

    /* renamed from: lambda$createClientCall$0$com-google-firebase-firestore-remote-GrpcCallProvider, reason: not valid java name */
    /* synthetic */ Task m5344x1673c39e(MethodDescriptor methodDescriptor, Task task) throws Exception {
        return Tasks.forResult(((ManagedChannel) task.getResult()).newCall(methodDescriptor, this.callOptions));
    }

    void shutdown() {
        try {
            ManagedChannel managedChannel = (ManagedChannel) Tasks.await(this.channelTask);
            managedChannel.shutdown();
            try {
                if (managedChannel.awaitTermination(1L, TimeUnit.SECONDS)) {
                    return;
                }
                Logger.debug("FirestoreChannel", "Unable to gracefully shutdown the gRPC ManagedChannel. Will attempt an immediate shutdown.", new Object[0]);
                managedChannel.shutdownNow();
                if (managedChannel.awaitTermination(60L, TimeUnit.SECONDS)) {
                    return;
                }
                Logger.warn("FirestoreChannel", "Unable to forcefully shutdown the gRPC ManagedChannel.", new Object[0]);
            } catch (InterruptedException unused) {
                managedChannel.shutdownNow();
                Logger.warn("FirestoreChannel", "Interrupted while shutting down the gRPC Managed Channel", new Object[0]);
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException unused2) {
            Logger.warn("FirestoreChannel", "Interrupted while retrieving the gRPC Managed Channel", new Object[0]);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Logger.warn("FirestoreChannel", "Channel is not initialized, shutdown will just do nothing. Channel initializing run into exception: %s", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onConnectivityStateChange, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m5348x2cfc29ec(final ManagedChannel managedChannel) {
        ConnectivityState state = managedChannel.getState(true);
        Logger.debug(LOG_TAG, "Current gRPC connectivity state: " + state, new Object[0]);
        clearConnectivityAttemptTimer();
        if (state == ConnectivityState.CONNECTING) {
            Logger.debug(LOG_TAG, "Setting the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.CONNECTIVITY_ATTEMPT_TIMER, 15000L, new Runnable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5347x3a7d4ab(managedChannel);
                }
            });
        }
        managedChannel.notifyWhenStateChanged(state, new Runnable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5349x56507f2d(managedChannel);
            }
        });
    }

    /* renamed from: lambda$onConnectivityStateChange$1$com-google-firebase-firestore-remote-GrpcCallProvider, reason: not valid java name */
    /* synthetic */ void m5347x3a7d4ab(ManagedChannel managedChannel) {
        Logger.debug(LOG_TAG, "connectivityAttemptTimer elapsed. Resetting the channel.", new Object[0]);
        clearConnectivityAttemptTimer();
        resetChannel(managedChannel);
    }

    /* renamed from: lambda$onConnectivityStateChange$3$com-google-firebase-firestore-remote-GrpcCallProvider, reason: not valid java name */
    /* synthetic */ void m5349x56507f2d(final ManagedChannel managedChannel) {
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5348x2cfc29ec(managedChannel);
            }
        });
    }

    private void resetChannel(final ManagedChannel managedChannel) {
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5350xfb8fbff1(managedChannel);
            }
        });
    }

    /* renamed from: lambda$resetChannel$4$com-google-firebase-firestore-remote-GrpcCallProvider, reason: not valid java name */
    /* synthetic */ void m5350xfb8fbff1(ManagedChannel managedChannel) {
        managedChannel.shutdownNow();
        initChannelTask();
    }

    private void initChannelTask() {
        this.channelTask = Tasks.call(Executors.BACKGROUND_EXECUTOR, new Callable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m5346xa80a811d();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lambda$initChannelTask$6$com-google-firebase-firestore-remote-GrpcCallProvider, reason: not valid java name */
    /* synthetic */ ManagedChannel m5346xa80a811d() throws Exception {
        final ManagedChannel managedChannelInitChannel = initChannel(this.context, this.databaseInfo);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: com.google.firebase.firestore.remote.GrpcCallProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5345x7eb62bdc(managedChannelInitChannel);
            }
        });
        this.callOptions = ((FirestoreGrpc.FirestoreStub) ((FirestoreGrpc.FirestoreStub) FirestoreGrpc.newStub(managedChannelInitChannel).withCallCredentials(this.firestoreHeaders)).withExecutor(this.asyncQueue.getExecutor())).getCallOptions();
        Logger.debug(LOG_TAG, "Channel successfully reset.", new Object[0]);
        return managedChannelInitChannel;
    }

    private void clearConnectivityAttemptTimer() {
        if (this.connectivityAttemptTimer != null) {
            Logger.debug(LOG_TAG, "Clearing the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer.cancel();
            this.connectivityAttemptTimer = null;
        }
    }
}
