package org.spongycastle.cms;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cms.Attribute;
import org.spongycastle.asn1.cms.AttributeTable;
import org.spongycastle.asn1.cms.CMSAlgorithmProtection;
import org.spongycastle.asn1.cms.CMSAttributes;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes4.dex */
public class DefaultAuthenticatedAttributeTableGenerator implements CMSAttributeTableGenerator {
    private final Hashtable table;

    public DefaultAuthenticatedAttributeTableGenerator() {
        this.table = new Hashtable();
    }

    public DefaultAuthenticatedAttributeTableGenerator(AttributeTable attributeTable) {
        if (attributeTable != null) {
            this.table = attributeTable.toHashtable();
        } else {
            this.table = new Hashtable();
        }
    }

    protected Hashtable createStandardAttributeTable(Map map) {
        Hashtable hashtable = new Hashtable();
        Enumeration enumerationKeys = this.table.keys();
        while (enumerationKeys.hasMoreElements()) {
            Object objNextElement = enumerationKeys.nextElement();
            hashtable.put(objNextElement, this.table.get(objNextElement));
        }
        if (!hashtable.containsKey(CMSAttributes.contentType)) {
            Attribute attribute = new Attribute(CMSAttributes.contentType, new DERSet(ASN1ObjectIdentifier.getInstance(map.get(CMSAttributeTableGenerator.CONTENT_TYPE))));
            hashtable.put(attribute.getAttrType(), attribute);
        }
        if (!hashtable.containsKey(CMSAttributes.messageDigest)) {
            Attribute attribute2 = new Attribute(CMSAttributes.messageDigest, new DERSet(new DEROctetString((byte[]) map.get(CMSAttributeTableGenerator.DIGEST))));
            hashtable.put(attribute2.getAttrType(), attribute2);
        }
        if (!hashtable.contains(CMSAttributes.cmsAlgorithmProtect)) {
            Attribute attribute3 = new Attribute(CMSAttributes.cmsAlgorithmProtect, new DERSet(new CMSAlgorithmProtection((AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER), 2, (AlgorithmIdentifier) map.get(CMSAttributeTableGenerator.MAC_ALGORITHM_IDENTIFIER))));
            hashtable.put(attribute3.getAttrType(), attribute3);
        }
        return hashtable;
    }

    @Override // org.spongycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return new AttributeTable(createStandardAttributeTable(map));
    }
}
