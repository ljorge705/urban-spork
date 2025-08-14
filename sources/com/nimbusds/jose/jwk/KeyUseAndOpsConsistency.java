package com.nimbusds.jose.jwk;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
class KeyUseAndOpsConsistency {
    static final Map<KeyUse, Set<KeyOperation>> MAP;

    KeyUseAndOpsConsistency() {
    }

    static {
        HashMap map = new HashMap();
        map.put(KeyUse.SIGNATURE, new HashSet(Arrays.asList(KeyOperation.SIGN, KeyOperation.VERIFY)));
        map.put(KeyUse.ENCRYPTION, new HashSet(Arrays.asList(KeyOperation.ENCRYPT, KeyOperation.DECRYPT, KeyOperation.WRAP_KEY, KeyOperation.UNWRAP_KEY)));
        MAP = Collections.unmodifiableMap(map);
    }

    static boolean areConsistent(KeyUse keyUse, Set<KeyOperation> set) {
        if (keyUse == null || set == null) {
            return true;
        }
        Map<KeyUse, Set<KeyOperation>> map = MAP;
        return !map.containsKey(keyUse) || map.get(keyUse).containsAll(set);
    }
}
