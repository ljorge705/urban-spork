package com.uxcam.screenshot.legacyscreenshot;

import android.graphics.Canvas;
import io.flutter.embedding.android.FlutterSurfaceView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1", f = "LegacyScreenshotImpl.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a, reason: collision with root package name */
    public Iterator f270a;
    public int b;
    public final /* synthetic */ List<WeakReference<FlutterSurfaceView>> c;
    public final /* synthetic */ Canvas d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1(List<? extends WeakReference<FlutterSurfaceView>> list, Canvas canvas, Continuation<? super LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1> continuation) {
        super(2, continuation);
        this.c = list;
        this.d = canvas;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1(this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004d -> B:17:0x0052). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.b
            r2 = 1
            if (r1 == 0) goto L1c
            if (r1 != r2) goto L14
            java.util.Iterator r1 = r7.f270a
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r1
            r1 = r0
            r0 = r7
            goto L52
        L14:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1c:
            kotlin.ResultKt.throwOnFailure(r8)
            java.util.List<java.lang.ref.WeakReference<io.flutter.embedding.android.FlutterSurfaceView>> r8 = r7.c
            java.util.Iterator r8 = r8.iterator()
            r1 = r8
            r8 = r7
        L27:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L5f
            java.lang.Object r3 = r1.next()
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            java.lang.Object r3 = r3.get()
            io.flutter.embedding.android.FlutterSurfaceView r3 = (io.flutter.embedding.android.FlutterSurfaceView) r3
            if (r3 == 0) goto L27
            android.graphics.Canvas r4 = r8.d
            com.uxcam.screenshot.surface.SurfaceSnapshotDrawer r5 = new com.uxcam.screenshot.surface.SurfaceSnapshotDrawer
            r5.<init>(r3, r4)
            r8.f270a = r1
            r8.b = r2
            java.lang.Object r3 = r5.a(r8)
            if (r3 != r0) goto L4d
            return r0
        L4d:
            r6 = r0
            r0 = r8
            r8 = r3
            r3 = r1
            r1 = r6
        L52:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8)
            r8 = r0
            r0 = r1
            r1 = r3
            goto L27
        L5f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
