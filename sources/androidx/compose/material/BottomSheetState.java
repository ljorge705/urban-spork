package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BottomSheetScaffold.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0011\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Landroidx/compose/material/BottomSheetState;", "Landroidx/compose/material/SwipeableState;", "Landroidx/compose/material/BottomSheetValue;", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material/BottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;)V", "isCollapsed", "()Z", "isExpanded", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection$material_release", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "collapse", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expand", "Companion", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@ExperimentalMaterialApi
/* loaded from: classes.dex */
public final class BottomSheetState extends SwipeableState<BottomSheetValue> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final NestedScrollConnection nestedScrollConnection;

    /* renamed from: getNestedScrollConnection$material_release, reason: from getter */
    public final NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    public /* synthetic */ BottomSheetState(BottomSheetValue bottomSheetValue, SpringSpec<Float> springSpec, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bottomSheetValue, (i & 2) != 0 ? SwipeableDefaults.INSTANCE.getAnimationSpec() : springSpec, (i & 4) != 0 ? new Function1<BottomSheetValue, Boolean>() { // from class: androidx.compose.material.BottomSheetState.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(BottomSheetValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(BottomSheetValue bottomSheetValue2) {
                return Boolean.valueOf(invoke2(bottomSheetValue2));
            }
        } : anonymousClass1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomSheetState(BottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super BottomSheetValue, Boolean> confirmStateChange) {
        super(initialValue, animationSpec, confirmStateChange);
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        this.nestedScrollConnection = SwipeableKt.getPreUpPostDownNestedScrollConnection(this);
    }

    public final boolean isExpanded() {
        return getCurrentValue() == BottomSheetValue.Expanded;
    }

    public final boolean isCollapsed() {
        return getCurrentValue() == BottomSheetValue.Collapsed;
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = SwipeableState.animateTo$default(this, BottomSheetValue.Expanded, null, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final Object collapse(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = SwipeableState.animateTo$default(this, BottomSheetValue.Collapsed, null, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    /* compiled from: BottomSheetScaffold.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¨\u0006\r"}, d2 = {"Landroidx/compose/material/BottomSheetState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/BottomSheetState;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmStateChange", "Lkotlin/Function1;", "Landroidx/compose/material/BottomSheetValue;", "", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<BottomSheetState, ?> Saver(final AnimationSpec<Float> animationSpec, final Function1<? super BottomSheetValue, Boolean> confirmStateChange) {
            Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
            Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
            return SaverKt.Saver(new Function2<SaverScope, BottomSheetState, BottomSheetValue>() { // from class: androidx.compose.material.BottomSheetState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final BottomSheetValue invoke(SaverScope Saver, BottomSheetState it) {
                    Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getCurrentValue();
                }
            }, new Function1<BottomSheetValue, BottomSheetState>() { // from class: androidx.compose.material.BottomSheetState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final BottomSheetState invoke(BottomSheetValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new BottomSheetState(it, animationSpec, confirmStateChange);
                }
            });
        }
    }
}
