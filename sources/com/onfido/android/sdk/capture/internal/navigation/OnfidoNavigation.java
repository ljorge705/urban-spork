package com.onfido.android.sdk.capture.internal.navigation;

import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/OnfidoNavigation;", "", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "commandQueue", "Lcom/onfido/android/sdk/capture/internal/navigation/CommandQueue;", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "getNavigator", "()Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoNavigation {
    private final CommandQueue commandQueue;
    private final Navigator navigator;

    public OnfidoNavigation(final SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        CommandQueue commandQueue = new CommandQueue(new Executor() { // from class: com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                OnfidoNavigation.commandQueue$lambda$0(schedulersProvider, runnable);
            }
        });
        this.commandQueue = commandQueue;
        this.navigator = new Navigator(commandQueue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void commandQueue$lambda$0(SchedulersProvider schedulersProvider, Runnable runnable) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "$schedulersProvider");
        schedulersProvider.getUi().scheduleDirect(runnable);
    }

    public final NavigationManagerHolder getNavigationManagerHolder() {
        return this.commandQueue;
    }

    public final Navigator getNavigator() {
        return this.navigator;
    }
}
