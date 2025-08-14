package com.clevertap.android.sdk.inapp.images.preload;

import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.CTExecutors;
import com.clevertap.android.sdk.task.OnFailureListener;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: FilePreloaderExecutors.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0091\u0002\u0010\u0017\u001a\u00020\u00162\u0018\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a0\u00192-\u0010\u001d\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00160\u001e2/\b\u0002\u0010\"\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00160\u001e2-\u0010#\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00160\u001e2-\u0010%\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020'0&¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00160\u001e2/\u0010)\u001a+\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010*0\u001eH\u0002JÞ\u0001\u0010+\u001a\u00020\u00162\u0018\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a0\u00192-\u0010\u001d\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00160\u001e2-\u0010\"\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00160\u001e2-\u0010#\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00160\u001e2-\u0010%\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020'0&¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00160\u001eH\u0016R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006,"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderExecutors;", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "executor", "Lcom/clevertap/android/sdk/task/CTExecutors;", "config", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "timeoutForPreload", "", "(Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/ILogger;Lcom/clevertap/android/sdk/task/CTExecutors;Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;J)V", "getConfig", "()Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "getLogger", "()Lcom/clevertap/android/sdk/ILogger;", "getTimeoutForPreload", "()J", "cleanup", "", "preloadAssets", "urlMetas", "", "Lkotlin/Pair;", "", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "meta", "failureBlock", "startedBlock", "urlMeta", "preloadFinished", "", "", "urlDownloadStatus", "assetBlock", "", "preloadFilesAndCache", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FilePreloaderExecutors implements FilePreloaderStrategy {
    private final FilePreloadConfig config;
    private final CTExecutors executor;
    private final FileResourceProvider fileResourceProvider;
    private final ILogger logger;
    private final long timeoutForPreload;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderExecutors(FileResourceProvider fileResourceProvider) {
        this(fileResourceProvider, null, null, null, 0L, 30, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderExecutors(FileResourceProvider fileResourceProvider, ILogger iLogger) {
        this(fileResourceProvider, iLogger, null, null, 0L, 28, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderExecutors(FileResourceProvider fileResourceProvider, ILogger iLogger, CTExecutors executor) {
        this(fileResourceProvider, iLogger, executor, null, 0L, 24, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(executor, "executor");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderExecutors(FileResourceProvider fileResourceProvider, ILogger iLogger, CTExecutors executor, FilePreloadConfig config) {
        this(fileResourceProvider, iLogger, executor, config, 0L, 16, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(config, "config");
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public void cleanup() {
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public FilePreloadConfig getConfig() {
        return this.config;
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public FileResourceProvider getFileResourceProvider() {
        return this.fileResourceProvider;
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public ILogger getLogger() {
        return this.logger;
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public long getTimeoutForPreload() {
        return this.timeoutForPreload;
    }

    public FilePreloaderExecutors(FileResourceProvider fileResourceProvider, ILogger iLogger, CTExecutors executor, FilePreloadConfig config, long j) {
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(config, "config");
        this.fileResourceProvider = fileResourceProvider;
        this.logger = iLogger;
        this.executor = executor;
        this.config = config;
        this.timeoutForPreload = j;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FilePreloaderExecutors(FileResourceProvider fileResourceProvider, ILogger iLogger, CTExecutors cTExecutors, FilePreloadConfig filePreloadConfig, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ILogger iLogger2 = (i & 2) != 0 ? null : iLogger;
        if ((i & 4) != 0) {
            cTExecutors = CTExecutorFactory.executorResourceDownloader();
            Intrinsics.checkNotNullExpressionValue(cTExecutors, "executorResourceDownloader()");
        }
        CTExecutors cTExecutors2 = cTExecutors;
        FilePreloadConfig filePreloadConfigM4795default = (i & 8) != 0 ? FilePreloadConfig.INSTANCE.m4795default() : filePreloadConfig;
        if ((i & 16) != 0) {
            Duration.Companion companion = Duration.INSTANCE;
            j = Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(5, DurationUnit.MINUTES));
        }
        this(fileResourceProvider, iLogger2, cTExecutors2, filePreloadConfigM4795default, j);
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMetas, Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> startedBlock, Function1<? super Map<String, Boolean>, Unit> preloadFinished) {
        Intrinsics.checkNotNullParameter(urlMetas, "urlMetas");
        Intrinsics.checkNotNullParameter(successBlock, "successBlock");
        Intrinsics.checkNotNullParameter(failureBlock, "failureBlock");
        Intrinsics.checkNotNullParameter(startedBlock, "startedBlock");
        Intrinsics.checkNotNullParameter(preloadFinished, "preloadFinished");
        preloadAssets(urlMetas, successBlock, failureBlock, startedBlock, preloadFinished, new Function1<Pair<? extends String, ? extends CtCacheType>, Object>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors.preloadFilesAndCache.1

            /* compiled from: FilePreloaderExecutors.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors$preloadFilesAndCache$1$WhenMappings */
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

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Pair<? extends String, ? extends CtCacheType> pair) {
                return invoke2((Pair<String, ? extends CtCacheType>) pair);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(Pair<String, ? extends CtCacheType> urlMeta) {
                Intrinsics.checkNotNullParameter(urlMeta, "urlMeta");
                String first = urlMeta.getFirst();
                int i = WhenMappings.$EnumSwitchMapping$0[urlMeta.getSecond().ordinal()];
                if (i == 1) {
                    return FilePreloaderExecutors.this.getFileResourceProvider().fetchInAppImageV1(first);
                }
                if (i == 2) {
                    return FilePreloaderExecutors.this.getFileResourceProvider().fetchInAppGifV1(first);
                }
                if (i == 3) {
                    return FilePreloaderExecutors.this.getFileResourceProvider().fetchFile(first);
                }
                throw new NoWhenBranchMatchedException();
            }
        });
    }

    static /* synthetic */ void preloadAssets$default(FilePreloaderExecutors filePreloaderExecutors, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors.preloadAssets.1
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
        filePreloaderExecutors.preloadAssets(list, function1, function12, function13, function14, function15);
    }

    private final void preloadAssets(List<? extends Pair<String, ? extends CtCacheType>> urlMetas, final Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, final Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock, final Function1<? super Pair<String, ? extends CtCacheType>, Unit> startedBlock, Function1<? super Map<String, Boolean>, Unit> preloadFinished, final Function1<? super Pair<String, ? extends CtCacheType>, ? extends Object> assetBlock) {
        final CountDownLatch countDownLatch = new CountDownLatch(urlMetas.size());
        List<? extends Pair<String, ? extends CtCacheType>> list = urlMetas;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(TuplesKt.to(((Pair) it.next()).getFirst(), false));
        }
        ArrayList<Pair> arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Pair pair : arrayList2) {
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        final Map mutableMap = MapsKt.toMutableMap(linkedHashMap);
        for (final Pair<String, ? extends CtCacheType> pair2 : urlMetas) {
            Task taskIoTaskWithCallbackOnCurrentThread = this.executor.ioTaskWithCallbackOnCurrentThread();
            taskIoTaskWithCallbackOnCurrentThread.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors$$ExternalSyntheticLambda0
                @Override // com.clevertap.android.sdk.task.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FilePreloaderExecutors.preloadAssets$lambda$2(countDownLatch, (Unit) obj);
                }
            });
            taskIoTaskWithCallbackOnCurrentThread.addOnFailureListener(new OnFailureListener() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors$$ExternalSyntheticLambda1
                @Override // com.clevertap.android.sdk.task.OnFailureListener
                public final void onFailure(Object obj) {
                    FilePreloaderExecutors.preloadAssets$lambda$3(countDownLatch, (Exception) obj);
                }
            });
            taskIoTaskWithCallbackOnCurrentThread.execute("tag", new Callable() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderExecutors$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return FilePreloaderExecutors.preloadAssets$lambda$4(startedBlock, pair2, assetBlock, mutableMap, successBlock, failureBlock);
                }
            });
        }
        try {
            if (countDownLatch.await(5L, TimeUnit.MINUTES)) {
                preloadFinished.invoke(mutableMap);
            }
        } catch (InterruptedException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void preloadAssets$lambda$2(CountDownLatch countDownLatch, Unit unit) {
        Intrinsics.checkNotNullParameter(countDownLatch, "$countDownLatch");
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void preloadAssets$lambda$3(CountDownLatch countDownLatch, Exception exc) {
        Intrinsics.checkNotNullParameter(countDownLatch, "$countDownLatch");
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit preloadAssets$lambda$4(Function1 startedBlock, Pair url, Function1 assetBlock, Map downloadStatus, Function1 successBlock, Function1 failureBlock) {
        Intrinsics.checkNotNullParameter(startedBlock, "$startedBlock");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(assetBlock, "$assetBlock");
        Intrinsics.checkNotNullParameter(downloadStatus, "$downloadStatus");
        Intrinsics.checkNotNullParameter(successBlock, "$successBlock");
        Intrinsics.checkNotNullParameter(failureBlock, "$failureBlock");
        startedBlock.invoke(url);
        if (assetBlock.invoke(url) != null) {
            downloadStatus.put(url.getFirst(), true);
            successBlock.invoke(url);
        } else {
            downloadStatus.put(url.getFirst(), false);
            failureBlock.invoke(url);
        }
        return Unit.INSTANCE;
    }
}
