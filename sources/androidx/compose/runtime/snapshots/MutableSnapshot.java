package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.hermes.intl.Constants;
import io.sentry.protocol.SentryStackTrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Snapshot.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b \b\u0017\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010.\u001a\u00020\tH\u0002J\r\u0010/\u001a\u00020\tH\u0000¢\u0006\u0002\b0J'\u0010/\u001a\u0002H1\"\u0004\b\u0000\u001012\f\u00102\u001a\b\u0012\u0004\u0012\u0002H103H\u0080\bø\u0001\u0000¢\u0006\u0004\b0\u00104J\b\u00105\u001a\u000206H\u0016J\r\u00107\u001a\u00020\tH\u0010¢\u0006\u0002\b8J\b\u00109\u001a\u00020\tH\u0016J\b\u0010:\u001a\u00020\rH\u0016J3\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020\u00032\u0014\u0010=\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020?\u0018\u00010>2\u0006\u0010@\u001a\u00020\u0005H\u0000¢\u0006\u0002\bAJ\u0015\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\u0001H\u0010¢\u0006\u0002\bDJ\u0015\u0010E\u001a\u00020\t2\u0006\u0010C\u001a\u00020\u0001H\u0010¢\u0006\u0002\bFJ\r\u0010G\u001a\u00020\tH\u0010¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u0014H\u0010¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\bMJ\u0015\u0010N\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u0005H\u0000¢\u0006\u0002\bOJ\u0015\u0010P\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\bQJ\u0015\u0010R\u001a\u00020\t2\u0006\u0010S\u001a\u00020 H\u0000¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020\tH\u0010¢\u0006\u0002\bVJ\r\u0010W\u001a\u00020\tH\u0000¢\u0006\u0002\bXJ8\u0010Y\u001a\u00020\u00002\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u0016J\u001e\u0010Z\u001a\u00020\u00012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u0016J\r\u0010[\u001a\u00020\tH\u0000¢\u0006\u0002\b\\J\r\u0010]\u001a\u00020\tH\u0000¢\u0006\u0002\b^R\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R4\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013@VX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u000fR\u0014\u0010)\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006_"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "id", "", Constants.COLLATION_INVALID, "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "(ILandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "applied", "", "getApplied$runtime_release", "()Z", "setApplied$runtime_release", "(Z)V", "<set-?>", "", "Landroidx/compose/runtime/snapshots/StateObject;", "modified", "getModified$runtime_release", "()Ljava/util/Set;", "setModified", "(Ljava/util/Set;)V", "previousIds", "getPreviousIds$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime_release", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "previousPinnedSnapshots", "", "getPreviousPinnedSnapshots$runtime_release", "()[I", "setPreviousPinnedSnapshots$runtime_release", "([I)V", "getReadObserver$runtime_release", "()Lkotlin/jvm/functions/Function1;", "readOnly", "getReadOnly", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "snapshots", "getWriteObserver$runtime_release", "abandon", "advance", "advance$runtime_release", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "closeLocked", "closeLocked$runtime_release", "dispose", "hasPendingChanges", "innerApplyLocked", "snapshotId", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApplyLocked$runtime_release", "nestedActivated", SentryStackTrace.JsonKeys.SNAPSHOT, "nestedActivated$runtime_release", "nestedDeactivated", "nestedDeactivated$runtime_release", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime_release", "recordModified", "state", "recordModified$runtime_release", "recordPrevious", "recordPrevious$runtime_release", "recordPreviousList", "recordPreviousList$runtime_release", "recordPreviousPinnedSnapshot", "recordPreviousPinnedSnapshot$runtime_release", "recordPreviousPinnedSnapshots", "handles", "recordPreviousPinnedSnapshots$runtime_release", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime_release", "releasePreviouslyPinnedSnapshotsLocked", "releasePreviouslyPinnedSnapshotsLocked$runtime_release", "takeNestedMutableSnapshot", "takeNestedSnapshot", "validateNotApplied", "validateNotApplied$runtime_release", "validateNotAppliedOrPinned", "validateNotAppliedOrPinned$runtime_release", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public class MutableSnapshot extends Snapshot {
    public static final int $stable = 8;
    private boolean applied;
    private Set<StateObject> modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private final Function1<Object, Unit> writeObserver;

    /* renamed from: getApplied$runtime_release, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Set<StateObject> getModified$runtime_release() {
        return this.modified;
    }

    /* renamed from: getPreviousIds$runtime_release, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    /* renamed from: getPreviousPinnedSnapshots$runtime_release, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getReadObserver$runtime_release() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* renamed from: nestedActivated$runtime_release */
    public void mo1532nestedActivated$runtime_release(Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.snapshots++;
    }

    public final void setApplied$runtime_release(boolean z) {
        this.applied = z;
    }

    public void setModified(Set<StateObject> set) {
        this.modified = set;
    }

    public final void setPreviousIds$runtime_release(SnapshotIdSet snapshotIdSet) {
        Intrinsics.checkNotNullParameter(snapshotIdSet, "<set-?>");
        this.previousIds = snapshotIdSet;
    }

    public final void setPreviousPinnedSnapshots$runtime_release(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.previousPinnedSnapshots = iArr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSnapshot(int i, SnapshotIdSet invalid, Function1<Object, Unit> function1, Function1<Object, Unit> function12) {
        super(i, invalid, null);
        Intrinsics.checkNotNullParameter(invalid, "invalid");
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = new int[0];
        this.snapshots = 1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        return modified$runtime_release != null && (modified$runtime_release.isEmpty() ^ true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function12 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function12);
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        NestedMutableSnapshot nestedMutableSnapshot;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned$runtime_release();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            SnapshotIdSet invalid$runtime_release = getInvalid();
            setInvalid$runtime_release(invalid$runtime_release.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(invalid$runtime_release, getId() + 1, i), SnapshotKt.mergedReadObserver$default(readObserver, getReadObserver$runtime_release(), false, 4, null), SnapshotKt.mergedWriteObserver(writeObserver, getWriteObserver$runtime_release()), this);
        }
        if (!getApplied() && !getDisposed()) {
            int id = getId();
            synchronized (SnapshotKt.getLock()) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
        }
        return nestedMutableSnapshot;
    }

    public SnapshotApplyResult apply() {
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges;
        Pair pair;
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release != null) {
            Object obj = SnapshotKt.currentGlobalSnapshot.get();
            Intrinsics.checkNotNullExpressionValue(obj, "currentGlobalSnapshot.get()");
            mapOptimisticMerges = SnapshotKt.optimisticMerges((MutableSnapshot) obj, this, SnapshotKt.openSnapshots.clear(((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getId()));
        } else {
            mapOptimisticMerges = null;
        }
        synchronized (SnapshotKt.getLock()) {
            SnapshotKt.validateOpen(this);
            if (modified$runtime_release == null || modified$runtime_release.size() == 0) {
                closeLocked$runtime_release();
                GlobalSnapshot previousGlobalSnapshot = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                Intrinsics.checkNotNullExpressionValue(previousGlobalSnapshot, "previousGlobalSnapshot");
                SnapshotKt.takeNewGlobalSnapshot(previousGlobalSnapshot, SnapshotKt.emptyLambda);
                Set<StateObject> modified$runtime_release2 = previousGlobalSnapshot.getModified$runtime_release();
                if (modified$runtime_release2 != null && (!modified$runtime_release2.isEmpty())) {
                    pair = TuplesKt.to(CollectionsKt.toMutableList((Collection) SnapshotKt.applyObservers), modified$runtime_release2);
                } else {
                    pair = TuplesKt.to(CollectionsKt.emptyList(), null);
                }
            } else {
                GlobalSnapshot previousGlobalSnapshot2 = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime_release = innerApplyLocked$runtime_release(SnapshotKt.nextSnapshotId, mapOptimisticMerges, SnapshotKt.openSnapshots.clear(previousGlobalSnapshot2.getId()));
                if (!Intrinsics.areEqual(snapshotApplyResultInnerApplyLocked$runtime_release, SnapshotApplyResult.Success.INSTANCE)) {
                    return snapshotApplyResultInnerApplyLocked$runtime_release;
                }
                closeLocked$runtime_release();
                Intrinsics.checkNotNullExpressionValue(previousGlobalSnapshot2, "previousGlobalSnapshot");
                SnapshotKt.takeNewGlobalSnapshot(previousGlobalSnapshot2, SnapshotKt.emptyLambda);
                Set<StateObject> modified$runtime_release3 = previousGlobalSnapshot2.getModified$runtime_release();
                setModified(null);
                previousGlobalSnapshot2.setModified(null);
                pair = TuplesKt.to(CollectionsKt.toMutableList((Collection) SnapshotKt.applyObservers), modified$runtime_release3);
            }
            List list = (List) pair.component1();
            Set set = (Set) pair.component2();
            this.applied = true;
            Set set2 = set;
            if (set2 != null && !set2.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((Function2) list.get(i)).invoke(set, this);
                }
            }
            Set<StateObject> set3 = modified$runtime_release;
            if (set3 != null && !set3.isEmpty()) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ((Function2) list.get(i2)).invoke(modified$runtime_release, this);
                }
            }
            synchronized (SnapshotKt.getLock()) {
                releasePinnedSnapshotsForCloseLocked$runtime_release();
                if (set != null) {
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        SnapshotKt.overwriteUnusedRecordsLocked((StateObject) it.next());
                    }
                }
                if (modified$runtime_release != null) {
                    Iterator<T> it2 = modified$runtime_release.iterator();
                    while (it2.hasNext()) {
                        SnapshotKt.overwriteUnusedRecordsLocked((StateObject) it2.next());
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return SnapshotApplyResult.Success.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed()) {
            return;
        }
        super.dispose();
        mo1533nestedDeactivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned$runtime_release();
        int id = getId();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(getInvalid(), id + 1, i), readObserver, this);
        }
        if (!getApplied() && !getDisposed()) {
            int id2 = getId();
            synchronized (SnapshotKt.getLock()) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id2 + 1, getId()));
        }
        return nestedReadonlySnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* renamed from: nestedDeactivated$runtime_release */
    public void mo1533nestedDeactivated$runtime_release(Snapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        int i = this.snapshots;
        if (i <= 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i2 = i - 1;
        this.snapshots = i2;
        if (i2 != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        releasePreviouslyPinnedSnapshotsLocked$runtime_release();
        super.releasePinnedSnapshotsForCloseLocked$runtime_release();
    }

    public final void validateNotApplied$runtime_release() {
        if (!(!this.applied)) {
            throw new IllegalStateException("Unsupported operation on a snapshot that has been applied".toString());
        }
    }

    public final void validateNotAppliedOrPinned$runtime_release() {
        if (this.applied && ((Snapshot) this).pinningTrackingHandle < 0) {
            throw new IllegalStateException("Unsupported operation on a disposed or applied snapshot".toString());
        }
    }

    private final void abandon() {
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release != null) {
            validateNotApplied$runtime_release();
            setModified(null);
            int id = getId();
            Iterator<StateObject> it = modified$runtime_release.iterator();
            while (it.hasNext()) {
                for (StateRecord firstStateRecord = it.next().getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext()) {
                    if (firstStateRecord.getSnapshotId() == id || CollectionsKt.contains(this.previousIds, Integer.valueOf(firstStateRecord.getSnapshotId()))) {
                        firstStateRecord.setSnapshotId$runtime_release(0);
                    }
                }
            }
        }
        closeAndReleasePinning$runtime_release();
    }

    public final SnapshotApplyResult innerApplyLocked$runtime_release(int snapshotId, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) {
        StateRecord stateRecord;
        StateRecord stateRecordMergeRecords;
        Intrinsics.checkNotNullParameter(invalidSnapshots, "invalidSnapshots");
        SnapshotIdSet snapshotIdSetOr = getInvalid().set(getId()).or(this.previousIds);
        Set<StateObject> modified$runtime_release = getModified$runtime_release();
        Intrinsics.checkNotNull(modified$runtime_release);
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (StateObject stateObject : modified$runtime_release) {
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, snapshotId, invalidSnapshots);
            if (stateRecord2 != null && (stateRecord = SnapshotKt.readable(firstStateRecord, getId(), snapshotIdSetOr)) != null && !Intrinsics.areEqual(stateRecord2, stateRecord)) {
                StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid());
                if (stateRecord3 == null) {
                    SnapshotKt.readError();
                    throw new KotlinNothingValueException();
                }
                if (optimisticMerges == null || (stateRecordMergeRecords = optimisticMerges.get(stateRecord2)) == null) {
                    stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                }
                if (stateRecordMergeRecords == null) {
                    return new SnapshotApplyResult.Failure(this);
                }
                if (!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord3)) {
                    if (Intrinsics.areEqual(stateRecordMergeRecords, stateRecord2)) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(TuplesKt.to(stateObject, stateRecord2.create()));
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(stateObject);
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord) ? TuplesKt.to(stateObject, stateRecordMergeRecords) : TuplesKt.to(stateObject, stateRecord.create()));
                    }
                }
            }
        }
        if (arrayList != null) {
            advance$runtime_release();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Pair pair = (Pair) arrayList.get(i);
                StateObject stateObject2 = (StateObject) pair.component1();
                StateRecord stateRecord4 = (StateRecord) pair.component2();
                stateRecord4.setSnapshotId$runtime_release(getId());
                synchronized (SnapshotKt.getLock()) {
                    stateRecord4.setNext$runtime_release(stateObject2.getFirstStateRecord());
                    stateObject2.prependStateRecord(stateRecord4);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        if (arrayList2 != null) {
            modified$runtime_release.removeAll(arrayList2);
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final <T> T advance$runtime_release(Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        recordPrevious$runtime_release(getId());
        T tInvoke = block.invoke();
        if (!getApplied() && !getDisposed()) {
            int id = getId();
            synchronized (SnapshotKt.getLock()) {
                try {
                    int i = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i + 1;
                    setId$runtime_release(i);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            InlineMarker.finallyEnd(1);
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
        }
        return tInvoke;
    }

    public final void recordPreviousPinnedSnapshot$runtime_release(int id) {
        if (id >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, id);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime_release(int[] handles) {
        Intrinsics.checkNotNullParameter(handles, "handles");
        if (handles.length == 0) {
            return;
        }
        int[] iArr = this.previousPinnedSnapshots;
        if (iArr.length == 0) {
            this.previousPinnedSnapshots = handles;
        } else {
            this.previousPinnedSnapshots = ArraysKt.plus(iArr, handles);
        }
    }

    public final void releasePreviouslyPinnedSnapshotsLocked$runtime_release() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* renamed from: recordModified$runtime_release */
    public void mo1534recordModified$runtime_release(StateObject state) {
        Intrinsics.checkNotNullParameter(state, "state");
        HashSet modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            modified$runtime_release = new HashSet();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(state);
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        Unit unit = Unit.INSTANCE;
        if (getApplied() || getDisposed()) {
            return;
        }
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            setId$runtime_release(i);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            Unit unit2 = Unit.INSTANCE;
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid(), id + 1, getId()));
    }

    public final void recordPrevious$runtime_release(int id) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousList$runtime_release(SnapshotIdSet snapshots) {
        Intrinsics.checkNotNullParameter(snapshots, "snapshots");
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshots);
            Unit unit = Unit.INSTANCE;
        }
    }
}
