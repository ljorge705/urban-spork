package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.di.capture.MotionCaptureComponentHolder;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent;
import io.sentry.Session;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0006\u0010\f\u001a\u00020\nR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder;", "", "()V", "component", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponent;", "getComponent", "motionHostComponent", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponent;", "getComponent$onfido_capture_sdk_core_release", Session.JsonKeys.INIT, "", "motionCaptureComponent", "onDestroy", "Companion", "Holder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCaptureComponentHolder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MotionCaptureComponentHolder> instance$delegate = LazyKt.lazy(new Function0<MotionCaptureComponentHolder>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.di.capture.MotionCaptureComponentHolder$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MotionCaptureComponentHolder invoke() {
            return MotionCaptureComponentHolder.Holder.INSTANCE.getINSTANCE();
        }
    });
    private MotionCaptureComponent component;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder$Companion;", "", "()V", "instance", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder;", "getInstance$annotations", "getInstance", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder;", "instance$delegate", "Lkotlin/Lazy;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final MotionCaptureComponentHolder getInstance() {
            return (MotionCaptureComponentHolder) MotionCaptureComponentHolder.instance$delegate.getValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder$Holder;", "", "()V", "INSTANCE", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder;", "getINSTANCE", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponentHolder;", "INSTANCE$1", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Holder {
        public static final Holder INSTANCE = new Holder();

        /* renamed from: INSTANCE$1, reason: from kotlin metadata */
        private static final MotionCaptureComponentHolder INSTANCE = new MotionCaptureComponentHolder(null);

        private Holder() {
        }

        public final MotionCaptureComponentHolder getINSTANCE() {
            return INSTANCE;
        }
    }

    private MotionCaptureComponentHolder() {
    }

    public static final MotionCaptureComponentHolder getInstance() {
        return INSTANCE.getInstance();
    }

    public final MotionCaptureComponent getComponent$onfido_capture_sdk_core_release(MotionHostComponent motionHostComponent) {
        Intrinsics.checkNotNullParameter(motionHostComponent, "motionHostComponent");
        MotionCaptureComponent motionCaptureComponent = this.component;
        if (motionCaptureComponent != null) {
            return motionCaptureComponent;
        }
        init(motionHostComponent);
        MotionCaptureComponent motionCaptureComponent2 = this.component;
        Intrinsics.checkNotNull(motionCaptureComponent2);
        return motionCaptureComponent2;
    }

    public final void init(MotionCaptureComponent motionCaptureComponent) {
        Intrinsics.checkNotNullParameter(motionCaptureComponent, "motionCaptureComponent");
        this.component = motionCaptureComponent;
    }

    public final void onDestroy() {
        this.component = null;
    }

    public /* synthetic */ MotionCaptureComponentHolder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final void init(MotionHostComponent motionHostComponent) {
        this.component = DaggerMotionCaptureComponent.builder().motionHostComponent(motionHostComponent).build();
    }
}
