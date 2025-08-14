package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.media.AudioManager;
import android.os.ResultReceiver;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.media3.common.MimeTypes;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.config.EnterpriseConfig;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorEmpty;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorEmpty;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorEmpty;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetector;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorEmpty;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorEmpty;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractorGoogle;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorEmpty;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle;
import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsImpl;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.UserAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.DocumentCaptureEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.FaceCaptureEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.FlowExitPublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicAnalyticsEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.utils.EventCache;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultImageAnalyzer;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReader;
import com.onfido.android.sdk.capture.internal.nfc.JMRTDPassportNfcReaderStateful;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcReader;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsNetwork;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.LocalizationUtil;
import com.onfido.android.sdk.capture.utils.OnfidoConfigExtensionsKt;
import com.onfido.android.sdk.capture.utils.TokenExtensionsKt;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetectorFactory;
import com.onfido.android.sdk.capture.utils.mlmodel.RealOnfidoDocumentDetectorFactory;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationType;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository;
import com.onfido.api.client.ErrorParser;
import com.onfido.api.client.ErrorParserImpl;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import com.onfido.javax.inject.Provider;
import com.onfido.javax.inject.Singleton;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u0000\u008c\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0007J3\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0016H\u0001¢\u0006\u0002\b\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0001\u0010$\u001a\u00020\u0003H\u0007J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\r\u0010'\u001a\u00020\fH\u0001¢\u0006\u0002\b(J\u0015\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-J\r\u0010.\u001a\u00020,H\u0001¢\u0006\u0002\b/J\b\u00100\u001a\u000201H\u0007J3\u00102\u001a\u0002032\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00162\f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u0016H\u0001¢\u0006\u0002\b8J3\u00109\u001a\u00020:2\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00162\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u0016H\u0001¢\u0006\u0002\b?J\n\u0010@\u001a\u0004\u0018\u00010\u0007H\u0007J\u001b\u0010A\u001a\b\u0012\u0004\u0012\u00020C0B2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\bDJ\u0013\u0010E\u001a\b\u0012\u0004\u0012\u00020C0FH\u0001¢\u0006\u0002\bGJ\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u001eH\u0007J3\u0010K\u001a\u00020L2\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00162\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u0016H\u0001¢\u0006\u0002\bQJ\u0012\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010\u0004\u001a\u00020\u0005H\u0007J\r\u0010T\u001a\u00020UH\u0001¢\u0006\u0002\bVJ3\u0010W\u001a\u00020X2\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\u00162\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u0016H\u0001¢\u0006\u0002\b]JQ\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010b\u001a\u00020c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010d\u001a\u00020e2\n\b\u0001\u0010f\u001a\u0004\u0018\u00010SH\u0001¢\u0006\u0002\bgJ\u0012\u0010h\u001a\u0004\u0018\u00010S2\u0006\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010i\u001a\u00020\u0005H\u0007J\r\u0010j\u001a\u00020kH\u0001¢\u0006\u0002\blJ\b\u0010m\u001a\u00020nH\u0007J\u001d\u0010o\u001a\u00020p2\u0006\u0010J\u001a\u00020\u001e2\u0006\u0010q\u001a\u00020rH\u0001¢\u0006\u0002\bsJA\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020 2\u0006\u0010b\u001a\u00020c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010d\u001a\u00020e2\n\b\u0001\u0010f\u001a\u0004\u0018\u00010SH\u0001¢\u0006\u0002\byJ\r\u0010z\u001a\u00020eH\u0001¢\u0006\u0002\b{J6\u0010|\u001a\u00020}2\b\b\u0001\u0010\u0002\u001a\u00020\u00032\f\u0010~\u001a\b\u0012\u0004\u0012\u00020\u007f0\u00162\u000e\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u0016H\u0001¢\u0006\u0003\b\u0082\u0001J\u0010\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0001¢\u0006\u0003\b\u0085\u0001J\u000f\u0010\u0086\u0001\u001a\u00020\u0003H\u0001¢\u0006\u0003\b\u0087\u0001JS\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u000f\u0010\u008a\u0001\u001a\n\u0012\u0005\u0012\u00030\u008c\u00010\u008b\u00012\u000f\u0010\u008d\u0001\u001a\n\u0012\u0005\u0012\u00030\u008e\u00010\u008b\u00012\u000f\u0010\u008f\u0001\u001a\n\u0012\u0005\u0012\u00030\u0090\u00010\u008b\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0003\b\u0091\u0001J\u0014\u0010\u0092\u0001\u001a\u00030\u0093\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0094\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkModule;", "", "context", "Landroid/content/Context;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "flowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/FlowConfig;)V", "isInhouseAnalyticsDisabled", "", "enterpriseConfig", "Lcom/onfido/android/sdk/capture/config/EnterpriseConfig;", "provideApplicantUuid", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "provideAudioManager", "Landroid/media/AudioManager;", "provideBarcodeDetector", "Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetector;", "barcodeDetectorGoogle", "Lcom/onfido/dagger/Lazy;", "Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetectorGoogle;", "barcodeDetectorEmpty", "Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetectorEmpty;", "provideBarcodeDetector$onfido_capture_sdk_core_release", "provideBarcodeValidationSuspender", "Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "provideCameraProvider", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "applicationContext", "provideCustomerUserHash", "", "provideEnterpriseConfig", "provideEnterpriseConfig$onfido_capture_sdk_core_release", "provideErrorHandler", "Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;", "errorParser", "Lcom/onfido/api/client/ErrorParser;", "provideErrorHandler$onfido_capture_sdk_core_release", "provideErrorParser", "provideErrorParser$onfido_capture_sdk_core_release", "provideFaceDetectionModel", "Lcom/google/mlkit/vision/face/FaceDetector;", "provideFaceDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "faceDetectorGoogle", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorGoogle;", "faceDetectorEmpty", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectorEmpty;", "provideFaceDetector$onfido_capture_sdk_core_release", "provideFaceOnDocumentDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetector;", "faceOnDocumentDetectorGoogle", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetectorGoogle;", "faceOnDocumentDetectorEmpty", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetectorEmpty;", "provideFaceOnDocumentDetector$onfido_capture_sdk_core_release", "provideFlowConfig", "provideFrameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "provideFrameSampler$onfido_capture_sdk_core_release", "provideImageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "provideImageAnalyzer$onfido_capture_sdk_core_release", "provideLivenessCaptureSettings", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "onfidoRemoteConfig", "provideMRZDetector", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetector;", "mrzDetectorGoogle", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetectorGoogle;", "mrzDetectorEmpty", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetectorEmpty;", "provideMRZDetector$onfido_capture_sdk_core_release", "provideMediaCallback", "Landroid/os/ResultReceiver;", "provideMlKitBarcodeScanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "provideMlKitBarcodeScanner$onfido_capture_sdk_core_release", "provideOnDeviceMRZDocumentExtractor", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "mrzDocumentExtractor", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractorGoogle;", "mrzDocumentExtractorEmpty", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractorEmpty;", "provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release", "provideOnfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "analyticsRepository", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/InhouseAnalyticsRepository;", "eventCache", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/utils/EventCache;", "publicEventMapper", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;", "onfidoAnalyticsEventListener", "provideOnfidoAnalytics$onfido_capture_sdk_core_release", "provideOnfidoAnalyticsEventListener", "provideOnfidoConfig", "provideOnfidoDocumentDetectorFactory", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoDocumentDetectorFactory;", "provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release", "provideOnfidoToken", "Lcom/onfido/api/client/token/Token;", "providePassportNfcReader", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcReader;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "providePassportNfcReader$onfido_capture_sdk_core_release", "providePerformanceAnalyticsNetwork", "Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsNetwork;", "performanceAnalyticsRepository", "Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsRepository;", "schedulers", "providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_release", "providePublicEventMapper", "providePublicEventMapper$onfido_capture_sdk_core_release", "provideRectangleDetector", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetector;", "rectangleDetectorGoogle", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetectorGoogle;", "rectangleDetectorEmpty", "Lcom/onfido/android/sdk/capture/detector/rectangle/RectangleDetectorEmpty;", "provideRectangleDetector$onfido_capture_sdk_core_release", "provideRetainableValidationsResult", "Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "provideRetainableValidationsResult$onfido_capture_sdk_core_release", "provideSdkContext", "provideSdkContext$onfido_capture_sdk_core_release", "provideSupportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "supportedDocumentsRepository", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/OnfidoSupportedDocumentsRepository;", "workflowSupportedDocumentsRepository", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsRepository;", "remoteSupportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/document/supported/data/repository/RemoteSupportedDocumentsRepository;", "provideSupportedDocumentsRepository$onfido_capture_sdk_core_release", "provideTelephonyManager", "Landroid/telephony/TelephonyManager;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SdkModule {
    private final Context context;
    private final FlowConfig flowConfig;
    private final OnfidoConfig onfidoConfig;

    public SdkModule(Context context, OnfidoConfig onfidoConfig, FlowConfig flowConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        this.context = context;
        this.onfidoConfig = onfidoConfig;
        this.flowConfig = flowConfig;
    }

    private final boolean isInhouseAnalyticsDisabled(OnfidoConfig onfidoConfig, EnterpriseConfig enterpriseConfig) {
        if (TokenExtensionsKt.mapToInternalToken(onfidoConfig.getToken()).getIsDemoToken()) {
            return true;
        }
        EnterpriseFeatures enterpriseFeatures = enterpriseConfig.getEnterpriseFeatures();
        return enterpriseFeatures != null && enterpriseFeatures.getDisableMobileSdkAnalytics();
    }

    @Provides
    public final ApplicantId provideApplicantUuid(OnfidoTokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        return tokenProvider.provideToken().getApplicantId();
    }

    @Provides
    public final AudioManager provideAudioManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        return (AudioManager) systemService;
    }

    @Provides
    public final BarcodeDetector provideBarcodeDetector$onfido_capture_sdk_core_release(Context context, Lazy<BarcodeDetectorGoogle> barcodeDetectorGoogle, Lazy<BarcodeDetectorEmpty> barcodeDetectorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcodeDetectorGoogle, "barcodeDetectorGoogle");
        Intrinsics.checkNotNullParameter(barcodeDetectorEmpty, "barcodeDetectorEmpty");
        BarcodeDetector barcodeDetector = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? barcodeDetectorGoogle.get() : barcodeDetectorEmpty.get();
        Intrinsics.checkNotNull(barcodeDetector);
        return barcodeDetector;
    }

    @Provides
    @Singleton
    public final BarcodeValidationSuspender provideBarcodeValidationSuspender(OnfidoRemoteConfig remoteConfig, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        return new BarcodeValidationSuspender(remoteConfig, schedulersProvider);
    }

    @Provides
    public final ListenableFuture<ProcessCameraProvider> provideCameraProvider(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(applicationContext);
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(...)");
        return processCameraProvider;
    }

    @Provides
    public final String provideCustomerUserHash(OnfidoTokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        return tokenProvider.provideToken().getCustomerUserHash();
    }

    @Provides
    public final EnterpriseConfig provideEnterpriseConfig$onfido_capture_sdk_core_release() {
        return EnterpriseConfig.INSTANCE;
    }

    @Provides
    public final ErrorHandler provideErrorHandler$onfido_capture_sdk_core_release(ErrorParser errorParser) {
        Intrinsics.checkNotNullParameter(errorParser, "errorParser");
        return new ErrorHandler(errorParser);
    }

    @Provides
    public final ErrorParser provideErrorParser$onfido_capture_sdk_core_release() {
        return ErrorParserImpl.Companion.newInstance$default(ErrorParserImpl.INSTANCE, null, 1, null);
    }

    @Provides
    public final FaceDetector provideFaceDetectionModel() {
        FaceDetector client = FaceDetection.getClient();
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        return client;
    }

    @Provides
    public final com.onfido.android.sdk.capture.detector.face.FaceDetector provideFaceDetector$onfido_capture_sdk_core_release(Context context, Lazy<FaceDetectorGoogle> faceDetectorGoogle, Lazy<FaceDetectorEmpty> faceDetectorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(faceDetectorGoogle, "faceDetectorGoogle");
        Intrinsics.checkNotNullParameter(faceDetectorEmpty, "faceDetectorEmpty");
        com.onfido.android.sdk.capture.detector.face.FaceDetector faceDetector = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? faceDetectorGoogle.get() : faceDetectorEmpty.get();
        Intrinsics.checkNotNull(faceDetector);
        return faceDetector;
    }

    @Provides
    @Singleton
    public final FaceOnDocumentDetector provideFaceOnDocumentDetector$onfido_capture_sdk_core_release(Context context, Lazy<FaceOnDocumentDetectorGoogle> faceOnDocumentDetectorGoogle, Lazy<FaceOnDocumentDetectorEmpty> faceOnDocumentDetectorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(faceOnDocumentDetectorGoogle, "faceOnDocumentDetectorGoogle");
        Intrinsics.checkNotNullParameter(faceOnDocumentDetectorEmpty, "faceOnDocumentDetectorEmpty");
        FaceOnDocumentDetector faceOnDocumentDetector = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? faceOnDocumentDetectorGoogle.get() : faceOnDocumentDetectorEmpty.get();
        Intrinsics.checkNotNull(faceOnDocumentDetector);
        return faceOnDocumentDetector;
    }

    @Provides
    /* renamed from: provideFlowConfig, reason: from getter */
    public final FlowConfig getFlowConfig() {
        return this.flowConfig;
    }

    @Provides
    public final FrameSampler<OnfidoImage> provideFrameSampler$onfido_capture_sdk_core_release(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        return new DefaultFrameSampler(schedulersProvider);
    }

    @Provides
    public final ImageAnalyzer<OnfidoImage> provideImageAnalyzer$onfido_capture_sdk_core_release() {
        return new DefaultImageAnalyzer();
    }

    @Provides
    public final SdkConfiguration.LivenessCapture provideLivenessCaptureSettings(OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        return onfidoRemoteConfig.getLivenessCapture();
    }

    @Provides
    public final MRZDetector provideMRZDetector$onfido_capture_sdk_core_release(Context context, Lazy<MRZDetectorGoogle> mrzDetectorGoogle, Lazy<MRZDetectorEmpty> mrzDetectorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mrzDetectorGoogle, "mrzDetectorGoogle");
        Intrinsics.checkNotNullParameter(mrzDetectorEmpty, "mrzDetectorEmpty");
        MRZDetector mRZDetector = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? mrzDetectorGoogle.get() : mrzDetectorEmpty.get();
        Intrinsics.checkNotNull(mRZDetector);
        return mRZDetector;
    }

    @Provides
    public final ResultReceiver provideMediaCallback(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        return onfidoConfig.getMediaCallback();
    }

    @Provides
    public final BarcodeScanner provideMlKitBarcodeScanner$onfido_capture_sdk_core_release() {
        BarcodeScannerOptions barcodeScannerOptionsBuild = new BarcodeScannerOptions.Builder().setBarcodeFormats(2048, new int[0]).build();
        Intrinsics.checkNotNullExpressionValue(barcodeScannerOptionsBuild, "build(...)");
        BarcodeScanner client = BarcodeScanning.getClient(barcodeScannerOptionsBuild);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        return client;
    }

    @Provides
    public final MRZDocumentExtractor provideOnDeviceMRZDocumentExtractor$onfido_capture_sdk_core_release(Context context, Lazy<MRZDocumentExtractorGoogle> mrzDocumentExtractor, Lazy<MRZDocumentExtractorEmpty> mrzDocumentExtractorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mrzDocumentExtractor, "mrzDocumentExtractor");
        Intrinsics.checkNotNullParameter(mrzDocumentExtractorEmpty, "mrzDocumentExtractorEmpty");
        MRZDocumentExtractor mRZDocumentExtractor = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? mrzDocumentExtractor.get() : mrzDocumentExtractorEmpty.get();
        Intrinsics.checkNotNull(mRZDocumentExtractor);
        return mRZDocumentExtractor;
    }

    @Provides
    @Singleton
    public final OnfidoAnalytics provideOnfidoAnalytics$onfido_capture_sdk_core_release(InhouseAnalyticsRepository analyticsRepository, OnfidoConfig onfidoConfig, SchedulersProvider schedulersProvider, EventCache eventCache, EnterpriseConfig enterpriseConfig, OnfidoRemoteConfig remoteConfig, PublicEventMapper publicEventMapper, ResultReceiver onfidoAnalyticsEventListener) {
        Intrinsics.checkNotNullParameter(analyticsRepository, "analyticsRepository");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(eventCache, "eventCache");
        Intrinsics.checkNotNullParameter(enterpriseConfig, "enterpriseConfig");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(publicEventMapper, "publicEventMapper");
        return isInhouseAnalyticsDisabled(onfidoConfig, enterpriseConfig) ? new UserAnalytics(publicEventMapper, onfidoAnalyticsEventListener) : new OnfidoAnalyticsImpl(analyticsRepository, schedulersProvider, eventCache, remoteConfig, publicEventMapper, onfidoAnalyticsEventListener);
    }

    @Provides
    public final ResultReceiver provideOnfidoAnalyticsEventListener(OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        return onfidoConfig.getOnfidoAnalyticsEventListener();
    }

    @Provides
    /* renamed from: provideOnfidoConfig, reason: from getter */
    public final OnfidoConfig getOnfidoConfig() {
        return this.onfidoConfig;
    }

    @Provides
    public final OnfidoDocumentDetectorFactory provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release() {
        return new RealOnfidoDocumentDetectorFactory();
    }

    @Provides
    public final Token provideOnfidoToken() {
        return this.onfidoConfig.getToken();
    }

    @Provides
    public final PassportNfcReader providePassportNfcReader$onfido_capture_sdk_core_release(OnfidoRemoteConfig onfidoRemoteConfig, NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        return onfidoRemoteConfig.getDocumentCapture().isStatefulNfcReaderEnabled() ? new JMRTDPassportNfcReaderStateful(JMRTDPassportNfcReaderStateful.JMRTDHelperImpl.INSTANCE, onfidoRemoteConfig.getDocumentCapture().getNfc().getNfcScanTagTimeoutMs(), nfcTracker) : new JMRTDPassportNfcReader(onfidoRemoteConfig.getDocumentCapture().getNfc().getNfcScanTagTimeoutMs(), nfcTracker);
    }

    @Provides
    public final PerformanceAnalyticsNetwork providePerformanceAnalyticsNetwork$onfido_capture_sdk_core_release(PerformanceAnalyticsRepository performanceAnalyticsRepository, SchedulersProvider schedulers, EventCache eventCache, OnfidoRemoteConfig remoteConfig, PublicEventMapper publicEventMapper, ResultReceiver onfidoAnalyticsEventListener) {
        Intrinsics.checkNotNullParameter(performanceAnalyticsRepository, "performanceAnalyticsRepository");
        Intrinsics.checkNotNullParameter(schedulers, "schedulers");
        Intrinsics.checkNotNullParameter(eventCache, "eventCache");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(publicEventMapper, "publicEventMapper");
        return new PerformanceAnalyticsNetwork(new OnfidoAnalyticsImpl(performanceAnalyticsRepository, schedulers, eventCache, remoteConfig, publicEventMapper, onfidoAnalyticsEventListener));
    }

    @Provides
    @Singleton
    public final PublicEventMapper providePublicEventMapper$onfido_capture_sdk_core_release() {
        List<? extends Pair<String, ? extends PublicAnalyticsEventMapper>> listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("DOCUMENT_CAPTURE", new DocumentCaptureEventMapper()), TuplesKt.to(EventNames.Document.DOCUMENT_CONFIRMATION, new DocumentCaptureEventMapper()), TuplesKt.to(EventNames.Face.FACE_SELFIE_CAPTURE, new FaceCaptureEventMapper()), TuplesKt.to(EventNames.Face.FACE_VIDEO_CAPTURE, new FaceCaptureEventMapper()), TuplesKt.to(EventNames.Face.FACE_SELFIE_CONFIRMATION, new FaceCaptureEventMapper()), TuplesKt.to(EventNames.Face.FACE_VIDEO_CONFIRMATION, new FaceCaptureEventMapper()), TuplesKt.to(EventNames.Flow.USER_EXIT_TAPPED_ALERT_CONFIRM, new FlowExitPublicEventMapper())});
        PublicEventMapper publicEventMapper = new PublicEventMapper();
        publicEventMapper.addMappers(listListOf);
        return publicEventMapper;
    }

    @Provides
    public final RectangleDetector provideRectangleDetector$onfido_capture_sdk_core_release(Context context, Lazy<RectangleDetectorGoogle> rectangleDetectorGoogle, Lazy<RectangleDetectorEmpty> rectangleDetectorEmpty) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(rectangleDetectorGoogle, "rectangleDetectorGoogle");
        Intrinsics.checkNotNullParameter(rectangleDetectorEmpty, "rectangleDetectorEmpty");
        RectangleDetector rectangleDetector = ContextUtilsKt.isGooglePlayServiceAvailable(context) ? rectangleDetectorGoogle.get() : rectangleDetectorEmpty.get();
        Intrinsics.checkNotNull(rectangleDetector);
        return rectangleDetector;
    }

    @Provides
    @Singleton
    public final RetainableValidationsResult provideRetainableValidationsResult$onfido_capture_sdk_core_release() {
        return new RetainableValidationsResult(SetsKt.setOf(OnDeviceValidationType.PDF_417_BARCODE_DETECTION));
    }

    @Provides
    @Singleton
    public final Context provideSdkContext$onfido_capture_sdk_core_release() {
        Context contextApplyLanguage;
        Locale locale = this.onfidoConfig.getLocale();
        return (locale == null || (contextApplyLanguage = LocalizationUtil.INSTANCE.applyLanguage(this.context, locale)) == null) ? this.context : contextApplyLanguage;
    }

    @Provides
    public final SupportedDocumentsRepository provideSupportedDocumentsRepository$onfido_capture_sdk_core_release(Provider<OnfidoSupportedDocumentsRepository> supportedDocumentsRepository, Provider<WorkflowSupportedDocumentsRepository> workflowSupportedDocumentsRepository, Provider<RemoteSupportedDocumentsRepository> remoteSupportedDocumentsRepository, OnfidoRemoteConfig remoteConfig, OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(supportedDocumentsRepository, "supportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(workflowSupportedDocumentsRepository, "workflowSupportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(remoteSupportedDocumentsRepository, "remoteSupportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        SupportedDocumentsRepository supportedDocumentsRepository2 = OnfidoConfigExtensionsKt.inWorkflowMode(onfidoConfig) ? workflowSupportedDocumentsRepository.get() : (OnfidoConfigExtensionsKt.hasPreselectedDocuments(onfidoConfig) || OnfidoConfigExtensionsKt.hasPreselectedGenericDocuments(onfidoConfig) || !remoteConfig.isDocumentSupportRulesEnabled()) ? supportedDocumentsRepository.get() : remoteSupportedDocumentsRepository.get();
        Intrinsics.checkNotNull(supportedDocumentsRepository2);
        return supportedDocumentsRepository2;
    }

    @Provides
    public final TelephonyManager provideTelephonyManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        return (TelephonyManager) systemService;
    }
}
