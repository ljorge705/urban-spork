package com.onfido.api.client.data;

import com.clevertap.android.sdk.db.Column;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.api.client.serializers.DateAsStringSerializer;
import java.util.Date;
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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: PoaDocumentUpload.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/PoaDocumentUpload.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/PoaDocumentUpload;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class PoaDocumentUpload$$serializer implements GeneratedSerializer<PoaDocumentUpload> {
    public static final PoaDocumentUpload$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PoaDocumentUpload$$serializer poaDocumentUpload$$serializer = new PoaDocumentUpload$$serializer();
        INSTANCE = poaDocumentUpload$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.PoaDocumentUpload", poaDocumentUpload$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement(Column.CREATED_AT, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_NAME, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_TYPE, false);
        pluginGeneratedSerialDescriptor.addElement("file_size", false);
        pluginGeneratedSerialDescriptor.addElement("issuing_country", true);
        pluginGeneratedSerialDescriptor.addElement("applicant_id", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PoaDocumentUpload$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, DateAsStringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, LongSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public PoaDocumentUpload deserialize(Decoder decoder) {
        long jDecodeLongElement;
        String str;
        String strDecodeStringElement;
        String str2;
        String str3;
        Date date;
        String str4;
        String str5;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        String strDecodeStringElement2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            Date date2 = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, DateAsStringSerializer.INSTANCE, null);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            long jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 5);
            String str6 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, null);
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
            str = str6;
            i = 255;
            str3 = strDecodeStringElement5;
            str2 = strDecodeStringElement6;
            date = date2;
            jDecodeLongElement = jDecodeLongElement2;
            str5 = strDecodeStringElement3;
            str4 = strDecodeStringElement4;
        } else {
            boolean z = true;
            int i2 = 0;
            String str7 = null;
            String strDecodeStringElement7 = null;
            String strDecodeStringElement8 = null;
            String strDecodeStringElement9 = null;
            jDecodeLongElement = 0;
            String strDecodeStringElement10 = null;
            Date date3 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        i2 |= 1;
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        continue;
                    case 1:
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i2 |= 2;
                        continue;
                    case 2:
                        date3 = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, DateAsStringSerializer.INSTANCE, date3);
                        i2 |= 4;
                        break;
                    case 3:
                        strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i2 |= 8;
                        break;
                    case 4:
                        strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i2 |= 16;
                        break;
                    case 5:
                        jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        str7 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, str7);
                        i2 |= 64;
                        break;
                    case 7:
                        strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 7);
                        i2 |= 128;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = str7;
            strDecodeStringElement = strDecodeStringElement9;
            str2 = strDecodeStringElement8;
            str3 = strDecodeStringElement7;
            date = date3;
            str4 = strDecodeStringElement10;
            str5 = strDecodeStringElement2;
            i = i2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new PoaDocumentUpload(i, str5, str4, date, str3, str2, jDecodeLongElement, str, strDecodeStringElement, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, PoaDocumentUpload value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        PoaDocumentUpload.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
