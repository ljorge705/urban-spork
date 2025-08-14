package com.onfido.android.sdk.capture.internal.ui.countryselection;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoCountrySearchEmptyStateBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoElemCountrySelectionBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoElemCountrySelectionHeaderBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoSeparatorBinding;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.CountryCodeExtensionsKt;
import com.onfido.android.sdk.capture.utils.CustomTypefaceSpan;
import com.onfido.android.sdk.capture.utils.FontInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u000b\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00045678B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u0006\u0010'\u001a\u00020$J\u0016\u0010(\u001a\u00020\u001d2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00120*H\u0002J\u0018\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u00022\u0006\u0010&\u001a\u00020$H\u0016J\u0018\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020$H\u0016J\u0006\u00101\u001a\u00020\u001dJ\u0014\u00102\u001a\u00020\u001d2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00120*J\u000e\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u0015R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u00069"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "countrySelectionListener", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionListener;", "isForProofOfAddress", "", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionListener;Z)V", "buttonAccessibilityDelegate", "com/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$buttonAccessibilityDelegate$1", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$buttonAccessibilityDelegate$1;", "inflater", "Landroid/view/LayoutInflater;", "kotlin.jvm.PlatformType", FirebaseAnalytics.Param.ITEMS, "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/BaseAdapterItem;", "originalItems", "searchTerm", "", "searchTermTypeface", "Landroid/graphics/Typeface;", "getSearchTermTypeface", "()Landroid/graphics/Typeface;", "searchTermTypeface$delegate", "Lkotlin/Lazy;", "displayCountryOnView", "", "countryAvailability", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountryAvailability;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionBinding;", "filterBy", "getItemCount", "", "getItemViewType", ViewProps.POSITION, "getVisibleCountriesCount", "notifyAndTransformTo", "countries", "", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeItems", "setCountries", "setSearchTerm", FirebaseAnalytics.Param.TERM, "CountrySelectionHeaderViewHolder", "CountrySelectionSeparatorViewHolder", "CountrySelectionViewHolder", "NoCountriesAvailableViewHolder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CountrySelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final CountrySelectionAdapter$buttonAccessibilityDelegate$1 buttonAccessibilityDelegate;
    private final Context context;
    private final CountrySelectionListener countrySelectionListener;
    private final LayoutInflater inflater;
    private final boolean isForProofOfAddress;
    private List<BaseAdapterItem> items;
    private List<BaseAdapterItem> originalItems;
    private String searchTerm;

    /* renamed from: searchTermTypeface$delegate, reason: from kotlin metadata */
    private final Lazy searchTermTypeface;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$CountrySelectionHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionHeaderBinding;", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionHeaderBinding;)V", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionHeaderBinding;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CountrySelectionHeaderViewHolder extends RecyclerView.ViewHolder {
        private final OnfidoElemCountrySelectionHeaderBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountrySelectionHeaderViewHolder(OnfidoElemCountrySelectionHeaderBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final OnfidoElemCountrySelectionHeaderBinding getBinding() {
            return this.binding;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$CountrySelectionSeparatorViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoSeparatorBinding;", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoSeparatorBinding;)V", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoSeparatorBinding;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CountrySelectionSeparatorViewHolder extends RecyclerView.ViewHolder {
        private final OnfidoSeparatorBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountrySelectionSeparatorViewHolder(OnfidoSeparatorBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final OnfidoSeparatorBinding getBinding() {
            return this.binding;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$CountrySelectionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionBinding;", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionBinding;)V", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoElemCountrySelectionBinding;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CountrySelectionViewHolder extends RecyclerView.ViewHolder {
        private final OnfidoElemCountrySelectionBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountrySelectionViewHolder(OnfidoElemCountrySelectionBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final OnfidoElemCountrySelectionBinding getBinding() {
            return this.binding;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionAdapter$NoCountriesAvailableViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoCountrySearchEmptyStateBinding;", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoCountrySearchEmptyStateBinding;)V", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoCountrySearchEmptyStateBinding;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoCountriesAvailableViewHolder extends RecyclerView.ViewHolder {
        private final OnfidoCountrySearchEmptyStateBinding binding;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoCountriesAvailableViewHolder(OnfidoCountrySearchEmptyStateBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final OnfidoCountrySearchEmptyStateBinding getBinding() {
            return this.binding;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CountrySelectionSeparatorType.values().length];
            try {
                iArr[CountrySelectionSeparatorType.SUGGESTED_COUNTRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CountrySelectionSeparatorType.ALL_COUNTRIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CountrySelectionSeparatorType.SEPARATOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$buttonAccessibilityDelegate$1] */
    public CountrySelectionAdapter(Context context, CountrySelectionListener countrySelectionListener, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(countrySelectionListener, "countrySelectionListener");
        this.context = context;
        this.countrySelectionListener = countrySelectionListener;
        this.isForProofOfAddress = z;
        this.inflater = LayoutInflater.from(context);
        this.searchTerm = "";
        this.searchTermTypeface = LazyKt.lazy(new Function0<Typeface>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$searchTermTypeface$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Typeface invoke() {
                FontInfo fontInfoResolveFontFromAttr = ContextUtilsKt.resolveFontFromAttr(this.this$0.context, R.attr.onfidoFontFamilyBody);
                if (fontInfoResolveFontFromAttr != null) {
                    return TypefaceCompat.create(this.this$0.context, fontInfoResolveFontFromAttr.getTypeface(), 1);
                }
                return null;
            }
        });
        this.buttonAccessibilityDelegate = new AccessibilityDelegateCompat() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$buttonAccessibilityDelegate$1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        };
        this.items = new ArrayList();
        this.originalItems = new ArrayList();
    }

    private final void displayCountryOnView(CountryAvailability countryAvailability, OnfidoElemCountrySelectionBinding binding) {
        Object customTypefaceSpan;
        final CountryCode countryCode = countryAvailability.getCountryCode();
        if (binding != null) {
            TextView textView = binding.countryName;
            int iIndexOf = StringsKt.indexOf((CharSequence) CountryCodeExtensionsKt.getDisplayName(countryCode), this.searchTerm, 0, true);
            SpannableString spannableString = new SpannableString(CountryCodeExtensionsKt.getDisplayName(countryCode));
            if (getSearchTermTypeface() == null) {
                customTypefaceSpan = new TextAppearanceSpan(textView.getContext(), R.style.OnfidoTextStyle_Body_Regular_Bold);
            } else {
                Typeface searchTermTypeface = getSearchTermTypeface();
                Intrinsics.checkNotNull(searchTermTypeface);
                customTypefaceSpan = new CustomTypefaceSpan(null, searchTermTypeface);
            }
            spannableString.setSpan(customTypefaceSpan, iIndexOf, this.searchTerm.length() + iIndexOf, 17);
            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
            textView.setContentDescription(countryCode.getLocaleName());
            ViewCompat.setAccessibilityDelegate(textView, this.buttonAccessibilityDelegate);
            binding.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CountrySelectionAdapter.displayCountryOnView$lambda$2$lambda$1(this.f$0, countryCode, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void displayCountryOnView$lambda$2$lambda$1(CountrySelectionAdapter this$0, CountryCode country, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(country, "$country");
        this$0.countrySelectionListener.onCountrySelected(country);
    }

    private final Typeface getSearchTermTypeface() {
        return (Typeface) this.searchTermTypeface.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void notifyAndTransformTo(java.util.List<? extends com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r7) {
        /*
            r6 = this;
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r0 = r6.items
            int r0 = kotlin.collections.CollectionsKt.getLastIndex(r0)
        L6:
            r1 = -1
            if (r1 >= r0) goto L31
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r1 = r6.items
            java.lang.Object r1 = r1.get(r0)
            boolean r1 = r1 instanceof com.onfido.android.sdk.capture.internal.ui.countryselection.CountryAvailability
            if (r1 == 0) goto L26
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r1 = r6.items
            java.lang.Object r1 = r1.get(r0)
            java.lang.String r2 = "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.ui.countryselection.CountryAvailability"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            com.onfido.android.sdk.capture.internal.ui.countryselection.CountryAvailability r1 = (com.onfido.android.sdk.capture.internal.ui.countryselection.CountryAvailability) r1
            boolean r1 = r7.contains(r1)
            if (r1 != 0) goto L2e
        L26:
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r1 = r6.items
            r1.remove(r0)
            r6.notifyItemRemoved(r0)
        L2e:
            int r0 = r0 + (-1)
            goto L6
        L31:
            java.util.Iterator r0 = r7.iterator()
            r1 = 0
            r2 = r1
        L37:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L5c
            java.lang.Object r3 = r0.next()
            int r4 = r2 + 1
            if (r2 >= 0) goto L48
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L48:
            com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem r3 = (com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem) r3
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r5 = r6.items
            boolean r5 = r5.contains(r3)
            if (r5 != 0) goto L5a
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r5 = r6.items
            r5.add(r2, r3)
            r6.notifyItemInserted(r2)
        L5a:
            r2 = r4
            goto L37
        L5c:
            java.util.Iterator r7 = r7.iterator()
            r0 = r1
        L61:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L94
            java.lang.Object r2 = r7.next()
            int r3 = r0 + 1
            if (r0 >= 0) goto L72
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L72:
            com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem r2 = (com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem) r2
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r4 = r6.items
            int r4 = r4.indexOf(r2)
            if (r4 < 0) goto L8f
            if (r4 == r0) goto L8f
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r5 = r6.items
            r5.remove(r4)
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r5 = r6.items
            r5.add(r0, r2)
            r6.notifyItemMoved(r4, r0)
            r6.notifyItemChanged(r0)
            goto L92
        L8f:
            r6.notifyItemChanged(r4)
        L92:
            r0 = r3
            goto L61
        L94:
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r7 = r6.items
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto La9
            java.util.List<com.onfido.android.sdk.capture.internal.ui.countryselection.BaseAdapterItem> r7 = r6.items
            com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionEmptyState r0 = new com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionEmptyState
            r0.<init>()
            r7.add(r0)
            r6.notifyItemInserted(r1)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter.notifyAndTransformTo(java.util.List):void");
    }

    public final void filterBy(final String searchTerm) {
        Intrinsics.checkNotNullParameter(searchTerm, "searchTerm");
        List<BaseAdapterItem> list = this.originalItems;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            BaseAdapterItem baseAdapterItem = (BaseAdapterItem) obj;
            if ((baseAdapterItem instanceof CountryAvailability) && StringsKt.contains((CharSequence) CountryCodeExtensionsKt.getDisplayName(((CountryAvailability) baseAdapterItem).getCountryCode()), (CharSequence) searchTerm, true)) {
                arrayList.add(obj);
            }
        }
        notifyAndTransformTo(CollectionsKt.sortedWith(arrayList, ComparisonsKt.compareBy(new Function1<BaseAdapterItem, Comparable<?>>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$filterBy$newItems$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(BaseAdapterItem it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(StringsKt.indexOf((CharSequence) CountryCodeExtensionsKt.getDisplayName(((CountryAvailability) it).getCountryCode()), searchTerm, 0, true));
            }
        }, new Function1<BaseAdapterItem, Comparable<?>>() { // from class: com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionAdapter$filterBy$newItems$3
            @Override // kotlin.jvm.functions.Function1
            public final Comparable<?> invoke(BaseAdapterItem it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return CountryCodeExtensionsKt.getDisplayName(((CountryAvailability) it).getCountryCode());
            }
        })));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        if (!(this.items.get(position) instanceof CountrySelectionSeparator)) {
            return this.items.get(position) instanceof CountrySelectionEmptyState ? 3 : 0;
        }
        BaseAdapterItem baseAdapterItem = this.items.get(position);
        Intrinsics.checkNotNull(baseAdapterItem, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionSeparator");
        int i = WhenMappings.$EnumSwitchMapping$0[((CountrySelectionSeparator) baseAdapterItem).getType().ordinal()];
        if (i == 1 || i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final int getVisibleCountriesCount() {
        List<BaseAdapterItem> list = this.items;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((BaseAdapterItem) obj) instanceof CountryAvailability) {
                arrayList.add(obj);
            }
        }
        return arrayList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OnfidoCountrySearchEmptyStateBinding binding;
        TextView textView;
        int i;
        OnfidoElemCountrySelectionHeaderBinding binding2;
        OnfidoElemCountrySelectionHeaderBinding binding3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        BaseAdapterItem baseAdapterItem = this.items.get(position);
        if (baseAdapterItem instanceof CountryAvailability) {
            CountryAvailability countryAvailability = (CountryAvailability) baseAdapterItem;
            CountrySelectionViewHolder countrySelectionViewHolder = holder instanceof CountrySelectionViewHolder ? (CountrySelectionViewHolder) holder : null;
            displayCountryOnView(countryAvailability, countrySelectionViewHolder != null ? countrySelectionViewHolder.getBinding() : null);
            return;
        }
        if (baseAdapterItem instanceof CountrySelectionSeparator) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[((CountrySelectionSeparator) baseAdapterItem).getType().ordinal()];
            if (i2 == 1) {
                CountrySelectionHeaderViewHolder countrySelectionHeaderViewHolder = holder instanceof CountrySelectionHeaderViewHolder ? (CountrySelectionHeaderViewHolder) holder : null;
                if (countrySelectionHeaderViewHolder == null || (binding2 = countrySelectionHeaderViewHolder.getBinding()) == null || (textView = binding2.headerText) == null) {
                    return;
                } else {
                    i = R.string.onfido_country_select_list_header_suggested;
                }
            } else {
                if (i2 != 2) {
                    return;
                }
                CountrySelectionHeaderViewHolder countrySelectionHeaderViewHolder2 = holder instanceof CountrySelectionHeaderViewHolder ? (CountrySelectionHeaderViewHolder) holder : null;
                if (countrySelectionHeaderViewHolder2 == null || (binding3 = countrySelectionHeaderViewHolder2.getBinding()) == null || (textView = binding3.headerText) == null) {
                    return;
                } else {
                    i = R.string.onfido_country_select_list_header_all;
                }
            }
        } else {
            if (!(baseAdapterItem instanceof CountrySelectionEmptyState) || !this.isForProofOfAddress) {
                return;
            }
            NoCountriesAvailableViewHolder noCountriesAvailableViewHolder = holder instanceof NoCountriesAvailableViewHolder ? (NoCountriesAvailableViewHolder) holder : null;
            if (noCountriesAvailableViewHolder == null || (binding = noCountriesAvailableViewHolder.getBinding()) == null || (textView = binding.title) == null) {
                return;
            } else {
                i = R.string.onfido_country_select_error_no_country_body;
            }
        }
        textView.setText(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 1) {
            OnfidoElemCountrySelectionHeaderBinding onfidoElemCountrySelectionHeaderBindingInflate = OnfidoElemCountrySelectionHeaderBinding.inflate(this.inflater, parent, false);
            Intrinsics.checkNotNullExpressionValue(onfidoElemCountrySelectionHeaderBindingInflate, "inflate(...)");
            return new CountrySelectionHeaderViewHolder(onfidoElemCountrySelectionHeaderBindingInflate);
        }
        if (viewType == 2) {
            OnfidoSeparatorBinding onfidoSeparatorBindingInflate = OnfidoSeparatorBinding.inflate(this.inflater, parent, false);
            Intrinsics.checkNotNullExpressionValue(onfidoSeparatorBindingInflate, "inflate(...)");
            return new CountrySelectionSeparatorViewHolder(onfidoSeparatorBindingInflate);
        }
        if (viewType != 3) {
            OnfidoElemCountrySelectionBinding onfidoElemCountrySelectionBindingInflate = OnfidoElemCountrySelectionBinding.inflate(this.inflater, parent, false);
            Intrinsics.checkNotNullExpressionValue(onfidoElemCountrySelectionBindingInflate, "inflate(...)");
            return new CountrySelectionViewHolder(onfidoElemCountrySelectionBindingInflate);
        }
        OnfidoCountrySearchEmptyStateBinding onfidoCountrySearchEmptyStateBindingInflate = OnfidoCountrySearchEmptyStateBinding.inflate(this.inflater, parent, false);
        Intrinsics.checkNotNullExpressionValue(onfidoCountrySearchEmptyStateBindingInflate, "inflate(...)");
        return new NoCountriesAvailableViewHolder(onfidoCountrySearchEmptyStateBindingInflate);
    }

    public final void removeItems() {
        this.items.clear();
        this.originalItems.clear();
        notifyDataSetChanged();
    }

    public final void setCountries(List<? extends BaseAdapterItem> countries) {
        Intrinsics.checkNotNullParameter(countries, "countries");
        this.items = CollectionsKt.toMutableList((Collection) countries);
        this.originalItems = CollectionsKt.toMutableList((Collection) countries);
        notifyItemRangeInserted(0, countries.size());
    }

    public final void setSearchTerm(String term) {
        Intrinsics.checkNotNullParameter(term, "term");
        this.searchTerm = term;
    }

    public /* synthetic */ CountrySelectionAdapter(Context context, CountrySelectionListener countrySelectionListener, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, countrySelectionListener, (i & 4) != 0 ? false : z);
    }
}
