package com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload;

import android.os.Bundle;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.AvcUploadErrorScreen;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadViewModel;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001a\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00148\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "filePath$delegate", "Lkotlin/Lazy;", "motionHostViewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "getMotionHostViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "motionHostViewModel$delegate", "viewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel;", "viewModel$delegate", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider", "(Lcom/onfido/javax/inject/Provider;)V", "onUploadResultChanged", "", "uploadResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadViewModel$UploadResult;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionUploadFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_FILE_PATH = "key_file_path";

    /* renamed from: filePath$delegate, reason: from kotlin metadata */
    private final Lazy filePath;

    /* renamed from: motionHostViewModel$delegate, reason: from kotlin metadata */
    private final Lazy motionHostViewModel;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public Provider<MotionUploadViewModel> viewModelProvider;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadFragment$Companion;", "", "()V", "KEY_FILE_PATH", "", "createInstance", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/upload/MotionUploadFragment;", "filePath", "createInstance$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final MotionUploadFragment createInstance$onfido_capture_sdk_core_release(String filePath) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            MotionUploadFragment motionUploadFragment = new MotionUploadFragment();
            motionUploadFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(MotionUploadFragment.KEY_FILE_PATH, filePath)));
            return motionUploadFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MotionUploadFragment() {
        super(R.layout.onfido_motion_fragment_upload);
        this.filePath = LazyKt.lazy(new Function0<String>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$filePath$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String string = this.this$0.requireArguments().getString("key_file_path");
                Intrinsics.checkNotNull(string);
                return string;
            }
        });
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final MotionUploadFragment motionUploadFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        MotionUploadViewModel motionUploadViewModel = motionUploadFragment.getViewModelProvider().get();
                        Intrinsics.checkNotNull(motionUploadViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return motionUploadViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function02.invoke();
            }
        });
        final Function0 function03 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MotionUploadViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function04 = function03;
                if (function04 != null && (creationExtras = (CreationExtras) function04.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function0);
        final Function0<ViewModelStoreOwner> function04 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$motionHostViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                Fragment fragmentRequireParentFragment = this.this$0.requireParentFragment();
                Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
                return fragmentRequireParentFragment;
            }
        };
        final Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function04.invoke();
            }
        });
        this.motionHostViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MotionHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$7
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function05 = function03;
                if (function05 != null && (creationExtras = (CreationExtras) function05.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$special$$inlined$viewModels$default$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    private final String getFilePath() {
        return (String) this.filePath.getValue();
    }

    private final MotionHostViewModel getMotionHostViewModel() {
        return (MotionHostViewModel) this.motionHostViewModel.getValue();
    }

    private final MotionUploadViewModel getViewModel() {
        return (MotionUploadViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUploadResultChanged(MotionUploadViewModel.UploadResult uploadResult) {
        if (uploadResult instanceof MotionUploadViewModel.UploadResult.Success) {
            getMotionHostViewModel().onVideoUploadSuccess(((MotionUploadViewModel.UploadResult.Success) uploadResult).getUploadArtifact());
        } else if (!(uploadResult instanceof MotionUploadViewModel.UploadResult.Failure)) {
            if (Intrinsics.areEqual(uploadResult, MotionUploadViewModel.UploadResult.Initial.INSTANCE)) {
                showLoadingDialog$onfido_capture_sdk_core_release(new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_FACE_MOTION));
                return;
            }
            return;
        } else {
            Navigator navigator = getMotionHostViewModel().getNavigator();
            String filePath = getFilePath();
            Intrinsics.checkNotNullExpressionValue(filePath, "<get-filePath>(...)");
            navigator.replace(new AvcUploadErrorScreen(filePath));
        }
        dismissLoadingDialog$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final Provider<MotionUploadViewModel> getViewModelProvider() {
        Provider<MotionUploadViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment");
        ((MotionHostFragment) parentFragment).getMotionHostComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        LiveData<MotionUploadViewModel.UploadResult> uploadResult = getViewModel().getUploadResult();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final Function1<MotionUploadViewModel.UploadResult, Unit> function1 = new Function1<MotionUploadViewModel.UploadResult, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment.onViewCreated.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MotionUploadViewModel.UploadResult uploadResult2) {
                invoke2(uploadResult2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MotionUploadViewModel.UploadResult uploadResult2) {
                MotionUploadFragment motionUploadFragment = MotionUploadFragment.this;
                Intrinsics.checkNotNull(uploadResult2);
                motionUploadFragment.onUploadResultChanged(uploadResult2);
            }
        };
        uploadResult.observe(viewLifecycleOwner, new Observer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MotionUploadFragment.onViewCreated$lambda$0(function1, obj);
            }
        });
        getViewModel().upload(new File(getFilePath()), getMotionHostViewModel().getAudioEnabled());
    }

    public final void setViewModelProvider(Provider<MotionUploadViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }
}
