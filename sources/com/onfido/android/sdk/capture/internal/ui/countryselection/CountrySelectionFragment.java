package com.onfido.android.sdk.capture.internal.ui.countryselection;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.os.BundleKt;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentCountrySelectionBinding;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.widget.RecyclerView;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.FontInfo;
import com.onfido.javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.spongycastle.crypto.tls.CipherSuite;

@Metadata(d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u000e\b\u0007\u0018\u0000 22\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u000223B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0016J\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0018H\u0016J\b\u0010\"\u001a\u00020\u001eH\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020&H\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\u001a\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0016\u0010/\u001a\u00020\u001e2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0017H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R#\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a¨\u00064"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter$View;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionListener;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentCountrySelectionBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentCountrySelectionBinding;", "countriesAdapter", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter;", "menuProvider", "com/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment$menuProvider$1", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment$menuProvider$1;", "presenter", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter;", "getPresenter$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter;", "setPresenter$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionPresenter;)V", "supportedCountries", "", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getSupportedCountries", "()Ljava/util/List;", "supportedCountries$delegate", "Lkotlin/Lazy;", "announceCountriesCountForAccessibility", "", "enterLoadingState", "onCountrySelected", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "onDestroyView", "onQueryTextChange", "", "newText", "", "onQueryTextSubmit", SearchIntents.EXTRA_QUERY, "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setCountries", "countries", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/BaseAdapterItem;", "Companion", "CountrySelectionResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CountrySelectionFragment extends BaseFragment implements CountrySelectionPresenter.View, CountrySelectionListener, SearchView.OnQueryTextListener {
    private static final long ANNOUNCEMENT_DELAY_MILLIS = 1000;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String RESULT_COUNTRY_SELECTION = "country_selection_result";
    private static final String RESULT_KEY = "KEY_RESULT";
    private static final String SUPPORTED_COUNTRIES = "SUPPORTED_COUNTRIES";
    private OnfidoFragmentCountrySelectionBinding _binding;
    private CountrySelectionAdapter countriesAdapter;
    private final CountrySelectionFragment$menuProvider$1 menuProvider;

    @Inject
    public CountrySelectionPresenter presenter;

    /* renamed from: supportedCountries$delegate, reason: from kotlin metadata */
    private final Lazy supportedCountries;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rj\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment$Companion;", "", "()V", "ANNOUNCEMENT_DELAY_MILLIS", "", "RESULT_COUNTRY_SELECTION", "", "RESULT_KEY", CountrySelectionFragment.SUPPORTED_COUNTRIES, "createInstance", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment;", "resultKey", "poaSupportedCountries", "Ljava/util/ArrayList;", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "Lkotlin/collections/ArrayList;", "getResult", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment$CountrySelectionResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CountrySelectionFragment createInstance$default(Companion companion, String str, ArrayList arrayList, int i, Object obj) {
            if ((i & 2) != 0) {
                arrayList = null;
            }
            return companion.createInstance(str, arrayList);
        }

        @JvmStatic
        public final CountrySelectionFragment createInstance(String resultKey, ArrayList<CountryCode> poaSupportedCountries) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            CountrySelectionFragment countrySelectionFragment = new CountrySelectionFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CountrySelectionFragment.RESULT_KEY, resultKey);
            bundle.putSerializable(CountrySelectionFragment.SUPPORTED_COUNTRIES, poaSupportedCountries);
            countrySelectionFragment.setArguments(bundle);
            return countrySelectionFragment;
        }

        @JvmStatic
        public final CountrySelectionResult getResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(CountrySelectionFragment.RESULT_COUNTRY_SELECTION);
            Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment.CountrySelectionResult");
            return (CountrySelectionResult) parcelable;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\nHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment$CountrySelectionResult;", "Landroid/os/Parcelable;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "component1", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CountrySelectionResult implements Parcelable {
        public static final Parcelable.Creator<CountrySelectionResult> CREATOR = new Creator();
        private final CountryCode countryCode;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CountrySelectionResult> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CountrySelectionResult createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CountrySelectionResult(CountryCode.valueOf(parcel.readString()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final CountrySelectionResult[] newArray(int i) {
                return new CountrySelectionResult[i];
            }
        }

        public CountrySelectionResult(CountryCode countryCode) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            this.countryCode = countryCode;
        }

        public static /* synthetic */ CountrySelectionResult copy$default(CountrySelectionResult countrySelectionResult, CountryCode countryCode, int i, Object obj) {
            if ((i & 1) != 0) {
                countryCode = countrySelectionResult.countryCode;
            }
            return countrySelectionResult.copy(countryCode);
        }

        /* renamed from: component1, reason: from getter */
        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public final CountrySelectionResult copy(CountryCode countryCode) {
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            return new CountrySelectionResult(countryCode);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CountrySelectionResult) && this.countryCode == ((CountrySelectionResult) other).countryCode;
        }

        public final CountryCode getCountryCode() {
            return this.countryCode;
        }

        public int hashCode() {
            return this.countryCode.hashCode();
        }

        public String toString() {
            return "CountrySelectionResult(countryCode=" + this.countryCode + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.countryCode.name());
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$announceCountriesCountForAccessibility$1", f = "CountrySelectionFragment.kt", i = {}, l = {CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$announceCountriesCountForAccessibility$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CountrySelectionFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Resources.NotFoundException {
            int i;
            String string;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            CountrySelectionAdapter countrySelectionAdapter = CountrySelectionFragment.this.countriesAdapter;
            if (countrySelectionAdapter == null) {
                return Unit.INSTANCE;
            }
            int visibleCountriesCount = countrySelectionAdapter.getVisibleCountriesCount();
            Resources resources = CountrySelectionFragment.this.requireContext().getResources();
            if (visibleCountriesCount == 0) {
                i = R.string.onfido_country_select_search_results_none_accessibility;
            } else {
                if (visibleCountriesCount != 1) {
                    string = resources.getString(R.string.onfido_country_select_search_results_multiple_accessibility, Boxing.boxInt(visibleCountriesCount));
                    Intrinsics.checkNotNull(string);
                    CountrySelectionFragment.this.getBinding().countriesList.announceForAccessibility(string);
                    return Unit.INSTANCE;
                }
                i = R.string.onfido_country_select_search_results_one_accessibility;
            }
            string = resources.getString(i);
            Intrinsics.checkNotNull(string);
            CountrySelectionFragment.this.getBinding().countriesList.announceForAccessibility(string);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$menuProvider$1] */
    public CountrySelectionFragment() {
        super(R.layout.onfido_fragment_country_selection);
        this.supportedCountries = LazyKt.lazy(new Function0<ArrayList<CountryCode>>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$supportedCountries$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ArrayList<CountryCode> invoke() {
                Serializable serializable = this.this$0.requireArguments().getSerializable("SUPPORTED_COUNTRIES");
                if (serializable != null) {
                    return (ArrayList) serializable;
                }
                return null;
            }
        });
        this.menuProvider = new MenuProvider() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$menuProvider$1
            @Override // androidx.core.view.MenuProvider
            public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
                Intrinsics.checkNotNullParameter(menu, "menu");
                Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
                menuInflater.inflate(R.menu.onfido_country_selection, menu);
                MenuItem menuItemFindItem = menu.findItem(R.id.action_search);
                Intrinsics.checkNotNullExpressionValue(menuItemFindItem, "findItem(...)");
                View actionView = menuItemFindItem.getActionView();
                final SearchView searchView = actionView instanceof SearchView ? (SearchView) actionView : null;
                Drawable icon = menuItemFindItem.getIcon();
                if (searchView == null || icon == null) {
                    return;
                }
                searchView.setMaxWidth(Integer.MAX_VALUE);
                View viewFindViewById = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
                Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
                AppCompatAutoCompleteTextView appCompatAutoCompleteTextView = (AppCompatAutoCompleteTextView) viewFindViewById;
                appCompatAutoCompleteTextView.setHintTextColor(ContextCompat.getColor(this.this$0.requireContext(), R.color.onfidoTextColorSecondary));
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                FontInfo fontInfoResolveFontFromAttr = ContextUtilsKt.resolveFontFromAttr(contextRequireContext, R.attr.onfidoFontFamilyBody);
                if (fontInfoResolveFontFromAttr != null) {
                    appCompatAutoCompleteTextView.setTypeface(fontInfoResolveFontFromAttr.getTypeface());
                }
                Drawable drawableWrap = DrawableCompat.wrap(icon);
                Intrinsics.checkNotNullExpressionValue(drawableWrap, "wrap(...)");
                menuItemFindItem.setIcon(drawableWrap);
                final CountrySelectionFragment countrySelectionFragment = this.this$0;
                menuItemFindItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$menuProvider$1$onCreateMenu$2
                    @Override // android.view.MenuItem.OnActionExpandListener
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        Intrinsics.checkNotNullParameter(item, "item");
                        searchView.setOnQueryTextListener(null);
                        CountrySelectionAdapter countrySelectionAdapter = countrySelectionFragment.countriesAdapter;
                        if (countrySelectionAdapter != null) {
                            countrySelectionAdapter.setSearchTerm("");
                        }
                        CountrySelectionAdapter countrySelectionAdapter2 = countrySelectionFragment.countriesAdapter;
                        if (countrySelectionAdapter2 != null) {
                            countrySelectionAdapter2.removeItems();
                        }
                        countrySelectionFragment.getPresenter$onfido_capture_sdk_core_release().getCountrySuggestion(countrySelectionFragment.getSupportedCountries());
                        return true;
                    }

                    @Override // android.view.MenuItem.OnActionExpandListener
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        Intrinsics.checkNotNullParameter(item, "item");
                        searchView.setOnQueryTextListener(countrySelectionFragment);
                        countrySelectionFragment.onQueryTextChange("");
                        return true;
                    }
                });
            }

            @Override // androidx.core.view.MenuProvider
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Intrinsics.checkNotNullParameter(menuItem, "menuItem");
                return false;
            }
        };
    }

    private final void announceCountriesCountForAccessibility() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    @JvmStatic
    public static final CountrySelectionFragment createInstance(String str, ArrayList<CountryCode> arrayList) {
        return INSTANCE.createInstance(str, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentCountrySelectionBinding getBinding() {
        OnfidoFragmentCountrySelectionBinding onfidoFragmentCountrySelectionBinding = this._binding;
        if (onfidoFragmentCountrySelectionBinding != null) {
            return onfidoFragmentCountrySelectionBinding;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @JvmStatic
    public static final CountrySelectionResult getResult(Bundle bundle) {
        return INSTANCE.getResult(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CountryCode> getSupportedCountries() {
        return (List) this.supportedCountries.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(CountrySelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().description.setVisibility(0);
        this$0.getBinding().closeButton.setVisibility(0);
        this$0.getBinding().title.setEnabled(false);
        this$0.getBinding().description.sendAccessibilityEvent(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(CountrySelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().description.setVisibility(8);
        this$0.getBinding().closeButton.setVisibility(8);
        this$0.getBinding().countryPopoverSheetHint.setVisibility(8);
        this$0.getBinding().title.setEnabled(true);
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.View
    public void enterLoadingState() {
        getBinding().countriesList.enterLoadingState();
    }

    public final CountrySelectionPresenter getPresenter$onfido_capture_sdk_core_release() {
        CountrySelectionPresenter countrySelectionPresenter = this.presenter;
        if (countrySelectionPresenter != null) {
            return countrySelectionPresenter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenter");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionListener
    public void onCountrySelected(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        FragmentManager parentFragmentManager = getParentFragmentManager();
        String string = requireArguments().getString(RESULT_KEY);
        if (string == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(RESULT_COUNTRY_SELECTION, new CountrySelectionResult(countryCode))));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter$onfido_capture_sdk_core_release().detachView();
        this.countriesAdapter = null;
        this._binding = null;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String newText) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(newText, "newText");
        CountrySelectionAdapter countrySelectionAdapter = this.countriesAdapter;
        if (countrySelectionAdapter != null) {
            countrySelectionAdapter.setSearchTerm(newText);
        }
        CountrySelectionAdapter countrySelectionAdapter2 = this.countriesAdapter;
        if (countrySelectionAdapter2 != null) {
            countrySelectionAdapter2.filterBy(newText);
        }
        OnfidoFragmentCountrySelectionBinding onfidoFragmentCountrySelectionBinding = this._binding;
        if (onfidoFragmentCountrySelectionBinding != null && (recyclerView = onfidoFragmentCountrySelectionBinding.countriesList) != null) {
            recyclerView.scrollToPosition(0);
        }
        announceCountriesCountForAccessibility();
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getPresenter$onfido_capture_sdk_core_release().onStart(getSupportedCountries() != null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentCountrySelectionBinding.bind(view);
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext(...)");
        this.countriesAdapter = new CountrySelectionAdapter(contextRequireContext2, this, getSupportedCountries() != null);
        getBinding().countriesList.setAdapter(this.countriesAdapter);
        getBinding().countriesList.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment.onViewCreated.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(androidx.recyclerview.widget.RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 2) {
                    CountrySelectionFragment.this.getBinding().countryPopoverSheetHint.setVisibility(0);
                }
            }
        });
        getBinding().title.setEnabled(true);
        getBinding().title.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CountrySelectionFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        getBinding().closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CountrySelectionFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
        getPresenter$onfido_capture_sdk_core_release().attachView(this);
        getPresenter$onfido_capture_sdk_core_release().getCountrySuggestion(getSupportedCountries());
        requireActivity().addMenuProvider(this.menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    @Override // com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionPresenter.View
    public void setCountries(List<? extends BaseAdapterItem> countries) {
        Intrinsics.checkNotNullParameter(countries, "countries");
        CountrySelectionAdapter countrySelectionAdapter = this.countriesAdapter;
        if (countrySelectionAdapter != null) {
            countrySelectionAdapter.setCountries(countries);
        }
        getBinding().countriesList.exitLoadingState();
    }

    public final void setPresenter$onfido_capture_sdk_core_release(CountrySelectionPresenter countrySelectionPresenter) {
        Intrinsics.checkNotNullParameter(countrySelectionPresenter, "<set-?>");
        this.presenter = countrySelectionPresenter;
    }
}
