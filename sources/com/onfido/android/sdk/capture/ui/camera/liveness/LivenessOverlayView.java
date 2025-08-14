package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoChallengeMovementBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoLivenessOverlayViewBinding;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementType;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.ReciteLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.turn.LivenessProgressArrow;
import com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.javax.inject.Inject;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001;B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010$\u001a\u00020%H\u0014J\b\u0010&\u001a\u00020%H\u0014J\b\u0010'\u001a\u00020%H\u0016J\b\u0010(\u001a\u00020%H\u0016J\b\u0010)\u001a\u00020%H\u0016J\u0010\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020%H\u0016J\u0015\u0010.\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b/J\r\u00100\u001a\u00020%H\u0000¢\u0006\u0002\b1J\u001d\u00102\u001a\u00020%2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0000¢\u0006\u0002\b7J\u0015\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020\rH\u0000¢\u0006\u0002\b:R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00198\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006<"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView;", "Landroid/widget/RelativeLayout;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter$View;", "context", "Landroid/content/Context;", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoLivenessOverlayViewBinding;", "captureRect", "Landroid/graphics/RectF;", "challengeMovementBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoChallengeMovementBinding;", "headTurnDetectedString", "", "getHeadTurnDetectedString", "()Ljava/lang/String;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView$ChallengesListener;", "livenessChallengesDrawer", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer;", "livenessChallengesDrawerFactory", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer$Factory;", "getLivenessChallengesDrawerFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer$Factory;", "setLivenessChallengesDrawerFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesDrawer$Factory;)V", "presenter", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayPresenter;)V", "onAttachedToWindow", "", "onDetachedFromWindow", "onErrorObservingHeadTurnResults", "onFullProgressReached", "onHalfOfProgressReached", "onProgress", "progress", "", "onWrongHeadTurn", "setListener", "setListener$onfido_capture_sdk_core_release", "stopFaceTracking", "stopFaceTracking$onfido_capture_sdk_core_release", "updateInfo", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "manualLivenessCapture", "", "updateInfo$onfido_capture_sdk_core_release", "updateTextPosition", "rect", "updateTextPosition$onfido_capture_sdk_core_release", "ChallengesListener", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessOverlayView extends RelativeLayout implements LivenessOverlayPresenter.View {
    private final OnfidoLivenessOverlayViewBinding binding;
    private RectF captureRect;
    private OnfidoChallengeMovementBinding challengeMovementBinding;
    private ChallengesListener listener;
    private LivenessChallengesDrawer livenessChallengesDrawer;

    @Inject
    public LivenessChallengesDrawer.Factory livenessChallengesDrawerFactory;

    @Inject
    public LivenessOverlayPresenter presenter;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView$ChallengesListener;", "", "onErrorObservingHeadTurnResults", "", "onLivenessChallengeFinished", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ChallengesListener {
        void onErrorObservingHeadTurnResults();

        void onLivenessChallengeFinished();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivenessOverlayView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public String getHeadTurnDetectedString() {
        String string = getContext().getString(R.string.onfido_video_capture_turn_success_accessibility);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final LivenessChallengesDrawer.Factory getLivenessChallengesDrawerFactory$onfido_capture_sdk_core_release() {
        LivenessChallengesDrawer.Factory factory = this.livenessChallengesDrawerFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("livenessChallengesDrawerFactory");
        return null;
    }

    public final LivenessOverlayPresenter getPresenter$onfido_capture_sdk_core_release() {
        LivenessOverlayPresenter livenessOverlayPresenter = this.presenter;
        if (livenessOverlayPresenter != null) {
            return livenessOverlayPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        SdkController.getSdkComponent$default(companion, context, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        getPresenter$onfido_capture_sdk_core_release().attachView(this);
        LivenessChallengesDrawer.Factory livenessChallengesDrawerFactory$onfido_capture_sdk_core_release = getLivenessChallengesDrawerFactory$onfido_capture_sdk_core_release();
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        this.livenessChallengesDrawer = livenessChallengesDrawerFactory$onfido_capture_sdk_core_release.create(context2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopFaceTracking$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public void onErrorObservingHeadTurnResults() {
        ChallengesListener challengesListener = this.listener;
        if (challengesListener != null) {
            challengesListener.onErrorObservingHeadTurnResults();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public void onFullProgressReached() {
        stopFaceTracking$onfido_capture_sdk_core_release();
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding = this.challengeMovementBinding;
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding2 = null;
        if (onfidoChallengeMovementBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding = null;
        }
        TextView movementTitleFirst = onfidoChallengeMovementBinding.movementTitleFirst;
        Intrinsics.checkNotNullExpressionValue(movementTitleFirst, "movementTitleFirst");
        ViewExtensionsKt.clearText(movementTitleFirst);
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding3 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
        } else {
            onfidoChallengeMovementBinding2 = onfidoChallengeMovementBinding3;
        }
        TextView movementTitleSecond = onfidoChallengeMovementBinding2.movementTitleSecond;
        Intrinsics.checkNotNullExpressionValue(movementTitleSecond, "movementTitleSecond");
        ViewExtensionsKt.clearText(movementTitleSecond);
        ChallengesListener challengesListener = this.listener;
        if (challengesListener != null) {
            challengesListener.onLivenessChallengeFinished();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public void onHalfOfProgressReached() {
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding = null;
        }
        TextView movementTitleFirst = onfidoChallengeMovementBinding.movementTitleFirst;
        Intrinsics.checkNotNullExpressionValue(movementTitleFirst, "movementTitleFirst");
        ViewExtensionsKt.toGone$default(movementTitleFirst, false, 1, null);
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding2 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding2 = null;
        }
        LivenessProgressArrow arrow = onfidoChallengeMovementBinding2.arrow;
        Intrinsics.checkNotNullExpressionValue(arrow, "arrow");
        ViewExtensionsKt.toGone$default(arrow, false, 1, null);
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding3 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding3 = null;
        }
        TextView movementTitleSecond = onfidoChallengeMovementBinding3.movementTitleSecond;
        Intrinsics.checkNotNullExpressionValue(movementTitleSecond, "movementTitleSecond");
        ViewExtensionsKt.toGone$default(movementTitleSecond, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public void onProgress(float progress) {
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding = this.challengeMovementBinding;
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding2 = null;
        if (onfidoChallengeMovementBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding = null;
        }
        LivenessProgressArrow arrow = onfidoChallengeMovementBinding.arrow;
        Intrinsics.checkNotNullExpressionValue(arrow, "arrow");
        LivenessProgressArrow.setProgress$default(arrow, progress, null, 2, null);
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding3 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding3 = null;
        }
        if (!onfidoChallengeMovementBinding3.livenessErrorsBubble.isVisible$onfido_capture_sdk_core_release() || progress <= 0.0f) {
            return;
        }
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding4 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding4 = null;
        }
        OnfidoCaptureValidationBubble livenessErrorsBubble = onfidoChallengeMovementBinding4.livenessErrorsBubble;
        Intrinsics.checkNotNullExpressionValue(livenessErrorsBubble, "livenessErrorsBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(livenessErrorsBubble, OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE, false, 2, null);
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding5 = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
        } else {
            onfidoChallengeMovementBinding2 = onfidoChallengeMovementBinding5;
        }
        Object parent = onfidoChallengeMovementBinding2.livenessErrorsBubble.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        AccessibilityExtensionsKt.sendAccessibilityFocusEvent((View) parent);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayPresenter.View
    public void onWrongHeadTurn() {
        OnfidoChallengeMovementBinding onfidoChallengeMovementBinding = this.challengeMovementBinding;
        if (onfidoChallengeMovementBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengeMovementBinding");
            onfidoChallengeMovementBinding = null;
        }
        OnfidoCaptureValidationBubble livenessErrorsBubble = onfidoChallengeMovementBinding.livenessErrorsBubble;
        Intrinsics.checkNotNullExpressionValue(livenessErrorsBubble, "livenessErrorsBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(livenessErrorsBubble, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)), false, 2, null);
    }

    public final void setListener$onfido_capture_sdk_core_release(ChallengesListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    public final void setLivenessChallengesDrawerFactory$onfido_capture_sdk_core_release(LivenessChallengesDrawer.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.livenessChallengesDrawerFactory = factory;
    }

    public final void setPresenter$onfido_capture_sdk_core_release(LivenessOverlayPresenter livenessOverlayPresenter) {
        Intrinsics.checkNotNullParameter(livenessOverlayPresenter, "<set-?>");
        this.presenter = livenessOverlayPresenter;
    }

    public final void stopFaceTracking$onfido_capture_sdk_core_release() {
        getPresenter$onfido_capture_sdk_core_release().stop();
    }

    public final void updateInfo$onfido_capture_sdk_core_release(LivenessChallenge challenge, boolean manualLivenessCapture) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        RectF rectF = null;
        if (challenge instanceof ReciteLivenessChallenge) {
            LivenessChallengesDrawer livenessChallengesDrawer = this.livenessChallengesDrawer;
            if (livenessChallengesDrawer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("livenessChallengesDrawer");
                livenessChallengesDrawer = null;
            }
            int[] query = ((ReciteLivenessChallenge) challenge).getQuery();
            RectF rectF2 = this.captureRect;
            if (rectF2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureRect");
            } else {
                rectF = rectF2;
            }
            livenessChallengesDrawer.drawReciteChallengeRecordingMode$onfido_capture_sdk_core_release(query, rectF, this);
        } else if (challenge instanceof MovementLivenessChallenge) {
            LivenessChallengesDrawer livenessChallengesDrawer2 = this.livenessChallengesDrawer;
            if (livenessChallengesDrawer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("livenessChallengesDrawer");
                livenessChallengesDrawer2 = null;
            }
            MovementType query2 = ((MovementLivenessChallenge) challenge).getQuery();
            RectF rectF3 = this.captureRect;
            if (rectF3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureRect");
            } else {
                rectF = rectF3;
            }
            this.challengeMovementBinding = livenessChallengesDrawer2.drawMovementChallengeRecordingMode$onfido_capture_sdk_core_release(query2, rectF, this);
        }
        if (!(challenge instanceof MovementLivenessChallenge) || manualLivenessCapture) {
            return;
        }
        getPresenter$onfido_capture_sdk_core_release().startFaceTracker(((MovementLivenessChallenge) challenge).getQuery());
    }

    public final void updateTextPosition$onfido_capture_sdk_core_release(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.captureRect = rect;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LivenessOverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LivenessOverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoLivenessOverlayViewBinding onfidoLivenessOverlayViewBindingInflate = OnfidoLivenessOverlayViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoLivenessOverlayViewBindingInflate, "inflate(...)");
        this.binding = onfidoLivenessOverlayViewBindingInflate;
        RelativeLayout root = onfidoLivenessOverlayViewBindingInflate.getRoot();
        root.setWillNotDraw(false);
        root.setFocusable(true);
        root.setImportantForAccessibility(2);
    }

    public /* synthetic */ LivenessOverlayView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
