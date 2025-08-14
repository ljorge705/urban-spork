package org.spongycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Mac;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSException;
import org.spongycastle.cms.RecipientOperator;
import org.spongycastle.jcajce.io.MacOutputStream;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.MacCalculator;
import org.spongycastle.operator.jcajce.JceGenericKey;

/* loaded from: classes4.dex */
public class JcePasswordAuthenticatedRecipient extends JcePasswordRecipient {
    public JcePasswordAuthenticatedRecipient(char[] cArr) {
        super(cArr);
    }

    @Override // org.spongycastle.cms.PasswordRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) throws CMSException {
        final Key keyExtractSecretKey = extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr, bArr2);
        final Mac macCreateContentMac = this.helper.createContentMac(keyExtractSecretKey, algorithmIdentifier2);
        return new RecipientOperator(new MacCalculator() { // from class: org.spongycastle.cms.jcajce.JcePasswordAuthenticatedRecipient.1
            @Override // org.spongycastle.operator.MacCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // org.spongycastle.operator.MacCalculator
            public GenericKey getKey() {
                return new JceGenericKey(algorithmIdentifier2, keyExtractSecretKey);
            }

            @Override // org.spongycastle.operator.MacCalculator
            public OutputStream getOutputStream() {
                return new MacOutputStream(macCreateContentMac);
            }

            @Override // org.spongycastle.operator.MacCalculator
            public byte[] getMac() {
                return macCreateContentMac.doFinal();
            }
        });
    }
}
