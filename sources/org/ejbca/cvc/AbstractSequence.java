package org.ejbca.cvc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public abstract class AbstractSequence extends CVCObject {
    private static final long serialVersionUID = 1;
    private final List<CVCTagEnum> allowedFields;
    private final Map<CVCTagEnum, CVCObject> subfields;

    protected abstract CVCTagEnum[] getAllowedFields();

    AbstractSequence(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
        this.subfields = new HashMap();
        this.allowedFields = Arrays.asList(getAllowedFields());
    }

    void addSubfield(CVCObject cVCObject) throws ConstructionException {
        addSubfield(cVCObject, false);
    }

    void addSubfield(CVCObject cVCObject, boolean z) throws ConstructionException {
        if (cVCObject != null) {
            if (this.allowedFields.contains(cVCObject.getTag())) {
                if (this.subfields.containsKey(cVCObject.getTag()) && !z) {
                    throw new ConstructionException("Field " + cVCObject.getTag() + " has already been added to " + getClass().getName());
                }
                cVCObject.setParent(this);
                this.subfields.put(cVCObject.getTag(), cVCObject);
                return;
            }
            throw new ConstructionException("Field " + cVCObject.getTag() + " not allowed in " + getClass().getName());
        }
    }

    CVCObject getSubfield(CVCTagEnum cVCTagEnum) throws NoSuchFieldException {
        CVCObject cVCObject = this.subfields.get(cVCTagEnum);
        if (cVCObject != null) {
            return cVCObject;
        }
        throw new NoSuchFieldException("Could not find subfield " + cVCTagEnum);
    }

    CVCObject getOptionalSubfield(CVCTagEnum cVCTagEnum) {
        return this.subfields.get(cVCTagEnum);
    }

    protected Collection<CVCObject> getSubfields() {
        return this.subfields.values();
    }

    @Override // org.ejbca.cvc.CVCObject
    public int encode(DataOutputStream dataOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
        Iterator<CVCObject> it = getEncodableFields().iterator();
        int iEncode = 0;
        while (it.hasNext()) {
            iEncode += it.next().encode(dataOutputStream2);
        }
        dataOutputStream2.close();
        int value = getTag().getValue();
        int size = dataOutputStream.size();
        dataOutputStream.write(toByteArray(Integer.valueOf(value)));
        dataOutputStream.write(encodeLength(iEncode));
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return dataOutputStream.size() - size;
    }

    protected List<CVCObject> getEncodableFields() {
        return getOrderedSubfields();
    }

    public byte[] getDEREncoded() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                encode(new DataOutputStream(byteArrayOutputStream));
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
            th = th3;
        }
    }

    @Override // org.ejbca.cvc.CVCObject
    public String getAsText(String str) {
        return getAsText(str, true);
    }

    @Override // org.ejbca.cvc.CVCObject
    public String getAsText(String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.getAsText(str, z));
        for (CVCObject cVCObject : getOrderedSubfields()) {
            stringBuffer.append(NEWLINE);
            stringBuffer.append(cVCObject.getAsText(str + "   ", z));
        }
        return stringBuffer.toString();
    }

    protected List<CVCObject> getOrderedSubfields() {
        ArrayList arrayList = new ArrayList();
        Iterator<CVCTagEnum> it = this.allowedFields.iterator();
        while (it.hasNext()) {
            CVCObject cVCObject = this.subfields.get(it.next());
            if (cVCObject != null) {
                arrayList.add(cVCObject);
            }
        }
        return arrayList;
    }
}
