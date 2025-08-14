package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B7\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÁ\u0001¢\u0006\u0002\b!R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u0007\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\r\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0004\u0010\u000e¨\u0006$"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;", "", "seen1", "", "isSupportsExternalLink", "", "isSupportsExit", "isSupportsBack", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZZ)V", "isSupportsBack$annotations", "()V", "()Z", "isSupportsExit$annotations", "isSupportsExternalLink$annotations", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKContextConfiguration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isSupportsBack;
    private final boolean isSupportsExit;
    private final boolean isSupportsExternalLink;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKContextConfiguration;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKContextConfiguration> serializer() {
            return CaptureSDKContextConfiguration$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKContextConfiguration(int i, @SerialName("supportsExternalLink") boolean z, @SerialName("supportsExit") boolean z2, @SerialName("supportsBack") boolean z3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, CaptureSDKContextConfiguration$$serializer.INSTANCE.getDescriptor());
        }
        this.isSupportsExternalLink = z;
        this.isSupportsExit = z2;
        this.isSupportsBack = z3;
    }

    public static /* synthetic */ CaptureSDKContextConfiguration copy$default(CaptureSDKContextConfiguration captureSDKContextConfiguration, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = captureSDKContextConfiguration.isSupportsExternalLink;
        }
        if ((i & 2) != 0) {
            z2 = captureSDKContextConfiguration.isSupportsExit;
        }
        if ((i & 4) != 0) {
            z3 = captureSDKContextConfiguration.isSupportsBack;
        }
        return captureSDKContextConfiguration.copy(z, z2, z3);
    }

    @SerialName("supportsBack")
    public static /* synthetic */ void isSupportsBack$annotations() {
    }

    @SerialName("supportsExit")
    public static /* synthetic */ void isSupportsExit$annotations() {
    }

    @SerialName("supportsExternalLink")
    public static /* synthetic */ void isSupportsExternalLink$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKContextConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeBooleanElement(serialDesc, 0, self.isSupportsExternalLink);
        output.encodeBooleanElement(serialDesc, 1, self.isSupportsExit);
        output.encodeBooleanElement(serialDesc, 2, self.isSupportsBack);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsSupportsExternalLink() {
        return this.isSupportsExternalLink;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSupportsExit() {
        return this.isSupportsExit;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSupportsBack() {
        return this.isSupportsBack;
    }

    public final CaptureSDKContextConfiguration copy(boolean isSupportsExternalLink, boolean isSupportsExit, boolean isSupportsBack) {
        return new CaptureSDKContextConfiguration(isSupportsExternalLink, isSupportsExit, isSupportsBack);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKContextConfiguration)) {
            return false;
        }
        CaptureSDKContextConfiguration captureSDKContextConfiguration = (CaptureSDKContextConfiguration) other;
        return this.isSupportsExternalLink == captureSDKContextConfiguration.isSupportsExternalLink && this.isSupportsExit == captureSDKContextConfiguration.isSupportsExit && this.isSupportsBack == captureSDKContextConfiguration.isSupportsBack;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.isSupportsExternalLink) * 31) + Boolean.hashCode(this.isSupportsExit)) * 31) + Boolean.hashCode(this.isSupportsBack);
    }

    public final boolean isSupportsBack() {
        return this.isSupportsBack;
    }

    public final boolean isSupportsExit() {
        return this.isSupportsExit;
    }

    public final boolean isSupportsExternalLink() {
        return this.isSupportsExternalLink;
    }

    public String toString() {
        return "CaptureSDKContextConfiguration(isSupportsExternalLink=" + this.isSupportsExternalLink + ", isSupportsExit=" + this.isSupportsExit + ", isSupportsBack=" + this.isSupportsBack + ')';
    }

    public CaptureSDKContextConfiguration(boolean z, boolean z2, boolean z3) {
        this.isSupportsExternalLink = z;
        this.isSupportsExit = z2;
        this.isSupportsBack = z3;
    }
}
