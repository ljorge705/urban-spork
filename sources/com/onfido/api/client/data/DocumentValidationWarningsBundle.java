package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;
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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: DocumentValidationWarningsBundle.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 D2\u00020\u0001:\u0003CDEBm\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fBY\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0010J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\fHÆ\u0003J]\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0006\u0010/\u001a\u00020+J\u0006\u00100\u001a\u00020+J\u0006\u00101\u001a\u00020+J\u0006\u00102\u001a\u00020+J\u0006\u00103\u001a\u00020+J\b\u00104\u001a\u00020+H\u0002J\u0006\u00105\u001a\u00020+J\u0006\u00106\u001a\u00020+J\u0006\u00107\u001a\u00020+J\u0006\u00108\u001a\u00020+J\t\u00109\u001a\u00020\u0003HÖ\u0001J\t\u0010:\u001a\u00020.HÖ\u0001J&\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AHÁ\u0001¢\u0006\u0002\bBR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u0014R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u0014R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u0014¨\u0006F"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "", "seen1", "", "glareResult", "Lcom/onfido/api/client/data/ValidationResult;", "blurResult", "cutoffResult", "documentResult", "barcodeResult", "originalDocumentPresentResult", "imageQuality", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/ValidationResult;Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;)V", "getBarcodeResult$annotations", "()V", "getBarcodeResult", "()Lcom/onfido/api/client/data/ValidationResult;", "getBlurResult$annotations", "getBlurResult", "getCutoffResult$annotations", "getCutoffResult", "getDocumentResult$annotations", "getDocumentResult", "getGlareResult$annotations", "getGlareResult", "getImageQuality$annotations", "getImageQuality", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;", "getOriginalDocumentPresentResult$annotations", "getOriginalDocumentPresentResult", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "", "other", "getOriginalDocumentPresentWarningReason", "", "hasBarcodeWarning", "hasBlurWarning", "hasCutoffWarning", "hasDocumentWarning", "hasGlareWarning", "hasOriginalDocumentPresentWarning", "hasPhotoOfScreenWarning", "hasPhotocopyWarning", "hasScanWarning", "hasScreenshotWarning", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "ImageQuality", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class DocumentValidationWarningsBundle {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String ODP_REASON_PHOTOCOPY = "document_on_printed_paper";

    @Deprecated
    public static final String ODP_REASON_PHOTO_OF_SCREEN = "photo_of_screen";

    @Deprecated
    public static final String ODP_REASON_SCAN = "scan";

    @Deprecated
    public static final String ODP_REASON_SCREENSHOT = "screenshot";
    private final ValidationResult barcodeResult;
    private final ValidationResult blurResult;
    private final ValidationResult cutoffResult;
    private final ValidationResult documentResult;
    private final ValidationResult glareResult;
    private final ImageQuality imageQuality;
    private final ValidationResult originalDocumentPresentResult;

    public DocumentValidationWarningsBundle() {
        this((ValidationResult) null, (ValidationResult) null, (ValidationResult) null, (ValidationResult) null, (ValidationResult) null, (ValidationResult) null, (ImageQuality) null, 127, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DocumentValidationWarningsBundle copy$default(DocumentValidationWarningsBundle documentValidationWarningsBundle, ValidationResult validationResult, ValidationResult validationResult2, ValidationResult validationResult3, ValidationResult validationResult4, ValidationResult validationResult5, ValidationResult validationResult6, ImageQuality imageQuality, int i, Object obj) {
        if ((i & 1) != 0) {
            validationResult = documentValidationWarningsBundle.glareResult;
        }
        if ((i & 2) != 0) {
            validationResult2 = documentValidationWarningsBundle.blurResult;
        }
        ValidationResult validationResult7 = validationResult2;
        if ((i & 4) != 0) {
            validationResult3 = documentValidationWarningsBundle.cutoffResult;
        }
        ValidationResult validationResult8 = validationResult3;
        if ((i & 8) != 0) {
            validationResult4 = documentValidationWarningsBundle.documentResult;
        }
        ValidationResult validationResult9 = validationResult4;
        if ((i & 16) != 0) {
            validationResult5 = documentValidationWarningsBundle.barcodeResult;
        }
        ValidationResult validationResult10 = validationResult5;
        if ((i & 32) != 0) {
            validationResult6 = documentValidationWarningsBundle.originalDocumentPresentResult;
        }
        ValidationResult validationResult11 = validationResult6;
        if ((i & 64) != 0) {
            imageQuality = documentValidationWarningsBundle.imageQuality;
        }
        return documentValidationWarningsBundle.copy(validationResult, validationResult7, validationResult8, validationResult9, validationResult10, validationResult11, imageQuality);
    }

    @SerialName(IQSUploadErrorParser.BARCODE_DETECTION_ERROR_KEY)
    public static /* synthetic */ void getBarcodeResult$annotations() {
    }

    @SerialName(IQSUploadErrorParser.BLUR_DETECTION_ERROR_KEY)
    public static /* synthetic */ void getBlurResult$annotations() {
    }

    @SerialName(IQSUploadErrorParser.CUTOFF_DETECTION_ERROR_KEY)
    public static /* synthetic */ void getCutoffResult$annotations() {
    }

    @SerialName("detect_document")
    public static /* synthetic */ void getDocumentResult$annotations() {
    }

    @SerialName(IQSUploadErrorParser.GLARE_DETECTION_ERROR_KEY)
    public static /* synthetic */ void getGlareResult$annotations() {
    }

    @SerialName("image_quality")
    public static /* synthetic */ void getImageQuality$annotations() {
    }

    @SerialName(IQSUploadErrorParser.ORIGINAL_DOCUMENT_DETECTION_ERROR_KEY)
    public static /* synthetic */ void getOriginalDocumentPresentResult$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final ValidationResult getGlareResult() {
        return this.glareResult;
    }

    /* renamed from: component2, reason: from getter */
    public final ValidationResult getBlurResult() {
        return this.blurResult;
    }

    /* renamed from: component3, reason: from getter */
    public final ValidationResult getCutoffResult() {
        return this.cutoffResult;
    }

    /* renamed from: component4, reason: from getter */
    public final ValidationResult getDocumentResult() {
        return this.documentResult;
    }

    /* renamed from: component5, reason: from getter */
    public final ValidationResult getBarcodeResult() {
        return this.barcodeResult;
    }

    /* renamed from: component6, reason: from getter */
    public final ValidationResult getOriginalDocumentPresentResult() {
        return this.originalDocumentPresentResult;
    }

    /* renamed from: component7, reason: from getter */
    public final ImageQuality getImageQuality() {
        return this.imageQuality;
    }

    public final DocumentValidationWarningsBundle copy(ValidationResult glareResult, ValidationResult blurResult, ValidationResult cutoffResult, ValidationResult documentResult, ValidationResult barcodeResult, ValidationResult originalDocumentPresentResult, ImageQuality imageQuality) {
        return new DocumentValidationWarningsBundle(glareResult, blurResult, cutoffResult, documentResult, barcodeResult, originalDocumentPresentResult, imageQuality);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentValidationWarningsBundle)) {
            return false;
        }
        DocumentValidationWarningsBundle documentValidationWarningsBundle = (DocumentValidationWarningsBundle) other;
        return Intrinsics.areEqual(this.glareResult, documentValidationWarningsBundle.glareResult) && Intrinsics.areEqual(this.blurResult, documentValidationWarningsBundle.blurResult) && Intrinsics.areEqual(this.cutoffResult, documentValidationWarningsBundle.cutoffResult) && Intrinsics.areEqual(this.documentResult, documentValidationWarningsBundle.documentResult) && Intrinsics.areEqual(this.barcodeResult, documentValidationWarningsBundle.barcodeResult) && Intrinsics.areEqual(this.originalDocumentPresentResult, documentValidationWarningsBundle.originalDocumentPresentResult) && Intrinsics.areEqual(this.imageQuality, documentValidationWarningsBundle.imageQuality);
    }

    public final ValidationResult getBarcodeResult() {
        return this.barcodeResult;
    }

    public final ValidationResult getBlurResult() {
        return this.blurResult;
    }

    public final ValidationResult getCutoffResult() {
        return this.cutoffResult;
    }

    public final ValidationResult getDocumentResult() {
        return this.documentResult;
    }

    public final ValidationResult getGlareResult() {
        return this.glareResult;
    }

    public final ImageQuality getImageQuality() {
        return this.imageQuality;
    }

    public final ValidationResult getOriginalDocumentPresentResult() {
        return this.originalDocumentPresentResult;
    }

    public int hashCode() {
        ValidationResult validationResult = this.glareResult;
        int iHashCode = (validationResult == null ? 0 : validationResult.hashCode()) * 31;
        ValidationResult validationResult2 = this.blurResult;
        int iHashCode2 = (iHashCode + (validationResult2 == null ? 0 : validationResult2.hashCode())) * 31;
        ValidationResult validationResult3 = this.cutoffResult;
        int iHashCode3 = (iHashCode2 + (validationResult3 == null ? 0 : validationResult3.hashCode())) * 31;
        ValidationResult validationResult4 = this.documentResult;
        int iHashCode4 = (iHashCode3 + (validationResult4 == null ? 0 : validationResult4.hashCode())) * 31;
        ValidationResult validationResult5 = this.barcodeResult;
        int iHashCode5 = (iHashCode4 + (validationResult5 == null ? 0 : validationResult5.hashCode())) * 31;
        ValidationResult validationResult6 = this.originalDocumentPresentResult;
        int iHashCode6 = (iHashCode5 + (validationResult6 == null ? 0 : validationResult6.hashCode())) * 31;
        ImageQuality imageQuality = this.imageQuality;
        return iHashCode6 + (imageQuality != null ? imageQuality.hashCode() : 0);
    }

    public String toString() {
        return "DocumentValidationWarningsBundle(glareResult=" + this.glareResult + ", blurResult=" + this.blurResult + ", cutoffResult=" + this.cutoffResult + ", documentResult=" + this.documentResult + ", barcodeResult=" + this.barcodeResult + ", originalDocumentPresentResult=" + this.originalDocumentPresentResult + ", imageQuality=" + this.imageQuality + ")";
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ DocumentValidationWarningsBundle(int i, @SerialName(IQSUploadErrorParser.GLARE_DETECTION_ERROR_KEY) ValidationResult validationResult, @SerialName(IQSUploadErrorParser.BLUR_DETECTION_ERROR_KEY) ValidationResult validationResult2, @SerialName(IQSUploadErrorParser.CUTOFF_DETECTION_ERROR_KEY) ValidationResult validationResult3, @SerialName("detect_document") ValidationResult validationResult4, @SerialName(IQSUploadErrorParser.BARCODE_DETECTION_ERROR_KEY) ValidationResult validationResult5, @SerialName(IQSUploadErrorParser.ORIGINAL_DOCUMENT_DETECTION_ERROR_KEY) ValidationResult validationResult6, @SerialName("image_quality") ImageQuality imageQuality, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.glareResult = null;
        } else {
            this.glareResult = validationResult;
        }
        if ((i & 2) == 0) {
            this.blurResult = null;
        } else {
            this.blurResult = validationResult2;
        }
        if ((i & 4) == 0) {
            this.cutoffResult = null;
        } else {
            this.cutoffResult = validationResult3;
        }
        if ((i & 8) == 0) {
            this.documentResult = null;
        } else {
            this.documentResult = validationResult4;
        }
        if ((i & 16) == 0) {
            this.barcodeResult = null;
        } else {
            this.barcodeResult = validationResult5;
        }
        if ((i & 32) == 0) {
            this.originalDocumentPresentResult = null;
        } else {
            this.originalDocumentPresentResult = validationResult6;
        }
        if ((i & 64) == 0) {
            this.imageQuality = null;
        } else {
            this.imageQuality = imageQuality;
        }
    }

    public DocumentValidationWarningsBundle(ValidationResult validationResult, ValidationResult validationResult2, ValidationResult validationResult3, ValidationResult validationResult4, ValidationResult validationResult5, ValidationResult validationResult6, ImageQuality imageQuality) {
        this.glareResult = validationResult;
        this.blurResult = validationResult2;
        this.cutoffResult = validationResult3;
        this.documentResult = validationResult4;
        this.barcodeResult = validationResult5;
        this.originalDocumentPresentResult = validationResult6;
        this.imageQuality = imageQuality;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(DocumentValidationWarningsBundle self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.glareResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, ValidationResult$$serializer.INSTANCE, self.glareResult);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.blurResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, ValidationResult$$serializer.INSTANCE, self.blurResult);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.cutoffResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, ValidationResult$$serializer.INSTANCE, self.cutoffResult);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.documentResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, ValidationResult$$serializer.INSTANCE, self.documentResult);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.barcodeResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, ValidationResult$$serializer.INSTANCE, self.barcodeResult);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.originalDocumentPresentResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, ValidationResult$$serializer.INSTANCE, self.originalDocumentPresentResult);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 6) && self.imageQuality == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 6, DocumentValidationWarningsBundle$ImageQuality$$serializer.INSTANCE, self.imageQuality);
    }

    public /* synthetic */ DocumentValidationWarningsBundle(ValidationResult validationResult, ValidationResult validationResult2, ValidationResult validationResult3, ValidationResult validationResult4, ValidationResult validationResult5, ValidationResult validationResult6, ImageQuality imageQuality, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : validationResult, (i & 2) != 0 ? null : validationResult2, (i & 4) != 0 ? null : validationResult3, (i & 8) != 0 ? null : validationResult4, (i & 16) != 0 ? null : validationResult5, (i & 32) != 0 ? null : validationResult6, (i & 64) != 0 ? null : imageQuality);
    }

    public final boolean hasGlareWarning() {
        ValidationResult validationResult = this.glareResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    public final boolean hasBlurWarning() {
        ValidationResult validationResult = this.blurResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    public final boolean hasCutoffWarning() {
        ValidationResult validationResult = this.cutoffResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    public final boolean hasDocumentWarning() {
        ValidationResult validationResult = this.documentResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    public final boolean hasBarcodeWarning() {
        ValidationResult validationResult = this.barcodeResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    public final boolean hasPhotoOfScreenWarning() {
        return hasOriginalDocumentPresentWarning() && Intrinsics.areEqual(getOriginalDocumentPresentWarningReason(), "photo_of_screen");
    }

    public final boolean hasScreenshotWarning() {
        return hasOriginalDocumentPresentWarning() && Intrinsics.areEqual(getOriginalDocumentPresentWarningReason(), "screenshot");
    }

    public final boolean hasPhotocopyWarning() {
        return hasOriginalDocumentPresentWarning() && Intrinsics.areEqual(getOriginalDocumentPresentWarningReason(), "document_on_printed_paper");
    }

    public final boolean hasScanWarning() {
        return hasOriginalDocumentPresentWarning() && Intrinsics.areEqual(getOriginalDocumentPresentWarningReason(), "scan");
    }

    private final boolean hasOriginalDocumentPresentWarning() {
        ValidationResult validationResult = this.originalDocumentPresentResult;
        return (validationResult == null || validationResult.isValid()) ? false : true;
    }

    private final String getOriginalDocumentPresentWarningReason() {
        ImageQuality.Breakdown breakdown;
        ImageQuality.Breakdown.OriginalDocumentPresent originalDocumentPresent;
        ImageQuality imageQuality = this.imageQuality;
        if (imageQuality == null || (breakdown = imageQuality.getBreakdown()) == null || (originalDocumentPresent = breakdown.getOriginalDocumentPresent()) == null) {
            return null;
        }
        return originalDocumentPresent.getReason();
    }

    /* compiled from: DocumentValidationWarningsBundle.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;", "", "seen1", "", "breakdown", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;)V", "getBreakdown$annotations", "()V", "getBreakdown", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Breakdown", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ImageQuality {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final Breakdown breakdown;

        /* JADX WARN: Multi-variable type inference failed */
        public ImageQuality() {
            this((Breakdown) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ ImageQuality copy$default(ImageQuality imageQuality, Breakdown breakdown, int i, Object obj) {
            if ((i & 1) != 0) {
                breakdown = imageQuality.breakdown;
            }
            return imageQuality.copy(breakdown);
        }

        @SerialName("breakdown")
        public static /* synthetic */ void getBreakdown$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final Breakdown getBreakdown() {
            return this.breakdown;
        }

        public final ImageQuality copy(Breakdown breakdown) {
            return new ImageQuality(breakdown);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ImageQuality) && Intrinsics.areEqual(this.breakdown, ((ImageQuality) other).breakdown);
        }

        public final Breakdown getBreakdown() {
            return this.breakdown;
        }

        public int hashCode() {
            Breakdown breakdown = this.breakdown;
            if (breakdown == null) {
                return 0;
            }
            return breakdown.hashCode();
        }

        public String toString() {
            return "ImageQuality(breakdown=" + this.breakdown + ")";
        }

        /* compiled from: DocumentValidationWarningsBundle.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ImageQuality> serializer() {
                return DocumentValidationWarningsBundle$ImageQuality$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ImageQuality(int i, @SerialName("breakdown") Breakdown breakdown, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.breakdown = null;
            } else {
                this.breakdown = breakdown;
            }
        }

        public ImageQuality(Breakdown breakdown) {
            this.breakdown = breakdown;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(ImageQuality self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.breakdown == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, DocumentValidationWarningsBundle$ImageQuality$Breakdown$$serializer.INSTANCE, self.breakdown);
        }

        public /* synthetic */ ImageQuality(Breakdown breakdown, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : breakdown);
        }

        /* compiled from: DocumentValidationWarningsBundle.kt */
        @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001e\u001f B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J&\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÁ\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;", "", "seen1", "", "originalDocumentPresent", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;)V", "getOriginalDocumentPresent$annotations", "()V", "getOriginalDocumentPresent", "()Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "OriginalDocumentPresent", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @Serializable
        public static final /* data */ class Breakdown {

            /* renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final OriginalDocumentPresent originalDocumentPresent;

            /* JADX WARN: Multi-variable type inference failed */
            public Breakdown() {
                this((OriginalDocumentPresent) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            }

            public static /* synthetic */ Breakdown copy$default(Breakdown breakdown, OriginalDocumentPresent originalDocumentPresent, int i, Object obj) {
                if ((i & 1) != 0) {
                    originalDocumentPresent = breakdown.originalDocumentPresent;
                }
                return breakdown.copy(originalDocumentPresent);
            }

            @SerialName("original_document_present")
            public static /* synthetic */ void getOriginalDocumentPresent$annotations() {
            }

            /* renamed from: component1, reason: from getter */
            public final OriginalDocumentPresent getOriginalDocumentPresent() {
                return this.originalDocumentPresent;
            }

            public final Breakdown copy(OriginalDocumentPresent originalDocumentPresent) {
                return new Breakdown(originalDocumentPresent);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Breakdown) && Intrinsics.areEqual(this.originalDocumentPresent, ((Breakdown) other).originalDocumentPresent);
            }

            public final OriginalDocumentPresent getOriginalDocumentPresent() {
                return this.originalDocumentPresent;
            }

            public int hashCode() {
                OriginalDocumentPresent originalDocumentPresent = this.originalDocumentPresent;
                if (originalDocumentPresent == null) {
                    return 0;
                }
                return originalDocumentPresent.hashCode();
            }

            public String toString() {
                return "Breakdown(originalDocumentPresent=" + this.originalDocumentPresent + ")";
            }

            /* compiled from: DocumentValidationWarningsBundle.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Breakdown> serializer() {
                    return DocumentValidationWarningsBundle$ImageQuality$Breakdown$$serializer.INSTANCE;
                }
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ Breakdown(int i, @SerialName("original_document_present") OriginalDocumentPresent originalDocumentPresent, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 1) == 0) {
                    this.originalDocumentPresent = null;
                } else {
                    this.originalDocumentPresent = originalDocumentPresent;
                }
            }

            public Breakdown(OriginalDocumentPresent originalDocumentPresent) {
                this.originalDocumentPresent = originalDocumentPresent;
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$onfido_api_client(Breakdown self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.originalDocumentPresent == null) {
                    return;
                }
                output.encodeNullableSerializableElement(serialDesc, 0, DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent$$serializer.INSTANCE, self.originalDocumentPresent);
            }

            public /* synthetic */ Breakdown(OriginalDocumentPresent originalDocumentPresent, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : originalDocumentPresent);
            }

            /* compiled from: DocumentValidationWarningsBundle.kt */
            @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0015\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;", "", "seen1", "", "reason", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;)V", "getReason$annotations", "()V", "getReason", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
            @Serializable
            public static final /* data */ class OriginalDocumentPresent {

                /* renamed from: Companion, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);
                private final String reason;

                /* JADX WARN: Multi-variable type inference failed */
                public OriginalDocumentPresent() {
                    this((String) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
                }

                public static /* synthetic */ OriginalDocumentPresent copy$default(OriginalDocumentPresent originalDocumentPresent, String str, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = originalDocumentPresent.reason;
                    }
                    return originalDocumentPresent.copy(str);
                }

                @SerialName("reason")
                public static /* synthetic */ void getReason$annotations() {
                }

                /* renamed from: component1, reason: from getter */
                public final String getReason() {
                    return this.reason;
                }

                public final OriginalDocumentPresent copy(String reason) {
                    return new OriginalDocumentPresent(reason);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OriginalDocumentPresent) && Intrinsics.areEqual(this.reason, ((OriginalDocumentPresent) other).reason);
                }

                public final String getReason() {
                    return this.reason;
                }

                public int hashCode() {
                    String str = this.reason;
                    if (str == null) {
                        return 0;
                    }
                    return str.hashCode();
                }

                public String toString() {
                    return "OriginalDocumentPresent(reason=" + this.reason + ")";
                }

                /* compiled from: DocumentValidationWarningsBundle.kt */
                @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }

                    public final KSerializer<OriginalDocumentPresent> serializer() {
                        return DocumentValidationWarningsBundle$ImageQuality$Breakdown$OriginalDocumentPresent$$serializer.INSTANCE;
                    }
                }

                @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
                public /* synthetic */ OriginalDocumentPresent(int i, @SerialName("reason") String str, SerializationConstructorMarker serializationConstructorMarker) {
                    if ((i & 1) == 0) {
                        this.reason = null;
                    } else {
                        this.reason = str;
                    }
                }

                public OriginalDocumentPresent(String str) {
                    this.reason = str;
                }

                @JvmStatic
                public static final /* synthetic */ void write$Self$onfido_api_client(OriginalDocumentPresent self, CompositeEncoder output, SerialDescriptor serialDesc) {
                    if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.reason == null) {
                        return;
                    }
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.reason);
                }

                public /* synthetic */ OriginalDocumentPresent(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? null : str);
                }
            }
        }
    }

    /* compiled from: DocumentValidationWarningsBundle.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/api/client/data/DocumentValidationWarningsBundle$Companion;", "", "()V", "ODP_REASON_PHOTOCOPY", "", "ODP_REASON_PHOTO_OF_SCREEN", "ODP_REASON_SCAN", "ODP_REASON_SCREENSHOT", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<DocumentValidationWarningsBundle> serializer() {
            return DocumentValidationWarningsBundle$$serializer.INSTANCE;
        }
    }
}
