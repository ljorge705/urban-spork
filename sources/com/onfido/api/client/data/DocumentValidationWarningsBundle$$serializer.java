package com.onfido.api.client.data;

import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
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

/* compiled from: DocumentValidationWarningsBundle.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/DocumentValidationWarningsBundle.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class DocumentValidationWarningsBundle$$serializer implements GeneratedSerializer<DocumentValidationWarningsBundle> {
    public static final DocumentValidationWarningsBundle$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        DocumentValidationWarningsBundle$$serializer documentValidationWarningsBundle$$serializer = new DocumentValidationWarningsBundle$$serializer();
        INSTANCE = documentValidationWarningsBundle$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.DocumentValidationWarningsBundle", documentValidationWarningsBundle$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.GLARE_DETECTION_ERROR_KEY, true);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.BLUR_DETECTION_ERROR_KEY, true);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.CUTOFF_DETECTION_ERROR_KEY, true);
        pluginGeneratedSerialDescriptor.addElement("detect_document", true);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.BARCODE_DETECTION_ERROR_KEY, true);
        pluginGeneratedSerialDescriptor.addElement(IQSUploadErrorParser.ORIGINAL_DOCUMENT_DETECTION_ERROR_KEY, true);
        pluginGeneratedSerialDescriptor.addElement("image_quality", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DocumentValidationWarningsBundle$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ValidationResult$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(DocumentValidationWarningsBundle$ImageQuality$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public DocumentValidationWarningsBundle deserialize(Decoder decoder) {
        DocumentValidationWarningsBundle.ImageQuality imageQuality;
        ValidationResult validationResult;
        ValidationResult validationResult2;
        ValidationResult validationResult3;
        ValidationResult validationResult4;
        ValidationResult validationResult5;
        ValidationResult validationResult6;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 6;
        ValidationResult validationResult7 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            ValidationResult validationResult8 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, ValidationResult$$serializer.INSTANCE, null);
            ValidationResult validationResult9 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, ValidationResult$$serializer.INSTANCE, null);
            ValidationResult validationResult10 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, ValidationResult$$serializer.INSTANCE, null);
            ValidationResult validationResult11 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, ValidationResult$$serializer.INSTANCE, null);
            ValidationResult validationResult12 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, ValidationResult$$serializer.INSTANCE, null);
            ValidationResult validationResult13 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, ValidationResult$$serializer.INSTANCE, null);
            validationResult6 = validationResult8;
            imageQuality = (DocumentValidationWarningsBundle.ImageQuality) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 6, DocumentValidationWarningsBundle$ImageQuality$$serializer.INSTANCE, null);
            validationResult = validationResult13;
            validationResult3 = validationResult11;
            validationResult2 = validationResult12;
            validationResult4 = validationResult10;
            validationResult5 = validationResult9;
            i = 127;
        } else {
            boolean z = true;
            int i3 = 0;
            DocumentValidationWarningsBundle.ImageQuality imageQuality2 = null;
            ValidationResult validationResult14 = null;
            ValidationResult validationResult15 = null;
            ValidationResult validationResult16 = null;
            ValidationResult validationResult17 = null;
            ValidationResult validationResult18 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 6;
                    case 0:
                        validationResult7 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 0, ValidationResult$$serializer.INSTANCE, validationResult7);
                        i3 |= 1;
                        i2 = 6;
                    case 1:
                        validationResult14 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 1, ValidationResult$$serializer.INSTANCE, validationResult14);
                        i3 |= 2;
                        i2 = 6;
                    case 2:
                        validationResult15 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 2, ValidationResult$$serializer.INSTANCE, validationResult15);
                        i3 |= 4;
                    case 3:
                        validationResult16 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, ValidationResult$$serializer.INSTANCE, validationResult16);
                        i3 |= 8;
                    case 4:
                        validationResult17 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, ValidationResult$$serializer.INSTANCE, validationResult17);
                        i3 |= 16;
                    case 5:
                        validationResult18 = (ValidationResult) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 5, ValidationResult$$serializer.INSTANCE, validationResult18);
                        i3 |= 32;
                    case 6:
                        imageQuality2 = (DocumentValidationWarningsBundle.ImageQuality) compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, i2, DocumentValidationWarningsBundle$ImageQuality$$serializer.INSTANCE, imageQuality2);
                        i3 |= 64;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            imageQuality = imageQuality2;
            validationResult = validationResult18;
            validationResult2 = validationResult17;
            validationResult3 = validationResult16;
            validationResult4 = validationResult15;
            validationResult5 = validationResult14;
            validationResult6 = validationResult7;
            i = i3;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new DocumentValidationWarningsBundle(i, validationResult6, validationResult5, validationResult4, validationResult3, validationResult2, validationResult, imageQuality, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, DocumentValidationWarningsBundle value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        DocumentValidationWarningsBundle.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
