package com.onfido.android.sdk.capture.internal.ui.countryselection;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0003\n\u000b\fB\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability;", "", "()V", "countries", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$Country;", "getCountries", "()Ljava/util/List;", "setCountries", "(Ljava/util/List;)V", "Country", "CountryState", "DocumentType", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCountryAvailability {
    private List<Country> countries = new ArrayList();

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$Country;", "", "()V", "code", "", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "types", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$DocumentType;", "getTypes", "()Ljava/util/List;", "setTypes", "(Ljava/util/List;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Country {
        private String code = "";
        private List<DocumentType> types = new ArrayList();

        public final String getCode() {
            return this.code;
        }

        public final List<DocumentType> getTypes() {
            return this.types;
        }

        public final void setCode(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.code = str;
        }

        public final void setTypes(List<DocumentType> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.types = list;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$CountryState;", "", "()V", "code", "", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "name", "getName", "setName", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CountryState {
        private String name = "";
        private String code = "";

        public final String getCode() {
            return this.code;
        }

        public final String getName() {
            return this.name;
        }

        public final void setCode(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.code = str;
        }

        public final void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000e¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$DocumentType;", "", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "sides", "", "getSides", "()Ljava/util/List;", "setSides", "(Ljava/util/List;)V", "states", "", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/DocumentCountryAvailability$CountryState;", "getStates", "setStates", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentType {
        private String name = "";
        private List<String> sides = new ArrayList();
        private List<CountryState> states = new ArrayList();

        public final String getName() {
            return this.name;
        }

        public final List<String> getSides() {
            return this.sides;
        }

        public final List<CountryState> getStates() {
            return this.states;
        }

        public final void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        public final void setSides(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.sides = list;
        }

        public final void setStates(List<CountryState> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.states = list;
        }
    }

    public final List<Country> getCountries() {
        return this.countries;
    }

    public final void setCountries(List<Country> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.countries = list;
    }
}
