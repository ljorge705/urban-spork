package com.onfido.android.sdk.capture.ui.userconsent;

import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.microsoft.codepush.react.CodePushConstants;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.UIEvent;
import com.onfido.android.sdk.capture.internal.util.UIEventManager;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.OnfidoConfigExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function4;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0000\u0018\u0000 /2\u00020\u0001:\u0004/012B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\"J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\u0006\u0010)\u001a\u00020%J\b\u0010*\u001a\u00020%H\u0002J\u0006\u0010+\u001a\u00020%J\b\u0010,\u001a\u00020%H\u0014J\u0006\u0010-\u001a\u00020%J\u0006\u0010.\u001a\u00020%R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\b\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\u0002\b\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010#\u001a\r\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\b\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel;", "Landroidx/lifecycle/ViewModel;", "userConsentRepository", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentRepository;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "(Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "errorMessage", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "Lio/reactivex/rxjava3/annotations/NonNull;", CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$LoadingState;", "state", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$ViewState;", "getState", "()Lio/reactivex/rxjava3/core/Observable;", "state$delegate", "Lkotlin/Lazy;", "uiEventsManager", "Lcom/onfido/android/sdk/capture/internal/util/UIEventManager;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "userConsentPage", "consumeUIEvent", "", "uiEvent", "flowUserExit", "flowUserExitCanceled", "flowUserExitConfirmed", "loadUserConsentPage", "onAcceptClicked", "onCleared", "onRejectClicked", "onStart", "Companion", "LoadingState", "UserConsentUIEvent", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UserConsentViewModel extends ViewModel {
    private static final String STEP_USER_CONSENT = "user_consent";
    private final CompositeDisposable compositeDisposable;
    private final BehaviorSubject<String> errorMessage;
    private final FlowTracker flowTracker;
    private final BehaviorSubject<LoadingState> isLoading;
    private final OnfidoConfig onfidoConfig;
    private final OnfidoRemoteConfig remoteConfig;
    private final SchedulersProvider schedulersProvider;
    private final ScreenTracker screenTracker;

    /* renamed from: state$delegate, reason: from kotlin metadata */
    private final Lazy state;
    private final UIEventManager<UserConsentUIEvent> uiEventsManager;
    private final BehaviorSubject<String> userConsentPage;
    private final UserConsentRepository userConsentRepository;
    private final WaitingScreenTracker waitingScreenTracker;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$LoadingState;", "", CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, "", "loadingReason", "", "(ZLjava/lang/String;)V", "()Z", "getLoadingReason", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class LoadingState {
        private final boolean isLoading;
        private final String loadingReason;

        /* JADX WARN: Multi-variable type inference failed */
        public LoadingState() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ LoadingState copy$default(LoadingState loadingState, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = loadingState.isLoading;
            }
            if ((i & 2) != 0) {
                str = loadingState.loadingReason;
            }
            return loadingState.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsLoading() {
            return this.isLoading;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLoadingReason() {
            return this.loadingReason;
        }

        public final LoadingState copy(boolean isLoading, String loadingReason) {
            Intrinsics.checkNotNullParameter(loadingReason, "loadingReason");
            return new LoadingState(isLoading, loadingReason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoadingState)) {
                return false;
            }
            LoadingState loadingState = (LoadingState) other;
            return this.isLoading == loadingState.isLoading && Intrinsics.areEqual(this.loadingReason, loadingState.loadingReason);
        }

        public final String getLoadingReason() {
            return this.loadingReason;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isLoading) * 31) + this.loadingReason.hashCode();
        }

        public final boolean isLoading() {
            return this.isLoading;
        }

        public String toString() {
            return "LoadingState(isLoading=" + this.isLoading + ", loadingReason=" + this.loadingReason + ')';
        }

        public LoadingState(boolean z, String loadingReason) {
            Intrinsics.checkNotNullParameter(loadingReason, "loadingReason");
            this.isLoading = z;
            this.loadingReason = loadingReason;
        }

        public /* synthetic */ LoadingState(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "Lcom/onfido/android/sdk/capture/internal/util/UIEvent;", "()V", "ConsentAccepted", "ConsentExit", "ConsentRejected", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentAccepted;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentExit;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentRejected;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class UserConsentUIEvent extends UIEvent {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentAccepted;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentAccepted extends UserConsentUIEvent {
            public static final ConsentAccepted INSTANCE = new ConsentAccepted();

            private ConsentAccepted() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentExit;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentExit extends UserConsentUIEvent {
            public static final ConsentExit INSTANCE = new ConsentExit();

            private ConsentExit() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent$ConsentRejected;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ConsentRejected extends UserConsentUIEvent {
            public static final ConsentRejected INSTANCE = new ConsentRejected();

            private ConsentRejected() {
                super(null);
            }
        }

        private UserConsentUIEvent() {
        }

        public /* synthetic */ UserConsentUIEvent(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$ViewState;", "", "loadingState", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$LoadingState;", "consentPageString", "", "errorString", "uiEvents", "", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$UserConsentUIEvent;", "showExitFlow", "", "(Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$LoadingState;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "getConsentPageString", "()Ljava/lang/String;", "getErrorString", "getLoadingState", "()Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentViewModel$LoadingState;", "getShowExitFlow", "()Z", "getUiEvents", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class ViewState {
        private final String consentPageString;
        private final String errorString;
        private final LoadingState loadingState;
        private final boolean showExitFlow;
        private final List<UserConsentUIEvent> uiEvents;

        public ViewState() {
            this(null, null, null, null, false, 31, null);
        }

        public static /* synthetic */ ViewState copy$default(ViewState viewState, LoadingState loadingState, String str, String str2, List list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                loadingState = viewState.loadingState;
            }
            if ((i & 2) != 0) {
                str = viewState.consentPageString;
            }
            String str3 = str;
            if ((i & 4) != 0) {
                str2 = viewState.errorString;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                list = viewState.uiEvents;
            }
            List list2 = list;
            if ((i & 16) != 0) {
                z = viewState.showExitFlow;
            }
            return viewState.copy(loadingState, str3, str4, list2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final LoadingState getLoadingState() {
            return this.loadingState;
        }

        /* renamed from: component2, reason: from getter */
        public final String getConsentPageString() {
            return this.consentPageString;
        }

        /* renamed from: component3, reason: from getter */
        public final String getErrorString() {
            return this.errorString;
        }

        public final List<UserConsentUIEvent> component4() {
            return this.uiEvents;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getShowExitFlow() {
            return this.showExitFlow;
        }

        public final ViewState copy(LoadingState loadingState, String consentPageString, String errorString, List<? extends UserConsentUIEvent> uiEvents, boolean showExitFlow) {
            Intrinsics.checkNotNullParameter(loadingState, "loadingState");
            Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
            return new ViewState(loadingState, consentPageString, errorString, uiEvents, showExitFlow);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ViewState)) {
                return false;
            }
            ViewState viewState = (ViewState) other;
            return Intrinsics.areEqual(this.loadingState, viewState.loadingState) && Intrinsics.areEqual(this.consentPageString, viewState.consentPageString) && Intrinsics.areEqual(this.errorString, viewState.errorString) && Intrinsics.areEqual(this.uiEvents, viewState.uiEvents) && this.showExitFlow == viewState.showExitFlow;
        }

        public final String getConsentPageString() {
            return this.consentPageString;
        }

        public final String getErrorString() {
            return this.errorString;
        }

        public final LoadingState getLoadingState() {
            return this.loadingState;
        }

        public final boolean getShowExitFlow() {
            return this.showExitFlow;
        }

        public final List<UserConsentUIEvent> getUiEvents() {
            return this.uiEvents;
        }

        public int hashCode() {
            int iHashCode = this.loadingState.hashCode() * 31;
            String str = this.consentPageString;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.errorString;
            return ((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.uiEvents.hashCode()) * 31) + Boolean.hashCode(this.showExitFlow);
        }

        public String toString() {
            return "ViewState(loadingState=" + this.loadingState + ", consentPageString=" + this.consentPageString + ", errorString=" + this.errorString + ", uiEvents=" + this.uiEvents + ", showExitFlow=" + this.showExitFlow + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ViewState(LoadingState loadingState, String str, String str2, List<? extends UserConsentUIEvent> uiEvents, boolean z) {
            Intrinsics.checkNotNullParameter(loadingState, "loadingState");
            Intrinsics.checkNotNullParameter(uiEvents, "uiEvents");
            this.loadingState = loadingState;
            this.consentPageString = str;
            this.errorString = str2;
            this.uiEvents = uiEvents;
            this.showExitFlow = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ ViewState(LoadingState loadingState, String str, String str2, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new LoadingState(false, null, 3, 0 == true ? 1 : 0) : loadingState, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? false : z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Inject
    public UserConsentViewModel(UserConsentRepository userConsentRepository, ScreenTracker screenTracker, SchedulersProvider schedulersProvider, WaitingScreenTracker waitingScreenTracker, OnfidoRemoteConfig remoteConfig, OnfidoConfig onfidoConfig, FlowTracker flowTracker) {
        Intrinsics.checkNotNullParameter(userConsentRepository, "userConsentRepository");
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(waitingScreenTracker, "waitingScreenTracker");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(flowTracker, "flowTracker");
        this.userConsentRepository = userConsentRepository;
        this.screenTracker = screenTracker;
        this.schedulersProvider = schedulersProvider;
        this.waitingScreenTracker = waitingScreenTracker;
        this.remoteConfig = remoteConfig;
        this.onfidoConfig = onfidoConfig;
        this.flowTracker = flowTracker;
        this.compositeDisposable = new CompositeDisposable();
        this.uiEventsManager = new UIEventManager<>();
        BehaviorSubject<LoadingState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(new LoadingState(false, null, 3, 0 == true ? 1 : 0));
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.isLoading = behaviorSubjectCreateDefault;
        BehaviorSubject<String> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault("");
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault2, "createDefault(...)");
        this.userConsentPage = behaviorSubjectCreateDefault2;
        BehaviorSubject<String> behaviorSubjectCreateDefault3 = BehaviorSubject.createDefault("");
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault3, "createDefault(...)");
        this.errorMessage = behaviorSubjectCreateDefault3;
        this.state = LazyKt.lazy(new Function0<Observable<ViewState>>() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$state$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Observable<UserConsentViewModel.ViewState> invoke() {
                BehaviorSubject behaviorSubject = this.this$0.isLoading;
                BehaviorSubject behaviorSubject2 = this.this$0.userConsentPage;
                BehaviorSubject behaviorSubject3 = this.this$0.errorMessage;
                Observable uiEvents = this.this$0.uiEventsManager.getUiEvents();
                final UserConsentViewModel userConsentViewModel = this.this$0;
                Observable<UserConsentViewModel.ViewState> observableDistinctUntilChanged = Observable.combineLatest(behaviorSubject, behaviorSubject2, behaviorSubject3, uiEvents, new Function4() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$state$2.1
                    @Override // io.reactivex.rxjava3.functions.Function4
                    public final UserConsentViewModel.ViewState apply(UserConsentViewModel.LoadingState loadingState, String str, String str2, List<? extends UserConsentViewModel.UserConsentUIEvent> uiEvents2) {
                        Intrinsics.checkNotNullParameter(uiEvents2, "uiEvents");
                        Intrinsics.checkNotNull(loadingState);
                        return new UserConsentViewModel.ViewState(loadingState, StringsKt.isBlank(str) ^ true ? str : null, StringsKt.isBlank(str2) ^ true ? str2 : null, uiEvents2, userConsentViewModel.remoteConfig.isStudioUserFlowExitEnabled() && OnfidoConfigExtensionsKt.inWorkflowMode(userConsentViewModel.onfidoConfig));
                    }
                }).distinctUntilChanged();
                this.this$0.loadUserConsentPage();
                Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "also(...)");
                return observableDistinctUntilChanged;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadUserConsentPage() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.userConsentRepository.getUserConsentPage$onfido_capture_sdk_core_release().subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.loadUserConsentPage.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserConsentViewModel.this.isLoading.onNext(new LoadingState(true, WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GET_USER_CONSENT));
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.loadUserConsentPage.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String content) {
                Intrinsics.checkNotNullParameter(content, "content");
                UserConsentViewModel.this.isLoading.onNext(new LoadingState(false, null, 2, 0 == true ? 1 : 0));
                UserConsentViewModel.this.userConsentPage.onNext(content);
                UserConsentViewModel.this.errorMessage.onNext("");
                UserConsentViewModel.this.waitingScreenTracker.trackWaitingScreenCompletion(new LoadingFragment.Companion.DialogMode.Loading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GET_USER_CONSENT).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GET_USER_CONSENT);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.loadUserConsentPage.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                UserConsentViewModel.this.isLoading.onNext(new LoadingState(false, null, 2, 0 == true ? 1 : 0));
                UserConsentViewModel.this.userConsentPage.onNext("");
                UserConsentViewModel.this.errorMessage.onNext(throwable.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onAcceptClicked$lambda$0(UserConsentViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isLoading.onNext(new LoadingState(false, null, 2, 0 == true ? 1 : 0));
        this$0.waitingScreenTracker.trackWaitingScreenCompletion(new LoadingFragment.Companion.DialogMode.Loading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GRANT_USER_CONSENT).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GRANT_USER_CONSENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAcceptClicked$lambda$1(UserConsentViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.uiEventsManager.emitUIEvent(UserConsentUIEvent.ConsentAccepted.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onRejectClicked$lambda$2(UserConsentViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isLoading.onNext(new LoadingState(false, null, 2, 0 == true ? 1 : 0));
        this$0.waitingScreenTracker.trackWaitingScreenCompletion(new LoadingFragment.Companion.DialogMode.Loading(WaitingScreenTracker.ClassicFlowWaitingReason.REASON_REVOKE_USER_CONSENT).toTaskType(), WaitingScreenTracker.ClassicFlowWaitingReason.REASON_REVOKE_USER_CONSENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRejectClicked$lambda$3(UserConsentViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.uiEventsManager.emitUIEvent(UserConsentUIEvent.ConsentRejected.INSTANCE);
    }

    public final void consumeUIEvent(UserConsentUIEvent uiEvent) {
        Intrinsics.checkNotNullParameter(uiEvent, "uiEvent");
        this.uiEventsManager.consumeUIEvent(uiEvent);
    }

    public final void flowUserExit() {
        this.flowTracker.trackFlowUserExitButtonClicked(STEP_USER_CONSENT);
    }

    public final void flowUserExitCanceled() {
        this.flowTracker.trackFlowUserExitCanceled(STEP_USER_CONSENT);
    }

    public final void flowUserExitConfirmed() {
        this.flowTracker.trackFlowUserExitConfirmed(STEP_USER_CONSENT);
        this.uiEventsManager.emitUIEvent(UserConsentUIEvent.ConsentExit.INSTANCE);
    }

    public final Observable<ViewState> getState() {
        return (Observable) this.state.getValue();
    }

    public final void onAcceptClicked() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.userConsentRepository.grantUserConsent$onfido_capture_sdk_core_release().observeOn(this.schedulersProvider.getUi()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.onAcceptClicked.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserConsentViewModel.this.isLoading.onNext(new LoadingState(true, WaitingScreenTracker.ClassicFlowWaitingReason.REASON_GRANT_USER_CONSENT));
            }
        }).doOnTerminate(new Action() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UserConsentViewModel.onAcceptClicked$lambda$0(this.f$0);
            }
        }).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UserConsentViewModel.onAcceptClicked$lambda$1(this.f$0);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.onAcceptClicked.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                UserConsentViewModel.this.errorMessage.onNext(throwable.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.clear();
    }

    public final void onRejectClicked() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.userConsentRepository.revokeConsent$onfido_capture_sdk_core_release().observeOn(this.schedulersProvider.getUi()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.onRejectClicked.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserConsentViewModel.this.isLoading.onNext(new LoadingState(true, WaitingScreenTracker.ClassicFlowWaitingReason.REASON_REVOKE_USER_CONSENT));
            }
        }).doOnTerminate(new Action() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UserConsentViewModel.onRejectClicked$lambda$2(this.f$0);
            }
        }).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UserConsentViewModel.onRejectClicked$lambda$3(this.f$0);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentViewModel.onRejectClicked.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                UserConsentViewModel.this.errorMessage.onNext(throwable.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public final void onStart() {
        this.screenTracker.trackUserConsent$onfido_capture_sdk_core_release();
    }
}
