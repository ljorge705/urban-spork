package com.onfido.android.sdk.capture.ui.welcome;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0003J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0003J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter;", "", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "screenLoadTracker", "Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "welcomeOptions", "Lcom/onfido/android/sdk/capture/ui/options/WelcomeScreenOptions;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;Lcom/onfido/android/sdk/capture/ui/options/WelcomeScreenOptions;)V", "stateStream", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState;", "getStateStream", "()Lio/reactivex/rxjava3/core/Observable;", "getBulletPointStates", "", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState;", "getDocumentCaptureTypeStringRes", "", "getStringResIdsForFlowStepType", "flowStepType", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "onStart", "", "Factory", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WelcomePresenter {
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final ScreenLoadTracker screenLoadTracker;
    private final ScreenTracker screenTracker;
    private final Observable<ViewState> stateStream;
    private final WelcomeScreenOptions welcomeOptions;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter;", "welcomeOptions", "Lcom/onfido/android/sdk/capture/ui/options/WelcomeScreenOptions;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        WelcomePresenter create(WelcomeScreenOptions welcomeOptions);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001eB=\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState;", "", "titleResId", "", "subtitleResId", "listHeaderTitleResId", "nextButtonResId", "steps", "", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState;", "(IIIILjava/util/List;)V", "getListHeaderTitleResId", "()I", "getNextButtonResId", "getSteps", "()Ljava/util/List;", "getSubtitleResId", "getTitleResId", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "BulletPointState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ViewState {
        private final int listHeaderTitleResId;
        private final int nextButtonResId;
        private final List<BulletPointState> steps;
        private final int subtitleResId;
        private final int titleResId;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState;", "", "()V", "ArrowBulletPoint", "NumberBulletPoint", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState$ArrowBulletPoint;", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState$NumberBulletPoint;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static abstract class BulletPointState {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState$ArrowBulletPoint;", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState;", "stringResId", "", "(I)V", "getStringResId", "()I", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final /* data */ class ArrowBulletPoint extends BulletPointState {
                private final int stringResId;

                public ArrowBulletPoint(int i) {
                    super(null);
                    this.stringResId = i;
                }

                public static /* synthetic */ ArrowBulletPoint copy$default(ArrowBulletPoint arrowBulletPoint, int i, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        i = arrowBulletPoint.stringResId;
                    }
                    return arrowBulletPoint.copy(i);
                }

                /* renamed from: component1, reason: from getter */
                public final int getStringResId() {
                    return this.stringResId;
                }

                public final ArrowBulletPoint copy(int stringResId) {
                    return new ArrowBulletPoint(stringResId);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof ArrowBulletPoint) && this.stringResId == ((ArrowBulletPoint) other).stringResId;
                }

                public final int getStringResId() {
                    return this.stringResId;
                }

                public int hashCode() {
                    return Integer.hashCode(this.stringResId);
                }

                public String toString() {
                    return "ArrowBulletPoint(stringResId=" + this.stringResId + ')';
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState$NumberBulletPoint;", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomePresenter$ViewState$BulletPointState;", "index", "", "stringResId", "(II)V", "getIndex", "()I", "getStringResId", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final /* data */ class NumberBulletPoint extends BulletPointState {
                private final int index;
                private final int stringResId;

                public NumberBulletPoint(int i, int i2) {
                    super(null);
                    this.index = i;
                    this.stringResId = i2;
                }

                public static /* synthetic */ NumberBulletPoint copy$default(NumberBulletPoint numberBulletPoint, int i, int i2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        i = numberBulletPoint.index;
                    }
                    if ((i3 & 2) != 0) {
                        i2 = numberBulletPoint.stringResId;
                    }
                    return numberBulletPoint.copy(i, i2);
                }

                /* renamed from: component1, reason: from getter */
                public final int getIndex() {
                    return this.index;
                }

                /* renamed from: component2, reason: from getter */
                public final int getStringResId() {
                    return this.stringResId;
                }

                public final NumberBulletPoint copy(int index, int stringResId) {
                    return new NumberBulletPoint(index, stringResId);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof NumberBulletPoint)) {
                        return false;
                    }
                    NumberBulletPoint numberBulletPoint = (NumberBulletPoint) other;
                    return this.index == numberBulletPoint.index && this.stringResId == numberBulletPoint.stringResId;
                }

                public final int getIndex() {
                    return this.index;
                }

                public final int getStringResId() {
                    return this.stringResId;
                }

                public int hashCode() {
                    return (Integer.hashCode(this.index) * 31) + Integer.hashCode(this.stringResId);
                }

                public String toString() {
                    return "NumberBulletPoint(index=" + this.index + ", stringResId=" + this.stringResId + ')';
                }
            }

            private BulletPointState() {
            }

            public /* synthetic */ BulletPointState(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ViewState(int i, int i2, int i3, int i4, List<? extends BulletPointState> steps) {
            Intrinsics.checkNotNullParameter(steps, "steps");
            this.titleResId = i;
            this.subtitleResId = i2;
            this.listHeaderTitleResId = i3;
            this.nextButtonResId = i4;
            this.steps = steps;
        }

        public static /* synthetic */ ViewState copy$default(ViewState viewState, int i, int i2, int i3, int i4, List list, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = viewState.titleResId;
            }
            if ((i5 & 2) != 0) {
                i2 = viewState.subtitleResId;
            }
            int i6 = i2;
            if ((i5 & 4) != 0) {
                i3 = viewState.listHeaderTitleResId;
            }
            int i7 = i3;
            if ((i5 & 8) != 0) {
                i4 = viewState.nextButtonResId;
            }
            int i8 = i4;
            if ((i5 & 16) != 0) {
                list = viewState.steps;
            }
            return viewState.copy(i, i6, i7, i8, list);
        }

        /* renamed from: component1, reason: from getter */
        public final int getTitleResId() {
            return this.titleResId;
        }

        /* renamed from: component2, reason: from getter */
        public final int getSubtitleResId() {
            return this.subtitleResId;
        }

        /* renamed from: component3, reason: from getter */
        public final int getListHeaderTitleResId() {
            return this.listHeaderTitleResId;
        }

        /* renamed from: component4, reason: from getter */
        public final int getNextButtonResId() {
            return this.nextButtonResId;
        }

        public final List<BulletPointState> component5() {
            return this.steps;
        }

        public final ViewState copy(int titleResId, int subtitleResId, int listHeaderTitleResId, int nextButtonResId, List<? extends BulletPointState> steps) {
            Intrinsics.checkNotNullParameter(steps, "steps");
            return new ViewState(titleResId, subtitleResId, listHeaderTitleResId, nextButtonResId, steps);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ViewState)) {
                return false;
            }
            ViewState viewState = (ViewState) other;
            return this.titleResId == viewState.titleResId && this.subtitleResId == viewState.subtitleResId && this.listHeaderTitleResId == viewState.listHeaderTitleResId && this.nextButtonResId == viewState.nextButtonResId && Intrinsics.areEqual(this.steps, viewState.steps);
        }

        public final int getListHeaderTitleResId() {
            return this.listHeaderTitleResId;
        }

        public final int getNextButtonResId() {
            return this.nextButtonResId;
        }

        public final List<BulletPointState> getSteps() {
            return this.steps;
        }

        public final int getSubtitleResId() {
            return this.subtitleResId;
        }

        public final int getTitleResId() {
            return this.titleResId;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.titleResId) * 31) + Integer.hashCode(this.subtitleResId)) * 31) + Integer.hashCode(this.listHeaderTitleResId)) * 31) + Integer.hashCode(this.nextButtonResId)) * 31) + this.steps.hashCode();
        }

        public String toString() {
            return "ViewState(titleResId=" + this.titleResId + ", subtitleResId=" + this.subtitleResId + ", listHeaderTitleResId=" + this.listHeaderTitleResId + ", nextButtonResId=" + this.nextButtonResId + ", steps=" + this.steps + ')';
        }

        public /* synthetic */ ViewState(int i, int i2, int i3, int i4, List list, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, i3, i4, (i5 & 16) != 0 ? CollectionsKt.emptyList() : list);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FlowAction.values().length];
            try {
                iArr[FlowAction.CAPTURE_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FlowAction.CAPTURE_FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FlowAction.PROOF_OF_ADDRESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FlowAction.ACTIVE_VIDEO_CAPTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FlowAction.CAPTURE_LIVENESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter$getBulletPointStates$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<FlowStep, Integer> {
        AnonymousClass2(Object obj) {
            super(1, obj, WelcomePresenter.class, "getStringResIdsForFlowStepType", "getStringResIdsForFlowStepType(Lcom/onfido/android/sdk/capture/ui/options/FlowStep;)I", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Integer invoke(FlowStep p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Integer.valueOf(((WelcomePresenter) this.receiver).getStringResIdsForFlowStepType(p0));
        }
    }

    @AssistedInject
    public WelcomePresenter(OnfidoRemoteConfig onfidoRemoteConfig, ScreenTracker screenTracker, ScreenLoadTracker screenLoadTracker, @Assisted WelcomeScreenOptions welcomeOptions) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(screenLoadTracker, "screenLoadTracker");
        Intrinsics.checkNotNullParameter(welcomeOptions, "welcomeOptions");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.screenTracker = screenTracker;
        this.screenLoadTracker = screenLoadTracker;
        this.welcomeOptions = welcomeOptions;
        Observable<ViewState> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return WelcomePresenter.stateStream$lambda$1(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable(...)");
        this.stateStream = observableFromCallable;
    }

    private final List<ViewState.BulletPointState> getBulletPointStates() {
        return SequencesKt.toList(SequencesKt.mapIndexed(SequencesKt.map(CollectionsKt.asSequence(SequencesKt.toList(SequencesKt.filter(CollectionsKt.asSequence(this.welcomeOptions.getFlowSteps$onfido_capture_sdk_core_release()), new Function1<FlowStep, Boolean>() { // from class: com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter.getBulletPointStates.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FlowStep it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(CollectionsKt.listOf((Object[]) new FlowAction[]{FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.PROOF_OF_ADDRESS, FlowAction.ACTIVE_VIDEO_CAPTURE, FlowAction.CAPTURE_LIVENESS}).contains(it.getAction()));
            }
        }))), new AnonymousClass2(this)), new Function2<Integer, Integer, ViewState.BulletPointState.NumberBulletPoint>() { // from class: com.onfido.android.sdk.capture.ui.welcome.WelcomePresenter.getBulletPointStates.3
            public final ViewState.BulletPointState.NumberBulletPoint invoke(int i, int i2) {
                return new ViewState.BulletPointState.NumberBulletPoint(i + 1, i2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ ViewState.BulletPointState.NumberBulletPoint invoke(Integer num, Integer num2) {
                return invoke(num.intValue(), num2.intValue());
            }
        }));
    }

    private final int getDocumentCaptureTypeStringRes() {
        return this.onfidoRemoteConfig.isMultiImageCaptureEnabled() ? R.string.onfido_welcome_list_item_doc_generic : R.string.onfido_welcome_list_item_doc_photo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getStringResIdsForFlowStepType(FlowStep flowStepType) {
        int i = WhenMappings.$EnumSwitchMapping$0[flowStepType.getAction().ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? (i == 4 || i == 5) ? R.string.onfido_welcome_list_item_face_video : R.string.onfido_poa_doc_capture_empty : R.string.onfido_poa_welcome_text : R.string.onfido_welcome_list_item_face_photo : getDocumentCaptureTypeStringRes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ViewState stateStream$lambda$1(WelcomePresenter this$0) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i2 = R.string.onfido_welcome_title;
        int i3 = R.string.onfido_welcome_subtitle;
        int i4 = R.string.onfido_welcome_list_header;
        List<FlowStep> flowSteps$onfido_capture_sdk_core_release = this$0.welcomeOptions.getFlowSteps$onfido_capture_sdk_core_release();
        if ((flowSteps$onfido_capture_sdk_core_release instanceof Collection) && flowSteps$onfido_capture_sdk_core_release.isEmpty()) {
            i = R.string.onfido_welcome_button_primary;
        } else {
            Iterator<T> it = flowSteps$onfido_capture_sdk_core_release.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((FlowStep) it.next()).getAction() == FlowAction.CAPTURE_DOCUMENT) {
                    if (this$0.welcomeOptions.getFlowSteps$onfido_capture_sdk_core_release().get(0).getAction() == FlowAction.PROOF_OF_ADDRESS) {
                        break;
                    }
                    i = R.string.onfido_welcome_button_primary_doc;
                }
            }
        }
        return new ViewState(i2, i3, i4, i, this$0.getBulletPointStates());
    }

    public final Observable<ViewState> getStateStream() {
        return this.stateStream;
    }

    public final void onStart() {
        this.screenTracker.trackWelcome$onfido_capture_sdk_core_release();
        this.screenLoadTracker.trackNavigationCompleted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen.WELCOME);
    }
}
