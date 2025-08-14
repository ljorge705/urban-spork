package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn;

import com.clevertap.android.sdk.Constants;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001:\u0002\u0014\u0015J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\rH&J\b\u0010\u0011\u001a\u00020\rH&J\b\u0010\u0012\u001a\u00020\rH&J\b\u0010\u0013\u001a\u00020\rH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "", "leftTrackState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "getLeftTrackState", "()Lio/reactivex/rxjava3/core/Observable;", "rightTrackState", "getRightTrackState", "viewState", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$ViewState;", "getViewState", "handleFaceRotation", "", "faceAngle", "", "onViewDetached", "reset", "startHeadTurnTimer", "trackScreenEvent", "TrackState", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface HeadTurnGuidanceViewModel {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "", "Completed", "ProgressUpdated", "Reset", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$ProgressUpdated;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$Reset;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface TrackState {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Completed implements TrackState {
            public static final Completed INSTANCE = new Completed();

            private Completed() {
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$ProgressUpdated;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "faceAngle", "", "(F)V", "getFaceAngle", "()F", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class ProgressUpdated implements TrackState {
            private final float faceAngle;

            public ProgressUpdated(float f) {
                this.faceAngle = f;
            }

            public static /* synthetic */ ProgressUpdated copy$default(ProgressUpdated progressUpdated, float f, int i, Object obj) {
                if ((i & 1) != 0) {
                    f = progressUpdated.faceAngle;
                }
                return progressUpdated.copy(f);
            }

            /* renamed from: component1, reason: from getter */
            public final float getFaceAngle() {
                return this.faceAngle;
            }

            public final ProgressUpdated copy(float faceAngle) {
                return new ProgressUpdated(faceAngle);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ProgressUpdated) && Float.compare(this.faceAngle, ((ProgressUpdated) other).faceAngle) == 0;
            }

            public final float getFaceAngle() {
                return this.faceAngle;
            }

            public int hashCode() {
                return Float.hashCode(this.faceAngle);
            }

            public String toString() {
                return "ProgressUpdated(faceAngle=" + this.faceAngle + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState$Reset;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$TrackState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Reset implements TrackState {
            public static final Reset INSTANCE = new Reset();

            private Reset() {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel$ViewState;", "", "isCompleted", "", "showHeadTurnAnim", "(ZZ)V", "()Z", "getShowHeadTurnAnim", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ViewState {
        private final boolean isCompleted;
        private final boolean showHeadTurnAnim;

        public ViewState(boolean z, boolean z2) {
            this.isCompleted = z;
            this.showHeadTurnAnim = z2;
        }

        public static /* synthetic */ ViewState copy$default(ViewState viewState, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = viewState.isCompleted;
            }
            if ((i & 2) != 0) {
                z2 = viewState.showHeadTurnAnim;
            }
            return viewState.copy(z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsCompleted() {
            return this.isCompleted;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShowHeadTurnAnim() {
            return this.showHeadTurnAnim;
        }

        public final ViewState copy(boolean isCompleted, boolean showHeadTurnAnim) {
            return new ViewState(isCompleted, showHeadTurnAnim);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ViewState)) {
                return false;
            }
            ViewState viewState = (ViewState) other;
            return this.isCompleted == viewState.isCompleted && this.showHeadTurnAnim == viewState.showHeadTurnAnim;
        }

        public final boolean getShowHeadTurnAnim() {
            return this.showHeadTurnAnim;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isCompleted) * 31) + Boolean.hashCode(this.showHeadTurnAnim);
        }

        public final boolean isCompleted() {
            return this.isCompleted;
        }

        public String toString() {
            return "ViewState(isCompleted=" + this.isCompleted + ", showHeadTurnAnim=" + this.showHeadTurnAnim + ')';
        }
    }

    Observable<TrackState> getLeftTrackState();

    Observable<TrackState> getRightTrackState();

    /* renamed from: getViewState */
    Observable<ViewState> mo5553getViewState();

    void handleFaceRotation(float faceAngle);

    void onViewDetached();

    void reset();

    void startHeadTurnTimer();

    void trackScreenEvent();
}
