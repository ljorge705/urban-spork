package com.onfido.hosted.web.module.model;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class CaptureSDKBootstrapConfig$$serializer implements GeneratedSerializer<CaptureSDKBootstrapConfig> {
    public static final CaptureSDKBootstrapConfig$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        CaptureSDKBootstrapConfig$$serializer captureSDKBootstrapConfig$$serializer = new CaptureSDKBootstrapConfig$$serializer();
        INSTANCE = captureSDKBootstrapConfig$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.hosted.web.module.model.CaptureSDKBootstrapConfig", captureSDKBootstrapConfig$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("clientConfiguration", false);
        pluginGeneratedSerialDescriptor.addElement("studio", true);
        pluginGeneratedSerialDescriptor.addElement("module", true);
        pluginGeneratedSerialDescriptor.addElement(OnfidoLauncher.KEY_CONFIG, false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private CaptureSDKBootstrapConfig$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{CaptureSDKClientConfiguration$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(CaptureSDKStudio$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(CaptureSDKClassic$$serializer.INSTANCE), JsonObjectSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public CaptureSDKBootstrapConfig deserialize(Decoder decoder) {
        int i;
        CaptureSDKClientConfiguration captureSDKClientConfiguration;
        CaptureSDKStudio captureSDKStudio;
        CaptureSDKClassic captureSDKClassic;
        JsonObject jsonObject;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        CaptureSDKClientConfiguration captureSDKClientConfiguration2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            CaptureSDKClientConfiguration captureSDKClientConfiguration3 = (CaptureSDKClientConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, CaptureSDKClientConfiguration$$serializer.INSTANCE, null);
            CaptureSDKStudio captureSDKStudio2 = (CaptureSDKStudio) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, CaptureSDKStudio$$serializer.INSTANCE, null);
            CaptureSDKClassic captureSDKClassic2 = (CaptureSDKClassic) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, CaptureSDKClassic$$serializer.INSTANCE, null);
            captureSDKClientConfiguration = captureSDKClientConfiguration3;
            jsonObject = (JsonObject) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, JsonObjectSerializer.INSTANCE, null);
            captureSDKClassic = captureSDKClassic2;
            captureSDKStudio = captureSDKStudio2;
            i = 15;
        } else {
            boolean z = true;
            int i2 = 0;
            CaptureSDKStudio captureSDKStudio3 = null;
            CaptureSDKClassic captureSDKClassic3 = null;
            JsonObject jsonObject2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    captureSDKClientConfiguration2 = (CaptureSDKClientConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, CaptureSDKClientConfiguration$$serializer.INSTANCE, captureSDKClientConfiguration2);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    captureSDKStudio3 = (CaptureSDKStudio) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, CaptureSDKStudio$$serializer.INSTANCE, captureSDKStudio3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    captureSDKClassic3 = (CaptureSDKClassic) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, CaptureSDKClassic$$serializer.INSTANCE, captureSDKClassic3);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    jsonObject2 = (JsonObject) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, JsonObjectSerializer.INSTANCE, jsonObject2);
                    i2 |= 8;
                }
            }
            i = i2;
            captureSDKClientConfiguration = captureSDKClientConfiguration2;
            captureSDKStudio = captureSDKStudio3;
            captureSDKClassic = captureSDKClassic3;
            jsonObject = jsonObject2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new CaptureSDKBootstrapConfig(i, captureSDKClientConfiguration, captureSDKStudio, captureSDKClassic, jsonObject, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, CaptureSDKBootstrapConfig value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        CaptureSDKBootstrapConfig.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
