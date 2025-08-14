package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import androidx.media3.common.MimeTypes;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J+\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/UploadPoaDocumentUseCase;", "", "repository", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaRepository;", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaRepository;)V", "invoke", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/api/client/data/PoaDocumentUpload;", MimeTypes.BASE_TYPE_IMAGE, "", "poaDocumentType", "Lcom/onfido/api/client/data/PoaDocumentType;", "issuingCountryCode", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UploadPoaDocumentUseCase {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String FILE_TYPE_JPG = "image/jpeg";

    @Deprecated
    public static final String PICTURE_FILENAME = "onfido_captured_image";

    @Deprecated
    public static final String PICTURE_FILE_EXTENSION = ".jpg";
    private final PoaRepository repository;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/UploadPoaDocumentUseCase$Companion;", "", "()V", "FILE_TYPE_JPG", "", "PICTURE_FILENAME", "PICTURE_FILE_EXTENSION", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public UploadPoaDocumentUseCase(PoaRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public final Single<PoaDocumentUpload> invoke(byte[] image, PoaDocumentType poaDocumentType, CountryCode issuingCountryCode) {
        Intrinsics.checkNotNullParameter(image, "image");
        return this.repository.uploadPoaDocument$onfido_capture_sdk_core_release(new OnfidoApiService.PoaDocumentUploadParams("onfido_captured_image.jpg", poaDocumentType, "image/jpeg", image, issuingCountryCode != null ? issuingCountryCode.getAlpha3() : null));
    }
}
