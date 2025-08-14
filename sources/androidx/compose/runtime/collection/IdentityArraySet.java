package androidx.compose.runtime.collection;

import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: IdentityArraySet.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010(\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0006\u0010\u001a\u001a\u00020\u0018J\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016J\u0016\u0010\u001d\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J+\u0010 \u001a\u00020\u00182\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00180\"H\u0086\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0012\u0010#\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010&\u001a\u00020\u0006H\u0002J\u0016\u0010'\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u0014H\u0016J\u0006\u0010*\u001a\u00020\u0014J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000,H\u0096\u0002J\u0013\u0010-\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016J \u0010.\u001a\u00020\u00182\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140\"H\u0086\bø\u0001\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR.\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010\u0012\u0012\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00060"}, d2 = {"Landroidx/compose/runtime/collection/IdentityArraySet;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "()V", RRWebVideoEvent.JsonKeys.SIZE, "", "getSize", "()I", "setSize", "(I)V", "values", "", "getValues$annotations", "getValues", "()[Ljava/lang/Object;", "setValues", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "add", "", "value", "(Ljava/lang/Object;)Z", "checkIndexBounds", "", "index", "clear", "contains", "element", "containsAll", "elements", "", "fastForEach", "block", "Lkotlin/Function1;", "find", "findExactIndex", "midIndex", "valueHash", "get", "(I)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "iterator", "", "remove", "removeValueIf", "predicate", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IdentityArraySet<T> implements Set<T>, KMappedMarker {
    private int size;
    private Object[] values = new Object[16];

    public static /* synthetic */ void getValues$annotations() {
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int getSize() {
        return this.size;
    }

    public final Object[] getValues() {
        return this.values;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void setSize(int i) {
        this.size = i;
    }

    public final void setValues(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.values = objArr;
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object element) {
        return element != null && find(element) >= 0;
    }

    public final T get(int index) {
        checkIndexBounds(index);
        T t = (T) this.values[index];
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
        return t;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(T value) {
        int iFind;
        Intrinsics.checkNotNullParameter(value, "value");
        if (size() > 0) {
            iFind = find(value);
            if (iFind >= 0) {
                return false;
            }
        } else {
            iFind = -1;
        }
        int i = -(iFind + 1);
        int size = size();
        Object[] objArr = this.values;
        if (size == objArr.length) {
            Object[] objArr2 = new Object[objArr.length * 2];
            ArraysKt.copyInto(objArr, objArr2, i + 1, i, size());
            ArraysKt.copyInto$default(this.values, objArr2, 0, 0, i, 6, (Object) null);
            this.values = objArr2;
        } else {
            ArraysKt.copyInto(objArr, objArr, i + 1, i, size());
        }
        this.values[i] = value;
        setSize(size() + 1);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        ArraysKt.fill$default(this.values, (Object) null, 0, 0, 6, (Object) null);
        setSize(0);
    }

    public final void fastForEach(Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int size = size();
        for (int i = 0; i < size; i++) {
            block.invoke(get(i));
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    public final boolean isNotEmpty() {
        return size() > 0;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(T value) {
        int iFind;
        if (value == null || (iFind = find(value)) < 0) {
            return false;
        }
        if (iFind < size() - 1) {
            Object[] objArr = this.values;
            ArraysKt.copyInto(objArr, objArr, iFind, iFind + 1, size());
        }
        setSize(size() - 1);
        this.values[size()] = null;
        return true;
    }

    public final void removeValueIf(Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = getValues()[i2];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
            if (!predicate.invoke(obj).booleanValue()) {
                if (i != i2) {
                    getValues()[i] = obj;
                }
                i++;
            }
        }
        int size2 = size();
        for (int i3 = i; i3 < size2; i3++) {
            getValues()[i3] = null;
        }
        setSize(i);
    }

    private final int find(Object value) {
        int size = size() - 1;
        int iIdentityHashCode = ActualJvm_jvmKt.identityHashCode(value);
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            T t = get(i2);
            int iIdentityHashCode2 = ActualJvm_jvmKt.identityHashCode(t);
            if (iIdentityHashCode2 < iIdentityHashCode) {
                i = i2 + 1;
            } else {
                if (iIdentityHashCode2 <= iIdentityHashCode) {
                    return t == value ? i2 : findExactIndex(i2, value, iIdentityHashCode);
                }
                size = i2 - 1;
            }
        }
        return -(i + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
    
        return -(r3 + 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int findExactIndex(int r3, java.lang.Object r4, int r5) {
        /*
            r2 = this;
            int r0 = r3 + (-1)
        L2:
            r1 = -1
            if (r1 >= r0) goto L16
            java.lang.Object[] r1 = r2.values
            r1 = r1[r0]
            if (r1 != r4) goto Lc
            return r0
        Lc:
            int r1 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r1)
            if (r1 == r5) goto L13
            goto L16
        L13:
            int r0 = r0 + (-1)
            goto L2
        L16:
            int r3 = r3 + 1
            int r0 = r2.size()
        L1c:
            if (r3 >= r0) goto L32
            java.lang.Object[] r1 = r2.values
            r1 = r1[r3]
            if (r1 != r4) goto L25
            return r3
        L25:
            int r1 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r1)
            if (r1 == r5) goto L2f
        L2b:
            int r3 = r3 + 1
            int r3 = -r3
            return r3
        L2f:
            int r3 = r3 + 1
            goto L1c
        L32:
            int r3 = r2.size()
            goto L2b
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.IdentityArraySet.findExactIndex(int, java.lang.Object, int):int");
    }

    private final void checkIndexBounds(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index " + index + ", size " + size());
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection<? extends Object> collection = elements;
        if (collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* compiled from: IdentityArraySet.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\u000e\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"androidx/compose/runtime/collection/IdentityArraySet$iterator$1", "", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "next", "()Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.compose.runtime.collection.IdentityArraySet$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private int index;
        final /* synthetic */ IdentityArraySet<T> this$0;

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        AnonymousClass1(IdentityArraySet<T> identityArraySet) {
            this.this$0 = identityArraySet;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.this$0.size();
        }

        @Override // java.util.Iterator
        public T next() {
            Object[] values = this.this$0.getValues();
            int i = this.index;
            this.index = i + 1;
            T t = (T) values[i];
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
            return t;
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }
}
