package org.ejbca.cvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public abstract class AbstractArray extends AbstractSequence {
    private static final long serialVersionUID = 1;
    private final CVCTagEnum allowedField;
    private final List<CVCObject> subfields;

    protected abstract CVCTagEnum getAllowedField();

    AbstractArray(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
        this.subfields = new ArrayList();
        this.allowedField = getAllowedField();
    }

    @Override // org.ejbca.cvc.AbstractSequence
    void addSubfield(CVCObject cVCObject) throws ConstructionException {
        if (cVCObject != null) {
            if (this.allowedField != cVCObject.getTag()) {
                throw new ConstructionException("Field " + cVCObject.getTag() + " not allowed in " + getClass().getName());
            }
            cVCObject.setParent(this);
            this.subfields.add(cVCObject);
        }
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected final CVCTagEnum[] getAllowedFields() {
        return new CVCTagEnum[]{getAllowedField()};
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected final CVCObject getSubfield(CVCTagEnum cVCTagEnum) throws NoSuchFieldException {
        throw new IllegalStateException("Not applicable to AbstractArray");
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected final CVCObject getOptionalSubfield(CVCTagEnum cVCTagEnum) {
        throw new IllegalStateException("Not applicable to AbstractArray");
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected Collection<CVCObject> getSubfields() {
        return new ArrayList(this.subfields);
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected List<CVCObject> getOrderedSubfields() {
        return new ArrayList(this.subfields);
    }
}
