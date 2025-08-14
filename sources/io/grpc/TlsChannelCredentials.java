package io.grpc;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

/* loaded from: classes6.dex */
public final class TlsChannelCredentials extends ChannelCredentials {
    private final byte[] certificateChain;
    private final boolean fakeFeature;
    private final List<KeyManager> keyManagers;
    private final byte[] privateKey;
    private final String privateKeyPassword;
    private final byte[] rootCertificates;
    private final List<TrustManager> trustManagers;

    public enum Feature {
        FAKE,
        MTLS,
        CUSTOM_MANAGERS
    }

    public List<KeyManager> getKeyManagers() {
        return this.keyManagers;
    }

    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public List<TrustManager> getTrustManagers() {
        return this.trustManagers;
    }

    @Override // io.grpc.ChannelCredentials
    public ChannelCredentials withoutBearerTokens() {
        return this;
    }

    public static ChannelCredentials create() {
        return newBuilder().build();
    }

    TlsChannelCredentials(Builder builder) {
        this.fakeFeature = builder.fakeFeature;
        this.certificateChain = builder.certificateChain;
        this.privateKey = builder.privateKey;
        this.privateKeyPassword = builder.privateKeyPassword;
        this.keyManagers = builder.keyManagers;
        this.rootCertificates = builder.rootCertificates;
        this.trustManagers = builder.trustManagers;
    }

    public byte[] getCertificateChain() {
        byte[] bArr = this.certificateChain;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getPrivateKey() {
        byte[] bArr = this.privateKey;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getRootCertificates() {
        byte[] bArr = this.rootCertificates;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public Set<Feature> incomprehensible(Set<Feature> set) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Feature.class);
        if (this.fakeFeature) {
            requiredFeature(set, enumSetNoneOf, Feature.FAKE);
        }
        if (this.rootCertificates != null || this.privateKey != null || this.keyManagers != null) {
            requiredFeature(set, enumSetNoneOf, Feature.MTLS);
        }
        if (this.keyManagers != null || this.trustManagers != null) {
            requiredFeature(set, enumSetNoneOf, Feature.CUSTOM_MANAGERS);
        }
        return Collections.unmodifiableSet(enumSetNoneOf);
    }

    private static void requiredFeature(Set<Feature> set, Set<Feature> set2, Feature feature) {
        if (set.contains(feature)) {
            return;
        }
        set2.add(feature);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private byte[] certificateChain;
        private boolean fakeFeature;
        private List<KeyManager> keyManagers;
        private byte[] privateKey;
        private String privateKeyPassword;
        private byte[] rootCertificates;
        private List<TrustManager> trustManagers;

        private void clearKeyManagers() {
            this.certificateChain = null;
            this.privateKey = null;
            this.privateKeyPassword = null;
            this.keyManagers = null;
        }

        private void clearTrustManagers() {
            this.rootCertificates = null;
            this.trustManagers = null;
        }

        public Builder requireFakeFeature() {
            this.fakeFeature = true;
            return this;
        }

        private Builder() {
        }

        public Builder keyManager(File file, File file2) throws IOException {
            return keyManager(file, file2, (String) null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
        
            throw r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.TlsChannelCredentials.Builder keyManager(java.io.File r2, java.io.File r3, java.lang.String r4) throws java.io.IOException {
            /*
                r1 = this;
                java.io.FileInputStream r0 = new java.io.FileInputStream
                r0.<init>(r2)
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L1a
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L1a
                io.grpc.TlsChannelCredentials$Builder r3 = r1.keyManager(r0, r2, r4)     // Catch: java.lang.Throwable -> L15
                r2.close()     // Catch: java.lang.Throwable -> L1a
                r0.close()
                return r3
            L15:
                r3 = move-exception
                r2.close()     // Catch: java.lang.Throwable -> L1a
                throw r3     // Catch: java.lang.Throwable -> L1a
            L1a:
                r2 = move-exception
                r0.close()
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.TlsChannelCredentials.Builder.keyManager(java.io.File, java.io.File, java.lang.String):io.grpc.TlsChannelCredentials$Builder");
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2) throws IOException {
            return keyManager(inputStream, inputStream2, (String) null);
        }

        public Builder keyManager(InputStream inputStream, InputStream inputStream2, String str) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            byte[] byteArray2 = ByteStreams.toByteArray(inputStream2);
            clearKeyManagers();
            this.certificateChain = byteArray;
            this.privateKey = byteArray2;
            this.privateKeyPassword = str;
            return this;
        }

        public Builder keyManager(KeyManager... keyManagerArr) {
            List<KeyManager> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(keyManagerArr)));
            clearKeyManagers();
            this.keyManagers = listUnmodifiableList;
            return this;
        }

        public Builder trustManager(File file) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return trustManager(fileInputStream);
            } finally {
                fileInputStream.close();
            }
        }

        public Builder trustManager(InputStream inputStream) throws IOException {
            byte[] byteArray = ByteStreams.toByteArray(inputStream);
            clearTrustManagers();
            this.rootCertificates = byteArray;
            return this;
        }

        public Builder trustManager(TrustManager... trustManagerArr) {
            List<TrustManager> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(Arrays.asList(trustManagerArr)));
            clearTrustManagers();
            this.trustManagers = listUnmodifiableList;
            return this;
        }

        public ChannelCredentials build() {
            return new TlsChannelCredentials(this);
        }
    }
}
