package io.sentry.android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.IHub;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.AndroidCurrentDateProvider;
import io.sentry.android.core.internal.util.Debouncer;
import io.sentry.protocol.Device;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import io.sentry.util.StringUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public final class SystemEventsBreadcrumbsIntegration implements Integration, Closeable {
    private final List<String> actions;
    private final Context context;
    private boolean isClosed;
    private SentryAndroidOptions options;
    SystemEventsBroadcastReceiver receiver;
    private final Object startLock;

    public SystemEventsBreadcrumbsIntegration(Context context) {
        this(context, getDefaultActions());
    }

    public SystemEventsBreadcrumbsIntegration(Context context, List<String> list) {
        this.isClosed = false;
        this.startLock = new Object();
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "Context is required");
        this.actions = (List) Objects.requireNonNull(list, "Actions list is required");
    }

    @Override // io.sentry.Integration
    public void register(final IHub iHub, final SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SystemEventsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.options.isEnableSystemEventBreadcrumbs()));
        if (this.options.isEnableSystemEventBreadcrumbs()) {
            try {
                sentryOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.SystemEventsBreadcrumbsIntegration$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m6027x32c8443d(iHub, sentryOptions);
                    }
                });
            } catch (Throwable th) {
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "Failed to start SystemEventsBreadcrumbsIntegration on executor thread.", th);
            }
        }
    }

    /* renamed from: lambda$register$0$io-sentry-android-core-SystemEventsBreadcrumbsIntegration, reason: not valid java name */
    /* synthetic */ void m6027x32c8443d(IHub iHub, SentryOptions sentryOptions) {
        synchronized (this.startLock) {
            if (!this.isClosed) {
                startSystemEventsReceiver(iHub, (SentryAndroidOptions) sentryOptions);
            }
        }
    }

    private void startSystemEventsReceiver(IHub iHub, SentryAndroidOptions sentryAndroidOptions) {
        this.receiver = new SystemEventsBroadcastReceiver(iHub, sentryAndroidOptions);
        IntentFilter intentFilter = new IntentFilter();
        Iterator<String> it = this.actions.iterator();
        while (it.hasNext()) {
            intentFilter.addAction(it.next());
        }
        try {
            ContextUtils.registerReceiver(this.context, sentryAndroidOptions, this.receiver, intentFilter);
            sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SystemEventsBreadcrumbsIntegration installed.", new Object[0]);
            IntegrationUtils.addIntegrationToSdkVersion("SystemEventsBreadcrumbs");
        } catch (Throwable th) {
            sentryAndroidOptions.setEnableSystemEventBreadcrumbs(false);
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed to initialize SystemEventsBreadcrumbsIntegration.", th);
        }
    }

    private static List<String> getDefaultActions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.appwidget.action.APPWIDGET_DELETED");
        arrayList.add("android.appwidget.action.APPWIDGET_DISABLED");
        arrayList.add("android.appwidget.action.APPWIDGET_ENABLED");
        arrayList.add("android.appwidget.action.APPWIDGET_HOST_RESTORED");
        arrayList.add("android.appwidget.action.APPWIDGET_RESTORED");
        arrayList.add("android.appwidget.action.APPWIDGET_UPDATE");
        arrayList.add("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS");
        arrayList.add("android.intent.action.ACTION_POWER_CONNECTED");
        arrayList.add("android.intent.action.ACTION_POWER_DISCONNECTED");
        arrayList.add("android.intent.action.ACTION_SHUTDOWN");
        arrayList.add("android.intent.action.AIRPLANE_MODE");
        arrayList.add("android.intent.action.BATTERY_LOW");
        arrayList.add("android.intent.action.BATTERY_OKAY");
        arrayList.add("android.intent.action.BATTERY_CHANGED");
        arrayList.add("android.intent.action.BOOT_COMPLETED");
        arrayList.add("android.intent.action.CAMERA_BUTTON");
        arrayList.add("android.intent.action.CONFIGURATION_CHANGED");
        arrayList.add("android.intent.action.CONTENT_CHANGED");
        arrayList.add("android.intent.action.DATE_CHANGED");
        arrayList.add("android.intent.action.DEVICE_STORAGE_LOW");
        arrayList.add("android.intent.action.DEVICE_STORAGE_OK");
        arrayList.add("android.intent.action.DOCK_EVENT");
        arrayList.add("android.intent.action.DREAMING_STARTED");
        arrayList.add("android.intent.action.DREAMING_STOPPED");
        arrayList.add("android.intent.action.INPUT_METHOD_CHANGED");
        arrayList.add("android.intent.action.LOCALE_CHANGED");
        arrayList.add("android.intent.action.REBOOT");
        arrayList.add("android.intent.action.SCREEN_OFF");
        arrayList.add("android.intent.action.SCREEN_ON");
        arrayList.add("android.intent.action.TIMEZONE_CHANGED");
        arrayList.add("android.intent.action.TIME_SET");
        arrayList.add("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        arrayList.add("android.os.action.POWER_SAVE_MODE_CHANGED");
        arrayList.add("android.intent.action.APP_ERROR");
        arrayList.add("android.intent.action.BUG_REPORT");
        arrayList.add("android.intent.action.MEDIA_BAD_REMOVAL");
        arrayList.add("android.intent.action.MEDIA_MOUNTED");
        arrayList.add("android.intent.action.MEDIA_UNMOUNTABLE");
        arrayList.add("android.intent.action.MEDIA_UNMOUNTED");
        return arrayList;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.startLock) {
            this.isClosed = true;
        }
        SystemEventsBroadcastReceiver systemEventsBroadcastReceiver = this.receiver;
        if (systemEventsBroadcastReceiver != null) {
            this.context.unregisterReceiver(systemEventsBroadcastReceiver);
            this.receiver = null;
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "SystemEventsBreadcrumbsIntegration remove.", new Object[0]);
            }
        }
    }

    static final class SystemEventsBroadcastReceiver extends BroadcastReceiver {
        private static final long DEBOUNCE_WAIT_TIME_MS = 60000;
        private final Debouncer batteryChangedDebouncer = new Debouncer(AndroidCurrentDateProvider.getInstance(), 60000, 0);
        private final IHub hub;
        private final SentryAndroidOptions options;

        SystemEventsBroadcastReceiver(IHub iHub, SentryAndroidOptions sentryAndroidOptions) {
            this.hub = iHub;
            this.options = sentryAndroidOptions;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            final String action = intent.getAction();
            final boolean zEquals = "android.intent.action.BATTERY_CHANGED".equals(action);
            if (zEquals && this.batteryChangedDebouncer.checkForDebounce()) {
                return;
            }
            final long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                this.options.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m6028x4f6ca208(jCurrentTimeMillis, intent, action, zEquals);
                    }
                });
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to submit system event breadcrumb action.", new Object[0]);
            }
        }

        /* renamed from: lambda$onReceive$0$io-sentry-android-core-SystemEventsBreadcrumbsIntegration$SystemEventsBroadcastReceiver, reason: not valid java name */
        /* synthetic */ void m6028x4f6ca208(long j, Intent intent, String str, boolean z) {
            Breadcrumb breadcrumbCreateBreadcrumb = createBreadcrumb(j, intent, str, z);
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_INTENT, intent);
            this.hub.addBreadcrumb(breadcrumbCreateBreadcrumb, hint);
        }

        private Breadcrumb createBreadcrumb(long j, Intent intent, String str, boolean z) {
            Breadcrumb breadcrumb = new Breadcrumb(j);
            breadcrumb.setType("system");
            breadcrumb.setCategory("device.event");
            String stringAfterDot = StringUtils.getStringAfterDot(str);
            if (stringAfterDot != null) {
                breadcrumb.setData(Constants.KEY_ACTION, stringAfterDot);
            }
            if (z) {
                Float batteryLevel = DeviceInfoUtil.getBatteryLevel(intent, this.options);
                if (batteryLevel != null) {
                    breadcrumb.setData("level", batteryLevel);
                }
                Boolean boolIsCharging = DeviceInfoUtil.isCharging(intent, this.options);
                if (boolIsCharging != null) {
                    breadcrumb.setData(Device.JsonKeys.CHARGING, boolIsCharging);
                }
            } else {
                Bundle extras = intent.getExtras();
                HashMap map = new HashMap();
                if (extras != null && !extras.isEmpty()) {
                    for (String str2 : extras.keySet()) {
                        try {
                            Object obj = extras.get(str2);
                            if (obj != null) {
                                map.put(str2, obj.toString());
                            }
                        } catch (Throwable th) {
                            this.options.getLogger().log(SentryLevel.ERROR, th, "%s key of the %s action threw an error.", str2, str);
                        }
                    }
                    breadcrumb.setData("extras", map);
                }
            }
            breadcrumb.setLevel(SentryLevel.INFO);
            return breadcrumb;
        }
    }
}
