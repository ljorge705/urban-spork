package com.rnmaps.maps;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.BitmapDescriptor;

/* loaded from: classes6.dex */
public interface ImageReadable {
    void setIconBitmap(Bitmap bitmap);

    void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor);

    void update();
}
