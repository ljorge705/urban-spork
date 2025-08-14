package com.onfido.hosted.web.module.model;

import com.onfido.hosted.web.module.model.ProofOfAddressCaptureSDKOutput;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/hosted/web/module/model/ProofOfAddressCaptureSDKOutput.Sides.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/hosted/web/module/model/ProofOfAddressCaptureSDKOutput$Sides;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class ProofOfAddressCaptureSDKOutput$Sides$$serializer implements GeneratedSerializer<ProofOfAddressCaptureSDKOutput.Sides> {
    public static final ProofOfAddressCaptureSDKOutput$Sides$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        ProofOfAddressCaptureSDKOutput$Sides$$serializer proofOfAddressCaptureSDKOutput$Sides$$serializer = new ProofOfAddressCaptureSDKOutput$Sides$$serializer();
        INSTANCE = proofOfAddressCaptureSDKOutput$Sides$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.hosted.web.module.model.ProofOfAddressCaptureSDKOutput.Sides", proofOfAddressCaptureSDKOutput$Sides$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("front", false);
        pluginGeneratedSerialDescriptor.addElement("back", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private ProofOfAddressCaptureSDKOutput$Sides$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        ProofOfAddressCaptureSDKOutput$Side$$serializer proofOfAddressCaptureSDKOutput$Side$$serializer = ProofOfAddressCaptureSDKOutput$Side$$serializer.INSTANCE;
        return new KSerializer[]{proofOfAddressCaptureSDKOutput$Side$$serializer, BuiltinSerializersKt.getNullable(proofOfAddressCaptureSDKOutput$Side$$serializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public ProofOfAddressCaptureSDKOutput.Sides deserialize(Decoder decoder) {
        ProofOfAddressCaptureSDKOutput.Side side;
        int i;
        ProofOfAddressCaptureSDKOutput.Side side2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            ProofOfAddressCaptureSDKOutput$Side$$serializer proofOfAddressCaptureSDKOutput$Side$$serializer = ProofOfAddressCaptureSDKOutput$Side$$serializer.INSTANCE;
            side2 = (ProofOfAddressCaptureSDKOutput.Side) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, proofOfAddressCaptureSDKOutput$Side$$serializer, null);
            side = (ProofOfAddressCaptureSDKOutput.Side) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, proofOfAddressCaptureSDKOutput$Side$$serializer, null);
            i = 3;
        } else {
            boolean z = true;
            int i2 = 0;
            side = null;
            ProofOfAddressCaptureSDKOutput.Side side3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    side3 = (ProofOfAddressCaptureSDKOutput.Side) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, ProofOfAddressCaptureSDKOutput$Side$$serializer.INSTANCE, side3);
                    i2 |= 1;
                } else {
                    if (iDecodeElementIndex != 1) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    side = (ProofOfAddressCaptureSDKOutput.Side) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, ProofOfAddressCaptureSDKOutput$Side$$serializer.INSTANCE, side);
                    i2 |= 2;
                }
            }
            i = i2;
            side2 = side3;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new ProofOfAddressCaptureSDKOutput.Sides(i, side2, side, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, ProofOfAddressCaptureSDKOutput.Sides value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        ProofOfAddressCaptureSDKOutput.Sides.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
