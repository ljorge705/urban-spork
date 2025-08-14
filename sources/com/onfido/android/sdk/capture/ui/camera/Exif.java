package com.onfido.android.sdk.capture.ui.camera;

import android.media.ExifInterface;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Exif {
    private static final String TAG = "CameraExif";

    public static void copy(ExifInterface exifInterface, ExifInterface exifInterface2, String... strArr) throws IllegalAccessException, SecurityException, IOException, IllegalArgumentException {
        String str;
        String attribute;
        Object obj;
        try {
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArr) {
                arrayList.add(str2.toLowerCase(Locale.ROOT));
            }
            for (Field field : exifInterface.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().startsWith("TAG_")) {
                    try {
                        obj = field.get(exifInterface);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        str = null;
                    }
                    if (obj instanceof String) {
                        str = (String) obj;
                        if (str != null && (attribute = exifInterface.getAttribute(str)) != null && !arrayList.contains(str.toLowerCase(Locale.ROOT))) {
                            exifInterface2.setAttribute(str, attribute);
                        }
                    }
                }
            }
            exifInterface2.saveAttributes();
        } catch (RuntimeException e2) {
            Timber.e(e2, "Exif copy failed", new Object[0]);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x005a, code lost:
    
        com.onfido.android.sdk.capture.internal.util.logging.Timber.e("Invalid length", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0061, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0062, code lost:
    
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0063, code lost:
    
        r8 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int exifOrientationIdentifier(byte[] r10) {
        /*
            Method dump skipped, instructions count: 194
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.Exif.exifOrientationIdentifier(byte[]):int");
    }

    private static int pack(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & 255) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }
}
