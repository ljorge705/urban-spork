package com.onfido.android.sdk.capture.detector.mrz;

import com.onfido.android.sdk.capture.validation.DocumentCodeValidator;
import com.onfido.android.sdk.capture.validation.DocumentCodeValidatorResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrz/DutchIDMRZValidator;", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "()V", "validate", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidatorResult;", "text", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DutchIDMRZValidator implements DocumentCodeValidator {
    private static final int CHAR_COUNT_IN_LINE = 30;
    private static final String EXPECTED_FIRST_CHAR = "I";
    private static final String LAST_CHAR_NOT_A_DIGIT = "LAST_CHAR_NOT_A_DIGIT";
    private static final int LINE_COUNT = 3;
    private static final String MESSAGE_NO_MRZ_FOUND = "MESSAGE_NO_MRZ_FOUND";
    private static final String NOT_START_WITH_I = "NOT_START_WITH_I";

    @Override // com.onfido.android.sdk.capture.validation.DocumentCodeValidator
    public DocumentCodeValidatorResult validate(String text) {
        String str;
        Intrinsics.checkNotNullParameter(text, "text");
        List list = SequencesKt.toList(SequencesKt.filter(StringsKt.lineSequence(StringsKt.replace$default(text, StringUtils.SPACE, "", false, 4, (Object) null)), new Function1<String, Boolean>() { // from class: com.onfido.android.sdk.capture.detector.mrz.DutchIDMRZValidator$validate$validLines$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String line) {
                Intrinsics.checkNotNullParameter(line, "line");
                return Boolean.valueOf(line.length() == 30);
            }
        }));
        boolean z = false;
        if (list.size() != 3) {
            return new DocumentCodeValidatorResult(false, "MESSAGE_NO_MRZ_FOUND");
        }
        if (!StringsKt.startsWith$default((String) CollectionsKt.first(list), EXPECTED_FIRST_CHAR, false, 2, (Object) null)) {
            str = NOT_START_WITH_I;
        } else if (Character.isDigit(StringsKt.last((CharSequence) CollectionsKt.first(list)))) {
            z = true;
            str = "";
        } else {
            str = LAST_CHAR_NOT_A_DIGIT;
        }
        return new DocumentCodeValidatorResult(z, str);
    }
}
