package com.onfido.android.sdk.capture.ui.camera;

import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorEmpty;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.config.DefaultOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCaseResult;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCaseResult;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationResult;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationTargets;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingFailureCounts;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons;
import com.onfido.android.sdk.capture.ui.camera.UploadBinaryResult;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeType;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.ReciteLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureResultConsumer;
import com.onfido.android.sdk.capture.ui.camera.util.DocumentValidationResultMapper;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.DocumentUtils;
import com.onfido.android.sdk.capture.utils.ErrorTypeUtilsKt;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.ImageUtilsExtKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.ResultReceiverExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.StringRepresentation;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.validation.BackendDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.MRZData;
import com.onfido.android.sdk.capture.validation.MRZDataType;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.android.sdk.capture.validation.device.MRZValidationResult;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.exception.GeoblockedException;
import com.onfido.api.client.exception.HttpParsedException;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.reactivestreams.Publisher;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000à\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u0003\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u0000 ß\u00032\u00020\u0001:\u0006ß\u0003à\u0003á\u0003B\u009b\u0002\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010$\u001a\u00020%\u0012\u0006\u0010&\u001a\u00020'\u0012\u0006\u0010(\u001a\u00020)\u0012\u0006\u0010*\u001a\u00020+\u0012\u0006\u0010,\u001a\u00020-\u0012\u0006\u0010.\u001a\u00020/\u0012\u0006\u00100\u001a\u000201\u0012\u0006\u00102\u001a\u000203\u0012\u0006\u00104\u001a\u000205\u0012\u0006\u00106\u001a\u000207\u0012\u0006\u00108\u001a\u000209\u0012\u0006\u0010:\u001a\u00020;\u0012\u0006\u0010<\u001a\u00020=\u0012\n\b\u0001\u0010>\u001a\u0004\u0018\u00010?\u0012\u0006\u0010@\u001a\u00020A\u0012\u0006\u0010B\u001a\u00020C\u0012\u0006\u0010D\u001a\u00020E¢\u0006\u0002\u0010FJ\u001a\u0010Ì\u0001\u001a\u00030Í\u00012\b\u0010Î\u0001\u001a\u00030¶\u0001H\u0000¢\u0006\u0003\bÏ\u0001J=\u0010Ð\u0001\u001a\u00030Í\u00012\u0007\u0010Ñ\u0001\u001a\u00020i2\b\u0010Ò\u0001\u001a\u00030Ó\u00012\n\b\u0002\u0010Ô\u0001\u001a\u00030Õ\u00012\f\b\u0002\u0010Ö\u0001\u001a\u0005\u0018\u00010×\u0001H\u0000¢\u0006\u0003\bØ\u0001J\n\u0010Ù\u0001\u001a\u00030Í\u0001H\u0002J\u0014\u0010Ú\u0001\u001a\u00030Í\u00012\b\u0010Û\u0001\u001a\u00030\u008d\u0001H\u0002J\n\u0010Ü\u0001\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010Ý\u0001\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÞ\u0001J\n\u0010ß\u0001\u001a\u00030Í\u0001H\u0002J\u0014\u0010à\u0001\u001a\u00030Í\u00012\b\u0010Æ\u0001\u001a\u00030Ç\u0001H\u0002J\n\u0010á\u0001\u001a\u00030Í\u0001H\u0002J\u0010\u0010â\u0001\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bã\u0001J\"\u0010ä\u0001\u001a\u00030Í\u00012\u0007\u0010å\u0001\u001a\u00020a2\u0007\u0010Ñ\u0001\u001a\u00020iH\u0000¢\u0006\u0003\bæ\u0001J\n\u0010ç\u0001\u001a\u00030Í\u0001H\u0002J\n\u0010è\u0001\u001a\u00030Í\u0001H\u0002J\n\u0010é\u0001\u001a\u00030\u0095\u0001H\u0002J \u0010ê\u0001\u001a\u00030Í\u00012\n\u0010Ò\u0001\u001a\u0005\u0018\u00010Ó\u00012\b\u0010Ô\u0001\u001a\u00030Õ\u0001H\u0002J \u0010ë\u0001\u001a\u00030ì\u00012\b\u0010í\u0001\u001a\u00030î\u00012\n\b\u0002\u0010ï\u0001\u001a\u00030\u0095\u0001H\u0002J\u0019\u0010ð\u0001\u001a\t\u0012\u0004\u0012\u00020\\0ñ\u00012\u0007\u0010ò\u0001\u001a\u00020\\H\u0002J\u0011\u0010ó\u0001\u001a\u0004\u0018\u00010\\H\u0000¢\u0006\u0003\bô\u0001JA\u0010õ\u0001\u001a\n\u0012\u0005\u0012\u00030¶\u00010ö\u00012\b\u0010Ò\u0001\u001a\u00030Ó\u00012\f\b\u0002\u0010Ö\u0001\u001a\u0005\u0018\u00010×\u00012\n\u0010Ô\u0001\u001a\u0005\u0018\u00010Õ\u00012\n\b\u0002\u0010÷\u0001\u001a\u00030\u0095\u0001H\u0002J\u001e\u0010ø\u0001\u001a\t\u0012\u0004\u0012\u00020\\0ù\u00012\u0006\u0010U\u001a\u00020VH\u0002¢\u0006\u0003\u0010ú\u0001J\u0018\u0010û\u0001\u001a\u00030ü\u00012\u0006\u0010S\u001a\u00020TH\u0000¢\u0006\u0003\bý\u0001J\n\u0010þ\u0001\u001a\u00030ÿ\u0001H\u0002J\t\u0010\u0080\u0002\u001a\u00020\\H\u0002J/\u0010\u0081\u0002\u001a\u0016\u0012\f\u0012\n u*\u0004\u0018\u00010\\0\\0\u0082\u0002¢\u0006\u0002\bv2\u0007\u0010å\u0001\u001a\u00020a2\u0007\u0010Ñ\u0001\u001a\u00020iH\u0002J-\u0010\u0083\u0002\u001a\n\u0012\u0005\u0012\u00030\u0084\u00020ñ\u00012\n\u0010Ò\u0001\u001a\u0005\u0018\u00010Ó\u00012\b\u0010\u0085\u0002\u001a\u00030Õ\u0001H\u0000¢\u0006\u0003\b\u0086\u0002J\u0010\u0010\u0087\u0002\u001a\u00030\u0088\u0002H\u0000¢\u0006\u0003\b\u0089\u0002J\n\u0010\u008a\u0002\u001a\u00030\u0095\u0001H\u0002J\u0013\u0010\u008b\u0002\u001a\u00030Í\u00012\u0007\u0010\u008c\u0002\u001a\u00020\\H\u0002J\n\u0010\u008d\u0002\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010\u008e\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u008f\u0002J\n\u0010\u0090\u0002\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0091\u0002\u001a\u00030\u0095\u0001H\u0002J\u001a\u0010\u0092\u0002\u001a\u00030\u0095\u00012\b\u0010Î\u0001\u001a\u00030¶\u0001H\u0000¢\u0006\u0003\b\u0093\u0002J\n\u0010\u0094\u0002\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0095\u0002\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010\u0096\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u0097\u0002J\n\u0010\u0098\u0002\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0099\u0002\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u009a\u0002\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010\u009b\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b\u009c\u0002J\n\u0010\u009d\u0002\u001a\u00030Í\u0001H\u0002J\n\u0010\u009e\u0002\u001a\u00030Í\u0001H\u0002J\u0010\u0010\u009f\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b \u0002J\u001a\u0010¡\u0002\u001a\u00030Í\u00012\b\u0010Æ\u0001\u001a\u00030Ç\u0001H\u0001¢\u0006\u0003\b¢\u0002J\u0010\u0010£\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¤\u0002J\n\u0010¥\u0002\u001a\u00030Í\u0001H\u0016J\n\u0010¦\u0002\u001a\u00030Í\u0001H\u0016J\u001a\u0010§\u0002\u001a\u00030Í\u00012\b\u0010¨\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b©\u0002J\u0010\u0010ª\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b«\u0002J\u0010\u0010¬\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b\u00ad\u0002J\u0018\u0010®\u0002\u001a\u00030Í\u00012\u0006\u0010{\u001a\u00020\\H\u0000¢\u0006\u0003\b¯\u0002J\u0014\u0010°\u0002\u001a\u00030Í\u00012\b\u0010±\u0002\u001a\u00030²\u0002H\u0002J\u0010\u0010³\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b´\u0002J\u0010\u0010µ\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¶\u0002J\u0010\u0010·\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¸\u0002J\u0010\u0010¹\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bº\u0002J\b\u0010»\u0002\u001a\u00030Í\u0001J\b\u0010¼\u0002\u001a\u00030Í\u0001J\b\u0010½\u0002\u001a\u00030Í\u0001J\u0010\u0010¾\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¿\u0002J\u0010\u0010À\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÁ\u0002J\u0010\u0010Â\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÃ\u0002J\u001a\u0010Ä\u0002\u001a\u00030Í\u00012\b\u0010Å\u0002\u001a\u00030Æ\u0002H\u0000¢\u0006\u0003\bÇ\u0002J\u001a\u0010È\u0002\u001a\u00030Í\u00012\b\u0010É\u0002\u001a\u00030Ê\u0002H\u0000¢\u0006\u0003\bË\u0002J\u0019\u0010Ì\u0002\u001a\u00030Í\u00012\u0007\u0010Ñ\u0001\u001a\u00020iH\u0000¢\u0006\u0003\bÍ\u0002J\u001a\u0010Î\u0002\u001a\u00030Í\u00012\b\u0010Ï\u0002\u001a\u00030\u00ad\u0001H\u0000¢\u0006\u0003\bÐ\u0002J\u0010\u0010Ñ\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÒ\u0002J\u0019\u0010Ó\u0002\u001a\u00030Í\u00012\u0007\u0010Ô\u0002\u001a\u00020ZH\u0000¢\u0006\u0003\bÕ\u0002J\u001d\u0010Ö\u0002\u001a\u00030Í\u00012\b\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010Ñ\u0001\u001a\u00020iH\u0002J\u001c\u0010×\u0002\u001a\u00030Í\u00012\n\b\u0002\u0010Ø\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bÙ\u0002J0\u0010Ú\u0002\u001a\u00030Í\u00012\b\u0010Ô\u0001\u001a\u00030Õ\u00012\n\b\u0002\u0010Û\u0002\u001a\u00030\u0095\u00012\b\u0010Ü\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bÝ\u0002J\u001a\u0010Þ\u0002\u001a\u00030Í\u00012\b\u0010ß\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bà\u0002J\u001a\u0010á\u0002\u001a\u00030Í\u00012\b\u0010ß\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bâ\u0002J!\u0010ã\u0002\u001a\u00030Í\u00012\u0007\u0010ä\u0002\u001a\u00020c2\u0006\u0010U\u001a\u00020VH\u0000¢\u0006\u0003\bå\u0002J\u0019\u0010æ\u0002\u001a\u00030Í\u00012\u0007\u0010ç\u0002\u001a\u00020\\H\u0000¢\u0006\u0003\bè\u0002J\u0010\u0010é\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bê\u0002J\u0018\u0010ë\u0002\u001a\u00030Í\u00012\u0006\u0010U\u001a\u00020VH\u0000¢\u0006\u0003\bì\u0002J\u001a\u0010í\u0002\u001a\u00030Í\u00012\u0007\u0010Ñ\u0001\u001a\u00020iH\u0082@¢\u0006\u0003\u0010î\u0002J\u0014\u0010ï\u0002\u001a\u00030Í\u00012\b\u0010ð\u0002\u001a\u00030Æ\u0002H\u0002J\u0010\u0010ñ\u0002\u001a\u00030ò\u0002H\u0000¢\u0006\u0003\bó\u0002J\u0018\u0010ñ\u0002\u001a\u00030ò\u00022\u0006\u0010S\u001a\u00020TH\u0000¢\u0006\u0003\bó\u0002J\u001c\u0010ô\u0002\u001a\u00030Í\u00012\n\u0010õ\u0002\u001a\u0005\u0018\u00010ö\u0002H\u0000¢\u0006\u0003\b÷\u0002JD\u0010ø\u0002\u001a\u00030Í\u00012\b\u0010È\u0001\u001a\u00030É\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010S\u001a\u00020T2\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00012\n\b\u0002\u0010\u009e\u0001\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bù\u0002J\u0010\u0010ú\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bû\u0002J\u0010\u0010ü\u0002\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\bý\u0002J(\u0010þ\u0002\u001a\u00030\u0095\u00012\n\u0010Ò\u0001\u001a\u0005\u0018\u00010Ó\u00012\n\u0010Ö\u0001\u001a\u0005\u0018\u00010×\u0001H\u0000¢\u0006\u0003\bÿ\u0002J\n\u0010\u0080\u0003\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0081\u0003\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0082\u0003\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010\u0083\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u0084\u0003J\u0010\u0010\u0085\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u0086\u0003J\n\u0010\u0087\u0003\u001a\u00030\u0095\u0001H\u0002J\n\u0010\u0088\u0003\u001a\u00030\u0095\u0001H\u0002J$\u0010\u0089\u0003\u001a\u00030\u0095\u00012\b\u0010\u008a\u0003\u001a\u00030\u0095\u00012\b\u0010\u008b\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u008c\u0003J$\u0010\u008d\u0003\u001a\u00030\u0095\u00012\b\u0010\u008a\u0003\u001a\u00030\u0095\u00012\b\u0010\u008b\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u008e\u0003J\u001c\u0010\u008f\u0003\u001a\u00030\u0095\u00012\n\u0010Ò\u0001\u001a\u0005\u0018\u00010Ó\u0001H\u0000¢\u0006\u0003\b\u0090\u0003J$\u0010\u0091\u0003\u001a\u00030\u0095\u00012\b\u0010\u008a\u0003\u001a\u00030\u0095\u00012\b\u0010\u008b\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u0092\u0003J2\u0010\u0093\u0003\u001a\u00030\u0095\u00012\b\u0010\u008a\u0003\u001a\u00030\u0095\u00012\b\u0010\u008b\u0003\u001a\u00030\u0095\u00012\b\u0010Ò\u0001\u001a\u00030Ó\u00012\b\u0010Ö\u0001\u001a\u00030×\u0001H\u0002J$\u0010\u0094\u0003\u001a\u00030\u0095\u00012\b\u0010\u008a\u0003\u001a\u00030\u0095\u00012\b\u0010\u008b\u0003\u001a\u00030\u0095\u0001H\u0000¢\u0006\u0003\b\u0095\u0003J\n\u0010\u0096\u0003\u001a\u00030\u0095\u0001H\u0002J\u0019\u0010\u0097\u0003\u001a\u00030Í\u00012\u0007\u0010ä\u0002\u001a\u00020cH\u0000¢\u0006\u0003\b\u0098\u0003J\u0013\u0010\u0099\u0003\u001a\u00030Í\u00012\u0007\u0010\u009a\u0003\u001a\u00020\\H\u0002J\u0019\u0010\u009b\u0003\u001a\u00030Í\u00012\u0007\u0010\u009a\u0003\u001a\u00020\\H\u0000¢\u0006\u0003\b\u009c\u0003J\n\u0010\u009d\u0003\u001a\u00030Í\u0001H\u0002J@\u0010\u009e\u0003\u001a\u00030Í\u00012\u0007\u0010\u009f\u0003\u001a\u00020\\2\u0007\u0010 \u0003\u001a\u00020c2\b\u0010¡\u0003\u001a\u00030\u0095\u00012\n\u0010¢\u0003\u001a\u0005\u0018\u00010£\u00032\f\b\u0002\u0010í\u0001\u001a\u0005\u0018\u00010î\u0001H\u0002J\n\u0010¤\u0003\u001a\u00030Í\u0001H\u0002J\u0010\u0010¥\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¦\u0003J\u001a\u0010§\u0003\u001a\u00030Í\u00012\b\u0010¨\u0003\u001a\u00030©\u0003H\u0000¢\u0006\u0003\bª\u0003J\n\u0010«\u0003\u001a\u00030Í\u0001H\u0002J\n\u0010¬\u0003\u001a\u00030Í\u0001H\u0002J\u0010\u0010\u00ad\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b®\u0003J\n\u0010¯\u0003\u001a\u00030Í\u0001H\u0002J\u0010\u0010°\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b±\u0003J\n\u0010²\u0003\u001a\u00030Í\u0001H\u0002J\n\u0010³\u0003\u001a\u00030Í\u0001H\u0002J\n\u0010´\u0003\u001a\u00030Í\u0001H\u0002J\u0010\u0010µ\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b¶\u0003J\n\u0010·\u0003\u001a\u00030Í\u0001H\u0002J\t\u0010¸\u0003\u001a\u00020\\H\u0002J\u0010\u0010¹\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bº\u0003J\u0012\u0010»\u0003\u001a\u00030Í\u00012\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010¼\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b½\u0003J\u0018\u0010¾\u0003\u001a\u00030Í\u00012\u0006\u0010U\u001a\u00020VH\u0000¢\u0006\u0003\b¿\u0003J#\u0010¾\u0003\u001a\u00030Í\u00012\u0006\u0010U\u001a\u00020V2\t\u0010ä\u0002\u001a\u0004\u0018\u00010cH\u0000¢\u0006\u0003\b¿\u0003J\u0010\u0010À\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÁ\u0003J\u0014\u0010Â\u0003\u001a\u00030Í\u00012\b\u0010Ã\u0003\u001a\u00030\u0095\u0001H\u0002J\n\u0010Ä\u0003\u001a\u00030Í\u0001H\u0002J\u0013\u0010Å\u0003\u001a\u00030Í\u00012\u0007\u0010Æ\u0003\u001a\u00020TH\u0002J\u001c\u0010Ç\u0003\u001a\u00030Í\u00012\b\u0010Ã\u0003\u001a\u00030\u0095\u00012\u0006\u0010U\u001a\u00020VH\u0002J#\u0010È\u0003\u001a\u00030Í\u00012\u0007\u0010É\u0003\u001a\u00020P2\b\u0010Ê\u0003\u001a\u00030Ë\u0003H\u0000¢\u0006\u0003\bÌ\u0003J\u0010\u0010Í\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÎ\u0003J\u001b\u0010Ï\u0003\u001a\u00030Í\u00012\u0006\u0010U\u001a\u00020V2\u0007\u0010ä\u0002\u001a\u00020cH\u0002J\n\u0010Ð\u0003\u001a\u00030Í\u0001H\u0002J\u0010\u0010Ñ\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÒ\u0003J\u001a\u0010Ó\u0003\u001a\u00030Í\u00012\b\u0010Ô\u0003\u001a\u00030\u008d\u0001H\u0000¢\u0006\u0003\bÕ\u0003J\u001a\u0010Ö\u0003\u001a\u00030Í\u00012\b\u0010Ô\u0003\u001a\u00030\u008d\u0001H\u0000¢\u0006\u0003\b×\u0003J\u0010\u0010Ø\u0003\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÙ\u0003J\n\u0010Ú\u0003\u001a\u00030Í\u0001H\u0002J$\u0010Û\u0003\u001a\u00030Í\u00012\u000f\u0010Ü\u0003\u001a\n\u0012\u0005\u0012\u00030\u0084\u00020ñ\u00012\u0007\u0010å\u0001\u001a\u00020aH\u0002J\u0019\u0010Ý\u0003\u001a\u00030Í\u00012\u0007\u0010å\u0001\u001a\u00020aH\u0000¢\u0006\u0003\bÞ\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010HX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010I\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bK\u0010LR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020P8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u000e\u0010S\u001a\u00020TX\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010U\u001a\u00020V8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0010\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010]\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u0010N\u001a\u0004\b^\u0010LR\u0010\u0010`\u001a\u0004\u0018\u00010aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010b\u001a\u0004\u0018\u00010cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u001e\u0010j\u001a\u00020i2\u0006\u0010h\u001a\u00020i@BX\u0080.¢\u0006\b\n\u0000\u001a\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020nX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010s\u001a\u0015\u0012\f\u0012\n u*\u0004\u0018\u00010i0i0t¢\u0006\u0002\bvX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020xX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010y\u001a\u0004\u0018\u00010zX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010|\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b~\u0010N\u001a\u0004\b}\u0010LR\u0011\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020PX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0083\u00012\t\u0010h\u001a\u0005\u0018\u00010\u0083\u00018\u0000@BX\u0081\u000e¢\u0006\u0012\n\u0000\u0012\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u001e\u0010\u0089\u0001\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008b\u0001\u0010N\u001a\u0005\b\u008a\u0001\u0010LR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u008e\u0001\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0090\u0001\u0010N\u001a\u0005\b\u008f\u0001\u0010LR\u0010\u0010\u0091\u0001\u001a\u00030\u008d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R \u0010\u009a\u0001\u001a\u00030\u0095\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009b\u0001\u0010\u0097\u0001\"\u0006\b\u009c\u0001\u0010\u0099\u0001R\u0010\u0010\u009d\u0001\u001a\u00030\u0095\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u009e\u0001\u001a\u00030\u0095\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u009f\u0001\u001a\u00030\u008d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u0001\u001a\u00020J8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b¢\u0001\u0010N\u001a\u0005\b¡\u0001\u0010LR\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R1\u0010£\u0001\u001a$\u0012\u0005\u0012\u00030¥\u0001\u0012\u0005\u0012\u00030¦\u00010¤\u0001j\u0011\u0012\u0005\u0012\u00030¥\u0001\u0012\u0005\u0012\u00030¦\u0001`§\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010¨\u0001\u001a\u0005\u0018\u00010©\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010ª\u0001\u001a\u00030«\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010¬\u0001\u001a\u0017\u0012\u000e\u0012\f u*\u0005\u0018\u00010\u00ad\u00010\u00ad\u00010t¢\u0006\u0002\bvX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010®\u0001\u001a\u00020\\8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b±\u0001\u0010N\u001a\u0006\b¯\u0001\u0010°\u0001R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010²\u0001\u001a\u00030³\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010´\u0001\u001a\u00020PX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010µ\u0001\u001a\u00030¶\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010·\u0001\u001a\u00020PX\u0080\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b¸\u0001\u0010R\"\u0006\b¹\u0001\u0010º\u0001R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010»\u0001\u001a\u00030\u0095\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010¾\u0001\u001a\u00030½\u00012\b\u0010¼\u0001\u001a\u00030½\u00018@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\b¿\u0001\u0010À\u0001\"\u0006\bÁ\u0001\u0010Â\u0001R\u001e\u0010Ã\u0001\u001a\u00020PX\u0080\u000e¢\u0006\u0011\n\u0000\u001a\u0005\bÄ\u0001\u0010R\"\u0006\bÅ\u0001\u0010º\u0001R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010È\u0001\u001a\u00030É\u0001X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010Ê\u0001\u001a\u00030Ë\u0001X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006â\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter;", "Lcom/onfido/android/sdk/capture/ui/camera/ConfirmationStepButtons$Listener;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "rectangleDetector", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", "accessibleDocumentCaptureUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase;", "livenessInteractor", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessInteractor;", "backendDocumentValidationsManager", "Lcom/onfido/android/sdk/capture/validation/BackendDocumentValidationsManager;", "documentProcessingUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase;", "postCaptureDocumentValidationsManager", "Lcom/onfido/android/sdk/capture/validation/PostCaptureDocumentValidationsManager;", "permissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "faceDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "sdkUploadMetaDataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "documentService", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentService;", "nfcPropertiesService", "Lcom/onfido/android/sdk/capture/ui/camera/NfcPropertiesService;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "documentDelayTransformer", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "faceProcessingUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "documentProcessingFailureAnalyzer", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;", "retainableValidationsResult", "Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "barcodeValidationSuspender", "Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "captureTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "mediaCallback", "Landroid/os/ResultReceiver;", "mrzDocumentExtractor", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "documentValidationUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationUseCase;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase;Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessInteractor;Lcom/onfido/android/sdk/capture/validation/BackendDocumentValidationsManager;Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase;Lcom/onfido/android/sdk/capture/validation/PostCaptureDocumentValidationsManager;Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;Lcom/onfido/android/sdk/capture/ui/camera/DocumentService;Lcom/onfido/android/sdk/capture/ui/camera/NfcPropertiesService;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;Lcom/onfido/android/sdk/capture/utils/ImageUtils;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;Landroid/os/ResultReceiver;Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationUseCase;Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;)V", "autoCaptureJob", "Lkotlinx/coroutines/Job;", "autocaptureCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getAutocaptureCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "autocaptureCompositeDisposable$delegate", "Lkotlin/Lazy;", "captureErrorMessage", "", "getCaptureErrorMessage", "()I", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getCaptureType", "()Lcom/onfido/android/sdk/capture/ui/CaptureType;", "capturedImage", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "capturedVideoFilePath", "", "compositeSubscription", "getCompositeSubscription", "compositeSubscription$delegate", "croppedImage", "", "currentCaptureFlowError", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "getCurrentCaptureFlowError$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/upload/ErrorType;", "setCurrentCaptureFlowError$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;)V", "<set-?>", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "docFrame", "getDocFrame$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "documentCaptureResultConsumer", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureResultConsumer;", "getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureResultConsumer;", "setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureResultConsumer;)V", "documentFrameBehaviorSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "documentValidaMapper", "Lcom/onfido/android/sdk/capture/ui/camera/util/DocumentValidationResultMapper;", "documentValidationTarget", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;", "documentVideoId", "documentVideoRecordingCompositeDisposable", "getDocumentVideoRecordingCompositeDisposable", "documentVideoRecordingCompositeDisposable$delegate", "edgeDetectionFallbackTimerDisposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "emittedFramesCount", "extraFileInfo", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "extractedMRZDocument", "getExtractedMRZDocument$onfido_capture_sdk_core_release$annotations", "()V", "getExtractedMRZDocument$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "faceDetectionCompositeDisposable", "getFaceDetectionCompositeDisposable", "faceDetectionCompositeDisposable$delegate", "faceSelfieUploadStartTime", "", "faceTrackingCompositeDisposable", "getFaceTrackingCompositeDisposable", "faceTrackingCompositeDisposable$delegate", "firstFrameEmitTime", "iqsUploadParser", "Lcom/onfido/android/sdk/capture/ui/camera/IQSUploadErrorParser;", "isAutoCaptured", "", "isAutoCaptured$onfido_capture_sdk_core_release", "()Z", "setAutoCaptured$onfido_capture_sdk_core_release", "(Z)V", "isDisplayingOverlay", "isDisplayingOverlay$onfido_capture_sdk_core_release", "setDisplayingOverlay$onfido_capture_sdk_core_release", "isMRZExtractionTimeExceeded", "isProofOfAddress", "livenessPreviousChallengeCompleted", "movementChallengeCompositeDisposable", "getMovementChallengeCompositeDisposable", "movementChallengeCompositeDisposable$delegate", "mrzExtractionResultMap", "Ljava/util/HashMap;", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "Lkotlin/collections/HashMap;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "overlayMetricsBehaviorSubject", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "poaCaptureSessionId", "getPoaCaptureSessionId", "()Ljava/lang/String;", "poaCaptureSessionId$delegate", "presenterScope", "Lkotlinx/coroutines/CoroutineScope;", "processedFramesCount", "processingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "rejectionCount", "getRejectionCount$onfido_capture_sdk_core_release", "setRejectionCount$onfido_capture_sdk_core_release", "(I)V", "shouldWaitForMRZExtractionResult", "value", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$State;", "state", "getState$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$State;", "setState$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$State;)V", "takenPhotoCount", "getTakenPhotoCount$onfido_capture_sdk_core_release", "setTakenPhotoCount$onfido_capture_sdk_core_release", "uploadBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult;", "view", "Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$View;", "visibleRect", "Landroid/graphics/RectF;", "analyseProcessingResults", "", "results", "analyseProcessingResults$onfido_capture_sdk_core_release", "applyPostCaptureValidations", "frame", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "docSide", "Lcom/onfido/api/client/data/DocSide;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "applyPostCaptureValidations$onfido_capture_sdk_core_release", "applyValidations", "applyValidationsAfterAnimationDelay", "animationDelay", "backSideCaptureNeeded", "callMediaCallback", "callMediaCallback$onfido_capture_sdk_core_release", "checkDocumentFormat", "checkUploadBinaryResult", "checkUploading", "clearEdges", "clearEdges$onfido_capture_sdk_core_release", "cropImageAndSaveToFile", "jpegData", "cropImageAndSaveToFile$onfido_capture_sdk_core_release", "disposeAutocaptureSubscriptions", "disposeFaceDetectionSubscriptions", "edgeDetectionTimeoutNotStarted", "enableAccessibilityCapture", "getDocumentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "hasNfc", "getDocumentIdsForDocumentResponse", "", "uploadedMediaId", "getExtraFileInfo", "getExtraFileInfo$onfido_capture_sdk_core_release", "getImageProcessingObservable", "Lio/reactivex/rxjava3/core/Observable;", "enableManualFallback", "getMissingPermissions", "", "(Lcom/onfido/android/sdk/capture/ui/CaptureType;)[Ljava/lang/String;", "getOvalCaptureContentDescription", "Lcom/onfido/android/sdk/capture/utils/StringRepresentation;", "getOvalCaptureContentDescription$onfido_capture_sdk_core_release", "getPerformanceTrackingScreen", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "getPoaCaptureName", "getPoaFileNameAfterCropping", "Lio/reactivex/rxjava3/core/Single;", "getRequiredDocumentValidations", "Lcom/onfido/android/sdk/capture/validation/Validation;", "documentSide", "getRequiredDocumentValidations$onfido_capture_sdk_core_release", "getUploadChallengesList", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "getUploadChallengesList$onfido_capture_sdk_core_release", "hasNativeLibrary", "imageSavedSuccessfully", ReactNativeBridgeUtiles.KEY_FILE_NAME, "isBackSideOfRomanianNationalId", "isCameraXEnabled", "isCameraXEnabled$onfido_capture_sdk_core_release", "isCheckingImageQuality", "isDocumentCapture", "isDocumentFrameValidForAutoCapture", "isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release", "isDocumentUploaded", "isFinalStepForDocument", "isFourByThreeEnabled", "isFourByThreeEnabled$onfido_capture_sdk_core_release", "isMRZExtracted", "isMlModelAutoCaptureEnabled", "isMrzDetectionEnabled", "issueNextChallenge", "issueNextChallenge$onfido_capture_sdk_core_release", "observeAutoCapture", "observeFaceOut", "onAutoLivenessRecordingStart", "onAutoLivenessRecordingStart$onfido_capture_sdk_core_release", "onBinaryUploaded", "onBinaryUploaded$onfido_capture_sdk_core_release", "onCameraStarted", "onCameraStarted$onfido_capture_sdk_core_release", "onCaptureConfirmed", "onCaptureDiscarded", "onCaptureScreenResumedAfterCameraInitialized", "wasConfirmationScreenShown", "onCaptureScreenResumedAfterCameraInitialized$onfido_capture_sdk_core_release", "onConfirmationStepTracking", "onConfirmationStepTracking$onfido_capture_sdk_core_release", "onDestroy", "onDestroy$onfido_capture_sdk_core_release", "onDocumentVideoUploaded", "onDocumentVideoUploaded$onfido_capture_sdk_core_release", "onError", "throwable", "", "onErrorPictureTaking", "onErrorPictureTaking$onfido_capture_sdk_core_release", "onErrorVideoTaking", "onErrorVideoTaking$onfido_capture_sdk_core_release", "onFaceDetected", "onFaceDetected$onfido_capture_sdk_core_release", "onFaceSelfieUploaded", "onFaceSelfieUploaded$onfido_capture_sdk_core_release", "onFlowUserExit", "onFlowUserExitCancel", "onFlowUserExitConfirmed", "onGeneralUploadError", "onGeneralUploadError$onfido_capture_sdk_core_release", "onManualLivenessNextClick", "onManualLivenessNextClick$onfido_capture_sdk_core_release", "onManualLivenessRecordingStart", "onManualLivenessRecordingStart$onfido_capture_sdk_core_release", "onNextChallenge", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "onNextChallenge$onfido_capture_sdk_core_release", "onNextFaceDetectionFrame", "frameData", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "onNextFaceDetectionFrame$onfido_capture_sdk_core_release", "onNextFrame", "onNextFrame$onfido_capture_sdk_core_release", "onOverlayMetricsChanged", "overlayMetrics", "onOverlayMetricsChanged$onfido_capture_sdk_core_release", "onPause", "onPause$onfido_capture_sdk_core_release", "onPictureCaptured", MimeTypes.BASE_TYPE_IMAGE, "onPictureCaptured$onfido_capture_sdk_core_release", "onPostCaptureValidationsFinished", "onRecordingStarted", "isStartedManually", "onRecordingStarted$onfido_capture_sdk_core_release", "onStart", "isFirstStart", "isOnConfirmationStep", "onStart$onfido_capture_sdk_core_release", "onUploadFailure", "isOnCaptureScreen", "onUploadFailure$onfido_capture_sdk_core_release", "onUploadFailureWithGeoblocking", "onUploadFailureWithGeoblocking$onfido_capture_sdk_core_release", "onUploadValidationError", "errorType", "onUploadValidationError$onfido_capture_sdk_core_release", "onVideoCaptured", "filePath", "onVideoCaptured$onfido_capture_sdk_core_release", "onVideoRecordingCanceled", "onVideoRecordingCanceled$onfido_capture_sdk_core_release", "onViewResumed", "onViewResumed$onfido_capture_sdk_core_release", "processFrameForMRZ", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pushPerformedChallenge", "livenessChallenge", "sdkUploadMetadata", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "sdkUploadMetadata$onfido_capture_sdk_core_release", "setMRZResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "setMRZResult$onfido_capture_sdk_core_release", "setup", "setup$onfido_capture_sdk_core_release", "setupUIState", "setupUIState$onfido_capture_sdk_core_release", "shouldAutoCaptureDocument", "shouldAutoCaptureDocument$onfido_capture_sdk_core_release", "shouldAutocapture", "shouldAutocapture$onfido_capture_sdk_core_release", "shouldEnableAccessibilityCapture", "shouldForceRetry", "shouldGetNfcProperties", "shouldHideManualDocumentCaptureButton", "shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release", "shouldPerformFaceValidation", "shouldPerformFaceValidation$onfido_capture_sdk_core_release", "shouldRecordDocumentVideo", "shouldScanNfc", "shouldShowFrenchDLOverlay", "isFrontSide", "isOverlayGone", "shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release", "shouldShowGermanDLOverlay", "shouldShowGermanDLOverlay$onfido_capture_sdk_core_release", "shouldShowInitialOverlay", "shouldShowInitialOverlay$onfido_capture_sdk_core_release", "shouldShowItalianIDOverlay", "shouldShowItalianIDOverlay$onfido_capture_sdk_core_release", "shouldShowOverlay", "shouldShowSouthAfricanIDOverlay", "shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release", "shouldUploadDocument", "showErrorType", "showErrorType$onfido_capture_sdk_core_release", "showLoading", "reason", "showLoadingDialog", "showLoadingDialog$onfido_capture_sdk_core_release", "showMRZWarning", "showWarningBinaryResult", "documentId", "warning", "nfcSupported", "warningsBundle", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "startDocumentVideoRecording", "startDocumentVideoRecordingTimer", "startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release", "startLivenessProcessing", "livenessChallengesViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "startLivenessProcessing$onfido_capture_sdk_core_release", "startManualFallbackTimer", "startMovementChallengeTimeout", "startOverlayDisplayTimer", "startOverlayDisplayTimer$onfido_capture_sdk_core_release", "startVideoConfirmationTickTimer", "stop", "stop$onfido_capture_sdk_core_release", "stopDocumentRecording", "stopDocumentVideoRecordingAndCameraFeed", "stopDocumentVideoRecordingSubscription", "stopFaceTracking", "stopFaceTracking$onfido_capture_sdk_core_release", "stopMovementChallengeTimeout", "toStep", "trackAutocaptureShutterButtonClick", "trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release", "trackCapture", "trackCaptureBackButtonClicked", "trackCaptureBackButtonClicked$onfido_capture_sdk_core_release", "trackCaptureError", "trackCaptureError$onfido_capture_sdk_core_release", "trackConfirmationBackButtonClicked", "trackConfirmationBackButtonClicked$onfido_capture_sdk_core_release", "trackDocumentCapture", "isPortrait", "trackDocumentCaptureFlowCompleted", "trackDocumentConfirmation", "documentData", "trackFaceConfirmation", "trackLivenessChallenge", "challengeIndex", "challengeType", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "trackLivenessChallenge$onfido_capture_sdk_core_release", "trackUploadStarted", "trackUploadStarted$onfido_capture_sdk_core_release", "trackUploadValidationError", "trackVideoButtonClicked", "trackVideoCaptureTimeout", "trackVideoCaptureTimeout$onfido_capture_sdk_core_release", "trackVideoFinishButtonClicked", "duration", "trackVideoFinishButtonClicked$onfido_capture_sdk_core_release", "trackVideoNextButtonClicked", "trackVideoNextButtonClicked$onfido_capture_sdk_core_release", "trackVideoTimeoutRetryButtonClicked", "trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release", "trackWaitingScreenCompleted", "uploadDocumentMedia", "validations", "uploadImageForValidation", "uploadImageForValidation$onfido_capture_sdk_core_release", "Companion", "State", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CapturePresenter implements ConfirmationStepButtons.Listener {
    public static final long CONFIRMATION_VIEW_ANIM_DELAY = 1200;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DOCUMENT_PREFIX = "onfido-document";
    private static final String DOC_POA_PHOTO_PREFIX = "ONFIDO_POA_IMG_";
    private static final List<MRZDataType> DUTCH_ID_MRZ_REQUIRED_FIELDS;
    public static final long FACE_DETECTION_SAMPLING_PERIOD_MS = 200;
    public static final long FACE_DETECTION_TIMEOUT_MS = 5000;
    public static final long FACE_TRACKING_TIMEOUT_MS = 5000;
    public static final long FAILURE_RECORDING_INCREASE_IN_MS = 1500;
    private static final String JPEG_EXTENSION = "jpeg";
    public static final long MANUAL_FALLBACK_DELAY_MS = 7000;
    public static final long MAXIMUM_RECORDING_DURATION_IN_MS = 5000;
    private static final String MRZ_IS_NOT_READABLE = "0";
    private static final String MRZ_IS_READABLE = "1";
    private static final String NFC_LOGGER = "NFC Logger";
    public static final long OVERLAY_DELAY_MS = 4000;
    private static final List<MRZDataType> PASSPORT_MRZ_REQUIRED_FIELDS;
    public static final long PASSPORT_OVERLAY_DELAY_MS = 3000;
    private static final String POA_DEBUG = "POA_Debug";
    public static final String SELFIE_PREFIX = "onfido-selfie";
    public static final long VIDEO_RECORDING_SIZE_CHECK_INTERVAL_MS = 1000;
    private final AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase;
    private final AnnouncementService announcementService;
    private Job autoCaptureJob;

    /* renamed from: autocaptureCompositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy autocaptureCompositeDisposable;
    private final BackendDocumentValidationsManager backendDocumentValidationsManager;
    private final BarcodeValidationSuspender barcodeValidationSuspender;
    private CaptureStepDataBundle captureStepDataBundle;
    private final CaptureTracker captureTracker;
    private OnfidoImage capturedImage;
    private String capturedVideoFilePath;

    /* renamed from: compositeSubscription$delegate, reason: from kotlin metadata */
    private final Lazy compositeSubscription;
    private byte[] croppedImage;
    private ErrorType currentCaptureFlowError;
    private DocumentDetectionFrame docFrame;
    public DocumentCaptureResultConsumer documentCaptureResultConsumer;
    private final DocumentConfigurationManager documentConfigurationManager;
    private final DocumentCaptureDelayTransformer documentDelayTransformer;
    private final BehaviorSubject<DocumentDetectionFrame> documentFrameBehaviorSubject;
    private final DocumentProcessingResultsFailureAnalyzer documentProcessingFailureAnalyzer;
    private final DocumentProcessingUseCase documentProcessingUseCase;
    private final DocumentService documentService;
    private final DocumentValidationResultMapper documentValidaMapper;
    private DocumentValidationTargets documentValidationTarget;
    private final DocumentValidationUseCase documentValidationUseCase;
    private String documentVideoId;

    /* renamed from: documentVideoRecordingCompositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy documentVideoRecordingCompositeDisposable;
    private Disposable edgeDetectionFallbackTimerDisposable;
    private int emittedFramesCount;
    private String extraFileInfo;
    private MRZDocument extractedMRZDocument;

    /* renamed from: faceDetectionCompositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy faceDetectionCompositeDisposable;
    private final FaceDetector faceDetector;
    private final FaceProcessingUseCase faceProcessingUseCase;
    private long faceSelfieUploadStartTime;

    /* renamed from: faceTrackingCompositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy faceTrackingCompositeDisposable;
    private long firstFrameEmitTime;
    private final FlowTracker flowTracker;
    private final IdentityInteractor identityInteractor;
    private final ImageUtils imageUtils;
    private final IQSUploadErrorParser iqsUploadParser;
    private boolean isAutoCaptured;
    private boolean isDisplayingOverlay;
    private boolean isMRZExtractionTimeExceeded;
    private boolean isProofOfAddress;
    private final LivenessInteractor livenessInteractor;
    private long livenessPreviousChallengeCompleted;
    private final LivenessTracker livenessTracker;
    private final ResultReceiver mediaCallback;

    /* renamed from: movementChallengeCompositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy movementChallengeCompositeDisposable;
    private final MRZDocumentExtractor mrzDocumentExtractor;
    private final HashMap<MRZDataType, MRZData> mrzExtractionResultMap;
    private final NativeDetector nativeDetector;
    private NfcArguments nfcArguments;
    private final NfcInteractor nfcInteractor;
    private final NfcPropertiesService nfcPropertiesService;
    private final NfcTracker nfcTracker;
    private OnfidoConfig onfidoConfig;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final BehaviorSubject<OverlayMetrics> overlayMetricsBehaviorSubject;
    private final RuntimePermissionsManager permissionsManager;

    /* renamed from: poaCaptureSessionId$delegate, reason: from kotlin metadata */
    private final Lazy poaCaptureSessionId;
    private final PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager;
    private final CoroutineScope presenterScope;
    private int processedFramesCount;
    private DocumentProcessingResults processingResults;
    private final RectangleDetector rectangleDetector;
    private int rejectionCount;
    private final RetainableValidationsResult retainableValidationsResult;
    private final SchedulersProvider schedulersProvider;
    private final SdkUploadMetadataHelper sdkUploadMetaDataHelper;
    private boolean shouldWaitForMRZExtractionResult;
    private int takenPhotoCount;
    private final TimeProvider timeProvider;
    private UploadBinaryResult uploadBinaryResult;
    private View view;
    private RectF visibleRect;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u001b\u001a\u00020\u001c*\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0\u001dj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e`\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$Companion;", "", "()V", "CONFIRMATION_VIEW_ANIM_DELAY", "", "DOCUMENT_PREFIX", "", "DOC_POA_PHOTO_PREFIX", "DUTCH_ID_MRZ_REQUIRED_FIELDS", "", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "FACE_DETECTION_SAMPLING_PERIOD_MS", "FACE_DETECTION_TIMEOUT_MS", "FACE_TRACKING_TIMEOUT_MS", "FAILURE_RECORDING_INCREASE_IN_MS", "JPEG_EXTENSION", "MANUAL_FALLBACK_DELAY_MS", "MAXIMUM_RECORDING_DURATION_IN_MS", "MRZ_IS_NOT_READABLE", "MRZ_IS_READABLE", "NFC_LOGGER", "OVERLAY_DELAY_MS", "PASSPORT_MRZ_REQUIRED_FIELDS", "PASSPORT_OVERLAY_DELAY_MS", "POA_DEBUG", "SELFIE_PREFIX", "VIDEO_RECORDING_SIZE_CHECK_INTERVAL_MS", "hasRequiredFields", "", "Ljava/util/HashMap;", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "Lkotlin/collections/HashMap;", "requiredFields", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasRequiredFields(HashMap<MRZDataType, MRZData> map, List<? extends MRZDataType> list) {
            return map.keySet().containsAll(list);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$State;", "Landroid/os/Parcelable;", "numValidationErrors", "", "numOfTakenPictures", "documentProcessingFailureCounts", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;", "(IILcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;)V", "getDocumentProcessingFailureCounts", "()Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;", "getNumOfTakenPictures", "()I", "getNumValidationErrors", "component1", "component2", "component3", Constants.COPY_TYPE, "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new Creator();
        private final DocumentProcessingFailureCounts documentProcessingFailureCounts;
        private final int numOfTakenPictures;
        private final int numValidationErrors;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<State> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final State createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new State(parcel.readInt(), parcel.readInt(), DocumentProcessingFailureCounts.CREATOR.createFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final State[] newArray(int i) {
                return new State[i];
            }
        }

        public State(int i, int i2, DocumentProcessingFailureCounts documentProcessingFailureCounts) {
            Intrinsics.checkNotNullParameter(documentProcessingFailureCounts, "documentProcessingFailureCounts");
            this.numValidationErrors = i;
            this.numOfTakenPictures = i2;
            this.documentProcessingFailureCounts = documentProcessingFailureCounts;
        }

        public static /* synthetic */ State copy$default(State state, int i, int i2, DocumentProcessingFailureCounts documentProcessingFailureCounts, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = state.numValidationErrors;
            }
            if ((i3 & 2) != 0) {
                i2 = state.numOfTakenPictures;
            }
            if ((i3 & 4) != 0) {
                documentProcessingFailureCounts = state.documentProcessingFailureCounts;
            }
            return state.copy(i, i2, documentProcessingFailureCounts);
        }

        /* renamed from: component1, reason: from getter */
        public final int getNumValidationErrors() {
            return this.numValidationErrors;
        }

        /* renamed from: component2, reason: from getter */
        public final int getNumOfTakenPictures() {
            return this.numOfTakenPictures;
        }

        /* renamed from: component3, reason: from getter */
        public final DocumentProcessingFailureCounts getDocumentProcessingFailureCounts() {
            return this.documentProcessingFailureCounts;
        }

        public final State copy(int numValidationErrors, int numOfTakenPictures, DocumentProcessingFailureCounts documentProcessingFailureCounts) {
            Intrinsics.checkNotNullParameter(documentProcessingFailureCounts, "documentProcessingFailureCounts");
            return new State(numValidationErrors, numOfTakenPictures, documentProcessingFailureCounts);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof State)) {
                return false;
            }
            State state = (State) other;
            return this.numValidationErrors == state.numValidationErrors && this.numOfTakenPictures == state.numOfTakenPictures && Intrinsics.areEqual(this.documentProcessingFailureCounts, state.documentProcessingFailureCounts);
        }

        public final DocumentProcessingFailureCounts getDocumentProcessingFailureCounts() {
            return this.documentProcessingFailureCounts;
        }

        public final int getNumOfTakenPictures() {
            return this.numOfTakenPictures;
        }

        public final int getNumValidationErrors() {
            return this.numValidationErrors;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.numValidationErrors) * 31) + Integer.hashCode(this.numOfTakenPictures)) * 31) + this.documentProcessingFailureCounts.hashCode();
        }

        public String toString() {
            return "State(numValidationErrors=" + this.numValidationErrors + ", numOfTakenPictures=" + this.numOfTakenPictures + ", documentProcessingFailureCounts=" + this.documentProcessingFailureCounts + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeInt(this.numValidationErrors);
            parcel.writeInt(this.numOfTakenPictures);
            this.documentProcessingFailureCounts.writeToParcel(parcel, flags);
        }
    }

    @Metadata(d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020$H&J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001dH&J\b\u0010*\u001a\u00020$H&J\b\u0010+\u001a\u00020$H&J\b\u0010,\u001a\u00020$H&J\u0010\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020\u001dH&J\b\u0010/\u001a\u00020$H&J\b\u00100\u001a\u000201H&J\b\u00102\u001a\u000203H&J\b\u00104\u001a\u00020\u001dH&J\b\u00105\u001a\u00020$H&J\b\u00106\u001a\u00020$H&J\b\u00107\u001a\u00020$H&J\b\u00108\u001a\u00020$H&J\b\u00109\u001a\u00020$H&J\b\u0010:\u001a\u00020$H&J\u001c\u0010;\u001a\u00020$2\b\b\u0001\u0010<\u001a\u00020=2\b\b\u0001\u0010>\u001a\u00020=H&J\u0010\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020AH&J\u0018\u0010B\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010C\u001a\u00020DH&J\b\u0010E\u001a\u00020$H&J \u0010F\u001a\u00020$2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020\u001dH&J\b\u0010K\u001a\u00020$H&J\u0010\u0010L\u001a\u00020$2\u0006\u0010M\u001a\u00020NH&J\b\u0010O\u001a\u00020$H&J\b\u0010P\u001a\u00020$H&J\b\u0010Q\u001a\u00020$H&J\b\u0010R\u001a\u00020$H&J\b\u0010S\u001a\u00020$H&J\u0010\u0010T\u001a\u00020$2\u0006\u0010U\u001a\u00020VH&J \u0010W\u001a\u00020$2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010X\u001a\u00020YH&J\u0010\u0010Z\u001a\u00020$2\u0006\u0010[\u001a\u00020HH&J\b\u0010\\\u001a\u00020$H&J2\u0010]\u001a\u00020$2\u0006\u0010G\u001a\u00020H2\u0006\u0010^\u001a\u00020_2\u0006\u0010J\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020H2\b\u0010X\u001a\u0004\u0018\u00010YH&J\b\u0010`\u001a\u00020$H&J\b\u0010)\u001a\u00020$H&J\b\u0010a\u001a\u00020$H&J\b\u0010b\u001a\u00020$H&J\b\u0010c\u001a\u00020$H&J\u0010\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020\u001dH&J\b\u0010f\u001a\u00020$H&J\b\u0010g\u001a\u00020$H&J\u0010\u0010h\u001a\u00020$2\u0006\u0010i\u001a\u00020jH&J\u0010\u0010k\u001a\u00020$2\u0006\u0010l\u001a\u00020mH&J\b\u0010n\u001a\u00020$H&J\u0010\u0010o\u001a\u00020$2\u0006\u0010e\u001a\u00020\u001dH&J\b\u0010p\u001a\u00020$H&J\b\u0010q\u001a\u00020$H&J\b\u0010r\u001a\u00020$H&J\b\u0010s\u001a\u00020$H&J\b\u0010t\u001a\u00020$H&J\u0010\u0010u\u001a\u00020$2\u0006\u0010v\u001a\u00020wH&J\b\u0010x\u001a\u00020$H&J\u0010\u0010y\u001a\u00020$2\u0006\u0010z\u001a\u00020{H&JF\u0010|\u001a\u00020$2\b\b\u0001\u0010}\u001a\u00020=2\b\b\u0001\u0010~\u001a\u00020=2(\b\u0002\u0010\u007f\u001a\"\u0012\u0017\u0012\u00150\u0081\u0001¢\u0006\u000f\b\u0082\u0001\u0012\n\b\u0083\u0001\u0012\u0005\b\b(\u0084\u0001\u0012\u0004\u0012\u00020$0\u0080\u0001H&J\u001c\u0010\u0085\u0001\u001a\u00020$2\u0007\u0010\u0086\u0001\u001a\u00020H2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H&J\t\u0010\u0089\u0001\u001a\u00020$H&J\t\u0010\u008a\u0001\u001a\u00020$H&J\t\u0010\u008b\u0001\u001a\u00020$H&J\t\u0010\u008c\u0001\u001a\u00020$H&J\t\u0010\u008d\u0001\u001a\u00020$H&J\t\u0010\u008e\u0001\u001a\u00020$H&J\t\u0010\u008f\u0001\u001a\u00020$H&J\u0012\u0010\u0090\u0001\u001a\u00020$2\u0007\u0010\u0091\u0001\u001a\u00020\u001dH&J\t\u0010\u0092\u0001\u001a\u00020$H&J\u0013\u0010\u0093\u0001\u001a\u00020$2\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H&J\u0013\u0010\u0096\u0001\u001a\u00020$2\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H&J6\u0010\u0099\u0001\u001a\u00020$2\u0006\u0010G\u001a\u00020H2\u0007\u0010\u009a\u0001\u001a\u00020H2\u0006\u0010\n\u001a\u00020\u000b2\u0007\u0010\u009b\u0001\u001a\u00020\u00152\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00020\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0012\u0010\u001f\u001a\u00020 X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006\u009d\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CapturePresenter$View;", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "getCaptureType", "()Lcom/onfido/android/sdk/capture/ui/CaptureType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "getDocSide", "()Lcom/onfido/api/client/data/DocSide;", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentFormat", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "setDocumentFormat", "(Lcom/onfido/android/sdk/capture/DocumentFormat;)V", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "isOnConfirmationStep", "", "()Z", "screenOrientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "getScreenOrientation", "()Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "applyValidations", "", MimeTypes.BASE_TYPE_IMAGE, "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "cancelFlow", "capture", "playSingleFrameAutoCapturedAnimation", "deactivateCaptureButton", "destroyWithCanceledResult", "displayCaptureButton", "enableTorch", "isEnabled", "finishWithResultExitUserFlow", "getCaptureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getCapturedFilesDir", "Ljava/io/File;", "hasValidRecording", "hideCaptureButton", "hideDocumentOverlay", "hideLivenessControlButton", "hideLoading", "hideVideoRecordingProgressBar", "makeToolbarTitleNotImportantForAccessibility", "onAccessibleCaptureDocumentOverlayTextChanged", "mainTextResId", "", "mainTextContentDescriptionResId", "onAccessibleCaptureRectangleDetectionResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "onCaptureForProofOfAddressDone", "visibleRect", "Landroid/graphics/RectF;", "onChallengesCompleted", "onDocumentCreated", "documentId", "", "documentVideoId", "nfcSupported", "onDocumentVideoRecordingCompleted", "onFaceDetected", "faceDetectionResult", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "onFaceDetectionTimeout", "onFaceOutTimeout", "onFaceTrackingTimeout", "onImageProcessingFinished", "onManualFallbackDelayFinished", "onNextChallenge", "livenessChallengeViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "onNfcPropertiesFetched", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "onPoaImageCroppedAndSavedToFile", ReactNativeBridgeUtiles.KEY_FILE_NAME, "onStorageThresholdReached", "onWarningBinaryResult", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "openCaptureScreen", "removeDummyViewsAccessibility", "resetDocumentRecordingState", "restart", "setConfirmationButtons", "isGenericMessage", "setForceRetryButton", "setGlareWarningContent", "setLiveValidationBubbleContent", "content", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "setLiveValidationBubbleVisibilityCommand", "command", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "setOverlay", "setWarningConfirmationButtons", "setupCaptureButton", "setupConfirmationButtons", "setupUploadService", "showConfirmationPreview", "showConfirmationStep", "showDialog", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showDocumentFormatDialog", "showError", "descriptor", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "showErrorMessage", "titleResId", "messageResId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "showFaceLivenessConfirmationScreen", "dirPath", "performedChallanges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showLivenessButtonAndFocusWithDelay", "showVideoRecordCompletionTick", "showVideoRecordingCompleteMessage", "showVideoRecordingInProgressMessage", "showVideoRecordingProgressBar", "startCamera", "startDocumentVideoRecording", "startLivenessVideoRecording", "isStartedManually", "stopCamera", "trackNavigationCompleted", FirebaseAnalytics.Param.DESTINATION, "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "uploadImage", "jpegData", "", "uploadVideo", "videoPath", "docType", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View extends CaptureUploadServiceListener {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void onDocumentVideoUploaded(View view, String documentVideoId) {
                Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
                CaptureUploadServiceListener.DefaultImpls.onDocumentVideoUploaded(view, documentVideoId);
            }

            public static void onLivePhotoUploaded(View view, LivePhotoUpload photoUpload) {
                Intrinsics.checkNotNullParameter(photoUpload, "photoUpload");
                CaptureUploadServiceListener.DefaultImpls.onLivePhotoUploaded(view, photoUpload);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ void showErrorMessage$default(View view, int i, int i2, Function1 function1, int i3, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showErrorMessage");
                }
                if ((i3 & 4) != 0) {
                    function1 = new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$View$showErrorMessage$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                            invoke2(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DialogInterface it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                        }
                    };
                }
                view.showErrorMessage(i, i2, function1);
            }
        }

        void applyValidations(OnfidoImage image);

        void cancelFlow();

        void capture(boolean playSingleFrameAutoCapturedAnimation);

        void deactivateCaptureButton();

        void destroyWithCanceledResult();

        void displayCaptureButton();

        void enableTorch(boolean isEnabled);

        void finishWithResultExitUserFlow();

        CaptureStepDataBundle getCaptureStepDataBundle();

        CaptureType getCaptureType();

        File getCapturedFilesDir();

        CountryCode getCountryCode();

        DocSide getDocSide();

        DocumentFormat getDocumentFormat();

        DocumentType getDocumentType();

        DocumentPages getGenericDocPages();

        Orientation getScreenOrientation();

        boolean hasValidRecording();

        void hideCaptureButton();

        void hideDocumentOverlay();

        void hideLivenessControlButton();

        void hideLoading();

        void hideVideoRecordingProgressBar();

        /* renamed from: isOnConfirmationStep */
        boolean getIsOnConfirmationStep();

        void makeToolbarTitleNotImportantForAccessibility();

        void onAccessibleCaptureDocumentOverlayTextChanged(int mainTextResId, int mainTextContentDescriptionResId);

        void onAccessibleCaptureRectangleDetectionResult(RectDetectionResult result);

        void onCaptureForProofOfAddressDone(OnfidoImage image, RectF visibleRect);

        void onChallengesCompleted();

        void onDocumentCreated(String documentId, String documentVideoId, boolean nfcSupported);

        void onDocumentVideoRecordingCompleted();

        void onFaceDetected(FaceDetectionResult faceDetectionResult);

        void onFaceDetectionTimeout();

        void onFaceOutTimeout();

        void onFaceTrackingTimeout();

        void onImageProcessingFinished();

        void onManualFallbackDelayFinished();

        void onNextChallenge(LivenessChallengeViewModel livenessChallengeViewModel);

        void onNfcPropertiesFetched(String documentId, String documentVideoId, NfcProperties nfcProperties);

        void onPoaImageCroppedAndSavedToFile(String fileName);

        void onStorageThresholdReached();

        void onWarningBinaryResult(String documentId, ErrorType errorType, boolean nfcSupported, String documentVideoId, NfcProperties nfcProperties);

        void openCaptureScreen();

        void playSingleFrameAutoCapturedAnimation();

        void removeDummyViewsAccessibility();

        void resetDocumentRecordingState();

        void restart();

        void setConfirmationButtons(boolean isGenericMessage);

        void setDocumentFormat(DocumentFormat documentFormat);

        void setForceRetryButton();

        void setGlareWarningContent();

        void setLiveValidationBubbleContent(OnfidoCaptureValidationBubble.Content content);

        void setLiveValidationBubbleVisibilityCommand(OnfidoCaptureValidationBubble.VisibilityCommand command);

        void setOverlay();

        void setWarningConfirmationButtons(boolean isGenericMessage);

        void setupCaptureButton();

        void setupConfirmationButtons();

        void setupUploadService();

        void showConfirmationPreview();

        void showConfirmationStep();

        void showDialog(LoadingFragment.Companion.DialogMode dialogMode);

        void showDocumentFormatDialog();

        void showError(ErrorDescriptor descriptor);

        void showErrorMessage(int titleResId, int messageResId, Function1<? super DialogInterface, Unit> listener);

        void showFaceLivenessConfirmationScreen(String dirPath, LivenessPerformedChallenges performedChallanges);

        void showLivenessButtonAndFocusWithDelay();

        void showVideoRecordCompletionTick();

        void showVideoRecordingCompleteMessage();

        void showVideoRecordingInProgressMessage();

        void showVideoRecordingProgressBar();

        void startCamera();

        void startDocumentVideoRecording();

        void startLivenessVideoRecording(boolean isStartedManually);

        void stopCamera();

        void trackNavigationCompleted(PerformanceAnalyticsScreen destination);

        void uploadImage(byte[] jpegData);

        void uploadVideo(String documentId, String videoPath, DocSide docSide, DocumentType docType, CountryCode issuingCountry);
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", OnfidoLauncher.KEY_RESULT}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CapturePresenter$observeAutoCapture$1", f = "CapturePresenter.kt", i = {0}, l = {392}, m = "invokeSuspend", n = {OnfidoLauncher.KEY_RESULT}, s = {"L$0"})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$observeAutoCapture$1, reason: invalid class name and case insensitive filesystem */
    static final class C06031 extends SuspendLambda implements Function3<FlowCollector<? super DocumentValidationResult>, DocumentValidationResult, Continuation<? super Boolean>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C06031(Continuation<? super C06031> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            DocumentValidationResult documentValidationResult;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                DocumentValidationResult documentValidationResult2 = (DocumentValidationResult) this.L$1;
                this.L$0 = documentValidationResult2;
                this.label = 1;
                if (flowCollector.emit(documentValidationResult2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                documentValidationResult = documentValidationResult2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                documentValidationResult = (DocumentValidationResult) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(!Intrinsics.areEqual(documentValidationResult, DocumentValidationResult.Success.INSTANCE));
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super DocumentValidationResult> flowCollector, DocumentValidationResult documentValidationResult, Continuation<? super Boolean> continuation) {
            C06031 c06031 = new C06031(continuation);
            c06031.L$0 = flowCollector;
            c06031.L$1 = documentValidationResult;
            return c06031.invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CapturePresenter$observeAutoCapture$2", f = "CapturePresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$observeAutoCapture$2, reason: invalid class name and case insensitive filesystem */
    static final class C06042 extends SuspendLambda implements Function2<DocumentValidationResult, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06042(Continuation<? super C06042> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06042 c06042 = CapturePresenter.this.new C06042(continuation);
            c06042.L$0 = obj;
            return c06042;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DocumentValidationResult documentValidationResult, Continuation<? super Unit> continuation) {
            return ((C06042) create(documentValidationResult, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DocumentValidationResult documentValidationResult = (DocumentValidationResult) this.L$0;
            View view = null;
            if (Intrinsics.areEqual(documentValidationResult, DocumentValidationResult.Success.INSTANCE)) {
                View view2 = CapturePresenter.this.view;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view2 = null;
                }
                view2.setLiveValidationBubbleVisibilityCommand(OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE);
                View view3 = CapturePresenter.this.view;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view = view3;
                }
                view.capture(!CapturePresenter.this.onfidoRemoteConfig.isMultiImageCaptureEnabled());
            } else {
                OnfidoCaptureValidationBubble.Content contentInvoke = CapturePresenter.this.documentValidaMapper.invoke(documentValidationResult);
                if (contentInvoke != null) {
                    View view4 = CapturePresenter.this.view;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        view4 = null;
                    }
                    view4.setLiveValidationBubbleContent(contentInvoke);
                }
                View view5 = CapturePresenter.this.view;
                if (view5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view = view5;
                }
                view.setLiveValidationBubbleVisibilityCommand(new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(true)));
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.CapturePresenter", f = "CapturePresenter.kt", i = {0, 0}, l = {1193}, m = "processFrameForMRZ", n = {"this", "frameProcessStartTime"}, s = {"L$0", "J$0"})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$processFrameForMRZ$1, reason: invalid class name and case insensitive filesystem */
    static final class C06051 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C06051(Continuation<? super C06051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CapturePresenter.this.processFrameForMRZ(null, this);
        }
    }

    static {
        MRZDataType mRZDataType = MRZDataType.DATE_OF_BIRTH;
        MRZDataType mRZDataType2 = MRZDataType.EXPIRY_DATE;
        PASSPORT_MRZ_REQUIRED_FIELDS = CollectionsKt.listOf((Object[]) new MRZDataType[]{MRZDataType.PASSPORT_NUMBER, mRZDataType, mRZDataType2});
        DUTCH_ID_MRZ_REQUIRED_FIELDS = CollectionsKt.listOf((Object[]) new MRZDataType[]{MRZDataType.DOCUMENT_NUMBER, MRZDataType.PERSONAL_NUMBER, mRZDataType, mRZDataType2});
    }

    @Inject
    public CapturePresenter(NativeDetector nativeDetector, RectangleDetector rectangleDetector, AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase, LivenessInteractor livenessInteractor, BackendDocumentValidationsManager backendDocumentValidationsManager, DocumentProcessingUseCase documentProcessingUseCase, PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager, RuntimePermissionsManager permissionsManager, FaceDetector faceDetector, IdentityInteractor identityInteractor, DocumentConfigurationManager documentConfigurationManager, TimeProvider timeProvider, SdkUploadMetadataHelper sdkUploadMetaDataHelper, DocumentService documentService, NfcPropertiesService nfcPropertiesService, NfcInteractor nfcInteractor, DocumentCaptureDelayTransformer documentDelayTransformer, SchedulersProvider schedulersProvider, FaceProcessingUseCase faceProcessingUseCase, OnfidoRemoteConfig onfidoRemoteConfig, AnnouncementService announcementService, DocumentProcessingResultsFailureAnalyzer documentProcessingFailureAnalyzer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, ImageUtils imageUtils, CaptureTracker captureTracker, NfcTracker nfcTracker, LivenessTracker livenessTracker, WaitingScreenTracker waitingScreenTracker, FlowTracker flowTracker, ResultReceiver resultReceiver, MRZDocumentExtractor mrzDocumentExtractor, DocumentValidationUseCase documentValidationUseCase, DispatchersProvider dispatchersProvider) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(rectangleDetector, "rectangleDetector");
        Intrinsics.checkNotNullParameter(accessibleDocumentCaptureUseCase, "accessibleDocumentCaptureUseCase");
        Intrinsics.checkNotNullParameter(livenessInteractor, "livenessInteractor");
        Intrinsics.checkNotNullParameter(backendDocumentValidationsManager, "backendDocumentValidationsManager");
        Intrinsics.checkNotNullParameter(documentProcessingUseCase, "documentProcessingUseCase");
        Intrinsics.checkNotNullParameter(postCaptureDocumentValidationsManager, "postCaptureDocumentValidationsManager");
        Intrinsics.checkNotNullParameter(permissionsManager, "permissionsManager");
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(sdkUploadMetaDataHelper, "sdkUploadMetaDataHelper");
        Intrinsics.checkNotNullParameter(documentService, "documentService");
        Intrinsics.checkNotNullParameter(nfcPropertiesService, "nfcPropertiesService");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(documentDelayTransformer, "documentDelayTransformer");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(faceProcessingUseCase, "faceProcessingUseCase");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(documentProcessingFailureAnalyzer, "documentProcessingFailureAnalyzer");
        Intrinsics.checkNotNullParameter(retainableValidationsResult, "retainableValidationsResult");
        Intrinsics.checkNotNullParameter(barcodeValidationSuspender, "barcodeValidationSuspender");
        Intrinsics.checkNotNullParameter(imageUtils, "imageUtils");
        Intrinsics.checkNotNullParameter(captureTracker, "captureTracker");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        Intrinsics.checkNotNullParameter(flowTracker, "flowTracker");
        Intrinsics.checkNotNullParameter(mrzDocumentExtractor, "mrzDocumentExtractor");
        Intrinsics.checkNotNullParameter(documentValidationUseCase, "documentValidationUseCase");
        Intrinsics.checkNotNullParameter(dispatchersProvider, "dispatchersProvider");
        this.nativeDetector = nativeDetector;
        this.rectangleDetector = rectangleDetector;
        this.accessibleDocumentCaptureUseCase = accessibleDocumentCaptureUseCase;
        this.livenessInteractor = livenessInteractor;
        this.backendDocumentValidationsManager = backendDocumentValidationsManager;
        this.documentProcessingUseCase = documentProcessingUseCase;
        this.postCaptureDocumentValidationsManager = postCaptureDocumentValidationsManager;
        this.permissionsManager = permissionsManager;
        this.faceDetector = faceDetector;
        this.identityInteractor = identityInteractor;
        this.documentConfigurationManager = documentConfigurationManager;
        this.timeProvider = timeProvider;
        this.sdkUploadMetaDataHelper = sdkUploadMetaDataHelper;
        this.documentService = documentService;
        this.nfcPropertiesService = nfcPropertiesService;
        this.nfcInteractor = nfcInteractor;
        this.documentDelayTransformer = documentDelayTransformer;
        this.schedulersProvider = schedulersProvider;
        this.faceProcessingUseCase = faceProcessingUseCase;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.announcementService = announcementService;
        this.documentProcessingFailureAnalyzer = documentProcessingFailureAnalyzer;
        this.retainableValidationsResult = retainableValidationsResult;
        this.barcodeValidationSuspender = barcodeValidationSuspender;
        this.imageUtils = imageUtils;
        this.captureTracker = captureTracker;
        this.nfcTracker = nfcTracker;
        this.livenessTracker = livenessTracker;
        this.waitingScreenTracker = waitingScreenTracker;
        this.flowTracker = flowTracker;
        this.mediaCallback = resultReceiver;
        this.mrzDocumentExtractor = mrzDocumentExtractor;
        this.documentValidationUseCase = documentValidationUseCase;
        this.processingResults = new DocumentProcessingResults(null, null, null, null, null, null, 63, null);
        this.poaCaptureSessionId = LazyKt.lazy(new Function0<String>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$poaCaptureSessionId$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return String.valueOf(this.this$0.timeProvider.getCurrentTimestamp());
            }
        });
        this.compositeSubscription = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$compositeSubscription$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.faceDetectionCompositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$faceDetectionCompositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.faceTrackingCompositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$faceTrackingCompositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.movementChallengeCompositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$movementChallengeCompositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.autocaptureCompositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$autocaptureCompositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.mrzExtractionResultMap = new HashMap<>();
        this.iqsUploadParser = new IQSUploadErrorParser();
        this.documentVideoRecordingCompositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$documentVideoRecordingCompositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.presenterScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(dispatchersProvider.getMain()));
        this.documentValidaMapper = new DocumentValidationResultMapper();
        this.documentVideoId = "";
        BehaviorSubject<OverlayMetrics> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(...)");
        this.overlayMetricsBehaviorSubject = behaviorSubjectCreate;
        BehaviorSubject<DocumentDetectionFrame> behaviorSubjectCreate2 = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate2, "create(...)");
        this.documentFrameBehaviorSubject = behaviorSubjectCreate2;
    }

    public static /* synthetic */ void applyPostCaptureValidations$onfido_capture_sdk_core_release$default(CapturePresenter capturePresenter, DocumentDetectionFrame documentDetectionFrame, DocumentType documentType, DocSide docSide, CountryCode countryCode, int i, Object obj) {
        if ((i & 4) != 0) {
            docSide = DocSide.FRONT;
        }
        if ((i & 8) != 0) {
            countryCode = null;
        }
        capturePresenter.applyPostCaptureValidations$onfido_capture_sdk_core_release(documentDetectionFrame, documentType, docSide, countryCode);
    }

    private final void applyValidations() {
        OnfidoImage onfidoImage = this.capturedImage;
        if (onfidoImage == null) {
            return;
        }
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.applyValidations(onfidoImage);
    }

    private final void applyValidationsAfterAnimationDelay(long animationDelay) {
        final OnfidoImage onfidoImage = this.capturedImage;
        if (onfidoImage == null) {
            return;
        }
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = Completable.timer(animationDelay, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).subscribeOn(this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.applyValidationsAfterAnimationDelay$lambda$14(this.f$0, onfidoImage);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void applyValidationsAfterAnimationDelay$lambda$14(CapturePresenter this$0, OnfidoImage capturedImage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(capturedImage, "$capturedImage");
        View view = this$0.view;
        RectF rectF = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.stopCamera();
        if (!this$0.isProofOfAddress) {
            this$0.applyValidations();
            return;
        }
        View view2 = this$0.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view2 = null;
        }
        RectF rectF2 = this$0.visibleRect;
        if (rectF2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visibleRect");
        } else {
            rectF = rectF2;
        }
        view2.onCaptureForProofOfAddressDone(capturedImage, rectF);
    }

    private final boolean backSideCaptureNeeded() {
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        CaptureStepDataBundle captureStepDataBundle2 = null;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        DocumentType documentType = captureStepDataBundle.getDocumentType();
        if (documentType == null) {
            return false;
        }
        DocumentConfigurationManager documentConfigurationManager = this.documentConfigurationManager;
        CaptureStepDataBundle captureStepDataBundle3 = this.captureStepDataBundle;
        if (captureStepDataBundle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
        } else {
            captureStepDataBundle2 = captureStepDataBundle3;
        }
        return documentConfigurationManager.backSideCaptureNeeded(documentType, captureStepDataBundle2.getGenericDocPages());
    }

    private final void checkDocumentFormat() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        boolean zIsFoldedFormatSupported$onfido_capture_sdk_core_release = DocumentUtils.INSTANCE.isFoldedFormatSupported$onfido_capture_sdk_core_release(view.getDocumentType(), view.getCountryCode());
        if (view.getDocumentFormat() == null) {
            if (zIsFoldedFormatSupported$onfido_capture_sdk_core_release) {
                view.showDocumentFormatDialog();
                return;
            } else {
                view.setDocumentFormat(DocumentFormat.CARD);
                return;
            }
        }
        if (zIsFoldedFormatSupported$onfido_capture_sdk_core_release && DocumentFormat.FOLDED == view.getDocumentFormat()) {
            startOverlayDisplayTimer$onfido_capture_sdk_core_release();
        }
    }

    private final void checkUploadBinaryResult(UploadBinaryResult uploadBinaryResult) {
        View view = null;
        if (uploadBinaryResult instanceof UploadBinaryResult.BinaryUploaded) {
            Timber.INSTANCE.i("NFC Logger - Binary uploaded", new Object[0]);
            UploadBinaryResult.BinaryUploaded binaryUploaded = (UploadBinaryResult.BinaryUploaded) uploadBinaryResult;
            if (binaryUploaded.getWarning() != null) {
                showWarningBinaryResult$default(this, binaryUploaded.getDocumentId(), binaryUploaded.getWarning(), binaryUploaded.getDocumentNfcSupported(), binaryUploaded.getWarningsBundle(), null, 16, null);
                return;
            }
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.onDocumentCreated(binaryUploaded.getDocumentId(), this.documentVideoId, binaryUploaded.getDocumentNfcSupported());
            return;
        }
        if (!(uploadBinaryResult instanceof UploadBinaryResult.NfcPropertiesFetched)) {
            if (uploadBinaryResult instanceof UploadBinaryResult.Error) {
                UploadBinaryResult.Error error = (UploadBinaryResult.Error) uploadBinaryResult;
                Timber.INSTANCE.e("NFC Logger - UploadBinaryResult Error: " + error.getThrowable().getMessage(), new Object[0]);
                onError(error.getThrowable());
                return;
            }
            return;
        }
        Timber.INSTANCE.i("NFC Logger - Nfc properties fetched", new Object[0]);
        UploadBinaryResult.NfcPropertiesFetched nfcPropertiesFetched = (UploadBinaryResult.NfcPropertiesFetched) uploadBinaryResult;
        if (nfcPropertiesFetched.getWarning() != null) {
            showWarningBinaryResult(nfcPropertiesFetched.getDocumentId(), nfcPropertiesFetched.getWarning(), true, nfcPropertiesFetched.getWarningsBundle(), nfcPropertiesFetched.getNfcProperties());
            return;
        }
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view3;
        }
        view.onNfcPropertiesFetched(nfcPropertiesFetched.getDocumentId(), this.documentVideoId, nfcPropertiesFetched.getNfcProperties());
    }

    private final void checkUploading() {
        UploadBinaryResult uploadBinaryResult = this.uploadBinaryResult;
        if (uploadBinaryResult != null) {
            checkUploadBinaryResult(uploadBinaryResult);
        }
    }

    private final void disposeAutocaptureSubscriptions() {
        getAutocaptureCompositeDisposable().clear();
        this.edgeDetectionFallbackTimerDisposable = null;
    }

    private final void disposeFaceDetectionSubscriptions() {
        getFaceDetectionCompositeDisposable().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean edgeDetectionTimeoutNotStarted() {
        return this.edgeDetectionFallbackTimerDisposable == null;
    }

    private final void enableAccessibilityCapture(final DocumentType documentType, DocSide docSide) {
        RectangleDetector rectangleDetector = this.rectangleDetector;
        Observable<OverlayMetrics> observableHide = this.overlayMetricsBehaviorSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        Observable<DocumentDetectionFrame> observableHide2 = this.documentFrameBehaviorSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide2, "hide(...)");
        Observable<RectDetectionResult> observableShare = rectangleDetector.observeRectDetection(observableHide, observableHide2, documentType != DocumentType.PASSPORT && docSide == DocSide.FRONT).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$enableAccessibilityCapture$rectDetectionObservable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final RectDetectionResult apply(RectDetectionResult rectDetectionResult) {
                Intrinsics.checkNotNullParameter(rectDetectionResult, "rectDetectionResult");
                if (!(rectDetectionResult instanceof RectDetectionResult.RectDetected)) {
                    return rectDetectionResult;
                }
                RectDetectionResult.RectDetected rectDetected = (RectDetectionResult.RectDetected) rectDetectionResult;
                return RectDetectionResult.RectDetected.copy$default(rectDetected, ImageUtilsExtKt.trimDocument(rectDetected.getRect(), documentType), null, null, 6, null);
            }
        }).share();
        Intrinsics.checkNotNullExpressionValue(observableShare, "share(...)");
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = observableShare.subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).doOnDispose(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.enableAccessibilityCapture$lambda$3(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.enableAccessibilityCapture.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(RectDetectionResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                View view = CapturePresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onAccessibleCaptureRectangleDetectionResult(result);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
        CompositeDisposable compositeSubscription2 = getCompositeSubscription();
        Disposable disposableSubscribe2 = this.accessibleDocumentCaptureUseCase.observeAccessibilityCapture$onfido_capture_sdk_core_release(observableShare).doOnDispose(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.enableAccessibilityCapture$lambda$4(this.f$0);
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.enableAccessibilityCapture.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(AccessibleDocumentCaptureUseCaseResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                View view = null;
                if (result instanceof AccessibleDocumentCaptureUseCaseResult.AutoCaptured) {
                    View view2 = CapturePresenter.this.view;
                    if (view2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view = view2;
                    }
                    view.capture(!CapturePresenter.this.onfidoRemoteConfig.isMultiImageCaptureEnabled());
                    return;
                }
                if (result instanceof AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged) {
                    View view3 = CapturePresenter.this.view;
                    if (view3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view = view3;
                    }
                    AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged documentPositionChanged = (AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged) result;
                    view.onAccessibleCaptureDocumentOverlayTextChanged(documentPositionChanged.getMainTextResId(), documentPositionChanged.getContentDescriptionResId());
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription2, disposableSubscribe2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enableAccessibilityCapture$lambda$3(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onAccessibleCaptureRectangleDetectionResult(RectDetectionResult.NoRectDetected.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enableAccessibilityCapture$lambda$4(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onAccessibleCaptureRectangleDetectionResult(RectDetectionResult.NoRectDetected.INSTANCE);
    }

    private final CompositeDisposable getAutocaptureCompositeDisposable() {
        return (CompositeDisposable) this.autocaptureCompositeDisposable.getValue();
    }

    private final int getCaptureErrorMessage() {
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            return R.string.onfido_generic_error_doc_capture;
        }
        if (i == 2) {
            return R.string.onfido_generic_error_face_capture;
        }
        if (i == 3) {
            return R.string.onfido_generic_error_video_capture;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final CaptureType getCaptureType() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        return view.getCaptureType();
    }

    private final CompositeDisposable getCompositeSubscription() {
        return (CompositeDisposable) this.compositeSubscription.getValue();
    }

    private final DocumentFeatures getDocumentFeatures(NfcProperties nfcProperties, boolean hasNfc) {
        return new DocumentFeatures(hasNfc, nfcProperties.getHasCan(), nfcProperties.getHasPin(), nfcProperties.getCanLength(), nfcProperties.getPinLength());
    }

    static /* synthetic */ DocumentFeatures getDocumentFeatures$default(CapturePresenter capturePresenter, NfcProperties nfcProperties, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return capturePresenter.getDocumentFeatures(nfcProperties, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> getDocumentIdsForDocumentResponse(String uploadedMediaId) {
        NfcArguments.CapturedNfcData capturedData;
        NfcArguments nfcArguments = this.nfcArguments;
        return CollectionsKt.listOfNotNull((Object[]) new String[]{(nfcArguments == null || (capturedData = nfcArguments.getCapturedData()) == null) ? null : capturedData.getFrontId(), uploadedMediaId});
    }

    private final CompositeDisposable getDocumentVideoRecordingCompositeDisposable() {
        return (CompositeDisposable) this.documentVideoRecordingCompositeDisposable.getValue();
    }

    public static /* synthetic */ void getExtractedMRZDocument$onfido_capture_sdk_core_release$annotations() {
    }

    private final CompositeDisposable getFaceDetectionCompositeDisposable() {
        return (CompositeDisposable) this.faceDetectionCompositeDisposable.getValue();
    }

    private final CompositeDisposable getFaceTrackingCompositeDisposable() {
        return (CompositeDisposable) this.faceTrackingCompositeDisposable.getValue();
    }

    private final Observable<DocumentProcessingResults> getImageProcessingObservable(DocumentType documentType, CountryCode countryCode, DocSide docSide, final boolean enableManualFallback) {
        Observable<DocumentProcessingUseCaseResult> observableDoOnNext = this.documentProcessingUseCase.execute$onfido_capture_sdk_core_release(documentType, countryCode, docSide).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.getImageProcessingObservable.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentProcessingUseCaseResult documentProcessingUseCaseResult) {
                Intrinsics.checkNotNullParameter(documentProcessingUseCaseResult, "<name for destructuring parameter 0>");
                DocumentProcessingResults documentProcessingResults = documentProcessingUseCaseResult.getDocumentProcessingResults();
                if (enableManualFallback && this.edgeDetectionTimeoutNotStarted() && documentProcessingResults.getEdgeDetectionResults().hasAny()) {
                    this.startManualFallbackTimer();
                }
            }
        });
        final C06022 c06022 = new PropertyReference1Impl() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.getImageProcessingObservable.2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((DocumentProcessingUseCaseResult) obj).getDocumentProcessingResults();
            }
        };
        Observable map = observableDoOnNext.map(new Function(c06022) { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ Function1 function;

            {
                Intrinsics.checkNotNullParameter(c06022, "function");
                this.function = c06022;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    static /* synthetic */ Observable getImageProcessingObservable$default(CapturePresenter capturePresenter, DocumentType documentType, CountryCode countryCode, DocSide docSide, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            countryCode = null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        return capturePresenter.getImageProcessingObservable(documentType, countryCode, docSide, z);
    }

    private final String[] getMissingPermissions(CaptureType captureType) {
        String[] strArr = captureType == CaptureType.VIDEO ? new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"} : new String[]{"android.permission.CAMERA"};
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!this.permissionsManager.hasPermission(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private final CompositeDisposable getMovementChallengeCompositeDisposable() {
        return (CompositeDisposable) this.movementChallengeCompositeDisposable.getValue();
    }

    private final PerformanceAnalyticsScreen getPerformanceTrackingScreen() {
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            return PerformanceAnalyticsScreen.DOCUMENT_CAPTURE;
        }
        if (i == 2) {
            return PerformanceAnalyticsScreen.FACE_SELFIE_CAPTURE;
        }
        if (i == 3) {
            return PerformanceAnalyticsScreen.FACE_VIDEO_CAPTURE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String getPoaCaptureName() {
        return DOC_POA_PHOTO_PREFIX + getPoaCaptureSessionId() + ".jpg";
    }

    private final String getPoaCaptureSessionId() {
        return (String) this.poaCaptureSessionId.getValue();
    }

    private final Single<String> getPoaFileNameAfterCropping(final byte[] jpegData, final DocumentDetectionFrame frame) {
        Single<String> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CapturePresenter.getPoaFileNameAfterCropping$lambda$28(this.f$0, jpegData, frame);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getPoaFileNameAfterCropping$lambda$28(CapturePresenter this$0, byte[] jpegData, DocumentDetectionFrame frame) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(jpegData, "$jpegData");
        Intrinsics.checkNotNullParameter(frame, "$frame");
        byte[] imageContent = this$0.imageUtils.cropImageForScreenShowOnly$onfido_capture_sdk_core_release(jpegData, frame).getImageContent();
        String poaCaptureName = this$0.getPoaCaptureName();
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        File file = new File(view.getCapturedFilesDir(), poaCaptureName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            fileOutputStream.write(imageContent);
            fileOutputStream.close();
        } catch (IOException e) {
            Timber.INSTANCE.e(e, "POA_Debug: Failed saving image", new Object[0]);
        }
        return file.getPath();
    }

    private final boolean hasNativeLibrary() {
        return this.nativeDetector.hasNativeLibrary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void imageSavedSuccessfully(String fileName) {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onPoaImageCroppedAndSavedToFile(fileName);
    }

    private final boolean isBackSideOfRomanianNationalId() {
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        if (view.getDocSide() == DocSide.BACK) {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view3 = null;
            }
            if (view3.getCountryCode() == CountryCode.RO) {
                View view4 = this.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view2 = view4;
                }
                if (view2.getDocumentType() == DocumentType.NATIONAL_IDENTITY_CARD) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isCheckingImageQuality() {
        return isDocumentCapture() && shouldForceRetry() && this.onfidoRemoteConfig.isImageQualityServiceEnabled();
    }

    private final boolean isDocumentCapture() {
        return CaptureType.DOCUMENT == getCaptureType();
    }

    private final boolean isDocumentUploaded() {
        return this.uploadBinaryResult != null;
    }

    private final boolean isFinalStepForDocument() {
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        CaptureStepDataBundle captureStepDataBundle2 = null;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        if (captureStepDataBundle.getDocSide() != DocSide.FRONT || backSideCaptureNeeded()) {
            CaptureStepDataBundle captureStepDataBundle3 = this.captureStepDataBundle;
            if (captureStepDataBundle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            } else {
                captureStepDataBundle2 = captureStepDataBundle3;
            }
            if (captureStepDataBundle2.getDocSide() != DocSide.BACK) {
                return false;
            }
        }
        return true;
    }

    private final boolean isMRZExtracted() {
        boolean zHasRequiredFields;
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        if (view.getDocumentType() != DocumentType.PASSPORT) {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view3 = null;
            }
            if (view3.getDocumentType() == DocumentType.NATIONAL_IDENTITY_CARD) {
                View view4 = this.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view2 = view4;
                }
                if (view2.getCountryCode() == CountryCode.NL) {
                    zHasRequiredFields = INSTANCE.hasRequiredFields(this.mrzExtractionResultMap, DUTCH_ID_MRZ_REQUIRED_FIELDS);
                    Timber.INSTANCE.i("NFC Logger - MRZ detected [Dutch ID] : " + zHasRequiredFields, new Object[0]);
                }
            }
            Timber.INSTANCE.i("NFC Logger - MRZ not detected", new Object[0]);
            return false;
        }
        zHasRequiredFields = INSTANCE.hasRequiredFields(this.mrzExtractionResultMap, PASSPORT_MRZ_REQUIRED_FIELDS);
        Timber.INSTANCE.i("NFC Logger - MRZ detected [Passport] : " + zHasRequiredFields, new Object[0]);
        return zHasRequiredFields;
    }

    private final boolean isMlModelAutoCaptureEnabled() {
        if (this.onfidoRemoteConfig.getDocumentDetectionExperiment().getEnabled() && !shouldEnableAccessibilityCapture()) {
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            if (view.getDocumentFormat() != DocumentFormat.FOLDED && !this.isProofOfAddress) {
                return true;
            }
        }
        return false;
    }

    private final boolean isMrzDetectionEnabled() {
        boolean zIsMrzDetectionEnabled = this.onfidoRemoteConfig.getDocumentCapture().isMrzDetectionEnabled();
        Timber.INSTANCE.d("MRZ detection validation enabled: " + zIsMrzDetectionEnabled, new Object[0]);
        return zIsMrzDetectionEnabled;
    }

    private final void observeAutoCapture() {
        this.autoCaptureJob = FlowKt.launchIn(FlowKt.onEach(FlowKt.transformWhile(this.documentValidationUseCase.getValidationResult(), new C06031(null)), new C06042(null)), this.presenterScope);
    }

    private final void observeFaceOut() {
        CompositeDisposable faceTrackingCompositeDisposable = getFaceTrackingCompositeDisposable();
        Disposable disposableSubscribe = this.faceProcessingUseCase.observeFaceOut$onfido_capture_sdk_core_release().subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.observeFaceOut$lambda$12(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(faceTrackingCompositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeFaceOut$lambda$12(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onFaceOutTimeout();
    }

    private final void onError(Throwable throwable) {
        ErrorType invalidCertificate;
        if (throwable instanceof ErrorTypeException) {
            invalidCertificate = ((ErrorTypeException) throwable).getErrorType();
        } else if (throwable instanceof HttpParsedException) {
            invalidCertificate = this.iqsUploadParser.parseUploadError(((HttpParsedException) throwable).getErrorData(), getCaptureType());
        } else if (throwable instanceof TokenExpiredException) {
            invalidCertificate = ErrorType.TokenExpired.INSTANCE;
        } else if (throwable instanceof SSLPeerUnverifiedException) {
            String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "";
            }
            invalidCertificate = new ErrorType.InvalidCertificate(localizedMessage);
        } else {
            invalidCertificate = throwable instanceof HttpException ? ErrorType.Network.INSTANCE : throwable instanceof GeoblockedException ? ErrorType.Geoblocked.INSTANCE : ErrorType.Generic.INSTANCE;
        }
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onUploadError(invalidCertificate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPostCaptureValidationsFinished(DocumentProcessingResults processingResults, DocumentDetectionFrame frame) {
        String str;
        setMRZResult$onfido_capture_sdk_core_release(processingResults.getMrzValidationResult());
        this.processingResults = processingResults;
        View view = null;
        if (!this.onfidoRemoteConfig.isImageQualityServiceEnabled() || !processingResults.isValidDocumentImage()) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.showConfirmationStep();
            analyseProcessingResults$onfido_capture_sdk_core_release(processingResults);
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT;
        } else if (i == 2) {
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_FACE;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_VIDEO;
        }
        trackWaitingScreenCompleted();
        showLoading(str);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view3;
        }
        view.uploadImage(frame.getYuv());
    }

    public static /* synthetic */ void onRecordingStarted$onfido_capture_sdk_core_release$default(CapturePresenter capturePresenter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        capturePresenter.onRecordingStarted$onfido_capture_sdk_core_release(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStart$lambda$5(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onImageProcessingFinished();
    }

    public static /* synthetic */ void onStart$onfido_capture_sdk_core_release$default(CapturePresenter capturePresenter, DocSide docSide, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        capturePresenter.onStart$onfido_capture_sdk_core_release(docSide, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processFrameForMRZ(com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06051
            if (r0 == 0) goto L13
            r0 = r14
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter$processFrameForMRZ$1 r0 = (com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06051) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter$processFrameForMRZ$1 r0 = new com.onfido.android.sdk.capture.ui.camera.CapturePresenter$processFrameForMRZ$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            long r1 = r0.J$0
            java.lang.Object r13 = r0.L$0
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter r13 = (com.onfido.android.sdk.capture.ui.camera.CapturePresenter) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L51
        L2f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L37:
            kotlin.ResultKt.throwOnFailure(r14)
            com.onfido.android.sdk.capture.utils.TimeProvider r14 = r12.timeProvider
            long r4 = r14.getCurrentTimestamp()
            com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor r14 = r12.mrzDocumentExtractor
            r0.L$0 = r12
            r0.J$0 = r4
            r0.label = r3
            java.lang.Object r14 = r14.detect(r13, r0)
            if (r14 != r1) goto L4f
            return r1
        L4f:
            r13 = r12
            r1 = r4
        L51:
            com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult r14 = (com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult) r14
            com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult$Skipped r0 = com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult.Skipped.INSTANCE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r0)
            if (r0 != 0) goto L60
            int r0 = r13.processedFramesCount
            int r0 = r0 + r3
            r13.processedFramesCount = r0
        L60:
            boolean r0 = r14 instanceof com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult.Success
            if (r0 == 0) goto L87
            com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult$Success r14 = (com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult.Success) r14
            com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument r14 = r14.getDocument()
            boolean r0 = r14.isValid()
            if (r0 == 0) goto L87
            r13.extractedMRZDocument = r14
            com.onfido.android.sdk.capture.utils.TimeProvider r14 = r13.timeProvider
            long r3 = r14.getCurrentTimestamp()
            long r8 = r3 - r1
            long r0 = r13.firstFrameEmitTime
            long r6 = r3 - r0
            com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker r5 = r13.captureTracker
            int r10 = r13.emittedFramesCount
            int r11 = r13.processedFramesCount
            r5.trackDocumentValidMRZExtracted$onfido_capture_sdk_core_release(r6, r8, r10, r11)
        L87:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.processFrameForMRZ(com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushPerformedChallenge(LivenessChallenge livenessChallenge) {
        this.livenessInteractor.pushPerformedChallenge(livenessChallenge);
    }

    public static /* synthetic */ void setup$onfido_capture_sdk_core_release$default(CapturePresenter capturePresenter, View view, OnfidoConfig onfidoConfig, CaptureStepDataBundle captureStepDataBundle, NfcArguments nfcArguments, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        capturePresenter.setup$onfido_capture_sdk_core_release(view, onfidoConfig, captureStepDataBundle, nfcArguments, z);
    }

    private final boolean shouldEnableAccessibilityCapture() {
        return this.announcementService.isEnabled() && !(this.rectangleDetector instanceof RectangleDetectorEmpty);
    }

    private final boolean shouldForceRetry() {
        return this.rejectionCount < this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries() && !isBackSideOfRomanianNationalId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldGetNfcProperties() {
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        CaptureStepDataBundle captureStepDataBundle2 = null;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        DocumentType documentType = captureStepDataBundle.getDocumentType();
        if (documentType == null) {
            return false;
        }
        CaptureStepDataBundle captureStepDataBundle3 = this.captureStepDataBundle;
        if (captureStepDataBundle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
        } else {
            captureStepDataBundle2 = captureStepDataBundle3;
        }
        return captureStepDataBundle2.getDocSide() == this.documentConfigurationManager.getDocSideForNfcProperties(documentType);
    }

    private final boolean shouldRecordDocumentVideo() {
        return getCaptureType() == CaptureType.DOCUMENT && this.onfidoRemoteConfig.isMultiImageCaptureEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldScanNfc() {
        NFCOptions nfcOptions;
        NfcArguments nfcArguments = this.nfcArguments;
        if (Intrinsics.areEqual(nfcArguments != null ? nfcArguments.getNfcOptions() : null, NFCOptions.Enabled.Required.INSTANCE)) {
            return true;
        }
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        DocumentType documentType = captureStepDataBundle.getDocumentType();
        Boolean boolValueOf = documentType != null ? Boolean.valueOf(this.documentConfigurationManager.shouldScanNfc(documentType)) : null;
        NfcArguments nfcArguments2 = this.nfcArguments;
        return nfcArguments2 != null && (nfcOptions = nfcArguments2.getNfcOptions()) != null && NFCOptionsKt.isEnabled(nfcOptions) && Intrinsics.areEqual(boolValueOf, Boolean.TRUE) && this.nfcInteractor.isNfcSupported();
    }

    private final boolean shouldShowOverlay(boolean isFrontSide, boolean isOverlayGone, DocumentType documentType, CountryCode countryCode) {
        if (isFrontSide && !isOverlayGone) {
            DocumentFormat documentFormat = DocumentFormat.FOLDED;
            View view = this.view;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            if (documentFormat == view.getDocumentFormat()) {
                View view3 = this.view;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view3 = null;
                }
                if (view3.getDocumentType() == documentType) {
                    View view4 = this.view;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view2 = view4;
                    }
                    if (view2.getCountryCode() == countryCode) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean shouldUploadDocument() {
        return (getCaptureType().isDocument() && isDocumentUploaded() && this.onfidoRemoteConfig.isImageQualityServiceEnabled()) ? false : true;
    }

    private final void showLoading(String reason) {
        showLoadingDialog$onfido_capture_sdk_core_release(reason);
    }

    private final void showMRZWarning() {
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        ErrorDescriptor errorDescriptor = view.getDocumentType() == DocumentType.PASSPORT ? new ErrorDescriptor(R.string.onfido_doc_capture_alert_no_mrz_title, Integer.valueOf(R.string.onfido_doc_capture_alert_no_mrz_detail)) : new ErrorDescriptor(R.string.onfido_doc_capture_alert_no_mrz3_title, Integer.valueOf(R.string.onfido_doc_capture_alert_no_mrz_detail));
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.showError(errorDescriptor);
    }

    private final void showWarningBinaryResult(String documentId, ErrorType warning, boolean nfcSupported, DocumentValidationWarningsBundle warningsBundle, NfcProperties nfcProperties) {
        Map<ErrorType, UiAlerts.UiAlertType> mapEmptyMap;
        View view;
        CaptureTracker captureTracker = this.captureTracker;
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        int i = this.takenPhotoCount;
        int maxTotalRetries = this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries();
        if (warningsBundle == null || (mapEmptyMap = DocumentValidationWarningsBundleUtilsKt.remoteWarnings(warningsBundle)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        captureTracker.trackDocumentConfirmationWarning$onfido_capture_sdk_core_release(captureStepDataBundle, i, maxTotalRetries, mapEmptyMap, warning);
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        } else {
            view = view2;
        }
        view.onWarningBinaryResult(documentId, warning, nfcSupported, this.documentVideoId, nfcProperties);
    }

    static /* synthetic */ void showWarningBinaryResult$default(CapturePresenter capturePresenter, String str, ErrorType errorType, boolean z, DocumentValidationWarningsBundle documentValidationWarningsBundle, NfcProperties nfcProperties, int i, Object obj) {
        if ((i & 16) != 0) {
            nfcProperties = null;
        }
        capturePresenter.showWarningBinaryResult(str, errorType, z, documentValidationWarningsBundle, nfcProperties);
    }

    private final void startDocumentVideoRecording() {
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.startDocumentVideoRecording();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view3 = null;
        }
        view3.hideCaptureButton();
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view4 = null;
        }
        view4.showVideoRecordingProgressBar();
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view5;
        }
        view2.showVideoRecordingInProgressMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startLivenessProcessing$lambda$8(LivenessChallengesViewModel livenessChallengesViewModel, CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(livenessChallengesViewModel, "$livenessChallengesViewModel");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pushPerformedChallenge((LivenessChallenge) CollectionsKt.last((List) livenessChallengesViewModel.getChallenges()));
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onChallengesCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startManualFallbackTimer() {
        CompositeDisposable autocaptureCompositeDisposable = getAutocaptureCompositeDisposable();
        Disposable disposableSubscribe = Observable.timer(MANUAL_FALLBACK_DELAY_MS, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startManualFallbackTimer.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                CapturePresenter.this.edgeDetectionFallbackTimerDisposable = it;
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startManualFallbackTimer.2
            public final void accept(long j) {
                View view = CapturePresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onManualFallbackDelayFinished();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startManualFallbackTimer.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on autocapture fallback subscription: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(autocaptureCompositeDisposable, disposableSubscribe);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void startMovementChallengeTimeout() {
        /*
            r5 = this;
            io.reactivex.rxjava3.disposables.CompositeDisposable r0 = r5.getMovementChallengeCompositeDisposable()
            com.onfido.android.sdk.capture.analytics.IdentityInteractor r1 = r5.identityInteractor
            boolean r1 = r1.isDeviceHighEnd()
            if (r1 == 0) goto L1f
            com.onfido.android.sdk.capture.OnfidoConfig r1 = r5.onfidoConfig
            if (r1 != 0) goto L16
            java.lang.String r1 = "onfidoConfig"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = 0
        L16:
            boolean r1 = r1.getManualLivenessCapture()
            if (r1 != 0) goto L1f
            r1 = 5000(0x1388, double:2.4703E-320)
            goto L21
        L1f:
            r1 = 0
        L21:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
            com.onfido.android.sdk.capture.internal.util.SchedulersProvider r4 = r5.schedulersProvider
            io.reactivex.rxjava3.core.Scheduler r4 = r4.getTimer()
            io.reactivex.rxjava3.core.Observable r1 = io.reactivex.rxjava3.core.Observable.timer(r1, r3, r4)
            com.onfido.android.sdk.capture.internal.util.SchedulersProvider r2 = r5.schedulersProvider
            io.reactivex.rxjava3.core.Scheduler r2 = r2.getIo()
            io.reactivex.rxjava3.core.Observable r1 = r1.subscribeOn(r2)
            com.onfido.android.sdk.capture.internal.util.SchedulersProvider r2 = r5.schedulersProvider
            io.reactivex.rxjava3.core.Scheduler r2 = r2.getUi()
            io.reactivex.rxjava3.core.Observable r1 = r1.observeOn(r2)
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$1 r2 = new com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$1
            r2.<init>()
            com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$2<T> r3 = new io.reactivex.rxjava3.functions.Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startMovementChallengeTimeout.2
                static {
                    /*
                        com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$2 r0 = new com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$2<T>) com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startMovementChallengeTimeout.2.INSTANCE com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startMovementChallengeTimeout$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06092.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06092.<init>():void");
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public /* bridge */ /* synthetic */ void accept(java.lang.Object r1) {
                    /*
                        r0 = this;
                        java.lang.Throwable r1 = (java.lang.Throwable) r1
                        r0.accept(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06092.accept(java.lang.Object):void");
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(java.lang.Throwable r4) {
                    /*
                        r3 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                        com.onfido.android.sdk.capture.internal.util.logging.Timber$Forest r0 = com.onfido.android.sdk.capture.internal.util.logging.Timber.INSTANCE
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        java.lang.String r2 = "Error on face tracking timeout subscription: "
                        r1.<init>(r2)
                        java.lang.String r2 = r4.getMessage()
                        java.lang.StringBuilder r1 = r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        r2 = 0
                        java.lang.Object[] r2 = new java.lang.Object[r2]
                        r0.e(r4, r1, r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.C06092.accept(java.lang.Throwable):void");
                }
            }
            io.reactivex.rxjava3.disposables.Disposable r1 = r1.subscribe(r2, r3)
            java.lang.String r2 = "subscribe(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.onfido.android.sdk.capture.utils.RxExtensionsKt.plusAssign(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.startMovementChallengeTimeout():void");
    }

    private final void startVideoConfirmationTickTimer() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.showVideoRecordCompletionTick();
        applyValidationsAfterAnimationDelay(CONFIRMATION_VIEW_ANIM_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopDocumentRecording() {
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.enableTorch(false);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view3 = null;
        }
        view3.hideVideoRecordingProgressBar();
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view4;
        }
        view2.showVideoRecordingCompleteMessage();
        stopDocumentVideoRecordingAndCameraFeed();
        startVideoConfirmationTickTimer();
    }

    private final void stopDocumentVideoRecordingAndCameraFeed() {
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableScheduleDirect = this.schedulersProvider.getComputation().scheduleDirect(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                CapturePresenter.stopDocumentVideoRecordingAndCameraFeed$lambda$13(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableScheduleDirect, "scheduleDirect(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableScheduleDirect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopDocumentVideoRecordingAndCameraFeed$lambda$13(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onDocumentVideoRecordingCompleted();
    }

    private final void stopDocumentVideoRecordingSubscription() {
        getDocumentVideoRecordingCompositeDisposable().clear();
    }

    private final void stopMovementChallengeTimeout() {
        getMovementChallengeCompositeDisposable().clear();
    }

    private final String toStep() {
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            return "document_capture";
        }
        if (i == 2) {
            return FlowTracker.STEP_FACE_CAPTURE;
        }
        if (i == 3) {
            return FlowTracker.STEP_VIDEO_CAPTURE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void trackCapture(CaptureType captureType) {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        boolean zIsPortrait$onfido_capture_sdk_core_release = view.getScreenOrientation().isPortrait$onfido_capture_sdk_core_release();
        if (captureType == CaptureType.DOCUMENT) {
            trackDocumentCapture(zIsPortrait$onfido_capture_sdk_core_release);
        } else {
            this.livenessTracker.trackFaceCapture$onfido_capture_sdk_core_release(false, zIsPortrait$onfido_capture_sdk_core_release, captureType, this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
        }
    }

    private final void trackDocumentCapture(boolean isPortrait) {
        CaptureTracker captureTracker = this.captureTracker;
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        captureTracker.trackDocumentCapture(isPortrait, view.getCaptureStepDataBundle(), shouldAutoCaptureDocument$onfido_capture_sdk_core_release());
    }

    private final void trackDocumentCaptureFlowCompleted() {
        if (isFinalStepForDocument()) {
            View view = this.view;
            CaptureStepDataBundle captureStepDataBundle = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            if (view.getIsOnConfirmationStep()) {
                CaptureTracker captureTracker = this.captureTracker;
                CaptureStepDataBundle captureStepDataBundle2 = this.captureStepDataBundle;
                if (captureStepDataBundle2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                } else {
                    captureStepDataBundle = captureStepDataBundle2;
                }
                captureTracker.trackDocumentCaptureFlowCompleted$onfido_capture_sdk_core_release(captureStepDataBundle);
            }
        }
    }

    private final void trackDocumentConfirmation(CaptureStepDataBundle documentData) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!this.processingResults.getBlurResults().isValid()) {
            linkedHashMap.put(ErrorType.Blur.INSTANCE, UiAlerts.UiAlertType.WARNING);
        }
        if (!this.processingResults.getBarcodeResults().isValid()) {
            linkedHashMap.put(ErrorType.Barcode.INSTANCE, UiAlerts.UiAlertType.WARNING);
        }
        if (!this.processingResults.getFaceOnDocumentDetectionResult().isValid()) {
            linkedHashMap.put(ErrorType.NoFace.INSTANCE, UiAlerts.UiAlertType.WARNING);
        }
        if (!this.processingResults.getMrzValidationResult().isValid()) {
            linkedHashMap.put(ErrorType.Generic.INSTANCE, UiAlerts.UiAlertType.WARNING);
        }
        this.captureTracker.trackDocumentConfirmation(documentData, this.takenPhotoCount, this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries(), this.isAutoCaptured, linkedHashMap);
    }

    private final void trackFaceConfirmation(boolean isPortrait, CaptureType captureType) {
        this.livenessTracker.trackFaceCapture$onfido_capture_sdk_core_release(true, isPortrait, captureType, this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
    }

    private final void trackUploadValidationError(CaptureType captureType, ErrorType errorType) {
        if (captureType != CaptureType.DOCUMENT) {
            trackCaptureError$onfido_capture_sdk_core_release(captureType, errorType);
            return;
        }
        CaptureTracker captureTracker = this.captureTracker;
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        captureTracker.trackDocumentConfirmationError$onfido_capture_sdk_core_release(errorType, captureStepDataBundle.getDocSide());
    }

    private final void trackVideoButtonClicked() {
        long currentTimestamp = this.timeProvider.getCurrentTimestamp() - this.livenessInteractor.getLivenessStartTimestamp();
        long j = this.livenessPreviousChallengeCompleted;
        long j2 = currentTimestamp - j;
        this.livenessPreviousChallengeCompleted = j + j2;
        if (this.livenessInteractor.getLivenessPerformedChallenges().getChallenges().size() >= 1) {
            trackVideoFinishButtonClicked$onfido_capture_sdk_core_release(j2);
        } else {
            trackVideoNextButtonClicked$onfido_capture_sdk_core_release(j2);
        }
    }

    private final void trackWaitingScreenCompleted() {
        String str;
        WaitingScreenTracker waitingScreenTracker;
        LoadingFragment.Companion.DialogMode uploading;
        int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i == 1) {
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT;
        } else if (i == 2) {
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_FACE;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_VIDEO;
        }
        if (isCheckingImageQuality()) {
            waitingScreenTracker = this.waitingScreenTracker;
            uploading = new LoadingFragment.Companion.DialogMode.CheckingImageQuality(str);
        } else {
            waitingScreenTracker = this.waitingScreenTracker;
            uploading = new LoadingFragment.Companion.DialogMode.Uploading(str);
        }
        waitingScreenTracker.trackWaitingScreenCompletion(uploading.toTaskType(), str);
    }

    private final void uploadDocumentMedia(List<Validation> validations, byte[] jpegData) {
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        DocumentService documentService = this.documentService;
        String extraFileInfo = getExtraFileInfo();
        CaptureStepDataBundle captureStepDataBundle = this.captureStepDataBundle;
        CaptureStepDataBundle captureStepDataBundle2 = null;
        if (captureStepDataBundle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle = null;
        }
        DocSide docSide = captureStepDataBundle.getDocSide();
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        DocSide docSide2 = docSide;
        CaptureStepDataBundle captureStepDataBundle3 = this.captureStepDataBundle;
        if (captureStepDataBundle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle3 = null;
        }
        DocumentType documentType = captureStepDataBundle3.getDocumentType();
        if (documentType == null) {
            documentType = DocumentType.UNKNOWN;
        }
        DocumentType documentType2 = documentType;
        CaptureStepDataBundle captureStepDataBundle4 = this.captureStepDataBundle;
        if (captureStepDataBundle4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
            captureStepDataBundle4 = null;
        }
        SdkUploadMetaData sdkUploadMetaDataSdkUploadMetadata$onfido_capture_sdk_core_release = sdkUploadMetadata$onfido_capture_sdk_core_release(captureStepDataBundle4);
        CaptureStepDataBundle captureStepDataBundle5 = this.captureStepDataBundle;
        if (captureStepDataBundle5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
        } else {
            captureStepDataBundle2 = captureStepDataBundle5;
        }
        Disposable disposableSubscribe = documentService.uploadDocumentMedia(jpegData, extraFileInfo, docSide2, documentType2, validations, sdkUploadMetaDataSdkUploadMetadata$onfido_capture_sdk_core_release, captureStepDataBundle2.getCountryCode()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.uploadDocumentMedia.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends UploadBinaryResult> apply(final DocumentUploadResult result) {
                Single<R> singleJust;
                NfcArguments.CapturedNfcData capturedData;
                Intrinsics.checkNotNullParameter(result, "result");
                DocumentValidationWarningsBundle warningsBundle = result.getWarningsBundle();
                final ErrorType errorTypeFirstRemoteWarningOrNull = warningsBundle != null ? DocumentValidationWarningsBundleUtilsKt.firstRemoteWarningOrNull(warningsBundle) : null;
                DocumentFeatures documentFeatures = result.getDocumentFeatures();
                boolean hasNFC = documentFeatures != null ? documentFeatures.getHasNFC() : false;
                NfcArguments nfcArguments = CapturePresenter.this.nfcArguments;
                boolean z = !(nfcArguments == null || (capturedData = nfcArguments.getCapturedData()) == null || !capturedData.getNfcSupported()) || hasNFC;
                if (CapturePresenter.this.shouldScanNfc() && z && CapturePresenter.this.shouldGetNfcProperties()) {
                    Timber.INSTANCE.i("NFC Logger - Attempting to get Nfc properties", new Object[0]);
                    Single<NfcProperties> single = CapturePresenter.this.nfcPropertiesService.get(CapturePresenter.this.getDocumentIdsForDocumentResponse(result.getMediaId()), CapturePresenter.this.getExtractedMRZDocument(), result.getDocumentFeatures());
                    final CapturePresenter capturePresenter = CapturePresenter.this;
                    Single<R> map = single.map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.uploadDocumentMedia.1.1
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final UploadBinaryResult.NfcPropertiesFetched apply(NfcProperties nfcProperties) {
                            Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
                            NfcTracker nfcTracker = capturePresenter.nfcTracker;
                            boolean isNfcSupported = nfcProperties.getIsNfcSupported();
                            boolean z2 = nfcProperties.getNfcKey().length() > 0;
                            CaptureStepDataBundle captureStepDataBundle6 = capturePresenter.captureStepDataBundle;
                            if (captureStepDataBundle6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                                captureStepDataBundle6 = null;
                            }
                            DocumentType documentType3 = captureStepDataBundle6.getDocumentType();
                            CaptureStepDataBundle captureStepDataBundle7 = capturePresenter.captureStepDataBundle;
                            if (captureStepDataBundle7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                                captureStepDataBundle7 = null;
                            }
                            nfcTracker.trackDocumentNfcSupported$onfido_capture_sdk_core_release(isNfcSupported, z2, documentType3, captureStepDataBundle7.getCountryCode(), CapturePresenter.getDocumentFeatures$default(capturePresenter, nfcProperties, false, 2, null));
                            return new UploadBinaryResult.NfcPropertiesFetched(result.getMediaId(), nfcProperties, errorTypeFirstRemoteWarningOrNull, result.getWarningsBundle());
                        }
                    });
                    final CapturePresenter capturePresenter2 = CapturePresenter.this;
                    singleJust = map.doOnError(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.uploadDocumentMedia.1.2
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Throwable it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            capturePresenter2.nfcTracker.trackPropertiesError$onfido_capture_sdk_core_release(it.getMessage());
                        }
                    });
                } else {
                    singleJust = Single.just(new UploadBinaryResult.BinaryUploaded(result.getMediaId(), errorTypeFirstRemoteWarningOrNull, hasNFC, result.getWarningsBundle()));
                }
                Intrinsics.checkNotNull(singleJust);
                return singleJust;
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return CapturePresenter.uploadDocumentMedia$lambda$22((Throwable) obj);
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doOnDispose(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.uploadDocumentMedia$lambda$23(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.uploadDocumentMedia.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(UploadBinaryResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                CapturePresenter.this.onBinaryUploaded$onfido_capture_sdk_core_release(it);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter.uploadDocumentMedia.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "NFC Logger - Error on uploadBinary", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UploadBinaryResult uploadDocumentMedia$lambda$22(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Timber.INSTANCE.e(throwable, "NFC Logger - Error on uploadBinary", new Object[0]);
        return new UploadBinaryResult.Error(throwable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadDocumentMedia$lambda$23(CapturePresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = this$0.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
    }

    public final void analyseProcessingResults$onfido_capture_sdk_core_release(DocumentProcessingResults results) {
        ErrorType errorType;
        Intrinsics.checkNotNullParameter(results, "results");
        View view = null;
        if (results.isValidDocumentImage()) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.setConfirmationButtons(false);
            return;
        }
        if (!isBackSideOfRomanianNationalId()) {
            if (results.getBlurResults().getHasBlur()) {
                errorType = ErrorType.Blur.INSTANCE;
            } else if (!results.getMrzValidationResult().isValid() && isMrzDetectionEnabled()) {
                showMRZWarning();
            } else if (!results.getBarcodeResults().isValid()) {
                errorType = ErrorType.Barcode.INSTANCE;
            } else if (!results.getFaceOnDocumentDetectionResult().isValid()) {
                errorType = ErrorType.NoFace.INSTANCE;
            }
            showErrorType$onfido_capture_sdk_core_release(errorType);
        }
        if (shouldForceRetry()) {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view3;
            }
            view.setForceRetryButton();
        } else {
            View view4 = this.view;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view4;
            }
            view.setWarningConfirmationButtons(false);
        }
        this.rejectionCount++;
    }

    public final void applyPostCaptureValidations$onfido_capture_sdk_core_release(final DocumentDetectionFrame frame, DocumentType documentType, DocSide docSide, CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        this.docFrame = frame;
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = this.postCaptureDocumentValidationsManager.validate$onfido_capture_sdk_core_release(frame, documentType, countryCode, docSide).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$applyPostCaptureValidations$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentProcessingResults processingResults) {
                Intrinsics.checkNotNullParameter(processingResults, "processingResults");
                this.this$0.onPostCaptureValidationsFinished(processingResults, frame);
                this.this$0.documentProcessingFailureAnalyzer.analyzeDocumentProcessingResults(processingResults);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$applyPostCaptureValidations$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on post processing validations: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    public final void callMediaCallback$onfido_capture_sdk_core_release() {
        byte[] data = this.croppedImage;
        if (data == null) {
            OnfidoImage onfidoImage = this.capturedImage;
            data = onfidoImage != null ? onfidoImage.getData() : null;
            if (data == null) {
                return;
            }
        }
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        String strName = view.getDocSide().name();
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view2 = null;
        }
        DocumentType documentType = view2.getDocumentType();
        String strName2 = documentType != null ? documentType.name() : null;
        if (strName2 == null) {
            strName2 = "";
        }
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view3 = null;
        }
        CountryCode countryCode = view3.getCountryCode();
        String alpha3 = countryCode != null ? countryCode.getAlpha3() : null;
        String str = (getCaptureType().isDocument() ? DOCUMENT_PREFIX : SELFIE_PREFIX) + '-' + this.timeProvider.getCurrentTimestamp() + ".jpeg";
        ResultReceiver resultReceiver = this.mediaCallback;
        if (resultReceiver != null) {
            ResultReceiverExtensionsKt.sendMedia(resultReceiver, data, this.schedulersProvider.getIo(), BundleKt.bundleOf(TuplesKt.to(MediaCallbackResultReceiver.KEY_CAPTURE_TYPE, getCaptureType()), TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_TYPE, JPEG_EXTENSION), TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_NAME, str), TuplesKt.to(MediaCallbackResultReceiver.KEY_DOC_SIDE, strName), TuplesKt.to("doc_type", strName2), TuplesKt.to(MediaCallbackResultReceiver.KEY_COUNTRY, alpha3)));
        }
    }

    public final void clearEdges$onfido_capture_sdk_core_release() {
        this.nativeDetector.clearEdges();
    }

    public final void cropImageAndSaveToFile$onfido_capture_sdk_core_release(byte[] jpegData, DocumentDetectionFrame frame) {
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        Intrinsics.checkNotNullParameter(frame, "frame");
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = getPoaFileNameAfterCropping(jpegData, frame).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$cropImageAndSaveToFile$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                this.$tmp0.imageSavedSuccessfully(p0);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$cropImageAndSaveToFile$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "POA_Debug: failed cropping / saving poa file", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    /* renamed from: getCurrentCaptureFlowError$onfido_capture_sdk_core_release, reason: from getter */
    public final ErrorType getCurrentCaptureFlowError() {
        return this.currentCaptureFlowError;
    }

    public final DocumentDetectionFrame getDocFrame$onfido_capture_sdk_core_release() {
        DocumentDetectionFrame documentDetectionFrame = this.docFrame;
        if (documentDetectionFrame != null) {
            return documentDetectionFrame;
        }
        Intrinsics.throwUninitializedPropertyAccessException("docFrame");
        return null;
    }

    public final DocumentCaptureResultConsumer getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release() {
        DocumentCaptureResultConsumer documentCaptureResultConsumer = this.documentCaptureResultConsumer;
        if (documentCaptureResultConsumer != null) {
            return documentCaptureResultConsumer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("documentCaptureResultConsumer");
        return null;
    }

    /* renamed from: getExtraFileInfo$onfido_capture_sdk_core_release, reason: from getter */
    public final String getExtraFileInfo() {
        return this.extraFileInfo;
    }

    /* renamed from: getExtractedMRZDocument$onfido_capture_sdk_core_release, reason: from getter */
    public final MRZDocument getExtractedMRZDocument() {
        return this.extractedMRZDocument;
    }

    public final StringRepresentation getOvalCaptureContentDescription$onfido_capture_sdk_core_release(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        int i = WhenMappings.$EnumSwitchMapping$0[captureStepDataBundle.getCaptureType().ordinal()];
        if (i == 1) {
            if (captureStepDataBundle.getDocumentType() != null) {
                return new StringRepresentation.SingleStringResIdRepresentation(this.documentConfigurationManager.captureFrameContentDescription(captureStepDataBundle.getDocumentType(), captureStepDataBundle.getCountryCode(), captureStepDataBundle.getDocSide(), captureStepDataBundle.getDocumentFormat(), this.announcementService.isEnabled()));
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        if (i == 2) {
            return new StringRepresentation.SingleStringResIdRepresentation(R.string.onfido_selfie_capture_frame_accessibility);
        }
        if (i == 3) {
            return new StringRepresentation.SingleStringResIdRepresentation(R.string.onfido_video_capture_frame_accessibility);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* renamed from: getRejectionCount$onfido_capture_sdk_core_release, reason: from getter */
    public final int getRejectionCount() {
        return this.rejectionCount;
    }

    public final List<Validation> getRequiredDocumentValidations$onfido_capture_sdk_core_release(DocumentType documentType, DocSide documentSide) {
        Intrinsics.checkNotNullParameter(documentSide, "documentSide");
        return this.backendDocumentValidationsManager.getRequiredValidations(documentType, documentSide, this.rejectionCount);
    }

    public final State getState$onfido_capture_sdk_core_release() {
        return new State(this.rejectionCount, this.takenPhotoCount, this.documentProcessingFailureAnalyzer.getProcessingFailureCounts());
    }

    /* renamed from: getTakenPhotoCount$onfido_capture_sdk_core_release, reason: from getter */
    public final int getTakenPhotoCount() {
        return this.takenPhotoCount;
    }

    public final LivenessPerformedChallenges getUploadChallengesList$onfido_capture_sdk_core_release() {
        return this.livenessInteractor.getLivenessPerformedChallenges();
    }

    /* renamed from: isAutoCaptured$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsAutoCaptured() {
        return this.isAutoCaptured;
    }

    public final boolean isCameraXEnabled$onfido_capture_sdk_core_release() {
        return this.onfidoRemoteConfig.isCameraXEnabled();
    }

    /* renamed from: isDisplayingOverlay$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsDisplayingOverlay() {
        return this.isDisplayingOverlay;
    }

    public final boolean isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release(DocumentProcessingResults results) {
        Intrinsics.checkNotNullParameter(results, "results");
        return this.shouldWaitForMRZExtractionResult ? this.isMRZExtractionTimeExceeded && results.isValidDocumentImage() : results.isValidDocumentImage();
    }

    public final boolean isFourByThreeEnabled$onfido_capture_sdk_core_release() {
        return this.onfidoRemoteConfig.isFourByThreeEnabled();
    }

    public final void issueNextChallenge$onfido_capture_sdk_core_release() {
        this.livenessInteractor.getLivenessControlButtonSubject().onNext(Unit.INSTANCE);
    }

    public final void onAutoLivenessRecordingStart$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoAutoRecordingStarted$onfido_capture_sdk_core_release();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.startLivenessVideoRecording(false);
    }

    public final void onBinaryUploaded$onfido_capture_sdk_core_release(UploadBinaryResult uploadBinaryResult) {
        View view;
        Intrinsics.checkNotNullParameter(uploadBinaryResult, "uploadBinaryResult");
        this.uploadBinaryResult = uploadBinaryResult;
        String documentId = uploadBinaryResult.getDocumentId();
        if (!shouldRecordDocumentVideo() || documentId == null) {
            checkUploadBinaryResult(uploadBinaryResult);
        } else {
            trackWaitingScreenCompleted();
            String str = this.capturedVideoFilePath;
            Unit unit = null;
            CaptureStepDataBundle captureStepDataBundle = null;
            if (str != null) {
                View view2 = this.view;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                } else {
                    view = view2;
                }
                CaptureStepDataBundle captureStepDataBundle2 = this.captureStepDataBundle;
                if (captureStepDataBundle2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                    captureStepDataBundle2 = null;
                }
                DocSide docSide = captureStepDataBundle2.getDocSide();
                if (docSide == null) {
                    docSide = DocSide.FRONT;
                }
                CaptureStepDataBundle captureStepDataBundle3 = this.captureStepDataBundle;
                if (captureStepDataBundle3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                    captureStepDataBundle3 = null;
                }
                DocumentType documentType = captureStepDataBundle3.getDocumentType();
                if (documentType == null) {
                    documentType = DocumentType.UNKNOWN;
                }
                CaptureStepDataBundle captureStepDataBundle4 = this.captureStepDataBundle;
                if (captureStepDataBundle4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureStepDataBundle");
                } else {
                    captureStepDataBundle = captureStepDataBundle4;
                }
                view.uploadVideo(documentId, str, docSide, documentType, captureStepDataBundle.getCountryCode());
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                onDocumentVideoUploaded$onfido_capture_sdk_core_release("");
            }
        }
        this.captureTracker.trackDocumentUploadCompleted();
    }

    public final void onCameraStarted$onfido_capture_sdk_core_release() {
        View view = null;
        this.extractedMRZDocument = null;
        this.firstFrameEmitTime = 0L;
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view2;
        }
        if (view.getCaptureType().isPicture()) {
            if (shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release()) {
                view.deactivateCaptureButton();
            } else {
                view.displayCaptureButton();
            }
            view.setupCaptureButton();
            if (hasNativeLibrary()) {
                view.setGlareWarningContent();
            }
        }
        if (isMlModelAutoCaptureEnabled()) {
            observeAutoCapture();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureConfirmed() {
        String str;
        this.livenessTracker.trackFaceConfirmationUploadButtonClicked$onfido_capture_sdk_core_release(getCaptureType(), this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
        OnfidoImage onfidoImage = this.capturedImage;
        if (onfidoImage == null) {
            onCaptureDiscarded();
            return;
        }
        if (shouldUploadDocument()) {
            int i = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
            if (i == 1) {
                str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT;
            } else if (i == 2) {
                str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_FACE;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                str = WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_VIDEO;
            }
            showLoading(str);
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            view.uploadImage(onfidoImage.getData());
        } else {
            checkUploading();
        }
        trackDocumentCaptureFlowCompleted();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureDiscarded() {
        this.livenessTracker.trackFaceConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release(getCaptureType(), this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.openCaptureScreen();
    }

    public final void onCaptureScreenResumedAfterCameraInitialized$onfido_capture_sdk_core_release(boolean wasConfirmationScreenShown) {
        if (getCaptureType().isDocument() && this.onfidoRemoteConfig.isMultiImageCaptureEnabled()) {
            View view = this.view;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            view.hideVideoRecordingProgressBar();
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view3 = null;
            }
            view3.resetDocumentRecordingState();
            if (wasConfirmationScreenShown || !shouldAutoCaptureDocument$onfido_capture_sdk_core_release()) {
                View view4 = this.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view2 = view4;
                }
                view2.displayCaptureButton();
            }
        }
    }

    public final void onConfirmationStepTracking$onfido_capture_sdk_core_release() {
        CaptureType captureType = getCaptureType();
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        CaptureStepDataBundle captureStepDataBundle = view.getCaptureStepDataBundle();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        boolean zIsPortrait$onfido_capture_sdk_core_release = view2.getScreenOrientation().isPortrait$onfido_capture_sdk_core_release();
        if (captureType == CaptureType.DOCUMENT) {
            trackDocumentConfirmation(captureStepDataBundle);
        } else {
            trackFaceConfirmation(zIsPortrait$onfido_capture_sdk_core_release, captureType);
        }
    }

    public final void onDestroy$onfido_capture_sdk_core_release() {
        this.rectangleDetector.close();
        CoroutineScopeKt.cancel$default(this.presenterScope, null, 1, null);
    }

    public final void onDocumentVideoUploaded$onfido_capture_sdk_core_release(String documentVideoId) {
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        this.documentVideoId = documentVideoId;
        View view = this.view;
        Unit unit = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        trackWaitingScreenCompleted();
        UploadBinaryResult uploadBinaryResult = this.uploadBinaryResult;
        if (uploadBinaryResult != null) {
            checkUploadBinaryResult(uploadBinaryResult);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            onCaptureDiscarded();
        }
    }

    public final void onErrorPictureTaking$onfido_capture_sdk_core_release() {
        if (this.capturedImage != null) {
            Timber.INSTANCE.i("An issue occurred after the image capture. But, it has been ignored!", new Object[0]);
            return;
        }
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.showErrorMessage(R.string.onfido_generic_error_title, getCaptureErrorMessage(), new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onErrorPictureTaking$1
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
                if (this.this$0.onfidoRemoteConfig.getCameraXConfiguration().isFallbackEnabled()) {
                    Timber.INSTANCE.e("CameraX encountered an issue, switching to Camera1 API", new Object[0]);
                    DefaultOnfidoRemoteConfig.INSTANCE.setCameraXEnabled(false);
                }
                CapturePresenter.View view4 = this.this$0.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view4 = null;
                }
                view4.restart();
            }
        });
    }

    public final void onErrorVideoTaking$onfido_capture_sdk_core_release() {
        getDocumentVideoRecordingCompositeDisposable().clear();
        if (getCaptureType() != CaptureType.DOCUMENT || this.onfidoRemoteConfig.getDocumentCapture().getVideoRequired()) {
            onErrorPictureTaking$onfido_capture_sdk_core_release();
        }
    }

    public final void onFaceDetected$onfido_capture_sdk_core_release() {
        disposeFaceDetectionSubscriptions();
    }

    public final void onFaceSelfieUploaded$onfido_capture_sdk_core_release() {
        trackWaitingScreenCompleted();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        long currentTimestamp = this.timeProvider.getCurrentTimestamp() - this.faceSelfieUploadStartTime;
        this.currentCaptureFlowError = null;
        this.captureTracker.trackFaceSelfieConfirmationUploadStatus$onfido_capture_sdk_core_release(currentTimestamp, null, this.takenPhotoCount, this.rejectionCount);
        this.captureTracker.trackFaceSelfieUploadCompleted$onfido_capture_sdk_core_release(currentTimestamp, this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
    }

    public final void onFlowUserExit() {
        this.flowTracker.trackFlowUserExitButtonClicked(toStep());
    }

    public final void onFlowUserExitCancel() {
        this.flowTracker.trackFlowUserExitCanceled(toStep());
    }

    public final void onFlowUserExitConfirmed() {
        this.flowTracker.trackFlowUserExitConfirmed(toStep());
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.finishWithResultExitUserFlow();
    }

    public final void onGeneralUploadError$onfido_capture_sdk_core_release() {
        int i;
        trackCaptureError$onfido_capture_sdk_core_release(getCaptureType(), ErrorType.Generic.INSTANCE);
        trackWaitingScreenCompleted();
        int i2 = WhenMappings.$EnumSwitchMapping$0[getCaptureType().ordinal()];
        if (i2 == 1) {
            i = R.string.onfido_generic_error_doc_capture;
        } else if (i2 == 2) {
            i = R.string.onfido_generic_error_face_capture;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.onfido_generic_error_video_capture;
        }
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.showErrorMessage(R.string.onfido_generic_error_network_title, i, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onGeneralUploadError$1
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
                CapturePresenter.View view4 = this.this$0.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view4 = null;
                }
                view4.openCaptureScreen();
            }
        });
    }

    public final void onManualLivenessNextClick$onfido_capture_sdk_core_release() {
        trackVideoButtonClicked();
        issueNextChallenge$onfido_capture_sdk_core_release();
    }

    public final void onManualLivenessRecordingStart$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoManualRecordingStarted$onfido_capture_sdk_core_release();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.startLivenessVideoRecording(true);
    }

    public final void onNextChallenge$onfido_capture_sdk_core_release(LivenessChallenge challenge) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        if (challenge instanceof MovementLivenessChallenge) {
            view.hideLivenessControlButton();
            startMovementChallengeTimeout();
        } else if (challenge instanceof ReciteLivenessChallenge) {
            stopMovementChallengeTimeout();
            view.hideLivenessControlButton();
            view.showLivenessButtonAndFocusWithDelay();
        }
    }

    public final void onNextFaceDetectionFrame$onfido_capture_sdk_core_release(FaceDetectionFrame frameData) {
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        this.faceDetector.getFaceDetectionSubject().onNext(frameData);
    }

    public final void onNextFrame$onfido_capture_sdk_core_release(DocumentDetectionFrame frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        if (this.firstFrameEmitTime == 0) {
            this.emittedFramesCount = 0;
            this.processedFramesCount = 0;
            this.firstFrameEmitTime = this.timeProvider.getCurrentTimestamp();
        }
        this.emittedFramesCount++;
        if (isMlModelAutoCaptureEnabled()) {
            DocumentValidationTargets documentValidationTargets = this.documentValidationTarget;
            if (documentValidationTargets != null) {
                BuildersKt__Builders_commonKt.launch$default(this.presenterScope, null, null, new CapturePresenter$onNextFrame$1$1(this, frame, documentValidationTargets, null), 3, null);
            }
        } else {
            frame.getBitmap().recycle();
            this.nativeDetector.getFrameData().onNext(frame);
            this.documentFrameBehaviorSubject.onNext(frame);
        }
        BuildersKt__Builders_commonKt.launch$default(this.presenterScope, null, null, new CapturePresenter$onNextFrame$2(this, frame, null), 3, null);
    }

    public final void onOverlayMetricsChanged$onfido_capture_sdk_core_release(OverlayMetrics overlayMetrics) {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.visibleRect = overlayMetrics.getRealCaptureRect();
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        DocumentType documentType = view.getDocumentType();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        DocSide docSide = view2.getDocSide();
        OnfidoRectF.Companion companion = OnfidoRectF.INSTANCE;
        OnfidoRectF onfidoRectF = companion.toOnfidoRectF(overlayMetrics.getVisibleCaptureRect());
        OnfidoRectF onfidoRectF2 = companion.toOnfidoRectF(overlayMetrics.getRealCaptureRect());
        Duration.Companion companion2 = Duration.INSTANCE;
        this.documentValidationTarget = new DocumentValidationTargets(documentType, docSide, onfidoRectF, onfidoRectF2, DurationKt.toDuration(this.onfidoRemoteConfig.getDocumentDetectionExperiment().getHoldDurationInMs(), DurationUnit.MILLISECONDS), null);
        this.overlayMetricsBehaviorSubject.onNext(overlayMetrics);
    }

    public final void onPause$onfido_capture_sdk_core_release() {
        stopDocumentVideoRecordingSubscription();
        Job job = this.autoCaptureJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void onPictureCaptured$onfido_capture_sdk_core_release(OnfidoImage image) {
        Intrinsics.checkNotNullParameter(image, "image");
        this.takenPhotoCount++;
        this.capturedImage = image;
        this.uploadBinaryResult = null;
        this.documentVideoId = "";
        if (shouldRecordDocumentVideo()) {
            startDocumentVideoRecording();
        } else {
            applyValidationsAfterAnimationDelay((getCaptureType() == CaptureType.DOCUMENT && this.isAutoCaptured) ? CONFIRMATION_VIEW_ANIM_DELAY : 0L);
        }
    }

    public final void onRecordingStarted$onfido_capture_sdk_core_release(boolean isStartedManually) {
        if (this.livenessInteractor.getAvailableStorageSpace() < 5500000) {
            CompositeDisposable compositeSubscription = getCompositeSubscription();
            Disposable disposableSubscribe = Observable.interval(1000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onRecordingStarted$1
                public final Long apply(long j) {
                    return Long.valueOf(this.this$0.livenessInteractor.getAvailableStorageSpace());
                }

                @Override // io.reactivex.rxjava3.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return apply(((Number) obj).longValue());
                }
            }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onRecordingStarted$2
                public final boolean test(long j) {
                    return j < 500000;
                }

                @Override // io.reactivex.rxjava3.functions.Predicate
                public /* bridge */ /* synthetic */ boolean test(Object obj) {
                    return test(((Number) obj).longValue());
                }
            }).take(1L).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onRecordingStarted$3
                public final void accept(long j) {
                    CapturePresenter.View view = this.this$0.view;
                    if (view == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        view = null;
                    }
                    view.onStorageThresholdReached();
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public /* bridge */ /* synthetic */ void accept(Object obj) {
                    accept(((Number) obj).longValue());
                }
            }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onRecordingStarted$4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Timber.INSTANCE.e(it, "Error on available storage calculation: " + it.getMessage(), new Object[0]);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
            RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
        }
        disposeFaceDetectionSubscriptions();
        if (isStartedManually) {
            return;
        }
        observeFaceOut();
    }

    public final void onStart$onfido_capture_sdk_core_release(DocSide docSide, boolean isFirstStart, boolean isOnConfirmationStep) {
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        CaptureType captureType = getCaptureType();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        DocumentType documentType = view.getDocumentType();
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view2 = null;
        }
        CountryCode countryCode = view2.getCountryCode();
        if (isOnConfirmationStep) {
            onConfirmationStepTracking$onfido_capture_sdk_core_release();
        } else {
            trackCapture(captureType);
        }
        if (shouldEnableAccessibilityCapture()) {
            enableAccessibilityCapture(documentType, docSide);
        }
        this.mrzExtractionResultMap.clear();
        this.barcodeValidationSuspender.reset$onfido_capture_sdk_core_release();
        this.retainableValidationsResult.clear$onfido_capture_sdk_core_release();
        this.isAutoCaptured = false;
        this.capturedImage = null;
        if (captureType != CaptureType.DOCUMENT || documentType == null || isMlModelAutoCaptureEnabled()) {
            return;
        }
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = getImageProcessingObservable(documentType, countryCode, docSide, isFirstStart).compose(this.documentDelayTransformer.transform$onfido_capture_sdk_core_release(documentType, countryCode, isFirstStart, shouldEnableAccessibilityCapture())).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.onStart$lambda$5(this.f$0);
            }
        }).subscribe(getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release(), new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onStart$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Error on glare detector", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    public final void onUploadFailure$onfido_capture_sdk_core_release(final boolean isOnCaptureScreen) {
        trackCaptureError$onfido_capture_sdk_core_release(getCaptureType(), ErrorType.Network.INSTANCE);
        trackWaitingScreenCompleted();
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.showErrorMessage(R.string.onfido_generic_error_network_title, R.string.onfido_generic_error_network_detail, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onUploadFailure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (isOnCaptureScreen) {
                    CapturePresenter.View view4 = this.view;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        view4 = null;
                    }
                    view4.openCaptureScreen();
                }
            }
        });
    }

    public final void onUploadFailureWithGeoblocking$onfido_capture_sdk_core_release(final boolean isOnCaptureScreen) {
        trackCaptureError$onfido_capture_sdk_core_release(getCaptureType(), ErrorType.Network.INSTANCE);
        trackWaitingScreenCompleted();
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        view2.showErrorMessage(R.string.onfido_generic_errors_geoblocked_error_message, R.string.onfido_generic_errors_geoblocked_error_instruction, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$onUploadFailureWithGeoblocking$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (isOnCaptureScreen) {
                    CapturePresenter.View view4 = this.view;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        view4 = null;
                    }
                    view4.openCaptureScreen();
                }
            }
        });
    }

    public final void onUploadValidationError$onfido_capture_sdk_core_release(ErrorType errorType, CaptureType captureType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        trackUploadValidationError(captureType, errorType);
        if (isCheckingImageQuality()) {
            trackWaitingScreenCompleted();
        }
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideLoading();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view3 = null;
        }
        view3.showConfirmationStep();
        if (!isBackSideOfRomanianNationalId()) {
            showErrorType$onfido_capture_sdk_core_release(errorType);
        }
        if (shouldForceRetry()) {
            View view4 = this.view;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view2 = view4;
            }
            view2.setForceRetryButton();
        } else {
            View view5 = this.view;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view2 = view5;
            }
            view2.setConfirmationButtons(false);
        }
        this.rejectionCount++;
    }

    public final void onVideoCaptured$onfido_capture_sdk_core_release(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.capturedVideoFilePath = filePath;
        if (getCaptureType() == CaptureType.VIDEO) {
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            view.showFaceLivenessConfirmationScreen(filePath, getUploadChallengesList$onfido_capture_sdk_core_release());
        }
    }

    public final void onVideoRecordingCanceled$onfido_capture_sdk_core_release() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        if (shouldRecordDocumentVideo()) {
            view.hideVideoRecordingProgressBar();
        }
    }

    public final void onViewResumed$onfido_capture_sdk_core_release(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        View view = null;
        if (!(getMissingPermissions(captureType).length == 0)) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view2 = null;
            }
            view2.destroyWithCanceledResult();
        } else {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view3 = null;
            }
            view3.setupUploadService();
            View view4 = this.view;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view4 = null;
            }
            view4.setOverlay();
            setupUIState$onfido_capture_sdk_core_release();
        }
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view5;
        }
        view.trackNavigationCompleted(getPerformanceTrackingScreen());
    }

    public final SdkUploadMetaData sdkUploadMetadata$onfido_capture_sdk_core_release() {
        return this.sdkUploadMetaDataHelper.create();
    }

    public final void setAutoCaptured$onfido_capture_sdk_core_release(boolean z) {
        this.isAutoCaptured = z;
    }

    public final void setCurrentCaptureFlowError$onfido_capture_sdk_core_release(ErrorType errorType) {
        this.currentCaptureFlowError = errorType;
    }

    public final void setDisplayingOverlay$onfido_capture_sdk_core_release(boolean z) {
        this.isDisplayingOverlay = z;
    }

    public final void setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release(DocumentCaptureResultConsumer documentCaptureResultConsumer) {
        Intrinsics.checkNotNullParameter(documentCaptureResultConsumer, "<set-?>");
        this.documentCaptureResultConsumer = documentCaptureResultConsumer;
    }

    public final void setMRZResult$onfido_capture_sdk_core_release(MRZValidationResult result) {
        String str;
        if (result == null || !result.getWasExecuted()) {
            this.extraFileInfo = null;
            return;
        }
        if (result.isMrzReadable()) {
            Timber.INSTANCE.i("NFC Logger - MRZ is readable", new Object[0]);
            str = MRZ_IS_READABLE;
        } else {
            Timber.INSTANCE.i("NFC Logger - MRZ is not readable", new Object[0]);
            str = MRZ_IS_NOT_READABLE;
        }
        this.extraFileInfo = str;
    }

    public final void setRejectionCount$onfido_capture_sdk_core_release(int i) {
        this.rejectionCount = i;
    }

    public final void setState$onfido_capture_sdk_core_release(State value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.rejectionCount = value.getNumValidationErrors();
        this.takenPhotoCount = value.getNumOfTakenPictures();
        this.documentProcessingFailureAnalyzer.updateFailureCounts(value.getDocumentProcessingFailureCounts());
    }

    public final void setTakenPhotoCount$onfido_capture_sdk_core_release(int i) {
        this.takenPhotoCount = i;
    }

    public final void setup$onfido_capture_sdk_core_release(View view, OnfidoConfig onfidoConfig, CaptureStepDataBundle captureStepDataBundle, NfcArguments nfcArguments, boolean isProofOfAddress) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        this.view = view;
        this.onfidoConfig = onfidoConfig;
        this.captureStepDataBundle = captureStepDataBundle;
        this.nfcArguments = nfcArguments;
        this.rejectionCount = 0;
        setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release(new DocumentCaptureResultConsumer(view, this, this.onfidoRemoteConfig));
        this.retainableValidationsResult.clear$onfido_capture_sdk_core_release();
        this.barcodeValidationSuspender.reset$onfido_capture_sdk_core_release();
        this.isProofOfAddress = isProofOfAddress;
    }

    public final void setupUIState$onfido_capture_sdk_core_release() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        if (view.getCaptureType().isPicture()) {
            view.setupConfirmationButtons();
        }
        checkDocumentFormat();
    }

    public final boolean shouldAutoCaptureDocument$onfido_capture_sdk_core_release() {
        if (isDocumentCapture()) {
            View view = this.view;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            if (view.getDocumentType() != null && hasNativeLibrary()) {
                View view3 = this.view;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view3 = null;
                }
                DocumentType documentType = view3.getDocumentType();
                View view4 = this.view;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view2 = view4;
                }
                if (shouldAutocapture$onfido_capture_sdk_core_release(documentType, view2.getCountryCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean shouldAutocapture$onfido_capture_sdk_core_release(DocumentType documentType, CountryCode countryCode) {
        if (documentType != null) {
            return this.documentConfigurationManager.shouldAutocapture(documentType, countryCode);
        }
        return false;
    }

    public final boolean shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release() {
        return this.announcementService.isEnabled() && shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
    }

    public final boolean shouldPerformFaceValidation$onfido_capture_sdk_core_release() {
        return this.backendDocumentValidationsManager.getShouldPerformFaceValidation();
    }

    public final boolean shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.DRIVING_LICENCE, CountryCode.FR);
    }

    public final boolean shouldShowGermanDLOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.DRIVING_LICENCE, CountryCode.DE);
    }

    public final boolean shouldShowInitialOverlay$onfido_capture_sdk_core_release(DocumentType documentType) {
        if (documentType != null) {
            return this.documentConfigurationManager.shouldShowInitialOverlay(documentType);
        }
        return false;
    }

    public final boolean shouldShowItalianIDOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.NATIONAL_IDENTITY_CARD, CountryCode.IT);
    }

    public final boolean shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.NATIONAL_IDENTITY_CARD, CountryCode.ZA);
    }

    public final void showErrorType$onfido_capture_sdk_core_release(ErrorType errorType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        ErrorDescriptor errorDescriptorMapErrorType = ErrorTypeUtilsKt.mapErrorType(errorType);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.showError(errorDescriptorMapErrorType);
    }

    public final void showLoadingDialog$onfido_capture_sdk_core_release(String reason) {
        LoadingFragment.Companion.DialogMode uploading;
        Intrinsics.checkNotNullParameter(reason, "reason");
        View view = null;
        if (isCheckingImageQuality()) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            uploading = new LoadingFragment.Companion.DialogMode.CheckingImageQuality(reason);
        } else {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view3;
            }
            uploading = new LoadingFragment.Companion.DialogMode.Uploading(reason);
        }
        view.showDialog(uploading);
    }

    public final void startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release() {
        final SdkConfiguration.DocumentCapture documentCapture = this.onfidoRemoteConfig.getDocumentCapture();
        CompositeDisposable documentVideoRecordingCompositeDisposable = getDocumentVideoRecordingCompositeDisposable();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Disposable disposableSubscribe = Observable.interval(1L, timeUnit, this.schedulersProvider.getTimer()).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).takeUntil(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$1
            public final boolean test(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                return view.hasValidRecording();
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$2
            public final boolean test(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                return view.hasValidRecording();
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).timeout(documentCapture.getVideoTimeoutMs(), timeUnit, this.schedulersProvider.getTimer()).firstElement().flatMapObservable(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$3
            public final ObservableSource<? extends Long> apply(long j) {
                return Observable.intervalRange(0L, documentCapture.getVideoLengthMs(), 0L, 1L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer());
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$4
            public final boolean test(long j) {
                return j == documentCapture.getTorchTurnOnTimeMs();
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$5
            public final void accept(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.enableTorch(true);
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startDocumentVideoRecordingTimer$6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.onErrorVideoTaking$onfido_capture_sdk_core_release();
                Timber.INSTANCE.e(it, "startDocumentVideoRecordingTimer failed", new Object[0]);
            }
        }, new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                this.f$0.stopDocumentRecording();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(documentVideoRecordingCompositeDisposable, disposableSubscribe);
    }

    public final void startLivenessProcessing$onfido_capture_sdk_core_release(final LivenessChallengesViewModel livenessChallengesViewModel) {
        Intrinsics.checkNotNullParameter(livenessChallengesViewModel, "livenessChallengesViewModel");
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = Observable.zip(this.livenessInteractor.getLivenessControlButtonSubject(), Observable.range(0, livenessChallengesViewModel.getChallenges().size() + 1), new BiFunction() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$1
            public final Integer apply(Unit unit, int i) {
                return Integer.valueOf(i);
            }

            @Override // io.reactivex.rxjava3.functions.BiFunction
            public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2) {
                return apply((Unit) obj, ((Number) obj2).intValue());
            }
        }).concatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$2
            public final ObservableSource<? extends Pair<LivenessChallengeViewModel, Long>> apply(int i) {
                if (i >= livenessChallengesViewModel.getChallenges().size()) {
                    return Observable.empty();
                }
                return Observable.just(TuplesKt.to(new LivenessChallengeViewModel(i, livenessChallengesViewModel.getChallenges().get(i), i == CollectionsKt.getLastIndex(livenessChallengesViewModel.getChallenges())), Long.valueOf(this.timeProvider.getCurrentTimestamp())));
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).intValue());
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<LivenessChallengeViewModel, Long> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                int index = it.getFirst().getIndex();
                if (index == 0) {
                    this.this$0.livenessInteractor.initializeLivenessVideoTimestamp();
                } else {
                    this.this$0.pushPerformedChallenge(livenessChallengesViewModel.getChallenges().get(index - 1));
                }
            }
        }).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.livenessInteractor.initializePerformedChallenges(livenessChallengesViewModel.getId());
            }
        }).doOnComplete(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CapturePresenter.startLivenessProcessing$lambda$8(livenessChallengesViewModel, this);
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<LivenessChallengeViewModel, Long> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onNextChallenge(it.getFirst());
                view.removeDummyViewsAccessibility();
                view.makeToolbarTitleNotImportantForAccessibility();
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on liveness challenge provider: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
        CompositeDisposable faceDetectionCompositeDisposable = getFaceDetectionCompositeDisposable();
        long preRecordingInstructionsReadingTimeMilisseconds = this.livenessInteractor.getPreRecordingInstructionsReadingTimeMilisseconds();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Disposable disposableSubscribe2 = Flowable.timer(preRecordingInstructionsReadingTimeMilisseconds, timeUnit, this.schedulersProvider.getTimer()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$8$1
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }

            public final Publisher<? extends FaceDetectionResult> apply(long j) {
                Flowable<FaceDetectionResult> flowableObserveFaceTracking = this.this$0.faceDetector.observeFaceTracking();
                OnfidoConfig onfidoConfig = this.this$0.onfidoConfig;
                if (onfidoConfig == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onfidoConfig");
                    onfidoConfig = null;
                }
                return flowableObserveFaceTracking.sample(onfidoConfig.getManualLivenessCapture() ? 0L : 200L, TimeUnit.MILLISECONDS, this.this$0.schedulersProvider.getTimer());
            }
        }).subscribeOn(this.schedulersProvider.getComputation()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$8$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceDetectionResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onFaceDetected(it);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$8$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on observing the face detection results: " + it.getMessage(), new Object[0]);
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onFaceDetectionTimeout();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        RxExtensionsKt.plusAssign(faceDetectionCompositeDisposable, disposableSubscribe2);
        OnfidoConfig onfidoConfig = this.onfidoConfig;
        if (onfidoConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoConfig");
            onfidoConfig = null;
        }
        Disposable disposableSubscribe3 = Observable.timer(onfidoConfig.getManualLivenessCapture() ? 0L : 5000L, timeUnit, this.schedulersProvider.getTimer()).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$8$4
            public final void accept(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.onFaceDetectionTimeout();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startLivenessProcessing$8$5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on face detection timeout timer: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe3, "subscribe(...)");
        RxExtensionsKt.plusAssign(faceDetectionCompositeDisposable, disposableSubscribe3);
    }

    public final void startOverlayDisplayTimer$onfido_capture_sdk_core_release() {
        CompositeDisposable compositeSubscription = getCompositeSubscription();
        Disposable disposableSubscribe = Observable.timer(4000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startOverlayDisplayTimer$1
            public final void accept(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.hideDocumentOverlay();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startOverlayDisplayTimer$2
            public final void accept(long j) {
                CapturePresenter.View view = this.this$0.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.hideDocumentOverlay();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CapturePresenter$startOverlayDisplayTimer$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on startOverlayDisplayTimer: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
    }

    public final void stop$onfido_capture_sdk_core_release() {
        getCompositeSubscription().clear();
        this.nativeDetector.clearEdges();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.hideDocumentOverlay();
        disposeAutocaptureSubscriptions();
        disposeFaceDetectionSubscriptions();
        stopFaceTracking$onfido_capture_sdk_core_release();
        stopMovementChallengeTimeout();
        this.shouldWaitForMRZExtractionResult = false;
        this.isMRZExtractionTimeExceeded = false;
        Job job = this.autoCaptureJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void stopFaceTracking$onfido_capture_sdk_core_release() {
        getFaceTrackingCompositeDisposable().clear();
        this.faceDetector.close();
    }

    public final void trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release() {
        this.captureTracker.trackCaptureButtonClicked$onfido_capture_sdk_core_release(getCaptureType(), this.currentCaptureFlowError, this.takenPhotoCount + 1, this.rejectionCount);
    }

    public final void trackCaptureBackButtonClicked$onfido_capture_sdk_core_release() {
        this.captureTracker.trackBackButtonClicked$onfido_capture_sdk_core_release(getCaptureType(), this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
    }

    public final void trackCaptureError$onfido_capture_sdk_core_release(CaptureType captureType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        trackCaptureError$onfido_capture_sdk_core_release(captureType, null);
    }

    public final void trackConfirmationBackButtonClicked$onfido_capture_sdk_core_release() {
        this.livenessTracker.trackFaceConfirmationBackButtonClicked$onfido_capture_sdk_core_release(getCaptureType(), this.currentCaptureFlowError, this.takenPhotoCount, this.rejectionCount);
    }

    public final void trackLivenessChallenge$onfido_capture_sdk_core_release(int challengeIndex, LivenessChallengeType challengeType) {
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        if (challengeIndex == 0) {
            this.captureTracker.trackVideoCaptureFirstChallenge$onfido_capture_sdk_core_release(challengeType);
        } else if (challengeIndex == 1) {
            this.captureTracker.trackVideoCaptureSecondChallenge$onfido_capture_sdk_core_release(challengeType);
        }
        String strName = challengeType.name();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = strName.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.livenessTracker.trackLivenessChallenge$onfido_capture_sdk_core_release(challengeIndex + 1, lowerCase);
    }

    public final void trackUploadStarted$onfido_capture_sdk_core_release() {
        NFCOptions nfcOptions;
        CaptureTracker captureTracker = this.captureTracker;
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        CaptureStepDataBundle captureStepDataBundle = view.getCaptureStepDataBundle();
        int i = this.takenPhotoCount;
        int maxTotalRetries = this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries();
        boolean zShouldAutoCaptureDocument$onfido_capture_sdk_core_release = shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
        boolean z = this.isAutoCaptured;
        boolean zIsValid = this.processingResults.getMrzValidationResult().isValid();
        NfcArguments nfcArguments = this.nfcArguments;
        captureTracker.trackUploadStarted(captureStepDataBundle, i, maxTotalRetries, zShouldAutoCaptureDocument$onfido_capture_sdk_core_release, z, zIsValid, (nfcArguments == null || (nfcOptions = nfcArguments.getNfcOptions()) == null) ? false : NFCOptionsKt.isEnabled(nfcOptions));
    }

    public final void trackVideoCaptureTimeout$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
    }

    public final void trackVideoFinishButtonClicked$onfido_capture_sdk_core_release(long duration) {
        this.captureTracker.trackVideoFinishButtonClicked$onfido_capture_sdk_core_release(duration);
    }

    public final void trackVideoNextButtonClicked$onfido_capture_sdk_core_release(long duration) {
        this.captureTracker.trackVideoNextButtonClicked$onfido_capture_sdk_core_release(duration);
    }

    public final void trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
    }

    public final void uploadImageForValidation$onfido_capture_sdk_core_release(byte[] jpegData) {
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        View view = this.view;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        DocumentType documentType = view.getDocumentType();
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view2 = view3;
        }
        List<Validation> requiredDocumentValidations$onfido_capture_sdk_core_release = getRequiredDocumentValidations$onfido_capture_sdk_core_release(documentType, view2.getDocSide());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(requiredDocumentValidations$onfido_capture_sdk_core_release, 10));
        for (Validation validation : requiredDocumentValidations$onfido_capture_sdk_core_release) {
            if (isBackSideOfRomanianNationalId()) {
                validation = new Validation(validation.getValidationType(), ValidationLevel.WARNING);
            }
            arrayList.add(validation);
        }
        if (!this.onfidoRemoteConfig.isMediaCallbackCroppingDisabled()) {
            this.croppedImage = jpegData;
        }
        uploadDocumentMedia(arrayList, jpegData);
    }

    public final SdkUploadMetaData sdkUploadMetadata$onfido_capture_sdk_core_release(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        return this.sdkUploadMetaDataHelper.create(this.processingResults, this.documentProcessingFailureAnalyzer.getProcessingFailureCounts().getBlurFailureCount(), isMRZExtracted(), this.takenPhotoCount, captureStepDataBundle);
    }

    public final void trackCaptureError$onfido_capture_sdk_core_release(CaptureType captureType, ErrorType errorType) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        if (errorType != null) {
            if (captureType != CaptureType.DOCUMENT) {
                this.currentCaptureFlowError = errorType;
                if (captureType == CaptureType.FACE) {
                    this.captureTracker.trackFaceSelfieConfirmationUploadStatus$onfido_capture_sdk_core_release(this.timeProvider.getCurrentTimestamp() - this.faceSelfieUploadStartTime, errorType, this.takenPhotoCount, this.rejectionCount);
                    return;
                }
                return;
            }
            CaptureTracker captureTracker = this.captureTracker;
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                view = null;
            }
            captureTracker.trackDocumentCaptureError$onfido_capture_sdk_core_release(view.getDocSide());
        }
    }
}
