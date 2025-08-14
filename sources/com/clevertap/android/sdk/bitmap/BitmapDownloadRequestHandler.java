package com.clevertap.android.sdk.bitmap;

import android.content.Context;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import com.clevertap.android.sdk.network.NetworkManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BitmapDownloadRequestHandler.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequestHandler;", "Lcom/clevertap/android/sdk/bitmap/IBitmapDownloadRequestHandler;", "bitmapDownloader", "Lcom/clevertap/android/sdk/bitmap/BitmapDownloader;", "(Lcom/clevertap/android/sdk/bitmap/BitmapDownloader;)V", "handleRequest", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "bitmapDownloadRequest", "Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequest;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public class BitmapDownloadRequestHandler implements IBitmapDownloadRequestHandler {
    private final BitmapDownloader bitmapDownloader;

    public BitmapDownloadRequestHandler(BitmapDownloader bitmapDownloader) {
        Intrinsics.checkNotNullParameter(bitmapDownloader, "bitmapDownloader");
        this.bitmapDownloader = bitmapDownloader;
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapDownloadRequestHandler
    public DownloadedBitmap handleRequest(BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        Logger.v("handling bitmap download request in BitmapDownloadRequestHandler....");
        String bitmapPath = bitmapDownloadRequest.getBitmapPath();
        Context context = bitmapDownloadRequest.getContext();
        String str = bitmapPath;
        if (str == null || StringsKt.isBlank(str)) {
            return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_IMAGE);
        }
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(bitmapPath, "///", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null), "http:/", "http://", false, 4, (Object) null), "https:/", "https://", false, 4, (Object) null);
        if (context != null && !NetworkManager.isNetworkOnline(context)) {
            Logger.v("Network connectivity unavailable. Not downloading bitmap. URL was: " + strReplace$default);
            return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.NO_NETWORK);
        }
        return this.bitmapDownloader.downloadBitmap(strReplace$default);
    }
}
