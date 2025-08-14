package com.onfido.android.sdk.capture.utils;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.InternalDocSide;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004\"\u0002H\u0002H\u0000¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0006\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u0002H\u0002H\u0080\b¢\u0006\u0002\u0010\b\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\f\u0010\f\u001a\u00020\u000b*\u00020\nH\u0000\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0000¨\u0006\u0010"}, d2 = {"hashCode", "", ExifInterface.GPS_DIRECTION_TRUE, "objects", "", "([Ljava/lang/Object;)I", "tag", "", "(Ljava/lang/Object;)Ljava/lang/String;", "toDocType", "Lcom/onfido/api/client/data/DocType;", "Lcom/onfido/android/sdk/capture/DocumentType;", "toDocumentType", "toInternalDocSide", "Lcom/onfido/api/client/data/InternalDocSide;", "Lcom/onfido/api/client/data/DocSide;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoExtensionsKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DocType.values().length];
            try {
                iArr[DocType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocType.DRIVING_LICENSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocType.NATIONAL_ID_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocType.VISA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DocType.WORK_PERMIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DocType.RESIDENCE_PERMIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DocType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocumentType.values().length];
            try {
                iArr2[DocumentType.PASSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[DocumentType.DRIVING_LICENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[DocumentType.NATIONAL_IDENTITY_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[DocumentType.VISA.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[DocumentType.WORK_PERMIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[DocumentType.RESIDENCE_PERMIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[DocumentType.GENERIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[DocumentType.UNKNOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[DocSide.values().length];
            try {
                iArr3[DocSide.FRONT.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr3[DocSide.BACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public static final <T> int hashCode(T... objects) {
        Intrinsics.checkNotNullParameter(objects, "objects");
        int length = objects.length;
        int iHashCode = 0;
        for (int i = 0; i < length; i++) {
            T t = objects[i];
            iHashCode = (t != null ? t.hashCode() : 0) + (31 * iHashCode);
        }
        return iHashCode;
    }

    public static final /* synthetic */ <T> String tag(T t) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String simpleName = Object.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return simpleName;
    }

    public static final DocType toDocType(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[documentType.ordinal()]) {
            case 1:
                return DocType.PASSPORT;
            case 2:
                return DocType.DRIVING_LICENSE;
            case 3:
                return DocType.NATIONAL_ID_CARD;
            case 4:
                return DocType.VISA;
            case 5:
                return DocType.WORK_PERMIT;
            case 6:
                return DocType.RESIDENCE_PERMIT;
            case 7:
            case 8:
                return DocType.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final DocumentType toDocumentType(DocType docType) {
        Intrinsics.checkNotNullParameter(docType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[docType.ordinal()]) {
            case 1:
                return DocumentType.PASSPORT;
            case 2:
                return DocumentType.DRIVING_LICENCE;
            case 3:
                return DocumentType.NATIONAL_IDENTITY_CARD;
            case 4:
                return DocumentType.VISA;
            case 5:
                return DocumentType.WORK_PERMIT;
            case 6:
                return DocumentType.RESIDENCE_PERMIT;
            case 7:
                return DocumentType.UNKNOWN;
            default:
                return DocumentType.GENERIC;
        }
    }

    public static final InternalDocSide toInternalDocSide(DocSide docSide) {
        Intrinsics.checkNotNullParameter(docSide, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$2[docSide.ordinal()];
        if (i == 1) {
            return InternalDocSide.FRONT;
        }
        if (i == 2) {
            return InternalDocSide.BACK;
        }
        throw new NoWhenBranchMatchedException();
    }
}
