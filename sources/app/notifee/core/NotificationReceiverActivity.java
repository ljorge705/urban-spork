package app.notifee.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import n.o.t.i.f.e.e.k;

/* loaded from: classes5.dex */
public class NotificationReceiverActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        k.a(this, getIntent());
        finish();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        k.a(this, intent);
        finish();
    }
}
