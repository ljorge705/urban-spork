package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.validation.MRZData;
import com.onfido.android.sdk.capture.validation.MRZDataType;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a6\u0010\u0000\u001a\u00020\u0001*\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0000¨\u0006\b"}, d2 = {"hasRequiredFields", "", "Ljava/util/HashMap;", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "Lkotlin/collections/HashMap;", "requiredFields", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentUtilsKt {
    public static final boolean hasRequiredFields(HashMap<MRZDataType, MRZData> map, List<? extends MRZDataType> requiredFields) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(requiredFields, "requiredFields");
        return map.keySet().containsAll(requiredFields);
    }
}
