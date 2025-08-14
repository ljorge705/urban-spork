package com.google.android.play.core.splitinstall.internal;

import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzt {
    protected final Set zza = new HashSet();

    public final synchronized void zza(StateUpdatedListener stateUpdatedListener) {
        this.zza.add(stateUpdatedListener);
    }

    public final synchronized void zzb(StateUpdatedListener stateUpdatedListener) {
        this.zza.remove(stateUpdatedListener);
    }

    public final synchronized void zzc(Object obj) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(obj);
        }
    }
}
