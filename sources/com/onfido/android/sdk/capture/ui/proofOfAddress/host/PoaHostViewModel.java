package com.onfido.android.sdk.capture.ui.proofOfAddress.host;

import android.content.res.Resources;
import android.net.Uri;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaRepository;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.PoaResponseItem;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.CountryCodeExtensionsKt;
import com.onfido.android.sdk.capture.utils.RawResourceReader;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u0000 R2\u00020\u0001:\u0003RSTB1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010B\u001a\u00020\u0014J+\u0010C\u001a\u0012\u0012\u0004\u0012\u00020\u00140Dj\b\u0012\u0004\u0012\u00020\u0014`E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020;0:H\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010I\u001a\u00020 J\b\u0010J\u001a\u0004\u0018\u00010&J\u0006\u0010K\u001a\u00020LJ\u0016\u0010M\u001a\u0012\u0012\u0004\u0012\u00020 0Dj\b\u0012\u0004\u0012\u00020 `EJ\u0006\u0010N\u001a\u000205J\b\u0010O\u001a\u00020LH\u0014J6\u0010P\u001a\u00020L2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u001aR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u001a8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020 8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R(\u0010'\u001a\u0004\u0018\u00010&2\b\u0010\u0013\u001a\u0004\u0018\u00010&8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0019\u0010,\u001a\r\u0012\u0004\u0012\u00020\u001a0-¢\u0006\u0002\b.X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010/\u001a\b\u0012\u0004\u0012\u000201008@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0012\u001a\u0004\b2\u00103R$\u00106\u001a\u0002052\u0006\u0010\u0013\u001a\u0002058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020;0:8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001f\u0010A\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140:0-¢\u0006\u0002\b.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostViewModel;", "Landroidx/lifecycle/ViewModel;", "poaRepository", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaRepository;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "resourceReader", "Lcom/onfido/android/sdk/capture/utils/RawResourceReader;", "jsonParser", "Lkotlinx/serialization/json/Json;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaRepository;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/utils/RawResourceReader;Lkotlinx/serialization/json/Json;Landroidx/lifecycle/SavedStateHandle;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "value", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "setCountryCode", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "", "documentFileName", "getDocumentFileName", "()Ljava/lang/String;", "setDocumentFileName", "(Ljava/lang/String;)V", "Lcom/onfido/api/client/data/PoaDocumentType;", "documentType", "getDocumentType", "()Lcom/onfido/api/client/data/PoaDocumentType;", "setDocumentType", "(Lcom/onfido/api/client/data/PoaDocumentType;)V", "Landroid/net/Uri;", "documentUri", "getDocumentUri", "()Landroid/net/Uri;", "setDocumentUri", "(Landroid/net/Uri;)V", "errorMessageSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lio/reactivex/rxjava3/annotations/NonNull;", "getCountriesResponse", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostViewModel$GetCountriesResponse;", "getGetCountriesResponse$onfido_capture_sdk_core_release", "()Lio/reactivex/rxjava3/core/Observable;", "getCountriesResponse$delegate", "", "isTakePhoto", "()Z", "setTakePhoto", "(Z)V", "", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaResponseItem;", "listOfSupportedCountries", "getListOfSupportedCountries$onfido_capture_sdk_core_release", "()Ljava/util/List;", "setListOfSupportedCountries$onfido_capture_sdk_core_release", "(Ljava/util/List;)V", "poaCountriesSubject", "getPoaCountryCode", "getPoaCountryCodes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "poaListItems", "getPoaCountryCodes$onfido_capture_sdk_core_release", "getPoaDocumentFileName", "getPoaDocumentType", "getPoaDocumentUri", "getPoaSupportedCountries", "", "getPoaSupportedDocuments", "isPoaTakePhoto", "onCleared", "setPoaData", "poaDocumentFileName", "Companion", "Factory", "GetCountriesResponse", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaHostViewModel extends ViewModel {
    private static final String KEY_COUNTRY_CODE = "country_code";
    private static final String KEY_DOCUMENT_FILE_NAME = "document_file_name";
    private static final String KEY_DOCUMENT_TYPE = "document_type";
    private static final String KEY_DOCUMENT_URI = "document_uri";
    private static final String KEY_IS_TAKE_PHOTO = "is_take_photo";
    private static final String KEY_LIST_OF_SUPPORTED_COUNTRIES = "list_of_supported_countries";

    /* renamed from: compositeDisposable$delegate, reason: from kotlin metadata */
    private final Lazy compositeDisposable;
    private final BehaviorSubject<String> errorMessageSubject;

    /* renamed from: getCountriesResponse$delegate, reason: from kotlin metadata */
    private final Lazy getCountriesResponse;
    private final Json jsonParser;
    private final BehaviorSubject<List<CountryCode>> poaCountriesSubject;
    private final PoaRepository poaRepository;
    private final RawResourceReader resourceReader;
    private final SavedStateHandle savedStateHandle;
    private final SchedulersProvider schedulersProvider;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostViewModel$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        PoaHostViewModel create(SavedStateHandle savedStateHandle);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostViewModel$GetCountriesResponse;", "", "poaCountries", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "errorString", "", "(Ljava/util/List;Ljava/lang/String;)V", "getErrorString", "()Ljava/lang/String;", "getPoaCountries", "()Ljava/util/List;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class GetCountriesResponse {
        private final String errorString;
        private final List<CountryCode> poaCountries;

        /* JADX WARN: Multi-variable type inference failed */
        public GetCountriesResponse() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GetCountriesResponse copy$default(GetCountriesResponse getCountriesResponse, List list, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = getCountriesResponse.poaCountries;
            }
            if ((i & 2) != 0) {
                str = getCountriesResponse.errorString;
            }
            return getCountriesResponse.copy(list, str);
        }

        public final List<CountryCode> component1() {
            return this.poaCountries;
        }

        /* renamed from: component2, reason: from getter */
        public final String getErrorString() {
            return this.errorString;
        }

        public final GetCountriesResponse copy(List<? extends CountryCode> poaCountries, String errorString) {
            Intrinsics.checkNotNullParameter(poaCountries, "poaCountries");
            return new GetCountriesResponse(poaCountries, errorString);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GetCountriesResponse)) {
                return false;
            }
            GetCountriesResponse getCountriesResponse = (GetCountriesResponse) other;
            return Intrinsics.areEqual(this.poaCountries, getCountriesResponse.poaCountries) && Intrinsics.areEqual(this.errorString, getCountriesResponse.errorString);
        }

        public final String getErrorString() {
            return this.errorString;
        }

        public final List<CountryCode> getPoaCountries() {
            return this.poaCountries;
        }

        public int hashCode() {
            int iHashCode = this.poaCountries.hashCode() * 31;
            String str = this.errorString;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "GetCountriesResponse(poaCountries=" + this.poaCountries + ", errorString=" + this.errorString + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public GetCountriesResponse(List<? extends CountryCode> poaCountries, String str) {
            Intrinsics.checkNotNullParameter(poaCountries, "poaCountries");
            this.poaCountries = poaCountries;
            this.errorString = str;
        }

        public /* synthetic */ GetCountriesResponse(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : str);
        }
    }

    @AssistedInject
    public PoaHostViewModel(PoaRepository poaRepository, SchedulersProvider schedulersProvider, RawResourceReader resourceReader, Json jsonParser, @Assisted SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(poaRepository, "poaRepository");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(resourceReader, "resourceReader");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.poaRepository = poaRepository;
        this.schedulersProvider = schedulersProvider;
        this.resourceReader = resourceReader;
        this.jsonParser = jsonParser;
        this.savedStateHandle = savedStateHandle;
        this.compositeDisposable = LazyKt.lazy(new Function0<CompositeDisposable>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel$compositeDisposable$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CompositeDisposable invoke() {
                return new CompositeDisposable();
            }
        });
        BehaviorSubject<List<CountryCode>> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.poaCountriesSubject = behaviorSubjectCreateDefault;
        BehaviorSubject<String> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault("");
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault2, "createDefault(...)");
        this.errorMessageSubject = behaviorSubjectCreateDefault2;
        this.getCountriesResponse = LazyKt.lazy(new Function0<Observable<GetCountriesResponse>>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel$getCountriesResponse$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Observable<PoaHostViewModel.GetCountriesResponse> invoke() {
                return Observable.combineLatest(this.this$0.poaCountriesSubject, this.this$0.errorMessageSubject, new BiFunction() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel$getCountriesResponse$2.1
                    @Override // io.reactivex.rxjava3.functions.BiFunction
                    public final PoaHostViewModel.GetCountriesResponse apply(List<? extends CountryCode> list, String str) {
                        Intrinsics.checkNotNull(list);
                        return new PoaHostViewModel.GetCountriesResponse(list, str);
                    }
                });
            }
        });
    }

    private final CompositeDisposable getCompositeDisposable() {
        return (CompositeDisposable) this.compositeDisposable.getValue();
    }

    private final CountryCode getCountryCode() {
        Object obj = this.savedStateHandle.get("country_code");
        if (obj != null) {
            return (CountryCode) obj;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private final String getDocumentFileName() {
        return (String) this.savedStateHandle.get(KEY_DOCUMENT_FILE_NAME);
    }

    private final PoaDocumentType getDocumentType() {
        Object obj = this.savedStateHandle.get("document_type");
        if (obj != null) {
            return (PoaDocumentType) obj;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private final Uri getDocumentUri() {
        return (Uri) this.savedStateHandle.get(KEY_DOCUMENT_URI);
    }

    private final boolean isTakePhoto() {
        Boolean bool = (Boolean) this.savedStateHandle.get(KEY_IS_TAKE_PHOTO);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final void setCountryCode(CountryCode countryCode) {
        this.savedStateHandle.set("country_code", countryCode);
    }

    private final void setDocumentFileName(String str) {
        this.savedStateHandle.set(KEY_DOCUMENT_FILE_NAME, str);
    }

    private final void setDocumentType(PoaDocumentType poaDocumentType) {
        this.savedStateHandle.set("document_type", poaDocumentType);
    }

    private final void setDocumentUri(Uri uri) {
        this.savedStateHandle.set(KEY_DOCUMENT_URI, uri);
    }

    public static /* synthetic */ void setPoaData$default(PoaHostViewModel poaHostViewModel, CountryCode countryCode, PoaDocumentType poaDocumentType, Uri uri, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            countryCode = null;
        }
        if ((i & 2) != 0) {
            poaDocumentType = null;
        }
        if ((i & 4) != 0) {
            uri = null;
        }
        if ((i & 8) != 0) {
            str = null;
        }
        poaHostViewModel.setPoaData(countryCode, poaDocumentType, uri, str);
    }

    private final void setTakePhoto(boolean z) {
        this.savedStateHandle.set(KEY_IS_TAKE_PHOTO, Boolean.valueOf(z));
    }

    public final Observable<GetCountriesResponse> getGetCountriesResponse$onfido_capture_sdk_core_release() {
        Object value = this.getCountriesResponse.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Observable) value;
    }

    public final List<PoaResponseItem> getListOfSupportedCountries$onfido_capture_sdk_core_release() {
        Object obj = this.savedStateHandle.get(KEY_LIST_OF_SUPPORTED_COUNTRIES);
        if (obj == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Json.Companion companion = Json.INSTANCE;
        return (List) companion.decodeFromString(SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(PoaResponseItem.class)))), (String) obj);
    }

    public final CountryCode getPoaCountryCode() {
        return getCountryCode();
    }

    public final ArrayList<CountryCode> getPoaCountryCodes$onfido_capture_sdk_core_release(List<PoaResponseItem> poaListItems) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(poaListItems, "poaListItems");
        setListOfSupportedCountries$onfido_capture_sdk_core_release(poaListItems);
        ArrayList arrayList = new ArrayList();
        for (PoaResponseItem poaResponseItem : poaListItems) {
            CountryCode countryCodeValueOf = CountryCode.valueOf(poaResponseItem.getCountryAlpha2());
            Json json = this.jsonParser;
            String str = this.resourceReader.read(R.raw.onfido_country_code_native_name_map);
            SerializersModule serializersModule = json.getSerializersModule();
            KTypeProjection.Companion companion = KTypeProjection.INSTANCE;
            Map map = (Map) json.decodeFromString(SerializersKt.serializer(serializersModule, Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(String.class)))), str);
            countryCodeValueOf.setLocaleName(poaResponseItem.getCountryLocaleName());
            String str2 = (String) map.get(countryCodeValueOf.getAlpha3());
            if (str2 == null) {
                str2 = "";
            }
            countryCodeValueOf.setNativeName(str2);
            arrayList.add(countryCodeValueOf);
        }
        List listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel$getPoaCountryCodes$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(CountryCodeExtensionsKt.getDisplayName((CountryCode) t), CountryCodeExtensionsKt.getDisplayName((CountryCode) t2));
            }
        });
        ArrayList<CountryCode> arrayList2 = new ArrayList<>();
        arrayList2.addAll(listSortedWith);
        return arrayList2;
    }

    public final String getPoaDocumentFileName() {
        return getDocumentFileName();
    }

    public final PoaDocumentType getPoaDocumentType() {
        return getDocumentType();
    }

    public final Uri getPoaDocumentUri() {
        return getDocumentUri();
    }

    public final void getPoaSupportedCountries() {
        CompositeDisposable compositeDisposable = getCompositeDisposable();
        Disposable disposableSubscribe = this.poaRepository.getPoaSupportedCountries$onfido_capture_sdk_core_release().subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel.getPoaSupportedCountries.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ArrayList<CountryCode> apply(List<PoaResponseItem> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return PoaHostViewModel.this.getPoaCountryCodes$onfido_capture_sdk_core_release(it);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel.getPoaSupportedCountries.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(ArrayList<CountryCode> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaHostViewModel.this.poaCountriesSubject.onNext(it);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel.getPoaSupportedCountries.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PoaHostViewModel.this.errorMessageSubject.onNext(it.getMessage());
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    public final ArrayList<PoaDocumentType> getPoaSupportedDocuments() {
        for (PoaResponseItem poaResponseItem : getListOfSupportedCountries$onfido_capture_sdk_core_release()) {
            if (Intrinsics.areEqual(poaResponseItem.getCountryAlpha2(), getCountryCode().name())) {
                List<PoaDocumentType> documentTypes = poaResponseItem.getDocumentTypes();
                ArrayList<PoaDocumentType> arrayList = new ArrayList<>();
                arrayList.addAll(documentTypes);
                return arrayList;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final boolean isPoaTakePhoto() {
        return isTakePhoto();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        getCompositeDisposable().clear();
    }

    public final void setListOfSupportedCountries$onfido_capture_sdk_core_release(List<PoaResponseItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        SavedStateHandle savedStateHandle = this.savedStateHandle;
        Json.Companion companion = Json.INSTANCE;
        savedStateHandle.set(KEY_LIST_OF_SUPPORTED_COUNTRIES, companion.encodeToString(SerializersKt.serializer(companion.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(PoaResponseItem.class)))), value));
    }

    public final void setPoaData(CountryCode countryCode, PoaDocumentType documentType, Uri documentUri, String poaDocumentFileName) {
        if (countryCode != null) {
            setCountryCode(countryCode);
        }
        if (documentType != null) {
            setDocumentType(documentType);
        }
        if (documentUri != null) {
            setDocumentUri(documentUri);
            setTakePhoto(false);
        }
        if (poaDocumentFileName != null) {
            setDocumentFileName(poaDocumentFileName);
            setTakePhoto(true);
        }
    }
}
