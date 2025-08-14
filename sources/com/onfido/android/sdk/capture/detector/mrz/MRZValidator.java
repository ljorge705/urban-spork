package com.onfido.android.sdk.capture.detector.mrz;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.validation.DocumentCodeValidator;
import com.onfido.android.sdk.capture.validation.DocumentCodeValidatorResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrz/MRZValidator;", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "(Lcom/onfido/android/sdk/capture/DocumentType;)V", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "extractMrzLines", "", "", "text", "validate", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidatorResult;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZValidator implements DocumentCodeValidator {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int ID_LINES_COUNT = 3;

    @Deprecated
    public static final int ID_MIN_CHARS_PER_LINE = 26;

    @Deprecated
    public static final int PASSPORT_LINES_COUNT = 2;

    @Deprecated
    public static final int PASSPORT_MIN_CHARS_PER_LINE = 30;
    private final DocumentType documentType;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrz/MRZValidator$Companion;", "", "()V", "ID_LINES_COUNT", "", "ID_MIN_CHARS_PER_LINE", "PASSPORT_LINES_COUNT", "PASSPORT_MIN_CHARS_PER_LINE", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
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
                iArr[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MRZValidator(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        this.documentType = documentType;
    }

    private final List<String> extractMrzLines(String text) {
        List listSplit$default = StringsKt.split$default((CharSequence) StringsKt.replace$default(text, "«", "<<", false, 4, (Object) null), new String[]{"\n"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (StringsKt.contains$default((CharSequence) obj, Typography.less, false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    @Override // com.onfido.android.sdk.capture.validation.DocumentCodeValidator
    public DocumentCodeValidatorResult validate(String text) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(text, "text");
        List<String> listExtractMrzLines = extractMrzLines(text);
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.documentType.ordinal()];
        int i4 = 2;
        if (i3 == 1) {
            i = 30;
        } else {
            if (i3 != 2) {
                return new DocumentCodeValidatorResult(true, "");
            }
            i = 26;
            i4 = 3;
        }
        Pair pair = TuplesKt.to(i, Integer.valueOf(i4));
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        if ((listExtractMrzLines instanceof Collection) && listExtractMrzLines.isEmpty()) {
            i2 = 0;
        } else {
            Iterator<T> it = listExtractMrzLines.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (((String) it.next()).length() >= iIntValue && (i2 = i2 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return new DocumentCodeValidatorResult(i2 >= iIntValue2, "");
    }
}
