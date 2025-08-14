package com.onfido.api.client.data;

import com.onfido.api.client.data.SdkConfiguration;
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
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$$serializer implements GeneratedSerializer<SdkConfiguration> {
    public static final SdkConfiguration$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$$serializer sdkConfiguration$$serializer = new SdkConfiguration$$serializer();
        INSTANCE = sdkConfiguration$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration", sdkConfiguration$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("validations", true);
        pluginGeneratedSerialDescriptor.addElement("document_capture", true);
        pluginGeneratedSerialDescriptor.addElement("experimental_features", true);
        pluginGeneratedSerialDescriptor.addElement(SdkConfiguration.FIELD_LIVENESS_CAPTURE, true);
        pluginGeneratedSerialDescriptor.addElement(SdkConfiguration.FIELD_SELFIE_CAPTURE, true);
        pluginGeneratedSerialDescriptor.addElement(SdkConfiguration.FIELD_MOTION_CAPTURE, true);
        pluginGeneratedSerialDescriptor.addElement("sdk_features", true);
        pluginGeneratedSerialDescriptor.addElement("source", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(SdkConfiguration$Validations$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$DocumentCapture$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$LivenessCapture$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$SelfieCapture$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$MotionCapture$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$SdkFeatures$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration deserialize(Decoder decoder) {
        String str;
        int i;
        SdkConfiguration.SdkFeatures sdkFeatures;
        SdkConfiguration.Validations validations;
        SdkConfiguration.ExperimentalFeatures experimentalFeatures;
        SdkConfiguration.SelfieCapture selfieCapture;
        SdkConfiguration.MotionCapture motionCapture;
        SdkConfiguration.DocumentCapture documentCapture;
        SdkConfiguration.LivenessCapture livenessCapture;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 7;
        SdkConfiguration.Validations validations2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            SdkConfiguration.Validations validations3 = (SdkConfiguration.Validations) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, SdkConfiguration$Validations$$serializer.INSTANCE, null);
            SdkConfiguration.DocumentCapture documentCapture2 = (SdkConfiguration.DocumentCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, SdkConfiguration$DocumentCapture$$serializer.INSTANCE, null);
            SdkConfiguration.ExperimentalFeatures experimentalFeatures2 = (SdkConfiguration.ExperimentalFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE, null);
            SdkConfiguration.LivenessCapture livenessCapture2 = (SdkConfiguration.LivenessCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, SdkConfiguration$LivenessCapture$$serializer.INSTANCE, null);
            SdkConfiguration.SelfieCapture selfieCapture2 = (SdkConfiguration.SelfieCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, SdkConfiguration$SelfieCapture$$serializer.INSTANCE, null);
            SdkConfiguration.MotionCapture motionCapture2 = (SdkConfiguration.MotionCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, SdkConfiguration$MotionCapture$$serializer.INSTANCE, null);
            SdkConfiguration.SdkFeatures sdkFeatures2 = (SdkConfiguration.SdkFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, SdkConfiguration$SdkFeatures$$serializer.INSTANCE, null);
            str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, StringSerializer.INSTANCE, null);
            sdkFeatures = sdkFeatures2;
            motionCapture = motionCapture2;
            livenessCapture = livenessCapture2;
            selfieCapture = selfieCapture2;
            experimentalFeatures = experimentalFeatures2;
            i = 255;
            documentCapture = documentCapture2;
            validations = validations3;
        } else {
            boolean z = true;
            int i3 = 0;
            String str2 = null;
            SdkConfiguration.SdkFeatures sdkFeatures3 = null;
            SdkConfiguration.MotionCapture motionCapture3 = null;
            SdkConfiguration.DocumentCapture documentCapture3 = null;
            SdkConfiguration.ExperimentalFeatures experimentalFeatures3 = null;
            SdkConfiguration.LivenessCapture livenessCapture3 = null;
            SdkConfiguration.SelfieCapture selfieCapture3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 7;
                    case 0:
                        validations2 = (SdkConfiguration.Validations) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, SdkConfiguration$Validations$$serializer.INSTANCE, validations2);
                        i3 |= 1;
                        i2 = 7;
                    case 1:
                        documentCapture3 = (SdkConfiguration.DocumentCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, SdkConfiguration$DocumentCapture$$serializer.INSTANCE, documentCapture3);
                        i3 |= 2;
                        i2 = 7;
                    case 2:
                        experimentalFeatures3 = (SdkConfiguration.ExperimentalFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE, experimentalFeatures3);
                        i3 |= 4;
                        i2 = 7;
                    case 3:
                        livenessCapture3 = (SdkConfiguration.LivenessCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, SdkConfiguration$LivenessCapture$$serializer.INSTANCE, livenessCapture3);
                        i3 |= 8;
                        i2 = 7;
                    case 4:
                        selfieCapture3 = (SdkConfiguration.SelfieCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, SdkConfiguration$SelfieCapture$$serializer.INSTANCE, selfieCapture3);
                        i3 |= 16;
                    case 5:
                        motionCapture3 = (SdkConfiguration.MotionCapture) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, SdkConfiguration$MotionCapture$$serializer.INSTANCE, motionCapture3);
                        i3 |= 32;
                    case 6:
                        sdkFeatures3 = (SdkConfiguration.SdkFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, SdkConfiguration$SdkFeatures$$serializer.INSTANCE, sdkFeatures3);
                        i3 |= 64;
                    case 7:
                        str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, i2, StringSerializer.INSTANCE, str2);
                        i3 |= 128;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str2;
            i = i3;
            SdkConfiguration.SelfieCapture selfieCapture4 = selfieCapture3;
            sdkFeatures = sdkFeatures3;
            validations = validations2;
            experimentalFeatures = experimentalFeatures3;
            selfieCapture = selfieCapture4;
            SdkConfiguration.LivenessCapture livenessCapture4 = livenessCapture3;
            motionCapture = motionCapture3;
            documentCapture = documentCapture3;
            livenessCapture = livenessCapture4;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration(i, validations, documentCapture, experimentalFeatures, livenessCapture, selfieCapture, motionCapture, sdkFeatures, str, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
