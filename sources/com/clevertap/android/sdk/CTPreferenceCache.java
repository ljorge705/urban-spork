package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.CTPreferenceCache;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTPreferenceCache.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/CTPreferenceCache;", "", "()V", "isFirstTimeRequest", "", "setFirstTimeRequest", "", "fTR", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTPreferenceCache {
    private static volatile CTPreferenceCache INSTANCE;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static boolean firstTimeRequest = true;

    @JvmStatic
    public static final CTPreferenceCache getInstance(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        return INSTANCE.getInstance(context, cleverTapInstanceConfig);
    }

    @JvmStatic
    public static final void updateCacheToDisk(Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        INSTANCE.updateCacheToDisk(context, cleverTapInstanceConfig);
    }

    public final boolean isFirstTimeRequest() {
        return firstTimeRequest;
    }

    public final void setFirstTimeRequest(boolean fTR) {
        firstTimeRequest = fTR;
    }

    /* compiled from: CTPreferenceCache.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/clevertap/android/sdk/CTPreferenceCache$Companion;", "", "()V", "INSTANCE", "Lcom/clevertap/android/sdk/CTPreferenceCache;", InAppController.IS_FIRST_TIME_PERMISSION_REQUEST, "", "buildCache", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "getInstance", "updateCacheToDisk", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CTPreferenceCache getInstance(Context context, CleverTapInstanceConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            CTPreferenceCache cTPreferenceCache = CTPreferenceCache.INSTANCE;
            if (cTPreferenceCache == null) {
                synchronized (this) {
                    cTPreferenceCache = CTPreferenceCache.INSTANCE;
                    if (cTPreferenceCache == null) {
                        CTPreferenceCache cTPreferenceCacheBuildCache = CTPreferenceCache.INSTANCE.buildCache(context, config);
                        Companion companion = CTPreferenceCache.INSTANCE;
                        CTPreferenceCache.INSTANCE = cTPreferenceCacheBuildCache;
                        cTPreferenceCache = cTPreferenceCacheBuildCache;
                    }
                }
            }
            return cTPreferenceCache;
        }

        private final CTPreferenceCache buildCache(final Context context, CleverTapInstanceConfig config) {
            CTExecutorFactory.executors(config).ioTask().execute("buildCache", new Callable() { // from class: com.clevertap.android.sdk.CTPreferenceCache$Companion$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return CTPreferenceCache.Companion.buildCache$lambda$2(context);
                }
            });
            return new CTPreferenceCache();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Void buildCache$lambda$2(Context context) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Companion companion = CTPreferenceCache.INSTANCE;
            CTPreferenceCache.firstTimeRequest = StorageHelper.getBoolean(context, InAppController.IS_FIRST_TIME_PERMISSION_REQUEST, true);
            return null;
        }

        @JvmStatic
        public final void updateCacheToDisk(final Context context, CleverTapInstanceConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            CTExecutorFactory.executors(config).ioTask().execute("updateCacheToDisk", new Callable() { // from class: com.clevertap.android.sdk.CTPreferenceCache$Companion$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return CTPreferenceCache.Companion.updateCacheToDisk$lambda$3(context);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Void updateCacheToDisk$lambda$3(Context context) {
            Intrinsics.checkNotNullParameter(context, "$context");
            StorageHelper.putBooleanImmediate(context, InAppController.IS_FIRST_TIME_PERMISSION_REQUEST, CTPreferenceCache.firstTimeRequest);
            return null;
        }
    }
}
