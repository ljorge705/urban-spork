package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002J\f\u0010\u0010\u001a\u00020\u0006*\u00020\u0006H\u0002J\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u0006*\u00020\u0006H\u0002¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZParser;", "", "()V", "calculateMrzChecksum", "", "data", "", "getDocumentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "text", "parse", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "lines", "", "readCard", "readPassport", "clear", "validate", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZParser {
    private static final int MRZ_CHECKSUM_SHIFT = 10;
    private static final int MRZ_CHECKSUM_WEIGHTS_COUNT = 3;
    private static final int PASSPORT_SURNAME_STARTING_INDEX = 5;
    private static final Companion Companion = new Companion(null);
    private static final Pair<Integer, Integer> PASSPORT_COUNTRY_CODE_INDICES = TuplesKt.to(2, 5);
    private static final Pair<Integer, Integer> PASSPORT_DOCUMENT_NUMBER_INDICES = TuplesKt.to(0, 10);
    private static final Pair<Integer, Integer> PASSPORT_NATIONALITY_INDICES = TuplesKt.to(10, 13);
    private static final Pair<Integer, Integer> PASSPORT_DATE_OF_BIRTH_INDICES = TuplesKt.to(13, 20);
    private static final Pair<Integer, Integer> PASSPORT_SEX_INDICES = TuplesKt.to(20, 21);
    private static final Pair<Integer, Integer> PASSPORT_DATE_OF_EXPIRY_INDICES = TuplesKt.to(21, 28);
    private static final Pair<Integer, Integer> CARD_COUNTRY_CODE_INDICES = TuplesKt.to(2, 5);
    private static final Pair<Integer, Integer> CARD_DOCUMENT_NUMBER_INDICES = TuplesKt.to(5, 15);
    private static final Pair<Integer, Integer> CARD_DATE_OF_BIRTH_INDICES = TuplesKt.to(0, 7);
    private static final Pair<Integer, Integer> CARD_SEX_INDICES = TuplesKt.to(7, 8);
    private static final Pair<Integer, Integer> CARD_DATE_OF_EXPIRY_INDICES = TuplesKt.to(8, 15);
    private static final Pair<Integer, Integer> CARD_NATIONALITY_INDICES = TuplesKt.to(15, 18);
    private static final List<Integer> MRZ_CHECKSUM_WEIGHTS = CollectionsKt.listOf((Object[]) new Integer[]{7, 3, 1});

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\t\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZParser$Companion;", "", "()V", "CARD_COUNTRY_CODE_INDICES", "Lkotlin/Pair;", "", "CARD_DATE_OF_BIRTH_INDICES", "CARD_DATE_OF_EXPIRY_INDICES", "CARD_DOCUMENT_NUMBER_INDICES", "CARD_NATIONALITY_INDICES", "CARD_SEX_INDICES", "MRZ_CHECKSUM_SHIFT", "MRZ_CHECKSUM_WEIGHTS", "", "MRZ_CHECKSUM_WEIGHTS_COUNT", "PASSPORT_COUNTRY_CODE_INDICES", "PASSPORT_DATE_OF_BIRTH_INDICES", "PASSPORT_DATE_OF_EXPIRY_INDICES", "PASSPORT_DOCUMENT_NUMBER_INDICES", "PASSPORT_NATIONALITY_INDICES", "PASSPORT_SEX_INDICES", "PASSPORT_SURNAME_STARTING_INDEX", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.VISA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentType.RESIDENCE_PERMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final int calculateMrzChecksum(String data) {
        int i;
        ArrayList arrayList = new ArrayList(data.length());
        int i2 = 0;
        int i3 = 0;
        while (i2 < data.length()) {
            char cCharAt = data.charAt(i2);
            int i4 = i3 + 1;
            if ('0' <= cCharAt && cCharAt < ':') {
                i = cCharAt - '0';
            } else if ('A' <= cCharAt && cCharAt < '[') {
                i = cCharAt - '7';
            } else {
                if (cCharAt != '<') {
                    throw new IllegalStateException(("Invalid MRZ character: " + cCharAt).toString());
                }
                i = 0;
            }
            arrayList.add(Integer.valueOf(i * MRZ_CHECKSUM_WEIGHTS.get(i3 % 3).intValue()));
            i2++;
            i3 = i4;
        }
        return CollectionsKt.sumOfInt(arrayList) % 10;
    }

    private final String clear(String str) {
        String upperCase = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, StringUtils.SPACE, "", false, 4, (Object) null), "«", "<", false, 4, (Object) null), "e", "<", false, 4, (Object) null), "€", "<", false, 4, (Object) null).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    private final DocumentType getDocumentType(String text) {
        char cCharAt = text.charAt(0);
        return cCharAt == 'P' ? DocumentType.PASSPORT : (cCharAt == 'I' || cCharAt == 'A') ? text.charAt(1) == 'R' ? DocumentType.RESIDENCE_PERMIT : DocumentType.NATIONAL_IDENTITY_CARD : cCharAt == 'V' ? DocumentType.VISA : DocumentType.UNKNOWN;
    }

    private final MRZDocument readCard(List<String> lines) {
        String strClear;
        String str;
        String strClear2;
        String str2;
        String strClear3;
        int i;
        String str3 = (String) CollectionsKt.firstOrNull((List) lines);
        String strSubstring = null;
        if (str3 == null || (strClear = clear(str3)) == null || (str = (String) CollectionsKt.getOrNull(lines, 1)) == null || (strClear2 = clear(str)) == null || (str2 = (String) CollectionsKt.getOrNull(lines, 2)) == null || (strClear3 = clear(str2)) == null) {
            return null;
        }
        DocumentType documentType = getDocumentType(strClear);
        String strSubstring2 = StringExtensionsKt.substring(strClear, CARD_COUNTRY_CODE_INDICES);
        String strSubstring3 = StringExtensionsKt.substring(strClear, CARD_DOCUMENT_NUMBER_INDICES);
        String strSubstring4 = StringExtensionsKt.substring(strClear2, CARD_DATE_OF_BIRTH_INDICES);
        String strSubstring5 = StringExtensionsKt.substring(strClear2, CARD_SEX_INDICES);
        String strSubstring6 = StringExtensionsKt.substring(strClear2, CARD_DATE_OF_EXPIRY_INDICES);
        String strSubstring7 = StringExtensionsKt.substring(strClear2, CARD_NATIONALITY_INDICES);
        int length = strClear3.length();
        int i2 = 0;
        while (true) {
            i = -1;
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (!Character.isLetter(strClear3.charAt(i2))) {
                break;
            }
            i2++;
        }
        String strSubstring8 = strClear3.substring(0, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring8, "substring(...)");
        String strSubstring9 = strClear3.substring(i2 + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring9, "substring(...)");
        int length2 = strSubstring9.length();
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                i3 = -1;
                break;
            }
            if (Character.isLetter(strSubstring9.charAt(i3))) {
                break;
            }
            i3++;
        }
        String strSubstring10 = strSubstring9.substring(i3);
        Intrinsics.checkNotNullExpressionValue(strSubstring10, "substring(...)");
        int length3 = strSubstring10.length();
        int i4 = 0;
        while (true) {
            if (i4 >= length3) {
                break;
            }
            if (!Character.isLetter(strSubstring10.charAt(i4))) {
                i = i4;
                break;
            }
            i4++;
        }
        if (i > 0) {
            strSubstring = strSubstring10.substring(0, i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        }
        return new MRZDocument(documentType, strSubstring2, strSubstring, strSubstring8, validate(strSubstring3), strSubstring7, validate(strSubstring4), null, validate(strSubstring6), strSubstring5, 128, null);
    }

    private final MRZDocument readPassport(List<String> lines) {
        String strClear;
        String str;
        String strClear2;
        int i;
        String str2 = (String) CollectionsKt.firstOrNull((List) lines);
        String strSubstring = null;
        if (str2 == null || (strClear = clear(str2)) == null || (str = (String) CollectionsKt.getOrNull(lines, 1)) == null || (strClear2 = clear(str)) == null) {
            return null;
        }
        String strSubstring2 = StringExtensionsKt.substring(strClear, PASSPORT_COUNTRY_CODE_INDICES);
        String strSubstring3 = strClear.substring(5);
        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
        int length = strSubstring3.length();
        int i2 = 0;
        while (true) {
            i = -1;
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (!Character.isLetter(strSubstring3.charAt(i2))) {
                break;
            }
            i2++;
        }
        String strSubstring4 = strSubstring3.substring(0, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
        String strSubstring5 = strSubstring3.substring(i2 + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
        int length2 = strSubstring5.length();
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                i3 = -1;
                break;
            }
            if (Character.isLetter(strSubstring5.charAt(i3))) {
                break;
            }
            i3++;
        }
        String strSubstring6 = strSubstring5.substring(i3);
        Intrinsics.checkNotNullExpressionValue(strSubstring6, "substring(...)");
        int length3 = strSubstring6.length();
        int i4 = 0;
        while (true) {
            if (i4 >= length3) {
                break;
            }
            if (!Character.isLetter(strSubstring6.charAt(i4))) {
                i = i4;
                break;
            }
            i4++;
        }
        String strSubstring7 = StringExtensionsKt.substring(strClear2, PASSPORT_DOCUMENT_NUMBER_INDICES);
        String strSubstring8 = StringExtensionsKt.substring(strClear2, PASSPORT_NATIONALITY_INDICES);
        String strSubstring9 = StringExtensionsKt.substring(strClear2, PASSPORT_DATE_OF_BIRTH_INDICES);
        String strSubstring10 = StringExtensionsKt.substring(strClear2, PASSPORT_SEX_INDICES);
        String strSubstring11 = StringExtensionsKt.substring(strClear2, PASSPORT_DATE_OF_EXPIRY_INDICES);
        if (i > 0) {
            strSubstring = strSubstring6.substring(0, i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        }
        return new MRZDocument(DocumentType.PASSPORT, strSubstring2, strSubstring, strSubstring4, validate(strSubstring7), strSubstring8, validate(strSubstring9), null, validate(strSubstring11), strSubstring10, 128, null);
    }

    private final String validate(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int iDigitToInt = CharsKt.digitToInt(StringsKt.last(str));
        String strDropLast = StringsKt.dropLast(str, 1);
        if (iDigitToInt == calculateMrzChecksum(strDropLast)) {
            return strDropLast;
        }
        return null;
    }

    public final MRZDocument parse(List<String> lines) {
        String strClear;
        MRZDocument passport;
        Intrinsics.checkNotNullParameter(lines, "lines");
        try {
            String str = (String) CollectionsKt.firstOrNull((List) lines);
            if (str != null && (strClear = clear(str)) != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[getDocumentType(strClear).ordinal()];
                if (i == 1) {
                    passport = readPassport(lines);
                } else {
                    if (i != 2 && i != 3 && i != 4) {
                        Timber.INSTANCE.i("No document type found for MRZ parsing", new Object[0]);
                        return null;
                    }
                    passport = readCard(lines);
                }
                return passport;
            }
            return null;
        } catch (RuntimeException e) {
            Timber.INSTANCE.i(e);
            return null;
        }
    }
}
