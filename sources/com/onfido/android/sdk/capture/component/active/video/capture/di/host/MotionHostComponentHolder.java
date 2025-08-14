package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponentHolder;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import io.sentry.Session;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007J\u0006\u0010\u000e\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder;", "", "()V", "component", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponent;", "getComponent", "sdkComponent", "Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "motionOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "getComponent$onfido_capture_sdk_core_release", Session.JsonKeys.INIT, "", "hostComponent", "onDestroy", "Companion", "Holder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionHostComponentHolder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MotionHostComponentHolder> instance$delegate = LazyKt.lazy(new Function0<MotionHostComponentHolder>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponentHolder$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MotionHostComponentHolder invoke() {
            return MotionHostComponentHolder.Holder.INSTANCE.getINSTANCE();
        }
    });
    private MotionHostComponent component;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder$Companion;", "", "()V", "instance", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder;", "getInstance$annotations", "getInstance", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder;", "instance$delegate", "Lkotlin/Lazy;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final MotionHostComponentHolder getInstance() {
            return (MotionHostComponentHolder) MotionHostComponentHolder.instance$delegate.getValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder$Holder;", "", "()V", "INSTANCE", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder;", "getINSTANCE", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponentHolder;", "INSTANCE$1", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Holder {
        public static final Holder INSTANCE = new Holder();

        /* renamed from: INSTANCE$1, reason: from kotlin metadata */
        private static final MotionHostComponentHolder INSTANCE = new MotionHostComponentHolder(null);

        private Holder() {
        }

        public final MotionHostComponentHolder getINSTANCE() {
            return INSTANCE;
        }
    }

    private MotionHostComponentHolder() {
    }

    public static final MotionHostComponentHolder getInstance() {
        return INSTANCE.getInstance();
    }

    private final void init(SdkComponent sdkComponent, MotionCaptureVariantOptions motionOptions) {
        this.component = DaggerMotionHostComponent.builder().sdkComponent(sdkComponent).motionHostModule(new MotionHostModule(motionOptions)).build();
    }

    public final MotionHostComponent getComponent$onfido_capture_sdk_core_release(SdkComponent sdkComponent, MotionCaptureVariantOptions motionOptions) {
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        Intrinsics.checkNotNullParameter(motionOptions, "motionOptions");
        MotionHostComponent motionHostComponent = this.component;
        if (motionHostComponent != null) {
            return motionHostComponent;
        }
        init(sdkComponent, motionOptions);
        MotionHostComponent motionHostComponent2 = this.component;
        Intrinsics.checkNotNull(motionHostComponent2);
        return motionHostComponent2;
    }

    public final void onDestroy() {
        this.component = null;
    }

    public /* synthetic */ MotionHostComponentHolder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void init(MotionHostComponent hostComponent) {
        Intrinsics.checkNotNullParameter(hostComponent, "hostComponent");
        this.component = hostComponent;
    }
}
