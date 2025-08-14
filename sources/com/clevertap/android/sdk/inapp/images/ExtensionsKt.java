package com.clevertap.android.sdk.inapp.images;

import android.graphics.BitmapFactory;
import java.io.File;
import kotlin.Metadata;

/* compiled from: Extensions.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"hasValidBitmap", "", "Ljava/io/File;", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class ExtensionsKt {
    public static final boolean hasValidBitmap(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        return (options.outWidth == -1 || options.outHeight == -1) ? false : true;
    }
}
