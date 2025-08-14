package com.onfido.hosted.web.module.externallink;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoHostedWebModuleBottomSheetLayoutBinding;
import com.onfido.hosted.web.module.di.DaggerHostedWebModuleComponent;
import com.onfido.hosted.web.module.di.HostedWebModuleComponent;
import com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment;
import com.onfido.hosted.web.module.utils.HostedWebModuleExtKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.net.URL;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0017J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130&H\u0003J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006*"}, d2 = {"Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoHostedWebModuleBottomSheetLayoutBinding;", "component", "Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "getComponent", "()Lcom/onfido/hosted/web/module/di/HostedWebModuleComponent;", "component$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkViewModel;", "getViewModel", "()Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkViewModel;", "viewModel$delegate", "getUrlParams", "Ljava/net/URL;", "handleResult", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkResult;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "openWebView", "isPdf", "", "pageFinished", "Lkotlin/Function0;", "setProgressBarVisibility", "isVisible", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HostedWebModuleExternalLinkFragment extends BottomSheetDialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_URL = "url";
    private OnfidoHostedWebModuleBottomSheetLayoutBinding binding;

    /* renamed from: component$delegate, reason: from kotlin metadata */
    private final Lazy component = LazyKt.lazy(new Function0<HostedWebModuleComponent>() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$component$2
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

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = LazyKt.lazy(new Function0<HostedWebModuleExternalLinkViewModel>() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$viewModel$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HostedWebModuleExternalLinkViewModel invoke() {
            final HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment = this.this$0;
            return (HostedWebModuleExternalLinkViewModel) new ViewModelProvider(hostedWebModuleExternalLinkFragment, new ViewModelProvider.Factory() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                @Override // androidx.lifecycle.ViewModelProvider.Factory
                public <T extends ViewModel> T create(Class<T> modelClass) {
                    Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                    HostedWebModuleExternalLinkViewModel hostedWebModuleExternalLinkViewModel = hostedWebModuleExternalLinkFragment.getComponent().getHostedWebModuleExternalLinkViewModel();
                    Intrinsics.checkNotNull(hostedWebModuleExternalLinkViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                    return hostedWebModuleExternalLinkViewModel;
                }
            }).get(HostedWebModuleExternalLinkViewModel.class);
        }
    });

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkFragment$Companion;", "", "()V", "KEY_URL", "", "newInstance", "Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkFragment;", "url", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final HostedWebModuleExternalLinkFragment newInstance(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment = new HostedWebModuleExternalLinkFragment();
            hostedWebModuleExternalLinkFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("url", url)));
            return hostedWebModuleExternalLinkFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$handleResult$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ HostedWebModuleExternalLinkResult $result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HostedWebModuleExternalLinkResult hostedWebModuleExternalLinkResult) {
            super(0);
            this.$result = hostedWebModuleExternalLinkResult;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(HostedWebModuleExternalLinkFragment this$0, String str) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.setProgressBarVisibility(false);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding = HostedWebModuleExternalLinkFragment.this.binding;
            if (onfidoHostedWebModuleBottomSheetLayoutBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                onfidoHostedWebModuleBottomSheetLayoutBinding = null;
            }
            WebView webView = onfidoHostedWebModuleBottomSheetLayoutBinding.onfidoBottomSheetWebView;
            String jsCode = ((PdfSuccess) this.$result).getJsCode();
            final HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment = HostedWebModuleExternalLinkFragment.this;
            webView.evaluateJavascript(jsCode, new ValueCallback() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$handleResult$1$$ExternalSyntheticLambda0
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    HostedWebModuleExternalLinkFragment.AnonymousClass1.invoke$lambda$0(hostedWebModuleExternalLinkFragment, (String) obj);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$onViewCreated$1", f = "HostedWebModuleExternalLinkFragment.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$onViewCreated$1, reason: invalid class name and case insensitive filesystem */
    static final class C07401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$onViewCreated$1$1", f = "HostedWebModuleExternalLinkFragment.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$onViewCreated$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ HostedWebModuleExternalLinkFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$onViewCreated$1$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01741 extends AdaptedFunctionReference implements Function2<HostedWebModuleExternalLinkResult, Continuation<? super Unit>, Object>, SuspendFunction {
                C01741(Object obj) {
                    super(2, obj, HostedWebModuleExternalLinkFragment.class, "handleResult", "handleResult(Lcom/onfido/hosted/web/module/externallink/HostedWebModuleExternalLinkResult;)V", 4);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(HostedWebModuleExternalLinkResult hostedWebModuleExternalLinkResult, Continuation<? super Unit> continuation) {
                    return C01731.invokeSuspend$handleResult((HostedWebModuleExternalLinkFragment) this.receiver, hostedWebModuleExternalLinkResult, continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01731(HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment, Continuation<? super C01731> continuation) {
                super(2, continuation);
                this.this$0 = hostedWebModuleExternalLinkFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$handleResult(HostedWebModuleExternalLinkFragment hostedWebModuleExternalLinkFragment, HostedWebModuleExternalLinkResult hostedWebModuleExternalLinkResult, Continuation continuation) {
                hostedWebModuleExternalLinkFragment.handleResult(hostedWebModuleExternalLinkResult);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01731(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<HostedWebModuleExternalLinkResult> result$onfido_capture_sdk_core_release = this.this$0.getViewModel().getResult$onfido_capture_sdk_core_release();
                    C01741 c01741 = new C01741(this.this$0);
                    this.label = 1;
                    if (HostedWebModuleExtKt.collectNonNull(result$onfido_capture_sdk_core_release, c01741, this) == coroutine_suspended) {
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
                return ((C01731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        C07401(Continuation<? super C07401> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HostedWebModuleExternalLinkFragment.this.new C07401(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner viewLifecycleOwner = HostedWebModuleExternalLinkFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                Lifecycle.State state = Lifecycle.State.CREATED;
                C01731 c01731 = new C01731(HostedWebModuleExternalLinkFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, c01731, this) == coroutine_suspended) {
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
            return ((C07401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostedWebModuleComponent getComponent() {
        return (HostedWebModuleComponent) this.component.getValue();
    }

    private final URL getUrlParams() {
        return new URL(requireArguments().getString("url"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostedWebModuleExternalLinkViewModel getViewModel() {
        return (HostedWebModuleExternalLinkViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResult(HostedWebModuleExternalLinkResult result) {
        if (Intrinsics.areEqual(result, Failed.INSTANCE)) {
            dismiss();
            return;
        }
        if (Intrinsics.areEqual(result, Loading.INSTANCE)) {
            setProgressBarVisibility(true);
        } else if (result instanceof PdfSuccess) {
            openWebView(true, new AnonymousClass1(result));
        } else if (Intrinsics.areEqual(result, UrlSuccess.INSTANCE)) {
            openWebView(false, new Function0<Unit>() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment.handleResult.2
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
                    HostedWebModuleExternalLinkFragment.this.setProgressBarVisibility(false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$3$lambda$2(HostedWebModuleExternalLinkFragment this$0, DialogInterface dialogInterface) {
        final BottomSheetBehavior<FrameLayout> behavior;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Dialog dialog = this$0.getDialog();
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding = null;
        BottomSheetDialog bottomSheetDialog = dialog instanceof BottomSheetDialog ? (BottomSheetDialog) dialog : null;
        if (bottomSheetDialog == null || (behavior = bottomSheetDialog.getBehavior()) == null) {
            return;
        }
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding2 = this$0.binding;
        if (onfidoHostedWebModuleBottomSheetLayoutBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleBottomSheetLayoutBinding2 = null;
        }
        onfidoHostedWebModuleBottomSheetLayoutBinding2.onfidoBottomSheetWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HostedWebModuleExternalLinkFragment.onCreateDialog$lambda$3$lambda$2$lambda$0(behavior, view, motionEvent);
            }
        });
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding3 = this$0.binding;
        if (onfidoHostedWebModuleBottomSheetLayoutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            onfidoHostedWebModuleBottomSheetLayoutBinding = onfidoHostedWebModuleBottomSheetLayoutBinding3;
        }
        onfidoHostedWebModuleBottomSheetLayoutBinding.buttonClose.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HostedWebModuleExternalLinkFragment.onCreateDialog$lambda$3$lambda$2$lambda$1(behavior, view);
            }
        });
        behavior.setDraggable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreateDialog$lambda$3$lambda$2$lambda$0(BottomSheetBehavior behavior, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(behavior, "$behavior");
        int action = motionEvent.getAction();
        if (action == 0) {
            behavior.setDraggable(false);
        } else if (action == 1 || action == 3) {
            behavior.setDraggable(true);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateDialog$lambda$3$lambda$2$lambda$1(BottomSheetBehavior behavior, View view) {
        Intrinsics.checkNotNullParameter(behavior, "$behavior");
        behavior.setState(5);
    }

    private final void openWebView(boolean isPdf, final Function0<Unit> pageFinished) {
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding = this.binding;
        if (onfidoHostedWebModuleBottomSheetLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleBottomSheetLayoutBinding = null;
        }
        WebView webView = onfidoHostedWebModuleBottomSheetLayoutBinding.onfidoBottomSheetWebView;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$openWebView$1$1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pageFinished.invoke();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                this.this$0.dismiss();
            }
        });
        webView.loadUrl(isPdf ? "file:///android_asset/index.html" : getUrlParams().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setProgressBarVisibility(boolean isVisible) {
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBinding = this.binding;
        if (onfidoHostedWebModuleBottomSheetLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleBottomSheetLayoutBinding = null;
        }
        onfidoHostedWebModuleBottomSheetLayoutBinding.progressCircular.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.onfido.hosted.web.module.externallink.HostedWebModuleExternalLinkFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                HostedWebModuleExternalLinkFragment.onCreateDialog$lambda$3$lambda$2(this.f$0, dialogInterface);
            }
        });
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (container != null) {
            container.setBackgroundResource(R.color.transparent);
        }
        OnfidoHostedWebModuleBottomSheetLayoutBinding onfidoHostedWebModuleBottomSheetLayoutBindingInflate = OnfidoHostedWebModuleBottomSheetLayoutBinding.inflate(inflater);
        Intrinsics.checkNotNullExpressionValue(onfidoHostedWebModuleBottomSheetLayoutBindingInflate, "inflate(...)");
        this.binding = onfidoHostedWebModuleBottomSheetLayoutBindingInflate;
        if (onfidoHostedWebModuleBottomSheetLayoutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoHostedWebModuleBottomSheetLayoutBindingInflate = null;
        }
        LinearLayout root = onfidoHostedWebModuleBottomSheetLayoutBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C07401(null), 3, null);
        getViewModel().onExternalLinkReceived(getUrlParams());
    }
}
