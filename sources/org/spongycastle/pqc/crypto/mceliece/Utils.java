package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

/* loaded from: classes7.dex */
class Utils {
    Utils() {
    }

    static Digest getDigest(String str) {
        if (str.equals("SHA-1")) {
            return new SHA1Digest();
        }
        if (str.equals("SHA-224")) {
            return new SHA224Digest();
        }
        if (str.equals("SHA-256")) {
            return new SHA256Digest();
        }
        if (str.equals("SHA-384")) {
            return new SHA384Digest();
        }
        if (str.equals("SHA-512")) {
            return new SHA512Digest();
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }
}
