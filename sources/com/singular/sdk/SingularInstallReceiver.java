package com.singular.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.singular.sdk.internal.SingularLog;
import com.singular.sdk.internal.Utils;

/* loaded from: classes6.dex */
public class SingularInstallReceiver extends BroadcastReceiver {
    private static final SingularLog logger = SingularLog.getLogger("Singular");

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Utils.isEmptyOrNull(action)) {
            logger.debug("Install.onReceive() action is empty");
            return;
        }
        if (!action.equals("com.android.vending.INSTALL_REFERRER")) {
            logger.debug("Install.onReceive() unknown action = %s", action);
            return;
        }
        String stringExtra = intent.getStringExtra("referrer");
        if (Utils.isEmptyOrNull(stringExtra)) {
            return;
        }
        Utils.saveCSIReferrer(context, stringExtra);
        logger.debug("saved referrer = %s", stringExtra);
    }
}
