package io.sentry.android.core;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IHub;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.DeviceOrientations;
import io.sentry.protocol.Device;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes6.dex */
public final class AppComponentsBreadcrumbsIntegration implements Integration, Closeable, ComponentCallbacks2 {
    private final Context context;
    private IHub hub;
    private SentryAndroidOptions options;

    public AppComponentsBreadcrumbsIntegration(Context context) {
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "Context is required");
    }

    @Override // io.sentry.Integration
    public void register(IHub iHub, SentryOptions sentryOptions) {
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.options.isEnableAppComponentBreadcrumbs()));
        if (this.options.isEnableAppComponentBreadcrumbs()) {
            try {
                this.context.registerComponentCallbacks(this);
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration installed.", new Object[0]);
                IntegrationUtils.addIntegrationToSdkVersion("AppComponentsBreadcrumbs");
            } catch (Throwable th) {
                this.options.setEnableAppComponentBreadcrumbs(false);
                sentryOptions.getLogger().log(SentryLevel.INFO, th, "ComponentCallbacks2 is not available.", new Object[0]);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.context.unregisterComponentCallbacks(this);
        } catch (Throwable th) {
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, th, "It was not possible to unregisterComponentCallbacks", new Object[0]);
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = this.options;
        if (sentryAndroidOptions2 != null) {
            sentryAndroidOptions2.getLogger().log(SentryLevel.DEBUG, "AppComponentsBreadcrumbsIntegration removed.", new Object[0]);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration configuration) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        executeInBackground(new Runnable() { // from class: io.sentry.android.core.AppComponentsBreadcrumbsIntegration$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6014x57f88530(jCurrentTimeMillis, configuration);
            }
        });
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        executeInBackground(new Runnable() { // from class: io.sentry.android.core.AppComponentsBreadcrumbsIntegration$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6015xa628e2d0(jCurrentTimeMillis);
            }
        });
    }

    /* renamed from: lambda$onLowMemory$1$io-sentry-android-core-AppComponentsBreadcrumbsIntegration, reason: not valid java name */
    /* synthetic */ void m6015xa628e2d0(long j) {
        captureLowMemoryBreadcrumb(j, null);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(final int i) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        executeInBackground(new Runnable() { // from class: io.sentry.android.core.AppComponentsBreadcrumbsIntegration$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6016x285db429(jCurrentTimeMillis, i);
            }
        });
    }

    /* renamed from: lambda$onTrimMemory$2$io-sentry-android-core-AppComponentsBreadcrumbsIntegration, reason: not valid java name */
    /* synthetic */ void m6016x285db429(long j, int i) {
        captureLowMemoryBreadcrumb(j, Integer.valueOf(i));
    }

    private void captureLowMemoryBreadcrumb(long j, Integer num) {
        if (this.hub != null) {
            Breadcrumb breadcrumb = new Breadcrumb(j);
            if (num != null) {
                if (num.intValue() < 40) {
                    return;
                } else {
                    breadcrumb.setData("level", num);
                }
            }
            breadcrumb.setType("system");
            breadcrumb.setCategory("device.event");
            breadcrumb.setMessage("Low memory");
            breadcrumb.setData(Constants.KEY_ACTION, "LOW_MEMORY");
            breadcrumb.setLevel(SentryLevel.WARNING);
            this.hub.addBreadcrumb(breadcrumb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: captureConfigurationChangedBreadcrumb, reason: merged with bridge method [inline-methods] */
    public void m6014x57f88530(long j, Configuration configuration) {
        if (this.hub != null) {
            Device.DeviceOrientation orientation = DeviceOrientations.getOrientation(this.context.getResources().getConfiguration().orientation);
            String lowerCase = orientation != null ? orientation.name().toLowerCase(Locale.ROOT) : "undefined";
            Breadcrumb breadcrumb = new Breadcrumb(j);
            breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
            breadcrumb.setCategory("device.orientation");
            breadcrumb.setData(ViewProps.POSITION, lowerCase);
            breadcrumb.setLevel(SentryLevel.INFO);
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_CONFIGURATION, configuration);
            this.hub.addBreadcrumb(breadcrumb, hint);
        }
    }

    private void executeInBackground(Runnable runnable) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(runnable);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to submit app components breadcrumb task", new Object[0]);
            }
        }
    }
}
