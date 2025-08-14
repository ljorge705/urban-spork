package com.uxcam.screenshot.legacyscreenshot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.uxcam.screenaction.utils.ReflectionUtil;
import com.uxcam.screenshot.flutterviewfinder.FlutterConfig;
import io.flutter.embedding.android.FlutterSurfaceView;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/Job;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$2", f = "LegacyScreenshotImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class LegacyScreenshotImpl$drawViewOnCanvas$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {

    /* renamed from: a, reason: collision with root package name */
    public /* synthetic */ Object f273a;
    public final /* synthetic */ LegacyScreenshotImpl b;
    public final /* synthetic */ LegacyScreenshotConfig c;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$2$1", f = "LegacyScreenshotImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$2$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LegacyScreenshotImpl f274a;
        public final /* synthetic */ LegacyScreenshotConfig b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LegacyScreenshotImpl legacyScreenshotImpl, LegacyScreenshotConfig legacyScreenshotConfig, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.f274a = legacyScreenshotImpl;
            this.b = legacyScreenshotConfig;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.f274a, this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws SecurityException {
            List<WeakReference<FlutterSurfaceView>> list;
            FlutterConfig flutterConfig;
            FlutterConfig flutterConfig2;
            List<WeakReference<FlutterView>> list2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            LegacyScreenshotImpl legacyScreenshotImpl = this.f274a;
            LegacyScreenshotConfig legacyScreenshotConfig = this.b;
            legacyScreenshotImpl.getClass();
            Canvas canvas = legacyScreenshotConfig.c;
            if (legacyScreenshotConfig.i && (flutterConfig2 = legacyScreenshotConfig.d) != null && (list2 = flutterConfig2.f263a) != null && !list2.isEmpty()) {
                int[] iArr = new int[2];
                try {
                    for (WeakReference<FlutterView> weakReference : legacyScreenshotConfig.d.f263a) {
                        FlutterView flutterView = weakReference.get();
                        if (flutterView != null) {
                            flutterView.getLocationOnScreen(iArr);
                        }
                        FlutterView flutterView2 = weakReference.get();
                        FlutterNativeView flutterNativeView = flutterView2 != null ? flutterView2.getFlutterNativeView() : null;
                        Method declaredMethod = flutterNativeView != null ? flutterNativeView.getClass().getDeclaredMethod("getFlutterJNI", null) : null;
                        if (declaredMethod != null) {
                            declaredMethod.setAccessible(true);
                        }
                        Object objInvoke = declaredMethod != null ? declaredMethod.invoke(flutterNativeView, new Object[0]) : null;
                        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type io.flutter.embedding.engine.FlutterJNI");
                        Bitmap bitmap = ((FlutterJNI) objInvoke).getBitmap();
                        Intrinsics.checkNotNullExpressionValue(bitmap, "flutterJNI.getBitmap()");
                        canvas.drawBitmap(bitmap, iArr[0], iArr[1], (Paint) null);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            }
            LegacyScreenshotImpl legacyScreenshotImpl2 = this.f274a;
            LegacyScreenshotConfig legacyScreenshotConfig2 = this.b;
            legacyScreenshotImpl2.getClass();
            Canvas canvas2 = legacyScreenshotConfig2.c;
            FlutterConfig flutterConfig3 = legacyScreenshotConfig2.d;
            if (legacyScreenshotConfig2.i && flutterConfig3 != null && (list = flutterConfig3.b) != null && !list.isEmpty() && (flutterConfig = legacyScreenshotConfig2.d) != null) {
                try {
                    for (WeakReference<FlutterSurfaceView> weakReference2 : flutterConfig.b) {
                        Object fieldValueFlutter = ReflectionUtil.getFieldValueFlutter("flutterRenderer", weakReference2.get());
                        Intrinsics.checkNotNull(fieldValueFlutter, "null cannot be cast to non-null type io.flutter.embedding.engine.renderer.FlutterRenderer");
                        FlutterRenderer flutterRenderer = (FlutterRenderer) fieldValueFlutter;
                        int[] iArr2 = new int[2];
                        FlutterSurfaceView flutterSurfaceView = weakReference2.get();
                        if (flutterSurfaceView != null) {
                            flutterSurfaceView.getLocationOnScreen(iArr2);
                        }
                        Bitmap bitmap2 = flutterRenderer.getBitmap();
                        Intrinsics.checkNotNullExpressionValue(bitmap2, "flutterRenderer.getBitmap()");
                        canvas2.drawBitmap(bitmap2, iArr2[0], iArr2[1], (Paint) null);
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyScreenshotImpl$drawViewOnCanvas$2(LegacyScreenshotImpl legacyScreenshotImpl, LegacyScreenshotConfig legacyScreenshotConfig, Continuation<? super LegacyScreenshotImpl$drawViewOnCanvas$2> continuation) {
        super(2, continuation);
        this.b = legacyScreenshotImpl;
        this.c = legacyScreenshotConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LegacyScreenshotImpl$drawViewOnCanvas$2 legacyScreenshotImpl$drawViewOnCanvas$2 = new LegacyScreenshotImpl$drawViewOnCanvas$2(this.b, this.c, continuation);
        legacyScreenshotImpl$drawViewOnCanvas$2.f273a = obj;
        return legacyScreenshotImpl$drawViewOnCanvas$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
        return ((LegacyScreenshotImpl$drawViewOnCanvas$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.f273a, Dispatchers.getMain(), null, new AnonymousClass1(this.b, this.c, null), 2, null);
    }
}
