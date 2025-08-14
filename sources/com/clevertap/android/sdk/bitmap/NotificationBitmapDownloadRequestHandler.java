package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NotificationBitmapDownloadRequestHandler.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/NotificationBitmapDownloadRequestHandler;", "Lcom/clevertap/android/sdk/bitmap/IBitmapDownloadRequestHandler;", "iBitmapDownloadRequestHandler", "(Lcom/clevertap/android/sdk/bitmap/IBitmapDownloadRequestHandler;)V", "handleRequest", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "bitmapDownloadRequest", "Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequest;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class NotificationBitmapDownloadRequestHandler implements IBitmapDownloadRequestHandler {
    private final IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler;

    public NotificationBitmapDownloadRequestHandler(IBitmapDownloadRequestHandler iBitmapDownloadRequestHandler) {
        Intrinsics.checkNotNullParameter(iBitmapDownloadRequestHandler, "iBitmapDownloadRequestHandler");
        this.iBitmapDownloadRequestHandler = iBitmapDownloadRequestHandler;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    public DownloadedBitmap handleRequest(BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in NotificationBitmapDownloadRequestHandler....");
        String bitmapPath = bitmapDownloadRequest.getBitmapPath();
        boolean fallbackToAppIcon = bitmapDownloadRequest.getFallbackToAppIcon();
        Context context = bitmapDownloadRequest.getContext();
        String str = bitmapPath;
        if (str == null || StringsKt.isBlank(str)) {
            DownloadedBitmap downloadedBitmapPostFallbackIconCheck = Utils.getDownloadedBitmapPostFallbackIconCheck(fallbackToAppIcon, context, DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_IMAGE));
            Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck, "getDownloadedBitmapPostF…s(NO_IMAGE)\n            )");
            return downloadedBitmapPostFallbackIconCheck;
        }
        DownloadedBitmap downloadedBitmapPostFallbackIconCheck2 = Utils.getDownloadedBitmapPostFallbackIconCheck(fallbackToAppIcon, context, this.iBitmapDownloadRequestHandler.handleRequest(bitmapDownloadRequest));
        Intrinsics.checkNotNullExpressionValue(downloadedBitmapPostFallbackIconCheck2, "getDownloadedBitmapPostF…ontext, downloadedBitmap)");
        return downloadedBitmapPostFallbackIconCheck2;
    }
}
