package com.uxcam.screenaction.compose;

import com.uxcam.screenaction.models.GestureData;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@DebugMetadata(c = "com.uxcam.screenaction.compose.RadiographyFork$getScreenAction$2", f = "RadiographyFork.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class RadiographyFork$getScreenAction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RadiographyFork f243a;
    public final /* synthetic */ ScannableView b;
    public final /* synthetic */ GestureData c;
    public final /* synthetic */ List<ComposeScreenAction> d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RadiographyFork$getScreenAction$2(RadiographyFork radiographyFork, ScannableView scannableView, GestureData gestureData, List<ComposeScreenAction> list, Continuation<? super RadiographyFork$getScreenAction$2> continuation) {
        super(2, continuation);
        this.f243a = radiographyFork;
        this.b = scannableView;
        this.c = gestureData;
        this.d = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RadiographyFork$getScreenAction$2(this.f243a, this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((RadiographyFork$getScreenAction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        RadiographyFork radiographyFork = this.f243a;
        ScannableView scannableView = this.b;
        GestureData gestureData = this.c;
        radiographyFork.getClass();
        ComposeScreenAction composeScreenActionA = RadiographyFork.a(scannableView, gestureData);
        if (composeScreenActionA != null) {
            return Boxing.boxBoolean(this.d.add(composeScreenActionA));
        }
        return null;
    }
}
