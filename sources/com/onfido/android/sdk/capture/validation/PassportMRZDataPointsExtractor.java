package com.onfido.android.sdk.capture.validation;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ.\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001e\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/PassportMRZDataPointsExtractor;", "", "()V", "CHARACTERS_ORDER", "", "MIN_REQUIRED_CHARS", "", "extract", "", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "mrz", "Lcom/onfido/android/sdk/capture/validation/PassportMRZ;", "getDataPointAndCheckDigit", "Lkotlin/Pair;", "startIndex", "endIndex", "getDateOfBirth", "getExpiryDate", "getPassportNumber", "getStringCheckDigit", "line", "getWeightOfTheIndex", "index", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PassportMRZDataPointsExtractor {
    private static final String CHARACTERS_ORDER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final PassportMRZDataPointsExtractor INSTANCE = new PassportMRZDataPointsExtractor();
    private static final int MIN_REQUIRED_CHARS = 28;

    private PassportMRZDataPointsExtractor() {
    }

    private final Pair<String, String> getDataPointAndCheckDigit(int startIndex, int endIndex, PassportMRZ mrz) {
        String strSubstring = mrz.getLine2().substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strReplace$default = StringsKt.replace$default(strSubstring, "O", "0", false, 4, (Object) null);
        String strReplace$default2 = StringsKt.replace$default(String.valueOf(mrz.getLine2().charAt(endIndex)), "O", "0", false, 4, (Object) null);
        String strValueOf = String.valueOf(getStringCheckDigit(strReplace$default));
        if (Intrinsics.areEqual(strReplace$default2, strValueOf)) {
            return new Pair<>(strReplace$default, strValueOf);
        }
        return null;
    }

    private final Pair<String, String> getDateOfBirth(PassportMRZ mrz) {
        return getDataPointAndCheckDigit(13, 19, mrz);
    }

    private final Pair<String, String> getExpiryDate(PassportMRZ mrz) {
        return getDataPointAndCheckDigit(21, 27, mrz);
    }

    private final Pair<String, String> getPassportNumber(PassportMRZ mrz) {
        return getDataPointAndCheckDigit(0, 9, mrz);
    }

    private final int getStringCheckDigit(String line) {
        int length = line.length();
        int iIndexOf$default = 0;
        for (int i = 0; i < length; i++) {
            iIndexOf$default += StringsKt.indexOf$default((CharSequence) CHARACTERS_ORDER, line.charAt(i), 0, false, 6, (Object) null) * getWeightOfTheIndex(i);
        }
        return iIndexOf$default % 10;
    }

    private final int getWeightOfTheIndex(int index) {
        int i = index % 3;
        if (i != 0) {
            return i != 1 ? 1 : 3;
        }
        return 7;
    }

    public final List<MRZData> extract(PassportMRZ mrz) {
        Intrinsics.checkNotNullParameter(mrz, "mrz");
        if (mrz.getLine2().length() < 28) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Pair<String, String> passportNumber = getPassportNumber(mrz);
        if (passportNumber != null) {
            arrayList.add(new MRZData(MRZDataType.PASSPORT_NUMBER, passportNumber.getFirst(), passportNumber.getSecond()));
        }
        Pair<String, String> dateOfBirth = getDateOfBirth(mrz);
        if (dateOfBirth != null) {
            arrayList.add(new MRZData(MRZDataType.DATE_OF_BIRTH, dateOfBirth.getFirst(), dateOfBirth.getSecond()));
        }
        Pair<String, String> expiryDate = getExpiryDate(mrz);
        if (expiryDate != null) {
            arrayList.add(new MRZData(MRZDataType.EXPIRY_DATE, expiryDate.getFirst(), expiryDate.getSecond()));
        }
        return arrayList;
    }
}
