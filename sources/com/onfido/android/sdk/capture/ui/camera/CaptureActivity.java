package com.onfido.android.sdk.capture.ui.camera;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.PreviewView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.common.MimeTypes;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoViewDocumentFormatSelectionBinding;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionRect;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifactKt;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEventName;
import com.onfido.android.sdk.capture.internal.performance.trackers.PerformanceEvents;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.ZoomImageView;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotAvailableException;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotFoundException;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView;
import com.onfido.android.sdk.capture.ui.camera.util.CaptureActivityLayoutAdjuster;
import com.onfido.android.sdk.capture.ui.camera.util.IntentHelper;
import com.onfido.android.sdk.capture.ui.camera.util.ValidationBubblesOffsetDelegate;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.model.DocumentTypeUIModel;
import com.onfido.android.sdk.capture.ui.model.DocumentUITextModelMapper;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.FileUtils;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.OnfidoConfigExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.android.sdk.capture.utils.StringRepresentation;
import com.onfido.android.sdk.capture.utils.ToolbarExtensionsKt;
import com.onfido.android.sdk.capture.utils.ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1;
import com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewUtil;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.SentryEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000æ\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\r\u0018\u0000 Á\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002Á\u0003B\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010È\u0001\u001a\u00030É\u00012\b\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0002J\u0014\u0010Ì\u0001\u001a\u00030É\u00012\b\u0010Í\u0001\u001a\u00030¡\u0001H\u0016J\n\u0010Î\u0001\u001a\u00030É\u0001H\u0002J\n\u0010Ï\u0001\u001a\u00030É\u0001H\u0016J\u0013\u0010Ð\u0001\u001a\u00030É\u00012\u0007\u0010Ñ\u0001\u001a\u00020pH\u0016J\t\u0010Ò\u0001\u001a\u00020GH\u0002J\u001d\u0010Ó\u0001\u001a\u0005\u0018\u00010É\u00012\t\b\u0001\u0010Ô\u0001\u001a\u00020\u0013H\u0002¢\u0006\u0003\u0010Õ\u0001J\u0015\u0010Ö\u0001\u001a\u00030É\u00012\t\b\u0001\u0010Ô\u0001\u001a\u00020\u0013H\u0002J\n\u0010×\u0001\u001a\u00030É\u0001H\u0002J)\u0010Ø\u0001\u001a\u00030Ù\u00012\b\u0010Í\u0001\u001a\u00030¡\u00012\b\u0010Ú\u0001\u001a\u00030Ë\u00012\t\b\u0002\u0010Û\u0001\u001a\u00020\u0013H\u0002J\n\u0010Ü\u0001\u001a\u00030É\u0001H\u0016J\n\u0010Ý\u0001\u001a\u00030É\u0001H\u0016J\n\u0010Þ\u0001\u001a\u00030É\u0001H\u0016J\u0013\u0010ß\u0001\u001a\u00030É\u00012\u0007\u0010à\u0001\u001a\u00020pH\u0016J\u0019\u0010á\u0001\u001a\u00030É\u00012\r\u0010â\u0001\u001a\b0ã\u0001j\u0003`ä\u0001H\u0002J\n\u0010å\u0001\u001a\u00030É\u0001H\u0016J\n\u0010æ\u0001\u001a\u00030ç\u0001H\u0002J\t\u0010è\u0001\u001a\u00020\u0013H\u0002J\t\u0010é\u0001\u001a\u00020GH\u0016J\n\u0010ê\u0001\u001a\u00030ë\u0001H\u0016J%\u0010ì\u0001\u001a\u00030í\u00012\u0007\u0010î\u0001\u001a\u00020h2\u0007\u0010ï\u0001\u001a\u00020h2\u0007\u0010ð\u0001\u001a\u00020pH\u0002J\u0014\u0010ñ\u0001\u001a\u00030í\u00012\b\u0010ò\u0001\u001a\u00030ó\u0001H\u0002J\f\u0010ô\u0001\u001a\u0005\u0018\u00010¡\u0001H\u0016J\n\u0010õ\u0001\u001a\u00030ö\u0001H\u0002J\t\u0010÷\u0001\u001a\u00020pH\u0002J\t\u0010ø\u0001\u001a\u00020pH\u0016J\n\u0010ù\u0001\u001a\u00030É\u0001H\u0016J\n\u0010ú\u0001\u001a\u00030É\u0001H\u0016J\n\u0010û\u0001\u001a\u00030É\u0001H\u0016J\n\u0010ü\u0001\u001a\u00030É\u0001H\u0016J\n\u0010ý\u0001\u001a\u00030É\u0001H\u0016J\u0012\u0010þ\u0001\u001a\u00030É\u00012\u0006\u0010,\u001a\u00020-H\u0002J\n\u0010ÿ\u0001\u001a\u00030É\u0001H\u0002J\u0014\u0010\u0080\u0002\u001a\u00030\u0093\u00012\b\u0010P\u001a\u0004\u0018\u00010QH\u0002J\n\u0010\u0081\u0002\u001a\u00030É\u0001H\u0002J\n\u0010\u0082\u0002\u001a\u00030É\u0001H\u0002J\u001e\u0010\u0083\u0002\u001a\u00030\u0084\u00022\b\u0010\u0085\u0002\u001a\u00030Ë\u00012\b\u0010\u0086\u0002\u001a\u00030\u0087\u0002H\u0002J\n\u0010\u0088\u0002\u001a\u00030É\u0001H\u0016J\u001c\u0010\u0089\u0002\u001a\u00030É\u00012\u0007\u0010\u008a\u0002\u001a\u00020\u00132\u0007\u0010\u008b\u0002\u001a\u00020\u0013H\u0016J\u0014\u0010\u008c\u0002\u001a\u00030É\u00012\b\u0010\u008d\u0002\u001a\u00030\u008e\u0002H\u0016J\n\u0010\u008f\u0002\u001a\u00030É\u0001H\u0017J\n\u0010\u0090\u0002\u001a\u00030É\u0001H\u0002J\n\u0010\u0091\u0002\u001a\u00030É\u0001H\u0002J\n\u0010\u0092\u0002\u001a\u00030É\u0001H\u0002J\u001e\u0010\u0093\u0002\u001a\u00030É\u00012\b\u0010Í\u0001\u001a\u00030¡\u00012\b\u0010\u0094\u0002\u001a\u00030Ë\u0001H\u0016J\n\u0010\u0095\u0002\u001a\u00030É\u0001H\u0002J\u0014\u0010\u0096\u0002\u001a\u00030É\u00012\b\u0010\u0097\u0002\u001a\u00030\u0098\u0002H\u0016J\u0014\u0010\u0099\u0002\u001a\u00030É\u00012\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002H\u0002J\n\u0010\u009c\u0002\u001a\u00030É\u0001H\u0016J\n\u0010\u009d\u0002\u001a\u00030É\u0001H\u0016J\u0016\u0010\u009e\u0002\u001a\u00030É\u00012\n\u0010\u009f\u0002\u001a\u0005\u0018\u00010 \u0002H\u0014J\n\u0010¡\u0002\u001a\u00030É\u0001H\u0014J%\u0010¢\u0002\u001a\u00030É\u00012\u0007\u0010î\u0001\u001a\u00020h2\u0007\u0010ï\u0001\u001a\u00020h2\u0007\u0010ð\u0001\u001a\u00020pH\u0016J\n\u0010£\u0002\u001a\u00030É\u0001H\u0017J\u0013\u0010¤\u0002\u001a\u00030É\u00012\u0007\u0010ï\u0001\u001a\u00020hH\u0016J\n\u0010¥\u0002\u001a\u00030É\u0001H\u0016J\u0014\u0010¦\u0002\u001a\u00030É\u00012\b\u0010§\u0002\u001a\u00030¨\u0002H\u0016J\n\u0010©\u0002\u001a\u00030É\u0001H\u0016J\n\u0010ª\u0002\u001a\u00030É\u0001H\u0016J\n\u0010«\u0002\u001a\u00030É\u0001H\u0016J\n\u0010¬\u0002\u001a\u00030É\u0001H\u0002J\n\u0010\u00ad\u0002\u001a\u00030É\u0001H\u0016J\u0013\u0010®\u0002\u001a\u00030É\u00012\u0007\u0010¯\u0002\u001a\u00020hH\u0016J\u0014\u0010°\u0002\u001a\u00030É\u00012\b\u0010ò\u0001\u001a\u00030ó\u0001H\u0016J\n\u0010±\u0002\u001a\u00030É\u0001H\u0016J\n\u0010²\u0002\u001a\u00030É\u0001H\u0016J\u0014\u0010³\u0002\u001a\u00030É\u00012\b\u0010´\u0002\u001a\u00030µ\u0002H\u0016J\u0014\u0010¶\u0002\u001a\u00030É\u00012\b\u0010·\u0002\u001a\u00030¸\u0002H\u0002J&\u0010¹\u0002\u001a\u00030É\u00012\u0007\u0010î\u0001\u001a\u00020h2\u0007\u0010ï\u0001\u001a\u00020h2\b\u0010º\u0002\u001a\u00030»\u0002H\u0016J\u0014\u0010¼\u0002\u001a\u00030É\u00012\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0016J\n\u0010½\u0002\u001a\u00030É\u0001H\u0014J\u0014\u0010¾\u0002\u001a\u00030É\u00012\b\u0010Í\u0001\u001a\u00030¡\u0001H\u0007J\u0013\u0010¿\u0002\u001a\u00030É\u00012\u0007\u0010À\u0002\u001a\u00020hH\u0016J\n\u0010Á\u0002\u001a\u00030É\u0001H\u0014J\n\u0010Â\u0002\u001a\u00030É\u0001H\u0016J\u0014\u0010Ã\u0002\u001a\u00030É\u00012\b\u0010Ä\u0002\u001a\u00030 \u0002H\u0014J\n\u0010Å\u0002\u001a\u00030É\u0001H\u0014J\n\u0010Æ\u0002\u001a\u00030É\u0001H\u0002J\n\u0010Ç\u0002\u001a\u00030É\u0001H\u0014J\u0010\u0010È\u0002\u001a\u00030É\u0001H\u0010¢\u0006\u0003\bÉ\u0002J\n\u0010Ê\u0002\u001a\u00030É\u0001H\u0016J\n\u0010Ë\u0002\u001a\u00030É\u0001H\u0016J\u000f\u0010Ì\u0002\u001a\u00020pH\u0010¢\u0006\u0003\bÍ\u0002J\u0014\u0010Î\u0002\u001a\u00030É\u00012\b\u0010Ï\u0002\u001a\u00030Ð\u0002H\u0016J\n\u0010Ñ\u0002\u001a\u00030É\u0001H\u0016J\n\u0010Ò\u0002\u001a\u00030É\u0001H\u0002J\u0013\u0010Ó\u0002\u001a\u00030É\u00012\u0007\u0010Ô\u0002\u001a\u00020hH\u0002J\n\u0010Õ\u0002\u001a\u00030É\u0001H\u0007J\u0014\u0010Ö\u0002\u001a\u00030É\u00012\b\u0010×\u0002\u001a\u00030Ø\u0002H\u0002J;\u0010Ù\u0002\u001a\u00030É\u00012\u0007\u0010î\u0001\u001a\u00020h2\b\u0010Ï\u0002\u001a\u00030Ð\u00022\u0007\u0010ð\u0001\u001a\u00020p2\u0007\u0010ï\u0001\u001a\u00020h2\n\u0010º\u0002\u001a\u0005\u0018\u00010»\u0002H\u0016J\n\u0010Ú\u0002\u001a\u00030É\u0001H\u0016J\n\u0010Ñ\u0001\u001a\u00030É\u0001H\u0016J\u0016\u0010Û\u0002\u001a\u00030É\u00012\n\u0010\u009f\u0002\u001a\u0005\u0018\u00010 \u0002H\u0002J\n\u0010Ü\u0002\u001a\u00030É\u0001H\u0016J\n\u0010Ý\u0002\u001a\u00030É\u0001H\u0016J\n\u0010Þ\u0002\u001a\u00030É\u0001H\u0016J\n\u0010ß\u0002\u001a\u00030É\u0001H\u0016J\n\u0010à\u0002\u001a\u00030É\u0001H\u0002J\n\u0010á\u0002\u001a\u00030É\u0001H\u0002J\u0014\u0010â\u0002\u001a\u00030É\u00012\b\u0010\u0085\u0002\u001a\u00030Ë\u0001H\u0002J\n\u0010ã\u0002\u001a\u00030É\u0001H\u0002J\u0013\u0010ä\u0002\u001a\u00030É\u00012\u0007\u0010å\u0002\u001a\u00020pH\u0016J\u0013\u0010æ\u0002\u001a\u00030É\u00012\u0007\u0010ç\u0002\u001a\u00020pH\u0002J\n\u0010è\u0002\u001a\u00030É\u0001H\u0016J\n\u0010é\u0002\u001a\u00030É\u0001H\u0016J\u0014\u0010ê\u0002\u001a\u00030É\u00012\b\u0010ë\u0002\u001a\u00030¡\u0001H\u0002J\u0014\u0010ì\u0002\u001a\u00030É\u00012\b\u0010í\u0002\u001a\u00030î\u0002H\u0016J\u0014\u0010ï\u0002\u001a\u00030É\u00012\b\u0010ð\u0002\u001a\u00030ñ\u0002H\u0016J\u0014\u0010ò\u0002\u001a\u00030É\u00012\b\u0010ó\u0002\u001a\u00030Ë\u0001H\u0002J\n\u0010ô\u0002\u001a\u00030É\u0001H\u0016J\u0016\u0010õ\u0002\u001a\u00030É\u00012\n\u0010ö\u0002\u001a\u0005\u0018\u00010÷\u0002H\u0016J(\u0010ø\u0002\u001a\u00030É\u00012\t\b\u0001\u0010ù\u0002\u001a\u00020\u00132\u000b\b\u0003\u0010ú\u0002\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0003\u0010û\u0002J\u0014\u0010ü\u0002\u001a\u00030É\u00012\b\u0010ó\u0002\u001a\u00030Ë\u0001H\u0002J\u0013\u0010ý\u0002\u001a\u00030É\u00012\u0007\u0010å\u0002\u001a\u00020pH\u0016J\n\u0010þ\u0002\u001a\u00030É\u0001H\u0016J\n\u0010ÿ\u0002\u001a\u00030É\u0001H\u0016J\u001c\u0010\u0080\u0003\u001a\u00030É\u00012\u0006\u0010,\u001a\u00020-2\b\u0010P\u001a\u0004\u0018\u00010QH\u0002J\n\u0010\u0081\u0003\u001a\u00030É\u0001H\u0002J\n\u0010\u0082\u0003\u001a\u00030É\u0001H\u0016J\t\u0010\u0083\u0003\u001a\u00020pH\u0002J\n\u0010\u0084\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u0085\u0003\u001a\u00030É\u0001H\u0016J\u0014\u0010\u0086\u0003\u001a\u00030É\u00012\b\u0010\u0087\u0003\u001a\u00030\u0088\u0003H\u0016J\n\u0010\u0089\u0003\u001a\u00030É\u0001H\u0016J\u0014\u0010\u008a\u0003\u001a\u00030É\u00012\b\u0010\u0085\u0002\u001a\u00030Ë\u0001H\u0002J\u0014\u0010\u008b\u0003\u001a\u00030É\u00012\b\u0010\u008c\u0003\u001a\u00030\u008d\u0003H\u0016JJ\u0010\u008e\u0003\u001a\u00030É\u00012\t\b\u0001\u0010\u008f\u0003\u001a\u00020\u00132\t\b\u0001\u0010\u0090\u0003\u001a\u00020\u00132(\u0010\u0091\u0003\u001a#\u0012\u0017\u0012\u00150Ø\u0002¢\u0006\u000f\b\u0093\u0003\u0012\n\b\u0094\u0003\u0012\u0005\b\b(×\u0002\u0012\u0005\u0012\u00030É\u00010\u0092\u0003H\u0016J\n\u0010\u0095\u0003\u001a\u00030É\u0001H\u0002J\u001d\u0010\u0096\u0003\u001a\u00030É\u00012\u0007\u0010\u0097\u0003\u001a\u00020h2\b\u0010\u0098\u0003\u001a\u00030\u0099\u0003H\u0016J\n\u0010\u009a\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u009b\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u009c\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u009d\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u009e\u0003\u001a\u00030É\u0001H\u0016J\n\u0010\u009f\u0003\u001a\u00030É\u0001H\u0016J\n\u0010 \u0003\u001a\u00030É\u0001H\u0016J\u0013\u0010¡\u0003\u001a\u00030É\u00012\u0007\u0010¢\u0003\u001a\u00020pH\u0016J\u001d\u0010£\u0003\u001a\u00030É\u00012\u0011\b\u0002\u0010¤\u0003\u001a\n\u0012\u0005\u0012\u00030É\u00010¥\u0003H\u0002J\n\u0010¦\u0003\u001a\u00030É\u0001H\u0016J\n\u0010§\u0003\u001a\u00030É\u0001H\u0002J\u0014\u0010¨\u0003\u001a\u00030É\u00012\b\u0010©\u0003\u001a\u00030ª\u0003H\u0016J6\u0010«\u0003\u001a\u00030É\u00012\t\b\u0001\u0010¬\u0003\u001a\u00020\u00132\t\b\u0001\u0010\u00ad\u0003\u001a\u00020\u00132\t\b\u0001\u0010®\u0003\u001a\u00020\u00132\t\b\u0001\u0010¯\u0003\u001a\u00020\u0013H\u0002J\n\u0010°\u0003\u001a\u00030É\u0001H\u0002J\n\u0010±\u0003\u001a\u00030É\u0001H\u0002J\u001c\u0010²\u0003\u001a\u00030É\u00012\u0006\u0010,\u001a\u00020-2\b\u0010P\u001a\u0004\u0018\u00010QH\u0002J\u0014\u0010³\u0003\u001a\u00030É\u00012\b\u0010´\u0003\u001a\u00030µ\u0003H\u0002J\u0014\u0010¶\u0003\u001a\u00030É\u00012\b\u0010´\u0003\u001a\u00030µ\u0003H\u0016J\u0014\u0010·\u0003\u001a\u00030É\u00012\b\u0010´\u0003\u001a\u00030µ\u0003H\u0002J8\u0010¸\u0003\u001a\u00030É\u00012\u0007\u0010î\u0001\u001a\u00020h2\u0007\u0010¹\u0003\u001a\u00020h2\u0006\u0010B\u001a\u00020C2\u0007\u0010º\u0003\u001a\u00020Q2\t\u0010»\u0003\u001a\u0004\u0018\u000103H\u0016J!\u0010¼\u0003\u001a\u00020p*\u0004\u0018\u00010Q2\u0007\u0010½\u0003\u001a\u00020Q2\u0007\u0010¾\u0003\u001a\u000203H\u0002J\u0018\u0010¿\u0003\u001a\u00020p*\u00030¨\u00022\b\u0010À\u0003\u001a\u00030\u0084\u0002H\u0002R\u001e\u0010\b\u001a\u00020\t8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u0019X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\u0004\u0018\u00010)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082.¢\u0006\u0002\n\u0000R\u0016\u00102\u001a\u0004\u0018\u0001038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u001e\u00106\u001a\u0002078\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020=8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020C8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020G8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u001c\u0010J\u001a\u0004\u0018\u00010KX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0016\u0010P\u001a\u0004\u0018\u00010Q8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020UX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010W\u001a\u00020X8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001b\u0010]\u001a\u00020^8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\b_\u0010`R\u0014\u0010c\u001a\u00020d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u0016\u0010g\u001a\u0004\u0018\u00010h8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u000e\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020pX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010q\u001a\u00020r8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u000e\u0010w\u001a\u00020pX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010x\u001a\u00020p8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR\u001e\u0010{\u001a\u00020p2\u0006\u0010z\u001a\u00020p@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b{\u0010yR\u000e\u0010|\u001a\u00020}X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010~\u001a\u0004\u0018\u00010\u007fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0082.¢\u0006\u0002\n\u0000R$\u0010\u0082\u0001\u001a\u00030\u0083\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0010\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0082.¢\u0006\u0002\n\u0000R$\u0010\u008a\u0001\u001a\u00030\u008b\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001\"\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0010\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082.¢\u0006\u0002\n\u0000R$\u0010\u0094\u0001\u001a\u00030\u0095\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R$\u0010\u009a\u0001\u001a\u00030\u009b\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0012\u0010 \u0001\u001a\u0005\u0018\u00010¡\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010¤\u0001\u001a\u00030¥\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R$\u0010ª\u0001\u001a\u00030«\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b¬\u0001\u0010\u00ad\u0001\"\u0006\b®\u0001\u0010¯\u0001R\u0018\u0010°\u0001\u001a\u00030±\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R$\u0010´\u0001\u001a\u00030µ\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\b¶\u0001\u0010·\u0001\"\u0006\b¸\u0001\u0010¹\u0001R \u0010º\u0001\u001a\u00030»\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¾\u0001\u0010b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0010\u0010¿\u0001\u001a\u00030À\u0001X\u0082.¢\u0006\u0002\n\u0000R$\u0010Á\u0001\u001a\u00030Â\u00018\u0000@\u0000X\u0081.¢\u0006\u0012\n\u0000\u001a\u0006\bÃ\u0001\u0010Ä\u0001\"\u0006\bÅ\u0001\u0010Æ\u0001R\u000f\u0010Ç\u0001\u001a\u00020pX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Â\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureActivity;", "Lcom/onfido/android/sdk/capture/ui/BaseActivity;", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$View;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView$ChallengesListener;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$Listener;", "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragmentContainer;", "()V", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "getApplicantId", "()Lcom/onfido/api/client/token/sdk/ApplicantId;", "backgroundColorCaptureScreen", "", "getBackgroundColorCaptureScreen", "()I", "backgroundColorConfirmationScreen", "getBackgroundColorConfirmationScreen", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityCaptureBinding;", "getBinding$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityCaptureBinding;", "setBinding$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityCaptureBinding;)V", "cameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "getCameraFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "setCameraFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;)V", "captureActivityLayoutAdjuster", "Lcom/onfido/android/sdk/capture/ui/camera/util/CaptureActivityLayoutAdjuster;", "captureButton", "Landroid/view/View;", "captureConfirmationScreen", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureConfirmationScreen;", "getCaptureConfirmationScreen", "()Lcom/onfido/android/sdk/capture/ui/camera/CaptureConfirmationScreen;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getCaptureType", "()Lcom/onfido/android/sdk/capture/ui/CaptureType;", "captureUploadService", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "cryptography", "Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;", "getCryptography$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;", "setCryptography$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;)V", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "getDispatchersProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "setDispatchersProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;)V", "docSide", "Lcom/onfido/api/client/data/DocSide;", "getDocSide", "()Lcom/onfido/api/client/data/DocSide;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getDocumentData", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "setDocumentFormat", "(Lcom/onfido/android/sdk/capture/DocumentFormat;)V", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "documentTypeUIModel", "Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "dummyView", "environmentIntegrityChecker", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "getEnvironmentIntegrityChecker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "setEnvironmentIntegrityChecker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;)V", "frameSubscription", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getFrameSubscription", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "frameSubscription$delegate", "Lkotlin/Lazy;", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "genericDocTitle", "", "getGenericDocTitle", "()Ljava/lang/String;", "glareBubbleRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "hasOngoingCaptureRequest", "", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "isCameraViewInitialised", "isDocumentFrontSide", "()Z", "<set-?>", "isOnConfirmationStep", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "livenessChallengesLoadingView", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView;", "livenessControlButton", "Lcom/onfido/android/sdk/capture/ui/widget/OnfidoButton;", "onfidoApiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "getOnfidoApiService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "setOnfidoApiService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;)V", "onfidoCamera", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "getOnfidoRemoteConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "setOnfidoRemoteConfig$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "payloadHelper", "Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "getPayloadHelper$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "setPayloadHelper$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;)V", "presenter", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;)V", "previewImage", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "recorder", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "getSchedulersProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "setSchedulersProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "screenLoadTracker", "Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "getScreenLoadTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "setScreenLoadTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;)V", "screenOrientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "getScreenOrientation", "()Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "getTokenProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/api/client/token/TokenProvider;", "setTokenProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/api/client/token/TokenProvider;)V", "userExitFlowMenuProvider", "Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "getUserExitFlowMenuProvider", "()Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "userExitFlowMenuProvider$delegate", "validationBubbleOffsetDelegate", "Lcom/onfido/android/sdk/capture/ui/camera/util/ValidationBubblesOffsetDelegate;", "vibratorService", "Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "getVibratorService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "setVibratorService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/VibratorService;)V", "wasConfirmationNotShown", "adjustDummyAccessibilityView", "", "visibleCaptureRect", "Landroid/graphics/RectF;", "applyValidations", MimeTypes.BASE_TYPE_IMAGE, "breakIfDocTypeMissing", "cancelFlow", "capture", "playSingleFrameAutoCapturedAnimation", "captureStepDataBundleForDoc", "changeBackArrowColor", "color", "(I)Lkotlin/Unit;", "changeStatusBarColor", "closeConfirmationScreen", "createDocumentDetectionFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "outerLimits", ViewProps.ROTATION, "deactivateCaptureButton", "destroyWithCanceledResult", "displayCaptureButton", "enableTorch", "isEnabled", "finishActivityWithException", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithResultExitUserFlow", "getCameraFace", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "getCaptureButtonContentDescription", "getCaptureStepDataBundle", "getCapturedFilesDir", "Ljava/io/File;", "getDocumentResultIntent", "Landroid/content/Intent;", "documentId", "documentVideoId", "nfcSupported", "getLivePhotoUploadResultIntent", "photoUpload", "Lcom/onfido/api/client/data/LivePhotoUpload;", "getPreviewImage", "getSdkUploadMetadata", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "hasOnfidoConfig", "hasValidRecording", "hideCaptureButton", "hideDocumentOverlay", "hideLivenessControlButton", "hideLoading", "hideVideoRecordingProgressBar", "inflateCaptureButton", "inflateDummyAccessibilityView", "inflateOverlayView", "initDocumentFormat", "initDocumentTypeUIModel", "limitRect", "Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "rect", "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "makeToolbarTitleNotImportantForAccessibility", "onAccessibleCaptureDocumentOverlayTextChanged", "mainTextResId", "mainTextContentDescriptionResId", "onAccessibleCaptureRectangleDetectionResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "onBackPressed", "onCameraNotFound", "onCameraStarted", "onCameraUnavailable", "onCaptureForProofOfAddressDone", "visibleRect", "onCardFormatSelected", "onChallengeLoadingViewStateChanged", "screenState", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$ScreenState;", "onChallengesAvailable", "livenessChallengesViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "onChallengesCompleted", "onChallengesErrorBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDocumentCreated", "onDocumentVideoRecordingCompleted", "onDocumentVideoUploaded", "onErrorObservingHeadTurnResults", "onFaceDetected", "faceDetectionResult", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "onFaceDetectionTimeout", "onFaceOutTimeout", "onFaceTrackingTimeout", "onFoldedFormatSelected", "onImageProcessingFinished", "onInvalidCertificateDetected", "message", "onLivePhotoUploaded", "onLivenessChallengeFinished", "onManualFallbackDelayFinished", "onNextChallenge", "livenessChallengeViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "onNextFrame", "frame", "", "onNfcPropertiesFetched", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "onOverlayMetrics", "onPause", "onPictureCaptured", "onPoaImageCroppedAndSavedToFile", ReactNativeBridgeUtiles.KEY_FILE_NAME, "onResume", "onRetakeSelfieButtonClick", "onSaveInstanceState", "outState", "onStart", "onStartLiveness", "onStop", "onStopDuringExitWhenSentToBackgroundMode", "onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release", "onStorageThresholdReached", "onTokenExpired", "onToolbarBackEvent", "onToolbarBackEvent$onfido_capture_sdk_core_release", "onUploadError", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onUploadSelfieButtonClick", "onVideoCanceled", "onVideoCaptured", "filePath", "onVideoTimeoutExceeded", "onVideoTimeoutRetryClick", "dialog", "Landroid/content/DialogInterface;", "onWarningBinaryResult", "openCaptureScreen", "recoverStateFrom", "recreate", "removeDummyViewsAccessibility", "resetDocumentRecordingState", "restart", "runDocAutoCaptureAccessiblityEvents", "setCaptureFrameContentDescriptionAndTitle", "setCaptureRegion", "setColorsForCaptureScreen", "setConfirmationButtons", "isGenericMessage", "setConfirmationStepVisibility", ViewProps.VISIBLE, "setForceRetryButton", "setGlareWarningContent", "setImagePreview", "onfidoImage", "setLiveValidationBubbleContent", "content", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "setLiveValidationBubbleVisibilityCommand", "command", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "setLivenessOverlayMargin", "captureRect", "setOverlay", "setSupportActionBar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "setValidationBubbleContent", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "setVideoRecordingIndicatorMargin", "setWarningConfirmationButtons", "setupCaptureButton", "setupConfirmationButtons", "setupOverlayView", "setupPresenter", "setupUploadService", "shouldShowPassportOverlay", "showConfirmationPreview", "showConfirmationStep", "showDialog", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showDocumentFormatDialog", "showDocumentOverlay", "showError", "descriptor", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "showErrorMessage", "titleResId", "messageResId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "showFaceConfirmationFragment", "showFaceLivenessConfirmationScreen", "dirPath", "performedChallanges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showLivenessButtonAndFocusWithDelay", "showVideoRecordCompletionTick", "showVideoRecordingCompleteMessage", "showVideoRecordingInProgressMessage", "showVideoRecordingProgressBar", "startCamera", "startDocumentVideoRecording", "startLivenessVideoRecording", "isStartedManually", "startVideoRecording", "videoRecordingStarted", "Lkotlin/Function0;", "stopCamera", "trackAutocaptureShutterButtonClick", "trackNavigationCompleted", FirebaseAnalytics.Param.DESTINATION, "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "updateColors", "toolbarBackgroundColor", "toolbarTitleColor", "toolbarSubtitleColor", "screenBackgroundColor", "updateColorsForConfirmationScreen", "updateConfirmationImageTranslationAndScale", "updateOverlayView", "uploadDocument", "jpegData", "", "uploadImage", "uploadSelfieForValidation", "uploadVideo", "videoPath", "docType", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "isFolded", "type", MediaCallbackResultReceiver.KEY_COUNTRY, "isInsideOval", "ovalFrame", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CaptureActivity extends BaseActivity implements OverlayView.Listener, CapturePresenter.View, LivenessOverlayView.ChallengesListener, LivenessChallengesLoadingView.Listener, FaceConfirmationFragmentContainer {
    public static final String CAPTURE_TYPE_PARAM = "type";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DOC_COUNTRY = "doc_country";
    private static final String DOC_CUSTOM_TITLE = "doc_title";
    private static final String DOC_FORMAT = "doc_format";
    private static final String DOC_PAGES = "doc_pages";
    public static final String DOC_TYPE = "doc_type";
    private static final String DOC_VIDEO_ID = "doc_video_id";
    private static final float FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE = 0.3f;
    private static final String FRAGMENT_TAG_CONFIRMATION = "confirmation_fragment";
    private static final long GLARE_BUBBLE_FINAL_ANIMATION_DELAY_MS = 300;
    private static final String IS_FRONT_SIDE = "is_front_side";
    private static final boolean IS_FRONT_SIDE_DEFAULT = true;
    private static final String IS_PROOF_OF_ADDRESS = "is_proof_of_address";
    private static final String IS_RECREATING_KEY = "IS_RECREATING";
    private static final String KEY_STATE = "KEY_STATE";
    private static final long LIVENESS_CHALLENGE_FINISHED_DELAY_MS = 500;
    private static final String NFC_SUPPORTED = "nfc_supported";
    public static final String POA_CAPTURED_FILE_NAME = "poa_captured_file_name";
    private static final long RECITE_LIVENESS_BUTTON_DELAY_MS = 5000;
    public static final int RESULT_EXIT_USER_FLOW = 448;
    private static final String UPLOAD_ARTIFACT = "upload_artifact";
    private static final String UPLOAD_ID = "upload_id";
    public static final String VIDEO_PATH = "video_path";

    @Inject
    public AnnouncementService announcementService;
    public OnfidoActivityCaptureBinding binding;

    @Inject
    public CameraFactory cameraFactory;
    private CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster;
    private View captureButton;
    private CaptureUploadService captureUploadService;

    @Inject
    public Cryptography cryptography;

    @Inject
    public DispatchersProvider dispatchersProvider;
    private DocumentFormat documentFormat;
    private DocumentTypeUIModel documentTypeUIModel;
    private View dummyView;

    @Inject
    public EnvironmentIntegrityChecker environmentIntegrityChecker;
    private boolean hasOngoingCaptureRequest;

    @Inject
    public ImageUtils imageUtils;
    private boolean isCameraViewInitialised;
    private boolean isOnConfirmationStep;
    private LivenessChallengesLoadingView livenessChallengesLoadingView;
    private OnfidoButton livenessControlButton;

    @Inject
    public OnfidoApiService onfidoApiService;
    private OnfidoCamera onfidoCamera;

    @Inject
    public OnfidoRemoteConfig onfidoRemoteConfig;
    private OverlayMetrics overlayMetrics;
    private OverlayView overlayView;

    @Inject
    public PayloadHelper payloadHelper;

    @Inject
    public CapturePresenter presenter;
    private OnfidoImage previewImage;
    private OnfidoCamera.VideoRecorder recorder;

    @Inject
    public SchedulersProvider schedulersProvider;

    @Inject
    public ScreenLoadTracker screenLoadTracker;

    @Inject
    public TokenProvider tokenProvider;
    private ValidationBubblesOffsetDelegate validationBubbleOffsetDelegate;

    @Inject
    public VibratorService vibratorService;
    private final LifecycleAwareDialog lifecycleAwareDialog = new LifecycleAwareDialog((AppCompatActivity) this, (Function1) null, 2, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    private final Handler handler = new Handler();
    private final Runnable glareBubbleRunnable = new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            CaptureActivity.glareBubbleRunnable$lambda$0(this.f$0);
        }
    };
    private boolean wasConfirmationNotShown = true;

    /* renamed from: frameSubscription$delegate, reason: from kotlin metadata */
    private final Lazy frameSubscription = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$frameSubscription$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CompositeDisposable invoke() {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            LifecycleDisposableKt.disposeOnDestroy(compositeDisposable, this.this$0);
            return compositeDisposable;
        }
    });

    /* renamed from: userExitFlowMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy userExitFlowMenuProvider = LazyKt.lazy(new Function0<UserExitFlowMenuProvider>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$userExitFlowMenuProvider$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UserExitFlowMenuProvider invoke() {
            LifecycleAwareDialog lifecycleAwareDialog = this.this$0.lifecycleAwareDialog;
            int i = R.menu.onfido_capture_activity_workflow_toolbar_menu;
            final CaptureActivity captureActivity = this.this$0;
            Function0<Unit> function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$userExitFlowMenuProvider$2.1
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
                    captureActivity.getPresenter$onfido_capture_sdk_core_release().onFlowUserExit();
                }
            };
            final CaptureActivity captureActivity2 = this.this$0;
            Function1<DialogInterface, Unit> function1 = new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$userExitFlowMenuProvider$2.2
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
                    captureActivity2.getPresenter$onfido_capture_sdk_core_release().onFlowUserExitConfirmed();
                }
            };
            final CaptureActivity captureActivity3 = this.this$0;
            return new UserExitFlowMenuProvider(lifecycleAwareDialog, i, function0, function1, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$userExitFlowMenuProvider$2.3
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
                    captureActivity3.getPresenter$onfido_capture_sdk_core_release().onFlowUserExitCancel();
                }
            });
        }
    });

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002Jf\u0010%\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u00122\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00101\u001a\u0004\u0018\u000102J \u00103\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010#\u001a\u00020$J\u0016\u00104\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0017\u00105\u001a\u0004\u0018\u00010(2\u0006\u00106\u001a\u00020 H\u0000¢\u0006\u0002\b7J\u0017\u00108\u001a\u0004\u0018\u00010*2\u0006\u00106\u001a\u00020 H\u0000¢\u0006\u0002\b9J\u0017\u0010:\u001a\u0004\u0018\u00010,2\u0006\u00106\u001a\u00020 H\u0000¢\u0006\u0002\b;J\u000e\u0010<\u001a\u00020\u00042\u0006\u00106\u001a\u00020 J\u000e\u0010=\u001a\u0002022\u0006\u00106\u001a\u00020 J\u0010\u0010>\u001a\u0004\u0018\u00010\u00042\u0006\u00106\u001a\u00020 J\u000e\u0010?\u001a\u00020\u00122\u0006\u00106\u001a\u00020 J\u0010\u0010@\u001a\u0004\u0018\u00010A2\u0006\u00106\u001a\u00020 J\u000e\u0010B\u001a\u00020\u00042\u0006\u00106\u001a\u00020 J\u0015\u0010/\u001a\u00020\u00122\u0006\u00106\u001a\u00020 H\u0000¢\u0006\u0002\bCR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureActivity$Companion;", "", "()V", "CAPTURE_TYPE_PARAM", "", "DOC_COUNTRY", "DOC_CUSTOM_TITLE", "DOC_FORMAT", "DOC_PAGES", "DOC_TYPE", "DOC_VIDEO_ID", "FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE", "", "FRAGMENT_TAG_CONFIRMATION", "GLARE_BUBBLE_FINAL_ANIMATION_DELAY_MS", "", "IS_FRONT_SIDE", "IS_FRONT_SIDE_DEFAULT", "", "IS_PROOF_OF_ADDRESS", "IS_RECREATING_KEY", CaptureActivity.KEY_STATE, "LIVENESS_CHALLENGE_FINISHED_DELAY_MS", "NFC_SUPPORTED", "POA_CAPTURED_FILE_NAME", "RECITE_LIVENESS_BUTTON_DELAY_MS", "RESULT_EXIT_USER_FLOW", "", "UPLOAD_ARTIFACT", "UPLOAD_ID", "VIDEO_PATH", "createBaseIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "createDocumentIntent", "isFrontSide", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentCountry", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "isProofOfAddress", "genericDocTitle", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "createFaceIntent", "createLivenessIntent", "getDocTypeFrom", "intent", "getDocTypeFrom$onfido_capture_sdk_core_release", "getDocumentCountryFrom", "getDocumentCountryFrom$onfido_capture_sdk_core_release", "getDocumentFormat", "getDocumentFormat$onfido_capture_sdk_core_release", "getDocumentVideoId", "getGenericDocumentPageSpecification", "getGenericDocumentTitle", "getMediaSupportedNFC", "getUploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "getUploadedFileId", "isProofOfAddress$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        private final Intent createBaseIntent(Context context, OnfidoConfig onfidoConfig) {
            Intent intentPutExtra = new Intent(context, (Class<?>) CaptureActivity.class).putExtra(OnfidoConstants.ONFIDO_CONFIG, onfidoConfig);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }

        public final Intent createDocumentIntent(Context context, OnfidoConfig onfidoConfig, boolean isFrontSide, DocumentType documentType, CountryCode documentCountry, DocumentFormat documentFormat, NfcArguments nfcArguments, boolean isProofOfAddress, String genericDocTitle, DocumentPages genericDocPages) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
            IntentHelper intentHelper = IntentHelper.INSTANCE;
            Intent intentPutExtra = createBaseIntent(context, onfidoConfig).putExtra("type", CaptureType.DOCUMENT).putExtra(CaptureActivity.IS_FRONT_SIDE, isFrontSide).putExtra("doc_type", documentType).putExtra(CaptureActivity.DOC_COUNTRY, documentCountry).putExtra(CaptureActivity.DOC_FORMAT, documentFormat).putExtra(CaptureActivity.IS_PROOF_OF_ADDRESS, isProofOfAddress);
            if (genericDocTitle != null) {
                intentPutExtra.putExtra(CaptureActivity.DOC_CUSTOM_TITLE, genericDocTitle);
            }
            if (genericDocPages != null) {
                intentPutExtra.putExtra(CaptureActivity.DOC_PAGES, genericDocPages);
            }
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "apply(...)");
            return intentHelper.putNfcArguments$onfido_capture_sdk_core_release(intentPutExtra, nfcArguments);
        }

        public final Intent createFaceIntent(Context context, DocumentType documentType, OnfidoConfig onfidoConfig) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            Intent intentPutExtra = createBaseIntent(context, onfidoConfig).putExtra("type", CaptureType.FACE).putExtra("doc_type", documentType);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }

        public final Intent createLivenessIntent(Context context, OnfidoConfig onfidoConfig) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            Intent intentPutExtra = createBaseIntent(context, onfidoConfig).putExtra("type", CaptureType.VIDEO);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }

        public final DocumentType getDocTypeFrom$onfido_capture_sdk_core_release(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Serializable serializableExtra = intent.getSerializableExtra("doc_type");
            if (serializableExtra instanceof DocumentType) {
                return (DocumentType) serializableExtra;
            }
            return null;
        }

        public final CountryCode getDocumentCountryFrom$onfido_capture_sdk_core_release(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Serializable serializableExtra = intent.getSerializableExtra(CaptureActivity.DOC_COUNTRY);
            if (serializableExtra instanceof CountryCode) {
                return (CountryCode) serializableExtra;
            }
            return null;
        }

        public final DocumentFormat getDocumentFormat$onfido_capture_sdk_core_release(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Serializable serializableExtra = intent.getSerializableExtra(CaptureActivity.DOC_FORMAT);
            if (serializableExtra instanceof DocumentFormat) {
                return (DocumentFormat) serializableExtra;
            }
            return null;
        }

        public final String getDocumentVideoId(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String stringExtra = intent.getStringExtra(CaptureActivity.DOC_VIDEO_ID);
            return stringExtra == null ? "" : stringExtra;
        }

        public final DocumentPages getGenericDocumentPageSpecification(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Serializable serializableExtra = intent.getSerializableExtra(CaptureActivity.DOC_PAGES);
            DocumentPages documentPages = serializableExtra instanceof DocumentPages ? (DocumentPages) serializableExtra : null;
            return documentPages == null ? DocumentPages.FRONT_AND_BACK : documentPages;
        }

        public final String getGenericDocumentTitle(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return intent.getStringExtra(CaptureActivity.DOC_CUSTOM_TITLE);
        }

        public final boolean getMediaSupportedNFC(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return intent.getBooleanExtra(CaptureActivity.NFC_SUPPORTED, false);
        }

        public final UploadedArtifact getUploadedArtifact(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return (UploadedArtifact) intent.getParcelableExtra(CaptureActivity.UPLOAD_ARTIFACT);
        }

        public final String getUploadedFileId(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String stringExtra = intent.getStringExtra(CaptureActivity.UPLOAD_ID);
            Intrinsics.checkNotNull(stringExtra);
            return stringExtra;
        }

        public final boolean isProofOfAddress$onfido_capture_sdk_core_release(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return intent.getBooleanExtra(CaptureActivity.IS_PROOF_OF_ADDRESS, false);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CaptureType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocumentType.values().length];
            try {
                iArr2[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CaptureActivity$uploadDocument$1", f = "CaptureActivity.kt", i = {}, l = {1577}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$uploadDocument$1, reason: invalid class name and case insensitive filesystem */
    static final class C06011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ byte[] $jpegData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06011(byte[] bArr, Continuation<? super C06011> continuation) {
            super(2, continuation);
            this.$jpegData = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CaptureActivity.this.new C06011(this.$jpegData, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcherMo5607getDefault = CaptureActivity.this.getDispatchersProvider$onfido_capture_sdk_core_release().mo5607getDefault();
                CaptureActivity$uploadDocument$1$croppedJpegData$1 captureActivity$uploadDocument$1$croppedJpegData$1 = new CaptureActivity$uploadDocument$1$croppedJpegData$1(CaptureActivity.this, this.$jpegData, null);
                this.label = 1;
                obj = BuildersKt.withContext(coroutineDispatcherMo5607getDefault, captureActivity$uploadDocument$1$croppedJpegData$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            CaptureActivity.this.getPresenter$onfido_capture_sdk_core_release().uploadImageForValidation$onfido_capture_sdk_core_release((byte[]) obj);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    private final void adjustDummyAccessibilityView(RectF visibleCaptureRect) {
        Rect rect = new Rect();
        visibleCaptureRect.roundOut(rect);
        View view = this.dummyView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyView");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = rect.width();
        layoutParams2.height = rect.height();
        layoutParams2.leftMargin = rect.left;
        layoutParams2.topMargin = rect.top;
        layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.onfido_capture_instructions_outer_top_margin);
        view.setLayoutParams(layoutParams2);
    }

    private final void breakIfDocTypeMissing() {
        if (!getIntent().hasExtra("type")) {
            throw new IllegalArgumentException("CaptureActivity should be created through createFor factory method".toString());
        }
    }

    private final CaptureStepDataBundle captureStepDataBundleForDoc() {
        return new CaptureStepDataBundle(CaptureType.DOCUMENT, getDocumentType(), getCountryCode(), getDocumentFormat(), isDocumentFrontSide() ? DocSide.FRONT : DocSide.BACK, getGenericDocTitle(), getGenericDocPages());
    }

    private final Unit changeBackArrowColor(int color) {
        Drawable navigationIcon = getBinding$onfido_capture_sdk_core_release().toolbar.getNavigationIcon();
        if (navigationIcon == null) {
            return null;
        }
        navigationIcon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        return Unit.INSTANCE;
    }

    private final void changeStatusBarColor(int color) {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
    }

    private final void closeConfirmationScreen() {
        Object captureConfirmationScreen = getCaptureConfirmationScreen();
        Fragment fragment = captureConfirmationScreen instanceof Fragment ? (Fragment) captureConfirmationScreen : null;
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commitNow();
        }
        FragmentContainerView fragmentContainer = getBinding$onfido_capture_sdk_core_release().fragmentContainer;
        Intrinsics.checkNotNullExpressionValue(fragmentContainer, "fragmentContainer");
        ViewExtensionsKt.toGone$default(fragmentContainer, false, 1, null);
    }

    private final DocumentDetectionFrame createDocumentDetectionFrame(OnfidoImage image, RectF outerLimits, int rotation) {
        return new DocumentDetectionFrame(image.getData(), image.getWidth(), image.getHeight(), image.getWidth(), image.getHeight(), limitRect(outerLimits, image.getCropRect()), null, rotation, image.getCropRect(), image.getBitmap(), 64, null);
    }

    static /* synthetic */ DocumentDetectionFrame createDocumentDetectionFrame$default(CaptureActivity captureActivity, OnfidoImage onfidoImage, RectF rectF, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = captureActivity.getImageUtils$onfido_capture_sdk_core_release().getExifOrientationDegrees(Exif.exifOrientationIdentifier(onfidoImage.getData()));
        }
        return captureActivity.createDocumentDetectionFrame(onfidoImage, rectF, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishActivityWithException(Exception exception) {
        Intent intentPutExtra = new Intent().putExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT, exception);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        finishWithResult$onfido_capture_sdk_core_release(-2, intentPutExtra);
    }

    private final ApplicantId getApplicantId() {
        InternalToken internalTokenProvideToken = getTokenProvider$onfido_capture_sdk_core_release().provideToken();
        Intrinsics.checkNotNull(internalTokenProvideToken, "null cannot be cast to non-null type com.onfido.api.client.token.sdk.InternalSDKToken");
        return ((InternalSDKToken) internalTokenProvideToken).getApplicantId();
    }

    private final int getBackgroundColorCaptureScreen() {
        return ContextUtilsKt.color(this, R.color.onfido_camera_blur);
    }

    private final int getBackgroundColorConfirmationScreen() {
        return ContextUtilsKt.colorFromAttr(this, R.attr.onfidoColorBackground);
    }

    private final OnfidoCamera.CameraFacing getCameraFace() {
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            return OnfidoCamera.CameraFacing.BACK;
        }
        if (i == 2 || i == 3) {
            return OnfidoCamera.CameraFacing.FRONT;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final int getCaptureButtonContentDescription() {
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            return R.string.onfido_doc_capture_button_accessibility;
        }
        if (i == 2 || i == 3) {
            return R.string.onfido_selfie_capture_button_accessibility;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final CaptureConfirmationScreen getCaptureConfirmationScreen() {
        ActivityResultCaller activityResultCallerFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_CONFIRMATION);
        if (activityResultCallerFindFragmentByTag instanceof CaptureConfirmationScreen) {
            return (CaptureConfirmationScreen) activityResultCallerFindFragmentByTag;
        }
        return null;
    }

    private final CaptureStepDataBundle getDocumentData() {
        return new CaptureStepDataBundle(getCaptureType(), getDocumentType(), getCountryCode(), getDocumentFormat(), getDocSide(), getGenericDocTitle(), getGenericDocPages());
    }

    private final Intent getDocumentResultIntent(String documentId, String documentVideoId, boolean nfcSupported) {
        IntentHelper intentHelper = IntentHelper.INSTANCE;
        Intent intentPutExtra = new Intent().putExtra(UPLOAD_ID, documentId).putExtra("doc_type", getDocumentType()).putExtra(DOC_FORMAT, getDocumentFormat()).putExtra(IS_FRONT_SIDE, isDocumentFrontSide()).putExtra(DOC_COUNTRY, getCountryCode()).putExtra(NFC_SUPPORTED, nfcSupported).putExtra(DOC_VIDEO_ID, documentVideoId);
        if (getGenericDocTitle() != null) {
            intentPutExtra.putExtra(DOC_CUSTOM_TITLE, getGenericDocTitle());
        }
        intentPutExtra.putExtra(DOC_PAGES, getGenericDocPages());
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "apply(...)");
        return intentHelper.putCaptureStepDataBundle$onfido_capture_sdk_core_release(intentPutExtra, captureStepDataBundleForDoc());
    }

    private final CompositeDisposable getFrameSubscription() {
        return (CompositeDisposable) this.frameSubscription.getValue();
    }

    private final String getGenericDocTitle() {
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return companion.getGenericDocumentTitle(intent);
    }

    private final Intent getLivePhotoUploadResultIntent(LivePhotoUpload photoUpload) {
        Intent intentPutExtra = new Intent().putExtra(UPLOAD_ID, photoUpload.getId()).putExtra(UPLOAD_ARTIFACT, UploadedArtifactKt.toUploadedArtifact(photoUpload));
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return intentPutExtra;
    }

    private final SdkUploadMetaData getSdkUploadMetadata() {
        return getPresenter$onfido_capture_sdk_core_release().sdkUploadMetadata$onfido_capture_sdk_core_release(getDocumentData());
    }

    private final UserExitFlowMenuProvider getUserExitFlowMenuProvider() {
        return (UserExitFlowMenuProvider) this.userExitFlowMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void glareBubbleRunnable$lambda$0(CaptureActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding$onfido_capture_sdk_core_release().liveValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, true);
    }

    private final boolean hasOnfidoConfig() {
        return getIntent().hasExtra(OnfidoConstants.ONFIDO_CONFIG);
    }

    private final void inflateCaptureButton(CaptureType captureType) {
        View view = null;
        if (captureType.isPicture()) {
            View viewFindViewById = getLayoutInflater().inflate(R.layout.onfido_capture_button_picture, (ViewGroup) getBinding$onfido_capture_sdk_core_release().content, true).findViewById(R.id.captureButton);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.captureButton = viewFindViewById;
            if (viewFindViewById == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureButton");
                viewFindViewById = null;
            }
            ViewExtensionsKt.setContentDescription(viewFindViewById, getCaptureButtonContentDescription());
            CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = this.captureActivityLayoutAdjuster;
            if (captureActivityLayoutAdjuster == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
                captureActivityLayoutAdjuster = null;
            }
            View view2 = this.captureButton;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            } else {
                view = view2;
            }
            captureActivityLayoutAdjuster.setCaptureInstructionsAboveView(view);
            return;
        }
        View viewFindViewById2 = getLayoutInflater().inflate(R.layout.onfido_capture_button_video, (ViewGroup) getBinding$onfido_capture_sdk_core_release().content, true).findViewById(R.id.livenessControlButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.livenessControlButton = (OnfidoButton) viewFindViewById2;
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster2 = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
            captureActivityLayoutAdjuster2 = null;
        }
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        captureActivityLayoutAdjuster2.setCaptureInstructionsAboveView(onfidoButton);
        OnfidoButton onfidoButton2 = this.livenessControlButton;
        if (onfidoButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
        } else {
            view = onfidoButton2;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                CaptureActivity.inflateCaptureButton$lambda$8(this.f$0, view3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void inflateCaptureButton$lambda$8(CaptureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnfidoCamera.VideoRecorder videoRecorder = this$0.recorder;
        if (videoRecorder == null || !videoRecorder.isRecording()) {
            this$0.getPresenter$onfido_capture_sdk_core_release().onManualLivenessRecordingStart$onfido_capture_sdk_core_release();
        } else {
            this$0.getPresenter$onfido_capture_sdk_core_release().onManualLivenessNextClick$onfido_capture_sdk_core_release();
        }
    }

    private final void inflateDummyAccessibilityView() {
        View viewFindViewById = getLayoutInflater().inflate(R.layout.onfido_dummy_accessibility_view, (ViewGroup) getBinding$onfido_capture_sdk_core_release().content, true).findViewById(R.id.dummyAccessibility);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.dummyView = viewFindViewById;
        setCaptureFrameContentDescriptionAndTitle();
    }

    private final OverlayView inflateOverlayView(DocumentType documentType) {
        int i;
        View viewInflate;
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        if (companion.isProofOfAddress$onfido_capture_sdk_core_release(intent)) {
            viewInflate = getLayoutInflater().inflate(R.layout.onfido_view_overlay_poa_a4page, (ViewGroup) getBinding$onfido_capture_sdk_core_release().contentLayout, false);
        } else {
            int i2 = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    i = R.layout.onfido_view_overlay_face;
                } else {
                    if (i2 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i = R.layout.onfido_view_overlay_video;
                }
            } else if (CollectionsKt.contains(SetsKt.setOf((Object[]) new DocumentType[]{DocumentType.PASSPORT, DocumentType.VISA}), documentType)) {
                i = R.layout.onfido_view_overlay_passport;
            } else {
                DocumentType documentType2 = DocumentType.DRIVING_LICENCE;
                if (isFolded(documentType, documentType2, CountryCode.DE)) {
                    i = R.layout.onfido_view_overlay_german_dl;
                } else if (isFolded(documentType, documentType2, CountryCode.FR)) {
                    i = R.layout.onfido_view_overlay_french_dl;
                } else {
                    DocumentType documentType3 = DocumentType.NATIONAL_IDENTITY_CARD;
                    i = (isFolded(documentType, documentType3, CountryCode.IT) || isFolded(documentType, documentType3, CountryCode.ZA)) ? R.layout.onfido_view_overlay_italian_id : documentType == DocumentType.GENERIC ? R.layout.onfido_view_overlay_generic : R.layout.onfido_view_overlay_document;
                }
            }
            viewInflate = getLayoutInflater().inflate(i, (ViewGroup) getBinding$onfido_capture_sdk_core_release().contentLayout, false);
        }
        Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.OverlayView");
        return (OverlayView) viewInflate;
    }

    private final void initDocumentFormat() {
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        setDocumentFormat(companion.getDocumentFormat$onfido_capture_sdk_core_release(intent));
    }

    private final void initDocumentTypeUIModel() {
        DocumentUITextModelMapper documentUITextModelMapper = DocumentUITextModelMapper.INSTANCE;
        DocumentType documentType = getDocumentType();
        if (documentType == null) {
            documentType = DocumentType.GENERIC;
        }
        DocumentFormat documentFormat = getDocumentFormat();
        CountryCode countryCode = getCountryCode();
        boolean zIsEnabled = getAnnouncementService$onfido_capture_sdk_core_release().isEnabled();
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        this.documentTypeUIModel = documentUITextModelMapper.toDocumentUIModel(documentType, documentFormat, countryCode, zIsEnabled, companion.isProofOfAddress$onfido_capture_sdk_core_release(intent));
    }

    private final boolean isDocumentFrontSide() {
        IntentHelper intentHelper = IntentHelper.INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return intentHelper.getIsDocumentFrontSide(intent, true);
    }

    private final boolean isFolded(DocumentType documentType, DocumentType documentType2, CountryCode countryCode) {
        return DocumentFormat.FOLDED == getDocumentFormat() && documentType2 == documentType && countryCode == getCountryCode();
    }

    private final boolean isInsideOval(FaceDetectionResult faceDetectionResult, Frame frame) {
        if (faceDetectionResult instanceof FaceDetectionResult.FaceDetected) {
            float width = frame.getWidth() * FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE;
            float left = frame.getLeft() - width;
            float left2 = frame.getLeft() + frame.getWidth() + width;
            float top = frame.getTop() - width;
            float top2 = frame.getTop() + frame.getHeight() + width;
            FaceDetectionRect faceRect = ((FaceDetectionResult.FaceDetected) faceDetectionResult).getFaceRect();
            if (faceRect.getLeft() >= left && faceRect.getTop() >= top && faceRect.getTop() + faceRect.height$onfido_capture_sdk_core_release() <= top2 && faceRect.getLeft() + faceRect.width$onfido_capture_sdk_core_release() <= left2) {
                return true;
            }
        }
        return false;
    }

    private final Frame limitRect(RectF rect, OnfidoImage.CropRect cropRect) {
        float zoomFactor = cropRect.getZoomFactor();
        return new Frame((int) (rect.width() / zoomFactor), (int) (rect.height() / zoomFactor), (int) (cropRect.getHorizontalOffset() + (rect.left / zoomFactor)), (int) (cropRect.getVerticalOffset() + (rect.top / zoomFactor)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraNotFound() {
        getPresenter$onfido_capture_sdk_core_release().trackCaptureError$onfido_capture_sdk_core_release(getCaptureType());
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_unavailable, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onCameraNotFound.1
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
                CaptureActivity.this.finishActivityWithException(CameraNotFoundException.INSTANCE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraStarted() {
        getPresenter$onfido_capture_sdk_core_release().onCameraStarted$onfido_capture_sdk_core_release();
        getFrameSubscription().clear();
        CompositeDisposable frameSubscription = getFrameSubscription();
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        Disposable disposableSubscribe = onfidoCamera.observeFrame().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onCameraStarted.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                CaptureActivity.this.onNextFrame(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(frameSubscription, disposableSubscribe);
        if (this.isCameraViewInitialised && !getIsOnConfirmationStep() && this.onfidoCamera != null) {
            getPresenter$onfido_capture_sdk_core_release().onCaptureScreenResumedAfterCameraInitialized$onfido_capture_sdk_core_release(!this.wasConfirmationNotShown);
        }
        this.isCameraViewInitialised = true;
        if (getCaptureType() == CaptureType.VIDEO) {
            LivenessChallengesLoadingView livenessChallengesLoadingView = new LivenessChallengesLoadingView(this, null, 0, 6, null);
            livenessChallengesLoadingView.setListener(this);
            livenessChallengesLoadingView.fetchChallenges();
            this.livenessChallengesLoadingView = livenessChallengesLoadingView;
            getBinding$onfido_capture_sdk_core_release().content.addView(this.livenessChallengesLoadingView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraUnavailable() {
        getPresenter$onfido_capture_sdk_core_release().trackCaptureError$onfido_capture_sdk_core_release(getCaptureType());
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_used_by_other_app, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onCameraUnavailable.1
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
                CaptureActivity.this.finishActivityWithException(CameraNotAvailableException.INSTANCE);
            }
        });
    }

    private final void onCardFormatSelected() {
        setDocumentFormat(DocumentFormat.CARD);
        setCaptureFrameContentDescriptionAndTitle();
    }

    private final void onChallengesAvailable(LivenessChallengesViewModel livenessChallengesViewModel) {
        getBinding$onfido_capture_sdk_core_release().content.removeView(this.livenessChallengesLoadingView);
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
            captureActivityLayoutAdjuster = null;
        }
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        captureActivityLayoutAdjuster.setCaptureInstructionsAboveView(onfidoButton);
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        ViewExtensionsKt.toVisible$default(overlayView, false, 1, null);
        OverlayTextView overlayTextContainer = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        ViewExtensionsKt.toVisible$default(overlayTextContainer, false, 1, null);
        getPresenter$onfido_capture_sdk_core_release().startLivenessProcessing$onfido_capture_sdk_core_release(livenessChallengesViewModel);
        AnnouncementService announcementService$onfido_capture_sdk_core_release = getAnnouncementService$onfido_capture_sdk_core_release();
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(2048);
        Intrinsics.checkNotNullExpressionValue(accessibilityEventObtain, "obtain(...)");
        announcementService$onfido_capture_sdk_core_release.sendEvent(accessibilityEventObtain).blockingAwait();
    }

    private final void onFoldedFormatSelected() throws Resources.NotFoundException {
        setDocumentFormat(DocumentFormat.FOLDED);
        DocumentUITextModelMapper documentUITextModelMapper = DocumentUITextModelMapper.INSTANCE;
        DocumentType documentType = getDocumentType();
        Intrinsics.checkNotNull(documentType);
        this.documentTypeUIModel = documentUITextModelMapper.toDocumentUIModel(documentType, getDocumentFormat(), getCountryCode(), (8 & 4) != 0 ? false : getAnnouncementService$onfido_capture_sdk_core_release().isEnabled(), (8 & 8) != 0 ? false : false);
        updateOverlayView(getCaptureType(), getDocumentType());
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        setCaptureRegion(overlayMetrics.getVisibleCaptureRect());
        getPresenter$onfido_capture_sdk_core_release().startOverlayDisplayTimer$onfido_capture_sdk_core_release();
        setCaptureFrameContentDescriptionAndTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onManualFallbackDelayFinished$lambda$13$lambda$12(OnfidoCaptureValidationBubble this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.setState$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.State.SOFT_LOCK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNextFrame(Object frame) {
        Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.camera.OnfidoImage");
        OnfidoImage onfidoImage = (OnfidoImage) frame;
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i != 1) {
            if (i != 3) {
                return;
            }
            getPresenter$onfido_capture_sdk_core_release().onNextFaceDetectionFrame$onfido_capture_sdk_core_release(new FaceDetectionFrame(onfidoImage.getData(), onfidoImage.getWidth(), onfidoImage.getHeight(), onfidoImage.getRotation(), onfidoImage.getCropRect(), onfidoImage.getBitmap()));
            return;
        }
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        OverlayMetrics overlayMetrics2 = null;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        RectF realCaptureRect = overlayMetrics.getRealCaptureRect();
        OverlayMetrics overlayMetrics3 = this.overlayMetrics;
        if (overlayMetrics3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
        } else {
            overlayMetrics2 = overlayMetrics3;
        }
        getPresenter$onfido_capture_sdk_core_release().onNextFrame$onfido_capture_sdk_core_release(new DocumentDetectionFrame(onfidoImage.getData(), onfidoImage.getWidth(), onfidoImage.getHeight(), onfidoImage.getWidth(), onfidoImage.getHeight(), limitRect(realCaptureRect, onfidoImage.getCropRect()), limitRect(overlayMetrics2.getVisibleCaptureRect(), onfidoImage.getCropRect()), onfidoImage.getRotation(), onfidoImage.getCropRect(), onfidoImage.getBitmap()));
    }

    private final void onStartLiveness() {
        if (this.livenessChallengesLoadingView != null && getBinding$onfido_capture_sdk_core_release().content.indexOfChild(this.livenessChallengesLoadingView) < 0) {
            getBinding$onfido_capture_sdk_core_release().content.addView(this.livenessChallengesLoadingView);
        }
        OverlayTextView overlayTextContainer = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        ViewExtensionsKt.toGone$default(overlayTextContainer, false, 1, null);
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        ViewExtensionsKt.toGone$default(onfidoButton, false, 1, null);
        LivenessChallengesLoadingView livenessChallengesLoadingView = this.livenessChallengesLoadingView;
        if (livenessChallengesLoadingView != null) {
            livenessChallengesLoadingView.fetchChallenges();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoCanceled() {
        getPresenter$onfido_capture_sdk_core_release().onVideoRecordingCanceled$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoCaptured(final String filePath) {
        this.handler.post(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CaptureActivity.onVideoCaptured$lambda$23(this.f$0, filePath);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onVideoCaptured$lambda$23(CaptureActivity this$0, String filePath) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(filePath, "$filePath");
        this$0.getPresenter$onfido_capture_sdk_core_release().onVideoCaptured$onfido_capture_sdk_core_release(filePath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoTimeoutRetryClick(DialogInterface dialog) {
        dialog.dismiss();
        getPresenter$onfido_capture_sdk_core_release().trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
        recreate();
    }

    private final void recoverStateFrom(Bundle savedInstanceState) {
        CapturePresenter.State state = savedInstanceState != null ? (CapturePresenter.State) savedInstanceState.getParcelable(KEY_STATE) : null;
        if (state != null) {
            getPresenter$onfido_capture_sdk_core_release().setState$onfido_capture_sdk_core_release(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runDocAutoCaptureAccessiblityEvents() {
        Completable completableVibrateForSuccess = getVibratorService$onfido_capture_sdk_core_release().vibrateForSuccess();
        Completable completableInterruptAnnouncement = getAnnouncementService$onfido_capture_sdk_core_release().interruptAnnouncement();
        AnnouncementService announcementService$onfido_capture_sdk_core_release = getAnnouncementService$onfido_capture_sdk_core_release();
        String string = getString(R.string.onfido_doc_capture_frame_success_accessibility);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Completable.mergeArray(completableVibrateForSuccess, completableInterruptAnnouncement, AnnouncementService.announceString$default(announcementService$onfido_capture_sdk_core_release, new String[]{string}, false, 2, (Object) null)).blockingAwait();
    }

    private final void setCaptureFrameContentDescriptionAndTitle() {
        StringRepresentation ovalCaptureContentDescription$onfido_capture_sdk_core_release = getPresenter$onfido_capture_sdk_core_release().getOvalCaptureContentDescription$onfido_capture_sdk_core_release(getDocumentData());
        View view = this.dummyView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyView");
            view = null;
        }
        view.setContentDescription(ovalCaptureContentDescription$onfido_capture_sdk_core_release.getString(this));
        setTitle(ovalCaptureContentDescription$onfido_capture_sdk_core_release.getString(this));
    }

    private final void setCaptureRegion(RectF rect) {
        if (getCaptureType() == CaptureType.DOCUMENT) {
            showDocumentOverlay(rect);
        }
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        validationBubblesOffsetDelegate.onCaptureRegionSet(rect);
    }

    private final void setColorsForCaptureScreen() throws Resources.NotFoundException {
        updateColors(R.attr.onfidoColorToolbarBackgroundDark, R.attr.onfidoColorContentToolbarTitleDark, R.attr.onfidoColorContentToolbarSubtitleDark, getBackgroundColorCaptureScreen());
    }

    private final void setConfirmationStepVisibility(boolean visible) {
        ViewUtil.setViewVisibility(getBinding$onfido_capture_sdk_core_release().confirmationImage, visible);
        ViewUtil.setViewVisibility(getBinding$onfido_capture_sdk_core_release().confirmationButtons, visible);
        if (visible) {
            View view = this.captureButton;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureButton");
                view = null;
            }
            ViewExtensionsKt.toGone(view, false);
        }
    }

    private final void setImagePreview(OnfidoImage onfidoImage) {
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap;
        getBinding$onfido_capture_sdk_core_release().confirmationImage.setScaleX(getCameraFace() == OnfidoCamera.CameraFacing.FRONT ? -1 : 1);
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(getImageUtils$onfido_capture_sdk_core_release(), onfidoImage.getData(), getBinding$onfido_capture_sdk_core_release().confirmationImage.getWidth(), getBinding$onfido_capture_sdk_core_release().confirmationImage.getHeight(), null, null, 24, null);
        if (getBinding$onfido_capture_sdk_core_release().confirmationImage.getDrawable() instanceof BitmapDrawable) {
            Drawable drawable = getBinding$onfido_capture_sdk_core_release().confirmationImage.getDrawable();
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            bitmapDrawable = (BitmapDrawable) drawable;
        } else {
            bitmapDrawable = null;
        }
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
            bitmap.recycle();
        }
        getBinding$onfido_capture_sdk_core_release().confirmationImage.setImageBitmap(bitmapDecodeScaledResource$default);
        updateConfirmationImageTranslationAndScale();
    }

    private final void setLivenessOverlayMargin(RectF captureRect) {
        getBinding$onfido_capture_sdk_core_release().livenessOverlayView.updateTextPosition$onfido_capture_sdk_core_release(captureRect);
    }

    private final void setValidationBubbleContent(int title, Integer subtitle) {
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding$onfido_capture_sdk_core_release().postCaptureValidationBubble;
        Intrinsics.checkNotNull(onfidoCaptureValidationBubble);
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.Content.Error(title, subtitle), false, 2, null);
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)), false, 2, null);
    }

    static /* synthetic */ void setValidationBubbleContent$default(CaptureActivity captureActivity, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        captureActivity.setValidationBubbleContent(i, num);
    }

    private final void setVideoRecordingIndicatorMargin(RectF captureRect) throws Resources.NotFoundException {
        LinearLayout root = getBinding$onfido_capture_sdk_core_release().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        float f = 2;
        layoutParams2.setMargins(0, (int) ((captureRect.top + (captureRect.height() / f)) - (getResources().getDimension(R.dimen.onfido_document_capture_video_indicator_height) / f)), 0, 0);
        root.setLayoutParams(layoutParams2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCaptureButton$lambda$11(CaptureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.capture(false);
        this$0.trackAutocaptureShutterButtonClick();
    }

    private final void setupOverlayView(CaptureType captureType, DocumentType documentType) throws Resources.NotFoundException {
        OverlayView overlayView = null;
        if (this.overlayView != null) {
            FrameLayout frameLayout = getBinding$onfido_capture_sdk_core_release().contentLayout;
            OverlayView overlayView2 = this.overlayView;
            if (overlayView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                overlayView2 = null;
            }
            frameLayout.removeView(overlayView2);
        }
        OverlayView overlayViewInflateOverlayView = inflateOverlayView(documentType);
        overlayViewInflateOverlayView.setUp(captureType, this);
        OverlayView.setColorOutsideOverlay$default(overlayViewInflateOverlayView, getBackgroundColorCaptureScreen(), false, 2, null);
        ViewExtensionsKt.runOnMeasured(overlayViewInflateOverlayView, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$setupOverlayView$1$1
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
                this.this$0.startCamera();
            }
        });
        this.overlayView = overlayViewInflateOverlayView;
        OverlayTextView overlayTextView = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        overlayTextView.setCaptureOverlayText$onfido_capture_sdk_core_release(captureType, documentTypeUIModel, getDocSide());
        OverlayView overlayView3 = this.overlayView;
        if (overlayView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView3 = null;
        }
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        overlayView3.setIsProofOfAddress(companion.isProofOfAddress$onfido_capture_sdk_core_release(intent));
        FrameLayout frameLayout2 = getBinding$onfido_capture_sdk_core_release().contentLayout;
        OverlayView overlayView4 = this.overlayView;
        if (overlayView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
        } else {
            overlayView = overlayView4;
        }
        frameLayout2.addView(overlayView);
    }

    private final void setupPresenter() {
        CapturePresenter presenter$onfido_capture_sdk_core_release = getPresenter$onfido_capture_sdk_core_release();
        OnfidoConfig onfidoConfig$onfido_capture_sdk_core_release = getOnfidoConfig$onfido_capture_sdk_core_release();
        CaptureStepDataBundle documentData = getDocumentData();
        IntentHelper intentHelper = IntentHelper.INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        NfcArguments nfcArguments = intentHelper.getNfcArguments(intent);
        Companion companion = INSTANCE;
        Intent intent2 = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
        presenter$onfido_capture_sdk_core_release.setup$onfido_capture_sdk_core_release(this, onfidoConfig$onfido_capture_sdk_core_release, documentData, nfcArguments, companion.isProofOfAddress$onfido_capture_sdk_core_release(intent2));
    }

    private final boolean shouldShowPassportOverlay() {
        ShapeableImageView passportOverlay = getBinding$onfido_capture_sdk_core_release().passportOverlay;
        Intrinsics.checkNotNullExpressionValue(passportOverlay, "passportOverlay");
        return !ViewExtensionsKt.isGone(passportOverlay) && getPresenter$onfido_capture_sdk_core_release().shouldShowInitialOverlay$onfido_capture_sdk_core_release(getDocumentType()) && getPresenter$onfido_capture_sdk_core_release().shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDocumentFormatDialog$lambda$7$lambda$5(BottomSheetDialog this_with, CaptureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_with.dismiss();
        this$0.onCardFormatSelected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDocumentFormatDialog$lambda$7$lambda$6(BottomSheetDialog this_with, CaptureActivity this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_with.dismiss();
        this$0.onFoldedFormatSelected();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void showDocumentOverlay(android.graphics.RectF r10) {
        /*
            r9 = this;
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r0 = r9.getPresenter$onfido_capture_sdk_core_release()
            r1 = 1
            r0.setDisplayingOverlay$onfido_capture_sdk_core_release(r1)
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r0 = r9.getPresenter$onfido_capture_sdk_core_release()
            boolean r1 = r9.isDocumentFrontSide()
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r2 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r2 = r2.frenchDLOverlay
            java.lang.String r3 = "frenchDLOverlay"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            boolean r2 = com.onfido.android.sdk.capture.utils.ViewExtensionsKt.isGone(r2)
            boolean r0 = r0.shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release(r1, r2)
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r1 = r9.getPresenter$onfido_capture_sdk_core_release()
            boolean r2 = r9.isDocumentFrontSide()
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r4 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r4 = r4.germanDLOverlay
            java.lang.String r5 = "germanDLOverlay"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            boolean r4 = com.onfido.android.sdk.capture.utils.ViewExtensionsKt.isGone(r4)
            boolean r1 = r1.shouldShowGermanDLOverlay$onfido_capture_sdk_core_release(r2, r4)
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r2 = r9.getPresenter$onfido_capture_sdk_core_release()
            boolean r4 = r9.isDocumentFrontSide()
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r6 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r6 = r6.italianIDOverlay
            java.lang.String r7 = "italianIDOverlay"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            boolean r6 = com.onfido.android.sdk.capture.utils.ViewExtensionsKt.isGone(r6)
            boolean r2 = r2.shouldShowItalianIDOverlay$onfido_capture_sdk_core_release(r4, r6)
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r4 = r9.getPresenter$onfido_capture_sdk_core_release()
            boolean r6 = r9.isDocumentFrontSide()
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r8 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r8 = r8.italianIDOverlay
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r7)
            boolean r8 = com.onfido.android.sdk.capture.utils.ViewExtensionsKt.isGone(r8)
            boolean r4 = r4.shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release(r6, r8)
            if (r0 == 0) goto L81
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r0 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r0 = r0.frenchDLOverlay
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
        L7d:
            com.onfido.android.sdk.capture.utils.ImageUtilsExtKt.showOverlay(r0, r10)
            goto Lae
        L81:
            if (r1 == 0) goto L8d
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r0 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r0 = r0.germanDLOverlay
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            goto L7d
        L8d:
            if (r2 == 0) goto L90
            goto L92
        L90:
            if (r4 == 0) goto L9c
        L92:
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r0 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r0 = r0.italianIDOverlay
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)
            goto L7d
        L9c:
            boolean r0 = r9.shouldShowPassportOverlay()
            if (r0 == 0) goto Lae
            com.onfido.android.sdk.capture.databinding.OnfidoActivityCaptureBinding r0 = r9.getBinding$onfido_capture_sdk_core_release()
            com.google.android.material.imageview.ShapeableImageView r0 = r0.passportOverlay
            java.lang.String r1 = "passportOverlay"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            goto L7d
        Lae:
            com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig r0 = r9.getOnfidoRemoteConfig$onfido_capture_sdk_core_release()
            boolean r0 = r0.isFourByThreeEnabled()
            if (r0 == 0) goto Lc5
            com.onfido.android.sdk.capture.internal.camera.OnfidoCamera r0 = r9.onfidoCamera
            if (r0 != 0) goto Lc2
            java.lang.String r0 = "onfidoCamera"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = 0
        Lc2:
            r0.centerPreviewAccordingTo(r10)
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.showDocumentOverlay(android.graphics.RectF):void");
    }

    private final void showFaceConfirmationFragment() {
        if (getCaptureConfirmationScreen() != null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FaceConfirmationFragment(), FRAGMENT_TAG_CONFIRMATION).commit();
        FragmentContainerView fragmentContainer = getBinding$onfido_capture_sdk_core_release().fragmentContainer;
        Intrinsics.checkNotNullExpressionValue(fragmentContainer, "fragmentContainer");
        ViewExtensionsKt.toVisible$default(fragmentContainer, false, 1, null);
    }

    private final void startVideoRecording(final Function0<Unit> videoRecordingStarted) {
        FileUtils fileUtils = FileUtils.INSTANCE;
        File cacheDir = getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
        fileUtils.deleteFilesWithPrefixFromFolder(cacheDir, VideoCaptureConfig.DEFAULT_FILE_NAME_PREFIX);
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        this.recorder = onfidoCamera.takeVideo(new Function1<OnfidoCamera.VideoCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.startVideoRecording.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.VideoCaptureEvent videoCaptureEvent) {
                invoke2(videoCaptureEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.VideoCaptureEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE)) {
                    CaptureActivity.this.onVideoCanceled();
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Error) {
                    CaptureActivity.this.getPresenter$onfido_capture_sdk_core_release().onErrorVideoTaking$onfido_capture_sdk_core_release();
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Recorded) {
                    CaptureActivity.this.onVideoCaptured(((OnfidoCamera.VideoCaptureEvent.Recorded) event).getFilePath());
                } else if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Started.INSTANCE)) {
                    videoRecordingStarted.invoke();
                } else if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE)) {
                    CaptureActivity.this.onVideoTimeoutExceeded();
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void startVideoRecording$default(CaptureActivity captureActivity, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.startVideoRecording.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        captureActivity.startVideoRecording(function0);
    }

    private final void trackAutocaptureShutterButtonClick() {
        if (getPresenter$onfido_capture_sdk_core_release().shouldAutoCaptureDocument$onfido_capture_sdk_core_release() || getDocumentData().getCaptureType() != CaptureType.DOCUMENT) {
            getPresenter$onfido_capture_sdk_core_release().trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release();
        }
    }

    private final void updateColors(int toolbarBackgroundColor, int toolbarTitleColor, int toolbarSubtitleColor, int screenBackgroundColor) throws Resources.NotFoundException {
        if (getSupportActionBar() != null) {
            int iColorFromAttr = ContextUtilsKt.colorFromAttr(this, android.R.attr.colorPrimaryDark);
            int iColorFromAttr2 = ContextUtilsKt.colorFromAttr(this, toolbarBackgroundColor);
            int iColorFromAttr3 = ContextUtilsKt.colorFromAttr(this, toolbarTitleColor);
            int iColorFromAttr4 = ContextUtilsKt.colorFromAttr(this, toolbarSubtitleColor);
            changeStatusBarColor(iColorFromAttr);
            getBinding$onfido_capture_sdk_core_release().toolbar.setBackgroundColor(iColorFromAttr2);
            getBinding$onfido_capture_sdk_core_release().toolbar.setTitleTextColor(iColorFromAttr3);
            changeBackArrowColor(iColorFromAttr3);
            getBinding$onfido_capture_sdk_core_release().toolbar.setSubtitleTextColor(iColorFromAttr4);
        }
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        OverlayView.setColorOutsideOverlay$default(overlayView, screenBackgroundColor, false, 2, null);
        if (getIsOnConfirmationStep()) {
            getBinding$onfido_capture_sdk_core_release().watermark.setLightBackground();
        } else {
            getBinding$onfido_capture_sdk_core_release().watermark.setDarkBackground();
        }
    }

    private final void updateColorsForConfirmationScreen() throws Resources.NotFoundException {
        updateColors(R.attr.onfidoColorToolbarBackground, R.attr.onfidoColorContentToolbarTitle, R.attr.onfidoColorContentToolbarSubtitle, getBackgroundColorConfirmationScreen());
    }

    private final void updateConfirmationImageTranslationAndScale() {
        if (getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isFourByThreeEnabled()) {
            OverlayMetrics overlayMetrics = this.overlayMetrics;
            OverlayMetrics overlayMetrics2 = null;
            if (overlayMetrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
                overlayMetrics = null;
            }
            float fCenterX = overlayMetrics.getVisibleCaptureRect().centerX();
            OverlayMetrics overlayMetrics3 = this.overlayMetrics;
            if (overlayMetrics3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            } else {
                overlayMetrics2 = overlayMetrics3;
            }
            float fCenterY = overlayMetrics2.getVisibleCaptureRect().centerY() - (getBinding$onfido_capture_sdk_core_release().confirmationImage.getHeight() / 2);
            getBinding$onfido_capture_sdk_core_release().confirmationImage.setTranslationX(fCenterX - (getBinding$onfido_capture_sdk_core_release().confirmationImage.getWidth() / 2));
            getBinding$onfido_capture_sdk_core_release().confirmationImage.setTranslationY(fCenterY);
            getBinding$onfido_capture_sdk_core_release().confirmationImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    private final void updateOverlayView(CaptureType captureType, DocumentType documentType) throws Resources.NotFoundException {
        OverlayView overlayView = this.overlayView;
        OverlayView overlayView2 = null;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        OverlayView overlayViewInflateOverlayView = inflateOverlayView(documentType);
        overlayViewInflateOverlayView.setUp(captureType, this);
        overlayViewInflateOverlayView.setColorOutsideOverlay(getBackgroundColorCaptureScreen(), false);
        this.overlayView = overlayViewInflateOverlayView;
        OverlayTextView overlayTextView = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        overlayTextView.setCaptureOverlayText$onfido_capture_sdk_core_release(captureType, documentTypeUIModel, getDocSide());
        getBinding$onfido_capture_sdk_core_release().contentLayout.removeView(overlayView);
        FrameLayout frameLayout = getBinding$onfido_capture_sdk_core_release().contentLayout;
        OverlayView overlayView3 = this.overlayView;
        if (overlayView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
        } else {
            overlayView2 = overlayView3;
        }
        frameLayout.addView(overlayView2);
    }

    private final void uploadDocument(byte[] jpegData) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06011(jpegData, null), 3, null);
    }

    private final void uploadSelfieForValidation(byte[] jpegData) {
        boolean zShouldPerformFaceValidation$onfido_capture_sdk_core_release = getPresenter$onfido_capture_sdk_core_release().shouldPerformFaceValidation$onfido_capture_sdk_core_release();
        boolean zIsPayloadSigningEnabled = getOnfidoRemoteConfig$onfido_capture_sdk_core_release().getSelfieCapture().isPayloadSigningEnabled();
        CaptureUploadService captureUploadService = this.captureUploadService;
        if (captureUploadService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureUploadService");
            captureUploadService = null;
        }
        captureUploadService.uploadSelfie$onfido_capture_sdk_core_release(zShouldPerformFaceValidation$onfido_capture_sdk_core_release, jpegData, getPresenter$onfido_capture_sdk_core_release().sdkUploadMetadata$onfido_capture_sdk_core_release(), zIsPayloadSigningEnabled);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void applyValidations(OnfidoImage image) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(image, "image");
        this.previewImage = image;
        setImagePreview(image);
        DocumentType documentType = getDocumentType();
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        RectF realCaptureRect = overlayMetrics.getRealCaptureRect();
        if (getCaptureType() != CaptureType.DOCUMENT || documentType == null) {
            showConfirmationStep();
        } else {
            getPresenter$onfido_capture_sdk_core_release().applyPostCaptureValidations$onfido_capture_sdk_core_release(createDocumentDetectionFrame$default(this, image, realCaptureRect, 0, 4, null), documentType, getDocSide(), getCountryCode());
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void cancelFlow() {
        BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, 0, null, 2, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void capture(final boolean playSingleFrameAutoCapturedAnimation) {
        if (this.hasOngoingCaptureRequest) {
            Timber.INSTANCE.i("There is an ongoing capture request! This request has been ignored!", new Object[0]);
            return;
        }
        this.hasOngoingCaptureRequest = true;
        getFrameSubscription().clear();
        if (!getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isCutoffImprovementsEnabled() && playSingleFrameAutoCapturedAnimation) {
            playSingleFrameAutoCapturedAnimation();
        }
        View view = this.captureButton;
        OnfidoCamera onfidoCamera = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        ViewExtensionsKt.toInvisible(view, false);
        getPresenter$onfido_capture_sdk_core_release().stop$onfido_capture_sdk_core_release();
        CaptureType captureType = getCaptureType();
        CaptureType captureType2 = CaptureType.DOCUMENT;
        boolean z = captureType != captureType2;
        final String str = getCaptureType() == captureType2 ? "document_capture" : PerformanceEventName.FACE_CAPTURE;
        getPerformanceAnalytics$onfido_capture_sdk_core_release().trackStart(new PerformanceEvents.TraceStart(str));
        OnfidoCamera onfidoCamera2 = this.onfidoCamera;
        if (onfidoCamera2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
        } else {
            onfidoCamera = onfidoCamera2;
        }
        onfidoCamera.takePicture(new PhotoCaptureConfig(z), new Function1<OnfidoCamera.PictureCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.capture.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.PictureCaptureEvent pictureCaptureEvent) {
                invoke2(pictureCaptureEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.PictureCaptureEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                if (CaptureActivity.this.getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isCutoffImprovementsEnabled() && playSingleFrameAutoCapturedAnimation) {
                    CaptureActivity.this.playSingleFrameAutoCapturedAnimation();
                }
                CaptureActivity.this.getPerformanceAnalytics$onfido_capture_sdk_core_release().trackEnd(new PerformanceEvents.TraceEnd(str));
                if (event instanceof OnfidoCamera.PictureCaptureEvent.Captured) {
                    CaptureActivity.this.onPictureCaptured(((OnfidoCamera.PictureCaptureEvent.Captured) event).getImage());
                } else if (event instanceof OnfidoCamera.PictureCaptureEvent.Error) {
                    CaptureActivity.this.getPresenter$onfido_capture_sdk_core_release().onErrorPictureTaking$onfido_capture_sdk_core_release();
                }
                CaptureActivity.this.hasOngoingCaptureRequest = false;
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void deactivateCaptureButton() {
        View view = this.captureButton;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        ViewExtensionsKt.deactivate(view);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void destroyWithCanceledResult() {
        BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, 0, null, 2, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void displayCaptureButton() {
        if (getIsOnConfirmationStep()) {
            return;
        }
        View view = this.captureButton;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        ViewExtensionsKt.toVisible$default(view, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void enableTorch(boolean isEnabled) {
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.getCameraControl().enableTorch(isEnabled);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void finishWithResultExitUserFlow() {
        BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, RESULT_EXIT_USER_FLOW, null, 2, null);
    }

    public final AnnouncementService getAnnouncementService$onfido_capture_sdk_core_release() {
        AnnouncementService announcementService = this.announcementService;
        if (announcementService != null) {
            return announcementService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        return null;
    }

    public final OnfidoActivityCaptureBinding getBinding$onfido_capture_sdk_core_release() {
        OnfidoActivityCaptureBinding onfidoActivityCaptureBinding = this.binding;
        if (onfidoActivityCaptureBinding != null) {
            return onfidoActivityCaptureBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final CameraFactory getCameraFactory$onfido_capture_sdk_core_release() {
        CameraFactory cameraFactory = this.cameraFactory;
        if (cameraFactory != null) {
            return cameraFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cameraFactory");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public CaptureStepDataBundle getCaptureStepDataBundle() {
        return getDocumentData();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public CaptureType getCaptureType() {
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        Serializable serializable = extras.getSerializable("type");
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.CaptureType");
        return (CaptureType) serializable;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public File getCapturedFilesDir() {
        File filesDir = getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
        return filesDir;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public CountryCode getCountryCode() {
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return companion.getDocumentCountryFrom$onfido_capture_sdk_core_release(intent);
    }

    public final Cryptography getCryptography$onfido_capture_sdk_core_release() {
        Cryptography cryptography = this.cryptography;
        if (cryptography != null) {
            return cryptography;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cryptography");
        return null;
    }

    public final DispatchersProvider getDispatchersProvider$onfido_capture_sdk_core_release() {
        DispatchersProvider dispatchersProvider = this.dispatchersProvider;
        if (dispatchersProvider != null) {
            return dispatchersProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dispatchersProvider");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public DocSide getDocSide() {
        return isDocumentFrontSide() ? DocSide.FRONT : DocSide.BACK;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public DocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public DocumentType getDocumentType() {
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return companion.getDocTypeFrom$onfido_capture_sdk_core_release(intent);
    }

    public final EnvironmentIntegrityChecker getEnvironmentIntegrityChecker$onfido_capture_sdk_core_release() {
        EnvironmentIntegrityChecker environmentIntegrityChecker = this.environmentIntegrityChecker;
        if (environmentIntegrityChecker != null) {
            return environmentIntegrityChecker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("environmentIntegrityChecker");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public DocumentPages getGenericDocPages() {
        Companion companion = INSTANCE;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
        return companion.getGenericDocumentPageSpecification(intent);
    }

    public final ImageUtils getImageUtils$onfido_capture_sdk_core_release() {
        ImageUtils imageUtils = this.imageUtils;
        if (imageUtils != null) {
            return imageUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUtils");
        return null;
    }

    public final OnfidoApiService getOnfidoApiService$onfido_capture_sdk_core_release() {
        OnfidoApiService onfidoApiService = this.onfidoApiService;
        if (onfidoApiService != null) {
            return onfidoApiService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onfidoApiService");
        return null;
    }

    public final OnfidoRemoteConfig getOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        OnfidoRemoteConfig onfidoRemoteConfig = this.onfidoRemoteConfig;
        if (onfidoRemoteConfig != null) {
            return onfidoRemoteConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onfidoRemoteConfig");
        return null;
    }

    public final PayloadHelper getPayloadHelper$onfido_capture_sdk_core_release() {
        PayloadHelper payloadHelper = this.payloadHelper;
        if (payloadHelper != null) {
            return payloadHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("payloadHelper");
        return null;
    }

    public final CapturePresenter getPresenter$onfido_capture_sdk_core_release() {
        CapturePresenter capturePresenter = this.presenter;
        if (capturePresenter != null) {
            return capturePresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public OnfidoImage getPreviewImage() {
        return this.previewImage;
    }

    public final SchedulersProvider getSchedulersProvider$onfido_capture_sdk_core_release() {
        SchedulersProvider schedulersProvider = this.schedulersProvider;
        if (schedulersProvider != null) {
            return schedulersProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("schedulersProvider");
        return null;
    }

    public final ScreenLoadTracker getScreenLoadTracker$onfido_capture_sdk_core_release() {
        ScreenLoadTracker screenLoadTracker = this.screenLoadTracker;
        if (screenLoadTracker != null) {
            return screenLoadTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screenLoadTracker");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public Orientation getScreenOrientation() {
        return getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT;
    }

    public final TokenProvider getTokenProvider$onfido_capture_sdk_core_release() {
        TokenProvider tokenProvider = this.tokenProvider;
        if (tokenProvider != null) {
            return tokenProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tokenProvider");
        return null;
    }

    public final VibratorService getVibratorService$onfido_capture_sdk_core_release() {
        VibratorService vibratorService = this.vibratorService;
        if (vibratorService != null) {
            return vibratorService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vibratorService");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public boolean hasValidRecording() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        return videoRecorder != null && videoRecorder.getHasValidRecording();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void hideCaptureButton() {
        View view = this.captureButton;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        ViewExtensionsKt.toInvisible$default(view, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void hideDocumentOverlay() {
        getPresenter$onfido_capture_sdk_core_release().setDisplayingOverlay$onfido_capture_sdk_core_release(false);
        ShapeableImageView[] shapeableImageViewArr = {getBinding$onfido_capture_sdk_core_release().passportOverlay, getBinding$onfido_capture_sdk_core_release().frenchDLOverlay, getBinding$onfido_capture_sdk_core_release().germanDLOverlay, getBinding$onfido_capture_sdk_core_release().italianIDOverlay};
        ArrayList<ShapeableImageView> arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            ShapeableImageView shapeableImageView = shapeableImageViewArr[i];
            Intrinsics.checkNotNull(shapeableImageView);
            if (ViewExtensionsKt.isVisible(shapeableImageView)) {
                arrayList.add(shapeableImageView);
            }
        }
        for (ShapeableImageView shapeableImageView2 : arrayList) {
            Intrinsics.checkNotNull(shapeableImageView2);
            ViewExtensionsKt.toGone$default(shapeableImageView2, false, 1, null);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void hideLivenessControlButton() {
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        ViewExtensionsKt.toInvisible(onfidoButton, false);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void hideLoading() {
        super.dismissLoadingDialog$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void hideVideoRecordingProgressBar() {
        LinearLayout root = getBinding$onfido_capture_sdk_core_release().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensionsKt.toGone(root, false);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    /* renamed from: isOnConfirmationStep, reason: from getter */
    public boolean getIsOnConfirmationStep() {
        return this.isOnConfirmationStep;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void makeToolbarTitleNotImportantForAccessibility() {
        Toolbar toolbar = getBinding$onfido_capture_sdk_core_release().toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "toolbar");
        ToolbarExtensionsKt.makeTitleNotImportantForAccessibility(toolbar);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onAccessibleCaptureDocumentOverlayTextChanged(int mainTextResId, int mainTextContentDescriptionResId) {
        getBinding$onfido_capture_sdk_core_release().overlayTextContainer.setMainText$onfido_capture_sdk_core_release(mainTextResId, mainTextContentDescriptionResId, true);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onAccessibleCaptureRectangleDetectionResult(RectDetectionResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        getBinding$onfido_capture_sdk_core_release().onfidoAccessibleRectDetectorFrame.update$onfido_capture_sdk_core_release(result);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Overrides a deprecated method, compilation error with Kotlin 1.9")
    public void onBackPressed() throws Resources.NotFoundException {
        if (!getIsOnConfirmationStep()) {
            getPresenter$onfido_capture_sdk_core_release().trackCaptureBackButtonClicked$onfido_capture_sdk_core_release();
            BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, 0, null, 2, null);
        } else {
            getPresenter$onfido_capture_sdk_core_release().trackConfirmationBackButtonClicked$onfido_capture_sdk_core_release();
            closeConfirmationScreen();
            openCaptureScreen();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onCaptureForProofOfAddressDone(OnfidoImage image, RectF visibleRect) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(visibleRect, "visibleRect");
        getPresenter$onfido_capture_sdk_core_release().cropImageAndSaveToFile$onfido_capture_sdk_core_release(image.getData(), createDocumentDetectionFrame$default(this, image, visibleRect, 0, 4, null));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onChallengeLoadingViewStateChanged(LivenessChallengesLoadingView.ScreenState screenState) {
        Intrinsics.checkNotNullParameter(screenState, "screenState");
        if (!Intrinsics.areEqual(screenState, LivenessChallengesLoadingView.ScreenState.Loading.INSTANCE)) {
            if (screenState instanceof LivenessChallengesLoadingView.ScreenState.Success) {
                onChallengesAvailable(((LivenessChallengesLoadingView.ScreenState.Success) screenState).getLivenessChallengesViewModel());
                return;
            } else {
                Intrinsics.areEqual(screenState, LivenessChallengesLoadingView.ScreenState.Error.INSTANCE);
                return;
            }
        }
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        ViewExtensionsKt.toGone$default(overlayView, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onChallengesCompleted() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.finish();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onChallengesErrorBackPressed() throws Resources.NotFoundException {
        onBackPressed();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnfidoActivityCaptureBinding onfidoActivityCaptureBindingInflate = OnfidoActivityCaptureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(onfidoActivityCaptureBindingInflate, "inflate(...)");
        setBinding$onfido_capture_sdk_core_release(onfidoActivityCaptureBindingInflate);
        setContentView(getBinding$onfido_capture_sdk_core_release().getRoot());
        DocumentTypeUIModel documentTypeUIModel = null;
        SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), this, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        this.captureActivityLayoutAdjuster = new CaptureActivityLayoutAdjuster(this, getCaptureType(), getDocSide());
        RelativeLayout content = getBinding$onfido_capture_sdk_core_release().content;
        Intrinsics.checkNotNullExpressionValue(content, "content");
        this.validationBubbleOffsetDelegate = new ValidationBubblesOffsetDelegate(content, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.liveValidationBubble), Integer.valueOf(R.id.postCaptureValidationBubble)}), getCaptureType());
        Lifecycle lifecycle = getLifecycleRegistry();
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
            captureActivityLayoutAdjuster = null;
        }
        lifecycle.addObserver(captureActivityLayoutAdjuster);
        Lifecycle lifecycle2 = getLifecycleRegistry();
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        lifecycle2.addObserver(validationBubblesOffsetDelegate);
        breakIfDocTypeMissing();
        initDocumentFormat();
        initDocumentTypeUIModel();
        inflateCaptureButton(getCaptureType());
        inflateDummyAccessibilityView();
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            ConfirmationStepButtons confirmationStepButtons = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
            DocumentTypeUIModel documentTypeUIModel2 = this.documentTypeUIModel;
            if (documentTypeUIModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
                documentTypeUIModel2 = null;
            }
            int readabilityDiscardLabel = documentTypeUIModel2.getReadabilityDiscardLabel();
            DocumentTypeUIModel documentTypeUIModel3 = this.documentTypeUIModel;
            if (documentTypeUIModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            } else {
                documentTypeUIModel = documentTypeUIModel3;
            }
            confirmationStepButtons.setDocumentCapture$onfido_capture_sdk_core_release(readabilityDiscardLabel, documentTypeUIModel.getReadabilityConfirmationLabel());
        } else if (i == 2) {
            getBinding$onfido_capture_sdk_core_release().confirmationButtons.setFaceCapture();
            getBinding$onfido_capture_sdk_core_release().confirmationImage.setOnTouchListener(null);
        }
        setupPresenter();
        closeConfirmationScreen();
        recoverStateFrom(savedInstanceState);
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (hasOnfidoConfig()) {
            CaptureUploadService captureUploadService = this.captureUploadService;
            if (captureUploadService != null) {
                if (captureUploadService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureUploadService");
                    captureUploadService = null;
                }
                captureUploadService.stop$onfido_capture_sdk_core_release();
            }
            hideLoading();
            this.handler.removeCallbacksAndMessages(null);
            getPresenter$onfido_capture_sdk_core_release().onDestroy$onfido_capture_sdk_core_release();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onDocumentCreated(String documentId, String documentVideoId, boolean nfcSupported) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        if (!getIsOnConfirmationStep()) {
            showConfirmationStep();
            setConfirmationButtons(false);
        } else {
            getPresenter$onfido_capture_sdk_core_release().callMediaCallback$onfido_capture_sdk_core_release();
            hideLoading();
            finishWithResult$onfido_capture_sdk_core_release(-1, getDocumentResultIntent(documentId, documentVideoId, nfcSupported));
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onDocumentVideoRecordingCompleted() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.finish();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onDocumentVideoUploaded(String documentVideoId) {
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        getPresenter$onfido_capture_sdk_core_release().onDocumentVideoUploaded$onfido_capture_sdk_core_release(documentVideoId);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView.ChallengesListener
    public void onErrorObservingHeadTurnResults() {
        getPresenter$onfido_capture_sdk_core_release().stopFaceTracking$onfido_capture_sdk_core_release();
        onFaceTrackingTimeout();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onFaceDetected(FaceDetectionResult faceDetectionResult) {
        Intrinsics.checkNotNullParameter(faceDetectionResult, "faceDetectionResult");
        if (faceDetectionResult instanceof FaceDetectionResult.FaceDetected) {
            OverlayMetrics overlayMetrics = this.overlayMetrics;
            if (overlayMetrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
                overlayMetrics = null;
            }
            Frame frameLimitRect = limitRect(overlayMetrics.getRealCaptureRect(), ((FaceDetectionResult.FaceDetected) faceDetectionResult).getCropRect());
            OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
            if ((videoRecorder == null || !videoRecorder.isRecording()) && isInsideOval(faceDetectionResult, frameLimitRect) && !getOnfidoConfig$onfido_capture_sdk_core_release().getManualLivenessCapture()) {
                OnfidoButton onfidoButton = this.livenessControlButton;
                if (onfidoButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
                    onfidoButton = null;
                }
                ViewExtensionsKt.toInvisible(onfidoButton, false);
                Completable completableVibrateForSuccess = getVibratorService$onfido_capture_sdk_core_release().vibrateForSuccess();
                Completable completableInterruptAnnouncement = getAnnouncementService$onfido_capture_sdk_core_release().interruptAnnouncement();
                AnnouncementService announcementService$onfido_capture_sdk_core_release = getAnnouncementService$onfido_capture_sdk_core_release();
                String string = getString(R.string.onfido_video_capture_frame_success_accessibility);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                Completable.concatArray(completableVibrateForSuccess, completableInterruptAnnouncement, AnnouncementService.announceString$default(announcementService$onfido_capture_sdk_core_release, new String[]{string}, false, 2, (Object) null)).blockingAwait();
                ViewUtil.setViewVisibilityWithoutAnimation(getBinding$onfido_capture_sdk_core_release().overlayTextContainer, false);
                OverlayView overlayView = this.overlayView;
                if (overlayView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                    overlayView = null;
                }
                OverlayView.showFaceConfirmationTick$default(overlayView, null, 1, null);
                getPresenter$onfido_capture_sdk_core_release().onFaceDetected$onfido_capture_sdk_core_release();
                Handler handler = this.handler;
                String string2 = getString(R.string.onfido_video_capture_frame_success_accessibility);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$onFaceDetected$$inlined$postDelayed$default$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        OnfidoCamera.VideoRecorder videoRecorder2 = this.this$0.recorder;
                        if (videoRecorder2 == null || !videoRecorder2.isRecording()) {
                            OverlayView overlayView2 = this.this$0.overlayView;
                            if (overlayView2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                                overlayView2 = null;
                            }
                            OverlayView.resetFaceDetectedState$default(overlayView2, false, false, false, 7, null);
                            this.this$0.getPresenter$onfido_capture_sdk_core_release().onAutoLivenessRecordingStart$onfido_capture_sdk_core_release();
                        }
                    }
                }, StringExtensionsKt.readingTimeMilliseconds(string2));
            }
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onFaceDetectionTimeout() {
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        ViewExtensionsKt.toVisible$default(onfidoButton, false, 1, null);
        AccessibilityExtensionsKt.sendAccessibilityFocusEvent(onfidoButton);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onFaceOutTimeout() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
        getPresenter$onfido_capture_sdk_core_release().stop$onfido_capture_sdk_core_release();
        getBinding$onfido_capture_sdk_core_release().livenessOverlayView.stopFaceTracking$onfido_capture_sdk_core_release();
        getPresenter$onfido_capture_sdk_core_release().trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_video_capture_prompt_header_restart), R.string.onfido_video_capture_prompt_detail_restart, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_video_capture_prompt_button_restart, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onFaceOutTimeout.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                CaptureActivity.this.onVideoTimeoutRetryClick(dialog);
            }
        }));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onFaceTrackingTimeout() {
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        ViewExtensionsKt.toVisible$default(onfidoButton, false, 1, null);
        AccessibilityExtensionsKt.sendAccessibilityFocusEvent(onfidoButton);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onImageProcessingFinished() {
        this.handler.postDelayed(this.glareBubbleRunnable, 300L);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onInvalidCertificateDetected(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intent intentPutExtra = new Intent().putExtra(OnfidoActivity.INVALID_CERTIFICATE_MESSAGE_EXTRA, message);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        finishWithResult$onfido_capture_sdk_core_release(OnfidoActivity.RESULT_EXIT_INVALID_CERTIFICATE, intentPutExtra);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onLivePhotoUploaded(LivePhotoUpload photoUpload) {
        Intrinsics.checkNotNullParameter(photoUpload, "photoUpload");
        getPresenter$onfido_capture_sdk_core_release().onFaceSelfieUploaded$onfido_capture_sdk_core_release();
        getPresenter$onfido_capture_sdk_core_release().callMediaCallback$onfido_capture_sdk_core_release();
        finishWithResult$onfido_capture_sdk_core_release(-1, getLivePhotoUploadResultIntent(photoUpload));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView.ChallengesListener
    public void onLivenessChallengeFinished() {
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.showFaceConfirmationTick(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onLivenessChallengeFinished.1
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
                OverlayView overlayView2 = CaptureActivity.this.overlayView;
                if (overlayView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                    overlayView2 = null;
                }
                OverlayView.resetFaceDetectedState$default(overlayView2, false, false, false, 4, null);
                Handler handler = CaptureActivity.this.handler;
                final CaptureActivity captureActivity = CaptureActivity.this;
                handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$onLivenessChallengeFinished$1$invoke$$inlined$postDelayed$default$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        captureActivity.getPresenter$onfido_capture_sdk_core_release().issueNextChallenge$onfido_capture_sdk_core_release();
                    }
                }, 500L);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onManualFallbackDelayFinished() {
        if (getIsOnConfirmationStep() || !getPresenter$onfido_capture_sdk_core_release().shouldAutoCaptureDocument$onfido_capture_sdk_core_release()) {
            return;
        }
        View view = this.captureButton;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        ViewExtensionsKt.toVisible$default(view, false, 1, null);
        final OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding$onfido_capture_sdk_core_release().liveValidationBubble;
        onfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release(new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_alert_manual_capture_title, Integer.valueOf(R.string.onfido_doc_capture_alert_manual_capture_detail)), true);
        onfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release(new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(true)), true);
        onfidoCaptureValidationBubble.setState$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.State.HARD_LOCK);
        onfidoCaptureValidationBubble.getHandler().postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CaptureActivity.onManualFallbackDelayFinished$lambda$13$lambda$12(onfidoCaptureValidationBubble);
            }
        }, onfidoCaptureValidationBubble.readingTimeMilliseconds$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onNextChallenge(LivenessChallengeViewModel livenessChallengeViewModel) {
        Intrinsics.checkNotNullParameter(livenessChallengeViewModel, "livenessChallengeViewModel");
        int index = livenessChallengeViewModel.getIndex();
        LivenessChallenge challenge = livenessChallengeViewModel.getChallenge();
        boolean zIsLastChallenge = livenessChallengeViewModel.isLastChallenge();
        getPresenter$onfido_capture_sdk_core_release().onNextChallenge$onfido_capture_sdk_core_release(challenge);
        getBinding$onfido_capture_sdk_core_release().livenessOverlayView.updateInfo$onfido_capture_sdk_core_release(challenge, getOnfidoConfig$onfido_capture_sdk_core_release().getManualLivenessCapture());
        getPresenter$onfido_capture_sdk_core_release().trackLivenessChallenge$onfido_capture_sdk_core_release(index, challenge.getType());
        String string = getString(zIsLastChallenge ? R.string.onfido_video_capture_button_primary_finish : R.string.onfido_video_capture_button_primary_fallback);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        OnfidoButton onfidoButton = this.livenessControlButton;
        if (onfidoButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
            onfidoButton = null;
        }
        onfidoButton.setText(string);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onNfcPropertiesFetched(String documentId, String documentVideoId, NfcProperties nfcProperties) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
        if (!getIsOnConfirmationStep()) {
            showConfirmationStep();
            setConfirmationButtons(false);
        } else {
            getPresenter$onfido_capture_sdk_core_release().callMediaCallback$onfido_capture_sdk_core_release();
            hideLoading();
            finishWithResult$onfido_capture_sdk_core_release(-1, IntentHelper.INSTANCE.putNfcProperties$onfido_capture_sdk_core_release(getDocumentResultIntent(documentId, documentVideoId, false), nfcProperties));
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.OverlayView.Listener
    public void onOverlayMetrics(OverlayMetrics overlayMetrics) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.overlayMetrics = overlayMetrics;
        getPresenter$onfido_capture_sdk_core_release().onOverlayMetricsChanged$onfido_capture_sdk_core_release(overlayMetrics);
        RectF visibleCaptureRect = overlayMetrics.getVisibleCaptureRect();
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
            captureActivityLayoutAdjuster = null;
        }
        captureActivityLayoutAdjuster.adjustLayoutParams(getIsOnConfirmationStep());
        setCaptureRegion(visibleCaptureRect);
        setLivenessOverlayMargin(visibleCaptureRect);
        setVideoRecordingIndicatorMargin(visibleCaptureRect);
        LivenessChallengesLoadingView livenessChallengesLoadingView = this.livenessChallengesLoadingView;
        if (livenessChallengesLoadingView != null) {
            livenessChallengesLoadingView.setTopLimit(overlayMetrics.getVisibleCaptureRect().bottom);
        }
        if (getPresenter$onfido_capture_sdk_core_release().shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release() && this.wasConfirmationNotShown) {
            View view = this.captureButton;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureButton");
                view = null;
            }
            ViewExtensionsKt.toInvisible$default(view, false, 1, null);
            getBinding$onfido_capture_sdk_core_release().liveValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, true);
        }
        adjustDummyAccessibilityView(overlayMetrics.getVisibleCaptureRect());
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        getPresenter$onfido_capture_sdk_core_release().onPause$onfido_capture_sdk_core_release();
        super.onPause();
    }

    public final void onPictureCaptured(OnfidoImage image) {
        Intrinsics.checkNotNullParameter(image, "image");
        getPresenter$onfido_capture_sdk_core_release().onPictureCaptured$onfido_capture_sdk_core_release(image);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onPoaImageCroppedAndSavedToFile(String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intent intentPutExtra = new Intent().putExtra(POA_CAPTURED_FILE_NAME, fileName);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        finishWithResult$onfido_capture_sdk_core_release(-1, intentPutExtra);
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getPresenter$onfido_capture_sdk_core_release().onViewResumed$onfido_capture_sdk_core_release(getCaptureType());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public void onRetakeSelfieButtonClick() {
        getPresenter$onfido_capture_sdk_core_release().onCaptureDiscarded();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_STATE, getPresenter$onfido_capture_sdk_core_release().getState$onfido_capture_sdk_core_release());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (getIsOnConfirmationStep()) {
            return;
        }
        getPresenter$onfido_capture_sdk_core_release().onStart$onfido_capture_sdk_core_release(getDocSide(), this.wasConfirmationNotShown, getIsOnConfirmationStep());
        if (getCaptureType() == CaptureType.VIDEO) {
            onStartLiveness();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        OnfidoCamera.VideoRecorder videoRecorder;
        OverlayView overlayView;
        super.onStop();
        getPresenter$onfido_capture_sdk_core_release().stop$onfido_capture_sdk_core_release();
        if (this.isCameraViewInitialised) {
            OverlayView overlayView2 = this.overlayView;
            if (overlayView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                overlayView = null;
            } else {
                overlayView = overlayView2;
            }
            OverlayView.resetFaceDetectedState$default(overlayView, false, false, false, 2, null);
        }
        if (getCaptureType().isVideo() && (videoRecorder = this.recorder) != null && !videoRecorder.isRecording()) {
            OnfidoButton onfidoButton = this.livenessControlButton;
            if (onfidoButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
                onfidoButton = null;
            }
            ViewUtil.setViewVisibility(onfidoButton, true);
        }
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity
    public void onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release() {
        BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, OnfidoActivity.RESULT_EXIT_BACKGROUND_STOP, null, 2, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onStorageThresholdReached() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_video_capture_error_storage_title), R.string.onfido_video_capture_error_storage_detail, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_ok, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onStorageThresholdReached.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) throws Resources.NotFoundException {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) throws Resources.NotFoundException {
                Intrinsics.checkNotNullParameter(it, "it");
                CaptureActivity.this.onBackPressed();
            }
        }));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onTokenExpired() {
        BaseActivity.finishWithResult$onfido_capture_sdk_core_release$default(this, OnfidoActivity.RESULT_EXIT_TOKEN_EXPIRED, null, 2, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity
    public boolean onToolbarBackEvent$onfido_capture_sdk_core_release() throws Resources.NotFoundException {
        onBackPressed();
        return true;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onUploadError(ErrorType errorType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        if ((errorType instanceof ErrorType.Document) || Intrinsics.areEqual(errorType, ErrorType.Cutoff.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Glare.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Blur.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.NoFace.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.MultipleFaces.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Barcode.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.PhotoOfScreen.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Screenshot.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Photocopy.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Scan.INSTANCE)) {
            getPresenter$onfido_capture_sdk_core_release().onUploadValidationError$onfido_capture_sdk_core_release(errorType, getCaptureType());
            return;
        }
        if (errorType instanceof ErrorType.Network) {
            getPresenter$onfido_capture_sdk_core_release().onUploadFailure$onfido_capture_sdk_core_release(!getIsOnConfirmationStep());
            return;
        }
        if (errorType instanceof ErrorType.InvalidCertificate) {
            onInvalidCertificateDetected(((ErrorType.InvalidCertificate) errorType).getMessage());
            return;
        }
        if (errorType instanceof ErrorType.TokenExpired) {
            onTokenExpired();
        } else if (errorType instanceof ErrorType.Geoblocked) {
            getPresenter$onfido_capture_sdk_core_release().onUploadFailureWithGeoblocking$onfido_capture_sdk_core_release(!getIsOnConfirmationStep());
        } else if (errorType instanceof ErrorType.Generic) {
            getPresenter$onfido_capture_sdk_core_release().onGeneralUploadError$onfido_capture_sdk_core_release();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public void onUploadSelfieButtonClick() {
        getPresenter$onfido_capture_sdk_core_release().onCaptureConfirmed();
    }

    public final void onVideoTimeoutExceeded() {
        getPresenter$onfido_capture_sdk_core_release().stop$onfido_capture_sdk_core_release();
        getBinding$onfido_capture_sdk_core_release().livenessOverlayView.stopFaceTracking$onfido_capture_sdk_core_release();
        getPresenter$onfido_capture_sdk_core_release().trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_video_capture_prompt_title_timeout), R.string.onfido_video_capture_prompt_detail_timeout, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_video_capture_prompt_button_timeout, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.onVideoTimeoutExceeded.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                CaptureActivity.this.onVideoTimeoutRetryClick(dialog);
            }
        }));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void onWarningBinaryResult(String documentId, ErrorType errorType, boolean nfcSupported, String documentVideoId, NfcProperties nfcProperties) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        if (getIsOnConfirmationStep()) {
            getPresenter$onfido_capture_sdk_core_release().callMediaCallback$onfido_capture_sdk_core_release();
            finishWithResult$onfido_capture_sdk_core_release(-1, IntentHelper.INSTANCE.putNfcProperties$onfido_capture_sdk_core_release(getDocumentResultIntent(documentId, documentVideoId, nfcSupported), nfcProperties));
        } else {
            showConfirmationStep();
            setConfirmationButtons(false);
            getPresenter$onfido_capture_sdk_core_release().showErrorType$onfido_capture_sdk_core_release(errorType);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void openCaptureScreen() throws Resources.NotFoundException {
        this.isOnConfirmationStep = false;
        hideLoading();
        startCamera();
        closeConfirmationScreen();
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = null;
        getBinding$onfido_capture_sdk_core_release().confirmationImage.setImageBitmap(null);
        setConfirmationStepVisibility(false);
        getPresenter$onfido_capture_sdk_core_release().clearEdges$onfido_capture_sdk_core_release();
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.switchConfirmationMode(false);
        OverlayTextView overlayTextView = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        CaptureType captureType = getCaptureType();
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        overlayTextView.setCaptureOverlayText$onfido_capture_sdk_core_release(captureType, documentTypeUIModel, getDocSide());
        OnfidoCaptureValidationBubble postCaptureValidationBubble = getBinding$onfido_capture_sdk_core_release().postCaptureValidationBubble;
        Intrinsics.checkNotNullExpressionValue(postCaptureValidationBubble, "postCaptureValidationBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(postCaptureValidationBubble, OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, false, 2, null);
        getBinding$onfido_capture_sdk_core_release().confirmationButtons.setListener$onfido_capture_sdk_core_release(getPresenter$onfido_capture_sdk_core_release());
        ConfirmationStepButtons confirmationStepButtons = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        CaptureType captureType2 = getCaptureType();
        DocumentTypeUIModel documentTypeUIModel2 = this.documentTypeUIModel;
        if (documentTypeUIModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel2 = null;
        }
        Integer numValueOf = Integer.valueOf(documentTypeUIModel2.getReadabilityDiscardLabel());
        DocumentTypeUIModel documentTypeUIModel3 = this.documentTypeUIModel;
        if (documentTypeUIModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel3 = null;
        }
        confirmationStepButtons.setErrorState(false, captureType2, numValueOf, Integer.valueOf(documentTypeUIModel3.getReadabilityConfirmationLabel()));
        setColorsForCaptureScreen();
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster2 = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
        } else {
            captureActivityLayoutAdjuster = captureActivityLayoutAdjuster2;
        }
        captureActivityLayoutAdjuster.adjustLayoutParams(getIsOnConfirmationStep());
        setCaptureFrameContentDescriptionAndTitle();
        getPresenter$onfido_capture_sdk_core_release().onStart$onfido_capture_sdk_core_release(getDocSide(), false, getIsOnConfirmationStep());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void playSingleFrameAutoCapturedAnimation() {
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.onSingleFrameAutoCaptured(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.playSingleFrameAutoCapturedAnimation.1
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
                CaptureActivity.this.runDocAutoCaptureAccessiblityEvents();
            }
        });
    }

    @Override // android.app.Activity
    public void recreate() {
        Intent intentPutExtra = new Intent().putExtra(IS_RECREATING_KEY, true).putExtra(OnfidoActivity.ONFIDO_INTENT_EXTRA, getIntent());
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        finishWithResult$onfido_capture_sdk_core_release(OnfidoActivity.ONFIDO_RECREATE, intentPutExtra);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void removeDummyViewsAccessibility() {
        View view = this.dummyView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyView");
            view = null;
        }
        view.setImportantForAccessibility(2);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void resetDocumentRecordingState() {
        ViewUtil.setViewVisibilityWithoutAnimation(getBinding$onfido_capture_sdk_core_release().overlayTextContainer, true);
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.resetDocumentOverlay();
        getBinding$onfido_capture_sdk_core_release().confirmationImage.setImageBitmap(null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void restart() {
        recreate();
    }

    public final void setAnnouncementService$onfido_capture_sdk_core_release(AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(announcementService, "<set-?>");
        this.announcementService = announcementService;
    }

    public final void setBinding$onfido_capture_sdk_core_release(OnfidoActivityCaptureBinding onfidoActivityCaptureBinding) {
        Intrinsics.checkNotNullParameter(onfidoActivityCaptureBinding, "<set-?>");
        this.binding = onfidoActivityCaptureBinding;
    }

    public final void setCameraFactory$onfido_capture_sdk_core_release(CameraFactory cameraFactory) {
        Intrinsics.checkNotNullParameter(cameraFactory, "<set-?>");
        this.cameraFactory = cameraFactory;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setConfirmationButtons(boolean isGenericMessage) {
        ConfirmationStepButtons confirmationStepButtons = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        DocumentTypeUIModel documentTypeUIModel2 = null;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        confirmationStepButtons.setWarningState(false, documentTypeUIModel);
        ConfirmationStepButtons confirmationStepButtons2 = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        DocumentTypeUIModel documentTypeUIModel3 = this.documentTypeUIModel;
        if (documentTypeUIModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel3 = null;
        }
        int readabilityConfirmationLabel = documentTypeUIModel3.getReadabilityConfirmationLabel();
        DocumentTypeUIModel documentTypeUIModel4 = this.documentTypeUIModel;
        if (documentTypeUIModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
        } else {
            documentTypeUIModel2 = documentTypeUIModel4;
        }
        confirmationStepButtons2.setDocumentCaptureCopy(readabilityConfirmationLabel, documentTypeUIModel2.getReadabilityDiscardLabel(), isGenericMessage);
    }

    public final void setCryptography$onfido_capture_sdk_core_release(Cryptography cryptography) {
        Intrinsics.checkNotNullParameter(cryptography, "<set-?>");
        this.cryptography = cryptography;
    }

    public final void setDispatchersProvider$onfido_capture_sdk_core_release(DispatchersProvider dispatchersProvider) {
        Intrinsics.checkNotNullParameter(dispatchersProvider, "<set-?>");
        this.dispatchersProvider = dispatchersProvider;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setDocumentFormat(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    public final void setEnvironmentIntegrityChecker$onfido_capture_sdk_core_release(EnvironmentIntegrityChecker environmentIntegrityChecker) {
        Intrinsics.checkNotNullParameter(environmentIntegrityChecker, "<set-?>");
        this.environmentIntegrityChecker = environmentIntegrityChecker;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setForceRetryButton() {
        CaptureConfirmationScreen captureConfirmationScreen = getCaptureConfirmationScreen();
        if (captureConfirmationScreen != null) {
            captureConfirmationScreen.setForceRetryButton();
            return;
        }
        ConfirmationStepButtons confirmationStepButtons = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        DocumentTypeUIModel documentTypeUIModel2 = null;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        confirmationStepButtons.setWarningState(false, documentTypeUIModel);
        ConfirmationStepButtons confirmationStepButtons2 = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        CaptureType captureType = getCaptureType();
        DocumentTypeUIModel documentTypeUIModel3 = this.documentTypeUIModel;
        if (documentTypeUIModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel3 = null;
        }
        Integer numValueOf = Integer.valueOf(documentTypeUIModel3.getReadabilityDiscardLabel());
        DocumentTypeUIModel documentTypeUIModel4 = this.documentTypeUIModel;
        if (documentTypeUIModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
        } else {
            documentTypeUIModel2 = documentTypeUIModel4;
        }
        confirmationStepButtons2.setErrorState(true, captureType, numValueOf, Integer.valueOf(documentTypeUIModel2.getReadabilityConfirmationLabel()));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setGlareWarningContent() {
        OnfidoCaptureValidationBubble liveValidationBubble = getBinding$onfido_capture_sdk_core_release().liveValidationBubble;
        Intrinsics.checkNotNullExpressionValue(liveValidationBubble, "liveValidationBubble");
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(liveValidationBubble, new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_alert_glare_title, Integer.valueOf(R.string.onfido_doc_capture_alert_glare_detail)), false, 2, null);
    }

    public final void setImageUtils$onfido_capture_sdk_core_release(ImageUtils imageUtils) {
        Intrinsics.checkNotNullParameter(imageUtils, "<set-?>");
        this.imageUtils = imageUtils;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setLiveValidationBubbleContent(OnfidoCaptureValidationBubble.Content content) {
        Intrinsics.checkNotNullParameter(content, "content");
        OnfidoCaptureValidationBubble liveValidationBubble = getBinding$onfido_capture_sdk_core_release().liveValidationBubble;
        Intrinsics.checkNotNullExpressionValue(liveValidationBubble, "liveValidationBubble");
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(liveValidationBubble, content, false, 2, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setLiveValidationBubbleVisibilityCommand(OnfidoCaptureValidationBubble.VisibilityCommand command) {
        Intrinsics.checkNotNullParameter(command, "command");
        OnfidoCaptureValidationBubble liveValidationBubble = getBinding$onfido_capture_sdk_core_release().liveValidationBubble;
        Intrinsics.checkNotNullExpressionValue(liveValidationBubble, "liveValidationBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(liveValidationBubble, command, false, 2, null);
    }

    public final void setOnfidoApiService$onfido_capture_sdk_core_release(OnfidoApiService onfidoApiService) {
        Intrinsics.checkNotNullParameter(onfidoApiService, "<set-?>");
        this.onfidoApiService = onfidoApiService;
    }

    public final void setOnfidoRemoteConfig$onfido_capture_sdk_core_release(OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "<set-?>");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setOverlay() throws Resources.NotFoundException {
        setSupportActionBar(getBinding$onfido_capture_sdk_core_release().toolbar);
        if (getIsOnConfirmationStep()) {
            return;
        }
        setupOverlayView(getCaptureType(), getDocumentType());
        setColorsForCaptureScreen();
    }

    public final void setPayloadHelper$onfido_capture_sdk_core_release(PayloadHelper payloadHelper) {
        Intrinsics.checkNotNullParameter(payloadHelper, "<set-?>");
        this.payloadHelper = payloadHelper;
    }

    public final void setPresenter$onfido_capture_sdk_core_release(CapturePresenter capturePresenter) {
        Intrinsics.checkNotNullParameter(capturePresenter, "<set-?>");
        this.presenter = capturePresenter;
    }

    public final void setSchedulersProvider$onfido_capture_sdk_core_release(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "<set-?>");
        this.schedulersProvider = schedulersProvider;
    }

    public final void setScreenLoadTracker$onfido_capture_sdk_core_release(ScreenLoadTracker screenLoadTracker) {
        Intrinsics.checkNotNullParameter(screenLoadTracker, "<set-?>");
        this.screenLoadTracker = screenLoadTracker;
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            Sequence sequenceFilter = SequencesKt.filter(ViewGroupKt.getChildren(toolbar), ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1.INSTANCE);
            Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            TextView textView = (TextView) SequencesKt.firstOrNull(sequenceFilter);
            if (textView != null) {
                ViewCompat.setAccessibilityHeading(textView, true);
            }
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeActionContentDescription(R.string.onfido_generic_back);
        }
        if (OnfidoConfigExtensionsKt.inWorkflowMode(getOnfidoConfig$onfido_capture_sdk_core_release()) && getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isStudioUserFlowExitEnabled()) {
            removeMenuProvider(getUserExitFlowMenuProvider());
            addMenuProvider(getUserExitFlowMenuProvider());
        }
    }

    public final void setTokenProvider$onfido_capture_sdk_core_release(TokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(tokenProvider, "<set-?>");
        this.tokenProvider = tokenProvider;
    }

    public final void setVibratorService$onfido_capture_sdk_core_release(VibratorService vibratorService) {
        Intrinsics.checkNotNullParameter(vibratorService, "<set-?>");
        this.vibratorService = vibratorService;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setWarningConfirmationButtons(boolean isGenericMessage) {
        ConfirmationStepButtons confirmationStepButtons = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        DocumentTypeUIModel documentTypeUIModel2 = null;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        confirmationStepButtons.setWarningState(true, documentTypeUIModel);
        ConfirmationStepButtons confirmationStepButtons2 = getBinding$onfido_capture_sdk_core_release().confirmationButtons;
        DocumentTypeUIModel documentTypeUIModel3 = this.documentTypeUIModel;
        if (documentTypeUIModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel3 = null;
        }
        int readabilityConfirmationLabel = documentTypeUIModel3.getReadabilityConfirmationLabel();
        DocumentTypeUIModel documentTypeUIModel4 = this.documentTypeUIModel;
        if (documentTypeUIModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
        } else {
            documentTypeUIModel2 = documentTypeUIModel4;
        }
        confirmationStepButtons2.setDocumentCaptureCopy(readabilityConfirmationLabel, documentTypeUIModel2.getReadabilityDiscardLabel(), isGenericMessage);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setupCaptureButton() {
        View view = this.captureButton;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureButton");
            view = null;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CaptureActivity.setupCaptureButton$lambda$11(this.f$0, view2);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setupConfirmationButtons() {
        getBinding$onfido_capture_sdk_core_release().confirmationButtons.setListener$onfido_capture_sdk_core_release(getPresenter$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void setupUploadService() {
        this.captureUploadService = new CaptureUploadService(getCaptureType(), getOnfidoApiService$onfido_capture_sdk_core_release(), this, getSchedulersProvider$onfido_capture_sdk_core_release(), getCryptography$onfido_capture_sdk_core_release(), getPayloadHelper$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showConfirmationPreview() {
        ZoomImageView confirmationImage = getBinding$onfido_capture_sdk_core_release().confirmationImage;
        Intrinsics.checkNotNullExpressionValue(confirmationImage, "confirmationImage");
        ViewExtensionsKt.toVisible$default(confirmationImage, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showConfirmationStep() throws Resources.NotFoundException {
        hideLoading();
        this.wasConfirmationNotShown = false;
        this.isOnConfirmationStep = true;
        getPresenter$onfido_capture_sdk_core_release().onConfirmationStepTracking$onfido_capture_sdk_core_release();
        View view = null;
        if (getIsDarkModeEnabled()) {
            OverlayView overlayView = this.overlayView;
            if (overlayView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                overlayView = null;
            }
            OverlayView.setColorOutsideOverlay$default(overlayView, getBackgroundColorConfirmationScreen(), false, 2, null);
        } else {
            updateColorsForConfirmationScreen();
        }
        if (getCaptureType() == CaptureType.FACE) {
            showFaceConfirmationFragment();
            return;
        }
        setConfirmationStepVisibility(true);
        if (getBinding$onfido_capture_sdk_core_release().confirmationButtons.getIsConfirmationButtonsHorizontal()) {
            getBinding$onfido_capture_sdk_core_release().confirmationButtons.enableAdaptableHorizontalButtonHeight();
            getBinding$onfido_capture_sdk_core_release().confirmationButtons.forceInnerButtonsMeasurement();
        }
        OverlayTextView overlayTextView = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        CaptureType captureType = getCaptureType();
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        if (documentTypeUIModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
            documentTypeUIModel = null;
        }
        overlayTextView.setConfirmationOverlayText(captureType, documentTypeUIModel);
        CaptureActivityLayoutAdjuster captureActivityLayoutAdjuster = this.captureActivityLayoutAdjuster;
        if (captureActivityLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureActivityLayoutAdjuster");
            captureActivityLayoutAdjuster = null;
        }
        captureActivityLayoutAdjuster.adjustLayoutParams(getIsOnConfirmationStep());
        if (getCaptureType() == CaptureType.DOCUMENT) {
            OverlayView overlayView2 = this.overlayView;
            if (overlayView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayView");
                overlayView2 = null;
            }
            overlayView2.switchConfirmationMode(true);
            OverlayTextView overlayTextContainer = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
            Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
            ViewExtensionsKt.toVisible$default(overlayTextContainer, false, 1, null);
        }
        int i = getDocumentType() != null ? R.string.onfido_doc_confirmation_image_accessibility : R.string.onfido_selfie_confirmation_image_accessibility;
        View view2 = this.dummyView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyView");
        } else {
            view = view2;
        }
        ViewExtensionsKt.setContentDescription(view, i);
        setTitle(i);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showDialog(LoadingFragment.Companion.DialogMode dialogMode) {
        Intrinsics.checkNotNullParameter(dialogMode, "dialogMode");
        hideLoading();
        showLoadingDialog$onfido_capture_sdk_core_release(dialogMode);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showDocumentFormatDialog() {
        int i;
        DocumentType documentType = getDocumentType();
        int i2 = documentType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[documentType.ordinal()];
        if (i2 == 1) {
            i = R.string.onfido_doc_capture_prompt_title_id;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException(("showDocumentFormatDialog() shouldn't be showed for this " + getDocumentType()).toString());
            }
            i = R.string.onfido_doc_capture_prompt_title_license;
        }
        setDocumentFormat(DocumentFormat.CARD);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        OnfidoViewDocumentFormatSelectionBinding onfidoViewDocumentFormatSelectionBindingInflate = OnfidoViewDocumentFormatSelectionBinding.inflate(bottomSheetDialog.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(onfidoViewDocumentFormatSelectionBindingInflate, "inflate(...)");
        bottomSheetDialog.setContentView(onfidoViewDocumentFormatSelectionBindingInflate.getRoot());
        bottomSheetDialog.setCancelable(false);
        onfidoViewDocumentFormatSelectionBindingInflate.title.setText(i);
        onfidoViewDocumentFormatSelectionBindingInflate.cardFormat.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CaptureActivity.showDocumentFormatDialog$lambda$7$lambda$5(bottomSheetDialog, this, view);
            }
        });
        onfidoViewDocumentFormatSelectionBindingInflate.foldedFormat.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                CaptureActivity.showDocumentFormatDialog$lambda$7$lambda$6(bottomSheetDialog, this, view);
            }
        });
        bottomSheetDialog.show();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showError(ErrorDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        CaptureConfirmationScreen captureConfirmationScreen = getCaptureConfirmationScreen();
        if (captureConfirmationScreen != null) {
            captureConfirmationScreen.showError(descriptor);
        } else {
            setValidationBubbleContent(descriptor.getTitle(), descriptor.getSubtitle());
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showErrorMessage(int titleResId, int messageResId, Function1<? super DialogInterface, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(titleResId), messageResId, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : listener));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showFaceLivenessConfirmationScreen(String dirPath, LivenessPerformedChallenges performedChallanges) {
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(performedChallanges, "performedChallanges");
        Intent intentPutExtra = new Intent().putExtra("video_path", dirPath).putExtra(LivenessConfirmationFragment.ONFIDO_LIVENESS_CHALLENGES_EXTRA, getPresenter$onfido_capture_sdk_core_release().getUploadChallengesList$onfido_capture_sdk_core_release());
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        finishWithResult$onfido_capture_sdk_core_release(-1, intentPutExtra);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showLivenessButtonAndFocusWithDelay() {
        this.handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity$showLivenessButtonAndFocusWithDelay$$inlined$postDelayed$default$1
            @Override // java.lang.Runnable
            public final void run() {
                OnfidoButton onfidoButton = this.this$0.livenessControlButton;
                if (onfidoButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("livenessControlButton");
                    onfidoButton = null;
                }
                ViewExtensionsKt.toVisible$default(onfidoButton, false, 1, null);
                AccessibilityExtensionsKt.sendAccessibilityFocusEvent(onfidoButton);
            }
        }, getOnfidoConfig$onfido_capture_sdk_core_release().getManualLivenessCapture() ? 0L : 5000L);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showVideoRecordCompletionTick() {
        showConfirmationPreview();
        OverlayView overlayView = this.overlayView;
        OverlayView overlayView2 = null;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.onDocumentVideoRecordFinished();
        OverlayView overlayView3 = this.overlayView;
        if (overlayView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
        } else {
            overlayView2 = overlayView3;
        }
        overlayView2.inflateDocumentDetectionTick(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.showVideoRecordCompletionTick.1
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
                CaptureActivity.this.runDocAutoCaptureAccessiblityEvents();
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showVideoRecordingCompleteMessage() {
        OverlayTextView overlayTextContainer = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        OverlayTextView.setMainText$onfido_capture_sdk_core_release$default(overlayTextContainer, R.string.onfido_doc_capture_header_recording_complete, 0, false, 6, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showVideoRecordingInProgressMessage() {
        OverlayTextView overlayTextContainer = getBinding$onfido_capture_sdk_core_release().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        OverlayTextView.setMainText$onfido_capture_sdk_core_release$default(overlayTextContainer, R.string.onfido_doc_capture_header_recording_video, 0, false, 6, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void showVideoRecordingProgressBar() {
        ImageView flipArrow = getBinding$onfido_capture_sdk_core_release().flipArrow;
        Intrinsics.checkNotNullExpressionValue(flipArrow, "flipArrow");
        flipArrow.setVisibility(8);
        LinearLayout root = getBinding$onfido_capture_sdk_core_release().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensionsKt.toVisible$default(root, false, 1, null);
        Completable completableInterruptAnnouncement = getAnnouncementService$onfido_capture_sdk_core_release().interruptAnnouncement();
        AnnouncementService announcementService$onfido_capture_sdk_core_release = getAnnouncementService$onfido_capture_sdk_core_release();
        String string = getString(R.string.onfido_doc_capture_header_capturing);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Completable.concatArray(completableInterruptAnnouncement, AnnouncementService.announceString$default(announcementService$onfido_capture_sdk_core_release, new String[]{string}, false, 2, (Object) null)).blockingAwait();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void startCamera() {
        OverlayView overlayView;
        CameraFactory cameraFactory$onfido_capture_sdk_core_release = getCameraFactory$onfido_capture_sdk_core_release();
        CameraSourcePreview cameraSourcePreview = getBinding$onfido_capture_sdk_core_release().cameraViewCamera1;
        PreviewView previewView = getBinding$onfido_capture_sdk_core_release().cameraViewCameraX;
        CaptureType captureType = getCaptureType();
        OverlayView overlayView2 = this.overlayView;
        OnfidoCamera onfidoCamera = null;
        if (overlayView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        } else {
            overlayView = overlayView2;
        }
        OnfidoCamera onfidoCameraCreate = cameraFactory$onfido_capture_sdk_core_release.create(this, cameraSourcePreview, previewView, overlayView, captureType);
        this.onfidoCamera = onfidoCameraCreate;
        if (onfidoCameraCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
        } else {
            onfidoCamera = onfidoCameraCreate;
        }
        onfidoCamera.start(getCameraFace(), new Function1<OnfidoCamera.CameraStatus, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.startCamera.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.CameraStatus cameraStatus) {
                invoke2(cameraStatus);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.CameraStatus cameraStatus) {
                Intrinsics.checkNotNullParameter(cameraStatus, "cameraStatus");
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.Started.INSTANCE)) {
                    CaptureActivity.this.onCameraStarted();
                    return;
                }
                if (cameraStatus instanceof OnfidoCamera.CameraStatus.Failed) {
                    CaptureActivity captureActivity = CaptureActivity.this;
                    String message = ((OnfidoCamera.CameraStatus.Failed) cameraStatus).getError().getMessage();
                    if (message == null) {
                        message = "";
                    }
                    captureActivity.finishActivityWithException(new UnknownCameraException(message));
                    return;
                }
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotFound.INSTANCE)) {
                    CaptureActivity.this.onCameraNotFound();
                } else if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotAvailable.INSTANCE)) {
                    CaptureActivity.this.onCameraUnavailable();
                }
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void startDocumentVideoRecording() {
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.onDocumentVideoRecordStarted();
        startVideoRecording(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.startDocumentVideoRecording.1
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
                CaptureActivity.this.getPresenter$onfido_capture_sdk_core_release().startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release();
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void startLivenessVideoRecording(boolean isStartedManually) {
        getPresenter$onfido_capture_sdk_core_release().onRecordingStarted$onfido_capture_sdk_core_release(isStartedManually);
        getPresenter$onfido_capture_sdk_core_release().issueNextChallenge$onfido_capture_sdk_core_release();
        ViewUtil.setViewVisibilityWithoutAnimation(getBinding$onfido_capture_sdk_core_release().overlayTextContainer, false);
        LivenessOverlayView livenessOverlayView = getBinding$onfido_capture_sdk_core_release().livenessOverlayView;
        Intrinsics.checkNotNullExpressionValue(livenessOverlayView, "livenessOverlayView");
        ViewExtensionsKt.toVisible$default(livenessOverlayView, false, 1, null);
        getBinding$onfido_capture_sdk_core_release().livenessOverlayView.setListener$onfido_capture_sdk_core_release(this);
        OverlayView overlayView = this.overlayView;
        if (overlayView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayView");
            overlayView = null;
        }
        overlayView.paintCaptureArea();
        startVideoRecording$default(this, null, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void stopCamera() {
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.stop();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void trackNavigationCompleted(PerformanceAnalyticsScreen destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        getScreenLoadTracker$onfido_capture_sdk_core_release().trackNavigationCompleted$onfido_capture_sdk_core_release(destination);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void uploadImage(byte[] jpegData) {
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        OnfidoCaptureValidationBubble postCaptureValidationBubble = getBinding$onfido_capture_sdk_core_release().postCaptureValidationBubble;
        Intrinsics.checkNotNullExpressionValue(postCaptureValidationBubble, "postCaptureValidationBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(postCaptureValidationBubble, OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, false, 2, null);
        if (getCaptureType() == CaptureType.DOCUMENT) {
            uploadDocument(jpegData);
        } else {
            uploadSelfieForValidation(jpegData);
        }
        getPresenter$onfido_capture_sdk_core_release().trackUploadStarted$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View
    public void uploadVideo(String documentId, String videoPath, DocSide docSide, DocumentType docType, CountryCode issuingCountry) {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        Intrinsics.checkNotNullParameter(docType, "docType");
        CaptureUploadService captureUploadService = this.captureUploadService;
        if (captureUploadService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureUploadService");
            captureUploadService = null;
        }
        captureUploadService.uploadDocumentVideo$onfido_capture_sdk_core_release(documentId, videoPath, getSdkUploadMetadata(), getApplicantId(), docSide, docType, issuingCountry);
    }
}
