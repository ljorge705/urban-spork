package com.onfido.android.sdk.capture.utils;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.Image;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u000bH\u0000\u001a\u0018\u0010\f\u001a\u00020\r*\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"INTERNATION_CARD_RATIO", "", "INTERNATION_PASSPORT_RATIO", "showOverlay", "", "Landroid/widget/ImageView;", "rect", "Landroid/graphics/RectF;", "toNV21", "", "Landroid/graphics/Bitmap;", "Landroid/media/Image;", "trimDocument", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageUtilsExtKt {
    private static final float INTERNATION_CARD_RATIO = 0.63084114f;
    private static final float INTERNATION_PASSPORT_RATIO = 0.704f;

    public static final void showOverlay(ImageView imageView, RectF rect) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        Intrinsics.checkNotNullParameter(rect, "rect");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) rect.width(), (int) rect.height());
        int i = (int) rect.left;
        layoutParams.setMargins(i, (int) rect.top, i, 0);
        imageView.setLayoutParams(layoutParams);
        ViewExtensionsKt.toVisible$default(imageView, false, 1, null);
    }

    public static final byte[] toNV21(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        return new ImageUtils().getNV21(bitmap.getWidth(), bitmap.getHeight(), bitmap);
    }

    public static final OnfidoRectF trimDocument(OnfidoRectF onfidoRectF, DocumentType documentType) {
        Intrinsics.checkNotNullParameter(onfidoRectF, "<this>");
        return OnfidoRectF.copy$default(onfidoRectF, 0.0f, onfidoRectF.getTop() + (onfidoRectF.height() - (onfidoRectF.width() * (documentType == DocumentType.PASSPORT ? INTERNATION_PASSPORT_RATIO : INTERNATION_CARD_RATIO))), 0.0f, 0.0f, 13, null);
    }

    public static /* synthetic */ OnfidoRectF trimDocument$default(OnfidoRectF onfidoRectF, DocumentType documentType, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = null;
        }
        return trimDocument(onfidoRectF, documentType);
    }

    public static final byte[] toNV21(Image image) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(image, "<this>");
        int width = image.getWidth();
        int height = image.getHeight();
        int i3 = width * height;
        byte[] bArr = new byte[((i3 / 4) * 2) + i3];
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer, "getBuffer(...)");
        ByteBuffer buffer2 = image.getPlanes()[1].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer2, "getBuffer(...)");
        ByteBuffer buffer3 = image.getPlanes()[2].getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer3, "getBuffer(...)");
        int rowStride = image.getPlanes()[0].getRowStride();
        image.getPlanes()[0].getPixelStride();
        if (rowStride == width) {
            buffer.get(bArr, 0, i3);
            i = i3;
        } else {
            long j = rowStride;
            long j2 = -j;
            i = 0;
            while (i < i3) {
                j2 += j;
                buffer.position((int) j2);
                buffer.get(bArr, i, width);
                i += width;
            }
        }
        int rowStride2 = image.getPlanes()[2].getRowStride();
        int pixelStride = image.getPlanes()[2].getPixelStride();
        image.getPlanes()[1].getRowStride();
        image.getPlanes()[1].getPixelStride();
        if (pixelStride == 2 && rowStride2 == width) {
            i2 = 0;
            if (buffer2.get(0) == buffer3.get(1)) {
                byte b = buffer3.get(1);
                byte b2 = (byte) (~b);
                try {
                    buffer3.put(1, b2);
                    if (buffer2.get(0) == b2) {
                        buffer3.put(1, b);
                        buffer3.position(0);
                        buffer2.position(0);
                        buffer3.get(bArr, i3, 1);
                        buffer2.get(bArr, i3 + 1, buffer2.remaining());
                        return bArr;
                    }
                } catch (RuntimeException unused) {
                }
                buffer3.put(1, b);
            }
        } else {
            i2 = 0;
        }
        int i4 = height / 2;
        for (int i5 = i2; i5 < i4; i5++) {
            int i6 = width / 2;
            for (int i7 = i2; i7 < i6; i7++) {
                int i8 = (i7 * pixelStride) + (i5 * rowStride2);
                int i9 = i + 1;
                bArr[i] = buffer3.get(i8);
                i += 2;
                bArr[i9] = buffer2.get(i8);
            }
        }
        return bArr;
    }
}
