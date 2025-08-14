package com.onfido.android.sdk.capture.ui.camera.liveness.intro;

import com.onfido.android.sdk.capture.internal.util.FileCache;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoRepository;", "", "urlProvider", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoUrlProvider;", "cache", "Lcom/onfido/android/sdk/capture/internal/util/FileCache;", "api", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoApi;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoUrlProvider;Lcom/onfido/android/sdk/capture/internal/util/FileCache;Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoApi;)V", "get", "Lio/reactivex/rxjava3/core/Single;", "Ljava/io/File;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessIntroVideoRepository {
    private final LivenessIntroVideoApi api;
    private final FileCache cache;
    private final LivenessIntroVideoUrlProvider urlProvider;

    @Inject
    public LivenessIntroVideoRepository(LivenessIntroVideoUrlProvider urlProvider, FileCache cache, LivenessIntroVideoApi api) {
        Intrinsics.checkNotNullParameter(urlProvider, "urlProvider");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(api, "api");
        this.urlProvider = urlProvider;
        this.cache = cache;
        this.api = api;
    }

    public Single<File> get() {
        this.cache.setFilePath(LivenessConstants.LIVENESS_INTRO_VIDEO_NAME);
        Single singleFlatMap = this.cache.get().flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository.get.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends File> apply(FileCache.CacheContents content) {
                Intrinsics.checkNotNullParameter(content, "content");
                if (content instanceof FileCache.CacheContents.CachedFile) {
                    return Single.just(((FileCache.CacheContents.CachedFile) content).getFile());
                }
                if (!(content instanceof FileCache.CacheContents.EmptyCache)) {
                    throw new NoWhenBranchMatchedException();
                }
                Single<String> indexUrl = LivenessIntroVideoRepository.this.urlProvider.getIndexUrl();
                final LivenessIntroVideoRepository livenessIntroVideoRepository = LivenessIntroVideoRepository.this;
                Single<R> singleFlatMap2 = indexUrl.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository.get.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final SingleSource<? extends LivenessIntroVideoIndexResponse> apply(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return livenessIntroVideoRepository.api.getLivenessIntroVideoPaths(it);
                    }
                });
                final LivenessIntroVideoRepository livenessIntroVideoRepository2 = LivenessIntroVideoRepository.this;
                Single<R> singleFlatMap3 = singleFlatMap2.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository.get.1.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final SingleSource<? extends String> apply(LivenessIntroVideoIndexResponse it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return livenessIntroVideoRepository2.urlProvider.getVideoUrl(it.getPaths());
                    }
                });
                final LivenessIntroVideoRepository livenessIntroVideoRepository3 = LivenessIntroVideoRepository.this;
                Single<R> singleFlatMap4 = singleFlatMap3.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository.get.1.3
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final SingleSource<? extends ResponseBody> apply(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return livenessIntroVideoRepository3.api.getLivenessVideo(it);
                    }
                });
                final LivenessIntroVideoRepository livenessIntroVideoRepository4 = LivenessIntroVideoRepository.this;
                return singleFlatMap4.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoRepository.get.1.4
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final SingleSource<? extends File> apply(ResponseBody it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return livenessIntroVideoRepository4.cache.put(it);
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "flatMap(...)");
        return singleFlatMap;
    }
}
