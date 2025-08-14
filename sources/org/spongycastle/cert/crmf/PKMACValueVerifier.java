package org.spongycastle.cert.crmf;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.cmp.PBMParameter;
import org.spongycastle.asn1.crmf.PKMACValue;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.operator.MacCalculator;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
class PKMACValueVerifier {
    private final PKMACBuilder builder;

    public PKMACValueVerifier(PKMACBuilder pKMACBuilder) {
        this.builder = pKMACBuilder;
    }

    public boolean isValid(PKMACValue pKMACValue, char[] cArr, SubjectPublicKeyInfo subjectPublicKeyInfo) throws CRMFException, IOException {
        this.builder.setParameters(PBMParameter.getInstance(pKMACValue.getAlgId().getParameters()));
        MacCalculator macCalculatorBuild = this.builder.build(cArr);
        OutputStream outputStream = macCalculatorBuild.getOutputStream();
        try {
            outputStream.write(subjectPublicKeyInfo.getEncoded("DER"));
            outputStream.close();
            return Arrays.areEqual(macCalculatorBuild.getMac(), pKMACValue.getValue().getBytes());
        } catch (IOException e) {
            throw new CRMFException("exception encoding mac input: " + e.getMessage(), e);
        }
    }
}
