package org.spongycastle.cms;

/* loaded from: classes4.dex */
public class CMSEncryptedGenerator {
    protected CMSAttributeTableGenerator unprotectedAttributeGenerator = null;

    public void setUnprotectedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unprotectedAttributeGenerator = cMSAttributeTableGenerator;
    }

    protected CMSEncryptedGenerator() {
    }
}
