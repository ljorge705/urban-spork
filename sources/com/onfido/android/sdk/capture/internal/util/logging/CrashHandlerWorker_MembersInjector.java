package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CrashHandlerWorker_MembersInjector implements MembersInjector<CrashHandlerWorker> {
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<RemoteLoggerTree> remoteLoggerTreeProvider;

    public CrashHandlerWorker_MembersInjector(Provider<RemoteLoggerTree> provider, Provider<FlowTracker> provider2) {
        this.remoteLoggerTreeProvider = provider;
        this.flowTrackerProvider = provider2;
    }

    public static MembersInjector<CrashHandlerWorker> create(Provider<RemoteLoggerTree> provider, Provider<FlowTracker> provider2) {
        return new CrashHandlerWorker_MembersInjector(provider, provider2);
    }

    public static void injectFlowTracker(CrashHandlerWorker crashHandlerWorker, FlowTracker flowTracker) {
        crashHandlerWorker.flowTracker = flowTracker;
    }

    public static void injectRemoteLoggerTree(CrashHandlerWorker crashHandlerWorker, RemoteLoggerTree remoteLoggerTree) {
        crashHandlerWorker.remoteLoggerTree = remoteLoggerTree;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(CrashHandlerWorker crashHandlerWorker) {
        injectRemoteLoggerTree(crashHandlerWorker, this.remoteLoggerTreeProvider.get());
        injectFlowTracker(crashHandlerWorker, this.flowTrackerProvider.get());
    }
}
