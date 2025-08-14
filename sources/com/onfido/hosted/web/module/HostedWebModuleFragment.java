package com.onfido.hosted.web.module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.databinding.OnfidoHostedWebModuleFragmentLayoutBinding;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.webview.OnfidoWebViewClient;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.hosted.web.module.HostedWebModuleViewModel;
import com.onfido.hosted.web.module.di.DaggerHostedWebModuleComponent;
import com.onfido.hosted.web.module.di.HostedWebModuleComponent;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment;
import com.onfido.hosted.web.module.model.CaptureSDKExternalLinkResponse;
import com.onfido.hosted.web.module.model.HostedWebModuleCancelled;
import com.onfido.hosted.web.module.model.HostedWebModuleExit;
import com.onfido.hosted.web.module.model.HostedWebModuleFailed;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import com.onfido.hosted.web.module.model.HostedWebModuleResult;
import com.onfido.hosted.web.module.model.HostedWebModuleScriptState;
import com.onfido.hosted.web.module.model.HostedWebModuleSuccess;
import com.onfido.hosted.web.module.utils.HostedWebModuleExtKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.net.MalformedURLException;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;
import org.jmrtd.lds.LDSFile;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0016J$\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020 H\u0016J\u001a\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0017J\u0010\u00102\u001a\u00020 2\u0006\u00103\u001a\u000204H\u0002J\u0010\u00105\u001a\u00020 2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020:H\u0002J\b\u0010;\u001a\u00020 H\u0002J\b\u0010<\u001a\u00020 H\u0002J\b\u0010=\u001a\u00020 H\u0002J\u0010\u0010>\u001a\u00020 2\u0006\u00109\u001a\u00020:H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001d¨\u0006@"}, d2 = {"Lcom/onfido/hosted/web/module/HostedWebModuleFragment;", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "()V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoHostedWebModuleFragmentLayoutBinding;", "component", "Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "getComponent", "()Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "component$delegate", "Lkotlin/Lazy;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "getLifecycleAwareDialog", "()Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "lifecycleAwareDialog$delegate", "permissionDelegate", "Lcom/onfido/hosted/web/module/FragmentPermissionDelegate;", "getPermissionDelegate", "()Lcom/onfido/hosted/web/module/FragmentPermissionDelegate;", "permissionDelegate$delegate", "pickerDelegate", "Lcom/onfido/hosted/web/module/DocumentPickerDelegate;", "getPickerDelegate", "()Lcom/onfido/hosted/web/module/DocumentPickerDelegate;", "pickerDelegate$delegate", "viewModel", "Lcom/onfido/hosted/web/module/HostedWebModuleViewModel;", "getViewModel", "()Lcom/onfido/hosted/web/module/HostedWebModuleViewModel;", "viewModel$delegate", "evaluateBootstrapScript", "", "getModuleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "handleBackNavigation", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onViewCreated", "view", "openNewTab", "externalNavigationLink", "Lcom/onfido/hosted/web/module/HostedWebModuleViewModel$ExternalNavigationLink;", "setActionBarVisibility", "isVisible", "", "setFragmentResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "setupWebView", "showErrorScreen", "showExitDialog", "submitResult", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleFragment extends FlowFragment {
    public static final String CAPTURE_SDK_INTERFACE_NAME = "callbackHandler";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_CAPTURE_SDK_BRIDGE_URL = "capture-sdk-bridge-url";
    public static final String KEY_CAPTURE_SDK_MODULE_INFO = "module_info";
    public static final String KEY_DARK_MODE_ENABLED = "is_dark_mode_key";
    public static final String REQUEST_KEY = "web_view_request_key";
    public static final String WEB_VIEW_RESULT_KEY = "web_view_result_key";
    private OnfidoHostedWebModuleFragmentLayoutBinding binding;

    /* renamed from: component$delegate, reason: from kotlin metadata */
    private final Lazy component;

    /* renamed from: lifecycleAwareDialog$delegate, reason: from kotlin metadata */
    private final Lazy lifecycleAwareDialog;

    /* renamed from: permissionDelegate$delegate, reason: from kotlin metadata */
    private final Lazy permissionDelegate;

    /* renamed from: pickerDelegate$delegate, reason: from kotlin metadata */
    private final Lazy pickerDelegate;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/hosted/web/module/HostedWebModuleFragment$Companion;", "", "()V", "CAPTURE_SDK_INTERFACE_NAME", "", "KEY_CAPTURE_SDK_BRIDGE_URL", "KEY_CAPTURE_SDK_MODULE_INFO", "KEY_DARK_MODE_ENABLED", "REQUEST_KEY", "WEB_VIEW_RESULT_KEY", "getResult", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "bundle", "Landroid/os/Bundle;", "newInstance", "Lcom/onfido/hosted/web/module/HostedWebModuleFragment;", "requestKey", "moduleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "captureSDKBridgeUrl", "isDarkMode", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final HostedWebModuleResult getResult(Bundle bundle) {
            Parcelable parcelable;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) bundle.getParcelable(HostedWebModuleFragment.WEB_VIEW_RESULT_KEY, HostedWebModuleResult.class);
            } else {
                Parcelable parcelable2 = bundle.getParcelable(HostedWebModuleFragment.WEB_VIEW_RESULT_KEY);
                if (!(parcelable2 instanceof HostedWebModuleResult)) {
                    parcelable2 = null;
                }
                parcelable = (HostedWebModuleResult) parcelable2;
            }
            if (parcelable != null) {
                return (HostedWebModuleResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public final HostedWebModuleFragment newInstance(String requestKey, HostedWebModuleModuleInfo moduleInfo, String captureSDKBridgeUrl, boolean isDarkMode) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(moduleInfo, "moduleInfo");
            HostedWebModuleFragment hostedWebModuleFragment = new HostedWebModuleFragment();
            hostedWebModuleFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(HostedWebModuleFragment.KEY_DARK_MODE_ENABLED, Boolean.valueOf(isDarkMode)), TuplesKt.to(HostedWebModuleFragment.KEY_CAPTURE_SDK_MODULE_INFO, moduleInfo), TuplesKt.to(HostedWebModuleFragment.KEY_CAPTURE_SDK_BRIDGE_URL, captureSDKBridgeUrl), TuplesKt.to(HostedWebModuleFragment.REQUEST_KEY, requestKey)));
            return hostedWebModuleFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/onfido/hosted/web/module/HostedWebModuleFragment$handleBackNavigation$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$handleBackNavigation$1, reason: invalid class name */
    public static final class AnonymousClass1 extends OnBackPressedCallback {
        AnonymousClass1() {
            super(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void handleOnBackPressed$lambda$0(HostedWebModuleFragment this$0, String str) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (Intrinsics.areEqual(str, Constants.CASEFIRST_FALSE) && this$0.getModuleInfo().getStudioInfo() == null) {
                this$0.getParentFragmentManager().popBackStack();
                this$0.setFragmentResult(HostedWebModuleCancelled.INSTANCE);
            }
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding = HostedWebModuleFragment.this.binding;
            if (onfidoHostedWebModuleFragmentLayoutBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                onfidoHostedWebModuleFragmentLayoutBinding = null;
            }
            WebView webView = onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView;
            String navigateBackScript = HostedWebModuleFragment.this.getViewModel().getNavigateBackScript();
            final HostedWebModuleFragment hostedWebModuleFragment = HostedWebModuleFragment.this;
            webView.evaluateJavascript(navigateBackScript, new ValueCallback() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$handleBackNavigation$1$$ExternalSyntheticLambda0
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    HostedWebModuleFragment.AnonymousClass1.handleOnBackPressed$lambda$0(hostedWebModuleFragment, (String) obj);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$2", f = "HostedWebModuleFragment.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$2$1", f = "HostedWebModuleFragment.kt", i = {}, l = {112}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ HostedWebModuleFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$2$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01701 extends AdaptedFunctionReference implements Function2<HostedWebModuleResult, Continuation<? super Unit>, Object>, SuspendFunction {
                C01701(Object obj) {
                    super(2, obj, HostedWebModuleFragment.class, "submitResult", "submitResult(Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(HostedWebModuleResult hostedWebModuleResult, Continuation<? super Unit> continuation) {
                    return AnonymousClass1.invokeSuspend$submitResult((HostedWebModuleFragment) this.receiver, hostedWebModuleResult, continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(HostedWebModuleFragment hostedWebModuleFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = hostedWebModuleFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$submitResult(HostedWebModuleFragment hostedWebModuleFragment, HostedWebModuleResult hostedWebModuleResult, Continuation continuation) {
                hostedWebModuleFragment.submitResult(hostedWebModuleResult);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<HostedWebModuleResult> result$onfido_capture_sdk_core_release = this.this$0.getViewModel().getResult$onfido_capture_sdk_core_release();
                    C01701 c01701 = new C01701(this.this$0);
                    this.label = 1;
                    if (HostedWebModuleExtKt.collectNonNull(result$onfido_capture_sdk_core_release, c01701, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HostedWebModuleFragment.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner viewLifecycleOwner = HostedWebModuleFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(HostedWebModuleFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$3", f = "HostedWebModuleFragment.kt", i = {}, l = {LDSFile.EF_DG2_TAG}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$3$1", f = "HostedWebModuleFragment.kt", i = {}, l = {LDSFile.EF_DG4_TAG}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$3$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ HostedWebModuleFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$3$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01711 extends AdaptedFunctionReference implements Function2<HostedWebModuleViewModel.ExternalNavigationLink, Continuation<? super Unit>, Object>, SuspendFunction {
                C01711(Object obj) {
                    super(2, obj, HostedWebModuleFragment.class, "openNewTab", "openNewTab(Lcom/onfido/hosted/web/module/HostedWebModuleViewModel$ExternalNavigationLink;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(HostedWebModuleViewModel.ExternalNavigationLink externalNavigationLink, Continuation<? super Unit> continuation) {
                    return AnonymousClass1.invokeSuspend$openNewTab((HostedWebModuleFragment) this.receiver, externalNavigationLink, continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(HostedWebModuleFragment hostedWebModuleFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = hostedWebModuleFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$openNewTab(HostedWebModuleFragment hostedWebModuleFragment, HostedWebModuleViewModel.ExternalNavigationLink externalNavigationLink, Continuation continuation) {
                hostedWebModuleFragment.openNewTab(externalNavigationLink);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<HostedWebModuleViewModel.ExternalNavigationLink> externalLinkResult$onfido_capture_sdk_core_release = this.this$0.getViewModel().getExternalLinkResult$onfido_capture_sdk_core_release();
                    C01711 c01711 = new C01711(this.this$0);
                    this.label = 1;
                    if (HostedWebModuleExtKt.collectNonNull(externalLinkResult$onfido_capture_sdk_core_release, c01711, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HostedWebModuleFragment.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner viewLifecycleOwner = HostedWebModuleFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(HostedWebModuleFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4", f = "HostedWebModuleFragment.kt", i = {}, l = {123}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4$1", f = "HostedWebModuleFragment.kt", i = {}, l = {PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ HostedWebModuleFragment this$0;

            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "scriptState", "Lcom/onfido/hosted/web/module/model/HostedWebModuleScriptState;"}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4$1$1", f = "HostedWebModuleFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.onfido.hosted.web.module.HostedWebModuleFragment$onViewCreated$4$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01721 extends SuspendLambda implements Function2<HostedWebModuleScriptState, Continuation<? super Unit>, Object> {
                /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ HostedWebModuleFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01721(HostedWebModuleFragment hostedWebModuleFragment, Continuation<? super C01721> continuation) {
                    super(2, continuation);
                    this.this$0 = hostedWebModuleFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C01721 c01721 = new C01721(this.this$0, continuation);
                    c01721.L$0 = obj;
                    return c01721;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(HostedWebModuleScriptState hostedWebModuleScriptState, Continuation<? super Unit> continuation) {
                    return ((C01721) create(hostedWebModuleScriptState, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (((HostedWebModuleScriptState) this.L$0) == HostedWebModuleScriptState.SHOULD_EVALUATE) {
                        this.this$0.evaluateBootstrapScript();
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(HostedWebModuleFragment hostedWebModuleFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = hostedWebModuleFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<HostedWebModuleScriptState> shouldEvaluateScript$onfido_capture_sdk_core_release = this.this$0.getViewModel().getShouldEvaluateScript$onfido_capture_sdk_core_release();
                    C01721 c01721 = new C01721(this.this$0, null);
                    this.label = 1;
                    if (HostedWebModuleExtKt.collectNonNull(shouldEvaluateScript$onfido_capture_sdk_core_release, c01721, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HostedWebModuleFragment.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner viewLifecycleOwner = HostedWebModuleFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(HostedWebModuleFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public HostedWebModuleFragment() {
        super(R.layout.onfido_hosted_web_module_fragment_layout);
        this.component = LazyKt.lazy(new Function0<HostedWebModuleComponent>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$component$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HostedWebModuleComponent invoke() {
                HostedWebModuleComponent.Factory factory = DaggerHostedWebModuleComponent.factory();
                SdkController companion = SdkController.INSTANCE.getInstance();
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                return factory.create(SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null));
            }
        });
        this.viewModel = LazyKt.lazy(new Function0<HostedWebModuleViewModel>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HostedWebModuleViewModel invoke() {
                final HostedWebModuleFragment hostedWebModuleFragment = this.this$0;
                return (HostedWebModuleViewModel) new ViewModelProvider(hostedWebModuleFragment, new ViewModelProvider.Factory() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        HostedWebModuleViewModel hostedWebModuleViewModel = hostedWebModuleFragment.getComponent().getHostedWebModuleViewModel();
                        Intrinsics.checkNotNull(hostedWebModuleViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return hostedWebModuleViewModel;
                    }
                }).get(HostedWebModuleViewModel.class);
            }
        });
        this.lifecycleAwareDialog = LazyKt.lazy(new Function0<LifecycleAwareDialog>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$lifecycleAwareDialog$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LifecycleAwareDialog invoke() {
                return new LifecycleAwareDialog(this.this$0, (Function1) null, 2, (DefaultConstructorMarker) null);
            }
        });
        this.pickerDelegate = LazyKt.lazy(new Function0<DocumentPickerDelegate>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$pickerDelegate$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DocumentPickerDelegate invoke() {
                return new DocumentPickerDelegate(this.this$0);
            }
        });
        this.permissionDelegate = LazyKt.lazy(new Function0<FragmentPermissionDelegate>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$permissionDelegate$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentPermissionDelegate invoke() {
                HostedWebModuleFragment hostedWebModuleFragment = this.this$0;
                FragmentManager parentFragmentManager = hostedWebModuleFragment.getParentFragmentManager();
                Intrinsics.checkNotNullExpressionValue(parentFragmentManager, "getParentFragmentManager(...)");
                return new FragmentPermissionDelegate(hostedWebModuleFragment, parentFragmentManager);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void evaluateBootstrapScript() {
        boolean z = requireArguments().getBoolean(KEY_DARK_MODE_ENABLED);
        HostedWebModuleViewModel viewModel = getViewModel();
        HostedWebModuleModuleInfo moduleInfo = getModuleInfo();
        WebModuleThemeBuilder webModuleThemeBuilder = WebModuleThemeBuilder.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        String captureSDKConfigScript = viewModel.getCaptureSDKConfigScript(moduleInfo, webModuleThemeBuilder.build(contextRequireContext, z));
        OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding = this.binding;
        if (onfidoHostedWebModuleFragmentLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleFragmentLayoutBinding = null;
        }
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.evaluateJavascript(captureSDKConfigScript, new ValueCallback() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                HostedWebModuleFragment.evaluateBootstrapScript$lambda$3(this.f$0, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void evaluateBootstrapScript$lambda$3(HostedWebModuleFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onScriptEvaluated();
        Timber.INSTANCE.d("Script has been evaluated: " + str, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostedWebModuleComponent getComponent() {
        return (HostedWebModuleComponent) this.component.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LifecycleAwareDialog getLifecycleAwareDialog() {
        return (LifecycleAwareDialog) this.lifecycleAwareDialog.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostedWebModuleModuleInfo getModuleInfo() {
        Parcelable parcelable;
        Bundle bundleRequireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(bundleRequireArguments, "requireArguments(...)");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) bundleRequireArguments.getParcelable(KEY_CAPTURE_SDK_MODULE_INFO, HostedWebModuleModuleInfo.class);
        } else {
            Parcelable parcelable2 = bundleRequireArguments.getParcelable(KEY_CAPTURE_SDK_MODULE_INFO);
            if (!(parcelable2 instanceof HostedWebModuleModuleInfo)) {
                parcelable2 = null;
            }
            parcelable = (HostedWebModuleModuleInfo) parcelable2;
        }
        if (parcelable != null) {
            return (HostedWebModuleModuleInfo) parcelable;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private final FragmentPermissionDelegate getPermissionDelegate() {
        return (FragmentPermissionDelegate) this.permissionDelegate.getValue();
    }

    private final DocumentPickerDelegate getPickerDelegate() {
        return (DocumentPickerDelegate) this.pickerDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostedWebModuleViewModel getViewModel() {
        return (HostedWebModuleViewModel) this.viewModel.getValue();
    }

    private final void handleBackNavigation() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new AnonymousClass1());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1$lambda$0(HostedWebModuleFragment this$0, OnfidoHostedWebModuleFragmentLayoutBinding this_with, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        this$0.getViewModel().onErrorRetried$onfido_capture_sdk_core_release();
        this_with.onfidoWebView.reload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openNewTab(HostedWebModuleViewModel.ExternalNavigationLink externalNavigationLink) {
        try {
            getViewModel().onCaptureSDKExternalLinkOpened$onfido_capture_sdk_core_release();
            if (externalNavigationLink.getTarget() == null || externalNavigationLink.getTarget() == CaptureSDKExternalLinkResponse.LinkTarget.OVERLAY) {
                HostedWebModuleExternalLinkFragment.INSTANCE.newInstance(externalNavigationLink.getUrl()).show(requireActivity().getSupportFragmentManager(), "");
            } else {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(externalNavigationLink.getUrl())));
            }
        } catch (MalformedURLException unused) {
            Timber.INSTANCE.e("CaptureSDK wrong external link: " + externalNavigationLink.getUrl(), new Object[0]);
        }
    }

    private final void setActionBarVisibility(boolean isVisible) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        ActionBar supportActionBar = ((AppCompatActivity) fragmentActivityRequireActivity).getSupportActionBar();
        if (supportActionBar != null) {
            if (isVisible) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFragmentResult(HostedWebModuleResult result) {
        String string = requireArguments().getString(REQUEST_KEY);
        if (string == null) {
            string = "";
        }
        getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(WEB_VIEW_RESULT_KEY, result)));
    }

    private final void setupWebView() {
        String string = requireArguments().getString(KEY_CAPTURE_SDK_BRIDGE_URL);
        if (string == null) {
            string = "";
        }
        HostedWebModuleListener hostedWebModuleListener = new HostedWebModuleListener(getViewModel());
        final OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding = this.binding;
        if (onfidoHostedWebModuleFragmentLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleFragmentLayoutBinding = null;
        }
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.getSettings().setJavaScriptEnabled(true);
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.getSettings().setSupportZoom(false);
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.onfido_transparent));
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.setWebChromeClient(new ChromeClient(getPermissionDelegate(), getPickerDelegate()));
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.setWebViewClient(new OnfidoWebViewClient(new Function0<Unit>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$setupWebView$1$1
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
                this.this$0.getViewModel().onPageFinished();
            }
        }, new Function0<Unit>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$setupWebView$1$2
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
                onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.setVisibility(0);
                onfidoHostedWebModuleFragmentLayoutBinding.webViewErrorLayout.setVisibility(8);
            }
        }, new Function1<WebResourceError, Unit>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$setupWebView$1$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WebResourceError webResourceError) {
                invoke2(webResourceError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WebResourceError webResourceError) {
                Timber.INSTANCE.e("Something went wrong while loading module: " + this.this$0.getModuleInfo() + " with error: " + webResourceError, new Object[0]);
                this.this$0.showErrorScreen();
            }
        }));
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.addJavascriptInterface(hostedWebModuleListener, CAPTURE_SDK_INTERFACE_NAME);
        onfidoHostedWebModuleFragmentLayoutBinding.onfidoWebView.loadUrl(getViewModel().getBridgeUrl$onfido_capture_sdk_core_release(string));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorScreen() {
        OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding = this.binding;
        OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding2 = null;
        if (onfidoHostedWebModuleFragmentLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleFragmentLayoutBinding = null;
        }
        onfidoHostedWebModuleFragmentLayoutBinding.webViewErrorLayout.setVisibility(0);
        OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding3 = this.binding;
        if (onfidoHostedWebModuleFragmentLayoutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            onfidoHostedWebModuleFragmentLayoutBinding2 = onfidoHostedWebModuleFragmentLayoutBinding3;
        }
        onfidoHostedWebModuleFragmentLayoutBinding2.onfidoWebView.setVisibility(8);
    }

    private final void showExitDialog() {
        getViewModel().flowUserExit(getModuleInfo());
        LifecycleAwareDialog lifecycleAwareDialog = getLifecycleAwareDialog();
        int i = R.string.onfido_generic_close_sdk_title;
        lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(i), R.string.onfido_generic_close_sdk_subtitle, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_generic_close_sdk_exit, (56 & 8) != 0 ? null : Integer.valueOf(R.string.onfido_generic_close_sdk_cancel), (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : new Function1<DialogInterface, Unit>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment.showExitDialog.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                HostedWebModuleFragment.this.getViewModel().flowUserExitCanceled(HostedWebModuleFragment.this.getModuleInfo());
                HostedWebModuleFragment.this.getLifecycleAwareDialog().dismiss();
            }
        }), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment.showExitDialog.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                HostedWebModuleFragment.this.getViewModel().flowUserExitConfirmed(HostedWebModuleFragment.this.getModuleInfo());
                String string = HostedWebModuleFragment.this.requireArguments().getString(HostedWebModuleFragment.REQUEST_KEY);
                if (string == null) {
                    string = "";
                }
                HostedWebModuleFragment.this.getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(HostedWebModuleFragment.WEB_VIEW_RESULT_KEY, new HostedWebModuleExit())));
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void submitResult(HostedWebModuleResult result) {
        if (result instanceof HostedWebModuleExit) {
            showExitDialog();
            return;
        }
        if (!(result instanceof HostedWebModuleFailed)) {
            if (result instanceof HostedWebModuleCancelled) {
                getParentFragmentManager().popBackStack();
            } else if (!(result instanceof HostedWebModuleSuccess)) {
                return;
            }
        }
        setFragmentResult(result);
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        setActionBarVisibility(false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBindingInflate = OnfidoHostedWebModuleFragmentLayoutBinding.inflate(inflater);
        Intrinsics.checkNotNullExpressionValue(onfidoHostedWebModuleFragmentLayoutBindingInflate, "inflate(...)");
        this.binding = onfidoHostedWebModuleFragmentLayoutBindingInflate;
        if (onfidoHostedWebModuleFragmentLayoutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleFragmentLayoutBindingInflate = null;
        }
        LinearLayout root = onfidoHostedWebModuleFragmentLayoutBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        setActionBarVisibility(true);
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        WebView.setWebContentsDebuggingEnabled(false);
        final OnfidoHostedWebModuleFragmentLayoutBinding onfidoHostedWebModuleFragmentLayoutBinding = this.binding;
        if (onfidoHostedWebModuleFragmentLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleFragmentLayoutBinding = null;
        }
        onfidoHostedWebModuleFragmentLayoutBinding.reloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.hosted.web.module.HostedWebModuleFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HostedWebModuleFragment.onViewCreated$lambda$1$lambda$0(this.f$0, onfidoHostedWebModuleFragmentLayoutBinding, view2);
            }
        });
        setupWebView();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new AnonymousClass2(null), 3, null);
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), null, null, new AnonymousClass3(null), 3, null);
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner3), null, null, new AnonymousClass4(null), 3, null);
        handleBackNavigation();
    }
}
