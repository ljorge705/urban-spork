package com.onfido.android.sdk.capture.ui.proofOfAddress;

import android.content.ContentResolver;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.webkit.MimeTypeMap;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.onfido.javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0001J#\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\u0010J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0001J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\u001f\u0010\u0015\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0016J\u001a\u0010\u0017\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0001J\u001a\u0010\u0018\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\u0018\u0010\u0019\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;", "", "()V", "fileExtensionPdf", "", "getBitmapFromUri", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "getDocumentUri", "uriString", "getFileExtension", "contentResolver", "Landroid/content/ContentResolver;", "getFileExtension$onfido_capture_sdk_core_release", "isImageFile", "", "isImageFileAndValid", "documentUri", "isImageFileValid", "isImageFileValid$onfido_capture_sdk_core_release", "isPdfFile", "isPdfFileAndValid", "isPdfFileValid", "isValidSupportedFile", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaUtils {
    private final String fileExtensionPdf = "pdf";

    @Inject
    public PoaUtils() {
    }

    private final boolean isImageFileAndValid(ContentResolver contentResolver, Uri documentUri) {
        return isImageFile(contentResolver, documentUri) && isImageFileValid$onfido_capture_sdk_core_release(contentResolver, documentUri);
    }

    private final boolean isPdfFileAndValid(ContentResolver contentResolver, Uri documentUri) {
        return isPdfFile(contentResolver, documentUri) && isPdfFileValid(contentResolver, documentUri);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap getBitmapFromUri(android.content.Context r2, android.net.Uri r3) {
        /*
            r1 = this;
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r0 = 0
            if (r2 == 0) goto L13
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.io.FileNotFoundException -> L18
            if (r2 == 0) goto L13
            java.io.InputStream r2 = r2.openInputStream(r3)     // Catch: java.io.FileNotFoundException -> L18
            goto L14
        L13:
            r2 = r0
        L14:
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r2)     // Catch: java.io.FileNotFoundException -> L18
        L18:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils.getBitmapFromUri(android.content.Context, android.net.Uri):android.graphics.Bitmap");
    }

    public final Uri getDocumentUri(Object uriString) {
        if (!(uriString instanceof String)) {
            Intrinsics.checkNotNull(uriString, "null cannot be cast to non-null type android.net.Uri");
            return (Uri) uriString;
        }
        Uri uri = Uri.parse((String) uriString);
        Intrinsics.checkNotNull(uri);
        return uri;
    }

    public final String getFileExtension$onfido_capture_sdk_core_release(ContentResolver contentResolver, Object uriString) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver != null ? contentResolver.getType(getDocumentUri(uriString)) : null);
    }

    public final boolean isImageFile(ContentResolver contentResolver, Object uriString) {
        String type = contentResolver != null ? contentResolver.getType(getDocumentUri(uriString)) : null;
        if (type != null) {
            return StringsKt.contains$default((CharSequence) type, (CharSequence) "image/", false, 2, (Object) null);
        }
        return false;
    }

    public final boolean isImageFileValid$onfido_capture_sdk_core_release(ContentResolver contentResolver, Uri documentUri) throws FileNotFoundException {
        InputStream inputStreamOpenInputStream;
        Intrinsics.checkNotNullParameter(documentUri, "documentUri");
        if (contentResolver != null) {
            try {
                inputStreamOpenInputStream = contentResolver.openInputStream(documentUri);
            } catch (FileNotFoundException unused) {
            }
        } else {
            inputStreamOpenInputStream = null;
        }
        return BitmapFactory.decodeStream(inputStreamOpenInputStream) != null;
    }

    public final boolean isPdfFile(ContentResolver contentResolver, Object uriString) {
        return Intrinsics.areEqual(getFileExtension$onfido_capture_sdk_core_release(contentResolver, uriString), this.fileExtensionPdf);
    }

    public final boolean isPdfFileValid(ContentResolver contentResolver, Uri uri) throws IOException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver != null ? contentResolver.openFileDescriptor(uri, "r") : null;
        try {
            Intrinsics.checkNotNull(parcelFileDescriptorOpenFileDescriptor);
            new PdfRenderer(parcelFileDescriptorOpenFileDescriptor);
            parcelFileDescriptorOpenFileDescriptor.close();
            return true;
        } catch (Exception unused) {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            return false;
        } catch (Throwable th) {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            throw th;
        }
    }

    public final boolean isValidSupportedFile(ContentResolver contentResolver, Uri documentUri) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(documentUri, "documentUri");
        return isPdfFileAndValid(contentResolver, documentUri) || isImageFileAndValid(contentResolver, documentUri);
    }
}
