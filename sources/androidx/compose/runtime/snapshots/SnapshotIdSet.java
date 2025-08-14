package androidx.compose.runtime.snapshots;

import com.facebook.hermes.intl.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: SnapshotIdSet.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002J \u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00100\u0012H\u0086\bø\u0001\u0000J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u0002J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0096\u0002J\u000e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "", "", "upperSet", "", "lowerSet", "lowerBound", "belowBound", "", "(JJI[I)V", "and", "bits", "andNot", "clear", "bit", "fastForEach", "", "block", "Lkotlin/Function1;", "get", "", "iterator", "", "lowest", Constants.COLLATION_DEFAULT, "or", "set", "toString", "", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SnapshotIdSet implements Iterable<Integer>, KMappedMarker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final int[] belowBound;
    private final int lowerBound;
    private final long lowerSet;
    private final long upperSet;

    private SnapshotIdSet(long j, long j2, int i, int[] iArr) {
        this.upperSet = j;
        this.lowerSet = j2;
        this.lowerBound = i;
        this.belowBound = iArr;
    }

    public final boolean get(int bit) {
        int[] iArr;
        int i = bit - this.lowerBound;
        if (i >= 0 && i < 64) {
            return ((1 << i) & this.lowerSet) != 0;
        }
        if (i >= 64 && i < 128) {
            return ((1 << (i - 64)) & this.upperSet) != 0;
        }
        if (i <= 0 && (iArr = this.belowBound) != null) {
            return SnapshotIdSetKt.binarySearch(iArr, bit) >= 0;
        }
        return false;
    }

    public final SnapshotIdSet set(int bit) {
        int i;
        int[] intArray;
        int i2 = this.lowerBound;
        int i3 = bit - i2;
        long j = 0;
        if (i3 >= 0 && i3 < 64) {
            long j2 = 1 << i3;
            long j3 = this.lowerSet;
            if ((j3 & j2) == 0) {
                return new SnapshotIdSet(this.upperSet, j3 | j2, i2, this.belowBound);
            }
        } else if (i3 >= 64 && i3 < 128) {
            long j4 = 1 << (i3 - 64);
            long j5 = this.upperSet;
            if ((j5 & j4) == 0) {
                return new SnapshotIdSet(j5 | j4, this.lowerSet, i2, this.belowBound);
            }
        } else if (i3 >= 128) {
            if (!get(bit)) {
                long j6 = this.upperSet;
                long j7 = this.lowerSet;
                int i4 = this.lowerBound;
                int i5 = ((bit + 1) / 64) * 64;
                ArrayList arrayList = null;
                long j8 = j7;
                long j9 = j6;
                while (true) {
                    if (i4 >= i5) {
                        i = i4;
                        break;
                    }
                    if (j8 != j) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            int[] iArr = this.belowBound;
                            if (iArr != null) {
                                for (int i6 : iArr) {
                                    arrayList.add(Integer.valueOf(i6));
                                }
                            }
                        }
                        for (int i7 = 0; i7 < 64; i7++) {
                            if (((1 << i7) & j8) != 0) {
                                arrayList.add(Integer.valueOf(i7 + i4));
                            }
                        }
                        j = 0;
                    }
                    if (j9 == j) {
                        i = i5;
                        j8 = j;
                        break;
                    }
                    i4 += 64;
                    j8 = j9;
                    j9 = j;
                }
                if (arrayList == null || (intArray = CollectionsKt.toIntArray(arrayList)) == null) {
                    intArray = this.belowBound;
                }
                return new SnapshotIdSet(j9, j8, i, intArray).set(bit);
            }
        } else {
            int[] iArr2 = this.belowBound;
            if (iArr2 == null) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, i2, new int[]{bit});
            }
            int iBinarySearch = SnapshotIdSetKt.binarySearch(iArr2, bit);
            if (iBinarySearch < 0) {
                int i8 = -(iBinarySearch + 1);
                int length = iArr2.length;
                int[] iArr3 = new int[length + 1];
                ArraysKt.copyInto(iArr2, iArr3, 0, 0, i8);
                ArraysKt.copyInto(iArr2, iArr3, i8 + 1, i8, length);
                iArr3[i8] = bit;
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr3);
            }
        }
        return this;
    }

    public final SnapshotIdSet clear(int bit) {
        int[] iArr;
        int iBinarySearch;
        int i = this.lowerBound;
        int i2 = bit - i;
        if (i2 >= 0 && i2 < 64) {
            long j = 1 << i2;
            long j2 = this.lowerSet;
            if ((j2 & j) != 0) {
                return new SnapshotIdSet(this.upperSet, j2 & (~j), i, this.belowBound);
            }
        } else if (i2 >= 64 && i2 < 128) {
            long j3 = 1 << (i2 - 64);
            long j4 = this.upperSet;
            if ((j4 & j3) != 0) {
                return new SnapshotIdSet(j4 & (~j3), this.lowerSet, i, this.belowBound);
            }
        } else if (i2 < 0 && (iArr = this.belowBound) != null && (iBinarySearch = SnapshotIdSetKt.binarySearch(iArr, bit)) >= 0) {
            int length = iArr.length;
            int i3 = length - 1;
            if (i3 == 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, null);
            }
            int[] iArr2 = new int[i3];
            if (iBinarySearch > 0) {
                ArraysKt.copyInto(iArr, iArr2, 0, 0, iBinarySearch);
            }
            if (iBinarySearch < i3) {
                ArraysKt.copyInto(iArr, iArr2, iBinarySearch, iBinarySearch + 1, length);
            }
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
        }
        return this;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return snapshotIdSet;
        }
        int i = bits.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet & (~bits.upperSet), this.lowerSet & (~bits.lowerSet), i2, iArr2);
            }
        }
        Iterator<Integer> it = bits.iterator();
        SnapshotIdSet snapshotIdSetClear = this;
        while (it.hasNext()) {
            snapshotIdSetClear = snapshotIdSetClear.clear(it.next().intValue());
        }
        return snapshotIdSetClear;
    }

    public final SnapshotIdSet and(SnapshotIdSet bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (Intrinsics.areEqual(bits, snapshotIdSet) || Intrinsics.areEqual(this, snapshotIdSet)) {
            return snapshotIdSet;
        }
        int i = bits.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                long j = this.upperSet;
                long j2 = bits.upperSet;
                long j3 = j & j2;
                long j4 = this.lowerSet;
                long j5 = bits.lowerSet;
                return (j3 == 0 && (j4 & j5) == 0 && iArr2 == null) ? snapshotIdSet : new SnapshotIdSet(j2 & j, j4 & j5, i2, iArr2);
            }
        }
        if (this.belowBound == null) {
            Iterator<Integer> it = iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                if (bits.get(iIntValue)) {
                    snapshotIdSet = snapshotIdSet.set(iIntValue);
                }
            }
            return snapshotIdSet;
        }
        Iterator<Integer> it2 = bits.iterator();
        while (it2.hasNext()) {
            int iIntValue2 = it2.next().intValue();
            if (get(iIntValue2)) {
                snapshotIdSet = snapshotIdSet.set(iIntValue2);
            }
        }
        return snapshotIdSet;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return bits;
        }
        int i = bits.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet | bits.upperSet, this.lowerSet | bits.lowerSet, i2, iArr2);
            }
        }
        if (this.belowBound == null) {
            Iterator<Integer> it = iterator();
            while (it.hasNext()) {
                bits = bits.set(it.next().intValue());
            }
            return bits;
        }
        Iterator<Integer> it2 = bits.iterator();
        SnapshotIdSet snapshotIdSet2 = this;
        while (it2.hasNext()) {
            snapshotIdSet2 = snapshotIdSet2.set(it2.next().intValue());
        }
        return snapshotIdSet2;
    }

    /* compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", i = {0, 0, 1, 1, 2, 2}, l = {295, 300, 307}, m = "invokeSuspend", n = {"$this$sequence", "belowBound", "$this$sequence", "index", "$this$sequence", "index"}, s = {"L$0", "L$1", "L$0", "I$0", "L$0", "I$0"})
    /* renamed from: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Integer>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SnapshotIdSet.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Integer> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0083  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00cd  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d2  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x007c -> B:19:0x007f). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00bc -> B:32:0x00c0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00bf -> B:32:0x00c0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00fc -> B:44:0x0100). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00ff -> B:44:0x0100). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 261
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Integer> iterator() {
        return SequencesKt.sequence(new AnonymousClass1(null)).iterator();
    }

    public final void fastForEach(Function1<? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int[] iArr = this.belowBound;
        if (iArr != null) {
            for (int i : iArr) {
                block.invoke(Integer.valueOf(i));
            }
        }
        if (this.lowerSet != 0) {
            for (int i2 = 0; i2 < 64; i2++) {
                if ((this.lowerSet & (1 << i2)) != 0) {
                    block.invoke(Integer.valueOf(this.lowerBound + i2));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int i3 = 0; i3 < 64; i3++) {
                if ((this.upperSet & (1 << i3)) != 0) {
                    block.invoke(Integer.valueOf(i3 + 64 + this.lowerBound));
                }
            }
        }
    }

    public final int lowest(int i) {
        int[] iArr = this.belowBound;
        if (iArr != null) {
            return iArr[0];
        }
        long j = this.lowerSet;
        if (j != 0) {
            return this.lowerBound + SnapshotIdSetKt.lowestBitOf(j);
        }
        long j2 = this.upperSet;
        return j2 != 0 ? this.lowerBound + 64 + SnapshotIdSetKt.lowestBitOf(j2) : i;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append(super.toString()).append(" [");
        SnapshotIdSet snapshotIdSet = this;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(snapshotIdSet, 10));
        Iterator<Integer> it = snapshotIdSet.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().intValue()));
        }
        return sbAppend.append(ListUtilsKt.fastJoinToString$default(arrayList, null, null, null, 0, null, null, 63, null)).append(AbstractJsonLexerKt.END_LIST).toString();
    }

    /* compiled from: SnapshotIdSet.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet$Companion;", "", "()V", "EMPTY", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getEMPTY", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SnapshotIdSet getEMPTY() {
            return SnapshotIdSet.EMPTY;
        }
    }
}
