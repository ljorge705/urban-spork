package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

import androidx.core.app.FrameMetricsAggregator;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import java.util.Locale;
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

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 /2\u00020\u0001:\u0002./B\u0085\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010B_\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0011J&\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,HÁ\u0001¢\u0006\u0002\b-R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0015R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0015R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0013\u001a\u0004\b%\u0010\u0015¨\u00060"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/UiAlertsNetwork;", "", "seen1", "", "faceVideoTimeout", "", "noFace", "multipleFaces", "requestError", "document", "glare", "blur", OptionalModuleUtils.BARCODE, "cutoff", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBarcode$annotations", "()V", "getBarcode", "()Ljava/lang/String;", "getBlur$annotations", "getBlur", "getCutoff$annotations", "getCutoff", "getDocument$annotations", "getDocument", "getFaceVideoTimeout$annotations", "getFaceVideoTimeout", "getGlare$annotations", "getGlare", "getMultipleFaces$annotations", "getMultipleFaces", "getNoFace$annotations", "getNoFace", "getRequestError$annotations", "getRequestError", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final class UiAlertsNetwork {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String barcode;
    private final String blur;
    private final String cutoff;
    private final String document;
    private final String faceVideoTimeout;
    private final String glare;
    private final String multipleFaces;
    private final String noFace;
    private final String requestError;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bHÆ\u0001¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/UiAlertsNetwork$Companion;", "", "()V", "fromUiAlerts", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/UiAlertsNetwork;", "uiAlerts", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/UiAlerts;", "serializer", "Lkotlinx/serialization/KSerializer;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final UiAlertsNetwork fromUiAlerts(UiAlerts uiAlerts) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String lowerCase;
            Intrinsics.checkNotNullParameter(uiAlerts, "uiAlerts");
            UiAlerts.UiAlertType faceVideoTimeout = uiAlerts.getFaceVideoTimeout();
            if (faceVideoTimeout != null) {
                String strName = faceVideoTimeout.name();
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase2 = strName.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                str = lowerCase2;
            } else {
                str = null;
            }
            UiAlerts.UiAlertType noFace = uiAlerts.getNoFace();
            if (noFace != null) {
                String strName2 = noFace.name();
                Locale US2 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US2, "US");
                String lowerCase3 = strName2.toLowerCase(US2);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                str2 = lowerCase3;
            } else {
                str2 = null;
            }
            UiAlerts.UiAlertType multipleFaces = uiAlerts.getMultipleFaces();
            if (multipleFaces != null) {
                String strName3 = multipleFaces.name();
                Locale US3 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US3, "US");
                String lowerCase4 = strName3.toLowerCase(US3);
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                str3 = lowerCase4;
            } else {
                str3 = null;
            }
            UiAlerts.UiAlertType requestError = uiAlerts.getRequestError();
            if (requestError != null) {
                String strName4 = requestError.name();
                Locale US4 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US4, "US");
                String lowerCase5 = strName4.toLowerCase(US4);
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                str4 = lowerCase5;
            } else {
                str4 = null;
            }
            UiAlerts.UiAlertType document = uiAlerts.getDocument();
            if (document != null) {
                String strName5 = document.name();
                Locale US5 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US5, "US");
                String lowerCase6 = strName5.toLowerCase(US5);
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
                str5 = lowerCase6;
            } else {
                str5 = null;
            }
            UiAlerts.UiAlertType glare = uiAlerts.getGlare();
            if (glare != null) {
                String strName6 = glare.name();
                Locale US6 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US6, "US");
                String lowerCase7 = strName6.toLowerCase(US6);
                Intrinsics.checkNotNullExpressionValue(lowerCase7, "toLowerCase(...)");
                str6 = lowerCase7;
            } else {
                str6 = null;
            }
            UiAlerts.UiAlertType blur = uiAlerts.getBlur();
            if (blur != null) {
                String strName7 = blur.name();
                Locale US7 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US7, "US");
                String lowerCase8 = strName7.toLowerCase(US7);
                Intrinsics.checkNotNullExpressionValue(lowerCase8, "toLowerCase(...)");
                str7 = lowerCase8;
            } else {
                str7 = null;
            }
            UiAlerts.UiAlertType barcode = uiAlerts.getBarcode();
            if (barcode != null) {
                String strName8 = barcode.name();
                Locale US8 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US8, "US");
                String lowerCase9 = strName8.toLowerCase(US8);
                Intrinsics.checkNotNullExpressionValue(lowerCase9, "toLowerCase(...)");
                str8 = lowerCase9;
            } else {
                str8 = null;
            }
            UiAlerts.UiAlertType cutoff = uiAlerts.getCutoff();
            if (cutoff != null) {
                String strName9 = cutoff.name();
                Locale US9 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US9, "US");
                lowerCase = strName9.toLowerCase(US9);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            return new UiAlertsNetwork(str, str2, str3, str4, str5, str6, str7, str8, lowerCase);
        }

        public final KSerializer<UiAlertsNetwork> serializer() {
            return UiAlertsNetwork$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ UiAlertsNetwork(int i, @SerialName("face_video_timeout") String str, @SerialName("no_face") String str2, @SerialName("multiple_faces") String str3, @SerialName("request_error") String str4, @SerialName("document") String str5, @SerialName("glare") String str6, @SerialName("blur") String str7, @SerialName(OptionalModuleUtils.BARCODE) String str8, @SerialName("cutoff") String str9, SerializationConstructorMarker serializationConstructorMarker) {
        if (511 != (i & FrameMetricsAggregator.EVERY_DURATION)) {
            PluginExceptionsKt.throwMissingFieldException(i, FrameMetricsAggregator.EVERY_DURATION, UiAlertsNetwork$$serializer.INSTANCE.getDescriptor());
        }
        this.faceVideoTimeout = str;
        this.noFace = str2;
        this.multipleFaces = str3;
        this.requestError = str4;
        this.document = str5;
        this.glare = str6;
        this.blur = str7;
        this.barcode = str8;
        this.cutoff = str9;
    }

    @SerialName(OptionalModuleUtils.BARCODE)
    public static /* synthetic */ void getBarcode$annotations() {
    }

    @SerialName("blur")
    public static /* synthetic */ void getBlur$annotations() {
    }

    @SerialName("cutoff")
    public static /* synthetic */ void getCutoff$annotations() {
    }

    @SerialName("document")
    public static /* synthetic */ void getDocument$annotations() {
    }

    @SerialName("face_video_timeout")
    public static /* synthetic */ void getFaceVideoTimeout$annotations() {
    }

    @SerialName("glare")
    public static /* synthetic */ void getGlare$annotations() {
    }

    @SerialName("multiple_faces")
    public static /* synthetic */ void getMultipleFaces$annotations() {
    }

    @SerialName("no_face")
    public static /* synthetic */ void getNoFace$annotations() {
    }

    @SerialName("request_error")
    public static /* synthetic */ void getRequestError$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(UiAlertsNetwork self, CompositeEncoder output, SerialDescriptor serialDesc) {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        output.encodeNullableSerializableElement(serialDesc, 0, stringSerializer, self.faceVideoTimeout);
        output.encodeNullableSerializableElement(serialDesc, 1, stringSerializer, self.noFace);
        output.encodeNullableSerializableElement(serialDesc, 2, stringSerializer, self.multipleFaces);
        output.encodeNullableSerializableElement(serialDesc, 3, stringSerializer, self.requestError);
        output.encodeNullableSerializableElement(serialDesc, 4, stringSerializer, self.document);
        output.encodeNullableSerializableElement(serialDesc, 5, stringSerializer, self.glare);
        output.encodeNullableSerializableElement(serialDesc, 6, stringSerializer, self.blur);
        output.encodeNullableSerializableElement(serialDesc, 7, stringSerializer, self.barcode);
        output.encodeNullableSerializableElement(serialDesc, 8, stringSerializer, self.cutoff);
    }

    public final String getBarcode() {
        return this.barcode;
    }

    public final String getBlur() {
        return this.blur;
    }

    public final String getCutoff() {
        return this.cutoff;
    }

    public final String getDocument() {
        return this.document;
    }

    public final String getFaceVideoTimeout() {
        return this.faceVideoTimeout;
    }

    public final String getGlare() {
        return this.glare;
    }

    public final String getMultipleFaces() {
        return this.multipleFaces;
    }

    public final String getNoFace() {
        return this.noFace;
    }

    public final String getRequestError() {
        return this.requestError;
    }

    public UiAlertsNetwork(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.faceVideoTimeout = str;
        this.noFace = str2;
        this.multipleFaces = str3;
        this.requestError = str4;
        this.document = str5;
        this.glare = str6;
        this.blur = str7;
        this.barcode = str8;
        this.cutoff = str9;
    }
}
