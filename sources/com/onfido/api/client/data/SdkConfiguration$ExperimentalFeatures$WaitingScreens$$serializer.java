package com.onfido.api.client.data;

import com.onfido.api.client.data.SdkConfiguration;
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

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/SdkConfiguration.ExperimentalFeatures.WaitingScreens.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer implements GeneratedSerializer<SdkConfiguration.ExperimentalFeatures.WaitingScreens> {
    public static final SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer sdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer = new SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer();
        INSTANCE = sdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.SdkConfiguration.ExperimentalFeatures.WaitingScreens", sdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer, 2);
        pluginGeneratedSerialDescriptor.addElement("studio_task_submission", false);
        pluginGeneratedSerialDescriptor.addElement("media_upload_submission", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$$serializer.INSTANCE, SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public SdkConfiguration.ExperimentalFeatures.WaitingScreens deserialize(Decoder decoder) {
        SdkConfiguration.ExperimentalFeatures.WaitingScreens.StudioTaskSubmission studioTaskSubmission;
        SdkConfiguration.ExperimentalFeatures.WaitingScreens.MediaUploadSubmission mediaUploadSubmission;
        int i;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            studioTaskSubmission = (SdkConfiguration.ExperimentalFeatures.WaitingScreens.StudioTaskSubmission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$$serializer.INSTANCE, null);
            mediaUploadSubmission = (SdkConfiguration.ExperimentalFeatures.WaitingScreens.MediaUploadSubmission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$$serializer.INSTANCE, null);
            i = 3;
        } else {
            boolean z = true;
            int i2 = 0;
            studioTaskSubmission = null;
            SdkConfiguration.ExperimentalFeatures.WaitingScreens.MediaUploadSubmission mediaUploadSubmission2 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                if (iDecodeElementIndex == -1) {
                    z = false;
                } else if (iDecodeElementIndex == 0) {
                    studioTaskSubmission = (SdkConfiguration.ExperimentalFeatures.WaitingScreens.StudioTaskSubmission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$$serializer.INSTANCE, studioTaskSubmission);
                    i2 |= 1;
                } else {
                    if (iDecodeElementIndex != 1) {
                        throw new UnknownFieldException(iDecodeElementIndex);
                    }
                    mediaUploadSubmission2 = (SdkConfiguration.ExperimentalFeatures.WaitingScreens.MediaUploadSubmission) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$$serializer.INSTANCE, mediaUploadSubmission2);
                    i2 |= 2;
                }
            }
            mediaUploadSubmission = mediaUploadSubmission2;
            i = i2;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new SdkConfiguration.ExperimentalFeatures.WaitingScreens(i, studioTaskSubmission, mediaUploadSubmission, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, SdkConfiguration.ExperimentalFeatures.WaitingScreens value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        SdkConfiguration.ExperimentalFeatures.WaitingScreens.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
