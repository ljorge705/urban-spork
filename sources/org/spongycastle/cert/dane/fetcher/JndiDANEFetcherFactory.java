package org.spongycastle.cert.dane.fetcher;

import com.facebook.hermes.intl.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.apache.commons.lang3.StringUtils;
import org.spongycastle.cert.dane.DANEEntry;
import org.spongycastle.cert.dane.DANEEntryFetcher;
import org.spongycastle.cert.dane.DANEEntryFetcherFactory;
import org.spongycastle.cert.dane.DANEException;

/* loaded from: classes4.dex */
public class JndiDANEFetcherFactory implements DANEEntryFetcherFactory {
    private static final String DANE_TYPE = "53";
    private List dnsServerList = new ArrayList();
    private boolean isAuthoritative;

    public JndiDANEFetcherFactory setAuthoritative(boolean z) {
        this.isAuthoritative = z;
        return this;
    }

    public JndiDANEFetcherFactory usingDNSServer(String str) {
        this.dnsServerList.add(str);
        return this;
    }

    @Override // org.spongycastle.cert.dane.DANEEntryFetcherFactory
    public DANEEntryFetcher build(final String str) {
        final Hashtable hashtable = new Hashtable();
        hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        hashtable.put("java.naming.authoritative", this.isAuthoritative ? "true" : Constants.CASEFIRST_FALSE);
        if (this.dnsServerList.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = this.dnsServerList.iterator();
            while (it.hasNext()) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(StringUtils.SPACE);
                }
                stringBuffer.append("dns://" + it.next());
            }
            hashtable.put("java.naming.provider.url", stringBuffer.toString());
        }
        return new DANEEntryFetcher() { // from class: org.spongycastle.cert.dane.fetcher.JndiDANEFetcherFactory.1
            @Override // org.spongycastle.cert.dane.DANEEntryFetcher
            public List getEntries() throws DANEException {
                ArrayList arrayList = new ArrayList();
                try {
                    InitialDirContext initialDirContext = new InitialDirContext(hashtable);
                    if (str.indexOf("_smimecert.") > 0) {
                        Attribute attribute = initialDirContext.getAttributes(str, new String[]{JndiDANEFetcherFactory.DANE_TYPE}).get(JndiDANEFetcherFactory.DANE_TYPE);
                        if (attribute != null) {
                            JndiDANEFetcherFactory.this.addEntries(arrayList, str, attribute);
                        }
                    } else {
                        NamingEnumeration namingEnumerationListBindings = initialDirContext.listBindings("_smimecert." + str);
                        while (namingEnumerationListBindings.hasMore()) {
                            DirContext dirContext = (DirContext) ((Binding) namingEnumerationListBindings.next()).getObject();
                            Attribute attribute2 = initialDirContext.getAttributes(dirContext.getNameInNamespace().substring(1, dirContext.getNameInNamespace().length() - 1), new String[]{JndiDANEFetcherFactory.DANE_TYPE}).get(JndiDANEFetcherFactory.DANE_TYPE);
                            if (attribute2 != null) {
                                String nameInNamespace = dirContext.getNameInNamespace();
                                JndiDANEFetcherFactory.this.addEntries(arrayList, nameInNamespace.substring(1, nameInNamespace.length() - 1), attribute2);
                            }
                        }
                    }
                    return arrayList;
                } catch (NamingException e) {
                    throw new DANEException("Exception dealing with DNS: " + e.getMessage(), e);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addEntries(List list, String str, Attribute attribute) throws DANEException, NamingException {
        for (int i = 0; i != attribute.size(); i++) {
            byte[] bArr = (byte[]) attribute.get(i);
            if (DANEEntry.isValidCertificate(bArr)) {
                try {
                    list.add(new DANEEntry(str, bArr));
                } catch (IOException e) {
                    throw new DANEException("Exception parsing entry: " + e.getMessage(), e);
                }
            }
        }
    }
}
