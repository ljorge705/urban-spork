package org.jmrtd;

import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;

/* loaded from: classes4.dex */
public abstract class FileSystemCardService extends CardService {
    @Deprecated
    public abstract CardFileInputStream getInputStream(short s) throws CardServiceException;

    public abstract CardFileInputStream getInputStream(short s, int i) throws CardServiceException;
}
