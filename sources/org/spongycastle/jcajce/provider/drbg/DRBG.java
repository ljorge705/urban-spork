package org.spongycastle.jcajce.provider.drbg;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.crypto.prng.EntropySourceProvider;
import org.spongycastle.crypto.prng.SP800SecureRandom;
import org.spongycastle.crypto.prng.SP800SecureRandomBuilder;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;
import org.spongycastle.util.Strings;

/* loaded from: classes7.dex */
public class DRBG {
    private static final String PREFIX = "org.spongycastle.jcajce.provider.drbg.DRBG";
    private static final String[][] initialEntropySourceNames = {new String[]{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, new String[]{"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, new String[]{"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, new String[]{"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};
    private static final Object[] initialEntropySourceAndSpi = findSource();

    private static final Object[] findSource() {
        int i = 0;
        while (true) {
            String[][] strArr = initialEntropySourceNames;
            if (i >= strArr.length) {
                return null;
            }
            String[] strArr2 = strArr[i];
            try {
                return new Object[]{Class.forName(strArr2[0]).newInstance(), Class.forName(strArr2[1]).newInstance()};
            } catch (Throwable unused) {
                i++;
            }
        }
    }

    private static class CoreSecureRandom extends SecureRandom {
        CoreSecureRandom() {
            super((SecureRandomSpi) DRBG.initialEntropySourceAndSpi[1], (Provider) DRBG.initialEntropySourceAndSpi[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SecureRandom createInitialEntropySource() {
        if (initialEntropySourceAndSpi != null) {
            return new CoreSecureRandom();
        }
        return new SecureRandom();
    }

    private static EntropySourceProvider createEntropySource() {
        final String property = System.getProperty("org.spongycastle.drbg.entropysource");
        return (EntropySourceProvider) AccessController.doPrivileged(new PrivilegedAction<EntropySourceProvider>() { // from class: org.spongycastle.jcajce.provider.drbg.DRBG.1
            @Override // java.security.PrivilegedAction
            public EntropySourceProvider run() {
                try {
                    return (EntropySourceProvider) ClassUtil.loadClass(DRBG.class, property).newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("entropy source " + property + " not created: " + e.getMessage(), e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SecureRandom createBaseRandom(boolean z) {
        byte[] bArrGenerateNonceIVPersonalizationString;
        byte[] bArrGenerateNonceIVPersonalizationString2;
        if (System.getProperty("org.spongycastle.drbg.entropysource") != null) {
            EntropySourceProvider entropySourceProviderCreateEntropySource = createEntropySource();
            EntropySource entropySource = entropySourceProviderCreateEntropySource.get(128);
            if (z) {
                bArrGenerateNonceIVPersonalizationString2 = generateDefaultPersonalizationString(entropySource.getEntropy());
            } else {
                bArrGenerateNonceIVPersonalizationString2 = generateNonceIVPersonalizationString(entropySource.getEntropy());
            }
            return new SP800SecureRandomBuilder(entropySourceProviderCreateEntropySource).setPersonalizationString(bArrGenerateNonceIVPersonalizationString2).buildHash(new SHA512Digest(), Arrays.concatenate(entropySource.getEntropy(), entropySource.getEntropy()), z);
        }
        HybridSecureRandom hybridSecureRandom = new HybridSecureRandom();
        if (z) {
            bArrGenerateNonceIVPersonalizationString = generateDefaultPersonalizationString(hybridSecureRandom.generateSeed(16));
        } else {
            bArrGenerateNonceIVPersonalizationString = generateNonceIVPersonalizationString(hybridSecureRandom.generateSeed(16));
        }
        return new SP800SecureRandomBuilder(hybridSecureRandom, true).setPersonalizationString(bArrGenerateNonceIVPersonalizationString).buildHash(new SHA512Digest(), hybridSecureRandom.generateSeed(32), z);
    }

    public static class Default extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(true);

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }
    }

    public static class NonceAndIV extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(false);

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }
    }

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecureRandom.DEFAULT", DRBG.PREFIX + "$Default");
            configurableProvider.addAlgorithm("SecureRandom.NONCEANDIV", DRBG.PREFIX + "$NonceAndIV");
        }
    }

    private static byte[] generateDefaultPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Default"), bArr, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
    }

    private static byte[] generateNonceIVPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Nonce"), bArr, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
    }

    private static class HybridSecureRandom extends SecureRandom {
        private final SecureRandom baseRandom;
        private final SP800SecureRandom drbg;
        private final AtomicInteger samples;
        private final AtomicBoolean seedAvailable;

        HybridSecureRandom() {
            super(null, null);
            this.seedAvailable = new AtomicBoolean(false);
            this.samples = new AtomicInteger(0);
            SecureRandom secureRandomCreateInitialEntropySource = DRBG.createInitialEntropySource();
            this.baseRandom = secureRandomCreateInitialEntropySource;
            this.drbg = new SP800SecureRandomBuilder(new EntropySourceProvider() { // from class: org.spongycastle.jcajce.provider.drbg.DRBG.HybridSecureRandom.1
                @Override // org.spongycastle.crypto.prng.EntropySourceProvider
                public EntropySource get(int i) {
                    return HybridSecureRandom.this.new SignallingEntropySource(i);
                }
            }).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), secureRandomCreateInitialEntropySource.generateSeed(32), false);
        }

        @Override // java.security.SecureRandom
        public void setSeed(byte[] bArr) {
            SP800SecureRandom sP800SecureRandom = this.drbg;
            if (sP800SecureRandom != null) {
                sP800SecureRandom.setSeed(bArr);
            }
        }

        @Override // java.security.SecureRandom, java.util.Random
        public void setSeed(long j) {
            SP800SecureRandom sP800SecureRandom = this.drbg;
            if (sP800SecureRandom != null) {
                sP800SecureRandom.setSeed(j);
            }
        }

        @Override // java.security.SecureRandom
        public byte[] generateSeed(int i) {
            byte[] bArr = new byte[i];
            if (this.samples.getAndIncrement() > 20 && this.seedAvailable.getAndSet(false)) {
                this.samples.set(0);
                this.drbg.reseed(null);
            }
            this.drbg.nextBytes(bArr);
            return bArr;
        }

        private class SignallingEntropySource implements EntropySource {
            private final int byteLength;
            private final AtomicReference entropy = new AtomicReference();
            private final AtomicBoolean scheduled = new AtomicBoolean(false);

            @Override // org.spongycastle.crypto.prng.EntropySource
            public int entropySize() {
                return this.byteLength * 8;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public boolean isPredictionResistant() {
                return true;
            }

            SignallingEntropySource(int i) {
                this.byteLength = (i + 7) / 8;
            }

            @Override // org.spongycastle.crypto.prng.EntropySource
            public byte[] getEntropy() {
                byte[] bArrGenerateSeed = (byte[]) this.entropy.getAndSet(null);
                if (bArrGenerateSeed == null || bArrGenerateSeed.length != this.byteLength) {
                    bArrGenerateSeed = HybridSecureRandom.this.baseRandom.generateSeed(this.byteLength);
                } else {
                    this.scheduled.set(false);
                }
                if (!this.scheduled.getAndSet(true)) {
                    new Thread(new EntropyGatherer(this.byteLength)).start();
                }
                return bArrGenerateSeed;
            }

            private class EntropyGatherer implements Runnable {
                private final int numBytes;

                EntropyGatherer(int i) {
                    this.numBytes = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    SignallingEntropySource.this.entropy.set(HybridSecureRandom.this.baseRandom.generateSeed(this.numBytes));
                    HybridSecureRandom.this.seedAvailable.set(true);
                }
            }
        }
    }
}
