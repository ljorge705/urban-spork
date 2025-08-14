package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.SnapshotThreadLocal;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.firebase.messaging.Constants;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.SentryStackTrace;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Snapshot.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\n\u001a\b\u0010$\u001a\u00020\bH\u0002\u001a6\u0010$\u001a\u0002H%\"\u0004\b\u0000\u0010%2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H%0\u000fH\u0002¢\u0006\u0002\u0010'\u001a4\u0010(\u001a\u00020\u00072\b\u0010)\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\b\b\u0002\u0010+\u001a\u00020,H\u0002\u001a\u001f\u0010-\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.2\u0006\u0010/\u001a\u0002H%H\u0001¢\u0006\u0002\u00100\u001a'\u0010-\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.2\u0006\u0010/\u001a\u0002H%2\u0006\u00101\u001a\u00020\u0007H\u0001¢\u0006\u0002\u00102\u001a\b\u00103\u001a\u00020\u0007H\u0000\u001aL\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\b\b\u0002\u00106\u001a\u00020,H\u0002\u001aB\u00107\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f2\u0014\u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u000fH\u0002\u001a\u0018\u00109\u001a\u00020\b2\u0006\u00101\u001a\u00020\u00072\u0006\u0010:\u001a\u00020;H\u0001\u001a.\u0010<\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020.\u0018\u00010=2\u0006\u00103\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020\u0010H\u0002\u001a\u0010\u0010A\u001a\u00020,2\u0006\u0010:\u001a\u00020;H\u0002\u001a\b\u0010B\u001a\u00020CH\u0002\u001a1\u0010D\u001a\u0004\u0018\u0001H%\"\b\b\u0000\u0010%*\u00020.2\u0006\u0010/\u001a\u0002H%2\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010F\u001a\u0010\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\u0001H\u0000\u001a\b\u0010I\u001a\u00020CH\u0002\u001a%\u0010J\u001a\u0002H%\"\u0004\b\u0000\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0KH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010L\u001a>\u0010M\u001a\u0002H%\"\u0004\b\u0000\u0010%2\u0006\u0010N\u001a\u00020\u00072!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H%0\u000fH\u0002¢\u0006\u0002\u0010O\u001a:\u0010P\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020\u00072!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u0002H%0\u000fH\u0002¢\u0006\u0002\u0010Q\u001a\u0018\u0010R\u001a\u00020\u00012\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0000\u001a\u0012\u0010S\u001a\u0004\u0018\u00010.2\u0006\u0010:\u001a\u00020;H\u0002\u001a \u0010T\u001a\u00020,2\u0006\u0010U\u001a\u00020.2\u0006\u00101\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u001a \u0010T\u001a\u00020,2\u0006\u00103\u001a\u00020\u00012\u0006\u0010V\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u001a\u0010\u0010W\u001a\u00020\b2\u0006\u00101\u001a\u00020\u0007H\u0002\u001a\u001c\u0010X\u001a\u00020\u0010*\u00020\u00102\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010Z\u001a\u00020\u0001H\u0000\u001a#\u0010[\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\u0010\\\u001a+\u0010]\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010^\u001a+\u0010_\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010^\u001aN\u0010`\u001a\u0002Ha\"\b\b\u0000\u0010%*\u00020.\"\u0004\b\u0001\u0010a*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u0010b\u001a\u0002H%2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002Ha0\u000f¢\u0006\u0002\bcH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010d\u001a3\u0010e\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u00072\u0006\u0010b\u001a\u0002H%H\u0000¢\u0006\u0002\u0010f\u001a!\u0010D\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;¢\u0006\u0002\u0010\\\u001a)\u0010D\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u0007¢\u0006\u0002\u0010^\u001aH\u0010g\u001a\u0002Ha\"\b\b\u0000\u0010%*\u00020.\"\u0004\b\u0001\u0010a*\u0002H%2!\u0010&\u001a\u001d\u0012\u0013\u0012\u0011H%¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002Ha0\u000fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010h\u001aF\u0010i\u001a\u0002Ha\"\b\b\u0000\u0010%*\u00020.\"\u0004\b\u0001\u0010a*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002Ha0\u000f¢\u0006\u0002\bcH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010j\u001aN\u0010i\u001a\u0002Ha\"\b\b\u0000\u0010%*\u00020.\"\u0004\b\u0001\u0010a*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u00072\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002Ha0\u000f¢\u0006\u0002\bcH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010k\u001a+\u0010l\u001a\u0002H%\"\b\b\u0000\u0010%*\u00020.*\u0002H%2\u0006\u0010:\u001a\u00020;2\u0006\u00101\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010^\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\",\u0010\u0002\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\".\u0010\t\u001a\"\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nj\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b`\rX\u0082\u0004¢\u0006\u0002\n\u0000\")\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000f0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0015\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u001e\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0017\u001a\u0004\b \u0010!\"\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006m"}, d2 = {"INVALID_SNAPSHOT", "", "applyObservers", "", "Lkotlin/Function2;", "", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "", "currentGlobalSnapshot", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "kotlin.jvm.PlatformType", "Landroidx/compose/runtime/AtomicReference;", "emptyLambda", "Lkotlin/Function1;", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "Lkotlin/ParameterName;", "name", Constants.COLLATION_INVALID, "globalWriteObservers", SentryStackFrame.JsonKeys.LOCK, "getLock$annotations", "()V", "getLock", "()Ljava/lang/Object;", "nextSnapshotId", "openSnapshots", "pinningTable", "Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "snapshotInitializer", "getSnapshotInitializer$annotations", "getSnapshotInitializer", "()Landroidx/compose/runtime/snapshots/Snapshot;", "threadSnapshot", "Landroidx/compose/runtime/SnapshotThreadLocal;", "advanceGlobalSnapshot", ExifInterface.GPS_DIRECTION_TRUE, "block", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "createTransparentSnapshotWithNoParentReadObserver", "previousSnapshot", "readObserver", "ownsPreviousSnapshot", "", SentryThread.JsonKeys.CURRENT, "Landroidx/compose/runtime/snapshots/StateRecord;", "r", "(Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", SentryStackTrace.JsonKeys.SNAPSHOT, "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "currentSnapshot", "mergedReadObserver", "parentObserver", "mergeReadObserver", "mergedWriteObserver", "writeObserver", "notifyWrite", "state", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "applyingSnapshot", "invalidSnapshots", "overwriteUnusedRecordsLocked", "readError", "", "readable", "id", "(Landroidx/compose/runtime/snapshots/StateRecord;ILandroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/StateRecord;", "releasePinningLocked", "handle", "reportReadonlySnapshotWrite", BaseJavaModule.METHOD_TYPE_SYNC, "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "takeNewGlobalSnapshot", "previousGlobalSnapshot", "(Landroidx/compose/runtime/snapshots/Snapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "takeNewSnapshot", "(Lkotlin/jvm/functions/Function1;)Landroidx/compose/runtime/snapshots/Snapshot;", "trackPinning", "usedLocked", "valid", "data", "candidateSnapshot", "validateOpen", "addRange", Constants.MessagePayloadKeys.FROM, "until", "newOverwritableRecordLocked", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;)Landroidx/compose/runtime/snapshots/StateRecord;", "newWritableRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "newWritableRecordLocked", "overwritable", "R", "candidate", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "overwritableRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Landroidx/compose/runtime/snapshots/StateRecord;)Landroidx/compose/runtime/snapshots/StateRecord;", "withCurrent", "(Landroidx/compose/runtime/snapshots/StateRecord;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writable", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Landroidx/compose/runtime/snapshots/StateRecord;Landroidx/compose/runtime/snapshots/StateObject;Landroidx/compose/runtime/snapshots/Snapshot;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writableRecord", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SnapshotKt {
    private static final int INVALID_SNAPSHOT = 0;
    private static final AtomicReference<GlobalSnapshot> currentGlobalSnapshot;
    private static int nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static final Snapshot snapshotInitializer;
    private static final Function1<SnapshotIdSet, Unit> emptyLambda = new Function1<SnapshotIdSet, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SnapshotIdSet it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SnapshotIdSet snapshotIdSet) {
            invoke2(snapshotIdSet);
            return Unit.INSTANCE;
        }
    };
    private static final SnapshotThreadLocal<Snapshot> threadSnapshot = new SnapshotThreadLocal<>();
    private static final Object lock = new Object();
    private static final SnapshotDoubleIndexHeap pinningTable = new SnapshotDoubleIndexHeap();
    private static final List<Function2<Set<? extends Object>, Snapshot, Unit>> applyObservers = new ArrayList();
    private static final List<Function1<Object, Unit>> globalWriteObservers = new ArrayList();

    public static final Object getLock() {
        return lock;
    }

    public static /* synthetic */ void getLock$annotations() {
    }

    public static final Snapshot getSnapshotInitializer() {
        return snapshotInitializer;
    }

    public static /* synthetic */ void getSnapshotInitializer$annotations() {
    }

    public static final int trackPinning(int i, SnapshotIdSet invalid) {
        int iAdd;
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        int iLowest = invalid.lowest(i);
        synchronized (getLock()) {
            iAdd = pinningTable.add(iLowest);
        }
        return iAdd;
    }

    public static final void releasePinningLocked(int i) {
        pinningTable.remove(i);
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = threadSnapshot.get();
        if (snapshot != null) {
            return snapshot;
        }
        GlobalSnapshot globalSnapshot = currentGlobalSnapshot.get();
        Intrinsics.checkNotNullExpressionValue(globalSnapshot, "currentGlobalSnapshot.get()");
        return globalSnapshot;
    }

    static {
        openSnapshots = SnapshotIdSet.INSTANCE.getEMPTY();
        nextSnapshotId = 1;
        int i = nextSnapshotId;
        nextSnapshotId = i + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i, SnapshotIdSet.INSTANCE.getEMPTY());
        openSnapshots = openSnapshots.set(globalSnapshot.getId());
        AtomicReference<GlobalSnapshot> atomicReference = new AtomicReference<>(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        GlobalSnapshot globalSnapshot2 = atomicReference.get();
        Intrinsics.checkNotNullExpressionValue(globalSnapshot2, "currentGlobalSnapshot.get()");
        snapshotInitializer = globalSnapshot2;
    }

    static /* synthetic */ Snapshot createTransparentSnapshotWithNoParentReadObserver$default(Snapshot snapshot, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return createTransparentSnapshotWithNoParentReadObserver(snapshot, function1, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1<Object, Unit> function1, boolean z) {
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (z2 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z2 ? (MutableSnapshot) snapshot : null, function1, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, function1, false, z);
    }

    static /* synthetic */ Function1 mergedReadObserver$default(Function1 function1, Function1 function12, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return mergedReadObserver(function1, function12, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Object, Unit> mergedReadObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function12, boolean z) {
        if (!z) {
            function12 = null;
        }
        if (function1 == null || function12 == null || Intrinsics.areEqual(function1, function12)) {
            return function1 == null ? function12 : function1;
        }
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedReadObserver.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object state) {
                Intrinsics.checkNotNullParameter(state, "state");
                function1.invoke(state);
                function12.invoke(state);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<Object, Unit> mergedWriteObserver(final Function1<Object, Unit> function1, final Function1<Object, Unit> function12) {
        if (function1 == null || function12 == null || Intrinsics.areEqual(function1, function12)) {
            return function1 == null ? function12 : function1;
        }
        return new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedWriteObserver.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object state) {
                Intrinsics.checkNotNullParameter(state, "state");
                function1.invoke(state);
                function12.invoke(state);
            }
        };
    }

    public static final <T> T sync(Function0<? extends T> block) {
        T tInvoke;
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (getLock()) {
            try {
                tInvoke = block.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return tInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T takeNewGlobalSnapshot(Snapshot snapshot, Function1<? super SnapshotIdSet, ? extends T> function1) {
        T tInvoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (getLock()) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            openSnapshots = openSnapshots.clear(snapshot.getId());
            currentGlobalSnapshot.set(new GlobalSnapshot(i, openSnapshots));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i);
            Unit unit = Unit.INSTANCE;
        }
        return tInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T advanceGlobalSnapshot(Function1<? super SnapshotIdSet, ? extends T> function1) {
        GlobalSnapshot globalSnapshot;
        T t;
        List mutableList;
        Snapshot snapshot = snapshotInitializer;
        Intrinsics.checkNotNull(snapshot, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.GlobalSnapshot");
        synchronized (getLock()) {
            globalSnapshot = currentGlobalSnapshot.get();
            Intrinsics.checkNotNullExpressionValue(globalSnapshot, "currentGlobalSnapshot.get()");
            t = (T) takeNewGlobalSnapshot(globalSnapshot, function1);
        }
        Set<StateObject> modified$runtime_release = globalSnapshot.getModified$runtime_release();
        if (modified$runtime_release != null) {
            synchronized (getLock()) {
                mutableList = CollectionsKt.toMutableList((Collection) applyObservers);
            }
            int size = mutableList.size();
            for (int i = 0; i < size; i++) {
                ((Function2) mutableList.get(i)).invoke(modified$runtime_release, globalSnapshot);
            }
        }
        synchronized (getLock()) {
            if (modified$runtime_release != null) {
                Iterator<T> it = modified$runtime_release.iterator();
                while (it.hasNext()) {
                    overwriteUnusedRecordsLocked((StateObject) it.next());
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void advanceGlobalSnapshot() {
        advanceGlobalSnapshot(new Function1<SnapshotIdSet, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot.3
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SnapshotIdSet it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SnapshotIdSet snapshotIdSet) {
                invoke2(snapshotIdSet);
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends Snapshot> T takeNewSnapshot(final Function1<? super SnapshotIdSet, ? extends T> function1) {
        return (T) advanceGlobalSnapshot(new Function1<SnapshotIdSet, T>() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.takeNewSnapshot.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Incorrect return type in method signature: (Landroidx/compose/runtime/snapshots/SnapshotIdSet;)TT; */
            @Override // kotlin.jvm.functions.Function1
            public final Snapshot invoke(SnapshotIdSet invalid) {
                Intrinsics.checkNotNullParameter(invalid, "invalid");
                Snapshot snapshot = (Snapshot) function1.invoke(invalid);
                synchronized (SnapshotKt.getLock()) {
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
                    Unit unit = Unit.INSTANCE;
                }
                return snapshot;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOpen(Snapshot snapshot) {
        if (!openSnapshots.get(snapshot.getId())) {
            throw new IllegalStateException("Snapshot is not open".toString());
        }
    }

    private static final boolean valid(int i, int i2, SnapshotIdSet snapshotIdSet) {
        return (i2 == 0 || i2 > i || snapshotIdSet.get(i2)) ? false : true;
    }

    private static final boolean valid(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        return valid(i, stateRecord.getSnapshotId(), snapshotIdSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends StateRecord> T readable(T t, int i, SnapshotIdSet snapshotIdSet) {
        T t2 = null;
        while (t != null) {
            if (valid(t, i, snapshotIdSet) && (t2 == null || t2.getSnapshotId() < t.getSnapshotId())) {
                t2 = t;
            }
            t = (T) t.getNext();
        }
        if (t2 != null) {
            return t2;
        }
        return null;
    }

    public static final <T extends StateRecord> T readable(T t, StateObject state) {
        T t2;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Snapshot current = Snapshot.INSTANCE.getCurrent();
        Function1<Object, Unit> readObserver$runtime_release = current.getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(state);
        }
        T t3 = (T) readable(t, current.getId(), current.getInvalid());
        if (t3 != null) {
            return t3;
        }
        synchronized (getLock()) {
            Snapshot current2 = Snapshot.INSTANCE.getCurrent();
            StateRecord firstStateRecord = state.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable$lambda$7");
            t2 = (T) readable(firstStateRecord, current2.getId(), current2.getInvalid());
            if (t2 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
        }
        return t2;
    }

    public static final <T extends StateRecord> T readable(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Function1<Object, Unit> readObserver$runtime_release = snapshot.getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(state);
        }
        T t2 = (T) readable(t, snapshot.getId(), snapshot.getInvalid());
        if (t2 != null) {
            return t2;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied".toString());
    }

    private static final StateRecord usedLocked(StateObject stateObject) {
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        SnapshotIdSet empty = SnapshotIdSet.INSTANCE.getEMPTY();
        StateRecord stateRecord = null;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
            if (firstStateRecord.getSnapshotId() == 0) {
                return firstStateRecord;
            }
            if (valid(firstStateRecord, iLowestOrDefault, empty)) {
                if (stateRecord != null) {
                    return firstStateRecord.getSnapshotId() < stateRecord.getSnapshotId() ? firstStateRecord : stateRecord;
                }
                stateRecord = firstStateRecord;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        StateRecord stateRecord2 = null;
        int i = 0;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
            int snapshotId$runtime_release = firstStateRecord.getSnapshotId();
            if (snapshotId$runtime_release != 0) {
                if (snapshotId$runtime_release > iLowestOrDefault) {
                    i++;
                } else if (stateRecord2 == null) {
                    stateRecord2 = firstStateRecord;
                } else {
                    if (firstStateRecord.getSnapshotId() < stateRecord2.getSnapshotId()) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord;
                    } else {
                        stateRecord = firstStateRecord;
                    }
                    stateRecord2.setSnapshotId$runtime_release(0);
                    stateRecord2.assign(stateRecord);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i < 1;
    }

    public static final <T extends StateRecord> T writableRecord(T t, StateObject state, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        if (snapshot.getReadOnly()) {
            snapshot.mo1534recordModified$runtime_release(state);
        }
        T t2 = (T) readable(t, snapshot.getId(), snapshot.getInvalid());
        if (t2 == null) {
            readError();
            throw new KotlinNothingValueException();
        }
        if (t2.getSnapshotId() == snapshot.getId()) {
            return t2;
        }
        T t3 = (T) newWritableRecord(t2, state, snapshot);
        snapshot.mo1534recordModified$runtime_release(state);
        return t3;
    }

    public static final <T extends StateRecord> T overwritableRecord(T t, StateObject state, Snapshot snapshot, T candidate) {
        T t2;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        if (snapshot.getReadOnly()) {
            snapshot.mo1534recordModified$runtime_release(state);
        }
        int id = snapshot.getId();
        if (candidate.getSnapshotId() == id) {
            return candidate;
        }
        synchronized (getLock()) {
            t2 = (T) newOverwritableRecordLocked(t, state);
        }
        t2.setSnapshotId$runtime_release(id);
        snapshot.mo1534recordModified$runtime_release(state);
        return t2;
    }

    private static final <T extends StateRecord> T newWritableRecordLocked(T t, StateObject stateObject, Snapshot snapshot) {
        T t2 = (T) newOverwritableRecordLocked(t, stateObject);
        t2.assign(t);
        t2.setSnapshotId$runtime_release(snapshot.getId());
        return t2;
    }

    public static final <T extends StateRecord> T newOverwritableRecordLocked(T t, StateObject state) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        T t2 = (T) usedLocked(state);
        if (t2 != null) {
            t2.setSnapshotId$runtime_release(Integer.MAX_VALUE);
            return t2;
        }
        T t3 = (T) t.create();
        t3.setSnapshotId$runtime_release(Integer.MAX_VALUE);
        t3.setNext$runtime_release(state.getFirstStateRecord());
        Intrinsics.checkNotNull(t3, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked$lambda$13");
        state.prependStateRecord(t3);
        Intrinsics.checkNotNull(t3, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked");
        return t3;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject state) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(state, "state");
        Function1<Object, Unit> writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(state);
        }
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject state, Function1<? super T, ? extends R> block) {
        Snapshot current;
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(block, "block");
        getSnapshotInitializer();
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                rInvoke = block.invoke(writableRecord(t, state, current));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, state);
        return rInvoke;
    }

    public static final <T extends StateRecord, R> R overwritable(T t, StateObject state, T candidate, Function1<? super T, ? extends R> block) {
        Snapshot current;
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(block, "block");
        getSnapshotInitializer();
        synchronized (getLock()) {
            try {
                current = Snapshot.INSTANCE.getCurrent();
                rInvoke = block.invoke(overwritableRecord(t, state, current, candidate));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(current, state);
        return rInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<StateRecord, StateRecord> optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord;
        Set<StateObject> modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet snapshotIdSetOr = mutableSnapshot2.getInvalid().set(mutableSnapshot2.getId()).or(mutableSnapshot2.getPreviousIds$runtime_release());
        HashMap map = null;
        for (StateObject stateObject : modified$runtime_release) {
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord stateRecord2 = readable(firstStateRecord, id, snapshotIdSet);
            if (stateRecord2 != null && (stateRecord = readable(firstStateRecord, id, snapshotIdSetOr)) != null && !Intrinsics.areEqual(stateRecord2, stateRecord)) {
                StateRecord stateRecord3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid());
                if (stateRecord3 == null) {
                    readError();
                    throw new KotlinNothingValueException();
                }
                StateRecord stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                if (stateRecordMergeRecords == null) {
                    return null;
                }
                HashMap map2 = map;
                if (map2 == null) {
                    map = new HashMap();
                    map2 = map;
                }
                map2.put(stateRecord2, stateRecordMergeRecords);
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void reportReadonlySnapshotWrite() {
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot".toString());
    }

    public static final <T extends StateRecord> T current(T r, Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(r, "r");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        T t = (T) readable(r, snapshot.getId(), snapshot.getInvalid());
        if (t != null) {
            return t;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final <T extends StateRecord> T current(T r) {
        T t;
        Intrinsics.checkNotNullParameter(r, "r");
        Snapshot current = Snapshot.INSTANCE.getCurrent();
        T t2 = (T) readable(r, current.getId(), current.getInvalid());
        if (t2 != null) {
            return t2;
        }
        synchronized (getLock()) {
            Snapshot current2 = Snapshot.INSTANCE.getCurrent();
            t = (T) readable(r, current2.getId(), current2.getInvalid());
        }
        if (t != null) {
            return t;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final <T extends StateRecord, R> R withCurrent(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(current(t));
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, int i, int i2) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<this>");
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }

    public static final <T extends StateRecord> T newWritableRecord(T t, StateObject state, Snapshot snapshot) {
        T t2;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        synchronized (getLock()) {
            t2 = (T) newWritableRecordLocked(t, state, snapshot);
        }
        return t2;
    }

    public static final <T extends StateRecord, R> R writable(T t, StateObject state, Snapshot snapshot, Function1<? super T, ? extends R> block) {
        R rInvoke;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(block, "block");
        synchronized (getLock()) {
            try {
                rInvoke = block.invoke(writableRecord(t, state, snapshot));
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        notifyWrite(snapshot, state);
        return rInvoke;
    }
}
