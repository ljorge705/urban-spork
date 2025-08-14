package org.spongycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class CMSAuthenticatedGenerator extends CMSEnvelopedGenerator {
    protected CMSAttributeTableGenerator authGen;
    protected CMSAttributeTableGenerator unauthGen;

    public void setAuthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.authGen = cMSAttributeTableGenerator;
    }

    public void setUnauthenticatedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.unauthGen = cMSAttributeTableGenerator;
    }

    protected Map getBaseParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        HashMap map = new HashMap();
        map.put(CMSAttributeTableGenerator.CONTENT_TYPE, aSN1ObjectIdentifier);
        map.put(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER, algorithmIdentifier);
        map.put(CMSAttributeTableGenerator.DIGEST, Arrays.clone(bArr));
        map.put(CMSAttributeTableGenerator.MAC_ALGORITHM_IDENTIFIER, algorithmIdentifier2);
        return map;
    }
}
