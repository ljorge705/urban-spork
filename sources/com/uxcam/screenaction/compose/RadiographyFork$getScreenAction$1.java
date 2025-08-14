package com.uxcam.screenaction.compose;

import com.uxcam.screenaction.models.GestureData;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.uxcam.screenaction.compose.RadiographyFork", f = "RadiographyFork.kt", i = {0, 0, 0}, l = {22}, m = "getScreenAction", n = {"this", "gestureData", "composeScreenActions"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes6.dex */
final class RadiographyFork$getScreenAction$1 extends ContinuationImpl {

    /* renamed from: a, reason: collision with root package name */
    public RadiographyFork f242a;
    public GestureData b;
    public List c;
    public Iterator d;
    public /* synthetic */ Object e;
    public final /* synthetic */ RadiographyFork f;
    public int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RadiographyFork$getScreenAction$1(RadiographyFork radiographyFork, Continuation<? super RadiographyFork$getScreenAction$1> continuation) {
        super(continuation);
        this.f = radiographyFork;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        this.g |= Integer.MIN_VALUE;
        return this.f.a(this);
    }
}
