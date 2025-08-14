package org.spongycastle.cms;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private List signerInfs = new ArrayList();

    public CMSSignedData generate(CMSTypedData cMSTypedData) throws CMSException {
        return generate(cMSTypedData, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.spongycastle.cms.CMSSignedData generate(org.spongycastle.cms.CMSTypedData r12, boolean r13) throws java.io.IOException, org.spongycastle.cms.CMSException {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.cms.CMSSignedDataGenerator.generate(org.spongycastle.cms.CMSTypedData, boolean):org.spongycastle.cms.CMSSignedData");
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) throws CMSException {
        return generate(new CMSProcessableByteArray(null, signerInformation.getSignature()), false).getSignerInfos();
    }
}
