package app.notifee.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes5.dex */
public class AlarmPermissionBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.app.action.SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED")) {
            Log.i("AlarmPermissionReceiver", "Received alarm permission state changed event");
            new b().b();
        }
    }
}
