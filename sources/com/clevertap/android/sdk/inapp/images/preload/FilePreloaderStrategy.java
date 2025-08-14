package com.clevertap.android.sdk.inapp.images.preload;

import com.clevertap.android.sdk.ILogger;
import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FilePreloaderStrategy.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0013H&Jæ\u0001\u0010\u0014\u001a\u00020\u00132\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00170\u00162/\b\u0002\u0010\u001a\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00130\u001b2/\b\u0002\u0010\u001f\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00130\u001b2/\b\u0002\u0010 \u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00130\u001b2/\b\u0002\u0010!\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020#0\"¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00130\u001bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloaderStrategy;", "", "config", "Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "getConfig", "()Lcom/clevertap/android/sdk/inapp/images/preload/FilePreloadConfig;", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "logger", "Lcom/clevertap/android/sdk/ILogger;", "getLogger", "()Lcom/clevertap/android/sdk/ILogger;", "timeoutForPreload", "", "getTimeoutForPreload", "()J", "cleanup", "", "preloadFilesAndCache", "urlMetas", "", "Lkotlin/Pair;", "", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "urlMeta", "failureBlock", "startedBlock", "preloadFinished", "", "", "urlDownloadStatus", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface FilePreloaderStrategy {
    void cleanup();

    FilePreloadConfig getConfig();

    FileResourceProvider getFileResourceProvider();

    ILogger getLogger();

    long getTimeoutForPreload();

    void preloadFilesAndCache(List<? extends Pair<String, ? extends CtCacheType>> urlMetas, Function1<? super Pair<String, ? extends CtCacheType>, Unit> successBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> failureBlock, Function1<? super Pair<String, ? extends CtCacheType>, Unit> startedBlock, Function1<? super Map<String, Boolean>, Unit> preloadFinished);

    /* compiled from: FilePreloaderStrategy.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void preloadFilesAndCache$default(FilePreloaderStrategy filePreloaderStrategy, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: preloadFilesAndCache");
            }
            if ((i & 2) != 0) {
                function1 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy.preloadFilesAndCache.1
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
            Function1 function15 = function1;
            if ((i & 4) != 0) {
                function12 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy.preloadFilesAndCache.2
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
                function13 = new Function1<Pair<? extends String, ? extends CtCacheType>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy.preloadFilesAndCache.3
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
                function14 = new Function1<Map<String, ? extends Boolean>, Unit>() { // from class: com.clevertap.android.sdk.inapp.images.preload.FilePreloaderStrategy.preloadFilesAndCache.4
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
            filePreloaderStrategy.preloadFilesAndCache(list, function15, function16, function17, function14);
        }
    }
}
