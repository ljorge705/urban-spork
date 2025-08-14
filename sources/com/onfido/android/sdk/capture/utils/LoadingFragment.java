package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.clevertap.android.sdk.Constants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.databinding.OnfidoProgressDialogLayoutBinding;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.ui.widget.LoadingFragmentViewModel;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.javax.inject.Inject;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Locale;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010!\u001a\u00020\u0018H\u0016J\u001a\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006("}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoProgressDialogLayoutBinding;", "loadingFragmentViewModel", "Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel;", "getLoadingFragmentViewModel", "()Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel;", "loadingFragmentViewModel$delegate", "Lkotlin/Lazy;", "loadingViewModelFactory", "Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel$Factory;", "getLoadingViewModelFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel$Factory;", "setLoadingViewModelFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel$Factory;)V", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "getStorage$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "setStorage$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onDetach", "onViewCreated", "view", "renderState", "state", "Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel$ViewState;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LoadingFragment extends DialogFragment {
    private static final String ARG_MODE = "arg_mode";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private OnfidoProgressDialogLayoutBinding binding;

    /* renamed from: loadingFragmentViewModel$delegate, reason: from kotlin metadata */
    private final Lazy loadingFragmentViewModel;

    @Inject
    public LoadingFragmentViewModel.Factory loadingViewModelFactory;

    @Inject
    public SharedPreferencesDataSource storage;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion;", "", "()V", "ARG_MODE", "", "TAG", "getTAG", "()Ljava/lang/String;", "show", "", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "DialogMode", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\u0003R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "Landroid/os/Parcelable;", "reason", "", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "toTaskType", "CheckingImageQuality", "Loading", "Uploading", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$CheckingImageQuality;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$Loading;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$Uploading;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static abstract class DialogMode implements Parcelable {
            private final String reason;

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$CheckingImageQuality;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "reason", "", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class CheckingImageQuality extends DialogMode {
                public static final Parcelable.Creator<CheckingImageQuality> CREATOR = new Creator();
                private final String reason;

                @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                public static final class Creator implements Parcelable.Creator<CheckingImageQuality> {
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final CheckingImageQuality createFromParcel(Parcel parcel) {
                        Intrinsics.checkNotNullParameter(parcel, "parcel");
                        return new CheckingImageQuality(parcel.readString());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final CheckingImageQuality[] newArray(int i) {
                        return new CheckingImageQuality[i];
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public CheckingImageQuality(String reason) {
                    super(reason, null);
                    Intrinsics.checkNotNullParameter(reason, "reason");
                    this.reason = reason;
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                @Override // com.onfido.android.sdk.capture.utils.LoadingFragment.Companion.DialogMode
                public String getReason() {
                    return this.reason;
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int flags) {
                    Intrinsics.checkNotNullParameter(parcel, "out");
                    parcel.writeString(this.reason);
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$Loading;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "reason", "", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Loading extends DialogMode {
                public static final Parcelable.Creator<Loading> CREATOR = new Creator();
                private final String reason;

                @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                public static final class Creator implements Parcelable.Creator<Loading> {
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Loading createFromParcel(Parcel parcel) {
                        Intrinsics.checkNotNullParameter(parcel, "parcel");
                        return new Loading(parcel.readString());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Loading[] newArray(int i) {
                        return new Loading[i];
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public Loading(String reason) {
                    super(reason, null);
                    Intrinsics.checkNotNullParameter(reason, "reason");
                    this.reason = reason;
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                @Override // com.onfido.android.sdk.capture.utils.LoadingFragment.Companion.DialogMode
                public String getReason() {
                    return this.reason;
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int flags) {
                    Intrinsics.checkNotNullParameter(parcel, "out");
                    parcel.writeString(this.reason);
                }
            }

            @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode$Uploading;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "reason", "", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final /* data */ class Uploading extends DialogMode {
                public static final Parcelable.Creator<Uploading> CREATOR = new Creator();
                private final String reason;

                @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                public static final class Creator implements Parcelable.Creator<Uploading> {
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Uploading createFromParcel(Parcel parcel) {
                        Intrinsics.checkNotNullParameter(parcel, "parcel");
                        return new Uploading(parcel.readString());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Uploading[] newArray(int i) {
                        return new Uploading[i];
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public Uploading(String reason) {
                    super(reason, null);
                    Intrinsics.checkNotNullParameter(reason, "reason");
                    this.reason = reason;
                }

                public static /* synthetic */ Uploading copy$default(Uploading uploading, String str, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = uploading.reason;
                    }
                    return uploading.copy(str);
                }

                /* renamed from: component1, reason: from getter */
                public final String getReason() {
                    return this.reason;
                }

                public final Uploading copy(String reason) {
                    Intrinsics.checkNotNullParameter(reason, "reason");
                    return new Uploading(reason);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof Uploading) && Intrinsics.areEqual(this.reason, ((Uploading) other).reason);
                }

                @Override // com.onfido.android.sdk.capture.utils.LoadingFragment.Companion.DialogMode
                public String getReason() {
                    return this.reason;
                }

                public int hashCode() {
                    return this.reason.hashCode();
                }

                public String toString() {
                    return "Uploading(reason=" + this.reason + ')';
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int flags) {
                    Intrinsics.checkNotNullParameter(parcel, "out");
                    parcel.writeString(this.reason);
                }
            }

            private DialogMode(String str) {
                this.reason = str;
            }

            public String getReason() {
                return this.reason;
            }

            public final String toTaskType() {
                if (this instanceof CheckingImageQuality) {
                    return WaitingScreenTracker.WaitingTaskTypes.TASK_TYPE_CHECKING_IMAGE_QUALITY;
                }
                if (this instanceof Loading) {
                    return WaitingScreenTracker.WaitingTaskTypes.TASK_TYPE_LOADING;
                }
                if (this instanceof Uploading) {
                    return WaitingScreenTracker.WaitingTaskTypes.TASK_TYPE_UPLOADING;
                }
                throw new NoWhenBranchMatchedException();
            }

            public /* synthetic */ DialogMode(String str, DefaultConstructorMarker defaultConstructorMarker) {
                this(str);
            }
        }

        private Companion() {
        }

        public final String getTAG() {
            return LoadingFragment.TAG;
        }

        public final void show(DialogMode dialogMode, FragmentManager fragmentManager) {
            Intrinsics.checkNotNullParameter(dialogMode, "dialogMode");
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            LoadingFragment loadingFragment = new LoadingFragment(null);
            loadingFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(LoadingFragment.ARG_MODE, dialogMode)));
            loadingFragment.show(fragmentManager, LoadingFragment.INSTANCE.getTAG());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.LoadingFragment$onViewCreated$1", f = "LoadingFragment.kt", i = {}, l = {80}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.utils.LoadingFragment$onViewCreated$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.utils.LoadingFragment$onViewCreated$1$1", f = "LoadingFragment.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.utils.LoadingFragment$onViewCreated$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ LoadingFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.android.sdk.capture.utils.LoadingFragment$onViewCreated$1$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01681 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ LoadingFragment $tmp0;

                C01681(LoadingFragment loadingFragment) {
                    this.$tmp0 = loadingFragment;
                }

                public final Object emit(LoadingFragmentViewModel.ViewState viewState, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$renderState = C01671.invokeSuspend$renderState(this.$tmp0, viewState, continuation);
                    return objInvokeSuspend$renderState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$renderState : Unit.INSTANCE;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, LoadingFragment.class, "renderState", "renderState(Lcom/onfido/android/sdk/capture/ui/widget/LoadingFragmentViewModel$ViewState;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                    return emit((LoadingFragmentViewModel.ViewState) obj, (Continuation<? super Unit>) continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01671(LoadingFragment loadingFragment, Continuation<? super C01671> continuation) {
                super(2, continuation);
                this.this$0 = loadingFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$renderState(LoadingFragment loadingFragment, LoadingFragmentViewModel.ViewState viewState, Continuation continuation) {
                loadingFragment.renderState(viewState);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01671(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<LoadingFragmentViewModel.ViewState> state = this.this$0.getLoadingFragmentViewModel().getState();
                    C01681 c01681 = new C01681(this.this$0);
                    this.label = 1;
                    if (state.collect(c01681, this) == coroutine_suspended) {
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
                return ((C01671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LoadingFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle lifecycle = LoadingFragment.this.getViewLifecycleOwner().getLifecycleRegistry();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "getLifecycle(...)");
                Lifecycle.State state = Lifecycle.State.STARTED;
                C01671 c01671 = new C01671(LoadingFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, state, c01671, this) == coroutine_suspended) {
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
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("LoadingFragment", "getSimpleName(...)");
        TAG = "LoadingFragment";
    }

    private LoadingFragment() {
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$loadingFragmentViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final LoadingFragment loadingFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$loadingFragmentViewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Parcelable parcelable;
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        LoadingFragmentViewModel.Factory loadingViewModelFactory$onfido_capture_sdk_core_release = loadingFragment.getLoadingViewModelFactory$onfido_capture_sdk_core_release();
                        Bundle bundleRequireArguments = loadingFragment.requireArguments();
                        Intrinsics.checkNotNullExpressionValue(bundleRequireArguments, "requireArguments(...)");
                        if (Build.VERSION.SDK_INT >= 33) {
                            parcelable = (Parcelable) bundleRequireArguments.getParcelable("arg_mode", LoadingFragment.Companion.DialogMode.class);
                        } else {
                            Parcelable parcelable2 = bundleRequireArguments.getParcelable("arg_mode");
                            if (!(parcelable2 instanceof LoadingFragment.Companion.DialogMode)) {
                                parcelable2 = null;
                            }
                            parcelable = (LoadingFragment.Companion.DialogMode) parcelable2;
                        }
                        if (parcelable == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        LoadingFragmentViewModel loadingFragmentViewModelCreate = loadingViewModelFactory$onfido_capture_sdk_core_release.create((LoadingFragment.Companion.DialogMode) parcelable);
                        Intrinsics.checkNotNull(loadingFragmentViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return loadingFragmentViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$special$$inlined$viewModels$default$2
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
        this.loadingFragmentViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LoadingFragmentViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.utils.LoadingFragment$special$$inlined$viewModels$default$4
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoadingFragmentViewModel getLoadingFragmentViewModel() {
        return (LoadingFragmentViewModel) this.loadingFragmentViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderState(LoadingFragmentViewModel.ViewState state) {
        OnfidoProgressDialogLayoutBinding onfidoProgressDialogLayoutBinding = this.binding;
        if (onfidoProgressDialogLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoProgressDialogLayoutBinding = null;
        }
        if (state instanceof LoadingFragmentViewModel.ViewState.ShowMessage) {
            TextView title = onfidoProgressDialogLayoutBinding.title;
            Intrinsics.checkNotNullExpressionValue(title, "title");
            ViewExtensionsKt.toVisible$default(title, false, 1, null);
            LoadingFragmentViewModel.ViewState.ShowMessage showMessage = (LoadingFragmentViewModel.ViewState.ShowMessage) state;
            onfidoProgressDialogLayoutBinding.title.setText(getString(showMessage.getTitleResId()));
            if (showMessage.getSubTitleResId() != null) {
                TextView subtitle = onfidoProgressDialogLayoutBinding.subtitle;
                Intrinsics.checkNotNullExpressionValue(subtitle, "subtitle");
                ViewExtensionsKt.toVisible$default(subtitle, false, 1, null);
                onfidoProgressDialogLayoutBinding.subtitle.setText(getString(showMessage.getSubTitleResId().intValue()));
                return;
            }
        } else {
            if (!Intrinsics.areEqual(state, LoadingFragmentViewModel.ViewState.ShowProgressOnly.INSTANCE)) {
                return;
            }
            TextView title2 = onfidoProgressDialogLayoutBinding.title;
            Intrinsics.checkNotNullExpressionValue(title2, "title");
            ViewExtensionsKt.toInvisible$default(title2, false, 1, null);
        }
        TextView subtitle2 = onfidoProgressDialogLayoutBinding.subtitle;
        Intrinsics.checkNotNullExpressionValue(subtitle2, "subtitle");
        ViewExtensionsKt.toInvisible$default(subtitle2, false, 1, null);
    }

    public final LoadingFragmentViewModel.Factory getLoadingViewModelFactory$onfido_capture_sdk_core_release() {
        LoadingFragmentViewModel.Factory factory = this.loadingViewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("loadingViewModelFactory");
        return null;
    }

    public final SharedPreferencesDataSource getStorage$onfido_capture_sdk_core_release() {
        SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
        if (sharedPreferencesDataSource != null) {
            return sharedPreferencesDataSource;
        }
        Intrinsics.throwUninitializedPropertyAccessException(KeychainModule.Maps.STORAGE);
        return null;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Object locale;
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        Boolean boolValueOf = null;
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onCreate(savedInstanceState);
        SharedPreferencesDataSource storage$onfido_capture_sdk_core_release = getStorage$onfido_capture_sdk_core_release();
        StorageKey storageKey = StorageKey.IS_IN_DARK_MODE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = storage$onfido_capture_sdk_core_release.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = storageKey.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                locale = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                boolValueOf = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = storage$onfido_capture_sdk_core_release.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            boolValueOf = (Boolean) locale;
        }
        if (boolValueOf == null) {
            boolValueOf = Boolean.FALSE;
        }
        setStyle(0, boolValueOf.booleanValue() ? R.style.OnfidoAlertDialogDarkTheme_LoadingProgress_FullScreen : R.style.OnfidoAlertDialogLightTheme_LoadingProgress_FullScreen);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        OnfidoProgressDialogLayoutBinding onfidoProgressDialogLayoutBindingInflate = OnfidoProgressDialogLayoutBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(onfidoProgressDialogLayoutBindingInflate, "inflate(...)");
        this.binding = onfidoProgressDialogLayoutBindingInflate;
        setCancelable(false);
        OnfidoProgressDialogLayoutBinding onfidoProgressDialogLayoutBinding = this.binding;
        if (onfidoProgressDialogLayoutBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            onfidoProgressDialogLayoutBinding = null;
        }
        ConstraintLayout root = onfidoProgressDialogLayoutBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        getLoadingFragmentViewModel().onDetach();
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AnonymousClass1(null));
    }

    public final void setLoadingViewModelFactory$onfido_capture_sdk_core_release(LoadingFragmentViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.loadingViewModelFactory = factory;
    }

    public final void setStorage$onfido_capture_sdk_core_release(SharedPreferencesDataSource sharedPreferencesDataSource) {
        Intrinsics.checkNotNullParameter(sharedPreferencesDataSource, "<set-?>");
        this.storage = sharedPreferencesDataSource;
    }

    public /* synthetic */ LoadingFragment(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
