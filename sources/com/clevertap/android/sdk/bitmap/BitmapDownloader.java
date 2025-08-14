package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapDownloader.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/BitmapDownloader;", "", "httpUrlConnectionParams", "Lcom/clevertap/android/sdk/bitmap/HttpUrlConnectionParams;", "bitmapInputStreamReader", "Lcom/clevertap/android/sdk/bitmap/IBitmapInputStreamReader;", "sizeConstrainedPair", "Lkotlin/Pair;", "", "", "(Lcom/clevertap/android/sdk/bitmap/HttpUrlConnectionParams;Lcom/clevertap/android/sdk/bitmap/IBitmapInputStreamReader;Lkotlin/Pair;)V", "connection", "Ljava/net/HttpURLConnection;", "downloadStartTimeInMilliseconds", "", "srcUrl", "", "createConnection", "url", "Ljava/net/URL;", "downloadBitmap", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class BitmapDownloader {
    private final IBitmapInputStreamReader bitmapInputStreamReader;
    private HttpURLConnection connection;
    private long downloadStartTimeInMilliseconds;
    private final HttpUrlConnectionParams httpUrlConnectionParams;
    private final Pair<Boolean, Integer> sizeConstrainedPair;
    private String srcUrl;

    public BitmapDownloader(HttpUrlConnectionParams httpUrlConnectionParams, IBitmapInputStreamReader bitmapInputStreamReader, Pair<Boolean, Integer> sizeConstrainedPair) {
        Intrinsics.checkNotNullParameter(httpUrlConnectionParams, "httpUrlConnectionParams");
        Intrinsics.checkNotNullParameter(bitmapInputStreamReader, "bitmapInputStreamReader");
        Intrinsics.checkNotNullParameter(sizeConstrainedPair, "sizeConstrainedPair");
        this.httpUrlConnectionParams = httpUrlConnectionParams;
        this.bitmapInputStreamReader = bitmapInputStreamReader;
        this.sizeConstrainedPair = sizeConstrainedPair;
    }

    public /* synthetic */ BitmapDownloader(HttpUrlConnectionParams httpUrlConnectionParams, IBitmapInputStreamReader iBitmapInputStreamReader, Pair pair, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpUrlConnectionParams, iBitmapInputStreamReader, (i & 4) != 0 ? new Pair(false, 0) : pair);
    }

    public final DownloadedBitmap downloadBitmap(String srcUrl) {
        Intrinsics.checkNotNullParameter(srcUrl, "srcUrl");
        Logger.v("initiating bitmap download in BitmapDownloader....");
        this.srcUrl = srcUrl;
        this.downloadStartTimeInMilliseconds = Utils.getNowInMillis();
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnectionCreateConnection = createConnection(new URL(srcUrl));
            this.connection = httpURLConnectionCreateConnection;
            if (httpURLConnectionCreateConnection == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
                httpURLConnectionCreateConnection = null;
            }
            httpURLConnectionCreateConnection.connect();
            if (httpURLConnectionCreateConnection.getResponseCode() != 200) {
                Logger.d("File not loaded completely not going forward. URL was: " + srcUrl);
                DownloadedBitmap downloadedBitmapNullBitmapWithStatus = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
                HttpURLConnection httpURLConnection2 = this.connection;
                if (httpURLConnection2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("connection");
                } else {
                    httpURLConnection = httpURLConnection2;
                }
                httpURLConnection.disconnect();
                return downloadedBitmapNullBitmapWithStatus;
            }
            Logger.v("Downloading " + srcUrl + "....");
            int contentLength = httpURLConnectionCreateConnection.getContentLength();
            Pair<Boolean, Integer> pair = this.sizeConstrainedPair;
            boolean zBooleanValue = pair.component1().booleanValue();
            int iIntValue = pair.component2().intValue();
            if (zBooleanValue && contentLength > iIntValue) {
                Logger.v("Image size is larger than " + iIntValue + " bytes. Cancelling download!");
                DownloadedBitmap downloadedBitmapNullBitmapWithStatus2 = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.SIZE_LIMIT_EXCEEDED);
                HttpURLConnection httpURLConnection3 = this.connection;
                if (httpURLConnection3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("connection");
                } else {
                    httpURLConnection = httpURLConnection3;
                }
                httpURLConnection.disconnect();
                return downloadedBitmapNullBitmapWithStatus2;
            }
            IBitmapInputStreamReader iBitmapInputStreamReader = this.bitmapInputStreamReader;
            InputStream inputStream = httpURLConnectionCreateConnection.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
            DownloadedBitmap inputStream2 = iBitmapInputStreamReader.readInputStream(inputStream, httpURLConnectionCreateConnection, this.downloadStartTimeInMilliseconds);
            HttpURLConnection httpURLConnection4 = this.connection;
            if (httpURLConnection4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connection");
            } else {
                httpURLConnection = httpURLConnection4;
            }
            httpURLConnection.disconnect();
            return inputStream2;
        } catch (Throwable th) {
            try {
                Logger.v("Couldn't download the notification icon. URL was: " + srcUrl);
                th.printStackTrace();
                return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
            } finally {
                try {
                    HttpURLConnection httpURLConnection5 = this.connection;
                    if (httpURLConnection5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("connection");
                    } else {
                        httpURLConnection = httpURLConnection5;
                    }
                    httpURLConnection.disconnect();
                } catch (Throwable th2) {
                    Logger.v("Couldn't close connection!", th2);
                }
            }
        }
    }

    private final HttpURLConnection createConnection(URL url) throws IOException {
        URLConnection uRLConnectionOpenConnection = url.openConnection();
        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.setConnectTimeout(this.httpUrlConnectionParams.getConnectTimeout());
        httpURLConnection.setReadTimeout(this.httpUrlConnectionParams.getReadTimeout());
        httpURLConnection.setUseCaches(this.httpUrlConnectionParams.getUseCaches());
        httpURLConnection.setDoInput(this.httpUrlConnectionParams.getDoInput());
        for (Map.Entry<String, String> entry : this.httpUrlConnectionParams.getRequestMap().entrySet()) {
            httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
        return httpURLConnection;
    }
}
