package io.sentry.android.ndk;

import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.IDebugImagesLoader;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.DebugImage;
import io.sentry.util.Objects;
import java.util.List;

/* loaded from: classes6.dex */
public final class DebugImagesLoader implements IDebugImagesLoader {
    private static List<DebugImage> debugImages;
    private static final Object debugImagesLock = new Object();
    private final NativeModuleListLoader moduleListLoader;
    private final SentryOptions options;

    List<DebugImage> getCachedDebugImages() {
        return debugImages;
    }

    public DebugImagesLoader(SentryAndroidOptions sentryAndroidOptions, NativeModuleListLoader nativeModuleListLoader) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryAndroidOptions, "The SentryAndroidOptions is required.");
        this.moduleListLoader = (NativeModuleListLoader) Objects.requireNonNull(nativeModuleListLoader, "The NativeModuleListLoader is required.");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0043 A[Catch: all -> 0x0047, DONT_GENERATE, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:12:0x0034, B:13:0x0043, B:7:0x0008, B:9:0x0010), top: B:21:0x0003, inners: #0 }] */
    @Override // io.sentry.android.core.IDebugImagesLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<io.sentry.protocol.DebugImage> loadDebugImages() {
        /*
            r7 = this;
            java.lang.Object r0 = io.sentry.android.ndk.DebugImagesLoader.debugImagesLock
            monitor-enter(r0)
            java.util.List<io.sentry.protocol.DebugImage> r1 = io.sentry.android.ndk.DebugImagesLoader.debugImages     // Catch: java.lang.Throwable -> L47
            if (r1 != 0) goto L43
            r1 = 0
            io.sentry.android.ndk.NativeModuleListLoader r2 = r7.moduleListLoader     // Catch: java.lang.Throwable -> L33
            io.sentry.protocol.DebugImage[] r2 = r2.loadModuleList()     // Catch: java.lang.Throwable -> L33
            if (r2 == 0) goto L43
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch: java.lang.Throwable -> L33
            io.sentry.android.ndk.DebugImagesLoader.debugImages = r2     // Catch: java.lang.Throwable -> L33
            io.sentry.SentryOptions r2 = r7.options     // Catch: java.lang.Throwable -> L33
            io.sentry.ILogger r2 = r2.getLogger()     // Catch: java.lang.Throwable -> L33
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.DEBUG     // Catch: java.lang.Throwable -> L33
            java.lang.String r4 = "Debug images loaded: %d"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L33
            java.util.List<io.sentry.protocol.DebugImage> r6 = io.sentry.android.ndk.DebugImagesLoader.debugImages     // Catch: java.lang.Throwable -> L33
            int r6 = r6.size()     // Catch: java.lang.Throwable -> L33
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> L33
            r5[r1] = r6     // Catch: java.lang.Throwable -> L33
            r2.log(r3, r4, r5)     // Catch: java.lang.Throwable -> L33
            goto L43
        L33:
            r2 = move-exception
            io.sentry.SentryOptions r3 = r7.options     // Catch: java.lang.Throwable -> L47
            io.sentry.ILogger r3 = r3.getLogger()     // Catch: java.lang.Throwable -> L47
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> L47
            java.lang.String r5 = "Failed to load debug images."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L47
            r3.log(r4, r2, r5, r1)     // Catch: java.lang.Throwable -> L47
        L43:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L47
            java.util.List<io.sentry.protocol.DebugImage> r0 = io.sentry.android.ndk.DebugImagesLoader.debugImages
            return r0
        L47:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L47
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.ndk.DebugImagesLoader.loadDebugImages():java.util.List");
    }

    @Override // io.sentry.android.core.IDebugImagesLoader
    public void clearDebugImages() {
        synchronized (debugImagesLock) {
            try {
                this.moduleListLoader.clearModuleList();
                this.options.getLogger().log(SentryLevel.INFO, "Debug images cleared.", new Object[0]);
            } finally {
                debugImages = null;
            }
            debugImages = null;
        }
    }
}
