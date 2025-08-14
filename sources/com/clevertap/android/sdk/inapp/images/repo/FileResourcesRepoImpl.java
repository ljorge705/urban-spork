package com.clevertap.android.sdk.inapp.images.repo;

import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy;
import com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy;
import com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo;
import com.clevertap.android.sdk.inapp.store.preference.FileStore;
import com.clevertap.android.sdk.inapp.store.preference.InAppAssetsStore;
import com.clevertap.android.sdk.inapp.store.preference.LegacyInAppStore;
import io.sentry.SentryReplayEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: FileResourcesRepoImpl.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 52\u00020\u0001:\u00015B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0016\u0010\u001a\u001a\u00020\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016JW\u0010\u001c\u001a\u00020\u00122\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150!2#\b\u0002\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001f0#H\u0002J¯\u0001\u0010'\u001a\u00020\u00122\u0018\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180)0\u00142-\u0010*\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020,0+¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00120#2-\u0010.\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180)¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00120#2-\u0010/\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180)¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00120#H\u0016J\b\u00100\u001a\u00020\u0012H\u0002J$\u00101\u001a\u00020\u00122\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00180)2\u0006\u00103\u001a\u000204H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u00066"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepoImpl;", "Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepo;", "cleanupStrategy", "Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "preloaderStrategy", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "inAppAssetsStore", "Lcom/clevertap/android/sdk/inapp/store/preference/InAppAssetsStore;", "fileStore", "Lcom/clevertap/android/sdk/inapp/store/preference/FileStore;", "legacyInAppsStore", "Lcom/clevertap/android/sdk/inapp/store/preference/LegacyInAppStore;", "(Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;Lcom/clevertap/android/sdk/inapp/store/preference/InAppAssetsStore;Lcom/clevertap/android/sdk/inapp/store/preference/FileStore;Lcom/clevertap/android/sdk/inapp/store/preference/LegacyInAppStore;)V", "getCleanupStrategy", "()Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "getPreloaderStrategy", "()Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "cleanupAllFiles", "", "cleanupUrls", "", "", "cleanupAllResources", "cacheTpe", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "cleanupExpiredResources", "cleanupStaleFiles", SentryReplayEvent.JsonKeys.URLS, "cleanupStaleFilesNow", "validUrls", "currentTime", "", "allFileUrls", "", "expiryTs", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "url", "preloadFilesAndCache", "urlMeta", "Lkotlin/Pair;", "completionCallback", "", "", "urlStatusMap", "successBlock", "failureBlock", "repoUpdated", "updateRepoStatus", "meta", "downloadState", "Lcom/clevertap/android/sdk/inapp/images/repo/DownloadState;", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FileResourcesRepoImpl implements FileResourcesRepo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long EXPIRY_OFFSET_MILLIS;
    private static final HashMap<String, DownloadState> downloadInProgressUrls;
    private static final Object fetchAllFilesLock;
    private static final Set<DownloadTriggerForUrls> urlTriggers;
    private final FileCleanupStrategy cleanupStrategy;
    private final FileStore fileStore;
    private final InAppAssetsStore inAppAssetsStore;
    private final LegacyInAppStore legacyInAppsStore;
    private final FilePreloaderStrategy preloaderStrategy;

    /* compiled from: FileResourcesRepoImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CtCacheType.values().length];
            try {
                iArr[CtCacheType.IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CtCacheType.GIF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CtCacheType.FILES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final void saveUrlExpiryToStore(Pair<String, ? extends CtCacheType> pair, Pair<FileStore, InAppAssetsStore> pair2) {
        INSTANCE.saveUrlExpiryToStore(pair, pair2);
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public FileCleanupStrategy getCleanupStrategy() {
        return this.cleanupStrategy;
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public FilePreloaderStrategy getPreloaderStrategy() {
        return this.preloaderStrategy;
    }

    public FileResourcesRepoImpl(FileCleanupStrategy cleanupStrategy, FilePreloaderStrategy preloaderStrategy, InAppAssetsStore inAppAssetsStore, FileStore fileStore, LegacyInAppStore legacyInAppsStore) {
        Intrinsics.checkNotNullParameter(cleanupStrategy, "cleanupStrategy");
        Intrinsics.checkNotNullParameter(preloaderStrategy, "preloaderStrategy");
        Intrinsics.checkNotNullParameter(inAppAssetsStore, "inAppAssetsStore");
        Intrinsics.checkNotNullParameter(fileStore, "fileStore");
        Intrinsics.checkNotNullParameter(legacyInAppsStore, "legacyInAppsStore");
        this.cleanupStrategy = cleanupStrategy;
        this.preloaderStrategy = preloaderStrategy;
        this.inAppAssetsStore = inAppAssetsStore;
        this.fileStore = fileStore;
        this.legacyInAppsStore = legacyInAppsStore;
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void cleanupStaleFiles() {
        FileResourcesRepo.DefaultImpls.cleanupStaleFiles(this);
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> list) {
        FileResourcesRepo.DefaultImpls.preloadFilesAndCache(this, list);
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> list, Function1<? super Map<String, Boolean>, Unit> function1) {
        FileResourcesRepo.DefaultImpls.preloadFilesAndCache(this, list, function1);
    }

    /* compiled from: FileResourcesRepoImpl.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepoImpl$Companion;", "", "()V", "EXPIRY_OFFSET_MILLIS", "", "downloadInProgressUrls", "Ljava/util/HashMap;", "", "Lcom/clevertap/android/sdk/inapp/images/repo/DownloadState;", "Lkotlin/collections/HashMap;", "fetchAllFilesLock", "urlTriggers", "", "Lcom/clevertap/android/sdk/inapp/images/repo/DownloadTriggerForUrls;", "saveUrlExpiryToStore", "", "urlMeta", "Lkotlin/Pair;", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "storePair", "Lcom/clevertap/android/sdk/inapp/store/preference/FileStore;", "Lcom/clevertap/android/sdk/inapp/store/preference/InAppAssetsStore;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {

        /* compiled from: FileResourcesRepoImpl.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CtCacheType.values().length];
                try {
                    iArr[CtCacheType.IMAGE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CtCacheType.GIF.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CtCacheType.FILES.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void saveUrlExpiryToStore(Pair<String, ? extends CtCacheType> urlMeta, Pair<FileStore, InAppAssetsStore> storePair) {
            Intrinsics.checkNotNullParameter(urlMeta, "urlMeta");
            Intrinsics.checkNotNullParameter(storePair, "storePair");
            String first = urlMeta.getFirst();
            long jCurrentTimeMillis = System.currentTimeMillis() + FileResourcesRepoImpl.EXPIRY_OFFSET_MILLIS;
            FileStore first2 = storePair.getFirst();
            InAppAssetsStore second = storePair.getSecond();
            int i = WhenMappings.$EnumSwitchMapping$0[urlMeta.getSecond().ordinal()];
            if (i == 1 || i == 2) {
                second.saveAssetUrl(first, jCurrentTimeMillis);
                first2.saveFileUrl(first, jCurrentTimeMillis);
            } else {
                if (i != 3) {
                    return;
                }
                first2.saveFileUrl(first, jCurrentTimeMillis);
            }
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        EXPIRY_OFFSET_MILLIS = Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(14, DurationUnit.DAYS));
        urlTriggers = new LinkedHashSet();
        downloadInProgressUrls = new HashMap<>();
        fetchAllFilesLock = new Object();
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMeta, Function1<? super Map<String, Boolean>, Unit> completionCallback, final Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, final Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock) {
        Intrinsics.checkNotNullParameter(urlMeta, "urlMeta");
        Intrinsics.checkNotNullParameter(completionCallback, "completionCallback");
        Intrinsics.checkNotNullParameter(successBlock, "successBlock");
        Intrinsics.checkNotNullParameter(failureBlock, "failureBlock");
        getPreloaderStrategy().preloadFilesAndCache(urlMeta, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl$preloadFilesAndCache$successBlockk$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                invoke2((Pair<String, ? extends CtCacheType>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<String, ? extends CtCacheType> meta) {
                Intrinsics.checkNotNullParameter(meta, "meta");
                FileResourcesRepoImpl.INSTANCE.saveUrlExpiryToStore(meta, new Pair<>(this.this$0.fileStore, this.this$0.inAppAssetsStore));
                this.this$0.updateRepoStatus(meta, DownloadState.SUCCESSFUL);
                successBlock.invoke(meta);
            }
        }, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl$preloadFilesAndCache$failureBlockk$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                invoke2((Pair<String, ? extends CtCacheType>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<String, ? extends CtCacheType> meta) {
                Intrinsics.checkNotNullParameter(meta, "meta");
                this.this$0.updateRepoStatus(meta, DownloadState.FAILED);
                failureBlock.invoke(meta);
            }
        }, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl$preloadFilesAndCache$started$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                invoke2((Pair<String, ? extends CtCacheType>) pair);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Pair<String, ? extends CtCacheType> meta) {
                Intrinsics.checkNotNullParameter(meta, "meta");
                this.this$0.updateRepoStatus(meta, DownloadState.IN_PROGRESS);
            }
        }, completionCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRepoStatus(Pair<String, ? extends CtCacheType> meta, DownloadState downloadState) {
        if (urlTriggers.isEmpty()) {
            return;
        }
        synchronized (fetchAllFilesLock) {
            downloadInProgressUrls.put(meta.getFirst(), downloadState);
            repoUpdated();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void cleanupStaleFiles(List<String> urls) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.legacyInAppsStore.lastCleanupTs() < EXPIRY_OFFSET_MILLIS) {
            return;
        }
        cleanupStaleFilesNow$default(this, urls, jCurrentTimeMillis, null, null, 12, null);
        this.legacyInAppsStore.updateAssetCleanupTs(jCurrentTimeMillis);
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void cleanupExpiredResources(CtCacheType cacheTpe) {
        Set<String> allAssetUrls;
        Intrinsics.checkNotNullParameter(cacheTpe, "cacheTpe");
        int i = WhenMappings.$EnumSwitchMapping$0[cacheTpe.ordinal()];
        if (i == 1 || i == 2) {
            allAssetUrls = this.inAppAssetsStore.getAllAssetUrls();
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            allAssetUrls = SetsKt.plus((Set) this.fileStore.getAllFileUrls(), (Iterable) this.inAppAssetsStore.getAllAssetUrls());
        }
        cleanupStaleFilesNow$default(this, null, 0L, allAssetUrls, null, 11, null);
    }

    @Override // com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo
    public void cleanupAllResources(CtCacheType cacheTpe) {
        Set<String> allAssetUrls;
        Intrinsics.checkNotNullParameter(cacheTpe, "cacheTpe");
        int i = WhenMappings.$EnumSwitchMapping$0[cacheTpe.ordinal()];
        if (i == 1 || i == 2) {
            allAssetUrls = this.inAppAssetsStore.getAllAssetUrls();
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            allAssetUrls = SetsKt.plus((Set) this.fileStore.getAllFileUrls(), (Iterable) this.inAppAssetsStore.getAllAssetUrls());
        }
        cleanupAllFiles(CollectionsKt.toList(allAssetUrls));
    }

    static /* synthetic */ void cleanupStaleFilesNow$default(FileResourcesRepoImpl fileResourcesRepoImpl, List list, long j, Set set, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        long j2 = j;
        if ((i & 4) != 0) {
            set = SetsKt.plus((Set) fileResourcesRepoImpl.fileStore.getAllFileUrls(), (Iterable) fileResourcesRepoImpl.inAppAssetsStore.getAllAssetUrls());
        }
        Set set2 = set;
        if ((i & 8) != 0) {
            function1 = new Function1<String, Long>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl.cleanupStaleFilesNow.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Long invoke(String key) {
                    Intrinsics.checkNotNullParameter(key, "key");
                    return Long.valueOf(Math.max(FileResourcesRepoImpl.this.fileStore.expiryForUrl(key), FileResourcesRepoImpl.this.inAppAssetsStore.expiryForUrl(key)));
                }
            };
        }
        fileResourcesRepoImpl.cleanupStaleFilesNow(list, j2, set2, function1);
    }

    private final void cleanupStaleFilesNow(List<String> validUrls, long currentTime, Set<String> allFileUrls, Function1<? super String, Long> expiryTs) {
        List<String> list = validUrls;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(obj, (String) obj);
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        Set mutableSet = CollectionsKt.toMutableSet(allFileUrls);
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : mutableSet) {
            String str = (String) obj2;
            boolean z = !linkedHashMap2.containsKey(str);
            boolean z2 = currentTime > expiryTs.invoke(str).longValue();
            if (z && z2) {
                arrayList.add(obj2);
            }
        }
        cleanupAllFiles(arrayList);
    }

    private final void cleanupAllFiles(List<String> cleanupUrls) {
        getCleanupStrategy().clearFileAssets(cleanupUrls, new Function1<String, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepoImpl$cleanupAllFiles$successBlock$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String url) {
                Intrinsics.checkNotNullParameter(url, "url");
                this.this$0.fileStore.clearFileUrl(url);
                this.this$0.inAppAssetsStore.clearAssetUrl(url);
            }
        });
    }

    private final void repoUpdated() {
        for (DownloadTriggerForUrls downloadTriggerForUrls : urlTriggers) {
            List<String> urls = downloadTriggerForUrls.getUrls();
            if (!(urls instanceof Collection) || !urls.isEmpty()) {
                for (String str : urls) {
                    HashMap<String, DownloadState> map = downloadInProgressUrls;
                    if (map.get(str) == DownloadState.SUCCESSFUL || map.get(str) == DownloadState.FAILED) {
                    }
                }
            }
            downloadTriggerForUrls.getCallback().invoke();
        }
    }
}
