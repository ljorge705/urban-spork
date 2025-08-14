package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.Task;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapDownloadRequestHandlerWithTimeLimit.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequestHandlerWithTimeLimit;", "Lcom/clevertap/android/sdk/bitmap/IBitmapDownloadRequestHandler;", "iBitmapDownloadRequestHandler", "(Lcom/clevertap/android/sdk/bitmap/IBitmapDownloadRequestHandler;)V", "handleRequest", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "bitmapDownloadRequest", "Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequest;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class BitmapDownloadRequestHandlerWithTimeLimit implements IBitmapDownloadRequestHandler {
    private final IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler;

    public BitmapDownloadRequestHandlerWithTimeLimit(IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler) {
        Intrinsics.checkNotNullParameter(iBitmapDownloadRequestHandler, "iBitmapDownloadRequestHandler");
        this.iBitmapDownloadRequestHandler = iBitmapDownloadRequestHandler;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    public DownloadedBitmap handleRequest(final BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in BitmapDownloadRequestHandlerWithTimeLimit....");
        boolean fallbackToAppIcon = bitmapDownloadRequest.getFallbackToAppIcon();
        Context context = bitmapDownloadRequest.getContext();
        CleverTapInstanceConfig instanceConfig = bitmapDownloadRequest.getInstanceConfig();
        long downloadTimeLimitInMillis = bitmapDownloadRequest.getDownloadTimeLimitInMillis();
        if (instanceConfig == null || downloadTimeLimitInMillis == -1) {
            Logger.v("either config is null or downloadTimeLimitInMillis is negative.");
            Logger.v("will download bitmap without time limit");
            return this.iBitmapDownloadRequestHandler.handleRequest(bitmapDownloadRequest);
        }
        Task taskIoTask = CTExecutorFactory.executors(instanceConfig).ioTask();
        Intrinsics.checkNotNullExpressionValue(taskIoTask, "executors(instanceConfig).ioTask()");
        DownloadedBitmap downloadedBitmapNullBitmapWithStatus = (DownloadedBitmap) taskIoTask.submitAndGetResult("getNotificationBitmap", new Callable() { // from class: com.clevertap.android.sdk.bitmap.BitmapDownloadRequestHandlerWithTimeLimit$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BitmapDownloadRequestHandlerWithTimeLimit.handleRequest$lambda$0(this.f$0, bitmapDownloadRequest);
            }
        }, downloadTimeLimitInMillis);
        if (downloadedBitmapNullBitmapWithStatus == null) {
            downloadedBitmapNullBitmapWithStatus = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
        }
        DownloadedBitmap downloadedBitmapPostFallbackIconCheck = Utils.getDownloadedBitmapPostFallbackIconCheck(fallbackToAppIcon, context, downloadedBitmapNullBitmapWithStatus);
        Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck, "getDownloadedBitmapPostF…ontext, downloadedBitmap)");
        return downloadedBitmapPostFallbackIconCheck;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DownloadedBitmap handleRequest$lambda$0(BitmapDownloadRequestHandlerWithTimeLimit this$0, BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "$bitmapDownloadRequest");
        return this$0.iBitmapDownloadRequestHandler.handleRequest(bitmapDownloadRequest);
    }
}
