package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import java.util.Arrays;
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
import kotlinx.serialization.internal.IntArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: NfcProperties.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,BG\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J7\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0007HÖ\u0001J&\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)HÁ\u0001¢\u0006\u0002\b*R\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0004\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0015¨\u0006-"}, d2 = {"Lcom/onfido/api/client/data/NfcProperties;", "", "seen1", "", "isNfcSupported", "", "nfcKey", "", "aaChallenge", "", "can", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;[ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/lang/String;[ILjava/lang/String;)V", "getAaChallenge$annotations", "()V", "getAaChallenge", "()[I", "getCan$annotations", "getCan", "()Ljava/lang/String;", "isNfcSupported$annotations", "()Z", "getNfcKey$annotations", "getNfcKey", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class NfcProperties {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int[] aaChallenge;
    private final String can;
    private final boolean isNfcSupported;
    private final String nfcKey;

    public static /* synthetic */ NfcProperties copy$default(NfcProperties nfcProperties, boolean z, String str, int[] iArr, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = nfcProperties.isNfcSupported;
        }
        if ((i & 2) != 0) {
            str = nfcProperties.nfcKey;
        }
        if ((i & 4) != 0) {
            iArr = nfcProperties.aaChallenge;
        }
        if ((i & 8) != 0) {
            str2 = nfcProperties.can;
        }
        return nfcProperties.copy(z, str, iArr, str2);
    }

    @SerialName("aa_challenge")
    public static /* synthetic */ void getAaChallenge$annotations() {
    }

    @SerialName("can")
    public static /* synthetic */ void getCan$annotations() {
    }

    @SerialName("nfc_key")
    public static /* synthetic */ void getNfcKey$annotations() {
    }

    @SerialName("nfc_supported")
    public static /* synthetic */ void isNfcSupported$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsNfcSupported() {
        return this.isNfcSupported;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNfcKey() {
        return this.nfcKey;
    }

    /* renamed from: component3, reason: from getter */
    public final int[] getAaChallenge() {
        return this.aaChallenge;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCan() {
        return this.can;
    }

    public final NfcProperties copy(boolean isNfcSupported, String nfcKey, int[] aaChallenge, String can) {
        return new NfcProperties(isNfcSupported, nfcKey, aaChallenge, can);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NfcProperties)) {
            return false;
        }
        NfcProperties nfcProperties = (NfcProperties) other;
        return this.isNfcSupported == nfcProperties.isNfcSupported && Intrinsics.areEqual(this.nfcKey, nfcProperties.nfcKey) && Intrinsics.areEqual(this.aaChallenge, nfcProperties.aaChallenge) && Intrinsics.areEqual(this.can, nfcProperties.can);
    }

    public final int[] getAaChallenge() {
        return this.aaChallenge;
    }

    public final String getCan() {
        return this.can;
    }

    public final String getNfcKey() {
        return this.nfcKey;
    }

    public int hashCode() {
        int iHashCode = Boolean.hashCode(this.isNfcSupported) * 31;
        String str = this.nfcKey;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        int[] iArr = this.aaChallenge;
        int iHashCode3 = (iHashCode2 + (iArr == null ? 0 : Arrays.hashCode(iArr))) * 31;
        String str2 = this.can;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isNfcSupported() {
        return this.isNfcSupported;
    }

    public String toString() {
        return "NfcProperties(isNfcSupported=" + this.isNfcSupported + ", nfcKey=" + this.nfcKey + ", aaChallenge=" + Arrays.toString(this.aaChallenge) + ", can=" + this.can + ")";
    }

    /* compiled from: NfcProperties.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/NfcProperties$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/NfcProperties;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<NfcProperties> serializer() {
            return NfcProperties$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ NfcProperties(int i, @SerialName("nfc_supported") boolean z, @SerialName("nfc_key") String str, @SerialName("aa_challenge") int[] iArr, @SerialName("can") String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, NfcProperties$$serializer.INSTANCE.getDescriptor());
        }
        this.isNfcSupported = z;
        this.nfcKey = str;
        this.aaChallenge = iArr;
        this.can = str2;
    }

    public NfcProperties(boolean z, String str, int[] iArr, String str2) {
        this.isNfcSupported = z;
        this.nfcKey = str;
        this.aaChallenge = iArr;
        this.can = str2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(NfcProperties self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeBooleanElement(serialDesc, 0, self.isNfcSupported);
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.nfcKey);
        output.encodeNullableSerializableElement(serialDesc, 2, IntArraySerializer.INSTANCE, self.aaChallenge);
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.can);
    }
}
