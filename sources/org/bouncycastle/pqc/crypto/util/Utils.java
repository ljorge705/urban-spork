package org.bouncycastle.pqc.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.util.Integers;

/* loaded from: classes4.dex */
class Utils {
    static final AlgorithmIdentifier AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
    static final AlgorithmIdentifier AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);
    static final AlgorithmIdentifier SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
    static final AlgorithmIdentifier SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
    static final AlgorithmIdentifier XMSS_SHA256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    static final AlgorithmIdentifier XMSS_SHA512 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
    static final AlgorithmIdentifier XMSS_SHAKE128 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
    static final AlgorithmIdentifier XMSS_SHAKE256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
    static final Map bikeOids;
    static final Map bikeParams;
    static final Map categories;
    static final Map dilithiumOids;
    static final Map dilithiumParams;
    static final Map falconOids;
    static final Map falconParams;
    static final Map frodoOids;
    static final Map frodoParams;
    static final Map hqcOids;
    static final Map hqcParams;
    static final Map kyberOids;
    static final Map kyberParams;
    static final Map mcElieceOids;
    static final Map mcElieceParams;
    static final Map ntruOids;
    static final Map ntruParams;
    static final Map ntruprimeOids;
    static final Map ntruprimeParams;
    static final Map picnicOids;
    static final Map picnicParams;
    static final Map rainbowOids;
    static final Map rainbowParams;
    static final Map saberOids;
    static final Map saberParams;
    static final Map sikeOids;
    static final Map sikeParams;
    static final Map sntruprimeOids;
    static final Map sntruprimeParams;
    static final Map sphincsPlusOids;
    static final Map sphincsPlusParams;

    static {
        HashMap map = new HashMap();
        categories = map;
        HashMap map2 = new HashMap();
        picnicOids = map2;
        HashMap map3 = new HashMap();
        picnicParams = map3;
        HashMap map4 = new HashMap();
        frodoOids = map4;
        HashMap map5 = new HashMap();
        frodoParams = map5;
        HashMap map6 = new HashMap();
        saberOids = map6;
        HashMap map7 = new HashMap();
        saberParams = map7;
        HashMap map8 = new HashMap();
        mcElieceOids = map8;
        HashMap map9 = new HashMap();
        mcElieceParams = map9;
        HashMap map10 = new HashMap();
        sphincsPlusOids = map10;
        HashMap map11 = new HashMap();
        sphincsPlusParams = map11;
        sikeOids = new HashMap();
        sikeParams = new HashMap();
        HashMap map12 = new HashMap();
        ntruOids = map12;
        HashMap map13 = new HashMap();
        ntruParams = map13;
        HashMap map14 = new HashMap();
        falconOids = map14;
        HashMap map15 = new HashMap();
        falconParams = map15;
        HashMap map16 = new HashMap();
        kyberOids = map16;
        HashMap map17 = new HashMap();
        kyberParams = map17;
        HashMap map18 = new HashMap();
        ntruprimeOids = map18;
        HashMap map19 = new HashMap();
        ntruprimeParams = map19;
        HashMap map20 = new HashMap();
        sntruprimeOids = map20;
        HashMap map21 = new HashMap();
        sntruprimeParams = map21;
        HashMap map22 = new HashMap();
        dilithiumOids = map22;
        HashMap map23 = new HashMap();
        dilithiumParams = map23;
        HashMap map24 = new HashMap();
        bikeOids = map24;
        HashMap map25 = new HashMap();
        bikeParams = map25;
        HashMap map26 = new HashMap();
        hqcOids = map26;
        HashMap map27 = new HashMap();
        hqcParams = map27;
        HashMap map28 = new HashMap();
        rainbowOids = map28;
        HashMap map29 = new HashMap();
        rainbowParams = map29;
        map.put(PQCObjectIdentifiers.qTESLA_p_I, Integers.valueOf(5));
        map.put(PQCObjectIdentifiers.qTESLA_p_III, Integers.valueOf(6));
        map8.put(CMCEParameters.mceliece348864r3, BCObjectIdentifiers.mceliece348864_r3);
        map8.put(CMCEParameters.mceliece348864fr3, BCObjectIdentifiers.mceliece348864f_r3);
        map8.put(CMCEParameters.mceliece460896r3, BCObjectIdentifiers.mceliece460896_r3);
        map8.put(CMCEParameters.mceliece460896fr3, BCObjectIdentifiers.mceliece460896f_r3);
        map8.put(CMCEParameters.mceliece6688128r3, BCObjectIdentifiers.mceliece6688128_r3);
        map8.put(CMCEParameters.mceliece6688128fr3, BCObjectIdentifiers.mceliece6688128f_r3);
        map8.put(CMCEParameters.mceliece6960119r3, BCObjectIdentifiers.mceliece6960119_r3);
        map8.put(CMCEParameters.mceliece6960119fr3, BCObjectIdentifiers.mceliece6960119f_r3);
        map8.put(CMCEParameters.mceliece8192128r3, BCObjectIdentifiers.mceliece8192128_r3);
        map8.put(CMCEParameters.mceliece8192128fr3, BCObjectIdentifiers.mceliece8192128f_r3);
        map9.put(BCObjectIdentifiers.mceliece348864_r3, CMCEParameters.mceliece348864r3);
        map9.put(BCObjectIdentifiers.mceliece348864f_r3, CMCEParameters.mceliece348864fr3);
        map9.put(BCObjectIdentifiers.mceliece460896_r3, CMCEParameters.mceliece460896r3);
        map9.put(BCObjectIdentifiers.mceliece460896f_r3, CMCEParameters.mceliece460896fr3);
        map9.put(BCObjectIdentifiers.mceliece6688128_r3, CMCEParameters.mceliece6688128r3);
        map9.put(BCObjectIdentifiers.mceliece6688128f_r3, CMCEParameters.mceliece6688128fr3);
        map9.put(BCObjectIdentifiers.mceliece6960119_r3, CMCEParameters.mceliece6960119r3);
        map9.put(BCObjectIdentifiers.mceliece6960119f_r3, CMCEParameters.mceliece6960119fr3);
        map9.put(BCObjectIdentifiers.mceliece8192128_r3, CMCEParameters.mceliece8192128r3);
        map9.put(BCObjectIdentifiers.mceliece8192128f_r3, CMCEParameters.mceliece8192128fr3);
        map4.put(FrodoParameters.frodokem640aes, BCObjectIdentifiers.frodokem640aes);
        map4.put(FrodoParameters.frodokem640shake, BCObjectIdentifiers.frodokem640shake);
        map4.put(FrodoParameters.frodokem976aes, BCObjectIdentifiers.frodokem976aes);
        map4.put(FrodoParameters.frodokem976shake, BCObjectIdentifiers.frodokem976shake);
        map4.put(FrodoParameters.frodokem1344aes, BCObjectIdentifiers.frodokem1344aes);
        map4.put(FrodoParameters.frodokem1344shake, BCObjectIdentifiers.frodokem1344shake);
        map5.put(BCObjectIdentifiers.frodokem640aes, FrodoParameters.frodokem640aes);
        map5.put(BCObjectIdentifiers.frodokem640shake, FrodoParameters.frodokem640shake);
        map5.put(BCObjectIdentifiers.frodokem976aes, FrodoParameters.frodokem976aes);
        map5.put(BCObjectIdentifiers.frodokem976shake, FrodoParameters.frodokem976shake);
        map5.put(BCObjectIdentifiers.frodokem1344aes, FrodoParameters.frodokem1344aes);
        map5.put(BCObjectIdentifiers.frodokem1344shake, FrodoParameters.frodokem1344shake);
        map6.put(SABERParameters.lightsaberkem128r3, BCObjectIdentifiers.lightsaberkem128r3);
        map6.put(SABERParameters.saberkem128r3, BCObjectIdentifiers.saberkem128r3);
        map6.put(SABERParameters.firesaberkem128r3, BCObjectIdentifiers.firesaberkem128r3);
        map6.put(SABERParameters.lightsaberkem192r3, BCObjectIdentifiers.lightsaberkem192r3);
        map6.put(SABERParameters.saberkem192r3, BCObjectIdentifiers.saberkem192r3);
        map6.put(SABERParameters.firesaberkem192r3, BCObjectIdentifiers.firesaberkem192r3);
        map6.put(SABERParameters.lightsaberkem256r3, BCObjectIdentifiers.lightsaberkem256r3);
        map6.put(SABERParameters.saberkem256r3, BCObjectIdentifiers.saberkem256r3);
        map6.put(SABERParameters.firesaberkem256r3, BCObjectIdentifiers.firesaberkem256r3);
        map6.put(SABERParameters.ulightsaberkemr3, BCObjectIdentifiers.ulightsaberkemr3);
        map6.put(SABERParameters.usaberkemr3, BCObjectIdentifiers.usaberkemr3);
        map6.put(SABERParameters.ufiresaberkemr3, BCObjectIdentifiers.ufiresaberkemr3);
        map6.put(SABERParameters.lightsaberkem90sr3, BCObjectIdentifiers.lightsaberkem90sr3);
        map6.put(SABERParameters.saberkem90sr3, BCObjectIdentifiers.saberkem90sr3);
        map6.put(SABERParameters.firesaberkem90sr3, BCObjectIdentifiers.firesaberkem90sr3);
        map6.put(SABERParameters.ulightsaberkem90sr3, BCObjectIdentifiers.ulightsaberkem90sr3);
        map6.put(SABERParameters.usaberkem90sr3, BCObjectIdentifiers.usaberkem90sr3);
        map6.put(SABERParameters.ufiresaberkem90sr3, BCObjectIdentifiers.ufiresaberkem90sr3);
        map7.put(BCObjectIdentifiers.lightsaberkem128r3, SABERParameters.lightsaberkem128r3);
        map7.put(BCObjectIdentifiers.saberkem128r3, SABERParameters.saberkem128r3);
        map7.put(BCObjectIdentifiers.firesaberkem128r3, SABERParameters.firesaberkem128r3);
        map7.put(BCObjectIdentifiers.lightsaberkem192r3, SABERParameters.lightsaberkem192r3);
        map7.put(BCObjectIdentifiers.saberkem192r3, SABERParameters.saberkem192r3);
        map7.put(BCObjectIdentifiers.firesaberkem192r3, SABERParameters.firesaberkem192r3);
        map7.put(BCObjectIdentifiers.lightsaberkem256r3, SABERParameters.lightsaberkem256r3);
        map7.put(BCObjectIdentifiers.saberkem256r3, SABERParameters.saberkem256r3);
        map7.put(BCObjectIdentifiers.firesaberkem256r3, SABERParameters.firesaberkem256r3);
        map7.put(BCObjectIdentifiers.ulightsaberkemr3, SABERParameters.ulightsaberkemr3);
        map7.put(BCObjectIdentifiers.usaberkemr3, SABERParameters.usaberkemr3);
        map7.put(BCObjectIdentifiers.ufiresaberkemr3, SABERParameters.ufiresaberkemr3);
        map7.put(BCObjectIdentifiers.lightsaberkem90sr3, SABERParameters.lightsaberkem90sr3);
        map7.put(BCObjectIdentifiers.saberkem90sr3, SABERParameters.saberkem90sr3);
        map7.put(BCObjectIdentifiers.firesaberkem90sr3, SABERParameters.firesaberkem90sr3);
        map7.put(BCObjectIdentifiers.ulightsaberkem90sr3, SABERParameters.ulightsaberkem90sr3);
        map7.put(BCObjectIdentifiers.usaberkem90sr3, SABERParameters.usaberkem90sr3);
        map7.put(BCObjectIdentifiers.ufiresaberkem90sr3, SABERParameters.ufiresaberkem90sr3);
        map2.put(PicnicParameters.picnicl1fs, BCObjectIdentifiers.picnicl1fs);
        map2.put(PicnicParameters.picnicl1ur, BCObjectIdentifiers.picnicl1ur);
        map2.put(PicnicParameters.picnicl3fs, BCObjectIdentifiers.picnicl3fs);
        map2.put(PicnicParameters.picnicl3ur, BCObjectIdentifiers.picnicl3ur);
        map2.put(PicnicParameters.picnicl5fs, BCObjectIdentifiers.picnicl5fs);
        map2.put(PicnicParameters.picnicl5ur, BCObjectIdentifiers.picnicl5ur);
        map2.put(PicnicParameters.picnic3l1, BCObjectIdentifiers.picnic3l1);
        map2.put(PicnicParameters.picnic3l3, BCObjectIdentifiers.picnic3l3);
        map2.put(PicnicParameters.picnic3l5, BCObjectIdentifiers.picnic3l5);
        map2.put(PicnicParameters.picnicl1full, BCObjectIdentifiers.picnicl1full);
        map2.put(PicnicParameters.picnicl3full, BCObjectIdentifiers.picnicl3full);
        map2.put(PicnicParameters.picnicl5full, BCObjectIdentifiers.picnicl5full);
        map3.put(BCObjectIdentifiers.picnicl1fs, PicnicParameters.picnicl1fs);
        map3.put(BCObjectIdentifiers.picnicl1ur, PicnicParameters.picnicl1ur);
        map3.put(BCObjectIdentifiers.picnicl3fs, PicnicParameters.picnicl3fs);
        map3.put(BCObjectIdentifiers.picnicl3ur, PicnicParameters.picnicl3ur);
        map3.put(BCObjectIdentifiers.picnicl5fs, PicnicParameters.picnicl5fs);
        map3.put(BCObjectIdentifiers.picnicl5ur, PicnicParameters.picnicl5ur);
        map3.put(BCObjectIdentifiers.picnic3l1, PicnicParameters.picnic3l1);
        map3.put(BCObjectIdentifiers.picnic3l3, PicnicParameters.picnic3l3);
        map3.put(BCObjectIdentifiers.picnic3l5, PicnicParameters.picnic3l5);
        map3.put(BCObjectIdentifiers.picnicl1full, PicnicParameters.picnicl1full);
        map3.put(BCObjectIdentifiers.picnicl3full, PicnicParameters.picnicl3full);
        map3.put(BCObjectIdentifiers.picnicl5full, PicnicParameters.picnicl5full);
        map12.put(NTRUParameters.ntruhps2048509, BCObjectIdentifiers.ntruhps2048509);
        map12.put(NTRUParameters.ntruhps2048677, BCObjectIdentifiers.ntruhps2048677);
        map12.put(NTRUParameters.ntruhps4096821, BCObjectIdentifiers.ntruhps4096821);
        map12.put(NTRUParameters.ntruhps40961229, BCObjectIdentifiers.ntruhps40961229);
        map12.put(NTRUParameters.ntruhrss701, BCObjectIdentifiers.ntruhrss701);
        map12.put(NTRUParameters.ntruhrss1373, BCObjectIdentifiers.ntruhrss1373);
        map13.put(BCObjectIdentifiers.ntruhps2048509, NTRUParameters.ntruhps2048509);
        map13.put(BCObjectIdentifiers.ntruhps2048677, NTRUParameters.ntruhps2048677);
        map13.put(BCObjectIdentifiers.ntruhps4096821, NTRUParameters.ntruhps4096821);
        map13.put(BCObjectIdentifiers.ntruhps40961229, NTRUParameters.ntruhps40961229);
        map13.put(BCObjectIdentifiers.ntruhrss701, NTRUParameters.ntruhrss701);
        map13.put(BCObjectIdentifiers.ntruhrss1373, NTRUParameters.ntruhrss1373);
        map14.put(FalconParameters.falcon_512, BCObjectIdentifiers.falcon_512);
        map14.put(FalconParameters.falcon_1024, BCObjectIdentifiers.falcon_1024);
        map15.put(BCObjectIdentifiers.falcon_512, FalconParameters.falcon_512);
        map15.put(BCObjectIdentifiers.falcon_1024, FalconParameters.falcon_1024);
        map16.put(KyberParameters.kyber512, BCObjectIdentifiers.kyber512);
        map16.put(KyberParameters.kyber768, BCObjectIdentifiers.kyber768);
        map16.put(KyberParameters.kyber1024, BCObjectIdentifiers.kyber1024);
        map17.put(BCObjectIdentifiers.kyber512, KyberParameters.kyber512);
        map17.put(BCObjectIdentifiers.kyber768, KyberParameters.kyber768);
        map17.put(BCObjectIdentifiers.kyber1024, KyberParameters.kyber1024);
        map18.put(NTRULPRimeParameters.ntrulpr653, BCObjectIdentifiers.ntrulpr653);
        map18.put(NTRULPRimeParameters.ntrulpr761, BCObjectIdentifiers.ntrulpr761);
        map18.put(NTRULPRimeParameters.ntrulpr857, BCObjectIdentifiers.ntrulpr857);
        map18.put(NTRULPRimeParameters.ntrulpr953, BCObjectIdentifiers.ntrulpr953);
        map18.put(NTRULPRimeParameters.ntrulpr1013, BCObjectIdentifiers.ntrulpr1013);
        map18.put(NTRULPRimeParameters.ntrulpr1277, BCObjectIdentifiers.ntrulpr1277);
        map19.put(BCObjectIdentifiers.ntrulpr653, NTRULPRimeParameters.ntrulpr653);
        map19.put(BCObjectIdentifiers.ntrulpr761, NTRULPRimeParameters.ntrulpr761);
        map19.put(BCObjectIdentifiers.ntrulpr857, NTRULPRimeParameters.ntrulpr857);
        map19.put(BCObjectIdentifiers.ntrulpr953, NTRULPRimeParameters.ntrulpr953);
        map19.put(BCObjectIdentifiers.ntrulpr1013, NTRULPRimeParameters.ntrulpr1013);
        map19.put(BCObjectIdentifiers.ntrulpr1277, NTRULPRimeParameters.ntrulpr1277);
        map20.put(SNTRUPrimeParameters.sntrup653, BCObjectIdentifiers.sntrup653);
        map20.put(SNTRUPrimeParameters.sntrup761, BCObjectIdentifiers.sntrup761);
        map20.put(SNTRUPrimeParameters.sntrup857, BCObjectIdentifiers.sntrup857);
        map20.put(SNTRUPrimeParameters.sntrup953, BCObjectIdentifiers.sntrup953);
        map20.put(SNTRUPrimeParameters.sntrup1013, BCObjectIdentifiers.sntrup1013);
        map20.put(SNTRUPrimeParameters.sntrup1277, BCObjectIdentifiers.sntrup1277);
        map21.put(BCObjectIdentifiers.sntrup653, SNTRUPrimeParameters.sntrup653);
        map21.put(BCObjectIdentifiers.sntrup761, SNTRUPrimeParameters.sntrup761);
        map21.put(BCObjectIdentifiers.sntrup857, SNTRUPrimeParameters.sntrup857);
        map21.put(BCObjectIdentifiers.sntrup953, SNTRUPrimeParameters.sntrup953);
        map21.put(BCObjectIdentifiers.sntrup1013, SNTRUPrimeParameters.sntrup1013);
        map21.put(BCObjectIdentifiers.sntrup1277, SNTRUPrimeParameters.sntrup1277);
        map22.put(DilithiumParameters.dilithium2, BCObjectIdentifiers.dilithium2);
        map22.put(DilithiumParameters.dilithium3, BCObjectIdentifiers.dilithium3);
        map22.put(DilithiumParameters.dilithium5, BCObjectIdentifiers.dilithium5);
        map23.put(BCObjectIdentifiers.dilithium2, DilithiumParameters.dilithium2);
        map23.put(BCObjectIdentifiers.dilithium3, DilithiumParameters.dilithium3);
        map23.put(BCObjectIdentifiers.dilithium5, DilithiumParameters.dilithium5);
        map25.put(BCObjectIdentifiers.bike128, BIKEParameters.bike128);
        map25.put(BCObjectIdentifiers.bike192, BIKEParameters.bike192);
        map25.put(BCObjectIdentifiers.bike256, BIKEParameters.bike256);
        map24.put(BIKEParameters.bike128, BCObjectIdentifiers.bike128);
        map24.put(BIKEParameters.bike192, BCObjectIdentifiers.bike192);
        map24.put(BIKEParameters.bike256, BCObjectIdentifiers.bike256);
        map27.put(BCObjectIdentifiers.hqc128, HQCParameters.hqc128);
        map27.put(BCObjectIdentifiers.hqc192, HQCParameters.hqc192);
        map27.put(BCObjectIdentifiers.hqc256, HQCParameters.hqc256);
        map26.put(HQCParameters.hqc128, BCObjectIdentifiers.hqc128);
        map26.put(HQCParameters.hqc192, BCObjectIdentifiers.hqc192);
        map26.put(HQCParameters.hqc256, BCObjectIdentifiers.hqc256);
        map29.put(BCObjectIdentifiers.rainbow_III_classic, RainbowParameters.rainbowIIIclassic);
        map29.put(BCObjectIdentifiers.rainbow_III_circumzenithal, RainbowParameters.rainbowIIIcircumzenithal);
        map29.put(BCObjectIdentifiers.rainbow_III_compressed, RainbowParameters.rainbowIIIcompressed);
        map29.put(BCObjectIdentifiers.rainbow_V_classic, RainbowParameters.rainbowVclassic);
        map29.put(BCObjectIdentifiers.rainbow_V_circumzenithal, RainbowParameters.rainbowVcircumzenithal);
        map29.put(BCObjectIdentifiers.rainbow_V_compressed, RainbowParameters.rainbowVcompressed);
        map28.put(RainbowParameters.rainbowIIIclassic, BCObjectIdentifiers.rainbow_III_classic);
        map28.put(RainbowParameters.rainbowIIIcircumzenithal, BCObjectIdentifiers.rainbow_III_circumzenithal);
        map28.put(RainbowParameters.rainbowIIIcompressed, BCObjectIdentifiers.rainbow_III_compressed);
        map28.put(RainbowParameters.rainbowVclassic, BCObjectIdentifiers.rainbow_V_classic);
        map28.put(RainbowParameters.rainbowVcircumzenithal, BCObjectIdentifiers.rainbow_V_circumzenithal);
        map28.put(RainbowParameters.rainbowVcompressed, BCObjectIdentifiers.rainbow_V_compressed);
        map10.put(SPHINCSPlusParameters.sha2_128s_robust, BCObjectIdentifiers.sphincsPlus_sha2_128s_r3);
        map10.put(SPHINCSPlusParameters.sha2_128f_robust, BCObjectIdentifiers.sphincsPlus_sha2_128f_r3);
        map10.put(SPHINCSPlusParameters.shake_128s_robust, BCObjectIdentifiers.sphincsPlus_shake_128s_r3);
        map10.put(SPHINCSPlusParameters.shake_128f_robust, BCObjectIdentifiers.sphincsPlus_shake_128f_r3);
        map10.put(SPHINCSPlusParameters.haraka_128s, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3);
        map10.put(SPHINCSPlusParameters.haraka_128f, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3);
        map10.put(SPHINCSPlusParameters.sha2_192s_robust, BCObjectIdentifiers.sphincsPlus_sha2_192s_r3);
        map10.put(SPHINCSPlusParameters.sha2_192f_robust, BCObjectIdentifiers.sphincsPlus_sha2_192f_r3);
        map10.put(SPHINCSPlusParameters.shake_192s_robust, BCObjectIdentifiers.sphincsPlus_shake_192s_r3);
        map10.put(SPHINCSPlusParameters.shake_192f_robust, BCObjectIdentifiers.sphincsPlus_shake_192f_r3);
        map10.put(SPHINCSPlusParameters.haraka_192s, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3);
        map10.put(SPHINCSPlusParameters.haraka_192f, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3);
        map10.put(SPHINCSPlusParameters.sha2_256s_robust, BCObjectIdentifiers.sphincsPlus_sha2_256s_r3);
        map10.put(SPHINCSPlusParameters.sha2_256f_robust, BCObjectIdentifiers.sphincsPlus_sha2_256f_r3);
        map10.put(SPHINCSPlusParameters.shake_256s_robust, BCObjectIdentifiers.sphincsPlus_shake_256s_r3);
        map10.put(SPHINCSPlusParameters.shake_256f_robust, BCObjectIdentifiers.sphincsPlus_shake_256f_r3);
        map10.put(SPHINCSPlusParameters.haraka_256s, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3);
        map10.put(SPHINCSPlusParameters.haraka_256f, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3);
        map10.put(SPHINCSPlusParameters.haraka_128s_simple, BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple);
        map10.put(SPHINCSPlusParameters.haraka_128f_simple, BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple);
        map10.put(SPHINCSPlusParameters.haraka_192s_simple, BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple);
        map10.put(SPHINCSPlusParameters.haraka_192f_simple, BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple);
        map10.put(SPHINCSPlusParameters.haraka_256s_simple, BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple);
        map10.put(SPHINCSPlusParameters.haraka_256f_simple, BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple);
        map10.put(SPHINCSPlusParameters.sha2_128s, BCObjectIdentifiers.sphincsPlus_sha2_128s);
        map10.put(SPHINCSPlusParameters.sha2_128f, BCObjectIdentifiers.sphincsPlus_sha2_128f);
        map10.put(SPHINCSPlusParameters.shake_128s, BCObjectIdentifiers.sphincsPlus_shake_128s);
        map10.put(SPHINCSPlusParameters.shake_128f, BCObjectIdentifiers.sphincsPlus_shake_128f);
        map10.put(SPHINCSPlusParameters.sha2_192s, BCObjectIdentifiers.sphincsPlus_sha2_192s);
        map10.put(SPHINCSPlusParameters.sha2_192f, BCObjectIdentifiers.sphincsPlus_sha2_192f);
        map10.put(SPHINCSPlusParameters.shake_192s, BCObjectIdentifiers.sphincsPlus_shake_192s);
        map10.put(SPHINCSPlusParameters.shake_192f, BCObjectIdentifiers.sphincsPlus_shake_192f);
        map10.put(SPHINCSPlusParameters.sha2_256s, BCObjectIdentifiers.sphincsPlus_sha2_256s);
        map10.put(SPHINCSPlusParameters.sha2_256f, BCObjectIdentifiers.sphincsPlus_sha2_256f);
        map10.put(SPHINCSPlusParameters.shake_256s, BCObjectIdentifiers.sphincsPlus_shake_256s);
        map10.put(SPHINCSPlusParameters.shake_256f, BCObjectIdentifiers.sphincsPlus_shake_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128s, SPHINCSPlusParameters.sha2_128s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128f, SPHINCSPlusParameters.sha2_128f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128s, SPHINCSPlusParameters.shake_128s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128f, SPHINCSPlusParameters.shake_128f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192s, SPHINCSPlusParameters.sha2_192s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192f, SPHINCSPlusParameters.sha2_192f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192s, SPHINCSPlusParameters.shake_192s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192f, SPHINCSPlusParameters.shake_192f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256s, SPHINCSPlusParameters.sha2_256s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256f, SPHINCSPlusParameters.sha2_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256s, SPHINCSPlusParameters.shake_256s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256f, SPHINCSPlusParameters.shake_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3, SPHINCSPlusParameters.sha2_128s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3, SPHINCSPlusParameters.sha2_128f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3, SPHINCSPlusParameters.shake_128s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3, SPHINCSPlusParameters.shake_128f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3, SPHINCSPlusParameters.haraka_128s);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3, SPHINCSPlusParameters.haraka_128f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3, SPHINCSPlusParameters.sha2_192s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3, SPHINCSPlusParameters.sha2_192f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3, SPHINCSPlusParameters.shake_192s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3, SPHINCSPlusParameters.shake_192f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3, SPHINCSPlusParameters.haraka_192s);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3, SPHINCSPlusParameters.haraka_192f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3, SPHINCSPlusParameters.sha2_256s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3, SPHINCSPlusParameters.sha2_256f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3, SPHINCSPlusParameters.shake_256s_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3, SPHINCSPlusParameters.shake_256f_robust);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3, SPHINCSPlusParameters.haraka_256s);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3, SPHINCSPlusParameters.haraka_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128s_r3_simple, SPHINCSPlusParameters.sha2_128s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_128f_r3_simple, SPHINCSPlusParameters.sha2_128f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128s_r3_simple, SPHINCSPlusParameters.shake_128s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_128f_r3_simple, SPHINCSPlusParameters.shake_128f);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_128s_r3_simple, SPHINCSPlusParameters.haraka_128s_simple);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_128f_r3_simple, SPHINCSPlusParameters.haraka_128f_simple);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192s_r3_simple, SPHINCSPlusParameters.sha2_192s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_192f_r3_simple, SPHINCSPlusParameters.sha2_192f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192s_r3_simple, SPHINCSPlusParameters.shake_192s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_192f_r3_simple, SPHINCSPlusParameters.shake_192f);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_192s_r3_simple, SPHINCSPlusParameters.haraka_192s_simple);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_192f_r3_simple, SPHINCSPlusParameters.haraka_192f_simple);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256s_r3_simple, SPHINCSPlusParameters.sha2_256s);
        map11.put(BCObjectIdentifiers.sphincsPlus_sha2_256f_r3_simple, SPHINCSPlusParameters.sha2_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256s_r3_simple, SPHINCSPlusParameters.shake_256s);
        map11.put(BCObjectIdentifiers.sphincsPlus_shake_256f_r3_simple, SPHINCSPlusParameters.shake_256f);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_256s_r3_simple, SPHINCSPlusParameters.haraka_256s_simple);
        map11.put(BCObjectIdentifiers.sphincsPlus_haraka_256f_r3_simple, SPHINCSPlusParameters.haraka_256f_simple);
    }

    Utils() {
    }

    static ASN1ObjectIdentifier bikeOidLookup(BIKEParameters bIKEParameters) {
        return (ASN1ObjectIdentifier) bikeOids.get(bIKEParameters);
    }

    static BIKEParameters bikeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (BIKEParameters) bikeParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier dilithiumOidLookup(DilithiumParameters dilithiumParameters) {
        return (ASN1ObjectIdentifier) dilithiumOids.get(dilithiumParameters);
    }

    static DilithiumParameters dilithiumParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (DilithiumParameters) dilithiumParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier falconOidLookup(FalconParameters falconParameters) {
        return (ASN1ObjectIdentifier) falconOids.get(falconParameters);
    }

    static FalconParameters falconParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FalconParameters) falconParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier frodoOidLookup(FrodoParameters frodoParameters) {
        return (ASN1ObjectIdentifier) frodoOids.get(frodoParameters);
    }

    static FrodoParameters frodoParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FrodoParameters) frodoParams.get(aSN1ObjectIdentifier);
    }

    public static AlgorithmIdentifier getAlgorithmIdentifier(String str) {
        if (str.equals("SHA-1")) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (str.equals("SHA-224")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
        }
        if (str.equals("SHA-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        }
        if (str.equals("SHA-384")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        if (str.equals("SHA-512")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }

    static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) OIWObjectIdentifiers.idSHA1)) {
            return "SHA-1";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha224)) {
            return "SHA-224";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return "SHA-256";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha384)) {
            return "SHA-384";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return "SHA-512";
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier hqcOidLookup(HQCParameters hQCParameters) {
        return (ASN1ObjectIdentifier) hqcOids.get(hQCParameters);
    }

    static HQCParameters hqcParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (HQCParameters) hqcParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier kyberOidLookup(KyberParameters kyberParameters) {
        return (ASN1ObjectIdentifier) kyberOids.get(kyberParameters);
    }

    static KyberParameters kyberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (KyberParameters) kyberParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier mcElieceOidLookup(CMCEParameters cMCEParameters) {
        return (ASN1ObjectIdentifier) mcElieceOids.get(cMCEParameters);
    }

    static CMCEParameters mcElieceParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (CMCEParameters) mcElieceParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier ntruOidLookup(NTRUParameters nTRUParameters) {
        return (ASN1ObjectIdentifier) ntruOids.get(nTRUParameters);
    }

    static NTRUParameters ntruParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRUParameters) ntruParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier ntrulprimeOidLookup(NTRULPRimeParameters nTRULPRimeParameters) {
        return (ASN1ObjectIdentifier) ntruprimeOids.get(nTRULPRimeParameters);
    }

    static NTRULPRimeParameters ntrulprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRULPRimeParameters) ntruprimeParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier picnicOidLookup(PicnicParameters picnicParameters) {
        return (ASN1ObjectIdentifier) picnicOids.get(picnicParameters);
    }

    static PicnicParameters picnicParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (PicnicParameters) picnicParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier qTeslaLookupAlgID(int i) {
        if (i == 5) {
            return AlgID_qTESLA_p_I;
        }
        if (i == 6) {
            return AlgID_qTESLA_p_III;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    static int qTeslaLookupSecurityCategory(AlgorithmIdentifier algorithmIdentifier) {
        return ((Integer) categories.get(algorithmIdentifier.getAlgorithm())).intValue();
    }

    static ASN1ObjectIdentifier rainbowOidLookup(RainbowParameters rainbowParameters) {
        return (ASN1ObjectIdentifier) rainbowOids.get(rainbowParameters);
    }

    static RainbowParameters rainbowParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (RainbowParameters) rainbowParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier saberOidLookup(SABERParameters sABERParameters) {
        return (ASN1ObjectIdentifier) saberOids.get(sABERParameters);
    }

    static SABERParameters saberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SABERParameters) saberParams.get(aSN1ObjectIdentifier);
    }

    static ASN1ObjectIdentifier sntruprimeOidLookup(SNTRUPrimeParameters sNTRUPrimeParameters) {
        return (ASN1ObjectIdentifier) sntruprimeOids.get(sNTRUPrimeParameters);
    }

    static SNTRUPrimeParameters sntruprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SNTRUPrimeParameters) sntruprimeParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier sphincs256LookupTreeAlgID(String str) {
        if (str.equals("SHA3-256")) {
            return SPHINCS_SHA3_256;
        }
        if (str.equals(SPHINCSKeyParameters.SHA512_256)) {
            return SPHINCS_SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }

    static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams sPHINCS256KeyParams) {
        AlgorithmIdentifier treeDigest = sPHINCS256KeyParams.getTreeDigest();
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA3_256.getAlgorithm())) {
            return "SHA3-256";
        }
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA512_256.getAlgorithm())) {
            return SPHINCSKeyParameters.SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + treeDigest.getAlgorithm());
    }

    static ASN1ObjectIdentifier sphincsPlusOidLookup(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return (ASN1ObjectIdentifier) sphincsPlusOids.get(sPHINCSPlusParameters);
    }

    static SPHINCSPlusParameters sphincsPlusParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SPHINCSPlusParameters) sphincsPlusParams.get(aSN1ObjectIdentifier);
    }

    static AlgorithmIdentifier xmssLookupTreeAlgID(String str) {
        if (str.equals("SHA-256")) {
            return XMSS_SHA256;
        }
        if (str.equals("SHA-512")) {
            return XMSS_SHA512;
        }
        if (str.equals("SHAKE128")) {
            return XMSS_SHAKE128;
        }
        if (str.equals("SHAKE256")) {
            return XMSS_SHAKE256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }
}
