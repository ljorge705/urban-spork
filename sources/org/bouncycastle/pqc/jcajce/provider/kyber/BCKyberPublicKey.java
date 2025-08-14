package org.bouncycastle.pqc.jcajce.provider.kyber;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.PublicKeyFactory;
import org.bouncycastle.pqc.jcajce.interfaces.KyberPublicKey;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.pqc.jcajce.spec.KyberParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes4.dex */
public class BCKyberPublicKey implements KyberPublicKey {
    private static final long serialVersionUID = 1;
    private transient String algorithm;
    private transient byte[] encoding;
    private transient KyberPublicKeyParameters params;

    public BCKyberPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        init(subjectPublicKeyInfo);
    }

    public BCKyberPublicKey(KyberPublicKeyParameters kyberPublicKeyParameters) {
        init(kyberPublicKeyParameters);
    }

    private void init(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        init((KyberPublicKeyParameters) PublicKeyFactory.createKey(subjectPublicKeyInfo));
    }

    private void init(KyberPublicKeyParameters kyberPublicKeyParameters) {
        this.params = kyberPublicKeyParameters;
        this.algorithm = Strings.toUpperCase(kyberPublicKeyParameters.getParameters().getName());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        init(SubjectPublicKeyInfo.getInstance((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCKyberPublicKey) {
            return Arrays.areEqual(getEncoded(), ((BCKyberPublicKey) obj).getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        if (this.encoding == null) {
            this.encoding = KeyUtil.getEncodedSubjectPublicKeyInfo(this.params);
        }
        return Arrays.clone(this.encoding);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    KyberPublicKeyParameters getKeyParams() {
        return this.params;
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.KyberKey
    public KyberParameterSpec getParameterSpec() {
        return KyberParameterSpec.fromName(this.params.getParameters().getName());
    }

    public int hashCode() {
        return Arrays.hashCode(getEncoded());
    }
}
