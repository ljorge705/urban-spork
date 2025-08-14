package io.sentry.android.replay.gestures;

import android.view.MotionEvent;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.rrweb.RRWebIncrementalSnapshotEvent;
import io.sentry.rrweb.RRWebInteractionEvent;
import io.sentry.rrweb.RRWebInteractionMoveEvent;
import io.sentry.transport.ICurrentDateProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReplayGestureConverter.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015RJ\u0010\u0005\u001a>\u0012\u0004\u0012\u00020\u0007\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n0\u0006j\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lio/sentry/android/replay/gestures/ReplayGestureConverter;", "", "dateProvider", "Lio/sentry/transport/ICurrentDateProvider;", "(Lio/sentry/transport/ICurrentDateProvider;)V", "currentPositions", "Ljava/util/LinkedHashMap;", "", "Ljava/util/ArrayList;", "Lio/sentry/rrweb/RRWebInteractionMoveEvent$Position;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "lastCapturedMoveEvent", "", "touchMoveBaseline", "convert", "", "Lio/sentry/rrweb/RRWebIncrementalSnapshotEvent;", "event", "Landroid/view/MotionEvent;", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ReplayGestureConverter {
    private static final int CAPTURE_MOVE_EVENT_THRESHOLD = 500;
    private static final int TOUCH_MOVE_DEBOUNCE_THRESHOLD = 50;
    private final LinkedHashMap<Integer, ArrayList<RRWebInteractionMoveEvent.Position>> currentPositions;
    private final ICurrentDateProvider dateProvider;
    private long lastCapturedMoveEvent;
    private long touchMoveBaseline;
    public static final int $stable = 8;

    public ReplayGestureConverter(ICurrentDateProvider dateProvider) {
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        this.dateProvider = dateProvider;
        this.currentPositions = new LinkedHashMap<>(10);
    }

    public final List<RRWebIncrementalSnapshotEvent> convert(MotionEvent event, ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        int actionMasked = event.getActionMasked();
        int i = 10;
        int i2 = -1;
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    long currentTimeMillis = this.dateProvider.getCurrentTimeMillis();
                    long j = this.lastCapturedMoveEvent;
                    if (j != 0 && j + 50 > currentTimeMillis) {
                        return null;
                    }
                    this.lastCapturedMoveEvent = currentTimeMillis;
                    Set<Integer> setKeySet = this.currentPositions.keySet();
                    Intrinsics.checkNotNullExpressionValue(setKeySet, "currentPositions.keys");
                    for (Integer pId : setKeySet) {
                        Intrinsics.checkNotNullExpressionValue(pId, "pId");
                        int iFindPointerIndex = event.findPointerIndex(pId.intValue());
                        if (iFindPointerIndex != i2) {
                            if (this.touchMoveBaseline == 0) {
                                this.touchMoveBaseline = currentTimeMillis;
                            }
                            ArrayList<RRWebInteractionMoveEvent.Position> arrayList = this.currentPositions.get(pId);
                            Intrinsics.checkNotNull(arrayList);
                            RRWebInteractionMoveEvent.Position position = new RRWebInteractionMoveEvent.Position();
                            position.setX(event.getX(iFindPointerIndex) * recorderConfig.getScaleFactorX());
                            position.setY(event.getY(iFindPointerIndex) * recorderConfig.getScaleFactorY());
                            position.setId(0);
                            position.setTimeOffset(currentTimeMillis - this.touchMoveBaseline);
                            arrayList.add(position);
                        }
                        i2 = -1;
                    }
                    long j2 = currentTimeMillis - this.touchMoveBaseline;
                    if (j2 <= 500) {
                        return null;
                    }
                    ArrayList arrayList2 = new ArrayList(this.currentPositions.size());
                    Iterator<Map.Entry<Integer, ArrayList<RRWebInteractionMoveEvent.Position>>> it = this.currentPositions.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Integer, ArrayList<RRWebInteractionMoveEvent.Position>> next = it.next();
                        int iIntValue = next.getKey().intValue();
                        ArrayList<RRWebInteractionMoveEvent.Position> value = next.getValue();
                        if (!value.isEmpty()) {
                            ArrayList arrayList3 = arrayList2;
                            RRWebInteractionMoveEvent rRWebInteractionMoveEvent = new RRWebInteractionMoveEvent();
                            rRWebInteractionMoveEvent.setTimestamp(currentTimeMillis);
                            ArrayList<RRWebInteractionMoveEvent.Position> arrayList4 = value;
                            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, i));
                            for (RRWebInteractionMoveEvent.Position position2 : arrayList4) {
                                position2.setTimeOffset(position2.getTimeOffset() - j2);
                                arrayList5.add(position2);
                                it = it;
                            }
                            rRWebInteractionMoveEvent.setPositions(arrayList5);
                            rRWebInteractionMoveEvent.setPointerId(iIntValue);
                            arrayList3.add(rRWebInteractionMoveEvent);
                            ArrayList<RRWebInteractionMoveEvent.Position> arrayList6 = this.currentPositions.get(Integer.valueOf(iIntValue));
                            Intrinsics.checkNotNull(arrayList6);
                            arrayList6.clear();
                            it = it;
                            i = 10;
                        }
                    }
                    this.touchMoveBaseline = 0L;
                    return arrayList2;
                }
                if (actionMasked == 3) {
                    this.currentPositions.clear();
                    RRWebInteractionEvent rRWebInteractionEvent = new RRWebInteractionEvent();
                    rRWebInteractionEvent.setTimestamp(this.dateProvider.getCurrentTimeMillis());
                    rRWebInteractionEvent.setX(event.getX() * recorderConfig.getScaleFactorX());
                    rRWebInteractionEvent.setY(event.getY() * recorderConfig.getScaleFactorY());
                    rRWebInteractionEvent.setId(0);
                    rRWebInteractionEvent.setPointerId(0);
                    rRWebInteractionEvent.setInteractionType(RRWebInteractionEvent.InteractionType.TouchCancel);
                    return CollectionsKt.listOf(rRWebInteractionEvent);
                }
                if (actionMasked != 5) {
                    if (actionMasked != 6) {
                        return null;
                    }
                }
            }
            int pointerId = event.getPointerId(event.getActionIndex());
            int iFindPointerIndex2 = event.findPointerIndex(pointerId);
            if (iFindPointerIndex2 == -1) {
                return null;
            }
            this.currentPositions.remove(Integer.valueOf(pointerId));
            RRWebInteractionEvent rRWebInteractionEvent2 = new RRWebInteractionEvent();
            rRWebInteractionEvent2.setTimestamp(this.dateProvider.getCurrentTimeMillis());
            rRWebInteractionEvent2.setX(event.getX(iFindPointerIndex2) * recorderConfig.getScaleFactorX());
            rRWebInteractionEvent2.setY(event.getY(iFindPointerIndex2) * recorderConfig.getScaleFactorY());
            rRWebInteractionEvent2.setId(0);
            rRWebInteractionEvent2.setPointerId(pointerId);
            rRWebInteractionEvent2.setInteractionType(RRWebInteractionEvent.InteractionType.TouchEnd);
            return CollectionsKt.listOf(rRWebInteractionEvent2);
        }
        int pointerId2 = event.getPointerId(event.getActionIndex());
        int iFindPointerIndex3 = event.findPointerIndex(pointerId2);
        if (iFindPointerIndex3 == -1) {
            return null;
        }
        this.currentPositions.put(Integer.valueOf(pointerId2), new ArrayList<>(10));
        RRWebInteractionEvent rRWebInteractionEvent3 = new RRWebInteractionEvent();
        rRWebInteractionEvent3.setTimestamp(this.dateProvider.getCurrentTimeMillis());
        rRWebInteractionEvent3.setX(event.getX(iFindPointerIndex3) * recorderConfig.getScaleFactorX());
        rRWebInteractionEvent3.setY(event.getY(iFindPointerIndex3) * recorderConfig.getScaleFactorY());
        rRWebInteractionEvent3.setId(0);
        rRWebInteractionEvent3.setPointerId(pointerId2);
        rRWebInteractionEvent3.setInteractionType(RRWebInteractionEvent.InteractionType.TouchStart);
        return CollectionsKt.listOf(rRWebInteractionEvent3);
    }
}
