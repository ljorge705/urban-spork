package com.onfido.android.sdk.capture.ui.camera.util;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationResult;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.api.client.data.DocSide;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0002¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/DocumentValidationResultMapper;", "", "()V", "invoke", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentValidationResultMapper {
    private static final Companion Companion = new Companion(null);
    private static final Map<DocumentValidationResult, OnfidoCaptureValidationBubble.Content> validationResultMap;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/util/DocumentValidationResultMapper$Companion;", "", "()V", "validationResultMap", "", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "getValidationResultMap", "()Ljava/util/Map;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final Map<DocumentValidationResult, OnfidoCaptureValidationBubble.Content> getValidationResultMap() {
            return DocumentValidationResultMapper.validationResultMap;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Pair pair = TuplesKt.to(DocumentValidationResult.Warning.Blur.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_confirmation_alert_blur_title, Integer.valueOf(R.string.onfido_doc_confirmation_alert_blur_detail)));
        Pair pair2 = TuplesKt.to(DocumentValidationResult.Warning.CutOff.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_confirmation_alert_crop_title, Integer.valueOf(R.string.onfido_doc_confirmation_alert_crop_detail)));
        Pair pair3 = TuplesKt.to(DocumentValidationResult.Warning.DocumentTooClose.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_too_close_title, null, 2, null));
        Pair pair4 = TuplesKt.to(DocumentValidationResult.Warning.DocumentTooFar.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_too_far_title, null, 2, null));
        Pair pair5 = TuplesKt.to(DocumentValidationResult.Warning.Glare.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_glare_title, Integer.valueOf(R.string.onfido_doc_capture_alert_glare_detail)));
        Pair pair6 = TuplesKt.to(DocumentValidationResult.Warning.NoDocument.INSTANCE, new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_confirmation_alert_no_doc_title, Integer.valueOf(R.string.onfido_doc_confirmation_alert_no_doc_detail)));
        Pair pair7 = TuplesKt.to(DocumentValidationResult.Hold.INSTANCE, new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_header_live_guidance_doc_position_ok, null, 2, null));
        DocSide docSide = DocSide.FRONT;
        DocSide docSide2 = DocSide.BACK;
        validationResultMap = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, TuplesKt.to(new DocumentValidationResult.Warning.WrongSide(docSide, docSide2), new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_wrong_side_front_title, null, 2, null)), TuplesKt.to(new DocumentValidationResult.Warning.WrongSide(docSide2, docSide), new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_wrong_side_back_title, null, 2, null)), TuplesKt.to(new DocumentValidationResult.Warning.WrongDocument(DocumentType.PASSPORT, null, 2, null), new OnfidoCaptureValidationBubble.Content.Error(R.string.onfido_doc_capture_alert_photo_page_title, null, 2, null)));
    }

    public final OnfidoCaptureValidationBubble.Content invoke(DocumentValidationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return validationResultMap.get(result);
    }
}
