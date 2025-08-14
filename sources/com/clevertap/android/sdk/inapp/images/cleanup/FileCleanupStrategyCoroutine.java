package com.clevertap.android.sdk.inapp.images.cleanup;

import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import com.clevertap.android.sdk.utils.CtDefaultDispatchers;
import com.clevertap.android.sdk.utils.DispatcherProvider;
import io.sentry.SentryReplayEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: FileCleanupStrategyCoroutine.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J9\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u0012H\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategyCoroutine;", "Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "dispatchers", "Lcom/clevertap/android/sdk/utils/DispatcherProvider;", "(Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;Lcom/clevertap/android/sdk/utils/DispatcherProvider;)V", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "jobs", "", "Lkotlinx/coroutines/Job;", "clearFileAssets", "", SentryReplayEvent.JsonKeys.URLS, "", "", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "url", "stop", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class FileCleanupStrategyCoroutine implements FileCleanupStrategy {
    private final DispatcherProvider dispatchers;
    private final FileResourceProvider fileResourceProvider;
    private List<Job> jobs;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FileCleanupStrategyCoroutine(FileResourceProvider fileResourceProvider) {
        this(fileResourceProvider, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public FileResourceProvider getFileResourceProvider() {
        return this.fileResourceProvider;
    }

    public FileCleanupStrategyCoroutine(FileResourceProvider fileResourceProvider, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(fileResourceProvider, "fileResourceProvider");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.fileResourceProvider = fileResourceProvider;
        this.dispatchers = dispatchers;
        this.jobs = new ArrayList();
    }

    public /* synthetic */ FileCleanupStrategyCoroutine(FileResourceProvider fileResourceProvider, CtDefaultDispatchers ctDefaultDispatchers, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fileResourceProvider, (i & 2) != 0 ? new CtDefaultDispatchers() : ctDefaultDispatchers);
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public void clearFileAssets(List<String> urls, Function1<? super String, Unit> successBlock) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(successBlock, "successBlock");
        this.jobs.add(BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.dispatchers.io()), null, null, new FileCleanupStrategyCoroutine$clearFileAssets$job$1(urls, this, successBlock, null), 3, null));
    }

    @Override // com.clevertap.android.sdk.inapp.images.cleanup.FileCleanupStrategy
    public void stop() {
        Iterator<T> it = this.jobs.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
        }
    }
}
