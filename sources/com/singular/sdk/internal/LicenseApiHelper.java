package com.singular.sdk.internal;

import android.content.Context;

/* loaded from: classes6.dex */
public class LicenseApiHelper {
    public static final String LICENSING_EVENT_NAME = "__LicensingStatus";
    private static final SingularLog logger = SingularLog.getLogger("LicenseApiHelper");

    static abstract class LicenseResultHandler {
        public abstract void handle(int i, String str, String str2);

        LicenseResultHandler() {
        }
    }

    public static void checkLicense(Context context, LicenseResultHandler licenseResultHandler) {
        try {
            new LicenseChecker(context, licenseResultHandler).checkAccess();
        } catch (Throwable th) {
            logger.error("Error occurred while trying to run license check", th);
        }
    }
}
