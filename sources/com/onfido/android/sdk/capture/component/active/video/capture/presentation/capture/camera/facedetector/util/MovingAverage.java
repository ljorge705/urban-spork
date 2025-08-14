package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util;

import io.sentry.protocol.MetricSummary;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/util/MovingAverage;", "", RRWebVideoEvent.JsonKeys.SIZE, "", "(I)V", "data", "", "", MetricSummary.JsonKeys.SUM, "add", "value", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MovingAverage {
    private final List<Float> data = new ArrayList();
    private final int size;
    private float sum;

    public MovingAverage(int i) {
        this.size = i;
    }

    public final float add(float value) {
        if (this.data.size() == this.size) {
            this.sum -= this.data.remove(0).floatValue();
        }
        this.data.add(Float.valueOf(value));
        float f = this.sum + value;
        this.sum = f;
        return f / this.data.size();
    }
}
