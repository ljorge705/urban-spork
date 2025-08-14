package com.onfido.api.client.data;

import com.clevertap.android.sdk.db.Column;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.api.client.serializers.DateAsStringSerializer;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.rrweb.RRWebMetaEvent;
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

/* compiled from: DocumentUpload.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/DocumentUpload.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/DocumentUpload;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class DocumentUpload$$serializer implements GeneratedSerializer<DocumentUpload> {
    public static final DocumentUpload$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        DocumentUpload$$serializer documentUpload$$serializer = new DocumentUpload$$serializer();
        INSTANCE = documentUpload$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.DocumentUpload", documentUpload$$serializer, 13);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement(Column.CREATED_AT, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_NAME, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_TYPE, false);
        pluginGeneratedSerialDescriptor.addElement("file_size", false);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement(ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, true);
        pluginGeneratedSerialDescriptor.addElement("sdk_warnings", true);
        pluginGeneratedSerialDescriptor.addElement("issuing_country", true);
        pluginGeneratedSerialDescriptor.addElement(RRWebMetaEvent.JsonKeys.HREF, false);
        pluginGeneratedSerialDescriptor.addElement("download_href", false);
        pluginGeneratedSerialDescriptor.addElement("applicant_id", false);
        pluginGeneratedSerialDescriptor.addElement("document_features", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DocumentUpload$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, DateAsStringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, LongSerializer.INSTANCE, DocType$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DocumentValidationWarningsBundle$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(DocumentFeatures$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DocumentUpload deserialize(Decoder decoder) {
        Date date;
        String str;
        DocType docType;
        String str2;
        String str3;
        String str4;
        String str5;
        long j;
        DocumentFeatures documentFeatures;
        String str6;
        String str7;
        int i;
        DocumentValidationWarningsBundle documentValidationWarningsBundle;
        String str8;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 11;
        int i3 = 10;
        String strDecodeStringElement = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            Date date2 = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, DateAsStringSerializer.INSTANCE, null);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
            String strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            long jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 4);
            DocType docType2 = (DocType) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 5, DocType$$serializer.INSTANCE, null);
            String str9 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, null);
            DocumentValidationWarningsBundle documentValidationWarningsBundle2 = (DocumentValidationWarningsBundle) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, DocumentValidationWarningsBundle$$serializer.INSTANCE, null);
            String str10 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, null);
            String strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 9);
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 10);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 11);
            documentFeatures = (DocumentFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 12, DocumentFeatures$$serializer.INSTANCE, null);
            str5 = strDecodeStringElement7;
            str4 = strDecodeStringElement6;
            str3 = strDecodeStringElement5;
            docType = docType2;
            str = str10;
            i = 8191;
            str2 = strDecodeStringElement3;
            date = date2;
            j = jDecodeLongElement;
            documentValidationWarningsBundle = documentValidationWarningsBundle2;
            str6 = str9;
            str7 = strDecodeStringElement4;
            str8 = strDecodeStringElement2;
        } else {
            int i4 = 0;
            Date date3 = null;
            DocumentFeatures documentFeatures2 = null;
            DocumentValidationWarningsBundle documentValidationWarningsBundle3 = null;
            String str11 = null;
            String str12 = null;
            String strDecodeStringElement8 = null;
            String strDecodeStringElement9 = null;
            String strDecodeStringElement10 = null;
            String strDecodeStringElement11 = null;
            boolean z = true;
            long jDecodeLongElement2 = 0;
            DocType docType3 = null;
            String strDecodeStringElement12 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 11;
                    case 0:
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        i4 |= 1;
                        i2 = 11;
                        i3 = 10;
                    case 1:
                        date3 = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, DateAsStringSerializer.INSTANCE, date3);
                        i4 |= 2;
                        i2 = 11;
                        i3 = 10;
                    case 2:
                        strDecodeStringElement12 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
                        i4 |= 4;
                        i2 = 11;
                    case 3:
                        strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i4 |= 8;
                        i2 = 11;
                    case 4:
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 4);
                        i4 |= 16;
                        i2 = 11;
                    case 5:
                        docType3 = (DocType) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 5, DocType$$serializer.INSTANCE, docType3);
                        i4 |= 32;
                        i2 = 11;
                    case 6:
                        str12 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, str12);
                        i4 |= 64;
                        i2 = 11;
                    case 7:
                        documentValidationWarningsBundle3 = (DocumentValidationWarningsBundle) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, DocumentValidationWarningsBundle$$serializer.INSTANCE, documentValidationWarningsBundle3);
                        i4 |= 128;
                        i2 = 11;
                    case 8:
                        str11 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, str11);
                        i4 |= 256;
                    case 9:
                        strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 9);
                        i4 |= 512;
                    case 10:
                        strDecodeStringElement10 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, i3);
                        i4 |= 1024;
                    case 11:
                        strDecodeStringElement11 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, i2);
                        i4 |= 2048;
                    case 12:
                        documentFeatures2 = (DocumentFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 12, DocumentFeatures$$serializer.INSTANCE, documentFeatures2);
                        i4 |= 4096;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            date = date3;
            str = str11;
            docType = docType3;
            str2 = strDecodeStringElement12;
            str3 = strDecodeStringElement9;
            str4 = strDecodeStringElement10;
            str5 = strDecodeStringElement11;
            j = jDecodeLongElement2;
            documentFeatures = documentFeatures2;
            str6 = str12;
            str7 = strDecodeStringElement8;
            i = i4;
            documentValidationWarningsBundle = documentValidationWarningsBundle3;
            str8 = strDecodeStringElement;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new DocumentUpload(i, str8, date, str2, str7, j, docType, str6, documentValidationWarningsBundle, str, str3, str4, str5, documentFeatures, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DocumentUpload value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        DocumentUpload.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
