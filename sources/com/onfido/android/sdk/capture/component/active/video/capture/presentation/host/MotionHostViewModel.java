package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 C2\u00020\u0001:\u0003CDEB]\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0015J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013H\u0002J\b\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020/H\u0002J\u0006\u00100\u001a\u00020\u0013J\u000e\u00101\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013J\b\u00102\u001a\u00020*H\u0002J\u0006\u00103\u001a\u00020*J\b\u00104\u001a\u00020*H\u0014J\u000e\u00105\u001a\u00020*2\u0006\u00106\u001a\u000207J\u000e\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020:J\u000e\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020*J\u0006\u0010B\u001a\u00020*R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R!\u0010%\u001a\u0015\u0012\f\u0012\n '*\u0004\u0018\u00010\"0\"0&¢\u0006\u0002\b(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "Landroidx/lifecycle/ViewModel;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "runtimePermissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "getFaceDetectionModuleAvailabilityUseCase", "Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "audioEnabled", "", "showIntro", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/component/active/video/capture/domain/usecase/GetFaceDetectionModuleAvailabilityUseCase;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Landroidx/lifecycle/SavedStateHandle;ZZ)V", "getAudioEnabled", "()Z", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigator", "()Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "shouldUseMLKit", "getShowIntro", "viewEvent", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "getViewEvent", "()Lio/reactivex/rxjava3/core/Observable;", "viewEventSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "checkMLKitAvailability", "", "isGooglePlayServiceAvailable", "createMotionCaptureScreen", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionCaptureScreen;", "getCaptureType", "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "hasPermissions", "initialize", "initializeNavigation", "navigateToCaptureScreen", "onCleared", "onError", "error", "", "onPermissionsRequestResult", "grantResults", "", "onPermissionsScreenResult", "permissionResult", "Lcom/onfido/android/sdk/capture/common/permissions/PermissionResult;", "onVideoUploadSuccess", "uploadArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "restartCaptureNavigation", "restorePermissions", "Companion", "Factory", "ViewEvent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionHostViewModel extends ViewModel {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_NAVIGATOR_INITIALIZED = "key_navigator_initialized";
    private final OnfidoAnalytics analytics;
    private final boolean audioEnabled;
    private final CompositeDisposable compositeDisposable;
    private final GetFaceDetectionModuleAvailabilityUseCase getFaceDetectionModuleAvailabilityUseCase;
    private final NavigationManagerHolder navigationManagerHolder;
    private final Navigator navigator;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final RuntimePermissionsManager runtimePermissionsManager;
    private final SavedStateHandle savedStateHandle;
    private final SchedulersProvider schedulersProvider;
    private boolean shouldUseMLKit;
    private final boolean showIntro;
    private final Observable<ViewEvent> viewEvent;
    private final PublishSubject<ViewEvent> viewEventSubject;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$Companion;", "", "()V", "KEY_NAVIGATOR_INITIALIZED", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "audioEnabled", "", "showIntro", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        MotionHostViewModel create(SavedStateHandle savedStateHandle, @Assisted("audioEnabled") boolean audioEnabled, @Assisted("showIntro") boolean showIntro);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "", "Error", "Loading", "RequestPermission", "VideoUploaded", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$Loading;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$RequestPermission;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$VideoUploaded;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ViewEvent {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error implements ViewEvent {
            private final String message;

            public Error(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }

            public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = error.message;
                }
                return error.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final Error copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Error(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
            }

            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            public String toString() {
                return "Error(message=" + this.message + ')';
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$Loading;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "show", "", "(Z)V", "getShow", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Loading implements ViewEvent {
            private final boolean show;

            public Loading(boolean z) {
                this.show = z;
            }

            public static /* synthetic */ Loading copy$default(Loading loading, boolean z, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = loading.show;
                }
                return loading.copy(z);
            }

            /* renamed from: component1, reason: from getter */
            public final boolean getShow() {
                return this.show;
            }

            public final Loading copy(boolean show) {
                return new Loading(show);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Loading) && this.show == ((Loading) other).show;
            }

            public final boolean getShow() {
                return this.show;
            }

            public int hashCode() {
                return Boolean.hashCode(this.show);
            }

            public String toString() {
                return "Loading(show=" + this.show + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$RequestPermission;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", App.JsonKeys.APP_PERMISSIONS, "", "", "(Ljava/util/List;)V", "getPermissions", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class RequestPermission implements ViewEvent {
            private final List<String> permissions;

            public RequestPermission(List<String> permissions) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                this.permissions = permissions;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ RequestPermission copy$default(RequestPermission requestPermission, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    list = requestPermission.permissions;
                }
                return requestPermission.copy(list);
            }

            public final List<String> component1() {
                return this.permissions;
            }

            public final RequestPermission copy(List<String> permissions) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                return new RequestPermission(permissions);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof RequestPermission) && Intrinsics.areEqual(this.permissions, ((RequestPermission) other).permissions);
            }

            public final List<String> getPermissions() {
                return this.permissions;
            }

            public int hashCode() {
                return this.permissions.hashCode();
            }

            public String toString() {
                return "RequestPermission(permissions=" + this.permissions + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent$VideoUploaded;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel$ViewEvent;", "uploadedArtifact", "Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "(Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;)V", "getUploadedArtifact", "()Lcom/onfido/android/sdk/capture/internal/model/UploadedArtifact;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class VideoUploaded implements ViewEvent {
            private final UploadedArtifact uploadedArtifact;

            public VideoUploaded(UploadedArtifact uploadedArtifact) {
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                this.uploadedArtifact = uploadedArtifact;
            }

            public static /* synthetic */ VideoUploaded copy$default(VideoUploaded videoUploaded, UploadedArtifact uploadedArtifact, int i, Object obj) {
                if ((i & 1) != 0) {
                    uploadedArtifact = videoUploaded.uploadedArtifact;
                }
                return videoUploaded.copy(uploadedArtifact);
            }

            /* renamed from: component1, reason: from getter */
            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public final VideoUploaded copy(UploadedArtifact uploadedArtifact) {
                Intrinsics.checkNotNullParameter(uploadedArtifact, "uploadedArtifact");
                return new VideoUploaded(uploadedArtifact);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof VideoUploaded) && Intrinsics.areEqual(this.uploadedArtifact, ((VideoUploaded) other).uploadedArtifact);
            }

            public final UploadedArtifact getUploadedArtifact() {
                return this.uploadedArtifact;
            }

            public int hashCode() {
                return this.uploadedArtifact.hashCode();
            }

            public String toString() {
                return "VideoUploaded(uploadedArtifact=" + this.uploadedArtifact + ')';
            }
        }
    }

    @AssistedInject
    public MotionHostViewModel(Navigator navigator, NavigationManagerHolder navigationManagerHolder, RuntimePermissionsManager runtimePermissionsManager, OnfidoAnalytics analytics, OnfidoRemoteConfig onfidoRemoteConfig, GetFaceDetectionModuleAvailabilityUseCase getFaceDetectionModuleAvailabilityUseCase, SchedulersProvider schedulersProvider, @Assisted SavedStateHandle savedStateHandle, @Assisted("audioEnabled") boolean z, @Assisted("showIntro") boolean z2) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(navigationManagerHolder, "navigationManagerHolder");
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "runtimePermissionsManager");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(getFaceDetectionModuleAvailabilityUseCase, "getFaceDetectionModuleAvailabilityUseCase");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.navigator = navigator;
        this.navigationManagerHolder = navigationManagerHolder;
        this.runtimePermissionsManager = runtimePermissionsManager;
        this.analytics = analytics;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.getFaceDetectionModuleAvailabilityUseCase = getFaceDetectionModuleAvailabilityUseCase;
        this.schedulersProvider = schedulersProvider;
        this.savedStateHandle = savedStateHandle;
        this.audioEnabled = z;
        this.showIntro = z2;
        PublishSubject<ViewEvent> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.viewEventSubject = publishSubjectCreate;
        this.compositeDisposable = new CompositeDisposable();
        Observable<ViewEvent> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.viewEvent = observableHide;
    }

    private final void checkMLKitAvailability(boolean isGooglePlayServiceAvailable) {
        if (!isGooglePlayServiceAvailable) {
            this.analytics.track(AvcAnalyticsEvent.PlayServicesError.INSTANCE);
            initializeNavigation();
            return;
        }
        this.viewEventSubject.onNext(new ViewEvent.Loading(true));
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Single<GetFaceDetectionModuleAvailabilityUseCase.Result> singleDoFinally = this.getFaceDetectionModuleAvailabilityUseCase.invoke().subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MotionHostViewModel.checkMLKitAvailability$lambda$0(this.f$0);
            }
        });
        Consumer<? super GetFaceDetectionModuleAvailabilityUseCase.Result> consumer = new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel.checkMLKitAvailability.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(GetFaceDetectionModuleAvailabilityUseCase.Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Intrinsics.areEqual(it, GetFaceDetectionModuleAvailabilityUseCase.Result.Available.INSTANCE)) {
                    MotionHostViewModel.this.shouldUseMLKit = true;
                } else {
                    MotionHostViewModel.this.analytics.track(new AvcAnalyticsEvent.MLKitError(GetFaceDetectionModuleAvailabilityUseCase.ERROR_MESSAGE_ML_KIT));
                }
            }
        };
        final Timber.Companion companion = Timber.INSTANCE;
        Disposable disposableSubscribe = singleDoFinally.subscribe(consumer, new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel.checkMLKitAvailability.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                companion.e(th);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkMLKitAvailability$lambda$0(MotionHostViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.viewEventSubject.onNext(new ViewEvent.Loading(false));
        this$0.initializeNavigation();
    }

    private final MotionCaptureScreen createMotionCaptureScreen() {
        return new MotionCaptureScreen(this.shouldUseMLKit, this.onfidoRemoteConfig.isMotionCameraXEnabled());
    }

    private final CaptureType getCaptureType() {
        return this.audioEnabled ? CaptureType.VIDEO : CaptureType.FACE;
    }

    private final void initializeNavigation() {
        if (this.savedStateHandle.contains(KEY_NAVIGATOR_INITIALIZED)) {
            return;
        }
        if (this.showIntro) {
            this.navigator.navigateTo(MotionIntroScreen.INSTANCE);
        } else {
            navigateToCaptureScreen();
        }
        this.savedStateHandle.set(KEY_NAVIGATOR_INITIALIZED, Boolean.TRUE);
    }

    public final boolean getAudioEnabled() {
        return this.audioEnabled;
    }

    public final NavigationManagerHolder getNavigationManagerHolder() {
        return this.navigationManagerHolder;
    }

    public final Navigator getNavigator() {
        return this.navigator;
    }

    public final boolean getShowIntro() {
        return this.showIntro;
    }

    public final Observable<ViewEvent> getViewEvent() {
        return this.viewEvent;
    }

    public final boolean hasPermissions() {
        return this.runtimePermissionsManager.hasPermissionsForCaptureType(getCaptureType());
    }

    public final void initialize(boolean isGooglePlayServiceAvailable) {
        if (this.onfidoRemoteConfig.isMotionTensorFlowLiteEnabled()) {
            initializeNavigation();
        } else {
            checkMLKitAvailability(isGooglePlayServiceAvailable);
        }
    }

    public final void navigateToCaptureScreen() {
        Navigator navigator;
        Screen permissionsScreen;
        if (hasPermissions()) {
            navigator = this.navigator;
            permissionsScreen = createMotionCaptureScreen();
        } else {
            navigator = this.navigator;
            permissionsScreen = new PermissionsScreen(new CaptureStepDataBundle(getCaptureType(), null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null));
        }
        navigator.navigateTo(permissionsScreen);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.clear();
    }

    public final void onError(Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        String message = error.getMessage();
        if (message == null) {
            message = "";
        }
        Timber.INSTANCE.e(message, new Object[0]);
        this.analytics.track(new AvcAnalyticsEvent.CameraError(message));
        this.viewEventSubject.onNext(new ViewEvent.Error(message));
    }

    public final void onPermissionsRequestResult(int[] grantResults) {
        Navigator navigator;
        Screen permissionsScreen;
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (this.runtimePermissionsManager.werePermissionsGranted$onfido_capture_sdk_core_release(grantResults)) {
            navigator = this.navigator;
            permissionsScreen = createMotionCaptureScreen();
        } else {
            navigator = this.navigator;
            permissionsScreen = new PermissionsScreen(new CaptureStepDataBundle(getCaptureType(), null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null));
        }
        navigator.navigateTo(permissionsScreen);
    }

    public final void onPermissionsScreenResult(PermissionResult permissionResult) {
        Intrinsics.checkNotNullParameter(permissionResult, "permissionResult");
        this.navigator.exitCurrentScreen();
        if (permissionResult instanceof PermissionResult.Granted) {
            List<String> permissionsForCaptureType = this.runtimePermissionsManager.getPermissionsForCaptureType(((PermissionResult.Granted) permissionResult).getCaptureStepDataBundle().getCaptureType());
            ArrayList arrayList = new ArrayList();
            for (Object obj : permissionsForCaptureType) {
                if (!this.runtimePermissionsManager.hasPermission((String) obj)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                this.navigator.navigateTo(createMotionCaptureScreen());
            } else {
                this.viewEventSubject.onNext(new ViewEvent.RequestPermission(arrayList));
            }
        }
    }

    public final void onVideoUploadSuccess(UploadedArtifact uploadArtifact) {
        Intrinsics.checkNotNullParameter(uploadArtifact, "uploadArtifact");
        this.viewEventSubject.onNext(new ViewEvent.VideoUploaded(uploadArtifact));
    }

    public final void restartCaptureNavigation() {
        this.navigator.exitCurrentScreen();
        if (this.showIntro) {
            return;
        }
        navigateToCaptureScreen();
    }

    public final void restorePermissions() {
        this.navigator.exitCurrentScreen();
        this.navigator.navigateTo(new PermissionsScreen(new CaptureStepDataBundle(getCaptureType(), null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null)));
    }
}
