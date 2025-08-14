package com.onfido.hosted.web.module.model;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/hosted/web/module/model/CaptureSDKContext.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKContext;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class CaptureSDKContext$$serializer implements GeneratedSerializer<CaptureSDKContext> {
    public static final CaptureSDKContext$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        CaptureSDKContext$$serializer captureSDKContext$$serializer = new CaptureSDKContext$$serializer();
        INSTANCE = captureSDKContext$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.hosted.web.module.model.CaptureSDKContext", captureSDKContext$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("platform", false);
        pluginGeneratedSerialDescriptor.addElement("version", false);
        pluginGeneratedSerialDescriptor.addElement(OperatingSystem.TYPE, false);
        pluginGeneratedSerialDescriptor.addElement(OnfidoLauncher.KEY_CONFIG, false);
        pluginGeneratedSerialDescriptor.addElement("permission", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private CaptureSDKContext$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, CaptureSDKOS$$serializer.INSTANCE, CaptureSDKContextConfiguration$$serializer.INSTANCE, CaptureSDKContextPermission$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public CaptureSDKContext deserialize(Decoder decoder) {
        int i;
        String str;
        String str2;
        CaptureSDKOS captureSDKOS;
        CaptureSDKContextConfiguration captureSDKContextConfiguration;
        CaptureSDKContextPermission captureSDKContextPermission;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        String strDecodeStringElement = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            CaptureSDKOS captureSDKOS2 = (CaptureSDKOS) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, CaptureSDKOS$$serializer.INSTANCE, null);
            str = strDecodeStringElement2;
            captureSDKContextConfiguration = (CaptureSDKContextConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, CaptureSDKContextConfiguration$$serializer.INSTANCE, null);
            captureSDKContextPermission = (CaptureSDKContextPermission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 4, CaptureSDKContextPermission$$serializer.INSTANCE, null);
            captureSDKOS = captureSDKOS2;
            str2 = strDecodeStringElement3;
            i = 31;
        } else {
            boolean z = true;
            int i2 = 0;
            String strDecodeStringElement4 = null;
            CaptureSDKOS captureSDKOS3 = null;
            CaptureSDKContextConfiguration captureSDKContextConfiguration2 = null;
            CaptureSDKContextPermission captureSDKContextPermission2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    captureSDKOS3 = (CaptureSDKOS) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, CaptureSDKOS$$serializer.INSTANCE, captureSDKOS3);
                    i2 |= 4;
                } else if (iDecodeElementIndex == 3) {
                    captureSDKContextConfiguration2 = (CaptureSDKContextConfiguration) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, CaptureSDKContextConfiguration$$serializer.INSTANCE, captureSDKContextConfiguration2);
                    i2 |= 8;
                } else {
                    if (iDecodeElementIndex != 4) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    captureSDKContextPermission2 = (CaptureSDKContextPermission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 4, CaptureSDKContextPermission$$serializer.INSTANCE, captureSDKContextPermission2);
                    i2 |= 16;
                }
            }
            i = i2;
            str = strDecodeStringElement;
            str2 = strDecodeStringElement4;
            captureSDKOS = captureSDKOS3;
            captureSDKContextConfiguration = captureSDKContextConfiguration2;
            captureSDKContextPermission = captureSDKContextPermission2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new CaptureSDKContext(i, str, str2, captureSDKOS, captureSDKContextConfiguration, captureSDKContextPermission, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, CaptureSDKContext value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        CaptureSDKContext.write$Self$onfido_capture_sdk_core_release(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
