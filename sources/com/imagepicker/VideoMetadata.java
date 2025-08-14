package com.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class VideoMetadata extends Metadata {
    private int bitrate;
    private int duration;

    public int getBitrate() {
        return this.bitrate;
    }

    public int getDuration() {
        return this.duration;
    }

    public VideoMetadata(Uri uri, Context context) throws SecurityException, IOException, IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        Bitmap bitmap = getBitmap(uri, context, mediaMetadataRetriever);
        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
        String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(20);
        String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(5);
        if (strExtractMetadata != null) {
            this.duration = Math.round(Float.parseFloat(strExtractMetadata)) / 1000;
        }
        if (strExtractMetadata2 != null) {
            this.bitrate = Integer.parseInt(strExtractMetadata2);
        }
        if (strExtractMetadata3 != null) {
            this.datetime = getDateTimeInUTC(strExtractMetadata3.substring(0, strExtractMetadata3.indexOf(".")).replace(ExifInterface.GPS_DIRECTION_TRUE, StringUtils.SPACE), "yyyyMMdd HHmmss");
        }
        if (bitmap != null) {
            this.width = bitmap.getWidth();
            this.height = bitmap.getHeight();
        }
        try {
            mediaMetadataRetriever.release();
        } catch (IOException e) {
            Log.e("VideoMetadata", "IO error releasing metadataRetriever", e);
        }
    }

    @Override // com.imagepicker.Metadata
    public String getDateTime() {
        return this.datetime;
    }

    @Override // com.imagepicker.Metadata
    public int getWidth() {
        return this.width;
    }

    @Override // com.imagepicker.Metadata
    public int getHeight() {
        return this.height;
    }

    private Bitmap getBitmap(Uri uri, Context context, MediaMetadataRetriever mediaMetadataRetriever) throws IllegalArgumentException {
        try {
            mediaMetadataRetriever.setDataSource(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()).getFD());
            return mediaMetadataRetriever.getFrameAtTime();
        } catch (IOException | RuntimeException e) {
            Log.e("RNIP", "Could not retrieve width and height from video: " + e.getMessage());
            return null;
        }
    }
}
