package androidx.media3.common;

import android.os.Bundle;

/* loaded from: classes5.dex */
public interface Bundleable {

    public interface Creator<T extends Bundleable> {
        T fromBundle(Bundle bundle);
    }

    Bundle toBundle();
}
