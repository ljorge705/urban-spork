package com.onfido.android.sdk.capture.internal.util.environment;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityCheckerImpl;", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "context", "Landroid/content/Context;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "hasEnvironmentIntegrity", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnvironmentIntegrityCheckerImpl implements EnvironmentIntegrityChecker {
    private final Context context;
    private final NativeDetector nativeDetector;
    private final OnfidoRemoteConfig remoteConfig;

    @Inject
    public EnvironmentIntegrityCheckerImpl(Context context, NativeDetector nativeDetector, OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.context = context;
        this.nativeDetector = nativeDetector;
        this.remoteConfig = remoteConfig;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker
    public boolean hasEnvironmentIntegrity() {
        return (this.remoteConfig.isEnvironmentIntegrityCheckEnabled() && this.nativeDetector.isInVirtualEnvironment(this.context)) ? false : true;
    }
}
