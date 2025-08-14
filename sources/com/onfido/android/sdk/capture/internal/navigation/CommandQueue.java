package com.onfido.android.sdk.capture.internal.navigation;

import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000e\"\u00020\nH\u0007¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/CommandQueue;", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "mainExecutor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/NavigationManager;", "pendingCommandQueue", "Lkotlin/collections/ArrayDeque;", "", "Lcom/onfido/android/sdk/capture/internal/navigation/NavigationCommand;", "executeCommands", "", "navigationCommands", "", "([Lcom/onfido/android/sdk/capture/internal/navigation/NavigationCommand;)V", "resetNavigationManager", "setNavigationManager", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CommandQueue implements NavigationManagerHolder {
    private final Executor mainExecutor;
    private NavigationManager navigationManager;
    private final ArrayDeque<List<NavigationCommand>> pendingCommandQueue;

    public CommandQueue(Executor mainExecutor) {
        Intrinsics.checkNotNullParameter(mainExecutor, "mainExecutor");
        this.mainExecutor = mainExecutor;
        this.pendingCommandQueue = new ArrayDeque<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeCommands$lambda$0(CommandQueue this$0, NavigationCommand[] navigationCommands) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(navigationCommands, "$navigationCommands");
        NavigationManager navigationManager = this$0.navigationManager;
        if (navigationManager == null) {
            this$0.pendingCommandQueue.addLast(ArraysKt.toList(navigationCommands));
        } else if (navigationManager != null) {
            navigationManager.executeCommands(ArraysKt.toList(navigationCommands));
        }
    }

    public final void executeCommands(final NavigationCommand... navigationCommands) {
        Intrinsics.checkNotNullParameter(navigationCommands, "navigationCommands");
        this.mainExecutor.execute(new Runnable() { // from class: com.onfido.android.sdk.capture.internal.navigation.CommandQueue$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CommandQueue.executeCommands$lambda$0(this.f$0, navigationCommands);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder
    public void resetNavigationManager() {
        this.navigationManager = null;
    }

    @Override // com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder
    public void setNavigationManager(NavigationManager navigationManager) {
        Intrinsics.checkNotNullParameter(navigationManager, "navigationManager");
        while (!this.pendingCommandQueue.isEmpty()) {
            navigationManager.executeCommands(this.pendingCommandQueue.removeFirst());
        }
        this.navigationManager = navigationManager;
    }
}
