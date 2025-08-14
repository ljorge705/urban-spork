package com.onfido.android.sdk.capture.ui.userconsent;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentUserConsentScreenBinding;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel;
import com.onfido.android.sdk.capture.ui.userconsent.htmlutil.TextViewExtensionKt;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
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
import kotlin.text.StringsKt;
import org.jmrtd.cbeff.ISO781611;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 02\u00020\u0001:\u000201B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0016J\u001a\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020 H\u0002J\u0010\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020\u001cH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00062"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentUserConsentScreenBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentUserConsentScreenBinding;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "userExitFlowMenuProvider", "Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "getUserExitFlowMenuProvider", "()Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "userExitFlowMenuProvider$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel;", "viewModel$delegate", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider", "(Lcom/onfido/javax/inject/Provider;)V", "isUserConsentBottomVisible", "", "text", "Landroid/text/Editable;", "onDestroyView", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "renderState", "state", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$ViewState;", "sendResult", "userConsentResult", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "setDoNotAcceptButtonListener", "updateUserConsentActionButtons", "isEnabled", "Companion", "UserConsentResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UserConsentFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_REQUEST = "key_request";
    private static final String KEY_RESULT = "key_result";
    private OnfidoFragmentUserConsentScreenBinding _binding;
    private final LifecycleAwareDialog lifecycleAwareDialog;

    /* renamed from: userExitFlowMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy userExitFlowMenuProvider;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public Provider<UserConsentViewModel> viewModelProvider;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$Companion;", "", "()V", "KEY_REQUEST", "", "KEY_RESULT", "createInstance", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment;", "resultKey", "getUserConsentResult", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final UserConsentFragment createInstance(String resultKey) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            UserConsentFragment userConsentFragment = new UserConsentFragment();
            userConsentFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("key_request", resultKey)));
            return userConsentFragment;
        }

        public final UserConsentResult getUserConsentResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(UserConsentFragment.KEY_RESULT);
            if (parcelable != null) {
                return (UserConsentResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "Landroid/os/Parcelable;", "()V", "ConsentAccepted", "ConsentExit", "ConsentRejected", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentAccepted;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentExit;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentRejected;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class UserConsentResult implements Parcelable {

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentAccepted;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentAccepted extends UserConsentResult {
            public static final ConsentAccepted INSTANCE = new ConsentAccepted();
            public static final Parcelable.Creator<ConsentAccepted> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ConsentAccepted> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentAccepted createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return ConsentAccepted.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentAccepted[] newArray(int i) {
                    return new ConsentAccepted[i];
                }
            }

            private ConsentAccepted() {
                super(null);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentExit;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentExit extends UserConsentResult {
            public static final ConsentExit INSTANCE = new ConsentExit();
            public static final Parcelable.Creator<ConsentExit> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ConsentExit> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentExit createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return ConsentExit.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentExit[] newArray(int i) {
                    return new ConsentExit[i];
                }
            }

            private ConsentExit() {
                super(null);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult$ConsentRejected;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentRejected extends UserConsentResult {
            public static final ConsentRejected INSTANCE = new ConsentRejected();
            public static final Parcelable.Creator<ConsentRejected> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ConsentRejected> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentRejected createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return ConsentRejected.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ConsentRejected[] newArray(int i) {
                    return new ConsentRejected[i];
                }
            }

            private ConsentRejected() {
                super(null);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }

        private UserConsentResult() {
        }

        public /* synthetic */ UserConsentResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UserConsentFragment() {
        super(R.layout.onfido_fragment_user_consent_screen);
        final Function0 function0 = null;
        this.lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);
        Function0<ViewModelProvider.Factory> function02 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final UserConsentFragment userConsentFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        UserConsentViewModel userConsentViewModel = userConsentFragment.getViewModelProvider().get();
                        Intrinsics.checkNotNull(userConsentViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return userConsentViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function03 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function03.invoke();
            }
        });
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(UserConsentViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function04 = function0;
                if (function04 != null && (creationExtras = (CreationExtras) function04.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function02);
        this.userExitFlowMenuProvider = LazyKt.lazy(new Function0<UserExitFlowMenuProvider>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$userExitFlowMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserExitFlowMenuProvider invoke() {
                LifecycleAwareDialog lifecycleAwareDialog = this.this$0.lifecycleAwareDialog;
                final UserConsentFragment userConsentFragment = this.this$0;
                Function0<Unit> function04 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$userExitFlowMenuProvider$2.1
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
                        userConsentFragment.getViewModel().flowUserExit();
                    }
                };
                final UserConsentFragment userConsentFragment2 = this.this$0;
                Function1<DialogInterface, Unit> function1 = new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$userExitFlowMenuProvider$2.2
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
                        userConsentFragment2.getViewModel().flowUserExitConfirmed();
                    }
                };
                final UserConsentFragment userConsentFragment3 = this.this$0;
                return new UserExitFlowMenuProvider(lifecycleAwareDialog, 0, function04, function1, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$userExitFlowMenuProvider$2.3
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
                        userConsentFragment3.getViewModel().flowUserExitCanceled();
                    }
                }, 2, null);
            }
        });
    }

    private final OnfidoFragmentUserConsentScreenBinding getBinding() {
        OnfidoFragmentUserConsentScreenBinding onfidoFragmentUserConsentScreenBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentUserConsentScreenBinding);
        return onfidoFragmentUserConsentScreenBinding;
    }

    private final UserExitFlowMenuProvider getUserExitFlowMenuProvider() {
        return (UserExitFlowMenuProvider) this.userExitFlowMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserConsentViewModel getViewModel() {
        return (UserConsentViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isUserConsentBottomVisible(Editable text) {
        return (text == null || StringsKt.isBlank(text) || getBinding().userConsentTextView.canScrollVertically(1) || !isResumed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(UserConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().onAcceptClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderState(UserConsentViewModel.ViewState state) {
        UserConsentResult userConsentResult;
        OnfidoFragmentUserConsentScreenBinding binding = getBinding();
        if (state.getLoadingState().isLoading()) {
            showLoadingDialog$onfido_capture_sdk_core_release(new LoadingFragment.Companion.DialogMode.Loading(state.getLoadingState().getLoadingReason()));
        } else {
            dismissLoadingDialog$onfido_capture_sdk_core_release();
        }
        String consentPageString = state.getConsentPageString();
        if (consentPageString != null) {
            binding.userConsentTextView.setMovementMethod(LinkMovementMethod.getInstance());
            TextView userConsentTextView = binding.userConsentTextView;
            Intrinsics.checkNotNullExpressionValue(userConsentTextView, "userConsentTextView");
            TextViewExtensionKt.setTextFromHtml(userConsentTextView, consentPageString);
            LinearLayout userConsentFooterContainer = binding.userConsentFooterContainer;
            Intrinsics.checkNotNullExpressionValue(userConsentFooterContainer, "userConsentFooterContainer");
            ViewExtensionsKt.toVisible$default(userConsentFooterContainer, false, 1, null);
        }
        String errorString = state.getErrorString();
        if (errorString != null) {
            this.lifecycleAwareDialog.show((ISO781611.SMT_TAG & 1) != 0 ? null : null, errorString, (ISO781611.SMT_TAG & 4) != 0 ? R.string.onfido_ok : 0, (ISO781611.SMT_TAG & 8) != 0 ? null : null, (ISO781611.SMT_TAG & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((ISO781611.SMT_TAG & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((ISO781611.SMT_TAG & 64) != 0 ? LifecycleAwareDialog.C07221.INSTANCE : null));
        }
        for (UserConsentViewModel.UserConsentUIEvent userConsentUIEvent : state.getUiEvents()) {
            if (Intrinsics.areEqual(userConsentUIEvent, UserConsentViewModel.UserConsentUIEvent.ConsentAccepted.INSTANCE)) {
                userConsentResult = UserConsentResult.ConsentAccepted.INSTANCE;
            } else if (Intrinsics.areEqual(userConsentUIEvent, UserConsentViewModel.UserConsentUIEvent.ConsentRejected.INSTANCE)) {
                userConsentResult = UserConsentResult.ConsentRejected.INSTANCE;
            } else if (Intrinsics.areEqual(userConsentUIEvent, UserConsentViewModel.UserConsentUIEvent.ConsentExit.INSTANCE)) {
                userConsentResult = UserConsentResult.ConsentExit.INSTANCE;
            } else {
                getViewModel().consumeUIEvent(userConsentUIEvent);
            }
            sendResult(userConsentResult);
            getViewModel().consumeUIEvent(userConsentUIEvent);
        }
        if (state.getShowExitFlow()) {
            requireActivity().removeMenuProvider(getUserExitFlowMenuProvider());
            requireActivity().addMenuProvider(getUserExitFlowMenuProvider(), getViewLifecycleOwner());
        }
    }

    private final void sendResult(UserConsentResult userConsentResult) {
        String string = requireArguments().getString("key_request");
        if (string == null) {
            throw new IllegalArgumentException("key_request == null".toString());
        }
        Intrinsics.checkNotNullExpressionValue(string, "requireNotNull(...)");
        getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RESULT, userConsentResult)));
    }

    private final void setDoNotAcceptButtonListener() {
        getBinding().userConsentDoNotAcceptButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserConsentFragment.setDoNotAcceptButtonListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDoNotAcceptButtonListener$lambda$2(final UserConsentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LifecycleAwareDialog lifecycleAwareDialog = this$0.lifecycleAwareDialog;
        int i = R.string.onfido_user_consent_prompt_no_consent_title;
        lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(i), R.string.onfido_user_consent_prompt_no_consent_detail, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_user_consent_prompt_button_secondary, (56 & 8) != 0 ? null : Integer.valueOf(R.string.onfido_user_consent_prompt_button_primary), (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$setDoNotAcceptButtonListener$1$1
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
                this.this$0.getViewModel().onRejectClicked();
            }
        }), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$setDoNotAcceptButtonListener$1$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUserConsentActionButtons(boolean isEnabled) {
        getBinding().userConsentAcceptButton.setEnabled(isEnabled);
        getBinding().userConsentDoNotAcceptButton.setEnabled(isEnabled);
    }

    public final Provider<UserConsentViewModel> getViewModelProvider() {
        Provider<UserConsentViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().onStart();
        Disposable disposableSubscribe = getViewModel().getState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment.onStart.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(UserConsentViewModel.ViewState p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                UserConsentFragment.this.renderState(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentUserConsentScreenBinding.bind(view);
        LinearLayout userConsentFooterContainer = getBinding().userConsentFooterContainer;
        Intrinsics.checkNotNullExpressionValue(userConsentFooterContainer, "userConsentFooterContainer");
        ViewExtensionsKt.toGone$default(userConsentFooterContainer, false, 1, null);
        getBinding().userConsentAcceptButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserConsentFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        updateUserConsentActionButtons(false);
        getBinding().userConsentScrollView.setBottomScrollListener(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment.onViewCreated.2
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
                UserConsentFragment.this.updateUserConsentActionButtons(true);
            }
        });
        TextView userConsentTextView = getBinding().userConsentTextView;
        Intrinsics.checkNotNullExpressionValue(userConsentTextView, "userConsentTextView");
        userConsentTextView.addTextChangedListener(new TextWatcher() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment$onViewCreated$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (this.this$0.isUserConsentBottomVisible(s)) {
                    this.this$0.updateUserConsentActionButtons(true);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
        setDoNotAcceptButtonListener();
    }

    public final void setViewModelProvider(Provider<UserConsentViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }
}
