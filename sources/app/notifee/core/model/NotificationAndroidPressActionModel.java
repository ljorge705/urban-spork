package app.notifee.core.model;

import android.os.Build;
import android.os.Bundle;
import androidx.media3.common.C;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes5.dex */
public class NotificationAndroidPressActionModel {
    private Bundle mNotificationAndroidPressActionBundle;

    private NotificationAndroidPressActionModel(Bundle bundle) {
        this.mNotificationAndroidPressActionBundle = bundle;
    }

    public static NotificationAndroidPressActionModel fromBundle(Bundle bundle) {
        return new NotificationAndroidPressActionModel(bundle);
    }

    public String getId() {
        return (String) Objects.requireNonNull(this.mNotificationAndroidPressActionBundle.getString("id"));
    }

    public String getLaunchActivity() {
        return this.mNotificationAndroidPressActionBundle.getString("launchActivity");
    }

    public int getLaunchActivityFlags() {
        int i;
        if (!this.mNotificationAndroidPressActionBundle.containsKey("launchActivityFlags")) {
            return -1;
        }
        ArrayList arrayList = (ArrayList) Objects.requireNonNull(this.mNotificationAndroidPressActionBundle.getIntegerArrayList("launchActivityFlags"));
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            switch (((Integer) arrayList.get(i3)).intValue()) {
                case 0:
                    i = 1073741824;
                    i2 |= i;
                    break;
                case 1:
                    i = 536870912;
                    i2 |= i;
                    break;
                case 2:
                    i = 268435456;
                    i2 |= i;
                    break;
                case 3:
                    i = C.BUFFER_FLAG_FIRST_SAMPLE;
                    i2 |= i;
                    break;
                case 4:
                    i = 67108864;
                    i2 |= i;
                    break;
                case 5:
                    i = 33554432;
                    i2 |= i;
                    break;
                case 6:
                    i = 16777216;
                    i2 |= i;
                    break;
                case 7:
                    i = 8388608;
                    i2 |= i;
                    break;
                case 8:
                    i = 4194304;
                    i2 |= i;
                    break;
                case 9:
                    i = 2097152;
                    i2 |= i;
                    break;
                case 10:
                    i = 1048576;
                    i2 |= i;
                    break;
                case 11:
                case 12:
                    i = 524288;
                    i2 |= i;
                    break;
                case 13:
                    i = 262144;
                    i2 |= i;
                    break;
                case 14:
                    i = 131072;
                    i2 |= i;
                    break;
                case 15:
                    i = 65536;
                    i2 |= i;
                    break;
                case 16:
                    i = 32768;
                    i2 |= i;
                    break;
                case 17:
                    i = 16384;
                    i2 |= i;
                    break;
                case 18:
                    i = 8192;
                    i2 |= i;
                    break;
                case 19:
                    i = 4096;
                    i2 |= i;
                    break;
                case 20:
                    if (Build.VERSION.SDK_INT >= 28) {
                        i = 2048;
                        i2 |= i;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return i2;
    }

    public String getMainComponent() {
        return this.mNotificationAndroidPressActionBundle.getString("mainComponent");
    }

    public Bundle toBundle() {
        return (Bundle) this.mNotificationAndroidPressActionBundle.clone();
    }
}
