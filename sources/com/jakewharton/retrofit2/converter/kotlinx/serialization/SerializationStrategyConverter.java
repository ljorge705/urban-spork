package com.jakewharton.retrofit2.converter.kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.spongycastle.cms.CMSAttributeTableGenerator;
import retrofit2.Converter;

/* compiled from: SerializationStrategyConverter.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0015\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jakewharton/retrofit2/converter/kotlinx/serialization/SerializationStrategyConverter;", ExifInterface.GPS_DIRECTION_TRUE, "Lretrofit2/Converter;", "Lokhttp3/RequestBody;", CMSAttributeTableGenerator.CONTENT_TYPE, "Lokhttp3/MediaType;", "saver", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "Lcom/jakewharton/retrofit2/converter/kotlinx/serialization/Serializer;", "(Lokhttp3/MediaType;Lkotlinx/serialization/SerializationStrategy;Lcom/jakewharton/retrofit2/converter/kotlinx/serialization/Serializer;)V", "convert", "value", "(Ljava/lang/Object;)Lokhttp3/RequestBody;", "retrofit2-kotlinx-serialization-converter"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SerializationStrategyConverter<T> implements Converter<T, RequestBody> {
    private final MediaType contentType;
    private final SerializationStrategy<T> saver;
    private final Serializer serializer;

    /* JADX WARN: Multi-variable type inference failed */
    public SerializationStrategyConverter(MediaType contentType, SerializationStrategy<? super T> saver, Serializer serializer) {
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(saver, "saver");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.contentType = contentType;
        this.saver = saver;
        this.serializer = serializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // retrofit2.Converter
    public /* bridge */ /* synthetic */ RequestBody convert(Object obj) {
        return convert((SerializationStrategyConverter<T>) obj);
    }

    @Override // retrofit2.Converter
    public RequestBody convert(T value) {
        return this.serializer.toRequestBody(this.contentType, this.saver, value);
    }
}
