package com.google.android.gms.common.api.internal;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-base@@18.2.0 */
/* loaded from: classes3.dex */
final class zabs {
    private final ApiKey zaa;
    private final Feature zab;

    /* synthetic */ zabs(ApiKey apiKey, Feature feature, zabr zabrVar) {
        this.zaa = apiKey;
        this.zab = feature;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zabs)) {
            zabs zabsVar = (zabs) obj;
            if (Objects.equal(this.zaa, zabsVar.zaa) && Objects.equal(this.zab, zabsVar.zab)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zaa, this.zab);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(Constants.KEY_KEY, this.zaa).add("feature", this.zab).toString();
    }
}
