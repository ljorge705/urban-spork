package org.spongycastle.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.util.Iterable;

/* loaded from: classes4.dex */
public class RecipientInformationStore implements Iterable<RecipientInformation> {
    private final List all;
    private final Map table;

    public RecipientInformationStore(RecipientInformation recipientInformation) {
        HashMap map = new HashMap();
        this.table = map;
        ArrayList arrayList = new ArrayList(1);
        this.all = arrayList;
        arrayList.add(recipientInformation);
        map.put(recipientInformation.getRID(), arrayList);
    }

    public RecipientInformationStore(Collection<RecipientInformation> collection) {
        this.table = new HashMap();
        for (RecipientInformation recipientInformation : collection) {
            RecipientId rid = recipientInformation.getRID();
            ArrayList arrayList = (ArrayList) this.table.get(rid);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.table.put(rid, arrayList);
            }
            arrayList.add(recipientInformation);
        }
        this.all = new ArrayList(collection);
    }

    public RecipientInformation get(RecipientId recipientId) {
        Collection<Recipient> recipients = getRecipients(recipientId);
        if (recipients.size() == 0) {
            return null;
        }
        return (RecipientInformation) recipients.iterator().next();
    }

    public int size() {
        return this.all.size();
    }

    public Collection<RecipientInformation> getRecipients() {
        return new ArrayList(this.all);
    }

    public Collection<Recipient> getRecipients(RecipientId recipientId) {
        if (recipientId instanceof KeyTransRecipientId) {
            KeyTransRecipientId keyTransRecipientId = (KeyTransRecipientId) recipientId;
            X500Name issuer = keyTransRecipientId.getIssuer();
            byte[] subjectKeyIdentifier = keyTransRecipientId.getSubjectKeyIdentifier();
            if (issuer != null && subjectKeyIdentifier != null) {
                ArrayList arrayList = new ArrayList();
                Collection<Recipient> recipients = getRecipients(new KeyTransRecipientId(issuer, keyTransRecipientId.getSerialNumber()));
                if (recipients != null) {
                    arrayList.addAll(recipients);
                }
                Collection<Recipient> recipients2 = getRecipients(new KeyTransRecipientId(subjectKeyIdentifier));
                if (recipients2 != null) {
                    arrayList.addAll(recipients2);
                }
                return arrayList;
            }
        }
        ArrayList arrayList2 = (ArrayList) this.table.get(recipientId);
        return arrayList2 == null ? new ArrayList() : new ArrayList(arrayList2);
    }

    @Override // org.spongycastle.util.Iterable, java.lang.Iterable
    public Iterator<RecipientInformation> iterator() {
        return getRecipients().iterator();
    }
}
