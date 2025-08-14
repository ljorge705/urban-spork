package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.ViewExtensionsKt;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.databinding.OnfidoAvcViewHeadTurnGuidanceBinding;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0006\u0010\u001c\u001a\u00020\u0018J\u001e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010!\u001a\u00020\u0018H\u0014J(\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\tH\u0014J\u0006\u0010'\u001a\u00020\u0018R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/headturn/HeadTurnGuidanceView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", Session.JsonKeys.ATTRS, "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "alphaAnimator", "Landroid/view/ViewPropertyAnimator;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoAvcViewHeadTurnGuidanceBinding;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "hapticFeedback", "Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "viewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "announceSideCompleteAccessibility", "", "resId", "isSecondHeadTurn", "", "hide", "initialize", "observeLeftTrackState", "observeRightTrackState", "observeViewState", "onDetachedFromWindow", "onSizeChanged", Constants.INAPP_WINDOW, "h", "oldw", "oldh", "show", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HeadTurnGuidanceView extends FrameLayout {
    private ViewPropertyAnimator alphaAnimator;
    private AnnouncementService announcementService;
    private final OnfidoAvcViewHeadTurnGuidanceBinding binding;
    private final CompositeDisposable compositeDisposable;
    private HapticFeedback hapticFeedback;
    private HeadTurnGuidanceViewModel viewModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceView(Context context) throws Resources.NotFoundException {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnGuidanceBinding onfidoAvcViewHeadTurnGuidanceBindingInflate = OnfidoAvcViewHeadTurnGuidanceBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnGuidanceBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnGuidanceBindingInflate;
        this.compositeDisposable = new CompositeDisposable();
        onfidoAvcViewHeadTurnGuidanceBindingInflate.watermark.setDarkBackground();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void announceSideCompleteAccessibility(int resId, boolean isSecondHeadTurn) {
        AnnouncementService announcementService = null;
        if (isSecondHeadTurn) {
            AnnouncementService announcementService2 = this.announcementService;
            if (announcementService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("announcementService");
            } else {
                announcementService = announcementService2;
            }
            announcementService.announceStringAsync(new int[]{resId}, true);
            return;
        }
        AnnouncementService announcementService3 = this.announcementService;
        if (announcementService3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        } else {
            announcementService = announcementService3;
        }
        announcementService.announceStringAsync(new int[]{resId, R.string.onfido_avc_face_capture_feedback_turn_head_other_side_accessibility}, true);
    }

    private final void observeLeftTrackState(final HeadTurnGuidanceViewModel viewModel) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = viewModel.getLeftTrackState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceView.observeLeftTrackState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HeadTurnGuidanceViewModel.TrackState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it instanceof HeadTurnGuidanceViewModel.TrackState.ProgressUpdated) {
                    HeadTurnGuidanceView.this.binding.headTurnProgressView.setLeftSideProgress(((HeadTurnGuidanceViewModel.TrackState.ProgressUpdated) it).getFaceAngle());
                    return;
                }
                if (!Intrinsics.areEqual(it, HeadTurnGuidanceViewModel.TrackState.Completed.INSTANCE)) {
                    if (Intrinsics.areEqual(it, HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE)) {
                        HeadTurnGuidanceView.this.binding.headTurnProgressView.resetLeftSide();
                        return;
                    }
                    return;
                }
                HeadTurnGuidanceView.this.binding.headTurnProgressView.setLeftSideCompleted();
                HapticFeedback hapticFeedback = HeadTurnGuidanceView.this.hapticFeedback;
                if (hapticFeedback == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hapticFeedback");
                    hapticFeedback = null;
                }
                hapticFeedback.performTap(HeadTurnGuidanceView.this);
                HeadTurnGuidanceView headTurnGuidanceView = HeadTurnGuidanceView.this;
                headTurnGuidanceView.announceSideCompleteAccessibility(R.string.onfido_avc_face_capture_confirmation_left_side_complete_accessibility, headTurnGuidanceView.binding.headTurnProgressView.getRightSideCompleted());
                viewModel.startHeadTurnTimer();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeRightTrackState(final HeadTurnGuidanceViewModel viewModel) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = viewModel.getRightTrackState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceView.observeRightTrackState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HeadTurnGuidanceViewModel.TrackState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it instanceof HeadTurnGuidanceViewModel.TrackState.ProgressUpdated) {
                    HeadTurnGuidanceView.this.binding.headTurnProgressView.setRightSideProgress(((HeadTurnGuidanceViewModel.TrackState.ProgressUpdated) it).getFaceAngle());
                    return;
                }
                if (!Intrinsics.areEqual(it, HeadTurnGuidanceViewModel.TrackState.Completed.INSTANCE)) {
                    if (Intrinsics.areEqual(it, HeadTurnGuidanceViewModel.TrackState.Reset.INSTANCE)) {
                        HeadTurnGuidanceView.this.binding.headTurnProgressView.resetRightSide();
                        return;
                    }
                    return;
                }
                HeadTurnGuidanceView.this.binding.headTurnProgressView.setRightSideCompleted();
                HapticFeedback hapticFeedback = HeadTurnGuidanceView.this.hapticFeedback;
                if (hapticFeedback == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("hapticFeedback");
                    hapticFeedback = null;
                }
                hapticFeedback.performTap(HeadTurnGuidanceView.this);
                HeadTurnGuidanceView headTurnGuidanceView = HeadTurnGuidanceView.this;
                headTurnGuidanceView.announceSideCompleteAccessibility(R.string.onfido_avc_face_capture_confirmation_right_side_complete_accessibility, headTurnGuidanceView.binding.headTurnProgressView.getLeftSideCompleted());
                viewModel.startHeadTurnTimer();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeViewState(HeadTurnGuidanceViewModel viewModel) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = viewModel.mo5553getViewState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceView.observeViewState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HeadTurnGuidanceViewModel.ViewState it) throws JSONException {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getShowHeadTurnAnim()) {
                    HeadTurnGuidanceView.this.binding.headTurnAnimView.show();
                } else {
                    HeadTurnGuidanceView.this.binding.headTurnAnimView.hide();
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public final void hide() {
        ViewPropertyAnimator viewPropertyAnimator = this.alphaAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        setAlpha(0.0f);
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel = this.viewModel;
        if (headTurnGuidanceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            headTurnGuidanceViewModel = null;
        }
        headTurnGuidanceViewModel.reset();
    }

    public final void initialize(HeadTurnGuidanceViewModel viewModel, HapticFeedback hapticFeedback, AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(hapticFeedback, "hapticFeedback");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        this.viewModel = viewModel;
        this.hapticFeedback = hapticFeedback;
        this.announcementService = announcementService;
        observeViewState(viewModel);
        observeLeftTrackState(viewModel);
        observeRightTrackState(viewModel);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.compositeDisposable.clear();
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel = this.viewModel;
        if (headTurnGuidanceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            headTurnGuidanceViewModel = null;
        }
        headTurnGuidanceViewModel.onViewDetached();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) throws Resources.NotFoundException {
        TextView instructionTextView = this.binding.instructionTextView;
        Intrinsics.checkNotNullExpressionValue(instructionTextView, "instructionTextView");
        ViewExtensionsKt.setOvalMargin$default(instructionTextView, getWidth(), getHeight(), 0, 4, null);
    }

    public final void show() {
        this.alphaAnimator = com.onfido.android.sdk.capture.utils.ViewExtensionsKt.showWithAlphaAnim$default(this, 0.0f, 0L, 3, null);
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel = this.viewModel;
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel2 = null;
        if (headTurnGuidanceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            headTurnGuidanceViewModel = null;
        }
        headTurnGuidanceViewModel.startHeadTurnTimer();
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel3 = this.viewModel;
        if (headTurnGuidanceViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            headTurnGuidanceViewModel2 = headTurnGuidanceViewModel3;
        }
        headTurnGuidanceViewModel2.trackScreenEvent();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceView(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnGuidanceBinding onfidoAvcViewHeadTurnGuidanceBindingInflate = OnfidoAvcViewHeadTurnGuidanceBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnGuidanceBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnGuidanceBindingInflate;
        this.compositeDisposable = new CompositeDisposable();
        onfidoAvcViewHeadTurnGuidanceBindingInflate.watermark.setDarkBackground();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadTurnGuidanceView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        OnfidoAvcViewHeadTurnGuidanceBinding onfidoAvcViewHeadTurnGuidanceBindingInflate = OnfidoAvcViewHeadTurnGuidanceBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(onfidoAvcViewHeadTurnGuidanceBindingInflate, "inflate(...)");
        this.binding = onfidoAvcViewHeadTurnGuidanceBindingInflate;
        this.compositeDisposable = new CompositeDisposable();
        onfidoAvcViewHeadTurnGuidanceBindingInflate.watermark.setDarkBackground();
    }
}
