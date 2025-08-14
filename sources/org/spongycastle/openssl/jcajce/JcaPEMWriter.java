package org.spongycastle.openssl.jcajce;

import java.io.IOException;
import java.io.Writer;
import org.spongycastle.openssl.PEMEncryptor;
import org.spongycastle.util.io.pem.PemGenerationException;
import org.spongycastle.util.io.pem.PemObjectGenerator;
import org.spongycastle.util.io.pem.PemWriter;

/* loaded from: classes7.dex */
public class JcaPEMWriter extends PemWriter {
    public JcaPEMWriter(Writer writer) {
        super(writer);
    }

    public void writeObject(Object obj) throws IOException {
        writeObject(obj, null);
    }

    public void writeObject(Object obj, PEMEncryptor pEMEncryptor) throws IOException {
        try {
            super.writeObject((PemObjectGenerator) new JcaMiscPEMGenerator(obj, pEMEncryptor));
        } catch (PemGenerationException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }

    @Override // org.spongycastle.util.io.pem.PemWriter
    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        super.writeObject(pemObjectGenerator);
    }
}
