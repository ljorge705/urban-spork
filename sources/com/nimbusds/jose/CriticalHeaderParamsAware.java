package com.nimbusds.jose;

import java.util.Set;

/* loaded from: classes2.dex */
public interface CriticalHeaderParamsAware {
    Set<String> getDeferredCriticalHeaderParams();

    Set<String> getProcessedCriticalHeaderParams();
}
