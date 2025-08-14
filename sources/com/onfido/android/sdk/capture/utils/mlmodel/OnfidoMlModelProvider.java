package com.onfido.android.sdk.capture.utils.mlmodel;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.Device;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelProvider;", "", "getModel", ExifInterface.GPS_DIRECTION_TRUE, Device.JsonKeys.MODEL, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "(Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoMlModelProvider {
    <T> Object getModel(OnfidoMlModels onfidoMlModels, Continuation<? super T> continuation);
}
