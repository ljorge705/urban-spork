package com.uxcam.screenshot.helper;

import android.graphics.Bitmap;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0007R(\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/uxcam/screenshot/helper/BitmapSource;", "", "Landroid/graphics/Bitmap;", "removeFromQueue", "bitmap", "", "add", "", "count", "<set-?>", "b", "Landroid/graphics/Bitmap;", "getLastFrameCache", "()Landroid/graphics/Bitmap;", "lastFrameCache", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class BitmapSource {

    /* renamed from: a, reason: collision with root package name */
    public final ConcurrentLinkedQueue<Bitmap> f265a = new ConcurrentLinkedQueue<>();

    /* renamed from: b, reason: from kotlin metadata */
    public Bitmap lastFrameCache;

    public final int count() {
        return this.f265a.size();
    }

    public final Bitmap getLastFrameCache() {
        return this.lastFrameCache;
    }

    public final Bitmap removeFromQueue() {
        if (this.f265a.isEmpty()) {
            return null;
        }
        return this.f265a.remove();
    }

    public final void add(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.lastFrameCache = bitmap;
        this.f265a.add(bitmap);
    }
}
