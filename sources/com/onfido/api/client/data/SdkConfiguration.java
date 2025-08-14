package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: SdkConfiguration.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0087\b\u0018\u0000 I2\u00020\u0001:\fGHIJKLMNOPQRBy\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016Be\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0017J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0013HÆ\u0003Ji\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0003HÖ\u0001J\t\u0010>\u001a\u00020\u0013HÖ\u0001J&\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EHÁ\u0001¢\u0006\u0002\bFR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0019\u001a\u0004\b \u0010!R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0019\u001a\u0004\b#\u0010$R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0019\u001a\u0004\b&\u0010'R\u001e\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u0019\u001a\u0004\b)\u0010*R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0019\u001a\u0004\b,\u0010-R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u0019\u001a\u0004\b/\u00100¨\u0006S"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration;", "", "seen1", "", "validations", "Lcom/onfido/api/client/data/SdkConfiguration$Validations;", "documentCapture", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "experimentalFeatures", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "livenessCapture", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "selfieCapture", "Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "motionCapture", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "sdkFeatures", "Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;", "source", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/SdkConfiguration$Validations;Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/SdkConfiguration$Validations;Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;Ljava/lang/String;)V", "getDocumentCapture$annotations", "()V", "getDocumentCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "getExperimentalFeatures$annotations", "getExperimentalFeatures", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "getLivenessCapture$annotations", "getLivenessCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "getMotionCapture$annotations", "getMotionCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "getSdkFeatures$annotations", "getSdkFeatures", "()Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;", "getSelfieCapture$annotations", "getSelfieCapture", "()Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "getSource$annotations", "getSource", "()Ljava/lang/String;", "getValidations$annotations", "getValidations", "()Lcom/onfido/api/client/data/SdkConfiguration$Validations;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "CameraXConfiguration", "Companion", DocumentCaptureFragment.KEY_DOCUMENT_CAPTURE_FRAGMENT_TAG, "ExperimentalFeatures", "LivenessCapture", "LoggerConfiguration", "MotionCapture", "NfcConfiguration", "SdkFeatures", "SelfieCapture", "Validations", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class SdkConfiguration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String FIELD_ENABLE_DOCUMENT_SUPPORT_RULES = "enable_document_support_rules";
    public static final String FIELD_ENABLE_IN_HOUSE_ANALYTICS = "enable_in_house_analytics";
    public static final String FIELD_ENABLE_PERFORMANCE_ANALYTICS = "enable_performance_analytics";
    public static final String FIELD_ENABLE_REQUIRE_APPLICANT_CONSENTS = "enable_require_applicant_consents";
    public static final String FIELD_ENABLE_WEB_MODULE_BASED_POA = "enable_web_module_based_poa";
    public static final String FIELD_IS_PAYLOAD_SIGNING_ENABLED = "sign_upload";
    public static final String FIELD_LIVENESS_CAPTURE = "face_video_capture";
    public static final String FIELD_LOGGER_CONFIGURATION = "logger";
    public static final String FIELD_MOTION_CAPTURE = "motion_capture";
    public static final String FIELD_MOTION_VIDEO_SETTINGS = "video_settings";
    public static final String FIELD_REFACTORED_CAPTURE = "android_refactored_capture";
    public static final String FIELD_SELFIE_CAPTURE = "face_selfie_capture";
    private final DocumentCapture documentCapture;
    private final ExperimentalFeatures experimentalFeatures;
    private final LivenessCapture livenessCapture;
    private final MotionCapture motionCapture;
    private final SdkFeatures sdkFeatures;
    private final SelfieCapture selfieCapture;
    private final String source;
    private final Validations validations;

    public SdkConfiguration() {
        this((Validations) null, (DocumentCapture) null, (ExperimentalFeatures) null, (LivenessCapture) null, (SelfieCapture) null, (MotionCapture) null, (SdkFeatures) null, (String) null, 255, (DefaultConstructorMarker) null);
    }

    @SerialName("document_capture")
    public static /* synthetic */ void getDocumentCapture$annotations() {
    }

    @SerialName("experimental_features")
    public static /* synthetic */ void getExperimentalFeatures$annotations() {
    }

    @SerialName(FIELD_LIVENESS_CAPTURE)
    public static /* synthetic */ void getLivenessCapture$annotations() {
    }

    @SerialName(FIELD_MOTION_CAPTURE)
    public static /* synthetic */ void getMotionCapture$annotations() {
    }

    @SerialName("sdk_features")
    public static /* synthetic */ void getSdkFeatures$annotations() {
    }

    @SerialName(FIELD_SELFIE_CAPTURE)
    public static /* synthetic */ void getSelfieCapture$annotations() {
    }

    @SerialName("source")
    public static /* synthetic */ void getSource$annotations() {
    }

    @SerialName("validations")
    public static /* synthetic */ void getValidations$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Validations getValidations() {
        return this.validations;
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentCapture getDocumentCapture() {
        return this.documentCapture;
    }

    /* renamed from: component3, reason: from getter */
    public final ExperimentalFeatures getExperimentalFeatures() {
        return this.experimentalFeatures;
    }

    /* renamed from: component4, reason: from getter */
    public final LivenessCapture getLivenessCapture() {
        return this.livenessCapture;
    }

    /* renamed from: component5, reason: from getter */
    public final SelfieCapture getSelfieCapture() {
        return this.selfieCapture;
    }

    /* renamed from: component6, reason: from getter */
    public final MotionCapture getMotionCapture() {
        return this.motionCapture;
    }

    /* renamed from: component7, reason: from getter */
    public final SdkFeatures getSdkFeatures() {
        return this.sdkFeatures;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final SdkConfiguration copy(Validations validations, DocumentCapture documentCapture, ExperimentalFeatures experimentalFeatures, LivenessCapture livenessCapture, SelfieCapture selfieCapture, MotionCapture motionCapture, SdkFeatures sdkFeatures, String source) {
        return new SdkConfiguration(validations, documentCapture, experimentalFeatures, livenessCapture, selfieCapture, motionCapture, sdkFeatures, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SdkConfiguration)) {
            return false;
        }
        SdkConfiguration sdkConfiguration = (SdkConfiguration) other;
        return Intrinsics.areEqual(this.validations, sdkConfiguration.validations) && Intrinsics.areEqual(this.documentCapture, sdkConfiguration.documentCapture) && Intrinsics.areEqual(this.experimentalFeatures, sdkConfiguration.experimentalFeatures) && Intrinsics.areEqual(this.livenessCapture, sdkConfiguration.livenessCapture) && Intrinsics.areEqual(this.selfieCapture, sdkConfiguration.selfieCapture) && Intrinsics.areEqual(this.motionCapture, sdkConfiguration.motionCapture) && Intrinsics.areEqual(this.sdkFeatures, sdkConfiguration.sdkFeatures) && Intrinsics.areEqual(this.source, sdkConfiguration.source);
    }

    public final DocumentCapture getDocumentCapture() {
        return this.documentCapture;
    }

    public final ExperimentalFeatures getExperimentalFeatures() {
        return this.experimentalFeatures;
    }

    public final LivenessCapture getLivenessCapture() {
        return this.livenessCapture;
    }

    public final MotionCapture getMotionCapture() {
        return this.motionCapture;
    }

    public final SdkFeatures getSdkFeatures() {
        return this.sdkFeatures;
    }

    public final SelfieCapture getSelfieCapture() {
        return this.selfieCapture;
    }

    public final String getSource() {
        return this.source;
    }

    public final Validations getValidations() {
        return this.validations;
    }

    public int hashCode() {
        Validations validations = this.validations;
        int iHashCode = (validations == null ? 0 : validations.hashCode()) * 31;
        DocumentCapture documentCapture = this.documentCapture;
        int iHashCode2 = (iHashCode + (documentCapture == null ? 0 : documentCapture.hashCode())) * 31;
        ExperimentalFeatures experimentalFeatures = this.experimentalFeatures;
        int iHashCode3 = (iHashCode2 + (experimentalFeatures == null ? 0 : experimentalFeatures.hashCode())) * 31;
        LivenessCapture livenessCapture = this.livenessCapture;
        int iHashCode4 = (iHashCode3 + (livenessCapture == null ? 0 : livenessCapture.hashCode())) * 31;
        SelfieCapture selfieCapture = this.selfieCapture;
        int iHashCode5 = (iHashCode4 + (selfieCapture == null ? 0 : selfieCapture.hashCode())) * 31;
        MotionCapture motionCapture = this.motionCapture;
        int iHashCode6 = (iHashCode5 + (motionCapture == null ? 0 : motionCapture.hashCode())) * 31;
        SdkFeatures sdkFeatures = this.sdkFeatures;
        int iHashCode7 = (iHashCode6 + (sdkFeatures == null ? 0 : sdkFeatures.hashCode())) * 31;
        String str = this.source;
        return iHashCode7 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "SdkConfiguration(validations=" + this.validations + ", documentCapture=" + this.documentCapture + ", experimentalFeatures=" + this.experimentalFeatures + ", livenessCapture=" + this.livenessCapture + ", selfieCapture=" + this.selfieCapture + ", motionCapture=" + this.motionCapture + ", sdkFeatures=" + this.sdkFeatures + ", source=" + this.source + ")";
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SdkConfiguration(int i, @SerialName("validations") Validations validations, @SerialName("document_capture") DocumentCapture documentCapture, @SerialName("experimental_features") ExperimentalFeatures experimentalFeatures, @SerialName(FIELD_LIVENESS_CAPTURE) LivenessCapture livenessCapture, @SerialName(FIELD_SELFIE_CAPTURE) SelfieCapture selfieCapture, @SerialName(FIELD_MOTION_CAPTURE) MotionCapture motionCapture, @SerialName("sdk_features") SdkFeatures sdkFeatures, @SerialName("source") String str, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.validations = null;
        } else {
            this.validations = validations;
        }
        if ((i & 2) == 0) {
            this.documentCapture = null;
        } else {
            this.documentCapture = documentCapture;
        }
        if ((i & 4) == 0) {
            this.experimentalFeatures = null;
        } else {
            this.experimentalFeatures = experimentalFeatures;
        }
        if ((i & 8) == 0) {
            this.livenessCapture = null;
        } else {
            this.livenessCapture = livenessCapture;
        }
        if ((i & 16) == 0) {
            this.selfieCapture = null;
        } else {
            this.selfieCapture = selfieCapture;
        }
        if ((i & 32) == 0) {
            this.motionCapture = null;
        } else {
            this.motionCapture = motionCapture;
        }
        if ((i & 64) == 0) {
            this.sdkFeatures = null;
        } else {
            this.sdkFeatures = sdkFeatures;
        }
        if ((i & 128) == 0) {
            this.source = null;
        } else {
            this.source = str;
        }
    }

    public SdkConfiguration(Validations validations, DocumentCapture documentCapture, ExperimentalFeatures experimentalFeatures, LivenessCapture livenessCapture, SelfieCapture selfieCapture, MotionCapture motionCapture, SdkFeatures sdkFeatures, String str) {
        this.validations = validations;
        this.documentCapture = documentCapture;
        this.experimentalFeatures = experimentalFeatures;
        this.livenessCapture = livenessCapture;
        this.selfieCapture = selfieCapture;
        this.motionCapture = motionCapture;
        this.sdkFeatures = sdkFeatures;
        this.source = str;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(SdkConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.validations != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, SdkConfiguration$Validations$$serializer.INSTANCE, self.validations);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.documentCapture != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, SdkConfiguration$DocumentCapture$$serializer.INSTANCE, self.documentCapture);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.experimentalFeatures != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE, self.experimentalFeatures);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.livenessCapture != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, SdkConfiguration$LivenessCapture$$serializer.INSTANCE, self.livenessCapture);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.selfieCapture != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, SdkConfiguration$SelfieCapture$$serializer.INSTANCE, self.selfieCapture);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.motionCapture != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, SdkConfiguration$MotionCapture$$serializer.INSTANCE, self.motionCapture);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.sdkFeatures != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, SdkConfiguration$SdkFeatures$$serializer.INSTANCE, self.sdkFeatures);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 7) && self.source == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.source);
    }

    public /* synthetic */ SdkConfiguration(Validations validations, DocumentCapture documentCapture, ExperimentalFeatures experimentalFeatures, LivenessCapture livenessCapture, SelfieCapture selfieCapture, MotionCapture motionCapture, SdkFeatures sdkFeatures, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : validations, (i & 2) != 0 ? null : documentCapture, (i & 4) != 0 ? null : experimentalFeatures, (i & 8) != 0 ? null : livenessCapture, (i & 16) != 0 ? null : selfieCapture, (i & 32) != 0 ? null : motionCapture, (i & 64) != 0 ? null : sdkFeatures, (i & 128) == 0 ? str : null);
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 T2\u00020\u0001:\u0002STB§\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u0005\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000b\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u000b\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u000b\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017B\u0091\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003¢\u0006\u0002\u0010\u0018J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0010HÆ\u0003J\t\u00109\u001a\u00020\u000bHÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u000bHÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u000bHÆ\u0003J\u0095\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0003HÆ\u0001J\u0013\u0010F\u001a\u00020\u000b2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u0003HÖ\u0001J\t\u0010I\u001a\u00020JHÖ\u0001J&\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020QHÁ\u0001¢\u0006\u0002\bRR\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0012\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u001a\u001a\u0004\b\u0012\u0010!R\u001c\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001a\u001a\u0004\b\u000e\u0010!R\u001c\u0010\u0011\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u001a\u001a\u0004\b\u0011\u0010!R\u001c\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001a\u001a\u0004\b%\u0010\u001fR\u001c\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001a\u001a\u0004\b'\u0010(R\u001c\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001a\u001a\u0004\b*\u0010\u001fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001a\u001a\u0004\b,\u0010\u001cR\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001a\u001a\u0004\b.\u0010\u001fR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001a\u001a\u0004\b0\u0010\u001cR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u001a\u001a\u0004\b2\u0010\u001fR\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001a\u001a\u0004\b4\u0010!R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001a\u001a\u0004\b6\u0010\u001c¨\u0006U"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "", "seen1", "", "torchTurnOnTimeMs", "", "videoLengthMs", "videoTimeoutMs", "videoBitrate", "videoQuality", "videoRequired", "", "barcodeDetectionTimeoutMs", "maxTotalRetries", "isMrzDetectionEnabled", "nfc", "Lcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;", "isStatefulNfcReaderEnabled", "isCameraXViewPortEnabled", "imageCompressionQuality", "targetResolutionWidth", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJJJIIZJIZLcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;ZZIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(JJJIIZJIZLcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;ZZII)V", "getBarcodeDetectionTimeoutMs$annotations", "()V", "getBarcodeDetectionTimeoutMs", "()J", "getImageCompressionQuality$annotations", "getImageCompressionQuality", "()I", "isCameraXViewPortEnabled$annotations", "()Z", "isMrzDetectionEnabled$annotations", "isStatefulNfcReaderEnabled$annotations", "getMaxTotalRetries$annotations", "getMaxTotalRetries", "getNfc$annotations", "getNfc", "()Lcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;", "getTargetResolutionWidth$annotations", "getTargetResolutionWidth", "getTorchTurnOnTimeMs$annotations", "getTorchTurnOnTimeMs", "getVideoBitrate$annotations", "getVideoBitrate", "getVideoLengthMs$annotations", "getVideoLengthMs", "getVideoQuality$annotations", "getVideoQuality", "getVideoRequired$annotations", "getVideoRequired", "getVideoTimeoutMs$annotations", "getVideoTimeoutMs", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class DocumentCapture {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final DocumentCapture DEFAULT = new DocumentCapture(0L, 0L, 0L, 0, 0, false, 0L, 0, false, (NfcConfiguration) null, false, false, 0, 0, 16383, (DefaultConstructorMarker) null);
        private static final long DEFAULT_BARCODE_DETECTION_TIMEOUT_IN_MS = 5000;
        private static final long DEFAULT_DOCUMENT_RECORDING_DURATION_IN_MS = 1500;
        private static final long DEFAULT_DOCUMENT_RECORDING_TIMEOUT_IN_MS = 10000;
        private static final int DEFAULT_DOCUMENT_VIDEO_BITRATE = 3000000;
        private static final int DEFAULT_DOCUMENT_VIDEO_QUALITY = 1080;
        private static final int DEFAULT_IMAGE_COMPRESSION_QUALITY = 100;
        private static final int DEFAULT_MAX_RETRY = 2;
        private static final int DOC_CAPTURE_TARGET_RESOLUTION_WIDTH = 1920;
        private final long barcodeDetectionTimeoutMs;
        private final int imageCompressionQuality;
        private final boolean isCameraXViewPortEnabled;
        private final boolean isMrzDetectionEnabled;
        private final boolean isStatefulNfcReaderEnabled;
        private final int maxTotalRetries;
        private final NfcConfiguration nfc;
        private final int targetResolutionWidth;
        private final long torchTurnOnTimeMs;
        private final int videoBitrate;
        private final long videoLengthMs;
        private final int videoQuality;
        private final boolean videoRequired;
        private final long videoTimeoutMs;

        public DocumentCapture() {
            this(0L, 0L, 0L, 0, 0, false, 0L, 0, false, (NfcConfiguration) null, false, false, 0, 0, 16383, (DefaultConstructorMarker) null);
        }

        @SerialName("barcode_detection_timeout_ms")
        public static /* synthetic */ void getBarcodeDetectionTimeoutMs$annotations() {
        }

        @SerialName("image_compression_quality")
        public static /* synthetic */ void getImageCompressionQuality$annotations() {
        }

        @SerialName("max_total_retries")
        public static /* synthetic */ void getMaxTotalRetries$annotations() {
        }

        @SerialName("nfc")
        public static /* synthetic */ void getNfc$annotations() {
        }

        @SerialName("target_resolution_width")
        public static /* synthetic */ void getTargetResolutionWidth$annotations() {
        }

        @SerialName("torch_turn_on_time_ms")
        public static /* synthetic */ void getTorchTurnOnTimeMs$annotations() {
        }

        @SerialName("video_bitrate")
        public static /* synthetic */ void getVideoBitrate$annotations() {
        }

        @SerialName("video_length_ms")
        public static /* synthetic */ void getVideoLengthMs$annotations() {
        }

        @SerialName("video_quality")
        public static /* synthetic */ void getVideoQuality$annotations() {
        }

        @SerialName("video_required")
        public static /* synthetic */ void getVideoRequired$annotations() {
        }

        @SerialName("video_timeout_ms")
        public static /* synthetic */ void getVideoTimeoutMs$annotations() {
        }

        @SerialName("is_camerax_view_port_enabled")
        public static /* synthetic */ void isCameraXViewPortEnabled$annotations() {
        }

        @SerialName("enable_mrz_detection")
        public static /* synthetic */ void isMrzDetectionEnabled$annotations() {
        }

        @SerialName("enable_stateful_nfc_reader")
        public static /* synthetic */ void isStatefulNfcReaderEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final long getTorchTurnOnTimeMs() {
            return this.torchTurnOnTimeMs;
        }

        /* renamed from: component10, reason: from getter */
        public final NfcConfiguration getNfc() {
            return this.nfc;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getIsStatefulNfcReaderEnabled() {
            return this.isStatefulNfcReaderEnabled;
        }

        /* renamed from: component12, reason: from getter */
        public final boolean getIsCameraXViewPortEnabled() {
            return this.isCameraXViewPortEnabled;
        }

        /* renamed from: component13, reason: from getter */
        public final int getImageCompressionQuality() {
            return this.imageCompressionQuality;
        }

        /* renamed from: component14, reason: from getter */
        public final int getTargetResolutionWidth() {
            return this.targetResolutionWidth;
        }

        /* renamed from: component2, reason: from getter */
        public final long getVideoLengthMs() {
            return this.videoLengthMs;
        }

        /* renamed from: component3, reason: from getter */
        public final long getVideoTimeoutMs() {
            return this.videoTimeoutMs;
        }

        /* renamed from: component4, reason: from getter */
        public final int getVideoBitrate() {
            return this.videoBitrate;
        }

        /* renamed from: component5, reason: from getter */
        public final int getVideoQuality() {
            return this.videoQuality;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getVideoRequired() {
            return this.videoRequired;
        }

        /* renamed from: component7, reason: from getter */
        public final long getBarcodeDetectionTimeoutMs() {
            return this.barcodeDetectionTimeoutMs;
        }

        /* renamed from: component8, reason: from getter */
        public final int getMaxTotalRetries() {
            return this.maxTotalRetries;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getIsMrzDetectionEnabled() {
            return this.isMrzDetectionEnabled;
        }

        public final DocumentCapture copy(long torchTurnOnTimeMs, long videoLengthMs, long videoTimeoutMs, int videoBitrate, int videoQuality, boolean videoRequired, long barcodeDetectionTimeoutMs, int maxTotalRetries, boolean isMrzDetectionEnabled, NfcConfiguration nfc, boolean isStatefulNfcReaderEnabled, boolean isCameraXViewPortEnabled, int imageCompressionQuality, int targetResolutionWidth) {
            Intrinsics.checkNotNullParameter(nfc, "nfc");
            return new DocumentCapture(torchTurnOnTimeMs, videoLengthMs, videoTimeoutMs, videoBitrate, videoQuality, videoRequired, barcodeDetectionTimeoutMs, maxTotalRetries, isMrzDetectionEnabled, nfc, isStatefulNfcReaderEnabled, isCameraXViewPortEnabled, imageCompressionQuality, targetResolutionWidth);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DocumentCapture)) {
                return false;
            }
            DocumentCapture documentCapture = (DocumentCapture) other;
            return this.torchTurnOnTimeMs == documentCapture.torchTurnOnTimeMs && this.videoLengthMs == documentCapture.videoLengthMs && this.videoTimeoutMs == documentCapture.videoTimeoutMs && this.videoBitrate == documentCapture.videoBitrate && this.videoQuality == documentCapture.videoQuality && this.videoRequired == documentCapture.videoRequired && this.barcodeDetectionTimeoutMs == documentCapture.barcodeDetectionTimeoutMs && this.maxTotalRetries == documentCapture.maxTotalRetries && this.isMrzDetectionEnabled == documentCapture.isMrzDetectionEnabled && Intrinsics.areEqual(this.nfc, documentCapture.nfc) && this.isStatefulNfcReaderEnabled == documentCapture.isStatefulNfcReaderEnabled && this.isCameraXViewPortEnabled == documentCapture.isCameraXViewPortEnabled && this.imageCompressionQuality == documentCapture.imageCompressionQuality && this.targetResolutionWidth == documentCapture.targetResolutionWidth;
        }

        public final long getBarcodeDetectionTimeoutMs() {
            return this.barcodeDetectionTimeoutMs;
        }

        public final int getImageCompressionQuality() {
            return this.imageCompressionQuality;
        }

        public final int getMaxTotalRetries() {
            return this.maxTotalRetries;
        }

        public final NfcConfiguration getNfc() {
            return this.nfc;
        }

        public final int getTargetResolutionWidth() {
            return this.targetResolutionWidth;
        }

        public final long getTorchTurnOnTimeMs() {
            return this.torchTurnOnTimeMs;
        }

        public final int getVideoBitrate() {
            return this.videoBitrate;
        }

        public final long getVideoLengthMs() {
            return this.videoLengthMs;
        }

        public final int getVideoQuality() {
            return this.videoQuality;
        }

        public final boolean getVideoRequired() {
            return this.videoRequired;
        }

        public final long getVideoTimeoutMs() {
            return this.videoTimeoutMs;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((Long.hashCode(this.torchTurnOnTimeMs) * 31) + Long.hashCode(this.videoLengthMs)) * 31) + Long.hashCode(this.videoTimeoutMs)) * 31) + Integer.hashCode(this.videoBitrate)) * 31) + Integer.hashCode(this.videoQuality)) * 31) + Boolean.hashCode(this.videoRequired)) * 31) + Long.hashCode(this.barcodeDetectionTimeoutMs)) * 31) + Integer.hashCode(this.maxTotalRetries)) * 31) + Boolean.hashCode(this.isMrzDetectionEnabled)) * 31) + this.nfc.hashCode()) * 31) + Boolean.hashCode(this.isStatefulNfcReaderEnabled)) * 31) + Boolean.hashCode(this.isCameraXViewPortEnabled)) * 31) + Integer.hashCode(this.imageCompressionQuality)) * 31) + Integer.hashCode(this.targetResolutionWidth);
        }

        public final boolean isCameraXViewPortEnabled() {
            return this.isCameraXViewPortEnabled;
        }

        public final boolean isMrzDetectionEnabled() {
            return this.isMrzDetectionEnabled;
        }

        public final boolean isStatefulNfcReaderEnabled() {
            return this.isStatefulNfcReaderEnabled;
        }

        public String toString() {
            return "DocumentCapture(torchTurnOnTimeMs=" + this.torchTurnOnTimeMs + ", videoLengthMs=" + this.videoLengthMs + ", videoTimeoutMs=" + this.videoTimeoutMs + ", videoBitrate=" + this.videoBitrate + ", videoQuality=" + this.videoQuality + ", videoRequired=" + this.videoRequired + ", barcodeDetectionTimeoutMs=" + this.barcodeDetectionTimeoutMs + ", maxTotalRetries=" + this.maxTotalRetries + ", isMrzDetectionEnabled=" + this.isMrzDetectionEnabled + ", nfc=" + this.nfc + ", isStatefulNfcReaderEnabled=" + this.isStatefulNfcReaderEnabled + ", isCameraXViewPortEnabled=" + this.isCameraXViewPortEnabled + ", imageCompressionQuality=" + this.imageCompressionQuality + ", targetResolutionWidth=" + this.targetResolutionWidth + ")";
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ DocumentCapture(int i, @SerialName("torch_turn_on_time_ms") long j, @SerialName("video_length_ms") long j2, @SerialName("video_timeout_ms") long j3, @SerialName("video_bitrate") int i2, @SerialName("video_quality") int i3, @SerialName("video_required") boolean z, @SerialName("barcode_detection_timeout_ms") long j4, @SerialName("max_total_retries") int i4, @SerialName("enable_mrz_detection") boolean z2, @SerialName("nfc") NfcConfiguration nfcConfiguration, @SerialName("enable_stateful_nfc_reader") boolean z3, @SerialName("is_camerax_view_port_enabled") boolean z4, @SerialName("image_compression_quality") int i5, @SerialName("target_resolution_width") int i6, SerializationConstructorMarker serializationConstructorMarker) {
            this.torchTurnOnTimeMs = (i & 1) == 0 ? -1L : j;
            this.videoLengthMs = (i & 2) == 0 ? 1500L : j2;
            this.videoTimeoutMs = (i & 4) == 0 ? 10000L : j3;
            this.videoBitrate = (i & 8) == 0 ? DEFAULT_DOCUMENT_VIDEO_BITRATE : i2;
            this.videoQuality = (i & 16) == 0 ? 1080 : i3;
            if ((i & 32) == 0) {
                this.videoRequired = true;
            } else {
                this.videoRequired = z;
            }
            this.barcodeDetectionTimeoutMs = (i & 64) == 0 ? 5000L : j4;
            this.maxTotalRetries = (i & 128) == 0 ? 2 : i4;
            if ((i & 256) == 0) {
                this.isMrzDetectionEnabled = true;
            } else {
                this.isMrzDetectionEnabled = z2;
            }
            this.nfc = (i & 512) == 0 ? new NfcConfiguration(false, 0, (String) null, 0, 15, (DefaultConstructorMarker) null) : nfcConfiguration;
            this.isStatefulNfcReaderEnabled = (i & 1024) == 0 ? false : z3;
            if ((i & 2048) == 0) {
                this.isCameraXViewPortEnabled = true;
            } else {
                this.isCameraXViewPortEnabled = z4;
            }
            this.imageCompressionQuality = (i & 4096) == 0 ? 100 : i5;
            this.targetResolutionWidth = (i & 8192) == 0 ? DOC_CAPTURE_TARGET_RESOLUTION_WIDTH : i6;
        }

        public DocumentCapture(long j, long j2, long j3, int i, int i2, boolean z, long j4, int i3, boolean z2, NfcConfiguration nfc, boolean z3, boolean z4, int i4, int i5) {
            Intrinsics.checkNotNullParameter(nfc, "nfc");
            this.torchTurnOnTimeMs = j;
            this.videoLengthMs = j2;
            this.videoTimeoutMs = j3;
            this.videoBitrate = i;
            this.videoQuality = i2;
            this.videoRequired = z;
            this.barcodeDetectionTimeoutMs = j4;
            this.maxTotalRetries = i3;
            this.isMrzDetectionEnabled = z2;
            this.nfc = nfc;
            this.isStatefulNfcReaderEnabled = z3;
            this.isCameraXViewPortEnabled = z4;
            this.imageCompressionQuality = i4;
            this.targetResolutionWidth = i5;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(DocumentCapture self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.torchTurnOnTimeMs != -1) {
                output.encodeLongElement(serialDesc, 0, self.torchTurnOnTimeMs);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.videoLengthMs != 1500) {
                output.encodeLongElement(serialDesc, 1, self.videoLengthMs);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.videoTimeoutMs != 10000) {
                output.encodeLongElement(serialDesc, 2, self.videoTimeoutMs);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.videoBitrate != DEFAULT_DOCUMENT_VIDEO_BITRATE) {
                output.encodeIntElement(serialDesc, 3, self.videoBitrate);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.videoQuality != 1080) {
                output.encodeIntElement(serialDesc, 4, self.videoQuality);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || !self.videoRequired) {
                output.encodeBooleanElement(serialDesc, 5, self.videoRequired);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.barcodeDetectionTimeoutMs != 5000) {
                output.encodeLongElement(serialDesc, 6, self.barcodeDetectionTimeoutMs);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.maxTotalRetries != 2) {
                output.encodeIntElement(serialDesc, 7, self.maxTotalRetries);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || !self.isMrzDetectionEnabled) {
                output.encodeBooleanElement(serialDesc, 8, self.isMrzDetectionEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual(self.nfc, new NfcConfiguration(false, 0, (String) null, 0, 15, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 9, SdkConfiguration$NfcConfiguration$$serializer.INSTANCE, self.nfc);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 10) || self.isStatefulNfcReaderEnabled) {
                output.encodeBooleanElement(serialDesc, 10, self.isStatefulNfcReaderEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 11) || !self.isCameraXViewPortEnabled) {
                output.encodeBooleanElement(serialDesc, 11, self.isCameraXViewPortEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 12) || self.imageCompressionQuality != 100) {
                output.encodeIntElement(serialDesc, 12, self.imageCompressionQuality);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 13) && self.targetResolutionWidth == DOC_CAPTURE_TARGET_RESOLUTION_WIDTH) {
                return;
            }
            output.encodeIntElement(serialDesc, 13, self.targetResolutionWidth);
        }

        public /* synthetic */ DocumentCapture(long j, long j2, long j3, int i, int i2, boolean z, long j4, int i3, boolean z2, NfcConfiguration nfcConfiguration, boolean z3, boolean z4, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
            this((i6 & 1) != 0 ? -1L : j, (i6 & 2) != 0 ? 1500L : j2, (i6 & 4) != 0 ? 10000L : j3, (i6 & 8) != 0 ? DEFAULT_DOCUMENT_VIDEO_BITRATE : i, (i6 & 16) != 0 ? 1080 : i2, (i6 & 32) != 0 ? true : z, (i6 & 64) != 0 ? 5000L : j4, (i6 & 128) != 0 ? 2 : i3, (i6 & 256) != 0 ? true : z2, (i6 & 512) != 0 ? new NfcConfiguration(false, 0, (String) null, 0, 15, (DefaultConstructorMarker) null) : nfcConfiguration, (i6 & 1024) != 0 ? false : z3, (i6 & 2048) != 0 ? true : z4, (i6 & 4096) != 0 ? 100 : i4, (i6 & 8192) != 0 ? DOC_CAPTURE_TARGET_RESOLUTION_WIDTH : i5);
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012HÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "getDEFAULT", "()Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "DEFAULT_BARCODE_DETECTION_TIMEOUT_IN_MS", "", "DEFAULT_DOCUMENT_RECORDING_DURATION_IN_MS", "DEFAULT_DOCUMENT_RECORDING_TIMEOUT_IN_MS", "DEFAULT_DOCUMENT_VIDEO_BITRATE", "", "DEFAULT_DOCUMENT_VIDEO_QUALITY", "DEFAULT_IMAGE_COMPRESSION_QUALITY", "DEFAULT_MAX_RETRY", "DOC_CAPTURE_TARGET_RESOLUTION_WIDTH", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<DocumentCapture> serializer() {
                return SdkConfiguration$DocumentCapture$$serializer.INSTANCE;
            }

            public final DocumentCapture getDEFAULT() {
                return DocumentCapture.DEFAULT;
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 42\u00020\u0001:\u000234B_\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eBK\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001J&\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201HÁ\u0001¢\u0006\u0002\b2R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000b\u0010\u0012R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0011\u001a\u0004\b\n\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0012R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0012R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0012R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0012¨\u00065"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "", "seen1", "", "shouldInterruptVideoRecoverableError", "", "shouldInterruptImageRecoverableError", "shouldRemovePreviewVideoRecording", "shouldUseResolutionStrategy", "shouldRetryViewPortFailure", "isViewPortRequired", "isFallbackEnabled", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZZZZZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZZZZZZ)V", "isFallbackEnabled$annotations", "()V", "()Z", "isViewPortRequired$annotations", "getShouldInterruptImageRecoverableError$annotations", "getShouldInterruptImageRecoverableError", "getShouldInterruptVideoRecoverableError$annotations", "getShouldInterruptVideoRecoverableError", "getShouldRemovePreviewVideoRecording$annotations", "getShouldRemovePreviewVideoRecording", "getShouldRetryViewPortFailure$annotations", "getShouldRetryViewPortFailure", "getShouldUseResolutionStrategy$annotations", "getShouldUseResolutionStrategy", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CameraXConfiguration {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean isFallbackEnabled;
        private final boolean isViewPortRequired;
        private final boolean shouldInterruptImageRecoverableError;
        private final boolean shouldInterruptVideoRecoverableError;
        private final boolean shouldRemovePreviewVideoRecording;
        private final boolean shouldRetryViewPortFailure;
        private final boolean shouldUseResolutionStrategy;

        public CameraXConfiguration() {
            this(false, false, false, false, false, false, false, 127, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ CameraXConfiguration copy$default(CameraXConfiguration cameraXConfiguration, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, Object obj) {
            if ((i & 1) != 0) {
                z = cameraXConfiguration.shouldInterruptVideoRecoverableError;
            }
            if ((i & 2) != 0) {
                z2 = cameraXConfiguration.shouldInterruptImageRecoverableError;
            }
            boolean z8 = z2;
            if ((i & 4) != 0) {
                z3 = cameraXConfiguration.shouldRemovePreviewVideoRecording;
            }
            boolean z9 = z3;
            if ((i & 8) != 0) {
                z4 = cameraXConfiguration.shouldUseResolutionStrategy;
            }
            boolean z10 = z4;
            if ((i & 16) != 0) {
                z5 = cameraXConfiguration.shouldRetryViewPortFailure;
            }
            boolean z11 = z5;
            if ((i & 32) != 0) {
                z6 = cameraXConfiguration.isViewPortRequired;
            }
            boolean z12 = z6;
            if ((i & 64) != 0) {
                z7 = cameraXConfiguration.isFallbackEnabled;
            }
            return cameraXConfiguration.copy(z, z8, z9, z10, z11, z12, z7);
        }

        @SerialName("should_interrupt_image_recoverable_error")
        public static /* synthetic */ void getShouldInterruptImageRecoverableError$annotations() {
        }

        @SerialName("should_interrupt_video_recoverable_error")
        public static /* synthetic */ void getShouldInterruptVideoRecoverableError$annotations() {
        }

        @SerialName("should_remove_preview_video_recording")
        public static /* synthetic */ void getShouldRemovePreviewVideoRecording$annotations() {
        }

        @SerialName("should_retry_view_port_failure")
        public static /* synthetic */ void getShouldRetryViewPortFailure$annotations() {
        }

        @SerialName("should_use_resolution_strategy")
        public static /* synthetic */ void getShouldUseResolutionStrategy$annotations() {
        }

        @SerialName("is_fallback_enabled")
        public static /* synthetic */ void isFallbackEnabled$annotations() {
        }

        @SerialName("is_view_port_required")
        public static /* synthetic */ void isViewPortRequired$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getShouldInterruptVideoRecoverableError() {
            return this.shouldInterruptVideoRecoverableError;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShouldInterruptImageRecoverableError() {
            return this.shouldInterruptImageRecoverableError;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getShouldRemovePreviewVideoRecording() {
            return this.shouldRemovePreviewVideoRecording;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getShouldUseResolutionStrategy() {
            return this.shouldUseResolutionStrategy;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getShouldRetryViewPortFailure() {
            return this.shouldRetryViewPortFailure;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsViewPortRequired() {
            return this.isViewPortRequired;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getIsFallbackEnabled() {
            return this.isFallbackEnabled;
        }

        public final CameraXConfiguration copy(boolean shouldInterruptVideoRecoverableError, boolean shouldInterruptImageRecoverableError, boolean shouldRemovePreviewVideoRecording, boolean shouldUseResolutionStrategy, boolean shouldRetryViewPortFailure, boolean isViewPortRequired, boolean isFallbackEnabled) {
            return new CameraXConfiguration(shouldInterruptVideoRecoverableError, shouldInterruptImageRecoverableError, shouldRemovePreviewVideoRecording, shouldUseResolutionStrategy, shouldRetryViewPortFailure, isViewPortRequired, isFallbackEnabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CameraXConfiguration)) {
                return false;
            }
            CameraXConfiguration cameraXConfiguration = (CameraXConfiguration) other;
            return this.shouldInterruptVideoRecoverableError == cameraXConfiguration.shouldInterruptVideoRecoverableError && this.shouldInterruptImageRecoverableError == cameraXConfiguration.shouldInterruptImageRecoverableError && this.shouldRemovePreviewVideoRecording == cameraXConfiguration.shouldRemovePreviewVideoRecording && this.shouldUseResolutionStrategy == cameraXConfiguration.shouldUseResolutionStrategy && this.shouldRetryViewPortFailure == cameraXConfiguration.shouldRetryViewPortFailure && this.isViewPortRequired == cameraXConfiguration.isViewPortRequired && this.isFallbackEnabled == cameraXConfiguration.isFallbackEnabled;
        }

        public final boolean getShouldInterruptImageRecoverableError() {
            return this.shouldInterruptImageRecoverableError;
        }

        public final boolean getShouldInterruptVideoRecoverableError() {
            return this.shouldInterruptVideoRecoverableError;
        }

        public final boolean getShouldRemovePreviewVideoRecording() {
            return this.shouldRemovePreviewVideoRecording;
        }

        public final boolean getShouldRetryViewPortFailure() {
            return this.shouldRetryViewPortFailure;
        }

        public final boolean getShouldUseResolutionStrategy() {
            return this.shouldUseResolutionStrategy;
        }

        public int hashCode() {
            return (((((((((((Boolean.hashCode(this.shouldInterruptVideoRecoverableError) * 31) + Boolean.hashCode(this.shouldInterruptImageRecoverableError)) * 31) + Boolean.hashCode(this.shouldRemovePreviewVideoRecording)) * 31) + Boolean.hashCode(this.shouldUseResolutionStrategy)) * 31) + Boolean.hashCode(this.shouldRetryViewPortFailure)) * 31) + Boolean.hashCode(this.isViewPortRequired)) * 31) + Boolean.hashCode(this.isFallbackEnabled);
        }

        public final boolean isFallbackEnabled() {
            return this.isFallbackEnabled;
        }

        public final boolean isViewPortRequired() {
            return this.isViewPortRequired;
        }

        public String toString() {
            return "CameraXConfiguration(shouldInterruptVideoRecoverableError=" + this.shouldInterruptVideoRecoverableError + ", shouldInterruptImageRecoverableError=" + this.shouldInterruptImageRecoverableError + ", shouldRemovePreviewVideoRecording=" + this.shouldRemovePreviewVideoRecording + ", shouldUseResolutionStrategy=" + this.shouldUseResolutionStrategy + ", shouldRetryViewPortFailure=" + this.shouldRetryViewPortFailure + ", isViewPortRequired=" + this.isViewPortRequired + ", isFallbackEnabled=" + this.isFallbackEnabled + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CameraXConfiguration> serializer() {
                return SdkConfiguration$CameraXConfiguration$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ CameraXConfiguration(int i, @SerialName("should_interrupt_video_recoverable_error") boolean z, @SerialName("should_interrupt_image_recoverable_error") boolean z2, @SerialName("should_remove_preview_video_recording") boolean z3, @SerialName("should_use_resolution_strategy") boolean z4, @SerialName("should_retry_view_port_failure") boolean z5, @SerialName("is_view_port_required") boolean z6, @SerialName("is_fallback_enabled") boolean z7, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.shouldInterruptVideoRecoverableError = true;
            } else {
                this.shouldInterruptVideoRecoverableError = z;
            }
            if ((i & 2) == 0) {
                this.shouldInterruptImageRecoverableError = true;
            } else {
                this.shouldInterruptImageRecoverableError = z2;
            }
            if ((i & 4) == 0) {
                this.shouldRemovePreviewVideoRecording = true;
            } else {
                this.shouldRemovePreviewVideoRecording = z3;
            }
            if ((i & 8) == 0) {
                this.shouldUseResolutionStrategy = true;
            } else {
                this.shouldUseResolutionStrategy = z4;
            }
            if ((i & 16) == 0) {
                this.shouldRetryViewPortFailure = false;
            } else {
                this.shouldRetryViewPortFailure = z5;
            }
            if ((i & 32) == 0) {
                this.isViewPortRequired = false;
            } else {
                this.isViewPortRequired = z6;
            }
            if ((i & 64) == 0) {
                this.isFallbackEnabled = true;
            } else {
                this.isFallbackEnabled = z7;
            }
        }

        public CameraXConfiguration(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
            this.shouldInterruptVideoRecoverableError = z;
            this.shouldInterruptImageRecoverableError = z2;
            this.shouldRemovePreviewVideoRecording = z3;
            this.shouldUseResolutionStrategy = z4;
            this.shouldRetryViewPortFailure = z5;
            this.isViewPortRequired = z6;
            this.isFallbackEnabled = z7;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(CameraXConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !self.shouldInterruptVideoRecoverableError) {
                output.encodeBooleanElement(serialDesc, 0, self.shouldInterruptVideoRecoverableError);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !self.shouldInterruptImageRecoverableError) {
                output.encodeBooleanElement(serialDesc, 1, self.shouldInterruptImageRecoverableError);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !self.shouldRemovePreviewVideoRecording) {
                output.encodeBooleanElement(serialDesc, 2, self.shouldRemovePreviewVideoRecording);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || !self.shouldUseResolutionStrategy) {
                output.encodeBooleanElement(serialDesc, 3, self.shouldUseResolutionStrategy);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.shouldRetryViewPortFailure) {
                output.encodeBooleanElement(serialDesc, 4, self.shouldRetryViewPortFailure);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.isViewPortRequired) {
                output.encodeBooleanElement(serialDesc, 5, self.isViewPortRequired);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 6) && self.isFallbackEnabled) {
                return;
            }
            output.encodeBooleanElement(serialDesc, 6, self.isFallbackEnabled);
        }

        public /* synthetic */ CameraXConfiguration(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? true : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? true : z7);
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+BC\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB-\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J1\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\bHÖ\u0001J&\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(HÁ\u0001¢\u0006\u0002\b)R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0004\u0010\u0013R\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0016¨\u0006,"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;", "", "seen1", "", "isCanEntryScreenEnabled", "", "nfcScanTagTimeoutMs", "animationVersion", "", "maxRetries", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZILjava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZILjava/lang/String;I)V", "getAnimationVersion$annotations", "()V", "getAnimationVersion", "()Ljava/lang/String;", "isCanEntryScreenEnabled$annotations", "()Z", "getMaxRetries$annotations", "getMaxRetries", "()I", "getNfcScanTagTimeoutMs$annotations", "getNfcScanTagTimeoutMs", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class NfcConfiguration {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String animationVersion;
        private final boolean isCanEntryScreenEnabled;
        private final int maxRetries;
        private final int nfcScanTagTimeoutMs;

        public NfcConfiguration() {
            this(false, 0, (String) null, 0, 15, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ NfcConfiguration copy$default(NfcConfiguration nfcConfiguration, boolean z, int i, String str, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                z = nfcConfiguration.isCanEntryScreenEnabled;
            }
            if ((i3 & 2) != 0) {
                i = nfcConfiguration.nfcScanTagTimeoutMs;
            }
            if ((i3 & 4) != 0) {
                str = nfcConfiguration.animationVersion;
            }
            if ((i3 & 8) != 0) {
                i2 = nfcConfiguration.maxRetries;
            }
            return nfcConfiguration.copy(z, i, str, i2);
        }

        @SerialName("nfc_animation_version")
        public static /* synthetic */ void getAnimationVersion$annotations() {
        }

        @SerialName("max_retries")
        public static /* synthetic */ void getMaxRetries$annotations() {
        }

        @SerialName("nfc_scan_tag_timeout_ms")
        public static /* synthetic */ void getNfcScanTagTimeoutMs$annotations() {
        }

        @SerialName("enable_can_entry_screen")
        public static /* synthetic */ void isCanEntryScreenEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsCanEntryScreenEnabled() {
            return this.isCanEntryScreenEnabled;
        }

        /* renamed from: component2, reason: from getter */
        public final int getNfcScanTagTimeoutMs() {
            return this.nfcScanTagTimeoutMs;
        }

        /* renamed from: component3, reason: from getter */
        public final String getAnimationVersion() {
            return this.animationVersion;
        }

        /* renamed from: component4, reason: from getter */
        public final int getMaxRetries() {
            return this.maxRetries;
        }

        public final NfcConfiguration copy(boolean isCanEntryScreenEnabled, int nfcScanTagTimeoutMs, String animationVersion, int maxRetries) {
            Intrinsics.checkNotNullParameter(animationVersion, "animationVersion");
            return new NfcConfiguration(isCanEntryScreenEnabled, nfcScanTagTimeoutMs, animationVersion, maxRetries);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NfcConfiguration)) {
                return false;
            }
            NfcConfiguration nfcConfiguration = (NfcConfiguration) other;
            return this.isCanEntryScreenEnabled == nfcConfiguration.isCanEntryScreenEnabled && this.nfcScanTagTimeoutMs == nfcConfiguration.nfcScanTagTimeoutMs && Intrinsics.areEqual(this.animationVersion, nfcConfiguration.animationVersion) && this.maxRetries == nfcConfiguration.maxRetries;
        }

        public final String getAnimationVersion() {
            return this.animationVersion;
        }

        public final int getMaxRetries() {
            return this.maxRetries;
        }

        public final int getNfcScanTagTimeoutMs() {
            return this.nfcScanTagTimeoutMs;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.isCanEntryScreenEnabled) * 31) + Integer.hashCode(this.nfcScanTagTimeoutMs)) * 31) + this.animationVersion.hashCode()) * 31) + Integer.hashCode(this.maxRetries);
        }

        public final boolean isCanEntryScreenEnabled() {
            return this.isCanEntryScreenEnabled;
        }

        public String toString() {
            return "NfcConfiguration(isCanEntryScreenEnabled=" + this.isCanEntryScreenEnabled + ", nfcScanTagTimeoutMs=" + this.nfcScanTagTimeoutMs + ", animationVersion=" + this.animationVersion + ", maxRetries=" + this.maxRetries + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$NfcConfiguration;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<NfcConfiguration> serializer() {
                return SdkConfiguration$NfcConfiguration$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ NfcConfiguration(int i, @SerialName("enable_can_entry_screen") boolean z, @SerialName("nfc_scan_tag_timeout_ms") int i2, @SerialName("nfc_animation_version") String str, @SerialName("max_retries") int i3, SerializationConstructorMarker serializationConstructorMarker) {
            this.isCanEntryScreenEnabled = (i & 1) == 0 ? false : z;
            if ((i & 2) == 0) {
                this.nfcScanTagTimeoutMs = 5000;
            } else {
                this.nfcScanTagTimeoutMs = i2;
            }
            if ((i & 4) == 0) {
                this.animationVersion = "v4";
            } else {
                this.animationVersion = str;
            }
            if ((i & 8) == 0) {
                this.maxRetries = 3;
            } else {
                this.maxRetries = i3;
            }
        }

        public NfcConfiguration(boolean z, int i, String animationVersion, int i2) {
            Intrinsics.checkNotNullParameter(animationVersion, "animationVersion");
            this.isCanEntryScreenEnabled = z;
            this.nfcScanTagTimeoutMs = i;
            this.animationVersion = animationVersion;
            this.maxRetries = i2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(NfcConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.isCanEntryScreenEnabled) {
                output.encodeBooleanElement(serialDesc, 0, self.isCanEntryScreenEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.nfcScanTagTimeoutMs != 5000) {
                output.encodeIntElement(serialDesc, 1, self.nfcScanTagTimeoutMs);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.animationVersion, "v4")) {
                output.encodeStringElement(serialDesc, 2, self.animationVersion);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.maxRetries == 3) {
                return;
            }
            output.encodeIntElement(serialDesc, 3, self.maxRetries);
        }

        public /* synthetic */ NfcConfiguration(boolean z, int i, String str, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? 5000 : i, (i3 & 4) != 0 ? "v4" : str, (i3 & 8) != 0 ? 3 : i2);
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations;", "", "seen1", "", "onDevice", "Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;)V", "getOnDevice$annotations", "()V", "getOnDevice", "()Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "OnDevice", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Validations {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final OnDevice onDevice;

        public static /* synthetic */ Validations copy$default(Validations validations, OnDevice onDevice, int i, Object obj) {
            if ((i & 1) != 0) {
                onDevice = validations.onDevice;
            }
            return validations.copy(onDevice);
        }

        @SerialName("on_device")
        public static /* synthetic */ void getOnDevice$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final OnDevice getOnDevice() {
            return this.onDevice;
        }

        public final Validations copy(OnDevice onDevice) {
            return new Validations(onDevice);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Validations) && Intrinsics.areEqual(this.onDevice, ((Validations) other).onDevice);
        }

        public final OnDevice getOnDevice() {
            return this.onDevice;
        }

        public int hashCode() {
            OnDevice onDevice = this.onDevice;
            if (onDevice == null) {
                return 0;
            }
            return onDevice.hashCode();
        }

        public String toString() {
            return "Validations(onDevice=" + this.onDevice + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$Validations;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Validations> serializer() {
                return SdkConfiguration$Validations$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Validations(int i, @SerialName("on_device") OnDevice onDevice, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SdkConfiguration$Validations$$serializer.INSTANCE.getDescriptor());
            }
            this.onDevice = onDevice;
        }

        public Validations(OnDevice onDevice) {
            this.onDevice = onDevice;
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;", "", "seen1", "", "blur", "Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;)V", "getBlur$annotations", "()V", "getBlur", "()Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "ValidationType", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class OnDevice {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final ValidationType blur;

            public static /* synthetic */ OnDevice copy$default(OnDevice onDevice, ValidationType validationType, int i, Object obj) {
                if ((i & 1) != 0) {
                    validationType = onDevice.blur;
                }
                return onDevice.copy(validationType);
            }

            @SerialName("blur")
            public static /* synthetic */ void getBlur$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final ValidationType getBlur() {
                return this.blur;
            }

            public final OnDevice copy(ValidationType blur) {
                return new OnDevice(blur);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof OnDevice) && Intrinsics.areEqual(this.blur, ((OnDevice) other).blur);
            }

            public final ValidationType getBlur() {
                return this.blur;
            }

            public int hashCode() {
                ValidationType validationType = this.blur;
                if (validationType == null) {
                    return 0;
                }
                return validationType.hashCode();
            }

            public String toString() {
                return "OnDevice(blur=" + this.blur + ")";
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<OnDevice> serializer() {
                    return SdkConfiguration$Validations$OnDevice$$serializer.INSTANCE;
                }
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ OnDevice(int i, @SerialName("blur") ValidationType validationType, SerializationConstructorMarker serializationConstructorMarker) {
                if (1 != (i & 1)) {
                    PluginExceptionsKt.throwMissingFieldException(i, 1, SdkConfiguration$Validations$OnDevice$$serializer.INSTANCE.getDescriptor());
                }
                this.blur = validationType;
            }

            public OnDevice(ValidationType validationType) {
                this.blur = validationType;
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;", "", "seen1", "", "maxTotalRetries", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(I)V", "getMaxTotalRetries$annotations", "()V", "getMaxTotalRetries", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            @Serializable
            public static final /* data */ class ValidationType {

                /* renamed from: Companion, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);
                private final int maxTotalRetries;

                public static /* synthetic */ ValidationType copy$default(ValidationType validationType, int i, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        i = validationType.maxTotalRetries;
                    }
                    return validationType.copy(i);
                }

                @SerialName("max_total_retries")
                public static /* synthetic */ void getMaxTotalRetries$annotations() {
                }

                /* renamed from: component1, reason: from getter */
                public final int getMaxTotalRetries() {
                    return this.maxTotalRetries;
                }

                public final ValidationType copy(int maxTotalRetries) {
                    return new ValidationType(maxTotalRetries);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof ValidationType) && this.maxTotalRetries == ((ValidationType) other).maxTotalRetries;
                }

                public final int getMaxTotalRetries() {
                    return this.maxTotalRetries;
                }

                public int hashCode() {
                    return Integer.hashCode(this.maxTotalRetries);
                }

                public String toString() {
                    return "ValidationType(maxTotalRetries=" + this.maxTotalRetries + ")";
                }

                /* compiled from: SdkConfiguration.kt */
                @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$Validations$OnDevice$ValidationType;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }

                    public final KSerializer<ValidationType> serializer() {
                        return SdkConfiguration$Validations$OnDevice$ValidationType$$serializer.INSTANCE;
                    }
                }

                public ValidationType(int i) {
                    this.maxTotalRetries = i;
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
                public /* synthetic */ ValidationType(int i, @SerialName("max_total_retries") int i2, SerializationConstructorMarker serializationConstructorMarker) {
                    if (1 != (i & 1)) {
                        PluginExceptionsKt.throwMissingFieldException(i, 1, SdkConfiguration$Validations$OnDevice$ValidationType$$serializer.INSTANCE.getDescriptor());
                    }
                    this.maxTotalRetries = i2;
                }
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b?\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\b\u0018\u0000 k2\u00020\u0001:\u0005jklmnB\u0087\u0002\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0001\u0010\f\u001a\u00020\u0005\u0012\b\b\u0001\u0010\r\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0001\u0010\u0018\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0019\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\b\b\u0001\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u001d\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\b\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"Bç\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f¢\u0006\u0002\u0010#J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0017HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u001bHÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\u001fHÆ\u0003J\t\u0010U\u001a\u00020\bHÆ\u0003J\t\u0010V\u001a\u00020\u0005HÆ\u0003J\t\u0010W\u001a\u00020\u0005HÆ\u0003J\t\u0010X\u001a\u00020\u0005HÆ\u0003J\t\u0010Y\u001a\u00020\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u0005HÆ\u0003Jï\u0001\u0010\\\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001fHÆ\u0001J\u0013\u0010]\u001a\u00020\u00052\b\u0010^\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010_\u001a\u00020\u0003HÖ\u0001J\t\u0010`\u001a\u00020aHÖ\u0001J&\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020\u00002\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020hHÁ\u0001¢\u0006\u0002\biR\u001c\u0010\u001a\u001a\u00020\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001c\u0010\u001e\u001a\u00020\u001f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010%\u001a\u0004\b)\u0010*R\u001c\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010%\u001a\u0004\b\u0015\u0010,R\u001c\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010%\u001a\u0004\b\r\u0010,R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010%\u001a\u0004\b\f\u0010,R\u001c\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010%\u001a\u0004\b\u000f\u0010,R\u001c\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010%\u001a\u0004\b\u0012\u0010,R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010%\u001a\u0004\b\u000b\u0010,R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010%\u001a\u0004\b\u0004\u0010,R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010%\u001a\u0004\b\u0006\u0010,R\u001c\u0010\u0018\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010%\u001a\u0004\b\u0018\u0010,R\u001c\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010%\u001a\u0004\b\u0010\u0010,R\u001c\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b6\u0010%\u001a\u0004\b\u0013\u0010,R\u001c\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u0010%\u001a\u0004\b\u0014\u0010,R\u001c\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b8\u0010%\u001a\u0004\b\u0011\u0010,R\u001c\u0010\u001c\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010%\u001a\u0004\b\u001c\u0010,R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b:\u0010%\u001a\u0004\b\n\u0010,R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u0010%\u001a\u0004\b\t\u0010,R\u001c\u0010\u001d\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b<\u0010%\u001a\u0004\b\u001d\u0010,R\u001c\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b=\u0010%\u001a\u0004\b\u000e\u0010,R\u001c\u0010\u0019\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b>\u0010%\u001a\u0004\b\u0019\u0010,R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u0010%\u001a\u0004\b@\u0010AR\u001c\u0010\u0016\u001a\u00020\u00178\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bB\u0010%\u001a\u0004\bC\u0010D¨\u0006o"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "", "seen1", "", "isEnableIqs", "", "isEnableMultiFrameFeature", "motionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "isMotionTensorFlowLiteEnabled", "isMotionCameraXEnabled", "isEnableCameraX", "isCameraxStreamSharingEnabled", "isCameraXHighQualityModeEnabled", "isResolutionImprovementsEnabled", "isCutoffImprovementsEnabled", "isFocusImprovementsEnabled", "isIncreasedCompressionQualityEnabled", "isDocumentCropDisabled", "isFourByThreeEnabled", "isGenericMrzValidatorEnabled", "isAutoFlashModeEnabled", "waitingScreens", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "isEnvironmentIntegrityCheckEnabled", "isStudioUserFlowExitEnabled", "cameraXConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "isMediaCallbackCroppingDisabled", "isOnDeviceMRZExtractionEnabled", "documentDetectionExperiment", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;ZZZZZZZZZZZZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;ZZLcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;ZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;ZZZZZZZZZZZZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;ZZLcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;ZZLcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;)V", "getCameraXConfiguration$annotations", "()V", "getCameraXConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "getDocumentDetectionExperiment$annotations", "getDocumentDetectionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "isAutoFlashModeEnabled$annotations", "()Z", "isCameraXHighQualityModeEnabled$annotations", "isCameraxStreamSharingEnabled$annotations", "isCutoffImprovementsEnabled$annotations", "isDocumentCropDisabled$annotations", "isEnableCameraX$annotations", "isEnableIqs$annotations", "isEnableMultiFrameFeature$annotations", "isEnvironmentIntegrityCheckEnabled$annotations", "isFocusImprovementsEnabled$annotations", "isFourByThreeEnabled$annotations", "isGenericMrzValidatorEnabled$annotations", "isIncreasedCompressionQualityEnabled$annotations", "isMediaCallbackCroppingDisabled$annotations", "isMotionCameraXEnabled$annotations", "isMotionTensorFlowLiteEnabled$annotations", "isOnDeviceMRZExtractionEnabled$annotations", "isResolutionImprovementsEnabled$annotations", "isStudioUserFlowExitEnabled$annotations", "getMotionExperiment$annotations", "getMotionExperiment", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "getWaitingScreens$annotations", "getWaitingScreens", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "DocumentDetectionExperiment", "MotionExperiment", "WaitingScreens", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ExperimentalFeatures {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final CameraXConfiguration cameraXConfiguration;
        private final DocumentDetectionExperiment documentDetectionExperiment;
        private final boolean isAutoFlashModeEnabled;
        private final boolean isCameraXHighQualityModeEnabled;
        private final boolean isCameraxStreamSharingEnabled;
        private final boolean isCutoffImprovementsEnabled;
        private final boolean isDocumentCropDisabled;
        private final boolean isEnableCameraX;
        private final boolean isEnableIqs;
        private final boolean isEnableMultiFrameFeature;
        private final boolean isEnvironmentIntegrityCheckEnabled;
        private final boolean isFocusImprovementsEnabled;
        private final boolean isFourByThreeEnabled;
        private final boolean isGenericMrzValidatorEnabled;
        private final boolean isIncreasedCompressionQualityEnabled;
        private final boolean isMediaCallbackCroppingDisabled;
        private final boolean isMotionCameraXEnabled;
        private final boolean isMotionTensorFlowLiteEnabled;
        private final boolean isOnDeviceMRZExtractionEnabled;
        private final boolean isResolutionImprovementsEnabled;
        private final boolean isStudioUserFlowExitEnabled;
        private final MotionExperiment motionExperiment;
        private final WaitingScreens waitingScreens;

        @SerialName("camerax_configuration")
        public static /* synthetic */ void getCameraXConfiguration$annotations() {
        }

        @SerialName(IQSUploadErrorParser.DOCUMENT_DETECTION_ERROR_KEY)
        public static /* synthetic */ void getDocumentDetectionExperiment$annotations() {
        }

        @SerialName("motion_experiment")
        public static /* synthetic */ void getMotionExperiment$annotations() {
        }

        @SerialName("waiting_screens")
        public static /* synthetic */ void getWaitingScreens$annotations() {
        }

        @SerialName("enable_auto_flash_mode")
        public static /* synthetic */ void isAutoFlashModeEnabled$annotations() {
        }

        @SerialName("enable_camerax_high_quality")
        public static /* synthetic */ void isCameraXHighQualityModeEnabled$annotations() {
        }

        @SerialName("enable_camerax_stream_sharing")
        public static /* synthetic */ void isCameraxStreamSharingEnabled$annotations() {
        }

        @SerialName("enable_cutoff_improvements")
        public static /* synthetic */ void isCutoffImprovementsEnabled$annotations() {
        }

        @SerialName("disable_document_crop")
        public static /* synthetic */ void isDocumentCropDisabled$annotations() {
        }

        @SerialName("enable_camerax")
        public static /* synthetic */ void isEnableCameraX$annotations() {
        }

        @SerialName("enable_image_quality_service")
        public static /* synthetic */ void isEnableIqs$annotations() {
        }

        @SerialName("enable_multi_frame_capture")
        public static /* synthetic */ void isEnableMultiFrameFeature$annotations() {
        }

        @SerialName("android_enable_environment_integrity_check")
        public static /* synthetic */ void isEnvironmentIntegrityCheckEnabled$annotations() {
        }

        @SerialName("enable_focus_improvements")
        public static /* synthetic */ void isFocusImprovementsEnabled$annotations() {
        }

        @SerialName("enable_four_three_aspect_ratio")
        public static /* synthetic */ void isFourByThreeEnabled$annotations() {
        }

        @SerialName("enable_generic_mrz_validator")
        public static /* synthetic */ void isGenericMrzValidatorEnabled$annotations() {
        }

        @SerialName("enable_increased_compression_quality")
        public static /* synthetic */ void isIncreasedCompressionQualityEnabled$annotations() {
        }

        @SerialName("disable_media_callback_cropping")
        public static /* synthetic */ void isMediaCallbackCroppingDisabled$annotations() {
        }

        @SerialName("android_motion_camerax_enabled")
        public static /* synthetic */ void isMotionCameraXEnabled$annotations() {
        }

        @SerialName("android_motion_tensorflow_lite_enabled")
        public static /* synthetic */ void isMotionTensorFlowLiteEnabled$annotations() {
        }

        @SerialName("enable_on_device_mrz_extraction")
        public static /* synthetic */ void isOnDeviceMRZExtractionEnabled$annotations() {
        }

        @SerialName("enable_optimal_resolution_improvements")
        public static /* synthetic */ void isResolutionImprovementsEnabled$annotations() {
        }

        @SerialName("enable_studio_user_flow_exit")
        public static /* synthetic */ void isStudioUserFlowExitEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsEnableIqs() {
            return this.isEnableIqs;
        }

        /* renamed from: component10, reason: from getter */
        public final boolean getIsCutoffImprovementsEnabled() {
            return this.isCutoffImprovementsEnabled;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getIsFocusImprovementsEnabled() {
            return this.isFocusImprovementsEnabled;
        }

        /* renamed from: component12, reason: from getter */
        public final boolean getIsIncreasedCompressionQualityEnabled() {
            return this.isIncreasedCompressionQualityEnabled;
        }

        /* renamed from: component13, reason: from getter */
        public final boolean getIsDocumentCropDisabled() {
            return this.isDocumentCropDisabled;
        }

        /* renamed from: component14, reason: from getter */
        public final boolean getIsFourByThreeEnabled() {
            return this.isFourByThreeEnabled;
        }

        /* renamed from: component15, reason: from getter */
        public final boolean getIsGenericMrzValidatorEnabled() {
            return this.isGenericMrzValidatorEnabled;
        }

        /* renamed from: component16, reason: from getter */
        public final boolean getIsAutoFlashModeEnabled() {
            return this.isAutoFlashModeEnabled;
        }

        /* renamed from: component17, reason: from getter */
        public final WaitingScreens getWaitingScreens() {
            return this.waitingScreens;
        }

        /* renamed from: component18, reason: from getter */
        public final boolean getIsEnvironmentIntegrityCheckEnabled() {
            return this.isEnvironmentIntegrityCheckEnabled;
        }

        /* renamed from: component19, reason: from getter */
        public final boolean getIsStudioUserFlowExitEnabled() {
            return this.isStudioUserFlowExitEnabled;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsEnableMultiFrameFeature() {
            return this.isEnableMultiFrameFeature;
        }

        /* renamed from: component20, reason: from getter */
        public final CameraXConfiguration getCameraXConfiguration() {
            return this.cameraXConfiguration;
        }

        /* renamed from: component21, reason: from getter */
        public final boolean getIsMediaCallbackCroppingDisabled() {
            return this.isMediaCallbackCroppingDisabled;
        }

        /* renamed from: component22, reason: from getter */
        public final boolean getIsOnDeviceMRZExtractionEnabled() {
            return this.isOnDeviceMRZExtractionEnabled;
        }

        /* renamed from: component23, reason: from getter */
        public final DocumentDetectionExperiment getDocumentDetectionExperiment() {
            return this.documentDetectionExperiment;
        }

        /* renamed from: component3, reason: from getter */
        public final MotionExperiment getMotionExperiment() {
            return this.motionExperiment;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsMotionTensorFlowLiteEnabled() {
            return this.isMotionTensorFlowLiteEnabled;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsMotionCameraXEnabled() {
            return this.isMotionCameraXEnabled;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsEnableCameraX() {
            return this.isEnableCameraX;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getIsCameraxStreamSharingEnabled() {
            return this.isCameraxStreamSharingEnabled;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getIsCameraXHighQualityModeEnabled() {
            return this.isCameraXHighQualityModeEnabled;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getIsResolutionImprovementsEnabled() {
            return this.isResolutionImprovementsEnabled;
        }

        public final ExperimentalFeatures copy(boolean isEnableIqs, boolean isEnableMultiFrameFeature, MotionExperiment motionExperiment, boolean isMotionTensorFlowLiteEnabled, boolean isMotionCameraXEnabled, boolean isEnableCameraX, boolean isCameraxStreamSharingEnabled, boolean isCameraXHighQualityModeEnabled, boolean isResolutionImprovementsEnabled, boolean isCutoffImprovementsEnabled, boolean isFocusImprovementsEnabled, boolean isIncreasedCompressionQualityEnabled, boolean isDocumentCropDisabled, boolean isFourByThreeEnabled, boolean isGenericMrzValidatorEnabled, boolean isAutoFlashModeEnabled, WaitingScreens waitingScreens, boolean isEnvironmentIntegrityCheckEnabled, boolean isStudioUserFlowExitEnabled, CameraXConfiguration cameraXConfiguration, boolean isMediaCallbackCroppingDisabled, boolean isOnDeviceMRZExtractionEnabled, DocumentDetectionExperiment documentDetectionExperiment) {
            Intrinsics.checkNotNullParameter(motionExperiment, "motionExperiment");
            Intrinsics.checkNotNullParameter(waitingScreens, "waitingScreens");
            Intrinsics.checkNotNullParameter(cameraXConfiguration, "cameraXConfiguration");
            Intrinsics.checkNotNullParameter(documentDetectionExperiment, "documentDetectionExperiment");
            return new ExperimentalFeatures(isEnableIqs, isEnableMultiFrameFeature, motionExperiment, isMotionTensorFlowLiteEnabled, isMotionCameraXEnabled, isEnableCameraX, isCameraxStreamSharingEnabled, isCameraXHighQualityModeEnabled, isResolutionImprovementsEnabled, isCutoffImprovementsEnabled, isFocusImprovementsEnabled, isIncreasedCompressionQualityEnabled, isDocumentCropDisabled, isFourByThreeEnabled, isGenericMrzValidatorEnabled, isAutoFlashModeEnabled, waitingScreens, isEnvironmentIntegrityCheckEnabled, isStudioUserFlowExitEnabled, cameraXConfiguration, isMediaCallbackCroppingDisabled, isOnDeviceMRZExtractionEnabled, documentDetectionExperiment);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExperimentalFeatures)) {
                return false;
            }
            ExperimentalFeatures experimentalFeatures = (ExperimentalFeatures) other;
            return this.isEnableIqs == experimentalFeatures.isEnableIqs && this.isEnableMultiFrameFeature == experimentalFeatures.isEnableMultiFrameFeature && Intrinsics.areEqual(this.motionExperiment, experimentalFeatures.motionExperiment) && this.isMotionTensorFlowLiteEnabled == experimentalFeatures.isMotionTensorFlowLiteEnabled && this.isMotionCameraXEnabled == experimentalFeatures.isMotionCameraXEnabled && this.isEnableCameraX == experimentalFeatures.isEnableCameraX && this.isCameraxStreamSharingEnabled == experimentalFeatures.isCameraxStreamSharingEnabled && this.isCameraXHighQualityModeEnabled == experimentalFeatures.isCameraXHighQualityModeEnabled && this.isResolutionImprovementsEnabled == experimentalFeatures.isResolutionImprovementsEnabled && this.isCutoffImprovementsEnabled == experimentalFeatures.isCutoffImprovementsEnabled && this.isFocusImprovementsEnabled == experimentalFeatures.isFocusImprovementsEnabled && this.isIncreasedCompressionQualityEnabled == experimentalFeatures.isIncreasedCompressionQualityEnabled && this.isDocumentCropDisabled == experimentalFeatures.isDocumentCropDisabled && this.isFourByThreeEnabled == experimentalFeatures.isFourByThreeEnabled && this.isGenericMrzValidatorEnabled == experimentalFeatures.isGenericMrzValidatorEnabled && this.isAutoFlashModeEnabled == experimentalFeatures.isAutoFlashModeEnabled && Intrinsics.areEqual(this.waitingScreens, experimentalFeatures.waitingScreens) && this.isEnvironmentIntegrityCheckEnabled == experimentalFeatures.isEnvironmentIntegrityCheckEnabled && this.isStudioUserFlowExitEnabled == experimentalFeatures.isStudioUserFlowExitEnabled && Intrinsics.areEqual(this.cameraXConfiguration, experimentalFeatures.cameraXConfiguration) && this.isMediaCallbackCroppingDisabled == experimentalFeatures.isMediaCallbackCroppingDisabled && this.isOnDeviceMRZExtractionEnabled == experimentalFeatures.isOnDeviceMRZExtractionEnabled && Intrinsics.areEqual(this.documentDetectionExperiment, experimentalFeatures.documentDetectionExperiment);
        }

        public final CameraXConfiguration getCameraXConfiguration() {
            return this.cameraXConfiguration;
        }

        public final DocumentDetectionExperiment getDocumentDetectionExperiment() {
            return this.documentDetectionExperiment;
        }

        public final MotionExperiment getMotionExperiment() {
            return this.motionExperiment;
        }

        public final WaitingScreens getWaitingScreens() {
            return this.waitingScreens;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((Boolean.hashCode(this.isEnableIqs) * 31) + Boolean.hashCode(this.isEnableMultiFrameFeature)) * 31) + this.motionExperiment.hashCode()) * 31) + Boolean.hashCode(this.isMotionTensorFlowLiteEnabled)) * 31) + Boolean.hashCode(this.isMotionCameraXEnabled)) * 31) + Boolean.hashCode(this.isEnableCameraX)) * 31) + Boolean.hashCode(this.isCameraxStreamSharingEnabled)) * 31) + Boolean.hashCode(this.isCameraXHighQualityModeEnabled)) * 31) + Boolean.hashCode(this.isResolutionImprovementsEnabled)) * 31) + Boolean.hashCode(this.isCutoffImprovementsEnabled)) * 31) + Boolean.hashCode(this.isFocusImprovementsEnabled)) * 31) + Boolean.hashCode(this.isIncreasedCompressionQualityEnabled)) * 31) + Boolean.hashCode(this.isDocumentCropDisabled)) * 31) + Boolean.hashCode(this.isFourByThreeEnabled)) * 31) + Boolean.hashCode(this.isGenericMrzValidatorEnabled)) * 31) + Boolean.hashCode(this.isAutoFlashModeEnabled)) * 31) + this.waitingScreens.hashCode()) * 31) + Boolean.hashCode(this.isEnvironmentIntegrityCheckEnabled)) * 31) + Boolean.hashCode(this.isStudioUserFlowExitEnabled)) * 31) + this.cameraXConfiguration.hashCode()) * 31) + Boolean.hashCode(this.isMediaCallbackCroppingDisabled)) * 31) + Boolean.hashCode(this.isOnDeviceMRZExtractionEnabled)) * 31) + this.documentDetectionExperiment.hashCode();
        }

        public final boolean isAutoFlashModeEnabled() {
            return this.isAutoFlashModeEnabled;
        }

        public final boolean isCameraXHighQualityModeEnabled() {
            return this.isCameraXHighQualityModeEnabled;
        }

        public final boolean isCameraxStreamSharingEnabled() {
            return this.isCameraxStreamSharingEnabled;
        }

        public final boolean isCutoffImprovementsEnabled() {
            return this.isCutoffImprovementsEnabled;
        }

        public final boolean isDocumentCropDisabled() {
            return this.isDocumentCropDisabled;
        }

        public final boolean isEnableCameraX() {
            return this.isEnableCameraX;
        }

        public final boolean isEnableIqs() {
            return this.isEnableIqs;
        }

        public final boolean isEnableMultiFrameFeature() {
            return this.isEnableMultiFrameFeature;
        }

        public final boolean isEnvironmentIntegrityCheckEnabled() {
            return this.isEnvironmentIntegrityCheckEnabled;
        }

        public final boolean isFocusImprovementsEnabled() {
            return this.isFocusImprovementsEnabled;
        }

        public final boolean isFourByThreeEnabled() {
            return this.isFourByThreeEnabled;
        }

        public final boolean isGenericMrzValidatorEnabled() {
            return this.isGenericMrzValidatorEnabled;
        }

        public final boolean isIncreasedCompressionQualityEnabled() {
            return this.isIncreasedCompressionQualityEnabled;
        }

        public final boolean isMediaCallbackCroppingDisabled() {
            return this.isMediaCallbackCroppingDisabled;
        }

        public final boolean isMotionCameraXEnabled() {
            return this.isMotionCameraXEnabled;
        }

        public final boolean isMotionTensorFlowLiteEnabled() {
            return this.isMotionTensorFlowLiteEnabled;
        }

        public final boolean isOnDeviceMRZExtractionEnabled() {
            return this.isOnDeviceMRZExtractionEnabled;
        }

        public final boolean isResolutionImprovementsEnabled() {
            return this.isResolutionImprovementsEnabled;
        }

        public final boolean isStudioUserFlowExitEnabled() {
            return this.isStudioUserFlowExitEnabled;
        }

        public String toString() {
            return "ExperimentalFeatures(isEnableIqs=" + this.isEnableIqs + ", isEnableMultiFrameFeature=" + this.isEnableMultiFrameFeature + ", motionExperiment=" + this.motionExperiment + ", isMotionTensorFlowLiteEnabled=" + this.isMotionTensorFlowLiteEnabled + ", isMotionCameraXEnabled=" + this.isMotionCameraXEnabled + ", isEnableCameraX=" + this.isEnableCameraX + ", isCameraxStreamSharingEnabled=" + this.isCameraxStreamSharingEnabled + ", isCameraXHighQualityModeEnabled=" + this.isCameraXHighQualityModeEnabled + ", isResolutionImprovementsEnabled=" + this.isResolutionImprovementsEnabled + ", isCutoffImprovementsEnabled=" + this.isCutoffImprovementsEnabled + ", isFocusImprovementsEnabled=" + this.isFocusImprovementsEnabled + ", isIncreasedCompressionQualityEnabled=" + this.isIncreasedCompressionQualityEnabled + ", isDocumentCropDisabled=" + this.isDocumentCropDisabled + ", isFourByThreeEnabled=" + this.isFourByThreeEnabled + ", isGenericMrzValidatorEnabled=" + this.isGenericMrzValidatorEnabled + ", isAutoFlashModeEnabled=" + this.isAutoFlashModeEnabled + ", waitingScreens=" + this.waitingScreens + ", isEnvironmentIntegrityCheckEnabled=" + this.isEnvironmentIntegrityCheckEnabled + ", isStudioUserFlowExitEnabled=" + this.isStudioUserFlowExitEnabled + ", cameraXConfiguration=" + this.cameraXConfiguration + ", isMediaCallbackCroppingDisabled=" + this.isMediaCallbackCroppingDisabled + ", isOnDeviceMRZExtractionEnabled=" + this.isOnDeviceMRZExtractionEnabled + ", documentDetectionExperiment=" + this.documentDetectionExperiment + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ExperimentalFeatures> serializer() {
                return SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ExperimentalFeatures(int i, @SerialName("enable_image_quality_service") boolean z, @SerialName("enable_multi_frame_capture") boolean z2, @SerialName("motion_experiment") MotionExperiment motionExperiment, @SerialName("android_motion_tensorflow_lite_enabled") boolean z3, @SerialName("android_motion_camerax_enabled") boolean z4, @SerialName("enable_camerax") boolean z5, @SerialName("enable_camerax_stream_sharing") boolean z6, @SerialName("enable_camerax_high_quality") boolean z7, @SerialName("enable_optimal_resolution_improvements") boolean z8, @SerialName("enable_cutoff_improvements") boolean z9, @SerialName("enable_focus_improvements") boolean z10, @SerialName("enable_increased_compression_quality") boolean z11, @SerialName("disable_document_crop") boolean z12, @SerialName("enable_four_three_aspect_ratio") boolean z13, @SerialName("enable_generic_mrz_validator") boolean z14, @SerialName("enable_auto_flash_mode") boolean z15, @SerialName("waiting_screens") WaitingScreens waitingScreens, @SerialName("android_enable_environment_integrity_check") boolean z16, @SerialName("enable_studio_user_flow_exit") boolean z17, @SerialName("camerax_configuration") CameraXConfiguration cameraXConfiguration, @SerialName("disable_media_callback_cropping") boolean z18, @SerialName("enable_on_device_mrz_extraction") boolean z19, @SerialName(IQSUploadErrorParser.DOCUMENT_DETECTION_ERROR_KEY) DocumentDetectionExperiment documentDetectionExperiment, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, SdkConfiguration$ExperimentalFeatures$$serializer.INSTANCE.getDescriptor());
            }
            this.isEnableIqs = z;
            this.isEnableMultiFrameFeature = z2;
            this.motionExperiment = (i & 4) == 0 ? MotionExperiment.INSTANCE.getDISABLED() : motionExperiment;
            if ((i & 8) == 0) {
                this.isMotionTensorFlowLiteEnabled = false;
            } else {
                this.isMotionTensorFlowLiteEnabled = z3;
            }
            if ((i & 16) == 0) {
                this.isMotionCameraXEnabled = false;
            } else {
                this.isMotionCameraXEnabled = z4;
            }
            if ((i & 32) == 0) {
                this.isEnableCameraX = false;
            } else {
                this.isEnableCameraX = z5;
            }
            if ((i & 64) == 0) {
                this.isCameraxStreamSharingEnabled = false;
            } else {
                this.isCameraxStreamSharingEnabled = z6;
            }
            if ((i & 128) == 0) {
                this.isCameraXHighQualityModeEnabled = true;
            } else {
                this.isCameraXHighQualityModeEnabled = z7;
            }
            if ((i & 256) == 0) {
                this.isResolutionImprovementsEnabled = false;
            } else {
                this.isResolutionImprovementsEnabled = z8;
            }
            if ((i & 512) == 0) {
                this.isCutoffImprovementsEnabled = false;
            } else {
                this.isCutoffImprovementsEnabled = z9;
            }
            if ((i & 1024) == 0) {
                this.isFocusImprovementsEnabled = false;
            } else {
                this.isFocusImprovementsEnabled = z10;
            }
            if ((i & 2048) == 0) {
                this.isIncreasedCompressionQualityEnabled = false;
            } else {
                this.isIncreasedCompressionQualityEnabled = z11;
            }
            if ((i & 4096) == 0) {
                this.isDocumentCropDisabled = false;
            } else {
                this.isDocumentCropDisabled = z12;
            }
            if ((i & 8192) == 0) {
                this.isFourByThreeEnabled = false;
            } else {
                this.isFourByThreeEnabled = z13;
            }
            if ((i & 16384) == 0) {
                this.isGenericMrzValidatorEnabled = false;
            } else {
                this.isGenericMrzValidatorEnabled = z14;
            }
            if ((32768 & i) == 0) {
                this.isAutoFlashModeEnabled = false;
            } else {
                this.isAutoFlashModeEnabled = z15;
            }
            this.waitingScreens = (65536 & i) == 0 ? WaitingScreens.INSTANCE.getDEFAULT() : waitingScreens;
            if ((131072 & i) == 0) {
                this.isEnvironmentIntegrityCheckEnabled = false;
            } else {
                this.isEnvironmentIntegrityCheckEnabled = z16;
            }
            if ((262144 & i) == 0) {
                this.isStudioUserFlowExitEnabled = false;
            } else {
                this.isStudioUserFlowExitEnabled = z17;
            }
            this.cameraXConfiguration = (524288 & i) == 0 ? new CameraXConfiguration(false, false, false, false, false, false, false, 127, (DefaultConstructorMarker) null) : cameraXConfiguration;
            if ((1048576 & i) == 0) {
                this.isMediaCallbackCroppingDisabled = false;
            } else {
                this.isMediaCallbackCroppingDisabled = z18;
            }
            if ((2097152 & i) == 0) {
                this.isOnDeviceMRZExtractionEnabled = true;
            } else {
                this.isOnDeviceMRZExtractionEnabled = z19;
            }
            this.documentDetectionExperiment = (i & 4194304) == 0 ? new DocumentDetectionExperiment(false, false, 0, 0.0f, 15, (DefaultConstructorMarker) null) : documentDetectionExperiment;
        }

        public ExperimentalFeatures(boolean z, boolean z2, MotionExperiment motionExperiment, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, WaitingScreens waitingScreens, boolean z16, boolean z17, CameraXConfiguration cameraXConfiguration, boolean z18, boolean z19, DocumentDetectionExperiment documentDetectionExperiment) {
            Intrinsics.checkNotNullParameter(motionExperiment, "motionExperiment");
            Intrinsics.checkNotNullParameter(waitingScreens, "waitingScreens");
            Intrinsics.checkNotNullParameter(cameraXConfiguration, "cameraXConfiguration");
            Intrinsics.checkNotNullParameter(documentDetectionExperiment, "documentDetectionExperiment");
            this.isEnableIqs = z;
            this.isEnableMultiFrameFeature = z2;
            this.motionExperiment = motionExperiment;
            this.isMotionTensorFlowLiteEnabled = z3;
            this.isMotionCameraXEnabled = z4;
            this.isEnableCameraX = z5;
            this.isCameraxStreamSharingEnabled = z6;
            this.isCameraXHighQualityModeEnabled = z7;
            this.isResolutionImprovementsEnabled = z8;
            this.isCutoffImprovementsEnabled = z9;
            this.isFocusImprovementsEnabled = z10;
            this.isIncreasedCompressionQualityEnabled = z11;
            this.isDocumentCropDisabled = z12;
            this.isFourByThreeEnabled = z13;
            this.isGenericMrzValidatorEnabled = z14;
            this.isAutoFlashModeEnabled = z15;
            this.waitingScreens = waitingScreens;
            this.isEnvironmentIntegrityCheckEnabled = z16;
            this.isStudioUserFlowExitEnabled = z17;
            this.cameraXConfiguration = cameraXConfiguration;
            this.isMediaCallbackCroppingDisabled = z18;
            this.isOnDeviceMRZExtractionEnabled = z19;
            this.documentDetectionExperiment = documentDetectionExperiment;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(ExperimentalFeatures self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeBooleanElement(serialDesc, 0, self.isEnableIqs);
            output.encodeBooleanElement(serialDesc, 1, self.isEnableMultiFrameFeature);
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.motionExperiment, MotionExperiment.INSTANCE.getDISABLED())) {
                output.encodeSerializableElement(serialDesc, 2, SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE, self.motionExperiment);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.isMotionTensorFlowLiteEnabled) {
                output.encodeBooleanElement(serialDesc, 3, self.isMotionTensorFlowLiteEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.isMotionCameraXEnabled) {
                output.encodeBooleanElement(serialDesc, 4, self.isMotionCameraXEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.isEnableCameraX) {
                output.encodeBooleanElement(serialDesc, 5, self.isEnableCameraX);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.isCameraxStreamSharingEnabled) {
                output.encodeBooleanElement(serialDesc, 6, self.isCameraxStreamSharingEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || !self.isCameraXHighQualityModeEnabled) {
                output.encodeBooleanElement(serialDesc, 7, self.isCameraXHighQualityModeEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.isResolutionImprovementsEnabled) {
                output.encodeBooleanElement(serialDesc, 8, self.isResolutionImprovementsEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.isCutoffImprovementsEnabled) {
                output.encodeBooleanElement(serialDesc, 9, self.isCutoffImprovementsEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 10) || self.isFocusImprovementsEnabled) {
                output.encodeBooleanElement(serialDesc, 10, self.isFocusImprovementsEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 11) || self.isIncreasedCompressionQualityEnabled) {
                output.encodeBooleanElement(serialDesc, 11, self.isIncreasedCompressionQualityEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 12) || self.isDocumentCropDisabled) {
                output.encodeBooleanElement(serialDesc, 12, self.isDocumentCropDisabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 13) || self.isFourByThreeEnabled) {
                output.encodeBooleanElement(serialDesc, 13, self.isFourByThreeEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 14) || self.isGenericMrzValidatorEnabled) {
                output.encodeBooleanElement(serialDesc, 14, self.isGenericMrzValidatorEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 15) || self.isAutoFlashModeEnabled) {
                output.encodeBooleanElement(serialDesc, 15, self.isAutoFlashModeEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 16) || !Intrinsics.areEqual(self.waitingScreens, WaitingScreens.INSTANCE.getDEFAULT())) {
                output.encodeSerializableElement(serialDesc, 16, SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE, self.waitingScreens);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 17) || self.isEnvironmentIntegrityCheckEnabled) {
                output.encodeBooleanElement(serialDesc, 17, self.isEnvironmentIntegrityCheckEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 18) || self.isStudioUserFlowExitEnabled) {
                output.encodeBooleanElement(serialDesc, 18, self.isStudioUserFlowExitEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 19) || !Intrinsics.areEqual(self.cameraXConfiguration, new CameraXConfiguration(false, false, false, false, false, false, false, 127, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 19, SdkConfiguration$CameraXConfiguration$$serializer.INSTANCE, self.cameraXConfiguration);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 20) || self.isMediaCallbackCroppingDisabled) {
                output.encodeBooleanElement(serialDesc, 20, self.isMediaCallbackCroppingDisabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 21) || !self.isOnDeviceMRZExtractionEnabled) {
                output.encodeBooleanElement(serialDesc, 21, self.isOnDeviceMRZExtractionEnabled);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 22) && Intrinsics.areEqual(self.documentDetectionExperiment, new DocumentDetectionExperiment(false, false, 0, 0.0f, 15, (DefaultConstructorMarker) null))) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 22, SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer.INSTANCE, self.documentDetectionExperiment);
        }

        public /* synthetic */ ExperimentalFeatures(boolean z, boolean z2, MotionExperiment motionExperiment, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, WaitingScreens waitingScreens, boolean z16, boolean z17, CameraXConfiguration cameraXConfiguration, boolean z18, boolean z19, DocumentDetectionExperiment documentDetectionExperiment, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, z2, (i & 4) != 0 ? MotionExperiment.INSTANCE.getDISABLED() : motionExperiment, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? false : z5, (i & 64) != 0 ? false : z6, (i & 128) != 0 ? true : z7, (i & 256) != 0 ? false : z8, (i & 512) != 0 ? false : z9, (i & 1024) != 0 ? false : z10, (i & 2048) != 0 ? false : z11, (i & 4096) != 0 ? false : z12, (i & 8192) != 0 ? false : z13, (i & 16384) != 0 ? false : z14, (32768 & i) != 0 ? false : z15, (65536 & i) != 0 ? WaitingScreens.INSTANCE.getDEFAULT() : waitingScreens, (131072 & i) != 0 ? false : z16, (262144 & i) != 0 ? false : z17, (524288 & i) != 0 ? new CameraXConfiguration(false, false, false, false, false, false, false, 127, (DefaultConstructorMarker) null) : cameraXConfiguration, (1048576 & i) != 0 ? false : z18, (2097152 & i) != 0 ? true : z19, (i & 4194304) != 0 ? new DocumentDetectionExperiment(false, false, 0, 0.0f, 15, (DefaultConstructorMarker) null) : documentDetectionExperiment);
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B/\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u0004\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006#"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "", "seen1", "", "isEnabled", "", "reason", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/lang/String;)V", "isEnabled$annotations", "()V", "()Z", "getReason$annotations", "getReason", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class MotionExperiment {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private static final MotionExperiment DISABLED = new MotionExperiment(false, "feature_flag_disabled");
            private static final MotionExperiment ENABLED = new MotionExperiment(true, "feature_flag_enabled");
            private final boolean isEnabled;
            private final String reason;

            public static /* synthetic */ MotionExperiment copy$default(MotionExperiment motionExperiment, boolean z, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = motionExperiment.isEnabled;
                }
                if ((i & 2) != 0) {
                    str = motionExperiment.reason;
                }
                return motionExperiment.copy(z, str);
            }

            @SerialName("reason")
            public static /* synthetic */ void getReason$annotations() {
            }

            @SerialName(ViewProps.ENABLED)
            public static /* synthetic */ void isEnabled$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getIsEnabled() {
                return this.isEnabled;
            }

            /* renamed from: component2, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            public final MotionExperiment copy(boolean isEnabled, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new MotionExperiment(isEnabled, reason);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof MotionExperiment)) {
                    return false;
                }
                MotionExperiment motionExperiment = (MotionExperiment) other;
                return this.isEnabled == motionExperiment.isEnabled && Intrinsics.areEqual(this.reason, motionExperiment.reason);
            }

            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return (Boolean.hashCode(this.isEnabled) * 31) + this.reason.hashCode();
            }

            public final boolean isEnabled() {
                return this.isEnabled;
            }

            public String toString() {
                return "MotionExperiment(isEnabled=" + this.isEnabled + ", reason=" + this.reason + ")";
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ MotionExperiment(int i, @SerialName(ViewProps.ENABLED) boolean z, @SerialName("reason") String str, SerializationConstructorMarker serializationConstructorMarker) {
                if (3 != (i & 3)) {
                    PluginExceptionsKt.throwMissingFieldException(i, 3, SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE.getDescriptor());
                }
                this.isEnabled = z;
                this.reason = str;
            }

            public MotionExperiment(boolean z, String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.isEnabled = z;
                this.reason = reason;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$onfido_api_client(MotionExperiment self, CompositeEncoder output, SerialDescriptor serialDesc) {
                output.encodeBooleanElement(serialDesc, 0, self.isEnabled);
                output.encodeStringElement(serialDesc, 1, self.reason);
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment$Companion;", "", "()V", "DISABLED", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "getDISABLED", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$MotionExperiment;", "ENABLED", "getENABLED", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<MotionExperiment> serializer() {
                    return SdkConfiguration$ExperimentalFeatures$MotionExperiment$$serializer.INSTANCE;
                }

                public final MotionExperiment getDISABLED() {
                    return MotionExperiment.DISABLED;
                }

                public final MotionExperiment getENABLED() {
                    return MotionExperiment.ENABLED;
                }
            }
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0004$%&'B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÁ\u0001¢\u0006\u0002\b#R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "", "seen1", "", "studioTaskSubmission", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;", "mediaUploadSubmission", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;)V", "getMediaUploadSubmission$annotations", "()V", "getMediaUploadSubmission", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;", "getStudioTaskSubmission$annotations", "getStudioTaskSubmission", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "MediaUploadSubmission", "StudioTaskSubmission", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class WaitingScreens {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private static final WaitingScreens DEFAULT;
            private final MediaUploadSubmission mediaUploadSubmission;
            private final StudioTaskSubmission studioTaskSubmission;

            public static /* synthetic */ WaitingScreens copy$default(WaitingScreens waitingScreens, StudioTaskSubmission studioTaskSubmission, MediaUploadSubmission mediaUploadSubmission, int i, Object obj) {
                if ((i & 1) != 0) {
                    studioTaskSubmission = waitingScreens.studioTaskSubmission;
                }
                if ((i & 2) != 0) {
                    mediaUploadSubmission = waitingScreens.mediaUploadSubmission;
                }
                return waitingScreens.copy(studioTaskSubmission, mediaUploadSubmission);
            }

            @SerialName("media_upload_submission")
            public static /* synthetic */ void getMediaUploadSubmission$annotations() {
            }

            @SerialName("studio_task_submission")
            public static /* synthetic */ void getStudioTaskSubmission$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final StudioTaskSubmission getStudioTaskSubmission() {
                return this.studioTaskSubmission;
            }

            /* renamed from: component2, reason: from getter */
            public final MediaUploadSubmission getMediaUploadSubmission() {
                return this.mediaUploadSubmission;
            }

            public final WaitingScreens copy(StudioTaskSubmission studioTaskSubmission, MediaUploadSubmission mediaUploadSubmission) {
                Intrinsics.checkNotNullParameter(studioTaskSubmission, "studioTaskSubmission");
                Intrinsics.checkNotNullParameter(mediaUploadSubmission, "mediaUploadSubmission");
                return new WaitingScreens(studioTaskSubmission, mediaUploadSubmission);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WaitingScreens)) {
                    return false;
                }
                WaitingScreens waitingScreens = (WaitingScreens) other;
                return Intrinsics.areEqual(this.studioTaskSubmission, waitingScreens.studioTaskSubmission) && Intrinsics.areEqual(this.mediaUploadSubmission, waitingScreens.mediaUploadSubmission);
            }

            public final MediaUploadSubmission getMediaUploadSubmission() {
                return this.mediaUploadSubmission;
            }

            public final StudioTaskSubmission getStudioTaskSubmission() {
                return this.studioTaskSubmission;
            }

            public int hashCode() {
                return (this.studioTaskSubmission.hashCode() * 31) + this.mediaUploadSubmission.hashCode();
            }

            public String toString() {
                return "WaitingScreens(studioTaskSubmission=" + this.studioTaskSubmission + ", mediaUploadSubmission=" + this.mediaUploadSubmission + ")";
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ WaitingScreens(int i, @SerialName("studio_task_submission") StudioTaskSubmission studioTaskSubmission, @SerialName("media_upload_submission") MediaUploadSubmission mediaUploadSubmission, SerializationConstructorMarker serializationConstructorMarker) {
                if (3 != (i & 3)) {
                    PluginExceptionsKt.throwMissingFieldException(i, 3, SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE.getDescriptor());
                }
                this.studioTaskSubmission = studioTaskSubmission;
                this.mediaUploadSubmission = mediaUploadSubmission;
            }

            public WaitingScreens(StudioTaskSubmission studioTaskSubmission, MediaUploadSubmission mediaUploadSubmission) {
                Intrinsics.checkNotNullParameter(studioTaskSubmission, "studioTaskSubmission");
                Intrinsics.checkNotNullParameter(mediaUploadSubmission, "mediaUploadSubmission");
                this.studioTaskSubmission = studioTaskSubmission;
                this.mediaUploadSubmission = mediaUploadSubmission;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$onfido_api_client(WaitingScreens self, CompositeEncoder output, SerialDescriptor serialDesc) {
                output.encodeSerializableElement(serialDesc, 0, SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$$serializer.INSTANCE, self.studioTaskSubmission);
                output.encodeSerializableElement(serialDesc, 1, SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$$serializer.INSTANCE, self.mediaUploadSubmission);
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B+\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0019\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dHÁ\u0001¢\u0006\u0002\b\u001eR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;", "", "seen1", "", "waitingTimes", "", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getWaitingTimes$annotations", "()V", "getWaitingTimes", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            @Serializable
            public static final /* data */ class StudioTaskSubmission {
                private final List<Long> waitingTimes;

                /* renamed from: Companion, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);
                private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(LongSerializer.INSTANCE)};

                /* JADX WARN: Multi-variable type inference failed */
                public StudioTaskSubmission() {
                    this((List) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
                }

                /* JADX WARN: Multi-variable type inference failed */
                public static /* synthetic */ StudioTaskSubmission copy$default(StudioTaskSubmission studioTaskSubmission, List list, int i, Object obj) {
                    if ((i & 1) != 0) {
                        list = studioTaskSubmission.waitingTimes;
                    }
                    return studioTaskSubmission.copy(list);
                }

                @SerialName("waiting_screens_ms")
                public static /* synthetic */ void getWaitingTimes$annotations() {
                }

                public final List<Long> component1() {
                    return this.waitingTimes;
                }

                public final StudioTaskSubmission copy(List<Long> waitingTimes) {
                    Intrinsics.checkNotNullParameter(waitingTimes, "waitingTimes");
                    return new StudioTaskSubmission(waitingTimes);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof StudioTaskSubmission) && Intrinsics.areEqual(this.waitingTimes, ((StudioTaskSubmission) other).waitingTimes);
                }

                public final List<Long> getWaitingTimes() {
                    return this.waitingTimes;
                }

                public int hashCode() {
                    return this.waitingTimes.hashCode();
                }

                public String toString() {
                    return "StudioTaskSubmission(waitingTimes=" + this.waitingTimes + ")";
                }

                /* compiled from: SdkConfiguration.kt */
                @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }

                    public final KSerializer<StudioTaskSubmission> serializer() {
                        return SdkConfiguration$ExperimentalFeatures$WaitingScreens$StudioTaskSubmission$$serializer.INSTANCE;
                    }
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
                public /* synthetic */ StudioTaskSubmission(int i, @SerialName("waiting_screens_ms") List list, SerializationConstructorMarker serializationConstructorMarker) {
                    if ((i & 1) == 0) {
                        this.waitingTimes = CollectionsKt.emptyList();
                    } else {
                        this.waitingTimes = list;
                    }
                }

                public StudioTaskSubmission(List<Long> waitingTimes) {
                    Intrinsics.checkNotNullParameter(waitingTimes, "waitingTimes");
                    this.waitingTimes = waitingTimes;
                }

                @JvmStatic
                public static final /* synthetic */ void write$Self$onfido_api_client(StudioTaskSubmission self, CompositeEncoder output, SerialDescriptor serialDesc) {
                    KSerializer<Object>[] kSerializerArr = $childSerializers;
                    if (!output.shouldEncodeElementDefault(serialDesc, 0) && Intrinsics.areEqual(self.waitingTimes, CollectionsKt.emptyList())) {
                        return;
                    }
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.waitingTimes);
                }

                public /* synthetic */ StudioTaskSubmission(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
                }
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B+\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0019\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J&\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dHÁ\u0001¢\u0006\u0002\b\u001eR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;", "", "seen1", "", "waitingTimes", "", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getWaitingTimes$annotations", "()V", "getWaitingTimes", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            @Serializable
            public static final /* data */ class MediaUploadSubmission {
                private final List<Long> waitingTimes;

                /* renamed from: Companion, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);
                private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(LongSerializer.INSTANCE)};

                /* JADX WARN: Multi-variable type inference failed */
                public MediaUploadSubmission() {
                    this((List) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
                }

                /* JADX WARN: Multi-variable type inference failed */
                public static /* synthetic */ MediaUploadSubmission copy$default(MediaUploadSubmission mediaUploadSubmission, List list, int i, Object obj) {
                    if ((i & 1) != 0) {
                        list = mediaUploadSubmission.waitingTimes;
                    }
                    return mediaUploadSubmission.copy(list);
                }

                @SerialName("waiting_screens_ms")
                public static /* synthetic */ void getWaitingTimes$annotations() {
                }

                public final List<Long> component1() {
                    return this.waitingTimes;
                }

                public final MediaUploadSubmission copy(List<Long> waitingTimes) {
                    Intrinsics.checkNotNullParameter(waitingTimes, "waitingTimes");
                    return new MediaUploadSubmission(waitingTimes);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof MediaUploadSubmission) && Intrinsics.areEqual(this.waitingTimes, ((MediaUploadSubmission) other).waitingTimes);
                }

                public final List<Long> getWaitingTimes() {
                    return this.waitingTimes;
                }

                public int hashCode() {
                    return this.waitingTimes.hashCode();
                }

                public String toString() {
                    return "MediaUploadSubmission(waitingTimes=" + this.waitingTimes + ")";
                }

                /* compiled from: SdkConfiguration.kt */
                @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }

                    public final KSerializer<MediaUploadSubmission> serializer() {
                        return SdkConfiguration$ExperimentalFeatures$WaitingScreens$MediaUploadSubmission$$serializer.INSTANCE;
                    }
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
                public /* synthetic */ MediaUploadSubmission(int i, @SerialName("waiting_screens_ms") List list, SerializationConstructorMarker serializationConstructorMarker) {
                    if ((i & 1) == 0) {
                        this.waitingTimes = CollectionsKt.emptyList();
                    } else {
                        this.waitingTimes = list;
                    }
                }

                public MediaUploadSubmission(List<Long> waitingTimes) {
                    Intrinsics.checkNotNullParameter(waitingTimes, "waitingTimes");
                    this.waitingTimes = waitingTimes;
                }

                @JvmStatic
                public static final /* synthetic */ void write$Self$onfido_api_client(MediaUploadSubmission self, CompositeEncoder output, SerialDescriptor serialDesc) {
                    KSerializer<Object>[] kSerializerArr = $childSerializers;
                    if (!output.shouldEncodeElementDefault(serialDesc, 0) && Intrinsics.areEqual(self.waitingTimes, CollectionsKt.emptyList())) {
                        return;
                    }
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.waitingTimes);
                }

                public /* synthetic */ MediaUploadSubmission(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
                }
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "getDEFAULT", "()Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$WaitingScreens;", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<WaitingScreens> serializer() {
                    return SdkConfiguration$ExperimentalFeatures$WaitingScreens$$serializer.INSTANCE;
                }

                public final WaitingScreens getDEFAULT() {
                    return WaitingScreens.DEFAULT;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            static {
                int i = 1;
                DEFAULT = new WaitingScreens(new StudioTaskSubmission((List) (0 == true ? 1 : 0), i, (DefaultConstructorMarker) (0 == true ? 1 : 0)), new MediaUploadSubmission((List) (0 == true ? 1 : 0), i, (DefaultConstructorMarker) (0 == true ? 1 : 0)));
            }
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-BA\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB-\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\rJ\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J1\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001J&\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*HÁ\u0001¢\u0006\u0002\b+R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0011R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0019¨\u0006."}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "", "seen1", "", ViewProps.ENABLED, "", "shouldFallback", "holdDurationInMs", "threshold", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZIFLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZIF)V", "getEnabled$annotations", "()V", "getEnabled", "()Z", "getHoldDurationInMs$annotations", "getHoldDurationInMs", "()I", "getShouldFallback$annotations", "getShouldFallback", "getThreshold$annotations", "getThreshold", "()F", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class DocumentDetectionExperiment {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final boolean enabled;
            private final int holdDurationInMs;
            private final boolean shouldFallback;
            private final float threshold;

            public DocumentDetectionExperiment() {
                this(false, false, 0, 0.0f, 15, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ DocumentDetectionExperiment copy$default(DocumentDetectionExperiment documentDetectionExperiment, boolean z, boolean z2, int i, float f, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    z = documentDetectionExperiment.enabled;
                }
                if ((i2 & 2) != 0) {
                    z2 = documentDetectionExperiment.shouldFallback;
                }
                if ((i2 & 4) != 0) {
                    i = documentDetectionExperiment.holdDurationInMs;
                }
                if ((i2 & 8) != 0) {
                    f = documentDetectionExperiment.threshold;
                }
                return documentDetectionExperiment.copy(z, z2, i, f);
            }

            @SerialName(ViewProps.ENABLED)
            public static /* synthetic */ void getEnabled$annotations() {
            }

            @SerialName("hold_duration_in_ms")
            public static /* synthetic */ void getHoldDurationInMs$annotations() {
            }

            @SerialName("should_fallback")
            public static /* synthetic */ void getShouldFallback$annotations() {
            }

            @SerialName("threshold")
            public static /* synthetic */ void getThreshold$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getEnabled() {
                return this.enabled;
            }

            /* renamed from: component2, reason: from getter */
            public final boolean getShouldFallback() {
                return this.shouldFallback;
            }

            /* renamed from: component3, reason: from getter */
            public final int getHoldDurationInMs() {
                return this.holdDurationInMs;
            }

            /* renamed from: component4, reason: from getter */
            public final float getThreshold() {
                return this.threshold;
            }

            public final DocumentDetectionExperiment copy(boolean enabled, boolean shouldFallback, int holdDurationInMs, float threshold) {
                return new DocumentDetectionExperiment(enabled, shouldFallback, holdDurationInMs, threshold);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DocumentDetectionExperiment)) {
                    return false;
                }
                DocumentDetectionExperiment documentDetectionExperiment = (DocumentDetectionExperiment) other;
                return this.enabled == documentDetectionExperiment.enabled && this.shouldFallback == documentDetectionExperiment.shouldFallback && this.holdDurationInMs == documentDetectionExperiment.holdDurationInMs && Float.compare(this.threshold, documentDetectionExperiment.threshold) == 0;
            }

            public final boolean getEnabled() {
                return this.enabled;
            }

            public final int getHoldDurationInMs() {
                return this.holdDurationInMs;
            }

            public final boolean getShouldFallback() {
                return this.shouldFallback;
            }

            public final float getThreshold() {
                return this.threshold;
            }

            public int hashCode() {
                return (((((Boolean.hashCode(this.enabled) * 31) + Boolean.hashCode(this.shouldFallback)) * 31) + Integer.hashCode(this.holdDurationInMs)) * 31) + Float.hashCode(this.threshold);
            }

            public String toString() {
                return "DocumentDetectionExperiment(enabled=" + this.enabled + ", shouldFallback=" + this.shouldFallback + ", holdDurationInMs=" + this.holdDurationInMs + ", threshold=" + this.threshold + ")";
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<DocumentDetectionExperiment> serializer() {
                    return SdkConfiguration$ExperimentalFeatures$DocumentDetectionExperiment$$serializer.INSTANCE;
                }
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ DocumentDetectionExperiment(int i, @SerialName(ViewProps.ENABLED) boolean z, @SerialName("should_fallback") boolean z2, @SerialName("hold_duration_in_ms") int i2, @SerialName("threshold") float f, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.enabled = false;
                } else {
                    this.enabled = z;
                }
                if ((i & 2) == 0) {
                    this.shouldFallback = false;
                } else {
                    this.shouldFallback = z2;
                }
                if ((i & 4) == 0) {
                    this.holdDurationInMs = 1000;
                } else {
                    this.holdDurationInMs = i2;
                }
                if ((i & 8) == 0) {
                    this.threshold = 0.6f;
                } else {
                    this.threshold = f;
                }
            }

            public DocumentDetectionExperiment(boolean z, boolean z2, int i, float f) {
                this.enabled = z;
                this.shouldFallback = z2;
                this.holdDurationInMs = i;
                this.threshold = f;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$onfido_api_client(DocumentDetectionExperiment self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.enabled) {
                    output.encodeBooleanElement(serialDesc, 0, self.enabled);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.shouldFallback) {
                    output.encodeBooleanElement(serialDesc, 1, self.shouldFallback);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.holdDurationInMs != 1000) {
                    output.encodeIntElement(serialDesc, 2, self.holdDurationInMs);
                }
                if (!output.shouldEncodeElementDefault(serialDesc, 3) && Float.compare(self.threshold, 0.6f) == 0) {
                    return;
                }
                output.encodeFloatElement(serialDesc, 3, self.threshold);
            }

            public /* synthetic */ DocumentDetectionExperiment(boolean z, boolean z2, int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? 1000 : i, (i2 & 8) != 0 ? 0.6f : f);
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002)*BA\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J1\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001J&\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'HÁ\u0001¢\u0006\u0002\b(R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u0004\u0010\u000fR\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0012¨\u0006+"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "", "seen1", "", "isPayloadSigningEnabled", "", "videoQuality", "maxVideoLengthMs", "videoBitrate", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZIIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZIII)V", "isPayloadSigningEnabled$annotations", "()V", "()Z", "getMaxVideoLengthMs$annotations", "getMaxVideoLengthMs", "()I", "getVideoBitrate$annotations", "getVideoBitrate", "getVideoQuality$annotations", "getVideoQuality", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LivenessCapture {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final LivenessCapture DEFAULT = new LivenessCapture(true, 0, 0, 0, 14, (DefaultConstructorMarker) null);
        private static final int DEFAULT_MAX_RECORDING_DURATION_IN_MS = 30000;
        private static final int DEFAULT_VIDEO_BITRATE = 3000000;
        private static final int DEFAULT_VIDEO_QUALITY = 1080;
        private final boolean isPayloadSigningEnabled;
        private final int maxVideoLengthMs;
        private final int videoBitrate;
        private final int videoQuality;

        public static /* synthetic */ LivenessCapture copy$default(LivenessCapture livenessCapture, boolean z, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                z = livenessCapture.isPayloadSigningEnabled;
            }
            if ((i4 & 2) != 0) {
                i = livenessCapture.videoQuality;
            }
            if ((i4 & 4) != 0) {
                i2 = livenessCapture.maxVideoLengthMs;
            }
            if ((i4 & 8) != 0) {
                i3 = livenessCapture.videoBitrate;
            }
            return livenessCapture.copy(z, i, i2, i3);
        }

        @SerialName("max_video_length_ms")
        public static /* synthetic */ void getMaxVideoLengthMs$annotations() {
        }

        @SerialName("video_bitrate")
        public static /* synthetic */ void getVideoBitrate$annotations() {
        }

        @SerialName("video_quality")
        public static /* synthetic */ void getVideoQuality$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_IS_PAYLOAD_SIGNING_ENABLED)
        public static /* synthetic */ void isPayloadSigningEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsPayloadSigningEnabled() {
            return this.isPayloadSigningEnabled;
        }

        /* renamed from: component2, reason: from getter */
        public final int getVideoQuality() {
            return this.videoQuality;
        }

        /* renamed from: component3, reason: from getter */
        public final int getMaxVideoLengthMs() {
            return this.maxVideoLengthMs;
        }

        /* renamed from: component4, reason: from getter */
        public final int getVideoBitrate() {
            return this.videoBitrate;
        }

        public final LivenessCapture copy(boolean isPayloadSigningEnabled, int videoQuality, int maxVideoLengthMs, int videoBitrate) {
            return new LivenessCapture(isPayloadSigningEnabled, videoQuality, maxVideoLengthMs, videoBitrate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LivenessCapture)) {
                return false;
            }
            LivenessCapture livenessCapture = (LivenessCapture) other;
            return this.isPayloadSigningEnabled == livenessCapture.isPayloadSigningEnabled && this.videoQuality == livenessCapture.videoQuality && this.maxVideoLengthMs == livenessCapture.maxVideoLengthMs && this.videoBitrate == livenessCapture.videoBitrate;
        }

        public final int getMaxVideoLengthMs() {
            return this.maxVideoLengthMs;
        }

        public final int getVideoBitrate() {
            return this.videoBitrate;
        }

        public final int getVideoQuality() {
            return this.videoQuality;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.isPayloadSigningEnabled) * 31) + Integer.hashCode(this.videoQuality)) * 31) + Integer.hashCode(this.maxVideoLengthMs)) * 31) + Integer.hashCode(this.videoBitrate);
        }

        public final boolean isPayloadSigningEnabled() {
            return this.isPayloadSigningEnabled;
        }

        public String toString() {
            return "LivenessCapture(isPayloadSigningEnabled=" + this.isPayloadSigningEnabled + ", videoQuality=" + this.videoQuality + ", maxVideoLengthMs=" + this.maxVideoLengthMs + ", videoBitrate=" + this.videoBitrate + ")";
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ LivenessCapture(int i, @SerialName(SdkConfiguration.FIELD_IS_PAYLOAD_SIGNING_ENABLED) boolean z, @SerialName("video_quality") int i2, @SerialName("max_video_length_ms") int i3, @SerialName("video_bitrate") int i4, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SdkConfiguration$LivenessCapture$$serializer.INSTANCE.getDescriptor());
            }
            this.isPayloadSigningEnabled = z;
            if ((i & 2) == 0) {
                this.videoQuality = 1080;
            } else {
                this.videoQuality = i2;
            }
            if ((i & 4) == 0) {
                this.maxVideoLengthMs = 30000;
            } else {
                this.maxVideoLengthMs = i3;
            }
            if ((i & 8) == 0) {
                this.videoBitrate = DEFAULT_VIDEO_BITRATE;
            } else {
                this.videoBitrate = i4;
            }
        }

        public LivenessCapture(boolean z, int i, int i2, int i3) {
            this.isPayloadSigningEnabled = z;
            this.videoQuality = i;
            this.maxVideoLengthMs = i2;
            this.videoBitrate = i3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(LivenessCapture self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeBooleanElement(serialDesc, 0, self.isPayloadSigningEnabled);
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.videoQuality != 1080) {
                output.encodeIntElement(serialDesc, 1, self.videoQuality);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.maxVideoLengthMs != 30000) {
                output.encodeIntElement(serialDesc, 2, self.maxVideoLengthMs);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.videoBitrate == DEFAULT_VIDEO_BITRATE) {
                return;
            }
            output.encodeIntElement(serialDesc, 3, self.videoBitrate);
        }

        public /* synthetic */ LivenessCapture(boolean z, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i4 & 2) != 0 ? 1080 : i, (i4 & 4) != 0 ? 30000 : i2, (i4 & 8) != 0 ? DEFAULT_VIDEO_BITRATE : i3);
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "getDEFAULT", "()Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "DEFAULT_MAX_RECORDING_DURATION_IN_MS", "", "DEFAULT_VIDEO_BITRATE", "DEFAULT_VIDEO_QUALITY", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LivenessCapture> serializer() {
                return SdkConfiguration$LivenessCapture$$serializer.INSTANCE;
            }

            public final LivenessCapture getDEFAULT() {
                return LivenessCapture.DEFAULT;
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aHÁ\u0001¢\u0006\u0002\b\u001bR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\u0004\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "", "seen1", "", "isPayloadSigningEnabled", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Z)V", "isPayloadSigningEnabled$annotations", "()V", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SelfieCapture {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SelfieCapture DEFAULT = new SelfieCapture(true);
        private final boolean isPayloadSigningEnabled;

        public SelfieCapture() {
            this(false, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SelfieCapture copy$default(SelfieCapture selfieCapture, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = selfieCapture.isPayloadSigningEnabled;
            }
            return selfieCapture.copy(z);
        }

        @SerialName(SdkConfiguration.FIELD_IS_PAYLOAD_SIGNING_ENABLED)
        public static /* synthetic */ void isPayloadSigningEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsPayloadSigningEnabled() {
            return this.isPayloadSigningEnabled;
        }

        public final SelfieCapture copy(boolean isPayloadSigningEnabled) {
            return new SelfieCapture(isPayloadSigningEnabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SelfieCapture) && this.isPayloadSigningEnabled == ((SelfieCapture) other).isPayloadSigningEnabled;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isPayloadSigningEnabled);
        }

        public final boolean isPayloadSigningEnabled() {
            return this.isPayloadSigningEnabled;
        }

        public String toString() {
            return "SelfieCapture(isPayloadSigningEnabled=" + this.isPayloadSigningEnabled + ")";
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ SelfieCapture(int i, @SerialName(SdkConfiguration.FIELD_IS_PAYLOAD_SIGNING_ENABLED) boolean z, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.isPayloadSigningEnabled = true;
            } else {
                this.isPayloadSigningEnabled = z;
            }
        }

        public SelfieCapture(boolean z) {
            this.isPayloadSigningEnabled = z;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(SelfieCapture self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.isPayloadSigningEnabled) {
                return;
            }
            output.encodeBooleanElement(serialDesc, 0, self.isPayloadSigningEnabled);
        }

        public /* synthetic */ SelfieCapture(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z);
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "getDEFAULT", "()Lcom/onfido/api/client/data/SdkConfiguration$SelfieCapture;", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SelfieCapture> serializer() {
                return SdkConfiguration$SelfieCapture$$serializer.INSTANCE;
            }

            public final SelfieCapture getDEFAULT() {
                return SelfieCapture.DEFAULT;
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "", "seen1", "", "videoSettings", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;)V", "getVideoSettings$annotations", "()V", "getVideoSettings", "()Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "MotionVideoSettings", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MotionCapture {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final MotionCapture DEFAULT = new MotionCapture(new MotionVideoSettings(true));
        private final MotionVideoSettings videoSettings;

        public static /* synthetic */ MotionCapture copy$default(MotionCapture motionCapture, MotionVideoSettings motionVideoSettings, int i, Object obj) {
            if ((i & 1) != 0) {
                motionVideoSettings = motionCapture.videoSettings;
            }
            return motionCapture.copy(motionVideoSettings);
        }

        @SerialName(SdkConfiguration.FIELD_MOTION_VIDEO_SETTINGS)
        public static /* synthetic */ void getVideoSettings$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final MotionVideoSettings getVideoSettings() {
            return this.videoSettings;
        }

        public final MotionCapture copy(MotionVideoSettings videoSettings) {
            Intrinsics.checkNotNullParameter(videoSettings, "videoSettings");
            return new MotionCapture(videoSettings);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MotionCapture) && Intrinsics.areEqual(this.videoSettings, ((MotionCapture) other).videoSettings);
        }

        public final MotionVideoSettings getVideoSettings() {
            return this.videoSettings;
        }

        public int hashCode() {
            return this.videoSettings.hashCode();
        }

        public String toString() {
            return "MotionCapture(videoSettings=" + this.videoSettings + ")";
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ MotionCapture(int i, @SerialName(SdkConfiguration.FIELD_MOTION_VIDEO_SETTINGS) MotionVideoSettings motionVideoSettings, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, SdkConfiguration$MotionCapture$$serializer.INSTANCE.getDescriptor());
            }
            this.videoSettings = motionVideoSettings;
        }

        public MotionCapture(MotionVideoSettings videoSettings) {
            Intrinsics.checkNotNullParameter(videoSettings, "videoSettings");
            this.videoSettings = videoSettings;
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "", "seen1", "", "exposureLock", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Z)V", "getExposureLock$annotations", "()V", "getExposureLock", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class MotionVideoSettings {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final boolean exposureLock;

            public MotionVideoSettings() {
                this(false, 1, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ MotionVideoSettings copy$default(MotionVideoSettings motionVideoSettings, boolean z, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = motionVideoSettings.exposureLock;
                }
                return motionVideoSettings.copy(z);
            }

            @SerialName("exposure_lock")
            public static /* synthetic */ void getExposureLock$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getExposureLock() {
                return this.exposureLock;
            }

            public final MotionVideoSettings copy(boolean exposureLock) {
                return new MotionVideoSettings(exposureLock);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof MotionVideoSettings) && this.exposureLock == ((MotionVideoSettings) other).exposureLock;
            }

            public final boolean getExposureLock() {
                return this.exposureLock;
            }

            public int hashCode() {
                return Boolean.hashCode(this.exposureLock);
            }

            public String toString() {
                return "MotionVideoSettings(exposureLock=" + this.exposureLock + ")";
            }

            /* compiled from: SdkConfiguration.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<MotionVideoSettings> serializer() {
                    return SdkConfiguration$MotionCapture$MotionVideoSettings$$serializer.INSTANCE;
                }
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ MotionVideoSettings(int i, @SerialName("exposure_lock") boolean z, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.exposureLock = true;
                } else {
                    this.exposureLock = z;
                }
            }

            public MotionVideoSettings(boolean z) {
                this.exposureLock = z;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$onfido_api_client(MotionVideoSettings self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.exposureLock) {
                    return;
                }
                output.encodeBooleanElement(serialDesc, 0, self.exposureLock);
            }

            public /* synthetic */ MotionVideoSettings(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? true : z);
            }
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$Companion;", "", "()V", "DEFAULT", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "getDEFAULT", "()Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture;", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MotionCapture> serializer() {
                return SdkConfiguration$MotionCapture$$serializer.INSTANCE;
            }

            public final MotionCapture getDEFAULT() {
                return MotionCapture.DEFAULT;
            }
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223Ba\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0001\u0010\f\u001a\u00020\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fBG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003JO\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010%\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0003HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001J&\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200HÁ\u0001¢\u0006\u0002\b1R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0007\u0010\u0014R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0012\u001a\u0004\b\n\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0004\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0006\u0010\u0014R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u000b\u0010\u0014R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u001c¨\u00064"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;", "", "seen1", "", "isInhouseAnalyticsEnabled", "", "isPerformanceAnalyticsEnabled", "isApplicantConsentRequired", "loggerConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "isDocumentSupportRulesEnabled", "isRefactoredCaptureEnabled", "enableWebModuleBasedPoa", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZZLcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;ZZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZZLcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;ZZZ)V", "getEnableWebModuleBasedPoa$annotations", "()V", "getEnableWebModuleBasedPoa", "()Z", "isApplicantConsentRequired$annotations", "isDocumentSupportRulesEnabled$annotations", "isInhouseAnalyticsEnabled$annotations", "isPerformanceAnalyticsEnabled$annotations", "isRefactoredCaptureEnabled$annotations", "getLoggerConfiguration$annotations", "getLoggerConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SdkFeatures {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean enableWebModuleBasedPoa;
        private final boolean isApplicantConsentRequired;
        private final boolean isDocumentSupportRulesEnabled;
        private final boolean isInhouseAnalyticsEnabled;
        private final boolean isPerformanceAnalyticsEnabled;
        private final boolean isRefactoredCaptureEnabled;
        private final LoggerConfiguration loggerConfiguration;

        public static /* synthetic */ SdkFeatures copy$default(SdkFeatures sdkFeatures, boolean z, boolean z2, boolean z3, LoggerConfiguration loggerConfiguration, boolean z4, boolean z5, boolean z6, int i, Object obj) {
            if ((i & 1) != 0) {
                z = sdkFeatures.isInhouseAnalyticsEnabled;
            }
            if ((i & 2) != 0) {
                z2 = sdkFeatures.isPerformanceAnalyticsEnabled;
            }
            boolean z7 = z2;
            if ((i & 4) != 0) {
                z3 = sdkFeatures.isApplicantConsentRequired;
            }
            boolean z8 = z3;
            if ((i & 8) != 0) {
                loggerConfiguration = sdkFeatures.loggerConfiguration;
            }
            LoggerConfiguration loggerConfiguration2 = loggerConfiguration;
            if ((i & 16) != 0) {
                z4 = sdkFeatures.isDocumentSupportRulesEnabled;
            }
            boolean z9 = z4;
            if ((i & 32) != 0) {
                z5 = sdkFeatures.isRefactoredCaptureEnabled;
            }
            boolean z10 = z5;
            if ((i & 64) != 0) {
                z6 = sdkFeatures.enableWebModuleBasedPoa;
            }
            return sdkFeatures.copy(z, z7, z8, loggerConfiguration2, z9, z10, z6);
        }

        @SerialName(SdkConfiguration.FIELD_ENABLE_WEB_MODULE_BASED_POA)
        public static /* synthetic */ void getEnableWebModuleBasedPoa$annotations() {
        }

        @SerialName("logger")
        public static /* synthetic */ void getLoggerConfiguration$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_ENABLE_REQUIRE_APPLICANT_CONSENTS)
        public static /* synthetic */ void isApplicantConsentRequired$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_ENABLE_DOCUMENT_SUPPORT_RULES)
        public static /* synthetic */ void isDocumentSupportRulesEnabled$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_ENABLE_IN_HOUSE_ANALYTICS)
        public static /* synthetic */ void isInhouseAnalyticsEnabled$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_ENABLE_PERFORMANCE_ANALYTICS)
        public static /* synthetic */ void isPerformanceAnalyticsEnabled$annotations() {
        }

        @SerialName(SdkConfiguration.FIELD_REFACTORED_CAPTURE)
        public static /* synthetic */ void isRefactoredCaptureEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsInhouseAnalyticsEnabled() {
            return this.isInhouseAnalyticsEnabled;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsPerformanceAnalyticsEnabled() {
            return this.isPerformanceAnalyticsEnabled;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsApplicantConsentRequired() {
            return this.isApplicantConsentRequired;
        }

        /* renamed from: component4, reason: from getter */
        public final LoggerConfiguration getLoggerConfiguration() {
            return this.loggerConfiguration;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsDocumentSupportRulesEnabled() {
            return this.isDocumentSupportRulesEnabled;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getIsRefactoredCaptureEnabled() {
            return this.isRefactoredCaptureEnabled;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getEnableWebModuleBasedPoa() {
            return this.enableWebModuleBasedPoa;
        }

        public final SdkFeatures copy(boolean isInhouseAnalyticsEnabled, boolean isPerformanceAnalyticsEnabled, boolean isApplicantConsentRequired, LoggerConfiguration loggerConfiguration, boolean isDocumentSupportRulesEnabled, boolean isRefactoredCaptureEnabled, boolean enableWebModuleBasedPoa) {
            Intrinsics.checkNotNullParameter(loggerConfiguration, "loggerConfiguration");
            return new SdkFeatures(isInhouseAnalyticsEnabled, isPerformanceAnalyticsEnabled, isApplicantConsentRequired, loggerConfiguration, isDocumentSupportRulesEnabled, isRefactoredCaptureEnabled, enableWebModuleBasedPoa);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SdkFeatures)) {
                return false;
            }
            SdkFeatures sdkFeatures = (SdkFeatures) other;
            return this.isInhouseAnalyticsEnabled == sdkFeatures.isInhouseAnalyticsEnabled && this.isPerformanceAnalyticsEnabled == sdkFeatures.isPerformanceAnalyticsEnabled && this.isApplicantConsentRequired == sdkFeatures.isApplicantConsentRequired && Intrinsics.areEqual(this.loggerConfiguration, sdkFeatures.loggerConfiguration) && this.isDocumentSupportRulesEnabled == sdkFeatures.isDocumentSupportRulesEnabled && this.isRefactoredCaptureEnabled == sdkFeatures.isRefactoredCaptureEnabled && this.enableWebModuleBasedPoa == sdkFeatures.enableWebModuleBasedPoa;
        }

        public final boolean getEnableWebModuleBasedPoa() {
            return this.enableWebModuleBasedPoa;
        }

        public final LoggerConfiguration getLoggerConfiguration() {
            return this.loggerConfiguration;
        }

        public int hashCode() {
            return (((((((((((Boolean.hashCode(this.isInhouseAnalyticsEnabled) * 31) + Boolean.hashCode(this.isPerformanceAnalyticsEnabled)) * 31) + Boolean.hashCode(this.isApplicantConsentRequired)) * 31) + this.loggerConfiguration.hashCode()) * 31) + Boolean.hashCode(this.isDocumentSupportRulesEnabled)) * 31) + Boolean.hashCode(this.isRefactoredCaptureEnabled)) * 31) + Boolean.hashCode(this.enableWebModuleBasedPoa);
        }

        public final boolean isApplicantConsentRequired() {
            return this.isApplicantConsentRequired;
        }

        public final boolean isDocumentSupportRulesEnabled() {
            return this.isDocumentSupportRulesEnabled;
        }

        public final boolean isInhouseAnalyticsEnabled() {
            return this.isInhouseAnalyticsEnabled;
        }

        public final boolean isPerformanceAnalyticsEnabled() {
            return this.isPerformanceAnalyticsEnabled;
        }

        public final boolean isRefactoredCaptureEnabled() {
            return this.isRefactoredCaptureEnabled;
        }

        public String toString() {
            return "SdkFeatures(isInhouseAnalyticsEnabled=" + this.isInhouseAnalyticsEnabled + ", isPerformanceAnalyticsEnabled=" + this.isPerformanceAnalyticsEnabled + ", isApplicantConsentRequired=" + this.isApplicantConsentRequired + ", loggerConfiguration=" + this.loggerConfiguration + ", isDocumentSupportRulesEnabled=" + this.isDocumentSupportRulesEnabled + ", isRefactoredCaptureEnabled=" + this.isRefactoredCaptureEnabled + ", enableWebModuleBasedPoa=" + this.enableWebModuleBasedPoa + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$SdkFeatures;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SdkFeatures> serializer() {
                return SdkConfiguration$SdkFeatures$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ SdkFeatures(int i, @SerialName(SdkConfiguration.FIELD_ENABLE_IN_HOUSE_ANALYTICS) boolean z, @SerialName(SdkConfiguration.FIELD_ENABLE_PERFORMANCE_ANALYTICS) boolean z2, @SerialName(SdkConfiguration.FIELD_ENABLE_REQUIRE_APPLICANT_CONSENTS) boolean z3, @SerialName("logger") LoggerConfiguration loggerConfiguration, @SerialName(SdkConfiguration.FIELD_ENABLE_DOCUMENT_SUPPORT_RULES) boolean z4, @SerialName(SdkConfiguration.FIELD_REFACTORED_CAPTURE) boolean z5, @SerialName(SdkConfiguration.FIELD_ENABLE_WEB_MODULE_BASED_POA) boolean z6, SerializationConstructorMarker serializationConstructorMarker) {
            if (5 != (i & 5)) {
                PluginExceptionsKt.throwMissingFieldException(i, 5, SdkConfiguration$SdkFeatures$$serializer.INSTANCE.getDescriptor());
            }
            this.isInhouseAnalyticsEnabled = z;
            if ((i & 2) == 0) {
                this.isPerformanceAnalyticsEnabled = false;
            } else {
                this.isPerformanceAnalyticsEnabled = z2;
            }
            this.isApplicantConsentRequired = z3;
            if ((i & 8) == 0) {
                this.loggerConfiguration = new LoggerConfiguration(false, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.loggerConfiguration = loggerConfiguration;
            }
            if ((i & 16) == 0) {
                this.isDocumentSupportRulesEnabled = false;
            } else {
                this.isDocumentSupportRulesEnabled = z4;
            }
            if ((i & 32) == 0) {
                this.isRefactoredCaptureEnabled = false;
            } else {
                this.isRefactoredCaptureEnabled = z5;
            }
            if ((i & 64) == 0) {
                this.enableWebModuleBasedPoa = false;
            } else {
                this.enableWebModuleBasedPoa = z6;
            }
        }

        public SdkFeatures(boolean z, boolean z2, boolean z3, LoggerConfiguration loggerConfiguration, boolean z4, boolean z5, boolean z6) {
            Intrinsics.checkNotNullParameter(loggerConfiguration, "loggerConfiguration");
            this.isInhouseAnalyticsEnabled = z;
            this.isPerformanceAnalyticsEnabled = z2;
            this.isApplicantConsentRequired = z3;
            this.loggerConfiguration = loggerConfiguration;
            this.isDocumentSupportRulesEnabled = z4;
            this.isRefactoredCaptureEnabled = z5;
            this.enableWebModuleBasedPoa = z6;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(SdkFeatures self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeBooleanElement(serialDesc, 0, self.isInhouseAnalyticsEnabled);
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.isPerformanceAnalyticsEnabled) {
                output.encodeBooleanElement(serialDesc, 1, self.isPerformanceAnalyticsEnabled);
            }
            output.encodeBooleanElement(serialDesc, 2, self.isApplicantConsentRequired);
            if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.loggerConfiguration, new LoggerConfiguration(false, (List) null, (List) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 3, SdkConfiguration$LoggerConfiguration$$serializer.INSTANCE, self.loggerConfiguration);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.isDocumentSupportRulesEnabled) {
                output.encodeBooleanElement(serialDesc, 4, self.isDocumentSupportRulesEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.isRefactoredCaptureEnabled) {
                output.encodeBooleanElement(serialDesc, 5, self.isRefactoredCaptureEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.enableWebModuleBasedPoa) {
                output.encodeBooleanElement(serialDesc, 6, self.enableWebModuleBasedPoa);
            }
        }

        public /* synthetic */ SdkFeatures(boolean z, boolean z2, boolean z3, LoggerConfiguration loggerConfiguration, boolean z4, boolean z5, boolean z6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? false : z2, z3, (i & 8) != 0 ? new LoggerConfiguration(false, (List) null, (List) null, 7, (DefaultConstructorMarker) null) : loggerConfiguration, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? false : z5, (i & 64) != 0 ? false : z6);
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'BG\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB/\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\rJ\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J3\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÁ\u0001¢\u0006\u0002\b%R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0004\u0010\u0010R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0013¨\u0006("}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "", "seen1", "", "isEnabled", "", "labels", "", "", "levels", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/util/List;Ljava/util/List;)V", "isEnabled$annotations", "()V", "()Z", "getLabels$annotations", "getLabels", "()Ljava/util/List;", "getLevels$annotations", "getLevels", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LoggerConfiguration {
        private final boolean isEnabled;
        private final List<String> labels;
        private final List<String> levels;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final KSerializer<Object>[] $childSerializers = {null, new ArrayListSerializer(StringSerializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE)};

        public LoggerConfiguration() {
            this(false, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LoggerConfiguration copy$default(LoggerConfiguration loggerConfiguration, boolean z, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = loggerConfiguration.isEnabled;
            }
            if ((i & 2) != 0) {
                list = loggerConfiguration.labels;
            }
            if ((i & 4) != 0) {
                list2 = loggerConfiguration.levels;
            }
            return loggerConfiguration.copy(z, list, list2);
        }

        @SerialName("labels")
        public static /* synthetic */ void getLabels$annotations() {
        }

        @SerialName("levels")
        public static /* synthetic */ void getLevels$annotations() {
        }

        @SerialName(ViewProps.ENABLED)
        public static /* synthetic */ void isEnabled$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsEnabled() {
            return this.isEnabled;
        }

        public final List<String> component2() {
            return this.labels;
        }

        public final List<String> component3() {
            return this.levels;
        }

        public final LoggerConfiguration copy(boolean isEnabled, List<String> labels, List<String> levels) {
            Intrinsics.checkNotNullParameter(labels, "labels");
            Intrinsics.checkNotNullParameter(levels, "levels");
            return new LoggerConfiguration(isEnabled, labels, levels);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoggerConfiguration)) {
                return false;
            }
            LoggerConfiguration loggerConfiguration = (LoggerConfiguration) other;
            return this.isEnabled == loggerConfiguration.isEnabled && Intrinsics.areEqual(this.labels, loggerConfiguration.labels) && Intrinsics.areEqual(this.levels, loggerConfiguration.levels);
        }

        public final List<String> getLabels() {
            return this.labels;
        }

        public final List<String> getLevels() {
            return this.levels;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.isEnabled) * 31) + this.labels.hashCode()) * 31) + this.levels.hashCode();
        }

        public final boolean isEnabled() {
            return this.isEnabled;
        }

        public String toString() {
            return "LoggerConfiguration(isEnabled=" + this.isEnabled + ", labels=" + this.labels + ", levels=" + this.levels + ")";
        }

        /* compiled from: SdkConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration$LoggerConfiguration;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LoggerConfiguration> serializer() {
                return SdkConfiguration$LoggerConfiguration$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ LoggerConfiguration(int i, @SerialName(ViewProps.ENABLED) boolean z, @SerialName("labels") List list, @SerialName("levels") List list2, SerializationConstructorMarker serializationConstructorMarker) {
            this.isEnabled = (i & 1) == 0 ? true : z;
            if ((i & 2) == 0) {
                this.labels = CollectionsKt.emptyList();
            } else {
                this.labels = list;
            }
            if ((i & 4) == 0) {
                this.levels = CollectionsKt.listOf("error");
            } else {
                this.levels = list2;
            }
        }

        public LoggerConfiguration(boolean z, List<String> labels, List<String> levels) {
            Intrinsics.checkNotNullParameter(labels, "labels");
            Intrinsics.checkNotNullParameter(levels, "levels");
            this.isEnabled = z;
            this.labels = labels;
            this.levels = levels;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(LoggerConfiguration self, CompositeEncoder output, SerialDescriptor serialDesc) {
            KSerializer<Object>[] kSerializerArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !self.isEnabled) {
                output.encodeBooleanElement(serialDesc, 0, self.isEnabled);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.labels, CollectionsKt.emptyList())) {
                output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.labels);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.levels, CollectionsKt.listOf("error"))) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.levels);
        }

        public /* synthetic */ LoggerConfiguration(boolean z, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.listOf("error") : list2);
        }
    }

    /* compiled from: SdkConfiguration.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/api/client/data/SdkConfiguration$Companion;", "", "()V", "FIELD_ENABLE_DOCUMENT_SUPPORT_RULES", "", "FIELD_ENABLE_IN_HOUSE_ANALYTICS", "FIELD_ENABLE_PERFORMANCE_ANALYTICS", "FIELD_ENABLE_REQUIRE_APPLICANT_CONSENTS", "FIELD_ENABLE_WEB_MODULE_BASED_POA", "FIELD_IS_PAYLOAD_SIGNING_ENABLED", "FIELD_LIVENESS_CAPTURE", "FIELD_LOGGER_CONFIGURATION", "FIELD_MOTION_CAPTURE", "FIELD_MOTION_VIDEO_SETTINGS", "FIELD_REFACTORED_CAPTURE", "FIELD_SELFIE_CAPTURE", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/SdkConfiguration;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SdkConfiguration> serializer() {
            return SdkConfiguration$$serializer.INSTANCE;
        }
    }
}
