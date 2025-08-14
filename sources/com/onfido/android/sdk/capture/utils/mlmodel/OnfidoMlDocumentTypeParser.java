package com.onfido.android.sdk.capture.utils.mlmodel;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocumentTypeParser;", "", "()V", "DOCUMENT_CODE_DOCUMENT_TYPE_MAP", "", "", "Lcom/onfido/android/sdk/capture/DocumentType;", "ML_DOCUMENT_TYPE_DELIMITER", "documentSideMap", "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlDocumentSide;", "getDocumentCountryCode", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", OnfidoLauncher.KEY_RESULT, "getDocumentSide", "getDocumentTypeFrom", "getDocumentVersion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoMlDocumentTypeParser {
    private static final String ML_DOCUMENT_TYPE_DELIMITER = "_";
    public static final OnfidoMlDocumentTypeParser INSTANCE = new OnfidoMlDocumentTypeParser();
    private static final Map<String, DocumentType> DOCUMENT_CODE_DOCUMENT_TYPE_MAP = MapsKt.mapOf(TuplesKt.to("P", DocumentType.PASSPORT), TuplesKt.to("D", DocumentType.DRIVING_LICENCE), TuplesKt.to("I", DocumentType.NATIONAL_IDENTITY_CARD), TuplesKt.to(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, DocumentType.VISA), TuplesKt.to(ExifInterface.LONGITUDE_WEST, DocumentType.WORK_PERMIT), TuplesKt.to("R", DocumentType.RESIDENCE_PERMIT));
    private static final Map<String, OnfidoMlDocumentSide> documentSideMap = MapsKt.mapOf(TuplesKt.to("BACK", OnfidoMlDocumentSide.BACK), TuplesKt.to("FRONT", OnfidoMlDocumentSide.FRONT));

    private OnfidoMlDocumentTypeParser() {
    }

    public final CountryCode getDocumentCountryCode(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        String str = (String) CollectionsKt.getOrNull(StringsKt.split$default((CharSequence) result, new String[]{ML_DOCUMENT_TYPE_DELIMITER}, false, 0, 6, (Object) null), 1);
        if (str == null) {
            return null;
        }
        return CountryCode.INSTANCE.fromAlpha3(str);
    }

    public final OnfidoMlDocumentSide getDocumentSide(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        String str = (String) CollectionsKt.lastOrNull(CollectionsKt.dropLast(StringsKt.split$default((CharSequence) result, new String[]{ML_DOCUMENT_TYPE_DELIMITER}, false, 0, 6, (Object) null), 1));
        if (str == null) {
            return null;
        }
        OnfidoMlDocumentSide onfidoMlDocumentSide = documentSideMap.get(str);
        if (onfidoMlDocumentSide == null) {
            onfidoMlDocumentSide = OnfidoMlDocumentSide.UNKNOWN;
        }
        return onfidoMlDocumentSide;
    }

    public final DocumentType getDocumentTypeFrom(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        String str = (String) CollectionsKt.firstOrNull(StringsKt.split$default((CharSequence) result, new String[]{ML_DOCUMENT_TYPE_DELIMITER}, false, 0, 6, (Object) null));
        if (str == null) {
            return null;
        }
        DocumentType documentType = DOCUMENT_CODE_DOCUMENT_TYPE_MAP.get(str);
        return documentType == null ? DocumentType.UNKNOWN : documentType;
    }

    public final String getDocumentVersion(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        String str = (String) CollectionsKt.lastOrNull(StringsKt.split$default((CharSequence) result, new String[]{ML_DOCUMENT_TYPE_DELIMITER}, false, 0, 6, (Object) null));
        if (str == null) {
            return null;
        }
        return str;
    }
}
