package com.onfido.api.client.token.sdk.model;

import com.nimbusds.jwt.JWTClaimNames;
import io.sentry.SentryReplayEvent;
import io.sentry.protocol.DebugImage;
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

/* compiled from: InternalSDKTokenPayload.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/token/sdk/model/InternalSDKTokenPayload.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/token/sdk/model/InternalSDKTokenPayload;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class InternalSDKTokenPayload$$serializer implements GeneratedSerializer<InternalSDKTokenPayload> {
    public static final InternalSDKTokenPayload$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        InternalSDKTokenPayload$$serializer internalSDKTokenPayload$$serializer = new InternalSDKTokenPayload$$serializer();
        INSTANCE = internalSDKTokenPayload$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.token.sdk.model.InternalSDKTokenPayload", internalSDKTokenPayload$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement(SentryReplayEvent.JsonKeys.URLS, true);
        pluginGeneratedSerialDescriptor.addElement(DebugImage.JsonKeys.UUID, true);
        pluginGeneratedSerialDescriptor.addElement("payload", false);
        pluginGeneratedSerialDescriptor.addElement(JWTClaimNames.ISSUER, true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private InternalSDKTokenPayload$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(InternalSDKTokenUrl$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), InternalSDKTokenPayloadField$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public InternalSDKTokenPayload deserialize(Decoder decoder) {
        int i;
        InternalSDKTokenUrl internalSDKTokenUrl;
        String str;
        InternalSDKTokenPayloadField internalSDKTokenPayloadField;
        String str2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        InternalSDKTokenUrl internalSDKTokenUrl2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            InternalSDKTokenUrl internalSDKTokenUrl3 = (InternalSDKTokenUrl) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, InternalSDKTokenUrl$$serializer.INSTANCE, null);
            String str3 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, StringSerializer.INSTANCE, null);
            InternalSDKTokenPayloadField internalSDKTokenPayloadField2 = (InternalSDKTokenPayloadField) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, InternalSDKTokenPayloadField$$serializer.INSTANCE, null);
            internalSDKTokenUrl = internalSDKTokenUrl3;
            str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, null);
            internalSDKTokenPayloadField = internalSDKTokenPayloadField2;
            str = str3;
            i = 15;
        } else {
            boolean z = true;
            int i2 = 0;
            String str4 = null;
            InternalSDKTokenPayloadField internalSDKTokenPayloadField3 = null;
            String str5 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    internalSDKTokenUrl2 = (InternalSDKTokenUrl) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, InternalSDKTokenUrl$$serializer.INSTANCE, internalSDKTokenUrl2);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    str4 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, StringSerializer.INSTANCE, str4);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    internalSDKTokenPayloadField3 = (InternalSDKTokenPayloadField) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, InternalSDKTokenPayloadField$$serializer.INSTANCE, internalSDKTokenPayloadField3);
                    i2 |= 4;
                } else {
                    if (iDecodeElementIndex != 3) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    str5 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, str5);
                    i2 |= 8;
                }
            }
            i = i2;
            internalSDKTokenUrl = internalSDKTokenUrl2;
            str = str4;
            internalSDKTokenPayloadField = internalSDKTokenPayloadField3;
            str2 = str5;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new InternalSDKTokenPayload(i, internalSDKTokenUrl, str, internalSDKTokenPayloadField, str2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, InternalSDKTokenPayload value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        InternalSDKTokenPayload.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
