package com.clevertap.android.sdk.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GzipBitmapInputStreamReader.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0012"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/GzipBitmapInputStreamReader;", "Lcom/clevertap/android/sdk/bitmap/BitmapInputStreamDecoder;", "saveBytes", "", "logger", "Lcom/clevertap/android/sdk/Logger;", "(ZLcom/clevertap/android/sdk/Logger;)V", "getDownloadedBitmapFromStream", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "dataReadFromStream", "Ljava/io/ByteArrayOutputStream;", "downloadStartTimeInMilliseconds", "", "readInputStream", "inputStream", "Ljava/io/InputStream;", "connection", "Ljava/net/HttpURLConnection;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class GzipBitmapInputStreamReader extends BitmapInputStreamDecoder {
    /* JADX WARN: Multi-variable type inference failed */
    public GzipBitmapInputStreamReader() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ GzipBitmapInputStreamReader(boolean z, Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : logger);
    }

    public GzipBitmapInputStreamReader(boolean z, Logger logger) {
        super(z, false, logger, 2, null);
    }

    @Override // com.clevertap.android.sdk.bitmap.BitmapInputStreamDecoder, com.clevertap.android.sdk.bitmap.IBitmapInputStreamReader
    public DownloadedBitmap readInputStream(InputStream inputStream, HttpURLConnection connection, long downloadStartTimeInMilliseconds) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Logger.v("reading bitmap input stream in GzipBitmapInputStreamReader....");
        String contentEncoding = connection.getContentEncoding();
        if (contentEncoding != null && StringsKt.contains$default((CharSequence) contentEncoding, (CharSequence) "gzip", false, 2, (Object) null)) {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
            byte[] bArr = new byte[16384];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = gZIPInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            Logger logger = getLogger();
            if (logger != null) {
                logger.verbose("Total decompressed download size for bitmap from output stream = " + byteArrayOutputStream.size());
            }
            return getDownloadedBitmapFromStream(byteArrayOutputStream, downloadStartTimeInMilliseconds);
        }
        return super.readInputStream(inputStream, connection, downloadStartTimeInMilliseconds);
    }

    private final DownloadedBitmap getDownloadedBitmapFromStream(ByteArrayOutputStream dataReadFromStream, long downloadStartTimeInMilliseconds) {
        byte[] byteArray = dataReadFromStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        DownloadedBitmapFactory downloadedBitmapFactory = DownloadedBitmapFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return DownloadedBitmapFactory.successBitmap$default(downloadedBitmapFactory, bitmap, Utils.getNowInMillis() - downloadStartTimeInMilliseconds, null, 4, null);
    }
}
