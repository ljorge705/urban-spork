package org.spongycastle.cms;

import java.util.Map;
import org.spongycastle.asn1.cms.AttributeTable;

/* loaded from: classes4.dex */
public class SimpleAttributeTableGenerator implements CMSAttributeTableGenerator {
    private final AttributeTable attributes;

    @Override // org.spongycastle.cms.CMSAttributeTableGenerator
    public AttributeTable getAttributes(Map map) {
        return this.attributes;
    }

    public SimpleAttributeTableGenerator(AttributeTable attributeTable) {
        this.attributes = attributeTable;
    }
}
