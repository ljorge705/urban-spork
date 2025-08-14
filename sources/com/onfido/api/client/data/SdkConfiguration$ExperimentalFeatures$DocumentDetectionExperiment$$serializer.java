package com.onfido.api.client.data;

import com.facebook.react.uimanager.ViewProps;
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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer implements GeneratedSerializer<SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment> {
    public static final SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer sdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer = new SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer();
        INSTANCE = sdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment", sdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement(ViewProps.ENABLED, true);
        pluginGeneratedSerialDescriptor.addElement("should_fallback", true);
        pluginGeneratedSerialDescriptor.addElement("hold_duration_in_ms", true);
        pluginGeneratedSerialDescriptor.addElement("threshold", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, IntSerializer.INSTANCE, FloatSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment deserialize(Decoder decoder) {
        boolean z;
        float fDecodeFloatElement;
        int i;
        boolean z2;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
            boolean zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 2);
            z = zDecodeBooleanElement;
            fDecodeFloatElement = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 3);
            i = iDecodeIntElement;
            z2 = zDecodeBooleanElement2;
            i2 = 15;
        } else {
            float fDecodeFloatElement2 = 0.0f;
            boolean z3 = true;
            boolean zDecodeBooleanElement3 = false;
            int iDecodeIntElement2 = 0;
            boolean zDecodeBooleanElement4 = false;
            int i3 = 0;
            while (z3) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z3 = false;
                } else if (iDecodeElementIndex == 0) {
                    zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
                    i3 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
                    i3 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 2);
                    i3 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    fDecodeFloatElement2 = compositeDecoderBeginStructure.decodeFloatElement(descriptor2, 3);
                    i3 |= 8;
                }
            }
            z = zDecodeBooleanElement3;
            fDecodeFloatElement = fDecodeFloatElement2;
            i = iDecodeIntElement2;
            z2 = zDecodeBooleanElement4;
            i2 = i3;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment(i2, z, z2, i, fDecodeFloatElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
