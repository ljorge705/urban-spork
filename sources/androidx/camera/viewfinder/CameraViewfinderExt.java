package androidx.camera.viewfinder;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: CameraViewfinderExt.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Landroidx/camera/viewfinder/CameraViewfinderExt;", "", "()V", "requestSurface", "Landroid/view/Surface;", "Landroidx/camera/viewfinder/CameraViewfinder;", "viewfinderSurfaceRequest", "Landroidx/camera/viewfinder/ViewfinderSurfaceRequest;", "(Landroidx/camera/viewfinder/CameraViewfinder;Landroidx/camera/viewfinder/ViewfinderSurfaceRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-viewfinder_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CameraViewfinderExt {
    public static final CameraViewfinderExt INSTANCE = new CameraViewfinderExt();

    /* compiled from: CameraViewfinderExt.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.viewfinder.CameraViewfinderExt", f = "CameraViewfinderExt.kt", i = {}, l = {31}, m = "requestSurface", n = {}, s = {})
    /* renamed from: androidx.camera.viewfinder.CameraViewfinderExt$requestSurface$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CameraViewfinderExt.this.requestSurface(null, null, this);
        }
    }

    private CameraViewfinderExt() {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object requestSurface(androidx.camera.viewfinder.CameraViewfinder r5, androidx.camera.viewfinder.ViewfinderSurfaceRequest r6, kotlin.coroutines.Continuation<? super android.view.Surface> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.camera.viewfinder.CameraViewfinderExt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            androidx.camera.viewfinder.CameraViewfinderExt$requestSurface$1 r0 = (androidx.camera.viewfinder.CameraViewfinderExt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            androidx.camera.viewfinder.CameraViewfinderExt$requestSurface$1 r0 = new androidx.camera.viewfinder.CameraViewfinderExt$requestSurface$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r7)
            com.google.common.util.concurrent.ListenableFuture r5 = r5.requestSurfaceAsync(r6)
            java.lang.String r6 = "requestSurfaceAsync(viewfinderSurfaceRequest)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r0.label = r3
            java.lang.Object r7 = androidx.concurrent.futures.ListenableFutureKt.await(r5, r0)
            if (r7 != r1) goto L47
            return r1
        L47:
            java.lang.String r5 = "requestSurfaceAsync(view…erSurfaceRequest).await()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.viewfinder.CameraViewfinderExt.requestSurface(androidx.camera.viewfinder.CameraViewfinder, androidx.camera.viewfinder.ViewfinderSurfaceRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
