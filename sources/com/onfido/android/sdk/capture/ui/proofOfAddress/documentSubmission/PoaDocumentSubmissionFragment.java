package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.core.os.BundleKt;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoActivityOnfidoBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentPoaDocumentSubmissionBinding;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaZoomClass;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel;
import com.onfido.android.sdk.capture.ui.widget.ShadowedScrollView;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.File;
import java.io.FileNotFoundException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 @2\u00020\u0001:\u0002@AB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020+H\u0016J\b\u00102\u001a\u00020+H\u0016J\u001a\u00103\u001a\u00020+2\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u00108\u001a\u00020+2\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020+2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010<\u001a\u00020+2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0017\u0010=\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0000¢\u0006\u0002\b>J\b\u0010?\u001a\u00020+H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'¨\u0006B"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentPoaDocumentSubmissionBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentPoaDocumentSubmissionBinding;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "intentResultObserver", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "isTakePhoto", "", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "poaUtils", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;", "getPoaUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;", "setPoaUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;)V", "poaViewModelFactory", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel;", "getPoaViewModelFactory", "()Lcom/onfido/javax/inject/Provider;", "setPoaViewModelFactory", "(Lcom/onfido/javax/inject/Provider;)V", "selectedImageBitmap", "Landroid/graphics/Bitmap;", "viewModel", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getImageBitmap", "", "documentFileName", "", "handleViewState", "viewState", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "onDestroyView", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "renderFromMediaUpload", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "renderLargeImage", "renderSmallImage", "renderSmallImageAfterRetakePhoto", "renderSmallImageAfterRetakePhoto$onfido_capture_sdk_core_release", "showInvalidFileDialog", "Companion", "PoaSubmissionResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentSubmissionFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DOCUMENT_COUNTRY = "document_country";
    private static final String DOCUMENT_FILE_NAME = "document_file_name";
    private static final String DOCUMENT_TYPE = "document_type";
    private static final String DOCUMENT_URI = "document_uri";
    private static final String IS_TAKE_PHOTO = "is_take_photo";
    private static final String KEY_RESULT = "key_result";
    private OnfidoFragmentPoaDocumentSubmissionBinding _binding;

    @Inject
    public ImageUtils imageUtils;
    private final ActivityResultLauncher<Intent> intentResultObserver;
    private boolean isTakePhoto;
    private final LifecycleAwareDialog lifecycleAwareDialog;

    @Inject
    public PoaUtils poaUtils;

    @Inject
    public Provider<PoaDocumentSubmissionViewModel> poaViewModelFactory;
    private Bitmap selectedImageBitmap;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$Companion;", "", "()V", "DOCUMENT_COUNTRY", "", "DOCUMENT_FILE_NAME", "DOCUMENT_TYPE", "DOCUMENT_URI", "IS_TAKE_PHOTO", "KEY_RESULT", "createInstance", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment;", "resultKey", "isTakePhoto", "", "documentUri", "Landroid/net/Uri;", "documentFileName", "documentCountry", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentType", "Lcom/onfido/api/client/data/PoaDocumentType;", "getResult", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final PoaDocumentSubmissionFragment createInstance(String resultKey, boolean isTakePhoto, Uri documentUri, String documentFileName, CountryCode documentCountry, PoaDocumentType documentType) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            Intrinsics.checkNotNullParameter(documentCountry, "documentCountry");
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            PoaDocumentSubmissionFragment poaDocumentSubmissionFragment = new PoaDocumentSubmissionFragment();
            poaDocumentSubmissionFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(PoaDocumentSubmissionFragment.KEY_RESULT, resultKey), TuplesKt.to(PoaDocumentSubmissionFragment.IS_TAKE_PHOTO, Boolean.valueOf(isTakePhoto)), TuplesKt.to(PoaDocumentSubmissionFragment.DOCUMENT_URI, String.valueOf(documentUri)), TuplesKt.to(PoaDocumentSubmissionFragment.DOCUMENT_FILE_NAME, documentFileName), TuplesKt.to(PoaDocumentSubmissionFragment.DOCUMENT_COUNTRY, documentCountry), TuplesKt.to("document_type", documentType)));
            return poaDocumentSubmissionFragment;
        }

        public final PoaSubmissionResult getResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(PoaDocumentSubmissionFragment.KEY_RESULT);
            if (parcelable != null) {
                return (PoaSubmissionResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult;", "Landroid/os/Parcelable;", "()V", "RepeatPhotoCapture", "SuccessfulDocumentInfo", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult$RepeatPhotoCapture;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult$SuccessfulDocumentInfo;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class PoaSubmissionResult implements Parcelable {

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult$RepeatPhotoCapture;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RepeatPhotoCapture extends PoaSubmissionResult {
            public static final RepeatPhotoCapture INSTANCE = new RepeatPhotoCapture();
            public static final Parcelable.Creator<RepeatPhotoCapture> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<RepeatPhotoCapture> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final RepeatPhotoCapture createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return RepeatPhotoCapture.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final RepeatPhotoCapture[] newArray(int i) {
                    return new RepeatPhotoCapture[i];
                }
            }

            private RepeatPhotoCapture() {
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

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult$SuccessfulDocumentInfo;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionFragment$PoaSubmissionResult;", "documentId", "", "type", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDocumentId", "()Ljava/lang/String;", "getIssuingCountry", "getType", "component1", "component2", "component3", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class SuccessfulDocumentInfo extends PoaSubmissionResult {
            public static final Parcelable.Creator<SuccessfulDocumentInfo> CREATOR = new Creator();
            private final String documentId;
            private final String issuingCountry;
            private final String type;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<SuccessfulDocumentInfo> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final SuccessfulDocumentInfo createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new SuccessfulDocumentInfo(parcel.readString(), parcel.readString(), parcel.readString());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final SuccessfulDocumentInfo[] newArray(int i) {
                    return new SuccessfulDocumentInfo[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SuccessfulDocumentInfo(String documentId, String type, String str) {
                super(null);
                Intrinsics.checkNotNullParameter(documentId, "documentId");
                Intrinsics.checkNotNullParameter(type, "type");
                this.documentId = documentId;
                this.type = type;
                this.issuingCountry = str;
            }

            public static /* synthetic */ SuccessfulDocumentInfo copy$default(SuccessfulDocumentInfo successfulDocumentInfo, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = successfulDocumentInfo.documentId;
                }
                if ((i & 2) != 0) {
                    str2 = successfulDocumentInfo.type;
                }
                if ((i & 4) != 0) {
                    str3 = successfulDocumentInfo.issuingCountry;
                }
                return successfulDocumentInfo.copy(str, str2, str3);
            }

            /* renamed from: component1, reason: from getter */
            public final String getDocumentId() {
                return this.documentId;
            }

            /* renamed from: component2, reason: from getter */
            public final String getType() {
                return this.type;
            }

            /* renamed from: component3, reason: from getter */
            public final String getIssuingCountry() {
                return this.issuingCountry;
            }

            public final SuccessfulDocumentInfo copy(String documentId, String type, String issuingCountry) {
                Intrinsics.checkNotNullParameter(documentId, "documentId");
                Intrinsics.checkNotNullParameter(type, "type");
                return new SuccessfulDocumentInfo(documentId, type, issuingCountry);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SuccessfulDocumentInfo)) {
                    return false;
                }
                SuccessfulDocumentInfo successfulDocumentInfo = (SuccessfulDocumentInfo) other;
                return Intrinsics.areEqual(this.documentId, successfulDocumentInfo.documentId) && Intrinsics.areEqual(this.type, successfulDocumentInfo.type) && Intrinsics.areEqual(this.issuingCountry, successfulDocumentInfo.issuingCountry);
            }

            public final String getDocumentId() {
                return this.documentId;
            }

            public final String getIssuingCountry() {
                return this.issuingCountry;
            }

            public final String getType() {
                return this.type;
            }

            public int hashCode() {
                int iHashCode = ((this.documentId.hashCode() * 31) + this.type.hashCode()) * 31;
                String str = this.issuingCountry;
                return iHashCode + (str == null ? 0 : str.hashCode());
            }

            public String toString() {
                return "SuccessfulDocumentInfo(documentId=" + this.documentId + ", type=" + this.type + ", issuingCountry=" + this.issuingCountry + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.documentId);
                parcel.writeString(this.type);
                parcel.writeString(this.issuingCountry);
            }
        }

        private PoaSubmissionResult() {
        }

        public /* synthetic */ PoaSubmissionResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PoaDocumentSubmissionFragment() {
        super(R.layout.onfido_fragment_poa_document_submission);
        final Function0 function0 = null;
        this.lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);
        Function0<ViewModelProvider.Factory> function02 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final PoaDocumentSubmissionFragment poaDocumentSubmissionFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        PoaDocumentSubmissionViewModel poaDocumentSubmissionViewModel = poaDocumentSubmissionFragment.getPoaViewModelFactory().get();
                        Intrinsics.checkNotNull(poaDocumentSubmissionViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return poaDocumentSubmissionViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function03 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function03.invoke();
            }
        });
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PoaDocumentSubmissionViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$special$$inlined$viewModels$default$4
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
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) throws FileNotFoundException {
                PoaDocumentSubmissionFragment.intentResultObserver$lambda$2(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.intentResultObserver = activityResultLauncherRegisterForActivityResult;
    }

    @JvmStatic
    public static final PoaDocumentSubmissionFragment createInstance(String str, boolean z, Uri uri, String str2, CountryCode countryCode, PoaDocumentType poaDocumentType) {
        return INSTANCE.createInstance(str, z, uri, str2, countryCode, poaDocumentType);
    }

    private final OnfidoFragmentPoaDocumentSubmissionBinding getBinding() {
        OnfidoFragmentPoaDocumentSubmissionBinding onfidoFragmentPoaDocumentSubmissionBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentPoaDocumentSubmissionBinding);
        return onfidoFragmentPoaDocumentSubmissionBinding;
    }

    private final void getImageBitmap(String documentFileName) {
        File file = new File(documentFileName);
        if (file.exists()) {
            getViewModel().getImageBitmapFromFileByteArray(file);
        }
    }

    private final PoaDocumentSubmissionViewModel getViewModel() {
        return (PoaDocumentSubmissionViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleViewState(PoaDocumentSubmissionViewModel.ViewState viewState) {
        String string;
        if (Intrinsics.areEqual(viewState, PoaDocumentSubmissionViewModel.ViewState.Error.INSTANCE)) {
            this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : null, R.string.onfido_generic_error_network_detail, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : null));
            return;
        }
        if (Intrinsics.areEqual(viewState, PoaDocumentSubmissionViewModel.ViewState.ShowLoading.INSTANCE)) {
            showLoadingDialog$onfido_capture_sdk_core_release(new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_POA_DOCUMENT_UPLOAD));
            return;
        }
        if (Intrinsics.areEqual(viewState, PoaDocumentSubmissionViewModel.ViewState.HideLoading.INSTANCE)) {
            dismissLoadingDialog$onfido_capture_sdk_core_release();
            return;
        }
        if (viewState instanceof PoaDocumentSubmissionViewModel.ViewState.UploadCompleted) {
            PoaDocumentSubmissionViewModel.ViewState.UploadCompleted uploadCompleted = (PoaDocumentSubmissionViewModel.ViewState.UploadCompleted) viewState;
            if (uploadCompleted.getDocumentId() == null || (string = requireArguments().getString(KEY_RESULT)) == null) {
                return;
            }
            FragmentManager parentFragmentManager = getParentFragmentManager();
            String documentId = uploadCompleted.getDocumentId();
            String type = uploadCompleted.getType();
            if (type == null) {
                type = "";
            }
            parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RESULT, new PoaSubmissionResult.SuccessfulDocumentInfo(documentId, type, uploadCompleted.getIssuingCountry()))));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void intentResultObserver$lambda$2(PoaDocumentSubmissionFragment this$0, ActivityResult activityResult) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent data = activityResult.getData();
        if (data != null) {
            if (!this$0.isTakePhoto) {
                Uri data2 = data.getData();
                Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type android.net.Uri");
                this$0.renderFromMediaUpload(data2);
            } else {
                String stringExtra = data.getStringExtra(CaptureActivity.POA_CAPTURED_FILE_NAME);
                if (stringExtra != null) {
                    this$0.getImageBitmap(stringExtra);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$11(PoaDocumentSubmissionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isTakePhoto) {
            String string = this$0.requireArguments().getString(KEY_RESULT);
            if (string != null) {
                this$0.getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RESULT, PoaSubmissionResult.RepeatPhotoCapture.INSTANCE)));
                return;
            }
            return;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this$0.intentResultObserver;
        ImageUtils imageUtils$onfido_capture_sdk_core_release = this$0.getImageUtils$onfido_capture_sdk_core_release();
        String string2 = this$0.getString(R.string.onfido_poa_select_document_details_upload_text);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        activityResultLauncher.launch(imageUtils$onfido_capture_sdk_core_release.getUploadMediaPickerIntent$onfido_capture_sdk_core_release(string2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$12(PoaDocumentSubmissionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PoaDocumentSubmissionViewModel viewModel = this$0.getViewModel();
        Bitmap bitmap = this$0.selectedImageBitmap;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedImageBitmap");
            bitmap = null;
        }
        viewModel.uploadPoaDocument(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(PoaDocumentSubmissionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.renderLargeImage(this$0.getBinding());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(PoaDocumentSubmissionFragment this$0, View view) {
        OnfidoActivityOnfidoBinding binding$onfido_capture_sdk_core_release;
        Toolbar toolbar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Group poaImageLargeGroup = this$0.getBinding().poaImageLargeGroup;
        Intrinsics.checkNotNullExpressionValue(poaImageLargeGroup, "poaImageLargeGroup");
        ViewExtensionsKt.toGone$default(poaImageLargeGroup, false, 1, null);
        FragmentActivity activity = this$0.getActivity();
        OnfidoActivity onfidoActivity = activity instanceof OnfidoActivity ? (OnfidoActivity) activity : null;
        if (onfidoActivity != null && (binding$onfido_capture_sdk_core_release = onfidoActivity.getBinding$onfido_capture_sdk_core_release()) != null && (toolbar = binding$onfido_capture_sdk_core_release.toolbar) != null) {
            ViewExtensionsKt.toVisible$default(toolbar, false, 1, null);
        }
        new WindowInsetsControllerCompat(this$0.requireActivity().getWindow(), this$0.getBinding().getRoot()).show(WindowInsetsCompat.Type.systemBars());
        ShadowedScrollView poaImageSmallGroup = this$0.getBinding().poaImageSmallGroup;
        Intrinsics.checkNotNullExpressionValue(poaImageSmallGroup, "poaImageSmallGroup");
        ViewExtensionsKt.toVisible$default(poaImageSmallGroup, false, 1, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
    
        if (r4 != null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void renderFromMediaUpload(android.net.Uri r4) throws java.io.FileNotFoundException {
        /*
            r3 = this;
            com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils r0 = r3.getPoaUtils$onfido_capture_sdk_core_release()
            android.content.Context r1 = r3.getContext()
            r2 = 0
            if (r1 == 0) goto L10
            android.content.ContentResolver r1 = r1.getContentResolver()
            goto L11
        L10:
            r1 = r2
        L11:
            boolean r0 = r0.isPdfFile(r1, r4)
            if (r0 == 0) goto L4a
            com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils r0 = r3.getPoaUtils$onfido_capture_sdk_core_release()
            android.content.Context r1 = r3.getContext()
            if (r1 == 0) goto L25
            android.content.ContentResolver r2 = r1.getContentResolver()
        L25:
            boolean r0 = r0.isPdfFileValid(r2, r4)
            if (r0 == 0) goto L60
            android.content.Context r0 = r3.getContext()
            if (r0 == 0) goto L63
            android.content.ContentResolver r0 = r0.getContentResolver()
            if (r0 == 0) goto L63
            android.content.res.Resources r1 = r3.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r1 = r1.densityDpi
            com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel r2 = r3.getViewModel()
            android.graphics.Bitmap r4 = r2.loadBitmapFromPdfUri(r4, r0, r1)
            goto L58
        L4a:
            com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils r0 = r3.getPoaUtils$onfido_capture_sdk_core_release()
            android.content.Context r1 = r3.getContext()
            android.graphics.Bitmap r4 = r0.getBitmapFromUri(r1, r4)
            if (r4 == 0) goto L60
        L58:
            r3.selectedImageBitmap = r4
            boolean r4 = r3.isTakePhoto
            r3.renderSmallImage(r4)
            goto L63
        L60:
            r3.showInvalidFileDialog()
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment.renderFromMediaUpload(android.net.Uri):void");
    }

    private final void renderLargeImage(OnfidoFragmentPoaDocumentSubmissionBinding binding) {
        OnfidoActivityOnfidoBinding binding$onfido_capture_sdk_core_release;
        Toolbar toolbar;
        ShadowedScrollView poaImageSmallGroup = binding.poaImageSmallGroup;
        Intrinsics.checkNotNullExpressionValue(poaImageSmallGroup, "poaImageSmallGroup");
        Bitmap bitmap = null;
        ViewExtensionsKt.toGone$default(poaImageSmallGroup, false, 1, null);
        FragmentActivity activity = getActivity();
        OnfidoActivity onfidoActivity = activity instanceof OnfidoActivity ? (OnfidoActivity) activity : null;
        if (onfidoActivity != null && (binding$onfido_capture_sdk_core_release = onfidoActivity.getBinding$onfido_capture_sdk_core_release()) != null && (toolbar = binding$onfido_capture_sdk_core_release.toolbar) != null) {
            ViewExtensionsKt.toGone$default(toolbar, false, 1, null);
        }
        Window window = requireActivity().getWindow();
        window.getStatusBarColor();
        WindowCompat.setDecorFitsSystemWindows(window, true);
        window.addFlags(Integer.MIN_VALUE);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, binding.getRoot());
        windowInsetsControllerCompat.setSystemBarsBehavior(2);
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        Group poaImageLargeGroup = binding.poaImageLargeGroup;
        Intrinsics.checkNotNullExpressionValue(poaImageLargeGroup, "poaImageLargeGroup");
        ViewExtensionsKt.toVisible$default(poaImageLargeGroup, false, 1, null);
        binding.poaDocumentImageLarge.invalidate();
        PoaZoomClass poaZoomClass = binding.poaDocumentImageLarge;
        Bitmap bitmap2 = this.selectedImageBitmap;
        if (bitmap2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedImageBitmap");
        } else {
            bitmap = bitmap2;
        }
        poaZoomClass.setImageBitmap(bitmap);
        binding.poaDocumentImageLarge.fitToScreen$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderSmallImage(boolean isTakePhoto) {
        ImageView imageView = getBinding().poaDocumentImage;
        Bitmap bitmap = this.selectedImageBitmap;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedImageBitmap");
            bitmap = null;
        }
        imageView.setImageBitmap(bitmap);
        getBinding().poaDocumentImage.setAdjustViewBounds(true);
        getBinding().poaDocumentImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        getBinding().poaRepeatButton.setText$onfido_capture_sdk_core_release(getViewModel().getTitleResId(isTakePhoto));
    }

    private final void showInvalidFileDialog() {
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_poa_err_invalid_file_title), R.string.onfido_poa_err_invalid_file_message, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_poa_err_invalid_file_ok, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment.showInvalidFileDialog.1
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

    public final ImageUtils getImageUtils$onfido_capture_sdk_core_release() {
        ImageUtils imageUtils = this.imageUtils;
        if (imageUtils != null) {
            return imageUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUtils");
        return null;
    }

    public final PoaUtils getPoaUtils$onfido_capture_sdk_core_release() {
        PoaUtils poaUtils = this.poaUtils;
        if (poaUtils != null) {
            return poaUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaUtils");
        return null;
    }

    public final Provider<PoaDocumentSubmissionViewModel> getPoaViewModelFactory() {
        Provider<PoaDocumentSubmissionViewModel> provider = this.poaViewModelFactory;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaViewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Bitmap bitmap = null;
        this._binding = null;
        Bitmap bitmap2 = this.selectedImageBitmap;
        if (bitmap2 != null) {
            if (bitmap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedImageBitmap");
            } else {
                bitmap = bitmap2;
            }
            bitmap.recycle();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Disposable disposableSubscribe = getViewModel().getViewState$onfido_capture_sdk_core_release().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment.onStart.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(PoaDocumentSubmissionViewModel.ViewState viewState) {
                Intrinsics.checkNotNullParameter(viewState, "viewState");
                PoaDocumentSubmissionFragment.this.handleViewState(viewState);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
        Disposable disposableSubscribe2 = getViewModel().getImageReadResult$onfido_capture_sdk_core_release().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment.onStart.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Bitmap it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaDocumentSubmissionFragment.this.selectedImageBitmap = it;
                PoaDocumentSubmissionFragment poaDocumentSubmissionFragment = PoaDocumentSubmissionFragment.this;
                poaDocumentSubmissionFragment.renderSmallImage(poaDocumentSubmissionFragment.isTakePhoto);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe2, viewLifecycleOwner2);
        getViewModel().trackPoaDocumentSubmission();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).poaComponentFactory$onfido_capture_sdk_core_release().create().inject(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentPoaDocumentSubmissionBinding.bind(view);
        PoaDocumentSubmissionViewModel viewModel = getViewModel();
        Object obj = requireArguments().get(DOCUMENT_COUNTRY);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.onfido.android.sdk.capture.utils.CountryCode");
        PoaDocumentSubmissionViewModel.setPoaData$onfido_capture_sdk_core_release$default(viewModel, (CountryCode) obj, null, 2, null);
        PoaDocumentSubmissionViewModel viewModel2 = getViewModel();
        Object obj2 = requireArguments().get("document_type");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.onfido.api.client.data.PoaDocumentType");
        PoaDocumentSubmissionViewModel.setPoaData$onfido_capture_sdk_core_release$default(viewModel2, null, (PoaDocumentType) obj2, 1, null);
        boolean z = requireArguments().getBoolean(IS_TAKE_PHOTO);
        this.isTakePhoto = z;
        if (z) {
            String string = requireArguments().getString(DOCUMENT_FILE_NAME);
            if (string != null) {
                Intrinsics.checkNotNull(string);
                getImageBitmap(string);
            }
        } else {
            renderFromMediaUpload(getPoaUtils$onfido_capture_sdk_core_release().getDocumentUri(requireArguments().getString(DOCUMENT_URI)));
        }
        getBinding().poaEnlargeButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentSubmissionFragment.onViewCreated$lambda$7(this.f$0, view2);
            }
        });
        getBinding().poaCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentSubmissionFragment.onViewCreated$lambda$9(this.f$0, view2);
            }
        });
        getBinding().poaRepeatButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentSubmissionFragment.onViewCreated$lambda$11(this.f$0, view2);
            }
        });
        getBinding().poaSubmitDocumentButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentSubmissionFragment.onViewCreated$lambda$12(this.f$0, view2);
            }
        });
    }

    public final void renderSmallImageAfterRetakePhoto$onfido_capture_sdk_core_release(String documentFileName) {
        if (documentFileName != null) {
            getImageBitmap(documentFileName);
        }
    }

    public final void setImageUtils$onfido_capture_sdk_core_release(ImageUtils imageUtils) {
        Intrinsics.checkNotNullParameter(imageUtils, "<set-?>");
        this.imageUtils = imageUtils;
    }

    public final void setPoaUtils$onfido_capture_sdk_core_release(PoaUtils poaUtils) {
        Intrinsics.checkNotNullParameter(poaUtils, "<set-?>");
        this.poaUtils = poaUtils;
    }

    public final void setPoaViewModelFactory(Provider<PoaDocumentSubmissionViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.poaViewModelFactory = provider;
    }
}
