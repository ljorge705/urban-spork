package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* compiled from: JsonElementMarker.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
/* synthetic */ class JsonElementMarker$origin$1 extends FunctionReferenceImpl implements Function2<SerialDescriptor, Integer, Boolean> {
    JsonElementMarker$origin$1(Object obj) {
        super(2, obj, JsonElementMarker.class, "readIfAbsent", "readIfAbsent(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", 0);
    }

    public final Boolean invoke(SerialDescriptor p0, int i) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Boolean.valueOf(((JsonElementMarker) this.receiver).readIfAbsent(p0, i));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(SerialDescriptor serialDescriptor, Integer num) {
        return invoke(serialDescriptor, num.intValue());
    }
}
