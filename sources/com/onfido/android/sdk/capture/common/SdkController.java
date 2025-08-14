package com.onfido.android.sdk.capture.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.di.DaggerSdkComponent;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.common.di.SdkModule;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import io.sentry.Session;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\"\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0006\u0010\u0019\u001a\u00020\u0015R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/common/SdkController;", "", "()V", "component", "Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "isUnderTest", "", "isUnderTest$onfido_capture_sdk_core_release", "()Z", "setUnderTest$onfido_capture_sdk_core_release", "(Z)V", "getSdkComponent", "context", "Landroid/content/Context;", "defaultOnfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "getUILessSdkComponent", "activity", "Landroid/app/Activity;", "onfidoConfig", Session.JsonKeys.INIT, "", "flowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "sdkComponent", "onDestroy", "Companion", "Holder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SdkController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SdkController> instance$delegate = LazyKt.lazy(new Function0<SdkController>() { // from class: com.onfido.android.sdk.capture.common.SdkController$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SdkController invoke() {
            return SdkController.Holder.INSTANCE.getINSTANCE();
        }
    });
    private SdkComponent component;
    private boolean isUnderTest;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/common/SdkController$Companion;", "", "()V", "instance", "Lcom/onfido/android/sdk/capture/common/SdkController;", "getInstance$annotations", "getInstance", "()Lcom/onfido/android/sdk/capture/common/SdkController;", "instance$delegate", "Lkotlin/Lazy;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final SdkController getInstance() {
            return (SdkController) SdkController.instance$delegate.getValue();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/common/SdkController$Holder;", "", "()V", "INSTANCE", "Lcom/onfido/android/sdk/capture/common/SdkController;", "getINSTANCE", "()Lcom/onfido/android/sdk/capture/common/SdkController;", "INSTANCE$1", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Holder {
        public static final Holder INSTANCE = new Holder();

        /* renamed from: INSTANCE$1, reason: from kotlin metadata */
        private static final SdkController INSTANCE = new SdkController(null);

        private Holder() {
        }

        public final SdkController getINSTANCE() {
            return INSTANCE;
        }
    }

    private SdkController() {
    }

    public static final SdkController getInstance() {
        return INSTANCE.getInstance();
    }

    public static /* synthetic */ SdkComponent getSdkComponent$default(SdkController sdkController, Context context, OnfidoConfig onfidoConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            onfidoConfig = null;
        }
        return sdkController.getSdkComponent(context, onfidoConfig);
    }

    public static /* synthetic */ void init$default(SdkController sdkController, Context context, OnfidoConfig onfidoConfig, FlowConfig flowConfig, int i, Object obj) {
        if ((i & 4) != 0) {
            flowConfig = null;
        }
        sdkController.init(context, onfidoConfig, flowConfig);
    }

    public final SdkComponent getSdkComponent(Context context, OnfidoConfig defaultOnfidoConfig) {
        Intent intent;
        Parcelable parcelable;
        Intrinsics.checkNotNullParameter(context, "context");
        SdkComponent sdkComponent = this.component;
        if (sdkComponent != null) {
            return sdkComponent;
        }
        BaseActivity baseActivity = context instanceof BaseActivity ? (BaseActivity) context : null;
        if (baseActivity != null && (intent = baseActivity.getIntent()) != null) {
            Intrinsics.checkNotNull(intent);
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG, OnfidoConfig.class);
            } else {
                Parcelable parcelableExtra = intent.getParcelableExtra(OnfidoConstants.ONFIDO_CONFIG);
                if (!(parcelableExtra instanceof OnfidoConfig)) {
                    parcelableExtra = null;
                }
                parcelable = (OnfidoConfig) parcelableExtra;
            }
            OnfidoConfig onfidoConfig = (OnfidoConfig) parcelable;
            if (onfidoConfig != null) {
                defaultOnfidoConfig = onfidoConfig;
            }
        }
        if (defaultOnfidoConfig == null) {
            Timber.INSTANCE.e("Onfido Config is missing. Finishing flow...", new Object[0]);
            if (baseActivity != null) {
                BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(baseActivity, OnfidoActivity.RESULT_EXIT_MISSING_ONFIDO_CONFIG, null, 2, null);
            }
        }
        DaggerSdkComponent.Builder builder = DaggerSdkComponent.builder();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        if (defaultOnfidoConfig == null) {
            defaultOnfidoConfig = new OnfidoConfig.Builder(context).build();
        }
        SdkComponent sdkComponentBuild = builder.sdkModule(new SdkModule(applicationContext, defaultOnfidoConfig, null)).build();
        this.component = sdkComponentBuild;
        Intrinsics.checkNotNull(sdkComponentBuild);
        return sdkComponentBuild;
    }

    public final SdkComponent getUILessSdkComponent(Activity activity, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        SdkComponent sdkComponent = this.component;
        if (sdkComponent != null) {
            return sdkComponent;
        }
        SdkComponent sdkComponentBuild = DaggerSdkComponent.builder().sdkModule(new SdkModule(activity, onfidoConfig, null)).build();
        this.component = sdkComponentBuild;
        Intrinsics.checkNotNull(sdkComponentBuild);
        return sdkComponentBuild;
    }

    public final void init(Context context, OnfidoConfig onfidoConfig, FlowConfig flowConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        if (this.isUnderTest) {
            return;
        }
        DaggerSdkComponent.Builder builder = DaggerSdkComponent.builder();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.component = builder.sdkModule(new SdkModule(applicationContext, onfidoConfig, flowConfig)).build();
    }

    /* renamed from: isUnderTest$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsUnderTest() {
        return this.isUnderTest;
    }

    public final void onDestroy() {
        this.component = null;
    }

    public final void setUnderTest$onfido_capture_sdk_core_release(boolean z) {
        this.isUnderTest = z;
    }

    public /* synthetic */ SdkController(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void init(SdkComponent sdkComponent) {
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        this.component = sdkComponent;
        this.isUnderTest = true;
    }
}
