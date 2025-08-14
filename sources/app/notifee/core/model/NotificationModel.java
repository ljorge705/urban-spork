package app.notifee.core.model;

import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import java.util.Objects;

/* loaded from: classes5.dex */
public class NotificationModel {

    /* renamed from: a, reason: collision with root package name */
    public Bundle f54a;

    public NotificationModel(Bundle bundle) {
        this.f54a = bundle;
    }

    public NotificationAndroidModel a() {
        return NotificationAndroidModel.fromBundle(this.f54a.getBundle(Constants.KEY_ANDROID));
    }

    public Integer b() {
        return Integer.valueOf(c().hashCode());
    }

    public String c() {
        return (String) Objects.requireNonNull(this.f54a.getString("id"));
    }

    public Bundle toBundle() {
        return (Bundle) this.f54a.clone();
    }
}
