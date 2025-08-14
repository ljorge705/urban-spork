package androidx.compose.runtime.collection;

import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityScopeMap.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020(J\u0011\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u0002H\u0086\u0002J\u0012\u0010+\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u00022\u0006\u0010.\u001a\u00020\u000eH\u0002J7\u0010/\u001a\u00020(2\u0006\u0010$\u001a\u00020\u00022!\u00100\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020(01H\u0086\bø\u0001\u0000J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010$\u001a\u00020\u0002H\u0002J\u001b\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u0010&J\u0013\u00106\u001a\u00020(2\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u00107J/\u00108\u001a\u00020(2!\u00109\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020#01H\u0086\bø\u0001\u0000J#\u0010:\u001a\u00020(2\u0018\u0010;\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020(01H\u0082\bJ\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010=\u001a\u00020\u000eH\u0002J\u0011\u0010>\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u000eH\u0082\bR4\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010\f\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u00158\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR.\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006?"}, d2 = {"Landroidx/compose/runtime/collection/IdentityScopeMap;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "scopeSets", "", "Landroidx/compose/runtime/collection/IdentityArraySet;", "getScopeSets$annotations", "getScopeSets", "()[Landroidx/compose/runtime/collection/IdentityArraySet;", "setScopeSets", "([Landroidx/compose/runtime/collection/IdentityArraySet;)V", "[Landroidx/compose/runtime/collection/IdentityArraySet;", RRWebVideoEvent.JsonKeys.SIZE, "", "getSize$annotations", "getSize", "()I", "setSize", "(I)V", "valueOrder", "", "getValueOrder$annotations", "getValueOrder", "()[I", "setValueOrder", "([I)V", "values", "getValues$annotations", "getValues", "()[Ljava/lang/Object;", "setValues", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "add", "", "value", "scope", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "clear", "", "contains", "element", "find", "findExactIndex", "midIndex", "valueHash", "forEachScopeOf", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "getOrCreateIdentitySet", "remove", "removeScope", "(Ljava/lang/Object;)V", "removeValueIf", "predicate", "removingScopes", "removalOperation", "scopeSetAt", "index", "valueAt", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IdentityScopeMap<T> {
    private IdentityArraySet<T>[] scopeSets;
    private int size;
    private int[] valueOrder;
    private Object[] values;

    public static /* synthetic */ void getScopeSets$annotations() {
    }

    public static /* synthetic */ void getSize$annotations() {
    }

    public static /* synthetic */ void getValueOrder$annotations() {
    }

    public static /* synthetic */ void getValues$annotations() {
    }

    public final IdentityArraySet<T>[] getScopeSets() {
        return this.scopeSets;
    }

    public final int getSize() {
        return this.size;
    }

    public final int[] getValueOrder() {
        return this.valueOrder;
    }

    public final Object[] getValues() {
        return this.values;
    }

    public final void setScopeSets(IdentityArraySet<T>[] identityArraySetArr) {
        Intrinsics.checkNotNullParameter(identityArraySetArr, "<set-?>");
        this.scopeSets = identityArraySetArr;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public final void setValueOrder(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.valueOrder = iArr;
    }

    public final void setValues(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.values = objArr;
    }

    public IdentityScopeMap() {
        int[] iArr = new int[50];
        for (int i = 0; i < 50; i++) {
            iArr[i] = i;
        }
        this.valueOrder = iArr;
        this.values = new Object[50];
        this.scopeSets = new IdentityArraySet[50];
    }

    private final Object valueAt(int index) {
        Object obj = this.values[this.valueOrder[index]];
        Intrinsics.checkNotNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IdentityArraySet<T> scopeSetAt(int index) {
        IdentityArraySet<T> identityArraySet = this.scopeSets[this.valueOrder[index]];
        Intrinsics.checkNotNull(identityArraySet);
        return identityArraySet;
    }

    public final boolean add(Object value, T scope) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        return getOrCreateIdentitySet(value).add(scope);
    }

    public final boolean contains(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return find(element) >= 0;
    }

    public final void forEachScopeOf(Object value, Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(block, "block");
        int iFind = find(value);
        if (iFind >= 0) {
            IdentityArraySet identityArraySetScopeSetAt = scopeSetAt(iFind);
            int size = identityArraySetScopeSetAt.size();
            for (int i = 0; i < size; i++) {
                block.invoke((Object) identityArraySetScopeSetAt.get(i));
            }
        }
    }

    private final IdentityArraySet<T> getOrCreateIdentitySet(Object value) {
        int iFind;
        if (this.size > 0) {
            iFind = find(value);
            if (iFind >= 0) {
                return scopeSetAt(iFind);
            }
        } else {
            iFind = -1;
        }
        int i = -(iFind + 1);
        int i2 = this.size;
        int[] iArr = this.valueOrder;
        if (i2 < iArr.length) {
            int i3 = iArr[i2];
            this.values[i3] = value;
            IdentityArraySet<T> identityArraySet = this.scopeSets[i3];
            if (identityArraySet == null) {
                identityArraySet = new IdentityArraySet<>();
                this.scopeSets[i3] = identityArraySet;
            }
            int i4 = this.size;
            if (i < i4) {
                int[] iArr2 = this.valueOrder;
                ArraysKt.copyInto(iArr2, iArr2, i + 1, i, i4);
            }
            this.valueOrder[i] = i3;
            this.size++;
            return identityArraySet;
        }
        int length = iArr.length * 2;
        Object[] objArrCopyOf = Arrays.copyOf(this.scopeSets, length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        this.scopeSets = (IdentityArraySet[]) objArrCopyOf;
        IdentityArraySet<T> identityArraySet2 = new IdentityArraySet<>();
        this.scopeSets[i2] = identityArraySet2;
        Object[] objArrCopyOf2 = Arrays.copyOf(this.values, length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(this, newSize)");
        this.values = objArrCopyOf2;
        objArrCopyOf2[i2] = value;
        int[] iArr3 = new int[length];
        int i5 = this.size;
        while (true) {
            i5++;
            if (i5 >= length) {
                break;
            }
            iArr3[i5] = i5;
        }
        int i6 = this.size;
        if (i < i6) {
            ArraysKt.copyInto(this.valueOrder, iArr3, i + 1, i, i6);
        }
        iArr3[i] = i2;
        if (i > 0) {
            ArraysKt.copyInto$default(this.valueOrder, iArr3, 0, 0, i, 6, (Object) null);
        }
        this.valueOrder = iArr3;
        this.size++;
        return identityArraySet2;
    }

    public final void clear() {
        int length = this.scopeSets.length;
        for (int i = 0; i < length; i++) {
            IdentityArraySet<T> identityArraySet = this.scopeSets[i];
            if (identityArraySet != null) {
                identityArraySet.clear();
            }
            this.valueOrder[i] = i;
            this.values[i] = null;
        }
        this.size = 0;
    }

    public final boolean remove(Object value, T scope) {
        int i;
        IdentityArraySet<T> identityArraySet;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        int iFind = find(value);
        if (iFind < 0 || (identityArraySet = this.scopeSets[(i = this.valueOrder[iFind])]) == null) {
            return false;
        }
        boolean zRemove = identityArraySet.remove(scope);
        if (identityArraySet.size() == 0) {
            int i2 = iFind + 1;
            int i3 = this.size;
            if (i2 < i3) {
                int[] iArr = this.valueOrder;
                ArraysKt.copyInto(iArr, iArr, iFind, i2, i3);
            }
            int[] iArr2 = this.valueOrder;
            int i4 = this.size;
            iArr2[i4 - 1] = i;
            this.values[i] = null;
            this.size = i4 - 1;
        }
        return zRemove;
    }

    private final void removingScopes(Function1<? super IdentityArraySet<T>, Unit> removalOperation) {
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = getValueOrder()[i2];
            IdentityArraySet<T> identityArraySet = getScopeSets()[i3];
            Intrinsics.checkNotNull(identityArraySet);
            removalOperation.invoke(identityArraySet);
            if (identityArraySet.size() > 0) {
                if (i != i2) {
                    int i4 = getValueOrder()[i];
                    getValueOrder()[i] = i3;
                    getValueOrder()[i2] = i4;
                }
                i++;
            }
        }
        int size2 = getSize();
        for (int i5 = i; i5 < size2; i5++) {
            getValues()[getValueOrder()[i5]] = null;
        }
        setSize(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int find(Object value) {
        int iIdentityHashCode = ActualJvm_jvmKt.identityHashCode(value);
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj = this.values[this.valueOrder[i3]];
            Intrinsics.checkNotNull(obj);
            int iIdentityHashCode2 = ActualJvm_jvmKt.identityHashCode(obj);
            if (iIdentityHashCode2 < iIdentityHashCode) {
                i2 = i3 + 1;
            } else {
                if (iIdentityHashCode2 <= iIdentityHashCode) {
                    return value == obj ? i3 : findExactIndex(i3, value, iIdentityHashCode);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public final void removeValueIf(Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = getValueOrder()[i2];
            IdentityArraySet<T> identityArraySet = getScopeSets()[i3];
            Intrinsics.checkNotNull(identityArraySet);
            int size2 = identityArraySet.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size2; i5++) {
                Object obj = identityArraySet.getValues()[i5];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                if (!predicate.invoke(obj).booleanValue()) {
                    if (i4 != i5) {
                        identityArraySet.getValues()[i4] = obj;
                    }
                    i4++;
                }
            }
            int size3 = identityArraySet.size();
            for (int i6 = i4; i6 < size3; i6++) {
                identityArraySet.getValues()[i6] = null;
            }
            identityArraySet.setSize(i4);
            if (identityArraySet.size() > 0) {
                if (i != i2) {
                    int i7 = getValueOrder()[i];
                    getValueOrder()[i] = i3;
                    getValueOrder()[i2] = i7;
                }
                i++;
            }
        }
        int size4 = getSize();
        for (int i8 = i; i8 < size4; i8++) {
            getValues()[getValueOrder()[i8]] = null;
        }
        setSize(i);
    }

    public final void removeScope(T scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = getValueOrder()[i2];
            IdentityArraySet<T> identityArraySet = getScopeSets()[i3];
            Intrinsics.checkNotNull(identityArraySet);
            identityArraySet.remove(scope);
            if (identityArraySet.size() > 0) {
                if (i != i2) {
                    int i4 = getValueOrder()[i];
                    getValueOrder()[i] = i3;
                    getValueOrder()[i2] = i4;
                }
                i++;
            }
        }
        int size2 = getSize();
        for (int i5 = i; i5 < size2; i5++) {
            getValues()[getValueOrder()[i5]] = null;
        }
        setSize(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
    
        return -(r4 + 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int findExactIndex(int r4, java.lang.Object r5, int r6) {
        /*
            r3 = this;
            int r0 = r4 + (-1)
        L2:
            r1 = -1
            if (r1 >= r0) goto L1d
            java.lang.Object[] r1 = r3.values
            int[] r2 = r3.valueOrder
            r2 = r2[r0]
            r1 = r1[r2]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            if (r1 != r5) goto L13
            return r0
        L13:
            int r1 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r1)
            if (r1 == r6) goto L1a
            goto L1d
        L1a:
            int r0 = r0 + (-1)
            goto L2
        L1d:
            int r4 = r4 + 1
            int r0 = r3.size
        L21:
            if (r4 >= r0) goto L3e
            java.lang.Object[] r1 = r3.values
            int[] r2 = r3.valueOrder
            r2 = r2[r4]
            r1 = r1[r2]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            if (r1 != r5) goto L31
            return r4
        L31:
            int r1 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r1)
            if (r1 == r6) goto L3b
        L37:
            int r4 = r4 + 1
            int r4 = -r4
            return r4
        L3b:
            int r4 = r4 + 1
            goto L21
        L3e:
            int r4 = r3.size
            goto L37
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.IdentityScopeMap.findExactIndex(int, java.lang.Object, int):int");
    }
}
