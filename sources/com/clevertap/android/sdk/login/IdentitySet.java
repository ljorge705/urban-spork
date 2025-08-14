package com.clevertap.android.sdk.login;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class IdentitySet {
    private final HashSet<String> identities;

    private IdentitySet(String[] strArr) {
        this.identities = new HashSet<>();
        init(strArr);
    }

    private IdentitySet(HashSet<String> hashSet) {
        HashSet<String> hashSet2 = new HashSet<>();
        this.identities = hashSet2;
        hashSet2.addAll(hashSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.identities.equals(((IdentitySet) obj).identities);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.identities.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (Constants.ALL_IDENTITY_KEYS.contains(next)) {
                sb.append(next).append(it.hasNext() ? Constants.SEPARATOR_COMMA : "");
            }
        }
        return sb.toString();
    }

    boolean contains(String str) {
        return Utils.containsIgnoreCase(this.identities, str);
    }

    boolean isValid() {
        return !this.identities.isEmpty();
    }

    private void init(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (String str : strArr) {
            if (Utils.containsIgnoreCase(Constants.ALL_IDENTITY_KEYS, str)) {
                this.identities.add(Utils.convertToTitleCase(str));
            }
        }
    }

    static IdentitySet from(String str) {
        return new IdentitySet(str.split(Constants.SEPARATOR_COMMA));
    }

    static IdentitySet from(String[] strArr) {
        return new IdentitySet(strArr);
    }

    static IdentitySet getDefault() {
        return new IdentitySet(Constants.LEGACY_IDENTITY_KEYS);
    }
}
