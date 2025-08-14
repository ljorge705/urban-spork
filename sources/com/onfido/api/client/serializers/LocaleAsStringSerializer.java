package com.onfido.api.client.serializers;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* compiled from: LocaleAsStringSerializer.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/onfido/api/client/serializers/LocaleAsStringSerializer;", "Lkotlinx/serialization/KSerializer;", "Ljava/util/Locale;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LocaleAsStringSerializer implements KSerializer<Locale> {
    public static final LocaleAsStringSerializer INSTANCE = new LocaleAsStringSerializer();
    private static final SerialDescriptor descriptor = LocaleProps.INSTANCE.serializer().getDescriptor();

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    private LocaleAsStringSerializer() {
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Locale deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        LocaleProps localeProps = (LocaleProps) decoder.decodeSerializableValue(LocaleProps.INSTANCE.serializer());
        return new Locale(localeProps.getLanguage(), localeProps.getCountry());
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Locale value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        KSerializer<LocaleProps> kSerializerSerializer = LocaleProps.INSTANCE.serializer();
        String language = value.getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
        String country = value.getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
        encoder.encodeSerializableValue(kSerializerSerializer, new LocaleProps(language, country));
    }
}
