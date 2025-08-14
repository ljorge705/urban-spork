package com.onfido.android.sdk.capture.ui.options;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionCaptureVariantOptions.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "showIntro", "", "audioEnabled", "isTestEnv", "encryptedBiometricToken", "", "(ZZZLjava/lang/String;)V", "getAudioEnabled", "()Z", "getEncryptedBiometricToken", "()Ljava/lang/String;", "getShowIntro", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MotionCaptureVariantOptions extends BaseOptions {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MotionCaptureVariantOptions DEFAULT = new MotionCaptureVariantOptions(false, false, false, null, 15, null);
    private final boolean audioEnabled;
    private final String encryptedBiometricToken;
    private final boolean isTestEnv;
    private final boolean showIntro;

    public MotionCaptureVariantOptions() {
        this(false, false, false, null, 15, null);
    }

    public static /* synthetic */ MotionCaptureVariantOptions copy$default(MotionCaptureVariantOptions motionCaptureVariantOptions, boolean z, boolean z2, boolean z3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = motionCaptureVariantOptions.showIntro;
        }
        if ((i & 2) != 0) {
            z2 = motionCaptureVariantOptions.audioEnabled;
        }
        if ((i & 4) != 0) {
            z3 = motionCaptureVariantOptions.isTestEnv;
        }
        if ((i & 8) != 0) {
            str = motionCaptureVariantOptions.encryptedBiometricToken;
        }
        return motionCaptureVariantOptions.copy(z, z2, z3, str);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowIntro() {
        return this.showIntro;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getAudioEnabled() {
        return this.audioEnabled;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsTestEnv() {
        return this.isTestEnv;
    }

    /* renamed from: component4, reason: from getter */
    public final String getEncryptedBiometricToken() {
        return this.encryptedBiometricToken;
    }

    public final MotionCaptureVariantOptions copy(boolean showIntro, boolean audioEnabled, boolean isTestEnv, String encryptedBiometricToken) {
        return new MotionCaptureVariantOptions(showIntro, audioEnabled, isTestEnv, encryptedBiometricToken);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MotionCaptureVariantOptions)) {
            return false;
        }
        MotionCaptureVariantOptions motionCaptureVariantOptions = (MotionCaptureVariantOptions) other;
        return this.showIntro == motionCaptureVariantOptions.showIntro && this.audioEnabled == motionCaptureVariantOptions.audioEnabled && this.isTestEnv == motionCaptureVariantOptions.isTestEnv && Intrinsics.areEqual(this.encryptedBiometricToken, motionCaptureVariantOptions.encryptedBiometricToken);
    }

    public final boolean getAudioEnabled() {
        return this.audioEnabled;
    }

    public final String getEncryptedBiometricToken() {
        return this.encryptedBiometricToken;
    }

    public final boolean getShowIntro() {
        return this.showIntro;
    }

    public int hashCode() {
        int iHashCode = ((((Boolean.hashCode(this.showIntro) * 31) + Boolean.hashCode(this.audioEnabled)) * 31) + Boolean.hashCode(this.isTestEnv)) * 31;
        String str = this.encryptedBiometricToken;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isTestEnv() {
        return this.isTestEnv;
    }

    public String toString() {
        return "MotionCaptureVariantOptions(showIntro=" + this.showIntro + ", audioEnabled=" + this.audioEnabled + ", isTestEnv=" + this.isTestEnv + ", encryptedBiometricToken=" + this.encryptedBiometricToken + ")";
    }

    public /* synthetic */ MotionCaptureVariantOptions(boolean z, boolean z2, boolean z3, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? null : str);
    }

    public MotionCaptureVariantOptions(boolean z, boolean z2, boolean z3, String str) {
        this.showIntro = z;
        this.audioEnabled = z2;
        this.isTestEnv = z3;
        this.encryptedBiometricToken = str;
    }

    /* compiled from: MotionCaptureVariantOptions.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "getDEFAULT", "()Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MotionCaptureVariantOptions getDEFAULT() {
            return MotionCaptureVariantOptions.DEFAULT;
        }
    }
}
