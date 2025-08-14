package com.uxcam.screenaction;

import com.uxcam.screenaction.compose.ComposeScreenActionProvider;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.models.UXCamView;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.uxcam.screenaction.ScreenActionProviderImpl", f = "ScreenActionProviderImpl.kt", i = {0, 0, 0, 0, 0}, l = {73}, m = "acquireComposeScreenActionWithViewSystemFallback", n = {"this", "onResult", "uxCamView", "occludedViews", "currentTime"}, s = {"L$0", "L$1", "L$2", "L$3", "F$0"})
/* loaded from: classes6.dex */
final class ScreenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1 extends ContinuationImpl {

    /* renamed from: a, reason: collision with root package name */
    public ScreenActionProviderImpl f220a;
    public Function1 b;
    public UXCamView c;
    public List d;
    public float e;
    public /* synthetic */ Object f;
    public final /* synthetic */ ScreenActionProviderImpl g;
    public int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1(ScreenActionProviderImpl screenActionProviderImpl, Continuation<? super ScreenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1> continuation) {
        super(continuation);
        this.g = screenActionProviderImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ScreenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1 screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1;
        UXCamView uXCamView;
        float f;
        List<? extends UXCamOccludeView> list;
        Function1<? super ScreenAction, Unit> function1;
        this.f = obj;
        this.h |= Integer.MIN_VALUE;
        ScreenActionProviderImpl screenActionProviderImpl = this.g;
        screenActionProviderImpl.getClass();
        int i = this.h;
        if ((i & Integer.MIN_VALUE) != 0) {
            this.h = i - Integer.MIN_VALUE;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1 = this;
        } else {
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1 = new ScreenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1(screenActionProviderImpl, this);
        }
        Object objA = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.f;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.h;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objA);
            ComposeScreenActionProvider composeScreenActionProvider = screenActionProviderImpl.b;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.f220a = screenActionProviderImpl;
            uXCamView = null;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.b = null;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.c = null;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.d = null;
            f = 0.0f;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.e = 0.0f;
            screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.h = 1;
            objA = composeScreenActionProvider.a(screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1);
            if (objA == coroutine_suspended) {
                return coroutine_suspended;
            }
            list = null;
            function1 = null;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            float f2 = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.e;
            list = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.d;
            uXCamView = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.c;
            function1 = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.b;
            ScreenActionProviderImpl screenActionProviderImpl2 = screenActionProviderImpl$acquireComposeScreenActionWithViewSystemFallback$1.f220a;
            ResultKt.throwOnFailure(objA);
            f = f2;
            screenActionProviderImpl = screenActionProviderImpl2;
        }
        ScreenAction screenAction = (ScreenAction) objA;
        if (screenAction != null) {
            function1.invoke(screenAction);
        } else {
            screenActionProviderImpl.a(function1, uXCamView, f, list);
        }
        return Unit.INSTANCE;
    }
}
