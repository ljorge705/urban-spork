package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
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
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: LiveVideoLanguage.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0019\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÂ\u0003J!\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\f¨\u0006 "}, d2 = {"Lcom/onfido/api/client/data/LiveVideoLanguage;", "", "seen1", "", "source", "", RemoteConfigConstants.RequestFieldKey.LANGUAGE_CODE, "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getLanguageCode$annotations", "()V", "getSource$annotations", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class LiveVideoLanguage {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String languageCode;
    private final String source;

    /* renamed from: component1, reason: from getter */
    private final String getSource() {
        return this.source;
    }

    /* renamed from: component2, reason: from getter */
    private final String getLanguageCode() {
        return this.languageCode;
    }

    public static /* synthetic */ LiveVideoLanguage copy$default(LiveVideoLanguage liveVideoLanguage, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveVideoLanguage.source;
        }
        if ((i & 2) != 0) {
            str2 = liveVideoLanguage.languageCode;
        }
        return liveVideoLanguage.copy(str, str2);
    }

    @SerialName("language_code")
    private static /* synthetic */ void getLanguageCode$annotations() {
    }

    @SerialName("source")
    private static /* synthetic */ void getSource$annotations() {
    }

    public final LiveVideoLanguage copy(String source, String languageCode) {
        return new LiveVideoLanguage(source, languageCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveVideoLanguage)) {
            return false;
        }
        LiveVideoLanguage liveVideoLanguage = (LiveVideoLanguage) other;
        return Intrinsics.areEqual(this.source, liveVideoLanguage.source) && Intrinsics.areEqual(this.languageCode, liveVideoLanguage.languageCode);
    }

    public int hashCode() {
        String str = this.source;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.languageCode;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LiveVideoLanguage(source=" + this.source + ", languageCode=" + this.languageCode + ")";
    }

    /* compiled from: LiveVideoLanguage.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/LiveVideoLanguage$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/LiveVideoLanguage;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<LiveVideoLanguage> serializer() {
            return LiveVideoLanguage$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LiveVideoLanguage(int i, @SerialName("source") String str, @SerialName("language_code") String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, LiveVideoLanguage$$serializer.INSTANCE.getDescriptor());
        }
        this.source = str;
        this.languageCode = str2;
    }

    public LiveVideoLanguage(String str, String str2) {
        this.source = str;
        this.languageCode = str2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(LiveVideoLanguage self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.source);
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.languageCode);
    }
}
