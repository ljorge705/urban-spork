package com.imagepicker;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;

/* loaded from: classes2.dex */
public class ImageMetadata extends Metadata {
    @Override // com.imagepicker.Metadata
    public int getHeight() {
        return 0;
    }

    @Override // com.imagepicker.Metadata
    public int getWidth() {
        return 0;
    }

    public ImageMetadata(Uri uri, Context context) {
        try {
            String attribute = new ExifInterface(context.getContentResolver().openInputStream(uri)).getAttribute(ExifInterface.TAG_DATETIME);
            if (attribute != null) {
                this.datetime = getDateTimeInUTC(attribute, "yyyy:MM:dd HH:mm:ss");
            }
        } catch (Exception e) {
            Log.e("RNIP", "Could not load image metadata: " + e.getMessage());
        }
    }

    @Override // com.imagepicker.Metadata
    public String getDateTime() {
        return this.datetime;
    }
}
