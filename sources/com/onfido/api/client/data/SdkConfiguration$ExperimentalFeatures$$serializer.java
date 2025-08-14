package com.onfido.api.client.data;

import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;
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
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.ExperimentalFeatures.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$ExperimentalFeatures$$serializer implements GeneratedSerializer<SdkConfiguration.ExperimentalFeatures> {
    public static final SdkConfiguration$ExperimentalFeatures$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$ExperimentalFeatures$$serializer sdkConfiguration$ExperimentalFeatures$$serializer = new SdkConfiguration$ExperimentalFeatures$$serializer();
        INSTANCE = sdkConfiguration$ExperimentalFeatures$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration.ExperimentalFeatures", sdkConfiguration$ExperimentalFeatures$$serializer, 23);
        pluginGeneratedSerialDescriptor.addElement("enable_image_quality_service", false);
        pluginGeneratedSerialDescriptor.addElement("enable_multi_frame_capture", false);
        pluginGeneratedSerialDescriptor.addElement("motion_experiment", true);
        pluginGeneratedSerialDescriptor.addElement("android_motion_tensorflow_lite_enabled", true);
        pluginGeneratedSerialDescriptor.addElement("android_motion_camerax_enabled", true);
        pluginGeneratedSerialDescriptor.addElement("enable_camerax", true);
        pluginGeneratedSerialDescriptor.addElement("enable_camerax_stream_sharing", true);
        pluginGeneratedSerialDescriptor.addElement("enable_camerax_high_quality", true);
        pluginGeneratedSerialDescriptor.addElement("enable_optimal_resolution_improvements", true);
        pluginGeneratedSerialDescriptor.addElement("enable_cutoff_improvements", true);
        pluginGeneratedSerialDescriptor.addElement("enable_focus_improvements", true);
        pluginGeneratedSerialDescriptor.addElement("enable_increased_compression_quality", true);
        pluginGeneratedSerialDescriptor.addElement("disable_document_crop", true);
        pluginGeneratedSerialDescriptor.addElement("enable_four_three_aspect_ratio", true);
        pluginGeneratedSerialDescriptor.addElement("enable_generic_mrz_validator", true);
        pluginGeneratedSerialDescriptor.addElement("enable_auto_flash_mode", true);
        pluginGeneratedSerialDescriptor.addElement("waiting_screens", true);
        pluginGeneratedSerialDescriptor.addElement("android_enable_environment_integrity_check", true);
        pluginGeneratedSerialDescriptor.addElement("enable_studio_user_flow_exit", true);
        pluginGeneratedSerialDescriptor.addElement("camerax_configuration", true);
        pluginGeneratedSerialDescriptor.addElement("disable_media_callback_cropping", true);
        pluginGeneratedSerialDescriptor.addElement("enable_on_device_mrz_extraction", true);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.DOCUMENT_DETECTION_ERROR_KEY, true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$ExperimentalFeatures$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, SdkConfiguration$CameraXConfiguration$$serializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration.ExperimentalFeatures deserialize(Decoder decoder) {
        SdkConfiguration.ExperimentalFeatures.MotionExperiment motionExperiment;
        SdkConfiguration.ExperimentalFeatures.WaitingScreens waitingScreens;
        SdkConfiguration.CameraXConfiguration cameraXConfiguration;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment documentDetectionExperiment;
        int i;
        boolean z19;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i3 = 6;
        int i4 = 8;
        int i5 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
            boolean zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
            SdkConfiguration.ExperimentalFeatures.MotionExperiment motionExperiment2 = (SdkConfiguration.ExperimentalFeatures.MotionExperiment) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE, null);
            boolean zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 3);
            boolean zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 4);
            boolean zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
            boolean zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
            boolean zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 7);
            boolean zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 8);
            boolean zDecodeBooleanElement9 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 9);
            boolean zDecodeBooleanElement10 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 10);
            boolean zDecodeBooleanElement11 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 11);
            boolean zDecodeBooleanElement12 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 12);
            boolean zDecodeBooleanElement13 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 13);
            boolean zDecodeBooleanElement14 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 14);
            boolean zDecodeBooleanElement15 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 15);
            SdkConfiguration.ExperimentalFeatures.WaitingScreens waitingScreens2 = (SdkConfiguration.ExperimentalFeatures.WaitingScreens) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 16, SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE, null);
            boolean zDecodeBooleanElement16 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 17);
            boolean zDecodeBooleanElement17 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 18);
            SdkConfiguration.CameraXConfiguration cameraXConfiguration2 = (SdkConfiguration.CameraXConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 19, SdkConfiguration$CameraXConfiguration$$serializer.INSTANCE, null);
            boolean zDecodeBooleanElement18 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 20);
            boolean zDecodeBooleanElement19 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 21);
            documentDetectionExperiment = (SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 22, SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer.INSTANCE, null);
            z10 = zDecodeBooleanElement19;
            z12 = zDecodeBooleanElement17;
            z9 = zDecodeBooleanElement2;
            motionExperiment = motionExperiment2;
            z19 = zDecodeBooleanElement3;
            z15 = zDecodeBooleanElement10;
            z11 = zDecodeBooleanElement6;
            z6 = zDecodeBooleanElement18;
            cameraXConfiguration = cameraXConfiguration2;
            z13 = zDecodeBooleanElement13;
            z7 = zDecodeBooleanElement4;
            i = 8388607;
            z8 = zDecodeBooleanElement8;
            z = zDecodeBooleanElement;
            waitingScreens = waitingScreens2;
            z18 = zDecodeBooleanElement12;
            z16 = zDecodeBooleanElement9;
            z17 = zDecodeBooleanElement7;
            z14 = zDecodeBooleanElement11;
            z4 = zDecodeBooleanElement5;
            z2 = zDecodeBooleanElement14;
            z3 = zDecodeBooleanElement15;
            z5 = zDecodeBooleanElement16;
        } else {
            SdkConfiguration.ExperimentalFeatures.WaitingScreens waitingScreens3 = null;
            SdkConfiguration.CameraXConfiguration cameraXConfiguration3 = null;
            SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment documentDetectionExperiment2 = null;
            boolean z20 = true;
            boolean zDecodeBooleanElement20 = false;
            boolean zDecodeBooleanElement21 = false;
            boolean zDecodeBooleanElement22 = false;
            boolean zDecodeBooleanElement23 = false;
            boolean zDecodeBooleanElement24 = false;
            boolean zDecodeBooleanElement25 = false;
            boolean zDecodeBooleanElement26 = false;
            boolean zDecodeBooleanElement27 = false;
            boolean zDecodeBooleanElement28 = false;
            boolean zDecodeBooleanElement29 = false;
            boolean zDecodeBooleanElement30 = false;
            boolean zDecodeBooleanElement31 = false;
            boolean zDecodeBooleanElement32 = false;
            boolean zDecodeBooleanElement33 = false;
            boolean zDecodeBooleanElement34 = false;
            boolean zDecodeBooleanElement35 = false;
            boolean zDecodeBooleanElement36 = false;
            boolean zDecodeBooleanElement37 = false;
            motionExperiment = null;
            boolean zDecodeBooleanElement38 = false;
            while (z20) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z20 = false;
                        i3 = 6;
                    case 0:
                        i5 |= 1;
                        zDecodeBooleanElement38 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 0);
                        i4 = 8;
                        i3 = 6;
                    case 1:
                        zDecodeBooleanElement28 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 1);
                        i5 |= 2;
                        i4 = 8;
                        i3 = 6;
                    case 2:
                        motionExperiment = (SdkConfiguration.ExperimentalFeatures.MotionExperiment) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE, motionExperiment);
                        i5 |= 4;
                        i4 = 8;
                        i3 = 6;
                    case 3:
                        zDecodeBooleanElement25 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 3);
                        i5 |= 8;
                        i4 = 8;
                        i3 = 6;
                    case 4:
                        zDecodeBooleanElement26 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 4);
                        i5 |= 16;
                        i4 = 8;
                        i3 = 6;
                    case 5:
                        zDecodeBooleanElement22 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
                        i5 |= 32;
                        i4 = 8;
                        i3 = 6;
                    case 6:
                        int i6 = i3;
                        zDecodeBooleanElement30 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, i6);
                        i5 |= 64;
                        i3 = i6;
                        i4 = 8;
                    case 7:
                        zDecodeBooleanElement36 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 7);
                        i5 |= 128;
                        i3 = 6;
                    case 8:
                        zDecodeBooleanElement27 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, i4);
                        i5 |= 256;
                        i3 = 6;
                    case 9:
                        zDecodeBooleanElement35 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 9);
                        i5 |= 512;
                        i3 = 6;
                    case 10:
                        zDecodeBooleanElement34 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 10);
                        i5 |= 1024;
                        i3 = 6;
                    case 11:
                        zDecodeBooleanElement33 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 11);
                        i5 |= 2048;
                        i3 = 6;
                    case 12:
                        zDecodeBooleanElement37 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 12);
                        i5 |= 4096;
                        i3 = 6;
                    case 13:
                        zDecodeBooleanElement32 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 13);
                        i5 |= 8192;
                        i3 = 6;
                    case 14:
                        i5 |= 16384;
                        zDecodeBooleanElement20 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 14);
                        i3 = 6;
                    case 15:
                        zDecodeBooleanElement21 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 15);
                        i5 |= 32768;
                        i3 = 6;
                    case 16:
                        waitingScreens3 = (SdkConfiguration.ExperimentalFeatures.WaitingScreens) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 16, SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE, waitingScreens3);
                        i2 = 65536;
                        i5 |= i2;
                        i3 = 6;
                    case 17:
                        zDecodeBooleanElement23 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 17);
                        i5 |= 131072;
                        i3 = 6;
                    case 18:
                        zDecodeBooleanElement31 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 18);
                        i5 |= 262144;
                        i3 = 6;
                    case 19:
                        cameraXConfiguration3 = (SdkConfiguration.CameraXConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 19, SdkConfiguration$CameraXConfiguration$$serializer.INSTANCE, cameraXConfiguration3);
                        i2 = 524288;
                        i5 |= i2;
                        i3 = 6;
                    case 20:
                        zDecodeBooleanElement24 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 20);
                        i2 = 1048576;
                        i5 |= i2;
                        i3 = 6;
                    case 21:
                        zDecodeBooleanElement29 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 21);
                        i2 = 2097152;
                        i5 |= i2;
                        i3 = 6;
                    case 22:
                        documentDetectionExperiment2 = (SdkConfiguration.ExperimentalFeatures.DocumentDetectionExperiment) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 22, SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer.INSTANCE, documentDetectionExperiment2);
                        i2 = 4194304;
                        i5 |= i2;
                        i3 = 6;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            waitingScreens = waitingScreens3;
            cameraXConfiguration = cameraXConfiguration3;
            z = zDecodeBooleanElement38;
            z2 = zDecodeBooleanElement20;
            z3 = zDecodeBooleanElement21;
            z4 = zDecodeBooleanElement22;
            z5 = zDecodeBooleanElement23;
            z6 = zDecodeBooleanElement24;
            z7 = zDecodeBooleanElement26;
            z8 = zDecodeBooleanElement27;
            z9 = zDecodeBooleanElement28;
            z10 = zDecodeBooleanElement29;
            z11 = zDecodeBooleanElement30;
            z12 = zDecodeBooleanElement31;
            z13 = zDecodeBooleanElement32;
            z14 = zDecodeBooleanElement33;
            z15 = zDecodeBooleanElement34;
            z16 = zDecodeBooleanElement35;
            z17 = zDecodeBooleanElement36;
            z18 = zDecodeBooleanElement37;
            documentDetectionExperiment = documentDetectionExperiment2;
            i = i5;
            z19 = zDecodeBooleanElement25;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration.ExperimentalFeatures(i, z, z9, motionExperiment, z19, z7, z4, z11, z17, z8, z16, z15, z14, z18, z13, z2, z3, waitingScreens, z5, z12, cameraXConfiguration, z6, z10, documentDetectionExperiment, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration.ExperimentalFeatures value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.ExperimentalFeatures.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
