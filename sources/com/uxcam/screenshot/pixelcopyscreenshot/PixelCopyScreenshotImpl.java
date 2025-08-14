package com.uxcam.screenshot.pixelcopyscreenshot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import androidx.core.internal.view.SupportMenu;
import com.uxcam.screenshot.floatingpanel.FloatingPanelData;
import com.uxcam.screenshot.floatingpanel.FloatingPanelDataProvider;
import com.uxcam.screenshot.floatingpanel.FloatingPanelRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/pixelcopyscreenshot/PixelCopyScreenshotImpl;", "Lcom/uxcam/screenshot/pixelcopyscreenshot/PixelCopyScreenshot;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class PixelCopyScreenshotImpl implements PixelCopyScreenshot {
    public static final void a(FloatingPanelRenderer floatingPanelRenderer, PixelCopyScreenshotConfig config, PixelCopyScreenshotImpl this$0, int i) {
        ArrayList<FloatingPanelData> arrayList;
        Intrinsics.checkNotNullParameter(floatingPanelRenderer, "$floatingPanelRenderer");
        Intrinsics.checkNotNullParameter(config, "$config");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            Activity activity = (Activity) config.e;
            Canvas canvas = config.b;
            FloatingPanelDataProvider floatingPanelDataProvider = floatingPanelRenderer.f262a;
            floatingPanelDataProvider.getClass();
            try {
                arrayList = floatingPanelDataProvider.a(activity);
            } catch (Exception unused) {
                arrayList = new ArrayList();
            }
            if (!arrayList.isEmpty()) {
                try {
                    FloatingPanelData floatingPanelData = null;
                    for (FloatingPanelData floatingPanelData2 : arrayList) {
                        if (floatingPanelData2.c.type == 2) {
                            FloatingPanelRenderer.a(canvas, floatingPanelData2);
                            floatingPanelData = floatingPanelData2;
                        }
                    }
                    if (floatingPanelData != null) {
                        canvas.drawColor(Color.argb((int) (floatingPanelData.c.dimAmount * 255.0f), 0, 0, 0));
                        FloatingPanelRenderer.a(canvas, floatingPanelData);
                    }
                } catch (Exception unused2) {
                }
            }
            List<RectF> list = config.d;
            Canvas canvas2 = config.b;
            this$0.getClass();
            if (!list.isEmpty()) {
                Paint paint = new Paint();
                paint.setColor(SupportMenu.CATEGORY_MASK);
                paint.setStrokeWidth(3.0f);
                paint.setStyle(Paint.Style.FILL);
                Iterator<RectF> it = list.iterator();
                while (it.hasNext()) {
                    canvas2.drawRect(it.next(), paint);
                }
            }
        }
        config.c.onScreenshotTaken(config.f275a);
    }

    @Override // com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshot
    public final void a(final PixelCopyScreenshotConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        final FloatingPanelRenderer floatingPanelRenderer = new FloatingPanelRenderer(FloatingPanelDataProvider.d);
        Context context = config.e;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        PixelCopy.request(((Activity) context).getWindow(), config.f275a, new PixelCopy.OnPixelCopyFinishedListener() { // from class: com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshotImpl$$ExternalSyntheticLambda0
            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
            public final void onPixelCopyFinished(int i) {
                PixelCopyScreenshotImpl.a(floatingPanelRenderer, config, this, i);
            }
        }, new Handler(Looper.getMainLooper()));
    }
}
