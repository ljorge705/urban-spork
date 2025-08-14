package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 #2\u00020\u0001:\u0002#$B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0014J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00172\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0018H\u0002J7\u0010\u001c\u001a!\u0012\u0018\u0012\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u001d0\u0017¢\u0006\u0002\b\u001e2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0018H\u0002J\u0018\u0010\u001f\u001a\u00020\u00142\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0018J\u0010\u0010 \u001a\u00020\u00142\b\b\u0002\u0010!\u001a\u00020\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter;", "", "supportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "getCurrentCountryCodeUseCase", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/GetCurrentCountryCodeUseCase;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/ui/countryselection/SupportedDocumentsRepository;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/ui/countryselection/GetCurrentCountryCodeUseCase;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "subscriptions", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getSubscriptions", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "subscriptions$delegate", "Lkotlin/Lazy;", "view", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter$View;", "attachView", "", "detachView", "getCountryAvailabilities", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountryAvailability;", "supportedCountries", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getCountryAvailabilitiesWithSuggestion", "Lkotlin/Pair;", "Lio/reactivex/rxjava3/annotations/NonNull;", "getCountrySuggestion", "onStart", "isForProofOfAddress", "", "Companion", "View", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CountrySelectionPresenter {
    private static final long COUNTRY_LIST_ITEMS_DELAY_MS = 250;
    private static final Companion Companion = new Companion(null);
    private static final int SIZE_PADDING = 4;
    private final GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase;
    private final SchedulersProvider schedulersProvider;
    private final ScreenTracker screenTracker;

    /* renamed from: subscriptions$delegate, reason: from kotlin metadata */
    private final Lazy subscriptions;
    private final SupportedDocumentsRepository supportedDocumentsRepository;
    private View view;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter$Companion;", "", "()V", "COUNTRY_LIST_ITEMS_DELAY_MS", "", "SIZE_PADDING", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter$View;", "", "enterLoadingState", "", "setCountries", "countries", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/BaseAdapterItem;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface View {
        void enterLoadingState();

        void setCountries(List<? extends BaseAdapterItem> countries);
    }

    @Inject
    public CountrySelectionPresenter(SupportedDocumentsRepository supportedDocumentsRepository, ScreenTracker screenTracker, GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(supportedDocumentsRepository, "supportedDocumentsRepository");
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(getCurrentCountryCodeUseCase, "getCurrentCountryCodeUseCase");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.supportedDocumentsRepository = supportedDocumentsRepository;
        this.screenTracker = screenTracker;
        this.getCurrentCountryCodeUseCase = getCurrentCountryCodeUseCase;
        this.schedulersProvider = schedulersProvider;
        this.subscriptions = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter$subscriptions$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
    }

    private final Single<List<CountryAvailability>> getCountryAvailabilities(final List<? extends CountryCode> supportedCountries) {
        Single<List<CountryAvailability>> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CountrySelectionPresenter.getCountryAvailabilities$lambda$1(supportedCountries, this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getCountryAvailabilities$lambda$1(List list, CountrySelectionPresenter this$0) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null) {
            List<CountryCode> listFindAllSupportedCountries = this$0.supportedDocumentsRepository.findAllSupportedCountries();
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFindAllSupportedCountries, 10));
            Iterator<T> it = listFindAllSupportedCountries.iterator();
            while (it.hasNext()) {
                arrayList.add(new CountryAvailability((CountryCode) it.next()));
            }
        } else {
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(new CountryAvailability((CountryCode) it2.next()));
            }
        }
        return arrayList;
    }

    private final Single<Pair<CountryAvailability, List<CountryAvailability>>> getCountryAvailabilitiesWithSuggestion(List<? extends CountryCode> supportedCountries) {
        Single singleFlatMap = getCountryAvailabilities(supportedCountries).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.getCountryAvailabilitiesWithSuggestion.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends Pair<CountryAvailability, List<CountryAvailability>>> apply(final List<CountryAvailability> countryAvailabilities) {
                Intrinsics.checkNotNullParameter(countryAvailabilities, "countryAvailabilities");
                return CountrySelectionPresenter.this.getCurrentCountryCodeUseCase.build().map(new Function() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.getCountryAvailabilitiesWithSuggestion.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Pair<CountryAvailability, List<CountryAvailability>> apply(String suggestedCountryCode) {
                        T next;
                        Intrinsics.checkNotNullParameter(suggestedCountryCode, "suggestedCountryCode");
                        Iterator<T> it = countryAvailabilities.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                next = (T) null;
                                break;
                            }
                            next = it.next();
                            String strName = ((CountryAvailability) next).getCountryCode().name();
                            Locale locale = Locale.ROOT;
                            String lowerCase = strName.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                            String lowerCase2 = suggestedCountryCode.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                                break;
                            }
                        }
                        CountryAvailability countryAvailability = next;
                        List<CountryAvailability> list = countryAvailabilities;
                        ArrayList arrayList = new ArrayList();
                        for (T t : list) {
                            if (((CountryAvailability) t).getCountryCode() != (countryAvailability != null ? countryAvailability.getCountryCode() : null)) {
                                arrayList.add(t);
                            }
                        }
                        return TuplesKt.to(countryAvailability, arrayList);
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "flatMap(...)");
        return singleFlatMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void getCountrySuggestion$default(CountrySelectionPresenter countrySelectionPresenter, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        countrySelectionPresenter.getCountrySuggestion(list);
    }

    private final CompositeDisposable getSubscriptions() {
        return (CompositeDisposable) this.subscriptions.getValue();
    }

    public static /* synthetic */ void onStart$default(CountrySelectionPresenter countrySelectionPresenter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        countrySelectionPresenter.onStart(z);
    }

    public final void attachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    public final void detachView() {
        getSubscriptions().clear();
    }

    public final void getCountrySuggestion(List<? extends CountryCode> supportedCountries) {
        CompositeDisposable subscriptions = getSubscriptions();
        Disposable disposableSubscribe = getCountryAvailabilitiesWithSuggestion(supportedCountries).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).delay(COUNTRY_LIST_ITEMS_DELAY_MS, TimeUnit.MILLISECONDS, this.schedulersProvider.getUi()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.getCountrySuggestion.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                View view = CountrySelectionPresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.enterLoadingState();
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.getCountrySuggestion.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<CountryAvailability, ? extends List<CountryAvailability>> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                CountryAvailability countryAvailabilityComponent1 = pair.component1();
                List<CountryAvailability> listComponent2 = pair.component2();
                ArrayList arrayList = new ArrayList(listComponent2.size() + 4);
                if (countryAvailabilityComponent1 != null) {
                    arrayList.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.SUGGESTED_COUNTRY));
                    arrayList.add(countryAvailabilityComponent1);
                    arrayList.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.SEPARATOR));
                    arrayList.add(new CountrySelectionSeparator(CountrySelectionSeparatorType.ALL_COUNTRIES));
                }
                arrayList.addAll(listComponent2);
                View view = CountrySelectionPresenter.this.view;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                    view = null;
                }
                view.setCountries(arrayList);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.getCountrySuggestion.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failed to getCountrySuggestion", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(subscriptions, disposableSubscribe);
    }

    public final void onStart(boolean isForProofOfAddress) {
        this.screenTracker.trackCountrySelection$onfido_capture_sdk_core_release(isForProofOfAddress);
    }
}
