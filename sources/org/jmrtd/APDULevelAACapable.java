package org.jmrtd;

import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes4.dex */
public interface APDULevelAACapable {
    byte[] sendInternalAuthenticate(APDUWrapper aPDUWrapper, int i, byte[] bArr) throws CardServiceException;
}
