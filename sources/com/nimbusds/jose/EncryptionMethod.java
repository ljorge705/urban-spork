package com.nimbusds.jose;

import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class EncryptionMethod extends Algorithm {
    private static final long serialVersionUID = 1;
    private final int cekBitLength;
    public static final EncryptionMethod A128CBC_HS256 = new EncryptionMethod("A128CBC-HS256", Requirement.REQUIRED, 256);
    public static final EncryptionMethod A192CBC_HS384 = new EncryptionMethod("A192CBC-HS384", Requirement.OPTIONAL, 384);
    public static final EncryptionMethod A256CBC_HS512 = new EncryptionMethod("A256CBC-HS512", Requirement.REQUIRED, 512);
    public static final EncryptionMethod A128CBC_HS256_DEPRECATED = new EncryptionMethod("A128CBC+HS256", Requirement.OPTIONAL, 256);
    public static final EncryptionMethod A256CBC_HS512_DEPRECATED = new EncryptionMethod("A256CBC+HS512", Requirement.OPTIONAL, 512);
    public static final EncryptionMethod A128GCM = new EncryptionMethod("A128GCM", Requirement.RECOMMENDED, 128);
    public static final EncryptionMethod A192GCM = new EncryptionMethod("A192GCM", Requirement.OPTIONAL, 192);
    public static final EncryptionMethod A256GCM = new EncryptionMethod("A256GCM", Requirement.RECOMMENDED, 256);
    public static final EncryptionMethod XC20P = new EncryptionMethod("XC20P", Requirement.OPTIONAL, 256);

    public int cekBitLength() {
        return this.cekBitLength;
    }

    public static final class Family extends AlgorithmFamily<EncryptionMethod> {
        public static final Family AES_CBC_HMAC_SHA = new Family(EncryptionMethod.A128CBC_HS256, EncryptionMethod.A192CBC_HS384, EncryptionMethod.A256CBC_HS512);
        public static final Family AES_GCM = new Family(EncryptionMethod.A128GCM, EncryptionMethod.A192GCM, EncryptionMethod.A256GCM);
        private static final long serialVersionUID = 1;

        @Override // com.nimbusds.jose.AlgorithmFamily
        public /* bridge */ /* synthetic */ boolean add(Algorithm algorithm) {
            return super.add((Family) algorithm);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
            return super.addAll(collection);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean remove(Object obj) {
            return super.remove(obj);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
            return super.removeAll(collection);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
            return super.retainAll(collection);
        }

        public Family(EncryptionMethod... encryptionMethodArr) {
            super(encryptionMethodArr);
        }
    }

    public EncryptionMethod(String str, Requirement requirement, int i) {
        super(str, requirement);
        this.cekBitLength = i;
    }

    public EncryptionMethod(String str, Requirement requirement) {
        this(str, requirement, 0);
    }

    public EncryptionMethod(String str) {
        this(str, null, 0);
    }

    public static EncryptionMethod parse(String str) {
        EncryptionMethod encryptionMethod = A128CBC_HS256;
        if (str.equals(encryptionMethod.getName())) {
            return encryptionMethod;
        }
        EncryptionMethod encryptionMethod2 = A192CBC_HS384;
        if (str.equals(encryptionMethod2.getName())) {
            return encryptionMethod2;
        }
        EncryptionMethod encryptionMethod3 = A256CBC_HS512;
        if (str.equals(encryptionMethod3.getName())) {
            return encryptionMethod3;
        }
        EncryptionMethod encryptionMethod4 = A128GCM;
        if (str.equals(encryptionMethod4.getName())) {
            return encryptionMethod4;
        }
        EncryptionMethod encryptionMethod5 = A192GCM;
        if (str.equals(encryptionMethod5.getName())) {
            return encryptionMethod5;
        }
        EncryptionMethod encryptionMethod6 = A256GCM;
        if (str.equals(encryptionMethod6.getName())) {
            return encryptionMethod6;
        }
        EncryptionMethod encryptionMethod7 = A128CBC_HS256_DEPRECATED;
        if (str.equals(encryptionMethod7.getName())) {
            return encryptionMethod7;
        }
        EncryptionMethod encryptionMethod8 = A256CBC_HS512_DEPRECATED;
        if (str.equals(encryptionMethod8.getName())) {
            return encryptionMethod8;
        }
        EncryptionMethod encryptionMethod9 = XC20P;
        return str.equals(encryptionMethod9.getName()) ? encryptionMethod9 : new EncryptionMethod(str);
    }
}
