package com.onfido.android.sdk.capture.ui;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.flow.FlowValidation;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.NavigationManager;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.navigation.screens.DocumentSelectionScreen;
import com.onfido.android.sdk.capture.internal.navigation.screens.EmptyScreen;
import com.onfido.android.sdk.capture.internal.navigation.screens.WelcomeScreen;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.ShouldLaunchNfcFlowUseCase;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.ui.OnfidoPresenterInitializer;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraException;
import com.onfido.android.sdk.capture.ui.camera.exception.InvalidCertificateException;
import com.onfido.android.sdk.capture.ui.camera.exception.TokenExpiredException;
import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureVariant;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.options.BaseOptions;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenOptions;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.MessageScreenOptions;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.ui.options.PhotoCaptureVariantOptions;
import com.onfido.android.sdk.capture.ui.options.VideoCaptureVariantOptions;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.upload.Document;
import com.onfido.android.sdk.capture.upload.DocumentSide;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.upload.Face;
import com.onfido.android.sdk.capture.upload.ProofOfAddress;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.DeviceUtils;
import com.onfido.android.sdk.capture.utils.ListExtensionsKt;
import com.onfido.android.sdk.capture.utils.OnfidoConfigExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.Visibility;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import com.onfido.hosted.web.module.model.ClassicModuleInfo;
import com.onfido.hosted.web.module.model.HostedWebModuleCancelled;
import com.onfido.hosted.web.module.model.HostedWebModuleExit;
import com.onfido.hosted.web.module.model.HostedWebModuleFailed;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import com.onfido.hosted.web.module.model.HostedWebModuleResult;
import com.onfido.hosted.web.module.model.HostedWebModuleSuccess;
import com.onfido.hosted.web.module.model.ProofOfAddressCaptureSDKOutput;
import com.onfido.workflow.internal.ui.processor.biometric.token.BiometricTokenStorageFlowProcessor;
import com.uxcam.screenaction.models.KeyConstant;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.SentryEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000ì\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0010\u0018\u00002\u00020\u0001:\u0006\u0090\u0002\u0091\u0002\u0092\u0002B\u0089\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\b\b\u0001\u0010 \u001a\u00020!¢\u0006\u0002\u0010\"J\b\u0010T\u001a\u00020UH\u0012J\r\u0010V\u001a\u00020UH\u0010¢\u0006\u0002\bWJ\u001c\u0010X\u001a\u00020$2\b\u00101\u001a\u0004\u0018\u0001022\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\u0012\u0010[\u001a\u00020U2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\u0012\u0010^\u001a\u0004\u0018\u00010.2\u0006\u0010_\u001a\u00020*H\u0012J\u0010\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020cH\u0012J\b\u0010d\u001a\u00020eH\u0012J!\u0010f\u001a\u00020U2\b\b\u0002\u0010g\u001a\u00020$2\b\b\u0002\u0010h\u001a\u00020$H\u0010¢\u0006\u0002\biJ\u0010\u0010j\u001a\u00020U2\u0006\u0010k\u001a\u00020$H\u0012J\u0010\u0010l\u001a\u00020U2\u0006\u0010k\u001a\u00020$H\u0012J\u001c\u0010m\u001a\u00020U2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020p\u0012\u0004\u0012\u00020U0oH\u0012J\b\u0010q\u001a\u00020$H\u0012J\b\u0010r\u001a\u00020$H\u0012J\b\u0010s\u001a\u00020$H\u0012J\u0012\u0010t\u001a\u00020U2\b\u00101\u001a\u0004\u0018\u000102H\u0012J \u0010u\u001a\u00020U2\u0006\u0010v\u001a\u00020.2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\"\u0010y\u001a\u00020U2\b\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\u0018\u0010|\u001a\u00020U2\u0006\u0010}\u001a\u00020~2\u0006\u0010w\u001a\u00020xH\u0012J\u0012\u0010\u007f\u001a\u00020$2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0012J\t\u0010\u0082\u0001\u001a\u00020UH\u0016J\u0019\u0010\u0083\u0001\u001a\u00020U2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0010¢\u0006\u0003\b\u0086\u0001J\u000f\u0010\u0087\u0001\u001a\u00020UH\u0010¢\u0006\u0003\b\u0088\u0001J\u0018\u0010\u0089\u0001\u001a\u00020U2\r\u0010\u008a\u0001\u001a\b0\u008b\u0001j\u0003`\u008c\u0001H\u0012J\u0018\u0010\u008d\u0001\u001a\u00020U2\u0007\u0010\u008e\u0001\u001a\u00020aH\u0010¢\u0006\u0003\b\u008f\u0001J\u001d\u0010\u0090\u0001\u001a\u00020U2\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\u000f\u0010\u0095\u0001\u001a\u00020UH\u0010¢\u0006\u0003\b\u0096\u0001J\u0013\u0010\u0097\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0016J#\u0010\u009a\u0001\u001a\u00020U2\b\u0010\u0080\u0001\u001a\u00030\u009b\u00012\b\u0010A\u001a\u0004\u0018\u00010BH\u0010¢\u0006\u0003\b\u009c\u0001J6\u0010\u009d\u0001\u001a\u00020U2\u0007\u0010\u009e\u0001\u001a\u0002022\u0006\u0010'\u001a\u00020(2\n\u0010\u009f\u0001\u001a\u0005\u0018\u00010 \u00012\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0010¢\u0006\u0003\b¡\u0001J\u0013\u0010¢\u0001\u001a\u00020U2\b\u00101\u001a\u0004\u0018\u000102H\u0012J\u0013\u0010£\u0001\u001a\u00020U2\b\u0010¤\u0001\u001a\u00030 \u0001H\u0016J\u0013\u0010¥\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030¦\u0001H\u0016J\t\u0010§\u0001\u001a\u00020UH\u0012J\u000f\u0010¨\u0001\u001a\u00020UH\u0010¢\u0006\u0003\b©\u0001J\u000f\u0010ª\u0001\u001a\u00020UH\u0010¢\u0006\u0003\b«\u0001J\u0013\u0010¬\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030\u00ad\u0001H\u0016J\u0019\u0010®\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030¯\u0001H\u0010¢\u0006\u0003\b°\u0001J\u0019\u0010±\u0001\u001a\u00020U2\b\u0010²\u0001\u001a\u00030³\u0001H\u0010¢\u0006\u0003\b´\u0001J3\u0010µ\u0001\u001a\u00020U2\f\b\u0002\u0010¶\u0001\u001a\u0005\u0018\u00010·\u00012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\b\u0010¸\u0001\u001a\u00030·\u0001H\u0010¢\u0006\u0003\b¹\u0001J\u0019\u0010º\u0001\u001a\u00020U2\b\u0010»\u0001\u001a\u00030¼\u0001H\u0010¢\u0006\u0003\b½\u0001J\u001d\u0010¾\u0001\u001a\u00020U2\b\u0010¿\u0001\u001a\u00030À\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0012J\u0019\u0010Á\u0001\u001a\u00020U2\b\u0010Â\u0001\u001a\u00030Ã\u0001H\u0010¢\u0006\u0003\bÄ\u0001J\t\u0010Å\u0001\u001a\u00020UH\u0012J\t\u0010Æ\u0001\u001a\u00020UH\u0012J\u0012\u0010Ç\u0001\u001a\u00020U2\u0007\u0010È\u0001\u001a\u00020aH\u0012J\u0019\u0010É\u0001\u001a\u00020U2\b\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0010¢\u0006\u0003\bÌ\u0001J\u0019\u0010Í\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030Î\u0001H\u0010¢\u0006\u0003\bÏ\u0001J\u0013\u0010Ð\u0001\u001a\u00020U2\b\u0010Ñ\u0001\u001a\u00030Ò\u0001H\u0016J\u0019\u0010Ó\u0001\u001a\u00020U2\b\u0010Ô\u0001\u001a\u00030Õ\u0001H\u0010¢\u0006\u0003\bÖ\u0001J\u0013\u0010×\u0001\u001a\u00020U2\b\u0010²\u0001\u001a\u00030³\u0001H\u0012J\u0019\u0010Ø\u0001\u001a\u00020U2\b\u0010Ù\u0001\u001a\u00030Ú\u0001H\u0010¢\u0006\u0003\bÛ\u0001J\t\u0010Ü\u0001\u001a\u00020UH\u0016J\u0019\u0010Ý\u0001\u001a\u00020U2\b\u0010\u0098\u0001\u001a\u00030Þ\u0001H\u0010¢\u0006\u0003\bß\u0001J$\u0010à\u0001\u001a\u00020U2\u0007\u0010á\u0001\u001a\u00020$2\u0007\u0010â\u0001\u001a\u00020a2\u0007\u0010ã\u0001\u001a\u00020eH\u0012J\"\u0010ä\u0001\u001a\u00020U2\r\u0010å\u0001\u001a\b\u0012\u0004\u0012\u00020.062\b\u0010b\u001a\u0004\u0018\u00010{H\u0012J&\u0010æ\u0001\u001a\b\u0012\u0004\u0012\u00020.062\u0006\u0010k\u001a\u00020$2\r\u00105\u001a\t\u0012\u0004\u0012\u00020.0ç\u0001H\u0012J\"\u0010è\u0001\u001a\u00020U2\r\u0010å\u0001\u001a\b\u0012\u0004\u0012\u00020.062\b\u0010b\u001a\u0004\u0018\u00010{H\u0012J\"\u0010é\u0001\u001a\u00020U2\r\u0010å\u0001\u001a\b\u0012\u0004\u0012\u00020.062\b\u0010z\u001a\u0004\u0018\u00010{H\u0012J\u000f\u0010ê\u0001\u001a\u00020UH\u0010¢\u0006\u0003\bë\u0001J\u0010\u0010ì\u0001\u001a\t\u0012\u0004\u0012\u00020.0ç\u0001H\u0012J\u000f\u0010í\u0001\u001a\u00020UH\u0010¢\u0006\u0003\bî\u0001J\t\u0010ï\u0001\u001a\u00020UH\u0012J\u0011\u0010ð\u0001\u001a\u00020U2\u0006\u0010_\u001a\u00020*H\u0012J\u0012\u0010ñ\u0001\u001a\u00020U2\u0007\u0010ò\u0001\u001a\u000204H\u0012J\u001d\u0010ó\u0001\u001a\u00020U2\b\u0010ô\u0001\u001a\u00030 \u00012\b\u0010õ\u0001\u001a\u00030ö\u0001H\u0016J#\u0010÷\u0001\u001a\u00020U2\u0006\u0010R\u001a\u00020S2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010JH\u0010¢\u0006\u0003\bø\u0001J\t\u0010ù\u0001\u001a\u00020$H\u0012J\u0013\u0010ú\u0001\u001a\u00020$2\b\u0010b\u001a\u0004\u0018\u00010{H\u0012J\u0013\u0010û\u0001\u001a\u00020$2\b\u0010b\u001a\u0004\u0018\u00010{H\u0012J+\u0010ü\u0001\u001a\u00020U2\u0007\u0010á\u0001\u001a\u00020$2\u0007\u0010â\u0001\u001a\u00020a2\b\u0010ý\u0001\u001a\u00030·\u0001H\u0010¢\u0006\u0003\bþ\u0001J#\u0010ÿ\u0001\u001a\u00020U2\b\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J!\u0010\u0080\u0002\u001a\u00020U2\u0006\u0010b\u001a\u00020c2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\u0019\u0010\u0081\u0002\u001a\u00020U2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\t\u0010\u0082\u0002\u001a\u00020UH\u0012J\u0019\u0010\u0083\u0002\u001a\u00020U2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\u0019\u0010\u0084\u0002\u001a\u00020U2\u0006\u0010w\u001a\u00020x2\u0006\u0010-\u001a\u00020.H\u0012J\u0011\u0010\u0085\u0002\u001a\u00020U2\u0006\u0010w\u001a\u00020xH\u0012J\u001b\u0010\u0086\u0002\u001a\u00020U2\b\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010w\u001a\u00020xH\u0012J\u0019\u0010\u0087\u0002\u001a\u00020U2\u0006\u0010v\u001a\u00020.2\u0006\u0010w\u001a\u00020xH\u0012J\u001b\u0010\u0088\u0002\u001a\u00020U2\b\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010w\u001a\u00020xH\u0012J\u001a\u0010\u0089\u0002\u001a\u00020U2\u0007\u0010â\u0001\u001a\u00020a2\u0006\u0010w\u001a\u00020xH\u0012J\u0019\u0010\u008a\u0002\u001a\u00020U2\u0006\u0010z\u001a\u00020{2\u0006\u0010w\u001a\u00020xH\u0012J\t\u0010\u008b\u0002\u001a\u00020UH\u0012J\t\u0010\u008c\u0002\u001a\u00020UH\u0012J\u0011\u0010\u008d\u0002\u001a\u00020U2\u0006\u0010h\u001a\u00020$H\u0012J\u000f\u0010\u008e\u0002\u001a\u00020$H\u0010¢\u0006\u0003\b\u008f\u0002R\u000e\u0010#\u001a\u00020$X\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0092\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0092\u000e¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\u00020*8RX\u0092\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020.8RX\u0092\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u000e\u0010\u0018\u001a\u00020\u0019X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0092\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0092\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020.06X\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u000208X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020<X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u000e\u0010\u0010\u001a\u00020\u0011X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0092\u0004¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u000e\u0010E\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0092\u0004¢\u0006\u0002\n\u0000R$\u0010K\u001a\u00020J2\u0006\u0010I\u001a\u00020J8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u000e\u0010P\u001a\u00020QX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0093\u0002"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter;", "Lcom/onfido/android/sdk/capture/ui/NfcDataServiceListener;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "screenLoadTracker", "Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "runtimePermissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "onfidoPresenterInitializer", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenterInitializer;", "nfcDataRepository", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcDataRepository;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "remoteLoggerTree", "Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;", "deviceUtils", "Lcom/onfido/android/sdk/capture/utils/DeviceUtils;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "shouldLaunchNfcFlowUseCase", "Lcom/onfido/android/sdk/capture/internal/nfc/ShouldLaunchNfcFlowUseCase;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/android/sdk/capture/ui/OnfidoPresenterInitializer;Lcom/onfido/android/sdk/capture/ui/nfc/NfcDataRepository;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;Lcom/onfido/android/sdk/capture/utils/DeviceUtils;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/internal/nfc/ShouldLaunchNfcFlowUseCase;Lcom/onfido/android/sdk/capture/OnfidoConfig;)V", "awaitingPermissions", "", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "currentAction", "Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "getCurrentAction", "()Lcom/onfido/android/sdk/capture/ui/options/FlowAction;", "currentStep", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "getCurrentStep", "()Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "flowIndex", "", "flowSteps", "", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "getNavigator", "()Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "nfcDataService", "Lcom/onfido/android/sdk/capture/ui/NfcDataService;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "getOnfidoConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/OnfidoConfig;", "onfidoNavigation", "Lcom/onfido/android/sdk/capture/internal/navigation/OnfidoNavigation;", "orchestrationUserConsentVisibility", "Lcom/onfido/android/sdk/capture/utils/Visibility;", "value", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$State;", "state", "getState$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$State;", "setState$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$State;)V", "uploadResult", "Lcom/onfido/android/sdk/capture/upload/Captures;", "view", "Lcom/onfido/android/sdk/capture/ui/OnfidoView;", "addLoggerTree", "", "appendNfcFeature", "appendNfcFeature$onfido_capture_sdk_core_release", "backSideCaptureNeeded", "genericDocumentPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "cleanFiles", "directory", "Ljava/io/File;", "findFlowStep", "flowAction", "getCaptureBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/CaptureScreenOptions;", "getNfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "initFlow", "isRestoringState", "isSystemDarkModeEnabled", "initFlow$onfido_capture_sdk_core_release", "initFlowSteps", "shouldAskForConsent", "initOrchestrationFlow", "initializeSdk", Constants.KEY_ACTION, "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenterInitializer$InitializerResult$Success;", "isDeviceNotSupported", "isOnlyOneDocAvailable", "isVideoAvailableOnConfirmationScreen", "launchFaceCapture", "moveTo", AnalyticsPropertyKeys.STEP, "flowStepDirection", "Lcom/onfido/android/sdk/capture/flow/FlowStepDirection;", "moveToDocumentCaptureOrPermissions", "baseOptions", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "navigateTo", KeyConstant.KEY_SCREEN, "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "needToCaptureBackOfDocument", "documentResult", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Completed;", "nextAction", "onAvcHostResult", "avcHostResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "onAvcHostResult$onfido_capture_sdk_core_release", "onBackPressed", "onBackPressed$onfido_capture_sdk_core_release", "onCaptureException", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onCaptureWithoutPermissions", "captureData", "onCaptureWithoutPermissions$onfido_capture_sdk_core_release", "onDataUploaded", "documentMediaUpload", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "onDocumentCaptureBackPressed", "onDocumentCaptureBackPressed$onfido_capture_sdk_core_release", "onDocumentCaptureResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "onDocumentCaptured", "Lcom/onfido/android/sdk/capture/upload/DocumentSide;", "onDocumentCaptured$onfido_capture_sdk_core_release", "onDocumentTypeSelected", "docType", "genericDocumentTitle", "", "onDocumentTypeSelected$onfido_capture_sdk_core_release", "onFaceCapturePermissionGranted", "onFaceCaptured", "faceId", "onFaceSelfieCaptureResult", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "onFaceVideoPermissionGranted", "onFlowCompleted", "onFlowCompleted$onfido_capture_sdk_core_release", "onFlowExited", "onFlowExited$onfido_capture_sdk_core_release", "onLivenessCaptureResult", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "onLivenessConfirmationResultReceived", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment$LivenessConfirmationResult;", "onLivenessConfirmationResultReceived$onfido_capture_sdk_core_release", "onMotionCaptured", "videoResult", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "onMotionCaptured$onfido_capture_sdk_core_release", "onNavigationStarted", "origin", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", FirebaseAnalytics.Param.DESTINATION, "onNavigationStarted$onfido_capture_sdk_core_release", "onNfcHostResult", "nfcHostResult", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "onNfcHostResult$onfido_capture_sdk_core_release", "onNfcScanSucceeded", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "onPermissionResult", "permissionResult", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "onPermissionResult$onfido_capture_sdk_core_release", "onPermissionScreenBackPressedAfterDocSelection", "onPermissionScreenDismissed", "onPermissionsGranted", "captureDataBundle", "onPoaResult", "poaResult", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment$PoaResult;", "onPoaResult$onfido_capture_sdk_core_release", "onRestrictedDocumentSelectionResult", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", "onRestrictedDocumentSelectionResult$onfido_capture_sdk_core_release", "onUploadError", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onUserConsentResult", "userConsentResult", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment$UserConsentResult;", "onUserConsentResult$onfido_capture_sdk_core_release", "onVideoCaptured", "onViewStarted", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/NavigationManager;", "onViewStarted$onfido_capture_sdk_core_release", "onViewStopped", "onWebPoaResult", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "onWebPoaResult$onfido_capture_sdk_core_release", "openNewDocumentCapture", "isFrontSide", "captureBundle", "nfcArguments", "prepareFaceSteps", "modifiedSteps", "prepareFlowSteps", "", "prepareLivenessSteps", "prepareMotionSteps", "previousAction", "previousAction$onfido_capture_sdk_core_release", "previousSteps", "removeLoggerTree", "removeLoggerTree$onfido_capture_sdk_core_release", "removeNfcStep", "removeStep", "setActionWithIndex", "newIndex", "setLivenessVideoOptions", "videoPath", "livenessPerformedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "setup", "setup$onfido_capture_sdk_core_release", "shouldShowDocumentSelection", "shouldShowFaceCaptureIntro", "shouldShowLivenessIntroVideo", "showCaptureStep", "currentScreen", "showCaptureStep$onfido_capture_sdk_core_release", "showDocumentCapture", "showDocumentCaptureOrPermissionsScreen", "showDocumentTypeSelection", "showEmptyScreen", "showFaceSelfieCapture", "showFaceSelfieIntro", "showLivenessCapture", "showLivenessCaptureConfirmation", "showLivenessIntro", "showMessageScreen", "showPermissionsScreen", "showWelcomeScreen", "skipNfcScanStep", "startFlow", "trackFlowInitiatedEvents", "useLocalBackNavigation", "useLocalBackNavigation$onfido_capture_sdk_core_release", "Factory", "PresenterAssistedFactory", "State", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnfidoPresenter implements NfcDataServiceListener {
    private boolean awaitingPermissions;
    private final CompositeDisposable compositeDisposable;
    private CountryCode countryCode;
    private final DeviceUtils deviceUtils;
    private final DocumentConfigurationManager documentConfigurationManager;
    private DocumentType documentType;
    private int flowIndex;
    private List<FlowStep> flowSteps;
    private final FlowTracker flowTracker;
    private final LivenessTracker livenessTracker;
    private final NavigationManagerHolder navigationManagerHolder;
    private final Navigator navigator;
    private final NfcDataRepository nfcDataRepository;
    private NfcDataService nfcDataService;
    private final NfcInteractor nfcInteractor;
    private NfcProperties nfcProperties;
    private final NfcTracker nfcTracker;
    private final OnfidoConfig onfidoConfig;
    private final OnfidoNavigation onfidoNavigation;
    private final OnfidoPresenterInitializer onfidoPresenterInitializer;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private Visibility orchestrationUserConsentVisibility;
    private final RemoteLoggerTree remoteLoggerTree;
    private final RuntimePermissionsManager runtimePermissionsManager;
    private final SchedulersProvider schedulersProvider;
    private final ScreenLoadTracker screenLoadTracker;
    private final ShouldLaunchNfcFlowUseCase shouldLaunchNfcFlowUseCase;
    private Captures uploadResult;
    private OnfidoView view;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bç\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        OnfidoPresenter create(OnfidoConfig onfidoConfig);
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bá\u0080\u0001\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$PresenterAssistedFactory;", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface PresenterAssistedFactory extends Factory {
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000eHÆ\u0003JQ\u0010\"\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\u0006HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00060"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$State;", "Landroid/os/Parcelable;", "flowSteps", "", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "flowIndex", "", "captures", "Lcom/onfido/android/sdk/capture/upload/Captures;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "(Ljava/util/List;ILcom/onfido/android/sdk/capture/upload/Captures;Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;)V", "getCaptures", "()Lcom/onfido/android/sdk/capture/upload/Captures;", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getFlowIndex", "()I", "getFlowSteps", "()Ljava/util/List;", "getNfcProperties", "()Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new Creator();
        private final Captures captures;
        private final CountryCode countryCode;
        private final DocumentType documentType;
        private final int flowIndex;
        private final List<FlowStep> flowSteps;
        private final NfcProperties nfcProperties;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<State> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final State createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                int i = parcel.readInt();
                ArrayList arrayList = new ArrayList(i);
                for (int i2 = 0; i2 != i; i2++) {
                    arrayList.add(parcel.readSerializable());
                }
                return new State(arrayList, parcel.readInt(), (Captures) parcel.readSerializable(), parcel.readInt() == 0 ? null : DocumentType.valueOf(parcel.readString()), parcel.readInt() == 0 ? null : CountryCode.valueOf(parcel.readString()), parcel.readInt() != 0 ? NfcProperties.CREATOR.createFromParcel(parcel) : null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final State[] newArray(int i) {
                return new State[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public State(List<? extends FlowStep> flowSteps, int i, Captures captures, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties) {
            Intrinsics.checkNotNullParameter(flowSteps, "flowSteps");
            Intrinsics.checkNotNullParameter(captures, "captures");
            this.flowSteps = flowSteps;
            this.flowIndex = i;
            this.captures = captures;
            this.documentType = documentType;
            this.countryCode = countryCode;
            this.nfcProperties = nfcProperties;
        }

        public static /* synthetic */ State copy$default(State state, List list, int i, Captures captures, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                list = state.flowSteps;
            }
            if ((i2 & 2) != 0) {
                i = state.flowIndex;
            }
            int i3 = i;
            if ((i2 & 4) != 0) {
                captures = state.captures;
            }
            Captures captures2 = captures;
            if ((i2 & 8) != 0) {
                documentType = state.documentType;
            }
            DocumentType documentType2 = documentType;
            if ((i2 & 16) != 0) {
                countryCode = state.countryCode;
            }
            CountryCode countryCode2 = countryCode;
            if ((i2 & 32) != 0) {
                nfcProperties = state.nfcProperties;
            }
            return state.copy(list, i3, captures2, documentType2, countryCode2, nfcProperties);
        }

        public final List<FlowStep> component1() {
            return this.flowSteps;
        }

        /* renamed from: component2, reason: from getter */
        public final int getFlowIndex() {
            return this.flowIndex;
        }

        /* renamed from: component3, reason: from getter */
        public final Captures getCaptures() {
            return this.captures;
        }

        /* renamed from: component4, reason: from getter */
        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        /* renamed from: component5, reason: from getter */
        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        /* renamed from: component6, reason: from getter */
        public final NfcProperties getNfcProperties() {
            return this.nfcProperties;
        }

        public final State copy(List<? extends FlowStep> flowSteps, int flowIndex, Captures captures, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties) {
            Intrinsics.checkNotNullParameter(flowSteps, "flowSteps");
            Intrinsics.checkNotNullParameter(captures, "captures");
            return new State(flowSteps, flowIndex, captures, documentType, countryCode, nfcProperties);
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
            return Intrinsics.areEqual(this.flowSteps, state.flowSteps) && this.flowIndex == state.flowIndex && Intrinsics.areEqual(this.captures, state.captures) && this.documentType == state.documentType && this.countryCode == state.countryCode && Intrinsics.areEqual(this.nfcProperties, state.nfcProperties);
        }

        public final Captures getCaptures() {
            return this.captures;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final DocumentType getDocumentType() {
            return this.documentType;
        }

        public final int getFlowIndex() {
            return this.flowIndex;
        }

        public final List<FlowStep> getFlowSteps() {
            return this.flowSteps;
        }

        public final NfcProperties getNfcProperties() {
            return this.nfcProperties;
        }

        public int hashCode() {
            int iHashCode = ((((this.flowSteps.hashCode() * 31) + Integer.hashCode(this.flowIndex)) * 31) + this.captures.hashCode()) * 31;
            DocumentType documentType = this.documentType;
            int iHashCode2 = (iHashCode + (documentType == null ? 0 : documentType.hashCode())) * 31;
            CountryCode countryCode = this.countryCode;
            int iHashCode3 = (iHashCode2 + (countryCode == null ? 0 : countryCode.hashCode())) * 31;
            NfcProperties nfcProperties = this.nfcProperties;
            return iHashCode3 + (nfcProperties != null ? nfcProperties.hashCode() : 0);
        }

        public String toString() {
            return "State(flowSteps=" + this.flowSteps + ", flowIndex=" + this.flowIndex + ", captures=" + this.captures + ", documentType=" + this.documentType + ", countryCode=" + this.countryCode + ", nfcProperties=" + this.nfcProperties + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            List<FlowStep> list = this.flowSteps;
            parcel.writeInt(list.size());
            Iterator<FlowStep> it = list.iterator();
            while (it.hasNext()) {
                parcel.writeSerializable(it.next());
            }
            parcel.writeInt(this.flowIndex);
            parcel.writeSerializable(this.captures);
            DocumentType documentType = this.documentType;
            if (documentType == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeString(documentType.name());
            }
            CountryCode countryCode = this.countryCode;
            if (countryCode == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeString(countryCode.name());
            }
            NfcProperties nfcProperties = this.nfcProperties;
            if (nfcProperties == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                nfcProperties.writeToParcel(parcel, flags);
            }
        }

        public /* synthetic */ State(List list, int i, Captures captures, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, i, captures, (i2 & 8) != 0 ? null : documentType, (i2 & 16) != 0 ? null : countryCode, (i2 & 32) != 0 ? null : nfcProperties);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FlowAction.values().length];
            try {
                iArr[FlowAction.ACTIVE_VIDEO_CAPTURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FlowAction.CAPTURE_LIVENESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FlowAction.CAPTURE_FACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FlowAction.WELCOME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FlowAction.PROOF_OF_ADDRESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FlowAction.CAPTURE_DOCUMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FlowAction.USER_CONSENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[FlowAction.INTRO_FACE_CAPTURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[FlowAction.INTRO_LIVENESS_CAPTURE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[FlowAction.CAPTURE_LIVENESS_CONFIRMATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[FlowAction.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[FlowAction.NFC_HOST_FEATURE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[FlowAction.FINAL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[FlowAction.DYNAMIC_CONTENT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CaptureType.values().length];
            try {
                iArr2[CaptureType.DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[CaptureType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[CaptureType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @AssistedInject
    public OnfidoPresenter(FlowTracker flowTracker, LivenessTracker livenessTracker, ScreenLoadTracker screenLoadTracker, NfcTracker nfcTracker, RuntimePermissionsManager runtimePermissionsManager, DocumentConfigurationManager documentConfigurationManager, OnfidoPresenterInitializer onfidoPresenterInitializer, NfcDataRepository nfcDataRepository, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig, RemoteLoggerTree remoteLoggerTree, DeviceUtils deviceUtils, WaitingScreenTracker waitingScreenTracker, NfcInteractor nfcInteractor, ShouldLaunchNfcFlowUseCase shouldLaunchNfcFlowUseCase, @Assisted OnfidoConfig onfidoConfig) {
        Intrinsics.checkNotNullParameter(flowTracker, "flowTracker");
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(screenLoadTracker, "screenLoadTracker");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "runtimePermissionsManager");
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(onfidoPresenterInitializer, "onfidoPresenterInitializer");
        Intrinsics.checkNotNullParameter(nfcDataRepository, "nfcDataRepository");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(remoteLoggerTree, "remoteLoggerTree");
        Intrinsics.checkNotNullParameter(deviceUtils, "deviceUtils");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(shouldLaunchNfcFlowUseCase, "shouldLaunchNfcFlowUseCase");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        this.flowTracker = flowTracker;
        this.livenessTracker = livenessTracker;
        this.screenLoadTracker = screenLoadTracker;
        this.nfcTracker = nfcTracker;
        this.runtimePermissionsManager = runtimePermissionsManager;
        this.documentConfigurationManager = documentConfigurationManager;
        this.onfidoPresenterInitializer = onfidoPresenterInitializer;
        this.nfcDataRepository = nfcDataRepository;
        this.schedulersProvider = schedulersProvider;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.remoteLoggerTree = remoteLoggerTree;
        this.deviceUtils = deviceUtils;
        this.waitingScreenTracker = waitingScreenTracker;
        this.nfcInteractor = nfcInteractor;
        this.shouldLaunchNfcFlowUseCase = shouldLaunchNfcFlowUseCase;
        this.onfidoConfig = onfidoConfig;
        this.flowSteps = new ArrayList();
        this.compositeDisposable = new CompositeDisposable();
        this.orchestrationUserConsentVisibility = Visibility.GONE;
        this.uploadResult = new Captures(null, null, null, 7, null);
        OnfidoNavigation onfidoNavigation = new OnfidoNavigation(schedulersProvider);
        this.onfidoNavigation = onfidoNavigation;
        this.navigationManagerHolder = onfidoNavigation.getNavigationManagerHolder();
        this.navigator = onfidoNavigation.getNavigator();
    }

    private void addLoggerTree() {
        Timber.Companion companion = Timber.INSTANCE;
        if (companion.forest().contains(this.remoteLoggerTree)) {
            return;
        }
        companion.plant(this.remoteLoggerTree);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cleanFiles$lambda$20(File file, String str) {
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, LivenessConstants.LIVENESS_VIDEO_PREFIX, false, 2, (Object) null) || Intrinsics.areEqual(str, LivenessConstants.LIVENESS_INTRO_VIDEO_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cleanFiles$lambda$24(File file, String str) {
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, LivenessConstants.LIVENESS_MF_VIDEO_PREFIX, false, 2, (Object) null);
    }

    private FlowStep findFlowStep(FlowAction flowAction) {
        Object next;
        Iterator<T> it = this.flowSteps.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((FlowStep) next).getAction() == flowAction) {
                break;
            }
        }
        return (FlowStep) next;
    }

    private CaptureStepDataBundle getCaptureBundle(CaptureScreenOptions options) {
        return new CaptureStepDataBundle(CaptureType.DOCUMENT, options.getDocumentType(), options.getCountry(), options.getDocumentFormat(), DocSide.FRONT, options.getGenericDocTitle(), options.getGenericDocPages());
    }

    private FlowAction getCurrentAction() {
        return getCurrentStep().getAction();
    }

    private FlowStep getCurrentStep() {
        return this.flowSteps.get(this.flowIndex);
    }

    private NfcArguments getNfcArguments() {
        DocumentSide front;
        NFCOptions nfcOptions = getOnfidoConfig().getNfcOptions();
        Document document = this.uploadResult.getDocument();
        return new NfcArguments(nfcOptions, (document == null || (front = document.getFront()) == null) ? null : new NfcArguments.CapturedNfcData(front.getId(), this.nfcProperties, front.getNfcSupported()));
    }

    public static /* synthetic */ void initFlow$onfido_capture_sdk_core_release$default(OnfidoPresenter onfidoPresenter, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initFlow");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        onfidoPresenter.initFlow$onfido_capture_sdk_core_release(z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFlowSteps(boolean shouldAskForConsent) {
        List<FlowStep> listPrepareFlowSteps = prepareFlowSteps(shouldAskForConsent, getOnfidoConfig().getFlowSteps());
        for (FlowStep flowStep : listPrepareFlowSteps) {
            int i = WhenMappings.$EnumSwitchMapping$0[flowStep.getAction().ordinal()];
            if (i == 4) {
                flowStep.setOptions(new WelcomeScreenOptions(listPrepareFlowSteps));
            } else if (i == 6) {
                BaseOptions options = flowStep.getOptions();
                CaptureScreenOptions captureScreenOptions = options instanceof CaptureScreenOptions ? (CaptureScreenOptions) options : null;
                if (captureScreenOptions != null) {
                    this.documentType = captureScreenOptions.getDocumentType();
                }
            }
        }
        this.flowSteps = listPrepareFlowSteps;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPrepareFlowSteps, 10));
        Iterator<T> it = listPrepareFlowSteps.iterator();
        while (it.hasNext()) {
            arrayList.add(((FlowStep) it.next()).getAction());
        }
        for (FlowAction flowAction : FlowAction.INSTANCE.getNonDuplicable()) {
            if (ListExtensionsKt.hasDuplicate(arrayList, flowAction)) {
                StringBuilder sb = new StringBuilder("Custom flow cannot have duplicates of:");
                String string = Arrays.toString(FlowAction.INSTANCE.getNonDuplicable());
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                throw new IllegalStateException(sb.append(string).toString().toString());
            }
        }
        if (!(!FlowValidation.INSTANCE.containsDifferentFaceCaptureVariants(arrayList))) {
            throw new IllegalStateException("Custom flow cannot contain more than one FaceCaptureVariant".toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOrchestrationFlow(boolean shouldAskForConsent) {
        OnfidoView onfidoView = null;
        if (!shouldAskForConsent) {
            OnfidoView onfidoView2 = this.view;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView2;
            }
            onfidoView.showWorkflowFragment(getOnfidoConfig().getWorkflowConfig());
            return;
        }
        this.orchestrationUserConsentVisibility = Visibility.VISIBLE;
        OnfidoView onfidoView3 = this.view;
        if (onfidoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            onfidoView = onfidoView3;
        }
        onfidoView.showUserConsentScreen(FlowStepDirection.NO_DIRECTION);
    }

    private void initializeSdk(final Function1<? super OnfidoPresenterInitializer.InitializerResult.Success, Unit> action) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.onfidoPresenterInitializer.initialize$onfido_capture_sdk_core_release(OnfidoConfigExtensionsKt.hasPreselectedDocuments(getOnfidoConfig())).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter.initializeSdk.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(OnfidoPresenterInitializer.InitializerResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                OnfidoView onfidoView = null;
                if (Intrinsics.areEqual(result, OnfidoPresenterInitializer.InitializerResult.Loading.INSTANCE)) {
                    OnfidoView onfidoView2 = OnfidoPresenter.this.view;
                    if (onfidoView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        onfidoView = onfidoView2;
                    }
                    onfidoView.showLoadingView();
                    return;
                }
                if (result instanceof OnfidoPresenterInitializer.InitializerResult.Success) {
                    OnfidoView onfidoView3 = OnfidoPresenter.this.view;
                    if (onfidoView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        onfidoView3 = null;
                    }
                    onfidoView3.hideLoadingView();
                    OnfidoView onfidoView4 = OnfidoPresenter.this.view;
                    if (onfidoView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        onfidoView = onfidoView4;
                    }
                    onfidoView.enableToolbarBackNavigation();
                    action.invoke(result);
                    return;
                }
                if (result instanceof OnfidoPresenterInitializer.InitializerResult.Error.GenericError) {
                    Timber.INSTANCE.e(((OnfidoPresenterInitializer.InitializerResult.Error.GenericError) result).getCause(), "Error during initializing presenter", new Object[0]);
                    return;
                }
                if (result instanceof OnfidoPresenterInitializer.InitializerResult.Error.SSLHandshakeUnverified) {
                    OnfidoView onfidoView5 = OnfidoPresenter.this.view;
                    if (onfidoView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        onfidoView = onfidoView5;
                    }
                    onfidoView.onError(new InvalidCertificateException(((OnfidoPresenterInitializer.InitializerResult.Error.SSLHandshakeUnverified) result).getMessage()));
                    return;
                }
                if (Intrinsics.areEqual(result, OnfidoPresenterInitializer.InitializerResult.Error.TokenExpired.INSTANCE)) {
                    OnfidoView onfidoView6 = OnfidoPresenter.this.view;
                    if (onfidoView6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        onfidoView = onfidoView6;
                    }
                    onfidoView.onError(TokenExpiredException.INSTANCE);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private boolean isDeviceNotSupported() {
        return NFCOptionsKt.isRequired(getOnfidoConfig().getNfcOptions()) && !this.nfcInteractor.isNfcSupported();
    }

    private boolean isOnlyOneDocAvailable() {
        Object next;
        Iterator<T> it = this.flowSteps.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((FlowStep) next).getAction() == FlowAction.CAPTURE_DOCUMENT) {
                break;
            }
        }
        FlowStep flowStep = (FlowStep) next;
        Object options = flowStep != null ? flowStep.getOptions() : null;
        return (options instanceof CaptureScreenOptions ? (CaptureScreenOptions) options : null) != null;
    }

    private boolean isVideoAvailableOnConfirmationScreen() {
        FlowStep flowStepFindFlowStep = findFlowStep(FlowAction.CAPTURE_LIVENESS_CONFIRMATION);
        BaseOptions options = flowStepFindFlowStep != null ? flowStepFindFlowStep.getOptions() : null;
        if (options instanceof VideoCaptureVariantOptions) {
            return ((VideoCaptureVariantOptions) options).getShowConfirmationVideo();
        }
        if (options instanceof LivenessConfirmationOptions) {
            return ((LivenessConfirmationOptions) options).getShowConfirmationVideo();
        }
        return true;
    }

    private void launchFaceCapture(DocumentType documentType) {
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            navigateTo(SelfieCaptureScreen.INSTANCE, FlowStepDirection.RIGHT_TO_LEFT);
            return;
        }
        showEmptyScreen();
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showFaceCapture(documentType);
    }

    private void moveTo(FlowStep step, FlowStepDirection flowStepDirection, FlowStep currentStep) {
        OnfidoView onfidoView;
        BaseOptions options = step.getOptions();
        OnfidoView onfidoView2 = null;
        OnfidoView onfidoView3 = null;
        OnfidoView onfidoView4 = null;
        switch (WhenMappings.$EnumSwitchMapping$0[step.getAction().ordinal()]) {
            case 1:
                OnfidoView onfidoView5 = this.view;
                if (onfidoView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    onfidoView5 = null;
                }
                onfidoView5.showMotionFlow(options instanceof MotionCaptureVariantOptions ? (MotionCaptureVariantOptions) options : null, flowStepDirection);
                return;
            case 2:
                showLivenessCapture(flowStepDirection);
                return;
            case 3:
                showFaceSelfieCapture(flowStepDirection, currentStep);
                return;
            case 4:
                WelcomeScreenOptions welcomeScreenOptions = new WelcomeScreenOptions(this.flowSteps);
                if (options == null) {
                    options = welcomeScreenOptions;
                }
                showWelcomeScreen(options, flowStepDirection);
                return;
            case 5:
                if (this.onfidoRemoteConfig.getEnableWebModuleBasedPoa()) {
                    OnfidoView onfidoView6 = this.view;
                    if (onfidoView6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                        onfidoView6 = null;
                    }
                    onfidoView6.showProofOfAddressFlowWeb(new HostedWebModuleModuleInfo(BiometricTokenStorageFlowProcessor.EMPTY_REQUEST_OBJECT, BiometricTokenStorageFlowProcessor.EMPTY_REQUEST_OBJECT, null, new ClassicModuleInfo("proofOfAddress", null, 2, null), 4, null), flowStepDirection);
                    return;
                }
                OnfidoView onfidoView7 = this.view;
                if (onfidoView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    onfidoView4 = onfidoView7;
                }
                onfidoView4.showProofOfAddressFlow(flowStepDirection);
                return;
            case 6:
                showDocumentCapture(options, flowStepDirection, currentStep);
                return;
            case 7:
                OnfidoView onfidoView8 = this.view;
                if (onfidoView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    onfidoView3 = onfidoView8;
                }
                onfidoView3.showUserConsentScreen(flowStepDirection);
                return;
            case 8:
                showFaceSelfieIntro(flowStepDirection, currentStep);
                return;
            case 9:
                showLivenessIntro(step, flowStepDirection);
                return;
            case 10:
                showLivenessCaptureConfirmation(options, flowStepDirection);
                return;
            case 11:
                showMessageScreen(options, flowStepDirection);
                return;
            case 12:
                OnfidoView onfidoView9 = this.view;
                if (onfidoView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    onfidoView = null;
                } else {
                    onfidoView = onfidoView9;
                }
                DocumentType documentType = this.documentType;
                CountryCode countryCode = this.countryCode;
                NfcProperties nfcProperties = this.nfcProperties;
                if (nfcProperties == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                onfidoView.showNfcHostFragment(documentType, countryCode, nfcProperties, flowStepDirection, isOnlyOneDocAvailable());
                return;
            case 13:
                OnfidoView onfidoView10 = this.view;
                if (onfidoView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    onfidoView2 = onfidoView10;
                }
                onfidoView2.showFinalScreen(flowStepDirection);
                return;
            default:
                return;
        }
    }

    private void moveToDocumentCaptureOrPermissions(BaseOptions baseOptions, FlowStepDirection flowStepDirection, FlowStep currentStep) {
        CaptureScreenOptions captureScreenOptions = baseOptions instanceof CaptureScreenOptions ? (CaptureScreenOptions) baseOptions : null;
        if (captureScreenOptions != null) {
            showDocumentCaptureOrPermissionsScreen(captureScreenOptions, flowStepDirection, currentStep);
        } else {
            showDocumentTypeSelection(flowStepDirection, currentStep);
        }
    }

    private void navigateTo(Screen screen, FlowStepDirection flowStepDirection) {
        if (SetsKt.setOf((Object[]) new FlowStepDirection[]{FlowStepDirection.RIGHT_TO_LEFT, FlowStepDirection.NO_DIRECTION}).contains(flowStepDirection)) {
            getNavigator().navigateTo(screen);
        } else {
            getNavigator().backTo(screen);
        }
    }

    private boolean needToCaptureBackOfDocument(DocumentCaptureScreen.DocumentCaptureResult.Completed documentResult) {
        CaptureStepDataBundle documentData = documentResult.getDocumentData();
        return documentData.getDocSide() == DocSide.FRONT && backSideCaptureNeeded(documentData.getDocumentType(), documentData.getGenericDocPages());
    }

    private void onCaptureException(Exception exception) {
        if (exception instanceof CameraException) {
            onBackPressed$onfido_capture_sdk_core_release();
            return;
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.onError(exception);
    }

    private void onFaceCapturePermissionGranted(DocumentType documentType) {
        onNavigationStarted$onfido_capture_sdk_core_release$default(this, PerformanceAnalyticsScreen.PERMISSION_MANAGEMENT, null, PerformanceAnalyticsScreen.FACE_SELFIE_CAPTURE, 2, null);
        launchFaceCapture(documentType);
    }

    private void onFaceVideoPermissionGranted() {
        onNavigationStarted$onfido_capture_sdk_core_release$default(this, PerformanceAnalyticsScreen.PERMISSION_MANAGEMENT, null, PerformanceAnalyticsScreen.FACE_VIDEO_CAPTURE, 2, null);
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            getNavigator().navigateTo(LivenessCaptureScreen.INSTANCE);
            return;
        }
        showEmptyScreen();
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showLivenessCapture();
    }

    public static /* synthetic */ void onNavigationStarted$onfido_capture_sdk_core_release$default(OnfidoPresenter onfidoPresenter, PerformanceAnalyticsScreen performanceAnalyticsScreen, FlowStep flowStep, PerformanceAnalyticsScreen performanceAnalyticsScreen2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onNavigationStarted");
        }
        if ((i & 1) != 0) {
            performanceAnalyticsScreen = null;
        }
        if ((i & 2) != 0) {
            flowStep = null;
        }
        onfidoPresenter.onNavigationStarted$onfido_capture_sdk_core_release(performanceAnalyticsScreen, flowStep, performanceAnalyticsScreen2);
    }

    private void onNfcScanSucceeded(NfcPassportExtraction nfcData, NfcFlowType nfcFlowType) {
        NfcDataService nfcDataService = this.nfcDataService;
        if (nfcDataService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcDataService");
            nfcDataService = null;
        }
        nfcDataService.uploadBinary(nfcData, this.documentType, nfcFlowType);
        this.nfcTracker.trackDataUploadStarted(nfcFlowType);
    }

    private void onPermissionScreenBackPressedAfterDocSelection() {
        if (this.flowIndex != 0 || shouldShowDocumentSelection()) {
            return;
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
    }

    private void onPermissionScreenDismissed() {
        boolean z = getCurrentAction() == FlowAction.CAPTURE_DOCUMENT && !shouldShowDocumentSelection();
        boolean z2 = getCurrentAction() == FlowAction.CAPTURE_FACE;
        boolean z3 = getCurrentAction() == FlowAction.CAPTURE_LIVENESS;
        if (z || z2 || z3) {
            previousAction$onfido_capture_sdk_core_release();
        }
    }

    private void onPermissionsGranted(CaptureStepDataBundle captureDataBundle) {
        if (this.awaitingPermissions) {
            this.awaitingPermissions = false;
            int i = WhenMappings.$EnumSwitchMapping$1[captureDataBundle.getCaptureType().ordinal()];
            if (i == 1) {
                showCaptureStep$onfido_capture_sdk_core_release(true, captureDataBundle, PerformanceAnalyticsScreen.PERMISSION_MANAGEMENT);
            } else if (i == 2) {
                onFaceCapturePermissionGranted(captureDataBundle.getDocumentType());
            } else {
                if (i != 3) {
                    return;
                }
                onFaceVideoPermissionGranted();
            }
        }
    }

    private void onVideoCaptured(UploadedArtifact videoResult) {
        this.uploadResult.setFace(new Face(videoResult.getId(), FaceCaptureVariant.VIDEO));
        nextAction();
    }

    private void openNewDocumentCapture(boolean isFrontSide, CaptureStepDataBundle captureBundle, NfcArguments nfcArguments) {
        DocumentCaptureScreen documentCaptureScreen = new DocumentCaptureScreen(captureBundle, isFrontSide, nfcArguments);
        if (isFrontSide) {
            getNavigator().navigateTo(documentCaptureScreen);
        } else {
            getNavigator().replace(documentCaptureScreen);
        }
    }

    private void prepareFaceSteps(List<FlowStep> modifiedSteps, BaseOptions options) {
        FlowStep flowStep;
        if (this.onfidoRemoteConfig.getMotionExperiment().isEnabled()) {
            flowStep = new FlowStep(FlowAction.ACTIVE_VIDEO_CAPTURE);
        } else {
            if (shouldShowFaceCaptureIntro(options)) {
                modifiedSteps.add(new FlowStep(FlowAction.INTRO_FACE_CAPTURE));
            }
            flowStep = new FlowStep(FlowAction.CAPTURE_FACE);
        }
        modifiedSteps.add(flowStep);
    }

    private List<FlowStep> prepareFlowSteps(boolean shouldAskForConsent, List<? extends FlowStep> flowSteps) {
        LinkedList linkedList = new LinkedList();
        boolean z = false;
        for (FlowStep flowStep : flowSteps) {
            FlowAction action = flowStep.getAction();
            BaseOptions options = flowStep.getOptions();
            int i = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
            if (i == 1) {
                prepareMotionSteps(linkedList, options);
            } else if (i == 2) {
                prepareLivenessSteps(linkedList, options);
            } else if (i == 3) {
                prepareFaceSteps(linkedList, options);
            } else if (i != 4) {
                if (i == 5) {
                    flowStep = new FlowStep(FlowAction.PROOF_OF_ADDRESS);
                }
                linkedList.add(flowStep);
            } else {
                linkedList.add(flowStep);
                if (shouldAskForConsent) {
                    linkedList.add(new FlowStep(FlowAction.USER_CONSENT));
                    z = true;
                }
            }
        }
        if (!z && shouldAskForConsent) {
            linkedList.addFirst(new FlowStep(FlowAction.USER_CONSENT));
        }
        return SequencesKt.toMutableList(SequencesKt.distinct(CollectionsKt.asSequence(linkedList)));
    }

    private void prepareLivenessSteps(List<FlowStep> modifiedSteps, BaseOptions options) {
        if (this.onfidoRemoteConfig.getMotionExperiment().isEnabled()) {
            modifiedSteps.add(new FlowStep(FlowAction.ACTIVE_VIDEO_CAPTURE));
            return;
        }
        FlowStep flowStep = new FlowStep(FlowAction.INTRO_LIVENESS_CAPTURE);
        flowStep.setOptions(options);
        modifiedSteps.add(flowStep);
        modifiedSteps.add(new FlowStep(FlowAction.CAPTURE_LIVENESS));
        FlowStep flowStep2 = new FlowStep(FlowAction.CAPTURE_LIVENESS_CONFIRMATION);
        flowStep2.setOptions(options);
        modifiedSteps.add(flowStep2);
    }

    private void prepareMotionSteps(List<FlowStep> modifiedSteps, BaseOptions baseOptions) {
        FlowStep flowStep = new FlowStep(FlowAction.ACTIVE_VIDEO_CAPTURE);
        flowStep.setOptions(baseOptions);
        modifiedSteps.add(flowStep);
    }

    private List<FlowStep> previousSteps() {
        return this.flowSteps.subList(0, this.flowIndex);
    }

    private void removeNfcStep() {
        setState$onfido_capture_sdk_core_release(State.copy$default(getState$onfido_capture_sdk_core_release(), null, 0, null, null, null, null, 31, null));
        removeStep(FlowAction.NFC_HOST_FEATURE);
    }

    private void removeStep(final FlowAction flowAction) {
        if (CollectionsKt.removeAll((List) this.flowSteps, (Function1) new Function1<FlowStep, Boolean>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter$removeStep$isRemoved$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FlowStep it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.getAction() == flowAction);
            }
        })) {
            this.flowIndex = Math.max(0, this.flowIndex - 1);
        }
    }

    private void setActionWithIndex(int newIndex) {
        int i = this.flowIndex;
        FlowStepDirection flowStepDirection = i > newIndex ? FlowStepDirection.LEFT_TO_RIGHT : (i < newIndex || i == 0) ? FlowStepDirection.RIGHT_TO_LEFT : FlowStepDirection.NO_DIRECTION;
        FlowStep flowStep = this.flowSteps.get(i);
        this.flowIndex = newIndex;
        moveTo(this.flowSteps.get(newIndex), flowStepDirection, flowStep);
    }

    public static /* synthetic */ void setup$onfido_capture_sdk_core_release$default(OnfidoPresenter onfidoPresenter, OnfidoView onfidoView, State state, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setup");
        }
        if ((i & 2) != 0) {
            state = null;
        }
        onfidoPresenter.setup$onfido_capture_sdk_core_release(onfidoView, state);
    }

    private boolean shouldShowDocumentSelection() {
        return getCurrentStep().getOptions() == null;
    }

    private boolean shouldShowFaceCaptureIntro(BaseOptions options) {
        return options == null || !(options instanceof PhotoCaptureVariantOptions) || ((PhotoCaptureVariantOptions) options).getWithIntroScreen();
    }

    private boolean shouldShowLivenessIntroVideo(BaseOptions options) {
        return options == null || !(options instanceof VideoCaptureVariantOptions) || ((VideoCaptureVariantOptions) options).getShowIntroVideo();
    }

    private void showDocumentCapture(BaseOptions baseOptions, FlowStepDirection flowStepDirection, FlowStep currentStep) {
        if (!isDeviceNotSupported()) {
            moveToDocumentCaptureOrPermissions(baseOptions, flowStepDirection, currentStep);
            return;
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showDeviceNotSupportedFragment(flowStepDirection);
    }

    private void showDocumentCaptureOrPermissionsScreen(CaptureScreenOptions options, FlowStepDirection flowStepDirection, FlowStep currentStep) {
        CaptureStepDataBundle captureBundle = getCaptureBundle(options);
        if (this.runtimePermissionsManager.hasPermissionsForCaptureType(CaptureType.DOCUMENT)) {
            showCaptureStep$onfido_capture_sdk_core_release(true, captureBundle, PerformanceAnalyticsScreen.INSTANCE.fromFlowAction(currentStep.getAction()));
        } else {
            showPermissionsScreen(captureBundle, flowStepDirection);
        }
    }

    private void showDocumentTypeSelection(FlowStepDirection flowStepDirection, FlowStep currentStep) {
        onNavigationStarted$onfido_capture_sdk_core_release$default(this, null, currentStep, PerformanceAnalyticsScreen.DOCUMENT_TYPE_SELECTION, 1, null);
        navigateTo(new DocumentSelectionScreen(this.countryCode, null, NFCOptionsKt.isRequired(getOnfidoConfig().getNfcOptions()), false, 10, null), flowStepDirection);
    }

    private void showEmptyScreen() {
        getNavigator().navigateTo(EmptyScreen.INSTANCE);
    }

    private void showFaceSelfieCapture(FlowStepDirection flowStepDirection, FlowStep currentStep) {
        RuntimePermissionsManager runtimePermissionsManager = this.runtimePermissionsManager;
        CaptureType captureType = CaptureType.FACE;
        if (!runtimePermissionsManager.hasPermissionsForCaptureType(captureType)) {
            showPermissionsScreen(new CaptureStepDataBundle(captureType, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null), flowStepDirection);
        } else {
            onNavigationStarted$onfido_capture_sdk_core_release$default(this, null, currentStep, PerformanceAnalyticsScreen.FACE_SELFIE_CAPTURE, 1, null);
            launchFaceCapture(this.documentType);
        }
    }

    private void showFaceSelfieIntro(FlowStepDirection flowStepDirection, FlowStep currentStep) {
        onNavigationStarted$onfido_capture_sdk_core_release$default(this, null, currentStep, PerformanceAnalyticsScreen.FACE_SELFIE_INTRO, 1, null);
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showCaptureFaceMessage(flowStepDirection);
    }

    private void showLivenessCapture(FlowStepDirection flowStepDirection) {
        RuntimePermissionsManager runtimePermissionsManager = this.runtimePermissionsManager;
        CaptureType captureType = CaptureType.VIDEO;
        if (!runtimePermissionsManager.hasPermissionsForCaptureType(captureType)) {
            showPermissionsScreen(new CaptureStepDataBundle(captureType, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null), flowStepDirection);
            return;
        }
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            getNavigator().navigateTo(LivenessCaptureScreen.INSTANCE);
            return;
        }
        showEmptyScreen();
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showLivenessCapture();
    }

    private void showLivenessCaptureConfirmation(BaseOptions baseOptions, FlowStepDirection flowStepDirection) {
        OnfidoView onfidoView = null;
        LivenessConfirmationOptions livenessConfirmationOptions = baseOptions instanceof LivenessConfirmationOptions ? (LivenessConfirmationOptions) baseOptions : null;
        if (livenessConfirmationOptions == null) {
            return;
        }
        OnfidoView onfidoView2 = this.view;
        if (onfidoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            onfidoView = onfidoView2;
        }
        onfidoView.showCaptureLivenessConfirmation(flowStepDirection, livenessConfirmationOptions.getVideoFilePath(), livenessConfirmationOptions.getLivenessPerformedChallenges(), livenessConfirmationOptions.getShowConfirmationVideo());
    }

    private void showLivenessIntro(FlowStep step, FlowStepDirection flowStepDirection) {
        boolean zShouldShowLivenessIntroVideo = shouldShowLivenessIntroVideo(step.getOptions());
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showLivenessIntro(zShouldShowLivenessIntroVideo, flowStepDirection);
    }

    private void showMessageScreen(BaseOptions baseOptions, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNull(baseOptions, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.options.MessageScreenOptions");
        MessageScreenOptions messageScreenOptions = (MessageScreenOptions) baseOptions;
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showMessageScreen(messageScreenOptions.getTitle(), messageScreenOptions.getMessage(), flowStepDirection);
    }

    private void showPermissionsScreen(CaptureStepDataBundle captureBundle, FlowStepDirection flowStepDirection) {
        this.awaitingPermissions = true;
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showPermissionsManagementFragment(captureBundle, flowStepDirection);
    }

    private void showWelcomeScreen(BaseOptions baseOptions, FlowStepDirection flowStepDirection) {
        onNavigationStarted$onfido_capture_sdk_core_release$default(this, PerformanceAnalyticsScreen.SPLASH, null, PerformanceAnalyticsScreen.WELCOME, 2, null);
        Intrinsics.checkNotNull(baseOptions, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions");
        navigateTo(new WelcomeScreen((WelcomeScreenOptions) baseOptions), flowStepDirection);
    }

    private void skipNfcScanStep() {
        Document document = this.uploadResult.getDocument();
        if (document != null) {
            document.setNfcMediaUUID(null);
        }
        nextAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFlow() {
        if (this.flowSteps.isEmpty()) {
            return;
        }
        setActionWithIndex(this.flowIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackFlowInitiatedEvents(boolean isSystemDarkModeEnabled) {
        this.flowTracker.trackFlowStart$onfido_capture_sdk_core_release();
        FlowTracker flowTracker = this.flowTracker;
        Locale locale = getOnfidoConfig().getLocale();
        String languageTag = locale != null ? locale.toLanguageTag() : null;
        String languageTag2 = Locale.getDefault().toLanguageTag();
        Intrinsics.checkNotNullExpressionValue(languageTag2, "toLanguageTag(...)");
        flowTracker.trackLanguageConfig$onfido_capture_sdk_core_release(languageTag, languageTag2, this.deviceUtils.getDeviceLanguages$onfido_capture_sdk_core_release());
        this.flowTracker.trackUiThemeConfig$onfido_capture_sdk_core_release(getOnfidoConfig().getTheme(), isSystemDarkModeEnabled);
    }

    public void appendNfcFeature$onfido_capture_sdk_core_release() {
        boolean z;
        boolean zInvoke = this.shouldLaunchNfcFlowUseCase.invoke(this.nfcProperties);
        List<FlowStep> list = this.flowSteps;
        if ((list instanceof Collection) && list.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((FlowStep) it.next()).getAction() == FlowAction.NFC_HOST_FEATURE) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (zInvoke && !z) {
            this.flowSteps.add(this.flowIndex + 1, new FlowStep(FlowAction.NFC_HOST_FEATURE));
        }
        if (NFCOptionsKt.isRequired(getOnfidoConfig().getNfcOptions()) && this.nfcProperties == null) {
            this.nfcProperties = new NfcProperties(false, "", new byte[0], null, false, false, 0, 0, null, 496, null);
        }
    }

    public boolean backSideCaptureNeeded(DocumentType documentType, DocumentPages genericDocumentPages) {
        return documentType != null && this.documentConfigurationManager.backSideCaptureNeeded(documentType, genericDocumentPages);
    }

    public void cleanFiles(File directory) {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        List<FlowStep> list = this.flowSteps;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FlowStep) it.next()).getAction());
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (((FlowAction) it2.next()) == FlowAction.CAPTURE_LIVENESS) {
                    if (directory != null && (fileArrListFiles2 = directory.listFiles(new FilenameFilter() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter$$ExternalSyntheticLambda0
                        @Override // java.io.FilenameFilter
                        public final boolean accept(File file, String str) {
                            return OnfidoPresenter.cleanFiles$lambda$20(file, str);
                        }
                    })) != null) {
                        for (File file : fileArrListFiles2) {
                            file.delete();
                        }
                    }
                }
            }
        }
        List<FlowStep> list2 = this.flowSteps;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it3 = list2.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((FlowStep) it3.next()).getAction());
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        Iterator it4 = arrayList2.iterator();
        while (it4.hasNext()) {
            if (((FlowAction) it4.next()) == FlowAction.CAPTURE_DOCUMENT) {
                if (directory == null || (fileArrListFiles = directory.listFiles(new FilenameFilter() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter$$ExternalSyntheticLambda1
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file2, String str) {
                        return OnfidoPresenter.cleanFiles$lambda$24(file2, str);
                    }
                })) == null) {
                    return;
                }
                for (File file2 : fileArrListFiles) {
                    file2.delete();
                }
                return;
            }
        }
    }

    public NavigationManagerHolder getNavigationManagerHolder() {
        return this.navigationManagerHolder;
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    /* renamed from: getOnfidoConfig$onfido_capture_sdk_core_release, reason: from getter */
    public OnfidoConfig getOnfidoConfig() {
        return this.onfidoConfig;
    }

    public State getState$onfido_capture_sdk_core_release() {
        return new State(this.flowSteps, this.flowIndex, this.uploadResult, this.documentType, this.countryCode, this.nfcProperties);
    }

    public void initFlow$onfido_capture_sdk_core_release(final boolean isRestoringState, final boolean isSystemDarkModeEnabled) {
        initializeSdk(new Function1<OnfidoPresenterInitializer.InitializerResult.Success, Unit>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter$initFlow$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoPresenterInitializer.InitializerResult.Success success) {
                invoke2(success);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoPresenterInitializer.InitializerResult.Success initializeResultSuccess) {
                Intrinsics.checkNotNullParameter(initializeResultSuccess, "initializeResultSuccess");
                if (isRestoringState) {
                    return;
                }
                if (OnfidoConfigExtensionsKt.inWorkflowMode(this.getOnfidoConfig())) {
                    this.initOrchestrationFlow(initializeResultSuccess.getShouldAskForConsent());
                } else {
                    this.initFlowSteps(initializeResultSuccess.getShouldAskForConsent());
                    this.startFlow();
                }
                this.trackFlowInitiatedEvents(isSystemDarkModeEnabled);
            }
        });
    }

    public void nextAction() {
        if (this.flowIndex < CollectionsKt.getLastIndex(this.flowSteps)) {
            setActionWithIndex(this.flowIndex + 1);
            return;
        }
        onFlowCompleted$onfido_capture_sdk_core_release();
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.completeFlow();
    }

    public void onAvcHostResult$onfido_capture_sdk_core_release(MotionHostFragment.AvcHostResult avcHostResult) {
        Intrinsics.checkNotNullParameter(avcHostResult, "avcHostResult");
        if (Intrinsics.areEqual(avcHostResult, MotionHostFragment.AvcHostResult.Exit.INSTANCE)) {
            onBackPressed$onfido_capture_sdk_core_release();
            return;
        }
        if (avcHostResult instanceof MotionHostFragment.AvcHostResult.Completed) {
            onMotionCaptured$onfido_capture_sdk_core_release(((MotionHostFragment.AvcHostResult.Completed) avcHostResult).getUploadedArtifact());
            return;
        }
        if (avcHostResult instanceof MotionHostFragment.AvcHostResult.Error) {
            OnfidoView onfidoView = this.view;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
                onfidoView = null;
            }
            onfidoView.onError(new OnfidoException(((MotionHostFragment.AvcHostResult.Error) avcHostResult).getMessage()));
        }
    }

    public void onBackPressed$onfido_capture_sdk_core_release() {
        if (getCurrentAction() == FlowAction.CAPTURE_DOCUMENT) {
            if (!isDeviceNotSupported()) {
                if (this.runtimePermissionsManager.hasPermissionsForCaptureType(CaptureType.DOCUMENT)) {
                    onDocumentCaptureBackPressed$onfido_capture_sdk_core_release();
                    return;
                } else {
                    onPermissionScreenBackPressedAfterDocSelection();
                    return;
                }
            }
        } else {
            if (this.flowIndex == 0 || previousSteps().contains(FlowStep.FINAL)) {
                OnfidoView onfidoView = this.view;
                if (onfidoView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    onfidoView = null;
                }
                onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
                return;
            }
            if (getCurrentAction() != FlowAction.NFC_HOST_FEATURE) {
                if (getCurrentAction() == FlowAction.INTRO_FACE_CAPTURE) {
                    this.livenessTracker.trackFaceSelfieIntroBackButtonClicked$onfido_capture_sdk_core_release();
                } else if (getCurrentAction() == FlowAction.INTRO_LIVENESS_CAPTURE) {
                    this.livenessTracker.trackFaceVideoIntroBackButtonClicked$onfido_capture_sdk_core_release();
                } else if (getCurrentAction() == FlowAction.CAPTURE_LIVENESS) {
                    if (this.runtimePermissionsManager.hasPermissionsForCaptureType(CaptureType.VIDEO)) {
                        this.livenessTracker.trackFaceVideoCaptureBackButtonClicked$onfido_capture_sdk_core_release();
                    }
                } else if (getCurrentAction() == FlowAction.CAPTURE_LIVENESS_CONFIRMATION) {
                    this.livenessTracker.trackFaceVideoConfirmationBackButtonClicked$onfido_capture_sdk_core_release();
                }
            }
        }
        previousAction$onfido_capture_sdk_core_release();
    }

    public void onCaptureWithoutPermissions$onfido_capture_sdk_core_release(CaptureStepDataBundle captureData) {
        Intrinsics.checkNotNullParameter(captureData, "captureData");
        getNavigator().exitCurrentScreen();
        showPermissionsScreen(captureData, FlowStepDirection.RIGHT_TO_LEFT);
    }

    @Override // com.onfido.android.sdk.capture.ui.NfcDataServiceListener
    public void onDataUploaded(DocumentMediaUploadResponse documentMediaUpload, NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(documentMediaUpload, "documentMediaUpload");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.nfcTracker.trackDataUploadCompleted(nfcFlowType);
        Document document = this.uploadResult.getDocument();
        if (document != null) {
            document.setNfcMediaUUID(documentMediaUpload.mediaId());
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.hideLoadingDialog();
        nextAction();
    }

    public void onDocumentCaptureBackPressed$onfido_capture_sdk_core_release() {
        if (shouldShowDocumentSelection()) {
            onNavigationStarted$onfido_capture_sdk_core_release$default(this, PerformanceAnalyticsScreen.DOCUMENT_CAPTURE, null, PerformanceAnalyticsScreen.DOCUMENT_TYPE_SELECTION, 2, null);
        }
        getNavigator().exitCurrentScreen();
        if (shouldShowDocumentSelection()) {
            return;
        }
        previousAction$onfido_capture_sdk_core_release();
    }

    public void onDocumentCaptureResult(DocumentCaptureScreen.DocumentCaptureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof DocumentCaptureScreen.DocumentCaptureResult.Error) {
            onCaptureException(((DocumentCaptureScreen.DocumentCaptureResult.Error) result).getException());
            return;
        }
        if (!(result instanceof DocumentCaptureScreen.DocumentCaptureResult.Completed)) {
            if (result instanceof DocumentCaptureScreen.DocumentCaptureResult.POACompleted) {
                throw new IllegalStateException(("POA result is handled inside the POAHostFragment: " + result).toString());
            }
            return;
        }
        DocumentCaptureScreen.DocumentCaptureResult.Completed completed = (DocumentCaptureScreen.DocumentCaptureResult.Completed) result;
        String uploadId = completed.getUploadId();
        DocSide docSide = completed.getDocumentData().getDocSide();
        if (docSide == null) {
            docSide = DocSide.BACK;
        }
        DocumentType documentType = completed.getDocumentData().getDocumentType();
        Intrinsics.checkNotNull(documentType);
        onDocumentCaptured$onfido_capture_sdk_core_release(new DocumentSide(uploadId, docSide, documentType, completed.isNfcSupported()), completed.getNfcProperties());
        if (needToCaptureBackOfDocument(completed)) {
            showCaptureStep$onfido_capture_sdk_core_release(false, CaptureStepDataBundle.copy$default(completed.getDocumentData(), null, null, null, null, DocSide.BACK, null, null, 111, null), PerformanceAnalyticsScreen.DOCUMENT_CAPTURE);
        } else {
            appendNfcFeature$onfido_capture_sdk_core_release();
            nextAction();
        }
    }

    public void onDocumentCaptured$onfido_capture_sdk_core_release(DocumentSide documentResult, NfcProperties nfcProperties) {
        Intrinsics.checkNotNullParameter(documentResult, "documentResult");
        if (Intrinsics.areEqual(documentResult.getSide().getId(), DocSide.FRONT.getId())) {
            Captures captures = this.uploadResult;
            Document document = new Document(null, null, null, null, null, 31, null);
            document.setType(documentResult.getType());
            document.setFront(documentResult);
            document.setBack(null);
            document.setNfcMediaUUID(null);
            captures.setDocument(document);
        } else {
            Document document2 = this.uploadResult.getDocument();
            if (document2 != null) {
                document2.setBack(documentResult);
            }
        }
        if (nfcProperties != null) {
            this.nfcProperties = nfcProperties;
        }
    }

    public void onDocumentTypeSelected$onfido_capture_sdk_core_release(DocumentType docType, CountryCode countryCode, String genericDocumentTitle, DocumentPages genericDocumentPages) {
        Intrinsics.checkNotNullParameter(docType, "docType");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.documentType = docType;
        this.countryCode = countryCode;
        CaptureType captureType = CaptureType.DOCUMENT;
        CaptureStepDataBundle captureStepDataBundle = new CaptureStepDataBundle(captureType, docType, countryCode, null, DocSide.FRONT, genericDocumentTitle, genericDocumentPages, 8, null);
        removeNfcStep();
        if (this.runtimePermissionsManager.hasPermissionsForCaptureType(captureType)) {
            showCaptureStep$onfido_capture_sdk_core_release(true, captureStepDataBundle, PerformanceAnalyticsScreen.DOCUMENT_TYPE_SELECTION);
        } else {
            showPermissionsScreen(captureStepDataBundle, FlowStepDirection.RIGHT_TO_LEFT);
        }
    }

    public void onFaceCaptured(String faceId) {
        Intrinsics.checkNotNullParameter(faceId, "faceId");
        this.uploadResult.setFace(new Face(faceId, FaceCaptureVariant.PHOTO));
    }

    public void onFaceSelfieCaptureResult(SelfieCaptureScreen.SelfieCaptureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof SelfieCaptureScreen.SelfieCaptureResult.Completed) {
            onFaceCaptured(((SelfieCaptureScreen.SelfieCaptureResult.Completed) result).getUploadId());
            nextAction();
        } else if (result instanceof SelfieCaptureScreen.SelfieCaptureResult.Error) {
            onCaptureException(((SelfieCaptureScreen.SelfieCaptureResult.Error) result).getException());
        } else if (Intrinsics.areEqual(result, SelfieCaptureScreen.SelfieCaptureResult.Back.INSTANCE)) {
            previousAction$onfido_capture_sdk_core_release();
        }
    }

    public void onFlowCompleted$onfido_capture_sdk_core_release() {
        this.flowTracker.trackFlowCompletion();
    }

    public void onFlowExited$onfido_capture_sdk_core_release() {
        this.flowTracker.trackFlowCancellation();
    }

    public void onLivenessCaptureResult(LivenessCaptureScreen.LivenessCaptureResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof LivenessCaptureScreen.LivenessCaptureResult.Completed) {
            LivenessCaptureScreen.LivenessCaptureResult.Completed completed = (LivenessCaptureScreen.LivenessCaptureResult.Completed) result;
            setLivenessVideoOptions(completed.getVideoPath(), completed.getPerformedChallenges());
            nextAction();
        } else if (result instanceof LivenessCaptureScreen.LivenessCaptureResult.Error) {
            onCaptureException(((LivenessCaptureScreen.LivenessCaptureResult.Error) result).getException());
        } else if (Intrinsics.areEqual(result, LivenessCaptureScreen.LivenessCaptureResult.Back.INSTANCE)) {
            previousAction$onfido_capture_sdk_core_release();
        }
    }

    public void onLivenessConfirmationResultReceived$onfido_capture_sdk_core_release(LivenessConfirmationFragment.LivenessConfirmationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof LivenessConfirmationFragment.LivenessConfirmationResult.Exit) {
            previousAction$onfido_capture_sdk_core_release();
            return;
        }
        if (result instanceof LivenessConfirmationFragment.LivenessConfirmationResult.VideoUploadedSuccessfully) {
            onVideoCaptured(((LivenessConfirmationFragment.LivenessConfirmationResult.VideoUploadedSuccessfully) result).getUploadResult());
            return;
        }
        OnfidoView onfidoView = null;
        if (result instanceof LivenessConfirmationFragment.LivenessConfirmationResult.OnError.OnInvalidCertificate) {
            OnfidoView onfidoView2 = this.view;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView2;
            }
            onfidoView.onError(new InvalidCertificateException(((LivenessConfirmationFragment.LivenessConfirmationResult.OnError.OnInvalidCertificate) result).getMessage()));
            return;
        }
        if (Intrinsics.areEqual(result, LivenessConfirmationFragment.LivenessConfirmationResult.OnError.OnTokenExpired.INSTANCE)) {
            OnfidoView onfidoView3 = this.view;
            if (onfidoView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView3;
            }
            onfidoView.onError(TokenExpiredException.INSTANCE);
        }
    }

    public void onMotionCaptured$onfido_capture_sdk_core_release(UploadedArtifact videoResult) {
        Intrinsics.checkNotNullParameter(videoResult, "videoResult");
        this.uploadResult.setFace(new Face(videoResult.getId(), FaceCaptureVariant.MOTION));
        nextAction();
    }

    public void onNavigationStarted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen origin, FlowStep currentStep, PerformanceAnalyticsScreen destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (origin == null) {
            PerformanceAnalyticsScreen.Companion companion = PerformanceAnalyticsScreen.INSTANCE;
            Intrinsics.checkNotNull(currentStep);
            origin = companion.fromFlowAction(currentStep.getAction());
        }
        ScreenLoadTracker.trackNavigationStarted$onfido_capture_sdk_core_release$default(this.screenLoadTracker, origin, destination, null, null, 12, null);
    }

    public void onNfcHostResult$onfido_capture_sdk_core_release(NfcHostFragment.NfcHostResult nfcHostResult) {
        Intrinsics.checkNotNullParameter(nfcHostResult, "nfcHostResult");
        OnfidoView onfidoView = null;
        if (Intrinsics.areEqual(nfcHostResult, NfcHostFragment.NfcHostResult.Exit.INSTANCE)) {
            this.uploadResult.setDocument(null);
            this.nfcProperties = null;
            onBackPressed$onfido_capture_sdk_core_release();
            CollectionsKt.removeAll((List) this.flowSteps, (Function1) new Function1<FlowStep, Boolean>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoPresenter$onNfcHostResult$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FlowStep it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(it.getAction() == FlowAction.NFC_HOST_FEATURE);
                }
            });
            return;
        }
        if (Intrinsics.areEqual(nfcHostResult, NfcHostFragment.NfcHostResult.ExitOnfidoFlow.INSTANCE)) {
            OnfidoView onfidoView2 = this.view;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView2;
            }
            onfidoView.exitFlow(ExitCode.REQUIRED_NFC_FLOW_NOT_COMPLETED);
            return;
        }
        if (Intrinsics.areEqual(nfcHostResult, NfcHostFragment.NfcHostResult.NfcScanSkipped.INSTANCE)) {
            skipNfcScanStep();
            return;
        }
        if (nfcHostResult instanceof NfcHostFragment.NfcHostResult.NfcScanSuccess) {
            OnfidoView onfidoView3 = this.view;
            if (onfidoView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView3;
            }
            onfidoView.showLoadingDialog(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOAD_NFC_DATA);
            NfcHostFragment.NfcHostResult.NfcScanSuccess nfcScanSuccess = (NfcHostFragment.NfcHostResult.NfcScanSuccess) nfcHostResult;
            onNfcScanSucceeded(nfcScanSuccess.getNfcData(), nfcScanSuccess.getNfcFlowType());
        }
    }

    public void onPermissionResult$onfido_capture_sdk_core_release(PermissionResult permissionResult) {
        Intrinsics.checkNotNullParameter(permissionResult, "permissionResult");
        if (permissionResult instanceof PermissionResult.Granted) {
            onPermissionsGranted(((PermissionResult.Granted) permissionResult).getCaptureStepDataBundle());
        } else if (permissionResult instanceof PermissionResult.Canceled) {
            onPermissionScreenDismissed();
        }
    }

    public void onPoaResult$onfido_capture_sdk_core_release(PoaHostFragment.PoaResult poaResult) {
        Intrinsics.checkNotNullParameter(poaResult, "poaResult");
        OnfidoView onfidoView = null;
        if (Intrinsics.areEqual(poaResult, PoaHostFragment.PoaResult.Exit.INSTANCE)) {
            OnfidoView onfidoView2 = this.view;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView2;
            }
            onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
            return;
        }
        if (Intrinsics.areEqual(poaResult, PoaHostFragment.PoaResult.Canceled.INSTANCE)) {
            onBackPressed$onfido_capture_sdk_core_release();
        } else {
            if (!(poaResult instanceof PoaHostFragment.PoaResult.OnDocumentSubmittedResult)) {
                throw new NoWhenBranchMatchedException();
            }
            PoaHostFragment.PoaResult.OnDocumentSubmittedResult onDocumentSubmittedResult = (PoaHostFragment.PoaResult.OnDocumentSubmittedResult) poaResult;
            this.uploadResult.setPoa(new ProofOfAddress(onDocumentSubmittedResult.getType(), new ProofOfAddress.ProofOfAddressDocumentSide(onDocumentSubmittedResult.getDocumentId(), null), null));
            nextAction();
        }
    }

    public void onRestrictedDocumentSelectionResult$onfido_capture_sdk_core_release(DocumentSelectionHostFragment.DocumentSelectionResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof DocumentSelectionHostFragment.DocumentSelectionResult.Exit) {
            previousAction$onfido_capture_sdk_core_release();
        } else if (result instanceof DocumentSelectionHostFragment.DocumentSelectionResult.Completed) {
            DocumentSelectionHostFragment.DocumentSelectionResult.Completed completed = (DocumentSelectionHostFragment.DocumentSelectionResult.Completed) result;
            onDocumentTypeSelected$onfido_capture_sdk_core_release(completed.getDocumentType(), completed.getCountryCode(), completed.getGenericDocTitle(), completed.getGenericDocPages());
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.NfcDataServiceListener
    public void onUploadError(ErrorType errorType) {
        InvalidCertificateException invalidCertificateException;
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        OnfidoView onfidoView = null;
        if (errorType instanceof ErrorType.TokenExpired) {
            OnfidoView onfidoView2 = this.view;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView2;
            }
            onfidoView.onError(TokenExpiredException.INSTANCE);
            return;
        }
        if (errorType instanceof ErrorType.InvalidCertificate) {
            OnfidoView onfidoView3 = this.view;
            if (onfidoView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView3;
            }
            invalidCertificateException = new InvalidCertificateException(((ErrorType.InvalidCertificate) errorType).getMessage());
        } else {
            OnfidoView onfidoView4 = this.view;
            if (onfidoView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView4;
            }
            invalidCertificateException = new InvalidCertificateException(errorType.toString());
        }
        onfidoView.onError(invalidCertificateException);
    }

    public void onUserConsentResult$onfido_capture_sdk_core_release(UserConsentFragment.UserConsentResult userConsentResult) {
        Intrinsics.checkNotNullParameter(userConsentResult, "userConsentResult");
        OnfidoView onfidoView = null;
        if (userConsentResult instanceof UserConsentFragment.UserConsentResult.ConsentAccepted) {
            if (!OnfidoConfigExtensionsKt.inWorkflowMode(getOnfidoConfig())) {
                nextAction();
                removeStep(FlowAction.USER_CONSENT);
                return;
            } else {
                OnfidoView onfidoView2 = this.view;
                if (onfidoView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    onfidoView = onfidoView2;
                }
                onfidoView.showWorkflowFragment(getOnfidoConfig().getWorkflowConfig());
            }
        } else {
            if (!(userConsentResult instanceof UserConsentFragment.UserConsentResult.ConsentRejected)) {
                if (Intrinsics.areEqual(userConsentResult, UserConsentFragment.UserConsentResult.ConsentExit.INSTANCE)) {
                    OnfidoView onfidoView3 = this.view;
                    if (onfidoView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        onfidoView = onfidoView3;
                    }
                    onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
                    return;
                }
                return;
            }
            OnfidoView onfidoView4 = this.view;
            if (onfidoView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView4;
            }
            onfidoView.exitFlow(ExitCode.USER_CONSENT_DENIED);
        }
        this.orchestrationUserConsentVisibility = Visibility.GONE;
    }

    public void onViewStarted$onfido_capture_sdk_core_release(NavigationManager navigationManager) {
        Intrinsics.checkNotNullParameter(navigationManager, "navigationManager");
        getNavigationManagerHolder().setNavigationManager(navigationManager);
    }

    public void onViewStopped() {
        this.compositeDisposable.clear();
        NfcDataService nfcDataService = this.nfcDataService;
        if (nfcDataService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcDataService");
            nfcDataService = null;
        }
        nfcDataService.stop();
        getNavigationManagerHolder().resetNavigationManager();
    }

    public void onWebPoaResult$onfido_capture_sdk_core_release(HostedWebModuleResult result) {
        ProofOfAddressCaptureSDKOutput proofOfAddressCaptureSDKOutput;
        Intrinsics.checkNotNullParameter(result, "result");
        OnfidoView onfidoView = null;
        OnfidoView onfidoView2 = null;
        if (result instanceof HostedWebModuleExit) {
            OnfidoView onfidoView3 = this.view;
            if (onfidoView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView = onfidoView3;
            }
            onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
            return;
        }
        if (result instanceof HostedWebModuleFailed) {
            OnfidoView onfidoView4 = this.view;
            if (onfidoView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                onfidoView2 = onfidoView4;
            }
            onfidoView2.onError(new OnfidoException(((HostedWebModuleFailed) result).getBody()));
            return;
        }
        if (result instanceof HostedWebModuleCancelled) {
            onBackPressed$onfido_capture_sdk_core_release();
            return;
        }
        if (!(result instanceof HostedWebModuleSuccess)) {
            throw new NoWhenBranchMatchedException();
        }
        try {
            Json jsonParserInstance = JsonParserFactoryKt.getJsonParserInstance();
            proofOfAddressCaptureSDKOutput = (ProofOfAddressCaptureSDKOutput) jsonParserInstance.decodeFromString(SerializersKt.serializer(jsonParserInstance.getSerializersModule(), Reflection.typeOf(ProofOfAddressCaptureSDKOutput.class)), ((HostedWebModuleSuccess) result).getBody());
        } catch (SerializationException unused) {
            proofOfAddressCaptureSDKOutput = null;
        }
        if (proofOfAddressCaptureSDKOutput == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        this.uploadResult.setPoa(new ProofOfAddress(proofOfAddressCaptureSDKOutput.getType(), new ProofOfAddress.ProofOfAddressDocumentSide(proofOfAddressCaptureSDKOutput.getSides().getFront().getId(), proofOfAddressCaptureSDKOutput.getSides().getFront().getType()), proofOfAddressCaptureSDKOutput.getSides().getBack() != null ? new ProofOfAddress.ProofOfAddressDocumentSide(proofOfAddressCaptureSDKOutput.getSides().getBack().getId(), proofOfAddressCaptureSDKOutput.getSides().getBack().getType()) : null));
        nextAction();
    }

    public void previousAction$onfido_capture_sdk_core_release() {
        int i = this.flowIndex;
        if (i > 0) {
            setActionWithIndex(i - 1);
            return;
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
    }

    public void removeLoggerTree$onfido_capture_sdk_core_release() {
        Timber.Companion companion = Timber.INSTANCE;
        if (companion.forest().contains(this.remoteLoggerTree)) {
            this.remoteLoggerTree.onUproot$onfido_capture_sdk_core_release();
            companion.uproot(this.remoteLoggerTree);
        }
    }

    public void setLivenessVideoOptions(String videoPath, LivenessPerformedChallenges livenessPerformedChallenges) {
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(livenessPerformedChallenges, "livenessPerformedChallenges");
        boolean zIsVideoAvailableOnConfirmationScreen = isVideoAvailableOnConfirmationScreen();
        FlowStep flowStepFindFlowStep = findFlowStep(FlowAction.CAPTURE_LIVENESS_CONFIRMATION);
        if (flowStepFindFlowStep == null) {
            return;
        }
        flowStepFindFlowStep.setOptions(new LivenessConfirmationOptions(videoPath, livenessPerformedChallenges, zIsVideoAvailableOnConfirmationScreen));
    }

    public void setState$onfido_capture_sdk_core_release(State value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.flowSteps = CollectionsKt.toMutableList((Collection) value.getFlowSteps());
        this.flowIndex = value.getFlowIndex();
        this.uploadResult = value.getCaptures();
        this.documentType = value.getDocumentType();
        this.countryCode = value.getCountryCode();
        this.nfcProperties = value.getNfcProperties();
    }

    public void setup$onfido_capture_sdk_core_release(OnfidoView view, State state) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.nfcDataService = new NfcDataService(this.nfcDataRepository, this, this.schedulersProvider.getIo(), this.schedulersProvider.getUi(), this.waitingScreenTracker);
        if (state != null) {
            setState$onfido_capture_sdk_core_release(state);
        }
        addLoggerTree();
    }

    public void showCaptureStep$onfido_capture_sdk_core_release(boolean isFrontSide, CaptureStepDataBundle captureBundle, PerformanceAnalyticsScreen currentScreen) {
        Intrinsics.checkNotNullParameter(captureBundle, "captureBundle");
        Intrinsics.checkNotNullParameter(currentScreen, "currentScreen");
        this.screenLoadTracker.trackNavigationStarted$onfido_capture_sdk_core_release(currentScreen, PerformanceAnalyticsScreen.DOCUMENT_CAPTURE, captureBundle.getDocumentType(), isFrontSide ? DocSide.FRONT : DocSide.BACK);
        NfcArguments nfcArguments = getNfcArguments();
        if (this.onfidoRemoteConfig.isRefactoredCaptureEnabled()) {
            openNewDocumentCapture(isFrontSide, captureBundle, nfcArguments);
            return;
        }
        if (isFrontSide) {
            showEmptyScreen();
        }
        OnfidoView onfidoView = this.view;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            onfidoView = null;
        }
        onfidoView.showDocumentCapture(isFrontSide, captureBundle, nfcArguments);
    }

    public boolean useLocalBackNavigation$onfido_capture_sdk_core_release() {
        if (OnfidoConfigExtensionsKt.inWorkflowMode(getOnfidoConfig())) {
            if (this.orchestrationUserConsentVisibility == Visibility.GONE) {
                return true;
            }
        } else {
            if (ArraysKt.contains(new FlowAction[]{FlowAction.ACTIVE_VIDEO_CAPTURE, FlowAction.NFC_HOST_FEATURE}, getCurrentAction())) {
                return true;
            }
            if (getCurrentAction() == FlowAction.PROOF_OF_ADDRESS && !this.onfidoRemoteConfig.getEnableWebModuleBasedPoa()) {
                return true;
            }
        }
        return false;
    }
}
