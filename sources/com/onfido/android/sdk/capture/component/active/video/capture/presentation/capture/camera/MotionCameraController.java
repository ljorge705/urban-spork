package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J9\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00030\u000eH&JG\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00030\u000eH&¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "", "cancelRecording", "", "finishRecording", "getVideoFilePath", "", "onDestroy", "onStop", "reset", ViewProps.START, "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "startRecording", "onStarted", "onFinished", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionCameraController {
    void cancelRecording();

    void finishRecording();

    String getVideoFilePath();

    void onDestroy();

    void onStop();

    void reset();

    void start(Function0<Unit> onSuccess, Function1<? super Throwable, Unit> onError);

    void startRecording(Function0<Unit> onStarted, Function0<Unit> onFinished, Function1<? super Throwable, Unit> onError);
}
