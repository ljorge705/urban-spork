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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.DocumentCapture.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$DocumentCapture$$serializer implements GeneratedSerializer<SdkConfiguration.DocumentCapture> {
    public static final SdkConfiguration$DocumentCapture$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$DocumentCapture$$serializer sdkConfiguration$DocumentCapture$$serializer = new SdkConfiguration$DocumentCapture$$serializer();
        INSTANCE = sdkConfiguration$DocumentCapture$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration.DocumentCapture", sdkConfiguration$DocumentCapture$$serializer, 14);
        pluginGeneratedSerialDescriptor.addElement("torch_turn_on_time_ms", true);
        pluginGeneratedSerialDescriptor.addElement("video_length_ms", true);
        pluginGeneratedSerialDescriptor.addElement("video_timeout_ms", true);
        pluginGeneratedSerialDescriptor.addElement("video_bitrate", true);
        pluginGeneratedSerialDescriptor.addElement("video_quality", true);
        pluginGeneratedSerialDescriptor.addElement("video_required", true);
        pluginGeneratedSerialDescriptor.addElement("barcode_detection_timeout_ms", true);
        pluginGeneratedSerialDescriptor.addElement("max_total_retries", true);
        pluginGeneratedSerialDescriptor.addElement("enable_mrz_detection", true);
        pluginGeneratedSerialDescriptor.addElement("nfc", true);
        pluginGeneratedSerialDescriptor.addElement("enable_stateful_nfc_reader", true);
        pluginGeneratedSerialDescriptor.addElement("is_camerax_view_port_enabled", true);
        pluginGeneratedSerialDescriptor.addElement("image_compression_quality", true);
        pluginGeneratedSerialDescriptor.addElement("target_resolution_width", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$DocumentCapture$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, BooleanSerializer.INSTANCE, LongSerializer.INSTANCE, IntSerializer.INSTANCE, BooleanSerializer.INSTANCE, SdkConfiguration$NfcConfiguration$$serializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration.DocumentCapture deserialize(Decoder decoder) {
        int iDecodeIntElement;
        int iDecodeIntElement2;
        boolean z;
        int i;
        int i2;
        int i3;
        SdkConfiguration.NfcConfiguration nfcConfiguration;
        boolean z2;
        boolean zDecodeBooleanElement;
        long j;
        boolean z3;
        long j2;
        int i4;
        long j3;
        long j4;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i5 = 0;
        SdkConfiguration.NfcConfiguration nfcConfiguration2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            long jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 0);
            long jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 1);
            long jDecodeLongElement3 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 2);
            int iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 3);
            int iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 4);
            boolean zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
            long jDecodeLongElement4 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 6);
            int iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 7);
            boolean zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 8);
            SdkConfiguration.NfcConfiguration nfcConfiguration3 = (SdkConfiguration.NfcConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, SdkConfiguration$NfcConfiguration$$serializer.INSTANCE, null);
            boolean zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 10);
            nfcConfiguration = nfcConfiguration3;
            zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 11);
            z3 = zDecodeBooleanElement4;
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 12);
            i4 = iDecodeIntElement5;
            z2 = zDecodeBooleanElement3;
            z = zDecodeBooleanElement2;
            i = iDecodeIntElement3;
            iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 13);
            i2 = iDecodeIntElement4;
            j3 = jDecodeLongElement3;
            j4 = jDecodeLongElement4;
            j2 = jDecodeLongElement2;
            j = jDecodeLongElement;
            i3 = 16383;
        } else {
            int i6 = 13;
            long jDecodeLongElement5 = 0;
            boolean zDecodeBooleanElement5 = false;
            boolean zDecodeBooleanElement6 = false;
            int iDecodeIntElement6 = 0;
            int iDecodeIntElement7 = 0;
            boolean zDecodeBooleanElement7 = false;
            boolean zDecodeBooleanElement8 = false;
            int iDecodeIntElement8 = 0;
            int iDecodeIntElement9 = 0;
            int iDecodeIntElement10 = 0;
            boolean z4 = true;
            long jDecodeLongElement6 = 0;
            long jDecodeLongElement7 = 0;
            long jDecodeLongElement8 = 0;
            while (z4) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z4 = false;
                        i6 = 13;
                    case 0:
                        jDecodeLongElement5 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 0);
                        i5 |= 1;
                        i6 = 13;
                    case 1:
                        jDecodeLongElement7 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 1);
                        i5 |= 2;
                        i6 = 13;
                    case 2:
                        jDecodeLongElement6 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 2);
                        i5 |= 4;
                        i6 = 13;
                    case 3:
                        iDecodeIntElement8 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 3);
                        i5 |= 8;
                        i6 = 13;
                    case 4:
                        iDecodeIntElement10 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 4);
                        i5 |= 16;
                        i6 = 13;
                    case 5:
                        zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
                        i5 |= 32;
                        i6 = 13;
                    case 6:
                        jDecodeLongElement8 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 6);
                        i5 |= 64;
                        i6 = 13;
                    case 7:
                        iDecodeIntElement7 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 7);
                        i5 |= 128;
                        i6 = 13;
                    case 8:
                        zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 8);
                        i5 |= 256;
                        i6 = 13;
                    case 9:
                        nfcConfiguration2 = (SdkConfiguration.NfcConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, SdkConfiguration$NfcConfiguration$$serializer.INSTANCE, nfcConfiguration2);
                        i5 |= 512;
                        i6 = 13;
                    case 10:
                        zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 10);
                        i5 |= 1024;
                    case 11:
                        zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 11);
                        i5 |= 2048;
                    case 12:
                        iDecodeIntElement6 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 12);
                        i5 |= 4096;
                    case 13:
                        iDecodeIntElement9 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, i6);
                        i5 |= 8192;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            iDecodeIntElement = iDecodeIntElement6;
            iDecodeIntElement2 = iDecodeIntElement9;
            z = zDecodeBooleanElement8;
            i = iDecodeIntElement8;
            i2 = iDecodeIntElement10;
            long j5 = jDecodeLongElement5;
            i3 = i5;
            long j6 = jDecodeLongElement7;
            nfcConfiguration = nfcConfiguration2;
            z2 = zDecodeBooleanElement7;
            long j7 = jDecodeLongElement8;
            zDecodeBooleanElement = zDecodeBooleanElement5;
            j = j5;
            z3 = zDecodeBooleanElement6;
            j2 = j6;
            i4 = iDecodeIntElement7;
            j3 = jDecodeLongElement6;
            j4 = j7;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration.DocumentCapture(i3, j, j2, j3, i, i2, z, j4, i4, z2, nfcConfiguration, z3, zDecodeBooleanElement, iDecodeIntElement, iDecodeIntElement2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration.DocumentCapture value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.DocumentCapture.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
