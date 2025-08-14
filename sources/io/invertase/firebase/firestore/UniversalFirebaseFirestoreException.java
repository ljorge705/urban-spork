package io.invertase.firebase.firestore;

import com.google.firebase.firestore.FirebaseFirestoreException;

/* loaded from: classes6.dex */
public class UniversalFirebaseFirestoreException extends Exception {
    private final String code;
    private final String message;

    public String getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:134:0x022d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    UniversalFirebaseFirestoreException(com.google.firebase.firestore.FirebaseFirestoreException r28, java.lang.Throwable r29) {
        /*
            Method dump skipped, instructions count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.UniversalFirebaseFirestoreException.<init>(com.google.firebase.firestore.FirebaseFirestoreException, java.lang.Throwable):void");
    }

    /* renamed from: io.invertase.firebase.firestore.UniversalFirebaseFirestoreException$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = iArr;
            try {
                iArr[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }
}
