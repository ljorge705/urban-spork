package com.rnmaps.maps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.clevertap.android.sdk.Constants;
import java.io.ByteArrayOutputStream;

/* loaded from: classes6.dex */
public class ImageUtil {
    public static Bitmap convert(String str) throws IllegalArgumentException {
        byte[] bArrDecode = Base64.decode(str.substring(str.indexOf(Constants.SEPARATOR_COMMA) + 1), 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
