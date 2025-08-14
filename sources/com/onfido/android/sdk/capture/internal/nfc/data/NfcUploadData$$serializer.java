package com.onfido.android.sdk.capture.internal.nfc.data;

import androidx.core.app.FrameMetricsAggregator;
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
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/nfc/data/NfcUploadData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcUploadData;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class NfcUploadData$$serializer implements GeneratedSerializer<NfcUploadData> {
    public static final NfcUploadData$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        NfcUploadData$$serializer nfcUploadData$$serializer = new NfcUploadData$$serializer();
        INSTANCE = nfcUploadData$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.nfc.data.NfcUploadData", nfcUploadData$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement("dg1", false);
        pluginGeneratedSerialDescriptor.addElement("dg2", false);
        pluginGeneratedSerialDescriptor.addElement("dg11", false);
        pluginGeneratedSerialDescriptor.addElement("dg12", false);
        pluginGeneratedSerialDescriptor.addElement("dg13", false);
        pluginGeneratedSerialDescriptor.addElement("dg14", false);
        pluginGeneratedSerialDescriptor.addElement("dg15", false);
        pluginGeneratedSerialDescriptor.addElement("sod", false);
        pluginGeneratedSerialDescriptor.addElement("aa_signed_challenge", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private NfcUploadData$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        IntArraySerializer intArraySerializer = IntArraySerializer.INSTANCE;
        return new KSerializer[]{intArraySerializer, intArraySerializer, BuiltinSerializersKt.getNullable(intArraySerializer), BuiltinSerializersKt.getNullable(intArraySerializer), BuiltinSerializersKt.getNullable(intArraySerializer), BuiltinSerializersKt.getNullable(intArraySerializer), BuiltinSerializersKt.getNullable(intArraySerializer), intArraySerializer, BuiltinSerializersKt.getNullable(intArraySerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public NfcUploadData deserialize(Decoder decoder) {
        int i;
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int[] iArr7;
        int[] iArr8;
        int[] iArr9;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 7;
        int[] iArr10 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            IntArraySerializer intArraySerializer = IntArraySerializer.INSTANCE;
            int[] iArr11 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, intArraySerializer, null);
            int[] iArr12 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, intArraySerializer, null);
            int[] iArr13 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, intArraySerializer, null);
            int[] iArr14 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, intArraySerializer, null);
            int[] iArr15 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, intArraySerializer, null);
            int[] iArr16 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, intArraySerializer, null);
            int[] iArr17 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, intArraySerializer, null);
            int[] iArr18 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 7, intArraySerializer, null);
            int[] iArr19 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, intArraySerializer, null);
            i = FrameMetricsAggregator.EVERY_DURATION;
            iArr = iArr19;
            iArr5 = iArr18;
            iArr6 = iArr17;
            iArr8 = iArr16;
            iArr3 = iArr15;
            iArr2 = iArr11;
            iArr4 = iArr14;
            iArr9 = iArr13;
            iArr7 = iArr12;
        } else {
            boolean z = true;
            int i3 = 0;
            int[] iArr20 = null;
            int[] iArr21 = null;
            int[] iArr22 = null;
            int[] iArr23 = null;
            int[] iArr24 = null;
            int[] iArr25 = null;
            int[] iArr26 = null;
            int[] iArr27 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 7;
                    case 0:
                        iArr10 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, IntArraySerializer.INSTANCE, iArr10);
                        i3 |= 1;
                        i2 = 7;
                    case 1:
                        iArr25 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, IntArraySerializer.INSTANCE, iArr25);
                        i3 |= 2;
                        i2 = 7;
                    case 2:
                        iArr26 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, IntArraySerializer.INSTANCE, iArr26);
                        i3 |= 4;
                        i2 = 7;
                    case 3:
                        iArr27 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, IntArraySerializer.INSTANCE, iArr27);
                        i3 |= 8;
                        i2 = 7;
                    case 4:
                        iArr24 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, IntArraySerializer.INSTANCE, iArr24);
                        i3 |= 16;
                        i2 = 7;
                    case 5:
                        iArr23 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, IntArraySerializer.INSTANCE, iArr23);
                        i3 |= 32;
                        i2 = 7;
                    case 6:
                        iArr22 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, IntArraySerializer.INSTANCE, iArr22);
                        i3 |= 64;
                    case 7:
                        iArr20 = (int[]) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i2, IntArraySerializer.INSTANCE, iArr20);
                        i3 |= 128;
                    case 8:
                        iArr21 = (int[]) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, IntArraySerializer.INSTANCE, iArr21);
                        i3 |= 256;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            i = i3;
            iArr = iArr21;
            iArr2 = iArr10;
            iArr3 = iArr24;
            iArr4 = iArr27;
            iArr5 = iArr20;
            int[] iArr28 = iArr26;
            iArr6 = iArr22;
            iArr7 = iArr25;
            iArr8 = iArr23;
            iArr9 = iArr28;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new NfcUploadData(i, iArr2, iArr7, iArr9, iArr4, iArr3, iArr8, iArr6, iArr5, iArr, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, NfcUploadData value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        NfcUploadData.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
