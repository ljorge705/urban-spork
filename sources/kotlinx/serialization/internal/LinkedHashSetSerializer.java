package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* compiled from: CollectionSerializers.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\b\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012*\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u0002H\u00010\u0004j\b\u0012\u0004\u0012\u0002H\u0001`\u00050\u0002B\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014J\u001c\u0010\u000e\u001a\u00020\u000f*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014J$\u0010\u0010\u001a\u00020\u0011*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J\u0012\u0010\u0015\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J1\u0010\u0016\u001a\u00020\u0011*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u00052\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019J\"\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0014J\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003*\u0012\u0012\u0004\u0012\u00028\u00000\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u0005H\u0014R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/internal/LinkedHashSetSerializer;", ExifInterface.LONGITUDE_EAST, "Lkotlinx/serialization/internal/ListLikeSerializer;", "", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "eSerializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "builder", "builderSize", "", "checkCapacity", "", RRWebVideoEvent.JsonKeys.SIZE, "collectionIterator", "", "collectionSize", "insert", "index", "element", "(Ljava/util/LinkedHashSet;ILjava/lang/Object;)V", "toBuilder", "toResult", "kotlinx-serialization-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class LinkedHashSetSerializer<E> extends ListLikeSerializer<E, Set<? extends E>, LinkedHashSet<E>> {
    private final SerialDescriptor descriptor;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(LinkedHashSet<E> linkedHashSet, int i) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public /* bridge */ /* synthetic */ void insert(Object obj, int i, Object obj2) {
        insert((LinkedHashSet<int>) obj, i, (int) obj2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkedHashSetSerializer(KSerializer<E> eSerializer) {
        super(eSerializer, null);
        Intrinsics.checkNotNullParameter(eSerializer, "eSerializer");
        this.descriptor = new LinkedHashSetClassDesc(eSerializer.getDescriptor());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Set<? extends E> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        return set.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Iterator<E> collectionIterator(Set<? extends E> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        return set.iterator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public LinkedHashSet<E> builder() {
        return new LinkedHashSet<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(LinkedHashSet<E> linkedHashSet) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        return linkedHashSet.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Set<E> toResult(LinkedHashSet<E> linkedHashSet) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public LinkedHashSet<E> toBuilder(Set<? extends E> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        LinkedHashSet<E> linkedHashSet = set instanceof LinkedHashSet ? (LinkedHashSet) set : null;
        return linkedHashSet == null ? new LinkedHashSet<>(set) : linkedHashSet;
    }

    protected void insert(LinkedHashSet<E> linkedHashSet, int i, E e) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        linkedHashSet.add(e);
    }
}
