package net.sf.scuba.smartcards;

import java.util.EventListener;

/* loaded from: classes4.dex */
public interface APDUListener extends EventListener {
    void exchangedAPDU(APDUEvent aPDUEvent);
}
