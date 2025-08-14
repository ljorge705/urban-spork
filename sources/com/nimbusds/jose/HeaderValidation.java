package com.nimbusds.jose;

import java.util.Iterator;

/* loaded from: classes2.dex */
class HeaderValidation {
    static void ensureDisjoint(Header header, UnprotectedHeader unprotectedHeader) throws IllegalHeaderException {
        if (header == null || unprotectedHeader == null) {
            return;
        }
        Iterator<String> it = unprotectedHeader.getIncludedParams().iterator();
        while (it.hasNext()) {
            if (header.getIncludedParams().contains(it.next())) {
                throw new IllegalHeaderException("The parameters in the protected header and the unprotected header must be disjoint");
            }
        }
    }

    private HeaderValidation() {
    }
}
