package com.imagepicker;

import android.util.Log;
import com.google.firebase.storage.internal.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
abstract class Metadata {
    protected String datetime;
    protected int height;
    protected int width;

    public abstract String getDateTime();

    public abstract int getHeight();

    public abstract int getWidth();

    Metadata() {
    }

    protected String getDateTimeInUTC(String str, String str2) {
        try {
            Date date = new SimpleDateFormat(str2, Locale.US).parse(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Util.ISO_8601_FORMAT, Locale.US);
            if (date != null) {
                return simpleDateFormat.format(date);
            }
            return null;
        } catch (Exception e) {
            Log.e("RNIP", "Could not parse image datetime to UTC: " + e.getMessage());
            return null;
        }
    }
}
