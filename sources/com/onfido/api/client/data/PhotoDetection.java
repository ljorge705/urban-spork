package com.onfido.api.client.data;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: PhotoDetection.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0004*+,-BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB-\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J9\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001J&\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(HÁ\u0001¢\u0006\u0002\b)R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010¨\u0006."}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection;", "", "seen1", "", "glareDetection", "Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "blurDetection", "barcodeDetection", "mrzExtraction", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;)V", "getBarcodeDetection$annotations", "()V", "getBarcodeDetection", "()Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "getBlurDetection$annotations", "getBlurDetection", "getGlareDetection$annotations", "getGlareDetection", "getMrzExtraction$annotations", "getMrzExtraction", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "BarcodeProcessingResult", "Companion", "ProcessingResult", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class PhotoDetection {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ProcessingResult barcodeDetection;
    private final ProcessingResult blurDetection;
    private final ProcessingResult glareDetection;
    private final ProcessingResult mrzExtraction;

    public static /* synthetic */ PhotoDetection copy$default(PhotoDetection photoDetection, ProcessingResult processingResult, ProcessingResult processingResult2, ProcessingResult processingResult3, ProcessingResult processingResult4, int i, Object obj) {
        if ((i & 1) != 0) {
            processingResult = photoDetection.glareDetection;
        }
        if ((i & 2) != 0) {
            processingResult2 = photoDetection.blurDetection;
        }
        if ((i & 4) != 0) {
            processingResult3 = photoDetection.barcodeDetection;
        }
        if ((i & 8) != 0) {
            processingResult4 = photoDetection.mrzExtraction;
        }
        return photoDetection.copy(processingResult, processingResult2, processingResult3, processingResult4);
    }

    @SerialName("barcode_detection")
    public static /* synthetic */ void getBarcodeDetection$annotations() {
    }

    @SerialName("blur_detection")
    public static /* synthetic */ void getBlurDetection$annotations() {
    }

    @SerialName("glare_detection")
    public static /* synthetic */ void getGlareDetection$annotations() {
    }

    @SerialName("mrz_extraction")
    public static /* synthetic */ void getMrzExtraction$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final ProcessingResult getGlareDetection() {
        return this.glareDetection;
    }

    /* renamed from: component2, reason: from getter */
    public final ProcessingResult getBlurDetection() {
        return this.blurDetection;
    }

    /* renamed from: component3, reason: from getter */
    public final ProcessingResult getBarcodeDetection() {
        return this.barcodeDetection;
    }

    /* renamed from: component4, reason: from getter */
    public final ProcessingResult getMrzExtraction() {
        return this.mrzExtraction;
    }

    public final PhotoDetection copy(ProcessingResult glareDetection, ProcessingResult blurDetection, ProcessingResult barcodeDetection, ProcessingResult mrzExtraction) {
        return new PhotoDetection(glareDetection, blurDetection, barcodeDetection, mrzExtraction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhotoDetection)) {
            return false;
        }
        PhotoDetection photoDetection = (PhotoDetection) other;
        return Intrinsics.areEqual(this.glareDetection, photoDetection.glareDetection) && Intrinsics.areEqual(this.blurDetection, photoDetection.blurDetection) && Intrinsics.areEqual(this.barcodeDetection, photoDetection.barcodeDetection) && Intrinsics.areEqual(this.mrzExtraction, photoDetection.mrzExtraction);
    }

    public final ProcessingResult getBarcodeDetection() {
        return this.barcodeDetection;
    }

    public final ProcessingResult getBlurDetection() {
        return this.blurDetection;
    }

    public final ProcessingResult getGlareDetection() {
        return this.glareDetection;
    }

    public final ProcessingResult getMrzExtraction() {
        return this.mrzExtraction;
    }

    public int hashCode() {
        ProcessingResult processingResult = this.glareDetection;
        int iHashCode = (processingResult == null ? 0 : processingResult.hashCode()) * 31;
        ProcessingResult processingResult2 = this.blurDetection;
        int iHashCode2 = (iHashCode + (processingResult2 == null ? 0 : processingResult2.hashCode())) * 31;
        ProcessingResult processingResult3 = this.barcodeDetection;
        int iHashCode3 = (iHashCode2 + (processingResult3 == null ? 0 : processingResult3.hashCode())) * 31;
        ProcessingResult processingResult4 = this.mrzExtraction;
        return iHashCode3 + (processingResult4 != null ? processingResult4.hashCode() : 0);
    }

    public String toString() {
        return "PhotoDetection(glareDetection=" + this.glareDetection + ", blurDetection=" + this.blurDetection + ", barcodeDetection=" + this.barcodeDetection + ", mrzExtraction=" + this.mrzExtraction + ")";
    }

    /* compiled from: PhotoDetection.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/PhotoDetection;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PhotoDetection> serializer() {
            return PhotoDetection$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ PhotoDetection(int i, @SerialName("glare_detection") ProcessingResult processingResult, @SerialName("blur_detection") ProcessingResult processingResult2, @SerialName("barcode_detection") ProcessingResult processingResult3, @SerialName("mrz_extraction") ProcessingResult processingResult4, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (i & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i, 15, PhotoDetection$$serializer.INSTANCE.getDescriptor());
        }
        this.glareDetection = processingResult;
        this.blurDetection = processingResult2;
        this.barcodeDetection = processingResult3;
        this.mrzExtraction = processingResult4;
    }

    public PhotoDetection(ProcessingResult processingResult, ProcessingResult processingResult2, ProcessingResult processingResult3, ProcessingResult processingResult4) {
        this.glareDetection = processingResult;
        this.blurDetection = processingResult2;
        this.barcodeDetection = processingResult3;
        this.mrzExtraction = processingResult4;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_api_client(PhotoDetection self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeNullableSerializableElement(serialDesc, 0, PhotoDetection$ProcessingResult$$serializer.INSTANCE, self.glareDetection);
        output.encodeNullableSerializableElement(serialDesc, 1, PhotoDetection$ProcessingResult$$serializer.INSTANCE, self.blurDetection);
        output.encodeNullableSerializableElement(serialDesc, 2, PhotoDetection$ProcessingResult$$serializer.INSTANCE, self.barcodeDetection);
        output.encodeNullableSerializableElement(serialDesc, 3, PhotoDetection$ProcessingResult$$serializer.INSTANCE, self.mrzExtraction);
    }

    /* compiled from: PhotoDetection.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u001a2\u00020\u0001:\u0002\u0019\u001aB/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J!\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018HÇ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\r\u0010\f¨\u0006\u001b\u0080å\b\u0004\u0080å\b\u0006"}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "", "seen1", "", "isValid", "", "retryCount", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/lang/Integer;)V", "isValid$annotations", "()V", "getRetryCount$annotations", "Ljava/lang/Integer;", "equals", "other", "hashCode", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static class ProcessingResult {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean isValid;
        private final Integer retryCount;

        @SerialName(AnalyticsPropertyKeys.RETRY_COUNT)
        private static /* synthetic */ void getRetryCount$annotations() {
        }

        @SerialName("is_valid")
        private static /* synthetic */ void isValid$annotations() {
        }

        /* compiled from: PhotoDetection.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ProcessingResult> serializer() {
                return PhotoDetection$ProcessingResult$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ProcessingResult(int i, @SerialName("is_valid") boolean z, @SerialName(AnalyticsPropertyKeys.RETRY_COUNT) Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, PhotoDetection$ProcessingResult$$serializer.INSTANCE.getDescriptor());
            }
            this.isValid = z;
            if ((i & 2) == 0) {
                this.retryCount = null;
            } else {
                this.retryCount = num;
            }
        }

        public ProcessingResult(boolean z, Integer num) {
            this.isValid = z;
            this.retryCount = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self(ProcessingResult self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeBooleanElement(serialDesc, 0, self.isValid);
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.retryCount == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.retryCount);
        }

        public /* synthetic */ ProcessingResult(boolean z, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : num);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProcessingResult)) {
                return false;
            }
            ProcessingResult processingResult = (ProcessingResult) other;
            return this.isValid == processingResult.isValid && Intrinsics.areEqual(this.retryCount, processingResult.retryCount);
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isValid) * 31;
            Integer num = this.retryCount;
            return iHashCode + (num != null ? num.intValue() : 0);
        }
    }

    /* compiled from: PhotoDetection.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%BE\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u0017\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÁ\u0001¢\u0006\u0002\b#R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0004\u0010\u0012¨\u0006&"}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection$BarcodeProcessingResult;", "Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "seen1", "", "isValid", "", "retryCount", "extractedInfo", "Lcom/onfido/api/client/data/ExtractedInfo;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/Integer;Lcom/onfido/api/client/data/ExtractedInfo;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/api/client/data/ExtractedInfo;Z)V", "getExtractedInfo$annotations", "()V", "getExtractedInfo", "()Lcom/onfido/api/client/data/ExtractedInfo;", "isValid$annotations", "()Z", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_api_client", "$serializer", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final /* data */ class BarcodeProcessingResult extends ProcessingResult {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final ExtractedInfo extractedInfo;
        private final boolean isValid;

        public static /* synthetic */ BarcodeProcessingResult copy$default(BarcodeProcessingResult barcodeProcessingResult, ExtractedInfo extractedInfo, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                extractedInfo = barcodeProcessingResult.extractedInfo;
            }
            if ((i & 2) != 0) {
                z = barcodeProcessingResult.isValid;
            }
            return barcodeProcessingResult.copy(extractedInfo, z);
        }

        @SerialName("extracted_info")
        public static /* synthetic */ void getExtractedInfo$annotations() {
        }

        @SerialName("isValid")
        public static /* synthetic */ void isValid$annotations() {
        }

        /* renamed from: component1, reason: from getter */
        public final ExtractedInfo getExtractedInfo() {
            return this.extractedInfo;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsValid() {
            return this.isValid;
        }

        public final BarcodeProcessingResult copy(ExtractedInfo extractedInfo, boolean isValid) {
            return new BarcodeProcessingResult(extractedInfo, isValid);
        }

        @Override // com.onfido.api.client.data.PhotoDetection.ProcessingResult
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BarcodeProcessingResult)) {
                return false;
            }
            BarcodeProcessingResult barcodeProcessingResult = (BarcodeProcessingResult) other;
            return Intrinsics.areEqual(this.extractedInfo, barcodeProcessingResult.extractedInfo) && this.isValid == barcodeProcessingResult.isValid;
        }

        public final ExtractedInfo getExtractedInfo() {
            return this.extractedInfo;
        }

        @Override // com.onfido.api.client.data.PhotoDetection.ProcessingResult
        public int hashCode() {
            ExtractedInfo extractedInfo = this.extractedInfo;
            return ((extractedInfo == null ? 0 : extractedInfo.hashCode()) * 31) + Boolean.hashCode(this.isValid);
        }

        public final boolean isValid() {
            return this.isValid;
        }

        public String toString() {
            return "BarcodeProcessingResult(extractedInfo=" + this.extractedInfo + ", isValid=" + this.isValid + ")";
        }

        /* compiled from: PhotoDetection.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/api/client/data/PhotoDetection$BarcodeProcessingResult$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/api/client/data/PhotoDetection$BarcodeProcessingResult;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<BarcodeProcessingResult> serializer() {
                return PhotoDetection$BarcodeProcessingResult$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ BarcodeProcessingResult(int i, @SerialName("is_valid") boolean z, @SerialName(AnalyticsPropertyKeys.RETRY_COUNT) Integer num, @SerialName("extracted_info") ExtractedInfo extractedInfo, @SerialName("isValid") boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i, z, num, serializationConstructorMarker);
            if (13 != (i & 13)) {
                PluginExceptionsKt.throwMissingFieldException(i, 13, PhotoDetection$BarcodeProcessingResult$$serializer.INSTANCE.getDescriptor());
            }
            this.extractedInfo = extractedInfo;
            this.isValid = z2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$onfido_api_client(BarcodeProcessingResult self, CompositeEncoder output, SerialDescriptor serialDesc) {
            ProcessingResult.write$Self(self, output, serialDesc);
            output.encodeNullableSerializableElement(serialDesc, 2, ExtractedInfo$$serializer.INSTANCE, self.extractedInfo);
            output.encodeBooleanElement(serialDesc, 3, self.isValid);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BarcodeProcessingResult(ExtractedInfo extractedInfo, boolean z) {
            super(z, (Integer) null, 2, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            this.extractedInfo = extractedInfo;
            this.isValid = z;
        }
    }
}
