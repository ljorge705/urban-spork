package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.lifecycle.ViewModel;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 G2\u00020\u0001:\u0002GHB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J(\u00102\u001a\u0015\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00150\u001503¢\u0006\u0002\b\u001b2\u0006\u00100\u001a\u000201H\u0000¢\u0006\u0002\b4J\u000e\u00105\u001a\u0002062\u0006\u00107\u001a\u000208J\u001e\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u000206J\b\u0010?\u001a\u00020/H\u0014J%\u0010@\u001a\u00020/2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010#H\u0000¢\u0006\u0002\bCJ\u0006\u0010D\u001a\u00020/J\u000e\u0010E\u001a\u00020/2\u0006\u0010F\u001a\u00020\u0015R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u0018\u001a\u0015\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00150\u00150\u0019¢\u0006\u0002\b\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010(\u001a\u0015\u0012\f\u0012\n \u001a*\u0004\u0018\u00010*0*0)¢\u0006\u0002\b\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00148@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u0012\u001a\u0004\b,\u0010\u0017R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel;", "Landroidx/lifecycle/ViewModel;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "compressPoaDocumentUseCase", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/CompressPoaDocumentUseCase;", "uploadPoaDocumentUseCase", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/UploadPoaDocumentUseCase;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/CompressPoaDocumentUseCase;Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/UploadPoaDocumentUseCase;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "imageReadResult", "Lio/reactivex/rxjava3/core/Observable;", "Landroid/graphics/Bitmap;", "getImageReadResult$onfido_capture_sdk_core_release", "()Lio/reactivex/rxjava3/core/Observable;", "imageReadSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "issuingCountryCode", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getIssuingCountryCode$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "setIssuingCountryCode$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "poaDocumentType", "Lcom/onfido/api/client/data/PoaDocumentType;", "getPoaDocumentType$onfido_capture_sdk_core_release", "()Lcom/onfido/api/client/data/PoaDocumentType;", "setPoaDocumentType$onfido_capture_sdk_core_release", "(Lcom/onfido/api/client/data/PoaDocumentType;)V", "uploadResultSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "viewState", "getViewState$onfido_capture_sdk_core_release", "viewState$delegate", "getImageBitmapFromFileByteArray", "", "file", "Ljava/io/File;", "getImageFromFileAsBitmap", "Lio/reactivex/rxjava3/core/Single;", "getImageFromFileAsBitmap$onfido_capture_sdk_core_release", "getTitleResId", "", "isTakePhoto", "", "loadBitmapFromPdfUri", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "densityDpi", "onCleared", "setPoaData", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "documentType", "setPoaData$onfido_capture_sdk_core_release", "trackPoaDocumentSubmission", "uploadPoaDocument", "bitmap", "Companion", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentSubmissionViewModel extends ViewModel {
    private static final Companion Companion = new Companion(null);
    private static final int PDF_DEFAULT_RESOLUTION_DPI = 72;

    /* renamed from: compositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy compositeDisposable;
    private final CompressPoaDocumentUseCase compressPoaDocumentUseCase;
    private final Observable<Bitmap> imageReadResult;
    private final PublishSubject<Bitmap> imageReadSubject;
    private CountryCode issuingCountryCode;
    private PoaDocumentType poaDocumentType;
    private final SchedulersProvider schedulersProvider;
    private final ScreenTracker screenTracker;
    private final UploadPoaDocumentUseCase uploadPoaDocumentUseCase;
    private final BehaviorSubject<ViewState> uploadResultSubject;

    /* renamed from: viewState$delegate, reason: from kotlin metadata */
    private final Lazy viewState;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$Companion;", "", "()V", "PDF_DEFAULT_RESOLUTION_DPI", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "", "()V", "Error", "HideLoading", "ShowLoading", "UploadCompleted", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$Error;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$HideLoading;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$ShowLoading;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$UploadCompleted;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class ViewState {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$Error;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Error extends ViewState {
            public static final Error INSTANCE = new Error();

            private Error() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$HideLoading;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class HideLoading extends ViewState {
            public static final HideLoading INSTANCE = new HideLoading();

            private HideLoading() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$ShowLoading;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ShowLoading extends ViewState {
            public static final ShowLoading INSTANCE = new ShowLoading();

            private ShowLoading() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState$UploadCompleted;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSubmission/PoaDocumentSubmissionViewModel$ViewState;", "documentId", "", "type", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDocumentId", "()Ljava/lang/String;", "getIssuingCountry", "getType", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class UploadCompleted extends ViewState {
            private final String documentId;
            private final String issuingCountry;
            private final String type;

            public UploadCompleted() {
                this(null, null, null, 7, null);
            }

            public static /* synthetic */ UploadCompleted copy$default(UploadCompleted uploadCompleted, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = uploadCompleted.documentId;
                }
                if ((i & 2) != 0) {
                    str2 = uploadCompleted.type;
                }
                if ((i & 4) != 0) {
                    str3 = uploadCompleted.issuingCountry;
                }
                return uploadCompleted.copy(str, str2, str3);
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

            public final UploadCompleted copy(String documentId, String type, String issuingCountry) {
                return new UploadCompleted(documentId, type, issuingCountry);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof UploadCompleted)) {
                    return false;
                }
                UploadCompleted uploadCompleted = (UploadCompleted) other;
                return Intrinsics.areEqual(this.documentId, uploadCompleted.documentId) && Intrinsics.areEqual(this.type, uploadCompleted.type) && Intrinsics.areEqual(this.issuingCountry, uploadCompleted.issuingCountry);
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
                String str = this.documentId;
                int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.type;
                int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.issuingCountry;
                return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
            }

            public String toString() {
                return "UploadCompleted(documentId=" + this.documentId + ", type=" + this.type + ", issuingCountry=" + this.issuingCountry + ')';
            }

            public UploadCompleted(String str, String str2, String str3) {
                super(null);
                this.documentId = str;
                this.type = str2;
                this.issuingCountry = str3;
            }

            public /* synthetic */ UploadCompleted(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
            }
        }

        private ViewState() {
        }

        public /* synthetic */ ViewState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public PoaDocumentSubmissionViewModel(ScreenTracker screenTracker, SchedulersProvider schedulersProvider, CompressPoaDocumentUseCase compressPoaDocumentUseCase, UploadPoaDocumentUseCase uploadPoaDocumentUseCase, WaitingScreenTracker waitingScreenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(compressPoaDocumentUseCase, "compressPoaDocumentUseCase");
        Intrinsics.checkNotNullParameter(uploadPoaDocumentUseCase, "uploadPoaDocumentUseCase");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        this.screenTracker = screenTracker;
        this.schedulersProvider = schedulersProvider;
        this.compressPoaDocumentUseCase = compressPoaDocumentUseCase;
        this.uploadPoaDocumentUseCase = uploadPoaDocumentUseCase;
        this.waitingScreenTracker = waitingScreenTracker;
        this.compositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel$compositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        BehaviorSubject<ViewState> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(...)");
        this.uploadResultSubject = behaviorSubjectCreate;
        PublishSubject<Bitmap> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.imageReadSubject = publishSubjectCreate;
        Observable<Bitmap> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.imageReadResult = observableHide;
        this.viewState = LazyKt.lazy(new Function0<Observable<ViewState>>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel$viewState$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Observable<PoaDocumentSubmissionViewModel.ViewState> invoke() {
                return this.this$0.uploadResultSubject.distinctUntilChanged();
            }
        });
    }

    private final CompositeDisposable getCompositeDisposable() {
        return (CompositeDisposable) this.compositeDisposable.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap getImageFromFileAsBitmap$lambda$2(File file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "$file");
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        new DataInputStream(new BufferedInputStream(new FileInputStream(file))).readFully(bArr);
        file.delete();
        return BitmapFactory.decodeByteArray(bArr, 0, length);
    }

    public static /* synthetic */ void setPoaData$onfido_capture_sdk_core_release$default(PoaDocumentSubmissionViewModel poaDocumentSubmissionViewModel, CountryCode countryCode, PoaDocumentType poaDocumentType, int i, Object obj) {
        if ((i & 1) != 0) {
            countryCode = null;
        }
        if ((i & 2) != 0) {
            poaDocumentType = null;
        }
        poaDocumentSubmissionViewModel.setPoaData$onfido_capture_sdk_core_release(countryCode, poaDocumentType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadPoaDocument$lambda$4(PoaDocumentSubmissionViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.uploadResultSubject.onNext(ViewState.HideLoading.INSTANCE);
        this$0.waitingScreenTracker.trackWaitingScreenCompletion(new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_POA_DOCUMENT_UPLOAD).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_POA_DOCUMENT_UPLOAD);
    }

    public final void getImageBitmapFromFileByteArray(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        CompositeDisposable compositeDisposable = getCompositeDisposable();
        Disposable disposableSubscribe = getImageFromFileAsBitmap$onfido_capture_sdk_core_release(file).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.getImageBitmapFromFileByteArray.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Bitmap bitmap) {
                PoaDocumentSubmissionViewModel.this.imageReadSubject.onNext(bitmap);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.getImageBitmapFromFileByteArray.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "failed getting image from file", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public final Single<Bitmap> getImageFromFileAsBitmap$onfido_capture_sdk_core_release(final File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Single<Bitmap> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PoaDocumentSubmissionViewModel.getImageFromFileAsBitmap$lambda$2(file);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    public final Observable<Bitmap> getImageReadResult$onfido_capture_sdk_core_release() {
        return this.imageReadResult;
    }

    /* renamed from: getIssuingCountryCode$onfido_capture_sdk_core_release, reason: from getter */
    public final CountryCode getIssuingCountryCode() {
        return this.issuingCountryCode;
    }

    /* renamed from: getPoaDocumentType$onfido_capture_sdk_core_release, reason: from getter */
    public final PoaDocumentType getPoaDocumentType() {
        return this.poaDocumentType;
    }

    public final int getTitleResId(boolean isTakePhoto) {
        return isTakePhoto ? R.string.onfido_poa_capture_redo : R.string.onfido_poa_capture_choose_another;
    }

    public final Observable<ViewState> getViewState$onfido_capture_sdk_core_release() {
        Object value = this.viewState.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Observable) value;
    }

    public final Bitmap loadBitmapFromPdfUri(Uri uri, ContentResolver contentResolver, int densityDpi) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
        Intrinsics.checkNotNull(parcelFileDescriptorOpenFileDescriptor);
        PdfRenderer pdfRenderer = new PdfRenderer(parcelFileDescriptorOpenFileDescriptor);
        PdfRenderer.Page pageOpenPage = pdfRenderer.openPage(0);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((pageOpenPage.getWidth() * densityDpi) / 72, (densityDpi * pageOpenPage.getHeight()) / 72, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.eraseColor(-1);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "apply(...)");
        pageOpenPage.render(bitmapCreateBitmap, null, null, 2);
        pageOpenPage.close();
        pdfRenderer.close();
        return bitmapCreateBitmap;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        getCompositeDisposable().clear();
    }

    public final void setIssuingCountryCode$onfido_capture_sdk_core_release(CountryCode countryCode) {
        this.issuingCountryCode = countryCode;
    }

    public final void setPoaData$onfido_capture_sdk_core_release(CountryCode countryCode, PoaDocumentType documentType) {
        if (countryCode != null) {
            this.issuingCountryCode = countryCode;
        }
        if (documentType != null) {
            this.poaDocumentType = documentType;
        }
    }

    public final void setPoaDocumentType$onfido_capture_sdk_core_release(PoaDocumentType poaDocumentType) {
        this.poaDocumentType = poaDocumentType;
    }

    public final void trackPoaDocumentSubmission() {
        this.screenTracker.trackPoaDocumentSubmission$onfido_capture_sdk_core_release();
    }

    public final void uploadPoaDocument(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        CompositeDisposable compositeDisposable = getCompositeDisposable();
        Disposable disposableSubscribe = this.compressPoaDocumentUseCase.invoke(bitmap).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.uploadPoaDocument.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends PoaDocumentUpload> apply(byte[] image) {
                Intrinsics.checkNotNullParameter(image, "image");
                return PoaDocumentSubmissionViewModel.this.uploadPoaDocumentUseCase.invoke(image, PoaDocumentSubmissionViewModel.this.getPoaDocumentType(), PoaDocumentSubmissionViewModel.this.getIssuingCountryCode());
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doOnError(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.uploadPoaDocument.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaDocumentSubmissionViewModel.this.uploadResultSubject.onNext(ViewState.Error.INSTANCE);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.uploadPoaDocument.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaDocumentSubmissionViewModel.this.uploadResultSubject.onNext(ViewState.ShowLoading.INSTANCE);
            }
        }).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                PoaDocumentSubmissionViewModel.uploadPoaDocument$lambda$4(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.uploadPoaDocument.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(PoaDocumentUpload poaDocumentUpload) {
                Intrinsics.checkNotNullParameter(poaDocumentUpload, "poaDocumentUpload");
                PoaDocumentSubmissionViewModel.this.uploadResultSubject.onNext(new ViewState.UploadCompleted(poaDocumentUpload.getId(), poaDocumentUpload.getType(), poaDocumentUpload.getIssuingCountry()));
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission.PoaDocumentSubmissionViewModel.uploadPoaDocument.6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaDocumentSubmissionViewModel.this.uploadResultSubject.onNext(ViewState.Error.INSTANCE);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }
}
