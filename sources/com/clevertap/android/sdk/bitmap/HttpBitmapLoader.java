package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.google.common.net.HttpHeaders;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpBitmapLoader.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/HttpBitmapLoader;", "", "()V", "RESOURCE_CONNECTION_TIMEOUT", "", "RESOURCE_READ_TIMEOUT", "inAppStandardHttpUrlConnectionParams", "Lcom/clevertap/android/sdk/bitmap/HttpUrlConnectionParams;", "standardGzipHttpUrlConnectionParams", "getHttpBitmap", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "bitmapOperation", "Lcom/clevertap/android/sdk/bitmap/HttpBitmapLoader$HttpBitmapOperation;", "bitmapDownloadRequest", "Lcom/clevertap/android/sdk/bitmap/BitmapDownloadRequest;", "HttpBitmapOperation", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class HttpBitmapLoader {
    private static final int RESOURCE_CONNECTION_TIMEOUT = 5000;
    public static final HttpBitmapLoader INSTANCE = new HttpBitmapLoader();
    private static final HttpUrlConnectionParams standardGzipHttpUrlConnectionParams = new HttpUrlConnectionParams(1000, 5000, true, true, MapsKt.mapOf(TuplesKt.to(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate")));
    private static final int RESOURCE_READ_TIMEOUT = 15000;
    private static final HttpUrlConnectionParams inAppStandardHttpUrlConnectionParams = new HttpUrlConnectionParams(5000, RESOURCE_READ_TIMEOUT, true, true, null, 16, null);

    /* compiled from: HttpBitmapLoader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/HttpBitmapLoader$HttpBitmapOperation;", "", "(Ljava/lang/String;I)V", "DOWNLOAD_NOTIFICATION_BITMAP", "DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT", "DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP", "DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT", "DOWNLOAD_INAPP_BITMAP", "DOWNLOAD_ANY_BITMAP", "DOWNLOAD_BYTES", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum HttpBitmapOperation {
        DOWNLOAD_NOTIFICATION_BITMAP,
        DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT,
        DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP,
        DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT,
        DOWNLOAD_INAPP_BITMAP,
        DOWNLOAD_ANY_BITMAP,
        DOWNLOAD_BYTES
    }

    /* compiled from: HttpBitmapLoader.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HttpBitmapOperation.values().length];
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_NOTIFICATION_BITMAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_SIZE_CONSTRAINED_GZIP_NOTIFICATION_BITMAP_WITH_TIME_LIMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_INAPP_BITMAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_ANY_BITMAP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[HttpBitmapOperation.DOWNLOAD_BYTES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private HttpBitmapLoader() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final DownloadedBitmap getHttpBitmap(HttpBitmapOperation bitmapOperation, BitmapDownloadRequest bitmapDownloadRequest) {
        Intrinsics.checkNotNullParameter(bitmapOperation, "bitmapOperation");
        Intrinsics.checkNotNullParameter(bitmapDownloadRequest, "bitmapDownloadRequest");
        int i = 3;
        boolean z = false;
        Logger logger = null;
        switch (WhenMappings.$EnumSwitchMapping$0[bitmapOperation.ordinal()]) {
            case 1:
                return new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(standardGzipHttpUrlConnectionParams, new BitmapInputStreamDecoder(false, false, null, 7, null), null, 4, null))).handleRequest(bitmapDownloadRequest);
            case 2:
                return new BitmapDownloadRequestHandlerWithTimeLimit(new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(standardGzipHttpUrlConnectionParams, new GzipBitmapInputStreamReader(z, null == true ? 1 : 0, i, null == true ? 1 : 0), null, 4, null)))).handleRequest(bitmapDownloadRequest);
            case 3:
                return new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(standardGzipHttpUrlConnectionParams, new GzipBitmapInputStreamReader(z, null == true ? 1 : 0, i, null == true ? 1 : 0), new Pair(true, Integer.valueOf(bitmapDownloadRequest.getDownloadSizeLimitInBytes()))))).handleRequest(bitmapDownloadRequest);
            case 4:
                return new BitmapDownloadRequestHandlerWithTimeLimit(new NotificationBitmapDownloadRequestHandler(new BitmapDownloadRequestHandler(new BitmapDownloader(standardGzipHttpUrlConnectionParams, new GzipBitmapInputStreamReader(z, null == true ? 1 : 0, i, null == true ? 1 : 0), new Pair(true, Integer.valueOf(bitmapDownloadRequest.getDownloadSizeLimitInBytes())))))).handleRequest(bitmapDownloadRequest);
            case 5:
                return new BitmapDownloadRequestHandler(new BitmapDownloader(inAppStandardHttpUrlConnectionParams, new BitmapInputStreamDecoder(true, false, null, 6, null), null, 4, null)).handleRequest(bitmapDownloadRequest);
            case 6:
                return new BitmapDownloadRequestHandler(new BitmapDownloader(standardGzipHttpUrlConnectionParams, new GzipBitmapInputStreamReader(z, logger, i, null == true ? 1 : 0), null, 4, null)).handleRequest(bitmapDownloadRequest);
            case 7:
                return new BitmapDownloadRequestHandler(new BitmapDownloader(inAppStandardHttpUrlConnectionParams, new BitmapInputStreamDecoder(true, false, null, 4, null), null, 4, null)).handleRequest(bitmapDownloadRequest);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
