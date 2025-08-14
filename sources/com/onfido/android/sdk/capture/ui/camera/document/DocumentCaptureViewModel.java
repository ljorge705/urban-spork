package com.onfido.android.sdk.capture.ui.camera.document;

import android.content.SharedPreferences;
import android.graphics.RectF;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.henninghall.date_picker.props.ModeProp;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.rectangle.RectDetectionResult;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorEmpty;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.UiAlerts;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.config.DefaultOnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCase;
import com.onfido.android.sdk.capture.internal.usecase.AccessibleDocumentCaptureUseCaseResult;
import com.onfido.android.sdk.capture.internal.usecase.MediaCallbacksUseCase;
import com.onfido.android.sdk.capture.internal.usecase.NfcUseCase;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationResult;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationTargets;
import com.onfido.android.sdk.capture.internal.usecase.validation.DocumentValidationUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadService;
import com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.DocumentService;
import com.onfido.android.sdk.capture.ui.camera.DocumentValidationWarningsBundleUtilsKt;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.android.sdk.capture.ui.camera.UploadBinaryResult;
import com.onfido.android.sdk.capture.ui.camera.document.CameraState;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.exception.InvalidCertificateException;
import com.onfido.android.sdk.capture.ui.camera.exception.TokenExpiredException;
import com.onfido.android.sdk.capture.ui.camera.rx.DocumentCaptureDelayTransformer;
import com.onfido.android.sdk.capture.ui.camera.util.DocumentValidationResultMapper;
import com.onfido.android.sdk.capture.ui.model.DocumentTypeUIModel;
import com.onfido.android.sdk.capture.ui.model.DocumentUITextModelMapper;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.DocumentUtils;
import com.onfido.android.sdk.capture.utils.ErrorTypeUtilsKt;
import com.onfido.android.sdk.capture.utils.FlowExtKt;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.ImageUtilsExtKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
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
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
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
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.rx3.RxConvertKt;

