package org.jmrtd;

import net.sf.scuba.smartcards.CardServiceException;

@Deprecated
/* loaded from: classes4.dex */
public class AccessDeniedException extends CardServiceException {
    private static final long serialVersionUID = -7094953658210693249L;
    private final AccessKeySpec bacKey;

    public AccessKeySpec getAccessKey() {
        return this.bacKey;
    }

    public AccessDeniedException(String str, int i) {
        this(str, null, i);
    }

    public AccessDeniedException(String str, AccessKeySpec accessKeySpec, int i) {
        super(str, i);
        this.bacKey = accessKeySpec;
    }
}
