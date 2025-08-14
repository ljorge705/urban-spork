package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.Enum;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Enums.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0005H\u0016R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlinx/serialization/internal/EnumSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/KSerializer;", "serialName", "", "values", "", "(Ljava/lang/String;[Ljava/lang/Enum;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "[Ljava/lang/Enum;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Enum;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Enum;)V", "toString", "kotlinx-serialization-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class EnumSerializer<T extends Enum<T>> implements KSerializer<T> {
    private final SerialDescriptor descriptor;
    private final T[] values;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public EnumSerializer(final String serialName, T[] values) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = values;
        this.descriptor = SerialDescriptorsKt.buildSerialDescriptor(serialName, SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], new Function1<ClassSerialDescriptorBuilder, Unit>(this) { // from class: kotlinx.serialization.internal.EnumSerializer$descriptor$1
            final /* synthetic */ EnumSerializer<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
                invoke2(classSerialDescriptorBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ClassSerialDescriptorBuilder buildSerialDescriptor) {
                Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
                Enum[] enumArr = ((EnumSerializer) this.this$0).values;
                String str = serialName;
                for (Enum r2 : enumArr) {
                    ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, r2.name(), SerialDescriptorsKt.buildSerialDescriptor$default(str + ClassUtils.PACKAGE_SEPARATOR_CHAR + r2.name(), StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], null, 8, null), null, false, 12, null);
                }
            }
        });
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        int iIndexOf = ArraysKt.indexOf(this.values, value);
        if (iIndexOf == -1) {
            StringBuilder sbAppend = new StringBuilder().append(value).append(" is not a valid enum ").append(getDescriptor().getSerialName()).append(", must be one of ");
            String string = Arrays.toString(this.values);
            Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
            throw new SerializationException(sbAppend.append(string).toString());
        }
        encoder.encodeEnum(getDescriptor(), iIndexOf);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public T deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        int iDecodeEnum = decoder.decodeEnum(getDescriptor());
        if (iDecodeEnum >= 0) {
            T[] tArr = this.values;
            if (iDecodeEnum <= tArr.length - 1) {
                return tArr[iDecodeEnum];
            }
        }
        throw new SerializationException(iDecodeEnum + " is not among valid " + getDescriptor().getSerialName() + " enum values, values size is " + this.values.length);
    }

    public String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().getSerialName() + Typography.greater;
    }
}
