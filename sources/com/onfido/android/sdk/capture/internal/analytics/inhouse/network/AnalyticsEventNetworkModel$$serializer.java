package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/analytics/inhouse/network/AnalyticsEventNetworkModel.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/AnalyticsEventNetworkModel;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class AnalyticsEventNetworkModel$$serializer implements GeneratedSerializer<AnalyticsEventNetworkModel> {
    public static final AnalyticsEventNetworkModel$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        AnalyticsEventNetworkModel$$serializer analyticsEventNetworkModel$$serializer = new AnalyticsEventNetworkModel$$serializer();
        INSTANCE = analyticsEventNetworkModel$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.analytics.inhouse.network.AnalyticsEventNetworkModel", analyticsEventNetworkModel$$serializer, 13);
        pluginGeneratedSerialDescriptor.addElement("event", false);
        pluginGeneratedSerialDescriptor.addElement("event_uuid", false);
        pluginGeneratedSerialDescriptor.addElement("workflow_run_uuid", false);
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

    private AnalyticsEventNetworkModel$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr = AnalyticsEventNetworkModel.$childSerializers;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, BuiltinSerializersKt.getNullable(stringSerializer), stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, kSerializerArr[9], SourceMetaData$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(EventMetaData$$serializer.INSTANCE), SdkConfig$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public AnalyticsEventNetworkModel deserialize(Decoder decoder) {
        String str;
        int i;
        String str2;
        String str3;
        SdkConfig sdkConfig;
        String str4;
        String str5;
        Map map;
        String str6;
        String str7;
        EventMetaData eventMetaData;
        SourceMetaData sourceMetaData;
        String str8;
        String str9;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        KSerializer[] kSerializerArr = AnalyticsEventNetworkModel.$childSerializers;
        int i2 = 10;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            String str10 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, StringSerializer.INSTANCE, null);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
            Map map2 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, kSerializerArr[9], null);
            SourceMetaData sourceMetaData2 = (SourceMetaData) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 10, SourceMetaData$$serializer.INSTANCE, null);
            EventMetaData eventMetaData2 = (EventMetaData) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, EventMetaData$$serializer.INSTANCE, null);
            map = map2;
            sdkConfig = (SdkConfig) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 12, SdkConfig$$serializer.INSTANCE, null);
            sourceMetaData = sourceMetaData2;
            str7 = strDecodeStringElement7;
            str9 = strDecodeStringElement6;
            str5 = strDecodeStringElement5;
            str6 = strDecodeStringElement3;
            str3 = strDecodeStringElement8;
            eventMetaData = eventMetaData2;
            i = 8191;
            str = str10;
            str8 = strDecodeStringElement2;
            str2 = strDecodeStringElement4;
            str4 = strDecodeStringElement;
        } else {
            int i3 = 12;
            EventMetaData eventMetaData3 = null;
            SdkConfig sdkConfig2 = null;
            SourceMetaData sourceMetaData3 = null;
            String strDecodeStringElement9 = null;
            String strDecodeStringElement10 = null;
            String strDecodeStringElement11 = null;
            String strDecodeStringElement12 = null;
            String strDecodeStringElement13 = null;
            String strDecodeStringElement14 = null;
            String strDecodeStringElement15 = null;
            String strDecodeStringElement16 = null;
            boolean z = true;
            int i4 = 0;
            String str11 = null;
            Map map3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i3 = 12;
                    case 0:
                        strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        i4 |= 1;
                        i3 = 12;
                        i2 = 10;
                    case 1:
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i4 |= 2;
                        i3 = 12;
                        i2 = 10;
                    case 2:
                        str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, StringSerializer.INSTANCE, str11);
                        i4 |= 4;
                        i3 = 12;
                        i2 = 10;
                    case 3:
                        strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i4 |= 8;
                        i3 = 12;
                    case 4:
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i4 |= 16;
                        i3 = 12;
                    case 5:
                        strDecodeStringElement13 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
                        i4 |= 32;
                        i3 = 12;
                    case 6:
                        strDecodeStringElement14 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
                        i4 |= 64;
                        i3 = 12;
                    case 7:
                        strDecodeStringElement15 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
                        i4 |= 128;
                        i3 = 12;
                    case 8:
                        strDecodeStringElement16 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
                        i4 |= 256;
                        i3 = 12;
                    case 9:
                        map3 = (Map) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 9, kSerializerArr[9], map3);
                        i4 |= 512;
                        i3 = 12;
                    case 10:
                        sourceMetaData3 = (SourceMetaData) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i2, SourceMetaData$$serializer.INSTANCE, sourceMetaData3);
                        i4 |= 1024;
                        i3 = 12;
                    case 11:
                        eventMetaData3 = (EventMetaData) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 11, EventMetaData$$serializer.INSTANCE, eventMetaData3);
                        i4 |= 2048;
                        i3 = 12;
                    case 12:
                        sdkConfig2 = (SdkConfig) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i3, SdkConfig$$serializer.INSTANCE, sdkConfig2);
                        i4 |= 4096;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str11;
            i = i4;
            str2 = strDecodeStringElement12;
            str3 = strDecodeStringElement16;
            sdkConfig = sdkConfig2;
            str4 = strDecodeStringElement9;
            str5 = strDecodeStringElement13;
            map = map3;
            str6 = strDecodeStringElement11;
            str7 = strDecodeStringElement15;
            eventMetaData = eventMetaData3;
            String str12 = strDecodeStringElement14;
            sourceMetaData = sourceMetaData3;
            str8 = strDecodeStringElement10;
            str9 = str12;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new AnalyticsEventNetworkModel(i, str4, str8, str, str6, str2, str5, str9, str7, str3, map, sourceMetaData, eventMetaData, sdkConfig, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, AnalyticsEventNetworkModel value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        AnalyticsEventNetworkModel.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
