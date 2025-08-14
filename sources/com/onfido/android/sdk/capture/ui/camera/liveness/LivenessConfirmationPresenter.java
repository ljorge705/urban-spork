package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.onfido.android.sdk.capture.audio.VolumeManager;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.CaptureStatus;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifactKt;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.ReciteLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.video_view.LivenessReviewChallenge;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.ResultReceiverExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.exception.RequestEntityTooLargeException;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.sentry.protocol.Message;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000 Y2\u00020\u0001:\u0003YZ[B[\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016J\u001a\u0010&\u001a\u00020'2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020)H\u0016J\u0012\u0010*\u001a\u00020'2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0012J\b\u00101\u001a\u00020'H\u0012J\u0016\u00102\u001a\b\u0012\u0004\u0012\u000204032\u0006\u00105\u001a\u000206H\u0012J\u0010\u00107\u001a\u00020'2\u0006\u00108\u001a\u00020)H\u0016J\u0010\u00109\u001a\u00020'2\u0006\u0010:\u001a\u00020;H\u0012J\u0010\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020>H\u0012J\b\u0010?\u001a\u00020'H\u0016J\u0010\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020\u001eH\u0016J\u001b\u0010B\u001a\u00020'2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0DH\u0016¢\u0006\u0002\u0010FJ\u0018\u0010G\u001a\u00020H2\u0006\u00105\u001a\u0002062\u0006\u0010/\u001a\u000200H\u0012J\u0010\u0010I\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010J\u001a\u00020'H\u0016J\b\u0010K\u001a\u00020'H\u0016J\u0010\u0010L\u001a\u00020'2\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020'H\u0016J\b\u0010P\u001a\u00020'H\u0016J\b\u0010Q\u001a\u00020'H\u0016J\b\u0010R\u001a\u00020'H\u0016J\u0018\u0010S\u001a\u00020'2\u0006\u00105\u001a\u0002062\u0006\u0010T\u001a\u00020,H\u0016J\u001e\u0010U\u001a\b\u0012\u0004\u0012\u00020;0V2\u0006\u0010W\u001a\u00020H2\u0006\u0010X\u001a\u00020.H\u0012R\u001b\u0010\u0017\u001a\u00020\u00188RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0092\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0092\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00188RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b \u0010\u001aR\u000e\u0010\"\u001a\u00020#X\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0092.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationPresenter;", "", "onfidoAPI", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "sdkUploadMetadataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "payloadHelper", "Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "livenessCaptureSettings", "Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;", "volumeManager", "Lcom/onfido/android/sdk/capture/audio/VolumeManager;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "mediaCallback", "Landroid/os/ResultReceiver;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;Lcom/onfido/api/client/data/SdkConfiguration$LivenessCapture;Lcom/onfido/android/sdk/capture/audio/VolumeManager;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Landroid/os/ResultReceiver;)V", "compositeSubscription", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeSubscription", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeSubscription$delegate", "Lkotlin/Lazy;", "currentVideoTimestamp", "", "uploadVideoCompositeSubscription", "getUploadVideoCompositeSubscription", "uploadVideoCompositeSubscription$delegate", "videoUploadStartTime", "", "view", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationPresenter$View;", "attachView", "", "showRecordedVideo", "", "deleteVideoFile", "videoPath", "", "getPayloadIntegrity", "Lcom/onfido/api/client/data/PayloadIntegrity;", "videoFile", "Ljava/io/File;", "hideLoading", "mapChallenges", "", "Lcom/onfido/api/client/data/LiveVideoChallenges$LiveVideoChallenge;", "performedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "onStart", "isPortrait", "onUploadCompleted", "videoUpload", "Lcom/onfido/api/client/data/LiveVideoUpload;", "onUploadError", "throwable", "", "onVideoFinished", "onVideoPaused", "timestamp", "onVideoStarted", "challenges", "", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/video_view/LivenessReviewChallenge;", "([Lcom/onfido/android/sdk/capture/ui/camera/liveness/video_view/LivenessReviewChallenge;)V", "prepareRequestParams", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationPresenter$LiveVideoUploadRequestParams;", "stop", "trackFaceVideoConfirmationRetakeButtonClicked", "trackFaceVideoConfirmationUploadButtonClicked", "trackFaceVideoConfirmationUploadStatus", "captureStatus", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/CaptureStatus;", "trackUploadCompleted", "trackUploadStarted", "trackVideoError", "turnVolumeOn", "uploadVideo", "filePath", "videoUploadRequest", "Lio/reactivex/rxjava3/core/Observable;", Message.JsonKeys.PARAMS, "payloadIntegrity", "Companion", "LiveVideoUploadRequestParams", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessConfirmationPresenter {
    private static final String MP4_EXTENSION = "mp4";
    public static final String MP4_MIME = "video/mp4";
    public static final String SDK_LANGUAGE_SOURCE = "sdk";
    private static final long VOLUME_CHECK_PERIOD_MS = 1000;

    /* renamed from: compositeSubscription$delegate, reason: from kotlin metadata */
    private final Lazy compositeSubscription;
    private int currentVideoTimestamp;
    private final SdkConfiguration.LivenessCapture livenessCaptureSettings;
    private final LivenessTracker livenessTracker;
    private final ResultReceiver mediaCallback;
    private final OnfidoApiService onfidoAPI;
    private final PayloadHelper payloadHelper;
    private final SchedulersProvider schedulersProvider;
    private final SdkUploadMetadataHelper sdkUploadMetadataHelper;
    private final TimeProvider timeProvider;

    /* renamed from: uploadVideoCompositeSubscription$delegate, reason: from kotlin metadata */
    private final Lazy uploadVideoCompositeSubscription;
    private long videoUploadStartTime;
    private View view;
    private final VolumeManager volumeManager;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J[\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationPresenter$LiveVideoUploadRequestParams;", "", "videoFileName", "", "videoFileBytes", "", "challengesId", "challenges", "", "Lcom/onfido/api/client/data/LiveVideoChallenges$LiveVideoChallenge;", "endChallengeTimestamp", "", "sdkLanguages", "Lcom/onfido/api/client/data/LiveVideoLanguage;", "videoFileType", "(Ljava/lang/String;[BLjava/lang/String;Ljava/util/List;JLjava/util/List;Ljava/lang/String;)V", "getChallenges", "()Ljava/util/List;", "getChallengesId", "()Ljava/lang/String;", "getEndChallengeTimestamp", "()J", "getSdkLanguages", "getVideoFileBytes", "()[B", "getVideoFileName", "getVideoFileType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class LiveVideoUploadRequestParams {
        private final List<LiveVideoChallenges.LiveVideoChallenge> challenges;
        private final String challengesId;
        private final long endChallengeTimestamp;
        private final List<LiveVideoLanguage> sdkLanguages;
        private final byte[] videoFileBytes;
        private final String videoFileName;
        private final String videoFileType;

        /* JADX WARN: Multi-variable type inference failed */
        public LiveVideoUploadRequestParams(String videoFileName, byte[] videoFileBytes, String challengesId, List<? extends LiveVideoChallenges.LiveVideoChallenge> challenges, long j, List<LiveVideoLanguage> sdkLanguages, String videoFileType) {
            Intrinsics.checkNotNullParameter(videoFileName, "videoFileName");
            Intrinsics.checkNotNullParameter(videoFileBytes, "videoFileBytes");
            Intrinsics.checkNotNullParameter(challengesId, "challengesId");
            Intrinsics.checkNotNullParameter(challenges, "challenges");
            Intrinsics.checkNotNullParameter(sdkLanguages, "sdkLanguages");
            Intrinsics.checkNotNullParameter(videoFileType, "videoFileType");
            this.videoFileName = videoFileName;
            this.videoFileBytes = videoFileBytes;
            this.challengesId = challengesId;
            this.challenges = challenges;
            this.endChallengeTimestamp = j;
            this.sdkLanguages = sdkLanguages;
            this.videoFileType = videoFileType;
        }

        /* renamed from: component1, reason: from getter */
        public final String getVideoFileName() {
            return this.videoFileName;
        }

        /* renamed from: component2, reason: from getter */
        public final byte[] getVideoFileBytes() {
            return this.videoFileBytes;
        }

        /* renamed from: component3, reason: from getter */
        public final String getChallengesId() {
            return this.challengesId;
        }

        public final List<LiveVideoChallenges.LiveVideoChallenge> component4() {
            return this.challenges;
        }

        /* renamed from: component5, reason: from getter */
        public final long getEndChallengeTimestamp() {
            return this.endChallengeTimestamp;
        }

        public final List<LiveVideoLanguage> component6() {
            return this.sdkLanguages;
        }

        /* renamed from: component7, reason: from getter */
        public final String getVideoFileType() {
            return this.videoFileType;
        }

        public final LiveVideoUploadRequestParams copy(String videoFileName, byte[] videoFileBytes, String challengesId, List<? extends LiveVideoChallenges.LiveVideoChallenge> challenges, long endChallengeTimestamp, List<LiveVideoLanguage> sdkLanguages, String videoFileType) {
            Intrinsics.checkNotNullParameter(videoFileName, "videoFileName");
            Intrinsics.checkNotNullParameter(videoFileBytes, "videoFileBytes");
            Intrinsics.checkNotNullParameter(challengesId, "challengesId");
            Intrinsics.checkNotNullParameter(challenges, "challenges");
            Intrinsics.checkNotNullParameter(sdkLanguages, "sdkLanguages");
            Intrinsics.checkNotNullParameter(videoFileType, "videoFileType");
            return new LiveVideoUploadRequestParams(videoFileName, videoFileBytes, challengesId, challenges, endChallengeTimestamp, sdkLanguages, videoFileType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiveVideoUploadRequestParams)) {
                return false;
            }
            LiveVideoUploadRequestParams liveVideoUploadRequestParams = (LiveVideoUploadRequestParams) other;
            return Intrinsics.areEqual(this.videoFileName, liveVideoUploadRequestParams.videoFileName) && Arrays.equals(this.videoFileBytes, liveVideoUploadRequestParams.videoFileBytes) && Intrinsics.areEqual(this.challengesId, liveVideoUploadRequestParams.challengesId) && Intrinsics.areEqual(this.challenges, liveVideoUploadRequestParams.challenges) && this.endChallengeTimestamp == liveVideoUploadRequestParams.endChallengeTimestamp && Intrinsics.areEqual(this.sdkLanguages, liveVideoUploadRequestParams.sdkLanguages) && Intrinsics.areEqual(this.videoFileType, liveVideoUploadRequestParams.videoFileType);
        }

        public final List<LiveVideoChallenges.LiveVideoChallenge> getChallenges() {
            return this.challenges;
        }

        public final String getChallengesId() {
            return this.challengesId;
        }

        public final long getEndChallengeTimestamp() {
            return this.endChallengeTimestamp;
        }

        public final List<LiveVideoLanguage> getSdkLanguages() {
            return this.sdkLanguages;
        }

        public final byte[] getVideoFileBytes() {
            return this.videoFileBytes;
        }

        public final String getVideoFileName() {
            return this.videoFileName;
        }

        public final String getVideoFileType() {
            return this.videoFileType;
        }

        public int hashCode() {
            return (((((((((((this.videoFileName.hashCode() * 31) + Arrays.hashCode(this.videoFileBytes)) * 31) + this.challengesId.hashCode()) * 31) + this.challenges.hashCode()) * 31) + Long.hashCode(this.endChallengeTimestamp)) * 31) + this.sdkLanguages.hashCode()) * 31) + this.videoFileType.hashCode();
        }

        public String toString() {
            return "LiveVideoUploadRequestParams(videoFileName=" + this.videoFileName + ", videoFileBytes=" + Arrays.toString(this.videoFileBytes) + ", challengesId=" + this.challengesId + ", challenges=" + this.challenges + ", endChallengeTimestamp=" + this.endChallengeTimestamp + ", sdkLanguages=" + this.sdkLanguages + ", videoFileType=" + this.videoFileType + ')';
        }

        public /* synthetic */ LiveVideoUploadRequestParams(String str, byte[] bArr, String str2, List list, long j, List list2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, bArr, str2, list, j, list2, (i & 64) != 0 ? "video/mp4" : str3);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0003H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0003H&¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationPresenter$View;", "", "onInvalidCertificate", "", "message", "", "onMediaPlayerError", "onNextChallenge", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "onNoVolumeDetected", "onTokenExpired", "onVideoNotFound", "onVideoTooLarge", "onVideoUploadError", "onVideoUploaded", "liveVideoUpload", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "onVolumeDetected", "setLoading", ViewProps.ENABLED, "", "showNoVideoConfirmationView", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        void onInvalidCertificate(String message);

        void onMediaPlayerError();

        void onNextChallenge(LivenessChallenge challenge);

        void onNoVolumeDetected();

        void onTokenExpired();

        void onVideoNotFound();

        void onVideoTooLarge();

        void onVideoUploadError();

        void onVideoUploaded(UploadedArtifact liveVideoUpload);

        void onVolumeDetected();

        void setLoading(boolean enabled);

        void showNoVideoConfirmationView();
    }

    @Inject
    public LivenessConfirmationPresenter(OnfidoApiService onfidoAPI, SdkUploadMetadataHelper sdkUploadMetadataHelper, PayloadHelper payloadHelper, SdkConfiguration.LivenessCapture livenessCaptureSettings, VolumeManager volumeManager, SchedulersProvider schedulersProvider, LivenessTracker livenessTracker, TimeProvider timeProvider, WaitingScreenTracker waitingScreenTracker, ResultReceiver resultReceiver) {
        Intrinsics.checkNotNullParameter(onfidoAPI, "onfidoAPI");
        Intrinsics.checkNotNullParameter(sdkUploadMetadataHelper, "sdkUploadMetadataHelper");
        Intrinsics.checkNotNullParameter(payloadHelper, "payloadHelper");
        Intrinsics.checkNotNullParameter(livenessCaptureSettings, "livenessCaptureSettings");
        Intrinsics.checkNotNullParameter(volumeManager, "volumeManager");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        this.onfidoAPI = onfidoAPI;
        this.sdkUploadMetadataHelper = sdkUploadMetadataHelper;
        this.payloadHelper = payloadHelper;
        this.livenessCaptureSettings = livenessCaptureSettings;
        this.volumeManager = volumeManager;
        this.schedulersProvider = schedulersProvider;
        this.livenessTracker = livenessTracker;
        this.timeProvider = timeProvider;
        this.waitingScreenTracker = waitingScreenTracker;
        this.mediaCallback = resultReceiver;
        this.compositeSubscription = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$compositeSubscription$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        this.uploadVideoCompositeSubscription = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$uploadVideoCompositeSubscription$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
    }

    public static /* synthetic */ void attachView$default(LivenessConfirmationPresenter livenessConfirmationPresenter, View view, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: attachView");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        livenessConfirmationPresenter.attachView(view, z);
    }

    private CompositeDisposable getCompositeSubscription() {
        return (CompositeDisposable) this.compositeSubscription.getValue();
    }

    private PayloadIntegrity getPayloadIntegrity(File videoFile) {
        boolean zIsPayloadSigningEnabled = this.livenessCaptureSettings.isPayloadSigningEnabled();
        return this.payloadHelper.getSignedPayload(videoFile, this.sdkUploadMetadataHelper.create(), zIsPayloadSigningEnabled);
    }

    private CompositeDisposable getUploadVideoCompositeSubscription() {
        return (CompositeDisposable) this.uploadVideoCompositeSubscription.getValue();
    }

    private void hideLoading() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.setLoading(false);
        this.waitingScreenTracker.trackWaitingScreenCompletion(new LoadingFragment.Companion.DialogMode.Uploading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_VIDEO).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_UPLOADING_VIDEO);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<LiveVideoChallenges.LiveVideoChallenge> mapChallenges(LivenessPerformedChallenges performedChallenges) {
        Object movementQuery;
        List<LivenessPerformedChallenge> challenges = performedChallenges.getChallenges();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(challenges, 10));
        for (LivenessPerformedChallenge livenessPerformedChallenge : challenges) {
            LivenessChallenge livenessChallenge = livenessPerformedChallenge.getLivenessChallenge();
            int i = 2;
            String str = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            if (livenessChallenge instanceof ReciteLivenessChallenge) {
                movementQuery = new LiveVideoChallenges.LiveVideoChallenge.ReciteQuery(ArraysKt.toList(((ReciteLivenessChallenge) livenessPerformedChallenge.getLivenessChallenge()).getQuery()), str, i, objArr3 == true ? 1 : 0);
            } else {
                if (!(livenessChallenge instanceof MovementLivenessChallenge)) {
                    throw new NoWhenBranchMatchedException();
                }
                movementQuery = new LiveVideoChallenges.LiveVideoChallenge.MovementQuery(((MovementLivenessChallenge) livenessPerformedChallenge.getLivenessChallenge()).getQuery().getId(), objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
            }
            arrayList.add(movementQuery);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUploadCompleted(LiveVideoUpload videoUpload) {
        trackUploadCompleted();
        hideLoading();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onVideoUploaded(UploadedArtifactKt.toUploadedArtifact(videoUpload));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUploadError(Throwable throwable) {
        Timber.INSTANCE.e(throwable, "Error uploading liveness video - " + getClass().getName(), new Object[0]);
        hideLoading();
        View view = null;
        if (throwable instanceof SSLPeerUnverifiedException) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = "SSLPeerUnverified error";
            }
            view.onInvalidCertificate(localizedMessage);
            return;
        }
        if (throwable instanceof TokenExpiredException) {
            View view3 = this.view;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view3;
            }
            view.onTokenExpired();
            return;
        }
        if (throwable instanceof RequestEntityTooLargeException) {
            View view4 = this.view;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view4;
            }
            view.onVideoTooLarge();
            return;
        }
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view5;
        }
        view.onVideoUploadError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onVideoStarted$lambda$6(LivenessConfirmationPresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zIsVolumeOn = this$0.volumeManager.isVolumeOn();
        View view = null;
        View view2 = this$0.view;
        if (zIsVolumeOn) {
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.onVolumeDetected();
            return;
        }
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view2;
        }
        view.onNoVolumeDetected();
    }

    private LiveVideoUploadRequestParams prepareRequestParams(LivenessPerformedChallenges performedChallenges, File videoFile) {
        String name = videoFile.getName();
        byte[] bytes = FilesKt.readBytes(videoFile);
        String id = performedChallenges.getId();
        List<LiveVideoChallenges.LiveVideoChallenge> listMapChallenges = mapChallenges(performedChallenges);
        long endChallengeTimestamp = performedChallenges.getChallenges().get(CollectionsKt.getLastIndex(listMapChallenges) - 1).getEndChallengeTimestamp();
        List listListOf = CollectionsKt.listOf(new LiveVideoLanguage("sdk", Locale.getDefault().getLanguage()));
        Intrinsics.checkNotNull(name);
        return new LiveVideoUploadRequestParams(name, bytes, id, listMapChallenges, endChallengeTimestamp, listListOf, null, 64, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadVideo$lambda$0(File videoFile) {
        Intrinsics.checkNotNullParameter(videoFile, "$videoFile");
        videoFile.delete();
    }

    private Observable<LiveVideoUpload> videoUploadRequest(LiveVideoUploadRequestParams params, PayloadIntegrity payloadIntegrity) {
        return this.onfidoAPI.uploadLiveVideo$onfido_capture_sdk_core_release(new OnfidoApiService.VideoUploadParams(params.getVideoFileName(), params.getVideoFileType(), params.getVideoFileBytes(), params.getChallengesId(), params.getChallenges(), Long.valueOf(params.getEndChallengeTimestamp()), params.getSdkLanguages()), payloadIntegrity);
    }

    public void attachView(View view, boolean showRecordedVideo) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        if (showRecordedVideo) {
            return;
        }
        view.showNoVideoConfirmationView();
    }

    public void deleteVideoFile(String videoPath) {
        if (videoPath == null) {
            return;
        }
        try {
            File file = new File(videoPath);
            if (file.exists()) {
                file.delete();
            }
        } catch (SecurityException e) {
            Timber.INSTANCE.e(getClass().getName(), "Can't delete video", e);
        }
    }

    public void onStart(boolean isPortrait) {
        LivenessTracker.trackFaceCapture$onfido_capture_sdk_core_release$default(this.livenessTracker, true, isPortrait, CaptureType.VIDEO, null, 0, 0, 56, null);
    }

    public void onVideoFinished() {
        this.currentVideoTimestamp = 0;
        getCompositeSubscription().clear();
    }

    public void onVideoPaused(int timestamp) {
        this.currentVideoTimestamp = timestamp;
        getCompositeSubscription().clear();
    }

    public void onVideoStarted(LivenessReviewChallenge[] challenges) {
        View view;
        Object next;
        Intrinsics.checkNotNullParameter(challenges, "challenges");
        final List listSortedWith = ArraysKt.sortedWith(challenges, new Comparator() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$onVideoStarted$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((LivenessReviewChallenge) t).getEndTimestamp()), Long.valueOf(((LivenessReviewChallenge) t2).getEndTimestamp()));
            }
        });
        Iterator<Integer> it = new IntRange(0, CollectionsKt.getLastIndex(listSortedWith) - 1).iterator();
        while (it.hasNext()) {
            final int iNextInt = ((IntIterator) it).nextInt();
            if (((LivenessReviewChallenge) listSortedWith.get(iNextInt)).getEndTimestamp() > this.currentVideoTimestamp) {
                CompositeDisposable compositeSubscription = getCompositeSubscription();
                Disposable disposableSubscribe = Observable.timer(((LivenessReviewChallenge) listSortedWith.get(iNextInt)).getEndTimestamp() - this.currentVideoTimestamp, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$onVideoStarted$1$1
                    public final Integer apply(long j) {
                        return Integer.valueOf(iNextInt);
                    }

                    @Override // io.reactivex.rxjava3.functions.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply(((Number) obj).longValue());
                    }
                }).observeOn(this.schedulersProvider.getUi()).subscribeOn(this.schedulersProvider.getIo()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$onVideoStarted$1$2
                    public final void accept(int i) {
                        LivenessConfirmationPresenter.View view2 = this.this$0.view;
                        if (view2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("view");
                            view2 = null;
                        }
                        view2.onNextChallenge(listSortedWith.get(iNextInt + 1).getLivenessChallenge());
                    }

                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public /* bridge */ /* synthetic */ void accept(Object obj) {
                        accept(((Number) obj).intValue());
                    }
                }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$onVideoStarted$1$3
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Throwable it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        Timber.INSTANCE.e(this.this$0.getClass().getName(), "Error setting up the timer: " + it2.getMessage());
                    }
                });
                Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
                RxExtensionsKt.plusAssign(compositeSubscription, disposableSubscribe);
            }
        }
        Iterator it2 = listSortedWith.iterator();
        while (true) {
            view = null;
            if (!it2.hasNext()) {
                next = null;
                break;
            } else {
                next = it2.next();
                if (((LivenessReviewChallenge) next).getEndTimestamp() > this.currentVideoTimestamp) {
                    break;
                }
            }
        }
        LivenessReviewChallenge livenessReviewChallenge = (LivenessReviewChallenge) next;
        if (livenessReviewChallenge != null) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.onNextChallenge(livenessReviewChallenge.getLivenessChallenge());
        }
        CompositeDisposable compositeSubscription2 = getCompositeSubscription();
        Disposable disposableSubscribe2 = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.onVideoStarted.4
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }

            public final Pair<Boolean, Long> apply(long j) {
                return TuplesKt.to(Boolean.valueOf(LivenessConfirmationPresenter.this.volumeManager.isVolumeOn()), Long.valueOf(j));
            }
        }).distinctUntilChanged((Function<? super R, K>) new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.onVideoStarted.5
            @Override // io.reactivex.rxjava3.functions.Function
            public final Boolean apply(Pair<Boolean, Long> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                Boolean boolComponent1 = pair.component1();
                boolComponent1.booleanValue();
                return boolComponent1;
            }
        }).doOnDispose(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LivenessConfirmationPresenter.onVideoStarted$lambda$6(this.f$0);
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.onVideoStarted.7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<Boolean, Long> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                boolean zBooleanValue = pair.component1().booleanValue();
                pair.component2().longValue();
                View view3 = null;
                if (zBooleanValue) {
                    View view4 = LivenessConfirmationPresenter.this.view;
                    if (view4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    } else {
                        view3 = view4;
                    }
                    view3.onVolumeDetected();
                    return;
                }
                View view5 = LivenessConfirmationPresenter.this.view;
                if (view5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                } else {
                    view3 = view5;
                }
                view3.onNoVolumeDetected();
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.onVideoStarted.8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it3) {
                Intrinsics.checkNotNullParameter(it3, "it");
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeSubscription2, disposableSubscribe2);
    }

    public void stop(int currentVideoTimestamp) {
        getCompositeSubscription().clear();
        getUploadVideoCompositeSubscription().clear();
        this.currentVideoTimestamp = currentVideoTimestamp;
    }

    public void trackFaceVideoConfirmationRetakeButtonClicked() {
        this.livenessTracker.trackFaceVideoConfirmationRetakeButtonClicked$onfido_capture_sdk_core_release();
    }

    public void trackFaceVideoConfirmationUploadButtonClicked() {
        this.livenessTracker.trackFaceVideoConfirmationUploadButtonClicked$onfido_capture_sdk_core_release();
    }

    public void trackFaceVideoConfirmationUploadStatus(CaptureStatus captureStatus) {
        Intrinsics.checkNotNullParameter(captureStatus, "captureStatus");
        this.livenessTracker.trackFaceVideoConfirmationUploadStatus$onfido_capture_sdk_core_release(captureStatus, this.timeProvider.getCurrentTimestamp() - this.videoUploadStartTime);
    }

    public void trackUploadCompleted() {
        this.livenessTracker.trackUploadCompleted$onfido_capture_sdk_core_release(this.timeProvider.getCurrentTimestamp() - this.videoUploadStartTime);
    }

    public void trackUploadStarted() {
        this.videoUploadStartTime = this.timeProvider.getCurrentTimestamp();
        this.livenessTracker.trackUploadStarted$onfido_capture_sdk_core_release();
    }

    public void trackVideoError() {
        this.livenessTracker.trackFaceConfirmationVideoError$onfido_capture_sdk_core_release();
    }

    public void turnVolumeOn() {
        this.volumeManager.turnVolumeOn();
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
            view = null;
        }
        view.onVolumeDetected();
    }

    public void uploadVideo(LivenessPerformedChallenges performedChallenges, String filePath) {
        Intrinsics.checkNotNullParameter(performedChallenges, "performedChallenges");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        trackUploadStarted();
        final File file = new File(filePath);
        View view = null;
        if (!file.exists()) {
            View view2 = this.view;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            } else {
                view = view2;
            }
            view.onVideoNotFound();
            return;
        }
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        } else {
            view = view3;
        }
        view.setLoading(true);
        Observable<LiveVideoUpload> observableVideoUploadRequest = videoUploadRequest(prepareRequestParams(performedChallenges, file), getPayloadIntegrity(file));
        ResultReceiver resultReceiver = this.mediaCallback;
        if (resultReceiver != null) {
            ResultReceiverExtensionsKt.sendMedia(resultReceiver, FilesKt.readBytes(file), this.schedulersProvider.getIo(), BundleKt.bundleOf(TuplesKt.to(MediaCallbackResultReceiver.KEY_CAPTURE_TYPE, CaptureType.VIDEO), TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_TYPE, "mp4"), TuplesKt.to(MediaCallbackResultReceiver.KEY_FILE_NAME, file.getName())));
        }
        CompositeDisposable uploadVideoCompositeSubscription = getUploadVideoCompositeSubscription();
        Disposable disposableSubscribe = observableVideoUploadRequest.subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LivenessConfirmationPresenter.uploadVideo$lambda$0(file);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.uploadVideo.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(LiveVideoUpload videoUpload) {
                Intrinsics.checkNotNullParameter(videoUpload, "videoUpload");
                LivenessConfirmationPresenter.this.onUploadCompleted(videoUpload);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationPresenter.uploadVideo.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                LivenessConfirmationPresenter.this.onUploadError(throwable);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(uploadVideoCompositeSubscription, disposableSubscribe);
    }
}
