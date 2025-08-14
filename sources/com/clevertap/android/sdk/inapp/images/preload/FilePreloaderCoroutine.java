package com.clevertap.android.sdk.inapp.images.preload;

import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.utils.CtDefaultDispatchers;
import com.clevertap.android.sdk.utils.DispatcherProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: FilePreloaderCoroutine.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0095\u0002\u0010\u001e\u001a\u00020\u001d2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!0 2-\u0010$\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u001d0%2/\b\u0002\u0010)\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u001d0%2/\b\u0002\u0010*\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0%2/\b\u0002\u0010,\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u001d0%2/\u00100\u001a+\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u0001010%H\u0002JÞ\u0001\u00102\u001a\u00020\u001d2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!0 2-\u0010$\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0%2-\u0010)\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0%2-\u0010*\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0%2-\u0010,\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020.0-¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u001d0%H\u0016R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00063"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderCoroutine;", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "dispatchers", "Lcom/clevertap/android/sdk/utils/DispatcherProvider;", "config", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "timeoutForPreload", "", "(Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/ILogger;Lcom/clevertap/android/sdk/utils/DispatcherProvider;Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;J)V", "getConfig", "()Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "handler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "jobs", "", "Lkotlinx/coroutines/Job;", "getLogger", "()Lcom/clevertap/android/sdk/ILogger;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getTimeoutForPreload", "()J", "cleanup", "", "preloadAssets", "urlMetas", "", "Lkotlin/Pair;", "", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "meta", "failureBlock", "startedBlock", "urlMeta", "preloadFinished", "", "", "urlDownloadStatus", "assetBlock", "", "preloadFilesAndCache", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FilePreloaderCoroutine implements FilePreloaderStrategy {
    private final FilePreloadConfig config;
    private final DispatcherProvider dispatchers;
    private final FileResourceProvider fileResourceProvider;
    private final CoroutineExceptionHandler handler;
    private final List<Job> jobs;
    private final ILogger logger;
    private final CoroutineScope scope;
    private final long timeoutForPreload;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderCoroutine(FileResourceProvider fileResourceProvider) {
        this(fileResourceProvider, null, null, null, 0L, 30, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderCoroutine(FileResourceProvider fileResourceProvider, ILogger iLogger) {
        this(fileResourceProvider, iLogger, null, null, 0L, 28, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderCoroutine(FileResourceProvider fileResourceProvider, ILogger iLogger, DispatcherProvider dispatchers) {
        this(fileResourceProvider, iLogger, dispatchers, null, 0L, 24, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilePreloaderCoroutine(FileResourceProvider fileResourceProvider, ILogger iLogger, DispatcherProvider dispatchers, FilePreloadConfig config) {
        this(fileResourceProvider, iLogger, dispatchers, config, 0L, 16, null);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        Intrinsics.checkNotNullParameter(config, "config");
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

    public FilePreloaderCoroutine(FileResourceProvider fileResourceProvider, ILogger iLogger, DispatcherProvider dispatchers, FilePreloadConfig config, long j) {
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        Intrinsics.checkNotNullParameter(config, "config");
        this.fileResourceProvider = fileResourceProvider;
        this.logger = iLogger;
        this.dispatchers = dispatchers;
        this.config = config;
        this.timeoutForPreload = j;
        this.jobs = new ArrayList();
        this.handler = new FilePreloaderCoroutine$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this);
        this.scope = CoroutineScopeKt.CoroutineScope(dispatchers.io().limitedParallelism(getConfig().getParallelDownloads()));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FilePreloaderCoroutine(FileResourceProvider fileResourceProvider, ILogger iLogger, CtDefaultDispatchers ctDefaultDispatchers, FilePreloadConfig filePreloadConfig, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        ILogger iLogger2 = (i & 2) != 0 ? null : iLogger;
        DispatcherProvider ctDefaultDispatchers2 = (i & 4) != 0 ? new CtDefaultDispatchers() : ctDefaultDispatchers;
        FilePreloadConfig filePreloadConfigM4795default = (i & 8) != 0 ? FilePreloadConfig.INSTANCE.m4795default() : filePreloadConfig;
        if ((i & 16) != 0) {
            Duration.Companion companion = Duration.INSTANCE;
            j = Duration.m7440getInWholeMillisecondsimpl(DurationKt.toDuration(5, DurationUnit.MINUTES));
        }
        this(fileResourceProvider, iLogger2, ctDefaultDispatchers2, filePreloadConfigM4795default, j);
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMetas, Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> startedBlock, Function1<? super Map<String, Boolean>, Unit> preloadFinished) {
        Intrinsics.checkNotNullParameter(urlMetas, "urlMetas");
        Intrinsics.checkNotNullParameter(successBlock, "successBlock");
        Intrinsics.checkNotNullParameter(failureBlock, "failureBlock");
        Intrinsics.checkNotNullParameter(startedBlock, "startedBlock");
        Intrinsics.checkNotNullParameter(preloadFinished, "preloadFinished");
        preloadAssets(urlMetas, successBlock, failureBlock, startedBlock, preloadFinished, new Function1<Pair<? extends String, ? extends CtCacheType>, Object>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine.preloadFilesAndCache.1

            /* compiled from: FilePreloaderCoroutine.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            /* renamed from: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine$preloadFilesAndCache$1$WhenMappings */
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
                    return FilePreloaderCoroutine.this.getFileResourceProvider().fetchInAppImageV1(first);
                }
                if (i == 2) {
                    return FilePreloaderCoroutine.this.getFileResourceProvider().fetchInAppGifV1(first);
                }
                if (i == 3) {
                    return FilePreloaderCoroutine.this.getFileResourceProvider().fetchFile(first);
                }
                throw new NoWhenBranchMatchedException();
            }
        });
    }

    static /* synthetic */ void preloadAssets$default(FilePreloaderCoroutine filePreloaderCoroutine, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine.preloadAssets.1
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
        Function1 function16 = function12;
        if ((i & 8) != 0) {
            function13 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine.preloadAssets.2
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
        Function1 function17 = function13;
        if ((i & 16) != 0) {
            function14 = new Function1<Map<String, ? extends Boolean>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderCoroutine.preloadAssets.3
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
        filePreloaderCoroutine.preloadAssets(list, function1, function16, function17, function14, function15);
    }

    private final void preloadAssets(List<? extends Pair<String, ? extends CtCacheType>> urlMetas, Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> startedBlock, Function1<? super Map<String, Boolean>, Unit> preloadFinished, Function1<? super Pair<String, ? extends CtCacheType>, ? extends Object> assetBlock) {
        this.jobs.add(BuildersKt__Builders_commonKt.launch$default(this.scope, this.handler, null, new FilePreloaderCoroutine$preloadAssets$job$1(urlMetas, this, preloadFinished, startedBlock, assetBlock, successBlock, failureBlock, null), 2, null));
    }

    @Override // com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy
    public void cleanup() {
        Iterator<T> it = this.jobs.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
        }
    }
}
