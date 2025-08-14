package com.onfido.android.sdk.capture.internal.util.logging.network;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import java.util.Map;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/util/logging/network/LogBody.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/LogBody;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class LogBody$$serializer implements GeneratedSerializer<LogBody> {
    public static final LogBody$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        LogBody$$serializer logBody$$serializer = new LogBody$$serializer();
        INSTANCE = logBody$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.util.logging.network.LogBody", logBody$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement("event", false);
        pluginGeneratedSerialDescriptor.addElement("event_uuid", false);
        pluginGeneratedSerialDescriptor.addElement("event_time", false);
        pluginGeneratedSerialDescriptor.addElement("source", true);
        pluginGeneratedSerialDescriptor.addElement("applicant_uuid", false);
        pluginGeneratedSerialDescriptor.addElement("anonymous_uuid", false);
        pluginGeneratedSerialDescriptor.addElement("client_uuid", false);
        pluginGeneratedSerialDescriptor.addElement("session_uuid", false);
        pluginGeneratedSerialDescriptor.addElement(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, true);
        pluginGeneratedSerialDescriptor.addElement("source_metadata", false);
        pluginGeneratedSerialDescriptor.addElement("event_metadata", true);
        pluginGeneratedSerialDescriptor.addElement("sdk_config", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private LogBody$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        KSerializer<?> kSerializer = LogBody.$childSerializers[8];
        KSerializer<?> nullable = BuiltinSerializersKt.getNullable(EventMetadata$$serializer.INSTANCE);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, kSerializer, SourceMetadata$$serializer.INSTANCE, nullable, SdkConfig$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public LogBody deserialize(Decoder decoder) {
        int i;
        SdkConfig sdkConfig;
        String str;
        String str2;
        String str3;
        EventMetadata eventMetadata;
        String str4;
        SourceMetadata sourceMetadata;
        String str5;
        String str6;
        Map map;
        String str7;
        String str8;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        KSerializer[] kSerializerArr = LogBody.$childSerializers;
        String strDecodeStringElement = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
            Map map2 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 8, kSerializerArr[8], null);
            SourceMetadata sourceMetadata2 = (SourceMetadata) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, SourceMetadata$$serializer.INSTANCE, null);
            EventMetadata eventMetadata2 = (EventMetadata) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, EventMetadata$$serializer.INSTANCE, null);
            map = map2;
            sdkConfig = (SdkConfig) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 11, SdkConfig$$serializer.INSTANCE, null);
            eventMetadata = eventMetadata2;
            sourceMetadata = sourceMetadata2;
            str3 = strDecodeStringElement9;
            str8 = strDecodeStringElement8;
            str6 = strDecodeStringElement7;
            str7 = strDecodeStringElement5;
            str2 = strDecodeStringElement6;
            i = 4095;
            str = strDecodeStringElement3;
            str5 = strDecodeStringElement4;
            str4 = strDecodeStringElement2;
        } else {
            int i2 = 11;
            SdkConfig sdkConfig2 = null;
            EventMetadata eventMetadata3 = null;
            String strDecodeStringElement10 = null;
            String strDecodeStringElement11 = null;
            String strDecodeStringElement12 = null;
            String strDecodeStringElement13 = null;
            String strDecodeStringElement14 = null;
            String strDecodeStringElement15 = null;
            String strDecodeStringElement16 = null;
            boolean z = true;
            i = 0;
            SourceMetadata sourceMetadata3 = null;
            Map map3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 11;
                    case 0:
                        i |= 1;
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        i2 = 11;
                    case 1:
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i |= 2;
                        i2 = 11;
                    case 2:
                        strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
                        i |= 4;
                        i2 = 11;
                    case 3:
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i |= 8;
                        i2 = 11;
                    case 4:
                        strDecodeStringElement13 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i |= 16;
                        i2 = 11;
                    case 5:
                        strDecodeStringElement14 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
                        i |= 32;
                    case 6:
                        strDecodeStringElement15 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
                        i |= 64;
                    case 7:
                        strDecodeStringElement16 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
                        i |= 128;
                    case 8:
                        map3 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 8, kSerializerArr[8], map3);
                        i |= 256;
                    case 9:
                        sourceMetadata3 = (SourceMetadata) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, SourceMetadata$$serializer.INSTANCE, sourceMetadata3);
                        i |= 512;
                    case 10:
                        eventMetadata3 = (EventMetadata) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 10, EventMetadata$$serializer.INSTANCE, eventMetadata3);
                        i |= 1024;
                    case 11:
                        sdkConfig2 = (SdkConfig) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i2, SdkConfig$$serializer.INSTANCE, sdkConfig2);
                        i |= 2048;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            sdkConfig = sdkConfig2;
            str = strDecodeStringElement10;
            str2 = strDecodeStringElement13;
            str3 = strDecodeStringElement16;
            eventMetadata = eventMetadata3;
            str4 = strDecodeStringElement;
            String str9 = strDecodeStringElement15;
            sourceMetadata = sourceMetadata3;
            str5 = strDecodeStringElement11;
            str6 = strDecodeStringElement14;
            map = map3;
            str7 = strDecodeStringElement12;
            str8 = str9;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new LogBody(i, str4, str, str5, str7, str2, str6, str8, str3, map, sourceMetadata, eventMetadata, sdkConfig, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, LogBody value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        LogBody.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
