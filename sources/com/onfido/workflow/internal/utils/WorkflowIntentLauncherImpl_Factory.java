package com.onfido.workflow.internal.utils;

import android.content.Context;
import androidx.activity.result.ActivityResultRegistry;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class WorkflowIntentLauncherImpl_Factory {
    private final Provider<Context> contextProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public WorkflowIntentLauncherImpl_Factory(Provider<Context> provider, Provider<Navigator> provider2, Provider<OnfidoConfig> provider3) {
        this.contextProvider = provider;
        this.navigatorProvider = provider2;
        this.onfidoConfigProvider = provider3;
    }

    public WorkflowIntentLauncherImpl get(ActivityResultRegistry activityResultRegistry) {
        return newInstance(this.contextProvider.get(), this.navigatorProvider.get(), this.onfidoConfigProvider.get(), activityResultRegistry);
    }

    public static WorkflowIntentLauncherImpl_Factory create(Provider<Context> provider, Provider<Navigator> provider2, Provider<OnfidoConfig> provider3) {
        return new WorkflowIntentLauncherImpl_Factory(provider, provider2, provider3);
    }

    public static WorkflowIntentLauncherImpl newInstance(Context context, Navigator navigator, OnfidoConfig onfidoConfig, ActivityResultRegistry activityResultRegistry) {
        return new WorkflowIntentLauncherImpl(context, navigator, onfidoConfig, activityResultRegistry);
    }
}
