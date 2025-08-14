package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;

/* compiled from: TreeJsonEncoder.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0002\u001a\u0002H\u0003\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b¢\u0006\u0002\u0010\b\u001a-\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0003*\u00020\n2\u0006\u0010\u0005\u001a\u0002H\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00030\fH\u0000¢\u0006\u0002\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"PRIMITIVE_TAG", "", "cast", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/JsonElement;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "(Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/JsonElement;", "writeJson", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/json/Json;Ljava/lang/Object;Lkotlinx/serialization/SerializationStrategy;)Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class TreeJsonEncoderKt {
    public static final String PRIMITIVE_TAG = "primitive";

    public static final <T> JsonElement writeJson(Json json, T t, SerializationStrategy<? super T> serializer) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        new JsonTreeEncoder(json, new Function1<JsonElement, Unit>() { // from class: kotlinx.serialization.json.internal.TreeJsonEncoderKt$writeJson$encoder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsonElement jsonElement) {
                invoke2(jsonElement);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsonElement it) {
                Intrinsics.checkNotNullParameter(it, "it");
                objectRef.element = it;
            }
        }).encodeSerializableValue(serializer, t);
        if (objectRef.element != null) {
            return (JsonElement) objectRef.element;
        }
        Intrinsics.throwUninitializedPropertyAccessException(OnfidoLauncher.KEY_RESULT);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ <T extends JsonElement> T cast(JsonElement value, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (value instanceof JsonElement) {
            return value;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.append(Reflection.getOrCreateKotlinClass(JsonElement.class)).append(" as the serialized body of ").append(descriptor.getSerialName()).append(", but had ").append(Reflection.getOrCreateKotlinClass(value.getClass())).toString());
    }
}
