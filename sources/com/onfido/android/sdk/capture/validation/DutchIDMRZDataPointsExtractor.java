package com.onfido.android.sdk.capture.validation;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ.\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0004H\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010%\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0004H\u0002J\u0010\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/DutchIDMRZDataPointsExtractor;", "", "()V", "CHARACTERS_ORDER", "", "CHAR_INDEX_BASE", "", "CHAR_INDEX_WEIGHT_1", "CHAR_INDEX_WEIGHT_2", "CHAR_INDEX_WEIGHT_3", "CHECKSUM_BASE", "DOB_END_INDEX", "DOB_START_INDEX", "DOCUMENT_NUMBER_END_INDEX", "DOCUMENT_NUMBER_START_INDEX", "EXPIRY_DATE_END_INDEX", "EXPIRY_DATE_START_INDEX", "LINE1_PREFIX_END_INDEX", "LINE1_PREFIX_START_INDEX", "LINE2_NLD_END_INDEX", "LINE2_NLD_START_INDEX", "PERSONAL_NUMBER_END_INDEX", "PERSONAL_NUMBER_START_INDEX", "REQUIRED_CHARS", "extract", "", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "mrz", "Lcom/onfido/android/sdk/capture/validation/DutchIDMRZ;", "getDataPointAndCheckDigit", "Lkotlin/Pair;", "startIndex", "endIndex", "line", "getDateOfBirth", "getDocumentNumber", "getExpiryDate", "getPersonalNumber", "getStringCheckDigit", "getWeightOfTheIndex", "index", "isValidMRZ", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DutchIDMRZDataPointsExtractor {
    private static final String CHARACTERS_ORDER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CHAR_INDEX_BASE = 3;
    private static final int CHAR_INDEX_WEIGHT_1 = 7;
    private static final int CHAR_INDEX_WEIGHT_2 = 3;
    private static final int CHAR_INDEX_WEIGHT_3 = 1;
    private static final int CHECKSUM_BASE = 10;
    private static final int DOB_END_INDEX = 6;
    private static final int DOB_START_INDEX = 0;
    private static final int DOCUMENT_NUMBER_END_INDEX = 14;
    private static final int DOCUMENT_NUMBER_START_INDEX = 5;
    private static final int EXPIRY_DATE_END_INDEX = 14;
    private static final int EXPIRY_DATE_START_INDEX = 8;
    public static final DutchIDMRZDataPointsExtractor INSTANCE = new DutchIDMRZDataPointsExtractor();
    private static final int LINE1_PREFIX_END_INDEX = 5;
    private static final int LINE1_PREFIX_START_INDEX = 0;
    private static final int LINE2_NLD_END_INDEX = 18;
    private static final int LINE2_NLD_START_INDEX = 15;
    private static final int PERSONAL_NUMBER_END_INDEX = 29;
    private static final int PERSONAL_NUMBER_START_INDEX = 15;
    private static final int REQUIRED_CHARS = 30;

    private DutchIDMRZDataPointsExtractor() {
    }

    private final Pair<String, String> getDataPointAndCheckDigit(int startIndex, int endIndex, String line) {
        String strSubstring = line.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(strSubstring, "O", "0", false, 4, (Object) null), "<", "", false, 4, (Object) null);
        String strReplace$default2 = StringsKt.replace$default(String.valueOf(line.charAt(endIndex)), "O", "0", false, 4, (Object) null);
        String strValueOf = String.valueOf(getStringCheckDigit(strReplace$default));
        if (Intrinsics.areEqual(strReplace$default2, strValueOf)) {
            return new Pair<>(strReplace$default, strValueOf);
        }
        return null;
    }

    private final MRZData getDateOfBirth(DutchIDMRZ mrz) {
        Pair<String, String> dataPointAndCheckDigit = getDataPointAndCheckDigit(0, 6, mrz.getLine2());
        if (dataPointAndCheckDigit != null) {
            return new MRZData(MRZDataType.DATE_OF_BIRTH, dataPointAndCheckDigit.getFirst(), dataPointAndCheckDigit.getSecond());
        }
        return null;
    }

    private final MRZData getDocumentNumber(DutchIDMRZ mrz) {
        Pair<String, String> dataPointAndCheckDigit = getDataPointAndCheckDigit(5, 14, mrz.getLine1());
        if (dataPointAndCheckDigit != null) {
            return new MRZData(MRZDataType.DOCUMENT_NUMBER, dataPointAndCheckDigit.getFirst(), dataPointAndCheckDigit.getSecond());
        }
        return null;
    }

    private final MRZData getExpiryDate(DutchIDMRZ mrz) {
        Pair<String, String> dataPointAndCheckDigit = getDataPointAndCheckDigit(8, 14, mrz.getLine2());
        if (dataPointAndCheckDigit != null) {
            return new MRZData(MRZDataType.EXPIRY_DATE, dataPointAndCheckDigit.getFirst(), dataPointAndCheckDigit.getSecond());
        }
        return null;
    }

    private final MRZData getPersonalNumber(DutchIDMRZ mrz) {
        Pair<String, String> dataPointAndCheckDigit = getDataPointAndCheckDigit(15, 29, mrz.getLine1());
        if (dataPointAndCheckDigit != null) {
            return new MRZData(MRZDataType.PERSONAL_NUMBER, dataPointAndCheckDigit.getFirst(), dataPointAndCheckDigit.getSecond());
        }
        return null;
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

    private final boolean isValidMRZ(DutchIDMRZ mrz) {
        if (mrz.getLine1().length() != 30 || mrz.getLine2().length() != 30) {
            return false;
        }
        String strSubstring = mrz.getLine1().substring(0, 5);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        if (!Intrinsics.areEqual(strSubstring, "I<NLD")) {
            return false;
        }
        String strSubstring2 = mrz.getLine2().substring(15, 18);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return Intrinsics.areEqual(strSubstring2, "NLD");
    }

    public final List<MRZData> extract(DutchIDMRZ mrz) {
        Intrinsics.checkNotNullParameter(mrz, "mrz");
        if (!isValidMRZ(mrz)) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        MRZData documentNumber = getDocumentNumber(mrz);
        if (documentNumber != null) {
            arrayList.add(documentNumber);
        }
        MRZData personalNumber = getPersonalNumber(mrz);
        if (personalNumber != null) {
            arrayList.add(personalNumber);
        }
        MRZData dateOfBirth = getDateOfBirth(mrz);
        if (dateOfBirth != null) {
            arrayList.add(dateOfBirth);
        }
        MRZData expiryDate = getExpiryDate(mrz);
        if (expiryDate != null) {
            arrayList.add(expiryDate);
        }
        return arrayList;
    }
}
