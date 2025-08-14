package com.nimbusds.jose;

import com.nimbusds.jose.util.ArrayUtils;
import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class JWEAlgorithm extends Algorithm {
    private static final long serialVersionUID = 1;

    @Deprecated
    public static final JWEAlgorithm RSA1_5 = new JWEAlgorithm("RSA1_5", Requirement.REQUIRED);

    @Deprecated
    public static final JWEAlgorithm RSA_OAEP = new JWEAlgorithm("RSA-OAEP", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA_OAEP_256 = new JWEAlgorithm("RSA-OAEP-256", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA_OAEP_384 = new JWEAlgorithm("RSA-OAEP-384", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA_OAEP_512 = new JWEAlgorithm("RSA-OAEP-512", Requirement.OPTIONAL);
    public static final JWEAlgorithm A128KW = new JWEAlgorithm("A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm A192KW = new JWEAlgorithm("A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256KW = new JWEAlgorithm("A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm DIR = new JWEAlgorithm("dir", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES = new JWEAlgorithm("ECDH-ES", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A128KW = new JWEAlgorithm("ECDH-ES+A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A192KW = new JWEAlgorithm("ECDH-ES+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_ES_A256KW = new JWEAlgorithm("ECDH-ES+A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_1PU = new JWEAlgorithm("ECDH-1PU", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_1PU_A128KW = new JWEAlgorithm("ECDH-1PU+A128KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_1PU_A192KW = new JWEAlgorithm("ECDH-1PU+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_1PU_A256KW = new JWEAlgorithm("ECDH-1PU+A256KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A128GCMKW = new JWEAlgorithm("A128GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A192GCMKW = new JWEAlgorithm("A192GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256GCMKW = new JWEAlgorithm("A256GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS256_A128KW = new JWEAlgorithm("PBES2-HS256+A128KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS384_A192KW = new JWEAlgorithm("PBES2-HS384+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS512_A256KW = new JWEAlgorithm("PBES2-HS512+A256KW", Requirement.OPTIONAL);

    public static final class Family extends AlgorithmFamily<JWEAlgorithm> {
        public static final Family AES_GCM_KW;
        public static final Family AES_KW;
        public static final Family ASYMMETRIC;
        public static final Family ECDH_1PU;
        public static final Family ECDH_ES;
        public static final Family PBES2;
        public static final Family RSA;
        public static final Family SYMMETRIC;
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

        static {
            Family family = new Family(JWEAlgorithm.RSA1_5, JWEAlgorithm.RSA_OAEP, JWEAlgorithm.RSA_OAEP_256, JWEAlgorithm.RSA_OAEP_384, JWEAlgorithm.RSA_OAEP_512);
            RSA = family;
            Family family2 = new Family(JWEAlgorithm.A128KW, JWEAlgorithm.A192KW, JWEAlgorithm.A256KW);
            AES_KW = family2;
            Family family3 = new Family(JWEAlgorithm.ECDH_ES, JWEAlgorithm.ECDH_ES_A128KW, JWEAlgorithm.ECDH_ES_A192KW, JWEAlgorithm.ECDH_ES_A256KW);
            ECDH_ES = family3;
            ECDH_1PU = new Family(JWEAlgorithm.ECDH_1PU, JWEAlgorithm.ECDH_1PU_A128KW, JWEAlgorithm.ECDH_1PU_A192KW, JWEAlgorithm.ECDH_1PU_A256KW);
            Family family4 = new Family(JWEAlgorithm.A128GCMKW, JWEAlgorithm.A192GCMKW, JWEAlgorithm.A256GCMKW);
            AES_GCM_KW = family4;
            PBES2 = new Family(JWEAlgorithm.PBES2_HS256_A128KW, JWEAlgorithm.PBES2_HS384_A192KW, JWEAlgorithm.PBES2_HS512_A256KW);
            ASYMMETRIC = new Family((JWEAlgorithm[]) ArrayUtils.concat(family.toArray(new JWEAlgorithm[0]), (JWEAlgorithm[]) family3.toArray(new JWEAlgorithm[0])));
            SYMMETRIC = new Family((JWEAlgorithm[]) ArrayUtils.concat(family2.toArray(new JWEAlgorithm[0]), (JWEAlgorithm[]) family4.toArray(new JWEAlgorithm[0]), new JWEAlgorithm[]{JWEAlgorithm.DIR}));
        }

        public Family(JWEAlgorithm... jWEAlgorithmArr) {
            super(jWEAlgorithmArr);
        }
    }

    public JWEAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public JWEAlgorithm(String str) {
        super(str, null);
    }

    public static JWEAlgorithm parse(String str) {
        JWEAlgorithm jWEAlgorithm = RSA1_5;
        if (str.equals(jWEAlgorithm.getName())) {
            return jWEAlgorithm;
        }
        JWEAlgorithm jWEAlgorithm2 = RSA_OAEP;
        if (str.equals(jWEAlgorithm2.getName())) {
            return jWEAlgorithm2;
        }
        JWEAlgorithm jWEAlgorithm3 = RSA_OAEP_256;
        if (str.equals(jWEAlgorithm3.getName())) {
            return jWEAlgorithm3;
        }
        JWEAlgorithm jWEAlgorithm4 = RSA_OAEP_384;
        if (str.equals(jWEAlgorithm4.getName())) {
            return jWEAlgorithm4;
        }
        JWEAlgorithm jWEAlgorithm5 = RSA_OAEP_512;
        if (str.equals(jWEAlgorithm5.getName())) {
            return jWEAlgorithm5;
        }
        JWEAlgorithm jWEAlgorithm6 = A128KW;
        if (str.equals(jWEAlgorithm6.getName())) {
            return jWEAlgorithm6;
        }
        JWEAlgorithm jWEAlgorithm7 = A192KW;
        if (str.equals(jWEAlgorithm7.getName())) {
            return jWEAlgorithm7;
        }
        JWEAlgorithm jWEAlgorithm8 = A256KW;
        if (str.equals(jWEAlgorithm8.getName())) {
            return jWEAlgorithm8;
        }
        JWEAlgorithm jWEAlgorithm9 = DIR;
        if (str.equals(jWEAlgorithm9.getName())) {
            return jWEAlgorithm9;
        }
        JWEAlgorithm jWEAlgorithm10 = ECDH_ES;
        if (str.equals(jWEAlgorithm10.getName())) {
            return jWEAlgorithm10;
        }
        JWEAlgorithm jWEAlgorithm11 = ECDH_ES_A128KW;
        if (str.equals(jWEAlgorithm11.getName())) {
            return jWEAlgorithm11;
        }
        JWEAlgorithm jWEAlgorithm12 = ECDH_ES_A192KW;
        if (str.equals(jWEAlgorithm12.getName())) {
            return jWEAlgorithm12;
        }
        JWEAlgorithm jWEAlgorithm13 = ECDH_ES_A256KW;
        if (str.equals(jWEAlgorithm13.getName())) {
            return jWEAlgorithm13;
        }
        JWEAlgorithm jWEAlgorithm14 = ECDH_1PU;
        if (str.equals(jWEAlgorithm14.getName())) {
            return jWEAlgorithm14;
        }
        JWEAlgorithm jWEAlgorithm15 = ECDH_1PU_A128KW;
        if (str.equals(jWEAlgorithm15.getName())) {
            return jWEAlgorithm15;
        }
        JWEAlgorithm jWEAlgorithm16 = ECDH_1PU_A192KW;
        if (str.equals(jWEAlgorithm16.getName())) {
            return jWEAlgorithm16;
        }
        JWEAlgorithm jWEAlgorithm17 = ECDH_1PU_A256KW;
        if (str.equals(jWEAlgorithm17.getName())) {
            return jWEAlgorithm17;
        }
        JWEAlgorithm jWEAlgorithm18 = A128GCMKW;
        if (str.equals(jWEAlgorithm18.getName())) {
            return jWEAlgorithm18;
        }
        JWEAlgorithm jWEAlgorithm19 = A192GCMKW;
        if (str.equals(jWEAlgorithm19.getName())) {
            return jWEAlgorithm19;
        }
        JWEAlgorithm jWEAlgorithm20 = A256GCMKW;
        if (str.equals(jWEAlgorithm20.getName())) {
            return jWEAlgorithm20;
        }
        JWEAlgorithm jWEAlgorithm21 = PBES2_HS256_A128KW;
        if (str.equals(jWEAlgorithm21.getName())) {
            return jWEAlgorithm21;
        }
        JWEAlgorithm jWEAlgorithm22 = PBES2_HS384_A192KW;
        if (str.equals(jWEAlgorithm22.getName())) {
            return jWEAlgorithm22;
        }
        JWEAlgorithm jWEAlgorithm23 = PBES2_HS512_A256KW;
        return str.equals(jWEAlgorithm23.getName()) ? jWEAlgorithm23 : new JWEAlgorithm(str);
    }
}
