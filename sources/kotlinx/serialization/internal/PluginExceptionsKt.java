package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* compiled from: PluginExceptions.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, d2 = {"throwArrayMissingFieldException", "", "seenArray", "", "goldenMaskArray", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "throwMissingFieldException", "seen", "", "goldenMask", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class PluginExceptionsKt {
    @InternalSerializationApi
    public static final void throwMissingFieldException(int i, int i2, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ArrayList arrayList = new ArrayList();
        int i3 = (~i) & i2;
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if ((i3 & 1) != 0) {
                arrayList.add(descriptor.getElementName(i4));
            }
            i3 >>>= 1;
            if (i5 >= 32) {
                throw new MissingFieldException(arrayList, descriptor.getSerialName());
            }
            i4 = i5;
        }
    }

    @InternalSerializationApi
    public static final void throwArrayMissingFieldException(int[] seenArray, int[] goldenMaskArray, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(seenArray, "seenArray");
        Intrinsics.checkNotNullParameter(goldenMaskArray, "goldenMaskArray");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ArrayList arrayList = new ArrayList();
        int length = goldenMaskArray.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                int i3 = goldenMaskArray[i] & (~seenArray[i]);
                if (i3 != 0) {
                    int i4 = 0;
                    while (true) {
                        int i5 = i4 + 1;
                        if ((i3 & 1) != 0) {
                            arrayList.add(descriptor.getElementName((i * 32) + i4));
                        }
                        i3 >>>= 1;
                        if (i5 >= 32) {
                            break;
                        } else {
                            i4 = i5;
                        }
                    }
                }
                if (i2 > length) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        throw new MissingFieldException(arrayList, descriptor.getSerialName());
    }
}
