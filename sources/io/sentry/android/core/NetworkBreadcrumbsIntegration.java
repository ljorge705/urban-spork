package io.sentry.android.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.clevertap.android.sdk.Constants;
import io.sentry.Breadcrumb;
import io.sentry.DateUtils;
import io.sentry.Hint;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.Integration;
import io.sentry.SentryDateProvider;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.AndroidConnectionStatusProvider;
import io.sentry.util.IntegrationUtils;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes6.dex */
public final class NetworkBreadcrumbsIntegration implements Integration, Closeable {
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private volatile boolean isClosed;
    private final Object lock = new Object();
    private final ILogger logger;
    volatile NetworkBreadcrumbsNetworkCallback networkCallback;
    private SentryOptions options;

    public NetworkBreadcrumbsIntegration(Context context, BuildInfoProvider buildInfoProvider, ILogger iLogger) {
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "Context is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "ILogger is required");
    }

    @Override // io.sentry.Integration
    public void register(final IHub iHub, final SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.logger.log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
        this.options = sentryOptions;
        if (sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()) {
            if (this.buildInfoProvider.getSdkInfoVersion() < 24) {
                this.logger.log(SentryLevel.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
                return;
            }
            try {
                sentryOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.NetworkBreadcrumbsIntegration.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NetworkBreadcrumbsIntegration.this.isClosed) {
                            return;
                        }
                        synchronized (NetworkBreadcrumbsIntegration.this.lock) {
                            NetworkBreadcrumbsIntegration.this.networkCallback = new NetworkBreadcrumbsNetworkCallback(iHub, NetworkBreadcrumbsIntegration.this.buildInfoProvider, sentryOptions.getDateProvider());
                            if (AndroidConnectionStatusProvider.registerNetworkCallback(NetworkBreadcrumbsIntegration.this.context, NetworkBreadcrumbsIntegration.this.logger, NetworkBreadcrumbsIntegration.this.buildInfoProvider, NetworkBreadcrumbsIntegration.this.networkCallback)) {
                                NetworkBreadcrumbsIntegration.this.logger.log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration installed.", new Object[0]);
                                IntegrationUtils.addIntegrationToSdkVersion("NetworkBreadcrumbs");
                            } else {
                                NetworkBreadcrumbsIntegration.this.logger.log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration not installed.", new Object[0]);
                            }
                        }
                    }
                });
            } catch (Throwable th) {
                this.logger.log(SentryLevel.ERROR, "Error submitting NetworkBreadcrumbsIntegration task.", th);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.isClosed = true;
        try {
            ((SentryOptions) Objects.requireNonNull(this.options, "Options is required")).getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.NetworkBreadcrumbsIntegration$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m6022xbbc7aaa6();
                }
            });
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error submitting NetworkBreadcrumbsIntegration task.", th);
        }
    }

    /* renamed from: lambda$close$0$io-sentry-android-core-NetworkBreadcrumbsIntegration, reason: not valid java name */
    /* synthetic */ void m6022xbbc7aaa6() {
        synchronized (this.lock) {
            if (this.networkCallback != null) {
                AndroidConnectionStatusProvider.unregisterNetworkCallback(this.context, this.logger, this.buildInfoProvider, this.networkCallback);
                this.logger.log(SentryLevel.DEBUG, "NetworkBreadcrumbsIntegration removed.", new Object[0]);
            }
            this.networkCallback = null;
        }
    }

    static final class NetworkBreadcrumbsNetworkCallback extends ConnectivityManager.NetworkCallback {
        final BuildInfoProvider buildInfoProvider;
        final SentryDateProvider dateProvider;
        final IHub hub;
        Network currentNetwork = null;
        NetworkCapabilities lastCapabilities = null;
        long lastCapabilityNanos = 0;

        NetworkBreadcrumbsNetworkCallback(IHub iHub, BuildInfoProvider buildInfoProvider, SentryDateProvider sentryDateProvider) {
            this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
            this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
            this.dateProvider = (SentryDateProvider) Objects.requireNonNull(sentryDateProvider, "SentryDateProvider is required");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            if (network.equals(this.currentNetwork)) {
                return;
            }
            this.hub.addBreadcrumb(createBreadcrumb("NETWORK_AVAILABLE"));
            this.currentNetwork = network;
            this.lastCapabilities = null;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (network.equals(this.currentNetwork)) {
                long jNanoTimestamp = this.dateProvider.now().nanoTimestamp();
                NetworkBreadcrumbConnectionDetail newConnectionDetails = getNewConnectionDetails(this.lastCapabilities, networkCapabilities, this.lastCapabilityNanos, jNanoTimestamp);
                if (newConnectionDetails == null) {
                    return;
                }
                this.lastCapabilities = networkCapabilities;
                this.lastCapabilityNanos = jNanoTimestamp;
                Breadcrumb breadcrumbCreateBreadcrumb = createBreadcrumb("NETWORK_CAPABILITIES_CHANGED");
                breadcrumbCreateBreadcrumb.setData("download_bandwidth", Integer.valueOf(newConnectionDetails.downBandwidth));
                breadcrumbCreateBreadcrumb.setData("upload_bandwidth", Integer.valueOf(newConnectionDetails.upBandwidth));
                breadcrumbCreateBreadcrumb.setData("vpn_active", Boolean.valueOf(newConnectionDetails.isVpn));
                breadcrumbCreateBreadcrumb.setData("network_type", newConnectionDetails.type);
                if (newConnectionDetails.signalStrength != 0) {
                    breadcrumbCreateBreadcrumb.setData("signal_strength", Integer.valueOf(newConnectionDetails.signalStrength));
                }
                Hint hint = new Hint();
                hint.set(TypeCheckHint.ANDROID_NETWORK_CAPABILITIES, newConnectionDetails);
                this.hub.addBreadcrumb(breadcrumbCreateBreadcrumb, hint);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            if (network.equals(this.currentNetwork)) {
                this.hub.addBreadcrumb(createBreadcrumb("NETWORK_LOST"));
                this.currentNetwork = null;
                this.lastCapabilities = null;
            }
        }

        private Breadcrumb createBreadcrumb(String str) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setType("system");
            breadcrumb.setCategory("network.event");
            breadcrumb.setData(Constants.KEY_ACTION, str);
            breadcrumb.setLevel(SentryLevel.INFO);
            return breadcrumb;
        }

        private NetworkBreadcrumbConnectionDetail getNewConnectionDetails(NetworkCapabilities networkCapabilities, NetworkCapabilities networkCapabilities2, long j, long j2) {
            if (networkCapabilities == null) {
                return new NetworkBreadcrumbConnectionDetail(networkCapabilities2, this.buildInfoProvider, j2);
            }
            NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail = new NetworkBreadcrumbConnectionDetail(networkCapabilities, this.buildInfoProvider, j);
            NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail2 = new NetworkBreadcrumbConnectionDetail(networkCapabilities2, this.buildInfoProvider, j2);
            if (networkBreadcrumbConnectionDetail.isSimilar(networkBreadcrumbConnectionDetail2)) {
                return null;
            }
            return networkBreadcrumbConnectionDetail2;
        }
    }

    static class NetworkBreadcrumbConnectionDetail {
        final int downBandwidth;
        final boolean isVpn;
        final int signalStrength;
        private long timestampNanos;
        final String type;
        final int upBandwidth;

        NetworkBreadcrumbConnectionDetail(NetworkCapabilities networkCapabilities, BuildInfoProvider buildInfoProvider, long j) {
            Objects.requireNonNull(networkCapabilities, "NetworkCapabilities is required");
            Objects.requireNonNull(buildInfoProvider, "BuildInfoProvider is required");
            this.downBandwidth = networkCapabilities.getLinkDownstreamBandwidthKbps();
            this.upBandwidth = networkCapabilities.getLinkUpstreamBandwidthKbps();
            int signalStrength = buildInfoProvider.getSdkInfoVersion() >= 29 ? networkCapabilities.getSignalStrength() : 0;
            this.signalStrength = signalStrength > -100 ? signalStrength : 0;
            this.isVpn = networkCapabilities.hasTransport(4);
            String connectionType = AndroidConnectionStatusProvider.getConnectionType(networkCapabilities, buildInfoProvider);
            this.type = connectionType == null ? "" : connectionType;
            this.timestampNanos = j;
        }

        boolean isSimilar(NetworkBreadcrumbConnectionDetail networkBreadcrumbConnectionDetail) {
            int iAbs = Math.abs(this.signalStrength - networkBreadcrumbConnectionDetail.signalStrength);
            int iAbs2 = Math.abs(this.downBandwidth - networkBreadcrumbConnectionDetail.downBandwidth);
            int iAbs3 = Math.abs(this.upBandwidth - networkBreadcrumbConnectionDetail.upBandwidth);
            boolean z = DateUtils.nanosToMillis((double) Math.abs(this.timestampNanos - networkBreadcrumbConnectionDetail.timestampNanos)) < 5000.0d;
            return this.isVpn == networkBreadcrumbConnectionDetail.isVpn && this.type.equals(networkBreadcrumbConnectionDetail.type) && (z || iAbs <= 5) && (z || (((double) iAbs2) > Math.max(1000.0d, ((double) Math.abs(this.downBandwidth)) * 0.1d) ? 1 : (((double) iAbs2) == Math.max(1000.0d, ((double) Math.abs(this.downBandwidth)) * 0.1d) ? 0 : -1)) <= 0) && (z || (((double) iAbs3) > Math.max(1000.0d, ((double) Math.abs(this.upBandwidth)) * 0.1d) ? 1 : (((double) iAbs3) == Math.max(1000.0d, ((double) Math.abs(this.upBandwidth)) * 0.1d) ? 0 : -1)) <= 0);
        }
    }
}
