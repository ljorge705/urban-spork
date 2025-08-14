package com.uxcam.screenshot.surface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.PixelCopy;
import android.view.SurfaceView;
import io.flutter.embedding.android.FlutterSurfaceView;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/surface/SurfaceSnapshotDrawer;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class SurfaceSnapshotDrawer {

    /* renamed from: a, reason: collision with root package name */
    public final SurfaceView f283a;
    public final Canvas b;

    public SurfaceSnapshotDrawer(FlutterSurfaceView surfaceView, Canvas canvas) {
        Intrinsics.checkNotNullParameter(surfaceView, "surfaceView");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.f283a = surfaceView;
        this.b = canvas;
    }

    public final Object a(Continuation<? super Boolean> continuation) throws Throwable {
        final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        a(new Function1<Boolean, Unit>() { // from class: com.uxcam.screenshot.surface.SurfaceSnapshotDrawer$draw$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                Boolean boolValueOf = Boolean.valueOf(bool.booleanValue());
                Continuation<Boolean> continuation2 = safeContinuation;
                Result.Companion companion = Result.INSTANCE;
                continuation2.resumeWith(Result.m6095constructorimpl(boolValueOf));
                return Unit.INSTANCE;
            }
        });
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    public final void a(final Function1<? super Boolean, Unit> function1) {
        if (this.f283a.getVisibility() != 0 || this.f283a.getWidth() <= 0 || this.f283a.getHeight() <= 0) {
            return;
        }
        final int[] iArr = new int[2];
        this.f283a.getLocationOnScreen(iArr);
        final Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.f283a.getWidth(), this.f283a.getHeight(), Bitmap.Config.ARGB_8888);
        final HandlerThread handlerThread = new HandlerThread("SurfaceSnapshotDrawer-PixelCopy");
        PixelCopy.OnPixelCopyFinishedListener onPixelCopyFinishedListener = new PixelCopy.OnPixelCopyFinishedListener() { // from class: com.uxcam.screenshot.surface.SurfaceSnapshotDrawer$$ExternalSyntheticLambda0
            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
            public final void onPixelCopyFinished(int i) {
                SurfaceSnapshotDrawer.a(this.f$0, bitmapCreateBitmap, iArr, function1, handlerThread, i);
            }
        };
        handlerThread.start();
        PixelCopy.request(this.f283a, bitmapCreateBitmap, onPixelCopyFinishedListener, new Handler(handlerThread.getLooper()));
    }

    public static final void a(SurfaceSnapshotDrawer this$0, Bitmap bitmap, int[] location, Function1 function1, HandlerThread handlerThread, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(location, "$location");
        Intrinsics.checkNotNullParameter(handlerThread, "$handlerThread");
        if (i == 0) {
            this$0.b.drawBitmap(bitmap, location[0], location[1], (Paint) null);
            if (function1 != null) {
                function1.invoke(Boolean.TRUE);
            }
        } else if (function1 != null) {
            function1.invoke(Boolean.FALSE);
        }
        handlerThread.quitSafely();
    }
}
