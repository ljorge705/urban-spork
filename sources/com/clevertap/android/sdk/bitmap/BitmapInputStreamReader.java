package com.clevertap.android.sdk.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapInputStreamReader.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/BitmapInputStreamReader;", "Lcom/clevertap/android/sdk/bitmap/IBitmapInputStreamReader;", "nextBitmapInputStreamReader", "checkDownloadCompleteness", "", "(Lcom/clevertap/android/sdk/bitmap/IBitmapInputStreamReader;Z)V", "getDownloadedBitmapFromStream", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "dataReadFromStream", "Ljava/io/ByteArrayOutputStream;", "downloadStartTimeInMilliseconds", "", "readInputStream", "inputStream", "Ljava/io/InputStream;", "connection", "Ljava/net/HttpURLConnection;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class BitmapInputStreamReader implements IBitmapInputStreamReader {
    private final boolean checkDownloadCompleteness;
    private final IBitmapInputStreamReader nextBitmapInputStreamReader;

    /* JADX WARN: Multi-variable type inference failed */
    public BitmapInputStreamReader() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public BitmapInputStreamReader(IBitmapInputStreamReader iBitmapInputStreamReader, boolean z) {
        this.nextBitmapInputStreamReader = iBitmapInputStreamReader;
        this.checkDownloadCompleteness = z;
    }

    public /* synthetic */ BitmapInputStreamReader(IBitmapInputStreamReader iBitmapInputStreamReader, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : iBitmapInputStreamReader, (i & 2) != 0 ? false : z);
    }

    @Override // com.clevertap.android.sdk.bitmap.IBitmapInputStreamReader
    public DownloadedBitmap readInputStream(InputStream inputStream, HttpURLConnection connection, long downloadStartTimeInMilliseconds) throws IOException {
        DownloadedBitmap inputStream2;
        int contentLength;
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Logger.v("reading bitmap input stream in BitmapInputStreamReader....");
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 == -1) {
                break;
            }
            i += i2;
            byteArrayOutputStream.write(bArr, 0, i2);
            Logger.v("Downloaded " + i + " bytes");
        }
        Logger.v("Total download size for bitmap = " + i);
        if (!this.checkDownloadCompleteness || (contentLength = connection.getContentLength()) == -1 || contentLength == i) {
            IBitmapInputStreamReader iBitmapInputStreamReader = this.nextBitmapInputStreamReader;
            return (iBitmapInputStreamReader == null || (inputStream2 = iBitmapInputStreamReader.readInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), connection, downloadStartTimeInMilliseconds)) == null) ? getDownloadedBitmapFromStream(byteArrayOutputStream, downloadStartTimeInMilliseconds) : inputStream2;
        }
        Logger.d("File not loaded completely not going forward. URL was: " + connection.getURL());
        return DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.DOWNLOAD_FAILED);
    }

    private final DownloadedBitmap getDownloadedBitmapFromStream(ByteArrayOutputStream dataReadFromStream, long downloadStartTimeInMilliseconds) {
        byte[] byteArray = dataReadFromStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        DownloadedBitmapFactory downloadedBitmapFactory = DownloadedBitmapFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return DownloadedBitmapFactory.successBitmap$default(downloadedBitmapFactory, bitmap, Utils.getNowInMillis() - downloadStartTimeInMilliseconds, null, 4, null);
    }
}
