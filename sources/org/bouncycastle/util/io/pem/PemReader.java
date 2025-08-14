package org.bouncycastle.util.io.pem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.bouncycastle.util.encoders.Base64;

/* loaded from: classes4.dex */
public class PemReader extends BufferedReader {
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";

    public PemReader(Reader reader) {
        super(reader);
    }

    private PemObject loadObject(String str) throws IOException {
        String line;
        String str2 = END + str + "-----";
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        while (true) {
            line = readLine();
            if (line == null) {
                break;
            }
            int iIndexOf = line.indexOf(58);
            if (iIndexOf >= 0) {
                arrayList.add(new PemHeader(line.substring(0, iIndexOf), line.substring(iIndexOf + 1).trim()));
            } else {
                if (line.indexOf(str2) == 0) {
                    break;
                }
                stringBuffer.append(line.trim());
            }
        }
        if (line != null) {
            return new PemObject(str, arrayList, Base64.decode(stringBuffer.toString()));
        }
        throw new IOException(str2 + " not found");
    }

    public PemObject readPemObject() throws IOException {
        String line;
        String strTrim;
        int iIndexOf;
        do {
            line = readLine();
            if (line == null) {
                break;
            }
        } while (!line.startsWith(BEGIN));
        if (line == null || (iIndexOf = (strTrim = line.substring(11).trim()).indexOf(45)) <= 0 || !strTrim.endsWith("-----") || strTrim.length() - iIndexOf != 5) {
            return null;
        }
        return loadObject(strTrim.substring(0, iIndexOf));
    }
}