@Metadata(d1 = {"\u0000ô\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bM\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b+\b\u0000\u0018\u0000 ï\u00032\u00020\u00012\u00020\u0002:\u000eî\u0003ï\u0003ð\u0003ñ\u0003ò\u0003ó\u0003ô\u0003B×\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\u0006\u0010'\u001a\u00020(\u0012\u0006\u0010)\u001a\u00020*\u0012\u0006\u0010+\u001a\u00020,\u0012\u0006\u0010-\u001a\u00020.\u0012\u0006\u0010/\u001a\u000200\u0012\u0006\u00101\u001a\u000202\u0012\u0006\u00103\u001a\u000204\u0012\u0006\u00105\u001a\u000206¢\u0006\u0002\u00107J\u0014\u0010\u009f\u0002\u001a\u00030 \u00022\b\u0010¡\u0002\u001a\u00030ö\u0001H\u0002J#\u0010¢\u0002\u001a\u00030 \u00022\u0007\u0010£\u0002\u001a\u00020S2\b\u0010¤\u0002\u001a\u00030¥\u0002H\u0000¢\u0006\u0003\b¦\u0002J\u0014\u0010§\u0002\u001a\u00030 \u00022\b\u0010¨\u0002\u001a\u00030Ì\u0001H\u0002J\t\u0010©\u0002\u001a\u00020<H\u0002J\n\u0010ª\u0002\u001a\u00030 \u0002H\u0002J\u0014\u0010«\u0002\u001a\u00030 \u00022\b\u0010\u0090\u0002\u001a\u00030¬\u0002H\u0002J\n\u0010\u00ad\u0002\u001a\u00030 \u0002H\u0002J\u0014\u0010®\u0002\u001a\u00030 \u00022\b\u0010\u0090\u0002\u001a\u00030¯\u0002H\u0002J\u0014\u0010°\u0002\u001a\u00030 \u00022\b\u0010\u0090\u0002\u001a\u00030±\u0002H\u0002J\u0014\u0010²\u0002\u001a\u00030 \u00022\b\u0010\u0090\u0002\u001a\u00030\u0091\u0002H\u0002J\u0012\u0010³\u0002\u001a\u00020~2\u0007\u0010´\u0002\u001a\u00020~H\u0002J\n\u0010µ\u0002\u001a\u00030 \u0002H\u0002J\u0016\u0010¶\u0002\u001a\u00030 \u00022\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0002J\u001c\u0010·\u0002\u001a\u00030¸\u00022\n\u0010¹\u0002\u001a\u0005\u0018\u00010\u009b\u0001H\u0000¢\u0006\u0003\bº\u0002J\u0013\u0010»\u0002\u001a\u00030®\u00012\u0007\u0010Ø\u0001\u001a\u00020<H\u0002J\u000f\u0010¼\u0002\u001a\u00020>H\u0000¢\u0006\u0003\b½\u0002J\n\u0010¾\u0002\u001a\u00030Ì\u0001H\u0002J\u001f\u0010¿\u0002\u001a\u000f\u0012\u0004\u0012\u00020L0À\u0002¢\u0006\u0003\bÁ\u00022\u0007\u0010Â\u0002\u001a\u00020<H\u0002J\u0011\u0010Ã\u0002\u001a\n\u0012\u0005\u0012\u00030Å\u00020Ä\u0002H\u0002J\u000f\u0010Æ\u0002\u001a\u00020<H\u0000¢\u0006\u0003\bÇ\u0002J\n\u0010È\u0002\u001a\u00030 \u0002H\u0002J\u0019\u0010É\u0002\u001a\u00030 \u00022\u0007\u0010Ø\u0001\u001a\u00020<H\u0000¢\u0006\u0003\bÊ\u0002J\t\u0010Ë\u0002\u001a\u00020<H\u0002J\t\u0010Ì\u0002\u001a\u00020<H\u0002J\t\u0010Í\u0002\u001a\u00020<H\u0002J\u000f\u0010Î\u0002\u001a\u00020<H\u0000¢\u0006\u0003\bÏ\u0002J\u000f\u0010Ð\u0002\u001a\u00020<H\u0000¢\u0006\u0003\bÑ\u0002J\u0019\u0010Ò\u0002\u001a\u00020<2\b\u0010¡\u0002\u001a\u00030ö\u0001H\u0000¢\u0006\u0003\bÓ\u0002J\t\u0010Ô\u0002\u001a\u00020<H\u0002J\t\u0010Õ\u0002\u001a\u00020<H\u0002J\u000f\u0010Ö\u0002\u001a\u00020<H\u0000¢\u0006\u0003\b×\u0002J\t\u0010Ø\u0002\u001a\u00020<H\u0002J\t\u0010Ù\u0002\u001a\u00020<H\u0002J\n\u0010Ú\u0002\u001a\u00030 \u0002H\u0002J\u0014\u0010Û\u0002\u001a\u00030 \u00022\b\u0010\u0090\u0002\u001a\u00030\u0091\u0002H\u0002J\u0010\u0010Ü\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÝ\u0002J\u0010\u0010Þ\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bß\u0002J\u0019\u0010à\u0002\u001a\u00030 \u00022\u0007\u0010á\u0002\u001a\u00020bH\u0000¢\u0006\u0003\bâ\u0002J\u0010\u0010ã\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bä\u0002J\u0010\u0010å\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bæ\u0002J\u0010\u0010ç\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bè\u0002J\u0012\u0010é\u0002\u001a\u00030 \u00022\u0006\u0010j\u001a\u00020SH\u0002J\u0010\u0010ê\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bë\u0002J\n\u0010ì\u0002\u001a\u00030 \u0002H\u0014J\u001a\u0010í\u0002\u001a\u00030 \u00022\b\u0010\u009a\u0001\u001a\u00030\u009b\u0001H\u0000¢\u0006\u0003\bî\u0002J\u0013\u0010ï\u0002\u001a\u00030 \u00022\u0007\u0010·\u0001\u001a\u00020rH\u0016J\u0010\u0010ð\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bñ\u0002J\n\u0010ò\u0002\u001a\u00030 \u0002H\u0002J\n\u0010ó\u0002\u001a\u00030 \u0002H\u0002J\n\u0010ô\u0002\u001a\u00030 \u0002H\u0002J\u0013\u0010õ\u0002\u001a\u00030 \u00022\u0007\u0010ö\u0002\u001a\u00020rH\u0002J\u0010\u0010÷\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bø\u0002J#\u0010ù\u0002\u001a\u00030 \u00022\u0007\u0010ú\u0002\u001a\u00020S2\b\u0010í\u0001\u001a\u00030î\u0001H\u0000¢\u0006\u0003\bû\u0002J\u001a\u0010ü\u0002\u001a\u00030 \u00022\b\u0010í\u0001\u001a\u00030î\u0001H\u0000¢\u0006\u0003\bý\u0002J\u0010\u0010þ\u0002\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÿ\u0002J\u0019\u0010\u0080\u0003\u001a\u00030 \u00022\u0007\u0010£\u0002\u001a\u00020SH\u0000¢\u0006\u0003\b\u0081\u0003J\u001e\u0010\u0082\u0003\u001a\u00030 \u00022\b\u0010õ\u0001\u001a\u00030ö\u00012\b\u0010ú\u0002\u001a\u00030\u008d\u0001H\u0002J\n\u0010\u0083\u0003\u001a\u00030 \u0002H\u0002J\u0014\u0010\u0084\u0003\u001a\u00030 \u00022\b\u0010\u0085\u0003\u001a\u00030\u0085\u0001H\u0016J\n\u0010\u0086\u0003\u001a\u00030 \u0002H\u0002J\n\u0010\u0087\u0003\u001a\u00030 \u0002H\u0002J\u0014\u0010\u0088\u0003\u001a\u00030 \u00022\b\u0010\u0085\u0003\u001a\u00030\u0085\u0001H\u0002J\u0019\u0010\u0089\u0003\u001a\u00030 \u00022\u0007\u0010\u008a\u0003\u001a\u00020rH\u0000¢\u0006\u0003\b\u008b\u0003J\u0010\u0010\u008c\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\b\u008d\u0003J\u0010\u0010\u008e\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\b\u008f\u0003J#\u0010\u0090\u0003\u001a\u00030 \u00022\u0007\u0010\u0091\u0003\u001a\u00020<2\b\u0010\u0092\u0003\u001a\u00030\u0093\u0003H\u0000¢\u0006\u0003\b\u0094\u0003J\u0014\u0010\u0095\u0003\u001a\u00030 \u00022\b\u0010á\u0002\u001a\u00030\u0096\u0003H\u0002J\u001b\u0010\u0097\u0003\u001a\u00030 \u00022\b\u0010ú\u0002\u001a\u00030\u008d\u0001H\u0082@¢\u0006\u0003\u0010\u0098\u0003J\u0010\u0010\u0099\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\b\u009a\u0003J\u0014\u0010\u009b\u0003\u001a\u00030\u009c\u00032\b\u0010\u009d\u0003\u001a\u00030\u0095\u0001H\u0002J&\u0010\u009e\u0003\u001a\u00030 \u00022\b\u0010\u009f\u0003\u001a\u00030\u0095\u00012\n\u0010é\u0001\u001a\u0005\u0018\u00010è\u0001H\u0000¢\u0006\u0003\b \u0003J\u0016\u0010¡\u0003\u001a\u00030 \u00022\n\u0010á\u0002\u001a\u0005\u0018\u00010¢\u0003H\u0002J\u000f\u0010£\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b¤\u0003J\t\u0010¥\u0003\u001a\u00020<H\u0002J\u000f\u0010¦\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b§\u0003J\t\u0010¨\u0003\u001a\u00020<H\u0002J\u000f\u0010©\u0003\u001a\u00020<H\u0000¢\u0006\u0003\bª\u0003J\u000f\u0010«\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b¬\u0003J!\u0010\u00ad\u0003\u001a\u00020<2\u0007\u0010®\u0003\u001a\u00020<2\u0007\u0010¯\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b°\u0003J!\u0010±\u0003\u001a\u00020<2\u0007\u0010®\u0003\u001a\u00020<2\u0007\u0010¯\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b²\u0003J\u000f\u0010³\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b´\u0003J!\u0010µ\u0003\u001a\u00020<2\u0007\u0010®\u0003\u001a\u00020<2\u0007\u0010¯\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b¶\u0003J-\u0010·\u0003\u001a\u00020<2\u0007\u0010®\u0003\u001a\u00020<2\u0007\u0010¯\u0003\u001a\u00020<2\b\u0010§\u0001\u001a\u00030¨\u00012\u0006\u0010w\u001a\u00020xH\u0002J!\u0010¸\u0003\u001a\u00020<2\u0007\u0010®\u0003\u001a\u00020<2\u0007\u0010¯\u0003\u001a\u00020<H\u0000¢\u0006\u0003\b¹\u0003J\t\u0010º\u0003\u001a\u00020<H\u0002J \u0010»\u0003\u001a\u00030 \u00022\t\b\u0001\u0010¼\u0003\u001a\u00020>2\t\b\u0001\u0010ö\u0002\u001a\u00020>H\u0002J\u0014\u0010½\u0003\u001a\u00030 \u00022\b\u0010¾\u0003\u001a\u00030¿\u0003H\u0002J@\u0010À\u0003\u001a\u00030 \u00022\u0007\u0010Á\u0003\u001a\u00020r2\b\u0010Â\u0003\u001a\u00030\u0085\u00012\u0007\u0010Ã\u0003\u001a\u00020<2\n\u0010Ä\u0003\u001a\u0005\u0018\u00010Å\u00032\f\b\u0002\u0010Æ\u0003\u001a\u0005\u0018\u00010Ç\u0003H\u0002J \u0010È\u0003\u001a\u00030 \u00022\u000e\u0010É\u0003\u001a\t\u0012\u0004\u0012\u00020<0Ê\u0003H\u0000¢\u0006\u0003\bË\u0003J\n\u0010Ì\u0003\u001a\u00030 \u0002H\u0002J\u0010\u0010Í\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÎ\u0003J\n\u0010Ï\u0003\u001a\u00030 \u0002H\u0002J\n\u0010Ð\u0003\u001a\u00030 \u0002H\u0002J\u0010\u0010Ñ\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÒ\u0003J\u001a\u0010Ó\u0003\u001a\u00030 \u00022\b\u0010\u0092\u0003\u001a\u00030\u0093\u0003H\u0000¢\u0006\u0003\bÔ\u0003J\u0010\u0010Õ\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÖ\u0003J\u0010\u0010×\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bØ\u0003J\n\u0010Ù\u0003\u001a\u00030 \u0002H\u0002J\u0010\u0010Ú\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÛ\u0003J\u0010\u0010Ü\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bÝ\u0003J\u0010\u0010Þ\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bß\u0003J\u0010\u0010à\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bá\u0003J\b\u0010â\u0003\u001a\u00030 \u0002J\u0010\u0010ã\u0003\u001a\u00030 \u0002H\u0000¢\u0006\u0003\bä\u0003J\b\u0010å\u0003\u001a\u00030 \u0002J\u0013\u0010æ\u0003\u001a\u00030 \u00022\u0007\u0010´\u0002\u001a\u00020~H\u0002J*\u0010ç\u0003\u001a\u00030 \u00022\u000f\u0010è\u0003\u001a\n\u0012\u0005\u0012\u00030Å\u00020Ä\u00022\u0007\u0010´\u0002\u001a\u00020~H\u0000¢\u0006\u0003\bé\u0003J\u0013\u0010ê\u0003\u001a\u00030 \u00022\u0007\u0010´\u0002\u001a\u00020~H\u0002J#\u0010ë\u0003\u001a\u00020<*\u0005\u0018\u00010¨\u00012\b\u0010ì\u0003\u001a\u00030¨\u00012\u0007\u0010í\u0003\u001a\u00020xH\u0002R\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020:09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010>09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010@09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010F\u001a\b\u0012\u0004\u0012\u00020G09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<09X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020L0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020P0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010NR\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020S0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010NR\u0010\u0010U\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010NR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020:0\\X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u001a\u0010_\u001a\b\u0012\u0004\u0012\u00020<0\\X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010^R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020b0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bc\u0010NR\u001a\u0010d\u001a\u00020eX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR&\u0010j\u001a\u0004\u0018\u00010S8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bk\u0010l\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR*\u0010s\u001a\u0004\u0018\u00010r2\b\u0010q\u001a\u0004\u0018\u00010r8\u0000@BX\u0081\u000e¢\u0006\u000e\n\u0000\u0012\u0004\bt\u0010l\u001a\u0004\bu\u0010vR\u001c\u0010w\u001a\u0004\u0018\u00010xX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R*\u0010}\u001a\u0004\u0018\u00010~8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0000\u0012\u0004\b\u007f\u0010l\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0006\b\u0082\u0001\u0010\u0083\u0001R\"\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u0006\b\u0088\u0001\u0010\u0089\u0001R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u008a\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010>0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010^R\u0010\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0082.¢\u0006\u0002\n\u0000R \u0010\u008e\u0001\u001a\u00030\u008f\u0001X\u0080.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u0006\b\u0092\u0001\u0010\u0093\u0001R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0080.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0018\u0010 \u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u008d\u000109X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010¡\u0001\u001a\u0005\u0018\u00010¢\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b£\u0001\u0010¤\u0001\"\u0006\b¥\u0001\u0010¦\u0001R \u0010§\u0001\u001a\u00030¨\u0001X\u0080.¢\u0006\u0012\n\u0000\u001a\u0006\b©\u0001\u0010ª\u0001\"\u0006\b«\u0001\u0010¬\u0001R \u0010\u00ad\u0001\u001a\u00030®\u0001X\u0080.¢\u0006\u0012\n\u0000\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0006\b±\u0001\u0010²\u0001R\u0010\u0010³\u0001\u001a\u00030´\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010µ\u0001\u001a\u0005\u0018\u00010¶\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010·\u0001\u001a\u00020r8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0000\u0012\u0005\b¸\u0001\u0010l\u001a\u0005\b¹\u0001\u0010v\"\u0006\bº\u0001\u0010»\u0001R\u0014\u0010¼\u0001\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0005\n\u0003\u0010½\u0001R\u000f\u0010¾\u0001\u001a\u00020>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010¿\u0001\u001a\t\u0012\u0005\u0012\u00030À\u00010KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÁ\u0001\u0010NR\u001d\u0010Â\u0001\u001a\t\u0012\u0005\u0012\u00030Ã\u00010KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÄ\u0001\u0010NR\u0011\u0010Å\u0001\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010Ç\u0001\u001a\u0005\u0018\u00010Æ\u00012\t\u0010q\u001a\u0005\u0018\u00010Æ\u00018\u0000@BX\u0081\u000e¢\u0006\u0011\n\u0000\u0012\u0005\bÈ\u0001\u0010l\u001a\u0006\bÉ\u0001\u0010Ê\u0001R\u0010\u0010Ë\u0001\u001a\u00030Ì\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010Í\u0001\u001a\b\u0012\u0004\u0012\u00020<0KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÎ\u0001\u0010NR\u001c\u0010Ï\u0001\u001a\b\u0012\u0004\u0012\u00020<0KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÐ\u0001\u0010NR\u0011\u0010Ñ\u0001\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010Ò\u0001\u001a\u00020<X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÓ\u0001\u0010Ô\u0001\"\u0006\bÕ\u0001\u0010Ö\u0001R\u000f\u0010×\u0001\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010Ø\u0001\u001a\u00020<X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÙ\u0001\u0010Ô\u0001\"\u0006\bÚ\u0001\u0010Ö\u0001R\u001e\u0010Û\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010@0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÜ\u0001\u0010^R\u001e\u0010Ý\u0001\u001a\t\u0012\u0005\u0012\u00030Þ\u000109X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\bß\u0001\u0010à\u0001R\u001c\u0010á\u0001\u001a\b\u0012\u0004\u0012\u00020<0KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bâ\u0001\u0010NR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R1\u0010ã\u0001\u001a$\u0012\u0005\u0012\u00030å\u0001\u0012\u0005\u0012\u00030æ\u00010ä\u0001j\u0011\u0012\u0005\u0012\u00030å\u0001\u0012\u0005\u0012\u00030æ\u0001`ç\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010é\u0001\u001a\u0005\u0018\u00010è\u00012\t\u0010q\u001a\u0005\u0018\u00010è\u00018\u0000@BX\u0081\u000e¢\u0006\u0011\n\u0000\u0012\u0005\bê\u0001\u0010l\u001a\u0006\bë\u0001\u0010ì\u0001R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010í\u0001\u001a\u00030î\u0001X\u0082.¢\u0006\u0002\n\u0000R\u0018\u0010ï\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010î\u000109X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010ð\u0001\u001a\u00020r8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0006\bò\u0001\u0010ó\u0001\u001a\u0005\bñ\u0001\u0010vR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010ô\u0001\u001a\u00020>X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010õ\u0001\u001a\u00030ö\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010÷\u0001\u001a\u00020>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010ø\u0001\u001a\b\u0012\u0004\u0012\u00020<0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bù\u0001\u0010^R\u001c\u0010ú\u0001\u001a\b\u0012\u0004\u0012\u00020<0KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bû\u0001\u0010NR\u001c\u0010ü\u0001\u001a\b\u0012\u0004\u0012\u00020<0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bý\u0001\u0010^R\u001c\u0010þ\u0001\u001a\b\u0012\u0004\u0012\u00020<0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\bÿ\u0001\u0010^R\u000f\u0010\u0080\u0002\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0081\u0002\u001a\t\u0012\u0005\u0012\u00030\u0082\u00020KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0083\u0002\u0010NR\u001e\u0010\u0084\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0085\u0002\u0010^R\u001c\u0010\u0086\u0002\u001a\b\u0012\u0004\u0012\u00020G0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0002\u0010^R\u001e\u0010\u0088\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0002\u0010^R\u001e\u0010\u008a\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0\\X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008b\u0002\u0010^R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u008c\u0002\u001a\u00020>2\u0006\u0010q\u001a\u00020>8\u0000@BX\u0081\u000e¢\u0006\u0011\n\u0000\u0012\u0005\b\u008d\u0002\u0010l\u001a\u0006\b\u008e\u0002\u0010\u008f\u0002R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R-\u0010\u0090\u0002\u001a\u0005\u0018\u00010\u0091\u00028\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b\u0092\u0002\u0010l\u001a\u0006\b\u0093\u0002\u0010\u0094\u0002\"\u0006\b\u0095\u0002\u0010\u0096\u0002R!\u0010\u0097\u0002\u001a\u00030\u0098\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009b\u0002\u0010ó\u0001\u001a\u0006\b\u0099\u0002\u0010\u009a\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u009c\u0002\u001a\t\u0012\u0005\u0012\u00030\u009d\u00020KX\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0002\u0010N¨\u0006õ\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "sdkUploadMetaDataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "documentService", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentService;", "uploadServiceFactory", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService$Factory;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "rectangleDetector", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "backendDocumentValidationsManager", "Lcom/onfido/android/sdk/capture/validation/BackendDocumentValidationsManager;", "postCaptureDocumentValidationsManager", "Lcom/onfido/android/sdk/capture/validation/PostCaptureDocumentValidationsManager;", "documentProcessingFailureAnalyzer", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;", "documentDelayTransformer", "Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer;", "mediaCallbacksUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/MediaCallbacksUseCase;", "nfcUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/NfcUseCase;", "accessibleDocumentCaptureUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "documentCaptureTracker", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureTracker;", "barcodeValidationSuspender", "Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "retainableValidationsResult", "Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "environmentIntegrityChecker", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "mrzDocumentExtractor", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "documentValidationUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationUseCase;", "(Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;Lcom/onfido/android/sdk/capture/ui/camera/DocumentService;Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService$Factory;Lcom/onfido/api/client/token/TokenProvider;Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;Lcom/onfido/android/sdk/capture/validation/BackendDocumentValidationsManager;Lcom/onfido/android/sdk/capture/validation/PostCaptureDocumentValidationsManager;Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;Lcom/onfido/android/sdk/capture/ui/camera/rx/DocumentCaptureDelayTransformer;Lcom/onfido/android/sdk/capture/internal/usecase/MediaCallbacksUseCase;Lcom/onfido/android/sdk/capture/internal/usecase/NfcUseCase;Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCase;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureTracker;Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;Lcom/onfido/android/sdk/capture/utils/ImageUtils;Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationUseCase;)V", "_cameraState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "_captureButtonVisibility", "", "_docFormatDialogTitleFlow", "", "_liveValidationBubbleVisibility", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "_shouldEnableTorch", "_shouldOpenCaptureScreen", "_shouldShowCaptureErrorDialog", "_showLiveValidation", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "_showVideoRecordingInfo", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$VideoRecordingEvent;", "_startCapture", "_startVideoRecording", "accessibleCaptureRectangleDetection", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "accessibleCaptureResult", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult$DocumentPositionChanged;", "getAccessibleCaptureResult$onfido_capture_sdk_core_release", "applyValidationsFlow", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "getApplyValidationsFlow$onfido_capture_sdk_core_release", "autoCaptureJob", "Lkotlinx/coroutines/Job;", "autocaptureFallbackJob", "binaryUploadWarning", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$BinaryUploadWarningEvent;", "getBinaryUploadWarning$onfido_capture_sdk_core_release", "cameraState", "Lkotlinx/coroutines/flow/StateFlow;", "getCameraState$onfido_capture_sdk_core_release", "()Lkotlinx/coroutines/flow/StateFlow;", "captureButtonVisibility", "getCaptureButtonVisibility$onfido_capture_sdk_core_release", "captureResult", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "getCaptureResult$onfido_capture_sdk_core_release", "capturedFilesDir", "Ljava/io/File;", "getCapturedFilesDir$onfido_capture_sdk_core_release", "()Ljava/io/File;", "setCapturedFilesDir$onfido_capture_sdk_core_release", "(Ljava/io/File;)V", "capturedImage", "getCapturedImage$onfido_capture_sdk_core_release$annotations", "()V", "getCapturedImage$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "setCapturedImage$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;)V", "<set-?>", "", "capturedVideoFilePath", "getCapturedVideoFilePath$onfido_capture_sdk_core_release$annotations", "getCapturedVideoFilePath$onfido_capture_sdk_core_release", "()Ljava/lang/String;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getCountryCode$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "setCountryCode$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "croppedImage", "", "getCroppedImage$onfido_capture_sdk_core_release$annotations", "getCroppedImage$onfido_capture_sdk_core_release", "()[B", "setCroppedImage$onfido_capture_sdk_core_release", "([B)V", "currentCaptureFlowError", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "getCurrentCaptureFlowError$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/upload/ErrorType;", "setCurrentCaptureFlowError$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;)V", "docFormatDialogTitleFlow", "getDocFormatDialogTitleFlow$onfido_capture_sdk_core_release", "docFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "documentCaptureResultConsumer", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureResultConsumer;", "getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureResultConsumer;", "setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureResultConsumer;)V", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "getDocumentData$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "setDocumentData$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;)V", "documentFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "getDocumentFormat$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/DocumentFormat;", "setDocumentFormat$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/DocumentFormat;)V", "documentFrame", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "getDocumentSide$onfido_capture_sdk_core_release", "()Lcom/onfido/api/client/data/DocSide;", "setDocumentSide$onfido_capture_sdk_core_release", "(Lcom/onfido/api/client/data/DocSide;)V", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentType$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/DocumentType;", "setDocumentType$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "documentTypeUIModel", "Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "getDocumentTypeUIModel$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;", "setDocumentTypeUIModel$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/model/DocumentTypeUIModel;)V", "documentValidaMapper", "Lcom/onfido/android/sdk/capture/ui/camera/util/DocumentValidationResultMapper;", "documentValidationTarget", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;", "documentVideoId", "getDocumentVideoId$onfido_capture_sdk_core_release$annotations", "getDocumentVideoId$onfido_capture_sdk_core_release", "setDocumentVideoId$onfido_capture_sdk_core_release", "(Ljava/lang/String;)V", "edgeDetectionFallbackTimerReached", "Ljava/lang/Boolean;", "emittedFramesCount", "errorDescriptorFlow", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "getErrorDescriptorFlow$onfido_capture_sdk_core_release", "errorMessageFlow", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ErrorMessageEvent;", "getErrorMessageFlow$onfido_capture_sdk_core_release", "extraFileInfo", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "extractedMRZDocument", "getExtractedMRZDocument$onfido_capture_sdk_core_release$annotations", "getExtractedMRZDocument$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "firstFrameEmitTime", "", "hidePostCaptureValidationBubbleFlow", "getHidePostCaptureValidationBubbleFlow$onfido_capture_sdk_core_release", "imageProcessingFinished", "getImageProcessingFinished$onfido_capture_sdk_core_release", "imageProcessingJob", "isAutoCaptured", "isAutoCaptured$onfido_capture_sdk_core_release", "()Z", "setAutoCaptured$onfido_capture_sdk_core_release", "(Z)V", "isMRZExtractionTimeExceeded", "isProofOfAddress", "isProofOfAddress$onfido_capture_sdk_core_release", "setProofOfAddress$onfido_capture_sdk_core_release", "liveValidationBubbleVisibility", "getLiveValidationBubbleVisibility$onfido_capture_sdk_core_release", "loadingFlow", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent;", "getLoadingFlow$onfido_capture_sdk_core_release", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "manualFallbackDelayFinished", "getManualFallbackDelayFinished$onfido_capture_sdk_core_release", "mrzExtractionResultMap", "Ljava/util/HashMap;", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "Lkotlin/collections/HashMap;", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "nfcArguments", "getNfcArguments$onfido_capture_sdk_core_release$annotations", "getNfcArguments$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "overlayMetricsStateFlow", "poaCaptureSessionId", "getPoaCaptureSessionId", "poaCaptureSessionId$delegate", "Lkotlin/Lazy;", "processedFramesCount", "processingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "rejectionCount", "shouldEnableTorch", "getShouldEnableTorch$onfido_capture_sdk_core_release", "shouldHideOverlay", "getShouldHideOverlay$onfido_capture_sdk_core_release", "shouldOpenCaptureScreen", "getShouldOpenCaptureScreen$onfido_capture_sdk_core_release", "shouldShowCaptureErrorDialog", "getShouldShowCaptureErrorDialog$onfido_capture_sdk_core_release", "shouldWaitForMRZExtractionResult", "showConfirmationFlow", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ShowConfirmationEvent;", "getShowConfirmationFlow$onfido_capture_sdk_core_release", "showLiveValidation", "getShowLiveValidation$onfido_capture_sdk_core_release", "showVideoRecordingInfo", "getShowVideoRecordingInfo$onfido_capture_sdk_core_release", "startCapture", "getStartCapture$onfido_capture_sdk_core_release", "startVideoRecording", "getStartVideoRecording$onfido_capture_sdk_core_release", "takenPhotoCount", "getTakenPhotoCount$onfido_capture_sdk_core_release$annotations", "getTakenPhotoCount$onfido_capture_sdk_core_release", "()I", "uploadBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult;", "getUploadBinaryResult$onfido_capture_sdk_core_release$annotations", "getUploadBinaryResult$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult;", "setUploadBinaryResult$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult;)V", "uploadService", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService;", "getUploadService", "()Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService;", "uploadService$delegate", "validCaptureFlow", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ValidCaptureEvent;", "getValidCaptureFlow$onfido_capture_sdk_core_release", "analyseProcessingResults", "", "results", "applyPostCaptureValidations", MimeTypes.BASE_TYPE_IMAGE, "outerLimits", "Landroid/graphics/RectF;", "applyPostCaptureValidations$onfido_capture_sdk_core_release", "applyValidationsAfterAnimationDelay", "animationDelayMs", "backSideCaptureNeeded", "callMediaCallback", "checkBinaryUploadedResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult$BinaryUploaded;", "checkDocumentFormat", "checkErrorBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult$Error;", "checkNfcPropertiesFetchedBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult$NfcPropertiesFetched;", "checkUploadBinaryResult", "cropImage", "jpegData", "disposeAutocaptureSubscriptions", "enableAccessibilityCapture", "getCaptureFrameContentDescription", "Lcom/onfido/android/sdk/capture/utils/StringRepresentation;", "forFormat", "getCaptureFrameContentDescription$onfido_capture_sdk_core_release", "getDocumentTypeUIModel", "getOverlayLayout", "getOverlayLayout$onfido_capture_sdk_core_release", "getPictureCapturedAnimationDelay", "getRectangleDetectorObservable", "Lio/reactivex/rxjava3/core/Observable;", "Lio/reactivex/rxjava3/annotations/NonNull;", "isPassport", "getRequiredDocumentValidations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "hasNativeLibrary", "hasNativeLibrary$onfido_capture_sdk_core_release", "hideLoading", "initDocumentTypeUIModel", "initDocumentTypeUIModel$onfido_capture_sdk_core_release", "isBackSideOfRomanianNationalId", "isCameraXEnabled", "isCheckingImageQuality", "isCutoffImprovementsEnabled", "isCutoffImprovementsEnabled$onfido_capture_sdk_core_release", "isDarkModeEnabled", "isDarkModeEnabled$onfido_capture_sdk_core_release", "isDocumentFrameValidForAutoCapture", "isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release", "isDocumentUploaded", "isFinalStepForDocument", "isFourByThreeEnabled", "isFourByThreeEnabled$onfido_capture_sdk_core_release", "isMlModelAutoCaptureEnabled", "isMrzDetectionEnabled", "observeAutoCapture", "onBinaryUploaded", "onCameraStarted", "onCameraStarted$onfido_capture_sdk_core_release", "onCaptureButtonClicked", "onCaptureButtonClicked$onfido_capture_sdk_core_release", "onCaptureCompleted", OnfidoLauncher.KEY_RESULT, "onCaptureCompleted$onfido_capture_sdk_core_release", "onCaptureConfirmed", "onCaptureConfirmed$onfido_capture_sdk_core_release", "onCaptureDiscarded", "onCaptureDiscarded$onfido_capture_sdk_core_release", "onCaptureErrorConfirmed", "onCaptureErrorConfirmed$onfido_capture_sdk_core_release", "onCaptureForProofOfAddressDone", "onCaptureScreenOpened", "onCaptureScreenOpened$onfido_capture_sdk_core_release", "onCleared", "onDocumentFormatSelected", "onDocumentFormatSelected$onfido_capture_sdk_core_release", "onDocumentVideoUploaded", "onErrorVideoRecording", "onErrorVideoRecording$onfido_capture_sdk_core_release", "onErrorVideoTaking", "onFoldedFormat", "onGeneralUploadError", "onInvalidCertificateDetected", "message", "onManualFallbackDelayFinished", "onManualFallbackDelayFinished$onfido_capture_sdk_core_release", "onNextFrame", "frame", "onNextFrame$onfido_capture_sdk_core_release", "onOverlayMetrics", "onOverlayMetrics$onfido_capture_sdk_core_release", "onPictureCaptureFailed", "onPictureCaptureFailed$onfido_capture_sdk_core_release", "onPictureCaptured", "onPictureCaptured$onfido_capture_sdk_core_release", "onPostCaptureValidationsFinished", "onTokenExpired", "onUploadError", "errorType", "onUploadFailure", "onUploadFailureWithGeoblocking", "onUploadValidationError", "onVideoCaptured", "filePath", "onVideoCaptured$onfido_capture_sdk_core_release", "onVideoRecordingStopped", "onVideoRecordingStopped$onfido_capture_sdk_core_release", "onViewResumed", "onViewResumed$onfido_capture_sdk_core_release", "prepareCaptureStart", "isFirstStart", "orientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "prepareCaptureStart$onfido_capture_sdk_core_release", "processAccessibleDocumentCaptureResult", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "processFrameForMRZ", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reset", "reset$onfido_capture_sdk_core_release", "sdkUploadMetadata", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "captureStepDataBundle", "setCaptureData", "captureDataBundle", "setCaptureData$onfido_capture_sdk_core_release", "setMRZResult", "Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "shouldAutoCaptureDocument", "shouldAutoCaptureDocument$onfido_capture_sdk_core_release", "shouldAutocapture", "shouldEnableAccessibilityCapture", "shouldEnableAccessibilityCapture$onfido_capture_sdk_core_release", "shouldForceRetry", "shouldHideManualDocumentCaptureButton", "shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release", "shouldRecordDocumentVideo", "shouldRecordDocumentVideo$onfido_capture_sdk_core_release", "shouldShowFrenchDLOverlay", "isFrontSide", "isOverlayGone", "shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release", "shouldShowGermanDLOverlay", "shouldShowGermanDLOverlay$onfido_capture_sdk_core_release", "shouldShowInitialOverlay", "shouldShowInitialOverlay$onfido_capture_sdk_core_release", "shouldShowItalianIDOverlay", "shouldShowItalianIDOverlay$onfido_capture_sdk_core_release", "shouldShowOverlay", "shouldShowSouthAfricanIDOverlay", "shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release", "shouldUploadDocument", "showErrorMessage", "title", "showLoading", ModeProp.name, "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showWarningBinaryResult", "documentId", "warning", "nfcSupported", "warningsBundle", "Lcom/onfido/api/client/data/DocumentValidationWarningsBundle;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "startDocumentVideoRecordingTimer", "hasValidRecording", "Lkotlin/Function0;", "startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release", "startManualFallbackTimer", "startOverlayDisplayTimer", "startOverlayDisplayTimer$onfido_capture_sdk_core_release", "stopDocumentRecording", "stopDocumentVideoRecordingAndCameraFeed", "trackAutocaptureShutterButtonClick", "trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release", "trackCapture", "trackCapture$onfido_capture_sdk_core_release", "trackCaptureBackButtonClicked", "trackCaptureBackButtonClicked$onfido_capture_sdk_core_release", "trackCaptureError", "trackCaptureError$onfido_capture_sdk_core_release", "trackDocumentCaptureFlowCompleted", "trackDocumentConfirmationStep", "trackDocumentConfirmationStep$onfido_capture_sdk_core_release", "trackEndPerformanceEvent", "trackEndPerformanceEvent$onfido_capture_sdk_core_release", "trackStartPerformanceEvent", "trackStartPerformanceEvent$onfido_capture_sdk_core_release", "trackUploadStarted", "trackUploadStarted$onfido_capture_sdk_core_release", "trackVideoCaptureTimeout", "trackVideoTimeoutRetryButtonClicked", "trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release", "trackWaitingScreenCompleted", "uploadDocument", "uploadDocumentMedia", "validations", "uploadDocumentMedia$onfido_capture_sdk_core_release", "uploadImageForValidation", "isFolded", "type", MediaCallbackResultReceiver.KEY_COUNTRY, "BinaryUploadWarningEvent", "Companion", "ErrorMessageEvent", "LoadingEvent", "ShowConfirmationEvent", "ValidCaptureEvent", "VideoRecordingEvent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureViewModel extends ViewModel implements CaptureUploadServiceListener {
    private static final long CONFIRMATION_VIEW_ANIM_DELAY = 1200;
    private static final String DOC_POA_PHOTO_PREFIX = "ONFIDO_POA_IMG_";
    private static final long MANUAL_FALLBACK_DELAY_MS = 7000;
    public static final String NFC_LOGGER = "NFC Logger";
    private static final long OVERLAY_DELAY_MS = 4000;
    private final MutableStateFlow<CameraState> _cameraState;
    private final MutableStateFlow<Boolean> _captureButtonVisibility;
    private final MutableStateFlow<Integer> _docFormatDialogTitleFlow;
    private final MutableStateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> _liveValidationBubbleVisibility;
    private final MutableStateFlow<Boolean> _shouldEnableTorch;
    private final MutableStateFlow<Boolean> _shouldOpenCaptureScreen;
    private final MutableStateFlow<Boolean> _shouldShowCaptureErrorDialog;
    private final MutableStateFlow<OnfidoCaptureValidationBubble.Content> _showLiveValidation;
    private final MutableStateFlow<VideoRecordingEvent> _showVideoRecordingInfo;
    private final MutableStateFlow<Boolean> _startCapture;
    private final MutableStateFlow<Boolean> _startVideoRecording;
    private final MutableSharedFlow<RectDetectionResult> accessibleCaptureRectangleDetection;
    private final MutableSharedFlow<AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged> accessibleCaptureResult;
    private final AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase;
    private final AnnouncementService announcementService;
    private final MutableSharedFlow<OnfidoImage> applyValidationsFlow;
    private Job autoCaptureJob;
    private Job autocaptureFallbackJob;
    private final BackendDocumentValidationsManager backendDocumentValidationsManager;
    private final BarcodeValidationSuspender barcodeValidationSuspender;
    private final MutableSharedFlow<BinaryUploadWarningEvent> binaryUploadWarning;
    private final StateFlow<CameraState> cameraState;
    private final StateFlow<Boolean> captureButtonVisibility;
    private final MutableSharedFlow<DocumentCaptureScreen.DocumentCaptureResult> captureResult;
    public File capturedFilesDir;
    private OnfidoImage capturedImage;
    private String capturedVideoFilePath;
    private CountryCode countryCode;
    private byte[] croppedImage;
    private ErrorType currentCaptureFlowError;
    private final DispatchersProvider dispatchersProvider;
    private final StateFlow<Integer> docFormatDialogTitleFlow;
    private DocumentDetectionFrame docFrame;
    public DocumentCaptureResultConsumer documentCaptureResultConsumer;
    private final DocumentCaptureTracker documentCaptureTracker;
    private final DocumentConfigurationManager documentConfigurationManager;
    public CaptureStepDataBundle documentData;
    private final DocumentCaptureDelayTransformer documentDelayTransformer;
    private DocumentFormat documentFormat;
    private final MutableStateFlow<DocumentDetectionFrame> documentFrame;
    private final DocumentProcessingResultsFailureAnalyzer documentProcessingFailureAnalyzer;
    private final DocumentService documentService;
    private DocSide documentSide;
    public DocumentType documentType;
    public DocumentTypeUIModel documentTypeUIModel;
    private final DocumentValidationResultMapper documentValidaMapper;
    private DocumentValidationTargets documentValidationTarget;
    private final DocumentValidationUseCase documentValidationUseCase;
    private String documentVideoId;
    private Boolean edgeDetectionFallbackTimerReached;
    private int emittedFramesCount;
    private final EnvironmentIntegrityChecker environmentIntegrityChecker;
    private final MutableSharedFlow<ErrorDescriptor> errorDescriptorFlow;
    private final MutableSharedFlow<ErrorMessageEvent> errorMessageFlow;
    private String extraFileInfo;
    private MRZDocument extractedMRZDocument;
    private long firstFrameEmitTime;
    private final MutableSharedFlow<Boolean> hidePostCaptureValidationBubbleFlow;
    private final MutableSharedFlow<Boolean> imageProcessingFinished;
    private Job imageProcessingJob;
    private final ImageUtils imageUtils;
    private boolean isAutoCaptured;
    private boolean isMRZExtractionTimeExceeded;
    private boolean isProofOfAddress;
    private final StateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> liveValidationBubbleVisibility;
    private final MutableStateFlow<LoadingEvent> loadingFlow;
    private final MutableSharedFlow<Boolean> manualFallbackDelayFinished;
    private final MediaCallbacksUseCase mediaCallbacksUseCase;
    private final MRZDocumentExtractor mrzDocumentExtractor;
    private final HashMap<MRZDataType, MRZData> mrzExtractionResultMap;
    private final NativeDetector nativeDetector;
    private NfcArguments nfcArguments;
    private final NfcUseCase nfcUseCase;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private OverlayMetrics overlayMetrics;
    private final MutableStateFlow<OverlayMetrics> overlayMetricsStateFlow;

    /* renamed from: poaCaptureSessionId$delegate, reason: from kotlin metadata */
    private final Lazy poaCaptureSessionId;
    private final PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager;
    private int processedFramesCount;
    private DocumentProcessingResults processingResults;
    private final RectangleDetector rectangleDetector;
    private int rejectionCount;
    private final RetainableValidationsResult retainableValidationsResult;
    private final SdkUploadMetadataHelper sdkUploadMetaDataHelper;
    private final StateFlow<Boolean> shouldEnableTorch;
    private final MutableSharedFlow<Boolean> shouldHideOverlay;
    private final StateFlow<Boolean> shouldOpenCaptureScreen;
    private final StateFlow<Boolean> shouldShowCaptureErrorDialog;
    private boolean shouldWaitForMRZExtractionResult;
    private final MutableSharedFlow<ShowConfirmationEvent> showConfirmationFlow;
    private final StateFlow<OnfidoCaptureValidationBubble.Content> showLiveValidation;
    private final StateFlow<VideoRecordingEvent> showVideoRecordingInfo;
    private final StateFlow<Boolean> startCapture;
    private final StateFlow<Boolean> startVideoRecording;
    private final SharedPreferencesDataSource storage;
    private int takenPhotoCount;
    private final TimeProvider timeProvider;
    private TokenProvider tokenProvider;
    private UploadBinaryResult uploadBinaryResult;

    /* renamed from: uploadService$delegate, reason: from kotlin metadata */
    private final Lazy uploadService;
    private final CaptureUploadService.Factory uploadServiceFactory;
    private final MutableSharedFlow<ValidCaptureEvent> validCaptureFlow;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$BinaryUploadWarningEvent;", "", "errorDescription", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "(Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;)V", "getErrorDescription", "()Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "getResult", "()Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class BinaryUploadWarningEvent {
        private final ErrorDescriptor errorDescription;
        private final DocumentCaptureScreen.DocumentCaptureResult result;

        public BinaryUploadWarningEvent(ErrorDescriptor errorDescription, DocumentCaptureScreen.DocumentCaptureResult result) {
            Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
            Intrinsics.checkNotNullParameter(result, "result");
            this.errorDescription = errorDescription;
            this.result = result;
        }

        public static /* synthetic */ BinaryUploadWarningEvent copy$default(BinaryUploadWarningEvent binaryUploadWarningEvent, ErrorDescriptor errorDescriptor, DocumentCaptureScreen.DocumentCaptureResult documentCaptureResult, int i, Object obj) {
            if ((i & 1) != 0) {
                errorDescriptor = binaryUploadWarningEvent.errorDescription;
            }
            if ((i & 2) != 0) {
                documentCaptureResult = binaryUploadWarningEvent.result;
            }
            return binaryUploadWarningEvent.copy(errorDescriptor, documentCaptureResult);
        }

        /* renamed from: component1, reason: from getter */
        public final ErrorDescriptor getErrorDescription() {
            return this.errorDescription;
        }

        /* renamed from: component2, reason: from getter */
        public final DocumentCaptureScreen.DocumentCaptureResult getResult() {
            return this.result;
        }

        public final BinaryUploadWarningEvent copy(ErrorDescriptor errorDescription, DocumentCaptureScreen.DocumentCaptureResult result) {
            Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
            Intrinsics.checkNotNullParameter(result, "result");
            return new BinaryUploadWarningEvent(errorDescription, result);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BinaryUploadWarningEvent)) {
                return false;
            }
            BinaryUploadWarningEvent binaryUploadWarningEvent = (BinaryUploadWarningEvent) other;
            return Intrinsics.areEqual(this.errorDescription, binaryUploadWarningEvent.errorDescription) && Intrinsics.areEqual(this.result, binaryUploadWarningEvent.result);
        }

        public final ErrorDescriptor getErrorDescription() {
            return this.errorDescription;
        }

        public final DocumentCaptureScreen.DocumentCaptureResult getResult() {
            return this.result;
        }

        public int hashCode() {
            return (this.errorDescription.hashCode() * 31) + this.result.hashCode();
        }

        public String toString() {
            return "BinaryUploadWarningEvent(errorDescription=" + this.errorDescription + ", result=" + this.result + ')';
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ErrorMessageEvent;", "", "title", "", "message", "(II)V", "getMessage", "()I", "getTitle", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ErrorMessageEvent {
        private final int message;
        private final int title;

        public ErrorMessageEvent(int i, int i2) {
            this.title = i;
            this.message = i2;
        }

        public static /* synthetic */ ErrorMessageEvent copy$default(ErrorMessageEvent errorMessageEvent, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = errorMessageEvent.title;
            }
            if ((i3 & 2) != 0) {
                i2 = errorMessageEvent.message;
            }
            return errorMessageEvent.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getTitle() {
            return this.title;
        }

        /* renamed from: component2, reason: from getter */
        public final int getMessage() {
            return this.message;
        }

        public final ErrorMessageEvent copy(int title, int message) {
            return new ErrorMessageEvent(title, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ErrorMessageEvent)) {
                return false;
            }
            ErrorMessageEvent errorMessageEvent = (ErrorMessageEvent) other;
            return this.title == errorMessageEvent.title && this.message == errorMessageEvent.message;
        }

        public final int getMessage() {
            return this.message;
        }

        public final int getTitle() {
            return this.title;
        }

        public int hashCode() {
            return (Integer.hashCode(this.title) * 31) + Integer.hashCode(this.message);
        }

        public String toString() {
            return "ErrorMessageEvent(title=" + this.title + ", message=" + this.message + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent;", "", "()V", "Hide", "Show", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent$Hide;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent$Show;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class LoadingEvent {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent$Hide;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Hide extends LoadingEvent {
            public static final Hide INSTANCE = new Hide();

            private Hide() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent$Show;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$LoadingEvent;", ModeProp.name, "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "(Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;)V", "getMode", "()Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Show extends LoadingEvent {
            private final LoadingFragment.Companion.DialogMode mode;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Show(LoadingFragment.Companion.DialogMode mode) {
                super(null);
                Intrinsics.checkNotNullParameter(mode, "mode");
                this.mode = mode;
            }

            public static /* synthetic */ Show copy$default(Show show, LoadingFragment.Companion.DialogMode dialogMode, int i, Object obj) {
                if ((i & 1) != 0) {
                    dialogMode = show.mode;
                }
                return show.copy(dialogMode);
            }

            /* renamed from: component1, reason: from getter */
            public final LoadingFragment.Companion.DialogMode getMode() {
                return this.mode;
            }

            public final Show copy(LoadingFragment.Companion.DialogMode mode) {
                Intrinsics.checkNotNullParameter(mode, "mode");
                return new Show(mode);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Show) && Intrinsics.areEqual(this.mode, ((Show) other).mode);
            }

            public final LoadingFragment.Companion.DialogMode getMode() {
                return this.mode;
            }

            public int hashCode() {
                return this.mode.hashCode();
            }

            public String toString() {
                return "Show(mode=" + this.mode + ')';
            }
        }

        private LoadingEvent() {
        }

        public /* synthetic */ LoadingEvent(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ShowConfirmationEvent;", "", "shouldShowForceRetry", "", "withWarning", "(ZZ)V", "getShouldShowForceRetry", "()Z", "getWithWarning", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ShowConfirmationEvent {
        private final boolean shouldShowForceRetry;
        private final boolean withWarning;

        /* JADX WARN: Illegal instructions before constructor call */
        public ShowConfirmationEvent() {
            boolean z = false;
            this(z, z, 3, null);
        }

        public static /* synthetic */ ShowConfirmationEvent copy$default(ShowConfirmationEvent showConfirmationEvent, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = showConfirmationEvent.shouldShowForceRetry;
            }
            if ((i & 2) != 0) {
                z2 = showConfirmationEvent.withWarning;
            }
            return showConfirmationEvent.copy(z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getShouldShowForceRetry() {
            return this.shouldShowForceRetry;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getWithWarning() {
            return this.withWarning;
        }

        public final ShowConfirmationEvent copy(boolean shouldShowForceRetry, boolean withWarning) {
            return new ShowConfirmationEvent(shouldShowForceRetry, withWarning);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShowConfirmationEvent)) {
                return false;
            }
            ShowConfirmationEvent showConfirmationEvent = (ShowConfirmationEvent) other;
            return this.shouldShowForceRetry == showConfirmationEvent.shouldShowForceRetry && this.withWarning == showConfirmationEvent.withWarning;
        }

        public final boolean getShouldShowForceRetry() {
            return this.shouldShowForceRetry;
        }

        public final boolean getWithWarning() {
            return this.withWarning;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.shouldShowForceRetry) * 31) + Boolean.hashCode(this.withWarning);
        }

        public String toString() {
            return "ShowConfirmationEvent(shouldShowForceRetry=" + this.shouldShowForceRetry + ", withWarning=" + this.withWarning + ')';
        }

        public ShowConfirmationEvent(boolean z, boolean z2) {
            this.shouldShowForceRetry = z;
            this.withWarning = z2;
        }

        public /* synthetic */ ShowConfirmationEvent(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$ValidCaptureEvent;", "", "isValid", "", "(Z)V", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ValidCaptureEvent {
        private final boolean isValid;

        public ValidCaptureEvent(boolean z) {
            this.isValid = z;
        }

        public static /* synthetic */ ValidCaptureEvent copy$default(ValidCaptureEvent validCaptureEvent, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = validCaptureEvent.isValid;
            }
            return validCaptureEvent.copy(z);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsValid() {
            return this.isValid;
        }

        public final ValidCaptureEvent copy(boolean isValid) {
            return new ValidCaptureEvent(isValid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ValidCaptureEvent) && this.isValid == ((ValidCaptureEvent) other).isValid;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isValid);
        }

        public final boolean isValid() {
            return this.isValid;
        }

        public String toString() {
            return "ValidCaptureEvent(isValid=" + this.isValid + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$VideoRecordingEvent;", "", "shouldShow", "", "withMessage", "", "(ZI)V", "getShouldShow", "()Z", "getWithMessage", "()I", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class VideoRecordingEvent {
        private final boolean shouldShow;
        private final int withMessage;

        public VideoRecordingEvent(boolean z, int i) {
            this.shouldShow = z;
            this.withMessage = i;
        }

        public static /* synthetic */ VideoRecordingEvent copy$default(VideoRecordingEvent videoRecordingEvent, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = videoRecordingEvent.shouldShow;
            }
            if ((i2 & 2) != 0) {
                i = videoRecordingEvent.withMessage;
            }
            return videoRecordingEvent.copy(z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getShouldShow() {
            return this.shouldShow;
        }

        /* renamed from: component2, reason: from getter */
        public final int getWithMessage() {
            return this.withMessage;
        }

        public final VideoRecordingEvent copy(boolean shouldShow, int withMessage) {
            return new VideoRecordingEvent(shouldShow, withMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoRecordingEvent)) {
                return false;
            }
            VideoRecordingEvent videoRecordingEvent = (VideoRecordingEvent) other;
            return this.shouldShow == videoRecordingEvent.shouldShow && this.withMessage == videoRecordingEvent.withMessage;
        }

        public final boolean getShouldShow() {
            return this.shouldShow;
        }

        public final int getWithMessage() {
            return this.withMessage;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.shouldShow) * 31) + Integer.hashCode(this.withMessage);
        }

        public String toString() {
            return "VideoRecordingEvent(shouldShow=" + this.shouldShow + ", withMessage=" + this.withMessage + ')';
        }

        public /* synthetic */ VideoRecordingEvent(boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i2 & 2) != 0 ? 0 : i);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$applyValidationsAfterAnimationDelay$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {704}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$applyValidationsAfterAnimationDelay$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $animationDelayMs;
        int label;
        final /* synthetic */ DocumentCaptureViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$animationDelayMs = j;
            this.this$0 = documentCaptureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$animationDelayMs, this.this$0, continuation);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = this.$animationDelayMs;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0._showVideoRecordingInfo.setValue(new VideoRecordingEvent(false, 0 == true ? 1 : 0, 2, null));
            OnfidoImage capturedImage = this.this$0.getCapturedImage();
            if (capturedImage == null) {
                return Unit.INSTANCE;
            }
            if (this.this$0.getIsProofOfAddress()) {
                this.this$0.onCaptureForProofOfAddressDone(capturedImage);
            } else {
                this.this$0.getApplyValidationsFlow$onfido_capture_sdk_core_release().tryEmit(capturedImage);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {1422}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1, reason: invalid class name and case insensitive filesystem */
    static final class C06451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Observable<RectDetectionResult> $rectDetectionObservable;
        int label;
        final /* synthetic */ DocumentCaptureViewModel this$0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {1420}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01521 extends SuspendLambda implements Function2<RectDetectionResult, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DocumentCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01521(DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super C01521> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01521 c01521 = new C01521(this.this$0, continuation);
                c01521.L$0 = obj;
                return c01521;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(RectDetectionResult rectDetectionResult, Continuation<? super Unit> continuation) {
                return ((C01521) create(rectDetectionResult, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    RectDetectionResult rectDetectionResult = (RectDetectionResult) this.L$0;
                    MutableSharedFlow<RectDetectionResult> accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release = this.this$0.getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release();
                    Intrinsics.checkNotNull(rectDetectionResult);
                    this.label = 1;
                    if (accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release.emit(rectDetectionResult, this) == coroutine_suspended) {
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
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectDetectionResult;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1$2", f = "DocumentCaptureViewModel.kt", i = {}, l = {1421}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super RectDetectionResult>, Throwable, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super AnonymousClass2> continuation) {
                super(3, continuation);
                this.this$0 = documentCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<RectDetectionResult> accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release = this.this$0.getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release();
                    RectDetectionResult.NoRectDetected noRectDetected = RectDetectionResult.NoRectDetected.INSTANCE;
                    this.label = 1;
                    if (accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release.emit(noRectDetected, this) == coroutine_suspended) {
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

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super RectDetectionResult> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                return new AnonymousClass2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06451(Observable<RectDetectionResult> observable, DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super C06451> continuation) {
            super(2, continuation);
            this.$rectDetectionObservable = observable;
            this.this$0 = documentCaptureViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06451(this.$rectDetectionObservable, this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowOnCompletion = FlowKt.onCompletion(FlowKt.onEach(RxConvertKt.asFlow(this.$rectDetectionObservable), new C01521(this.this$0, null)), new AnonymousClass2(this.this$0, null));
                this.label = 1;
                if (FlowKt.collect(flowOnCompletion, this) == coroutine_suspended) {
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
            return ((C06451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2", f = "DocumentCaptureViewModel.kt", i = {}, l = {1431}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Observable<RectDetectionResult> $rectDetectionObservable;
        int label;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<AccessibleDocumentCaptureUseCaseResult, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DocumentCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AccessibleDocumentCaptureUseCaseResult accessibleDocumentCaptureUseCaseResult, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(accessibleDocumentCaptureUseCaseResult, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                AccessibleDocumentCaptureUseCaseResult accessibleDocumentCaptureUseCaseResult = (AccessibleDocumentCaptureUseCaseResult) this.L$0;
                DocumentCaptureViewModel documentCaptureViewModel = this.this$0;
                Intrinsics.checkNotNull(accessibleDocumentCaptureUseCaseResult);
                documentCaptureViewModel.processAccessibleDocumentCaptureResult(accessibleDocumentCaptureUseCaseResult);
                return Unit.INSTANCE;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/internal/usecase/AccessibleDocumentCaptureUseCaseResult;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2$2", f = "DocumentCaptureViewModel.kt", i = {}, l = {1430}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$enableAccessibilityCapture$2$2, reason: invalid class name and collision with other inner class name */
        static final class C01532 extends SuspendLambda implements Function3<FlowCollector<? super AccessibleDocumentCaptureUseCaseResult>, Throwable, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01532(DocumentCaptureViewModel documentCaptureViewModel, Continuation<? super C01532> continuation) {
                super(3, continuation);
                this.this$0 = documentCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<RectDetectionResult> accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release = this.this$0.getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release();
                    RectDetectionResult.NoRectDetected noRectDetected = RectDetectionResult.NoRectDetected.INSTANCE;
                    this.label = 1;
                    if (accessibleCaptureRectangleDetection$onfido_capture_sdk_core_release.emit(noRectDetected, this) == coroutine_suspended) {
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

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super AccessibleDocumentCaptureUseCaseResult> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                return new C01532(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Observable<RectDetectionResult> observable, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$rectDetectionObservable = observable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureViewModel.this.new AnonymousClass2(this.$rectDetectionObservable, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowOnCompletion = FlowKt.onCompletion(FlowKt.onEach(RxConvertKt.asFlow(DocumentCaptureViewModel.this.accessibleDocumentCaptureUseCase.observeAccessibilityCapture$onfido_capture_sdk_core_release(this.$rectDetectionObservable)), new AnonymousClass1(DocumentCaptureViewModel.this, null)), new C01532(DocumentCaptureViewModel.this, null));
                this.label = 1;
                if (FlowKt.collect(flowOnCompletion, this) == coroutine_suspended) {
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

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;", OnfidoLauncher.KEY_RESULT}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$observeAutoCapture$2", f = "DocumentCaptureViewModel.kt", i = {0}, l = {569}, m = "invokeSuspend", n = {OnfidoLauncher.KEY_RESULT}, s = {"L$0"})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$observeAutoCapture$2, reason: invalid class name and case insensitive filesystem */
    static final class C06482 extends SuspendLambda implements Function3<FlowCollector<? super DocumentValidationResult>, DocumentValidationResult, Continuation<? super Boolean>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C06482(Continuation<? super C06482> continuation) {
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
            C06482 c06482 = new C06482(continuation);
            c06482.L$0 = flowCollector;
            c06482.L$1 = documentValidationResult;
            return c06482.invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$observeAutoCapture$3", f = "DocumentCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$observeAutoCapture$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<DocumentValidationResult, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = DocumentCaptureViewModel.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DocumentValidationResult documentValidationResult, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(documentValidationResult, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DocumentValidationResult documentValidationResult = (DocumentValidationResult) this.L$0;
            if (Intrinsics.areEqual(documentValidationResult, DocumentValidationResult.Success.INSTANCE)) {
                DocumentCaptureViewModel.this.setAutoCaptured$onfido_capture_sdk_core_release(true);
                DocumentCaptureViewModel.this._liveValidationBubbleVisibility.tryEmit(OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE);
                boolean z = !DocumentCaptureViewModel.this.onfidoRemoteConfig.isMultiImageCaptureEnabled();
                Job job = DocumentCaptureViewModel.this.autoCaptureJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                DocumentCaptureViewModel.this._startCapture.tryEmit(Boxing.boxBoolean(z));
            } else {
                OnfidoCaptureValidationBubble.Content contentInvoke = DocumentCaptureViewModel.this.documentValidaMapper.invoke(documentValidationResult);
                if (contentInvoke != null) {
                    Boxing.boxBoolean(DocumentCaptureViewModel.this._showLiveValidation.tryEmit(contentInvoke));
                }
                DocumentCaptureViewModel.this._liveValidationBubbleVisibility.tryEmit(new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(true)));
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$onCaptureForProofOfAddressDone$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {722}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$onCaptureForProofOfAddressDone$1, reason: invalid class name and case insensitive filesystem */
    static final class C06491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OnfidoImage $capturedImage;
        final /* synthetic */ DocumentDetectionFrame $documentDetectionFrame;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06491(OnfidoImage onfidoImage, DocumentDetectionFrame documentDetectionFrame, Continuation<? super C06491> continuation) {
            super(2, continuation);
            this.$capturedImage = onfidoImage;
            this.$documentDetectionFrame = documentDetectionFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureViewModel.this.new C06491(this.$capturedImage, this.$documentDetectionFrame, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ImageUtils imageUtils = DocumentCaptureViewModel.this.imageUtils;
                byte[] data = this.$capturedImage.getData();
                DocumentDetectionFrame documentDetectionFrame = this.$documentDetectionFrame;
                String str = DocumentCaptureViewModel.DOC_POA_PHOTO_PREFIX + DocumentCaptureViewModel.this.getPoaCaptureSessionId() + ".jpg";
                File capturedFilesDir$onfido_capture_sdk_core_release = DocumentCaptureViewModel.this.getCapturedFilesDir$onfido_capture_sdk_core_release();
                this.label = 1;
                obj = imageUtils.getPoaFileNameAfterCropping$onfido_capture_sdk_core_release(data, documentDetectionFrame, str, capturedFilesDir$onfido_capture_sdk_core_release, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            String str2 = (String) obj;
            if (str2 != null) {
                DocumentCaptureViewModel.this.getCaptureResult$onfido_capture_sdk_core_release().tryEmit(new DocumentCaptureScreen.DocumentCaptureResult.POACompleted(str2));
            } else {
                Timber.INSTANCE.e("POA_Debug: failed cropping / saving POA file", new Object[0]);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel", f = "DocumentCaptureViewModel.kt", i = {0, 0}, l = {PhotoshopDirectory.TAG_PIXEL_ASPECT_RATIO}, m = "processFrameForMRZ", n = {"this", "frameProcessStartTime"}, s = {"L$0", "J$0"})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$processFrameForMRZ$1, reason: invalid class name and case insensitive filesystem */
    static final class C06501 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C06501(Continuation<? super C06501> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureViewModel.this.processFrameForMRZ(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startManualFallbackTimer$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {435}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$startManualFallbackTimer$1, reason: invalid class name and case insensitive filesystem */
    static final class C06511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06511(Continuation<? super C06511> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureViewModel.this.new C06511(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (DelayKt.delay(7000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                DocumentCaptureViewModel.this.getManualFallbackDelayFinished$onfido_capture_sdk_core_release().tryEmit(Boxing.boxBoolean(true));
                DocumentCaptureViewModel.this.edgeDetectionFallbackTimerReached = Boxing.boxBoolean(true);
            } catch (CancellationException e) {
                Timber.INSTANCE.i(e, "Error on autocapture fallback: " + e.getMessage(), new Object[0]);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$uploadDocument$1", f = "DocumentCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$uploadDocument$1, reason: invalid class name and case insensitive filesystem */
    static final class C06521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ byte[] $jpegData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06521(byte[] bArr, Continuation<? super C06521> continuation) {
            super(2, continuation);
            this.$jpegData = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureViewModel.this.new C06521(this.$jpegData, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            byte[] bArrCropImage = DocumentCaptureViewModel.this.onfidoRemoteConfig.isDocumentCropDisabled() ? this.$jpegData : DocumentCaptureViewModel.this.cropImage(this.$jpegData);
            if (!DocumentCaptureViewModel.this.onfidoRemoteConfig.isMediaCallbackCroppingDisabled()) {
                DocumentCaptureViewModel.this.setCroppedImage$onfido_capture_sdk_core_release(bArrCropImage);
            }
            DocumentCaptureViewModel.this.uploadImageForValidation(bArrCropImage);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Inject
    public DocumentCaptureViewModel(DocumentConfigurationManager documentConfigurationManager, NativeDetector nativeDetector, AnnouncementService announcementService, OnfidoRemoteConfig onfidoRemoteConfig, SdkUploadMetadataHelper sdkUploadMetaDataHelper, DocumentService documentService, CaptureUploadService.Factory uploadServiceFactory, TokenProvider tokenProvider, RectangleDetector rectangleDetector, SharedPreferencesDataSource storage, BackendDocumentValidationsManager backendDocumentValidationsManager, PostCaptureDocumentValidationsManager postCaptureDocumentValidationsManager, DocumentProcessingResultsFailureAnalyzer documentProcessingFailureAnalyzer, DocumentCaptureDelayTransformer documentDelayTransformer, MediaCallbacksUseCase mediaCallbacksUseCase, NfcUseCase nfcUseCase, AccessibleDocumentCaptureUseCase accessibleDocumentCaptureUseCase, TimeProvider timeProvider, DocumentCaptureTracker documentCaptureTracker, BarcodeValidationSuspender barcodeValidationSuspender, RetainableValidationsResult retainableValidationsResult, DispatchersProvider dispatchersProvider, EnvironmentIntegrityChecker environmentIntegrityChecker, ImageUtils imageUtils, MRZDocumentExtractor mrzDocumentExtractor, DocumentValidationUseCase documentValidationUseCase) {
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(sdkUploadMetaDataHelper, "sdkUploadMetaDataHelper");
        Intrinsics.checkNotNullParameter(documentService, "documentService");
        Intrinsics.checkNotNullParameter(uploadServiceFactory, "uploadServiceFactory");
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(rectangleDetector, "rectangleDetector");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(backendDocumentValidationsManager, "backendDocumentValidationsManager");
        Intrinsics.checkNotNullParameter(postCaptureDocumentValidationsManager, "postCaptureDocumentValidationsManager");
        Intrinsics.checkNotNullParameter(documentProcessingFailureAnalyzer, "documentProcessingFailureAnalyzer");
        Intrinsics.checkNotNullParameter(documentDelayTransformer, "documentDelayTransformer");
        Intrinsics.checkNotNullParameter(mediaCallbacksUseCase, "mediaCallbacksUseCase");
        Intrinsics.checkNotNullParameter(nfcUseCase, "nfcUseCase");
        Intrinsics.checkNotNullParameter(accessibleDocumentCaptureUseCase, "accessibleDocumentCaptureUseCase");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(documentCaptureTracker, "documentCaptureTracker");
        Intrinsics.checkNotNullParameter(barcodeValidationSuspender, "barcodeValidationSuspender");
        Intrinsics.checkNotNullParameter(retainableValidationsResult, "retainableValidationsResult");
        Intrinsics.checkNotNullParameter(dispatchersProvider, "dispatchersProvider");
        Intrinsics.checkNotNullParameter(environmentIntegrityChecker, "environmentIntegrityChecker");
        Intrinsics.checkNotNullParameter(imageUtils, "imageUtils");
        Intrinsics.checkNotNullParameter(mrzDocumentExtractor, "mrzDocumentExtractor");
        Intrinsics.checkNotNullParameter(documentValidationUseCase, "documentValidationUseCase");
        this.documentConfigurationManager = documentConfigurationManager;
        this.nativeDetector = nativeDetector;
        this.announcementService = announcementService;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.sdkUploadMetaDataHelper = sdkUploadMetaDataHelper;
        this.documentService = documentService;
        this.uploadServiceFactory = uploadServiceFactory;
        this.tokenProvider = tokenProvider;
        this.rectangleDetector = rectangleDetector;
        this.storage = storage;
        this.backendDocumentValidationsManager = backendDocumentValidationsManager;
        this.postCaptureDocumentValidationsManager = postCaptureDocumentValidationsManager;
        this.documentProcessingFailureAnalyzer = documentProcessingFailureAnalyzer;
        this.documentDelayTransformer = documentDelayTransformer;
        this.mediaCallbacksUseCase = mediaCallbacksUseCase;
        this.nfcUseCase = nfcUseCase;
        this.accessibleDocumentCaptureUseCase = accessibleDocumentCaptureUseCase;
        this.timeProvider = timeProvider;
        this.documentCaptureTracker = documentCaptureTracker;
        this.barcodeValidationSuspender = barcodeValidationSuspender;
        this.retainableValidationsResult = retainableValidationsResult;
        this.dispatchersProvider = dispatchersProvider;
        this.environmentIntegrityChecker = environmentIntegrityChecker;
        this.imageUtils = imageUtils;
        this.mrzDocumentExtractor = mrzDocumentExtractor;
        this.documentValidationUseCase = documentValidationUseCase;
        this.processingResults = new DocumentProcessingResults(null, null, null, null, null, null, 63, null);
        this.poaCaptureSessionId = LazyKt.lazy(new Function0<String>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$poaCaptureSessionId$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return String.valueOf(this.this$0.timeProvider.getCurrentTimestamp());
            }
        });
        this.documentVideoId = "";
        this.mrzExtractionResultMap = new HashMap<>();
        this.documentValidaMapper = new DocumentValidationResultMapper();
        this.uploadService = LazyKt.lazy(new Function0<CaptureUploadService>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$uploadService$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CaptureUploadService invoke() {
                return this.this$0.uploadServiceFactory.create(CaptureType.DOCUMENT, this.this$0);
            }
        });
        MutableStateFlow<Integer> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._docFormatDialogTitleFlow = MutableStateFlow;
        this.docFormatDialogTitleFlow = FlowKt.asStateFlow(MutableStateFlow);
        BufferOverflow bufferOverflow = BufferOverflow.DROP_OLDEST;
        this.shouldHideOverlay = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        MutableStateFlow<CameraState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(CameraState.Uninitialized.INSTANCE);
        this._cameraState = MutableStateFlow2;
        this.cameraState = MutableStateFlow2;
        MutableStateFlow<Boolean> MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._startVideoRecording = MutableStateFlow3;
        this.startVideoRecording = MutableStateFlow3;
        Object[] objArr = 0 == true ? 1 : 0;
        MutableStateFlow<VideoRecordingEvent> MutableStateFlow4 = StateFlowKt.MutableStateFlow(new VideoRecordingEvent(false, objArr, 2, null));
        this._showVideoRecordingInfo = MutableStateFlow4;
        this.showVideoRecordingInfo = MutableStateFlow4;
        this.applyValidationsFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.hidePostCaptureValidationBubbleFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        Boolean bool = Boolean.FALSE;
        MutableStateFlow<Boolean> MutableStateFlow5 = StateFlowKt.MutableStateFlow(bool);
        this._shouldEnableTorch = MutableStateFlow5;
        this.shouldEnableTorch = MutableStateFlow5;
        this.showConfirmationFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.validCaptureFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.captureResult = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.errorMessageFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.errorDescriptorFlow = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.loadingFlow = StateFlowKt.MutableStateFlow(LoadingEvent.Hide.INSTANCE);
        MutableStateFlow<Boolean> MutableStateFlow6 = StateFlowKt.MutableStateFlow(null);
        this._startCapture = MutableStateFlow6;
        this.startCapture = MutableStateFlow6;
        MutableStateFlow<OnfidoCaptureValidationBubble.Content> MutableStateFlow7 = StateFlowKt.MutableStateFlow(null);
        this._showLiveValidation = MutableStateFlow7;
        this.showLiveValidation = MutableStateFlow7;
        MutableStateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> MutableStateFlow8 = StateFlowKt.MutableStateFlow(null);
        this._liveValidationBubbleVisibility = MutableStateFlow8;
        this.liveValidationBubbleVisibility = MutableStateFlow8;
        this.imageProcessingFinished = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.manualFallbackDelayFinished = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.accessibleCaptureRectangleDetection = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.accessibleCaptureResult = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.binaryUploadWarning = SharedFlowKt.MutableSharedFlow$default(0, 1, bufferOverflow, 1, null);
        this.overlayMetricsStateFlow = StateFlowKt.MutableStateFlow(null);
        this.documentFrame = StateFlowKt.MutableStateFlow(null);
        MutableStateFlow<Boolean> MutableStateFlow9 = StateFlowKt.MutableStateFlow(bool);
        this._captureButtonVisibility = MutableStateFlow9;
        this.captureButtonVisibility = MutableStateFlow9;
        MutableStateFlow<Boolean> MutableStateFlow10 = StateFlowKt.MutableStateFlow(bool);
        this._shouldOpenCaptureScreen = MutableStateFlow10;
        this.shouldOpenCaptureScreen = MutableStateFlow10;
        MutableStateFlow<Boolean> MutableStateFlow11 = StateFlowKt.MutableStateFlow(bool);
        this._shouldShowCaptureErrorDialog = MutableStateFlow11;
        this.shouldShowCaptureErrorDialog = MutableStateFlow11;
    }

    private final void analyseProcessingResults(DocumentProcessingResults results) {
        MutableSharedFlow<ShowConfirmationEvent> mutableSharedFlow;
        ShowConfirmationEvent showConfirmationEvent;
        ErrorDescriptor errorDescriptorForProcessingResult$onfido_capture_sdk_core_release;
        boolean z = true;
        char c = 1;
        char c2 = 1;
        if (results.isValidDocumentImage()) {
            this.validCaptureFlow.tryEmit(new ValidCaptureEvent(true));
            return;
        }
        if (!isBackSideOfRomanianNationalId() && (errorDescriptorForProcessingResult$onfido_capture_sdk_core_release = DocumentUtils.INSTANCE.getErrorDescriptorForProcessingResult$onfido_capture_sdk_core_release(results, getDocumentType$onfido_capture_sdk_core_release(), isMrzDetectionEnabled())) != null) {
            this.errorDescriptorFlow.tryEmit(errorDescriptorForProcessingResult$onfido_capture_sdk_core_release);
        }
        DefaultConstructorMarker defaultConstructorMarker = null;
        boolean z2 = false;
        if (shouldForceRetry()) {
            mutableSharedFlow = this.showConfirmationFlow;
            showConfirmationEvent = new ShowConfirmationEvent(z, z2, 2, defaultConstructorMarker);
        } else {
            mutableSharedFlow = this.showConfirmationFlow;
            showConfirmationEvent = new ShowConfirmationEvent(z2, c2 == true ? 1 : 0, c == true ? 1 : 0, defaultConstructorMarker);
        }
        mutableSharedFlow.tryEmit(showConfirmationEvent);
        this.rejectionCount++;
    }

    private final void applyValidationsAfterAnimationDelay(long animationDelayMs) {
        this._cameraState.setValue(CameraState.OFF.INSTANCE);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new AnonymousClass1(animationDelayMs, this, null), 2, null);
    }

    private final boolean backSideCaptureNeeded() {
        DocumentType documentType = getDocumentData$onfido_capture_sdk_core_release().getDocumentType();
        if (documentType != null) {
            return this.documentConfigurationManager.backSideCaptureNeeded(documentType, getDocumentData$onfido_capture_sdk_core_release().getGenericDocPages());
        }
        return false;
    }

    private final void callMediaCallback() {
        byte[] data = this.croppedImage;
        if (data == null) {
            OnfidoImage onfidoImage = this.capturedImage;
            data = onfidoImage != null ? onfidoImage.getData() : null;
            if (data == null) {
                return;
            }
        }
        this.mediaCallbacksUseCase.callMediaCallbackForPhotoCapture$onfido_capture_sdk_core_release(CaptureType.DOCUMENT, data, getDocumentType$onfido_capture_sdk_core_release(), this.documentSide);
    }

    private final void checkBinaryUploadedResult(UploadBinaryResult.BinaryUploaded uploadBinaryResult) {
        Timber.INSTANCE.i("NFC Logger - Binary uploaded", new Object[0]);
        if (uploadBinaryResult.getWarning() != null) {
            showWarningBinaryResult$default(this, uploadBinaryResult.getDocumentId(), uploadBinaryResult.getWarning(), uploadBinaryResult.getDocumentNfcSupported(), uploadBinaryResult.getWarningsBundle(), null, 16, null);
            return;
        }
        this.currentCaptureFlowError = null;
        MutableSharedFlow<DocumentCaptureScreen.DocumentCaptureResult> mutableSharedFlow = this.captureResult;
        String documentId = uploadBinaryResult.getDocumentId();
        String str = this.documentVideoId;
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release = getDocumentData$onfido_capture_sdk_core_release();
        DocSide docSide = this.documentSide;
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        mutableSharedFlow.tryEmit(new DocumentCaptureScreen.DocumentCaptureResult.Completed(documentId, str, CaptureStepDataBundle.copy$default(documentData$onfido_capture_sdk_core_release, null, null, null, this.documentFormat, docSide, null, null, 103, null), uploadBinaryResult.getDocumentNfcSupported(), null, false, 48, null));
    }

    private final void checkDocumentFormat() {
        int i;
        boolean zIsFoldedFormatSupported$onfido_capture_sdk_core_release = DocumentUtils.INSTANCE.isFoldedFormatSupported$onfido_capture_sdk_core_release(getDocumentType$onfido_capture_sdk_core_release(), this.countryCode);
        DocumentFormat documentFormat = this.documentFormat;
        if (documentFormat != null) {
            if (DocumentFormat.FOLDED == documentFormat && zIsFoldedFormatSupported$onfido_capture_sdk_core_release) {
                onFoldedFormat();
                return;
            }
            return;
        }
        if (!zIsFoldedFormatSupported$onfido_capture_sdk_core_release) {
            this.documentFormat = DocumentFormat.CARD;
            initDocumentTypeUIModel$onfido_capture_sdk_core_release(false);
            return;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[getDocumentType$onfido_capture_sdk_core_release().ordinal()];
        if (i2 == 1) {
            i = R.string.onfido_doc_capture_prompt_title_id;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException(("documentFormatDialog shouldn't be showed for this " + getDocumentType$onfido_capture_sdk_core_release()).toString());
            }
            i = R.string.onfido_doc_capture_prompt_title_license;
        }
        MutableStateFlow<Integer> mutableStateFlow = this._docFormatDialogTitleFlow;
        while (!mutableStateFlow.compareAndSet(mutableStateFlow.getValue(), Integer.valueOf(i))) {
        }
    }

    private final void checkErrorBinaryResult(UploadBinaryResult.Error uploadBinaryResult) {
        ErrorType errorTypeFromResult$onfido_capture_sdk_core_release = this.nfcUseCase.getErrorTypeFromResult$onfido_capture_sdk_core_release(uploadBinaryResult);
        this.currentCaptureFlowError = errorTypeFromResult$onfido_capture_sdk_core_release;
        onUploadError(errorTypeFromResult$onfido_capture_sdk_core_release);
    }

    private final void checkNfcPropertiesFetchedBinaryResult(UploadBinaryResult.NfcPropertiesFetched uploadBinaryResult) {
        Timber.INSTANCE.i("NFC Logger - Nfc properties fetched", new Object[0]);
        if (uploadBinaryResult.getWarning() != null) {
            showWarningBinaryResult(uploadBinaryResult.getDocumentId(), uploadBinaryResult.getWarning(), true, uploadBinaryResult.getWarningsBundle(), uploadBinaryResult.getNfcProperties());
            return;
        }
        this.currentCaptureFlowError = null;
        MutableSharedFlow<DocumentCaptureScreen.DocumentCaptureResult> mutableSharedFlow = this.captureResult;
        String documentId = uploadBinaryResult.getDocumentId();
        String str = this.documentVideoId;
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release = getDocumentData$onfido_capture_sdk_core_release();
        DocSide docSide = this.documentSide;
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        boolean z = true;
        mutableSharedFlow.tryEmit(new DocumentCaptureScreen.DocumentCaptureResult.Completed(documentId, str, CaptureStepDataBundle.copy$default(documentData$onfido_capture_sdk_core_release, null, null, null, this.documentFormat, docSide, null, null, 103, null), z, uploadBinaryResult.getNfcProperties(), false, 32, null));
    }

    private final void checkUploadBinaryResult(UploadBinaryResult uploadBinaryResult) {
        if (uploadBinaryResult instanceof UploadBinaryResult.BinaryUploaded) {
            checkBinaryUploadedResult((UploadBinaryResult.BinaryUploaded) uploadBinaryResult);
        } else if (uploadBinaryResult instanceof UploadBinaryResult.NfcPropertiesFetched) {
            checkNfcPropertiesFetchedBinaryResult((UploadBinaryResult.NfcPropertiesFetched) uploadBinaryResult);
        } else if (uploadBinaryResult instanceof UploadBinaryResult.Error) {
            checkErrorBinaryResult((UploadBinaryResult.Error) uploadBinaryResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] cropImage(byte[] jpegData) {
        ImageUtils imageUtils = this.imageUtils;
        DocumentDetectionFrame documentDetectionFrame = this.docFrame;
        if (documentDetectionFrame == null) {
            Intrinsics.throwUninitializedPropertyAccessException("docFrame");
            documentDetectionFrame = null;
        }
        return imageUtils.cropImage$onfido_capture_sdk_core_release(jpegData, documentDetectionFrame, this.onfidoRemoteConfig.getDocumentCapture().getImageCompressionQuality(), isCameraXEnabled(), isFourByThreeEnabled$onfido_capture_sdk_core_release(), !this.environmentIntegrityChecker.hasEnvironmentIntegrity()).getImageContent();
    }

    private final void disposeAutocaptureSubscriptions() {
        Job job = this.autocaptureFallbackJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.edgeDetectionFallbackTimerReached = null;
    }

    private final void enableAccessibilityCapture(DocumentType documentType) {
        Observable<RectDetectionResult> rectangleDetectorObservable = getRectangleDetectorObservable(documentType == DocumentType.PASSPORT);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new C06451(rectangleDetectorObservable, this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new AnonymousClass2(rectangleDetectorObservable, null), 2, null);
    }

    public static /* synthetic */ void getCapturedImage$onfido_capture_sdk_core_release$annotations() {
    }

    public static /* synthetic */ void getCapturedVideoFilePath$onfido_capture_sdk_core_release$annotations() {
    }

    public static /* synthetic */ void getCroppedImage$onfido_capture_sdk_core_release$annotations() {
    }

    private final DocumentTypeUIModel getDocumentTypeUIModel(boolean isProofOfAddress) {
        return DocumentUITextModelMapper.INSTANCE.toDocumentUIModel(getDocumentType$onfido_capture_sdk_core_release(), this.documentFormat, this.countryCode, this.announcementService.isEnabled(), isProofOfAddress);
    }

    public static /* synthetic */ void getDocumentVideoId$onfido_capture_sdk_core_release$annotations() {
    }

    public static /* synthetic */ void getExtractedMRZDocument$onfido_capture_sdk_core_release$annotations() {
    }

    public static /* synthetic */ void getNfcArguments$onfido_capture_sdk_core_release$annotations() {
    }

    private final long getPictureCapturedAnimationDelay() {
        return this.isAutoCaptured ? 1200L : 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPoaCaptureSessionId() {
        return (String) this.poaCaptureSessionId.getValue();
    }

    private final Observable<RectDetectionResult> getRectangleDetectorObservable(boolean isPassport) {
        RectangleDetector rectangleDetector = this.rectangleDetector;
        Observable<OverlayMetrics> observableHide = RxConvertKt.asObservable$default(FlowKt.filterNotNull(this.overlayMetricsStateFlow), null, 1, null).hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        Observable<DocumentDetectionFrame> observableHide2 = RxConvertKt.asObservable$default(FlowKt.filterNotNull(this.documentFrame), null, 1, null).hide();
        Intrinsics.checkNotNullExpressionValue(observableHide2, "hide(...)");
        Observable<RectDetectionResult> observableShare = rectangleDetector.observeRectDetection(observableHide, observableHide2, !isPassport && this.documentSide == DocSide.FRONT).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.getRectangleDetectorObservable.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final RectDetectionResult apply(RectDetectionResult rectDetectionResult) {
                Intrinsics.checkNotNullParameter(rectDetectionResult, "rectDetectionResult");
                if (!(rectDetectionResult instanceof RectDetectionResult.RectDetected)) {
                    return rectDetectionResult;
                }
                RectDetectionResult.RectDetected rectDetected = (RectDetectionResult.RectDetected) rectDetectionResult;
                return RectDetectionResult.RectDetected.copy$default(rectDetected, ImageUtilsExtKt.trimDocument(rectDetected.getRect(), DocumentCaptureViewModel.this.getDocumentType$onfido_capture_sdk_core_release()), null, null, 6, null);
            }
        }).share();
        Intrinsics.checkNotNullExpressionValue(observableShare, "share(...)");
        return observableShare;
    }

    private final List<Validation> getRequiredDocumentValidations() {
        BackendDocumentValidationsManager backendDocumentValidationsManager = this.backendDocumentValidationsManager;
        DocumentType documentType$onfido_capture_sdk_core_release = getDocumentType$onfido_capture_sdk_core_release();
        DocSide docSide = this.documentSide;
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        return backendDocumentValidationsManager.getRequiredValidations(documentType$onfido_capture_sdk_core_release, docSide, this.rejectionCount);
    }

    public static /* synthetic */ void getTakenPhotoCount$onfido_capture_sdk_core_release$annotations() {
    }

    public static /* synthetic */ void getUploadBinaryResult$onfido_capture_sdk_core_release$annotations() {
    }

    private final CaptureUploadService getUploadService() {
        return (CaptureUploadService) this.uploadService.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        this.loadingFlow.tryEmit(LoadingEvent.Hide.INSTANCE);
    }

    private final boolean isBackSideOfRomanianNationalId() {
        return this.documentSide == DocSide.BACK && this.countryCode == CountryCode.RO && getDocumentType$onfido_capture_sdk_core_release() == DocumentType.NATIONAL_IDENTITY_CARD;
    }

    private final boolean isCameraXEnabled() {
        return this.onfidoRemoteConfig.isCameraXEnabled();
    }

    private final boolean isCheckingImageQuality() {
        return shouldForceRetry() && this.onfidoRemoteConfig.isImageQualityServiceEnabled();
    }

    private final boolean isDocumentUploaded() {
        return this.uploadBinaryResult != null;
    }

    private final boolean isFinalStepForDocument() {
        return (getDocumentData$onfido_capture_sdk_core_release().getDocSide() == DocSide.FRONT && !backSideCaptureNeeded()) || getDocumentData$onfido_capture_sdk_core_release().getDocSide() == DocSide.BACK;
    }

    private final boolean isFolded(DocumentType documentType, DocumentType documentType2, CountryCode countryCode) {
        return DocumentFormat.FOLDED == this.documentFormat && documentType2 == documentType && countryCode == this.countryCode;
    }

    private final boolean isMlModelAutoCaptureEnabled() {
        return (!this.onfidoRemoteConfig.getDocumentDetectionExperiment().getEnabled() || shouldEnableAccessibilityCapture$onfido_capture_sdk_core_release() || this.documentFormat == DocumentFormat.FOLDED || this.isProofOfAddress) ? false : true;
    }

    private final boolean isMrzDetectionEnabled() {
        boolean zIsMrzDetectionEnabled = this.onfidoRemoteConfig.getDocumentCapture().isMrzDetectionEnabled();
        Timber.INSTANCE.d("MRZ detection validation enabled: " + zIsMrzDetectionEnabled, new Object[0]);
        return zIsMrzDetectionEnabled;
    }

    private final void observeAutoCapture() {
        this.autoCaptureJob = FlowKt.launchIn(FlowKt.onEach(FlowKt.transformWhile(FlowExtKt.skipWhile(this.documentValidationUseCase.getValidationResult(), new Function1<DocumentValidationResult, Boolean>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.observeAutoCapture.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(DocumentValidationResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(DocumentCaptureViewModel.this._docFormatDialogTitleFlow.getValue() != null);
            }
        }), new C06482(null)), new AnonymousClass3(null)), ViewModelKt.getViewModelScope(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBinaryUploaded(UploadBinaryResult uploadBinaryResult) {
        this.uploadBinaryResult = uploadBinaryResult;
        String documentId = uploadBinaryResult.getDocumentId();
        if (!shouldRecordDocumentVideo$onfido_capture_sdk_core_release() || documentId == null) {
            checkUploadBinaryResult(uploadBinaryResult);
        } else {
            trackWaitingScreenCompleted();
            String str = this.capturedVideoFilePath;
            if (str != null) {
                CaptureUploadService uploadService = getUploadService();
                SdkUploadMetaData sdkUploadMetadata = sdkUploadMetadata(getDocumentData$onfido_capture_sdk_core_release());
                InternalToken internalTokenProvideToken = this.tokenProvider.provideToken();
                Intrinsics.checkNotNull(internalTokenProvideToken, "null cannot be cast to non-null type com.onfido.api.client.token.sdk.InternalSDKToken");
                ApplicantId applicantId = ((InternalSDKToken) internalTokenProvideToken).getApplicantId();
                DocSide docSide = getDocumentData$onfido_capture_sdk_core_release().getDocSide();
                if (docSide == null) {
                    docSide = DocSide.FRONT;
                }
                DocSide docSide2 = docSide;
                DocumentType documentType = getDocumentData$onfido_capture_sdk_core_release().getDocumentType();
                if (documentType == null) {
                    documentType = DocumentType.UNKNOWN;
                }
                uploadService.uploadDocumentVideo$onfido_capture_sdk_core_release(documentId, str, sdkUploadMetadata, applicantId, docSide2, documentType, getDocumentData$onfido_capture_sdk_core_release().getCountryCode());
            } else {
                this.validCaptureFlow.tryEmit(new ValidCaptureEvent(false));
            }
        }
        this.documentCaptureTracker.trackDocumentUploadCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCaptureForProofOfAddressDone(OnfidoImage capturedImage) {
        ImageUtils imageUtils = this.imageUtils;
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new C06491(capturedImage, imageUtils.getDocumentDetectionFrame$onfido_capture_sdk_core_release(capturedImage, overlayMetrics.getVisibleCaptureRect()), null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onErrorVideoTaking() {
        if (this.onfidoRemoteConfig.getDocumentCapture().getVideoRequired()) {
            onPictureCaptureFailed$onfido_capture_sdk_core_release();
        }
    }

    private final void onFoldedFormat() {
        initDocumentTypeUIModel$onfido_capture_sdk_core_release(false);
        startOverlayDisplayTimer$onfido_capture_sdk_core_release();
    }

    private final void onGeneralUploadError() {
        trackCaptureError$onfido_capture_sdk_core_release();
        trackWaitingScreenCompleted();
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_network_title, R.string.onfido_generic_error_doc_capture);
    }

    private final void onInvalidCertificateDetected(String message) {
        this.captureResult.tryEmit(new DocumentCaptureScreen.DocumentCaptureResult.Error(new InvalidCertificateException(message)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPostCaptureValidationsFinished(DocumentProcessingResults processingResults, DocumentDetectionFrame frame) {
        setMRZResult(processingResults.getMrzValidationResult());
        this.processingResults = processingResults;
        if (this.onfidoRemoteConfig.isImageQualityServiceEnabled() && processingResults.isValidDocumentImage()) {
            trackWaitingScreenCompleted();
            uploadDocument(frame.getYuv());
        } else {
            boolean z = false;
            this.showConfirmationFlow.tryEmit(new ShowConfirmationEvent(z, z, 2, null));
            analyseProcessingResults(processingResults);
        }
    }

    private final void onTokenExpired() {
        this.captureResult.tryEmit(new DocumentCaptureScreen.DocumentCaptureResult.Error(TokenExpiredException.INSTANCE));
    }

    private final void onUploadFailure() {
        trackCaptureError$onfido_capture_sdk_core_release();
        trackWaitingScreenCompleted();
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_network_title, R.string.onfido_generic_error_network_detail);
    }

    private final void onUploadFailureWithGeoblocking() {
        trackCaptureError$onfido_capture_sdk_core_release();
        trackWaitingScreenCompleted();
        hideLoading();
        showErrorMessage(R.string.onfido_generic_errors_geoblocked_error_message, R.string.onfido_generic_errors_geoblocked_error_instruction);
    }

    private final void onUploadValidationError(ErrorType errorType) {
        this.documentCaptureTracker.trackDocumentConfirmationError(errorType, getDocumentData$onfido_capture_sdk_core_release().getDocSide());
        if (isCheckingImageQuality()) {
            trackWaitingScreenCompleted();
        }
        hideLoading();
        this.showConfirmationFlow.tryEmit(new ShowConfirmationEvent(shouldForceRetry(), false, 2, null));
        if (!isBackSideOfRomanianNationalId()) {
            this.errorDescriptorFlow.tryEmit(ErrorTypeUtilsKt.mapErrorType(errorType));
        }
        this.rejectionCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processAccessibleDocumentCaptureResult(AccessibleDocumentCaptureUseCaseResult result) {
        if (result instanceof AccessibleDocumentCaptureUseCaseResult.AutoCaptured) {
            this._startCapture.tryEmit(Boolean.valueOf(!shouldRecordDocumentVideo$onfido_capture_sdk_core_release()));
        } else if (result instanceof AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged) {
            this.accessibleCaptureResult.tryEmit(result);
        }
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
            boolean r0 = r14 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.C06501
            if (r0 == 0) goto L13
            r0 = r14
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$processFrameForMRZ$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.C06501) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$processFrameForMRZ$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel$processFrameForMRZ$1
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
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r13 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel) r13
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
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureTracker r5 = r13.documentCaptureTracker
            int r10 = r13.emittedFramesCount
            int r11 = r13.processedFramesCount
            r5.trackDocumentValidMRZExtracted(r6, r8, r10, r11)
        L87:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel.processFrameForMRZ(com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SdkUploadMetaData sdkUploadMetadata(CaptureStepDataBundle captureStepDataBundle) {
        return this.sdkUploadMetaDataHelper.create(this.processingResults, this.documentProcessingFailureAnalyzer.getProcessingFailureCounts().getBlurFailureCount(), this.nfcUseCase.isMRZExtracted$onfido_capture_sdk_core_release(getDocumentType$onfido_capture_sdk_core_release(), this.countryCode, this.mrzExtractionResultMap), this.takenPhotoCount, captureStepDataBundle);
    }

    private final void setMRZResult(MRZValidationResult result) {
        this.extraFileInfo = this.nfcUseCase.getMRZResult$onfido_capture_sdk_core_release(result);
    }

    private final boolean shouldAutocapture() {
        return this.documentConfigurationManager.shouldAutocapture(getDocumentType$onfido_capture_sdk_core_release(), this.countryCode);
    }

    private final boolean shouldForceRetry() {
        return this.rejectionCount < this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries() && !isBackSideOfRomanianNationalId();
    }

    private final boolean shouldShowOverlay(boolean isFrontSide, boolean isOverlayGone, DocumentType documentType, CountryCode countryCode) {
        return isFrontSide && !isOverlayGone && DocumentFormat.FOLDED == this.documentFormat && getDocumentType$onfido_capture_sdk_core_release() == documentType && this.countryCode == countryCode;
    }

    private final boolean shouldUploadDocument() {
        return (isDocumentUploaded() && this.onfidoRemoteConfig.isImageQualityServiceEnabled()) ? false : true;
    }

    private final void showErrorMessage(int title, int message) {
        this.errorMessageFlow.tryEmit(new ErrorMessageEvent(title, message));
    }

    private final void showLoading(LoadingFragment.Companion.DialogMode mode) {
        this.loadingFlow.tryEmit(new LoadingEvent.Show(mode));
    }

    private final void showWarningBinaryResult(String documentId, ErrorType warning, boolean nfcSupported, DocumentValidationWarningsBundle warningsBundle, NfcProperties nfcProperties) {
        Map<ErrorType, UiAlerts.UiAlertType> mapEmptyMap;
        DocumentCaptureTracker documentCaptureTracker = this.documentCaptureTracker;
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release = getDocumentData$onfido_capture_sdk_core_release();
        int i = this.takenPhotoCount;
        int maxTotalRetries = this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries();
        if (warningsBundle == null || (mapEmptyMap = DocumentValidationWarningsBundleUtilsKt.remoteWarnings(warningsBundle)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        documentCaptureTracker.trackDocumentConfirmationWarning(documentData$onfido_capture_sdk_core_release, i, maxTotalRetries, mapEmptyMap, warning);
        this.currentCaptureFlowError = warning;
        String str = this.documentVideoId;
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release2 = getDocumentData$onfido_capture_sdk_core_release();
        DocSide docSide = this.documentSide;
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        this.binaryUploadWarning.tryEmit(new BinaryUploadWarningEvent(ErrorTypeUtilsKt.mapErrorType(warning), new DocumentCaptureScreen.DocumentCaptureResult.Completed(documentId, str, CaptureStepDataBundle.copy$default(documentData$onfido_capture_sdk_core_release2, null, null, null, this.documentFormat, docSide, null, null, 103, null), nfcSupported, nfcProperties, true)));
    }

    static /* synthetic */ void showWarningBinaryResult$default(DocumentCaptureViewModel documentCaptureViewModel, String str, ErrorType errorType, boolean z, DocumentValidationWarningsBundle documentValidationWarningsBundle, NfcProperties nfcProperties, int i, Object obj) {
        if ((i & 16) != 0) {
            nfcProperties = null;
        }
        documentCaptureViewModel.showWarningBinaryResult(str, errorType, z, documentValidationWarningsBundle, nfcProperties);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startManualFallbackTimer() {
        if (this.autocaptureFallbackJob == null) {
            this.autocaptureFallbackJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getMain(), null, new C06511(null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void stopDocumentRecording() {
        this._shouldEnableTorch.setValue(Boolean.FALSE);
        this._showVideoRecordingInfo.setValue(new VideoRecordingEvent(false, 0 == true ? 1 : 0, 2, null));
        this._showVideoRecordingInfo.setValue(new VideoRecordingEvent(true, R.string.onfido_doc_capture_header_recording_complete));
        stopDocumentVideoRecordingAndCameraFeed();
    }

    private final void stopDocumentVideoRecordingAndCameraFeed() {
        this._startVideoRecording.setValue(Boolean.FALSE);
        applyValidationsAfterAnimationDelay(1200L);
    }

    private final void trackDocumentCaptureFlowCompleted() {
        if (isFinalStepForDocument()) {
            this.documentCaptureTracker.trackDocumentCaptureFlowCompleted(getDocumentData$onfido_capture_sdk_core_release());
        }
    }

    private final void uploadDocument(byte[] jpegData) {
        this.hidePostCaptureValidationBubbleFlow.tryEmit(Boolean.FALSE);
        showLoading(new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT));
        trackUploadStarted$onfido_capture_sdk_core_release();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new C06521(jpegData, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void uploadImageForValidation(byte[] jpegData) {
        List<Validation> requiredDocumentValidations = getRequiredDocumentValidations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(requiredDocumentValidations, 10));
        for (Validation validation : requiredDocumentValidations) {
            if (isBackSideOfRomanianNationalId()) {
                validation = new Validation(validation.getValidationType(), ValidationLevel.WARNING);
            }
            arrayList.add(validation);
        }
        uploadDocumentMedia$onfido_capture_sdk_core_release(arrayList, jpegData);
    }

    public final void applyPostCaptureValidations$onfido_capture_sdk_core_release(OnfidoImage image, RectF outerLimits) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(outerLimits, "outerLimits");
        DocumentDetectionFrame documentDetectionFrame$onfido_capture_sdk_core_release = this.imageUtils.getDocumentDetectionFrame$onfido_capture_sdk_core_release(image, outerLimits);
        this.docFrame = documentDetectionFrame$onfido_capture_sdk_core_release;
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new DocumentCaptureViewModel$applyPostCaptureValidations$1(this, documentDetectionFrame$onfido_capture_sdk_core_release, null), 2, null);
    }

    public final MutableSharedFlow<RectDetectionResult> getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release() {
        return this.accessibleCaptureRectangleDetection;
    }

    public final MutableSharedFlow<AccessibleDocumentCaptureUseCaseResult.DocumentPositionChanged> getAccessibleCaptureResult$onfido_capture_sdk_core_release() {
        return this.accessibleCaptureResult;
    }

    public final MutableSharedFlow<OnfidoImage> getApplyValidationsFlow$onfido_capture_sdk_core_release() {
        return this.applyValidationsFlow;
    }

    public final MutableSharedFlow<BinaryUploadWarningEvent> getBinaryUploadWarning$onfido_capture_sdk_core_release() {
        return this.binaryUploadWarning;
    }

    public final StateFlow<CameraState> getCameraState$onfido_capture_sdk_core_release() {
        return this.cameraState;
    }

    public final StateFlow<Boolean> getCaptureButtonVisibility$onfido_capture_sdk_core_release() {
        return this.captureButtonVisibility;
    }

    public final StringRepresentation getCaptureFrameContentDescription$onfido_capture_sdk_core_release(DocumentFormat forFormat) {
        DocumentConfigurationManager documentConfigurationManager = this.documentConfigurationManager;
        DocumentType documentType$onfido_capture_sdk_core_release = getDocumentType$onfido_capture_sdk_core_release();
        CountryCode countryCode = this.countryCode;
        DocSide docSide = this.documentSide;
        if (forFormat == null) {
            forFormat = this.documentFormat;
        }
        return new StringRepresentation.SingleStringResIdRepresentation(documentConfigurationManager.captureFrameContentDescription(documentType$onfido_capture_sdk_core_release, countryCode, docSide, forFormat, this.announcementService.isEnabled()));
    }

    public final MutableSharedFlow<DocumentCaptureScreen.DocumentCaptureResult> getCaptureResult$onfido_capture_sdk_core_release() {
        return this.captureResult;
    }

    public final File getCapturedFilesDir$onfido_capture_sdk_core_release() {
        File file = this.capturedFilesDir;
        if (file != null) {
            return file;
        }
        Intrinsics.throwUninitializedPropertyAccessException("capturedFilesDir");
        return null;
    }

    /* renamed from: getCapturedImage$onfido_capture_sdk_core_release, reason: from getter */
    public final OnfidoImage getCapturedImage() {
        return this.capturedImage;
    }

    /* renamed from: getCapturedVideoFilePath$onfido_capture_sdk_core_release, reason: from getter */
    public final String getCapturedVideoFilePath() {
        return this.capturedVideoFilePath;
    }

    /* renamed from: getCountryCode$onfido_capture_sdk_core_release, reason: from getter */
    public final CountryCode getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: getCroppedImage$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getCroppedImage() {
        return this.croppedImage;
    }

    /* renamed from: getCurrentCaptureFlowError$onfido_capture_sdk_core_release, reason: from getter */
    public final ErrorType getCurrentCaptureFlowError() {
        return this.currentCaptureFlowError;
    }

    public final StateFlow<Integer> getDocFormatDialogTitleFlow$onfido_capture_sdk_core_release() {
        return this.docFormatDialogTitleFlow;
    }

    public final DocumentCaptureResultConsumer getDocumentCaptureResultConsumer$onfido_capture_sdk_core_release() {
        DocumentCaptureResultConsumer documentCaptureResultConsumer = this.documentCaptureResultConsumer;
        if (documentCaptureResultConsumer != null) {
            return documentCaptureResultConsumer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("documentCaptureResultConsumer");
        return null;
    }

    public final CaptureStepDataBundle getDocumentData$onfido_capture_sdk_core_release() {
        CaptureStepDataBundle captureStepDataBundle = this.documentData;
        if (captureStepDataBundle != null) {
            return captureStepDataBundle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("documentData");
        return null;
    }

    /* renamed from: getDocumentFormat$onfido_capture_sdk_core_release, reason: from getter */
    public final DocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    /* renamed from: getDocumentSide$onfido_capture_sdk_core_release, reason: from getter */
    public final DocSide getDocumentSide() {
        return this.documentSide;
    }

    public final DocumentType getDocumentType$onfido_capture_sdk_core_release() {
        DocumentType documentType = this.documentType;
        if (documentType != null) {
            return documentType;
        }
        Intrinsics.throwUninitializedPropertyAccessException("documentType");
        return null;
    }

    public final DocumentTypeUIModel getDocumentTypeUIModel$onfido_capture_sdk_core_release() {
        DocumentTypeUIModel documentTypeUIModel = this.documentTypeUIModel;
        if (documentTypeUIModel != null) {
            return documentTypeUIModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("documentTypeUIModel");
        return null;
    }

    /* renamed from: getDocumentVideoId$onfido_capture_sdk_core_release, reason: from getter */
    public final String getDocumentVideoId() {
        return this.documentVideoId;
    }

    public final MutableSharedFlow<ErrorDescriptor> getErrorDescriptorFlow$onfido_capture_sdk_core_release() {
        return this.errorDescriptorFlow;
    }

    public final MutableSharedFlow<ErrorMessageEvent> getErrorMessageFlow$onfido_capture_sdk_core_release() {
        return this.errorMessageFlow;
    }

    /* renamed from: getExtractedMRZDocument$onfido_capture_sdk_core_release, reason: from getter */
    public final MRZDocument getExtractedMRZDocument() {
        return this.extractedMRZDocument;
    }

    public final MutableSharedFlow<Boolean> getHidePostCaptureValidationBubbleFlow$onfido_capture_sdk_core_release() {
        return this.hidePostCaptureValidationBubbleFlow;
    }

    public final MutableSharedFlow<Boolean> getImageProcessingFinished$onfido_capture_sdk_core_release() {
        return this.imageProcessingFinished;
    }

    public final StateFlow<OnfidoCaptureValidationBubble.VisibilityCommand> getLiveValidationBubbleVisibility$onfido_capture_sdk_core_release() {
        return this.liveValidationBubbleVisibility;
    }

    public final MutableStateFlow<LoadingEvent> getLoadingFlow$onfido_capture_sdk_core_release() {
        return this.loadingFlow;
    }

    public final MutableSharedFlow<Boolean> getManualFallbackDelayFinished$onfido_capture_sdk_core_release() {
        return this.manualFallbackDelayFinished;
    }

    /* renamed from: getNfcArguments$onfido_capture_sdk_core_release, reason: from getter */
    public final NfcArguments getNfcArguments() {
        return this.nfcArguments;
    }

    public final int getOverlayLayout$onfido_capture_sdk_core_release() {
        if (this.isProofOfAddress) {
            return R.layout.onfido_view_overlay_poa_a4page;
        }
        if (SetsKt.setOf((Object[]) new DocumentType[]{DocumentType.PASSPORT, DocumentType.VISA}).contains(getDocumentType$onfido_capture_sdk_core_release())) {
            return R.layout.onfido_view_overlay_passport;
        }
        DocumentType documentType$onfido_capture_sdk_core_release = getDocumentType$onfido_capture_sdk_core_release();
        DocumentType documentType = DocumentType.DRIVING_LICENCE;
        if (isFolded(documentType$onfido_capture_sdk_core_release, documentType, CountryCode.DE)) {
            return R.layout.onfido_view_overlay_german_dl;
        }
        if (isFolded(getDocumentType$onfido_capture_sdk_core_release(), documentType, CountryCode.FR)) {
            return R.layout.onfido_view_overlay_french_dl;
        }
        DocumentType documentType$onfido_capture_sdk_core_release2 = getDocumentType$onfido_capture_sdk_core_release();
        DocumentType documentType2 = DocumentType.NATIONAL_IDENTITY_CARD;
        return (isFolded(documentType$onfido_capture_sdk_core_release2, documentType2, CountryCode.IT) || isFolded(getDocumentType$onfido_capture_sdk_core_release(), documentType2, CountryCode.ZA)) ? R.layout.onfido_view_overlay_italian_id : getDocumentType$onfido_capture_sdk_core_release() == DocumentType.GENERIC ? R.layout.onfido_view_overlay_generic : R.layout.onfido_view_overlay_document;
    }

    public final StateFlow<Boolean> getShouldEnableTorch$onfido_capture_sdk_core_release() {
        return this.shouldEnableTorch;
    }

    public final MutableSharedFlow<Boolean> getShouldHideOverlay$onfido_capture_sdk_core_release() {
        return this.shouldHideOverlay;
    }

    public final StateFlow<Boolean> getShouldOpenCaptureScreen$onfido_capture_sdk_core_release() {
        return this.shouldOpenCaptureScreen;
    }

    public final StateFlow<Boolean> getShouldShowCaptureErrorDialog$onfido_capture_sdk_core_release() {
        return this.shouldShowCaptureErrorDialog;
    }

    public final MutableSharedFlow<ShowConfirmationEvent> getShowConfirmationFlow$onfido_capture_sdk_core_release() {
        return this.showConfirmationFlow;
    }

    public final StateFlow<OnfidoCaptureValidationBubble.Content> getShowLiveValidation$onfido_capture_sdk_core_release() {
        return this.showLiveValidation;
    }

    public final StateFlow<VideoRecordingEvent> getShowVideoRecordingInfo$onfido_capture_sdk_core_release() {
        return this.showVideoRecordingInfo;
    }

    public final StateFlow<Boolean> getStartCapture$onfido_capture_sdk_core_release() {
        return this.startCapture;
    }

    public final StateFlow<Boolean> getStartVideoRecording$onfido_capture_sdk_core_release() {
        return this.startVideoRecording;
    }

    /* renamed from: getTakenPhotoCount$onfido_capture_sdk_core_release, reason: from getter */
    public final int getTakenPhotoCount() {
        return this.takenPhotoCount;
    }

    /* renamed from: getUploadBinaryResult$onfido_capture_sdk_core_release, reason: from getter */
    public final UploadBinaryResult getUploadBinaryResult() {
        return this.uploadBinaryResult;
    }

    public final MutableSharedFlow<ValidCaptureEvent> getValidCaptureFlow$onfido_capture_sdk_core_release() {
        return this.validCaptureFlow;
    }

    public final boolean hasNativeLibrary$onfido_capture_sdk_core_release() {
        return this.nativeDetector.hasNativeLibrary();
    }

    public final void initDocumentTypeUIModel$onfido_capture_sdk_core_release(boolean isProofOfAddress) {
        setDocumentTypeUIModel$onfido_capture_sdk_core_release(getDocumentTypeUIModel(isProofOfAddress));
    }

    /* renamed from: isAutoCaptured$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsAutoCaptured() {
        return this.isAutoCaptured;
    }

    public final boolean isCutoffImprovementsEnabled$onfido_capture_sdk_core_release() {
        return this.onfidoRemoteConfig.isCutoffImprovementsEnabled();
    }

    public final boolean isDarkModeEnabled$onfido_capture_sdk_core_release() {
        Boolean boolValueOf;
        Object locale;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
        StorageKey storageKey = StorageKey.IS_IN_DARK_MODE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
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
                locale = sharedPreferencesDataSource.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            boolValueOf = (Boolean) locale;
        } else {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }

    public final boolean isDocumentFrameValidForAutoCapture$onfido_capture_sdk_core_release(DocumentProcessingResults results) {
        Intrinsics.checkNotNullParameter(results, "results");
        return this.shouldWaitForMRZExtractionResult ? this.isMRZExtractionTimeExceeded && results.isValidDocumentImage() : results.isValidDocumentImage();
    }

    public final boolean isFourByThreeEnabled$onfido_capture_sdk_core_release() {
        return this.onfidoRemoteConfig.isFourByThreeEnabled();
    }

    /* renamed from: isProofOfAddress$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsProofOfAddress() {
        return this.isProofOfAddress;
    }

    public final void onCameraStarted$onfido_capture_sdk_core_release() {
        this.extractedMRZDocument = null;
        this.firstFrameEmitTime = 0L;
        this._captureButtonVisibility.setValue(Boolean.valueOf(!shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release()));
        if (isMlModelAutoCaptureEnabled()) {
            observeAutoCapture();
        }
    }

    public final void onCaptureButtonClicked$onfido_capture_sdk_core_release() {
        this._startCapture.tryEmit(null);
        this._captureButtonVisibility.setValue(Boolean.FALSE);
        trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release();
        trackStartPerformanceEvent$onfido_capture_sdk_core_release();
        disposeAutocaptureSubscriptions();
    }

    public final void onCaptureCompleted$onfido_capture_sdk_core_release(DocumentCaptureScreen.DocumentCaptureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof DocumentCaptureScreen.DocumentCaptureResult.Completed) {
            callMediaCallback();
        }
    }

    public final void onCaptureConfirmed$onfido_capture_sdk_core_release() {
        OnfidoImage onfidoImage = this.capturedImage;
        if (onfidoImage == null) {
            this.validCaptureFlow.tryEmit(new ValidCaptureEvent(false));
            return;
        }
        Intrinsics.checkNotNull(onfidoImage, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.camera.OnfidoImage");
        if (shouldUploadDocument()) {
            uploadDocument(onfidoImage.getData());
        } else {
            UploadBinaryResult uploadBinaryResult = this.uploadBinaryResult;
            if (uploadBinaryResult != null) {
                checkUploadBinaryResult(uploadBinaryResult);
            }
        }
        trackDocumentCaptureFlowCompleted();
    }

    public final void onCaptureDiscarded$onfido_capture_sdk_core_release() {
        this._shouldOpenCaptureScreen.setValue(Boolean.TRUE);
    }

    public final void onCaptureErrorConfirmed$onfido_capture_sdk_core_release() {
        if (this.onfidoRemoteConfig.getCameraXConfiguration().isFallbackEnabled()) {
            Timber.INSTANCE.e("CameraX encountered an issue, switching to Camera1 API", new Object[0]);
            DefaultOnfidoRemoteConfig.INSTANCE.setCameraXEnabled(false);
        }
        this._shouldShowCaptureErrorDialog.setValue(Boolean.FALSE);
        this._shouldOpenCaptureScreen.setValue(Boolean.TRUE);
    }

    public final void onCaptureScreenOpened$onfido_capture_sdk_core_release() {
        this.capturedImage = null;
        this._shouldOpenCaptureScreen.setValue(Boolean.FALSE);
        this._cameraState.setValue(CameraState.ON.INSTANCE);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.rectangleDetector.close();
    }

    public final void onDocumentFormatSelected$onfido_capture_sdk_core_release(DocumentFormat documentFormat) {
        Intrinsics.checkNotNullParameter(documentFormat, "documentFormat");
        FlowExtKt.clear(this._docFormatDialogTitleFlow);
        this.documentFormat = documentFormat;
        if (documentFormat == DocumentFormat.FOLDED) {
            onFoldedFormat();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onDocumentVideoUploaded(String documentVideoId) {
        Intrinsics.checkNotNullParameter(documentVideoId, "documentVideoId");
        this.documentVideoId = documentVideoId;
        trackWaitingScreenCompleted();
        UploadBinaryResult uploadBinaryResult = this.uploadBinaryResult;
        if (uploadBinaryResult != null) {
            checkUploadBinaryResult(uploadBinaryResult);
        } else {
            this.validCaptureFlow.tryEmit(new ValidCaptureEvent(false));
        }
    }

    public final void onErrorVideoRecording$onfido_capture_sdk_core_release() {
        this._shouldShowCaptureErrorDialog.setValue(Boolean.TRUE);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onLivePhotoUploaded(LivePhotoUpload livePhotoUpload) {
        CaptureUploadServiceListener.DefaultImpls.onLivePhotoUploaded(this, livePhotoUpload);
    }

    public final void onManualFallbackDelayFinished$onfido_capture_sdk_core_release() {
        this._captureButtonVisibility.setValue(Boolean.TRUE);
    }

    public final void onNextFrame$onfido_capture_sdk_core_release(OnfidoImage frame, OverlayMetrics overlayMetrics) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        boolean z = false;
        if (this.firstFrameEmitTime == 0) {
            this.emittedFramesCount = 0;
            this.processedFramesCount = 0;
            this.firstFrameEmitTime = this.timeProvider.getCurrentTimestamp();
        }
        this.emittedFramesCount++;
        DocumentDetectionFrame documentDetectionFrame$onfido_capture_sdk_core_release = this.imageUtils.getDocumentDetectionFrame$onfido_capture_sdk_core_release(frame, overlayMetrics.getRealCaptureRect(), overlayMetrics.getVisibleCaptureRect());
        if (isMlModelAutoCaptureEnabled()) {
            DocumentValidationTargets documentValidationTargets = this.documentValidationTarget;
            if (documentValidationTargets != null) {
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DocumentCaptureViewModel$onNextFrame$1$1(this, documentDetectionFrame$onfido_capture_sdk_core_release, documentValidationTargets, null), 3, null);
            }
        } else {
            frame.getBitmap().recycle();
            this.nativeDetector.getFrameData().onNext(documentDetectionFrame$onfido_capture_sdk_core_release);
            this.documentFrame.tryEmit(documentDetectionFrame$onfido_capture_sdk_core_release);
        }
        this.nativeDetector.getFrameData().onNext(documentDetectionFrame$onfido_capture_sdk_core_release);
        this.documentFrame.tryEmit(documentDetectionFrame$onfido_capture_sdk_core_release);
        MRZDocument mRZDocument = this.extractedMRZDocument;
        if (mRZDocument != null && mRZDocument.isValid()) {
            z = true;
        }
        if (!this.onfidoRemoteConfig.isOnDeviceMRZExtractionEnabled() || z) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DocumentCaptureViewModel$onNextFrame$2(this, documentDetectionFrame$onfido_capture_sdk_core_release, null), 3, null);
    }

    public final void onOverlayMetrics$onfido_capture_sdk_core_release(OverlayMetrics overlayMetrics) {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.overlayMetrics = overlayMetrics;
        this.overlayMetricsStateFlow.tryEmit(overlayMetrics);
        this._cameraState.setValue(CameraState.ON.INSTANCE);
        if (this.documentType != null) {
            DocumentType documentType$onfido_capture_sdk_core_release = getDocumentType$onfido_capture_sdk_core_release();
            DocSide docSide = this.documentSide;
            OnfidoRectF.Companion companion = OnfidoRectF.INSTANCE;
            OnfidoRectF onfidoRectF = companion.toOnfidoRectF(overlayMetrics.getVisibleCaptureRect());
            OnfidoRectF onfidoRectF2 = companion.toOnfidoRectF(overlayMetrics.getRealCaptureRect());
            Duration.Companion companion2 = Duration.INSTANCE;
            this.documentValidationTarget = new DocumentValidationTargets(documentType$onfido_capture_sdk_core_release, docSide, onfidoRectF, onfidoRectF2, DurationKt.toDuration(this.onfidoRemoteConfig.getDocumentDetectionExperiment().getHoldDurationInMs(), DurationUnit.MILLISECONDS), null);
        }
    }

    public final void onPictureCaptureFailed$onfido_capture_sdk_core_release() {
        if (this.capturedImage != null) {
            Timber.INSTANCE.i("An issue occurred after the image capture. But, it has been ignored!", new Object[0]);
            return;
        }
        trackEndPerformanceEvent$onfido_capture_sdk_core_release();
        this._shouldShowCaptureErrorDialog.setValue(Boolean.TRUE);
        this._cameraState.setValue(CameraState.OFF.INSTANCE);
    }

    public final void onPictureCaptured$onfido_capture_sdk_core_release(OnfidoImage image) {
        Intrinsics.checkNotNullParameter(image, "image");
        trackEndPerformanceEvent$onfido_capture_sdk_core_release();
        this.takenPhotoCount++;
        this.capturedImage = image;
        this.uploadBinaryResult = null;
        this.documentVideoId = "";
        if (!shouldRecordDocumentVideo$onfido_capture_sdk_core_release()) {
            applyValidationsAfterAnimationDelay(getPictureCapturedAnimationDelay());
        } else {
            this._startVideoRecording.setValue(Boolean.TRUE);
            this._showVideoRecordingInfo.setValue(new VideoRecordingEvent(true, R.string.onfido_doc_capture_header_recording_video));
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CaptureUploadServiceListener
    public void onUploadError(ErrorType errorType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        if ((errorType instanceof ErrorType.Document) || Intrinsics.areEqual(errorType, ErrorType.Cutoff.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Glare.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Blur.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.NoFace.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.MultipleFaces.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Barcode.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.PhotoOfScreen.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Screenshot.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Photocopy.INSTANCE) || Intrinsics.areEqual(errorType, ErrorType.Scan.INSTANCE)) {
            onUploadValidationError(errorType);
            return;
        }
        if (errorType instanceof ErrorType.Network) {
            onUploadFailure();
            return;
        }
        if (errorType instanceof ErrorType.InvalidCertificate) {
            onInvalidCertificateDetected(((ErrorType.InvalidCertificate) errorType).getMessage());
            return;
        }
        if (errorType instanceof ErrorType.TokenExpired) {
            onTokenExpired();
        } else if (errorType instanceof ErrorType.Geoblocked) {
            onUploadFailureWithGeoblocking();
        } else if (errorType instanceof ErrorType.Generic) {
            onGeneralUploadError();
        }
    }

    public final void onVideoCaptured$onfido_capture_sdk_core_release(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.capturedVideoFilePath = filePath;
    }

    public final void onVideoRecordingStopped$onfido_capture_sdk_core_release() {
        this._startVideoRecording.setValue(null);
    }

    public final void onViewResumed$onfido_capture_sdk_core_release() {
        if (hasNativeLibrary$onfido_capture_sdk_core_release()) {
            this._showLiveValidation.tryEmit(new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_alert_glare_title, Integer.valueOf(R.string.onfido_doc_capture_alert_glare_detail)));
        }
        checkDocumentFormat();
    }

    public final void prepareCaptureStart$onfido_capture_sdk_core_release(boolean isFirstStart, Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        trackCapture$onfido_capture_sdk_core_release(orientation);
        if (shouldEnableAccessibilityCapture$onfido_capture_sdk_core_release()) {
            enableAccessibilityCapture(getDocumentType$onfido_capture_sdk_core_release());
        }
        this.mrzExtractionResultMap.clear();
        this.barcodeValidationSuspender.reset$onfido_capture_sdk_core_release();
        this.retainableValidationsResult.clear$onfido_capture_sdk_core_release();
        this.isAutoCaptured = false;
        setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release(new DocumentCaptureResultConsumer(this, this.onfidoRemoteConfig, shouldAutoCaptureDocument$onfido_capture_sdk_core_release(), this.shouldHideOverlay, this._startCapture, this._showLiveValidation, this._liveValidationBubbleVisibility));
        Job job = this.imageProcessingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.imageProcessingJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getIo(), null, new DocumentCaptureViewModel$prepareCaptureStart$1(this, isFirstStart, null), 2, null);
    }

    public final void reset$onfido_capture_sdk_core_release() {
        Job job = this.autoCaptureJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.imageProcessingJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.nativeDetector.clearEdges();
        this.shouldHideOverlay.tryEmit(Boolean.TRUE);
        disposeAutocaptureSubscriptions();
        this.shouldWaitForMRZExtractionResult = false;
        this.isMRZExtractionTimeExceeded = false;
        this._captureButtonVisibility.setValue(Boolean.FALSE);
    }

    public final void setAutoCaptured$onfido_capture_sdk_core_release(boolean z) {
        this.isAutoCaptured = z;
    }

    public final void setCaptureData$onfido_capture_sdk_core_release(CaptureStepDataBundle captureDataBundle, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
        setDocumentData$onfido_capture_sdk_core_release(captureDataBundle);
        DocumentType documentType = captureDataBundle.getDocumentType();
        if (documentType == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        setDocumentType$onfido_capture_sdk_core_release(documentType);
        this.countryCode = getDocumentData$onfido_capture_sdk_core_release().getCountryCode();
        this.documentFormat = getDocumentData$onfido_capture_sdk_core_release().getDocumentFormat();
        this.nfcArguments = nfcArguments;
    }

    public final void setCapturedFilesDir$onfido_capture_sdk_core_release(File file) {
        Intrinsics.checkNotNullParameter(file, "<set-?>");
        this.capturedFilesDir = file;
    }

    public final void setCapturedImage$onfido_capture_sdk_core_release(OnfidoImage onfidoImage) {
        this.capturedImage = onfidoImage;
    }

    public final void setCountryCode$onfido_capture_sdk_core_release(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public final void setCroppedImage$onfido_capture_sdk_core_release(byte[] bArr) {
        this.croppedImage = bArr;
    }

    public final void setCurrentCaptureFlowError$onfido_capture_sdk_core_release(ErrorType errorType) {
        this.currentCaptureFlowError = errorType;
    }

    public final void setDocumentCaptureResultConsumer$onfido_capture_sdk_core_release(DocumentCaptureResultConsumer documentCaptureResultConsumer) {
        Intrinsics.checkNotNullParameter(documentCaptureResultConsumer, "<set-?>");
        this.documentCaptureResultConsumer = documentCaptureResultConsumer;
    }

    public final void setDocumentData$onfido_capture_sdk_core_release(CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "<set-?>");
        this.documentData = captureStepDataBundle;
    }

    public final void setDocumentFormat$onfido_capture_sdk_core_release(DocumentFormat documentFormat) {
        this.documentFormat = documentFormat;
    }

    public final void setDocumentSide$onfido_capture_sdk_core_release(DocSide docSide) {
        this.documentSide = docSide;
    }

    public final void setDocumentType$onfido_capture_sdk_core_release(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "<set-?>");
        this.documentType = documentType;
    }

    public final void setDocumentTypeUIModel$onfido_capture_sdk_core_release(DocumentTypeUIModel documentTypeUIModel) {
        Intrinsics.checkNotNullParameter(documentTypeUIModel, "<set-?>");
        this.documentTypeUIModel = documentTypeUIModel;
    }

    public final void setDocumentVideoId$onfido_capture_sdk_core_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.documentVideoId = str;
    }

    public final void setProofOfAddress$onfido_capture_sdk_core_release(boolean z) {
        this.isProofOfAddress = z;
    }

    public final void setUploadBinaryResult$onfido_capture_sdk_core_release(UploadBinaryResult uploadBinaryResult) {
        this.uploadBinaryResult = uploadBinaryResult;
    }

    public final boolean shouldAutoCaptureDocument$onfido_capture_sdk_core_release() {
        return hasNativeLibrary$onfido_capture_sdk_core_release() && shouldAutocapture();
    }

    public final boolean shouldEnableAccessibilityCapture$onfido_capture_sdk_core_release() {
        return this.announcementService.isEnabled() && !(this.rectangleDetector instanceof RectangleDetectorEmpty);
    }

    public final boolean shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release() {
        return this.announcementService.isEnabled() && shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
    }

    public final boolean shouldRecordDocumentVideo$onfido_capture_sdk_core_release() {
        return this.onfidoRemoteConfig.isMultiImageCaptureEnabled();
    }

    public final boolean shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.DRIVING_LICENCE, CountryCode.FR);
    }

    public final boolean shouldShowGermanDLOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.DRIVING_LICENCE, CountryCode.DE);
    }

    public final boolean shouldShowInitialOverlay$onfido_capture_sdk_core_release() {
        return this.documentConfigurationManager.shouldShowInitialOverlay(getDocumentType$onfido_capture_sdk_core_release());
    }

    public final boolean shouldShowItalianIDOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.NATIONAL_IDENTITY_CARD, CountryCode.IT);
    }

    public final boolean shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release(boolean isFrontSide, boolean isOverlayGone) {
        return shouldShowOverlay(isFrontSide, isOverlayGone, DocumentType.NATIONAL_IDENTITY_CARD, CountryCode.ZA);
    }

    public final void startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release(Function0<Boolean> hasValidRecording) {
        Intrinsics.checkNotNullParameter(hasValidRecording, "hasValidRecording");
        SdkConfiguration.DocumentCapture documentCapture = this.onfidoRemoteConfig.getDocumentCapture();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getIo(), null, new DocumentCaptureViewModel$startDocumentVideoRecordingTimer$1(documentCapture.getVideoTimeoutMs(), documentCapture.getTorchTurnOnTimeMs(), documentCapture.getVideoLengthMs(), this, hasValidRecording, null), 2, null);
    }

    public final void startOverlayDisplayTimer$onfido_capture_sdk_core_release() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new DocumentCaptureViewModel$startOverlayDisplayTimer$1(this, null), 2, null);
    }

    public final void trackAutocaptureShutterButtonClick$onfido_capture_sdk_core_release() {
        if (shouldAutoCaptureDocument$onfido_capture_sdk_core_release()) {
            this.documentCaptureTracker.trackCaptureButtonClicked(this.currentCaptureFlowError, this.takenPhotoCount + 1, this.rejectionCount);
        }
    }

    public final void trackCapture$onfido_capture_sdk_core_release(Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        this.documentCaptureTracker.trackDocumentCapture(orientation.isPortrait$onfido_capture_sdk_core_release(), getDocumentData$onfido_capture_sdk_core_release(), shouldAutoCaptureDocument$onfido_capture_sdk_core_release());
    }

    public final void trackCaptureBackButtonClicked$onfido_capture_sdk_core_release() {
        this.documentCaptureTracker.trackCaptureBackButtonClicked$onfido_capture_sdk_core_release(this.takenPhotoCount, this.rejectionCount, this.currentCaptureFlowError);
    }

    public final void trackCaptureError$onfido_capture_sdk_core_release() {
        this.documentCaptureTracker.trackDocumentCaptureError(this.documentSide);
    }

    public final void trackDocumentConfirmationStep$onfido_capture_sdk_core_release() {
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
        this.documentCaptureTracker.trackDocumentConfirmation(getDocumentData$onfido_capture_sdk_core_release(), this.takenPhotoCount, this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries(), this.isAutoCaptured, linkedHashMap);
    }

    public final void trackEndPerformanceEvent$onfido_capture_sdk_core_release() {
        this.documentCaptureTracker.trackEndTracingPerformance();
    }

    public final void trackStartPerformanceEvent$onfido_capture_sdk_core_release() {
        this.documentCaptureTracker.trackStartTracingPerformance();
    }

    public final void trackUploadStarted$onfido_capture_sdk_core_release() {
        NFCOptions nfcOptions;
        DocumentCaptureTracker documentCaptureTracker = this.documentCaptureTracker;
        CaptureStepDataBundle documentData$onfido_capture_sdk_core_release = getDocumentData$onfido_capture_sdk_core_release();
        int i = this.takenPhotoCount;
        int maxTotalRetries = this.onfidoRemoteConfig.getDocumentCapture().getMaxTotalRetries();
        boolean zShouldAutoCaptureDocument$onfido_capture_sdk_core_release = shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
        boolean z = this.isAutoCaptured;
        boolean zIsValid = this.processingResults.getMrzValidationResult().isValid();
        NfcArguments nfcArguments = this.nfcArguments;
        documentCaptureTracker.trackUploadStarted(documentData$onfido_capture_sdk_core_release, i, maxTotalRetries, zShouldAutoCaptureDocument$onfido_capture_sdk_core_release, z, zIsValid, (nfcArguments == null || (nfcOptions = nfcArguments.getNfcOptions()) == null) ? false : NFCOptionsKt.isEnabled(nfcOptions));
    }

    public final void trackVideoCaptureTimeout() {
        this.documentCaptureTracker.trackVideoCaptureTimeout();
    }

    public final void trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release() {
        this.documentCaptureTracker.trackVideoTimeoutRetryButtonClicked();
    }

    public final void trackWaitingScreenCompleted() {
        this.documentCaptureTracker.trackWaitingScreenCompletion((isCheckingImageQuality() ? new LoadingFragment.Companion.DialogMode.CheckingImageQuality(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT) : new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT)).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_DOCUMENT);
    }

    public final void uploadDocumentMedia$onfido_capture_sdk_core_release(List<Validation> validations, byte[] jpegData) {
        Intrinsics.checkNotNullParameter(validations, "validations");
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getIo(), null, new DocumentCaptureViewModel$uploadDocumentMedia$1(this, jpegData, validations, null), 2, null);
    }
}
