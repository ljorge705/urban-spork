package org.spongycastle.tsp.cms;

import java.io.IOException;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.cms.Attributes;
import org.spongycastle.asn1.cms.MetaData;
import org.spongycastle.cms.CMSException;
import org.spongycastle.operator.DigestCalculator;

/* loaded from: classes7.dex */
class MetaDataUtil {
    private final MetaData metaData;

    MetaDataUtil(MetaData metaData) {
        this.metaData = metaData;
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws IOException, CMSException {
        MetaData metaData = this.metaData;
        if (metaData == null || !metaData.isHashProtected()) {
            return;
        }
        try {
            digestCalculator.getOutputStream().write(this.metaData.getEncoded("DER"));
        } catch (IOException e) {
            throw new CMSException("unable to initialise calculator from metaData: " + e.getMessage(), e);
        }
    }

    String getFileName() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getFileName());
        }
        return null;
    }

    String getMediaType() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getMediaType());
        }
        return null;
    }

    Attributes getOtherMetaData() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return metaData.getOtherMetaData();
        }
        return null;
    }

    private String convertString(ASN1String aSN1String) {
        if (aSN1String != null) {
            return aSN1String.toString();
        }
        return null;
    }
}
