package com.facebook.react.internal.featureflags;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNativeFeatureFlagsCxxAccessor.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0004H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016J\b\u0010\u001c\u001a\u00020\u0004H\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0004H\u0016R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\""}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsCxxAccessor;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "()V", "androidEnablePendingFabricTransactionsCache", "", "Ljava/lang/Boolean;", "batchRenderingUpdatesInEventLoopCache", "commonTestFlagCache", "destroyFabricSurfacesInReactInstanceManagerCache", "enableBackgroundExecutorCache", "enableCustomDrawOrderFabricCache", "enableFixForClippedSubviewsCrashCache", "enableMicrotasksCache", "enableSpannableBuildingUnificationCache", "inspectorEnableCxxInspectorPackagerConnectionCache", "inspectorEnableModernCDPRegistryCache", "useModernRuntimeSchedulerCache", "androidEnablePendingFabricTransactions", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "dangerouslyReset", "", "destroyFabricSurfacesInReactInstanceManager", "enableBackgroundExecutor", "enableCustomDrawOrderFabric", "enableFixForClippedSubviewsCrash", "enableMicrotasks", "enableSpannableBuildingUnification", "inspectorEnableCxxInspectorPackagerConnection", "inspectorEnableModernCDPRegistry", "override", "provider", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "useModernRuntimeScheduler", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ReactNativeFeatureFlagsCxxAccessor implements ReactNativeFeatureFlagsAccessor {
    private Boolean androidEnablePendingFabricTransactionsCache;
    private Boolean batchRenderingUpdatesInEventLoopCache;
    private Boolean commonTestFlagCache;
    private Boolean destroyFabricSurfacesInReactInstanceManagerCache;
    private Boolean enableBackgroundExecutorCache;
    private Boolean enableCustomDrawOrderFabricCache;
    private Boolean enableFixForClippedSubviewsCrashCache;
    private Boolean enableMicrotasksCache;
    private Boolean enableSpannableBuildingUnificationCache;
    private Boolean inspectorEnableCxxInspectorPackagerConnectionCache;
    private Boolean inspectorEnableModernCDPRegistryCache;
    private Boolean useModernRuntimeSchedulerCache;

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean commonTestFlag() {
        Boolean boolValueOf = this.commonTestFlagCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.commonTestFlag());
            this.commonTestFlagCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean androidEnablePendingFabricTransactions() {
        Boolean boolValueOf = this.androidEnablePendingFabricTransactionsCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.androidEnablePendingFabricTransactions());
            this.androidEnablePendingFabricTransactionsCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean batchRenderingUpdatesInEventLoop() {
        Boolean boolValueOf = this.batchRenderingUpdatesInEventLoopCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.batchRenderingUpdatesInEventLoop());
            this.batchRenderingUpdatesInEventLoopCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean destroyFabricSurfacesInReactInstanceManager() {
        Boolean boolValueOf = this.destroyFabricSurfacesInReactInstanceManagerCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.destroyFabricSurfacesInReactInstanceManager());
            this.destroyFabricSurfacesInReactInstanceManagerCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableBackgroundExecutor() {
        Boolean boolValueOf = this.enableBackgroundExecutorCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableBackgroundExecutor());
            this.enableBackgroundExecutorCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useModernRuntimeScheduler() {
        Boolean boolValueOf = this.useModernRuntimeSchedulerCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useModernRuntimeScheduler());
            this.useModernRuntimeSchedulerCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableMicrotasks() {
        Boolean boolValueOf = this.enableMicrotasksCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableMicrotasks());
            this.enableMicrotasksCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableSpannableBuildingUnification() {
        Boolean boolValueOf = this.enableSpannableBuildingUnificationCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableSpannableBuildingUnification());
            this.enableSpannableBuildingUnificationCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableCustomDrawOrderFabric() {
        Boolean boolValueOf = this.enableCustomDrawOrderFabricCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableCustomDrawOrderFabric());
            this.enableCustomDrawOrderFabricCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFixForClippedSubviewsCrash() {
        Boolean boolValueOf = this.enableFixForClippedSubviewsCrashCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableFixForClippedSubviewsCrash());
            this.enableFixForClippedSubviewsCrashCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean inspectorEnableCxxInspectorPackagerConnection() {
        Boolean boolValueOf = this.inspectorEnableCxxInspectorPackagerConnectionCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.inspectorEnableCxxInspectorPackagerConnection());
            this.inspectorEnableCxxInspectorPackagerConnectionCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean inspectorEnableModernCDPRegistry() {
        Boolean boolValueOf = this.inspectorEnableModernCDPRegistryCache;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.inspectorEnableModernCDPRegistry());
            this.inspectorEnableModernCDPRegistryCache = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void override(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        ReactNativeFeatureFlagsCxxInterop.override(provider);
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsAccessor
    public void dangerouslyReset() {
        ReactNativeFeatureFlagsCxxInterop.dangerouslyReset();
    }
}
