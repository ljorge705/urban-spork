package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

import androidx.core.app.FrameMetricsAggregator;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
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
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/analytics/inhouse/network/UiAlertsNetwork.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/UiAlertsNetwork;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class UiAlertsNetwork$$serializer implements GeneratedSerializer<UiAlertsNetwork> {
    public static final UiAlertsNetwork$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        UiAlertsNetwork$$serializer uiAlertsNetwork$$serializer = new UiAlertsNetwork$$serializer();
        INSTANCE = uiAlertsNetwork$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.analytics.inhouse.network.UiAlertsNetwork", uiAlertsNetwork$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement("face_video_timeout", false);
        pluginGeneratedSerialDescriptor.addElement("no_face", false);
        pluginGeneratedSerialDescriptor.addElement("multiple_faces", false);
        pluginGeneratedSerialDescriptor.addElement("request_error", false);
        pluginGeneratedSerialDescriptor.addElement("document", false);
        pluginGeneratedSerialDescriptor.addElement("glare", false);
        pluginGeneratedSerialDescriptor.addElement("blur", false);
        pluginGeneratedSerialDescriptor.addElement(OptionalModuleUtils.BARCODE, false);
        pluginGeneratedSerialDescriptor.addElement("cutoff", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private UiAlertsNetwork$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public UiAlertsNetwork deserialize(Decoder decoder) {
        int i;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 7;
        String str10 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            String str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, stringSerializer, null);
            String str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, stringSerializer, null);
            String str13 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, stringSerializer, null);
            String str14 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, stringSerializer, null);
            String str15 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, stringSerializer, null);
            String str16 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, stringSerializer, null);
            String str17 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, stringSerializer, null);
            String str18 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, stringSerializer, null);
            String str19 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, stringSerializer, null);
            i = FrameMetricsAggregator.EVERY_DURATION;
            str = str19;
            str5 = str18;
            str6 = str17;
            str8 = str16;
            str3 = str15;
            str2 = str11;
            str4 = str14;
            str9 = str13;
            str7 = str12;
        } else {
            boolean z = true;
            int i3 = 0;
            String str20 = null;
            String str21 = null;
            String str22 = null;
            String str23 = null;
            String str24 = null;
            String str25 = null;
            String str26 = null;
            String str27 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 7;
                    case 0:
                        str10 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, str10);
                        i3 |= 1;
                        i2 = 7;
                    case 1:
                        str25 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, StringSerializer.INSTANCE, str25);
                        i3 |= 2;
                        i2 = 7;
                    case 2:
                        str26 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, StringSerializer.INSTANCE, str26);
                        i3 |= 4;
                        i2 = 7;
                    case 3:
                        str27 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, str27);
                        i3 |= 8;
                        i2 = 7;
                    case 4:
                        str24 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, StringSerializer.INSTANCE, str24);
                        i3 |= 16;
                        i2 = 7;
                    case 5:
                        str23 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, StringSerializer.INSTANCE, str23);
                        i3 |= 32;
                        i2 = 7;
                    case 6:
                        str22 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, str22);
                        i3 |= 64;
                    case 7:
                        str20 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, i2, StringSerializer.INSTANCE, str20);
                        i3 |= 128;
                    case 8:
                        str21 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, str21);
                        i3 |= 256;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            i = i3;
            str = str21;
            str2 = str10;
            str3 = str24;
            str4 = str27;
            str5 = str20;
            String str28 = str26;
            str6 = str22;
            str7 = str25;
            str8 = str23;
            str9 = str28;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new UiAlertsNetwork(i, str2, str7, str9, str4, str3, str8, str6, str5, str, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, UiAlertsNetwork value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        UiAlertsNetwork.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
