package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Clickable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.ClickableKt$handlePressInteraction$2", f = "Clickable.kt", i = {0, 1, 2}, l = {OnfidoActivity.RESULT_EXIT_INVALID_CERTIFICATE, OnfidoActivity.RESULT_EXIT_MISSING_ONFIDO_CONFIG, 454, 455, 464}, m = "invokeSuspend", n = {"delayJob", FirebaseAnalytics.Param.SUCCESS, "releaseInteraction"}, s = {"L$0", "Z$0", "L$0"})
/* loaded from: classes.dex */
final class ClickableKt$handlePressInteraction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Function0<Boolean>> $delayPressInteraction;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $pressPoint;
    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
    final /* synthetic */ PressGestureScope $this_handlePressInteraction;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ClickableKt$handlePressInteraction$2(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, MutableState<PressInteraction.Press> mutableState, State<? extends Function0<Boolean>> state, Continuation<? super ClickableKt$handlePressInteraction$2> continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScope;
        this.$pressPoint = j;
        this.$interactionSource = mutableInteractionSource;
        this.$pressedInteraction = mutableState;
        this.$delayPressInteraction = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClickableKt$handlePressInteraction$2 clickableKt$handlePressInteraction$2 = new ClickableKt$handlePressInteraction$2(this.$this_handlePressInteraction, this.$pressPoint, this.$interactionSource, this.$pressedInteraction, this.$delayPressInteraction, continuation);
        clickableKt$handlePressInteraction$2.L$0 = obj;
        return clickableKt$handlePressInteraction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ClickableKt$handlePressInteraction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c7 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickableKt$handlePressInteraction$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
