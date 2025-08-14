package androidx.compose.runtime;

import androidx.compose.runtime.collection.MutableVector;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DerivedState.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000e\u001a.\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\f0\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000e\u001a0\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u000eH\u0082\b¢\u0006\u0004\b\u0015\u0010\u0016\u001aj\u0010\u0017\u001a\u00020\b\"\u0004\b\u0000\u0010\u00122%\u0010\u0018\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u000b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\b0\u00062%\u0010\u001b\u001a!\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u000b¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\b0\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u000eH\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"J\u0010\u0003\u001a>\u0012:\u00128\u00124\u00122\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005j\u0002`\t0\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*d\b\u0002\u0010\u001c\".\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u00060\u00052.\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005¨\u0006\u001d"}, d2 = {"calculationBlockNestedLevel", "Landroidx/compose/runtime/SnapshotThreadLocal;", "", "derivedStateObservers", "Landroidx/compose/runtime/collection/MutableVector;", "Lkotlin/Pair;", "Lkotlin/Function1;", "Landroidx/compose/runtime/DerivedState;", "", "Landroidx/compose/runtime/DerivedStateObservers;", "derivedStateOf", "Landroidx/compose/runtime/State;", ExifInterface.GPS_DIRECTION_TRUE, "calculation", "Lkotlin/Function0;", "policy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "notifyObservers", "R", "derivedState", "block", "notifyObservers$SnapshotStateKt__DerivedStateKt", "(Landroidx/compose/runtime/DerivedState;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "observeDerivedStateRecalculations", ViewProps.START, "Lkotlin/ParameterName;", "name", "done", "DerivedStateObservers", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
/* loaded from: classes.dex */
final /* synthetic */ class SnapshotStateKt__DerivedStateKt {
    private static final SnapshotThreadLocal<Integer> calculationBlockNestedLevel = new SnapshotThreadLocal<>();
    private static final SnapshotThreadLocal<MutableVector<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>>> derivedStateObservers = new SnapshotThreadLocal<>();

    public static final <T> State<T> derivedStateOf(Function0<? extends T> calculation) {
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        return new DerivedSnapshotState(calculation, null);
    }

    public static final <T> State<T> derivedStateOf(SnapshotMutationPolicy<T> policy, Function0<? extends T> calculation) {
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(calculation, "calculation");
        return new DerivedSnapshotState(calculation, policy);
    }

    private static final <R> R notifyObservers$SnapshotStateKt__DerivedStateKt(DerivedState<?> derivedState, Function0<? extends R> function0) {
        MutableVector mutableVector = (MutableVector) derivedStateObservers.get();
        int i = 0;
        if (mutableVector == null) {
            mutableVector = new MutableVector(new Pair[0], 0);
        }
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i2 = 0;
            do {
                ((Function1) ((Pair) content[i2]).component1()).invoke(derivedState);
                i2++;
            } while (i2 < size);
        }
        try {
            R rInvoke = function0.invoke();
            InlineMarker.finallyStart(1);
            int size2 = mutableVector.getSize();
            if (size2 > 0) {
                Object[] content2 = mutableVector.getContent();
                do {
                    ((Function1) ((Pair) content2[i]).component2()).invoke(derivedState);
                    i++;
                } while (i < size2);
            }
            InlineMarker.finallyEnd(1);
            return rInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            int size3 = mutableVector.getSize();
            if (size3 > 0) {
                Object[] content3 = mutableVector.getContent();
                do {
                    ((Function1) ((Pair) content3[i]).component2()).invoke(derivedState);
                    i++;
                } while (i < size3);
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final <R> void observeDerivedStateRecalculations(Function1<? super State<?>, Unit> start, Function1<? super State<?>, Unit> done, Function0<? extends R> block) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(done, "done");
        Intrinsics.checkNotNullParameter(block, "block");
        SnapshotThreadLocal<MutableVector<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>>> snapshotThreadLocal = derivedStateObservers;
        MutableVector<Pair<Function1<DerivedState<?>, Unit>, Function1<DerivedState<?>, Unit>>> mutableVector = snapshotThreadLocal.get();
        if (mutableVector == null) {
            mutableVector = new MutableVector<>(new Pair[16], 0);
            snapshotThreadLocal.set(mutableVector);
        }
        try {
            mutableVector.add(TuplesKt.to(start, done));
            block.invoke();
        } finally {
            mutableVector.removeAt(mutableVector.getSize() - 1);
        }
    }
}
