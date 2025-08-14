package com.nimbusds.jose.crypto.opts;

import com.nimbusds.jose.JWSSignerOption;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class OptionUtils {
    public static <T extends JWSSignerOption> boolean optionIsPresent(Set<JWSSignerOption> set, Class<T> cls) {
        if (set != null && !set.isEmpty()) {
            Iterator<JWSSignerOption> it = set.iterator();
            while (it.hasNext()) {
                if (it.next().getClass().isAssignableFrom(cls)) {
                    return true;
                }
            }
        }
        return false;
    }
}
