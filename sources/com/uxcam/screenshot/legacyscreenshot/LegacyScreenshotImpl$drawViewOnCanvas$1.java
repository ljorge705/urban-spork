package com.uxcam.screenshot.legacyscreenshot;

import android.graphics.Paint;
import android.view.View;
import com.uxcam.screenaction.models.ViewRootData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/Job;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$1", f = "LegacyScreenshotImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class LegacyScreenshotImpl$drawViewOnCanvas$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {

    /* renamed from: a, reason: collision with root package name */
    public /* synthetic */ Object f271a;
    public final /* synthetic */ LegacyScreenshotImpl b;
    public final /* synthetic */ LegacyScreenshotConfig c;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @DebugMetadata(c = "com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$1$1", f = "LegacyScreenshotImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$drawViewOnCanvas$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LegacyScreenshotImpl f272a;
        public final /* synthetic */ LegacyScreenshotConfig b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LegacyScreenshotImpl legacyScreenshotImpl, LegacyScreenshotConfig legacyScreenshotConfig, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.f272a = legacyScreenshotImpl;
            this.b = legacyScreenshotConfig;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.f272a, this.b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            LegacyScreenshotImpl legacyScreenshotImpl = this.f272a;
            LegacyScreenshotConfig legacyScreenshotConfig = this.b;
            legacyScreenshotImpl.getClass();
            ViewRootData viewRootData = legacyScreenshotConfig.f269a;
            if (viewRootData != null) {
                View view = viewRootData.getView();
                view.setDrawingCacheEnabled(true);
                legacyScreenshotConfig.c.drawBitmap(view.getDrawingCache(), 0.0f, 0.0f, (Paint) null);
                view.setDrawingCacheEnabled(false);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyScreenshotImpl$drawViewOnCanvas$1(LegacyScreenshotImpl legacyScreenshotImpl, LegacyScreenshotConfig legacyScreenshotConfig, Continuation<? super LegacyScreenshotImpl$drawViewOnCanvas$1> continuation) {
        super(2, continuation);
        this.b = legacyScreenshotImpl;
        this.c = legacyScreenshotConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LegacyScreenshotImpl$drawViewOnCanvas$1 legacyScreenshotImpl$drawViewOnCanvas$1 = new LegacyScreenshotImpl$drawViewOnCanvas$1(this.b, this.c, continuation);
        legacyScreenshotImpl$drawViewOnCanvas$1.f271a = obj;
        return legacyScreenshotImpl$drawViewOnCanvas$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
        return ((LegacyScreenshotImpl$drawViewOnCanvas$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.f271a, Dispatchers.getMain(), null, new AnonymousClass1(this.b, this.c, null), 2, null);
    }
}
