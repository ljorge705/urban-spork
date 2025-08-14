package com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model;

import io.sentry.protocol.DebugImage;
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

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019HÁ\u0001¢\u0006\u0002\b\u001aR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse;", "", "seen1", "", DebugImage.JsonKeys.UUID, "", "dataResponse", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadDataResponse;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadDataResponse;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadDataResponse;)V", "getDataResponse$annotations", "()V", "getDataResponse", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadDataResponse;", "getUuid$annotations", "getUuid", "()Ljava/lang/String;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes2.dex */
public final class MotionUploadMediaResponse {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final MotionUploadDataResponse dataResponse;
    private final String uuid;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/MotionUploadMediaResponse;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<MotionUploadMediaResponse> serializer() {
            return MotionUploadMediaResponse$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MotionUploadMediaResponse(int i, @SerialName(DebugImage.JsonKeys.UUID) String str, @SerialName("data") MotionUploadDataResponse motionUploadDataResponse, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, MotionUploadMediaResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.uuid = str;
        this.dataResponse = motionUploadDataResponse;
    }

    @SerialName("data")
    public static /* synthetic */ void getDataResponse$annotations() {
    }

    @SerialName(DebugImage.JsonKeys.UUID)
    public static /* synthetic */ void getUuid$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(MotionUploadMediaResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.uuid);
        output.encodeSerializableElement(serialDesc, 1, MotionUploadDataResponse$$serializer.INSTANCE, self.dataResponse);
    }

    public final MotionUploadDataResponse getDataResponse() {
        return this.dataResponse;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public MotionUploadMediaResponse(String uuid, MotionUploadDataResponse dataResponse) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(dataResponse, "dataResponse");
        this.uuid = uuid;
        this.dataResponse = dataResponse;
    }
}
