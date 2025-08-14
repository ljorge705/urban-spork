package androidx.compose.runtime.collection;

import androidx.compose.runtime.ActualJvm_jvmKt;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityArrayMap.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u001aJ\u0012\u0010\u001b\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001e\u001a\u00020\u0005H\u0002JD\u0010\u001f\u001a\u00020\u001626\u0010 \u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00160!H\u0086\bø\u0001\u0000J\u0018\u0010%\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020\u0018J\u0006\u0010(\u001a\u00020\u0018J\u0013\u0010)\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aJD\u0010*\u001a\u00020\u001626\u0010 \u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00180!H\u0086\bø\u0001\u0000J/\u0010+\u001a\u00020\u00162!\u0010 \u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00180,H\u0086\bø\u0001\u0000J\u001e\u0010-\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0002\u0010.R$\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u000e\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0006R$\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006/"}, d2 = {"Landroidx/compose/runtime/collection/IdentityArrayMap;", "Key", "", "Value", "capacity", "", "(I)V", QuickTimeAtomTypes.ATOM_KEYS, "", "getKeys$runtime_release", "()[Ljava/lang/Object;", "setKeys$runtime_release", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", RRWebVideoEvent.JsonKeys.SIZE, "getSize$runtime_release", "()I", "setSize$runtime_release", "values", "getValues$runtime_release", "setValues$runtime_release", "clear", "", "contains", "", Constants.KEY_KEY, "(Ljava/lang/Object;)Z", "find", "findExactIndex", "midIndex", "keyHash", "forEach", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "remove", "removeIf", "removeValueIf", "Lkotlin/Function1;", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IdentityArrayMap<Key, Value> {
    private Object[] keys;
    private int size;
    private Object[] values;

    public IdentityArrayMap() {
        this(0, 1, null);
    }

    /* renamed from: getKeys$runtime_release, reason: from getter */
    public final Object[] getKeys() {
        return this.keys;
    }

    /* renamed from: getSize$runtime_release, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    /* renamed from: getValues$runtime_release, reason: from getter */
    public final Object[] getValues() {
        return this.values;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isNotEmpty() {
        return this.size > 0;
    }

    public final void setKeys$runtime_release(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.keys = objArr;
    }

    public final void setSize$runtime_release(int i) {
        this.size = i;
    }

    public final void setValues$runtime_release(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.values = objArr;
    }

    public IdentityArrayMap(int i) {
        this.keys = new Object[i];
        this.values = new Object[i];
    }

    public /* synthetic */ IdentityArrayMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i);
    }

    public final boolean contains(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return find(key) >= 0;
    }

    public final Value get(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iFind = find(key);
        if (iFind >= 0) {
            return (Value) this.values[iFind];
        }
        return null;
    }

    public final void set(Key key, Value value) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iFind = find(key);
        if (iFind >= 0) {
            this.values[iFind] = value;
            return;
        }
        int i = -(iFind + 1);
        int i2 = this.size;
        Object[] objArr = this.keys;
        boolean z = i2 == objArr.length;
        Object[] objArr2 = z ? new Object[i2 * 2] : objArr;
        int i3 = i + 1;
        ArraysKt.copyInto(objArr, objArr2, i3, i, i2);
        if (z) {
            ArraysKt.copyInto$default(this.keys, objArr2, 0, 0, i, 6, (Object) null);
        }
        objArr2[i] = key;
        this.keys = objArr2;
        Object[] objArr3 = z ? new Object[this.size * 2] : this.values;
        ArraysKt.copyInto(this.values, objArr3, i3, i, this.size);
        if (z) {
            ArraysKt.copyInto$default(this.values, objArr3, 0, 0, i, 6, (Object) null);
        }
        objArr3[i] = value;
        this.values = objArr3;
        this.size++;
    }

    public final boolean remove(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iFind = find(key);
        if (iFind < 0) {
            return false;
        }
        int i = this.size;
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int i2 = iFind + 1;
        ArraysKt.copyInto(objArr, objArr, iFind, i2, i);
        ArraysKt.copyInto(objArr2, objArr2, iFind, i2, i);
        int i3 = i - 1;
        objArr[i3] = null;
        objArr2[i3] = null;
        this.size = i3;
        return true;
    }

    public final void clear() {
        this.size = 0;
        ArraysKt.fill$default(this.keys, (Object) null, 0, 0, 6, (Object) null);
        ArraysKt.fill$default(this.values, (Object) null, 0, 0, 6, (Object) null);
    }

    public final void removeIf(Function2<? super Key, ? super Value, Boolean> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = getKeys()[i2];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            if (!block.invoke(obj, getValues()[i2]).booleanValue()) {
                if (i != i2) {
                    getKeys()[i] = obj;
                    getValues()[i] = getValues()[i2];
                }
                i++;
            }
        }
        if (getSize() > i) {
            int size2 = getSize();
            for (int i3 = i; i3 < size2; i3++) {
                getKeys()[i3] = null;
                getValues()[i3] = null;
            }
            setSize$runtime_release(i);
        }
    }

    public final void forEach(Function2<? super Key, ? super Value, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int size = getSize();
        for (int i = 0; i < size; i++) {
            Object obj = getKeys()[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            block.invoke(obj, getValues()[i]);
        }
    }

    private final int find(Object key) {
        int iIdentityHashCode = ActualJvm_jvmKt.identityHashCode(key);
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj = this.keys[i3];
            int iIdentityHashCode2 = ActualJvm_jvmKt.identityHashCode(obj);
            if (iIdentityHashCode2 < iIdentityHashCode) {
                i2 = i3 + 1;
            } else {
                if (iIdentityHashCode2 <= iIdentityHashCode) {
                    return key == obj ? i3 : findExactIndex(i3, key, iIdentityHashCode);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
    
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
            java.lang.Object[] r1 = r2.keys
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
            int r0 = r2.size
        L1a:
            if (r3 >= r0) goto L30
            java.lang.Object[] r1 = r2.keys
            r1 = r1[r3]
            if (r1 != r4) goto L23
            return r3
        L23:
            int r1 = androidx.compose.runtime.ActualJvm_jvmKt.identityHashCode(r1)
            if (r1 == r5) goto L2d
        L29:
            int r3 = r3 + 1
            int r3 = -r3
            return r3
        L2d:
            int r3 = r3 + 1
            goto L1a
        L30:
            int r3 = r2.size
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.IdentityArrayMap.findExactIndex(int, java.lang.Object, int):int");
    }

    public final void removeValueIf(Function1<? super Value, Boolean> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int size = getSize();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = getKeys()[i2];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
            if (!block.invoke(getValues()[i2]).booleanValue()) {
                if (i != i2) {
                    getKeys()[i] = obj;
                    getValues()[i] = getValues()[i2];
                }
                i++;
            }
        }
        if (getSize() > i) {
            int size2 = getSize();
            for (int i3 = i; i3 < size2; i3++) {
                getKeys()[i3] = null;
                getValues()[i3] = null;
            }
            setSize$runtime_release(i);
        }
    }
}
