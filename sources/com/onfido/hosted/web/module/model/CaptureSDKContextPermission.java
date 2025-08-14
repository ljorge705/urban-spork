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
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;", "", "seen1", "", "alwaysRequestPermissions", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Z)V", "getAlwaysRequestPermissions$annotations", "()V", "getAlwaysRequestPermissions", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKContextPermission {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean alwaysRequestPermissions;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKContextPermission;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKContextPermission> serializer() {
            return CaptureSDKContextPermission$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CaptureSDKContextPermission() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ CaptureSDKContextPermission copy$default(CaptureSDKContextPermission captureSDKContextPermission, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = captureSDKContextPermission.alwaysRequestPermissions;
        }
        return captureSDKContextPermission.copy(z);
    }

    @SerialName("alwaysRequestPermissions")
    public static /* synthetic */ void getAlwaysRequestPermissions$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKContextPermission self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.alwaysRequestPermissions) {
            return;
        }
        output.encodeBooleanElement(serialDesc, 0, self.alwaysRequestPermissions);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getAlwaysRequestPermissions() {
        return this.alwaysRequestPermissions;
    }

    public final CaptureSDKContextPermission copy(boolean alwaysRequestPermissions) {
        return new CaptureSDKContextPermission(alwaysRequestPermissions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CaptureSDKContextPermission) && this.alwaysRequestPermissions == ((CaptureSDKContextPermission) other).alwaysRequestPermissions;
    }

    public final boolean getAlwaysRequestPermissions() {
        return this.alwaysRequestPermissions;
    }

    public int hashCode() {
        return Boolean.hashCode(this.alwaysRequestPermissions);
    }

    public String toString() {
        return "CaptureSDKContextPermission(alwaysRequestPermissions=" + this.alwaysRequestPermissions + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKContextPermission(int i, @SerialName("alwaysRequestPermissions") boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.alwaysRequestPermissions = true;
        } else {
            this.alwaysRequestPermissions = z;
        }
    }

    public CaptureSDKContextPermission(boolean z) {
        this.alwaysRequestPermissions = z;
    }

    public /* synthetic */ CaptureSDKContextPermission(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }
}
