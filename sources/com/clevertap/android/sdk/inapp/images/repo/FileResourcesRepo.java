package com.clevertap.android.sdk.inapp.images.repo;

import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy;
import com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy;
import io.sentry.SentryReplayEvent;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileResourcesRepo.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\u0016\u0010\u000f\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\"\u0010\u0013\u001a\u00020\u000b2\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00150\u0011H\u0016JQ\u0010\u0013\u001a\u00020\u000b2\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00150\u00112-\u0010\u0016\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000b0\u0017H\u0016Jµ\u0001\u0010\u0013\u001a\u00020\u000b2\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u00150\u00112/\b\u0002\u0010\u0016\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000b0\u00172/\b\u0002\u0010\u001d\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000b0\u00172/\b\u0002\u0010\u001e\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0015¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000b0\u0017H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/FileResourcesRepo;", "", "cleanupStrategy", "Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "getCleanupStrategy", "()Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "preloaderStrategy", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "getPreloaderStrategy", "()Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "cleanupAllResources", "", "cacheTpe", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "cleanupExpiredResources", "cleanupStaleFiles", SentryReplayEvent.JsonKeys.URLS, "", "", "preloadFilesAndCache", "urlMeta", "Lkotlin/Pair;", "completionCallback", "Lkotlin/Function1;", "", "", "Lkotlin/ParameterName;", "name", "urlStatusMap", "successBlock", "failureBlock", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface FileResourcesRepo {
    void cleanupAllResources(CtCacheType cacheTpe);

    void cleanupExpiredResources(CtCacheType cacheTpe);

    void cleanupStaleFiles();

    void cleanupStaleFiles(List<String> urls);

    FileCleanupStrategy getCleanupStrategy();

    FilePreloaderStrategy getPreloaderStrategy();

    void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMeta);

    void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMeta, Function1<? super Map<String, Boolean>, Unit> completionCallback);

    void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMeta, Function1<? super Map<String, Boolean>, Unit> completionCallback, Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock);

    /* compiled from: FileResourcesRepo.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void preloadFilesAndCache(FileResourcesRepo fileResourcesRepo, List<? extends Pair<String, ? extends CtCacheType>> urlMeta) {
            Intrinsics.checkNotNullParameter(urlMeta, "urlMeta");
            fileResourcesRepo.preloadFilesAndCache(urlMeta, new Function1<Map<String, ? extends Boolean>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Map<String, Boolean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Boolean> map) {
                    invoke2((Map<String, Boolean>) map);
                    return Unit.INSTANCE;
                }
            }, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                    invoke2((Pair<String, ? extends CtCacheType>) pair);
                    return Unit.INSTANCE;
                }
            }, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                    invoke2((Pair<String, ? extends CtCacheType>) pair);
                    return Unit.INSTANCE;
                }
            });
        }

        public static void preloadFilesAndCache(FileResourcesRepo fileResourcesRepo, List<? extends Pair<String, ? extends CtCacheType>> urlMeta, Function1<? super Map<String, Boolean>, Unit> completionCallback) {
            Intrinsics.checkNotNullParameter(urlMeta, "urlMeta");
            Intrinsics.checkNotNullParameter(completionCallback, "completionCallback");
            fileResourcesRepo.preloadFilesAndCache(urlMeta, completionCallback, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                    invoke2((Pair<String, ? extends CtCacheType>) pair);
                    return Unit.INSTANCE;
                }
            }, new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.5
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                    invoke2((Pair<String, ? extends CtCacheType>) pair);
                    return Unit.INSTANCE;
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void preloadFilesAndCache$default(FileResourcesRepo fileResourcesRepo, List list, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: preloadFilesAndCache");
            }
            if ((i & 2) != 0) {
                function1 = new Function1<Map<String, ? extends Boolean>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.6
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Map<String, Boolean> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Boolean> map) {
                        invoke2((Map<String, Boolean>) map);
                        return Unit.INSTANCE;
                    }
                };
            }
            if ((i & 4) != 0) {
                function12 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.7
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                        invoke2((Pair<String, ? extends CtCacheType>) pair);
                        return Unit.INSTANCE;
                    }
                };
            }
            if ((i & 8) != 0) {
                function13 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.repo.FileResourcesRepo.preloadFilesAndCache.8
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Pair<String, ? extends CtCacheType> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                        invoke2((Pair<String, ? extends CtCacheType>) pair);
                        return Unit.INSTANCE;
                    }
                };
            }
            fileResourcesRepo.preloadFilesAndCache(list, function1, function12, function13);
        }

        public static void cleanupStaleFiles(FileResourcesRepo fileResourcesRepo) {
            fileResourcesRepo.cleanupStaleFiles(CollectionsKt.emptyList());
        }
    }
}
