package com.clevertap.android.sdk.inapp.images.cleanup;

import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.CTExecutors;
import io.sentry.SentryReplayEvent;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileCleanupStrategyExecutors.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J9\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\n0\u000fH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategyExecutors;", "Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "executor", "Lcom/clevertap/android/sdk/task/CTExecutors;", "(Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/task/CTExecutors;)V", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "clearFileAssets", "", SentryReplayEvent.JsonKeys.URLS, "", "", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "url", "stop", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FileCleanupStrategyExecutors implements FileCleanupStrategy {
    private static final String TAG = "fileCleanupExecutor";
    private final CTExecutors executor;
    private final FileResourceProvider fileResourceProvider;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FileCleanupStrategyExecutors(FileResourceProvider fileResourceProvider) {
        this(fileResourceProvider, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public FileResourceProvider getFileResourceProvider() {
        return this.fileResourceProvider;
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public void stop() {
    }

    public FileCleanupStrategyExecutors(FileResourceProvider fileResourceProvider, CTExecutors executor) {
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.fileResourceProvider = fileResourceProvider;
        this.executor = executor;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ FileCleanupStrategyExecutors(FileResourceProvider fileResourceProvider, CTExecutors cTExecutors, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            cTExecutors = CTExecutorFactory.executorResourceDownloader();
            Intrinsics.checkNotNullExpressionValue(cTExecutors, "executorResourceDownloader()");
        }
        this(fileResourceProvider, cTExecutors);
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public void clearFileAssets(List<String> urls, final Function1<? super String, Unit> successBlock) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(successBlock, "successBlock");
        for (final String str : urls) {
            this.executor.ioTaskNonUi().execute(TAG, new Callable() { // from class: com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategyExecutors$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return FileCleanupStrategyExecutors.clearFileAssets$lambda$0(this.f$0, str, successBlock);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearFileAssets$lambda$0(FileCleanupStrategyExecutors this$0, String url, Function1 successBlock) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(successBlock, "$successBlock");
        this$0.getFileResourceProvider().deleteData(url);
        successBlock.invoke(url);
        return Unit.INSTANCE;
    }
}
