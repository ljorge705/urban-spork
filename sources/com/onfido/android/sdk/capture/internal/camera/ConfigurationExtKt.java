package com.onfido.android.sdk.capture.internal.camera;

import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.api.client.data.SdkConfiguration;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\" \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0002*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0018\u0010\u0005\u001a\u00020\u0002*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\n¨\u0006\u000b"}, d2 = {"QUALITY_PROFILE_MAPPER", "", "", "getQUALITY_PROFILE_MAPPER", "()Ljava/util/Map;", "videoQualityProfile", "Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;", "getVideoQualityProfile", "(Lcom/onfido/api/client/data/SdkConfiguration$DocumentCapture;)I", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "(Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;)I", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ConfigurationExtKt {
    private static final Map<Integer, Integer> QUALITY_PROFILE_MAPPER = MapsKt.mapOf(TuplesKt.to(Integer.valueOf(FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH), 7), TuplesKt.to(Integer.valueOf(DefaultFrameSampler.DESIRED_FRAME_WIDTH), 4), TuplesKt.to(720, 5), TuplesKt.to(Integer.valueOf(PhotoshopDirectory.TAG_COUNT_INFORMATION), 6), TuplesKt.to(1440, 11), TuplesKt.to(2160, 8));

    public static final Map<Integer, Integer> getQUALITY_PROFILE_MAPPER() {
        return QUALITY_PROFILE_MAPPER;
    }

    public static final int getVideoQualityProfile(SdkConfiguration.DocumentCapture documentCapture) {
        Intrinsics.checkNotNullParameter(documentCapture, "<this>");
        Integer num = QUALITY_PROFILE_MAPPER.get(Integer.valueOf(documentCapture.getVideoQuality()));
        if (num == null) {
            Timber.INSTANCE.e("Document Video Quality couldn't be mapped. Using a default profile", new Object[0]);
            num = 6;
        }
        return num.intValue();
    }

    public static final int getVideoQualityProfile(SdkConfiguration.LivenessCapture livenessCapture) {
        Intrinsics.checkNotNullParameter(livenessCapture, "<this>");
        Integer num = QUALITY_PROFILE_MAPPER.get(Integer.valueOf(livenessCapture.getVideoQuality()));
        if (num == null) {
            Timber.INSTANCE.e("Selfie Video Quality couldn't be mapped. Using a default profile", new Object[0]);
            num = 6;
        }
        return num.intValue();
    }
}
