package com.onfido.android.sdk.capture.internal.util;

import android.content.Context;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.onfido.android.sdk.capture.utils.NetworkExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0016B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/FileCache;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cachedFile", "Ljava/io/File;", "getCachedFile", "()Ljava/io/File;", "cachedFile$delegate", "Lkotlin/Lazy;", "filePath", "", "get", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents;", "put", "responseBody", "Lokhttp3/ResponseBody;", "setFilePath", "", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "CacheContents", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileCache {

    /* renamed from: cachedFile$delegate, reason: from kotlin metadata */
    private final Lazy cachedFile;
    private final Context context;
    private String filePath;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents;", "", "CachedFile", "EmptyCache", "Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents$CachedFile;", "Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents$EmptyCache;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface CacheContents {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents$CachedFile;", "Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "getFile", "()Ljava/io/File;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class CachedFile implements CacheContents {
            private final File file;

            public CachedFile(File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                this.file = file;
            }

            public final File getFile() {
                return this.file;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents$EmptyCache;", "Lcom/onfido/android/sdk/capture/internal/util/FileCache$CacheContents;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class EmptyCache implements CacheContents {
            public static final EmptyCache INSTANCE = new EmptyCache();

            private EmptyCache() {
            }
        }
    }

    @Inject
    public FileCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.cachedFile = LazyKt.lazy(new Function0<File>() { // from class: com.onfido.android.sdk.capture.internal.util.FileCache$cachedFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File filesDir = this.this$0.context.getFilesDir();
                String str = this.this$0.filePath;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("filePath");
                    str = null;
                }
                return new File(filesDir, str);
            }
        });
    }

    private final File getCachedFile() {
        return (File) this.cachedFile.getValue();
    }

    public final Single<CacheContents> get() {
        Single<CacheContents> singleJust = Single.just(getCachedFile().exists() ? new CacheContents.CachedFile(getCachedFile()) : CacheContents.EmptyCache.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    public final Single<File> put(ResponseBody responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        return NetworkExtensionsKt.saveFile(responseBody, getCachedFile());
    }

    public final void setFilePath(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.filePath = path;
    }
}
