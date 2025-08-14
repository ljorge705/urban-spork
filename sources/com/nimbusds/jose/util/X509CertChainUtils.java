package com.nimbusds.jose.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

/* loaded from: classes2.dex */
public class X509CertChainUtils {
    public static List<Base64> toBase64List(List<Object> list) throws ParseException {
        if (list == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj == null) {
                throw new ParseException("The X.509 certificate at position " + i + " must not be null", 0);
            }
            if (!(obj instanceof String)) {
                throw new ParseException("The X.509 certificate at position " + i + " must be encoded as a Base64 string", 0);
            }
            linkedList.add(new Base64((String) obj));
        }
        return linkedList;
    }

    public static List<X509Certificate> parse(List<Base64> list) throws ParseException {
        if (list == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                X509Certificate x509Certificate = X509CertUtils.parse(list.get(i).decode());
                if (x509Certificate == null) {
                    throw new ParseException("Invalid X.509 certificate at position " + i, 0);
                }
                linkedList.add(x509Certificate);
            }
        }
        return linkedList;
    }

    public static List<X509Certificate> parse(File file) throws IOException, CertificateException {
        return parse(new String(Files.readAllBytes(file.toPath()), StandardCharset.UTF_8));
    }

    public static List<X509Certificate> parse(String str) throws IOException, CertificateException {
        Object object;
        PEMParser pEMParser = new PEMParser(new StringReader(str));
        LinkedList linkedList = new LinkedList();
        do {
            object = pEMParser.readObject();
            if (object instanceof X509CertificateHolder) {
                linkedList.add(X509CertUtils.parseWithException(((X509CertificateHolder) object).getEncoded()));
            }
        } while (object != null);
        return linkedList;
    }

    public static List<UUID> store(KeyStore keyStore, List<X509Certificate> list) throws KeyStoreException {
        LinkedList linkedList = new LinkedList();
        for (X509Certificate x509Certificate : list) {
            UUID uuidRandomUUID = UUID.randomUUID();
            keyStore.setCertificateEntry(uuidRandomUUID.toString(), x509Certificate);
            linkedList.add(uuidRandomUUID);
        }
        return linkedList;
    }

    private X509CertChainUtils() {
    }
}
