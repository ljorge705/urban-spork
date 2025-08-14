package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/EventMetaData;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class EventMetaData$$serializer implements GeneratedSerializer<EventMetaData> {
    public static final EventMetaData$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        EventMetaData$$serializer eventMetaData$$serializer = new EventMetaData$$serializer();
        INSTANCE = eventMetaData$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.analytics.inhouse.network.EventMetaData", eventMetaData$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement(OperatingSystem.TYPE, false);
        pluginGeneratedSerialDescriptor.addElement("os_version", false);
        pluginGeneratedSerialDescriptor.addElement("minimum_os_version", false);
        pluginGeneratedSerialDescriptor.addElement(SentryEvent.JsonKeys.FINGERPRINT, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.MODEL, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.MANUFACTURER, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.BRAND, false);
        pluginGeneratedSerialDescriptor.addElement("product", false);
        pluginGeneratedSerialDescriptor.addElement("hardware", false);
        pluginGeneratedSerialDescriptor.addElement("androidApiLevel", false);
        pluginGeneratedSerialDescriptor.addElement("application_version", false);
        pluginGeneratedSerialDescriptor.addElement("application_id", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private EventMetaData$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public EventMetaData deserialize(Decoder decoder) {
        String strDecodeStringElement;
        String str;
        String str2;
        int i;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
            String strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
            String strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 9);
            String strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 10);
            str2 = strDecodeStringElement2;
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 11);
            str = strDecodeStringElement12;
            str3 = strDecodeStringElement11;
            str5 = strDecodeStringElement9;
            str8 = strDecodeStringElement8;
            str10 = strDecodeStringElement7;
            str9 = strDecodeStringElement5;
            str7 = strDecodeStringElement10;
            str11 = strDecodeStringElement6;
            str6 = strDecodeStringElement4;
            str4 = strDecodeStringElement3;
            i = 4095;
        } else {
            String strDecodeStringElement13 = null;
            String strDecodeStringElement14 = null;
            String strDecodeStringElement15 = null;
            String strDecodeStringElement16 = null;
            String strDecodeStringElement17 = null;
            String strDecodeStringElement18 = null;
            String strDecodeStringElement19 = null;
            String strDecodeStringElement20 = null;
            String strDecodeStringElement21 = null;
            String strDecodeStringElement22 = null;
            String strDecodeStringElement23 = null;
            String strDecodeStringElement24 = null;
            boolean z = true;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        i2 |= 1;
                        strDecodeStringElement13 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        continue;
                    case 1:
                        strDecodeStringElement24 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i2 |= 2;
                        continue;
                    case 2:
                        strDecodeStringElement23 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
                        i2 |= 4;
                        break;
                    case 3:
                        strDecodeStringElement20 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i2 |= 8;
                        break;
                    case 4:
                        strDecodeStringElement22 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i2 |= 16;
                        break;
                    case 5:
                        strDecodeStringElement19 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        strDecodeStringElement18 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
                        i2 |= 64;
                        break;
                    case 7:
                        strDecodeStringElement17 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
                        i2 |= 128;
                        break;
                    case 8:
                        strDecodeStringElement21 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
                        i2 |= 256;
                        break;
                    case 9:
                        strDecodeStringElement16 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 9);
                        i2 |= 512;
                        break;
                    case 10:
                        strDecodeStringElement15 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 10);
                        i2 |= 1024;
                        break;
                    case 11:
                        strDecodeStringElement14 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 11);
                        i2 |= 2048;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            strDecodeStringElement = strDecodeStringElement14;
            str = strDecodeStringElement15;
            str2 = strDecodeStringElement13;
            i = i2;
            String str12 = strDecodeStringElement24;
            str3 = strDecodeStringElement16;
            str4 = str12;
            String str13 = strDecodeStringElement22;
            str5 = strDecodeStringElement17;
            str6 = strDecodeStringElement23;
            str7 = strDecodeStringElement21;
            str8 = strDecodeStringElement18;
            str9 = strDecodeStringElement20;
            str10 = strDecodeStringElement19;
            str11 = str13;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new EventMetaData(i, str2, str4, str6, str9, str11, str10, str8, str5, str7, str3, str, strDecodeStringElement, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, EventMetaData value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        EventMetaData.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
