package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.facebook.react.uimanager.events.TouchesHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0015J\u000e\u0010-\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0007J\u0006\u0010.\u001a\u00020/J\u0015\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\b4JW\u00100\u001a\u0002012\u0006\u0010)\u001a\u0002052\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u0006\u0010#\u001a\u00020\u00152\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0000¢\u0006\u0004\b4\u00106J\u0006\u00107\u001a\u00020\u001aJ\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020\u001509H\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010\u00032\u0006\u0010;\u001a\u00020\u001eH\u0016J\u0012\u0010<\u001a\u0004\u0018\u00010=2\u0006\u0010>\u001a\u00020\u0015H\u0002J\u0016\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0007J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001509H\u0002J\u0010\u0010B\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u0015H\u0002J\u001d\u0010C\u001a\n\u0012\u0004\u0012\u00020=\u0018\u0001092\u0006\u0010D\u001a\u00020\u0015H\u0000¢\u0006\u0002\bEJ\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00030GH\u0096\u0002J\u000e\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001509H\u0002J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001509H\u0002J\u0006\u0010J\u001a\u000203J\u0006\u0010K\u001a\u000205J\u000e\u0010L\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u0007J\u000e\u0010M\u001a\b\u0012\u0004\u0012\u00020\u001509H\u0002J:\u0010N\u001a\u0002HO\"\u0004\b\u0000\u0010O2!\u0010P\u001a\u001d\u0012\u0013\u0012\u001103¢\u0006\f\bR\u0012\b\bS\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002HO0QH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010TJO\u0010U\u001a\u0002012\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u0006\u0010#\u001a\u00020\u00152\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0000¢\u0006\u0004\bV\u0010WJ\u001d\u0010X\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e092\u0006\u0010>\u001a\u00020\u0015H\u0000¢\u0006\u0002\bYJ\u0006\u0010Z\u001a\u000201J:\u0010[\u001a\u0002HO\"\u0004\b\u0000\u0010O2!\u0010P\u001a\u001d\u0012\u0013\u0012\u001105¢\u0006\f\bR\u0012\b\bS\u0012\u0004\b\b()\u0012\u0004\u0012\u0002HO0QH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010TJ \u0010\\\u001a\u00020\u0015*\u00060]j\u0002`^2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010_\u001a\u00020\u0015H\u0002R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u001e\u0010#\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u001a\u0010%\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u001a@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006`"}, d2 = {"Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "()V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "getAnchors$runtime_release", "()Ljava/util/ArrayList;", "setAnchors$runtime_release", "(Ljava/util/ArrayList;)V", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "<set-?>", "", "groups", "getGroups", "()[I", "", "groupsSize", "getGroupsSize", "()I", "isEmpty", "", "()Z", "readers", "", "", "slots", "getSlots", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "slotsSize", "getSlotsSize", "version", "getVersion$runtime_release", "setVersion$runtime_release", "(I)V", "writer", "getWriter$runtime_release", "anchor", "index", "anchorIndex", "asString", "", Constants.KEY_HIDE_CLOSE, "", "reader", "Landroidx/compose/runtime/SlotReader;", "close$runtime_release", "Landroidx/compose/runtime/SlotWriter;", "(Landroidx/compose/runtime/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;)V", "containsMark", "dataIndexes", "", "find", "identityToFind", "findEffectiveRecomposeScope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "group", "groupContainsAnchor", "groupIndex", "groupSizes", "invalidateGroup", "invalidateGroupsWithKey", TouchesHelper.TARGET_KEY, "invalidateGroupsWithKey$runtime_release", "iterator", "", QuickTimeAtomTypes.ATOM_KEYS, "nodes", "openReader", "openWriter", "ownsAnchor", "parentIndexes", "read", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "setTo", "setTo$runtime_release", "([II[Ljava/lang/Object;ILjava/util/ArrayList;)V", "slotsOf", "slotsOf$runtime_release", "verifyWellFormed", "write", "emitGroup", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "level", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SlotTable implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private ArrayList<Anchor> anchors = new ArrayList<>();

    public final ArrayList<Anchor> getAnchors$runtime_release() {
        return this.anchors;
    }

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    /* renamed from: getVersion$runtime_release, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* renamed from: getWriter$runtime_release, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    public final void setAnchors$runtime_release(ArrayList<Anchor> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.anchors = arrayList;
    }

    public final void setTo$runtime_release(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors) {
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
    }

    public final void setVersion$runtime_release(int i) {
        this.version = i;
    }

    public final <T> T read(Function1<? super SlotReader, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SlotReader slotReaderOpenReader = openReader();
        try {
            return block.invoke(slotReaderOpenReader);
        } finally {
            InlineMarker.finallyStart(1);
            slotReaderOpenReader.close();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T write(Function1<? super SlotWriter, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SlotWriter slotWriterOpenWriter = openWriter();
        try {
            return block.invoke(slotWriterOpenWriter);
        } finally {
            InlineMarker.finallyStart(1);
            slotWriterOpenWriter.close();
            InlineMarker.finallyEnd(1);
        }
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        if (!(!this.writer)) {
            ComposerKt.composeRuntimeError("Cannot start a writer when another writer is pending".toString());
            throw new KotlinNothingValueException();
        }
        if (this.readers <= 0) {
            this.writer = true;
            this.version++;
            return new SlotWriter(this);
        }
        ComposerKt.composeRuntimeError("Cannot start a writer when a reader is pending".toString());
        throw new KotlinNothingValueException();
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (!this.writer) {
            if (!anchor.getValid()) {
                throw new IllegalArgumentException("Anchor refers to a group that was removed".toString());
            }
            return anchor.getLocation();
        }
        ComposerKt.composeRuntimeError("Use active SlotWriter to determine anchor location instead".toString());
        throw new KotlinNothingValueException();
    }

    public final boolean ownsAnchor(Anchor anchor) {
        int iSearch;
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        return anchor.getValid() && (iSearch = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize)) >= 0 && Intrinsics.areEqual(this.anchors.get(iSearch), anchor);
    }

    public final boolean groupContainsAnchor(int groupIndex, Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (!(!this.writer)) {
            ComposerKt.composeRuntimeError("Writer is active".toString());
            throw new KotlinNothingValueException();
        }
        if (groupIndex >= 0 && groupIndex < this.groupsSize) {
            if (ownsAnchor(anchor)) {
                int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndex) + groupIndex;
                int location = anchor.getLocation();
                if (groupIndex <= location && location < iGroupSize) {
                    return true;
                }
            }
            return false;
        }
        ComposerKt.composeRuntimeError("Invalid group index".toString());
        throw new KotlinNothingValueException();
    }

    public final void close$runtime_release(SlotReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        if (reader.getTable() == this && this.readers > 0) {
            this.readers--;
        } else {
            ComposerKt.composeRuntimeError("Unexpected reader close()".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void close$runtime_release(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        if (writer.getTable() != this || !this.writer) {
            throw new IllegalArgumentException("Unexpected writer close()".toString());
        }
        this.writer = false;
        setTo$runtime_release(groups, groupsSize, slots, slotsSize, anchors);
    }

    public final List<RecomposeScopeImpl> invalidateGroupsWithKey$runtime_release(int target) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        SlotReader slotReaderOpenReader = openReader();
        try {
            invalidateGroupsWithKey$lambda$14$scanGroup(slotReaderOpenReader, target, arrayList, booleanRef, this, arrayList2);
            Unit unit = Unit.INSTANCE;
            slotReaderOpenReader.close();
            SlotWriter slotWriterOpenWriter = openWriter();
            try {
                slotWriterOpenWriter.startGroup();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    Anchor anchor = (Anchor) arrayList.get(i);
                    if (anchor.toIndexFor(slotWriterOpenWriter) >= slotWriterOpenWriter.getCurrentGroup()) {
                        slotWriterOpenWriter.seek(anchor);
                        slotWriterOpenWriter.bashGroup$runtime_release();
                    }
                }
                slotWriterOpenWriter.skipToGroupEnd();
                slotWriterOpenWriter.endGroup();
                slotWriterOpenWriter.close();
                if (booleanRef.element) {
                    return arrayList2;
                }
                return null;
            } catch (Throwable th) {
                slotWriterOpenWriter.close();
                throw th;
            }
        } catch (Throwable th2) {
            slotReaderOpenReader.close();
            throw th2;
        }
    }

    private static final void invalidateGroupsWithKey$lambda$14$scanGroup(SlotReader slotReader, int i, List<Anchor> list, Ref.BooleanRef booleanRef, SlotTable slotTable, List<RecomposeScopeImpl> list2) {
        if (slotReader.getGroupKey() == i) {
            list.add(SlotReader.anchor$default(slotReader, 0, 1, null));
            if (booleanRef.element) {
                RecomposeScopeImpl recomposeScopeImplFindEffectiveRecomposeScope = slotTable.findEffectiveRecomposeScope(slotReader.getCurrentGroup());
                if (recomposeScopeImplFindEffectiveRecomposeScope != null) {
                    list2.add(recomposeScopeImplFindEffectiveRecomposeScope);
                } else {
                    booleanRef.element = false;
                    list2.clear();
                }
            }
            slotReader.skipGroup();
            return;
        }
        slotReader.startGroup();
        while (!slotReader.isGroupEnd()) {
            invalidateGroupsWithKey$lambda$14$scanGroup(slotReader, i, list, booleanRef, slotTable, list2);
        }
        slotReader.endGroup();
    }

    public final boolean containsMark() {
        return this.groupsSize > 0 && SlotTableKt.containsMark(this.groups, 0);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        while (group > 0) {
            Iterator<Object> it = new DataIterator(this, group).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof RecomposeScopeImpl) {
                    return (RecomposeScopeImpl) next;
                }
            }
            group = SlotTableKt.parentAnchor(this.groups, group);
        }
        return null;
    }

    private final boolean invalidateGroup(int group) {
        while (group >= 0) {
            Iterator<Object> it = new DataIterator(this, group).iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof RecomposeScopeImpl) {
                    RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) next;
                    recomposeScopeImpl.setRequiresRecompose(true);
                    return recomposeScopeImpl.invalidateForResult(null) != InvalidationResult.IGNORED;
                }
            }
            group = SlotTableKt.parentAnchor(this.groups, group);
        }
        return false;
    }

    public final void verifyWellFormed() {
        Ref.IntRef intRef = new Ref.IntRef();
        int i = -1;
        if (this.groupsSize > 0) {
            while (intRef.element < this.groupsSize) {
                verifyWellFormed$validateGroup(intRef, this, -1, intRef.element + SlotTableKt.groupSize(this.groups, intRef.element));
            }
            if (intRef.element != this.groupsSize) {
                throw new IllegalStateException(("Incomplete group at root " + intRef.element + " expected to be " + this.groupsSize).toString());
            }
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            int indexFor = arrayList.get(i2).toIndexFor(this);
            if (indexFor < 0 || indexFor > this.groupsSize) {
                throw new IllegalArgumentException("Invalid anchor, location out of bound".toString());
            }
            if (i >= indexFor) {
                throw new IllegalArgumentException("Anchor is out of order".toString());
            }
            i2++;
            i = indexFor;
        }
    }

    private static final int verifyWellFormed$validateGroup(Ref.IntRef intRef, SlotTable slotTable, int i, int i2) {
        int i3 = intRef.element;
        int i4 = i3 + 1;
        intRef.element = i4;
        int iParentAnchor = SlotTableKt.parentAnchor(slotTable.groups, i3);
        if (iParentAnchor == i) {
            int iGroupSize = SlotTableKt.groupSize(slotTable.groups, i3) + i3;
            if (iGroupSize > slotTable.groupsSize) {
                throw new IllegalStateException(("A group extends past the end of the table at " + i3).toString());
            }
            if (iGroupSize <= i2) {
                int iDataAnchor = SlotTableKt.dataAnchor(slotTable.groups, i3);
                int iDataAnchor2 = i3 >= slotTable.groupsSize - 1 ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i4);
                if (iDataAnchor2 > slotTable.slots.length) {
                    throw new IllegalStateException(("Slots for " + i3 + " extend past the end of the slot table").toString());
                }
                if (iDataAnchor <= iDataAnchor2) {
                    if (SlotTableKt.slotAnchor(slotTable.groups, i3) > iDataAnchor2) {
                        throw new IllegalStateException(("Slots start out of range at " + i3).toString());
                    }
                    if (iDataAnchor2 - iDataAnchor >= (SlotTableKt.isNode(slotTable.groups, i3) ? 1 : 0) + (SlotTableKt.hasObjectKey(slotTable.groups, i3) ? 1 : 0) + (SlotTableKt.hasAux(slotTable.groups, i3) ? 1 : 0)) {
                        boolean zIsNode = SlotTableKt.isNode(slotTable.groups, i3);
                        if (zIsNode && slotTable.slots[SlotTableKt.nodeIndex(slotTable.groups, i3)] == null) {
                            throw new IllegalStateException(("No node recorded for a node group at " + i3).toString());
                        }
                        int iVerifyWellFormed$validateGroup = 0;
                        while (intRef.element < iGroupSize) {
                            iVerifyWellFormed$validateGroup += verifyWellFormed$validateGroup(intRef, slotTable, i3, iGroupSize);
                        }
                        int iNodeCount = SlotTableKt.nodeCount(slotTable.groups, i3);
                        int iGroupSize2 = SlotTableKt.groupSize(slotTable.groups, i3);
                        if (iNodeCount != iVerifyWellFormed$validateGroup) {
                            throw new IllegalStateException(("Incorrect node count detected at " + i3 + ", expected " + iNodeCount + ", received " + iVerifyWellFormed$validateGroup).toString());
                        }
                        int i5 = intRef.element - i3;
                        if (iGroupSize2 == i5) {
                            if (SlotTableKt.containsAnyMark(slotTable.groups, i3) && i3 > 0 && !SlotTableKt.containsMark(slotTable.groups, i)) {
                                throw new IllegalStateException(("Expected group " + i + " to record it contains a mark because " + i3 + " does").toString());
                            }
                            if (zIsNode) {
                                return 1;
                            }
                            return iVerifyWellFormed$validateGroup;
                        }
                        throw new IllegalStateException(("Incorrect slot count detected at " + i3 + ", expected " + iGroupSize2 + ", received " + i5).toString());
                    }
                    throw new IllegalStateException(("Not enough slots added for group " + i3).toString());
                }
                throw new IllegalStateException(("Invalid data anchor at " + i3).toString());
            }
            throw new IllegalStateException(("A group extends past its parent group at " + i3).toString());
        }
        throw new IllegalStateException(("Invalid parent index detected at " + i3 + ", expected parent index to be " + i + " found " + iParentAnchor).toString());
    }

    public final String asString() {
        if (this.writer) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append('\n');
        int i = this.groupsSize;
        if (i > 0) {
            int iEmitGroup = 0;
            while (iEmitGroup < i) {
                iEmitGroup += emitGroup(sb, iEmitGroup, 0);
            }
        } else {
            sb.append("<EMPTY>");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private final int emitGroup(StringBuilder sb, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(' ');
        }
        sb.append("Group(");
        sb.append(i);
        sb.append(") key=");
        sb.append(SlotTableKt.key(this.groups, i));
        int iGroupSize = SlotTableKt.groupSize(this.groups, i);
        sb.append(", nodes=");
        sb.append(SlotTableKt.nodeCount(this.groups, i));
        sb.append(", size=");
        sb.append(iGroupSize);
        if (SlotTableKt.hasMark(this.groups, i)) {
            sb.append(", mark");
        }
        if (SlotTableKt.containsMark(this.groups, i)) {
            sb.append(", contains mark");
        }
        int iEmitGroup$dataIndex = emitGroup$dataIndex(this, i);
        int iEmitGroup = i + 1;
        int iEmitGroup$dataIndex2 = emitGroup$dataIndex(this, iEmitGroup);
        if (iEmitGroup$dataIndex >= 0 && iEmitGroup$dataIndex <= iEmitGroup$dataIndex2 && iEmitGroup$dataIndex2 <= this.slotsSize) {
            if (SlotTableKt.hasObjectKey(this.groups, i)) {
                sb.append(" objectKey=" + this.slots[SlotTableKt.objectKeyIndex(this.groups, i)]);
            }
            if (SlotTableKt.isNode(this.groups, i)) {
                sb.append(" node=" + this.slots[SlotTableKt.nodeIndex(this.groups, i)]);
            }
            if (SlotTableKt.hasAux(this.groups, i)) {
                sb.append(" aux=" + this.slots[SlotTableKt.auxIndex(this.groups, i)]);
            }
            int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i);
            if (iSlotAnchor < iEmitGroup$dataIndex2) {
                sb.append(", slots=[");
                sb.append(iSlotAnchor);
                sb.append(": ");
                for (int i4 = iSlotAnchor; i4 < iEmitGroup$dataIndex2; i4++) {
                    if (i4 != iSlotAnchor) {
                        sb.append(", ");
                    }
                    sb.append(String.valueOf(this.slots[i4]));
                }
                sb.append("]");
            }
        } else {
            sb.append(", *invalid data offsets " + iEmitGroup$dataIndex + '-' + iEmitGroup$dataIndex2 + '*');
        }
        sb.append('\n');
        int i5 = i + iGroupSize;
        while (iEmitGroup < i5) {
            iEmitGroup += emitGroup(sb, iEmitGroup, i2 + 1);
        }
        return iGroupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable slotTable, int i) {
        return i >= slotTable.groupsSize ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i);
    }

    private final List<Integer> keys() {
        return SlotTableKt.keys(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> nodes() {
        return SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> parentIndexes() {
        return SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> dataIndexes() {
        return SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> groupSizes() {
        return SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
    }

    public final List<Object> slotsOf$runtime_release(int group) {
        int iDataAnchor = SlotTableKt.dataAnchor(this.groups, group);
        int i = group + 1;
        return ArraysKt.toList(this.slots).subList(iDataAnchor, i < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i) : this.slots.length);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        Intrinsics.checkNotNullParameter(identityToFind, "identityToFind");
        return new SlotTableGroup(this, 0, 0, 4, null).find(identityToFind);
    }

    public final Anchor anchor(int index) {
        int i;
        if (!(!this.writer)) {
            ComposerKt.composeRuntimeError("use active SlotWriter to create an anchor location instead ".toString());
            throw new KotlinNothingValueException();
        }
        if (index >= 0 && index < (i = this.groupsSize)) {
            ArrayList<Anchor> arrayList = this.anchors;
            int iSearch = SlotTableKt.search(arrayList, index, i);
            if (iSearch < 0) {
                Anchor anchor = new Anchor(index);
                arrayList.add(-(iSearch + 1), anchor);
                return anchor;
            }
            Anchor anchor2 = arrayList.get(iSearch);
            Intrinsics.checkNotNullExpressionValue(anchor2, "get(location)");
            return anchor2;
        }
        throw new IllegalArgumentException("Parameter index is out of range".toString());
    }
}
