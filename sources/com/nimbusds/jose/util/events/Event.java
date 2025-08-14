package com.nimbusds.jose.util.events;

import com.nimbusds.jose.proc.SecurityContext;

/* loaded from: classes2.dex */
public interface Event<S, C extends SecurityContext> {
    C getContext();

    S getSource();
}
