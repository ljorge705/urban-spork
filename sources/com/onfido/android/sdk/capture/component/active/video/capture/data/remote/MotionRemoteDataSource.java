package com.onfido.android.sdk.capture.component.active.video.capture.data.remote;

import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model.AVCUploadResponse;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Supplier;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionRemoteDataSource;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/MotionDataSource;", "motionCaptureVariantOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "motionApi", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionApi;", "payloadHelper", "Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "motionMetaDataHelper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionMetaDataHelper;", "(Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionApi;Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionMetaDataHelper;)V", "uploadMotionCapture", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionRemoteDataSource implements MotionDataSource {
    private static final String MEDIA_TYPE_VIDEO = "video/mp4";
    private static final String TYPE_PART_MOTION = "liveness";
    private static final String VIDEO_PART_NAME = "media";
    private final MotionApi motionApi;
    private final MotionCaptureVariantOptions motionCaptureVariantOptions;
    private final MotionMetaDataHelper motionMetaDataHelper;
    private final PayloadHelper payloadHelper;

    @Inject
    public MotionRemoteDataSource(MotionCaptureVariantOptions motionCaptureVariantOptions, MotionApi motionApi, PayloadHelper payloadHelper, MotionMetaDataHelper motionMetaDataHelper) {
        Intrinsics.checkNotNullParameter(motionCaptureVariantOptions, "motionCaptureVariantOptions");
        Intrinsics.checkNotNullParameter(motionApi, "motionApi");
        Intrinsics.checkNotNullParameter(payloadHelper, "payloadHelper");
        Intrinsics.checkNotNullParameter(motionMetaDataHelper, "motionMetaDataHelper");
        this.motionCaptureVariantOptions = motionCaptureVariantOptions;
        this.motionApi = motionApi;
        this.payloadHelper = payloadHelper;
        this.motionMetaDataHelper = motionMetaDataHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SingleSource uploadMotionCapture$lambda$0(File videoFile, MotionRemoteDataSource this$0, boolean z) {
        Intrinsics.checkNotNullParameter(videoFile, "$videoFile");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MultipartBody.Part.Companion companion = MultipartBody.Part.INSTANCE;
        String name = videoFile.getName();
        RequestBody.Companion companion2 = RequestBody.INSTANCE;
        MultipartBody.Part partCreateFormData = companion.createFormData("media", name, companion2.create(videoFile, MediaType.INSTANCE.get("video/mp4")));
        String metaData = this$0.motionMetaDataHelper.getMetaData(z);
        MediaType mediaType = MultipartBody.FORM;
        RequestBody requestBodyCreate = companion2.create(metaData, mediaType);
        RequestBody requestBodyCreate2 = companion2.create(TYPE_PART_MOTION, mediaType);
        Cryptography.Result signedPayload = this$0.payloadHelper.getSignedPayload(videoFile, metaData);
        String encryptedBiometricToken = this$0.motionCaptureVariantOptions.getEncryptedBiometricToken();
        RequestBody requestBodyCreate3 = encryptedBiometricToken != null ? companion2.create(encryptedBiometricToken, mediaType) : null;
        MotionApi motionApi = this$0.motionApi;
        RequestBody signaturePart = this$0.payloadHelper.getSignaturePart(signedPayload);
        RequestBody clientNoncePart = this$0.payloadHelper.getClientNoncePart(signedPayload);
        return requestBodyCreate3 != null ? motionApi.uploadFaceScanForAuth(partCreateFormData, requestBodyCreate2, requestBodyCreate, signaturePart, clientNoncePart, requestBodyCreate3) : motionApi.uploadFaceScan(partCreateFormData, requestBodyCreate2, requestBodyCreate, signaturePart, clientNoncePart);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource
    public Single<AVCUploadResponse> uploadMotionCapture(final File videoFile, final boolean isAudioEnabled) {
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        Single<AVCUploadResponse> singleDefer = Single.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionRemoteDataSource$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return MotionRemoteDataSource.uploadMotionCapture$lambda$0(videoFile, this, isAudioEnabled);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleDefer, "defer(...)");
        return singleDefer;
    }
}
