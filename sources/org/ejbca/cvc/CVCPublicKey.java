package org.ejbca.cvc;

import java.io.IOException;
import java.security.PublicKey;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public abstract class CVCPublicKey extends AbstractSequence implements PublicKey {
    private static final long serialVersionUID = 5330644668163139836L;

    CVCPublicKey() {
        super(CVCTagEnum.PUBLIC_KEY);
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return getDEREncoded();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OIDField getObjectIdentifier() throws NoSuchFieldException {
        return (OIDField) getSubfield(CVCTagEnum.OID);
    }

    public void setObjectIdentifier(OIDField oIDField) throws ConstructionException {
        addSubfield(oIDField, true);
    }
}
