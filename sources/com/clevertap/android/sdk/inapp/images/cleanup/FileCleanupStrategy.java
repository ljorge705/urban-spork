package com.clevertap.android.sdk.inapp.images.cleanup;

import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import io.sentry.SentryReplayEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: FileCleanupStrategy.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J9\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00070\fH&J\b\u0010\u0010\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/cleanup/FileCleanupStrategy;", "", "fileResourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "getFileResourceProvider", "()Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "clearFileAssets", "", SentryReplayEvent.JsonKeys.URLS, "", "", "successBlock", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "url", "stop", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface FileCleanupStrategy {
    void clearFileAssets(List<String> urls, Function1<? super String, Unit> successBlock);

    FileResourceProvider getFileResourceProvider();

    void stop();
}
