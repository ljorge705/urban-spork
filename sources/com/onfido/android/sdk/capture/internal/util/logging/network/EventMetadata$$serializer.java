package com.onfido.android.sdk.capture.internal.util.logging.network;

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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/EventMetadata;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes2.dex */
public final class EventMetadata$$serializer implements GeneratedSerializer<EventMetadata> {
    public static final EventMetadata$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        EventMetadata$$serializer eventMetadata$$serializer = new EventMetadata$$serializer();
        INSTANCE = eventMetadata$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.android.sdk.capture.internal.util.logging.network.EventMetadata", eventMetadata$$serializer, 9);
        pluginGeneratedSerialDescriptor.addElement(OperatingSystem.TYPE, false);
        pluginGeneratedSerialDescriptor.addElement("os_version", false);
        pluginGeneratedSerialDescriptor.addElement(SentryEvent.JsonKeys.FINGERPRINT, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.MODEL, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.MANUFACTURER, false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.BRAND, false);
        pluginGeneratedSerialDescriptor.addElement("product", false);
        pluginGeneratedSerialDescriptor.addElement("hardware", false);
        pluginGeneratedSerialDescriptor.addElement("androidApiLevel", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private EventMetadata$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public EventMetadata deserialize(Decoder decoder) {
        String strDecodeStringElement;
        String strDecodeStringElement2;
        String strDecodeStringElement3;
        int i;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 0;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
            String strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
            str6 = strDecodeStringElement4;
            strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
            str5 = strDecodeStringElement10;
            str3 = strDecodeStringElement9;
            str = strDecodeStringElement7;
            strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
            strDecodeStringElement = strDecodeStringElement8;
            str2 = strDecodeStringElement6;
            str4 = strDecodeStringElement5;
            i = 511;
        } else {
            String strDecodeStringElement11 = null;
            String strDecodeStringElement12 = null;
            String strDecodeStringElement13 = null;
            String strDecodeStringElement14 = null;
            String strDecodeStringElement15 = null;
            String strDecodeStringElement16 = null;
            strDecodeStringElement = null;
            String strDecodeStringElement17 = null;
            String strDecodeStringElement18 = null;
            boolean z = true;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        i2 |= 1;
                        strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        continue;
                    case 1:
                        strDecodeStringElement18 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i2 |= 2;
                        continue;
                    case 2:
                        strDecodeStringElement17 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
                        i2 |= 4;
                        break;
                    case 3:
                        strDecodeStringElement15 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i2 |= 8;
                        break;
                    case 4:
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i2 |= 16;
                        break;
                    case 5:
                        strDecodeStringElement14 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        strDecodeStringElement13 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 6);
                        i2 |= 64;
                        break;
                    case 7:
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
                        i2 |= 128;
                        break;
                    case 8:
                        strDecodeStringElement16 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 8);
                        i2 |= 256;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            strDecodeStringElement2 = strDecodeStringElement12;
            strDecodeStringElement3 = strDecodeStringElement16;
            i = i2;
            str = strDecodeStringElement15;
            str2 = strDecodeStringElement17;
            str3 = strDecodeStringElement14;
            str4 = strDecodeStringElement18;
            str5 = strDecodeStringElement13;
            str6 = strDecodeStringElement11;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new EventMetadata(i, str6, str4, str2, str, strDecodeStringElement, str3, str5, strDecodeStringElement2, strDecodeStringElement3, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, EventMetadata value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        EventMetadata.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
