package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.network.DownloadedBitmap;
import java.io.InputStream;
import java.net.HttpURLConnection;
import kotlin.Metadata;

/* compiled from: IBitmapInputStreamReader.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/IBitmapInputStreamReader;", "", "readInputStream", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "inputStream", "Ljava/io/InputStream;", "connection", "Ljava/net/HttpURLConnection;", "downloadStartTimeInMilliseconds", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface IBitmapInputStreamReader {
    DownloadedBitmap readInputStream(InputStream inputStream, HttpURLConnection connection, long downloadStartTimeInMilliseconds);
}
