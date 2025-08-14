package androidx.compose.material;

import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: Slider.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.SliderKt$SliderThumb$1$1$1", f = "Slider.kt", i = {}, l = {PhotoshopDirectory.TAG_WIN_DEVMODE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SliderKt$SliderThumb$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ SnapshotStateList<Interaction> $interactions;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SliderKt$SliderThumb$1$1$1(MutableInteractionSource mutableInteractionSource, SnapshotStateList<Interaction> snapshotStateList, Continuation<? super SliderKt$SliderThumb$1$1$1> continuation) {
        super(2, continuation);
        this.$interactionSource = mutableInteractionSource;
        this.$interactions = snapshotStateList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SliderKt$SliderThumb$1$1$1(this.$interactionSource, this.$interactions, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SliderKt$SliderThumb$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<Interaction> interactions = this.$interactionSource.getInteractions();
            final SnapshotStateList<Interaction> snapshotStateList = this.$interactions;
            this.label = 1;
            if (interactions.collect(new FlowCollector<Interaction>() { // from class: androidx.compose.material.SliderKt$SliderThumb$1$1$1$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public Object emit(Interaction interaction, Continuation<? super Unit> continuation) {
                    Interaction interaction2 = interaction;
                    if (interaction2 instanceof PressInteraction.Press) {
                        snapshotStateList.add(interaction2);
                    } else if (interaction2 instanceof PressInteraction.Release) {
                        snapshotStateList.remove(((PressInteraction.Release) interaction2).getPress());
                    } else if (interaction2 instanceof PressInteraction.Cancel) {
                        snapshotStateList.remove(((PressInteraction.Cancel) interaction2).getPress());
                    } else if (interaction2 instanceof DragInteraction.Start) {
                        snapshotStateList.add(interaction2);
                    } else if (interaction2 instanceof DragInteraction.Stop) {
                        snapshotStateList.remove(((DragInteraction.Stop) interaction2).getStart());
                    } else if (interaction2 instanceof DragInteraction.Cancel) {
                        snapshotStateList.remove(((DragInteraction.Cancel) interaction2).getStart());
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
