package com.onfido.android.sdk.capture.ui.userconsent.network;

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

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB/\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018HÁ\u0001¢\u0006\u0002\b\u0019R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/network/ConsentBody;", "", "seen1", "", "name", "", "isGranted", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Z)V", "isGranted$annotations", "()V", "()Z", "getName$annotations", "getName", "()Ljava/lang/String;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final class ConsentBody {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String NAME_PRIVACY_NOTICES_READ = "privacy_notices_read";
    private final boolean isGranted;
    private final String name;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/network/ConsentBody$Companion;", "", "()V", "NAME_PRIVACY_NOTICES_READ", "", "createConsent", "Lcom/onfido/android/sdk/capture/ui/userconsent/network/ConsentBody;", "isGranted", "", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final ConsentBody createConsent(boolean isGranted) {
            return new ConsentBody(ConsentBody.NAME_PRIVACY_NOTICES_READ, isGranted);
        }

        public final KSerializer<ConsentBody> serializer() {
            return ConsentBody$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ConsentBody(int i, @SerialName("name") String str, @SerialName("granted") boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, ConsentBody$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        if ((i & 2) == 0) {
            this.isGranted = false;
        } else {
            this.isGranted = z;
        }
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName("granted")
    public static /* synthetic */ void isGranted$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(ConsentBody self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.name);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.isGranted) {
            output.encodeBooleanElement(serialDesc, 1, self.isGranted);
        }
    }

    public final String getName() {
        return this.name;
    }

    /* renamed from: isGranted, reason: from getter */
    public final boolean getIsGranted() {
        return this.isGranted;
    }

    public ConsentBody(String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.isGranted = z;
    }

    public /* synthetic */ ConsentBody(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }
}
