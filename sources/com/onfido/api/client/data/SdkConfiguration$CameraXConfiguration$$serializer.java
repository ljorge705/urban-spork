package com.onfido.api.client.data;

import com.onfido.api.client.data.SdkConfiguration;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.CameraXConfiguration.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$CameraXConfiguration$$serializer implements GeneratedSerializer<SdkConfiguration.CameraXConfiguration> {
    public static final SdkConfiguration$CameraXConfiguration$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$CameraXConfiguration$$serializer sdkConfiguration$CameraXConfiguration$$serializer = new SdkConfiguration$CameraXConfiguration$$serializer();
        INSTANCE = sdkConfiguration$CameraXConfiguration$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration.CameraXConfiguration", sdkConfiguration$CameraXConfiguration$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("should_interrupt_video_recoverable_error", true);
        pluginGeneratedSerialDescriptor.addElement("should_interrupt_image_recoverable_error", true);
        pluginGeneratedSerialDescriptor.addElement("should_remove_preview_video_recording", true);
        pluginGeneratedSerialDescriptor.addElement("should_use_resolution_strategy", true);
        pluginGeneratedSerialDescriptor.addElement("should_retry_view_port_failure", true);
        pluginGeneratedSerialDescriptor.addElement("is_view_port_required", true);
        pluginGeneratedSerialDescriptor.addElement("is_fallback_enabled", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$CameraXConfiguration$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration.CameraXConfiguration deserialize(Decoder decoder) {
        boolean zDecodeBooleanElement;
        boolean zDecodeBooleanElement2;
        boolean z;
        int i;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            boolean zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
            boolean zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
            boolean zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 2);
            boolean zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 3);
            boolean zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 4);
            boolean zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
            z5 = zDecodeBooleanElement3;
            zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
            z = zDecodeBooleanElement8;
            z4 = zDecodeBooleanElement6;
            z2 = zDecodeBooleanElement7;
            zDecodeBooleanElement = zDecodeBooleanElement5;
            z3 = zDecodeBooleanElement4;
            i = 127;
        } else {
            boolean z6 = true;
            boolean zDecodeBooleanElement9 = false;
            boolean zDecodeBooleanElement10 = false;
            boolean zDecodeBooleanElement11 = false;
            boolean zDecodeBooleanElement12 = false;
            boolean zDecodeBooleanElement13 = false;
            zDecodeBooleanElement = false;
            boolean zDecodeBooleanElement14 = false;
            int i2 = 0;
            while (z6) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z6 = false;
                        continue;
                    case 0:
                        zDecodeBooleanElement9 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
                        i2 |= 1;
                        continue;
                    case 1:
                        zDecodeBooleanElement14 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
                        i2 |= 2;
                        break;
                    case 2:
                        zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 2);
                        i2 |= 4;
                        break;
                    case 3:
                        zDecodeBooleanElement12 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 3);
                        i2 |= 8;
                        break;
                    case 4:
                        zDecodeBooleanElement13 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 4);
                        i2 |= 16;
                        break;
                    case 5:
                        zDecodeBooleanElement11 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        zDecodeBooleanElement10 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
                        i2 |= 64;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            zDecodeBooleanElement2 = zDecodeBooleanElement10;
            z = zDecodeBooleanElement11;
            i = i2;
            z2 = zDecodeBooleanElement13;
            z3 = zDecodeBooleanElement14;
            z4 = zDecodeBooleanElement12;
            z5 = zDecodeBooleanElement9;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration.CameraXConfiguration(i, z5, z3, zDecodeBooleanElement, z4, z2, z, zDecodeBooleanElement2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration.CameraXConfiguration value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.CameraXConfiguration.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
