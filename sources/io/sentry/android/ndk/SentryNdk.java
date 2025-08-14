package io.sentry.android.ndk;

import com.onfido.android.sdk.capture.internal.util.logging.OnfidoLogMapper;
import io.sentry.android.core.SentryAndroidOptions;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public final class SentryNdk {
    private static final CountDownLatch loadLibraryLatch = new CountDownLatch(1);

    private static native void initSentryNative(SentryAndroidOptions sentryAndroidOptions);

    private static native void shutdown();

    static {
        new Thread(new Runnable() { // from class: io.sentry.android.ndk.SentryNdk$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SentryNdk.lambda$static$0();
            }
        }, "SentryNdkLoadLibs").start();
    }

    private SentryNdk() {
    }

    static /* synthetic */ void lambda$static$0() {
        try {
            System.loadLibrary(OnfidoLogMapper.LOG_EVENT_TYPE);
            System.loadLibrary("sentry");
            System.loadLibrary("sentry-android");
        } catch (Throwable unused) {
        }
        loadLibraryLatch.countDown();
    }

    public static void init(SentryAndroidOptions sentryAndroidOptions) {
        SentryNdkUtil.addPackage(sentryAndroidOptions.getSdkVersion());
        try {
            if (loadLibraryLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                initSentryNative(sentryAndroidOptions);
                if (sentryAndroidOptions.isEnableScopeSync()) {
                    sentryAndroidOptions.addScopeObserver(new NdkScopeObserver(sentryAndroidOptions));
                }
                sentryAndroidOptions.setDebugImagesLoader(new DebugImagesLoader(sentryAndroidOptions, new NativeModuleListLoader()));
                return;
            }
            throw new IllegalStateException("Timeout waiting for Sentry NDK library to load");
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread interrupted while waiting for NDK libs to be loaded", e);
        }
    }

    public static void close() {
        try {
            if (loadLibraryLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                shutdown();
                return;
            }
            throw new IllegalStateException("Timeout waiting for Sentry NDK library to load");
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread interrupted while waiting for NDK libs to be loaded", e);
        }
    }
}
