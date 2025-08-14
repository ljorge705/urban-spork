package com.nimbusds.jose.util.events;

import com.nimbusds.jose.proc.SecurityContext;

/* loaded from: classes2.dex */
public interface EventListener<S, C extends SecurityContext> {
    void notify(Event<S, C> event);
}
