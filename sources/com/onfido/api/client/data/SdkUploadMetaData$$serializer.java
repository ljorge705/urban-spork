package com.onfido.api.client.data;

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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: SdkUploadMetaData.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkUploadMetaData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkUploadMetaData$$serializer implements GeneratedSerializer<SdkUploadMetaData> {
    public static final SdkUploadMetaData$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkUploadMetaData$$serializer sdkUploadMetaData$$serializer = new SdkUploadMetaData$$serializer();
        INSTANCE = sdkUploadMetaData$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkUploadMetaData", sdkUploadMetaData$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("on_device_processing_results", false);
        pluginGeneratedSerialDescriptor.addElement("system", false);
        pluginGeneratedSerialDescriptor.addElement(OnfidoLauncher.KEY_CONFIG, false);
        pluginGeneratedSerialDescriptor.addElement("take_number", false);
        pluginGeneratedSerialDescriptor.addElement("environment_integrity", false);
        pluginGeneratedSerialDescriptor.addElement("sdk_source", false);
        pluginGeneratedSerialDescriptor.addElement("sdk_version", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkUploadMetaData$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(PhotoDetection$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(DeviceMetadata$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(SdkConfiguration$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkUploadMetaData deserialize(Decoder decoder) {
        String str;
        String str2;
        Boolean bool;
        Integer num;
        SdkConfiguration sdkConfiguration;
        DeviceMetadata deviceMetadata;
        PhotoDetection photoDetection;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 6;
        PhotoDetection photoDetection2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            PhotoDetection photoDetection3 = (PhotoDetection) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, PhotoDetection$$serializer.INSTANCE, null);
            DeviceMetadata deviceMetadata2 = (DeviceMetadata) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, DeviceMetadata$$serializer.INSTANCE, null);
            SdkConfiguration sdkConfiguration2 = (SdkConfiguration) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, SdkConfiguration$$serializer.INSTANCE, null);
            Integer num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, IntSerializer.INSTANCE, null);
            Boolean bool2 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, BooleanSerializer.INSTANCE, null);
            String str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, StringSerializer.INSTANCE, null);
            photoDetection = photoDetection3;
            str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, null);
            str2 = str3;
            num = num2;
            bool = bool2;
            sdkConfiguration = sdkConfiguration2;
            deviceMetadata = deviceMetadata2;
            i = 127;
        } else {
            boolean z = true;
            int i3 = 0;
            String str4 = null;
            DeviceMetadata deviceMetadata3 = null;
            SdkConfiguration sdkConfiguration3 = null;
            Integer num3 = null;
            Boolean bool3 = null;
            String str5 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 6;
                    case 0:
                        photoDetection2 = (PhotoDetection) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, PhotoDetection$$serializer.INSTANCE, photoDetection2);
                        i3 |= 1;
                        i2 = 6;
                    case 1:
                        deviceMetadata3 = (DeviceMetadata) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, DeviceMetadata$$serializer.INSTANCE, deviceMetadata3);
                        i3 |= 2;
                        i2 = 6;
                    case 2:
                        sdkConfiguration3 = (SdkConfiguration) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, SdkConfiguration$$serializer.INSTANCE, sdkConfiguration3);
                        i3 |= 4;
                    case 3:
                        num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, IntSerializer.INSTANCE, num3);
                        i3 |= 8;
                    case 4:
                        bool3 = (Boolean) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, BooleanSerializer.INSTANCE, bool3);
                        i3 |= 16;
                    case 5:
                        str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, StringSerializer.INSTANCE, str5);
                        i3 |= 32;
                    case 6:
                        str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, i2, StringSerializer.INSTANCE, str4);
                        i3 |= 64;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str4;
            str2 = str5;
            bool = bool3;
            num = num3;
            sdkConfiguration = sdkConfiguration3;
            deviceMetadata = deviceMetadata3;
            photoDetection = photoDetection2;
            i = i3;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkUploadMetaData(i, photoDetection, deviceMetadata, sdkConfiguration, num, bool, str2, str, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkUploadMetaData value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkUploadMetaData.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
