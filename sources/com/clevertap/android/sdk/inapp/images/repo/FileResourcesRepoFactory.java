package com.clevertap.android.sdk.inapp.images.repo;

import android.content.Context;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategyCoroutine;
import com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine;
import com.clevertap.android.sdk.inapp.store.preference.FileStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppAssetsStore;
import com.clevertap.android.sdk.inapp.store.preference.LegacyInAppStore;
import com.clevertap.android.sdk.inapp.store.preference.StoreRegistry;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileResourcesRepoFactory.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepoFactory;", "", "()V", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FileResourcesRepoFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean USE_COROUTINES = true;

    @JvmStatic
    public static final FileResourcesRepoImpl createFileResourcesRepo(Context context, Logger logger, StoreRegistry storeRegistry) {
        return INSTANCE.createFileResourcesRepo(context, logger, storeRegistry);
    }

    /* compiled from: FileResourcesRepoFactory.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepoFactory$Companion;", "", "()V", "USE_COROUTINES", "", "createFileResourcesRepo", "Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepoImpl;", "context", "Landroid/content/Context;", "logger", "Lcom/clevertap/android/sdk/Logger;", "storeRegistry", "Lcom/clevertap/android/sdk/inapp/store/preference/StoreRegistry;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FileResourcesRepoImpl createFileResourcesRepo(Context context, Logger logger, StoreRegistry storeRegistry) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(logger, "logger");
            Intrinsics.checkNotNullParameter(storeRegistry, "storeRegistry");
            InAppAssetsStore inAppAssetsStore = storeRegistry.getInAppAssetsStore();
            FileStore filesStore = storeRegistry.getFilesStore();
            LegacyInAppStore legacyInAppStore = storeRegistry.getLegacyInAppStore();
            Logger logger2 = logger;
            FileResourceProvider fileResourceProvider = new FileResourceProvider(context, logger2);
            return new FileResourcesRepoImpl(new FileCleanupStrategyCoroutine(fileResourceProvider, null, 2, null), new FilePreloaderCoroutine(fileResourceProvider, logger2, null, null, 0L, 28, null), inAppAssetsStore, filesStore, legacyInAppStore);
        }
    }
}
