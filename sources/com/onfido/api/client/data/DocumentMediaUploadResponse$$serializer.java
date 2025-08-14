package com.onfido.api.client.data;

import java.util.List;
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

/* compiled from: DocumentMediaUploadResponse.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/DocumentMediaUploadResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class DocumentMediaUploadResponse$$serializer implements GeneratedSerializer<DocumentMediaUploadResponse> {
    public static final DocumentMediaUploadResponse$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        DocumentMediaUploadResponse$$serializer documentMediaUploadResponse$$serializer = new DocumentMediaUploadResponse$$serializer();
        INSTANCE = documentMediaUploadResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.DocumentMediaUploadResponse", documentMediaUploadResponse$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("binary_media", false);
        pluginGeneratedSerialDescriptor.addElement("sdk_warnings", true);
        pluginGeneratedSerialDescriptor.addElement("document_fields", true);
        pluginGeneratedSerialDescriptor.addElement("content_type", true);
        pluginGeneratedSerialDescriptor.addElement("document_features", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DocumentMediaUploadResponse$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{DocumentBinaryMedia$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(DocumentValidationWarningsBundle$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(DocumentMediaUploadResponse.$childSerializers[2]), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DocumentFeatures$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DocumentMediaUploadResponse deserialize(Decoder decoder) {
        int i;
        DocumentBinaryMedia documentBinaryMedia;
        DocumentValidationWarningsBundle documentValidationWarningsBundle;
        List list;
        String str;
        DocumentFeatures documentFeatures;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        KSerializer[] kSerializerArr = DocumentMediaUploadResponse.$childSerializers;
        DocumentBinaryMedia documentBinaryMedia2 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            DocumentBinaryMedia documentBinaryMedia3 = (DocumentBinaryMedia) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, DocumentBinaryMedia$$serializer.INSTANCE, null);
            DocumentValidationWarningsBundle documentValidationWarningsBundle2 = (DocumentValidationWarningsBundle) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, DocumentValidationWarningsBundle$$serializer.INSTANCE, null);
            list = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, kSerializerArr[2], null);
            documentBinaryMedia = documentBinaryMedia3;
            str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, null);
            documentFeatures = (DocumentFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, DocumentFeatures$$serializer.INSTANCE, null);
            i = 31;
            documentValidationWarningsBundle = documentValidationWarningsBundle2;
        } else {
            boolean z = true;
            int i2 = 0;
            DocumentValidationWarningsBundle documentValidationWarningsBundle3 = null;
            List list2 = null;
            String str2 = null;
            DocumentFeatures documentFeatures2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    documentBinaryMedia2 = (DocumentBinaryMedia) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, DocumentBinaryMedia$$serializer.INSTANCE, documentBinaryMedia2);
                    i2 |= 1;
                } else if (iDecodeElementIndex == 1) {
                    documentValidationWarningsBundle3 = (DocumentValidationWarningsBundle) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, DocumentValidationWarningsBundle$$serializer.INSTANCE, documentValidationWarningsBundle3);
                    i2 |= 2;
                } else if (iDecodeElementIndex == 2) {
                    list2 = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, kSerializerArr[2], list2);
                    i2 |= 4;
                } else if (iDecodeElementIndex == 3) {
                    str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, str2);
                    i2 |= 8;
                } else {
                    if (iDecodeElementIndex != 4) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    documentFeatures2 = (DocumentFeatures) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, DocumentFeatures$$serializer.INSTANCE, documentFeatures2);
                    i2 |= 16;
                }
            }
            i = i2;
            documentBinaryMedia = documentBinaryMedia2;
            documentValidationWarningsBundle = documentValidationWarningsBundle3;
            list = list2;
            str = str2;
            documentFeatures = documentFeatures2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new DocumentMediaUploadResponse(i, documentBinaryMedia, documentValidationWarningsBundle, list, str, documentFeatures, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DocumentMediaUploadResponse value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        DocumentMediaUploadResponse.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
