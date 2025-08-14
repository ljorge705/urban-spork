package androidx.compose.material;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* compiled from: Drawer.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u0011\u0010\b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0011\u0010\u000b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Landroidx/compose/material/BottomDrawerState;", "Landroidx/compose/material/SwipeableState;", "Landroidx/compose/material/BottomDrawerValue;", "initialValue", "confirmStateChange", "Lkotlin/Function1;", "", "(Landroidx/compose/material/BottomDrawerValue;Lkotlin/jvm/functions/Function1;)V", "isClosed", "()Z", "isExpanded", "isOpen", "isOpenEnabled", "nestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection$material_release", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", Constants.KEY_HIDE_CLOSE, "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expand", AbstractCircuitBreaker.PROPERTY_NAME, "Companion", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@ExperimentalMaterialApi
/* loaded from: classes.dex */
public final class BottomDrawerState extends SwipeableState<BottomDrawerValue> {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final NestedScrollConnection nestedScrollConnection;

    /* renamed from: getNestedScrollConnection$material_release, reason: from getter */
    public final NestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    public /* synthetic */ BottomDrawerState(BottomDrawerValue bottomDrawerValue, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bottomDrawerValue, (i & 2) != 0 ? new Function1<BottomDrawerValue, Boolean>() { // from class: androidx.compose.material.BottomDrawerState.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(BottomDrawerValue it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(BottomDrawerValue bottomDrawerValue2) {
                return Boolean.valueOf(invoke2(bottomDrawerValue2));
            }
        } : anonymousClass1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomDrawerState(BottomDrawerValue initialValue, Function1<? super BottomDrawerValue, Boolean> confirmStateChange) {
        super(initialValue, DrawerKt.AnimationSpec, confirmStateChange);
        Intrinsics.checkNotNullParameter(initialValue, "initialValue");
        Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
        this.nestedScrollConnection = SwipeableKt.getPreUpPostDownNestedScrollConnection(this);
    }

    public final boolean isOpen() {
        return getCurrentValue() != BottomDrawerValue.Closed;
    }

    public final boolean isClosed() {
        return getCurrentValue() == BottomDrawerValue.Closed;
    }

    public final boolean isExpanded() {
        return getCurrentValue() == BottomDrawerValue.Expanded;
    }

    public final Object open(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = SwipeableState.animateTo$default(this, isOpenEnabled() ? BottomDrawerValue.Open : BottomDrawerValue.Expanded, null, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final Object close(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = SwipeableState.animateTo$default(this, BottomDrawerValue.Closed, null, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = SwipeableState.animateTo$default(this, BottomDrawerValue.Expanded, null, continuation, 2, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    private final boolean isOpenEnabled() {
        return getAnchors$material_release().values().contains(BottomDrawerValue.Open);
    }

    /* compiled from: Drawer.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¨\u0006\n"}, d2 = {"Landroidx/compose/material/BottomDrawerState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material/BottomDrawerState;", "Landroidx/compose/material/BottomDrawerValue;", "confirmStateChange", "Lkotlin/Function1;", "", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<BottomDrawerState, BottomDrawerValue> Saver(final Function1<? super BottomDrawerValue, Boolean> confirmStateChange) {
            Intrinsics.checkNotNullParameter(confirmStateChange, "confirmStateChange");
            return SaverKt.Saver(new Function2<SaverScope, BottomDrawerState, BottomDrawerValue>() { // from class: androidx.compose.material.BottomDrawerState$Companion$Saver$1
                @Override // kotlin.jvm.functions.Function2
                public final BottomDrawerValue invoke(SaverScope Saver, BottomDrawerState it) {
                    Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getCurrentValue();
                }
            }, new Function1<BottomDrawerValue, BottomDrawerState>() { // from class: androidx.compose.material.BottomDrawerState$Companion$Saver$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final BottomDrawerState invoke(BottomDrawerValue it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new BottomDrawerState(it, confirmStateChange);
                }
            });
        }
    }
}
