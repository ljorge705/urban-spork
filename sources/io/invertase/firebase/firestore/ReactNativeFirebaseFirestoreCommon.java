package io.invertase.firebase.firestore;

import com.facebook.react.bridge.Promise;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.UniversalFirebasePreferences;

/* loaded from: classes6.dex */
class ReactNativeFirebaseFirestoreCommon {
    ReactNativeFirebaseFirestoreCommon() {
    }

    static void rejectPromiseFirestoreException(Promise promise, Exception exc) {
        Throwable cause;
        if (exc instanceof FirebaseFirestoreException) {
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, universalFirebaseFirestoreException.getCode(), universalFirebaseFirestoreException.getMessage());
        } else {
            if (exc.getCause() != null && (exc.getCause() instanceof FirebaseFirestoreException)) {
                FirebaseFirestoreException firebaseFirestoreException = (FirebaseFirestoreException) exc.getCause();
                if (exc.getCause().getCause() != null) {
                    cause = exc.getCause().getCause();
                } else {
                    cause = exc.getCause();
                }
                UniversalFirebaseFirestoreException universalFirebaseFirestoreException2 = new UniversalFirebaseFirestoreException(firebaseFirestoreException, cause);
                ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, universalFirebaseFirestoreException2.getCode(), universalFirebaseFirestoreException2.getMessage());
                return;
            }
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, exc);
        }
    }

    static DocumentSnapshot.ServerTimestampBehavior getServerTimestampBehavior(String str) {
        String stringValue = UniversalFirebasePreferences.getSharedInstance().getStringValue(UniversalFirebaseFirestoreStatics.FIRESTORE_SERVER_TIMESTAMP_BEHAVIOR + "_" + str, "none");
        if ("estimate".equals(stringValue)) {
            return DocumentSnapshot.ServerTimestampBehavior.ESTIMATE;
        }
        if ("previous".equals(stringValue)) {
            return DocumentSnapshot.ServerTimestampBehavior.PREVIOUS;
        }
        return DocumentSnapshot.ServerTimestampBehavior.NONE;
    }
}
