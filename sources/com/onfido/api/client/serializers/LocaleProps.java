package com.onfido.api.client.serializers;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import io.sentry.protocol.Device;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: LocaleAsStringSerializer.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006#"}, d2 = {"Lcom/onfido/api/client/serializers/LocaleProps;", "", "seen1", "", Device.JsonKeys.LANGUAGE, "", MediaCallbackResultReceiver.KEY_COUNTRY, "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getCountry$annotations", "()V", "getCountry", "()Ljava/lang/String;", "getLanguage$annotations", "getLanguage", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
final /* data */ class LocaleProps {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String country;
    private final String language;

    public static /* synthetic */ LocaleProps copy$default(LocaleProps localeProps, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = localeProps.language;
        }
        if ((i & 2) != 0) {
            str2 = localeProps.country;
        }
        return localeProps.copy(str, str2);
    }

    @SerialName(MediaCallbackResultReceiver.KEY_COUNTRY)
    public static /* synthetic */ void getCountry$annotations() {
    }

    @SerialName(Device.JsonKeys.LANGUAGE)
    public static /* synthetic */ void getLanguage$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    public final LocaleProps copy(String language, String country) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(country, "country");
        return new LocaleProps(language, country);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocaleProps)) {
            return false;
        }
        LocaleProps localeProps = (LocaleProps) other;
        return Intrinsics.areEqual(this.language, localeProps.language) && Intrinsics.areEqual(this.country, localeProps.country);
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getLanguage() {
        return this.language;
    }

    public int hashCode() {
        return (this.language.hashCode() * 31) + this.country.hashCode();
    }

    public String toString() {
        return "LocaleProps(language=" + this.language + ", country=" + this.country + ")";
    }

    /* compiled from: LocaleAsStringSerializer.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/serializers/LocaleProps$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/serializers/LocaleProps;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LocaleProps> serializer() {
            return LocaleProps$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LocaleProps(int i, @SerialName(Device.JsonKeys.LANGUAGE) String str, @SerialName(MediaCallbackResultReceiver.KEY_COUNTRY) String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, LocaleProps$$serializer.INSTANCE.getDescriptor());
        }
        this.language = str;
        this.country = str2;
    }

    public LocaleProps(String language, String country) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(country, "country");
        this.language = language;
        this.country = country;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(LocaleProps self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.language);
        output.encodeStringElement(serialDesc, 1, self.country);
    }
}
