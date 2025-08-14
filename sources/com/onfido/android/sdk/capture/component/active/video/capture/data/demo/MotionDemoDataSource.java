package com.onfido.android.sdk.capture.component.active.video.capture.data.demo;

import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.model.AVCUploadResponse;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/demo/MotionDemoDataSource;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/MotionDataSource;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/Json;)V", "uploadMotionCapture", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/model/AVCUploadResponse;", "videoFile", "Ljava/io/File;", "isAudioEnabled", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionDemoDataSource implements MotionDataSource {
    private static final String MOCK_RESPONSE = "{\n  \"media\": {\n    \"data\": {\n      \"content_type\": \"video/mp4\",\n      \"inserted_at\": \"2023-05-04T11:04:10.405640Z\",\n      \"media_type\": \"liveness\"\n    },\n    \"uuid\": \"2305e610-cd11-4856-a889-c4df218f84ef\"\n  }\n}";
    private final Json jsonParser;

    @Inject
    public MotionDemoDataSource(Json jsonParser) {
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.jsonParser = jsonParser;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource
    public Single<AVCUploadResponse> uploadMotionCapture(File videoFile, boolean isAudioEnabled) {
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        Json json = this.jsonParser;
        Single<AVCUploadResponse> singleDelay = Single.just(json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(AVCUploadResponse.class)), MOCK_RESPONSE)).delay(1L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(singleDelay, "delay(...)");
        return singleDelay;
    }
}
