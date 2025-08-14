package org.ejbca.cvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public class CVCertificateExtensions extends AbstractArray {
    private static final long serialVersionUID = 1;

    @Override // org.ejbca.cvc.AbstractArray
    protected CVCTagEnum getAllowedField() {
        return CVCTagEnum.DISCRETIONARY_DATA_TEMPLATE;
    }

    CVCertificateExtensions() {
        super(CVCTagEnum.CERTIFICATE_EXTENSIONS);
    }

    public CVCertificateExtensions(Collection<CVCDiscretionaryDataTemplate> collection) throws ConstructionException {
        this();
        Iterator<CVCDiscretionaryDataTemplate> it = collection.iterator();
        while (it.hasNext()) {
            addSubfield(it.next());
        }
    }

    public List<CVCDiscretionaryDataTemplate> getExtensions() {
        ArrayList arrayList = new ArrayList();
        Iterator<CVCObject> it = getEncodableFields().iterator();
        while (it.hasNext()) {
            arrayList.add((CVCDiscretionaryDataTemplate) it.next());
        }
        return arrayList;
    }
}
