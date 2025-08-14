package com.onfido.android.sdk.capture.ui.documentselection.host;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.ui.countryselection.GetCurrentCountryCodeUseCase;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionState;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 A2\u00020\u0001:\u0002ABBA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001aH\u0002J\u001b\u0010,\u001a\u0015\u0012\f\u0012\n #*\u0004\u0018\u00010\u00120\u00120-¢\u0006\u0002\b$H\u0002J\u0006\u0010.\u001a\u00020*J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020*H\u0014J\u000e\u00102\u001a\u00020*2\u0006\u00103\u001a\u00020\u0012J\u0006\u00104\u001a\u00020*J\u000e\u00105\u001a\u00020*2\u0006\u00103\u001a\u00020\u0012J\"\u00106\u001a\u00020*2\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<J\u0006\u0010=\u001a\u00020*J\u000e\u0010>\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001aJ\u000e\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010 \u001a\u0015\u0012\f\u0012\n #*\u0004\u0018\u00010\"0\"0!¢\u0006\u0002\b$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0019\u0010'\u001a\r\u0012\u0004\u0012\u00020\"0(¢\u0006\u0002\b$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "Landroidx/lifecycle/ViewModel;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "currentCountryCodeUseCase", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/GetCurrentCountryCodeUseCase;", "supportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "screenLoadTracker", "Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;Lcom/onfido/android/sdk/capture/internal/ui/countryselection/GetCurrentCountryCodeUseCase;Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/performance/trackers/ScreenLoadTracker;Landroidx/lifecycle/SavedStateHandle;)V", "defaultCountry", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigator", "()Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "value", "", "nfcWarningVisible", "getNfcWarningVisible", "()Z", "setNfcWarningVisible", "(Z)V", "state", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionState;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "getState$onfido_capture_sdk_core_release", "()Lio/reactivex/rxjava3/core/Observable;", "stateBehaviourSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "cacheAllDocuments", "", "loadSuggestCountry", "getSuggestedCountry", "Lio/reactivex/rxjava3/core/Maybe;", "loadScreens", "loadSuggestedCountry", "Lio/reactivex/rxjava3/disposables/Disposable;", "onCleared", "onCountrySelected", "selectedCountryCode", "onCountrySelectionClicked", "onCountrySelectionScreenResult", "onDocumentSelected", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "onStart", "retryFetchingDocuments", "setNfcRquiredWarning", "warningVisible", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentSelectionHostViewModel extends ViewModel {
    public static final String KEY_COUNTRY_PICKER_RESULT = "rds_country_picker_result_key";
    private static final String KEY_NAVIGATOR_INITIALIZED = "rds_key_navigator_initialized";
    public static final String KEY_NFC_WARNING_VISIBLE = "nfc_warning_visible";
    private final GetCurrentCountryCodeUseCase currentCountryCodeUseCase;
    private CountryCode defaultCountry;
    private final CompositeDisposable disposable;
    private final NavigationManagerHolder navigationManagerHolder;
    private final Navigator navigator;
    private final SavedStateHandle savedStateHandle;
    private final ScreenLoadTracker screenLoadTracker;
    private final ScreenTracker screenTracker;
    private final Observable<DocumentTypeSelectionState> state;
    private final BehaviorSubject<DocumentTypeSelectionState> stateBehaviourSubject;
    private final SupportedDocumentsRepository supportedDocumentsRepository;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bç\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        DocumentSelectionHostViewModel create(SavedStateHandle savedStateHandle);
    }

    @AssistedInject
    public DocumentSelectionHostViewModel(Navigator navigator, NavigationManagerHolder navigationManagerHolder, GetCurrentCountryCodeUseCase currentCountryCodeUseCase, SupportedDocumentsRepository supportedDocumentsRepository, ScreenTracker screenTracker, ScreenLoadTracker screenLoadTracker, @Assisted SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(navigationManagerHolder, "navigationManagerHolder");
        Intrinsics.checkNotNullParameter(currentCountryCodeUseCase, "currentCountryCodeUseCase");
        Intrinsics.checkNotNullParameter(supportedDocumentsRepository, "supportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(screenLoadTracker, "screenLoadTracker");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.navigator = navigator;
        this.navigationManagerHolder = navigationManagerHolder;
        this.currentCountryCodeUseCase = currentCountryCodeUseCase;
        this.supportedDocumentsRepository = supportedDocumentsRepository;
        this.screenTracker = screenTracker;
        this.screenLoadTracker = screenLoadTracker;
        this.savedStateHandle = savedStateHandle;
        this.disposable = new CompositeDisposable();
        BehaviorSubject<DocumentTypeSelectionState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(DocumentTypeSelectionState.NoCountrySelected.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.stateBehaviourSubject = behaviorSubjectCreateDefault;
        Observable<DocumentTypeSelectionState> observableObserveOn = behaviorSubjectCreateDefault.hide().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "observeOn(...)");
        this.state = observableObserveOn;
    }

    private final void cacheAllDocuments(final boolean loadSuggestCountry) {
        this.disposable.add(Maybe.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DocumentSelectionHostViewModel.cacheAllDocuments$lambda$0(this.f$0);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.cacheAllDocuments.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(List<? extends CountryCode> list) {
                if (loadSuggestCountry) {
                    this.disposable.add(this.loadSuggestedCountry());
                }
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.cacheAllDocuments.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to findAllSupportedCountries", new Object[0]);
                DocumentSelectionHostViewModel.this.stateBehaviourSubject.onNext(DocumentTypeSelectionState.LoadingDocumentsFailed.INSTANCE);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List cacheAllDocuments$lambda$0(DocumentSelectionHostViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.supportedDocumentsRepository.findAllSupportedCountries();
    }

    private final boolean getNfcWarningVisible() {
        Boolean bool = (Boolean) this.savedStateHandle.get(KEY_NFC_WARNING_VISIBLE);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final Maybe<CountryCode> getSuggestedCountry() {
        Maybe maybeFlatMap = this.currentCountryCodeUseCase.build().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.getSuggestedCountry.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return !StringsKt.isBlank(it);
            }
        }).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.getSuggestedCountry.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final MaybeSource<? extends CountryCode> apply(String currentCountCode) {
                T next;
                Maybe maybeEmpty;
                Intrinsics.checkNotNullParameter(currentCountCode, "currentCountCode");
                Iterator<T> it = DocumentSelectionHostViewModel.this.supportedDocumentsRepository.findAllSupportedCountries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = (T) null;
                        break;
                    }
                    next = it.next();
                    if (StringsKt.equals(((CountryCode) next).name(), currentCountCode, true)) {
                        break;
                    }
                }
                CountryCode countryCode = next;
                if (DocumentSelectionHostViewModel.this.defaultCountry != null) {
                    CountryCode countryCode2 = DocumentSelectionHostViewModel.this.defaultCountry;
                    Intrinsics.checkNotNull(countryCode2);
                    maybeEmpty = Maybe.just(countryCode2);
                } else {
                    maybeEmpty = countryCode == null ? Maybe.empty() : Maybe.just(countryCode);
                }
                Intrinsics.checkNotNull(maybeEmpty);
                return maybeEmpty;
            }
        });
        Intrinsics.checkNotNullExpressionValue(maybeFlatMap, "flatMap(...)");
        return maybeFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Disposable loadSuggestedCountry() {
        Disposable disposableSubscribe = getSuggestedCountry().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.loadSuggestedCountry.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(CountryCode countryCode) {
                if (DocumentSelectionHostViewModel.this.stateBehaviourSubject.getValue() instanceof DocumentTypeSelectionState.NoCountrySelected) {
                    BehaviorSubject behaviorSubject = DocumentSelectionHostViewModel.this.stateBehaviourSubject;
                    Intrinsics.checkNotNull(countryCode);
                    behaviorSubject.onNext(new DocumentTypeSelectionState.CountrySelected(countryCode));
                }
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel.loadSuggestedCountry.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to getSuggestedCountry", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        return disposableSubscribe;
    }

    private final void setNfcWarningVisible(boolean z) {
        this.savedStateHandle.set(KEY_NFC_WARNING_VISIBLE, Boolean.valueOf(z));
    }

    public final NavigationManagerHolder getNavigationManagerHolder() {
        return this.navigationManagerHolder;
    }

    public final Navigator getNavigator() {
        return this.navigator;
    }

    public final Observable<DocumentTypeSelectionState> getState$onfido_capture_sdk_core_release() {
        return this.state;
    }

    public final void loadScreens() {
        if (!this.savedStateHandle.contains(KEY_NAVIGATOR_INITIALIZED)) {
            this.navigator.navigateTo(new DocumentTypeSelectionScreen(getNfcWarningVisible()));
            this.savedStateHandle.set(KEY_NAVIGATOR_INITIALIZED, Boolean.TRUE);
        }
        cacheAllDocuments(true);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.disposable.clear();
    }

    public final void onCountrySelected(CountryCode selectedCountryCode) {
        Intrinsics.checkNotNullParameter(selectedCountryCode, "selectedCountryCode");
        this.defaultCountry = selectedCountryCode;
        this.stateBehaviourSubject.onNext(new DocumentTypeSelectionState.CountrySelected(selectedCountryCode));
    }

    public final void onCountrySelectionClicked() {
        this.navigator.navigateTo(new CountrySelectionScreen(KEY_COUNTRY_PICKER_RESULT));
    }

    public final void onCountrySelectionScreenResult(CountryCode selectedCountryCode) {
        Intrinsics.checkNotNullParameter(selectedCountryCode, "selectedCountryCode");
        this.navigator.backTo(new DocumentTypeSelectionScreen(getNfcWarningVisible()));
        onCountrySelected(selectedCountryCode);
    }

    public final void onDocumentSelected(DocumentType documentType, String genericDocTitle, DocumentPages genericDocPages) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        DocumentTypeSelectionState value = this.stateBehaviourSubject.getValue();
        if (value instanceof DocumentTypeSelectionState.CountrySelected) {
            this.stateBehaviourSubject.onNext(new DocumentTypeSelectionState.DocumentTypeSelected(((DocumentTypeSelectionState.CountrySelected) value).getCountryCode(), documentType, genericDocTitle, genericDocPages));
        }
    }

    public final void onStart() {
        this.screenTracker.trackDocumentTypeSelection$onfido_capture_sdk_core_release();
        this.screenLoadTracker.trackNavigationCompleted$onfido_capture_sdk_core_release(PerformanceAnalyticsScreen.DOCUMENT_TYPE_SELECTION);
    }

    public final void retryFetchingDocuments(boolean loadSuggestCountry) {
        this.screenTracker.trackDocumentListFetchRetried$onfido_capture_sdk_core_release();
        cacheAllDocuments(loadSuggestCountry);
    }

    public final void setNfcRquiredWarning(boolean warningVisible) {
        setNfcWarningVisible(warningVisible);
    }
}
