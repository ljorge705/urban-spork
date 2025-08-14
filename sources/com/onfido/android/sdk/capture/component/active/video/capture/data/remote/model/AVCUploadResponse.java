package com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model;

import com.clevertap.android.sdk.Constants;
import java.util.Date;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016HÁ\u0001¢\u0006\u0002\b\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "", "seen1", "", "mediaResponse", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse;)V", "getMediaResponse$annotations", "()V", "getInsertedAt", "Ljava/util/Date;", "getUuid", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final class AVCUploadResponse {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MotionUploadMediaResponse mediaResponse;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<AVCUploadResponse> serializer() {
            return AVCUploadResponse$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AVCUploadResponse(int i, @SerialName(Constants.KEY_MEDIA) MotionUploadMediaResponse motionUploadMediaResponse, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, AVCUploadResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.mediaResponse = motionUploadMediaResponse;
    }

    @SerialName(Constants.KEY_MEDIA)
    private static /* synthetic */ void getMediaResponse$annotations() {
    }

    public final Date getInsertedAt() {
        return this.mediaResponse.getDataResponse().getInsertedAt();
    }

    public final String getUuid() {
        return this.mediaResponse.getUuid();
    }

    public AVCUploadResponse(MotionUploadMediaResponse mediaResponse) {
        Intrinsics.checkNotNullParameter(mediaResponse, "mediaResponse");
        this.mediaResponse = mediaResponse;
    }
}
