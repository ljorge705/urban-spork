package com.nimbusds.jose.jwk;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public enum KeyOperation {
    SIGN("sign"),
    VERIFY("verify"),
    ENCRYPT("encrypt"),
    DECRYPT("decrypt"),
    WRAP_KEY("wrapKey"),
    UNWRAP_KEY("unwrapKey"),
    DERIVE_KEY("deriveKey"),
    DERIVE_BITS("deriveBits");

    private final String identifier;

    public String identifier() {
        return this.identifier;
    }

    KeyOperation(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The key operation identifier must not be null");
        }
        this.identifier = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return identifier();
    }

    public static Set<KeyOperation> parse(List<String> list) throws ParseException {
        KeyOperation keyOperation;
        if (list == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : list) {
            if (str != null) {
                KeyOperation[] keyOperationArrValues = values();
                int length = keyOperationArrValues.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        keyOperation = null;
                        break;
                    }
                    keyOperation = keyOperationArrValues[i];
                    if (str.equals(keyOperation.identifier())) {
                        break;
                    }
                    i++;
                }
                if (keyOperation != null) {
                    linkedHashSet.add(keyOperation);
                } else {
                    throw new ParseException("Invalid JWK operation: " + str, 0);
                }
            }
        }
        return linkedHashSet;
    }
}
