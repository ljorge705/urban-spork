package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.di.host.MotionHostComponentHolder;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload.MotionUploadFragment;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.core.features.motion.MotionFlow;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcFragmentHostBinding;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.android.sdk.capture.internal.util.FragmentManagerExtKt;
import com.onfido.android.sdk.capture.ui.NextActionListener;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.protocol.App;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 H2\u00020\u0001:\u0002GHB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020*H\u0002J\b\u0010/\u001a\u00020*H\u0002J\b\u00100\u001a\u00020*H\u0016J-\u00101\u001a\u00020*2\u0006\u00102\u001a\u0002032\u000e\u00104\u001a\n\u0012\u0006\b\u0001\u0012\u000206052\u0006\u00107\u001a\u000208H\u0017¢\u0006\u0002\u00109J\b\u0010:\u001a\u00020*H\u0016J\b\u0010;\u001a\u00020*H\u0016J\u001a\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\b\u0010A\u001a\u00020*H\u0002J\b\u0010B\u001a\u00020*H\u0002J\b\u0010C\u001a\u00020*H\u0002J\u0010\u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020FH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\r\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001e\u001a\u00020\u001f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\r\u001a\u0004\b&\u0010'¨\u0006I"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment;", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcFragmentHostBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcFragmentHostBinding;", "motionHostComponent", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponent;", "getMotionHostComponent$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/host/MotionHostComponent;", "motionHostComponent$delegate", "Lkotlin/Lazy;", "motionHostViewModelFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$Factory;", "getMotionHostViewModelFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$Factory;", "setMotionHostViewModelFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$Factory;)V", "motionOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "getMotionOptions", "()Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "motionOptions$delegate", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "getNavigationManager", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "navigationManager$delegate", "runtimePermissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "getRuntimePermissionsManager$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "setRuntimePermissionsManager$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;)V", "viewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "viewModel$delegate", "handleBackNavigation", "", "handleViewEvent", "event", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "hideToolbar", "observeViewEvent", "onDestroyView", "onRequestPermissionsResult", "requestCode", "", App.JsonKeys.APP_PERMISSIONS, "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "registerFragmentLifecycleCallbacks", "setFragmentResultsListeners", "showToolbar", "submitResult", "avcHostResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "AvcHostResult", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionHostFragment extends FlowFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_ARG_IS_IN_WORKFLOW = "key_is_in_workflow";
    private static final String KEY_ARG_OPTIONS = "key_options";
    private static final String KEY_ARG_REQUEST = "key_request";
    private static final String KEY_AVC_HOST_RESULT = "key_host_result";
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private OnfidoAvcFragmentHostBinding _binding;

    /* renamed from: motionHostComponent$delegate, reason: from kotlin metadata */
    private final Lazy motionHostComponent;

    @Inject
    public MotionHostViewModel.Factory motionHostViewModelFactory;

    /* renamed from: motionOptions$delegate, reason: from kotlin metadata */
    private final Lazy motionOptions;

    /* renamed from: navigationManager$delegate, reason: from kotlin metadata */
    private final Lazy navigationManager;

    @Inject
    public RuntimePermissionsManager runtimePermissionsManager;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "Landroid/os/Parcelable;", "()V", "Completed", "Error", "Exit", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Exit;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class AvcHostResult implements Parcelable {

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadedArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Completed extends AvcHostResult {
            public static final Parcelable.Creator<Completed> CREATOR = new Creator();
            private final UploadedArtifact uploadedArtifact;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Completed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Completed((UploadedArtifact) parcel.readParcelable(Completed.class.getClassLoader()));
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed[] newArray(int i) {
                    return new Completed[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Completed(UploadedArtifact uploadedArtifact) {
                super(null);
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                this.uploadedArtifact = uploadedArtifact;
            }

            public static /* synthetic */ Completed copy$default(Completed completed, UploadedArtifact uploadedArtifact, int i, Object obj) {
                if ((i & 1) != 0) {
                    uploadedArtifact = completed.uploadedArtifact;
                }
                return completed.copy(uploadedArtifact);
            }

            /* renamed from: component1, reason: from getter */
            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public final Completed copy(UploadedArtifact uploadedArtifact) {
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                return new Completed(uploadedArtifact);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Completed) && Intrinsics.areEqual(this.uploadedArtifact, ((Completed) other).uploadedArtifact);
            }

            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public int hashCode() {
                return this.uploadedArtifact.hashCode();
            }

            public String toString() {
                return "Completed(uploadedArtifact=" + this.uploadedArtifact + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeParcelable(this.uploadedArtifact, flags);
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends AvcHostResult {
            public static final Parcelable.Creator<Error> CREATOR = new Creator();
            private final String message;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Error> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Error createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Error(parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Error[] newArray(int i) {
                    return new Error[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(String message) {
                super(null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.message;
                }
                return error.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Error copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Error(message);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
            }

            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            public String toString() {
                return "Error(message=" + this.message + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.message);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult$Exit;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit extends AvcHostResult {
            public static final Exit INSTANCE = new Exit();
            public static final Parcelable.Creator<Exit> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Exit> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Exit createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Exit.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Exit[] newArray(int i) {
                    return new Exit[i];
                }
            }

            private Exit() {
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

        private AvcHostResult() {
        }

        public /* synthetic */ AvcHostResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$Companion;", "", "()V", "KEY_ARG_IS_IN_WORKFLOW", "", "KEY_ARG_OPTIONS", "KEY_ARG_REQUEST", "KEY_AVC_HOST_RESULT", "PERMISSIONS_REQUEST_CODE", "", "createInstance", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment;", "requestKey", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "isInWorkflow", "", "getAvcHostResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ MotionHostFragment createInstance$default(Companion companion, String str, MotionCaptureVariantOptions motionCaptureVariantOptions, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                motionCaptureVariantOptions = null;
            }
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.createInstance(str, motionCaptureVariantOptions, z);
        }

        public final MotionHostFragment createInstance(String requestKey, MotionCaptureVariantOptions options, boolean isInWorkflow) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            if (options == null) {
                options = MotionCaptureVariantOptions.INSTANCE.getDEFAULT();
            }
            MotionHostFragment motionHostFragment = new MotionHostFragment();
            motionHostFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("key_request", requestKey), TuplesKt.to(MotionHostFragment.KEY_ARG_OPTIONS, options), TuplesKt.to(MotionHostFragment.KEY_ARG_IS_IN_WORKFLOW, Boolean.valueOf(isInWorkflow))));
            return motionHostFragment;
        }

        public final AvcHostResult getAvcHostResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(MotionHostFragment.KEY_AVC_HOST_RESULT);
            if (parcelable != null) {
                return (AvcHostResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MotionHostFragment() {
        super(R.layout.onfido_avc_fragment_host);
        this.motionOptions = LazyKt.lazy(new Function0<MotionCaptureVariantOptions>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$motionOptions$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MotionCaptureVariantOptions invoke() {
                Serializable serializable = this.this$0.requireArguments().getSerializable("key_options");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions");
                return (MotionCaptureVariantOptions) serializable;
            }
        });
        this.motionHostComponent = LazyKt.lazy(new Function0<MotionHostComponent>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$motionHostComponent$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MotionHostComponent invoke() {
                MotionHostComponentHolder companion = MotionHostComponentHolder.INSTANCE.getInstance();
                SdkController companion2 = SdkController.INSTANCE.getInstance();
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                return companion.getComponent$onfido_capture_sdk_core_release(SdkController.getSdkComponent$default(companion2, contextRequireContext, null, 2, null), this.this$0.getMotionOptions());
            }
        });
        this.navigationManager = LazyKt.lazy(new Function0<FragmentNavigationManager>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$navigationManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentNavigationManager invoke() {
                MotionHostFragment motionHostFragment = this.this$0;
                FragmentManager childFragmentManager = motionHostFragment.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                return new FragmentNavigationManager(motionHostFragment, childFragmentManager, R.id.fragmentContainerView);
            }
        });
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final MotionHostFragment motionHostFragment = this.this$0;
                final Bundle bundle = null;
                return new AbstractSavedStateViewModelFactory(motionHostFragment, bundle) { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$viewModel$2$invoke$$inlined$createAbstractSavedStateFactory$default$1
                    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
                    protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        Intrinsics.checkNotNullParameter(handle, "handle");
                        MotionHostViewModel motionHostViewModelCreate = motionHostFragment.getMotionHostViewModelFactory$onfido_capture_sdk_core_release().create(handle, motionHostFragment.getMotionOptions().getAudioEnabled(), motionHostFragment.getMotionOptions().getShowIntro());
                        Intrinsics.checkNotNull(motionHostViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createAbstractSavedStateFactory.<no name provided>.create");
                        return motionHostViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MotionHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment$special$$inlined$viewModels$default$4
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

    private final OnfidoAvcFragmentHostBinding getBinding() {
        OnfidoAvcFragmentHostBinding onfidoAvcFragmentHostBinding = this._binding;
        Intrinsics.checkNotNull(onfidoAvcFragmentHostBinding);
        return onfidoAvcFragmentHostBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionCaptureVariantOptions getMotionOptions() {
        return (MotionCaptureVariantOptions) this.motionOptions.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentNavigationManager getNavigationManager() {
        return (FragmentNavigationManager) this.navigationManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionHostViewModel getViewModel() {
        return (MotionHostViewModel) this.viewModel.getValue();
    }

    private final void handleBackNavigation() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment.handleBackNavigation.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                if (MotionHostFragment.this.getNavigationManager().getCurrentScreensSnapshot().size() > 1) {
                    MotionHostFragment.this.getViewModel().getNavigator().exitCurrentScreen();
                } else {
                    MotionHostFragment.this.submitResult(AvcHostResult.Exit.INSTANCE);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleViewEvent(MotionHostViewModel.ViewEvent event) {
        AvcHostResult error;
        if (event instanceof MotionHostViewModel.ViewEvent.Loading) {
            RelativeLayout avcHostLoadingView = getBinding().avcHostLoadingView;
            Intrinsics.checkNotNullExpressionValue(avcHostLoadingView, "avcHostLoadingView");
            ViewExtensionsKt.toVisibleOrGone(avcHostLoadingView, ((MotionHostViewModel.ViewEvent.Loading) event).getShow());
        } else {
            if (event instanceof MotionHostViewModel.ViewEvent.RequestPermission) {
                getRuntimePermissionsManager$onfido_capture_sdk_core_release().requestPermissionsFromFragment$onfido_capture_sdk_core_release(this, ((MotionHostViewModel.ViewEvent.RequestPermission) event).getPermissions(), 100);
                return;
            }
            if (event instanceof MotionHostViewModel.ViewEvent.VideoUploaded) {
                error = new AvcHostResult.Completed(((MotionHostViewModel.ViewEvent.VideoUploaded) event).getUploadedArtifact());
            } else if (!(event instanceof MotionHostViewModel.ViewEvent.Error)) {
                return;
            } else {
                error = new AvcHostResult.Error(((MotionHostViewModel.ViewEvent.Error) event).getMessage());
            }
            submitResult(error);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideToolbar() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        ActionBar supportActionBar = ((AppCompatActivity) fragmentActivityRequireActivity).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    private final void observeViewEvent() {
        Disposable disposableSubscribe = getViewModel().getViewEvent().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment.observeViewEvent.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(MotionHostViewModel.ViewEvent p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                MotionHostFragment.this.handleViewEvent(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
    }

    private final void registerFragmentLifecycleCallbacks() {
        getChildFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment.registerFragmentLifecycleCallbacks.1
            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(fm, "fm");
                Intrinsics.checkNotNullParameter(f, "f");
                Intrinsics.checkNotNullParameter(v, "v");
                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
                if (f instanceof MotionUploadFragment) {
                    MotionHostFragment.this.hideToolbar();
                }
            }

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
                Intrinsics.checkNotNullParameter(fm, "fm");
                Intrinsics.checkNotNullParameter(f, "f");
                super.onFragmentViewDestroyed(fm, f);
                if (!(f instanceof MotionCaptureFragment)) {
                    if (f instanceof MotionUploadFragment) {
                        MotionHostFragment.this.showToolbar();
                    }
                } else {
                    NextActionListener nextActionListener = MotionHostFragment.this.getNextActionListener();
                    if (nextActionListener != null) {
                        nextActionListener.restoreToolbar();
                    }
                }
            }
        }, true);
    }

    private final void setFragmentResultsListeners() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FragmentManagerExtKt.setFragmentResultsListeners(childFragmentManager, viewLifecycleOwner, new String[]{"request_key_permissions_screen"}, new Function2<String, Bundle, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment.setFragmentResultsListeners.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String requestKey, Bundle bundle) {
                Intrinsics.checkNotNullParameter(requestKey, "requestKey");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                if (Intrinsics.areEqual(requestKey, "request_key_permissions_screen")) {
                    MotionHostFragment.this.getViewModel().onPermissionsScreenResult(PermissionsManagementFragment.INSTANCE.getResult(bundle));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showToolbar() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        ActionBar supportActionBar = ((AppCompatActivity) fragmentActivityRequireActivity).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void submitResult(AvcHostResult avcHostResult) {
        Flow.Result failed;
        String string = requireArguments().getString("key_request");
        if (string != null) {
            getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_AVC_HOST_RESULT, avcHostResult)));
            Bundle arguments = getArguments();
            if (arguments == null || !arguments.getBoolean(KEY_ARG_IS_IN_WORKFLOW)) {
                getParentFragmentManager().popBackStack();
                return;
            }
            return;
        }
        if (avcHostResult instanceof AvcHostResult.Completed) {
            failed = new MotionFlow.Result.Success(((AvcHostResult.Completed) avcHostResult).getUploadedArtifact().getId());
        } else if (avcHostResult instanceof AvcHostResult.Error) {
            failed = new MotionFlow.Result.Failed(new FailureReason.InternalException(((AvcHostResult.Error) avcHostResult).getMessage(), null, 2, null));
        } else {
            if (!Intrinsics.areEqual(avcHostResult, AvcHostResult.Exit.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            failed = new MotionFlow.Result.Failed(FailureReason.Canceled.INSTANCE);
        }
        finishFlow(failed);
    }

    public final MotionHostComponent getMotionHostComponent$onfido_capture_sdk_core_release() {
        return (MotionHostComponent) this.motionHostComponent.getValue();
    }

    public final MotionHostViewModel.Factory getMotionHostViewModelFactory$onfido_capture_sdk_core_release() {
        MotionHostViewModel.Factory factory = this.motionHostViewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("motionHostViewModelFactory");
        return null;
    }

    public final RuntimePermissionsManager getRuntimePermissionsManager$onfido_capture_sdk_core_release() {
        RuntimePermissionsManager runtimePermissionsManager = this.runtimePermissionsManager;
        if (runtimePermissionsManager != null) {
            return runtimePermissionsManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("runtimePermissionsManager");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
        MotionHostComponentHolder.INSTANCE.getInstance().onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Overrides a deprecated method, compilation error with Kotlin 1.9")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            getViewModel().onPermissionsRequestResult(grantResults);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        observeViewEvent();
        getViewModel().getNavigationManagerHolder().setNavigationManager(getNavigationManager());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getViewModel().getNavigationManagerHolder().resetNavigationManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getMotionHostComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoAvcFragmentHostBinding.bind(view);
        MotionHostViewModel viewModel = getViewModel();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        viewModel.initialize(ContextUtilsKt.isGooglePlayServiceAvailable(context));
        handleBackNavigation();
        setFragmentResultsListeners();
        registerFragmentLifecycleCallbacks();
    }

    public final void setMotionHostViewModelFactory$onfido_capture_sdk_core_release(MotionHostViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.motionHostViewModelFactory = factory;
    }

    public final void setRuntimePermissionsManager$onfido_capture_sdk_core_release(RuntimePermissionsManager runtimePermissionsManager) {
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "<set-?>");
        this.runtimePermissionsManager = runtimePermissionsManager;
    }
}
