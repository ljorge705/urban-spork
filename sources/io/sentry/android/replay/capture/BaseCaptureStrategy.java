package io.sentry.android.replay.capture;

import android.view.MotionEvent;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.evaluation.TriggerAdapter;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.Breadcrumb;
import io.sentry.DateUtils;
import io.sentry.IHub;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.BaseCaptureStrategy;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.android.replay.gestures.ReplayGestureConverter;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebIncrementalSnapshotEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import io.sentry.transport.ICurrentDateProvider;
import java.io.File;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: BaseCaptureStrategy.kt */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b!\u0018\u0000 }2\u00020\u0001:\u0002}~BN\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012%\b\u0002\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b¢\u0006\u0002\u0010\u0011J\u0090\u0001\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020S2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010_\u001a\u00020$2\u0006\u0010`\u001a\u00020$2\u0006\u0010a\u001a\u00020$2\b\b\u0002\u0010F\u001a\u00020E2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010b\u001a\u00020$2\b\b\u0002\u0010c\u001a\u00020$2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010L2\u0010\b\u0002\u0010d\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010e2\u000e\b\u0002\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0004J\u0010\u0010h\u001a\u00020i2\u0006\u00106\u001a\u000205H\u0016J\u0010\u0010j\u001a\u00020i2\u0006\u0010k\u001a\u00020lH\u0016J\b\u0010m\u001a\u00020iH\u0016Jr\u0010n\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010p\u0012\u0004\u0012\u0002Hq0o\"\u0004\b\u0000\u0010q2S\b\u0004\u0010r\u001aM\u0012\u0015\u0012\u0013\u0018\u00010L¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(t\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(u\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(v\u0012\u0004\u0012\u00020i0sH\u0082\bJ\u008b\u0001\u0010n\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010p\u0012\u0004\u0012\u0002Hq0o\"\u0004\b\u0000\u0010q2\n\b\u0002\u0010w\u001a\u0004\u0018\u0001Hq2\u0006\u0010t\u001a\u00020L2S\b\u0006\u0010r\u001aM\u0012\u0015\u0012\u0013\u0018\u00010L¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(t\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(u\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(v\u0012\u0004\u0012\u00020i0sH\u0082\b¢\u0006\u0002\u0010xJ\u008d\u0001\u0010y\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010p\u0012\u0006\u0012\u0004\u0018\u0001Hq0o\"\u0004\b\u0000\u0010q2\n\b\u0002\u0010w\u001a\u0004\u0018\u0001Hq2\u0006\u0010t\u001a\u00020L2S\b\u0006\u0010r\u001aM\u0012\u0015\u0012\u0013\u0018\u00010L¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(t\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(u\u0012\u0015\u0012\u0013\u0018\u0001Hq¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(v\u0012\u0004\u0012\u00020i0sH\u0082\b¢\u0006\u0002\u0010xJ\b\u0010z\u001a\u00020iH\u0016J*\u0010{\u001a\u00020i2\u0006\u00106\u001a\u0002052\u0006\u0010_\u001a\u00020$2\u0006\u0010\u000f\u001a\u00020\f2\b\u0010F\u001a\u0004\u0018\u00010EH\u0016J\b\u0010|\u001a\u00020iH\u0016R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR+\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R+\u0010%\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020$8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010#\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\u00020.X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00100\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b1\u00102R+\u00106\u001a\u0002052\u0006\u0010\u001c\u001a\u0002058D@DX\u0084\u008e\u0002¢\u0006\u0012\n\u0004\b;\u0010#\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0016\u0010<\u001a\u0004\u0018\u00010=8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R+\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u00102R\u0014\u0010A\u001a\u00020BX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR+\u0010F\u001a\u00020E2\u0006\u0010\u001c\u001a\u00020E8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010#\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR/\u0010M\u001a\u0004\u0018\u00010L2\b\u0010\u001c\u001a\u0004\u0018\u00010L8D@DX\u0084\u008e\u0002¢\u0006\u0012\n\u0004\bR\u0010#\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR/\u0010T\u001a\u0004\u0018\u00010S2\b\u0010\u001c\u001a\u0004\u0018\u00010S8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010#\u001a\u0004\bU\u0010V\"\u0004\bW\u0010X¨\u0006\u007f"}, d2 = {"Lio/sentry/android/replay/capture/BaseCaptureStrategy;", "Lio/sentry/android/replay/capture/CaptureStrategy;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "hub", "Lio/sentry/IHub;", "dateProvider", "Lio/sentry/transport/ICurrentDateProvider;", "replayExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "replayCacheProvider", "Lkotlin/Function1;", "Lio/sentry/protocol/SentryId;", "Lkotlin/ParameterName;", "name", "replayId", "Lio/sentry/android/replay/ReplayCache;", "(Lio/sentry/SentryOptions;Lio/sentry/IHub;Lio/sentry/transport/ICurrentDateProvider;Ljava/util/concurrent/ScheduledExecutorService;Lkotlin/jvm/functions/Function1;)V", "cache", "getCache", "()Lio/sentry/android/replay/ReplayCache;", "setCache", "(Lio/sentry/android/replay/ReplayCache;)V", "currentEvents", "Ljava/util/Deque;", "Lio/sentry/rrweb/RRWebEvent;", "getCurrentEvents", "()Ljava/util/Deque;", "<set-?>", "currentReplayId", "getCurrentReplayId", "()Lio/sentry/protocol/SentryId;", "setCurrentReplayId", "(Lio/sentry/protocol/SentryId;)V", "currentReplayId$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "currentSegment", "getCurrentSegment", "()I", "setCurrentSegment", "(I)V", "currentSegment$delegate", "gestureConverter", "Lio/sentry/android/replay/gestures/ReplayGestureConverter;", "isTerminating", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "persistingExecutor", "getPersistingExecutor", "()Ljava/util/concurrent/ScheduledExecutorService;", "persistingExecutor$delegate", "Lkotlin/Lazy;", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "recorderConfig", "getRecorderConfig", "()Lio/sentry/android/replay/ScreenshotRecorderConfig;", "setRecorderConfig", "(Lio/sentry/android/replay/ScreenshotRecorderConfig;)V", "recorderConfig$delegate", "replayCacheDir", "Ljava/io/File;", "getReplayCacheDir", "()Ljava/io/File;", "getReplayExecutor", "replayStartTimestamp", "Ljava/util/concurrent/atomic/AtomicLong;", "getReplayStartTimestamp", "()Ljava/util/concurrent/atomic/AtomicLong;", "Lio/sentry/SentryReplayEvent$ReplayType;", "replayType", "getReplayType", "()Lio/sentry/SentryReplayEvent$ReplayType;", "setReplayType", "(Lio/sentry/SentryReplayEvent$ReplayType;)V", "replayType$delegate", "", "screenAtStart", "getScreenAtStart", "()Ljava/lang/String;", "setScreenAtStart", "(Ljava/lang/String;)V", "screenAtStart$delegate", "Ljava/util/Date;", "segmentTimestamp", "getSegmentTimestamp", "()Ljava/util/Date;", "setSegmentTimestamp", "(Ljava/util/Date;)V", "segmentTimestamp$delegate", "createSegmentInternal", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "duration", "", "currentSegmentTimestamp", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "height", "width", RRWebVideoEvent.JsonKeys.FRAME_RATE, "bitRate", SentryBaseEvent.JsonKeys.BREADCRUMBS, "", "Lio/sentry/Breadcrumb;", "events", "onConfigurationChanged", "", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "pause", "persistableAtomic", "Lkotlin/properties/ReadWriteProperty;", "", ExifInterface.GPS_DIRECTION_TRUE, "onChange", "Lkotlin/Function3;", TriggerAdapter.INAPP_PROPERTYNAME, Constants.KEY_OLD_VALUE, Constants.KEY_NEW_VALUE, "initialValue", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/jvm/functions/Function3;)Lkotlin/properties/ReadWriteProperty;", "persistableAtomicNullable", "resume", ViewProps.START, "stop", "Companion", "ReplayPersistingExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class BaseCaptureStrategy implements CaptureStrategy {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "recorderConfig", "getRecorderConfig()Lio/sentry/android/replay/ScreenshotRecorderConfig;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "segmentTimestamp", "getSegmentTimestamp()Ljava/util/Date;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "screenAtStart", "getScreenAtStart()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "currentReplayId", "getCurrentReplayId()Lio/sentry/protocol/SentryId;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "currentSegment", "getCurrentSegment()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(BaseCaptureStrategy.class, "replayType", "getReplayType()Lio/sentry/SentryReplayEvent$ReplayType;", 0))};
    private static final String TAG = "CaptureStrategy";
    private ReplayCache cache;
    private final Deque<RRWebEvent> currentEvents;

    /* renamed from: currentReplayId$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty currentReplayId;

    /* renamed from: currentSegment$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty currentSegment;
    private final ICurrentDateProvider dateProvider;
    private final ReplayGestureConverter gestureConverter;
    private final IHub hub;
    private final AtomicBoolean isTerminating;
    private final SentryOptions options;

    /* renamed from: persistingExecutor$delegate, reason: from kotlin metadata */
    private final Lazy persistingExecutor;

    /* renamed from: recorderConfig$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty recorderConfig;
    private final Function1<SentryId, ReplayCache> replayCacheProvider;
    private final ScheduledExecutorService replayExecutor;
    private final AtomicLong replayStartTimestamp;

    /* renamed from: replayType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty replayType;

    /* renamed from: screenAtStart$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty screenAtStart;

    /* renamed from: segmentTimestamp$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty segmentTimestamp;

    protected final ReplayCache getCache() {
        return this.cache;
    }

    protected final Deque<RRWebEvent> getCurrentEvents() {
        return this.currentEvents;
    }

    protected final ScheduledExecutorService getReplayExecutor() {
        return this.replayExecutor;
    }

    protected final AtomicLong getReplayStartTimestamp() {
        return this.replayStartTimestamp;
    }

    /* renamed from: isTerminating, reason: from getter */
    protected final AtomicBoolean getIsTerminating() {
        return this.isTerminating;
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void pause() {
    }

    protected final void setCache(ReplayCache replayCache) {
        this.cache = replayCache;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BaseCaptureStrategy(SentryOptions options, IHub iHub, ICurrentDateProvider dateProvider, ScheduledExecutorService replayExecutor, Function1<? super SentryId, ReplayCache> function1) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        Intrinsics.checkNotNullParameter(replayExecutor, "replayExecutor");
        this.options = options;
        this.hub = iHub;
        this.dateProvider = dateProvider;
        this.replayExecutor = replayExecutor;
        this.replayCacheProvider = function1;
        this.persistingExecutor = LazyKt.lazy(new Function0<ScheduledExecutorService>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$persistingExecutor$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return Executors.newSingleThreadScheduledExecutor(new BaseCaptureStrategy.ReplayPersistingExecutorServiceThreadFactory());
            }
        });
        this.gestureConverter = new ReplayGestureConverter(dateProvider);
        this.isTerminating = new AtomicBoolean(false);
        final Object obj = null;
        final String str = "";
        this.recorderConfig = new ReadWriteProperty<Object, ScreenshotRecorderConfig>(obj, this, str, this) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$1
            final /* synthetic */ String $propertyName;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<ScreenshotRecorderConfig> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str;
                this.this$0 = this;
                this.value = new AtomicReference<>(obj);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public ScreenshotRecorderConfig getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final ScreenshotRecorderConfig value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final ScreenshotRecorderConfig andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str2 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj2 = andSet;
                        ScreenshotRecorderConfig screenshotRecorderConfig = (ScreenshotRecorderConfig) value;
                        if (screenshotRecorderConfig == null) {
                            return;
                        }
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(ReplayCache.SEGMENT_KEY_HEIGHT, String.valueOf(screenshotRecorderConfig.getRecordingHeight()));
                        }
                        ReplayCache cache2 = baseCaptureStrategy.getCache();
                        if (cache2 != null) {
                            cache2.persistSegmentValues(ReplayCache.SEGMENT_KEY_WIDTH, String.valueOf(screenshotRecorderConfig.getRecordingWidth()));
                        }
                        ReplayCache cache3 = baseCaptureStrategy.getCache();
                        if (cache3 != null) {
                            cache3.persistSegmentValues(ReplayCache.SEGMENT_KEY_FRAME_RATE, String.valueOf(screenshotRecorderConfig.getFrameRate()));
                        }
                        ReplayCache cache4 = baseCaptureStrategy.getCache();
                        if (cache4 != null) {
                            cache4.persistSegmentValues(ReplayCache.SEGMENT_KEY_BIT_RATE, String.valueOf(screenshotRecorderConfig.getBitRate()));
                        }
                    }
                });
            }
        };
        final String str2 = ReplayCache.SEGMENT_KEY_TIMESTAMP;
        this.segmentTimestamp = new ReadWriteProperty<Object, Date>(obj, this, str2, this) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$1
            final /* synthetic */ String $propertyName;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<Date> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str2;
                this.this$0 = this;
                this.value = new AtomicReference<>(obj);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public Date getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final Date value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final Date andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str3 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj2 = andSet;
                        Date date = (Date) value;
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(ReplayCache.SEGMENT_KEY_TIMESTAMP, date == null ? null : DateUtils.getTimestamp(date));
                        }
                    }
                });
            }
        };
        this.replayStartTimestamp = new AtomicLong();
        final String str3 = ReplayCache.SEGMENT_KEY_REPLAY_SCREEN_AT_START;
        final Object obj2 = null;
        this.screenAtStart = new ReadWriteProperty<Object, String>(obj2, this, str3, this, str3) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$2
            final /* synthetic */ String $propertyName;
            final /* synthetic */ String $propertyName$inlined;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<String> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str3;
                this.this$0 = this;
                this.$propertyName$inlined = str3;
                this.value = new AtomicReference<>(obj2);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public String getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final String value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final String andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str4 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                final String str5 = this.$propertyName$inlined;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomicNullable$default$2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj3 = value;
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(str5, String.valueOf(obj3));
                        }
                    }
                });
            }
        };
        final SentryId sentryId = SentryId.EMPTY_ID;
        final String str4 = ReplayCache.SEGMENT_KEY_REPLAY_ID;
        this.currentReplayId = new ReadWriteProperty<Object, SentryId>(sentryId, this, str4, this, str4) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$1
            final /* synthetic */ String $propertyName;
            final /* synthetic */ String $propertyName$inlined;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<SentryId> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str4;
                this.this$0 = this;
                this.$propertyName$inlined = str4;
                this.value = new AtomicReference<>(sentryId);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public SentryId getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final SentryId value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final SentryId andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str5 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                final String str6 = this.$propertyName$inlined;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj3 = value;
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(str6, String.valueOf(obj3));
                        }
                    }
                });
            }
        };
        final int i = -1;
        final String str5 = ReplayCache.SEGMENT_KEY_ID;
        this.currentSegment = new ReadWriteProperty<Object, Integer>(i, this, str5, this, str5) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$2
            final /* synthetic */ String $propertyName;
            final /* synthetic */ String $propertyName$inlined;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<Integer> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str5;
                this.this$0 = this;
                this.$propertyName$inlined = str5;
                this.value = new AtomicReference<>(i);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public Integer getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final Integer value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final Integer andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str6 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                final String str7 = this.$propertyName$inlined;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$2.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj3 = value;
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(str7, String.valueOf(obj3));
                        }
                    }
                });
            }
        };
        final String str6 = ReplayCache.SEGMENT_KEY_REPLAY_TYPE;
        final Object obj3 = null;
        this.replayType = new ReadWriteProperty<Object, SentryReplayEvent.ReplayType>(obj3, this, str6, this, str6) { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$3
            final /* synthetic */ String $propertyName;
            final /* synthetic */ String $propertyName$inlined;
            final /* synthetic */ BaseCaptureStrategy this$0;
            final /* synthetic */ BaseCaptureStrategy this$0$inline_fun;
            private final AtomicReference<SentryReplayEvent.ReplayType> value;

            {
                this.this$0$inline_fun = this;
                this.$propertyName = str6;
                this.this$0 = this;
                this.$propertyName$inlined = str6;
                this.value = new AtomicReference<>(obj3);
            }

            private final void runInBackground(final Function0<Unit> task) {
                if (this.this$0$inline_fun.options.getMainThreadChecker().isMainThread()) {
                    ExecutorsKt.submitSafely(this.this$0$inline_fun.getPersistingExecutor(), this.this$0$inline_fun.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$3.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            task.invoke();
                        }
                    });
                } else {
                    task.invoke();
                }
            }

            @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
            public SentryReplayEvent.ReplayType getValue(Object thisRef, KProperty<?> property) {
                Intrinsics.checkNotNullParameter(property, "property");
                return this.value.get();
            }

            @Override // kotlin.properties.ReadWriteProperty
            public void setValue(Object thisRef, KProperty<?> property, final SentryReplayEvent.ReplayType value) {
                Intrinsics.checkNotNullParameter(property, "property");
                final SentryReplayEvent.ReplayType andSet = this.value.getAndSet(value);
                if (Intrinsics.areEqual(andSet, value)) {
                    return;
                }
                final String str7 = this.$propertyName;
                final BaseCaptureStrategy baseCaptureStrategy = this.this$0;
                final String str8 = this.$propertyName$inlined;
                runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$special$$inlined$persistableAtomic$default$3.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Object obj4 = value;
                        ReplayCache cache = baseCaptureStrategy.getCache();
                        if (cache != null) {
                            cache.persistSegmentValues(str8, String.valueOf(obj4));
                        }
                    }
                });
            }
        };
        this.currentEvents = new ConcurrentLinkedDeque();
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void onScreenChanged(String str) {
        CaptureStrategy.DefaultImpls.onScreenChanged(this, str);
    }

    public /* synthetic */ BaseCaptureStrategy(SentryOptions sentryOptions, IHub iHub, ICurrentDateProvider iCurrentDateProvider, ScheduledExecutorService scheduledExecutorService, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, iHub, iCurrentDateProvider, scheduledExecutorService, (i & 16) != 0 ? null : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScheduledExecutorService getPersistingExecutor() {
        Object value = this.persistingExecutor.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-persistingExecutor>(...)");
        return (ScheduledExecutorService) value;
    }

    protected final ScreenshotRecorderConfig getRecorderConfig() {
        return (ScreenshotRecorderConfig) this.recorderConfig.getValue(this, $$delegatedProperties[0]);
    }

    protected final void setRecorderConfig(ScreenshotRecorderConfig screenshotRecorderConfig) {
        Intrinsics.checkNotNullParameter(screenshotRecorderConfig, "<set-?>");
        this.recorderConfig.setValue(this, $$delegatedProperties[0], screenshotRecorderConfig);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public Date getSegmentTimestamp() {
        return (Date) this.segmentTimestamp.getValue(this, $$delegatedProperties[1]);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void setSegmentTimestamp(Date date) {
        this.segmentTimestamp.setValue(this, $$delegatedProperties[1], date);
    }

    protected final String getScreenAtStart() {
        return (String) this.screenAtStart.getValue(this, $$delegatedProperties[2]);
    }

    protected final void setScreenAtStart(String str) {
        this.screenAtStart.setValue(this, $$delegatedProperties[2], str);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public SentryId getCurrentReplayId() {
        return (SentryId) this.currentReplayId.getValue(this, $$delegatedProperties[3]);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void setCurrentReplayId(SentryId sentryId) {
        Intrinsics.checkNotNullParameter(sentryId, "<set-?>");
        this.currentReplayId.setValue(this, $$delegatedProperties[3], sentryId);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public int getCurrentSegment() {
        return ((Number) this.currentSegment.getValue(this, $$delegatedProperties[4])).intValue();
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void setCurrentSegment(int i) {
        this.currentSegment.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public File getReplayCacheDir() {
        ReplayCache replayCache = this.cache;
        if (replayCache != null) {
            return replayCache.getReplayCacheDir$sentry_android_replay_release();
        }
        return null;
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public SentryReplayEvent.ReplayType getReplayType() {
        return (SentryReplayEvent.ReplayType) this.replayType.getValue(this, $$delegatedProperties[5]);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void setReplayType(SentryReplayEvent.ReplayType replayType) {
        Intrinsics.checkNotNullParameter(replayType, "<set-?>");
        this.replayType.setValue(this, $$delegatedProperties[5], replayType);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void start(ScreenshotRecorderConfig recorderConfig, int segmentId, SentryId replayId, SentryReplayEvent.ReplayType replayType) {
        ReplayCache replayCache;
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        Intrinsics.checkNotNullParameter(replayId, "replayId");
        Function1<SentryId, ReplayCache> function1 = this.replayCacheProvider;
        if (function1 == null || (replayCache = function1.invoke(replayId)) == null) {
            replayCache = new ReplayCache(this.options, replayId);
        }
        this.cache = replayCache;
        setCurrentReplayId(replayId);
        setCurrentSegment(segmentId);
        if (replayType == null) {
            replayType = this instanceof SessionCaptureStrategy ? SentryReplayEvent.ReplayType.SESSION : SentryReplayEvent.ReplayType.BUFFER;
        }
        setReplayType(replayType);
        setRecorderConfig(recorderConfig);
        setSegmentTimestamp(DateUtils.getCurrentDateTime());
        this.replayStartTimestamp.set(this.dateProvider.getCurrentTimeMillis());
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void resume() {
        setSegmentTimestamp(DateUtils.getCurrentDateTime());
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void stop() {
        ReplayCache replayCache = this.cache;
        if (replayCache != null) {
            replayCache.close();
        }
        setCurrentSegment(-1);
        this.replayStartTimestamp.set(0L);
        setSegmentTimestamp(null);
        SentryId EMPTY_ID = SentryId.EMPTY_ID;
        Intrinsics.checkNotNullExpressionValue(EMPTY_ID, "EMPTY_ID");
        setCurrentReplayId(EMPTY_ID);
    }

    public static /* synthetic */ CaptureStrategy.ReplaySegment createSegmentInternal$default(BaseCaptureStrategy baseCaptureStrategy, long j, Date date, SentryId sentryId, int i, int i2, int i3, SentryReplayEvent.ReplayType replayType, ReplayCache replayCache, int i4, int i5, String str, List list, Deque deque, int i6, Object obj) {
        if (obj == null) {
            return baseCaptureStrategy.createSegmentInternal(j, date, sentryId, i, i2, i3, (i6 & 64) != 0 ? baseCaptureStrategy.getReplayType() : replayType, (i6 & 128) != 0 ? baseCaptureStrategy.cache : replayCache, (i6 & 256) != 0 ? baseCaptureStrategy.getRecorderConfig().getFrameRate() : i4, (i6 & 512) != 0 ? baseCaptureStrategy.getRecorderConfig().getBitRate() : i5, (i6 & 1024) != 0 ? baseCaptureStrategy.getScreenAtStart() : str, (i6 & 2048) != 0 ? null : list, (i6 & 4096) != 0 ? baseCaptureStrategy.currentEvents : deque);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createSegmentInternal");
    }

    protected final CaptureStrategy.ReplaySegment createSegmentInternal(long duration, Date currentSegmentTimestamp, SentryId replayId, int segmentId, int height, int width, SentryReplayEvent.ReplayType replayType, ReplayCache cache, int frameRate, int bitRate, String screenAtStart, List<Breadcrumb> breadcrumbs, Deque<RRWebEvent> events) {
        Intrinsics.checkNotNullParameter(currentSegmentTimestamp, "currentSegmentTimestamp");
        Intrinsics.checkNotNullParameter(replayId, "replayId");
        Intrinsics.checkNotNullParameter(replayType, "replayType");
        Intrinsics.checkNotNullParameter(events, "events");
        return CaptureStrategy.INSTANCE.createSegment(this.hub, this.options, duration, currentSegmentTimestamp, replayId, segmentId, height, width, replayType, cache, frameRate, bitRate, screenAtStart, breadcrumbs, events);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void onConfigurationChanged(ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        setRecorderConfig(recorderConfig);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        List<RRWebIncrementalSnapshotEvent> listConvert = this.gestureConverter.convert(event, getRecorderConfig());
        if (listConvert != null) {
            CollectionsKt.addAll(this.currentEvents, listConvert);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BaseCaptureStrategy.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/capture/BaseCaptureStrategy$ReplayPersistingExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class ReplayPersistingExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryReplayPersister-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(r, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }

    static /* synthetic */ ReadWriteProperty persistableAtomicNullable$default(BaseCaptureStrategy baseCaptureStrategy, Object obj, final String str, Function3 function3, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: persistableAtomicNullable");
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            function3 = new Function3<String, T, T, Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy.persistableAtomicNullable.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str2, Object obj3, Object obj4) {
                    invoke2(str2, obj3, obj4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2, T t, T t2) {
                    ReplayCache cache = BaseCaptureStrategy.this.getCache();
                    if (cache != null) {
                        cache.persistSegmentValues(str, String.valueOf(t2));
                    }
                }
            };
        }
        return new AnonymousClass2(obj, baseCaptureStrategy, function3, str);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: BaseCaptureStrategy.kt */
    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001J&\u0010\u0005\u001a\u0004\u0018\u00018\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0096\u0002¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002J.\u0010\u000e\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000fR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"io/sentry/android/replay/capture/BaseCaptureStrategy$persistableAtomicNullable$2", "Lkotlin/properties/ReadWriteProperty;", "", "value", "Ljava/util/concurrent/atomic/AtomicReference;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "runInBackground", "", "task", "Lkotlin/Function0;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.sentry.android.replay.capture.BaseCaptureStrategy$persistableAtomicNullable$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements ReadWriteProperty<Object, T> {
        final /* synthetic */ Function3<String, T, T, Unit> $onChange;
        final /* synthetic */ String $propertyName;
        final /* synthetic */ BaseCaptureStrategy this$0;
        private final AtomicReference<T> value;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(T t, BaseCaptureStrategy baseCaptureStrategy, Function3<? super String, ? super T, ? super T, Unit> function3, String str) {
            this.this$0 = baseCaptureStrategy;
            this.$onChange = function3;
            this.$propertyName = str;
            this.value = new AtomicReference<>(t);
        }

        private final void runInBackground(final Function0<Unit> task) {
            if (this.this$0.options.getMainThreadChecker().isMainThread()) {
                ExecutorsKt.submitSafely(this.this$0.getPersistingExecutor(), this.this$0.options, "CaptureStrategy.runInBackground", new Runnable() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$persistableAtomicNullable$2$runInBackground$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        task.invoke();
                    }
                });
            } else {
                task.invoke();
            }
        }

        @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
        public T getValue(Object thisRef, KProperty<?> property) {
            Intrinsics.checkNotNullParameter(property, "property");
            return this.value.get();
        }

        @Override // kotlin.properties.ReadWriteProperty
        public void setValue(Object thisRef, KProperty<?> property, final T value) {
            Intrinsics.checkNotNullParameter(property, "property");
            final T andSet = this.value.getAndSet(value);
            if (Intrinsics.areEqual(andSet, value)) {
                return;
            }
            final Function3<String, T, T, Unit> function3 = this.$onChange;
            final String str = this.$propertyName;
            runInBackground(new Function0<Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy$persistableAtomicNullable$2$setValue$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function3.invoke(str, andSet, value);
                }
            });
        }
    }

    private final <T> ReadWriteProperty<Object, T> persistableAtomicNullable(T initialValue, String propertyName, Function3<? super String, ? super T, ? super T, Unit> onChange) {
        return new AnonymousClass2(initialValue, this, onChange, propertyName);
    }

    static /* synthetic */ ReadWriteProperty persistableAtomic$default(BaseCaptureStrategy baseCaptureStrategy, Object obj, final String str, Function3 function3, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: persistableAtomic");
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            function3 = new Function3<String, T, T, Unit>() { // from class: io.sentry.android.replay.capture.BaseCaptureStrategy.persistableAtomic.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str2, Object obj3, Object obj4) {
                    invoke2(str2, obj3, obj4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str2, T t, T t2) {
                    ReplayCache cache = BaseCaptureStrategy.this.getCache();
                    if (cache != null) {
                        cache.persistSegmentValues(str, String.valueOf(t2));
                    }
                }
            };
        }
        return new AnonymousClass2(obj, baseCaptureStrategy, function3, str);
    }

    private final <T> ReadWriteProperty<Object, T> persistableAtomic(T initialValue, String propertyName, Function3<? super String, ? super T, ? super T, Unit> onChange) {
        return new AnonymousClass2(initialValue, this, onChange, propertyName);
    }

    private final <T> ReadWriteProperty<Object, T> persistableAtomic(Function3<? super String, ? super T, ? super T, Unit> onChange) {
        return new AnonymousClass2(null, this, onChange, "");
    }
}
