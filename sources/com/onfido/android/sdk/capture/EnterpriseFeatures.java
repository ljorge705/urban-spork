package com.onfido.android.sdk.capture;

import android.os.Parcel;
import android.os.Parcelable;
import com.onfido.api.client.token.sdk.TokenEnterpriseFeatures;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnterpriseFeatures.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "Landroid/os/Parcelable;", TokenEnterpriseFeatures.HIDE_ONFIDO_LOGO, "", "cobrandingText", "", "cobrandingLogoLightMode", "", "cobrandingLogoDarkMode", TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS, "(ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getCobrandingLogoDarkMode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCobrandingLogoLightMode", "getCobrandingText", "()Ljava/lang/String;", "getDisableMobileSdkAnalytics", "()Z", "getHideOnfidoLogo", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Builder", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnterpriseFeatures implements Parcelable {
    private final Integer cobrandingLogoDarkMode;
    private final Integer cobrandingLogoLightMode;
    private final String cobrandingText;
    private final boolean disableMobileSdkAnalytics;
    private final boolean hideOnfidoLogo;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<EnterpriseFeatures> CREATOR = new Creator();

    /* compiled from: EnterpriseFeatures.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<EnterpriseFeatures> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final EnterpriseFeatures createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new EnterpriseFeatures(parcel.readInt() != 0, parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() != 0, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final EnterpriseFeatures[] newArray(int i) {
            return new EnterpriseFeatures[i];
        }
    }

    public /* synthetic */ EnterpriseFeatures(boolean z, String str, Integer num, Integer num2, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, num, num2, z2);
    }

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Integer getCobrandingLogoDarkMode() {
        return this.cobrandingLogoDarkMode;
    }

    public final Integer getCobrandingLogoLightMode() {
        return this.cobrandingLogoLightMode;
    }

    public final String getCobrandingText() {
        return this.cobrandingText;
    }

    public final boolean getDisableMobileSdkAnalytics() {
        return this.disableMobileSdkAnalytics;
    }

    public final boolean getHideOnfidoLogo() {
        return this.hideOnfidoLogo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.hideOnfidoLogo ? 1 : 0);
        parcel.writeString(this.cobrandingText);
        Integer num = this.cobrandingLogoLightMode;
        if (num == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        }
        Integer num2 = this.cobrandingLogoDarkMode;
        if (num2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
        }
        parcel.writeInt(this.disableMobileSdkAnalytics ? 1 : 0);
    }

    private EnterpriseFeatures(boolean z, String str, Integer num, Integer num2, boolean z2) {
        this.hideOnfidoLogo = z;
        this.cobrandingText = str;
        this.cobrandingLogoLightMode = num;
        this.cobrandingLogoDarkMode = num2;
        this.disableMobileSdkAnalytics = z2;
    }

    /* compiled from: EnterpriseFeatures.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\t\u001a\u00020\u0000J\u001a\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\u0006\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/EnterpriseFeatures$Builder;", "", "()V", "cobrandingLogoDarkMode", "", "Ljava/lang/Integer;", "cobrandingLogoLightMode", "cobrandingText", "", TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS, "", TokenEnterpriseFeatures.HIDE_ONFIDO_LOGO, OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/EnterpriseFeatures;", "withCobrandingLogo", "withCobrandingText", "withHideOnfidoLogo", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private Integer cobrandingLogoDarkMode;
        private Integer cobrandingLogoLightMode;
        private String cobrandingText;
        private boolean disableMobileSdkAnalytics;
        private boolean hideOnfidoLogo;

        public final Builder withHideOnfidoLogo(boolean hideOnfidoLogo) {
            this.hideOnfidoLogo = hideOnfidoLogo;
            return this;
        }

        public final Builder withCobrandingText(String cobrandingText) {
            Intrinsics.checkNotNullParameter(cobrandingText, "cobrandingText");
            this.cobrandingText = cobrandingText;
            return this;
        }

        public final Builder withCobrandingLogo(int cobrandingLogoLightMode, int cobrandingLogoDarkMode) {
            this.cobrandingLogoLightMode = Integer.valueOf(cobrandingLogoLightMode);
            this.cobrandingLogoDarkMode = Integer.valueOf(cobrandingLogoDarkMode);
            return this;
        }

        public final Builder disableMobileSdkAnalytics() {
            this.disableMobileSdkAnalytics = true;
            return this;
        }

        public final EnterpriseFeatures build() {
            return new EnterpriseFeatures(this.hideOnfidoLogo, this.cobrandingText, this.cobrandingLogoLightMode, this.cobrandingLogoDarkMode, this.disableMobileSdkAnalytics, null);
        }
    }

    /* compiled from: EnterpriseFeatures.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/EnterpriseFeatures$Companion;", "", "()V", "builder", "Lcom/onfido/android/sdk/capture/EnterpriseFeatures$Builder;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }
    }
}
