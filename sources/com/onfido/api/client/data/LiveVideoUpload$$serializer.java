package com.onfido.api.client.data;

import com.clevertap.android.sdk.db.Column;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.api.client.serializers.DateAsStringSerializer;
import io.sentry.rrweb.RRWebMetaEvent;
import java.util.Date;
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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: LiveVideoUpload.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/onfido/api/client/data/LiveVideoUpload.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/onfido/api/client/data/LiveVideoUpload;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes6.dex */
public final class LiveVideoUpload$$serializer implements GeneratedSerializer<LiveVideoUpload> {
    public static final LiveVideoUpload$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        LiveVideoUpload$$serializer liveVideoUpload$$serializer = new LiveVideoUpload$$serializer();
        INSTANCE = liveVideoUpload$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.onfido.api.client.data.LiveVideoUpload", liveVideoUpload$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement(Column.CREATED_AT, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_NAME, false);
        pluginGeneratedSerialDescriptor.addElement(MediaCallbackResultReceiver.KEY_FILE_TYPE, false);
        pluginGeneratedSerialDescriptor.addElement(RRWebMetaEvent.JsonKeys.HREF, false);
        pluginGeneratedSerialDescriptor.addElement("download_href", false);
        pluginGeneratedSerialDescriptor.addElement("file_size", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private LiveVideoUpload$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, DateAsStringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, LongSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public LiveVideoUpload deserialize(Decoder decoder) {
        Date date;
        String strDecodeStringElement;
        String strDecodeStringElement2;
        String strDecodeStringElement3;
        String strDecodeStringElement4;
        long jDecodeLongElement;
        int i;
        String str;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        String strDecodeStringElement5 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement6 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            Date date2 = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, DateAsStringSerializer.INSTANCE, null);
            String strDecodeStringElement7 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
            String strDecodeStringElement8 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
            String strDecodeStringElement9 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
            str = strDecodeStringElement6;
            i = 127;
            strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
            strDecodeStringElement2 = strDecodeStringElement8;
            strDecodeStringElement3 = strDecodeStringElement9;
            strDecodeStringElement = strDecodeStringElement7;
            date = date2;
            jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 6);
        } else {
            long jDecodeLongElement2 = 0;
            boolean z = true;
            int i2 = 0;
            date = null;
            strDecodeStringElement = null;
            strDecodeStringElement2 = null;
            strDecodeStringElement3 = null;
            strDecodeStringElement4 = null;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        continue;
                    case 0:
                        strDecodeStringElement5 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        i2 |= 1;
                        continue;
                    case 1:
                        date = (Date) compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, DateAsStringSerializer.INSTANCE, date);
                        i2 |= 2;
                        break;
                    case 2:
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 2);
                        i2 |= 4;
                        break;
                    case 3:
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 3);
                        i2 |= 8;
                        break;
                    case 4:
                        strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 4);
                        i2 |= 16;
                        break;
                    case 5:
                        strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(descriptor2, 6);
                        i2 |= 64;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            jDecodeLongElement = jDecodeLongElement2;
            i = i2;
            str = strDecodeStringElement5;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new LiveVideoUpload(i, str, date, strDecodeStringElement, strDecodeStringElement2, strDecodeStringElement3, strDecodeStringElement4, jDecodeLongElement, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, LiveVideoUpload value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        LiveVideoUpload.write$Self$onfido_api_client(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
