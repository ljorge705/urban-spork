package com.onfido.android.sdk.capture.internal.usecase;

import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ResultReceiverExtensionsKt;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Scheduler;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B#\b\u0007\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ5\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0000¢\u0006\u0002\b\u0013R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/MediaCallbacksUseCase;", "", "mediaCallback", "Landroid/os/ResultReceiver;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Landroid/os/ResultReceiver;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "callMediaCallbackForPhotoCapture", "", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "imageData", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "callMediaCallbackForPhotoCapture$onfido_capture_sdk_core_release", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MediaCallbacksUseCase {
    private static final String DOCUMENT_PREFIX = "onfido-document";
    private static final String JPEG_EXTENSION = "jpeg";
    private static final String SELFIE_PREFIX = "onfido-selfie";
    private final ResultReceiver mediaCallback;
    private final SchedulersProvider schedulersProvider;
    private final TimeProvider timeProvider;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public MediaCallbacksUseCase(ResultReceiver resultReceiver, SchedulersProvider schedulersProvider, TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.mediaCallback = resultReceiver;
        this.schedulersProvider = schedulersProvider;
        this.timeProvider = timeProvider;
    }

    public static /* synthetic */ void callMediaCallbackForPhotoCapture$onfido_capture_sdk_core_release$default(MediaCallbacksUseCase mediaCallbacksUseCase, CaptureType captureType, byte[] bArr, DocumentType documentType, DocSide docSide, int i, Object obj) {
        if ((i & 4) != 0) {
            documentType = null;
        }
        if ((i & 8) != 0) {
            docSide = DocSide.FRONT;
        }
        mediaCallbacksUseCase.callMediaCallbackForPhotoCapture$onfido_capture_sdk_core_release(captureType, bArr, documentType, docSide);
    }

    public final void callMediaCallbackForPhotoCapture$onfido_capture_sdk_core_release(CaptureType captureType, byte[] imageData, DocumentType documentType, DocSide documentSide) {
        String strName;
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        Intrinsics.checkNotNullParameter(imageData, "imageData");
        int i = WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()];
        String str = "";
        String str2 = (i != 1 ? i != 2 ? "" : "onfido-selfie" : "onfido-document") + '-' + this.timeProvider.getCurrentTimestamp() + ".jpeg";
        ResultReceiver resultReceiver = this.mediaCallback;
        if (resultReceiver != null) {
            Scheduler io2 = this.schedulersProvider.getIo();
            Pair pair = TuplesKt.to(MediaCallbackResultReceiver.KEY_CAPTURE_TYPE, captureType);
            Pair pair2 = TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_NAME, str2);
            Pair pair3 = TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_TYPE, JPEG_EXTENSION);
            Object obj = documentType;
            if (documentType == null) {
                obj = "";
            }
            Pair pair4 = TuplesKt.to("doc_type", obj);
            if (documentSide != null && (strName = documentSide.name()) != null) {
                str = strName;
            }
            if (ResultReceiverExtensionsKt.sendMedia(resultReceiver, imageData, io2, BundleKt.bundleOf(pair, pair2, pair3, pair4, TuplesKt.to(MediaCallbackResultReceiver.KEY_DOC_SIDE, str))) != null) {
                return;
            }
        }
        Timber.INSTANCE.d("No media callback provided", new Object[0]);
        Unit unit = Unit.INSTANCE;
    }
}
